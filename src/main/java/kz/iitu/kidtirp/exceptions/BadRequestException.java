package kz.iitu.kidtirp.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException {
    public BadRequestException(final String message, final String code) {
        super(new ErrorResponse(code, message, HttpStatus.BAD_REQUEST));
    }
}
