package com.itest.baseapplication.dto;

import com.itest.baseapplication.entity.AttemptTask;

import java.time.LocalDateTime;

public class AttemptTaskDTO {

    String attemptId;

    int taskId;

    String attemptJson;

    String testerId;

    LocalDateTime attemptDate;

    String attemptDuration;

    String attemptProgressPercentage;

    public AttemptTaskDTO () {
    }

    public String getAttemptId () {
        return attemptId;
    }

    public int getTaskId () {
        return taskId;
    }

    public String getAttemptJson () {
        return attemptJson;
    }

    public String getTesterId () {
        return testerId;
    }

    public LocalDateTime getAttemptDate () {
        return attemptDate;
    }

    public String getAttemptDuration () {
        return attemptDuration;
    }

    public String getAttemptProgressPercentage () {
        return attemptProgressPercentage;
    }

    public AttemptTaskDTO ( String attemptId, int taskId, String attemptJson, String testerId, LocalDateTime attemptDate, String attemptDuration, String attemptProgressPercentage ) {
        this.attemptId = attemptId;
        this.taskId = taskId;
        this.attemptJson = attemptJson;
        this.testerId = testerId;
        this.attemptDate = attemptDate;
        this.attemptDuration = attemptDuration;
        this.attemptProgressPercentage = attemptProgressPercentage;
    }

    public AttemptTask dtoToTask(){
        AttemptTask attemptTask = new AttemptTask();
        attemptTask.setAttemptDate(this.getAttemptDate());
        attemptTask.setTaskId(this.getTaskId());
        attemptTask.setAttemptJson(this.getAttemptJson());
        attemptTask.setAttemptDuration(this.getAttemptDuration());
        attemptTask.setAttemptProgressPercentage(this.getAttemptProgressPercentage());
        attemptTask.setTesterId(this.getTesterId());
        return attemptTask;
    }

}
