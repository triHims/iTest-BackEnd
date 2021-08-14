package com.itest.baseapplication.controller;


import com.itest.baseapplication.Auth.AuthenticatedProfileHelper;
import com.itest.baseapplication.dto.EmpRecordsDTO;
import com.itest.baseapplication.dto.ProfileDTO;
import com.itest.baseapplication.exception.ProfileNotFoundException;
import com.itest.baseapplication.service.UserProfileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins= {"http://localhost:4200","http://localhost:14060","http://10.81.64.15:14060","http://10.81.64.15:14059"})
public class AuthController {

    @Autowired
    private UserProfileService userProfileService;





    @ApiOperation(value = "Get the user Profile", tags = "auth-controller", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(value = "/getUserProfile")
    public EmpRecordsDTO getuserProfile() throws ProfileNotFoundException {
        ProfileDTO profileDTO = new AuthenticatedProfileHelper().getProfile();
        log.info(String.format("Inside of:: %s from class:: %s", "getuserProfile","AuthController" ));
        return userProfileService.getUserProfile(profileDTO.getUsername());
    }


}
