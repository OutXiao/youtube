package club.wenfan.youtube.validate.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/21 18:34
 */
@Component
public class SmsCodeAuthenticationSecurityConfig  extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler wenfanAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler wenfanAuthenticationFailureHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        /* 将SmsCodeAuthenticationFilter 托管到和
            UsernamePasswordAuthenticationFilter一样的AuthenticationManager
        */
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(wenfanAuthenticationFailureHandler);
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(wenfanAuthenticationSuccessHandler);

        SmsCodeAuthenticationProvider provider=new SmsCodeAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);  //这里一定要注入userDetailService

        http.authenticationProvider(provider)
                .addFilterAfter(smsCodeAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
    }
}
