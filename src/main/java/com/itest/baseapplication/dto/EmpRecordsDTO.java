package com.itest.baseapplication.dto;


import com.itest.baseapplication.entity.EmpRecords;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Setter
@Getter
public class EmpRecordsDTO {

    @NotNull
    private String userId;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String emailId;

    @NotNull
    private Boolean isVerified;

    @NotNull
    private String role;


    @NotNull
    private String fullName;

    public static EmpRecordsDTO getDTOFromEntity( EmpRecords records ){

        final ModelMapper mapper  = new ModelMapper();
        return  mapper.map(records,EmpRecordsDTO.class);
    }
}
