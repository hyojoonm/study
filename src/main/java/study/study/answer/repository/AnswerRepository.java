package study.study.answer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.study.answer.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
}
