package club.wenfan.youtube.validate.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author:wenfan
 * @description:
 * @data: 2018/12/31 21:40
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
