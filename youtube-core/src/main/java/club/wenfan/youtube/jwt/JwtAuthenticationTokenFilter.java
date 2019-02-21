package club.wenfan.youtube.jwt;

import club.wenfan.youtube.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author:wenfan
 * @description:
 * @data: 2018/8/28
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)throws ServletException, IOException {
        //设置response，否则前端页面拿不到option结果
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Origin", "http://admin.wenfan.com");  //允许跨域请求
        response.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers","Authorization");
        String method = request.getMethod();
        if(method.equals("OPTIONS")){
        //预请求需要往回写 让ajax预请求知道预请求是成功的
            response.setStatus(202);
            response.getWriter().write(1);
        }else {
            String authHead = request.getHeader(securityProperties.getJwt().getHead());
            if (authHead != null && authHead.startsWith(securityProperties.getJwt().getTokenHead())) {

                // The part after "Bearer "
                final String authToken = authHead.substring(securityProperties.getJwt().getTokenHead().length());

                // 从token 内获取username
                String username = jwtTokenUtil.getUsernameFromToken(authToken);

                logger.info("username=" + username);
                if (username != null & SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                    if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                        //通过userDetails 验证
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        logger.info("authenticated user " + username + ", setting security context");
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
            chain.doFilter(request, response);
        }
    }
}
