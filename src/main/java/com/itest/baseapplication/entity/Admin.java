package com.itest.baseapplication.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import  org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="admin_table")
@Setter
@Getter
public class Admin {
    @Id
    @GeneratedValue(generator = "id-generator")
    @GenericGenerator(name="id-generator",
    parameters = @Parameter(name="prefix",value = "admin"),
            strategy = "com.itest.baseapplication.util.IdGenerator"
    )
    @Column(name = "admin_id",unique=true,columnDefinition="VARCHAR(20)")
    private String adminId;

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
