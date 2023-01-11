package study.study.testttt;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Book {

    @Id @GeneratedValue
    private Long bookId;

    private String bookName;

    private int price;

    private int isbn;

    private String writer;

    @Enumerated(EnumType.STRING)
    private Type type;
}
