package com.butterfliesmonti.afterschool.apicalls;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_Student_Add {
@FormUrlEncoded
@POST("student_reg.php")
Call<ResponseBody> addstudent(
        @Field("action") String action,
        @Field("name") String name,
        @Field("gender") String gender,
        @Field("dob") String dob,
        @Field("parent1") String parent1,
        @Field("phone1") String phone1,
        @Field("parent2") String parent2,
        @Field("phone2") String phone2
);
}
