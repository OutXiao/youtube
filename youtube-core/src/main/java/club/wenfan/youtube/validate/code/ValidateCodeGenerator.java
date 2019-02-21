package club.wenfan.youtube.validate.code;


import javax.servlet.http.HttpServletRequest;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/1 18:32
 */
public interface ValidateCodeGenerator {
    ValidateCode CreateCode(HttpServletRequest request);
}
