package club.wenfan.youtube.jwt.service;

import club.wenfan.youtube.bean.SysUser;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/8 16:48
 */
public interface AuthService {

    SysUser register(SysUser user);

    String login(String username,String pwd);

    String refresh(String oldToken);

}
