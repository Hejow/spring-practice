package org.zerock.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.guestbook.entitiy.Guestbook;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long> {

}
