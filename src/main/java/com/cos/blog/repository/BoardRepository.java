package com.cos.blog.repository;

import com.cos.blog.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

// DAO
// 자동으로 Bean등록이 된다
// @Repository -> 생략가능함
public interface BoardRepository extends JpaRepository<Board, Integer> {

}
