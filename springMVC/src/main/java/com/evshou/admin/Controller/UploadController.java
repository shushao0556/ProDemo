package com.evshou.admin.Controller;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;

@Controller
@RequestMapping("/uploads")
public class UploadController {
    @RequestMapping("/uploadForm")
    public String uploadForm(){
        return "uploadForm";
    }
    public String newFilePath(String basePath){
        LocalDateTime ldt=LocalDateTime.now();
        int year = ldt.getYear();
        int monthValue = ldt.getMonthValue();
        int dayOfYear = ldt.getDayOfYear();
        String month=String.valueOf(monthValue);
        String day=String.valueOf(dayOfYear);
        if(monthValue<10) month="0"+month;
        else if(dayOfYear<10) day="0"+day;
        String path=basePath+File.separator+year+File.separator+month+File.separator+day;
        File file=new File(path);
        if(!file.exists()) file.mkdirs();
        return path;
    }
    public String newFileName(String filename){
        LocalDateTime ldt=LocalDateTime.now();
        int dayOfYear = ldt.getYear();
        int monthValue = ldt.getMonthValue();
        int dayOfMonth = ldt.getDayOfMonth();
        String year=String.valueOf(dayOfYear);
        String month=String.valueOf(monthValue);
        String day=String.valueOf(dayOfMonth);
        if(monthValue<10) month="0"+month;
        else if(dayOfYear<10) day="0"+day;
        int hour = ldt.getHour();
        int minute = ldt.getMinute();
        int second = ldt.getSecond();
        String h= String.valueOf(hour);
        String m= String.valueOf(minute);
        String s= String.valueOf(second);
        if(hour<10) h+="0";
        else if(minute<10) m+="0";
        else if(second<10) s+="0";
        return year+month+day+h+m+s+"_"+filename;
    }
    public void getFileList(String path, HashMap<String,String> map){
        File file=new File(path);
        File[] files = file.listFiles();
        if (files != null) {
            for (File file1 : files) {
                if (file1.isDirectory()) {
                    getFileList(file1.getPath(),map);
                }else{
                    String name = file1.getName();
                    String sub=name.substring(name.lastIndexOf("_")+1);
                    map.put(name,sub);
                }
            }
        }
    }
    @RequestMapping("/")
    public String upload(MultipartFile[] mfs, HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("utf-8");
        if (mfs != null) {
            for (MultipartFile mf : mfs) {
                String filename = mf.getOriginalFilename();
                System.out.println(filename);
                String realPath = request.getServletContext().getRealPath("/WEB-INF/upload");
                String newFilePath = newFilePath(realPath);
                String newFilename = newFileName(filename);
                File file=new File(newFilePath);
                File file2=new File("D:"+File.separator+"uploadDemo");
                if(!file.exists())file.mkdirs();
                mf.transferTo(new File(file+File.separator+newFilename));
                OutputStream os=new FileOutputStream(file2+File.separator+newFilename);
                os.close();
            }
        }
        return "redirect:/uploads/fileList";
    }
    @RequestMapping("/fileList")
    public String fileList(HttpServletRequest request){
        String realPath = request.getServletContext().getRealPath("/WEB-INF/upload");
        HashMap<String,String> map=new HashMap<>();
        getFileList(realPath,map);
        request.setAttribute("map",map);
        return "showFileLists";
    }
    @RequestMapping("/download")
    public void download(String filename,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String realPath = request.getServletContext().getRealPath("/WEB-INF/upload");
        String newFilePath = newFilePath(realPath);
        response.setHeader("content-disposition","attachment;filename="+filename);
        IOUtils.copy(new FileInputStream(newFilePath+File.separator+filename),response.getOutputStream());
    }
}
