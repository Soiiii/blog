package com.cos.blog.controller.api;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    //세션 객체를 스프링 컨트롤러가 빈으로 등록해서 가지고 있다(필요시 DI에서 불러와서 사용이 가능)
    //autowired를 해놓으면 파라미터로 session을 안 받아내도 됨
//    @Autowired
//    private HttpSession session;

    //회원가입하는 페이지 auth-> 인증 안된 사용자에게도 열어줌
    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user){
        // User 클래스를 사용하면 username, password, email 들어가서
        user.setRole(RoleType.USER);
        System.out.println("UserAPIController: Call Save");
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

//    @PostMapping("/api/user/login")
//    //HttpSession -> 세션을 만들어주는
//    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
////    public ResponseDto<Integer> login(@RequestBody User user){
//        System.out.println("UserAPIController: Call Login");
//        User principal = userService.로그인(user); //principal = 접근주체라는 의미
//        if(principal != null){
//            //session set해주면 됨
//            session.setAttribute("principal", principal);
//        }
//        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//    }
}
