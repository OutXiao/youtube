package club.wenfan.youtube.properties;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/12 21:12
 */
public class SessionProperties {

    // 同一个用户在系统中的最大session数，默认1
    private int maximumSessions = 1;
    // 达到最大session时是否阻止新的登录请求，默认为false，不阻止，新的登录会将老的登录失效掉
    private boolean maxSessionPreventsLogin;
    // session无效返回的url
    private String sessionInvalidUrl = SecurityConstants.DEFAULT_INVALID_TOKEN;

    public int getMaximumSessions() {
        return maximumSessions;
    }

    public void setMaximumSessions(int maximumSessions) {
        this.maximumSessions = maximumSessions;
    }

    public boolean isMaxSessionPreventsLogin() {
        return maxSessionPreventsLogin;
    }

    public void setMaxSessionPreventsLogin(boolean maxSessionPreventsLogin) {
        this.maxSessionPreventsLogin = maxSessionPreventsLogin;
    }

    public String getSessionInvalidUrl() {
        return sessionInvalidUrl;
    }

    public void setSessionInvalidUrl(String sessionInvalidUrl) {
        this.sessionInvalidUrl = sessionInvalidUrl;
    }

}
