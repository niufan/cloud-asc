package site.niufan.cloud.asc.impl.dal.entity;

import site.niufan.common.mybatis.dal.entity.CommonEntity;

import java.sql.Blob;

/**
 * <p>
 * 
 * </p>
 *
 * @author Fan Niu
 * @since 2018-08-07
 */
public class OauthAccessToken extends CommonEntity<OauthAccessToken> {

    private static final long serialVersionUID = 1L;

    private String tokenId;
    private String token;
    private String authenticationId;
    private String userName;
    private String clientId;
    private String authentication;
    private String refreshToken;


    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
