package com.example.privacy_policy_lib.core.model;

import com.example.privacy_policy_lib.core.utils.RetrofitServiceFactory;
import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;
 
public final class ApproveAgreementContent { 
    private ApproveAgreementRequest oApproveAgreementRequest;

    public static ApproveAgreementContent copydefault(ApproveAgreementContent approveAgreementContent, ApproveAgreementRequest approveAgreementRequest, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            approveAgreementRequest = approveAgreementContent.oApproveAgreementRequest;
        }
        return approveAgreementContent.copy(approveAgreementRequest);
    }

    public ApproveAgreementRequest component1() {
        return this.oApproveAgreementRequest;
    }

    public ApproveAgreementContent copy(@Element(name = "oApproveAgreementRequest") ApproveAgreementRequest approveAgreementRequest) {
        Intrinsics.checkNotNullParameter(approveAgreementRequest, "oApproveAgreementRequest");
        return new ApproveAgreementContent(approveAgreementRequest);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ApproveAgreementContent) && Intrinsics.areEqual(this.oApproveAgreementRequest, ((ApproveAgreementContent) obj).oApproveAgreementRequest);
    }

    public int hashCode() {
        return this.oApproveAgreementRequest.hashCode();
    }

    public String toString() {
        ApproveAgreementRequest approveAgreementRequest = this.oApproveAgreementRequest;
        return "ApproveAgreementContent(oApproveAgreementRequest=" + approveAgreementRequest + ")";
    }

    public ApproveAgreementContent(@Element(name = "oApproveAgreementRequest") ApproveAgreementRequest approveAgreementRequest) {
        Intrinsics.checkNotNullParameter(approveAgreementRequest, "oApproveAgreementRequest");
        this.oApproveAgreementRequest = approveAgreementRequest;
    }

    public ApproveAgreementRequest getOApproveAgreementRequest() {
        return this.oApproveAgreementRequest;
    }

    public void setOApproveAgreementRequest(ApproveAgreementRequest approveAgreementRequest) {
        Intrinsics.checkNotNullParameter(approveAgreementRequest, "<set-?>");
        this.oApproveAgreementRequest = approveAgreementRequest;
    }
}
