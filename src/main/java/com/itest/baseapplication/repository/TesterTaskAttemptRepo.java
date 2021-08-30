package com.itest.baseapplication.repository;

import com.itest.baseapplication.entity.TesterTaskAttempt;
import com.itest.baseapplication.projection.TesterTaskAttempExtendedProj;
import com.itest.baseapplication.util.Queries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TesterTaskAttemptRepo extends JpaRepository<TesterTaskAttempt,Integer> {
//	Total number of tasks attempted till date
	@Query("select count(*) from TesterTaskAttempt where testerId = :testerId")
	Integer countTaskAttempted(String testerId);

	@Query(value = Queries.GETTASKSWITHUSERNAME,nativeQuery = true)
	List<TesterTaskAttempExtendedProj> findByTaskIdWithTesterUserName( Integer taskId);

	List<TesterTaskAttempt> findByTaskIdAndTesterId(Integer taskId, String testerId);

	List<TesterTaskAttempt> findByTesterId(String testerId);

// TotalTask to attempted task
	@Query(value = Queries.COUNTATTEMPTEDTOTOTALTASK,nativeQuery = true)
	Map <String,Integer> countAttemptedToTotalTasks( String projectId, int taskId );


}
