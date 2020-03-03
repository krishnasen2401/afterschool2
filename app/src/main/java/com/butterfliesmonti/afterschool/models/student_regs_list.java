package com.butterfliesmonti.afterschool.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class student_regs_list {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("studentid")
    @Expose
    private String studentid;
    @SerializedName("activity_id")
    @Expose
    private String activityId;
    @SerializedName("date")
    @Expose
    private String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}