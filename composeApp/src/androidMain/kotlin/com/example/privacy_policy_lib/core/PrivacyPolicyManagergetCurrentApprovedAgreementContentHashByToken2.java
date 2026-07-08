package com.example.privacy_policy_lib.core;

import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByToken;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenBody;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenRequest;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.example.privacy_policy_lib.core.service.AgreementService;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.internal._FileSystemKtcommonListRecursively1;

import static com.example.privacy_policy_lib.core.AgreementTypes.boximpl;
import static com.example.privacy_policy_lib.core.AgreementTypes.unboximpl;

public final class PrivacyPolicyManagergetCurrentApprovedAgreementContentHashByToken2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends GetCurrentApprovedAgreementContentHashByTokenResponse>>, Object> {
    final String agreementToken;
    final boolean isProduction;
    int label;
    public PrivacyPolicyManagergetCurrentApprovedAgreementContentHashByToken2(String str, boolean z, Continuation<? super PrivacyPolicyManagergetCurrentApprovedAgreementContentHashByToken2> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.agreementToken = str;
        this.isProduction = z;
    }  
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new PrivacyPolicyManagergetCurrentApprovedAgreementContentHashByToken2(this.agreementToken, this.isProduction, ( Continuation<? super PrivacyPolicyManagergetCurrentApprovedAgreementContentHashByToken2> ) continuation);
    } 
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return invoke(coroutineScope, continuation);
    }
    public Object invokeSuspend(Object obj) {
        Object obj2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            GetCurrentApprovedAgreementContentHashByTokenRequest getCurrentApprovedAgreementContentHashByTokenRequest = new GetCurrentApprovedAgreementContentHashByTokenRequest(new GetCurrentApprovedAgreementContentHashByTokenBody(new GetCurrentApprovedAgreementContentHashByToken(this.agreementToken)));
            AgreementService agreementService = PrivacyPolicyManager.agreementService;
            boolean z = this.isProduction;
            this.label = 1;
            obj2 = agreementService.getCurrentApprovedAgreementContentHashByToken(z, getCurrentApprovedAgreementContentHashByTokenRequest, this);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 == 1) {
            ResultKt.throwOnFailure(obj);
            obj2 = unboximpl();
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return boximpl(obj2);
    }
    public Object invoke(Object p1, CoroutineContext.Element p2) {
        return null;
    }
}
