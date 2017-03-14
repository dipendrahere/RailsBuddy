package com.example.dipendra.railbuddy.models;

import android.widget.Spinner;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dipendra on 22/11/16.
 */

public class Trains {
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    private String train_number="";

    public String getTrain_number() {
        return train_number;
    }

    public void setTrain_number(String train_number) {
        this.train_number = train_number;
    }

    @JsonInclude(JsonInclude.Include.NON_ABSENT)

    private String position = "";
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    private CurrentStatus current_station;
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    private ArrayList<fare> fare;

    public Quota getQuota() {
        return quota;
    }

    public void setQuota(Quota quota) {
        this.quota = quota;
    }

    private Quota quota;
    public static class Quota{
        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public ArrayList<fare> getFarelist() {
        return fare;
    }

    public void setFarelist(ArrayList<fare> farelist) {
        this.fare = farelist;
    }

    public String getPosition() {
            return position;
        }
    public void setPosition(String position) {
            this.position = position;
        }
    public CurrentStatus getCurrent_station() {
            return current_station;
        }
    public void setCurrent_station(CurrentStatus current_station) {
        this.current_station = current_station;
    }
    private ArrayList<Route> route;

    public ArrayList<Trains.fare> getFare() {
        return fare;
    }

    public void setFare(ArrayList<Trains.fare> fare) {
        this.fare = fare;
    }

    public ArrayList<Route> getRoute() {
        return route;
    }

    public void setRoute(ArrayList<Route> route) {
        this.route = route;
    }

    public static class Route{
        private Station station_;
        private boolean has_arrived;
        private boolean has_departed;

        public boolean isHas_arrived() {
            return has_arrived;
        }

        public void setHas_arrived(boolean has_arrived) {
            this.has_arrived = has_arrived;
        }

        public boolean isHas_departed() {
            return has_departed;
        }

        public void setHas_departed(boolean has_departed) {
            this.has_departed = has_departed;
        }

        public String getActdep() {
            return actdep;
        }

        public void setActdep(String actdep) {
            this.actdep = actdep;
        }

        public int getLatemin() {
            return latemin;
        }

        public void setLatemin(int latemin) {
            this.latemin = latemin;
        }

        private String actarr;
        private String actdep;
        private int latemin;

        public String getActarr() {
            return actarr;
        }

        public void setActarr(String actarr) {
            this.actarr = actarr;
        }

        public Station getStation_() {
            return station_;
        }

        public void setStation_(Station station_) {
            this.station_ = station_;
        }

        public static class Station{
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
    public static class fare{
        @JsonInclude(JsonInclude.Include.NON_ABSENT)
        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getFare() {
            return fare;
        }

        public void setFare(String fare) {
            this.fare = fare;
        }
        @JsonInclude(JsonInclude.Include.NON_ABSENT)
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @JsonInclude(JsonInclude.Include.NON_ABSENT)
        private String fare;
    }

    public static class CurrentStatus{
        @JsonInclude(JsonInclude.Include.NON_ABSENT)
        private boolean has_arrived;
        @JsonInclude(JsonInclude.Include.NON_ABSENT)
        private boolean has_departed;
        @JsonInclude(JsonInclude.Include.NON_ABSENT)
        private int latemin;
        public boolean isHas_arrived() {
                return has_arrived;
        }
        public void setHas_arrived(boolean has_arrived) {
            this.has_arrived = has_arrived;
        }
        public boolean isHas_departed() {
            return has_departed;
        }
        public void setHas_departed(boolean has_departed) {
                this.has_departed = has_departed;
            }
        public int getLatemin() {
                return latemin;
            }
        public void setLatemin(int latemin) {
                this.latemin = latemin;
            }
        }

    }


