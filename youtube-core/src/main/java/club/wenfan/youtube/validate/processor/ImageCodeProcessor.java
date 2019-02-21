package club.wenfan.youtube.validate.processor;

import club.wenfan.youtube.validate.code.ImgCode;
import club.wenfan.youtube.validate.processor.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/21 15:25
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImgCode> {

    @Override
    protected void send(HttpServletRequest request, HttpServletResponse response, ImgCode imgCode) throws Exception {
        ImageIO.write(imgCode.getImage(),"JPEG",response.getOutputStream());
    }

}
