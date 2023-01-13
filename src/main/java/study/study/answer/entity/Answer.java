package study.study.answer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.study.answer.dto.AnswerChildrenDto;
import study.study.answer.dto.AnswerPostDto;
import study.study.post.entity.Post;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    private String content;

    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Answer parent;

    @OneToMany(mappedBy = "parent",orphanRemoval = true)
    private List<Answer> children = new ArrayList<>();


    public void setContent(String content) {
        this.content = content;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setParent(Answer parent) {
        this.parent = parent;
    }

    public void setChildren(List<Answer> children) {
        this.children = children;
    }

    @Builder
    public Answer(Long answerId, String content, Post post, String writer,Answer parent) {
        this.answerId = answerId;
        this.content = content;
        this.post = post;
        this.writer =writer;
        this.parent =parent;
    }

    public static Answer toEntity(AnswerPostDto answerPostDto, Post post){
        return Answer.builder()
                .content(answerPostDto.getContent())
                .post(post)
                .writer(answerPostDto.getWriter())
                .build();
    }

    public static Answer toParentEntity(AnswerChildrenDto answerChildrenDto, Post post, Answer answer){
        return Answer.builder()
                .content(answerChildrenDto.getContent())
                .post(post)
                .writer(answerChildrenDto.getWriter())
                .parent(answer)
                .build();
    }
}
