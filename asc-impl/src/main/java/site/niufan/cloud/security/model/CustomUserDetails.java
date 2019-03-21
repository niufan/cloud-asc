package site.niufan.cloud.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Fan Niu
 * @since 2018/8/4
 */
public class CustomUserDetails extends User {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CustomUserDetails(String id, String username, String password) {
        this(username, password, Collections.emptyList());
        this.id = id;
    }

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this(username, password, true, true, true, true, authorities);
    }

    public CustomUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public static CustomUserDetails fromUser(site.niufan.cloud.asc.impl.dal.entity.User user) {
        return new CustomUserDetails(user.getId(), user.getUsername(), user.getPassword());
    }


    public static site.niufan.cloud.asc.impl.dal.entity.User toUser(CustomUserDetails customUserDetails) {
        site.niufan.cloud.asc.impl.dal.entity.User user = new site.niufan.cloud.asc.impl.dal.entity.User();
        user.setUsername(customUserDetails.getUsername());
        user.setPassword(customUserDetails.getPassword());
        return user;
    }
}
