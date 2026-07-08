package com.proje.mobilesales.core.netsis;

import com.proje.mobilesales.core.netsis.sendmodel.cari.Cari;
import com.proje.mobilesales.core.netsis.sendmodel.chequedeed.NetsisChequeAndDeedMain;
import com.proje.mobilesales.core.netsis.sendmodel.edocument.DraftEDocumentParam;
import com.proje.mobilesales.core.netsis.sendmodel.edocument.SendEDocumentParam;
import com.proje.mobilesales.core.netsis.sendmodel.edocument.ShowEDocumentParam;
import com.proje.mobilesales.core.netsis.sendmodel.recipt.MixedReceiptsMain;
import com.proje.mobilesales.core.netsis.sendmodel.safedeposit.SafeDeposit;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlipParam;
import com.proje.mobilesales.core.netsis.sendmodel.sales.RiskParam;
import com.proje.mobilesales.features.model.NetsisTSql;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetsisRestEndPointApi {
    @POST("/api/v2/ItemSlips/Calculate")
    Observable<DataResponse<ItemSlip>> calculateFiche(@Body ItemSlip itemSlip);

    @POST("/api/v2/ItemSlips/Delete")
    Call<DataResponse> deleteItemSlip(@Body ItemSlipParam itemSlipParam);

    @GET("/api/v2/ARPs/{id}")
    Observable<DataResponse<Cari>> getArp(@Path("id") String str);

    @GET("/api/v2/ARPs")
    Call<NetsisDataHeader> getArps();

    @GET("/api/v2/CheckAndPNotesMain/{id}")
    Observable<DataResponse<NetsisChequeAndDeedMain>> getCheckAndPNotesMain(@Path("id") String str);

    @POST("/api/v2/ARPs/Risk")
    Observable<DataResponse<String>> getCustomerRisk(@Body RiskParam riskParam);

    @GET("/api/v2/ExRates")
    Call<NetsisDataHeader> getExRates();

    @GET("/api/v2/ExRates")
    Observable<NetsisDataHeader> getExRatesRx();

    @GET("/api/v2/ForExs")
    Call<NetsisDataHeader> getForExs();

    @GET("/api/v2/ForExs")
    Observable<NetsisDataHeader> getForExsRx();

    @POST("/api/v2/ItemSlips/IncrementNumber")
    Call<DataResponse<ItemSlip>> getIncrementNumber(@Body ItemSlipParam itemSlipParam);

    @GET("/api/v2/ItemSlips/{id}")
    Observable<DataResponse<ItemSlip>> getItemSlipRx(@Path("id") String str);

    @GET("/api/v2/ItemSlips")
    Observable<DataResponse<List<ItemSlip>>> getItemSlipsByTypeRx(@Query("limit") int i2, @Query("offset") int i3, @Query("docType") int i4, @Query("q") String str);

    @GET("/api/v2/MixedReceiptsMain/{id}")
    Observable<DataResponse<MixedReceiptsMain>> getMixedReceiptsMain(@Path("id") String str);

    @POST("/api/v2/Queries")
    Call<NetsisDataHeader> getQueries(@Body NetsisTSql netsisTSql);

    @FormUrlEncoded
    @POST("/api/v2/Queries")
    Call<NetsisDataHeader> getQueries(@Field("TSql") String str, @Query("limit") int i2, @Query("offset") int i3);

    @POST("/api/v2/Queries")
    Observable<NetsisDataHeader> getQueriesRx(@Body NetsisTSql netsisTSql);

    @GET("/api/v2/SafeDeposits")
    Observable<DataResponse<List<SafeDeposit>>> getSafeDeposit(@Query("q") String str);

    @GET("/api/v2/ItemSlips/{id}")
    Observable<DataResponse<ItemSlip>> getSalesOrderRx(@Path("id") String str);

    @POST("/api/v2/ItemSlips/NewNumber")
    Call<DataResponse<String>> getSlipNewNumber(@Body ItemSlipParam itemSlipParam);

    @POST("/api/v2/ItemSlips/NewNumber")
    Observable<DataResponse<String>> getSlipNewNumberRx(@Body ItemSlipParam itemSlipParam);

    @POST("/api/v2/ARPs")
    Observable<DataResponse<Cari>> postArps(@Body Cari cari);

    @POST("/api/v2/CheckAndPNotesMain")
    Observable<DataResponse<NetsisChequeAndDeedMain>> postChequeAndDeedRx(@Body NetsisChequeAndDeedMain netsisChequeAndDeedMain);

    @POST("/api/v2/EDocument")
    Observable<DataResponse> postEDocument(@Body DraftEDocumentParam draftEDocumentParam);

    @POST("/api/v2/ItemSlips")
    Observable<DataResponse<ItemSlip>> postItemSlipRx(@Body ItemSlip itemSlip);

    @POST("/api/v2/MixedReceiptsMain")
    Observable<DataResponse<MixedReceiptsMain>> postMixedReceiptsMain(@Body MixedReceiptsMain mixedReceiptsMain);

    @POST("/api/v2/SafeDeposits")
    Observable<DataResponse<SafeDeposit>> postSafeDepositRx(@Body SafeDeposit safeDeposit);

    @POST("/api/v2/EDocument/SendEDocument")
    Observable<DataResponse> sendEDocument(@Body SendEDocumentParam sendEDocumentParam);

    @POST("/api/v2/EDocument/ShowEDocument")
    Observable<DataResponse> showEDocument(@Body ShowEDocumentParam showEDocumentParam);
}
