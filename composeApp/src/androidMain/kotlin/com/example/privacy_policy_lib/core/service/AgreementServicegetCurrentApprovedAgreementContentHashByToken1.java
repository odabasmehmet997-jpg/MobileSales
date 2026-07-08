package com.example.privacy_policy_lib.core.service;

import com.example.privacy_policy_lib.core.AgreementTypes;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;

import java.util.Optional;

final class AgreementServicegetCurrentApprovedAgreementContentHashByToken1 extends ContinuationImpl {
    public static Function0<?> INSTANCE;
    int label;
     Object result;
    final   AgreementService this0;
    AgreementServicegetCurrentApprovedAgreementContentHashByToken1(AgreementService agreementService, Continuation<? super AgreementServicegetCurrentApprovedAgreementContentHashByToken1> continuation) {
        super((Continuation<Object>) continuation);
        this.this0 = agreementService;
    }
    public Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object getCurrentApprovedAgreementContentHashByToken = this.this0.getCurrentApprovedAgreementContentHashByToken(false, Optional.ofNullable(null), this);
        return getCurrentApprovedAgreementContentHashByToken == IntrinsicsKt.getCOROUTINE_SUSPENDED() ?
                getCurrentApprovedAgreementContentHashByToken : AgreementTypes.boximpl(getCurrentApprovedAgreementContentHashByToken);
    }
}
