package com.example.privacy_policy_lib.core.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

public final class GetCurrentApprovedAgreementContentHashByTokenResponseBody {
    @Element(name = "GetCurrentApprovedAgreementContentHashByTokenResponse", required = false)
    private CurrentApprovedAgreementContentResponse contentResponse;
    public GetCurrentApprovedAgreementContentHashByTokenResponseBody() {
        this(null, 1, null);
    }
    public static GetCurrentApprovedAgreementContentHashByTokenResponseBody copydefault(GetCurrentApprovedAgreementContentHashByTokenResponseBody getCurrentApprovedAgreementContentHashByTokenResponseBody, CurrentApprovedAgreementContentResponse currentApprovedAgreementContentResponse, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            currentApprovedAgreementContentResponse = getCurrentApprovedAgreementContentHashByTokenResponseBody.contentResponse;
        }
        return getCurrentApprovedAgreementContentHashByTokenResponseBody.copy(currentApprovedAgreementContentResponse);
    }
    public CurrentApprovedAgreementContentResponse component1() {
        return this.contentResponse;
    }
    public GetCurrentApprovedAgreementContentHashByTokenResponseBody copy(@Namespace(reference = "http://license1.proje.com.tr/LicenseService/AgreementService") CurrentApprovedAgreementContentResponse currentApprovedAgreementContentResponse) {
        return new GetCurrentApprovedAgreementContentHashByTokenResponseBody(currentApprovedAgreementContentResponse);
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof GetCurrentApprovedAgreementContentHashByTokenResponseBody) && Intrinsics.areEqual(this.contentResponse, ((GetCurrentApprovedAgreementContentHashByTokenResponseBody) obj).contentResponse);
    }
    public int hashCode() {
        CurrentApprovedAgreementContentResponse currentApprovedAgreementContentResponse = this.contentResponse;
        return currentApprovedAgreementContentResponse != null ? currentApprovedAgreementContentResponse.hashCode() : 0;
    }

    public String toString() {
        CurrentApprovedAgreementContentResponse currentApprovedAgreementContentResponse = this.contentResponse;
        return "GetCurrentApprovedAgreementContentHashByTokenResponseBody(contentResponse=" + currentApprovedAgreementContentResponse + ")";
    }
    public GetCurrentApprovedAgreementContentHashByTokenResponseBody(@Namespace(reference = "http://license1.proje.com.tr/LicenseService/AgreementService") CurrentApprovedAgreementContentResponse currentApprovedAgreementContentResponse) {
        this.contentResponse = currentApprovedAgreementContentResponse;
    }

    public  GetCurrentApprovedAgreementContentHashByTokenResponseBody(CurrentApprovedAgreementContentResponse currentApprovedAgreementContentResponse, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : currentApprovedAgreementContentResponse);
    }

    public CurrentApprovedAgreementContentResponse getContentResponse() {
        return this.contentResponse;
    }

    public void setContentResponse(CurrentApprovedAgreementContentResponse currentApprovedAgreementContentResponse) {
        this.contentResponse = currentApprovedAgreementContentResponse;
    }
}
