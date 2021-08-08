package com.itest.baseapplication.repository;

import com.itest.baseapplication.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepo extends JpaRepository<Developer,String> {
}
