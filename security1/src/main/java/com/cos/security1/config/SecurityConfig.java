package com.cos.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 활성화 시 스프링 시큐리티 필터가 필터체인에 등록
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secure 어노테이션 활성화, preAuthorize + postAuthorize 어노테이션 활성화
public class SecurityConfig {

    // 해당 메서드의 리턴되는 오브젝트를 IoC로 등록해준다.
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                    .antMatchers("/user/**").authenticated()
                    .antMatchers("/manager/**").access("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
                    .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                    .anyRequest().permitAll()
                    // 권한없는 페이지를 접근할 때 로그인 페이지로 이동
                .and()
                    .formLogin()
                    .loginPage("/loginForm")
                    // /login 주소 호출 시 시큐리티가 넘겨받아 대신 로그인을 진행.
                    .loginProcessingUrl("/login")
                    // loginForm을 통해서 로그인을 하면 "/"로 보내주지만, 특정 페이즈를 통해서 넘어와서 login시 해당 페이저로 리다이렉트한다.
                    .defaultSuccessUrl("/");

        return http.build();
    }
}
