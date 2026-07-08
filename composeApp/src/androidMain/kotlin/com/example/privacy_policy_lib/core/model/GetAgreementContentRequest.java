package com.example.privacy_policy_lib.core.model;

import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.Element;

public final class GetAgreementContentRequest {
    @Element(name = "soap:Body")
    private Body body;
    public static  GetAgreementContentRequest copydefault(GetAgreementContentRequest getAgreementContentRequest, Body body, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            body = getAgreementContentRequest.body;
        }
        return getAgreementContentRequest.copy(body);
    }
    public Body component1() {
        return this.body;
    }
    public GetAgreementContentRequest copy(@Element(name = "soap:Body") Body body) {
        Intrinsics.checkNotNullParameter(body, "body");
        return new GetAgreementContentRequest(body);
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof GetAgreementContentRequest) && Intrinsics.areEqual(this.body, ((GetAgreementContentRequest) obj).body);
    }
    public int hashCode() {
        return this.body.hashCode();
    }
    public String toString() {
        Body body = this.body;
        return "GetAgreementContentRequest(body=" + body + ")";
    }
    public GetAgreementContentRequest(@Element(name = "soap:Body") Body body) {
        Intrinsics.checkNotNullParameter(body, "body");
        this.body = body;
    }
    public Body getBody() {
        return this.body;
    }
    public void setBody(Body body) {
        Intrinsics.checkNotNullParameter(body, "<set-?>");
        this.body = body;
    }
}
