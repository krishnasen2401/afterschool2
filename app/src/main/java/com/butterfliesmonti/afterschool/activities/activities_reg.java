package com.butterfliesmonti.afterschool.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.butterfliesmonti.afterschool.Adapters.activities_Regs_Adapter;
import com.butterfliesmonti.afterschool.MainActivity;
import com.butterfliesmonti.afterschool.R;
import com.butterfliesmonti.afterschool.apicalls.RetrofitClient;
import com.butterfliesmonti.afterschool.models.activities_reg_list;
import com.butterfliesmonti.afterschool.models.activitieslist;
import com.google.gson.Gson;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activities_reg extends AppCompatActivity {
    List<activitieslist> activitieslists1;
    List<activities_reg_list> selectedActivites;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private activities_Regs_Adapter madapter;
    EditText etDateReg;
    String studentid,studentname,message;
    TextView tvStudenid,tvstudentname;
    private void getActivitiesListResponse(){
        Call<List<activitieslist>> call= RetrofitClient.getInstance().get_Api_fetch().getactitivitesJson("list");
        call.enqueue(new Callback<List<activitieslist>>() {
            @Override
            public void onResponse(Call<List<activitieslist>> call, Response<List<activitieslist>> response) {
                activitieslists1=new ArrayList<>(response.body());
                mRecyclerView=findViewById(R.id.rvActivitesListReg);
                mLayoutManager = new LinearLayoutManager(activities_reg.this,LinearLayoutManager.VERTICAL,false);
                mRecyclerView.setLayoutManager(mLayoutManager);
                madapter = new activities_Regs_Adapter(activitieslists1, getApplicationContext(),mRecyclerView,tvStudenid.getText().toString());
                mRecyclerView.setAdapter(madapter);
            }

            @Override
            public void onFailure(Call<List<activitieslist>> call, Throwable t) {
                Toast.makeText(activities_reg.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i=getIntent();
        studentid=i.getStringExtra("stid");
        studentname=i.getStringExtra("stname");
        setContentView(R.layout.activity_activities_reg);
        activitieslists1=new ArrayList<>();
        getActivitiesListResponse();
        selectedActivites=new ArrayList<>();
        etDateReg=findViewById(R.id.ETstdob2);
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        etDateReg.setText(currentDate);
        tvstudentname=findViewById(R.id.tvStudentNameActReg);
        tvstudentname.setText(studentname);
        tvStudenid=findViewById(R.id.tvStudentidActReg);
        tvStudenid.setText(studentid);
        message=null;
    }

    public void PickDate(View view){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        etDateReg.setText(String.format("%04d-%02d-%02d",year,monthOfYear+1,dayOfMonth));
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void submitActivites(View v){
        Gson gson= new Gson();
        selectedActivites=madapter.listofselectedactivites();
        final String newdataArray=gson.toJson(selectedActivites);
        Log.d("String gson",newdataArray);
        Call<ResponseBody> call= RetrofitClient.getInstance().get_Api_Activities_Reg_add().activites_reg_add("reg",newdataArray);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    message = response.body().string();
                    activitedstatus();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            message=t.getMessage();
            if(message.trim().equals(null)){
                Toast.makeText(activities_reg.this,"None of the activities are selected",Toast.LENGTH_LONG).show();
            }
            }
        });
    }
    public void activitedstatus(){
        List<String> status;
        String finalmessage="";
        String failed="";
        status= Arrays.asList(message.split("-"));
     for (int i=0;i<status.size();i++){
            if(status.get(i).split(":")[1].equals("successfully registered")){
                finalmessage=finalmessage+status.get(i)+"\n";
            }else{
                failed = failed+status.get(i)+"\n";
            }}
        finalmessage=finalmessage+failed;
     Log.d("message",message);
     Log.d("message length", String.valueOf(status.size()));
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Registrations Output");
            alertDialog.setMessage(finalmessage);
            alertDialog.setCancelable(false);
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Go Home", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent I=new Intent(activities_reg.this, MainActivity.class);
                    I.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(I);
                }
            });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Add Another Registration", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent I=new Intent(activities_reg.this, addstudent.class);
                I.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(I);
            }
        });
        alertDialog.show();
    }
}
