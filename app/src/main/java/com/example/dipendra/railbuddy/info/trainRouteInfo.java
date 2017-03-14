package com.example.dipendra.railbuddy.info;

/**
 * Created by ydeepak on 21/11/16.
 */

public class trainRouteInfo extends infoDetail {
    private String fullName;
    private String code;
    private String srno;
    private String scharr;
    private String schdep;
    private String day;
    private String distance;

    public trainRouteInfo(String fullName, String code, String srno, String scharr, String schdep, String day, String distance) {
        this.fullName = fullName;
        this.code = code;
        this.srno = srno;
        this.scharr = scharr;
        this.schdep = schdep;
        this.day = day;
        this.distance = distance;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCode() {
        return code;
    }

    public String getSrno() {
        return srno;
    }

    public String getScharr() {
        return scharr;
    }

    public String getSchdep() {
        return schdep;
    }

    public String getDay() {
        return day;
    }

    public String getDistance() {
        return distance;
    }

}
