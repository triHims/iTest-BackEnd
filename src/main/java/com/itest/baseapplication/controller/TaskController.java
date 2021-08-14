package com.itest.baseapplication.controller;


import com.itest.baseapplication.dto.AttemptTaskDTO;
import com.itest.baseapplication.dto.ProfileDTO;
import com.itest.baseapplication.dto.StepDTO;
import com.itest.baseapplication.dto.TaskDTO;
import com.itest.baseapplication.dto.TesterTaskAttemptDTO;
import com.itest.baseapplication.entity.EmpRecords;
import com.itest.baseapplication.entity.TesterTaskAttempt;
import com.itest.baseapplication.service.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tasks/")
@CrossOrigin(origins={"http://localhost:4200","http://localhost:14060","http://10.81.64.15:14060","http://10.81.64.15:14059"})
public class TaskController {

    @Autowired
    TaskService taskService;

    @ApiOperation(value = "display Task Steps", tags = "task-controller", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(value = "/getSteps")
    public ResponseEntity <StepDTO> getSteps(int taskId ) {
        log.info(String.format("Inside of:: %s from class:: %s", "getSteps","TaskController" ));
        return  new ResponseEntity <>(taskService.getTaskSteps(taskId),HttpStatus.OK);

    }

    @ApiOperation(value = "Post the attempt Object of the task containing step information", tags = "task-controller", authorizations = @Authorization(value = "Bearer"))
    @PostMapping(value = "/attemptTask")
    public ResponseEntity <String> setAttempt(@RequestBody AttemptTaskDTO attemptTask ) {

        log.info(String.format("Entered into:: %s from class:: %s", "setAttempt","TaskController" ));
        if(taskService.saveAttemptedTask(attemptTask)) {
                    log.info(String.format("Exited from:: %s of class:: %s", "getSteps","TaskController" ));
            return new ResponseEntity <>("Attempt is Set", HttpStatus.OK);
        }
        log.error("Task Write failed");
        return  new ResponseEntity <>("Error in persisting the entity to DB",HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ApiOperation(value = "display Project Tasks", tags = "project-controller", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(value = "{projectId}/getTasks")
    public ResponseEntity <List <TaskDTO>> getTasks( @PathVariable("projectId") String projectId) {
        log.info(String.format("Inside of:: %s from class:: %s", "getSteps","TaskController" ));
        return  new ResponseEntity <>(taskService.getAllTasks(projectId),HttpStatus.OK);
    }
    
    @ApiOperation(value = "display attempt history of Tasks", tags = "task-controller", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(value = "getHistory")
    public ResponseEntity <List <TesterTaskAttemptDTO>> getTaskHistory( @RequestParam Integer taskid ) {
        log.info(String.format("Inside of:: %s from class:: %s", "getSteps","TaskController" ));
        return  new ResponseEntity <>(taskService.getTaskHistory(taskid),HttpStatus.OK);
    }
    
    @ApiOperation(value = "Add a new task", tags = "task-controller", authorizations = @Authorization(value = "Bearer"))
    @PostMapping(value = "/addTask")
    public ResponseEntity <String> addTask(@RequestBody TaskDTO task ) {
        log.info(String.format("Entered into:: %s from class:: %s", "addTask","TaskController" ));
        return new ResponseEntity <>(taskService.addTask(task), HttpStatus.OK);
    }
    
    @ApiOperation(value = "Number of tasks attempted by Tester", tags = "task-controller", authorizations = @Authorization(value = "Bearer"))
    @PostMapping(value = "/findAttempts")
    public ResponseEntity <List<TesterTaskAttempt>> taskAttempts(@RequestParam Long testerId) {
        log.info(String.format("Entered into:: %s from class:: %s", "taskAttempts","TaskController" ));
        return new ResponseEntity <>(taskService.taskAttempts(testerId), HttpStatus.OK);
    }

}
