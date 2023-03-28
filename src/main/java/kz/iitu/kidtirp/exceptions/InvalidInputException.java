package kz.iitu.kidtirp.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidInputException extends BaseException {
    public InvalidInputException(final String message, final String code) {
        super(new ErrorResponse(code, message, HttpStatus.NOT_ACCEPTABLE));
    }
}
