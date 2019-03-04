/*
* VideoComentMapper.java
* http://www.wenfan.club
* Copyright © 2019 wenfan All Rights Reserved
* 作者：wenfan
* QQ：571696215
* E-Mail：guwenfan@qq.com
* 2019-02-21 19:07 Created
*/ 
package club.wenfan.youtube.mapper;

import club.wenfan.youtube.bean.VideoComent;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;


@Component
@org.apache.ibatis.annotations.Mapper
public interface VideoComentMapper extends Mapper<VideoComent> {
}