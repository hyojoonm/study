package study.study.testttt;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> ,BookRepositoryCustom{
}
