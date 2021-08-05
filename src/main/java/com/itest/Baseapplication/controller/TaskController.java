package com.itest.Baseapplication.controller;


import com.itest.Baseapplication.dto.AttemptTask;
import com.itest.Baseapplication.service.TaskService;
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
@RequestMapping("/Tasks/")
@CrossOrigin(origins="http://localhost:4200")
public class TaskController {

    @Autowired
    TaskService taskService;

    @ApiOperation(value = "display Task Steps", tags = "task-controller")
    @GetMapping(value = "/getSteps")
    public ResponseEntity <LinkedHashMap <String,Object>> getSteps() {
        return  new ResponseEntity <>(taskService.getDummyTask(),HttpStatus.OK);

    }

    @ApiOperation(value = "Post the attempt Object of the task containing step information", tags = "task-controller")
    @PostMapping(value = "/attemptTask")
    public ResponseEntity <String> setAttempt(@RequestBody AttemptTask attemptTask ) {
        System.out.println(attemptTask.toString());
        if(taskService.writeAttemptJSON(attemptTask)) {
            return new ResponseEntity <>("Attempt is Set", HttpStatus.OK);
        }
        return  new ResponseEntity <>("Error in writing the file",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
