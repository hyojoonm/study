package study.study.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.study.answer.dto.AnswerResponseDto;
import study.study.post.entity.Post;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostAnswerDto {

    private Long postId;

    private String title;

    private String body;

    private List<AnswerResponseDto> answers = new ArrayList<>();

    @Builder
    public PostAnswerDto(Long postId, String title, String body, List<AnswerResponseDto> answers) {
        this.postId = postId;
        this.title = title;
        this.body = body;
        this.answers = answers;
    }

    public static PostAnswerDto toPostAnswerDto(Post post,List<AnswerResponseDto> answers){
        return PostAnswerDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .body(post.getBody())
                .answers(answers)
                .build();
    }
}
