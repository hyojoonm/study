package study.study.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.study.answer.entity.Answer;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponseDto {
    private Long answerId;

    private Long parentId;

    private String content;

    private String writer;


    private List<Answer> children = new ArrayList<>();

    @Builder
    public AnswerResponseDto(Long answerId, String content,String writer,Long parentId,List<Answer> children) {
        this.answerId = answerId;
        this.content = content;
        this.writer = writer;
        this.parentId =parentId;
        this.children = children;
    }

    public AnswerResponseDto(Answer answer) {
        this.answerId = answer.getAnswerId();
        this.content = answer.getContent();
        this.writer = answer.getWriter();

    }


    public static AnswerResponseDto responseCreate(Answer answer){
        return AnswerResponseDto.builder()
                .answerId(answer.getAnswerId())
                .content(answer.getContent())
                .writer(answer.getWriter())
                .build();
    }

    public static AnswerResponseDto responseParentCreate(Answer answer){
        return AnswerResponseDto.builder()
                .answerId(answer.getAnswerId())
                .content(answer.getContent())
                .writer(answer.getWriter())
                .parentId(answer.getParent().getAnswerId())
                .build();
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
