package club.wenfan.youtube.properties;

/**
 * @author:wenfan
 * @description:  jwt 配置
 * @data: 2019/2/8 13:54
 */
public class JwtProperties {

    // token加密密钥
    private  String secret;
    // 过期时间
    private Long expiration;
    // 请求头里携带token的key
    private String head;
    // token 头
    private String tokenHead;
    // 验证token
    private String path;

    private String refresh;
    // 注册路径
    private String register;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getTokenHead() {
        return tokenHead;
    }

    public void setTokenHead(String tokenHead) {
        this.tokenHead = tokenHead;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }
}
