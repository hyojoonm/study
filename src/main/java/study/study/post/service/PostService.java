package study.study.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import study.study.answer.dto.AnswerResponseDto;
import study.study.answer.entity.Answer;
import study.study.exception.BusinessLogicException;
import study.study.exception.ExceptionCode;
import study.study.exception.PostNotFound;
import study.study.post.dto.PostAnswerDto;
import study.study.post.dto.PostPatchDto;
import study.study.post.dto.PostPostDto;
import study.study.post.dto.PostResponseDto;
import study.study.post.entity.Post;
import study.study.post.repository.PostRepository;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    // 생성
    public Post create(PostPostDto postPostDto) {
        Post post = Post.toEntity(postPostDto);
        return postRepository.save(post);
    }

    // 업데이트
    public Post update(PostPatchDto postPatchDto,Long postId) {
        Post verifiedPost = findVerifiedPost(postId);
        Post findPost = Post.toEntityPatch(postPatchDto, postId);

        return postRepository.save(findPost);
    }

    // 중복검사
    public Post findVerifiedPost(long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        Post post = optionalPost.orElseThrow(() -> new BusinessLogicException(ExceptionCode.POST_NOT_FOUND));
        return post;
    }

    // 전체 조회
    public Page<Post> findPosts(int page, int size) {

        return postRepository.findAll(PageRequest.of(page, size, Sort.by("postId").descending()));
    }

    // 하나 조회
    public PostAnswerDto findPost(long postId) {
        Post findPost = findVerifiedPost(postId);
        List<AnswerResponseDto> answerResponseDto = postRepository.answerList(postId);

        return PostAnswerDto.toPostAnswerDto(findPost,answerResponseDto);
    }

    public PostAnswerDto findPostAnswer(long postId) {
        Post findPost = findVerifiedPost(postId);
        List<Answer> answers = postRepository.answerParentList(postId);

        List<AnswerResponseDto> answerResponseDtoList = new ArrayList<>();
        Map<Long,AnswerResponseDto> map = new HashMap<>();

        answers.stream().forEach(answer -> {
            AnswerResponseDto answerResponseDto = new AnswerResponseDto(answer);
            if (answer.getParent() != null){
                answerResponseDto.setParentId(answer.getParent().getAnswerId());
            }
            map.put(answerResponseDto.getAnswerId(),answerResponseDto);
            if (answerResponseDto.getParentId() != null){
                map.get(answer.getParent().getAnswerId()).getChildren().add(answer);
            }
            else answerResponseDtoList.add(answerResponseDto);
        });

        return PostAnswerDto.toPostAnswerDto(findPost,answerResponseDtoList);
    }

    // 삭제
    public void deletePost(long postId) {
        Post findPost = findVerifiedPost(postId);
        postRepository.delete(findPost);
    }
}
