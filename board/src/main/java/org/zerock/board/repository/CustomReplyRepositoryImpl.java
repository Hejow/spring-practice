package org.zerock.board.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.dto.ReplyDTO;
import org.zerock.board.dto.testDTO;
import org.zerock.board.entity.Reply;

import java.util.List;

import static org.zerock.board.entity.QReply.reply;

@Repository
@Transactional
@RequiredArgsConstructor
public class CustomReplyRepositoryImpl implements CustomReplyRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<testDTO> getReplyList(Long boardId) {
        return queryFactory
                .select(Projections.constructor(testDTO.class,
                        reply.replyer,
                        reply.text))
                .from(reply)
                .where(reply.board.bno.eq(boardId))
                .fetch();
    }

    @Override
    public List<Long> getReplyIdList(Long boardId) {
        return queryFactory
                .select(reply.rno)
                .from(reply)
                .where(reply.board.bno.eq(boardId))
                .fetch();
    }
}
