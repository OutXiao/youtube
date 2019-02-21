package club.wenfan.youtube.config;

import club.wenfan.youtube.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author:wenfan
 * @description:  登陆成功处理
 * @data: 2019/2/7 13:59
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected AuthenticationFailureHandler myAuthenticationFailurelHandler;

    @Autowired
    protected AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    protected void applyAuthenticationConfig(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_LOGIN_PAGE_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .failureHandler(myAuthenticationFailurelHandler)
                .successHandler(myAuthenticationSuccessHandler);

    }
}
