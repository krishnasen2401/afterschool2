package com.butterfliesmonti.afterschool.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.butterfliesmonti.afterschool.Adapters.student_list_adapter_attendance;
import com.butterfliesmonti.afterschool.R;
import com.butterfliesmonti.afterschool.baseurl;
import com.butterfliesmonti.afterschool.models.student_regs_list;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class attendance_out extends AppCompatActivity {
    List<student_regs_list> studentnamelist;
    RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    student_list_adapter_attendance madapter;
    Retrofit retrofit;
    String Dateatt;
    TextView tvdate;
    baseurl bs=new baseurl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_out);
        Intent i=getIntent();
        Dateatt= new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        tvdate=findViewById(R.id.tvDateAtt);
        tvdate.setText(Dateatt);
        retrofit = new Retrofit.Builder()
                .baseUrl(bs.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api_students_fetch_solo service=retrofit.create(Api_students_fetch_solo.class);
        Call<List<student_regs_list>> call= service.getstudentNameJson("studentlist",i.getStringExtra("activityname"));
        call.enqueue(new Callback<List<student_regs_list>>() {
            @Override
            public void onResponse(Call<List<student_regs_list>> call, Response<List<student_regs_list>> response) {
                studentnamelist=new ArrayList<>(response.body());
                mRecyclerView=findViewById(R.id.rvStudentListAtt);
                mLayoutManager = new LinearLayoutManager(attendance_out.this,LinearLayoutManager.VERTICAL,false);
                mRecyclerView.setLayoutManager(mLayoutManager);
                madapter = new student_list_adapter_attendance(studentnamelist, getApplicationContext(),mRecyclerView);
                mRecyclerView.setAdapter(madapter);
            }
            @Override
            public void onFailure(Call<List<student_regs_list>> call, Throwable t) {
                Toast.makeText(attendance_out.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });

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
                        tvdate.setText(String.format("%04d-%02d-%02d",year,monthOfYear+1,dayOfMonth));
                        Dateatt=String.format("%04d-%02d-%02d",year,monthOfYear+1,dayOfMonth);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
public void sendAttendance(View v){
List<student_regs_list> selectedlist=madapter.sendSelectedList();
student_regs_list al;
for(int i=0;i<selectedlist.size();i++){
    al=selectedlist.get(i);
    al.setDate(Dateatt);
    }
Gson gson=new Gson();
final String newdataArray=gson.toJson(selectedlist);
Log.d("String gson",newdataArray);
Api_attendance_out_solo service=retrofit.create(Api_attendance_out_solo.class);
    Call<ResponseBody> call= service.attendanceout_add("reg",newdataArray);
    call.enqueue(new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            try {
                String message = response.body().string();
                Log.d("message",message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            String message=t.getMessage();
            if(message.trim().equals(null)){
                Toast.makeText(attendance_out.this,"None of the activities are selected",Toast.LENGTH_LONG).show();
            }
        }
    });
}
}
interface Api_students_fetch_solo {
    @FormUrlEncoded
    @POST("student_activites_reg_list.php")
    Call<List<student_regs_list>> getstudentNameJson(@Field("action") String action,@Field("activityname") String activityname);
}
interface Api_attendance_out_solo {
    @FormUrlEncoded
    @POST("attendance_android.php")
    Call<ResponseBody> attendanceout_add(
            @Field("action") String action,
            @Field("dataarray") String dataarray
    );
}
