package club.wenfan.youtube.validate.code.ValidateGeneratorImpl;

import club.wenfan.youtube.properties.SecurityProperties;
import club.wenfan.youtube.validate.code.ImgCode;
import club.wenfan.youtube.validate.code.ValidateCodeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/1 18:33
 */

@Component("imageValidateCodeGenerator")
public class ImgCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public ImgCode CreateCode(HttpServletRequest request) {
        //可以在请求中加入 width/height get参数  当没有参数时从用户的自定义的配置文件中读取
        int width =ServletRequestUtils.getIntParameter(request,"width",securityProperties.getCode().getImg().getWidth());// 定义图片的width
        int height = ServletRequestUtils.getIntParameter(request," height",securityProperties.getCode().getImg().getHeight());// 定义图片的height
        int codeCount =securityProperties.getCode().getImg().getCodeCount();// 定义图片上显示验证码的个数
        int expiredTime = securityProperties.getCode().getImg(). getExpiredTime();
        int xx = 18;
        int fontHeight = 20;
        int codeY = 27;
        char[] codeSequence = { '0','1', '2', '3', '4','5', '6', '7', '8', '9' };

        // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics gd = buffImg.getGraphics();
        // 创建一个随机数生成器类
        Random random = new Random();
        // 将图像填充为白色
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, width, height);

        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
        // 设置字体。
        gd.setFont(font);

        // 画边框。
        gd.setColor(Color.BLACK);
        gd.drawRect(0, 0, width - 1, height - 1);

        // 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
        gd.setColor(Color.BLACK);
        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }

        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        int red = 0, green = 0, blue = 0;

        // 随机产生codeCount数字的验证码。
        for (int i = 0; i <codeCount; i++) {
            // 得到随机产生的验证码数字。
            String code = String.valueOf(codeSequence[random.nextInt(10)]);
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);

            // 用随机产生的颜色将验证码绘制到图像中。
            gd.setColor(new Color(red, green, blue));
            gd.drawString(code, (i + 1) * xx, codeY);

            // 将产生的四个随机数组合在一起。
            randomCode.append(code);

        }
        log.info("产生验证码"+randomCode.toString());
        return new ImgCode(buffImg,randomCode.toString(),expiredTime);
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
