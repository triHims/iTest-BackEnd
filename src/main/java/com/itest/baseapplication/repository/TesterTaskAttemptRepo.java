package com.itest.baseapplication.repository;

import com.itest.baseapplication.entity.Tester;
import com.itest.baseapplication.entity.TesterTaskAttempt;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TesterTaskAttemptRepo extends JpaRepository<TesterTaskAttempt,Integer> {
	@Query("select count(*) from TesterTaskAttempt where testerId = :testerId")
	Integer countTaskAttempted(String testerId);
	List<TesterTaskAttempt> findByTaskId(Integer taskId);
	List<TesterTaskAttempt> findByTaskIdAndTesterId(Integer taskId, String testerId);
}
