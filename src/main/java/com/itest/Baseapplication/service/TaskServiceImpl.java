package com.itest.Baseapplication.service;


import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;


@Service
public class TaskServiceImpl  implements  TaskService{


    @Override
    public LinkedHashMap<String,Object> getDummyTask ()  {

        try {
            JSONParser dummytask =new JSONParser(new FileReader("src\\main\\resources\\DummyTask.json"));
            LinkedHashMap<String,Object> jsonObject = dummytask.object();

            return jsonObject;
        }
        catch (FileNotFoundException | ParseException ex){
            System.out.println(ex.getMessage());
        }

        return new LinkedHashMap <>();
    }
}
