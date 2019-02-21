/*
* SysUser.java
* http://www.wenfan.club
* Copyright © 2019 wenfan All Rights Reserved
* 作者：wenfan
* QQ：571696215
* E-Mail：guwenfan@qq.com
* 2019-02-11 16:48 Created
*/ 
package club.wenfan.youtube.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Table(name = "sys_user")
public class SysUser implements UserDetails,Serializable {


    /**
     * 用户的权限
     * @author wenfan
     * @date
     * @param
     * @return
     */
    @Transient
    private  Collection<? extends GrantedAuthority> authorities;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer uid;

    /**
     *  用户名
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String pwd;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * QQ id
     */
    private Integer openid;

    /**
     * 省id
     */
    @Column(name = "province_id")
    private Integer provinceId;

    /**
     * 地区id
     */
    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "city_id")
    private Integer cityId;

    /**
     * 电话
     */
    private String tel;

    /**
     * 用户类型
     */
    @Column(name = "user_type")
    private Integer userType;

    /**
     * 是否启用
     */
    @Column(name = "is_enabled")
    private Boolean isEnabled;

    /**
     * 是否锁定
     */
    @Column(name = "is_locked")
    private Boolean isLocked;

    /**
     * 是否开启数据权限验证  默认0
     */
    @Column(name = "enable_data_role")
    private Boolean enableDataRole;

    /**
     * 创建用户时间
     */
    @Column(name = "create_at")
    private Date createAt;

    /**
     * 上次登陆时间
     */
    @Column(name = "login_last_date")
    private Date loginLastDate;

    /**
     * 上次登陆ip
     */
    @Column(name = "login_last_ip")
    private String loginLastIp;

    private static final long serialVersionUID = 1L;


    public SysUser(Integer id,String username,String pwd ,Collection<? extends GrantedAuthority> authorities){
        this.id = id;
        this.username = username;
        this.pwd = pwd;
        this.authorities = authorities;
    }

    public SysUser(){};

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }


    /**
     * 设置 用户名
     *
     * @param username  用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return pwd - 密码
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 设置密码
     *
     * @param pwd 密码
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取用户头像
     *
     * @return avatar - 用户头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置用户头像
     *
     * @param avatar 用户头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取QQ id
     *
     * @return openid - QQ id
     */
    public Integer getOpenid() {
        return openid;
    }

    /**
     * 设置QQ id
     *
     * @param openid QQ id
     */
    public void setOpenid(Integer openid) {
        this.openid = openid;
    }

    /**
     * 获取省id
     *
     * @return province_id - 省id
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * 设置省id
     *
     * @param provinceId 省id
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 获取地区id
     *
     * @return district_id - 地区id
     */
    public Integer getDistrictId() {
        return districtId;
    }

    /**
     * 设置地区id
     *
     * @param districtId 地区id
     */
    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    /**
     * @return city_id
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * @param cityId
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * 获取电话
     *
     * @return tel - 电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置电话
     *
     * @param tel 电话
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 获取用户类型
     *
     * @return user_type - 用户类型
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * 设置用户类型
     *
     * @param userType 用户类型
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 获取是否启用
     *
     * @return is_enabled - 是否启用
     */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     * 设置是否启用
     *
     * @param isEnabled 是否启用
     */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * 获取是否锁定
     *
     * @return is_locked - 是否锁定
     */
    public Boolean getIsLocked() {
        return isLocked;
    }

    /**
     * 设置是否锁定
     *
     * @param isLocked 是否锁定
     */
    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    /**
     * 获取是否开启数据权限验证  默认0
     *
     * @return enable_data_role - 是否开启数据权限验证  默认0
     */
    public Boolean getEnableDataRole() {
        return enableDataRole;
    }

    /**
     * 设置是否开启数据权限验证  默认0
     *
     * @param enableDataRole 是否开启数据权限验证  默认0
     */
    public void setEnableDataRole(Boolean enableDataRole) {
        this.enableDataRole = enableDataRole;
    }

    /**
     * 获取创建用户时间
     *
     * @return create_at - 创建用户时间
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * 设置创建用户时间
     *
     * @param createAt 创建用户时间
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * 获取上次登陆时间
     *
     * @return login_last_date - 上次登陆时间
     */
    public Date getLoginLastDate() {
        return loginLastDate;
    }

    /**
     * 设置上次登陆时间
     *
     * @param loginLastDate 上次登陆时间
     */
    public void setLoginLastDate(Date loginLastDate) {
        this.loginLastDate = loginLastDate;
    }

    /**
     * 获取上次登陆ip
     *
     * @return login_last_ip - 上次登陆ip
     */
    public String getLoginLastIp() {
        return loginLastIp;
    }

    /**
     * 设置上次登陆ip
     *
     * @param loginLastIp 上次登陆ip
     */
    public void setLoginLastIp(String loginLastIp) {
        this.loginLastIp = loginLastIp;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  authorities ;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return pwd;
    }

    /**
     * 获取 用户名
     *
     * @return username -  用户名
     */
    public String getUsername() {
        return username;
    }


    // 账户是否过期
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    // 账户是否锁定
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 密码是否过期
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 账户是否激活
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}