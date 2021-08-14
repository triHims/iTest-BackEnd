package com.itest.baseapplication.repository;


import com.itest.baseapplication.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,String > {
    Optional <Project> findById( String id);
    List <Project> findAll();
    List <Project> findByLeadDeveloperId(String id);
}
