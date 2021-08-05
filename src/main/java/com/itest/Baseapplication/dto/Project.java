package com.itest.Baseapplication.dto;

import java.util.List;

public class Project {
    String taskId;
    int noOfSteps;

    List <AttemptedStep> attemptedStepList;

    public String getTaskId () {
        return taskId;
    }

    public void setTaskId ( String taskId ) {
        this.taskId = taskId;
    }

    public int getNoOfSteps () {
        return noOfSteps;
    }

    public void setNoOfSteps ( int noOfSteps ) {
        this.noOfSteps = noOfSteps;
    }

    public List <AttemptedStep> getAttemptedStepList () {
        return attemptedStepList;
    }

    public void setAttemptedStepList ( List <AttemptedStep> attemptedStep ) {
        this.attemptedStepList = attemptedStep;
    }

    public Project () {
    }

    public Project ( String taskId, int noOfSteps, List <AttemptedStep> attemptedStep ) {
        this.taskId = taskId;
        this.noOfSteps = noOfSteps;
        this.attemptedStepList = attemptedStep;
    }

    @Override
    public String toString () {
        return "{" +
                "taskId :'" + taskId + "\',\n" +
                " noOfSteps :" + noOfSteps + ",\n" +
                " attemptedStepList :" + attemptedStepList + ",\n" +
                '}';
    }
}
