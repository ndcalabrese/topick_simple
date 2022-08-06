package com.ndcalabrese.topick_simple.exception;

public class TopickException extends RuntimeException {
    public TopickException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public TopickException(String exMessage) {
        super(exMessage);
    }
}
