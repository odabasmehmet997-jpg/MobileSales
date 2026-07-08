package com.example.privacy_policy_lib.core.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/* compiled from: ApproveAgreementResponse.kt */
@Root(name = "Body", strict = false)
/* loaded from: classes.dex */
public final class ApproveAgreementResponseBody {
    @Element(name = "ApproveAgreementContentResponse", required = false)
    private ApproveAgreementContentResponse approveAgreementContentResponse;

    public ApproveAgreementResponseBody() {
        this(null, 1, null);
    }

    public static ApproveAgreementResponseBody copydefault(ApproveAgreementResponseBody approveAgreementResponseBody, ApproveAgreementContentResponse approveAgreementContentResponse, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            approveAgreementContentResponse = approveAgreementResponseBody.approveAgreementContentResponse;
        }
        return approveAgreementResponseBody.copy(approveAgreementContentResponse);
    }

    public ApproveAgreementContentResponse component1() {
        return this.approveAgreementContentResponse;
    }

    public ApproveAgreementResponseBody copy(@Namespace(reference = "http://license1.logo.com.tr/LogoLicenseService/AgreementService") ApproveAgreementContentResponse approveAgreementContentResponse) {
        return new ApproveAgreementResponseBody(approveAgreementContentResponse);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ApproveAgreementResponseBody) && Intrinsics.areEqual(this.approveAgreementContentResponse, ((ApproveAgreementResponseBody) obj).approveAgreementContentResponse);
    }

    public int hashCode() {
        ApproveAgreementContentResponse approveAgreementContentResponse = this.approveAgreementContentResponse;
        if (approveAgreementContentResponse == null) {
            return 0;
        }
        return approveAgreementContentResponse.hashCode();
    }

    public String toString() {
        ApproveAgreementContentResponse approveAgreementContentResponse = this.approveAgreementContentResponse;
        return "ApproveAgreementResponseBody(approveAgreementContentResponse=" + approveAgreementContentResponse + ")";
    }

    public ApproveAgreementResponseBody(@Namespace(reference = "http://license1.logo.com.tr/LogoLicenseService/AgreementService") ApproveAgreementContentResponse approveAgreementContentResponse) {
        this.approveAgreementContentResponse = approveAgreementContentResponse;
    }

    public ApproveAgreementResponseBody(ApproveAgreementContentResponse approveAgreementContentResponse, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : approveAgreementContentResponse);
    }

    public ApproveAgreementContentResponse getApproveAgreementContentResponse() {
        return this.approveAgreementContentResponse;
    }

    public void setApproveAgreementContentResponse(ApproveAgreementContentResponse approveAgreementContentResponse) {
        this.approveAgreementContentResponse = approveAgreementContentResponse;
    }
}
