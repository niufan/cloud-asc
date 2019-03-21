package site.niufan.cloud.asc.impl.dal.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import site.niufan.cloud.asc.impl.dal.entity.User;
import site.niufan.common.mybatis.dal.mapper.CommonMapper;

/**
 * @author Fan Niu
 * @since 2018/8/3
 */
@Repository("userMapper")
public interface UserMapper extends CommonMapper<User> {

    int changePassword(@Param("username") String username, @Param("oldPassword") String oldPassword
            , @Param("newPassword") String newPassword);
}
