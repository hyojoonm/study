package study.study.post.repository;

import study.study.answer.dto.AnswerResponseDto;

import java.util.*;

public interface PostRepositoryCustom {
    List<AnswerResponseDto>  answerList(Long postId);
}
