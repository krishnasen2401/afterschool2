package com.butterfliesmonti.afterschool.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.butterfliesmonti.afterschool.Adapters.student_list_adapter;
import com.butterfliesmonti.afterschool.R;
import com.butterfliesmonti.afterschool.apicalls.RetrofitClient;
import com.butterfliesmonti.afterschool.models.studentslist;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class student_list extends AppCompatActivity {
    List<studentslist> studentslists1;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private student_list_adapter madapter;

    private void getStudentListResponse(){
        Call<List<studentslist>> call= RetrofitClient.getInstance().get_Api_Student_fetch().getactitivitesJson("list");
        call.enqueue(new Callback<List<studentslist>>() {
            @Override
            public void onResponse(Call<List<studentslist>> call, Response<List<studentslist>> response) {
                studentslists1=new ArrayList<>(response.body());
                mRecyclerView=findViewById(R.id.rvStudentList);
                mLayoutManager = new LinearLayoutManager(student_list.this,LinearLayoutManager.VERTICAL,false);
                mRecyclerView.setLayoutManager(mLayoutManager);
                madapter = new student_list_adapter(studentslists1, getApplicationContext(),mRecyclerView);
                mRecyclerView.setAdapter(madapter);
            }

            @Override
            public void onFailure(Call<List<studentslist>> call, Throwable t) {
                Toast.makeText(student_list.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        getStudentListResponse();
    }
}
