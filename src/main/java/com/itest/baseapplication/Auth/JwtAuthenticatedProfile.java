package com.itest.baseapplication.Auth;

import com.itest.baseapplication.dto.ProfileDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class JwtAuthenticatedProfile implements Authentication {


    private final ProfileDTO profile;

    JwtAuthenticatedProfile(ProfileDTO profileDTO){
        profile=profileDTO;
    }
    @Override
    public Collection <? extends GrantedAuthority> getAuthorities () {
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_USER")
        );
    }

    @Override
    public Object getCredentials () {
        return null;
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
        return true;
    }

    @Override
    public void setAuthenticated ( boolean b ) throws IllegalArgumentException {

    }

    @Override
    public String getName () {
        return profile.getName();
    }
}
