package com.itest.baseapplication.entity;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="emp_records")
@Getter
@ToString
public class EmpRecords {
    @Id
    private String userId;

    private String username;

    private String password;

    private String emailId;

    private Boolean isVerified;

    private String role;

    private LocalDateTime dob;

    private String fullName;

    private String location;

    private byte[] userImg;


}
