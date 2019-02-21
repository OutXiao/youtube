package club.wenfan.youtube.validate.sms;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/21 10:13
 */
public interface SmsCodeSender {

    void send(String mobile, String code);

}
