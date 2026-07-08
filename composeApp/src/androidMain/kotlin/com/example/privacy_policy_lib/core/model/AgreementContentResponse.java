package com.example.privacy_policy_lib.core.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;
 
public final class AgreementContentResponse { 
    private AgreementContentResult result;
    public AgreementContentResponse() {
        this(null, 1, null);
    }
    public static AgreementContentResponse copydefault(AgreementContentResponse agreementContentResponse, AgreementContentResult agreementContentResult, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            agreementContentResult = agreementContentResponse.result;
        }
        return agreementContentResponse.copy(agreementContentResult);
    }
    public AgreementContentResult component1() {
        return this.result;
    }
    public AgreementContentResponse copy(AgreementContentResult agreementContentResult) {
        return new AgreementContentResponse(agreementContentResult);
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof AgreementContentResponse) && Intrinsics.areEqual(this.result, ((AgreementContentResponse) obj).result);
    }
    public int hashCode() {
        AgreementContentResult agreementContentResult = this.result;
        if (agreementContentResult == null) {
            return 0;
        }
        return agreementContentResult.hashCode();
    }
    public String toString() {
        AgreementContentResult agreementContentResult = this.result;
        return "AgreementContentResponse(result=" + agreementContentResult + ")";
    }
    public AgreementContentResponse(AgreementContentResult agreementContentResult) {
        this.result = agreementContentResult;
    }
    public AgreementContentResponse(AgreementContentResult agreementContentResult, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : agreementContentResult);
    }
    public AgreementContentResult getResult() {
        return this.result;
    }
    public void setResult(AgreementContentResult agreementContentResult) {
        this.result = agreementContentResult;
    }
}
