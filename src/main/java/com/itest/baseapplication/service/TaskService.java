package com.itest.baseapplication.service;

import com.itest.baseapplication.dto.AttemptTaskDTO;
import com.itest.baseapplication.dto.TaskDTO;

import java.util.List;


public interface TaskService {
    String getTaskSteps (Integer taskId) ;

    boolean saveAttemptedTask( AttemptTaskDTO attemptObject);

    public List <TaskDTO> getAllTasks( String projectId);
}
