package com.itest.baseapplication.dto;


import com.itest.baseapplication.entity.Admin;
import com.itest.baseapplication.entity.Developer;
import com.itest.baseapplication.entity.Tester;
import com.itest.baseapplication.util.UserType;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.Temporal;
import java.time.LocalDate;

@Getter
@Setter
public class SignUpDTO {

    @NotNull
    String username;

    @NotNull
    String password;

    @NotNull
    String emailId;

    @NotNull
    LocalDate dateOfBirth;

    @NotNull
    String country;

    @NotNull
    String fullName;

    @NotNull
    UserType userType;

    public Admin getAdminEntity(){
        Admin admin = new Admin();
        admin.setDob(dateOfBirth);
        admin.setEmailId(emailId);
        admin.setFullName(fullName);
        admin.setLocation(country);
        admin.setPassword(password);
        admin.setUsername(username);
        return admin;
    }

    public Tester getTesterEntity(){
        Tester tester = new Tester();
        tester.setDob(dateOfBirth);
        tester.setEmailId(emailId);
        tester.setFullName(fullName);
        tester.setLocation(country);
        tester.setPassword(password);
        tester.setUsername(username);
        return tester;
    }

    public Developer getDeveloperEntity(){
        Developer developer = new Developer();
        developer.setDob(dateOfBirth);
        developer.setEmailId(emailId);
        developer.setFullName(fullName);
        developer.setLocation(country);
        developer.setPassword(password);
        developer.setUsername(username);
        return developer;
    }
}
