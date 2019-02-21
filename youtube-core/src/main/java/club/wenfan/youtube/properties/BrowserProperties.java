package club.wenfan.youtube.properties;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/7 13:37
 */
public class BrowserProperties {

    private SessionProperties session = new SessionProperties();

    // 默认注册页
    private String signUrl = SecurityConstants.DEFAULT_SIGN_PAGE_URL;

    // 默认登陆页
    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    // 返回数据类型
    private LoginType loginType = LoginType.JSON;

    //记住我秒数
    private int rembermeSeconds = 60;

    public String getSignUrl() {
        return signUrl;
    }

    public void setSignUrl(String signUrl) {
        this.signUrl = signUrl;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public int getRembermeSeconds() {
        return rembermeSeconds;
    }

    public void setRembermeSeconds(int rembermeSeconds) {
        this.rembermeSeconds = rembermeSeconds;
    }

    public SessionProperties getSession() {
        return session;
    }

    public void setSession(SessionProperties session) {
        this.session = session;
    }
}
