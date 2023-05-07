package com.cos.blog.config.auth;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service //Bean등록
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //스프링이 로그인 요청을 가로챌때 username, password 변수 2개를 가로채는데
    //password 부분처리는 알아서 한다. ->
    //username이 DB에 있는지만 확인해주면 된다. ->loadUserByUsername 여기서 함
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1. 로그인이 진행될때 loadUserByUsername이 자동으로 실행되면서
        //2. findByUsername에서 자동으로 해당 유저을 찾고
        // 2-1) 해당 유저가 없으면 사용자를 찾을수가 없다고 나온다
        //
        User principal = userRepository.findByUsername(username)
                .orElseThrow(()->{
                    return new UsernameNotFoundException(
                            "해당 사용자를 찾을수가 없습니다." + username
                    );
                });
        //위에서 나온 User에서의 principal 값을 담는다
        //return 타입이 PrincipalDetail -> 시큐리티의 세에 유저 정보가 저장이 된다
        return new PrincipalDetail(principal);
    }
}
