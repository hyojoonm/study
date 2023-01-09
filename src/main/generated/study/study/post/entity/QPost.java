package study.study.post.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = -1386429721L;

    public static final QPost post = new QPost("post");

    public final ListPath<study.study.answer.entity.Answer, study.study.answer.entity.QAnswer> answers = this.<study.study.answer.entity.Answer, study.study.answer.entity.QAnswer>createList("answers", study.study.answer.entity.Answer.class, study.study.answer.entity.QAnswer.class, PathInits.DIRECT2);

    public final StringPath body = createString("body");

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public final StringPath title = createString("title");

    public QPost(String variable) {
        super(Post.class, forVariable(variable));
    }

    public QPost(Path<? extends Post> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPost(PathMetadata metadata) {
        super(Post.class, metadata);
    }

}

