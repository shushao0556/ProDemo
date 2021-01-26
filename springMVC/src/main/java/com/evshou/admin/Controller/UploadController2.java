package com.evshou.admin.Controller;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/up")
public class UploadController2 {
    @RequestMapping("/upForm")
    public String upForm(){
        return "uploadForm2";
    }
    //上传的路径
    public String uploadDispath(String basePath){
        LocalDateTime ldt=LocalDateTime.now();
        int dayOfYear = ldt.getYear();
        int monthValue = ldt.getMonthValue();
        int dayOfMonth = ldt.getDayOfMonth();
        String year=String.valueOf(dayOfYear);
        String month=String.valueOf(monthValue);
        String day=String.valueOf(dayOfMonth);
        if (monthValue < 10) month="0"+monthValue;
        if (dayOfMonth < 10) day="0"+dayOfMonth;
        String dir=basePath+File.separator+year +File.separator+month+File.separator+ day;
        File file=new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dir;
    }
    //重命名文件名称
    public String uploadFilename(String filename){
        LocalDateTime ldt=LocalDateTime.now();
        int dayOfYear = ldt.getYear();
        int monthValue = ldt.getMonthValue();
        int dayOfMonth = ldt.getDayOfMonth();
        int hour = ldt.getHour();
        int minute = ldt.getMinute();
        int second = ldt.getSecond();
        String year=String.valueOf(dayOfYear);
        String month=String.valueOf(monthValue);
        String day=String.valueOf(dayOfMonth);
        String h= String.valueOf(hour);
        String m= String.valueOf(minute);
        String s= String.valueOf(second);
        if (hour < 10) {
            h="0"+hour;
        } else if (minute < 10) {
            m="0"+minute;
        } else if (second < 10) {
            s="0"+second;
        }
        if (monthValue < 10) month="0"+monthValue;
        if (dayOfMonth < 10) day="0"+dayOfMonth;
        return year+month+day+h+m+s+"_"+filename;
    }
    //文件上传
    @RequestMapping("/upload")
    public String upload(MultipartFile[] filename, HttpServletRequest request) throws IOException {
        if (filename != null) {
            for (MultipartFile multipartFile : filename) {
                String fileName = multipartFile.getOriginalFilename();
                System.out.println("fileName:"+fileName);//fileName:3.png
                //获取文件上传的路径
                String realPath = request.getServletContext().getRealPath("/WEB-INF/uploads");
                String path = uploadDispath(realPath);//返回带有年月日的文件夹
                System.out.println("path:"+path);
                File file=new File(path);
                //获得文件的后缀
                String extension = FilenameUtils.getExtension(fileName);
                //生成唯一的文件名
                String filenames = uploadFilename(fileName);//返回带有年月日时分秒的文件名
                System.out.println("filenames:"+filenames);//filenames:20210123120744_3.png
                //获取文件类型
                String type = multipartFile.getContentType();
                //filename:20210123120744_3.png,type:image/png
                System.out.println("filename:"+filenames+",type:"+type);
                if (!file.exists()) {
                    file.mkdirs();
                }else multipartFile.transferTo(new File(file+File.separator+filenames));
                OutputStream os=new FileOutputStream(file+File.separator+filenames);
                os.close();
            }
        }
        return "index";
    }
    //文件下载
    @RequestMapping("/download")
    public void download(String filename, HttpServletRequest request,HttpServletResponse response)
            throws IOException {
        System.out.println("filename:"+filename);
        //获取下载的路径
        String realPath = request.getServletContext().getRealPath("/WEB-INF/uploads");
        String newPath = uploadDispath(realPath);
        response.setHeader("content-disposition","attachment;filename="+filename);
        IOUtils.copy(new FileInputStream(newPath+File.separator+filename),response.getOutputStream());
    }
    //获取文件列表
    public void getFileLists(String path,Map<String,String> filenames){
        //1.路径当成文件对象
        File file=new File(path);
        //2.获取该目录(uploads)下所有内容，包括文件或文件夹
        File[] files = file.listFiles();
        if (files!=null) {
            for (File file1 : files) {
                //如果是文件夹，递归遍历
                if (file1.isDirectory()) {
                    getFileLists(file1.getPath(),filenames);
                }else{
                    String name = file1.getName();
                    //获得原名称和新名称
                    String str=name.substring(name.lastIndexOf("_")+1);
                    filenames.put(name,str);
                }
            }
        }
    }
    //文件列表
    @RequestMapping("/fileList")
    public String fileList(HttpServletRequest request){
        //1.获得下载的目录路径
        String realPath = request.getServletContext().getRealPath("/WEB-INF/uploads");
        //2.创建Map集合 key--图片原名称  value--图片新名称
        Map<String,String> map=new HashMap<>();
        getFileLists(realPath,map);
        request.setAttribute("map",map);
        return "showFileLists";
    }
}
