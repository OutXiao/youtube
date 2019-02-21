package club.wenfan.youtube.validate.processor;

import club.wenfan.youtube.properties.SecurityConstants;
import club.wenfan.youtube.validate.code.ValidateCode;
import club.wenfan.youtube.validate.processor.impl.AbstractValidateCodeProcessor;
import club.wenfan.youtube.validate.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/21 15:13
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(HttpServletRequest request, HttpServletResponse response, ValidateCode vaildateCode) throws Exception {
        String paramName=SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        String mobile=ServletRequestUtils.getRequiredStringParameter(request,paramName);
        smsCodeSender.send(mobile,vaildateCode.getCode());
    }
}
