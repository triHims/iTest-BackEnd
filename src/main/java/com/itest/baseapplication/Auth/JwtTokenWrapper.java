package com.itest.baseapplication.Auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtTokenWrapper implements Authentication {
    private final String token;

    public JwtTokenWrapper(String token){
        this.token=token;
    }

    @Override
    public Collection <? extends GrantedAuthority> getAuthorities () {
        return null;
    }

    @Override
    public Object getCredentials () {
        return token;
    }

    @Override
    public Object getDetails () {
        return null;
    }

    @Override
    public Object getPrincipal () {
        return null;
    }

    @Override
    public boolean isAuthenticated () {
        return false;
    }

    @Override
    public void setAuthenticated ( boolean b ) throws IllegalArgumentException {

    }

    @Override
    public String getName () {
        return null;
    }
}
