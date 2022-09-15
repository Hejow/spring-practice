package org.zerock.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.board.entity.Member;

public class BoradRepository extends JpaRepository<Member, String> {
}
