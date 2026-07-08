package com.example.privacy_policy_lib.core.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;
 
public final class ApproveAgreementContentResponse {
    private ApproveAgreementContentResult approveAgreementContentResult;

    public ApproveAgreementContentResponse() {
        this(null, 1, null);
    }

    public static ApproveAgreementContentResponse copydefault(ApproveAgreementContentResponse approveAgreementContentResponse, ApproveAgreementContentResult approveAgreementContentResult, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            approveAgreementContentResult = approveAgreementContentResponse.approveAgreementContentResult;
        }
        return approveAgreementContentResponse.copy(approveAgreementContentResult);
    }

    public ApproveAgreementContentResult component1() {
        return this.approveAgreementContentResult;
    }

    public ApproveAgreementContentResponse copy(ApproveAgreementContentResult approveAgreementContentResult) {
        return new ApproveAgreementContentResponse(approveAgreementContentResult);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ApproveAgreementContentResponse) && Intrinsics.areEqual(this.approveAgreementContentResult, ((ApproveAgreementContentResponse) obj).approveAgreementContentResult);
    }

    public int hashCode() {
        ApproveAgreementContentResult approveAgreementContentResult = this.approveAgreementContentResult;
        if (approveAgreementContentResult == null) {
            return 0;
        }
        return approveAgreementContentResult.hashCode();
    }

    public String toString() {
        ApproveAgreementContentResult approveAgreementContentResult = this.approveAgreementContentResult;
        return "ApproveAgreementContentResponse(approveAgreementContentResult=" + approveAgreementContentResult + ")";
    }

    public ApproveAgreementContentResponse(ApproveAgreementContentResult approveAgreementContentResult) {
        this.approveAgreementContentResult = approveAgreementContentResult;
    }

    public ApproveAgreementContentResponse(ApproveAgreementContentResult approveAgreementContentResult, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : approveAgreementContentResult);
    }

    public ApproveAgreementContentResult getApproveAgreementContentResult() {
        return this.approveAgreementContentResult;
    }

    public void setApproveAgreementContentResult(ApproveAgreementContentResult approveAgreementContentResult) {
        this.approveAgreementContentResult = approveAgreementContentResult;
    }
}
