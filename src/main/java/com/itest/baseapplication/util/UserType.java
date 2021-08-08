package com.itest.baseapplication.util;

public enum UserType {
    ADMIN("ADMIN"),
    TESTER("TESTER"),
    DEVELOPER("DEVELOPER");

    private final String user;

    private UserType(String s){
        user=s;
    }

    public String getString(){
            return user;

    }
}
