package club.wenfan.youtube.validate.mobile;

import club.wenfan.youtube.properties.SecurityConstants;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/21 16:04
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    private String mobileParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;

    // 短信验证仅为post请求
    private boolean postOnly = true;

    public SmsCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, "POST"));
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String mobile = this.obtainMobile(request);
            if (mobile == null) {
                mobile = "";
            }

            mobile = mobile.trim();  //去掉两边的空字符串
            // SmsCodeAuthenticationToken (未认证)
            SmsCodeAuthenticationToken token = new SmsCodeAuthenticationToken(mobile);
            this.setDetails(request, token);
            //让AuthenticationManager 去执行 SmsCodeAuthenticationToken
            return this.getAuthenticationManager().authenticate(token);
        }
    }

    /**
     *  从请求中拿到 mobile 的值
     */
    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(mobileParameter);
    }

    protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken token) {
        token.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void SetMobileParameter(String mobileParameter) {
        Assert.hasText(mobileParameter, "mobileParameter parameter must not be empty or null");
        this.mobileParameter = mobileParameter;
    }


    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String GetMobileParameter() {
        return this.mobileParameter;
    }

}
