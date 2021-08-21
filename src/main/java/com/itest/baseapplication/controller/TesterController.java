package com.itest.baseapplication.controller;

import com.itest.baseapplication.dto.TesterTaskAttemptDTO;
import com.itest.baseapplication.service.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/tester/")
@CrossOrigin(origins={"http://localhost:4200","http://localhost:14060","http://10.81.64.15:14060","http://10.81.64.15:14059"})
public class TesterController {

    @Autowired
    TaskService taskService;


    @ApiOperation(value = "Find all the tasks attempted by Tester", tags = "tester-controller", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(value = "/findAttempts")
    public ResponseEntity <List <TesterTaskAttemptDTO>> allTasksAttempts ( @RequestParam Long testerId) {
        log.info(String.format("Entered into:: %s from class:: %s", "taskAttempts","TaskController" ));
        return new ResponseEntity <>(taskService.allTasksAttemptsByTesterId(testerId), HttpStatus.OK);
    }

    @ApiOperation(value = "count the tasks attempted by Tester in a Project to te total tasks in the project",
            tags = "tester-controller", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(value = "/countTasksAttemptForProject")
    public ResponseEntity <Map <String,Object>> countAttemptedAndTotalTasks ( @RequestParam String projectId, @RequestParam int testerId) {
        log.info(String.format("Entered into:: %s from class:: %s", "taskAttempts","TaskController" ));
        return new ResponseEntity <>(taskService.countAttemptedAndTotalTasks(projectId, testerId), HttpStatus.OK);
    }


}
