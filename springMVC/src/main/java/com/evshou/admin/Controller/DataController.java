package com.evshou.admin.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/data")
public class DataController {

    @RequestMapping("/test1")
    public String test1(HttpServletRequest request, HttpSession session){
        request.setAttribute("username","admin");
        session.setAttribute("age",15);
        return "data";
    }
}
