package club.wenfan.youtube.validate.code.ValidateGeneratorImpl;

import club.wenfan.youtube.properties.SecurityProperties;
import club.wenfan.youtube.validate.code.ValidateCode;
import club.wenfan.youtube.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/1 18:33
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode CreateCode(HttpServletRequest request) {
        String code=RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getCodeCount());
        return new ValidateCode(code,securityProperties.getCode().getSms().getExpiredTime());
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
