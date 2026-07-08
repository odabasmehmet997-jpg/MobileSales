package com.example.privacy_policy_lib.core.service;

import androidx.core.app.NotificationCompat;
import com.example.privacy_policy_lib.core.model.ApproveAgreementEnvelope;
import com.example.privacy_policy_lib.core.model.ApproveAgreementResponse;
import com.example.privacy_policy_lib.core.model.Body;
import com.example.privacy_policy_lib.core.model.GetAgreementContent;
import com.example.privacy_policy_lib.core.model.GetAgreementContentRequest;
import com.example.privacy_policy_lib.core.model.GetAgreementContentResponse;
import com.example.privacy_policy_lib.core.model.OGetAgreementRequest;
import com.example.privacy_policy_lib.core.model.PrivacyPolicyState;
import com.example.privacy_policy_lib.core.utils.RetrofitServiceFactory;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.io.IOException;

public final class AgreementService {
    public void callGetAgreementContent(int i2, Function1<? super GetAgreementContentResponse, Unit> function1, Function1<? super Throwable, Unit> function12) {
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onFailure");
        RetrofitServiceFactory retrofitServiceFactory = RetrofitServiceFactory.INSTANCE;
        retrofitServiceFactory.createRetrofit(PrivacyPolicyState.getParams().isProduction(), 0, 0).getAgreementContent(retrofitServiceFactory.getSoapAction(PrivacyPolicyState.getParams().isProduction(), RetrofitServiceFactory.GET_AGREEMENT_CONTENT), new GetAgreementContentRequest(new Body(new GetAgreementContent(new OGetAgreementRequest(PrivacyPolicyState.getParams().getContractor(), PrivacyPolicyState.getParams().getItemCode(), PrivacyPolicyState.getParams().getLanguage(), PrivacyPolicyState.getParams().getAgreementTypes().get(i2).toString()))))).enqueue(new Callback<GetAgreementContentResponse>(function1, function12) { // from class: com.example.privacy_policy_lib.core.service.AgreementServicecallGetAgreementContent1
            final Function1<Throwable, Unit> onFailure;
            final Function1<GetAgreementContentResponse, Unit> onSuccess;
            {
                this.onSuccess = (Function1<GetAgreementContentResponse, Unit>) function1;
                this.onFailure = (Function1<Throwable, Unit>) function12;
            }
            public void onResponse(Call<GetAgreementContentResponse> call, Response<GetAgreementContentResponse> response) {
                Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.isSuccessful()) {
                    this.onSuccess.invoke(response.body());
                    return;
                }
                Function1<Throwable, Unit> function13 = this.onFailure;
                ResponseBody errorBody = response.errorBody();
                String string = null;
                try {
                    string = errorBody != null ? errorBody.string() : null;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                function13.invoke(new Exception("Error: " + string));
            }

            public void onFailure(Call<GetAgreementContentResponse> call, Throwable th) {
                Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
                Intrinsics.checkNotNullParameter(th, "t");
                this.onFailure.invoke(th);
            }
        });
    }
    public void callApproveAgreementContent(boolean z, ApproveAgreementEnvelope approveAgreementEnvelope, Function1<? super ApproveAgreementResponse, Unit> function1, Function1<? super Throwable, Unit> function12) {
        Intrinsics.checkNotNullParameter(approveAgreementEnvelope, "request");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onFailure");
        RetrofitServiceFactory retrofitServiceFactory = RetrofitServiceFactory.INSTANCE;
        retrofitServiceFactory.createRetrofit(z, 0, 0).approveAgreementContent(retrofitServiceFactory.getSoapAction(z, RetrofitServiceFactory.APPROVE_AGREEMENT_CONTENT), approveAgreementEnvelope).enqueue(new Callback<ApproveAgreementResponse>(function1, function12) { // from class: com.example.privacy_policy_lib.core.service.AgreementServicecallApproveAgreementContent1
            final Function1<Throwable, Unit> onFailure = (Function1<Throwable, Unit>) function12;
            final Function1<ApproveAgreementResponse, Unit> onSuccess = (Function1<ApproveAgreementResponse, Unit>) function1;

            public void onResponse(Call<ApproveAgreementResponse> call, Response<ApproveAgreementResponse> response) {
                Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.isSuccessful()) {
                    this.onSuccess.invoke(response.body());
                    return;
                }
                Function1<Throwable, Unit> function13 = this.onFailure;
                ResponseBody errorBody = response.errorBody();
                String string = null;
                try {
                    string = errorBody != null ? errorBody.string() : null;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                function13.invoke(new Exception("Error: " + string));
            }
            public void onFailure(Call<ApproveAgreementResponse> call, Throwable th) {
                Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
                Intrinsics.checkNotNullParameter(th, "t");
                this.onFailure.invoke(th);
            }
        });
    }
    public Object getCurrentApprovedAgreementContentHashByToken(boolean r6, com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenRequest r7, kotlin.coroutines.Continuation<? super kotlin.Result<com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse>> r8) {
        throw new UnsupportedOperationException("Method not decompiled: com.example.privacy_policy_lib.core.service.AgreementService.getCurrentApprovedAgreementContentHashByToken(boolean, com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }
    public Object getCurrentApprovedAgreementContentHashByToken(boolean b, Object o, AgreementServicegetCurrentApprovedAgreementContentHashByToken1 agreementServicegetCurrentApprovedAgreementContentHashByToken1) {
        return o;
    }
}
