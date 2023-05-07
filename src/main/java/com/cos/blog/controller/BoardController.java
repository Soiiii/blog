package com.cos.blog.controller;


import com.cos.blog.config.auth.PrincipalDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
    // {"" -> 아무것도 안 붙혀이을때, "/" -> /가 붙어있을때 여기로 보낸다}
    @GetMapping({"", "/"})
    public String index(@AuthenticationPrincipal PrincipalDetail principal){
        // application.yml 에 이미 경로를 설정해놨기 떄문에 prefix, suffix의 경로를 앞에 붙여줌
        // /WEB-INF/views/index.jsp
        System.out.println("hjkhkj" + principal.getUsername());
        return "index";
    }

}
