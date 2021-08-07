package com.itest.baseapplication.service;

import com.itest.baseapplication.dto.EmpRecordsDTO;
import com.itest.baseapplication.dto.LoginDTO;
import com.itest.baseapplication.dto.LoginStatusDTO;
import com.itest.baseapplication.entity.EmpRecords;
import com.itest.baseapplication.repository.EmpRecordsRepo;
import com.itest.baseapplication.Auth.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class UserVerificationImpl implements UserVerification {


    @Autowired
    private EmpRecordsRepo empRecordsRepo;


    @Autowired
    private AuthUtil authUtil;

//    @Autowired
//    private AuthenticationManager authenticationManager;

    public LoginStatusDTO giveLogin ( LoginDTO loginDTO ) throws UsernameNotFoundException {
        System.out.println("Called user verification.");
        Optional <EmpRecords> empRecords = empRecordsRepo.findByUsername(loginDTO.getUsername());
        if (empRecords.isPresent()) {
            EmpRecordsDTO empRecordsDTO = EmpRecordsDTO.getDTOFromEntity(empRecords.get());
            if (empRecordsDTO.getIsVerified()
                    && empRecordsDTO.getPassword().equals(loginDTO.getPassword())) {

                HashMap<String,Object> claimsObj = new HashMap <>();
                claimsObj.put("username",empRecordsDTO.getUsername());
                claimsObj.put("token_r",
                        authUtil.encodeRole(empRecordsDTO.getRole()));
                claimsObj.put("fullName",empRecordsDTO.getFullName());

                String token = authUtil.createJWT(empRecordsDTO.getUsername(),"iTest-Admin","special-token",claimsObj);

//                authenticationManager.authenticate(
//                        new UsernamePasswordAuthenticationToken(empRecordsDTO.getUsername(),
//                                empRecordsDTO.getPassword())
//                );
                return new LoginStatusDTO(true,token);
            }
        }

            throw new UsernameNotFoundException("Wrong UserName or Password");
    }





}


