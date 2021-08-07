package com.itest.baseapplication.service;


import com.itest.baseapplication.dto.ProjectDTO;

import com.itest.baseapplication.entity.Project;
import com.itest.baseapplication.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
public class ProjectServiceImpl  implements  ProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ProjectDTO getProject(String projectId)  {
        log.info(String.format("Called %s from class %s", "getProject","ProjectServiceImpl" ));
        Optional<Project> projectOption = projectRepository.findById(projectId);
        if(projectOption.isPresent())
            return   ProjectDTO.dtoFromEntity(projectOption.get());

        log.info(String.format("Exiting from %s 0f class %s", "getProject","ProjectServiceImpl" ));
        return new ProjectDTO();
    }


    public List <ProjectDTO> getAllProjects() {
        log.info(String.format("Inside %s from class %s", "getProject","ProjectServiceImpl" ));
        return  projectRepository.findAll().stream().map(ProjectDTO::dtoFromEntity).collect(Collectors.toList());
    }
}
