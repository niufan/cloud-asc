package site.niufan.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 应用启动类
 * @author Fan Niu
 * @since 2018/8/3
 */
@EnableSwagger2
@EnableZuulProxy
@SpringBootApplication
public class AscApplication {

    public static void main(String[] args) {
        SpringApplication.run(AscApplication.class, args);
    }
}
