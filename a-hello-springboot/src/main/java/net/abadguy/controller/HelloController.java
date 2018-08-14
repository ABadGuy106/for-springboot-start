package net.abadguy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName:
 * @Description:
 * @author:
 * @date: 2018/8/14 9:32
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hell(){
        return "hello world";
    }
}
