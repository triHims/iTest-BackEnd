package com.itest.baseapplication.service;

import com.itest.baseapplication.dto.LoginDTO;
import com.itest.baseapplication.dto.LoginStatusDTO;

public interface UserVerification {
    LoginStatusDTO giveLogin( LoginDTO loginDTO );
}
