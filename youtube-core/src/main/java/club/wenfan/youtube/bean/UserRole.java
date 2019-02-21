/*
* UserRole.java
* http://www.wenfan.club
* Copyright © 2019 wenfan All Rights Reserved
* 作者：wenfan
* QQ：571696215
* E-Mail：guwenfan@qq.com
* 2019-02-11 16:48 Created
*/ 
package club.wenfan.youtube.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "user_role")
public class UserRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 授权日期
     */
    @Column(name = "authorized_date")
    private Date authorizedDate;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取角色id
     *
     * @return role_id - 角色id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取授权日期
     *
     * @return authorized_date - 授权日期
     */
    public Date getAuthorizedDate() {
        return authorizedDate;
    }

    /**
     * 设置授权日期
     *
     * @param authorizedDate 授权日期
     */
    public void setAuthorizedDate(Date authorizedDate) {
        this.authorizedDate = authorizedDate;
    }
}