package com.itest.baseapplication.controller;


import com.itest.baseapplication.dto.LoginDTO;
import com.itest.baseapplication.dto.LoginStatusDTO;
import com.itest.baseapplication.service.UserVerification;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/noauth")
@CrossOrigin(origins="http://localhost:4200")
public class NoAuthController {

//
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
    @ApiOperation(value = "Login a user", tags = "no-auth-controller")
    @PostMapping(value = "/login")
    public LoginStatusDTO getLogin( @RequestBody LoginDTO loginDTO){

        return userVerification.giveLogin(loginDTO);
    }
}
