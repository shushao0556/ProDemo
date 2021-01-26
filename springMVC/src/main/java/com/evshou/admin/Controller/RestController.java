package com.evshou.admin.Controller;

import com.evshou.admin.entity.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/rest")
public class RestController {
    @GetMapping("/user")//查询所有
    public String usersList(){
        System.out.println("query AllUsers");
        return "index";
    }
    @PostMapping("/user")//添加一个用户
    public String addUsers(Users users){
        System.out.println("add Users");
        return "index";
    }
    @PutMapping("/user")//修改一个用户
    public String update(@RequestBody Users users){//通过@RequestBody收参
        System.out.println("update Users");
        return "index";
    }
    @DeleteMapping("/user/{id}")//删除一个用户
    public String delete(@PathVariable Integer id){
        System.out.println("delete UserById");
        return "index";
    }
    @GetMapping("/user/{id}")//查询某个用户
    public String queryUsersById(@PathVariable Integer id){
        System.out.println("query UsersById");
        return "index";
    }
    @PostMapping("/user/{id}")//添加指定某个用户id
    public String addUsersById(@PathVariable Integer id){
        System.out.println("add UserById");
        return "index";
    }
    @GetMapping("/user/{pageNum}/{pageSize}")
    @ResponseBody
     public List<Users> queryAllUsers(@PathVariable Integer pageNum,@PathVariable Integer pageSize){
        System.out.println("get");
        List<Users> users=new ArrayList<>();
        return users;
    }
}
