package org.zerok.mreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerok.mreview.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
