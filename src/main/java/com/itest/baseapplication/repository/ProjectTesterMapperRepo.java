package com.itest.baseapplication.repository;


import com.itest.baseapplication.entity.ProjectTesterMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTesterMapperRepo extends JpaRepository<ProjectTesterMapper,Integer> {
	@Query("Select projectId from ProjectTesterMapper where testerId=:testerId")
	List<String> findProjectByTesterId(int testerId);
}
