package com.proje.mobilesales.core.service;

import com.proje.mobilesales.features.reports.model.ReportResponse;
import io.reactivex.Maybe;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


/* compiled from: PrinterServiceApi.kt */

public interface PrinterServiceApi {
    @GET("api/printers")
    Maybe<PrinterNames> getPrinter();

    @GET("api/reports")
    Single<ReportResponse> getReport();

    @POST("api/print")
    Single<PrintResponse> print(@Body PrintItem printItem);
}
