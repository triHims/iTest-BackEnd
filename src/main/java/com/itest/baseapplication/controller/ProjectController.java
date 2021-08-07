package com.itest.baseapplication.controller;

import com.itest.baseapplication.dto.ProjectDTO;
import com.itest.baseapplication.entity.Project;
import com.itest.baseapplication.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins="*")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @ApiOperation(value = "display whole project details", tags = "project-controller", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(value = "/{projectId}")
    public ResponseEntity <ProjectDTO> getProject( @PathVariable("projectId") String projectId) {
        return  new ResponseEntity <>(projectService.getProject(projectId),HttpStatus.OK);
    }

    @ApiOperation(value = "get all the project details.", tags = "project-controller", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(value = "/all")
    public ResponseEntity <List <ProjectDTO>> getAllProjects() {
        return  new ResponseEntity <>(projectService.getAllProjects(),HttpStatus.OK);
    }
}
