/*
* Categories.java
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

@Table(name = "categories")
public class Categories implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "created_date")
    private Date createdDate;

    /**
     * 视频数量
     */
    @Column(name = "video_count")
    private Integer videoCount;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return created_date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * 获取视频数量
     *
     * @return video_count - 视频数量
     */
    public Integer getVideoCount() {
        return videoCount;
    }

    /**
     * 设置视频数量
     *
     * @param videoCount 视频数量
     */
    public void setVideoCount(Integer videoCount) {
        this.videoCount = videoCount;
    }
}