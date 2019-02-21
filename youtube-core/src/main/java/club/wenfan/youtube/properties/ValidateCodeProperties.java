package club.wenfan.youtube.properties;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/1 9:31
 */
public class ValidateCodeProperties {

    private ImgCodeProperties img=new ImgCodeProperties();

    private SmsCodeProperties sms=new SmsCodeProperties();

    public SmsCodeProperties getSms() {
        return sms;
    }

    public void setSms(SmsCodeProperties sms) {
        this.sms = sms;
    }

    public ImgCodeProperties getImg() {
        return img;
    }

    public void setImg(ImgCodeProperties img) {
        this.img = img;
    }
}
