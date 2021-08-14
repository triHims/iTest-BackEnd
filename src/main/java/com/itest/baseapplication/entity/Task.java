package com.itest.baseapplication.entity;


import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="task_table")
@Getter
@Setter
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

}
