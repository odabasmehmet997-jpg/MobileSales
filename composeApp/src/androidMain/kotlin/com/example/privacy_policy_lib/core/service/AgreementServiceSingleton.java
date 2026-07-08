package com.example.privacy_policy_lib.core.service;

import kotlin.Lazy;
import kotlin.LazyKt;
public final class AgreementServiceSingleton {
    public static final AgreementServiceSingleton INSTANCE = new AgreementServiceSingleton();
    private static final Lazy instancedelegate = LazyKt.lazy(AgreementServicegetCurrentApprovedAgreementContentHashByToken1.INSTANCE);
    private AgreementServiceSingleton() {
    }
    public   AgreementService getInstance() {
        return (AgreementService) instancedelegate.getValue();
    }
}
