package com.butterfliesmonti.afterschool.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.butterfliesmonti.afterschool.R;
import com.butterfliesmonti.afterschool.apicalls.RetrofitClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addactivities extends AppCompatActivity {
EditText etactivityname,etfromage,ettoage,etfees;
String  activityname,fromage,toage,fees;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addactivities);
        etactivityname=findViewById(R.id.etActivityName);
        etfromage=findViewById(R.id.etFromAge);
        ettoage=findViewById(R.id.etToAge);
        etfees=findViewById(R.id.etFees);
    }
    public void addactivitiy(View v){
        activityname=String.valueOf(etactivityname.getText());
        fromage=String.valueOf(etfromage.getText());
        toage=String.valueOf(ettoage.getText());
        fees=String.valueOf(etfees.getText());
        Call<ResponseBody> call= RetrofitClient.getInstance().get_Api_Activities_Add().addActivites("reg",activityname,fromage,toage,fees);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String s = null;
                try {
                    s = response.body().string();
                    Log.d("success",s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(addactivities.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(addactivities.this,t.getMessage(),Toast.LENGTH_LONG).show();
            Log.d("api-call","failed");
            }
        });
    }
}
