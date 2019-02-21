package club.wenfan.youtube.validate.filter;

import club.wenfan.youtube.properties.SecurityProperties;
import club.wenfan.youtube.validate.code.ValidateCode;
import club.wenfan.youtube.validate.exception.ValidateCodeException;
import club.wenfan.youtube.validate.processor.ValidateCodeProcessor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author:wenfan
 * @description:
 * @data: 2018/12/31 21:34
 */

public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    private Set<String>urls=new HashSet<>();

    private AntPathMatcher antPathMatcher=new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String [] cofigUrls=StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getSms().getUrl(),",");
        for(String cofigUrl:cofigUrls){
            urls.add(cofigUrl);
            System.out.println(cofigUrl);
        }
        urls.add("/authentication/mobile");
    }

    /**
     *
     *  登陆过滤
     *
     * @author wenfan
     * @date
     * @param
     * @return
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        boolean action=false;
        for(String  url:urls){
            if(antPathMatcher.match(url,httpServletRequest.getRequestURI())){
                action=true;
            }
        }
        if(action){
            try {
                vaildate(new ServletWebRequest(httpServletRequest));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
                return;
            }


        }
            filterChain.doFilter(httpServletRequest,httpServletResponse);

    }

    public void vaildate(ServletWebRequest request) throws ServletRequestBindingException {

        ValidateCode codeInSession =(ValidateCode)sessionStrategy.getAttribute(request,
                ValidateCodeProcessor.SESSION_KEY_PREFIX+"SMS");
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),"smsCode");
        System.out.println("输入的验证码"+codeInRequest);
        System.out.println("真实验证码"+codeInSession.getCode());


        if(StringUtils.isBlank(codeInRequest)){
            throw new ValidateCodeException("验证码为空");
        }
        if(codeInSession.getCode()==null){
            throw new ValidateCodeException("验证码不存在");
        }
        if(codeInSession.isExpired()){
            sessionStrategy.removeAttribute(request,
                    ValidateCodeProcessor.SESSION_KEY_PREFIX+"SMS");
            throw new ValidateCodeException("验证码已过期");
        }
        if(!codeInSession.getCode().equals(codeInRequest)){
            throw new ValidateCodeException("验证码不正确");
        }

        //验证通过后移除session中的验证码
        sessionStrategy.removeAttribute(request,
                ValidateCodeProcessor.SESSION_KEY_PREFIX+"SMS");

    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }
}
