package study.study.post.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import study.study.answer.dto.AnswerResponseDto;
import study.study.answer.entity.QAnswer;

import javax.persistence.EntityManager;
import java.util.List;

import static study.study.answer.entity.QAnswer.*;


public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PostRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<AnswerResponseDto>  answerList(Long postId){
        return queryFactory
                .select(Projections.fields(AnswerResponseDto.class,
                        answer.answerId,
                        answer.content
                ))
                .from(answer)
                .where(answer.post.postId.eq(postId))
                .fetch();
    }

}
