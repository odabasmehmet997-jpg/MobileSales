package com.example.privacy_policy_lib.core.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class GetCurrentApprovedAgreementContentHashByTokenResponse {
    private GetCurrentApprovedAgreementContentHashByTokenResponseBody body;
    public GetCurrentApprovedAgreementContentHashByTokenResponse() {
        this(null, 1, null);
    }
    public static GetCurrentApprovedAgreementContentHashByTokenResponse copydefault(GetCurrentApprovedAgreementContentHashByTokenResponse getCurrentApprovedAgreementContentHashByTokenResponse, GetCurrentApprovedAgreementContentHashByTokenResponseBody getCurrentApprovedAgreementContentHashByTokenResponseBody, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            getCurrentApprovedAgreementContentHashByTokenResponseBody = getCurrentApprovedAgreementContentHashByTokenResponse.body;
        }
        return getCurrentApprovedAgreementContentHashByTokenResponse.copy(getCurrentApprovedAgreementContentHashByTokenResponseBody);
    }
    public GetCurrentApprovedAgreementContentHashByTokenResponseBody component1() {
        return this.body;
    }
    public GetCurrentApprovedAgreementContentHashByTokenResponse copy(GetCurrentApprovedAgreementContentHashByTokenResponseBody getCurrentApprovedAgreementContentHashByTokenResponseBody) {
        return new GetCurrentApprovedAgreementContentHashByTokenResponse(getCurrentApprovedAgreementContentHashByTokenResponseBody);
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof GetCurrentApprovedAgreementContentHashByTokenResponse) && Intrinsics.areEqual(this.body, ((GetCurrentApprovedAgreementContentHashByTokenResponse) obj).body);
    }
    public int hashCode() {
        GetCurrentApprovedAgreementContentHashByTokenResponseBody getCurrentApprovedAgreementContentHashByTokenResponseBody = this.body;
        return getCurrentApprovedAgreementContentHashByTokenResponseBody != null ? getCurrentApprovedAgreementContentHashByTokenResponseBody.hashCode() : 0;
    }
    public String toString() {
        GetCurrentApprovedAgreementContentHashByTokenResponseBody getCurrentApprovedAgreementContentHashByTokenResponseBody = this.body;
        return "GetCurrentApprovedAgreementContentHashByTokenResponse(body=" + getCurrentApprovedAgreementContentHashByTokenResponseBody + ")";
    }
    public GetCurrentApprovedAgreementContentHashByTokenResponse(GetCurrentApprovedAgreementContentHashByTokenResponseBody getCurrentApprovedAgreementContentHashByTokenResponseBody) {
        this.body = getCurrentApprovedAgreementContentHashByTokenResponseBody;
    }
    public  GetCurrentApprovedAgreementContentHashByTokenResponse(GetCurrentApprovedAgreementContentHashByTokenResponseBody getCurrentApprovedAgreementContentHashByTokenResponseBody, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : getCurrentApprovedAgreementContentHashByTokenResponseBody);
    }
    public GetCurrentApprovedAgreementContentHashByTokenResponseBody getBody() {
        return this.body;
    }
    public void setBody(GetCurrentApprovedAgreementContentHashByTokenResponseBody getCurrentApprovedAgreementContentHashByTokenResponseBody) {
        this.body = getCurrentApprovedAgreementContentHashByTokenResponseBody;
    }
}
