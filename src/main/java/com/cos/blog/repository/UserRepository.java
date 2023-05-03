package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// DAO
// 자동으로 Bean등록이 된다
// @Repository -> 생략가능함
public interface UserRepository extends JpaRepository<User, Integer> {


    //해당 JpaRepository user 테이블이 관리하는 레파지토리다. User테이블의 PK는 Integer이다

    //JPA Naming query
    // SELECT * FROM user WHERE username=? AND password=?
    // 전략 로그인을 위한 함수
//    User findByUsernameAndPassword(String username, String password);

    //UserRepository.login 을 쓰면 아래의 쿼리문이 동작
//    @Query(value = "value=SELECT * FROM user WHERE username=? AND password=?", nativeQuery = true)
//    User login(String username, String password);
}
