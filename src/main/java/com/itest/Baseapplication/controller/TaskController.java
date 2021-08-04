package com.itest.Baseapplication.controller;


import com.itest.Baseapplication.service.TaskService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "display Tasks information", tags = "task-controller")
    @GetMapping(value = "/getTasks")
    public ResponseEntity <LinkedHashMap <String,Object>> getTaask() {
        return  new ResponseEntity <>(taskService.getDummyTask(),HttpStatus.OK);

    }

}
