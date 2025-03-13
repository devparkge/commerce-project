package github.hyungi.exception;

public class NotFoundUUIDException extends RuntimeException {
    public NotFoundUUIDException() {
        super("존재하지 않는 UUID 입니다.");
    }
}
