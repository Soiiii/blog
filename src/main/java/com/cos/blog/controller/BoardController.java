package com.cos.blog.controller;


import com.cos.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    // {"" -> 아무것도 안 붙혀이을때, "/" -> /가 붙어있을때 여기로 보낸다}
    @GetMapping({"", "/"})
    public String index(Model model, @PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
        // application.yml 에 이미 경로를 설정해놨기 떄문에 prefix, suffix의 경로를 앞에 붙여줌
        // /WEB-INF/views/index.jsp
        model.addAttribute("boards", boardService.글목록(pageable));
        // apiController가 아니라 Controller이기 때문에 return할때 ViewResolver가 작동한다
        return "index";
    }

    //User권한 필요
    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }

}
