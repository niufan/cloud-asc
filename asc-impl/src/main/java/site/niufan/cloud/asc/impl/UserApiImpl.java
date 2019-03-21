package site.niufan.cloud.asc.impl;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import site.niufan.cloud.asc.UserApi;
import site.niufan.cloud.asc.dto.UserDto;
import site.niufan.cloud.asc.impl.dal.entity.User;
import site.niufan.cloud.asc.impl.service.UserService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Fan Niu
 * @since 2018/8/3
 */
@RestController
public class UserApiImpl implements UserApi {

    @Resource
    private UserService userService;

    @Override
    public UserDto get(@PathVariable String id) {
        UserDto userDto = new UserDto();
        User user = userService.selectById(id);
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    @Override
    public Map<String, Object> user(OAuth2Authentication user) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", user.getPrincipal());
        userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getAuthorities()));
        return userInfo;
    }
}
