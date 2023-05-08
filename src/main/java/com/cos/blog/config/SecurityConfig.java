package com.cos.blog.config;

import org.springframework.context.annotation.Configuration;

//Bean등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는것
@Configuration // IoC
//@EnableWebSecurity // Security 필터가 등록됨 = 스프링 시큐리티가 활성화가 되어 있는데 어떤 설정을 해당 파일에서 하겠다
//@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다
public class SecurityConfig{

    /**
    @Autowired
    private PrincipalDetailService principalDetailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CorsConfig corsConfig;

        //IOC가 됨
        @Bean
        public BCryptPasswordEncoder encodePWD(){
            //1234를 암호화해서 encPassword에 다 넣어준다
            String encPassword = new BCryptPasswordEncoder().encode("1234");
            return new BCryptPasswordEncoder();
        }

    public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http
                    .addFilter(corsConfig.corsFilter())
                    .addFilter(new JwtAuthenticationFilter(authenticationManager))
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository));
        }
    }

}


//시큐리티가 대신 로그인을 해주는데 password 가로채기를 하는데
    //해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수가 있음
    public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http
                    .addFilter(corsConfig.corsFilter());
//                    .addFilter(new JwtAuthenticationFilter(authenticationManager))
//                    .addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository));
        }
    }




        //@EnableWebSecurity가 활성화되면 여기가 작동
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                //csrf토큰 비활성화
                .csrf().disable()
                //요청이 들어오면
                .authorizeHttpRequests()
                        ///auth/** 해당 폼으로 들어오면 누구든지 허가를 준다
                        .requestMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**").permitAll()
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
                .loginPage("/auth/loginForm")
                //스프링 시큐리티가 해당 주소로 요청 오는 로그인을 가로채서 대신 로그인을 해줌
                .loginProcessingUrl("/auth/loginProc")
                //정상적인 로그인이 되면
                .defaultSuccessUrl("/");
        return http.build();
    }
    **/
}
