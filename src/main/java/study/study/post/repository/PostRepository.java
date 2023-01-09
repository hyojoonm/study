package study.study.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.study.post.entity.Post;

public interface PostRepository extends JpaRepository<Post,Long> , PostRepositoryCustom {
}
