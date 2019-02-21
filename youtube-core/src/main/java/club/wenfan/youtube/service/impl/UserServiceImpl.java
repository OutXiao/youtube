package club.wenfan.youtube.service.impl;

import club.wenfan.youtube.bean.SysRole;
import club.wenfan.youtube.mapper.SysRoleMapper;
import club.wenfan.youtube.mapper.SysUserMapper;
import club.wenfan.youtube.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/11 21:40
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<String> getAuthenticationByUsername(String username) {
        Integer id = null;
        List<SysRole> roles = null;
        List<String> list = new ArrayList<>();
        id = sysUserMapper.getIdByUsername(username);
        if(id == null){
            throw new RuntimeException("没有该用户");
        }else {
            roles = sysRoleMapper.getRolesById(id);
            for (int i = 0; i < roles.size(); i++) {
                list.add(roles.get(i).getRoleCode());
            }
        }
        return list;
    }
}
