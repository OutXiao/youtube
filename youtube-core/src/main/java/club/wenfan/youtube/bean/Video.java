/*
* Video.java
* http://www.wenfan.club
* Copyright © 2019 wenfan All Rights Reserved
* 作者：wenfan
* QQ：571696215
* E-Mail：guwenfan@qq.com
* 2019-02-21 19:07 Created
*/ 
package club.wenfan.youtube.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "video")
public class Video implements Serializable {
    /**
     * 视频id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 封面图片url
     */
    @Column(name = "cover_url")
    private String coverUrl;

    /**
     * 视频关键词
     */
    @JsonIgnore
    private String keyword;

    /**
     * 视频url
     */
    @JsonIgnore
    private String url;

    /**
     * 视频描述
     */
    @JsonIgnore
    private String description;

    /**
     * 分类id
     */
    @JsonIgnore
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * 点赞数
     */
    @Column(name = "like_size")
    private Integer likeSize;

    /**
     * 狂踩数
     */
    @Column(name = "unlike_size")
    private Integer unlikeSize;

    private static final long serialVersionUID = 1L;

    /**
     * 获取视频id
     *
     * @return id - 视频id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置视频id
     *
     * @param id 视频id
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
     * 获取视频标题
     *
     * @return title - 视频标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置视频标题
     *
     * @param title 视频标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取封面图片url
     *
     * @return cover_url - 封面图片url
     */
    public String getCoverUrl() {
        return coverUrl;
    }

    /**
     * 设置封面图片url
     *
     * @param coverUrl 封面图片url
     */
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    /**
     * 获取视频关键词
     *
     * @return keyword - 视频关键词
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * 设置视频关键词
     *
     * @param keyword 视频关键词
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * 获取视频url
     *
     * @return url - 视频url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置视频url
     *
     * @param url 视频url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取视频描述
     *
     * @return description - 视频描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置视频描述
     *
     * @param description 视频描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取分类id
     *
     * @return category_id - 分类id
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置分类id
     *
     * @param categoryId 分类id
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取点赞数
     *
     * @return like_size - 点赞数
     */
    public Integer getLikeSize() {
        return likeSize;
    }

    /**
     * 设置点赞数
     *
     * @param likeSize 点赞数
     */
    public void setLikeSize(Integer likeSize) {
        this.likeSize = likeSize;
    }

    /**
     * 获取狂踩数
     *
     * @return unlike_size - 狂踩数
     */
    public Integer getUnlikeSize() {
        return unlikeSize;
    }

    /**
     * 设置狂踩数
     *
     * @param unlikeSize 狂踩数
     */
    public void setUnlikeSize(Integer unlikeSize) {
        this.unlikeSize = unlikeSize;
    }
}