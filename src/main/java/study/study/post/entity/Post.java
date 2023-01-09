package study.study.post.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.study.answer.entity.Answer;
import study.study.post.dto.PostPatchDto;
import study.study.post.dto.PostPostDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;

    private String body;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Builder
    public Post(Long postId, String title, String body) {
        this.postId = postId;
        this.title = title;
        this.body = body;
    }

    public static Post toEntity(PostPostDto postPostDto){
        return Post.builder()
                .title(postPostDto.getTitle())
                .body(postPostDto.getBody())
                .build();
    }

    public static Post toEntityPatch(PostPatchDto postPatchDto,Long postId){
        return Post.builder()
                .postId(postId)
                .title(postPatchDto.getTitle())
                .body(postPatchDto.getBody())
                .build();
    }
}
