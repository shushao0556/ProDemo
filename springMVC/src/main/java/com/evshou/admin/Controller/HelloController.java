package com.evshou.admin.Controller;

import com.evshou.admin.entity.Users;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/hello")
public class  HelloController {

    /*零散收参*/
    @RequestMapping("/test")
    public String test1(Integer id, String username,
                        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date birth){
        System.out.println("id:"+id+",username:"+username);
        System.out.println("birth:"+birth);
        return "index";
    }
    @RequestMapping("/users")
    public String testUsers(Users users){
        System.out.println(users);
        return "index";
    }

    /*数组收参*/
    @RequestMapping("/arrList")
    public String testArr(String[] feed){
        for (String str : feed) {
            System.out.println(str);
        }
        return "index";
    }
    /*表单页面*/
    @RequestMapping("/arrListForm")
    public String testArrListForm(){
        return "arrList";
    }
}
