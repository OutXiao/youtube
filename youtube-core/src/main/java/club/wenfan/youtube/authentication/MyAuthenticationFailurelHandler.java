package club.wenfan.youtube.authentication;

import club.wenfan.youtube.properties.LoginType;
import club.wenfan.youtube.properties.SecurityProperties;
import club.wenfan.youtube.utils.IpAddress;
import club.wenfan.youtube.vo.SimpleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author:wenfan
 * @description:  登陆失败处理
 * @data: 2019/2/7 14:03
 */
@Component("myAuthenticationFailurelHandler")
public class MyAuthenticationFailurelHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ObjectMapper objectMapper;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.info(IpAddress.getIpAddress(request)+" ip登陆失败");
        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
        }else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
