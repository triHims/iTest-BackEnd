package com.itest.baseapplication.dto;


import com.itest.baseapplication.entity.Project;

import lombok.Getter;
import lombok.Setter;

import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ProjectDTO {
    private String id;
    private String projectName;
    private LocalDateTime createdDate;
    private String leadDeveloperId;
    private String projectDescription;
    private String projectLocation;
    private DeveloperDTO createdBy;
    private List<TaskDTO> tasks; // No. of tasks created by Dev
    private Integer attemptedTasks; // Only if its a tester, show the tasks attempted
    // private List<Tester> assignedTesters; // Doubtful

    public static ProjectDTO dtoFromEntity( Project project){
        final ModelMapper mapper = new ModelMapper();
        ProjectDTO projectDTO = mapper.map(project,ProjectDTO.class);
        return projectDTO;
    }
}
