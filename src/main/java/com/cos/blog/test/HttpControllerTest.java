package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

//사용자가 요청 -> 응답(HTML파일)
//@Controller

//사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {

    public static final String TAG = "HttpController Test: ";

    //localhost:8000/blog/http/lombok
    @GetMapping("/http/lombok")
    public String lombokTest(){
        Member m1 = new Member(1,"ss","123", "email");
        Member.MemberBuilder m = Member.builder().username("ssar").password("1234").email("asdf");

        System.out.println(TAG + "getter" + m1.getId());
        m1.setId(5000);
        System.out.println(TAG + "setter" + m1.getId());
        return "lombok test Done";
    }


    //인터넷 브라우저 요청은 무조건 get 요청밖에 할 수 없다
    //http://localhost:8080/http/get (select)
    @GetMapping("/http/get")
    public String getTest(Member m){
        System.out.println(TAG + "getter" + m.getId());
        m.setId(5000);
        System.out.println(TAG + "setter" + m.getId());

        return "get 요청" + m.getId() +"," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
    }

    //http://localhost:8080/http/post (insert)
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m){
        return "post 요청" + m.getId() +"," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
    }

    //수정하고 싶은 데이터가 있을때
    //http://localhost:8080/http/put (update)
    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m){
        return "put 요청" + m.getId() +"," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
    }

    //http://localhost:8080/http/delete (delete)
    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete 요청";
    }

}
