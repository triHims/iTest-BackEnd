package com.itest.baseapplication.Auth;

import com.itest.baseapplication.dto.ProfileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AuthUtil authUtil;
    @Override
    public Authentication authenticate ( Authentication authentication ) throws AuthenticationException {

        Optional<ProfileDTO> profile = null;
        try {
            profile = authUtil.verify(
                    (String)authentication.getCredentials() );

        } catch (JwtAuthenticationException e) {
                     log.error("Verification of profile has failed ");

        }

        return new JwtAuthenticatedProfile(profile.get());
    }

    @Override
    public boolean supports ( Class <?> aClass ) {
       return JwtTokenWrapper.class.equals(aClass);
    }
}
