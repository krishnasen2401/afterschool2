package com.butterfliesmonti.afterschool.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.butterfliesmonti.afterschool.Adapters.attendance_main_adapter;
import com.butterfliesmonti.afterschool.R;
import com.butterfliesmonti.afterschool.baseurl;
import com.butterfliesmonti.afterschool.models.ActivitynameList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class main_attendance extends AppCompatActivity {
List<ActivitynameList> activitynamelist;
RecyclerView mRecyclerView;
private RecyclerView.LayoutManager mLayoutManager;
attendance_main_adapter madapter;
baseurl bs=new baseurl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("extra",getIntent().getStringExtra("activity"));
        setContentView(R.layout.activity_main_attendane);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(bs.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api_Activites_fetch_solo service=retrofit.create(Api_Activites_fetch_solo.class);
        Call<List<ActivitynameList>> call= service.getactitivitesNameJson("activitynamelist");
        call.enqueue(new Callback<List<ActivitynameList>>() {
            @Override
            public void onResponse(Call<List<ActivitynameList>> call, Response<List<ActivitynameList>> response) {
                activitynamelist=new ArrayList<>(response.body());
                Log.d("length", String.valueOf(activitynamelist.size()));
                mRecyclerView=findViewById(R.id.rvActivityListAttend);
                mLayoutManager = new LinearLayoutManager(main_attendance.this,LinearLayoutManager.VERTICAL,false);
                mRecyclerView.setLayoutManager(mLayoutManager);
                madapter = new attendance_main_adapter(activitynamelist, getApplicationContext(),mRecyclerView);
                mRecyclerView.setAdapter(madapter);
            }
            @Override
            public void onFailure(Call<List<ActivitynameList>> call, Throwable t) {
                Toast.makeText(main_attendance.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });

    }
}

//the retrofit in single file
interface Api_Activites_fetch_solo {
    @FormUrlEncoded
    @POST("activities.php")
    Call<List<ActivitynameList>> getactitivitesNameJson(@Field("action") String action);
}
