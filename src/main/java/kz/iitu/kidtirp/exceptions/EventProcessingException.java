package kz.iitu.kidtirp.exceptions;

import org.springframework.http.HttpStatus;

public class EventProcessingException extends BaseException {
    public EventProcessingException(final String message, final String code) {
        super(new ErrorResponse(code, message, HttpStatus.UNPROCESSABLE_ENTITY));
    }
}
