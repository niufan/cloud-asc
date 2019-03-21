package site.niufan.cloud.asc.impl.service.impl;

import org.springframework.stereotype.Service;
import site.niufan.cloud.asc.impl.dal.entity.OauthRefreshToken;
import site.niufan.cloud.asc.impl.dal.mapper.OauthRefreshTokenMapper;
import site.niufan.cloud.asc.impl.service.OauthRefreshTokenService;
import site.niufan.common.mybatis.service.impl.CommonServiceImpl;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Fan Niu
 * @since 2018-08-07
 */
@Service("oauthRefreshTokenService")
public class OauthRefreshTokenServiceImpl extends CommonServiceImpl<OauthRefreshTokenMapper, OauthRefreshToken> implements OauthRefreshTokenService {

}
