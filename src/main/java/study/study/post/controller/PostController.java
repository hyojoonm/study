package study.study.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.study.post.dto.*;
import study.study.post.entity.Post;
import study.study.post.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @PostMapping("/post")
    public ResponseEntity create(@RequestBody PostPostDto postPostDto){
        Post response = postService.create(postPostDto);

        return new ResponseEntity<>(new SingleResponseDto<>(PostResponseDto.CreatePostDto(response)),HttpStatus.CREATED);
    }

    @PatchMapping("/post/{postId}")
    public ResponseEntity update (@PathVariable Long postId,@RequestBody PostPatchDto postPatchDto){

        Post response = postService.update(postPatchDto, postId);
        return new ResponseEntity<>(new SingleResponseDto<>(PostResponseDto.CreatePostDto(response)),HttpStatus.OK);
    }

    @GetMapping("/post")
    public ResponseEntity getPosts(@RequestParam int page, @RequestParam int size){
        Page<Post> posts = postService.findPosts(page - 1, size);
        List<PostResponseDto> content = posts.getContent().stream()
                .map(post -> PostResponseDto.builder()
                        .postId(post.getPostId())
                        .title(post.getTitle())
                        .body(post.getBody())
                        .build())
                .collect(Collectors.toList());
        // TODO : 리턴 타입도 다시 빌더 패턴으로 바꾸기
        return new ResponseEntity(new MultiResponseDto<>(content,posts),HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity getPost(@PathVariable Long postId){
        PostAnswerDto post = postService.findPostAnswer(postId);

        return new ResponseEntity<>(new SingleResponseDto<>(post),HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity deletePost(@PathVariable Long postId){
        postService.deletePost(postId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
