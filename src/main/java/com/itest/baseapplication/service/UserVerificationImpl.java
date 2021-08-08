package com.itest.baseapplication.service;

import com.itest.baseapplication.dto.EmpRecordsDTO;
import com.itest.baseapplication.dto.LoginDTO;
import com.itest.baseapplication.dto.LoginStatusDTO;
import com.itest.baseapplication.dto.SignUpDTO;
import com.itest.baseapplication.entity.Admin;
import com.itest.baseapplication.entity.Developer;
import com.itest.baseapplication.entity.EmpRecords;
import com.itest.baseapplication.entity.Tester;
import com.itest.baseapplication.repository.AdminRepo;
import com.itest.baseapplication.repository.DeveloperRepo;
import com.itest.baseapplication.repository.EmpRecordsRepo;
import com.itest.baseapplication.Auth.AuthUtil;
import com.itest.baseapplication.repository.TesterRepo;
import com.itest.baseapplication.util.UserType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

@Slf4j
@Service
public class UserVerificationImpl implements UserVerification {


    @Autowired
    private EmpRecordsRepo empRecordsRepo;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private DeveloperRepo developerRepo;

    @Autowired
    private TesterRepo testerRepo;


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

    @Override
    public String doSignUp ( SignUpDTO signUpDTO ) {
        log.info(String.format("Called %s from class %s", "doSignUp","TaskServiceImpl" ));
      switch (signUpDTO.getUserType()){
          case ADMIN: return signAdmin(signUpDTO);
          case DEVELOPER: return signDev(signUpDTO);
          case TESTER: return signTest(signUpDTO);
      }
        log.error(String.format("Problem on %s of class %s, Signup failed", "giveLogin","TaskServiceImpl" ));
      return "Sign up failed";
    }



    private String signDev(SignUpDTO signUpDTO){
            Developer dev = signUpDTO.getDeveloperEntity();

            dev.setIsVerified(1);
            dev.setAgeRange(giveAgeRange(dev.getDob()));

            dev = developerRepo.saveAndFlush(dev);
            return dev.getDeveloperId().toString();
    }
    private String signTest(SignUpDTO signUpDTO){
        Tester tester = signUpDTO.getTesterEntity();

        tester.setIsVerified(1);

        tester.setAgeRange(giveAgeRange(tester.getDob()));

        tester = testerRepo.saveAndFlush(tester);
        return tester.getTesterId().toString();

    }
    private String signAdmin(SignUpDTO signUpDTO){
        Admin admin = signUpDTO.getAdminEntity();

        admin.setIsVerified(1);

        admin.setAgeRange(giveAgeRange(admin.getDob()));

        admin = adminRepo.saveAndFlush(admin);
        return admin.getAdminId();
    }


    private String giveAgeRange( LocalDate dob){
        int yearOld =  dob.until(LocalDate.now()).getYears();

        if(yearOld<19){
            return "13-18";
        }else if(yearOld < 31){
            return "19-30";
        }else if(yearOld < 61){
            return "31-60";
        } else {
            return "60+";
        }
    }

}


