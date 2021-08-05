package com.itest.Baseapplication.controller;

import com.itest.Baseapplication.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/Project")
@CrossOrigin(origins="http://localhost:4200")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @ApiOperation(value = "display Project Tasks", tags = "project-controller")
    @GetMapping(value = "{projectId}/getTasks")
    public ResponseEntity <Object> getTasks(@PathVariable("projectId") String projectId) {
        return  new ResponseEntity <>(projectService.getTasks(projectId),HttpStatus.OK);
    }
    
    @ApiOperation(value = "display whole project details", tags = "project-controller")
    @GetMapping(value = "/{projectId}")
    public ResponseEntity <LinkedHashMap <String,Object>> getProject( @PathVariable("projectId") String projectId) {
        return  new ResponseEntity <>(projectService.getProject(projectId),HttpStatus.OK);
    }
}
