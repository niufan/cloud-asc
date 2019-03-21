package site.niufan.cloud.security.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @author Fan Niu
 * @since 2018/8/4
 */
@Configuration
@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService mybatisUserDetailsManager;

    public CustomSecurityConfig() {
        super();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService(){
        return mybatisUserDetailsManager;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(mybatisUserDetailsManager);
        return new ProviderManager(Collections.singletonList(daoAuthenticationProvider));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                // swagger ui
                .antMatchers("/**/*swagger*/**", "/v2/api-docs").permitAll()
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/**/favicon.ico").permitAll()
                .and().formLogin().loginPage("http://localhost/failure/failure_code_tree.html")
                .and().authorizeRequests().anyRequest().authenticated()
        ;
    }
}
