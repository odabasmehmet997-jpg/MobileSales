package com.proje.mobilesales.core.netsis;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface NetsisRestPublicApi {
    @GET("api/v2/public/AuthorizedBranches")
    Call<NetsisDataHeader> getBranches(@Query("company") String str, @Query("username") String str2);

    @GET("api/v2/public/AuthorizedBranches")
    Observable<NetsisDataHeader> getBranchesRx(@Query("company") String str, @Query("username") String str2);

    @GET("api/v2/public/Version")
    Call<String> getVersion();

    @GET("api/v2/public/Version")
    Observable<String> getVersionRx();

    @GET("api/v2/public/Ping")
    Call<String> sendPing();

    @GET("api/v2/public/Ping")
    Observable<String> sendPingRx();
}
