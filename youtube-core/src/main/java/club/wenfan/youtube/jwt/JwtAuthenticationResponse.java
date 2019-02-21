package club.wenfan.youtube.jwt;

import java.io.Serializable;

/**
 * @author:wenfan
 * @description:
 * @data: 2018/8/28
 */
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
