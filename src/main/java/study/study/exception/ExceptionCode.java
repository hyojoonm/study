package study.study.exception;

import lombok.Getter;

public enum ExceptionCode {

    POST_NOT_FOUND(404,"post not found"),
    ANSWER_NOT_FOUND(404,"answer not found");

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
