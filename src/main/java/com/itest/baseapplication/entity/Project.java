package com.itest.baseapplication.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="project_table")
public class Project {
    @Id
    @GeneratedValue(generator = "id-generator")
    @GenericGenerator(name="id-generator",
            parameters = @org.hibernate.annotations.Parameter(name="prefix",value = "proj"),
            strategy = "com.itest.baseapplication.util.IdGenerator"
    )
    String id;
    @Column(name="project_name")
    String projectName;
    @Column(name="created_date")
    LocalDateTime createdDate;
    @Column(name="lead_developer_id")
    String leadDeveloperId;
    @Column(name="project_description")
    String projectDescription;
    @Column(name="project_location")
    String projectLocation;


    public String getId () {
        return id;
    }

    public String getProjectName () {
        return projectName;
    }

    public LocalDateTime getCreatedDate () {
        return createdDate;
    }

    public String getLeadDeveloperId () {
        return leadDeveloperId;
    }

    public String getProjectDescription () {
        return projectDescription;
    }

    public String getProjectLocation () {
        return projectLocation;
    }
}
