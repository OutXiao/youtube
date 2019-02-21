package club.wenfan.youtube.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/18 16:54
 */

@RestController
@RequestMapping("/user")
public class TestController1 {

    @GetMapping("/getinfo1")
    public String getInfo(HttpServletRequest request, HttpServletResponse response){
        return (String) request.getSession().getAttribute("haha");
    }

}