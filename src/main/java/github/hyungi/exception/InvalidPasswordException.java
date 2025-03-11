package github.hyungi.exception;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("유효하지 않은 비밀번호 입니다.");
    }
}
