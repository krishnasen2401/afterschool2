package com.butterfliesmonti.afterschool.apicalls;

import com.butterfliesmonti.afterschool.baseurl;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    baseurl bs=new baseurl();
    private final String BASE_URL= bs.getBaseUrl();
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient(){
     retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized RetrofitClient getInstance(){
        if(mInstance==null){
            mInstance=new RetrofitClient();
        }
        return mInstance;
    }
    public Api_Student_Add get_Student_Add_Api(){
        return retrofit.create(Api_Student_Add.class);
    }

    public Api_Activities_Add get_Api_Activities_Add() {
        return retrofit.create(Api_Activities_Add.class);
    }
    public Api_Activites_fetch get_Api_fetch() {
        return retrofit.create(Api_Activites_fetch.class);
    }
    public Api_Student_fetch get_Api_Student_fetch() {
        return retrofit.create(Api_Student_fetch.class);
    }
    public Api_Activites_Reg_Add get_Api_Activities_Reg_add(){
        return retrofit.create(Api_Activites_Reg_Add.class);
    }
}
