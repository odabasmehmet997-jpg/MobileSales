package com.example.privacy_policy_lib.core.model;

import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.Element;
 
public final class ApproveAgreementBody { 
    private ApproveAgreementContent approveAgreementContent;

    public static ApproveAgreementBody copydefault(ApproveAgreementBody approveAgreementBody, ApproveAgreementContent approveAgreementContent, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            approveAgreementContent = approveAgreementBody.approveAgreementContent;
        }
        return approveAgreementBody.copy(approveAgreementContent);
    }

    public ApproveAgreementContent component1() {
        return this.approveAgreementContent;
    }

    public ApproveAgreementBody copy(@Element(name = "ApproveAgreementContent") ApproveAgreementContent approveAgreementContent) {
        Intrinsics.checkNotNullParameter(approveAgreementContent, "approveAgreementContent");
        return new ApproveAgreementBody(approveAgreementContent);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ApproveAgreementBody) && Intrinsics.areEqual(this.approveAgreementContent, ((ApproveAgreementBody) obj).approveAgreementContent);
    }

    public int hashCode() {
        return this.approveAgreementContent.hashCode();
    }

    public String toString() {
        ApproveAgreementContent approveAgreementContent = this.approveAgreementContent;
        return "ApproveAgreementBody(approveAgreementContent=" + approveAgreementContent + ")";
    }

    public ApproveAgreementBody(@Element(name = "ApproveAgreementContent") ApproveAgreementContent approveAgreementContent) {
        Intrinsics.checkNotNullParameter(approveAgreementContent, "approveAgreementContent");
        this.approveAgreementContent = approveAgreementContent;
    }

    public ApproveAgreementContent getApproveAgreementContent() {
        return this.approveAgreementContent;
    }

    public void setApproveAgreementContent(ApproveAgreementContent approveAgreementContent) {
        Intrinsics.checkNotNullParameter(approveAgreementContent, "<set-?>");
        this.approveAgreementContent = approveAgreementContent;
    }
}
