package com.itest.baseapplication.entity;


import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="emp_records")
@Getter
public class EmpRecords {
    @Id
    private String userId;

    private String username;

    private String password;

    private String emailId;

    private Boolean isVerified;

    private String role;

    private String fullName;

    private byte[] userImg;

    @Override
    public String toString () {
        return "EmpRecords{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", emailId='" + emailId + '\'' +
                ", isVerified=" + isVerified +
                ", role='" + role + '\'' +
                ", userImg='" + "binaryArr" + '\'' +
                '}';
    }
}
