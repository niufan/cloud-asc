package site.niufan.cloud.asc;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.niufan.cloud.asc.dto.AuthenticationDto;

/**
 * @author Fan Niu
 * @since 2018/8/27
 */
public interface AuthenticationApi {

    @PostMapping("/login")
    OAuth2AccessToken login(@RequestBody AuthenticationDto authenticationDto);
}
