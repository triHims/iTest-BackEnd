package com.itest.baseapplication.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="task_table")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="task_id")
    private Integer taskId;

    @Column(name="task_name")
    private String taskName;

    @Column(name="task_description")
    private String taskDescription;

    @Column(name="task_url")
    private String taskUrl;

    @Column(name="project_id")
    private String projectId;

    @Column(name="task_creator")
    private String taskCreator;

    @Column(name="task_steps")
    private String steps;

    @Column(name="flag")
    private String flag;

    @Column(name="created_date")
    private LocalDateTime createdDate;

    @Column(name="target_date")
    private LocalDateTime targetDate;

    @Column(name="target_device")
    private String targetDevice;
    @Column(name="task_step_count")
    private int stepCount;


    public Integer getTaskId () {
        return taskId;
    }

    public String getTaskName () {
        return taskName;
    }

    public String getTaskDescription () {
        return taskDescription;
    }

    public String getTaskUrl () {
        return taskUrl;
    }

    public String getProjectId () {
        return projectId;
    }

    public String getTaskCreator () {
        return taskCreator;
    }

    public String getSteps () {
        return steps;
    }

    public String getFlag () {
        return flag;
    }

    public LocalDateTime getCreatedDate () {
        return createdDate;
    }

    public LocalDateTime getTargetDate () {
        return targetDate;
    }

    public String getTargetDevice () {
        return targetDevice;
    }

    public int getStepCount () {
        return stepCount;
    }
}
