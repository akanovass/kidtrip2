package kz.iitu.kidtirp.exceptions;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends BaseException {

    public UserAlreadyExistsException(final String message, final String code) {
        super(new ErrorResponse(code, message, HttpStatus.LOCKED));
    }
}
