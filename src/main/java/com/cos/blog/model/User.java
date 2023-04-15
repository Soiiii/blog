package com.cos.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Timestamp;


// Getter,Setter 생성
@Data
// 빈생성자 생성
@NoArgsConstructor
// 전체 생성자
@AllArgsConstructor
// 빌더 패턴
@Builder
// User클래스가 MySQL에 테이블이 생성된다
@Entity
// insert할때 null인 필드 제외 시켜줌
@DynamicInsert
public class User {

    //ORM -> 자바(다른언어 포함) Object -> 테이블로 맵핑해주는 기술
    //oracle: 시퀀스, mysql: auto_increment
    @Id //Primary Key
    //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length=30)
    private String username;

    @Column(nullable = false, length=100)
    //1234=> 해쉬 (비밀번호 암호화)
    private String password;

    @Column(nullable = false, length = 50)
    private String email; //myEmail, my_email

    // Enum쓰는게 좋다 => 어떤 데이터의 도메인을 만들수가 있다
    // admin, 일반 user, manager들의 권한
    // @ColumnDefault("'user'")
    // ENUM 쓰는게 좋음
    // DB는 RoleType 이라는게 없다
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @CreationTimestamp
    //시간 자동으로 입력
    private Timestamp createDate;
}