package site.niufan.cloud.asc.impl.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis 配置
 * @author Fan Niu
 * @since 2018/3/2
 */
@Configuration
@MapperScan("site.niufan.cloud.asc.impl.dal.mapper")
public class MybatisPlusConfig {

    /**
     * 分页拦截器配置
     * @return 分页拦截器
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLocalPage(true);
        return paginationInterceptor;
    }

}
