package study.study.testttt;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.study.answer.dto.AnswerResponseDto;

import javax.persistence.EntityManager;

import java.util.List;

import static study.study.testttt.QBook.*;

public class BookRepositoryCustomImpl implements BookRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public BookRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Book findBook(Long bookId){
        return queryFactory.select(book)
                .from(book)
                .where(book.bookId.eq(bookId))
                .fetchOne();
    }


    public List<BookDto> findBooks(Pageable pageable){
        List<BookDto> fetch = queryFactory.select(Projections.fields(BookDto.class,
                        book.bookName,
                        book.price,
                        book.isbn,
                        book.writer))
                .from(book)
                .where(book.writer.eq("12"))
                .fetch();


        return fetch;

    }


}
