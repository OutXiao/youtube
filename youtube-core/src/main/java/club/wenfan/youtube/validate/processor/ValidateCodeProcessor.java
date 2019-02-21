package club.wenfan.youtube.validate.processor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/21 12:03
 */
public interface ValidateCodeProcessor {

    /**
     *  验证码放入session时的前缀
     * @author wenfan
     * @date
     * @param
     * @return
     */
    String SESSION_KEY_PREFIX="SESSION_KEY_FOR_CODE_";

    /**
     *  创建校验码
     * @author wenfan
     */
    void create(HttpServletRequest request, HttpServletResponse response) throws Exception;

    void validate(HttpServletRequest request,HttpServletResponse response);

}
