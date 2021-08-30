package com.itest.baseapplication.util;

public class Queries {

    public static final String GETSTEPBYTASKID = "SELECT * FROM Task where taskId=:taskId ";


    public static final String COUNTATTEMPTEDTOTOTALTASK="call countAttemptedAndTotalTasks(:projectId,:taskId);";


    public static final String GETTASKSWITHUSERNAME=
            "SELECT `tester_task_attempt_table`.`attempt_id` as attempt_id,\n" +
                    "    `tester_task_attempt_table`.`task_id` as task_id,\n" +
                    "    `tester_task_attempt_table`.`attempt_json` as attempt_json,\n" +
                    "    `tester_task_attempt_table`.`tester_id` as tester_id,\n" +
                    "    `tester_task_attempt_table`.`attempt_date` as attempt_date,\n" +
                    "    `tester_task_attempt_table`.`attempt_duration` as attempt_duration,\n" +
                    "    `tester_task_attempt_table`.`attempt_progress_percentage` as attempt_progress_percentage,\n" +
                    "    `tester_task_attempt_table`.`attempt_file_name` as attempt_file_name,\n" +
                    "    `tester_table`.`username` as `tester_username`\n" +
                    "FROM `itestdb`.`tester_task_attempt_table` inner join `itestdb`.`tester_table`\n" +
                    "on  `tester_task_attempt_table`.`tester_id` = `tester_table`.`tester_id` where `tester_task_attempt_table`.`task_id`=:taskId";
}
