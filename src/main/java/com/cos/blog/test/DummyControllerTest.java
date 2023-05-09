package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

//html파일이 아니라 data를 리턴해주는 = RestController
@RestController
public class DummyControllerTest {

    @Autowired //의존성 주입(DI)
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id){
        try{
            userRepository.deleteById(id);
        } catch (IllegalArgumentException e){
            return "삭제 실패. id 존재 안함";
        }

        return "삭제완료 id:" + id;
    }


    // email, password  수정할 예정
    // Json 데이터를 받으려면 @RequestBody가 필요하다
    // == Json 데이터 요청 => Java Object(MessageConverter의 Jackson 라이브러리가 변환해서 받아줌)
    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){
        System.out.println("id:" + id);
        System.out.println("password:" + requestUser.getPassword());
        System.out.println("email:" + requestUser.getEmail());


        // 실제 데이터베이스에 있는 값을 먼저 user에 담는다
        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패");
        });
        // Postman에서 수정할 password, email 값을 set함
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

//        userRepository.save(user);
        return user;
    }

    // http://localhost:8000/blog/dummy/user
    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    //http://localhost:8000/blog/dummy/user?page=2
    //page size에 따라 가져 올 데이터 갯수가 다름
    // 한페이지당 2건의 데이터를 리턴받아 볼 예정
    @GetMapping("/dummy/user")
    public Page<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC)Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);
        List<User> users = pagingUser.getContent();

        if(pagingUser.isFirst()){
        }
        return pagingUser;
    }


    // {id} 주소로 파라미티러를 전달 받을수가 있다.
    // http://localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    // @PathVariable : 함수의 파라미터에서 주소의 변수값 처리를 해줌, id는 주소값의 변수와 똑같게
    public User detail(@PathVariable int id){
        // user의 4번 데이터베이스에서 못찾아오게 되면 user가 null이 되므로
        // 그러면 null이 리턴이 되는데 문제가 생긴다
        // 그래서 Optional로 User 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return하라
        //User user = userRepository.findById(id).get(); -> 이렇게 하면 무슨 값이던 가져오는데
        // 실제 해당 되는 값이 없으면 에러의 위험이 있음
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//        User user = userRepository.findById(id).orElseGet(new Supplier<User>() {

            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id:" + id);
            }
        });
        //요청 : 웹 브라우저
        //user 객체 = 자바 오브젝트
        // (기존) 웹 브라우저가 이해할수 있는 데이터로 user 객체를 변환해서 return 해야함 -> json(Gson라이브러리)
        // (스프링부트) = MessageConverter 라는 애가 응답시에 자동으로 작동한다
        // 만약 자바 오브젝트를 리턴하게 되면 MessageConverter 가 Jackson 라이브러리를 호출해서
        // user 오브젝트를 json으로 변환해서 브라우저에 던져준다!
        return user;
    }

    // http://localhost:8000/blog/dummy/join (요청)
    // http의 바디에 username, password, email 데이터를 가지고 요청
    @PostMapping("/dummy/join")
    //RequestParam 실제로 받는 key값, 내가 보내는 key값("username")에 따라서 변수에 값이 들어옴(username)
    //public String join(@RequestParam("username") String username, String password, String email){

    // Postman에서 x-www-form-urlencoded 형식으로 바디에 담아 요청을 하면 key=value 형태로 스프링이 함수에 파라미터로
    // 파싱해서 집어 넣어준다
    // public String join(String username, String password, String email){
    public String join(User user){
        System.out.println("id:" + user.getId());
        System.out.println("username:" +user.getUsername());
        System.out.println("password:" + user.getPassword());
        System.out.println("email:" + user.getEmail());
        System.out.println("role:" + user.getRole());
        System.out.println("createDate:" + user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}
