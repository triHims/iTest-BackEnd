package com.itest.baseapplication.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="tester_task_attempt_table")
public class AttemptTask {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="attemptId")
  String attemptId;

  @Column(name="task_id")
  int taskId;

  @Column(name="attempt_json")
  String attemptJson;

  @Column(name="tester_id")
  String testerId;

  @Column(name="attempt_date")
  LocalDateTime attemptDate;

  @Column(name="attempt_duration")
  String attemptDuration;

  @Column(name="attempt_progress_percentage")
  String attemptProgressPercentage;


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

  public void setAttemptId ( String attemptId ) {
    this.attemptId = attemptId;
  }

  public void setTaskId ( int taskId ) {
    this.taskId = taskId;
  }

  public void setAttemptJson ( String attemptJson ) {
    this.attemptJson = attemptJson;
  }

  public void setTesterId ( String testerId ) {
    this.testerId = testerId;
  }

  public void setAttemptDate ( LocalDateTime attemptDate ) {
    this.attemptDate = attemptDate;
  }

  public void setAttemptDuration ( String attemptDuration ) {
    this.attemptDuration = attemptDuration;
  }

  public void setAttemptProgressPercentage ( String attemptProgressPercentage ) {
    this.attemptProgressPercentage = attemptProgressPercentage;
  }
}


