package club.wenfan.youtube.authentication;

import club.wenfan.youtube.config.SecurityConfig;
import club.wenfan.youtube.jwt.service.AuthService;
import club.wenfan.youtube.properties.LoginType;
import club.wenfan.youtube.properties.SecurityProperties;
import club.wenfan.youtube.utils.IpAddress;
import club.wenfan.youtube.vo.RestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.deploy.net.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/7 17:55
 */
@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthService authService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info(IpAddress.getIpAddress(request)+" ip登陆成功");
        String token = authService.login(username,password);
        if(token == null){
            throw new RuntimeException("token签发异常");
        }
        token = "Bearer "+token;
        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setContentType("application/json;charset=utf-8");
            // 登陆成功后将认证信息写入页面
            response.getWriter().write(objectMapper.writeValueAsString(new RestResponse(authentication.getPrincipal(),token)));
        }else {
            super.onAuthenticationSuccess(request,response,authentication);
        }
    }
}
