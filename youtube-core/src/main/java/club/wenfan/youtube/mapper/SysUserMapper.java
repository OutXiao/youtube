/*
* SysUserMapper.java
* http://www.wenfan.club
* Copyright © 2019 wenfan All Rights Reserved
* 作者：wenfan
* QQ：571696215
* E-Mail：guwenfan@qq.com
* 2019-02-11 16:51 Created
*/ 
package club.wenfan.youtube.mapper;

import club.wenfan.youtube.bean.SysUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Component
public interface SysUserMapper extends Mapper<SysUser> {

    Integer getIdByUsername(String username);

    @Select({
            "select id,username,pwd from sys_user where username= #{username}" +
                    ""})
    SysUser getAuthByUsername(String username);
}