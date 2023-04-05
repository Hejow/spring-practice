package com.example.demo.repository;

import com.example.demo.TestConfig;
import com.example.demo.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.stream.IntStream;

@SpringBootTest
@Import(TestConfig.class)
public class memberRepositoryTests {
    @Autowired
    private memberRepository repository;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = repository.save(Member.builder().name("test"+i).build());
        });
    }
}
