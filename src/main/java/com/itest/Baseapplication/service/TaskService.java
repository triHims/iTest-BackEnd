package com.itest.Baseapplication.service;

import com.itest.Baseapplication.dto.AttemptTask;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;



public interface TaskService {
    LinkedHashMap <String,Object> getDummyTask() ;
    boolean writeAttemptJSON( AttemptTask attemptObject);
}
