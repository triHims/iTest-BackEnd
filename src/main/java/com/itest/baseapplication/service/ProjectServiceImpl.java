package com.itest.baseapplication.service;


import com.itest.baseapplication.dto.ProjectDTO;

import com.itest.baseapplication.entity.Project;
import com.itest.baseapplication.repository.ProjectRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProjectServiceImpl  implements  ProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ProjectDTO getProject(String projectId)  {
        Optional<Project> projectOption = projectRepository.findById(projectId);
        if(projectOption.isPresent())
            return   ProjectDTO.dtoFromEntity(projectOption.get());

        return new ProjectDTO();
    }


    public List <ProjectDTO> getAllProjects() {
        return  projectRepository.findAll().stream().map(ProjectDTO::dtoFromEntity).collect(Collectors.toList());
    }
}
