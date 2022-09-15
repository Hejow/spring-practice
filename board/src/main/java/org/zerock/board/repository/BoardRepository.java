package org.zerock.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.board.entity.Board;

public class BoardRepository extends JpaRepository<Board, Long> {
}
