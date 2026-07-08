package com.example.privacy_policy_lib;

import android.os.Bundle;
import com.example.privacy_policy_lib.core.model.PrivacyPolicyLibParams;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
 
public final class PrivacyPolicyBuilder {
    public static final PrivacyPolicyBuilder INSTANCE = new PrivacyPolicyBuilder();

    private PrivacyPolicyBuilder() {
    }

    public static ContractsFragment createContractsFragmentdefault(PrivacyPolicyLibParams privacyPolicyLibParams, Function0 function0, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            privacyPolicyLibParams = null;
        }
        if ((i2 & 2) != 0) {
            function0 = null;
        }
        return createContractsFragment(privacyPolicyLibParams, function0);
    }

    public static ContractsFragment createContractsFragment(PrivacyPolicyLibParams privacyPolicyLibParams, Function0<Unit> function0) {
        ContractsFragment contractsFragment = new ContractsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("params", privacyPolicyLibParams);
        contractsFragment.setArguments(bundle);
        contractsFragment.setOnPrivacyPolicyAccepted(function0);
        return contractsFragment;
    }
 
    public static Object m126getCurrentApprovedAgreementContent0E7RQCE(boolean r4, String r5, kotlin.coroutines.Continuation<? super kotlin.Result<com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse>> r6) {
        throw new UnsupportedOperationException("Method not decompiled: com.example.privacy_policy_lib.PrivacyPolicyBuilder.m126getCurrentApprovedAgreementContent0E7RQCE(boolean, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
