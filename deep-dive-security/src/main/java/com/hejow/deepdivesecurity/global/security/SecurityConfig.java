package com.hejow.deepdivesecurity.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
public class SecurityConfig {
    private static final int VALID_TOKEN_TIME = 300;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailService() {
        UserDetails user = User.builder()
                .username("user")
                .password("user123")
                .passwordEncoder(passwordEncoder()::encode)
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("admin123")
                .passwordEncoder(passwordEncoder()::encode)
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(List.of(user, admin));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder managerBuilder = new AuthenticationManagerBuilder(objectPostProcessor);
//        managerBuilder.inMemoryAuthentication()
//                .withUser("user").password("user123").roles("USER")
//                .and()
//                .withUser("admin").password("admin123").roles("ADMIN");

        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true);

        http.formLogin()
                .usernameParameter("아이디")
                .passwordParameter("비밀번호")
                .defaultSuccessUrl("/")
                .permitAll();

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("remember-me", "JSESSIONID")
                .permitAll();

        http.rememberMe()
                .key("deep-dive-security")
                .rememberMeParameter("remember-me")
                .tokenValiditySeconds(VALID_TOKEN_TIME);

        http.authorizeHttpRequests()
                .requestMatchers("/mypage").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().permitAll();

        return http.build();
    }
}
