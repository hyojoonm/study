package study.study.testttt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.study.answer.dto.AnswerPostDto;
import study.study.answer.dto.AnswerResponseDto;
import study.study.answer.entity.Answer;
import study.study.post.dto.SingleResponseDto;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @PostMapping("/test")
    public ResponseEntity create(@RequestBody Book book){
        Book save = bookRepository.save(book);

        return new ResponseEntity<>(new SingleResponseDto<>(save), HttpStatus.CREATED);
    }

    @PatchMapping("/test")
    public ResponseEntity update(@RequestBody Book book){
        Book findBook = bookRepository.findById(book.getBookId()).get();
        Book save = bookRepository.save(findBook);

        return new ResponseEntity<>(new SingleResponseDto<>(save), HttpStatus.CREATED);
    }
    @DeleteMapping("/test/{bookId}")
    public ResponseEntity delete(@PathVariable Long bookId){
        Book book = bookRepository.findById(bookId).get();
        bookRepository.delete(book);

        return new ResponseEntity<>( HttpStatus.OK);
    }
}
