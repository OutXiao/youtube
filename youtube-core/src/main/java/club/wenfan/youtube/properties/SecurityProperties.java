package club.wenfan.youtube.properties;

import club.wenfan.youtube.validate.code.ValidateCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/7 13:51
 */
@ConfigurationProperties(prefix = "club.wenfan.youtube")
public class SecurityProperties {

    private CosProperties cos = new CosProperties();

    private BrowserProperties browser = new BrowserProperties();

    private JwtProperties jwt = new JwtProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public JwtProperties getJwt() {
        return jwt;
    }

    public void setJwt(JwtProperties jwt) {
        this.jwt = jwt;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public CosProperties getCos() {
        return cos;
    }

    public void setCos(CosProperties cos) {
        this.cos = cos;
    }
}
