package user_service.exception;

public class OfferedSkillException extends RuntimeException {
    public OfferedSkillException(String message, Object... args) {
        super(String.format(message, args));
    }

    public OfferedSkillException(String message) {
        super(message);
    }
}
