package com.example.privacy_policy_lib.core.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

public final class GetAgreementContentResponseBody {
    private AgreementContentResponse contentResponse;
    public GetAgreementContentResponseBody() {
        this(null, 1, null);
    }
    public static GetAgreementContentResponseBody copydefault(GetAgreementContentResponseBody getAgreementContentResponseBody, AgreementContentResponse agreementContentResponse, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            agreementContentResponse = getAgreementContentResponseBody.contentResponse;
        }
        return getAgreementContentResponseBody.copy(agreementContentResponse);
    }
    public AgreementContentResponse component1() {
        return this.contentResponse;
    }
    public GetAgreementContentResponseBody copy(@Namespace(reference = "http://license1.proje.com.tr/ProjeLicenseService/AgreementService") AgreementContentResponse agreementContentResponse) {
        return new GetAgreementContentResponseBody(agreementContentResponse);
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof GetAgreementContentResponseBody) && Intrinsics.areEqual(this.contentResponse, ((GetAgreementContentResponseBody) obj).contentResponse);
    }
    public int hashCode() {
        AgreementContentResponse agreementContentResponse = this.contentResponse;
        if (agreementContentResponse == null) {
            return 0;
        }
        return agreementContentResponse.hashCode();
    }
    public String toString() {
        AgreementContentResponse agreementContentResponse = this.contentResponse;
        return "GetAgreementContentResponseBody(contentResponse=" + agreementContentResponse + ")";
    }
    public GetAgreementContentResponseBody(@Namespace(reference = "http://license1.proje.com.tr/ProjeLicenseService/AgreementService") AgreementContentResponse agreementContentResponse) {
        this.contentResponse = agreementContentResponse;
    }
    public GetAgreementContentResponseBody(AgreementContentResponse agreementContentResponse, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : agreementContentResponse);
    }
    public AgreementContentResponse getContentResponse() {
        return this.contentResponse;
    }
    public void setContentResponse(AgreementContentResponse agreementContentResponse) {
        this.contentResponse = agreementContentResponse;
    }
}
