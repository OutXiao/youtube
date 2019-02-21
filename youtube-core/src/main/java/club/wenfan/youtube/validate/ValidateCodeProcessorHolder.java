package club.wenfan.youtube.validate;

import club.wenfan.youtube.validate.exception.ValidateCodeException;
import club.wenfan.youtube.validate.processor.ValidateCodeProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * @author:wenfan
 * @description:
 * @data: 2019/1/22 9:31
 */
@Component
public class ValidateCodeProcessorHolder {

    @Autowired
    private Map<String,ValidateCodeProcessor> validateCodeProcessors;

    private Logger log = LoggerFactory.getLogger(getClass());

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type){
        return findValidateCodeProcessor(type.toString().toLowerCase());

    }

    public ValidateCodeProcessor findValidateCodeProcessor(String type){
        String name=type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor=validateCodeProcessors.get(name);  //通过类型查找出用那个验证码处理器
        log.info("验证码处理器"+name);
        if(processor == null){
            throw new ValidateCodeException("验证码处理器"+name+"不存在");
        }
        return processor;
    }


}
