package kz.iitu.kidtirp.exceptions;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public record ErrorResponse(String errorCode, String errorMsg, HttpStatus responseStatus) implements Serializable {

}
