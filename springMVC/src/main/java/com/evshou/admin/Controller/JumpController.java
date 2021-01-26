package com.evshou.admin.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jump")
public class JumpController {
    @RequestMapping("/test")
    public String test(){
        System.out.println("test");
        return "redirect:/hello/arrListForm";
    }
    @RequestMapping("/test2")
    public String test2(){
        System.out.println("test2");
        return "forward:test";
    }
}
