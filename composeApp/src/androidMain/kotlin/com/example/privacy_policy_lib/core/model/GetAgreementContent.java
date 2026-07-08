package com.example.privacy_policy_lib.core.model;

import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.Element;


public final class GetAgreementContent {
    private OGetAgreementRequest oGetAgreementRequest;
    public static  GetAgreementContent copydefault(GetAgreementContent getAgreementContent, OGetAgreementRequest oGetAgreementRequest, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            oGetAgreementRequest = getAgreementContent.oGetAgreementRequest;
        }
        return getAgreementContent.copy(oGetAgreementRequest);
    }
    public OGetAgreementRequest component1() {
        return this.oGetAgreementRequest;
    }
    public GetAgreementContent copy(@Element(name = "oGetAgreementRequest") OGetAgreementRequest oGetAgreementRequest) {
        Intrinsics.checkNotNullParameter(oGetAgreementRequest, "oGetAgreementRequest");
        return new GetAgreementContent(oGetAgreementRequest);
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof GetAgreementContent) && Intrinsics.areEqual(this.oGetAgreementRequest, ((GetAgreementContent) obj).oGetAgreementRequest);
    }
    public int hashCode() {
        return this.oGetAgreementRequest.hashCode();
    }
    public String toString() {
        OGetAgreementRequest oGetAgreementRequest = this.oGetAgreementRequest;
        return "GetAgreementContent(oGetAgreementRequest=" + oGetAgreementRequest + ")";
    }
    public GetAgreementContent(@Element(name = "oGetAgreementRequest") OGetAgreementRequest oGetAgreementRequest) {
        Intrinsics.checkNotNullParameter(oGetAgreementRequest, "oGetAgreementRequest");
        this.oGetAgreementRequest = oGetAgreementRequest;
    }
    public OGetAgreementRequest getOGetAgreementRequest() {
        return this.oGetAgreementRequest;
    }
    public void setOGetAgreementRequest(OGetAgreementRequest oGetAgreementRequest) {
        Intrinsics.checkNotNullParameter(oGetAgreementRequest, "<set-?>");
        this.oGetAgreementRequest = oGetAgreementRequest;
    }
}
