package com.itest.baseapplication.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import  org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="tester_task_attempt_table")
@Setter
@Getter
public class TesterTaskAttempt {
    
	@Id
    private Integer attemptId;
    
    @Column(name = "task_id")
    private Integer taskId;
    
    @Column(name = "attempt_json")
    private String attemptJson;
    
    @Column(name = "tester_Id")
    private String testerId;
    
    @Column(name = "attempt_date")
    private LocalDate attemptDate;
    
    @Column(name = "attempt_duration")
    private String attemptDuration;
    
    @Column(name = "attempt_progress_percentage")
    private Integer attemptProgressPercentage;
}
