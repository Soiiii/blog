package com.cos.blog.config.auth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//Spring Security가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
//Spring Security 고유한 세션저장소에 저장을 해준다

@Getter
public class PrincipalDetail implements UserDetails {

    //class ~~~ extends User -> 상속
    //User user -> 객체를 품고있는것을 컴포지션이라고 부름
    private User user;

    //PrincipalDetailService에서 loadUserByUsername에 return 값을 PrincipalDetail로 설정했는데
    //생성자에 User값을 셋팅해주지 않으면 null값이 되므로 생성해야한다
    public PrincipalDetail(User user){
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //계정이 만료되지 않았는지 리턴한다 (true: 만료안됨, false:계정 만료)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겨있는지 아닌지 (true: 안 잠겨있음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호가 만료되었는지 아닌지 리턴(true: 만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정 활성화(사용가능)인지 리턴(true:활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

    //계정의 권한 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();
        // add를 하면 GrantedAuthority타입을 넣어주어야한다

        collectors.add(()-> {return "ROLE_" ;});
        return collectors;
   }

}
