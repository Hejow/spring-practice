package com.hejow.toby_spring.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
    public class DaoFactory {

        @Bean
        public UserDao userDao() {
            return new UserDao(connectionMaker());
        }

        // 분리해서 중복을 해결했다.
        @Bean
        public ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
}
