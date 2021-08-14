package com.itest.baseapplication.service;

import com.itest.baseapplication.dto.AttemptTaskDTO;
import com.itest.baseapplication.dto.StepDTO;
import com.itest.baseapplication.dto.TaskDTO;
import com.itest.baseapplication.dto.TesterTaskAttemptDTO;

import java.util.List;


public interface TaskService {
    StepDTO getTaskSteps ( Integer taskId) ;

    boolean saveAttemptedTask( AttemptTaskDTO attemptObject);

    public List <TaskDTO> getAllTasks( String projectId);

	public List <TesterTaskAttemptDTO> getTaskHistory(Integer taskid);
}
