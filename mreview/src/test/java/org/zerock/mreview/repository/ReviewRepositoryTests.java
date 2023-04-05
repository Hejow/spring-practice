package org.zerock.mreview.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.zerock.mreview.TestConfig;
import org.zerock.mreview.entity.Member;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.Review;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Import(TestConfig.class)
public class ReviewRepositoryTests {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    JPAQueryFactory queryFactory;

    @Test
    public void insertReviews() {
        IntStream.rangeClosed(1, 200).forEach(i -> {
            Long mno = (long)(Math.random()*100) + 1;

            Long mid = ((long)(Math.random()*100) + 1);
            Member member = Member.builder().mid(mid).build();

            Review movieReview = Review.builder()
                    .member(member)
                    .movie(Movie.builder().mno(mno).build())
                    .grade((int)(Math.random()*5)+1)
                    .text("이 영화에 대한 느낌..." + i)
                    .build();

            reviewRepository.save(movieReview);
        });
    }

    @Test
    @DisplayName("영화로 리뷰들 가져오기 테스트")
    public void testGetMovieReviews() {
        Movie movie = Movie.builder().mno(100L).build();

        List<Review> result = reviewRepository.findByMovie(movie);

        result.forEach(review -> {
            System.out.println(review.getReviewnum());
            System.out.println(review.getGrade());
            System.out.println(review.getText());
            System.out.println(review.getMember().getEmail());
        });
    }
}
