package study.study.exception;

public class AnswerNotFound extends BusinessLogicException{

    public AnswerNotFound(){
        super(ExceptionCode.ANSWER_NOT_FOUND.getMessage(),ExceptionCode.ANSWER_NOT_FOUND);
    }
}
