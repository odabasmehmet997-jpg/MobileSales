package com.example.privacy_policy_lib.core;

import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.internal._FileSystemKtcommonListRecursively1;

public final class PrivacyPolicyManagergetCurrentApprovedAgreementContentHashByTokenRx11 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super GetCurrentApprovedAgreementContentHashByTokenResponse>, Object> {
    final String agreementToken;
    final boolean isProduction;
    int label;
    public PrivacyPolicyManagergetCurrentApprovedAgreementContentHashByTokenRx11(boolean z, String str, Continuation<? super PrivacyPolicyManagergetCurrentApprovedAgreementContentHashByTokenRx11> continuation) {
        super(2, ( Continuation<Object> ) continuation);
        this.isProduction = z;
        this.agreementToken = str;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new PrivacyPolicyManagergetCurrentApprovedAgreementContentHashByTokenRx11(this.isProduction, this.agreementToken, ( Continuation<? super PrivacyPolicyManagergetCurrentApprovedAgreementContentHashByTokenRx11> ) continuation);
    }
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return ((PrivacyPolicyManagergetCurrentApprovedAgreementContentHashByTokenRx11) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
    public Object invokeSuspend(Object obj) {
        Object obj2 = null;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            boolean z = this.isProduction;
            String str = this.agreementToken;
            this.label = 1;
            obj2 = PrivacyPolicyManager.m131getCurrentApprovedAgreementContentHashByToken0E7RQCE(z, str, this);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 == 1) {
            ResultKt.throwOnFailure(obj);
            obj2 = AgreementTypes.boximpl(obj2);

        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj2);
        GetCurrentApprovedAgreementContentHashByTokenResponse getCurrentApprovedAgreementContentHashByTokenResponse = (GetCurrentApprovedAgreementContentHashByTokenResponse) obj2;
        if (getCurrentApprovedAgreementContentHashByTokenResponse != null) {
            return getCurrentApprovedAgreementContentHashByTokenResponse;
        }
        throw new NullPointerException("API yanıtı null döndü!");
    }
}
