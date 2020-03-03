package com.butterfliesmonti.afterschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.butterfliesmonti.afterschool.activities.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void addstudent(View v){
        Intent I=new Intent(this,addstudent.class);
        startActivity(I);
    }

    public void addActivities(View v){
        Intent I=new Intent(this, addactivities.class);
        startActivity(I);
    }
    public void takeAttendance(View v){
        Intent I=new Intent(this, main_attendance.class);
        startActivity(I);
    }
    public void studentlist(View V){
        Intent I=new Intent(this, student_list.class);
        startActivity(I);
    }
}
