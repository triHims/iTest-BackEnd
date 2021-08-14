package com.itest.baseapplication.dto;


import com.itest.baseapplication.entity.Developer;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;


@Setter
@Getter
public class DeveloperDTO {

    
    private String developerId;
    
    private String username;
    
    private String password;
    
    private String emailId;
    
    private Boolean isVerified;
    private String ageRange;
    
    private LocalDate dob;
    
    private String location;
    
    private String fullName;

    private byte[] userImg;

    public static DeveloperDTO getDTOFromEntity( Developer developer ){
        final ModelMapper mapper  = new ModelMapper();
        return  mapper.map(developer,DeveloperDTO.class);
    }
}
