package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// DAO
// 자동으로 Bean등록이 된다
// @Repository -> 생략가능함
public interface UserRepository extends JpaRepository<User, Integer> {
    //Naming query
    //SELECT * FROM user WHERE username =1?;
    //첫번째 파라미터인 username이 1번부분에 들어가면서 쿼리가 실행된다. (규칙임)
    Optional<User> findByUsername(String username);

    //해당 JpaRepository user 테이블이 관리하는 레파지토리다. User테이블의 PK는 Integer이다

    //JPA Naming query
    // SELECT * FROM user WHERE username=? AND password=?
    // 전략 로그인을 위한 함수
//    User findByUsernameAndPassword(String username, String password);

    //UserRepository.login 을 쓰면 아래의 쿼리문이 동작
//    @Query(value = "value=SELECT * FROM user WHERE username=? AND password=?", nativeQuery = true)
//    User login(String username, String password);
}
