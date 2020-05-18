package com.butterfliesmonti.afterschool.models;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Studentdata_list {

        @SerializedName("studentname")
        @Expose
        private String studentname;
        @SerializedName("activity list")
        @Expose
        private List<String> activityList = null;

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