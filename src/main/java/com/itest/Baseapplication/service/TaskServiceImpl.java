package com.itest.Baseapplication.service;


import com.itest.Baseapplication.dto.AttemptTask;
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
public class TaskServiceImpl  implements  TaskService{

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

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

    @Override
    public boolean writeAttemptJSON ( AttemptTask attemptObject) {
        String filepath = String.format("src\\main\\resources\\attempts\\AttempTask%s.json", LocalDateTime.now().format(formatter));
        try( FileWriter fileWriter = new FileWriter(filepath) ){
            fileWriter.write(attemptObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
