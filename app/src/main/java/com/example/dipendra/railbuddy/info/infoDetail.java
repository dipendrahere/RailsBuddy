package com.example.dipendra.railbuddy.info;

/**
 * Created by ydeepak on 20/11/16.
 */

public class infoDetail {

    private String trainName;
    private String trainNumber;
    private String[] runningDays = new String[7];

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public void setTrainNumber(String trainNumber1) {
        trainNumber = trainNumber1;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setRunningDays(String[] runningDays1) {
        runningDays = runningDays1;
    }

    public String[] getRunningDays() {
        return runningDays;
    }
}
