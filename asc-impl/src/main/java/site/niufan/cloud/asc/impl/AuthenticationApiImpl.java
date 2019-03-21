package site.niufan.cloud.asc.impl;

import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.niufan.cloud.asc.AuthenticationApi;
import site.niufan.cloud.asc.dto.AuthenticationDto;

import javax.annotation.Resource;

/**
 * @author Fan Niu
 * @since 2018/8/27
 */
@RestController
public class AuthenticationApiImpl implements AuthenticationApi {

    @Resource
    private OAuth2ClientProperties oAuth2ClientProperties;

    @Override
    public OAuth2AccessToken login(@RequestBody AuthenticationDto authenticationDto) {
        oAuth2ClientProperties.getClientId();

        return null;
    }
}
