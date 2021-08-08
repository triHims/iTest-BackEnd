package com.itest.baseapplication.service;

import com.itest.baseapplication.dto.EmpRecordsDTO;
import com.itest.baseapplication.dto.LoginDTO;
import com.itest.baseapplication.dto.LoginStatusDTO;
import com.itest.baseapplication.entity.EmpRecords;
import com.itest.baseapplication.repository.EmpRecordsRepo;
import com.itest.baseapplication.Auth.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Slf4j
@Service
public class UserVerificationImpl implements UserVerification {


    @Autowired
    private EmpRecordsRepo empRecordsRepo;


    @Autowired
    private AuthUtil authUtil;

//    @Autowired
//    private AuthenticationManager authenticationManager;

    public LoginStatusDTO giveLogin ( LoginDTO loginDTO ) throws UsernameNotFoundException {

        log.info(String.format("Called %s from class %s", "giveLogin","TaskServiceImpl" ));
        Optional <EmpRecords> empRecords = empRecordsRepo.findByUsername(loginDTO.getUsername());
        if (empRecords.isPresent()) {
            EmpRecordsDTO empRecordsDTO = EmpRecordsDTO.getDTOFromEntity(empRecords.get());
            if (Boolean.TRUE.equals(empRecordsDTO.getIsVerified())
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
        log.info(String.format("Exiting from %s of class %s", "giveLogin","TaskServiceImpl" ));
                return new LoginStatusDTO(true,token);
            }
        }

            throw new UsernameNotFoundException("Wrong UserName or Password");
    }





}


