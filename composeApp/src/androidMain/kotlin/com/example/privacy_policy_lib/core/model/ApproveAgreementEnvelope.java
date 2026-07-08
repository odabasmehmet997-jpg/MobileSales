package com.example.privacy_policy_lib.core.model;

import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

@NamespaceList({@Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/"), @Namespace(prefix = "xsi", reference = "http://www.w3.org/2001/XMLSchema-instance"), @Namespace(prefix = "xsd", reference = "http://www.w3.org/2001/XMLSchema")})
@Root(name = "soap:Envelope")

public final class ApproveAgreementEnvelope {
    @Element(name = "soap:Body")
    private ApproveAgreementBody body;
    public static ApproveAgreementEnvelope copydefault(ApproveAgreementEnvelope approveAgreementEnvelope, ApproveAgreementBody approveAgreementBody, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            approveAgreementBody = approveAgreementEnvelope.body;
        }
        return approveAgreementEnvelope.copy(approveAgreementBody);
    }
    public ApproveAgreementBody component1() {
        return this.body;
    }
    public ApproveAgreementEnvelope copy(@Element(name = "soap:Body") ApproveAgreementBody approveAgreementBody) {
        Intrinsics.checkNotNullParameter(approveAgreementBody, "body");
        return new ApproveAgreementEnvelope(approveAgreementBody);
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ApproveAgreementEnvelope) && Intrinsics.areEqual(this.body, ((ApproveAgreementEnvelope) obj).body);
    }
    public int hashCode() {
        return this.body.hashCode();
    }
    public String toString() {
        ApproveAgreementBody approveAgreementBody = this.body;
        return "ApproveAgreementEnvelope(body=" + approveAgreementBody + ")";
    }
    public ApproveAgreementEnvelope(@Element(name = "soap:Body") ApproveAgreementBody approveAgreementBody) {
        Intrinsics.checkNotNullParameter(approveAgreementBody, "body");
        this.body = approveAgreementBody;
    }
    public ApproveAgreementBody getBody() {
        return this.body;
    }
    public void setBody(ApproveAgreementBody approveAgreementBody) {
        Intrinsics.checkNotNullParameter(approveAgreementBody, "<set-?>");
        this.body = approveAgreementBody;
    }
}
