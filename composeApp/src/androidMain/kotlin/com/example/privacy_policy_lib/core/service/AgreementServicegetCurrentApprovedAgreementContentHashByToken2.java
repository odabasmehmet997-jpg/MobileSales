package com.example.privacy_policy_lib.core.service;

import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenRequest;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.example.privacy_policy_lib.core.model.api.AgreementServiceApi;
import com.example.privacy_policy_lib.core.utils.RetrofitServiceFactory;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.ResponseBody;
import okio.internal._FileSystemKtcommonListRecursively1;
import retrofit2.Response;

final class AgreementServicegetCurrentApprovedAgreementContentHashByToken2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends GetCurrentApprovedAgreementContentHashByTokenResponse>>, Object> {
    final   boolean isProduction;
    final   GetCurrentApprovedAgreementContentHashByTokenRequest request;
    int label;

    AgreementServicegetCurrentApprovedAgreementContentHashByToken2(boolean z, GetCurrentApprovedAgreementContentHashByTokenRequest getCurrentApprovedAgreementContentHashByTokenRequest, Continuation<? super AgreementServicegetCurrentApprovedAgreementContentHashByToken2> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.isProduction = z;
        this.request = getCurrentApprovedAgreementContentHashByTokenRequest;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new AgreementServicegetCurrentApprovedAgreementContentHashByToken2(this.isProduction, this.request, ( Continuation<? super AgreementServicegetCurrentApprovedAgreementContentHashByToken2> ) continuation);
    }
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return invoke2(coroutineScope, continuation);
    }
    public Object invoke2(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
    public Object invokeSuspend(Object obj) {
        Object  constructorimpl;
        Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        try {
            if (r1 == 0) {
                ResultKt.throwOnFailure(obj);
                RetrofitServiceFactory retrofitServiceFactory = RetrofitServiceFactory.INSTANCE;
                AgreementServiceApi agreementServiceApiCreateRetrofitdefault = RetrofitServiceFactory.createRetrofitdefault(retrofitServiceFactory, this.isProduction, 0L, 0L, 6, null);
                String soapAction = retrofitServiceFactory.getSoapAction(this.isProduction, RetrofitServiceFactory.GET_CURRENT_APPROVED_AGREEMENT_CONTENT_HASH_BY_TOKEN);
                GetCurrentApprovedAgreementContentHashByTokenRequest getCurrentApprovedAgreementContentHashByTokenRequest = this.request;
                this.label = 1;
                obj = agreementServiceApiCreateRetrofitdefault.getCurrentApprovedAgreementContentHashByToken(soapAction, getCurrentApprovedAgreementContentHashByTokenRequest, this);
                if (obj == obj2) {
                    return obj2;
                }
            } else {
                if (r1 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            Response response = (Response) obj;
            Result.Companion companion = Result.Companion;
            if (response.isSuccessful()) {
                
                constructorimpl =  constructorimpl(response.body());
            } else {
                ResponseBody responseBodyErrorBody = response.errorBody();
                constructorimpl = constructorimpl(ResultKt.createFailure(new Exception("Error: "
                        + (responseBodyErrorBody != null ? responseBodyErrorBody.string() : null))));
            }
        } catch (Exception e2) {
            Result.Companion companion3 = Result.Companion;
            constructorimpl = constructorimpl(ResultKt.createFailure(e2));
        }
        return constructorimpl(constructorimpl);
    }

    private Object constructorimpl(Object body) {
        return body;
    }

    private Object impl(Object failure) {
        return failure;
    }
}
