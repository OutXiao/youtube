/*
* SysRole.java
* http://www.wenfan.club
* Copyright © 2019 wenfan All Rights Reserved
* 作者：wenfan
* QQ：571696215
* E-Mail：guwenfan@qq.com
* 2019-02-11 16:51 Created
*/ 
package club.wenfan.youtube.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "sys_role")
public class SysRole implements Serializable {
    /**
     * roleId
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色代号
     */
    @Column(name = "role_code")
    private String roleCode;

    /**
     * 角色名
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 角色类型
     */
    @Column(name = "role_type")
    private Integer roleType;

    /**
     * 是否冻结账户. 1冻结,0正常
     */
    @Column(name = "is_enable")
    private Byte isEnable;

    /**
     * 已经创建的用户数量
     */
    @Column(name = "created_user")
    private Integer createdUser;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Date createdAt;

    /**
     * 修改时间
     */
    @Column(name = "updated_at")
    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    /**
     * 获取roleId
     *
     * @return id - roleId
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置roleId
     *
     * @param id roleId
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取角色代号
     *
     * @return role_code - 角色代号
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 设置角色代号
     *
     * @param roleCode 角色代号
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * 获取角色名
     *
     * @return role_name - 角色名
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名
     *
     * @param roleName 角色名
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取角色类型
     *
     * @return role_type - 角色类型
     */
    public Integer getRoleType() {
        return roleType;
    }

    /**
     * 设置角色类型
     *
     * @param roleType 角色类型
     */
    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    /**
     * 获取是否冻结账户. 1冻结,0正常
     *
     * @return is_enable - 是否冻结账户. 1冻结,0正常
     */
    public Byte getIsEnable() {
        return isEnable;
    }

    /**
     * 设置是否冻结账户. 1冻结,0正常
     *
     * @param isEnable 是否冻结账户. 1冻结,0正常
     */
    public void setIsEnable(Byte isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * 获取已经创建的用户数量
     *
     * @return created_user - 已经创建的用户数量
     */
    public Integer getCreatedUser() {
        return createdUser;
    }

    /**
     * 设置已经创建的用户数量
     *
     * @param createdUser 已经创建的用户数量
     */
    public void setCreatedUser(Integer createdUser) {
        this.createdUser = createdUser;
    }

    /**
     * 获取创建时间
     *
     * @return created_at - 创建时间
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * 设置创建时间
     *
     * @param createdAt 创建时间
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 获取修改时间
     *
     * @return updated_at - 修改时间
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 设置修改时间
     *
     * @param updatedAt 修改时间
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}