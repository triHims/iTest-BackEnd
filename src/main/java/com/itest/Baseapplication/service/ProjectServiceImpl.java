package com.itest.Baseapplication.service;


import com.itest.Baseapplication.dto.AttemptTask;
import com.itest.Baseapplication.dto.Project;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;


@Service
public class ProjectServiceImpl  implements  ProjectService{

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

    @Override
    public LinkedHashMap<String,Object> getProject(String projectId)  {

        try {
            JSONParser project =new JSONParser(new FileReader("src\\main\\resources\\projects\\"+projectId+".json"));
            LinkedHashMap<String,Object> jsonObject = project.object();

            return jsonObject;
        }
        catch (FileNotFoundException | ParseException ex){
            System.out.println(ex.getMessage());
        }

        return new LinkedHashMap <>();
    }
    @Override
    public Object getTasks(String projectId)  {

        try {
            JSONParser project =new JSONParser(new FileReader("src\\main\\resources\\projects\\"+projectId+".json"));
            LinkedHashMap<String,Object> jsonObject = project.object();
            return jsonObject.get("projectTaskList");
        }
        catch (FileNotFoundException | ParseException ex){
            System.out.println(ex.getMessage());
        }

        return new Object();
    }

    @Override
    public boolean writeAttemptJSON ( Project attemptObject) {
        return true;
    }
}
