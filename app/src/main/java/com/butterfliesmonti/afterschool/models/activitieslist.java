package com.butterfliesmonti.afterschool.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class activitieslist {

    @SerializedName("activity_id")
    @Expose
    private String activityId;
    @SerializedName("activity_name")
    @Expose
    private String activityName;
    @SerializedName("age_group")
    @Expose
    private String ageGroup;
    @SerializedName("fees")
    @Expose
    private String fees;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

}