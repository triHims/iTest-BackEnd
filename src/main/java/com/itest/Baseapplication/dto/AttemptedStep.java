package com.itest.Baseapplication.dto;

public class AttemptedStep {
    int stepNo;
    String stepType;
    String progress;
    String value;

    public AttemptedStep () {}

    public AttemptedStep ( int stepNo, String stepType, String progress, String value ) {
        this.stepNo = stepNo;
        this.stepType = stepType;
        this.progress = progress;
        this.value = value;
    }

    public int getStepNo () {
        return stepNo;
    }

    public void setStepNo ( int stepNo ) {
        this.stepNo = stepNo;
    }

    public String getStepType () {
        return stepType;
    }

    public void setStepType ( String stepType ) {
        this.stepType = stepType;
    }

    public String getProgress () {
        return progress;
    }

    public void setProgress ( String progress ) {
        this.progress = progress;
    }

    public String getValue () {
        return value;
    }

    public void setValue ( String value ) {
        this.value = value;
    }


    public String toString () {
        return "{" +
                " stepNo :" + stepNo + ",\n" +
                " stepType :'" + stepType + "\',\n" +
                " progress :'" + progress + "\',\n" +
                " value :'" + value + "\',\n" +

                '}';
    }
}
