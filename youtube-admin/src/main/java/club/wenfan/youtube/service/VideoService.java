package club.wenfan.youtube.service;

import club.wenfan.youtube.bean.PageBean;
import club.wenfan.youtube.bean.Video;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/22 17:51
 */
public interface VideoService {

    List<Video> getVideoes();

    PageBean<Video> getVideoesByPage(int pageIndex, int pageSize);

    Video getVideoById(Integer id);

    int uploadVideo(MultipartFile coverFile,MultipartFile videoFile, Video video, HttpServletRequest request);

    int deleteVideo(Integer id);

    int editVideo(Video video);


}
