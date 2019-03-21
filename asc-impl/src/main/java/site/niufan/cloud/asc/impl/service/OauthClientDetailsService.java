package site.niufan.cloud.asc.impl.service;


import site.niufan.cloud.asc.impl.dal.entity.OauthClientDetails;
import site.niufan.common.mybatis.service.CommonService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Fan Niu
 * @since 2018-08-07
 */
public interface OauthClientDetailsService extends CommonService<OauthClientDetails> {

    OauthClientDetails loadClientByClientId(String clientId);

    void addClientDetails(OauthClientDetails clientDetails);

    void updateClientDetails(OauthClientDetails clientDetails);

    void updateClientSecret(String clientId, String secret);

    void removeClientDetails(String clientId);

    List<OauthClientDetails> listClientDetails();
}
