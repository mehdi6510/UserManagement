package com.afifi.usermng.exception;

import java.util.Date;
import java.util.StringJoiner;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ErrorDetails.class.getSimpleName() + "[", "]")
                .add("timestamp=" + timestamp)
                .add("message='" + message + "'")
                .add("details='" + details + "'")
                .toString();
    }
}
