package com.evshou.admin.Controller;

import com.evshou.admin.entity.Address;
import com.evshou.admin.entity.Emp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping("/getAll")
    public String getAll(){
        return "";
    }
    @RequestMapping("/addForm")
    public String addForm(){
        return "addEmp";
    }
    @RequestMapping("/")
    public String add(HttpServletRequest request, Emp emp, Address address){
        System.out.println(emp+"#"+address);
        request.setAttribute("emp",emp);
        request.setAttribute("address",address);
        return "emp";
    }
}
