package com.itest.baseapplication.service;

import com.itest.baseapplication.dto.ProjectDTO;
import com.itest.baseapplication.entity.Project;

import java.util.LinkedHashMap;
import java.util.List;

public interface ProjectService {
    ProjectDTO getProject( String projectId) ;

    List <ProjectDTO> getAllProjects();
}
