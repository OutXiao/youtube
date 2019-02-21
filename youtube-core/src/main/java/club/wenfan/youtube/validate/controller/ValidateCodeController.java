package club.wenfan.youtube.validate.controller;

import club.wenfan.youtube.properties.SecurityConstants;
import club.wenfan.youtube.validate.ValidateCodeProcessorHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author:wenfan
 * @description:
 * @data: 2018/12/31 10:32
 */
@RestController
public class ValidateCodeController {

    @Autowired
    private ValidateCodeProcessorHolder holder;

    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
        holder.findValidateCodeProcessor(type).create(request,response);
    }
}
