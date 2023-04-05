package com.cos.security1.config.auth;

import com.cos.security1.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 시큐리티가 /login 요청이 오면 넘겨받아 로그인을 진행한다.
 * 로그인이 진행이 완료가 되면 시큐리티 Session에 넣어준다. (Security ContextHolder : 키값)
 * 같은 세션 공간 but 시큐리티만의 공간 -> Security Session, 들어갈 수 있는 오브젝트 -> Authentication 객체
 * Authentication 객체 : user Object(UserDetails 객체)를 가진다.
 */


public class PrincipalDetails implements UserDetails {
    private User user; // 콤포지션
    
    public PrincipalDetails(User user) {
        this.user = user;
    }

    // 해당 user의 권한을 리턴하는 메서드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        
        // 1년 미 로그인 시 휴면계정
        // 현재시간 - 로그인시간 > 1년 : return false
        return true;
    }
}
