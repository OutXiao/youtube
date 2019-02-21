package club.wenfan.youtube.service;

import club.wenfan.youtube.bean.SysUser;
import club.wenfan.youtube.jwt.JwtUserFactory;
import club.wenfan.youtube.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/7 19:06
 */
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser user = sysUserMapper.getAuthByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            List<String> roles = userService.getAuthenticationByUsername(user.getUsername());
            return JwtUserFactory.create(user,roles);
        }


    }

}
