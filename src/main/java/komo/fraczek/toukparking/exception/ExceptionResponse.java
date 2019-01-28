package komo.fraczek.toukparking.exception;

import java.time.LocalDateTime;

public class ExceptionResponse {

    private LocalDateTime timestamp;

    private String message;

    private String desription;

    private String details;


    public ExceptionResponse(LocalDateTime timestamp, String message, String desription, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.desription = desription;
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDesription() {
        return desription;
    }

    public String getDetails() {
        return details;
    }
}
