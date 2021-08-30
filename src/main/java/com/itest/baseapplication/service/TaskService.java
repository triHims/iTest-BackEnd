package com.itest.baseapplication.service;

import com.itest.baseapplication.dto.StepDTO;
import com.itest.baseapplication.dto.TaskDTO;
import com.itest.baseapplication.dto.TesterTaskAttemptDTO;
import com.itest.baseapplication.entity.TesterTaskAttempt;
import com.itest.baseapplication.model.TesterTaskAttemptExtended;

import java.util.List;
import java.util.Map;


public interface TaskService {
    StepDTO getTaskSteps ( Integer taskId) ;

    boolean saveAttemptedTask( TesterTaskAttemptDTO attemptObject);
    
    String addTask( TaskDTO task);

    public List <TaskDTO> getAllTasks( String projectId);

	public List <TesterTaskAttemptExtended> getTaskHistory( Integer taskid);
	
	List<TesterTaskAttemptDTO> allTasksAttemptsByTesterId ( Long testerId);

    Map <String,Object> countAttemptedAndTotalTasks( String projectId, int testerId);
}
