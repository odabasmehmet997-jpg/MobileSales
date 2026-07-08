package com.example.privacy_policy_lib.core.model;

import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.Element;

public record GetCurrentApprovedAgreementContentHashByToken(@Element(name = "agreementToken") String agreementToken) {

    public static GetCurrentApprovedAgreementContentHashByToken copydefault(GetCurrentApprovedAgreementContentHashByToken getCurrentApprovedAgreementContentHashByToken, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = getCurrentApprovedAgreementContentHashByToken.agreementToken;
        }
        return getCurrentApprovedAgreementContentHashByToken.copy(str);
    }

    public String component1() {
        return this.agreementToken;
    }

    public GetCurrentApprovedAgreementContentHashByToken copy(@Element(name = "agreementToken") String str) {
        Intrinsics.checkNotNullParameter(str, "agreementToken");
        return new GetCurrentApprovedAgreementContentHashByToken(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof GetCurrentApprovedAgreementContentHashByToken) && Intrinsics.areEqual(this.agreementToken, ((GetCurrentApprovedAgreementContentHashByToken) obj).agreementToken);
    }

    public String toString() {
        String str = this.agreementToken;
        return "GetCurrentApprovedAgreementContentHashByToken(agreementToken=" + str + ")";
    }

    public GetCurrentApprovedAgreementContentHashByToken {
        Intrinsics.checkNotNullParameter(agreementToken, "agreementToken");
    }

    @Override
    public String agreementToken() {
        return this.agreementToken;
    }
}
