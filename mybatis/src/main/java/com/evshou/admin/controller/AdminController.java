package com.evshou.admin.controller;

import com.evshou.admin.entity.Users;
import com.evshou.admin.mapper.UsersMapper;
import com.evshou.admin.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    //查询所有用户
    @GetMapping("/queryUsers")
    public String queryUsers(HttpServletRequest request){
        SqlSession ss = MybatisUtils.getSqlSession();
        UsersMapper mapper = ss.getMapper(UsersMapper.class);
        List<Users> list = mapper.queryAllUsers();
        request.setAttribute("list",list);
        return "/admin/AllUsersList";
    }
    //添加用户表单
    @RequestMapping("/addForm")
    public String addForm(){
        return "/admin/addUsers";
    }
    //添加用户
    @PostMapping("/")
    public String add(Users users, HttpSession session, HttpServletResponse response){
        System.out.println(users);
        SqlSession ss = MybatisUtils.getSqlSession();
        UsersMapper mapper = ss.getMapper(UsersMapper.class);
        int i;
        try{
            i=mapper.insert(users);
            session.setAttribute("users",users);
            ss.commit();
            if(i>0) return "redirect:/admin/queryUsers";
        }catch (Exception e){
            ss.rollback();
            return "/public/error";
        }
        return "index";
    }
    //删除用户
    @DeleteMapping("/{id}")
    public void del(@PathVariable Integer id){
        SqlSession ss = MybatisUtils.getSqlSession();
        UsersMapper mapper = ss.getMapper(UsersMapper.class);
        mapper.deleteByPrimaryKey(id);
        ss.commit();
    }
    //修改单个用户
    @PutMapping("/{id}")
    public String update(Users users){
        SqlSession ss = MybatisUtils.getSqlSession();
        UsersMapper mapper = ss.getMapper(UsersMapper.class);
        int i;
        try{
            i=mapper.updateByPrimaryKeySelective(users);
            if(i>0) {
                ss.commit();
                return "redirect:/admin/queryUsers";
            }
        }catch (Exception e){
            ss.rollback();
        }
        return "index";
    }
    //查询单个用户
    @GetMapping("/{id}")
    public String updateForm(@PathVariable Integer id,HttpServletRequest request){
        System.out.println(id);
        SqlSession ss = MybatisUtils.getSqlSession();
        UsersMapper mapper = ss.getMapper(UsersMapper.class);
        Users users = mapper.selectByPrimaryKey(id);
        request.setAttribute("users",users);
        return "/admin/updateUser";
    }
    @RequestMapping("/")
    public String adminPage(){
        return "/admin/index";
    }
}
