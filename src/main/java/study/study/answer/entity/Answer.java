package study.study.answer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.study.answer.dto.AnswerPostDto;
import study.study.post.entity.Post;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Answer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    public void setContent(String content) {
        this.content = content;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    @Builder
    public Answer(Long answerId, String content, Post post) {
        this.answerId = answerId;
        this.content = content;
        this.post = post;
    }

    public static Answer toEntity(AnswerPostDto answerPostDto, Post post){
        return Answer.builder()
                .content(answerPostDto.getContent())
                .post(post)
                .build();
    }
}
