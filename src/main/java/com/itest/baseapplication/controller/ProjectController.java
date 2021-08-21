package com.itest.baseapplication.controller;

import com.itest.baseapplication.dto.ProjectDTO;
import com.itest.baseapplication.entity.Project;
import com.itest.baseapplication.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/project")
@CrossOrigin(origins={"http://localhost:4200","http://localhost:14060","http://10.81.64.15:14060","http://10.81.64.15:14059"})
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @ApiOperation(value = "You have a projectId you want to get the details about it.", tags = "project-controller", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(value = "/{projectId}")
    public ResponseEntity <ProjectDTO> getProject( @PathVariable("projectId") String projectId) {
        log.info(String.format("Inside of:: %s from class:: %s", "getProject","ProjectController" ));
        return  new ResponseEntity <>(projectService.getProject(projectId),HttpStatus.OK);
    }

    @ApiOperation(value = "You want to know how many projects are related to the particular user.", tags = "project-controller", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(value = "/all")
    public ResponseEntity <List <ProjectDTO>> getAllProjects() {
        log.info(String.format("Inside of:: %s from class:: %s", "getAllProject","ProjectController" ));
        return  new ResponseEntity <>(projectService.getAllProjects(),HttpStatus.OK);
    }
}
