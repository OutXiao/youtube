/*
* VideoComent.java
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

@Table(name = "video_coment")
public class VideoComent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 视频id
     */
    @Column(name = "video_id")
    private Integer videoId;

    /**
     * 评论id
     */
    @Column(name = "comment_id")
    private Integer commentId;

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
     * 获取视频id
     *
     * @return video_id - 视频id
     */
    public Integer getVideoId() {
        return videoId;
    }

    /**
     * 设置视频id
     *
     * @param videoId 视频id
     */
    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    /**
     * 获取评论id
     *
     * @return comment_id - 评论id
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * 设置评论id
     *
     * @param commentId 评论id
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }
}