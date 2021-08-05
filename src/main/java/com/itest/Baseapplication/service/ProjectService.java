package com.itest.Baseapplication.service;

import com.itest.Baseapplication.dto.Project;
import com.itest.Baseapplication.dto.Task;

import java.util.LinkedHashMap;

public interface ProjectService {
    LinkedHashMap <String,Object> getProject(String projectId) ;
    Object getTasks(String projectId) ;
    boolean writeAttemptJSON( Project projectObject);
}
