package club.wenfan.youtube.controller;

import club.wenfan.youtube.VideoVO;
import club.wenfan.youtube.bean.PageBean;
import club.wenfan.youtube.rest.RestMsg;
import club.wenfan.youtube.rest.RestResult;
import club.wenfan.youtube.service.VideoService;
import club.wenfan.youtube.bean.Video;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/22 17:47
 */


@RestController
@RequestMapping("/admin/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/getVideoes")
    @ResponseBody
    public VideoVO getAllVideo(HttpServletRequest request) throws JsonProcessingException {
        String strPageIndex = request.getParameter("pageIndex");
        String strPageSize = request.getParameter("pageSize");
        int recordsTotal;
        int recordsFiltered;
        int pageIndex = Integer.parseInt(strPageIndex) ;
        int pageSize = Integer.parseInt(strPageSize);
        PageBean<Video> page = videoService.getVideoesByPage(pageIndex,pageSize);
        recordsTotal = page.getTotalNum();
        recordsFiltered = recordsTotal;
        return new VideoVO(recordsTotal,recordsFiltered,page.getItems());
    }

    @PostMapping("/addVideo")
    public RestMsg addVideo(@RequestParam("cover") MultipartFile coverFile, @RequestParam("video") MultipartFile videoFile, HttpServletRequest request){

        String title = request.getParameter("title");
        String keyword = request.getParameter("keyword");
        String description = request.getParameter("description");
        String categoryId = request.getParameter("category_id");
        //String userId = request.getParameter("userId");
        Video video = new Video();
        video.setTitle(title);
        video.setDescription(description);
        video.setCategoryId(Integer.parseInt(categoryId));
        video.setKeyword(keyword);
        video.setUserId(1);
        int i = videoService.uploadVideo(coverFile,videoFile,video,request);

        return i>0?RestResult.success() : RestResult.failure();
    }

    @PostMapping("/delVideo")
    public RestMsg delVideo(HttpServletRequest request){
        Integer id = Integer.parseInt(request.getParameter("id"));
        if(id == 0){
            return RestMsg.FAILURE;
        }
        int i = videoService.deleteVideo(id);
        return i>0?RestMsg.SUCCESS:RestMsg.FAIL;
    }

    @PostMapping("/getSingleVideoById")
    public Video getSingleVideoById(HttpServletRequest request){
        Integer id = Integer.parseInt(request.getParameter("id"));
        if(id == 0){
            return null;
        }
        return videoService.getVideoById(id);
    }

    @PostMapping("/editVideo")
    public RestMsg editVideo(HttpServletRequest request){
        Integer id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String coverUrl = request.getParameter("coverUrl");
        String likeSize = request.getParameter("likeSize");
        String unLikeSize = request.getParameter("unLikeSize");
        if(title == null|| coverUrl == null||likeSize == null || unLikeSize == null){
            return RestMsg.FAILURE;
        }
        Video video = new Video();
        video.setId(id);
        video.setTitle(title);
        video.setCoverUrl(coverUrl);
        video.setLikeSize(Integer.parseInt(likeSize));
        video.setUnlikeSize(Integer.parseInt(unLikeSize));
        int i = videoService.editVideo(video);
        return i>0?RestMsg.SUCCESS:RestMsg.FAIL;
    }

}
