package com.example.privacy_policy_lib.core.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

public final class CurrentApprovedAgreementContentResponse {
    @Element(name = "GetCurrentApprovedAgreementContentHashByTokenResult", required = false)
    private CurrentApprovedAgreementContentResult result;
    public CurrentApprovedAgreementContentResponse() {
        this(null, 1, null);
    }
    public static   CurrentApprovedAgreementContentResponse copydefault(CurrentApprovedAgreementContentResponse currentApprovedAgreementContentResponse, CurrentApprovedAgreementContentResult currentApprovedAgreementContentResult, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            currentApprovedAgreementContentResult = currentApprovedAgreementContentResponse.result;
        }
        return currentApprovedAgreementContentResponse.copy(currentApprovedAgreementContentResult);
    }
    public CurrentApprovedAgreementContentResult component1() {
        return this.result;
    }
    public CurrentApprovedAgreementContentResponse copy(CurrentApprovedAgreementContentResult currentApprovedAgreementContentResult) {
        return new CurrentApprovedAgreementContentResponse(currentApprovedAgreementContentResult);
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof CurrentApprovedAgreementContentResponse) && Intrinsics.areEqual(this.result, ((CurrentApprovedAgreementContentResponse) obj).result);
    }
    public int hashCode() {
        CurrentApprovedAgreementContentResult currentApprovedAgreementContentResult = this.result;
        if (currentApprovedAgreementContentResult == null) {
            return 0;
        }
        return currentApprovedAgreementContentResult.hashCode();
    }
    public String toString() {
        CurrentApprovedAgreementContentResult currentApprovedAgreementContentResult = this.result;
        return "CurrentApprovedAgreementContentResponse(result=" + currentApprovedAgreementContentResult + ")";
    }
    public CurrentApprovedAgreementContentResponse(CurrentApprovedAgreementContentResult currentApprovedAgreementContentResult) {
        this.result = currentApprovedAgreementContentResult;
    }
    public CurrentApprovedAgreementContentResponse(CurrentApprovedAgreementContentResult currentApprovedAgreementContentResult, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : currentApprovedAgreementContentResult);
    }
    public CurrentApprovedAgreementContentResult getResult() {
        return this.result;
    }
    public void setResult(CurrentApprovedAgreementContentResult currentApprovedAgreementContentResult) {
        this.result = currentApprovedAgreementContentResult;
    }
}
