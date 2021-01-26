package com.evshou.admin.Controller;

import cn.dsna.util.images.ValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/vc")
public class VcodeController {
    @RequestMapping("/")
    public void code(HttpServletResponse response, HttpSession session) throws IOException {
        ValidateCode vc=new ValidateCode(120,50,4,1);
        vc.write(response.getOutputStream());
        session.setAttribute("vc",vc);
    }
}
