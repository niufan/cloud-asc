package site.niufan.cloud.asc.impl.service;

import site.niufan.cloud.asc.impl.dal.entity.User;
import site.niufan.common.mybatis.service.CommonService;

import java.util.List;

/**
 * @author Fan Niu
 * @since 2018/8/3
 */
public interface UserService extends CommonService<User> {

    User selectByUsername(String username);

    void updateByUsername(User user);

    void deleteByUsername(String username);

    void changePassword(String username, String oldPassword, String newPassword);

    boolean userExists(String username);
}
