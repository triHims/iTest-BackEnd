package com.itest.baseapplication.repository;

import com.itest.baseapplication.entity.AttemptTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptTaskRepo extends JpaRepository <AttemptTask,String> {
}
