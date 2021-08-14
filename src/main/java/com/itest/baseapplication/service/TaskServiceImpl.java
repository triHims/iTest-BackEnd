package com.itest.baseapplication.service;


import com.itest.baseapplication.dto.AttemptTaskDTO;
import com.itest.baseapplication.dto.ProfileDTO;
import com.itest.baseapplication.dto.ProjectDTO;
import com.itest.baseapplication.dto.StepDTO;
import com.itest.baseapplication.dto.TaskDTO;
import com.itest.baseapplication.dto.TesterTaskAttemptDTO;
import com.itest.baseapplication.entity.EmpRecords;
import com.itest.baseapplication.entity.Task;
import com.itest.baseapplication.entity.Tester;
import com.itest.baseapplication.entity.TesterTaskAttempt;
import com.itest.baseapplication.repository.AttemptTaskRepo;
import com.itest.baseapplication.repository.EmpRecordsRepo;
import com.itest.baseapplication.repository.TaskRepository;
import com.itest.baseapplication.repository.TesterRepo;
import com.itest.baseapplication.repository.TesterTaskAttemptRepo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
public class TaskServiceImpl  implements  TaskService{

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AttemptTaskRepo attemptTaskRepo;
    
    @Autowired
    private TesterTaskAttemptRepo testerTaskAttemptRepo;
    
    @Autowired
    private EmpRecordsRepo empRecordsRepo;
    
    @Autowired
    private TesterRepo testerRepo;

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
    
    public List <TesterTaskAttemptDTO> getTaskHistory(Integer taskId){
        log.info(String.format("Inside %s from class %s", "getAllTasks","TaskServiceImpl" ));
        ProfileDTO profile = (ProfileDTO) SecurityContextHolder.getContext().getAuthentication().getDetails();
        List<TesterTaskAttemptDTO> tasks = new ArrayList<TesterTaskAttemptDTO>();
        Optional<EmpRecords> emp = empRecordsRepo.findByUsername(profile.getUsername());
        if(emp.isPresent()) {
        	EmpRecords empRecord = emp.get();
        	switch(profile.getRole()) {
        	case "tester":
        		tasks = testerTaskAttemptRepo.findByTaskIdAndTesterId(taskId, empRecord.getUserId().toString()).stream().map(TesterTaskAttemptDTO::dtofromEntity).collect(Collectors.toList());
        		break;
        	case "developer":        	
        		tasks = testerTaskAttemptRepo.findByTaskId(taskId).stream().map(TesterTaskAttemptDTO::dtofromEntity).collect(Collectors.toList());
        		break;
        	case "admin":
        		tasks =  testerTaskAttemptRepo.findAll().stream().map(TesterTaskAttemptDTO::dtofromEntity).collect(Collectors.toList());
        		break;
        	}        	
        }

        return tasks;
    }
    
    @Override
    public String addTask ( TaskDTO task) {
        log.info(String.format("Called %s from class %s", "saveAttempedTask","TaskServiceImpl" ));
        ProfileDTO profile = (ProfileDTO) SecurityContextHolder.getContext().getAuthentication().getDetails();
        if(!profile.getRole().equals("tester")) {
        	if(task.getCreatedDate()==null){
        		task.setCreatedDate(LocalDateTime.now());        		
        	}
        	taskRepository.saveAndFlush(task.EntityfromDTO(task));        
        	return "Successfully Added Task.";
        }
        return "Tester cannot add task.";
    }
    
    @Override
    public List<TesterTaskAttempt> taskAttempts (Long testerId) {
        log.info(String.format("Called %s from class %s", "taskAttempts","TaskServiceImpl" ));
        ProfileDTO profile = (ProfileDTO) SecurityContextHolder.getContext().getAuthentication().getDetails();
        List<TesterTaskAttempt> testerTaskAttempts = new ArrayList<TesterTaskAttempt>();
        if(profile.getRole().equals("tester")) {
        	Optional<EmpRecords> emp = empRecordsRepo.findByUsername(profile.getUsername());
        	testerTaskAttempts = testerTaskAttemptRepo.findByTesterId(emp.get().getUserId().toString());     
        } else if(testerId!=null) {
        	 Optional<Tester> tester = testerRepo.findByTesterId(testerId);
        	 if(tester.isPresent()) {
        		 testerTaskAttempts = testerTaskAttemptRepo.findByTesterId(testerId.toString());
        	 }
        }
        return testerTaskAttempts;
    }
    
    
    
    
}
