package org.zerock.mreview.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mreview.entity.Member;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.Review;

import java.util.List;

@Transactional
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @EntityGraph(attributePaths = "member")
    List<Review> findByMovie(Movie movie);

    @Modifying
    @Query("delete from Review r where r.member = :member")
    void deleteByMember(Member member);
}
