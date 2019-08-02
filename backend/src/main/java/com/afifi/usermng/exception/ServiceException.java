package com.afifi.usermng.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String DEFAULT_MASSAGE = "Unfortunately an exception occurred in the server. ";

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
        this.DEFAULT_MASSAGE += message;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        this.DEFAULT_MASSAGE += message;
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return DEFAULT_MASSAGE;
    }

}
