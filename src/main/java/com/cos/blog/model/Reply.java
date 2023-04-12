package com.cos.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Reply {
    @Id
    //Auto_Increment로 잡겠다
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 대용량 데이터
    @Column(nullable = false, length = 200)
    // 섬머노트 라이브러리 사용할 예정 <html>태그가 섞여서 디자인이 됨. -> 글자 용량이 매우 커짐
    private String content;

    @ManyToOne
    //ManyToOne 하나의 게시글에 여러개의 답변 존재
    //OneToOne 하나의 게시글에는 하나의 답변
    //OneToMany 여러개의 게시글에 하나의 답변만 존재
    @JoinColumn(name="boardId")
    private Board board;

    //누가 답변을 달았는지 알아야하니까
    //ManyToOne: 하나의 답변은 여러명의 유저가 달 수가 있다
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @CreationTimestamp
    private Timestamp createDate;

}
