package site.niufan.cloud.asc.impl.dal.entity;

import site.niufan.common.mybatis.dal.entity.CommonEntity;

import java.sql.Blob;

/**
 * <p>
 * 
 * </p>
 *
 * @author Fan Niu
 * @since 2018-08-07
 */
public class OauthRefreshToken extends CommonEntity<OauthRefreshToken> {

    private static final long serialVersionUID = 1L;

    private String tokenId;
    private String token;
    private String authentication;


    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

}
