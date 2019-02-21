package club.wenfan.youtube.jwt;

import club.wenfan.youtube.bean.SysRole;
import club.wenfan.youtube.bean.SysUser;
import club.wenfan.youtube.mapper.SysUserMapper;
import club.wenfan.youtube.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author:wenfan
 * @description:
 * @data: 2018/8/27
 */
public class JwtUserFactory {

    private JwtUserFactory(){}

    public static SysUser create(SysUser user,List<String> roles){

        // 这里需要将用户的权限传入
        return new SysUser( user.getId(), user.getUsername(), user.getPwd(),mapToGrantedAuthorities(roles));

    }

    /**
     *  将字符串权限 转化为GrantedAuthority
     *
     * @author wenfan
     * @date
     * @param
     * @return
     */
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String >authorities){
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)  //new 为访问SimpleGrantedAuthority的构造方法
                .collect(Collectors.toList());
    }

}
