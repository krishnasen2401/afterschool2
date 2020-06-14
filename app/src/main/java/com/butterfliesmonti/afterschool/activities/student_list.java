package com.butterfliesmonti.afterschool.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.butterfliesmonti.afterschool.Adapters.student_list_adapter_lvl1;
import com.butterfliesmonti.afterschool.R;
import com.butterfliesmonti.afterschool.baseurl;
import com.butterfliesmonti.afterschool.models.Studentdata_list;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class student_list extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private student_list_adapter_lvl1 madapter;
    baseurl bs=new baseurl();
    Retrofit retrofit;
    List<Studentdata_list> studentdata_lists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        mRecyclerView=findViewById(R.id.rvStudentListTest);
        mLayoutManager = new LinearLayoutManager(student_list.this,LinearLayoutManager.VERTICAL,false);
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        mRecyclerView.setLayoutManager(mLayoutManager);
        retrofit = new Retrofit.Builder()
                .baseUrl(bs.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())//mention which conveter we are using for fetch
                .build();
        Api_studentdata_fetch service=retrofit.create(Api_studentdata_fetch.class);
        Call<List<Studentdata_list>> call= service.getstudentdatajson();
        call.enqueue(new Callback<List<Studentdata_list>>() {
            @Override
            public void onResponse(Call<List<Studentdata_list>> call, Response<List<Studentdata_list>> response) {
                studentdata_lists=new ArrayList<>(response.body());
                Log.d("code", String.valueOf(response.code()));
                invokemadapter();
            }
            @Override
            public void onFailure(Call<List<Studentdata_list>> call, Throwable t) {
                Toast.makeText(student_list.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }

    void invokemadapter(){
        madapter = new student_list_adapter_lvl1(studentdata_lists, getApplicationContext(),mRecyclerView);
        mRecyclerView.setAdapter(madapter);
    }

}
interface Api_studentdata_fetch {
    @GET("studentdata.php")
    Call<List<Studentdata_list>> getstudentdatajson();
}