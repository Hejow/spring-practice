package com.example.demo.repository;

import com.example.demo.TestConfig;
import com.example.demo.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import javax.transaction.Transactional;
import java.util.stream.IntStream;

import static com.example.demo.entity.QPost.post;

@SpringBootTest
@Import(TestConfig.class)
public class postRepositoryTests {
    @Autowired
    JPAQueryFactory queryFactory;

    @Test
    public void repoTest() {

    }

    @Test
    @Transactional
    public void insertDummies() {
        Member member = Member.builder().id(350L).build();

        IntStream.rangeClosed(1, 10).forEach(i -> {
            queryFactory
                    .update(post)
                    .set(post.title, "test" + i)
                    .set(post.content, "test"+i)
                    .set(post.member, member)
                    .execute();
        });
    }
}
