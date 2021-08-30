package com.itest.baseapplication.projection;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import java.time.LocalDate;

public interface TesterTaskAttempExtendedProj {

    @Value("#{target.attempt_id}")
    Long getAttemptId ();

    @Value("#{target.task_id}")
    Integer getTaskId ();

    @Value("#{target.attempt_json}")
    String getAttemptJson ();

    @Value("#{target.tester_id}")
    String getTesterId ();

    @Value("#{target.attempt_date}")
    LocalDate getAttemptDate ();

    @Value("#{target.attempt_duration}")
    String getAttemptDuration ();

    @Value("#{target.attempt_progress_percentage}")
    Integer getAttemptProgressPercentage ();

    @Value("#{target.attempt_file_name}")
    String getAttemptFileName ();

    @Value("#{target.tester_username}")
    String getTesterUsername();
}
