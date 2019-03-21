package site.niufan.cloud.asc.impl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.niufan.cloud.asc.impl.dal.entity.User;
import site.niufan.cloud.asc.impl.dal.mapper.UserMapper;
import site.niufan.cloud.asc.impl.service.UserService;
import site.niufan.common.mybatis.service.impl.CommonServiceImpl;

import java.util.List;

/**
 * @author Fan Niu
 * @since 2018/8/3
 */
@Service("userService")
public class UserServiceImpl extends CommonServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User selectByUsername(String username) {
        return selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
    }

    @Override
    @Transactional
    public void updateByUsername(User user) {
        // 根据用户名更新用户信息
        update(user, new QueryWrapper<User>().lambda().eq(User::getUsername, user.getUsername()));
    }

    @Override
    @Transactional
    public void deleteByUsername(String username) {
        delete(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
    }

    @Override
    @Transactional
    public void changePassword(String username, String oldPassword, String newPassword) {
        int count = baseMapper.changePassword(username, oldPassword, newPassword);
        if (count <= 0) {
            throw new RuntimeException("用户名或密码错误");
        }
    }

    @Override
    public boolean userExists(String username) {
        List<User> users = baseMapper.selectList(new QueryWrapper<User>().lambda().select(User::getUsername)
                .eq(User::getUsername, username));
        int size = 0;
        if (users != null && (size = users.size()) > 1) {
            throw new RuntimeException("用户名不唯一");
        }
        return size == 1;
    }
}
