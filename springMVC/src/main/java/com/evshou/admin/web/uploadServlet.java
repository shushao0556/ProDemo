package com.evshou.admin.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "uploadServlet",value = "/up")
@MultipartConfig(maxFileSize = 1024*1024*100,maxRequestSize = 1024*1024*200)
public class uploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //2.获取文件上传的路径
        String realPath = request.getServletContext().getRealPath("/WEB-INF/upload");
        File file=new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        //文件上传实现步骤
        //1.通过请求获得多分布的集合
        Collection<Part> parts = request.getParts();
        for (Part part : parts) {
            String fileName = part.getSubmittedFileName();
            if (fileName != null) {
                //文件-->上传(路径+文件名)
                part.write(realPath+File.separator+fileName);
                part.delete();//清除临时缓冲区
            }else{
                String name = part.getName();
                String parameter = request.getParameter(name);
                System.out.println(parameter);
            }
        }
    }
}
