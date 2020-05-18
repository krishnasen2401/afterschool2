package com.butterfliesmonti.afterschool.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.butterfliesmonti.afterschool.MainActivity;
import com.butterfliesmonti.afterschool.R;
import com.butterfliesmonti.afterschool.apicalls.RetrofitClient;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addstudent extends AppCompatActivity {
EditText etdob,etname,etpname1,etpname2,etphone1,etphone2;
RadioButton rbmale,rbfemale;
String name,dob,pname1,pname2,gender,phone1,phone2;
String message,studentid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);
        etdob=findViewById(R.id.ETstdob);
        etname=findViewById(R.id.etSTName);
        etpname1=findViewById(R.id.etPname1);
        etpname2=findViewById(R.id.etPname2);
        etphone1=findViewById(R.id.etPhone1);
        etphone2=findViewById(R.id.etPhone2);
        rbmale=findViewById(R.id.rbmale);
        rbfemale=findViewById(R.id.rbfemale);
        message = null;
    }
    public void PickDate(View view){
        final Calendar c = Calendar.getInstance();int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        etdob.setText(String.format("%04d-%02d-%02d",year,monthOfYear+1,dayOfMonth));
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    public void submitdetails(View v){
        name= String.valueOf(etname.getText());
        dob=String.valueOf(etdob.getText());
        if(rbmale.isChecked()){
            gender="male";
        }else if(rbfemale.isChecked()){
            gender="female";
        }
        pname1= String.valueOf(etpname1.getText());
        phone1= String.valueOf(etphone1.getText());
        pname2= String.valueOf(etpname2.getText());
        phone2= String.valueOf(etphone2.getText());
        Call<ResponseBody> call= RetrofitClient.getInstance().get_Student_Add_Api().addstudent("reg",name,gender,dob,pname1,phone1,pname2,phone2);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    message = response.body().string();
                    AlertLauncher();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                AlertLauncher();
            }
        });
    }
    public void AlertLauncher(){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(message);
        if(message.split("-")[0].trim().equals("Entry successfully created")){
            studentid=message.split("-")[1].trim();
        alertDialog.setMessage("Do you want to added a record or register a course");
        alertDialog.setCancelable(false);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Continue With Course Registration", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent I=new Intent(addstudent.this, activities_reg.class);
                I.putExtra("stname",name);
                I.putExtra("stid",studentid);
                startActivity(I);
            }
        });
            alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL,"Enter Another Student Registration",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                }
            });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"Home",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent I=new Intent(addstudent.this, MainActivity.class);
                I.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(I);
            }
        });
        }
        else
        {
            alertDialog.setMessage("Seems like Student is already Registered check Student list");
            alertDialog.setCancelable(false);
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Enter", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            }); }
        alertDialog.show();

    }
}
