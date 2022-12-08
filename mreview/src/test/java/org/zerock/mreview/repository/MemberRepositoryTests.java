package org.zerock.mreview.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.zerock.mreview.entity.Member;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMembers() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("r"+i+"@naver.com")
                    .pw("1111")
                    .nickname("reviewer"+i).build();

            memberRepository.save(member);
        });
    }

    @Test
    @Commit
    public void 유저삭제테스트() {
        Long memberId = 10L;

        Member member = Member.builder().mid(memberId).build();

        // 외래 키를 가지는 쪽부터 삭제해야 한다.
        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(memberId);
    }
}
