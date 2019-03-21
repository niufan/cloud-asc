package site.niufan.cloud.asc.dto;

/**
 * @author Fan Niu
 * @since 2018/8/27
 */
public class AuthenticationDto {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
