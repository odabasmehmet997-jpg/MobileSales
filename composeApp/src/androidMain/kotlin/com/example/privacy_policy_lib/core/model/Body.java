package com.example.privacy_policy_lib.core.model;

import com.example.privacy_policy_lib.core.utils.RetrofitServiceFactory;
import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;

public final class Body {
    public static final String GET_AGREEMENT_CONTENT = RetrofitServiceFactory.GET_AGREEMENT_CONTENT;

    @Namespace(reference = "http://license1.proje.com.tr/ProjeLicenseService/AgreementService")
    private GetAgreementContent getAgreementContent;
    public static Body copydefault(Body body, GetAgreementContent getAgreementContent, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            getAgreementContent = body.getAgreementContent;
        }
        return body.copy(getAgreementContent);
    }
    public GetAgreementContent component1() {
        return this.getAgreementContent;
    }
    public Body copy(@Element(name = "GetAgreementContent") GetAgreementContent getAgreementContent) {
        Intrinsics.checkNotNullParameter(getAgreementContent, "getAgreementContent");
        return new Body(getAgreementContent);
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Body) && Intrinsics.areEqual(this.getAgreementContent, ((Body) obj).getAgreementContent);
    }
    public int hashCode() {
        return this.getAgreementContent.hashCode();
    }
    public String toString() {
        GetAgreementContent getAgreementContent = this.getAgreementContent;
        return "Body(getAgreementContent=" + getAgreementContent + ")";
    }
     public Body(@Element(name = "GetAgreementContent") GetAgreementContent getAgreementContent) {
        Intrinsics.checkNotNullParameter(getAgreementContent, "getAgreementContent");
        this.getAgreementContent = getAgreementContent;
    }
    public GetAgreementContent getGetAgreementContent() {
        return this.getAgreementContent;
    }
    public void setGetAgreementContent(GetAgreementContent getAgreementContent) {
        Intrinsics.checkNotNullParameter(getAgreementContent, "<set-?>");
        this.getAgreementContent = getAgreementContent;
    }
}
