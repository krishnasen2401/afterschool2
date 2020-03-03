package com.butterfliesmonti.afterschool.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class activities_reg_list {
    @SerializedName("studentid")
    @Expose
    private String studentid;
    @SerializedName("activity_id")
    @Expose
    private String activityId;
    @SerializedName("date_of_reg")
    @Expose
    private String dateOfReg;

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

    public String getDateOfReg() {
        return dateOfReg;
    }

    public void setDateOfReg(String dateOfReg) {
        this.dateOfReg = dateOfReg;
    }
}
