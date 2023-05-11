package com.cos.blog.service;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해준다, IoC를 해준다
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void 글쓰기(Board board, User user) { //title, content
        //조회수 기본값:0
        board.setCount(0);
        board.setUser(user);
        //save를 하면 저장이됨
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> 글목록(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    //select만 해오므로 readOnly true로 설정한다
    @Transactional(readOnly = true)
    public Board 글상세보기(int id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("Fail : cannot find id");
                });
    }

    @Transactional
    public void 삭제하기(int id){
        System.out.println("글 삭제하기" + id);
        boardRepository.deleteById(id);
    }

    @Transactional
    public void 글수정하기(int id, Board requestBoard){
        //영속화시킨다
        Board board = boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 상세보기 실패: cannot find id");
                });
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        //해당 함수로 종료시에(Service가 종료될 때) 트랜잭션이 종료된다.
        //이때 더티체킹 -> 왜냐하면 영속화되어있는 보드의 데이터가 달라졌기때문에
        // -자동 업데이트가 된다. DB flush
    }

}
