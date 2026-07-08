package com.proje.mobilesales.core.netsis;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface NetsisRestTokenApi {
    @FormUrlEncoded
    @POST("/api/v2/token")
    Call<NetsisTokenManager> getToken(@FieldMap Map<String, String> map);

    @GET("/api/v2/revoke")
    Call<String> logout(@Header("Authorization") String str);

    @FormUrlEncoded
    @POST("/api/v2/token")
    Call<NetsisTokenManager> refreshToken(@FieldMap Map<String, String> map, @Header("trustedKey") String str, @Header("sessionid") String str2);
}
