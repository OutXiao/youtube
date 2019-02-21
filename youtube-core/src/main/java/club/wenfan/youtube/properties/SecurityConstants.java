package club.wenfan.youtube.properties;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/7 13:41
 */
public interface SecurityConstants {

    /**
     *  验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_CODE_SMS="smsCode";

    /**
     *  发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_MOBILE="mobile";



    /**
     * 默认登陆页面
     */
    public static final String DEFAULT_LOGIN_PAGE_URL="/login.html";


    /**
     *  默认的用户名密码登录请求处理url
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_FORM="/authentication/form";

    /**
     *  默认的手机验证码登录请求处理url
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_MOBILE="/authentication/mobile";


    /**
     * 默认注册页面
     */
    public static final String DEFAULT_SIGN_PAGE_URL="/sign.html";

    /**
     *  请求需要身份认证时，跳转的url
     */
    public static final String DEFAULT_UNAUTHENTICATION_URL="/authentication/require";

    public static final String DEFAULT_INVALID_TOKEN = "/authentication/token";


    /**
     *  验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_CODE_IMAGE="imageCode";



    /**
     *  默认的处理验证码前缀
     */
    public static final String DEFAULT_VALIDATE_CODE_URL_PREFIX="/code";
}
