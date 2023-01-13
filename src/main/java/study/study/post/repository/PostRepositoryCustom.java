package study.study.post.repository;

import study.study.answer.dto.AnswerResponseDto;
import study.study.answer.entity.Answer;

import java.util.*;

public interface PostRepositoryCustom {
    List<AnswerResponseDto>  answerList(Long postId);

    List<Answer> answerParentList(Long postId);
}
