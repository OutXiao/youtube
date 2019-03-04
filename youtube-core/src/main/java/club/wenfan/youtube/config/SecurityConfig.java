package club.wenfan.youtube.config;

import club.wenfan.youtube.jwt.JwtAuthenticationTokenFilter;
import club.wenfan.youtube.properties.SecurityConstants;
import club.wenfan.youtube.properties.SecurityProperties;
import club.wenfan.youtube.session.WenfanExpiredSessionStrategy;
import club.wenfan.youtube.session.WenfanInvalidSessionStrategy;
import club.wenfan.youtube.validate.config.ValidateCodeSecurityConfig;
import club.wenfan.youtube.validate.mobile.SmsCodeAuthenticationSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/7 13:55
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启全局权限认证
public class SecurityConfig extends AbstractChannelSecurityConfig{

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    //注入authenticationManagerBean
    @Bean("authenticationManagerBean")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyAuthenticationConfig(http);

        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRembermeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                .logout()
                .logoutUrl("/signOut")
                .logoutSuccessUrl("/index.html")
                .deleteCookies("JSESSIONID")
                .and()
                .sessionManagement()
                .invalidSessionStrategy(new WenfanInvalidSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl()))
                .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
                .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionPreventsLogin())
                .expiredSessionStrategy(new WenfanExpiredSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl()))
                .and()
                .and()
                .authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_LOGIN_PAGE_URL,
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                        "/user/*",
                        "/users",
                        "/static/*",
                        "/admin/**"
                ).permitAll()
                .antMatchers("/test/*").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();

        // 添加JWT filter
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
