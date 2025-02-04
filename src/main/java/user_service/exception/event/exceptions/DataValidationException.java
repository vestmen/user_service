package user_service.exception.event.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class DataValidationException extends RuntimeException {
    private final List<String> validationErrors;

    public DataValidationException(String message, List<String> validationErrors) {
        super(message);
        this.validationErrors = validationErrors;
    }
}
