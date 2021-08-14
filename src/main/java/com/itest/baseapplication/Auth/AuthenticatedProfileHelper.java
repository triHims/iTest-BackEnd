package com.itest.baseapplication.Auth;

import com.itest.baseapplication.dto.ProfileDTO;
import lombok.Getter;
import org.springframework.security.core.context.SecurityContextHolder;

@Getter
public class AuthenticatedProfileHelper {
    ProfileDTO profile;
    public AuthenticatedProfileHelper (){
        profile = (ProfileDTO) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }
}
