package com.evshou.admin.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inter")
public class InterController {
    @RequestMapping("/test1/a/b")
    public String test1(){
        System.out.println("test1");
        return "index";
    }
    @RequestMapping("/test2")
    public String test2(){
        System.out.println("test2");
        return "index";
    }
}
