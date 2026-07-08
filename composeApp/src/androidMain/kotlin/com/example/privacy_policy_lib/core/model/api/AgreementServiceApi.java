package com.example.privacy_policy_lib.core.model.api;

import com.example.privacy_policy_lib.core.model.ApproveAgreementEnvelope;
import com.example.privacy_policy_lib.core.model.ApproveAgreementResponse;
import com.example.privacy_policy_lib.core.model.GetAgreementContentRequest;
import com.example.privacy_policy_lib.core.model.GetAgreementContentResponse;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenRequest;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import kotlin.coroutines.Continuation;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AgreementServiceApi {
    Companion Companion = AgreementServiceApi.Companion.INSTANCE;
    String REQUEST_URL = "ProjeLicenseService/AgreementService/ProjeAgreementService.asmx";
    String REQUEST_URL_TEST = "AgreementService/ProjeAgreementService.asmx";
    @Headers({"Content-Type: text/xml; charset=utf-8"})
    @POST("ProjeLicenseService/AgreementService/ProjeAgreementService.asmx")
    Call<ApproveAgreementResponse> approveAgreementContent(@Header("SOAPAction") String str,  ApproveAgreementEnvelope approveAgreementEnvelope);
    @Headers({"Content-Type: text/xml; charset=utf-8"})
    @POST("ProjeLicenseService/AgreementService/ProjeAgreementService.asmx")
    Call<GetAgreementContentResponse> getAgreementContent(@Header("SOAPAction") String str,  GetAgreementContentRequest getAgreementContentRequest);
    @Headers({"Content-Type: text/xml; charset=utf-8"})
    @POST("ProjeLicenseService/AgreementService/ProjeAgreementService.asmx")
    Object getCurrentApprovedAgreementContentHashByToken(@Header("SOAPAction") String str,  GetCurrentApprovedAgreementContentHashByTokenRequest getCurrentApprovedAgreementContentHashByTokenRequest, Continuation<? super Response<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation);
    final class Companion {
        static final Companion INSTANCE = new Companion();
        public static final String REQUEST_URL = "ProjeLicenseService/AgreementService/ProjeAgreementService.asmx";
        public static final String REQUEST_URL_TEST = "AgreementService/ProjeAgreementService.asmx";
        private Companion() {
        }
    }
}
