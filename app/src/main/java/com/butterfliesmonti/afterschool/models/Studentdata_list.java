package com.butterfliesmonti.afterschool.models;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Studentdata_list {

        @SerializedName("studentid")
        @Expose
        private String studentid;
        @SerializedName("studentname")
        @Expose
        private String studentname;
        @SerializedName("activity list")
        @Expose
        private List<String> activityList = null;
        private boolean expanded;

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public String getStudentid() { return studentid; }
    public void setStudentid(String studentid) { this.studentid = studentid; }

        public String getStudentname() {
            return studentname;
        }

        public void setStudentname(String studentname) {
            this.studentname = studentname;
        }

        public List<String> getActivityList() {
            return activityList;
        }

        public void setActivityList(List<String> activityList) {
            this.activityList = activityList;
        }

    }