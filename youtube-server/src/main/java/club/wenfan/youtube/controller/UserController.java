package club.wenfan.youtube.controller;

import club.wenfan.youtube.bean.SysUser;
import club.wenfan.youtube.jwt.service.AuthService;
import club.wenfan.youtube.mapper.SysUserMapper;
import club.wenfan.youtube.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/7 13:30
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService service;

    @GetMapping("/hello")
    public String getInfo() {
        return "hello youtube";
    }

    @GetMapping("/users")
    public List<SysUser> getUsers() {
        return sysUserMapper.selectAll();
    }

    /**
     * 登陆获取token
     *
     * @param
     * @return
     * @author wenfan
     * @date
     */
    @PostMapping("/auth")
    public String createAuthenticationToken(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String token = "";
        if(username != null && password != null){
            System.out.println(username + " "+password);
            token = authService.login(username, password);
            System.out.println(token);
        }
        return "Bearer " + token;

    }

    /**
     * 注册
     * @author wenfan
     * @date

     * @param
     * @return
     */
    @PostMapping(value = "/${club.wenfan.youtube.jwt.register}",consumes = "application/json")
    public SysUser register(@RequestBody SysUser user) throws AuthenticationException{
        return authService.register(user);
    }


    @GetMapping("/getUserByUsername")
    public String getUserByUsername(HttpServletRequest request){
        service.getAuthenticationByUsername("wenfan1");
        request.getSession(true).setAttribute("username","123");
        request.getSession(true).setAttribute("username","345");
        String username = (String) request.getSession(true).getAttribute("username");
        return username;
    }


}

