package com.itest.baseapplication.repository;

import com.itest.baseapplication.entity.Task;
import com.itest.baseapplication.util.Queries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {

    Task getByTaskId ( int taskId);

    List <Task> findByProjectId(String projectId);
    List <Task> findByTaskCreatorAndProjectId(String developerId, String projectId);
}
