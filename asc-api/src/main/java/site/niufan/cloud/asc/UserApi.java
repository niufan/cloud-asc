package site.niufan.cloud.asc;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import site.niufan.cloud.asc.dto.UserDto;

import java.util.Map;

/**
 * @author Fan Niu
 * @since 2018/8/3
 */
@RequestMapping(value = {"/user", "/auth/user"})
public interface UserApi {

    @GetMapping("/{id}")
    UserDto get(@PathVariable String id);

    Map<String, Object> user(OAuth2Authentication user);
}
