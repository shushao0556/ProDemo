package com.evshou.admin.Controller;

import cn.dsna.util.images.ValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/test1")
    public String test1()  {
        if(1==1) throw new NullPointerException("test1");
        return "index";
    }
}
