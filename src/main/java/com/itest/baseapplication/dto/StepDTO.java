package com.itest.baseapplication.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
public class StepDTO {
    private String stepList;
    private int taskId;
    private int noOfSteps;
}
