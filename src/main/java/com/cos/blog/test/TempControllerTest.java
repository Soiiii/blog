package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

    //http://localhost:8000/blog/temp/home
    @GetMapping("/temp/home")
    public String tempHome(){
        System.out.println("tempHome()");
        //파일리턴 기본경로: src/main/resources/static
        //리턴명 : /home.html
        //prefix: /WEB-INF/vies/
        //suffix: .jsp
        //fullname : /WEB-INf/views/test.jsp
        return "/home.html";
    }
    @GetMapping("/temp/img")
    public String tempImg(){
        return "/a.jpg";
    }
    @GetMapping("/temp/jsp")
    public String tempJsp(){
        return "/test.jsp";
    }

}
