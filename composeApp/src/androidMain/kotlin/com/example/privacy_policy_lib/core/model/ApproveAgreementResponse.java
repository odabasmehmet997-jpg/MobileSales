package com.example.privacy_policy_lib.core.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

@NamespaceList({@Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/"), @Namespace(prefix = "xsi", reference = "http://www.w3.org/2001/XMLSchema-instance"), @Namespace(prefix = "xsd", reference = "http://www.w3.org/2001/XMLSchema")})
@Root(name = "Envelope", strict = false)

public final class ApproveAgreementResponse {
    @Element(name = "Body", required = false)
    private ApproveAgreementResponseBody body;
    public ApproveAgreementResponse() {
        this(null, 1, null);
    }
    public static ApproveAgreementResponse copydefault(ApproveAgreementResponse approveAgreementResponse, ApproveAgreementResponseBody approveAgreementResponseBody, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            approveAgreementResponseBody = approveAgreementResponse.body;
        }
        return approveAgreementResponse.copy(approveAgreementResponseBody);
    }
    public ApproveAgreementResponseBody component1() {
        return this.body;
    }
    public ApproveAgreementResponse copy(ApproveAgreementResponseBody approveAgreementResponseBody) {
        return new ApproveAgreementResponse(approveAgreementResponseBody);
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ApproveAgreementResponse) && Intrinsics.areEqual(this.body, ((ApproveAgreementResponse) obj).body);
    }
    public int hashCode() {
        ApproveAgreementResponseBody approveAgreementResponseBody = this.body;
        if (approveAgreementResponseBody == null) {
            return 0;
        }
        return approveAgreementResponseBody.hashCode();
    }
    public String toString() {
        ApproveAgreementResponseBody approveAgreementResponseBody = this.body;
        return "ApproveAgreementResponse(body=" + approveAgreementResponseBody + ")";
    }
    public ApproveAgreementResponse(ApproveAgreementResponseBody approveAgreementResponseBody) {
        this.body = approveAgreementResponseBody;
    }
    public ApproveAgreementResponse(ApproveAgreementResponseBody approveAgreementResponseBody, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : approveAgreementResponseBody);
    }
    public ApproveAgreementResponseBody getBody() {
        return this.body;
    }
    public void setBody(ApproveAgreementResponseBody approveAgreementResponseBody) {
        this.body = approveAgreementResponseBody;
    }
}
