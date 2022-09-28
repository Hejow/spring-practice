package org.zerok.mreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerok.mreview.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
