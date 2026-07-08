package com.example.privacy_policy_lib.core.model;

import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

public final class GetCurrentApprovedAgreementContentHashByTokenRequest {
    private GetCurrentApprovedAgreementContentHashByTokenBody body;

    public static   GetCurrentApprovedAgreementContentHashByTokenRequest copydefault(GetCurrentApprovedAgreementContentHashByTokenRequest getCurrentApprovedAgreementContentHashByTokenRequest, GetCurrentApprovedAgreementContentHashByTokenBody getCurrentApprovedAgreementContentHashByTokenBody, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            getCurrentApprovedAgreementContentHashByTokenBody = getCurrentApprovedAgreementContentHashByTokenRequest.body;
        }
        return getCurrentApprovedAgreementContentHashByTokenRequest.copy(getCurrentApprovedAgreementContentHashByTokenBody);
    }

    public GetCurrentApprovedAgreementContentHashByTokenBody component1() {
        return this.body;
    }

    public GetCurrentApprovedAgreementContentHashByTokenRequest copy(@Element(name = "soap:Body") GetCurrentApprovedAgreementContentHashByTokenBody getCurrentApprovedAgreementContentHashByTokenBody) {
        Intrinsics.checkNotNullParameter(getCurrentApprovedAgreementContentHashByTokenBody, "body");
        return new GetCurrentApprovedAgreementContentHashByTokenRequest(getCurrentApprovedAgreementContentHashByTokenBody);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof GetCurrentApprovedAgreementContentHashByTokenRequest) && Intrinsics.areEqual(this.body, ((GetCurrentApprovedAgreementContentHashByTokenRequest) obj).body);
    }

    public int hashCode() {
        return this.body.hashCode();
    }
    public String toString() {
        GetCurrentApprovedAgreementContentHashByTokenBody getCurrentApprovedAgreementContentHashByTokenBody = this.body;
        return "GetCurrentApprovedAgreementContentHashByTokenRequest(body=" + getCurrentApprovedAgreementContentHashByTokenBody + ")";
    }
    public GetCurrentApprovedAgreementContentHashByTokenRequest(@Element(name = "soap:Body") GetCurrentApprovedAgreementContentHashByTokenBody getCurrentApprovedAgreementContentHashByTokenBody) {
        Intrinsics.checkNotNullParameter(getCurrentApprovedAgreementContentHashByTokenBody, "body");
        this.body = getCurrentApprovedAgreementContentHashByTokenBody;
    }
    public GetCurrentApprovedAgreementContentHashByTokenBody getBody() {
        return this.body;
    }
    public void setBody(GetCurrentApprovedAgreementContentHashByTokenBody getCurrentApprovedAgreementContentHashByTokenBody) {
        Intrinsics.checkNotNullParameter(getCurrentApprovedAgreementContentHashByTokenBody, "<set-?>");
        this.body = getCurrentApprovedAgreementContentHashByTokenBody;
    }
}
