package org.zerock.board.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.dto.ReplyDTO;
import org.zerock.board.dto.testDTO;

import java.util.List;

@Repository
@Transactional
public interface CustomReplyRepository {
    List<testDTO> getReplyList(Long boardId);

    List<Long> getReplyIdList(Long boardId);
}
