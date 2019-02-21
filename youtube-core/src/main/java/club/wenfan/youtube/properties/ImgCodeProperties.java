package club.wenfan.youtube.properties;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/1 9:21
 */
public class ImgCodeProperties extends SmsCodeProperties{

    private  int width = 100;// 定义图片的width
    private  int height= 30;// 定义图片的height

    public ImgCodeProperties(){
        setCodeCount(4);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


}
