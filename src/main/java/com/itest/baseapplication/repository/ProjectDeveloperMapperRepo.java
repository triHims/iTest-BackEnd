package com.itest.baseapplication.repository;

import com.itest.baseapplication.entity.ProjectDeveloperMapper;
import com.itest.baseapplication.entity.TesterTaskAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectDeveloperMapperRepo extends JpaRepository<ProjectDeveloperMapper,Integer> {
	@Query("Select projectId from ProjectDeveloperMapper where developerId=:developerId")
	List<String> findProjectByDeveloperId(int developerId);
}
