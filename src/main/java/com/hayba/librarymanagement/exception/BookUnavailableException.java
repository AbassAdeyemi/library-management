package com.hayba.librarymanagement.exception;

public class BookUnavailableException extends RuntimeException {

    public BookUnavailableException() {
        super();
    }

    public BookUnavailableException(String message) {
        super(message);
    }

    public BookUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
