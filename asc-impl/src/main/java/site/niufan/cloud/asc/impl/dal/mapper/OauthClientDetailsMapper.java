package site.niufan.cloud.asc.impl.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import site.niufan.cloud.asc.impl.dal.entity.OauthClientDetails;
import site.niufan.common.mybatis.dal.mapper.CommonMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Fan Niu
 * @since 2018-08-07
 */
public interface OauthClientDetailsMapper extends CommonMapper<OauthClientDetails> {

    int updateClientSecret(@Param("clientId") String clientId, @Param("secret") String secret);
}
