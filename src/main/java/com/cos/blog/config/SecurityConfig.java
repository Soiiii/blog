package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//Bean등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는것
@Configuration // IoC
@EnableWebSecurity // Security 필터가 등록됨 = 스프링 시큐리티가 활성화가 되어 있는데 어떤 설정을 해당 파일에서 하겠다
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    //@EnableWebSecurity가 활성화되면 여기가 작동
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http

                //요청이 들어오면
                .authorizeHttpRequests()
                        ///auth/** 해당 폼으로 들어오면 누구든지 허가를 준다
                        .requestMatchers("/auth/**").permitAll()
                        //이게 아닌 다른 모든 요청들은 인증 필요
                        .anyRequest().permitAll()
                        .and()

//                .antMatchers("/auth/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
                //로그인 안된 사용자는 /auth/loginForm으로 이동
                .formLogin()
                .loginPage("/auth/loginForm");
        return http.build();
    }
}
