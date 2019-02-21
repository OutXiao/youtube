package club.wenfan.youtube.jwt.service.impl;

import club.wenfan.youtube.bean.SysUser;
import club.wenfan.youtube.jwt.JwtTokenUtil;
import club.wenfan.youtube.jwt.service.AuthService;
import club.wenfan.youtube.mapper.SysUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/8 16:57
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Resource(name = "authenticationManagerBean")
    private AuthenticationManager authenticationManagerBean;

    private Logger log  = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     *  注册业务
     * @author wenfan
     * @date
     * @param
     * @return
     */
    @Override
    public SysUser register(SysUser user) {
        final String username=user.getUsername();
        if (sysUserMapper.getAuthByUsername(username)!=null){
            System.out.println(sysUserMapper.getAuthByUsername(username).getAuthorities());
            return null;
        }
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        System.out.println(user.getPwd());
        user.setPwd(encoder.encode(user.getPwd()));
        user.setCreateAt(new Date());
        sysUserMapper.insertSelective(user);
        return sysUserMapper.getAuthByUsername(username);

    }

    // 根据账户密码签发token
    @Override
    public String login(String username, String password) {
        /**
         *
         *  UsernamePasswordAuthenticationToken 验证机制
         *
         * UsernamePasswordAuthenticationToken继承AbstractAuthenticationToken实现Authentication
         * 所以当在页面中输入用户名和密码之后首先会进入到UsernamePasswordAuthenticationToken验证(Authentication)，
         * 然后生成的Authentication会被交由AuthenticationManager来进行管理
         * 而AuthenticationManager管理一系列的AuthenticationProvider，
         * 而每一个Provider都会通UserDetailsService和UserDetail来返回一个
         * 以UsernamePasswordAuthenticationToken实现的带用户名和密码以及权限的Authentication
         *
         */
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username,password);
        System.out.println(1231);
        Authentication authentication = authenticationManagerBean.authenticate(upToken);
        System.out.println(1111);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }


    @Override
    public String refresh(String oldToken) {
        return null;
    }
}
