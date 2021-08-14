package com.itest.baseapplication.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="tester_table")
@Setter
@Getter
public class Tester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY  )
    private Long testerId;

    private String username;

    private String password;

    private String emailId;

    @Column(name="isVerified")
    private int isVerified;

    private String ageRange;

    private String starRating;

    private LocalDate dob;

    private String location;

    private String fullName;

    private byte[] userImg;
}
