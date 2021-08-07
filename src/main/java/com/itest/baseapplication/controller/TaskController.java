package com.itest.baseapplication.controller;


import com.itest.baseapplication.dto.AttemptTaskDTO;
import com.itest.baseapplication.dto.StepDTO;
import com.itest.baseapplication.dto.TaskDTO;
import com.itest.baseapplication.service.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/")
@CrossOrigin(origins="http://localhost:4200")
public class TaskController {

    @Autowired
    TaskService taskService;

    @ApiOperation(value = "display Task Steps", tags = "task-controller", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(value = "/getSteps")
    public ResponseEntity <StepDTO> getSteps(int taskId ) {
        return  new ResponseEntity <>(taskService.getTaskSteps(taskId),HttpStatus.OK);

    }

    @ApiOperation(value = "Post the attempt Object of the task containing step information", tags = "task-controller", authorizations = @Authorization(value = "Bearer"))
    @PostMapping(value = "/attemptTask")
    public ResponseEntity <String> setAttempt(@RequestBody AttemptTaskDTO attemptTask ) {

        if(taskService.saveAttemptedTask(attemptTask)) {
            return new ResponseEntity <>("Attempt is Set", HttpStatus.OK);
        }
        return  new ResponseEntity <>("Error in writing the file",HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ApiOperation(value = "display Project Tasks", tags = "project-controller", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(value = "{projectId}/gettasks")
    public ResponseEntity <List <TaskDTO>> getTasks( @PathVariable("projectId") String projectId) {
        return  new ResponseEntity <>(taskService.getAllTasks(projectId),HttpStatus.OK);
    }

}
