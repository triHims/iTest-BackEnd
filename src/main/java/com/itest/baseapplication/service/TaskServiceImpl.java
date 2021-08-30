package com.itest.baseapplication.service;


import com.itest.baseapplication.dto.ProfileDTO;
import com.itest.baseapplication.dto.StepDTO;
import com.itest.baseapplication.dto.TaskDTO;
import com.itest.baseapplication.dto.TesterTaskAttemptDTO;
import com.itest.baseapplication.entity.EmpRecords;
import com.itest.baseapplication.entity.Task;
import com.itest.baseapplication.entity.Tester;
import com.itest.baseapplication.entity.TesterTaskAttempt;
import com.itest.baseapplication.model.TesterTaskAttemptExtended;
import com.itest.baseapplication.repository.EmpRecordsRepo;
import com.itest.baseapplication.repository.TaskRepository;
import com.itest.baseapplication.repository.TesterRepo;
import com.itest.baseapplication.repository.TesterTaskAttemptRepo;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Service
public class TaskServiceImpl  implements  TaskService{

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TesterTaskAttemptRepo attemptTaskRepo;
    
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
    public boolean saveAttemptedTask ( TesterTaskAttemptDTO attemptObject) {
        log.info(String.format("Called %s from class %s", "saveAttempedTask","TaskServiceImpl" ));
        attemptTaskRepo.saveAndFlush(TesterTaskAttemptDTO.entityFromDTO(attemptObject));
        return true;
    }


    public List <TaskDTO> getAllTasks(String projectId){
        log.info(String.format("Inside %s from class %s", "getAllTasks","TaskServiceImpl" ));
        return taskRepository.findByProjectId(projectId).stream()
                .map(TaskDTO::dtofromObject).collect(Collectors.toList());
    }
    
    public List <TesterTaskAttemptExtended> getTaskHistory(Integer taskId){



        log.info(String.format("Inside %s from class %s", "getTaskHistory","TaskServiceImpl" ));
        ProfileDTO profile = (ProfileDTO) SecurityContextHolder.getContext().getAuthentication().getDetails();
        List<TesterTaskAttemptExtended> tasks = new ArrayList<>();
        Optional<EmpRecords> emp = empRecordsRepo.findByUsername(profile.getUsername());
        if(emp.isPresent()) {
        	EmpRecords empRecord = emp.get();
        	switch(profile.getRole()) {
        	case "tester":
        		tasks = testerTaskAttemptRepo.findByTaskIdAndTesterId(taskId, empRecord.getUserId().toString()).stream().map(r->{
                    TesterTaskAttemptExtended taskTemp = TesterTaskAttemptExtended.convertFromTesterTaskAttemptDTO(r);
                    taskTemp.setTesterUsername(profile.getUsername());
                    return taskTemp;
                }).collect(Collectors.toList());
        		break;
            default:
        		tasks = testerTaskAttemptRepo.findByTaskIdWithTesterUserName(taskId).stream().
                        map(TesterTaskAttemptExtended::convertFromTesterTaskAttempt).collect(Collectors.toList());
        		break;

        	}        	
        }

        log.info(String.format("Exit %s from class %s", "getTaskHistory","TaskServiceImpl" ));

        return tasks;
    }
    
    @Override
    public String addTask ( TaskDTO task) {
        log.info(String.format("Called %s from class %s", "addTask","TaskServiceImpl" ));
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
    public List<TesterTaskAttemptDTO> allTasksAttemptsByTesterId ( Long testerId) {
        log.info(String.format("Called %s from class %s", "allTasksAttemptsByTesterId","TaskServiceImpl" ));
        ProfileDTO profile = (ProfileDTO) SecurityContextHolder.getContext().getAuthentication().getDetails();
        List<TesterTaskAttempt> testerTaskAttempts = new ArrayList<TesterTaskAttempt>();
        if(profile.getRole().equals("tester")) {
        	testerTaskAttempts = testerTaskAttemptRepo.findByTesterId(profile.getUserId());
        } else if(testerId!=null) {
        	 Optional<Tester> tester = testerRepo.findByTesterId(testerId);
        	 if(tester.isPresent()) {
        		 testerTaskAttempts = testerTaskAttemptRepo.findByTesterId(testerId.toString());
        	 }
        }

        log.info(String.format("Exit %s from class %s", "allTasksAttemptsByTesterId","TaskServiceImpl" ));
        return testerTaskAttempts.stream().map(TesterTaskAttemptDTO::dtofromEntity)
                .collect(Collectors.toList());
    }
    
    public Map <String, Object> countAttemptedAndTotalTasks ( String projectId, int testerId ) {
        log.info(String.format("Called %s from class %s", "countAttemptedAndTotalTasks","TaskServiceImpl" ));

        Map<String, Integer> attempt = testerTaskAttemptRepo.countAttemptedToTotalTasks(projectId,testerId);


        Map<String,Object> ret = new HashMap <>();
        ret.put("totalTasks",attempt.get("attempted_tasks"));
        ret.put("attemptedTasks",attempt.get("total_tasks"));


        log.info(String.format("Exit %s from class %s", "countAttemptedAndTotalTasks","TaskServiceImpl" ));
        return ret;

    }
    
    
}
