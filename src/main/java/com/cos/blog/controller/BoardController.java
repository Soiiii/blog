package com.cos.blog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
    @GetMapping("/")
    public String index(){
        // application.yml 에 이미 경로를 설정해놨기 떄문에 prefix, suffix의 경로를 앞에 붙여줌
        // /WEB-INF/views/index.jsp
        System.out.println("hjkhkj");
        return "index";
    }

}
