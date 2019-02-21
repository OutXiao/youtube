package club.wenfan.youtube.validate.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/21 16:19
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken=(SmsCodeAuthenticationToken)authentication;
        // 将未认证的手机号作为 验证信息
        // 这里可以通过配置持久层从数据库查找手机号

        UserDetails user=userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());

        if(user==null){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        //  拿到认证的用户token
        SmsCodeAuthenticationToken authenticationTokenResult=new SmsCodeAuthenticationToken(user,user.getAuthorities());
        authenticationTokenResult.setDetails(authenticationToken.getDetails());
        return authenticationTokenResult;
    }


    /**
     *  判断authentication是不是SmsCodeAuthenticationToken类型的
     * @author wenfan
     * @date
     * @param
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
