package com.evshou.admin.Controller;

import com.evshou.admin.entity.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/json")
public class JsonController {
    @RequestMapping("/test1")
    public Users test1(){
        return new Users(1,"admin","admin888",new Date());
    }
    @RequestMapping("/test2")
    public List<Users> test2(){
        return Arrays.asList(
                new Users(1,"admin","admin888",new Date()),
                new Users(2,"admin2","admin888",new Date())
        );
    }
}
