package study.study.answer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import study.study.answer.dto.AnswerPatchDto;
import study.study.answer.dto.AnswerPostDto;
import study.study.answer.entity.Answer;
import study.study.answer.repository.AnswerRepository;
import study.study.exception.AnswerNotFound;
import study.study.exception.PostNotFound;
import study.study.post.entity.Post;
import study.study.post.repository.PostRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final PostRepository postRepository;

    // 생성
    public Answer create(AnswerPostDto answerPostDto, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFound::new);
        Answer answer = Answer.toEntity(answerPostDto, post);
        return answerRepository.save(answer);
    }

    // 수정
    public Answer update(AnswerPatchDto answerPatchDto, Long answerId) {

        Answer findAnswer = answerRepository.findById(answerId).orElseThrow(AnswerNotFound::new);

        Optional.ofNullable(answerPatchDto.getContent())
                .ifPresent(findAnswer::setContent);

        return answerRepository.save(findAnswer);
    }

    // 전체 조회
    public Page<Answer> findAnswers(int page, int size) {

        return answerRepository.findAll(PageRequest.of(page, size, Sort.by("answerId")));

    }

    // 하나 조회
    public Answer findAnswers(Long answerId) {
        return answerRepository.findById(answerId).orElseThrow(AnswerNotFound::new);
    }

    // 삭제
    public void delete(Long answerId) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(AnswerNotFound::new);
        answerRepository.delete(answer);
    }
}
