package study.study.exception;

public class PostNotFound extends BusinessLogicException{
    public PostNotFound(){
        super(ExceptionCode.POST_NOT_FOUND.getMessage(),ExceptionCode.POST_NOT_FOUND);
    }
}
