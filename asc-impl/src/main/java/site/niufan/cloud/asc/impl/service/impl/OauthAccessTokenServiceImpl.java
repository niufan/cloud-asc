package site.niufan.cloud.asc.impl.service.impl;

import org.springframework.stereotype.Service;
import site.niufan.cloud.asc.impl.dal.entity.OauthAccessToken;
import site.niufan.cloud.asc.impl.dal.mapper.OauthAccessTokenMapper;
import site.niufan.cloud.asc.impl.service.OauthAccessTokenService;
import site.niufan.common.mybatis.service.impl.CommonServiceImpl;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Fan Niu
 * @since 2018-08-07
 */
@Service("oauthAccessTokenService")
public class OauthAccessTokenServiceImpl extends CommonServiceImpl<OauthAccessTokenMapper, OauthAccessToken> implements OauthAccessTokenService {

}
