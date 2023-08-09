package com.hejow.deepdivesecurity.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    private static final int TOKEN_VALID_TIME = 60 * 5;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailService() {
        UserDetails user = User.withUsername("user")
                .password("user123")
                .passwordEncoder(passwordEncoder()::encode)
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("admin123")
                .passwordEncoder(passwordEncoder()::encode)
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(List.of(user, admin));
    }

    @Bean
    public SecurityFilterChain adminFilter(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/admin/**")
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().hasRole("ADMIN")
                );
        http.formLogin(withDefaults());
        return http.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true);

        http.formLogin()
                .permitAll();

        http.logout()
                .deleteCookies("remember-me", "JSESSIONID")
                .permitAll();

        http.rememberMe()
                .key("deep-dive-security")
                .rememberMeParameter("remember-me")
                .tokenValiditySeconds(TOKEN_VALID_TIME);

        http.authorizeHttpRequests()
                .requestMatchers("/mypage").hasAnyRole("USER", "ADMIN")
                .anyRequest().permitAll();

        return http.build();
    }
}
