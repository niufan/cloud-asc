package site.niufan.cloud.security.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import site.niufan.cloud.asc.impl.dal.entity.User;
import site.niufan.cloud.asc.impl.service.UserService;
import site.niufan.cloud.security.model.CustomUserDetails;

import javax.annotation.Resource;

/**
 * @author Fan Niu
 * @since 2018/8/4
 */
@Service("mybatisUserDetailsManager")
public class MybatisUserDetailsManagerImpl extends MybatisUserDetailsServiceImpl implements UserDetailsManager {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserService userService;

    @Override
    public void createUser(UserDetails user) {
        userService.insert(toUser(user));
    }

    @Override
    public void updateUser(UserDetails user) {
        userService.updateByUsername(toUser(user));
    }

    @Override
    public void deleteUser(String username) {
        userService.deleteByUsername(username);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

        if (currentUser == null) {
            throw new AccessDeniedException(
                    "Can't change password as no Authentication object found in context "
                            + "for current user.");
        }

        String username = currentUser.getName();

        logger.debug("Changing password for user '" + username + "'");

        userService.changePassword(username, oldPassword, newPassword);

        SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(currentUser));

    }

    private Authentication createNewAuthentication(Authentication currentAuth) {
        UserDetails user = loadUserByUsername(currentAuth.getName());

        UsernamePasswordAuthenticationToken newAuthentication = new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities());
        newAuthentication.setDetails(currentAuth.getDetails());

        return newAuthentication;
    }

    @Override
    public boolean userExists(String username) {
        return userService.userExists(username);
    }

    private User toUser(UserDetails user) {
        return CustomUserDetails.toUser((CustomUserDetails) user);
    }
}
