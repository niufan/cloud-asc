package site.niufan.cloud.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author Fan Niu
 * @since 2018/8/5
 */
@Configuration
@EnableResourceServer
public class CustomResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**/*swagger*/**", "/v2/api-docs").permitAll()
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/**/favicon.ico").permitAll()
                .anyRequest().authenticated();
    }

}
