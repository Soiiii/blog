package com.cos.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    //Auto_Increment로 잡겠다
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length=100)
    private String title;

    // 대용량 데이터
    @Lob
    // 섬머노트 라이브러리 사용할 예정 <html>태그가 섞여서 디자인이 됨. -> 글자 용량이 매우 커짐
    private String content;

    @ColumnDefault("0")
    // 조회수
    private int count;

    // DB는 오브젝트를 저장할 수 없다.
    // Foreign Key, Java에서는 오브젝트를 저장할 수 있다.
    @ManyToOne(fetch = FetchType.EAGER)
    // Board테이블에서 Select하면 user 정보는 가져올게 한건만 있으니까! fetch = FetchType.EAGER
    // Many = Board, User = one (한명의 유저는 여러개의 게시글을 쓸 수 있다.)
    // OneToOne 한 명의 유저는 한 개의 게시글만 쓸 수 있다
    @JoinColumn(name="userId")
    private User user;

    // Reply 엔터티에서의 Board엔티티의 변수를 넣음
    // mappedBy: 연관관계의 주인이 아니다(FK가 아니라는 의미)
    // DB에 칼럼을 만들지 마세요라는 의미
    // Reply의 Board 변수가 FK이므로 Board에는 FK를 선언할 필요가 없다
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    // OneToMany의 Fetch타입은 fetch = FetchType.LAZY -> Board 하나의 Reply 수십만건이 될 수 있으므로
    // 필요하면 가져오고 필요 없으면 안들고 온다 = Fetch의 전략 LAZY
    // 영상에서 만드는 페이지 게시판의 상세보기에서 댓글창이 바로 보이므로 이건 EAGER로 설정을 해야함! 근데 댓글창이 댓글 펼치기 버튼을 누르는 기능이라면
    // Lazy로 설정하면 됨
    // FK는 필요없음 -> Board테이블에서 한 게시글에 대한 답변 번호가 1,2,3으로 설정이 불가하므로
    // @JoinColumn(name="replyId")
    // 답변은 다건으로 들어오니까 List로 받아야함
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;
}
