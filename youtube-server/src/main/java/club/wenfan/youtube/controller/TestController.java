package club.wenfan.youtube.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/11 12:35
 */

@RestController
@RequestMapping("/user")
public class TestController {

    @GetMapping("/getinfo")
    public String getInfo(HttpServletRequest request, HttpServletResponse response){
        request.getSession().setAttribute("haha","sdf");
        return "info";
    }

}
