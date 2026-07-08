package com.example.privacy_policy_lib.core.model;

import kotlin.jvm.internal.Intrinsics;
 
public final class PrivacyPolicyState {
    public static final String IS_APPROVED = "is_approved";
    public static final String PARAMS_TO_GET_FROM_APP = "privacyPolicyParamsFromApp";
    public static final String POSITION = "position";
    public static final String PRIVACY_POLICY = "privacy_policy";
    public static final PrivacyPolicyState INSTANCE = new PrivacyPolicyState();
    private static PrivacyPolicyLibParams params = new PrivacyPolicyLibParams(false, null, null, null, null, null, null, null, null, null, null, null, 4095, null);
    public static  void getParamsannotations() {
    }
    private PrivacyPolicyState() {
    }
    public static PrivacyPolicyLibParams getParams() {
        return params;
    }
    public static void setParams(PrivacyPolicyLibParams privacyPolicyLibParams) {
        Intrinsics.checkNotNullParameter(privacyPolicyLibParams, "<set-?>");
        params = privacyPolicyLibParams;
    }
}
