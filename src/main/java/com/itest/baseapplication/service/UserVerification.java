package com.itest.baseapplication.service;

import com.itest.baseapplication.dto.LoginDTO;
import com.itest.baseapplication.dto.LoginStatusDTO;
import com.itest.baseapplication.dto.SignUpDTO;

public interface UserVerification {
    boolean checkUserNameUnique(String userName);
    LoginStatusDTO giveLogin( LoginDTO loginDTO );

    String doSignUp ( SignUpDTO signUpDTO );
}
