package com.cos.security1.repository;

import com.cos.security1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


// JpaRepository 상속했기 때문에 @Repository 라는 어노테이션이 없어도 IoC가 가능하다.
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
}
