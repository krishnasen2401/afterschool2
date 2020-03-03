package com.butterfliesmonti.afterschool.apicalls;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_Activities_Add {
    @FormUrlEncoded
    @POST("activities.php")
    Call<ResponseBody> addActivites(
            @Field("action") String action,
            @Field("activities_name") String activities_name,
            @Field("age_start") String age_start,
            @Field("age_end") String age_end,
            @Field("fees") String fees
    );
}
