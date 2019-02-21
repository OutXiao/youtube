package club.wenfan.youtube.validate.filter;

import club.wenfan.youtube.properties.SecurityConstants;
import club.wenfan.youtube.properties.SecurityProperties;
import club.wenfan.youtube.validate.ValidateCodeProcessorHolder;
import club.wenfan.youtube.validate.ValidateCodeType;
import club.wenfan.youtube.validate.exception.ValidateCodeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author:wenfan
 * @description:
 * @data: 2018/12/31 21:34
 */
@Component("vaildateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    /**
     *  验证码失败校验处理
     */
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;


    /**
     * 存放所有需要校验验证码的url
     */
    private Map<String,ValidateCodeType> urlMap = new HashMap<>();

    private AntPathMatcher antPathMatcher=new AntPathMatcher();

    /**
     *  初始化需要拦截的url配置信息
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,ValidateCodeType.IMAGE);
        addUrlToMap(securityProperties.getCode().getImg().getUrl(),ValidateCodeType.IMAGE);

        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,ValidateCodeType.SMS);
        addUrlToMap(securityProperties.getCode().getSms().getUrl(),ValidateCodeType.SMS);
    }

    /**
     * 将需要拦截的配置放入map
     */
    private void addUrlToMap(String urlString,ValidateCodeType type){
        if(StringUtils.isNotBlank(urlString)){
            String [] urls=StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString,",");
            for(String url:urls){
                urlMap.put(url,type);
                System.out.println(url);
            }
        }
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
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        ValidateCodeType type=getValidateCodeType(request);
        if(type !=null){
            logger.info("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型" + type);
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type).validate(request,response);
                logger.info("验证码通过");
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
            filterChain.doFilter(request,response);
    }

    /**
     *  获取校验码的类型，如果当前请求不需要校验，则返回null
     * @author wenfan
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request){
        ValidateCodeType result = null;
        if(!StringUtils.equalsIgnoreCase(request.getMethod(),"get")){
            Set<String>urls=urlMap.keySet();
            for(String url:urls){
                if(antPathMatcher.match(url,request.getRequestURI())){
                    result=urlMap.get(url);
                }
            }
        }
        return result;
    }

}
