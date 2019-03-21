package site.niufan.cloud.asc.impl.dal.entity;

import site.niufan.common.mybatis.dal.entity.CommonEntity;

/**
 * @author Fan Niu
 * @since 2018/8/3
 */
public class User extends CommonEntity<User> {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
