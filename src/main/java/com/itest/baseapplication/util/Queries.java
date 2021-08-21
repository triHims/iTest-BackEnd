package com.itest.baseapplication.util;

public class Queries {

    public static final String GETSTEPBYTASKID = "SELECT * FROM Task where taskId=:taskId ";


    public static final String COUNTATTEMPTEDTOTOTALTASK="call countAttemptedAndTotalTasks(:projectId,:taskId);";
}
