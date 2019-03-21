package site.niufan.cloud.security.service.impl;

import org.springframework.security.oauth2.provider.*;
import org.springframework.stereotype.Service;
import site.niufan.cloud.asc.impl.service.OauthClientDetailsService;
import site.niufan.cloud.security.model.CustomOauthClientDetails;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Fan Niu
 * @since 2018/8/7
 */
@Service("mybatisClientDetailsService")
public class MybatisClientDetailsServiceImpl implements ClientDetailsService, ClientRegistrationService {

    @Resource
    private OauthClientDetailsService oauthClientDetailsService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return CustomOauthClientDetails.from(oauthClientDetailsService.loadClientByClientId(clientId));
    }

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
        oauthClientDetailsService.addClientDetails(CustomOauthClientDetails.toOauthClientDetails(clientDetails));
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        oauthClientDetailsService.updateClientDetails(CustomOauthClientDetails.toOauthClientDetails(clientDetails));

    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        oauthClientDetailsService.updateClientSecret(clientId, secret);
    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        oauthClientDetailsService.removeClientDetails(clientId);
    }

    @Override
    public List<ClientDetails> listClientDetails() {
        return CustomOauthClientDetails.from(oauthClientDetailsService.listClientDetails());
    }
}
