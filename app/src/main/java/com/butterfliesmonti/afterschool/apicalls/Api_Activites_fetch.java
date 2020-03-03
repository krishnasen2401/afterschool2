package com.butterfliesmonti.afterschool.apicalls;

import com.butterfliesmonti.afterschool.models.activitieslist;
import com.butterfliesmonti.afterschool.models.studentslist;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_Activites_fetch {
    @FormUrlEncoded
    @POST("activities.php")
    Call<List<activitieslist>> getactitivitesJson(@Field("action") String action);
}

