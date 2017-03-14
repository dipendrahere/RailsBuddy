package com.example.dipendra.railbuddy.info;

/**
 * Created by ydeepak on 20/11/16.
 */

public class trainBetweenStationInfo extends infoDetail{

    private String trainNumber;
    private String trainName;
    private String[] runningDays = new String[7];
    private String source;
    private String destination;
    private String depTime;
    private String arrTime;

    public trainBetweenStationInfo(String trainNumber, String trainName, String [] runningDays, String source, String destination, String depTime, String arrTime) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.runningDays = runningDays;
        this.source = source;
        this.destination = destination;
        this.depTime = depTime;
        this.arrTime = arrTime;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepTime() {
        return arrTime;
    }

    public String getArrTime() {
        return depTime;
    }

    public String[] getRunningDays() {
        return runningDays;
    }

}
