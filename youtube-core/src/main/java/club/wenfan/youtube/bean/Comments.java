/*
* Comments.java
* http://www.wenfan.club
* Copyright © 2019 wenfan All Rights Reserved
* 作者：wenfan
* QQ：571696215
* E-Mail：guwenfan@qq.com
* 2019-02-21 19:07 Created
*/ 
package club.wenfan.youtube.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "comments")
public class Comments implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 评论用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 评论昵称
     */
    private String nickname;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    @Column(name = "created_date")
    private Date createdDate;

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
     * 获取评论用户id
     *
     * @return user_id - 评论用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置评论用户id
     *
     * @param userId 评论用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取评论昵称
     *
     * @return nickname - 评论昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置评论昵称
     *
     * @param nickname 评论昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取评论内容
     *
     * @return content - 评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评论内容
     *
     * @param content 评论内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取评论时间
     *
     * @return created_date - 评论时间
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * 设置评论时间
     *
     * @param createdDate 评论时间
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}