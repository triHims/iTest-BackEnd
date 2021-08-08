package com.itest.baseapplication.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="developer_table")
@Getter
@Setter
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long developerId;

    private String username;

    private String password;

    private String emailId;

    @Column(name="isVerified")
    private int isVerified;

    private String ageRange;

    private LocalDate dob;

    private String location;

    private String fullName;
}
