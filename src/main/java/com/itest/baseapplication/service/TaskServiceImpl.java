package com.itest.baseapplication.service;


import com.itest.baseapplication.dto.AttemptTaskDTO;
import com.itest.baseapplication.dto.TaskDTO;
import com.itest.baseapplication.repository.AttemptTaskRepo;
import com.itest.baseapplication.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TaskServiceImpl  implements  TaskService{

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AttemptTaskRepo attemptTaskRepo;

    @Override
    public String  getTaskSteps (Integer taskId)  {

       String steps = taskRepository.getStepsByTaskId(taskId);
        if(steps!=null)
            return steps;
        return "";
    }

    @Override
    public boolean saveAttemptedTask ( AttemptTaskDTO attemptObject) {
        attemptTaskRepo.saveAndFlush(attemptObject.dtoToTask());
        return true;


    }


    public List <TaskDTO> getAllTasks(String projectId){
        return taskRepository.findByProjectId(projectId).stream()
                .map(TaskDTO::dtofromObject).collect(Collectors.toList());
    }
}
