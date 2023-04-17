package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

//ControllerAdvice: 어느 클래스에서 exception이 나던지 이 클래스로 오게됨
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    //IllegalArgumentException 해당 에러가 나게되면 exception에 대한 에러를 e 함수에 전달을 해준다
    @ExceptionHandler(value=IllegalArgumentException.class)
    public String handleArgumentException(IllegalArgumentException e){
        return "<h1>" + e.getMessage() + "</h1>";
    }

    // 해당되는 모든 Exception이 여기로 들어옴 (모든 exception의 부모 클래스)
    @ExceptionHandler(value=Exception.class)
    public String handleException(Exception e){
        return "<h1>" + e.getMessage() + "</h1>";
    }
}
