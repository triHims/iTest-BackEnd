package com.itest.baseapplication.dto;

import com.itest.baseapplication.entity.Task;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
public class TaskDTO {
    private Integer taskId;


    private String taskName;


    private String taskDescription;


    private String taskUrl;


    private String projectId;


    private String taskCreator;


    private String steps;


    private String flag;


    private LocalDateTime createdDate;


    private LocalDateTime targetDate;


    private String targetDevice;

    private String stepCount;


    public static TaskDTO dtofromObject( Task task){
        final ModelMapper mapper = new ModelMapper();

        return mapper.map(task,TaskDTO.class);

    }

}
