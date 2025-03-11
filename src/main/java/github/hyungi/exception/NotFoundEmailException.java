package github.hyungi.exception;

public class NotFoundEmailException extends RuntimeException {
    public NotFoundEmailException() {
        super("이메일이 존재하지 않습니다.");
    }
}
