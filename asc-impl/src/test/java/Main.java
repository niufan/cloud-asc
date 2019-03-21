import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

/**
 * @author Fan Niu
 * @since 2018/8/4
 */
public class Main {
    public static void main(String[] args) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        System.out.println(antPathMatcher.match("/*swagger*/**", "/swagger-ui.html"));
        System.out.println(antPathMatcher.match("/*swagger*/*", "/swagger/-ui.html"));
        System.out.println(antPathMatcher.match("/**/*swagger*/**", "/dddd/sdfs/sdfsd/asdfasdfswaggersdfasdfs/-ui/.html"));

        String password;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //System.out.println(bCryptPasswordEncoder.encode("niufan"));
        System.out.println(bCryptPasswordEncoder.encode("123456"));
        boolean match;
        match = bCryptPasswordEncoder.matches("123456", "$2a$10$J1CNlEgUuG0DUyVs4Yarmeg390Qkq4AJjVlbCu2t763KvlYBeGbWO");
        System.out.println(match);
        //match = bCryptPasswordEncoder.matches("niufan", "$2a$10$Qx05IZPiUL2W3/tLelZszewrveXQ1lnK2U3ZBVLdRvrZTxS3/ZjZG");

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        password = passwordEncoder.encode("niufan");
        System.out.println(password);
        System.out.println(match);
    }
}
