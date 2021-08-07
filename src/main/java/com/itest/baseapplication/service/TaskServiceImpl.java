package com.itest.baseapplication.service;


import com.itest.baseapplication.dto.AttemptTaskDTO;
import com.itest.baseapplication.dto.StepDTO;
import com.itest.baseapplication.dto.TaskDTO;
import com.itest.baseapplication.entity.Task;
import com.itest.baseapplication.repository.AttemptTaskRepo;
import com.itest.baseapplication.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class TaskServiceImpl  implements  TaskService{

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AttemptTaskRepo attemptTaskRepo;

    @Override
    public StepDTO  getTaskSteps (Integer taskId)  {

        log.info(String.format("Called %s from class %s", "getTaskSteps","TaskServiceImpl" ));
        StepDTO stepDTO = new StepDTO();

       Task task = taskRepository.getByTaskId(taskId);

        if(task==null)
            return stepDTO;
       stepDTO.setNoOfSteps(task.getStepCount());
       stepDTO.setStepList(task.getSteps());
       stepDTO.setTaskId(task.getTaskId());


        log.info(String.format("Exiting from %s of class %s", "getTaskSteps","TaskServiceImpl" ));
        return stepDTO;
    }

    @Override
    public boolean saveAttemptedTask ( AttemptTaskDTO attemptObject) {
        log.info(String.format("Called %s from class %s", "saveAttempedTask","TaskServiceImpl" ));
        attemptTaskRepo.saveAndFlush(attemptObject.dtoToTask());
        return true;


    }


    public List <TaskDTO> getAllTasks(String projectId){
        log.info(String.format("Inside %s from class %s", "getAllTasks","TaskServiceImpl" ));
        return taskRepository.findByProjectId(projectId).stream()
                .map(TaskDTO::dtofromObject).collect(Collectors.toList());
    }
}
