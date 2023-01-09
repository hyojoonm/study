package study.study.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.study.post.entity.Post;

@Getter
@NoArgsConstructor
public class PostResponseDto {

    private Long postId;

    private String title;

    private String body;

    @Builder
    public PostResponseDto(Long postId, String title, String body) {
        this.postId = postId;
        this.title = title;
        this.body = body;
    }

    public static PostResponseDto CreatePostDto(Post post){
        return PostResponseDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .body(post.getBody())
                .build();
    }

}
