package kz.iitu.kidtirp.exceptions;

import org.springframework.http.HttpStatus;

public class ObjectNotFoundException extends BaseException {

    public ObjectNotFoundException(final String message, final String code) {
        super(new ErrorResponse(code, message, HttpStatus.NOT_FOUND));
    }
}
