package study.study.answer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.study.answer.entity.Answer;

@Getter
@NoArgsConstructor
public class AnswerResponseDto {
    private Long answerId;

    private String content;

    @Builder
    public AnswerResponseDto(Long answerId, String content) {
        this.answerId = answerId;
        this.content = content;
    }

    public static AnswerResponseDto responseCreate(Answer answer){
        return AnswerResponseDto.builder()
                .answerId(answer.getAnswerId())
                .content(answer.getContent())
                .build();
    }
}
