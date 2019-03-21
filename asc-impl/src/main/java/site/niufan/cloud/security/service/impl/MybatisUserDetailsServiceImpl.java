package site.niufan.cloud.security.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import site.niufan.cloud.asc.impl.dal.entity.User;
import site.niufan.cloud.asc.impl.service.UserService;
import site.niufan.cloud.security.model.CustomUserDetails;

import javax.annotation.Resource;

/**
 * @author Fan Niu
 * @since 2018/8/4
 */
@Service("mybatisUserDetailsService")
public class MybatisUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.selectByUsername(username);
        return CustomUserDetails.fromUser(user);
    }
}
