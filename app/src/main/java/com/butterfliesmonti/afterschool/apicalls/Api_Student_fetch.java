package com.butterfliesmonti.afterschool.apicalls;

import com.butterfliesmonti.afterschool.models.studentslist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_Student_fetch {
    @FormUrlEncoded
    @POST("student_reg.php")
    Call<List<studentslist>> getactitivitesJson(@Field("action") String action);
}
