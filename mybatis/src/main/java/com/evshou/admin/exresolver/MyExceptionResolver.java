package com.evshou.admin.exresolver;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyExceptionResolver implements HandlerExceptionResolver {
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {
        ModelAndView mv=new ModelAndView();
        ex.printStackTrace();
        /*识别异常*/
        if(ex instanceof NullPointerException){
            mv.setViewName("redirect:/index");
        }else if(ex instanceof IndexOutOfBoundsException){
            mv.setViewName("redirect:/index");
        }else mv.setViewName("redirect:/index");
        return mv;
    }
}
