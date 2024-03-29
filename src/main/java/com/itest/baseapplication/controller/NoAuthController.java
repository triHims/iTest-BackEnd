package com.itest.baseapplication.controller;


import com.itest.baseapplication.dto.LoginDTO;
import com.itest.baseapplication.dto.LoginStatusDTO;
import com.itest.baseapplication.dto.SignUpDTO;
import com.itest.baseapplication.service.UserVerification;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/noauth")
@CrossOrigin(origins= {"http://localhost:4200","http://localhost:14060","http://10.81.64.15:14060","http://10.81.64.15:14059"})
public class NoAuthController {


//    @Autowired
//    private AuthUtil authUtil;
//
//    @ApiOperation(value = "Random Trash", tags = "no-auth-controller")
//    @PostMapping(value = "/rand")
//    public String getLogin(){
//        return authUtil.encodeRole("Bacchu") +"\n"+ authUtil.decodeRole(authUtil.encodeRole("Bacchu")) +"\n random salt -"+new RandomSalt().getSalt();
////        return RandomSalt.getSalt();
//    }

    @Autowired
    private UserVerification userVerification;


    @ApiOperation(value = "Check if UserName is unique", tags = "no-auth-controller")
    @GetMapping(value = "/checkUserName")
    public boolean CheckUserNameUnique(@RequestParam String username ){

        log.info(String.format("Inside of:: %s from class:: %s", "getLogin","Check UserName Unique" ));
        return userVerification.checkUserNameUnique(username);
    }




    @ApiOperation(value = "Login a user", tags = "no-auth-controller")
    @PostMapping(value = "/login")
    public LoginStatusDTO getLogin( @RequestBody LoginDTO loginDTO){

        log.info(String.format("Inside of:: %s from class:: %s", "getLogin","NoAuthController" ));
        return userVerification.giveLogin(loginDTO);
    }


    @ApiOperation(value = "Signup a new user", tags = "no-auth-controller")
    @PostMapping(value = "/signUp")
    public String getSigned( @RequestBody SignUpDTO signUpDTO){

        log.info(String.format("Inside of:: %s from class:: %s", "getSigned","NoAuthController" ));
        return userVerification.doSignUp(signUpDTO);
    }
}
