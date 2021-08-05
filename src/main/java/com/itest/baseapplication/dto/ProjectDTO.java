package com.itest.baseapplication.dto;


import com.itest.baseapplication.entity.Project;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

public class ProjectDTO {
    String id;
    String projectName;
    LocalDateTime createdDate;
    String leadDeveloperId;
    String projectDescription;
    String projectLocation;

    public String getId () {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
    }

    public String getProjectName () {
        return projectName;
    }

    public void setProjectName ( String projectName ) {
        this.projectName = projectName;
    }

    public LocalDateTime getCreatedDate () {
        return createdDate;
    }

    public void setCreatedDate ( LocalDateTime createdDate ) {
        this.createdDate = createdDate;
    }

    public String getLeadDeveloperId () {
        return leadDeveloperId;
    }

    public void setLeadDeveloperId ( String leadDeveloperId ) {
        this.leadDeveloperId = leadDeveloperId;
    }

    public String getProjectDescription () {
        return projectDescription;
    }

    public void setProjectDescription ( String projectDescription ) {
        this.projectDescription = projectDescription;
    }

    public String getProjectLocation () {
        return projectLocation;
    }

    public void setProjectLocation ( String projectLocation ) {
        this.projectLocation = projectLocation;
    }


    public static ProjectDTO dtoFromEntity( Project project){
        final ModelMapper mapper = new ModelMapper();
        ProjectDTO projectDTO = mapper.map(project,ProjectDTO.class);
        return projectDTO;
    }
}
