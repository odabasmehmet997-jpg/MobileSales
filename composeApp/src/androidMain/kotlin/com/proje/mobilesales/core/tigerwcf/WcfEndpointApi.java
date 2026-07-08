package com.proje.mobilesales.core.tigerwcf;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;



public interface WcfEndpointApi {
    @Headers({"Content-Type: text/xml;charset=utf-8", "Accept-Encoding: gzip", "Connection: close", "SOAPAction: http://tempuri.org/ISvc/AppendDataObject"})
    @POST("/LogoObjectService/Service")
    Call<ResponseEnvelope> append(@Body RequestEnvelope requestEnvelope);

    @Headers({"Content-Type: text/xml;charset=utf-8", "Accept-Encoding: gzip", "Connection: close", "SOAPAction: http://tempuri.org/ISvc/AppendDataObject"})
    @POST("/LogoObjectService/Service")
    Observable<Response<ResponseEnvelope>> appendRx(@Body RequestEnvelope requestEnvelope);

    @Headers({"Content-Type: text/xml;charset=utf-8", "Accept-Encoding: gzip", "Connection: close", "SOAPAction: http://tempuri.org/ISvc/CalculateDataObject"})
    @POST("/LogoObjectService/Service")
    Call<ResponseEnvelope> calculate(@Body RequestEnvelope requestEnvelope);

    @Headers({"Content-Type: text/xml;charset=utf-8", "Accept-Encoding: gzip", "Connection: close", "SOAPAction: http://tempuri.org/ISvc/CalculateDataObject"})
    @POST("/LogoObjectService/Service")
    Observable<Response<ResponseEnvelope>> calculateRx(@Body RequestEnvelope requestEnvelope);

    @Headers({"Content-Type: text/xml;charset=utf-8", "Accept-Encoding: gzip", "Connection: close", "SOAPAction: http://tempuri.org/ISvc/DeleteDataObject"})
    @POST("/LogoObjectService/Service")
    Call<ResponseEnvelope> delete(@Body RequestEnvelope requestEnvelope);

    @Headers({"Content-Type: text/xml;charset=utf-8", "Accept-Encoding: gzip", "Connection: close", "SOAPAction: http://tempuri.org/ISvc/DirectQuery"})
    @POST("/LogoObjectService/Service")
    Call<ResponseEnvelope> directQuery(@Body RequestEnvelope requestEnvelope);

    @Headers({"Content-Type: text/xml;charset=utf-8", "Accept-Encoding: gzip", "Connection: close", "SOAPAction: http://tempuri.org/ISvc/DirectQuery"})
    @POST("/LogoObjectService/Service")
    Observable<Response<ResponseEnvelope>> directQueryRx(@Body RequestEnvelope requestEnvelope);

    @Headers({"Content-Type: text/xml;charset=utf-8", "Accept-Encoding: gzip", "Connection: close", "SOAPAction: http://tempuri.org/ISvc/ExecQuery"})
    @POST("/LogoObjectService/Service")
    Call<ResponseEnvelope> execQuery(@Body RequestEnvelope requestEnvelope);

    @Headers({"Content-Type: text/xml;charset=utf-8", "Accept-Encoding: gzip", "Connection: close", "SOAPAction: http://tempuri.org/ISvc/ExecQuery"})
    @POST("/LogoObjectService/Service")
    Observable<Response<ResponseEnvelope>> execQueryRx(@Body RequestEnvelope requestEnvelope);

    @Headers({"Content-Type: text/xml;charset=utf-8", "Accept-Encoding: gzip", "Connection: close", "SOAPAction: http://tempuri.org/ISvc/ExecSP"})
    @POST("/LogoObjectService/Service")
    Call<ResponseEnvelope> execSp(@Body RequestEnvelope requestEnvelope);

    @Headers({"Content-Type: text/xml;charset=utf-8", "Accept-Encoding: gzip", "Connection: close", "SOAPAction: http://tempuri.org/ISvc/ExecSP"})
    @POST("/LogoObjectService/Service")
    Observable<Response<ResponseEnvelope>> execSpRx(@Body RequestEnvelope requestEnvelope);

    @Headers({"Content-Type: text/xml;charset=utf-8", "Accept-Encoding: gzip", "Connection: close", "SOAPAction: http://tempuri.org/ISvc/GetEDocumentContent"})
    @POST("/LogoObjectService/Service")
    Observable<Response<ResponseEnvelope>> getEdocumentContentRx(@Body RequestEnvelope requestEnvelope);

    @Headers({"Content-Type: text/xml;charset=utf-8", "Accept-Encoding: gzip", "Connection: close", "SOAPAction: http://tempuri.org/ISvc/getInfo"})
    @POST("/LogoObjectService/Service")
    Call<ResponseEnvelope> getInfoQuery(@Body RequestEnvelope requestEnvelope);

    @Headers({"Content-Type: text/xml;charset=utf-8", "Accept-Encoding: gzip", "Connection: close", "SOAPAction: http://tempuri.org/ISvc/UsableCampaignCards"})
    @POST("/LogoObjectService/Service")
    Observable<Response<ResponseEnvelope>> getUsableCampaignCardsRx(@Body RequestEnvelope requestEnvelope);

    @Headers({"Content-Type: text/xml;charset=utf-8", "Accept-Encoding: gzip", "Connection: close", "SOAPAction: http://tempuri.org/ISvc/getValue"})
    @POST("/LogoObjectService/Service")
    Call<ResponseEnvelope> getValueQuery(@Body RequestEnvelope requestEnvelope);

    @Headers({"Content-Type: text/xml;charset=utf-8", "Accept-Encoding: gzip", "Connection: close", "SOAPAction: http://tempuri.org/ISvc/ReadDataObject"})
    @POST("/LogoObjectService/Service")
    Call<ResponseEnvelope> read(@Body RequestEnvelope requestEnvelope);

    @Headers({"Content-Type: text/xml;charset=utf-8", "Accept-Encoding: gzip", "Connection: close", "SOAPAction: http://tempuri.org/ISvc/ReadDataObject"})
    @POST("/LogoObjectService/Service")
    Observable<Response<ResponseEnvelope>> readRx(@Body RequestEnvelope requestEnvelope);

    @Headers({"Content-Type: text/xml;charset=utf-8", "Accept-Encoding: gzip", "Connection: close", "SOAPAction: http://tempuri.org/ISvc/SendEArchiveDocuments"})
    @POST("/LogoObjectService/Service")
    Observable<Response<ResponseEnvelope>> sendEArchiveDocumentsRx(@Body RequestEnvelope requestEnvelope);

    @Headers({"Content-Type: text/xml;charset=utf-8", "Accept-Encoding: gzip", "Connection: close", "SOAPAction: http://tempuri.org/ISvc/SendRecvEDispatchDocuments"})
    @POST("/LogoObjectService/Service")
    Observable<Response<ResponseEnvelope>> sendRecvEDispatchDocumentsRx(@Body RequestEnvelope requestEnvelope);

    @Headers({"Content-Type: text/xml;charset=utf-8", "Accept-Encoding: gzip", "Connection: close", "SOAPAction: http://tempuri.org/ISvc/SendRecvEInvoiceDocuments"})
    @POST("/LogoObjectService/Service")
    Observable<Response<ResponseEnvelope>> sendRecvEInvoiceDocumentsRx(@Body RequestEnvelope requestEnvelope);
}
