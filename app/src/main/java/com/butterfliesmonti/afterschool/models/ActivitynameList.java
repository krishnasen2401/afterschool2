package com.butterfliesmonti.afterschool.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActivitynameList {
    @SerializedName("activity_name")
    @Expose
    private String activityName;
    public String getActivityName() {
        return activityName;
    }
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}