package com.itest.baseapplication.exception;

public class TokenException extends Exception {

    private static final long serialVersionUID = 1L;

    public TokenException() {
        super();
        message="";
    }

    public TokenException(String message) {
        super(message);
        this.message = message;
    }

    private final String message;


    @Override
    public String getMessage() {
        return message;
    }

}
