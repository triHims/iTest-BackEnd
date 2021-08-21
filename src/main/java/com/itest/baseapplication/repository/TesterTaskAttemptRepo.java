package com.itest.baseapplication.repository;

import com.itest.baseapplication.entity.Tester;
import com.itest.baseapplication.entity.TesterTaskAttempt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.itest.baseapplication.projection.AttemptStat;
import com.itest.baseapplication.util.Queries;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TesterTaskAttemptRepo extends JpaRepository<TesterTaskAttempt,Integer> {
	@Query("select count(*) from TesterTaskAttempt where testerId = :testerId")
	Integer countTaskAttempted(String testerId);
	List<TesterTaskAttempt> findByTaskId(Integer taskId);
	List<TesterTaskAttempt> findByTaskIdAndTesterId(Integer taskId, String testerId);
	List<TesterTaskAttempt> findByTesterId(String testerId);


	@Query(value = Queries.COUNTATTEMPTEDTOTOTALTASK,nativeQuery = true)
	Map <String,Integer> countAttemptedToTotalTasks( String projectId, int taskId );


}
