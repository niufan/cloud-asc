package site.niufan.cloud.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import site.niufan.cloud.asc.impl.dal.entity.OauthClientDetails;

import java.util.*;

/**
 * @author Fan Niu
 * @since 2018/8/7
 */
public class CustomOauthClientDetails implements ClientDetails {

    private static final JsonMapper mapper = createJsonMapper();

    private String clientId;
    private Set<String> resourceIds = Collections.emptySet();
    private String clientSecret;
    private Set<String> scope = Collections.emptySet();
    private Set<String> authorizedGrantTypes = Collections.emptySet();
    private Set<String> registeredRedirectUris;
    private List<GrantedAuthority> authorities = Collections.emptyList();
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    private Map<String, Object> additionalInformation = new LinkedHashMap<String, Object>();
    private Set<String> autoApproveScopes;

    public static List<ClientDetails> from(List<OauthClientDetails> oauthClientDetails) {
        List<ClientDetails> customOauthClientDetails = new ArrayList<>();
        for (OauthClientDetails temp: oauthClientDetails) {
            customOauthClientDetails.add(from(temp));
        }
        return customOauthClientDetails;
    }

    public static CustomOauthClientDetails from(OauthClientDetails oauthClientDetails) {
        CustomOauthClientDetails customOauthClientDetails = new CustomOauthClientDetails();
        customOauthClientDetails.setClientId(oauthClientDetails.getClientId());
        customOauthClientDetails.setClientSecret(oauthClientDetails.getClientSecret());
        customOauthClientDetails.setAccessTokenValiditySeconds(oauthClientDetails.getAccessTokenValidity());
        customOauthClientDetails.setRefreshTokenValiditySeconds(oauthClientDetails.getRefreshTokenValidity());
        String additionalInformationJson = oauthClientDetails.getAdditionalInformation();
        if (StringUtils.hasText(additionalInformationJson)) {
            try {
                @SuppressWarnings("unchecked")
                Map<String, Object> additionalInformation = mapper.read(additionalInformationJson, Map.class);
                customOauthClientDetails.setAdditionalInformation(additionalInformation);
            } catch (Exception e) {
                throw new IllegalArgumentException("Could not decode JSON for additional information: " + additionalInformationJson, e);
            }
        }

        String resourceIdsStr = oauthClientDetails.getResourceIds();
        if (StringUtils.hasText(resourceIdsStr)) {
            Set<String> resourceIds = StringUtils.commaDelimitedListToSet(resourceIdsStr);
            if (!resourceIds.isEmpty()) {
                customOauthClientDetails.setResourceIds(resourceIds);
            }
        }
        String scopesStr = oauthClientDetails.getScope();
        if (StringUtils.hasText(scopesStr)) {
            Set<String> scopes = StringUtils.commaDelimitedListToSet(scopesStr);
            if (!scopes.isEmpty()) {
                customOauthClientDetails.setScope(scopes);
            }
        }
        String authorizedGrantTypesStr = oauthClientDetails.getAuthorizedGrantTypes();
        if (StringUtils.hasText(authorizedGrantTypesStr)) {
            customOauthClientDetails.setAuthorizedGrantTypes(StringUtils
                    .commaDelimitedListToSet(authorizedGrantTypesStr));
        } else {
            customOauthClientDetails.setAuthorizedGrantTypes( new HashSet<>(Arrays.asList(
                    "authorization_code", "refresh_token")));
        }
        String authoritiesStr = oauthClientDetails.getAuthorities();
        if (StringUtils.hasText(authoritiesStr)) {
            customOauthClientDetails.setAuthorities(AuthorityUtils
                    .commaSeparatedStringToAuthorityList(authoritiesStr));
        }
        String webServerRedirectUri = oauthClientDetails.getWebServerRedirectUri();
        if (StringUtils.hasText(webServerRedirectUri)) {
            customOauthClientDetails.setRegisteredRedirectUris(StringUtils
                    .commaDelimitedListToSet(webServerRedirectUri));
        }

        return customOauthClientDetails;
    }

    public static OauthClientDetails toOauthClientDetails(ClientDetails customOauthClientDetails) {
        OauthClientDetails oauthClientDetails = new OauthClientDetails();
        oauthClientDetails.setClientId(customOauthClientDetails.getClientId());
        oauthClientDetails.setClientSecret(customOauthClientDetails.getClientSecret());
        oauthClientDetails.setAccessTokenValidity(customOauthClientDetails.getAccessTokenValiditySeconds());
        oauthClientDetails.setRefreshTokenValidity(customOauthClientDetails.getRefreshTokenValiditySeconds());
        Map<String, Object> additionalInformation = customOauthClientDetails.getAdditionalInformation();
        try {
            oauthClientDetails.setAdditionalInformation(mapper.write(customOauthClientDetails.getAdditionalInformation()));
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not encode JSON for additional information: " + additionalInformation, e);
        }
        Set<String> resourceIds = customOauthClientDetails.getResourceIds();
        if (!CollectionUtils.isEmpty(resourceIds)) {
            String resourceIdsStr = StringUtils.collectionToCommaDelimitedString(resourceIds);
            oauthClientDetails.setResourceIds(resourceIdsStr);
        }
        Set<String> scopes = customOauthClientDetails.getScope();
        if (!CollectionUtils.isEmpty(scopes)) {
            oauthClientDetails.setScope(StringUtils.collectionToCommaDelimitedString(scopes));
        }
        Set<String> authorizedGrantTypes = customOauthClientDetails.getAuthorizedGrantTypes();
        if (!CollectionUtils.isEmpty(authorizedGrantTypes)) {
            oauthClientDetails.setAuthorizedGrantTypes(StringUtils.collectionToCommaDelimitedString(authorizedGrantTypes));
        }

        Collection<GrantedAuthority> authorities = customOauthClientDetails.getAuthorities();
        if (!CollectionUtils.isEmpty(authorities)) {
            oauthClientDetails.setAuthorities(StringUtils
                    .collectionToCommaDelimitedString(authorities));
        }
        Set<String> registeredRedirectUris = customOauthClientDetails.getRegisteredRedirectUri();
        if (!CollectionUtils.isEmpty(registeredRedirectUris)) {
            oauthClientDetails.setWebServerRedirectUri(StringUtils
                    .collectionToCommaDelimitedString(registeredRedirectUris));
        }

        return oauthClientDetails;
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(Set<String> resourceIds) {
        this.resourceIds = resourceIds;
    }


    @Override
    public boolean isSecretRequired() {
        return this.clientSecret != null;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Override
    public boolean isScoped() {
        return this.scope != null && !this.scope.isEmpty();
    }

    @Override
    public Set<String> getScope() {
        return scope;
    }

    public void setScope(Set<String> scope) {
        this.scope = scope;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(Set<String> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return registeredRedirectUris;
    }

    public void setRegisteredRedirectUris(Set<String> registeredRedirectUris) {
        this.registeredRedirectUris = registeredRedirectUris == null ? null
                : new LinkedHashSet<>(registeredRedirectUris);
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        if (autoApproveScopes == null) {
            return false;
        }
        for (String auto : autoApproveScopes) {
            if (auto.equals("true") || scope.matches(auto)) {
                return true;
            }
        }
        return false;
    }

    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public Set<String> getAutoApproveScopes() {
        return autoApproveScopes;
    }

    public void setAutoApproveScopes(Set<String> autoApproveScopes) {
        this.autoApproveScopes = autoApproveScopes;
    }


    interface JsonMapper {
        String write(Object input) throws Exception;

        <T> T read(String input, Class<T> type) throws Exception;
    }

    private static JsonMapper createJsonMapper() {
        if (ClassUtils.isPresent("org.codehaus.jackson.map.ObjectMapper", null)) {
            return new JacksonMapper();
        }
        else if (ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", null)) {
            return new Jackson2Mapper();
        }
        return new NotSupportedJsonMapper();
    }

    private static class JacksonMapper implements JsonMapper {
        private org.codehaus.jackson.map.ObjectMapper mapper = new org.codehaus.jackson.map.ObjectMapper();

        @Override
        public String write(Object input) throws Exception {
            return mapper.writeValueAsString(input);
        }

        @Override
        public <T> T read(String input, Class<T> type) throws Exception {
            return mapper.readValue(input, type);
        }
    }

    private static class Jackson2Mapper implements JsonMapper {
        private com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();

        @Override
        public String write(Object input) throws Exception {
            return mapper.writeValueAsString(input);
        }

        @Override
        public <T> T read(String input, Class<T> type) throws Exception {
            return mapper.readValue(input, type);
        }
    }

    private static class NotSupportedJsonMapper implements JsonMapper {
        @Override
        public String write(Object input) throws Exception {
            throw new UnsupportedOperationException(
                    "Neither Jackson 1 nor 2 is available so JSON conversion cannot be done");
        }

        @Override
        public <T> T read(String input, Class<T> type) throws Exception {
            throw new UnsupportedOperationException(
                    "Neither Jackson 1 nor 2 is available so JSON conversion cannot be done");
        }
    }
}
