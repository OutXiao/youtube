package club.wenfan.youtube.service;

import club.wenfan.youtube.bean.SysUser;

import java.util.List;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/11 21:39
 */
public interface UserService {

    List<String> getAuthenticationByUsername(String username);

}
