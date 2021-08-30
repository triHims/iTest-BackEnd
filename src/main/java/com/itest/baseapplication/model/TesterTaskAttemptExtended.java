package com.itest.baseapplication.model;

import com.itest.baseapplication.dto.TaskDTO;
import com.itest.baseapplication.dto.TesterTaskAttemptDTO;
import com.itest.baseapplication.entity.TesterTaskAttempt;
import com.itest.baseapplication.projection.TesterTaskAttempExtendedProj;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class TesterTaskAttemptExtended extends TesterTaskAttemptDTO {
    private String testerUsername;
    public static TesterTaskAttemptExtended convertFromTesterTaskAttemptDTO( TesterTaskAttempt dto){
        TesterTaskAttemptExtended extended = new TesterTaskAttemptExtended();
        extended.setAttemptDate(dto.getAttemptDate());
        extended.setAttemptDuration(dto.getAttemptDuration());
        extended.setTaskId(dto.getTaskId());
        extended.setAttemptId( dto.getAttemptId());
        extended.setAttemptFileName(dto.getAttemptFileName()!=null? dto.getAttemptFileName() : null);
        extended.setAttemptJson(dto.getAttemptJson());
        extended.setTesterId(dto.getTesterId());
        extended.setAttemptProgressPercentage(dto.getAttemptProgressPercentage());
        return extended;
    }
    public static TesterTaskAttemptExtended convertFromTesterTaskAttempt( TesterTaskAttempExtendedProj dto){
        TesterTaskAttemptExtended extended = new TesterTaskAttemptExtended();
        extended.setAttemptDate(dto.getAttemptDate());
        extended.setAttemptDuration(dto.getAttemptDuration());
        extended.setTaskId(dto.getTaskId());
        extended.setAttemptId( dto.getAttemptId());
        extended.setAttemptFileName(dto.getAttemptFileName()!=null? dto.getAttemptFileName() : null);
        extended.setAttemptJson(dto.getAttemptJson());
        extended.setTesterId(dto.getTesterId());
        extended.setAttemptProgressPercentage(dto.getAttemptProgressPercentage());
        extended.setTesterUsername(dto.getTesterUsername());
        return extended;
    }
}
