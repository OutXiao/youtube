package club.wenfan.youtube.service.impl;

import club.wenfan.youtube.service.VideoService;
import club.wenfan.youtube.bean.PageBean;
import club.wenfan.youtube.bean.Video;
import club.wenfan.youtube.mapper.VideoMapper;
import club.wenfan.youtube.utils.FileUpload;
import club.wenfan.youtube.utils.UploadFileUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qcloud.cos.transfer.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/22 18:12
 */
@Service
public class VideoServiceImpl implements VideoService {


    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private UploadFileUtil uploadFileUtil;

    @Override
    public List<Video> getVideoes() {
        return videoMapper.selectAll();
    }

    @Override
    public PageBean<Video> getVideoesByPage(int pageIndex, int pageSize) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        Page page = PageHelper.startPage(pageIndex,pageSize);
        List<Video> allVideo = videoMapper.selectAll();
        int count = allVideo.size();
        PageBean<Video> pageBean = new PageBean<>(pageIndex,pageSize,count);
        pageBean.setItems(allVideo);
        pageBean.setTotalNum((int)page.getTotal());
        return pageBean;
    }

    @Override
    public Video getVideoById(Integer id) {
        return videoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int uploadVideo(MultipartFile coverFile,MultipartFile videoFile, Video video, HttpServletRequest request) {
        String videoFileName = videoFile.getOriginalFilename();
        String coverFileName = coverFile.getOriginalFilename();
        String filePath = request.getSession().getServletContext().getRealPath("fileupload/");
        try {
            FileUpload.uploadFile(coverFile.getBytes(),filePath,coverFileName);
            FileUpload.uploadFile(videoFile.getBytes(), filePath, videoFileName);
            System.out.println(filePath+videoFileName);
        } catch (Exception e) {
            throw new RuntimeException("文件上传失败");
        }
        File targetCover = new File(filePath+coverFileName);
        File targetVideo = new File(filePath+videoFileName);

        String videoFileType [] = videoFileName.split("\\.");
        String coverFileType [] = coverFileName.split("\\.");
        System.out.println(targetCover.toString());
        System.out.println(targetVideo.toString());
        String coverUrl = uploadFileUtil.uploadFile(targetCover,coverFileType[1]).toString();
        String videoUrl = uploadFileUtil.uploadFile(targetVideo,videoFileType[1]).toString();
        video.setCoverUrl(coverUrl);
        video.setUrl(videoUrl);
        return videoMapper.insertSelective(video);

    }

    @Override
    public int deleteVideo(Integer id) {
        if(id == null){
            return 0;
        }
        return videoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int editVideo(Video video) {

        return videoMapper.updateByPrimaryKeySelective(video);
    }
}
