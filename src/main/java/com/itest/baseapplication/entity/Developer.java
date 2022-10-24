package com.itest.baseapplication.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="developer_table")
@Getter
@Setter
public class Developer {

    @Id
    @GeneratedValue(generator = "id-generator")
    @GenericGenerator(name="id-generator",
            parameters = @org.hibernate.annotations.Parameter(name="prefix",value = "dev"),
            strategy = "com.itest.baseapplication.util.IdGenerator"
    )
    @Column(name = "developer_id",unique=true,columnDefinition="VARCHAR(45)")
   private String developerId;

    private String username;

    private String password;

    private String emailId;


    private int isVerified;

    private LocalDate dob;

    private String location;

    private String fullName;

    private byte[] userImg;
}
