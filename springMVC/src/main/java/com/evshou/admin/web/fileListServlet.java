package com.evshou.admin.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "fileListServlet",value = "/fls")
public class fileListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获得下载的目录路径
        String realPath = request.getServletContext().getRealPath("/WEB-INF/uploads");
        //2.创建Map集合 key--图片原名称  value--图片新名称
        Map<String,String> map=new HashMap<>();
    }
}
