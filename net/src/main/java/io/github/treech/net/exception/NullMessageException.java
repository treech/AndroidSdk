package io.github.treech.net.exception;

public class NullMessageException extends Exception {

    public NullMessageException() {
    }

    public NullMessageException(String message) {
        super(message);
    }

    public NullMessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullMessageException(Throwable cause) {
        super(cause);
    }
}
