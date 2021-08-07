package com.itest.baseapplication.Auth;

import javax.naming.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    JwtAuthenticationException(String  s){
        super(s);
    }
}
