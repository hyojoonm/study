package study.study.post.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import study.study.answer.dto.AnswerResponseDto;
import study.study.answer.entity.Answer;
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
                        answer.content,
                        answer.writer,
                        answer.parent.answerId
                ))
                .from(answer)
                .where(answer.post.postId.eq(postId))
                .orderBy(answer.parent.answerId.asc().nullsFirst(),answer.answerId.asc())
                .fetch();
    }

    public List<Answer> answerParentList(Long postId){
        return queryFactory.select(answer)
                .from(answer)
                .leftJoin(answer.parent)
                .fetchJoin()
                .where(answer.post.postId.eq(postId))

                .orderBy(answer.parent.answerId.asc().nullsFirst())
                .fetch();
    }

}
