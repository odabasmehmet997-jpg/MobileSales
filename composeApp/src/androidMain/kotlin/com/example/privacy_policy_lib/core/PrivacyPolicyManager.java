package com.example.privacy_policy_lib.core;

import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.example.privacy_policy_lib.core.service.AgreementService;
import com.example.privacy_policy_lib.core.service.AgreementServiceSingleton;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Callable;

import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;

public final class PrivacyPolicyManager {
    public static final PrivacyPolicyManager INSTANCE = new PrivacyPolicyManager();
    static final AgreementService agreementService = AgreementServiceSingleton.INSTANCE.getInstance();
    private PrivacyPolicyManager() {
    } 
    public static Object m131getCurrentApprovedAgreementContentHashByToken0E7RQCE(boolean r5, String r6, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> r7) {
        throw new UnsupportedOperationException("Method not decompiled: com.example.privacy_policy_lib.core.PrivacyPolicyManager.m131getCurrentApprovedAgreementContentHashByToken0E7RQCE(boolean, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
    public static Single<GetCurrentApprovedAgreementContentHashByTokenResponse> getCurrentApprovedAgreementContentHashByTokenRx(boolean z, String str) {
        Intrinsics.checkNotNullParameter(str, "agreementToken");
        Single<GetCurrentApprovedAgreementContentHashByTokenResponse> onErrorResumeNext = Single.fromCallable(new Callable(z, str) {
            public final boolean f0 = false;
            public final String f1 = "";

            public Object call() {
                return PrivacyPolicyManager.getCurrentApprovedAgreementContentHashByTokenRxlambda0(this.f0, this.f1);
            }
        }).subscribeOn(Schedulers.io()).onErrorResumeNext(new Function() {
            public Object apply(Object obj) {
                return PrivacyPolicyManager.getCurrentApprovedAgreementContentHashByTokenRxlambda1(this     , obj);
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        });
        Intrinsics.checkNotNullExpressionValue(onErrorResumeNext, "onErrorResumeNext(...)");
        return onErrorResumeNext;
    }
    public static GetCurrentApprovedAgreementContentHashByTokenResponse getCurrentApprovedAgreementContentHashByTokenRxlambda0(boolean z, String str) {
        Intrinsics.checkNotNullParameter(str, "agreementToken");
        try {
            return BuildersKt.runBlocking(null, new PrivacyPolicyManagergetCurrentApprovedAgreementContentHashByTokenRx11(z, str, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static SingleSource getCurrentApprovedAgreementContentHashByTokenRxlambda1(Function function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "tmp0");
        return (SingleSource) function1.invoke(obj);
    }
}
