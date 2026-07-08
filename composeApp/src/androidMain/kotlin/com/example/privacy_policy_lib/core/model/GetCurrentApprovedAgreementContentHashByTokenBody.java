package com.example.privacy_policy_lib.core.model;

import com.example.privacy_policy_lib.core.utils.RetrofitServiceFactory;
import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import static com.example.privacy_policy_lib.core.utils.RetrofitServiceFactory.*;

public final class GetCurrentApprovedAgreementContentHashByTokenBody {
    @Namespace(reference = "http://license1.proje.com.tr/LicenseService/AgreementService")
    private GetCurrentApprovedAgreementContentHashByToken getCurrentApprovedAgreementContentHashByToken;

    public static  GetCurrentApprovedAgreementContentHashByTokenBody copydefault(GetCurrentApprovedAgreementContentHashByTokenBody getCurrentApprovedAgreementContentHashByTokenBody, GetCurrentApprovedAgreementContentHashByToken getCurrentApprovedAgreementContentHashByToken, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            getCurrentApprovedAgreementContentHashByToken = getCurrentApprovedAgreementContentHashByTokenBody.getCurrentApprovedAgreementContentHashByToken;
        }
        return getCurrentApprovedAgreementContentHashByTokenBody.copy(getCurrentApprovedAgreementContentHashByToken);
    }

    public GetCurrentApprovedAgreementContentHashByToken component1() {
        return this.getCurrentApprovedAgreementContentHashByToken;
    }

    public GetCurrentApprovedAgreementContentHashByTokenBody copy(@Element(name = "GetCurrentApprovedAgreementContentHashByToken") GetCurrentApprovedAgreementContentHashByToken getCurrentApprovedAgreementContentHashByToken) {
        Intrinsics.checkNotNullParameter(getCurrentApprovedAgreementContentHashByToken, "getCurrentApprovedAgreementContentHashByToken");
        return new GetCurrentApprovedAgreementContentHashByTokenBody(getCurrentApprovedAgreementContentHashByToken);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof GetCurrentApprovedAgreementContentHashByTokenBody) && Intrinsics.areEqual(this.getCurrentApprovedAgreementContentHashByToken, ((GetCurrentApprovedAgreementContentHashByTokenBody) obj).getCurrentApprovedAgreementContentHashByToken);
    }

    public int hashCode() {
        return this.getCurrentApprovedAgreementContentHashByToken.hashCode();
    }

    public String toString() {
        GetCurrentApprovedAgreementContentHashByToken getCurrentApprovedAgreementContentHashByToken = this.getCurrentApprovedAgreementContentHashByToken;
        return "GetCurrentApprovedAgreementContentHashByTokenBody(getCurrentApprovedAgreementContentHashByToken=" + getCurrentApprovedAgreementContentHashByToken + ")";
    }

    public GetCurrentApprovedAgreementContentHashByTokenBody(@Element(name = "GetCurrentApprovedAgreementContentHashByToken") GetCurrentApprovedAgreementContentHashByToken getCurrentApprovedAgreementContentHashByToken) {
        Intrinsics.checkNotNullParameter(getCurrentApprovedAgreementContentHashByToken, "getCurrentApprovedAgreementContentHashByToken");
        this.getCurrentApprovedAgreementContentHashByToken = getCurrentApprovedAgreementContentHashByToken;
    }

    public GetCurrentApprovedAgreementContentHashByToken getGetCurrentApprovedAgreementContentHashByToken() {
        return this.getCurrentApprovedAgreementContentHashByToken;
    }

    public void setGetCurrentApprovedAgreementContentHashByToken(GetCurrentApprovedAgreementContentHashByToken getCurrentApprovedAgreementContentHashByToken) {
        Intrinsics.checkNotNullParameter(getCurrentApprovedAgreementContentHashByToken, "<set-?>");
        this.getCurrentApprovedAgreementContentHashByToken = getCurrentApprovedAgreementContentHashByToken;
    }
}
