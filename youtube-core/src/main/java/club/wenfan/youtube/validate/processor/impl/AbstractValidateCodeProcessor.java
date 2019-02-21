package club.wenfan.youtube.validate.processor.impl;

import club.wenfan.youtube.validate.ValidateCodeType;
import club.wenfan.youtube.validate.code.ValidateCode;
import club.wenfan.youtube.validate.code.ValidateCodeGenerator;
import club.wenfan.youtube.validate.exception.ValidateCodeException;
import club.wenfan.youtube.validate.processor.ValidateCodeProcessor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *
 * @author wenfan
 * @date
 * @param
 * @return
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    /**
     *
     * @author wenfan
     * @date
     * @param
     * @return
     */
    @Override
    public void create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        C validateCode = generate(request);
        System.out.println(request.getRequestURI());
        System.out.println(validateCode.getCode());
        save(request, validateCode);
        send(request,response, validateCode);
    }

    /**
     * 生成校验码
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    private C generate(HttpServletRequest request)  {
        String type = getValidateCodeType(request).toString().toLowerCase();
        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
        if (validateCodeGenerator == null) {
            throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
        }
        return (C) validateCodeGenerator.CreateCode(request);
    }

    /**
     * 保存校验码
     *
     * @param request
     * @param validateCode
     */
    private void save(HttpServletRequest request, C validateCode) {
        ValidateCode code = new ValidateCode(validateCode.getCode(),validateCode.getExpireTime());
        System.out.println(getSessionKey(request));
        request.getSession(true).setAttribute(getSessionKey(request), code);
        System.out.println(request.getSession().getAttribute(getSessionKey(request)));
    }

    /**
     * 构建验证码放入session时的key
     *
     * @param request
     * @return
     */
    private String getSessionKey(HttpServletRequest request) {
        return SESSION_KEY_PREFIX + getValidateCodeType(request).toString().toUpperCase();
    }

    /**
     * 发送校验码，由子类实现
     *
     * @param request
     * @param validateCode
     * @throws Exception
     */
    protected abstract void send(HttpServletRequest request,HttpServletResponse response, C validateCode) throws Exception;

    /**
     * 根据请求的url获取校验码的类型
     *
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void validate(HttpServletRequest request,HttpServletResponse response) {

        ValidateCodeType processorType = getValidateCodeType(request);
        String sessionKey = getSessionKey(request);
        System.out.println("sessionKey="+sessionKey);
        C codeInSession = (C) request.getSession(false).getAttribute(sessionKey);
        System.out.println(codeInSession==null?"codeinsession为null":"codeinsession不为null");
        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request,
                    processorType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(processorType + "验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException(processorType + "验证码不存在");
        }

        if (codeInSession.isExpired()) {
            request.getSession().removeAttribute(sessionKey);
            throw new ValidateCodeException(processorType + "验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException(processorType + "验证码不匹配");
        }
        request.getSession().removeAttribute(sessionKey);
    }

}
