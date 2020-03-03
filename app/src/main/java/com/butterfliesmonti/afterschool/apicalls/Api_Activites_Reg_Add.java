package com.butterfliesmonti.afterschool.apicalls;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.POST;

public interface Api_Activites_Reg_Add {
    @FormUrlEncoded
    @POST("activities_reg.php")
    Call<ResponseBody> activites_reg_add(
            @Field("action") String action,
            @Field("dataarray") String dataarray
            //@Body JsonArray  jsonObject

    );
    //Call<JsonObject> sendLocation(@Body JsonObject jsonObject);
}
