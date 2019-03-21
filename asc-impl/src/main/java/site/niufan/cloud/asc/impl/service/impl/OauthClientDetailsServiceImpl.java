package site.niufan.cloud.asc.impl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.niufan.cloud.asc.impl.dal.entity.OauthClientDetails;
import site.niufan.cloud.asc.impl.dal.mapper.OauthClientDetailsMapper;
import site.niufan.cloud.asc.impl.service.OauthClientDetailsService;
import site.niufan.common.mybatis.service.impl.CommonServiceImpl;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Fan Niu
 * @since 2018-08-07
 */
@Service("oauthClientDetailsService")
public class OauthClientDetailsServiceImpl extends CommonServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements OauthClientDetailsService {

    @Override
    public OauthClientDetails loadClientByClientId(String clientId) {
        return selectOne(new QueryWrapper<OauthClientDetails>().lambda().eq(OauthClientDetails::getClientId, clientId));
    }

    @Override
    @Transactional
    public void addClientDetails(OauthClientDetails clientDetails) {
        insert(clientDetails);
    }

    @Override
    @Transactional
    public void updateClientDetails(OauthClientDetails clientDetails) {
        update(clientDetails, new QueryWrapper<OauthClientDetails>().lambda().eq(OauthClientDetails::getClientId, clientDetails.getClientId()));
    }

    @Override
    @Transactional
    public void updateClientSecret(String clientId, String secret) {
        baseMapper.updateClientSecret(clientId, secret);
    }

    @Override
    @Transactional
    public void removeClientDetails(String clientId) {
        delete(new QueryWrapper<OauthClientDetails>().lambda().eq(OauthClientDetails::getClientId, clientId));
    }

    @Override
    public List<OauthClientDetails> listClientDetails() {
        return selectList(new QueryWrapper<OauthClientDetails>().lambda());
    }
}
