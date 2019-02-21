package club.wenfan.youtube.properties;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/1 9:21
 */
public class SmsCodeProperties {

    private  int codeCount = 6;// 定义短信上显示验证码的个数
    private int  expiredTime = 60;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public int getCodeCount() {
        return codeCount;
    }

    public void setCodeCount(int codeCount) {
        this.codeCount = codeCount;
    }

    public int getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(int expiredTime) {
        this.expiredTime = expiredTime;
    }
}
