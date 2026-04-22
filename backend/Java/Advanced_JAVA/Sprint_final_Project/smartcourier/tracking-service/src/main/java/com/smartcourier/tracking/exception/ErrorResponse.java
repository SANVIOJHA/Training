package com.smartcourier.tracking.exception;

import java.time.LocalDateTime;

class ErrorResponse {
    public String message;
    public int status;
    public LocalDateTime time = LocalDateTime.now();

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
