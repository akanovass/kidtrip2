package kz.iitu.kidtirp.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends BaseException {

    public InvalidTokenException(final String message, final String code) {
        super(new ErrorResponse(code, message, HttpStatus.UNAUTHORIZED));
    }
}
