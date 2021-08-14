package com.itest.baseapplication.exception;

public class ProfileNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public ProfileNotFoundException () {
        super();
        message="";
    }

    public ProfileNotFoundException ( String message) {
        super(message);
        this.message = message;
    }

    private final String message;


    @Override
    public String getMessage() {
        return message;
    }

}
