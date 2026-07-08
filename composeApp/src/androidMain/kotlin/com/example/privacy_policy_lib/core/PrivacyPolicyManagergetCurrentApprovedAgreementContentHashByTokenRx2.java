package com.example.privacy_policy_lib.core;

import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class PrivacyPolicyManagergetCurrentApprovedAgreementContentHashByTokenRx2 extends Lambda implements Function1<Throwable, SingleSource<? extends GetCurrentApprovedAgreementContentHashByTokenResponse>> {
    public static final PrivacyPolicyManagergetCurrentApprovedAgreementContentHashByTokenRx2 INSTANCE = new PrivacyPolicyManagergetCurrentApprovedAgreementContentHashByTokenRx2();
    PrivacyPolicyManagergetCurrentApprovedAgreementContentHashByTokenRx2() {
        super(1);
    }
    public SingleSource<? extends GetCurrentApprovedAgreementContentHashByTokenResponse> invoke(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "throwable");
        String message = th.getMessage();
        return Single.error(new Exception("API çağrısı başarısız oldu: " + message));
    }
    public SingleSource<? extends GetCurrentApprovedAgreementContentHashByTokenResponse> invoke(Object p1) {
        return null;
    }
}
