package com.example.privacy_policy_lib.core.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.ksoap2.SoapEnvelope;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

public final class GetAgreementContentResponse {
    private GetAgreementContentResponseBody body;
    public GetAgreementContentResponse() {
        this(null, 1, null);
    }
    public static  GetAgreementContentResponse copydefault(GetAgreementContentResponse getAgreementContentResponse, GetAgreementContentResponseBody getAgreementContentResponseBody, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            getAgreementContentResponseBody = getAgreementContentResponse.body;
        }
        return getAgreementContentResponse.copy(getAgreementContentResponseBody);
    }
    public GetAgreementContentResponseBody component1() {
        return this.body;
    }
    public GetAgreementContentResponse copy(GetAgreementContentResponseBody getAgreementContentResponseBody) {
        return new GetAgreementContentResponse(getAgreementContentResponseBody);
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof GetAgreementContentResponse) && Intrinsics.areEqual(this.body, ((GetAgreementContentResponse) obj).body);
    }
    public int hashCode() {
        GetAgreementContentResponseBody getAgreementContentResponseBody = this.body;
        return getAgreementContentResponseBody != null ? getAgreementContentResponseBody.hashCode() : 0;
    }
    public String toString() {
        GetAgreementContentResponseBody getAgreementContentResponseBody = this.body;
        return "GetAgreementContentResponse(body=" + getAgreementContentResponseBody + ")";
    }
    public GetAgreementContentResponse(GetAgreementContentResponseBody getAgreementContentResponseBody) {
        this.body = getAgreementContentResponseBody;
    }
    public GetAgreementContentResponse(GetAgreementContentResponseBody getAgreementContentResponseBody, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : getAgreementContentResponseBody);
    }
    public GetAgreementContentResponseBody getBody() {
        return this.body;
    }
    public void setBody(GetAgreementContentResponseBody getAgreementContentResponseBody) {
        this.body = getAgreementContentResponseBody;
    }
}
