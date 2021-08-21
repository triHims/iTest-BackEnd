package com.itest.baseapplication.dto;

import com.itest.baseapplication.entity.Task;
import com.itest.baseapplication.entity.TesterTaskAttempt;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class TesterTaskAttemptDTO {
private Integer attemptId;
    
    private Integer taskId;
    
    private String attemptJson;
    
    private String testerId;
    
    private LocalDate attemptDate;
    
    private String attemptDuration;
    
    private Integer attemptProgressPercentage;

    private String attemptFileName;

    public static TesterTaskAttemptDTO dtofromEntity( TesterTaskAttempt testerTaskAttempt){
        final ModelMapper mapper = new ModelMapper();

        return mapper.map(testerTaskAttempt,TesterTaskAttemptDTO.class);

    }

}
