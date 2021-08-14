package com.itest.baseapplication.repository;

import com.itest.baseapplication.entity.Developer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepo extends JpaRepository<Developer,String> {
	Developer findByDeveloperId(long developerId);
}
