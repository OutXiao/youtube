package club.wenfan.youtube.vo;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/16 10:41
 */
public class RestResponse {


    private Object principal;

    private String token;

    public RestResponse(Object principal,String token){
        this.principal = principal;
        this.token = token;
    }

    public Object getPrincipal() {
        return principal;
    }

    public void setPrincipal(Object principal) {
        this.principal = principal;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
