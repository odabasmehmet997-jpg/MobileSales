package com.example.privacy_policy_lib.core.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.example.privacy_policy_lib.core.AgreementTypes;
import java.util.List;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class PrivacyPolicyLibParams implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private List<Pair<AgreementTypes, String>> agreementTokenList;
    private List<AgreementTypes> agreementTypes;
    private List<Pair<AgreementTypes, String>> contentHashList;
    private String contractor;
    private List<Pair<AgreementTypes, String>> endDateList;
    private String erpType;
    private boolean isProduction;
    private String itemCode;
    private String language;
    private String password;
    private String server;
    private String userName;

    public PrivacyPolicyLibParams() {
        this(false, null, null, null, null, null, null, null, null, null, null, null, 4095, null);
    }
    public boolean component1() {
        return isProduction;
    }
    public List<Pair<AgreementTypes, String>> component10() {
        return contentHashList;
    }
    public List<Pair<AgreementTypes, String>> component11() {
        return agreementTokenList;
    }
    public List<Pair<AgreementTypes, String>> component12() {
        return endDateList;
    }
    public String component2() {
        return contractor;
    }
    public String component3() {
        return itemCode;
    }
    public String component4() {
        return language;
    }
    public List<AgreementTypes> component5() {
        return agreementTypes;
    }
    public String component6() {
        return server;
    }
    public String component7() {
        return erpType;
    }
    public String component8() {
        return userName;
    }
    public String component9() {
        return password;
    }
    public PrivacyPolicyLibParams copy(final boolean z, final String str, final String str2, final String str3, final List<AgreementTypes> list, final String str4, final String str5, final String str6, final String str7, final List<Pair<AgreementTypes, String>> list2, final List<Pair<AgreementTypes, String>> list3, final List<Pair<AgreementTypes, String>> list4) {
        Intrinsics.checkNotNullParameter(str, "contractor");
        Intrinsics.checkNotNullParameter(str2, "itemCode");
        Intrinsics.checkNotNullParameter(str3, "language");
        Intrinsics.checkNotNullParameter(list, "agreementTypes");
        Intrinsics.checkNotNullParameter(str4, "server");
        Intrinsics.checkNotNullParameter(str5, "erpType");
        Intrinsics.checkNotNullParameter(str6, "userName");
        Intrinsics.checkNotNullParameter(str7, "password");
        Intrinsics.checkNotNullParameter(list2, "contentHashList");
        Intrinsics.checkNotNullParameter(list3, "agreementTokenList");
        Intrinsics.checkNotNullParameter(list4, "endDateList");
        return new PrivacyPolicyLibParams(z, str, str2, str3, list, str4, str5, str6, str7, list2, list3, list4);
    }

    public int describeContents() {
        return 0;
    }
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PrivacyPolicyLibParams privacyPolicyLibParams)) {
            return false;
        }
        return isProduction == privacyPolicyLibParams.isProduction && Intrinsics.areEqual(contractor, privacyPolicyLibParams.contractor) && Intrinsics.areEqual(itemCode, privacyPolicyLibParams.itemCode) && Intrinsics.areEqual(language, privacyPolicyLibParams.language) && Intrinsics.areEqual(agreementTypes, privacyPolicyLibParams.agreementTypes) && Intrinsics.areEqual(server, privacyPolicyLibParams.server) && Intrinsics.areEqual(erpType, privacyPolicyLibParams.erpType) && Intrinsics.areEqual(userName, privacyPolicyLibParams.userName) && Intrinsics.areEqual(password, privacyPolicyLibParams.password) && Intrinsics.areEqual(contentHashList, privacyPolicyLibParams.contentHashList) && Intrinsics.areEqual(agreementTokenList, privacyPolicyLibParams.agreementTokenList) && Intrinsics.areEqual(endDateList, privacyPolicyLibParams.endDateList);
    }

    public int hashCode() {
        boolean z = isProduction;
        if (z) {
            z = true;
        }
        final int i2 = z ? 1 : 0;
        final int i3 = z ? 1 : 0;
        final int i4 = z ? 1 : 0;
        return (((((((((((((((((((((i2 * 31) + contractor.hashCode()) * 31) + itemCode.hashCode()) * 31) + language.hashCode()) * 31) + agreementTypes.hashCode()) * 31) + server.hashCode()) * 31) + erpType.hashCode()) * 31) + userName.hashCode()) * 31) + password.hashCode()) * 31) + contentHashList.hashCode()) * 31) + agreementTokenList.hashCode()) * 31) + endDateList.hashCode();
    }
    public String toString() {
        final boolean z = isProduction;
        final String str = contractor;
        final String str2 = itemCode;
        final String str3 = language;
        final List<AgreementTypes> list = agreementTypes;
        final String str4 = server;
        final String str5 = erpType;
        final String str6 = userName;
        final String str7 = password;
        final List<Pair<AgreementTypes, String>> list2 = contentHashList;
        final List<Pair<AgreementTypes, String>> list3 = agreementTokenList;
        final List<Pair<AgreementTypes, String>> list4 = endDateList;
        return "PrivacyPolicyLibParams(isProduction=" + z + ", contractor=" + str + ", itemCode=" + str2 + ", language=" + str3 + ", agreementTypes=" + list + ", server=" + str4 + ", erpType=" + str5 + ", userName=" + str6 + ", password=" + str7 + ", contentHashList=" + list2 + ", agreementTokenList=" + list3 + ", endDateList=" + list4 + ")";
    }
    public PrivacyPolicyLibParams(final boolean z, final String str, final String str2, final String str3, final List<AgreementTypes> list, final String str4, final String str5, final String str6, final String str7, final List<Pair<AgreementTypes, String>> list2, final List<Pair<AgreementTypes, String>> list3, final List<Pair<AgreementTypes, String>> list4) {
        Intrinsics.checkNotNullParameter(str, "contractor");
        Intrinsics.checkNotNullParameter(str2, "itemCode");
        Intrinsics.checkNotNullParameter(str3, "language");
        Intrinsics.checkNotNullParameter(list, "agreementTypes");
        Intrinsics.checkNotNullParameter(str4, "server");
        Intrinsics.checkNotNullParameter(str5, "erpType");
        Intrinsics.checkNotNullParameter(str6, "userName");
        Intrinsics.checkNotNullParameter(str7, "password");
        Intrinsics.checkNotNullParameter(list2, "contentHashList");
        Intrinsics.checkNotNullParameter(list3, "agreementTokenList");
        Intrinsics.checkNotNullParameter(list4, "endDateList");
        isProduction = z;
        contractor = str;
        itemCode = str2;
        language = str3;
        agreementTypes = list;
        server = str4;
        erpType = str5;
        userName = str6;
        password = str7;
        contentHashList = list2;
        agreementTokenList = list3;
        endDateList = list4;
    }
    public  PrivacyPolicyLibParams(final boolean r13, final String r14, final String r15, final String r16, final List r17, final String r18, final String r19, final String r20, final String r21, final List r22, final List r23, final List r24, final int r25, final DefaultConstructorMarker r26) {
        this(0 == (r25 & 1) && r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24);
        throw new UnsupportedOperationException("Method not decompiled: com.example.privacy_policy_lib.core.model.PrivacyPolicyLibParams.<init>(boolean, java.lang.String, java.lang.String, java.lang.String, java.util.List, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.List, java.util.List, java.util.List, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
    public boolean isProduction() {
        return isProduction;
    }
    public void setProduction(final boolean z) {
        isProduction = z;
    }
    public String getContractor() {
        return contractor;
    }
    public void setContractor(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        contractor = str;
    }
    public String getItemCode() {
        return itemCode;
    }
    public void setItemCode(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        itemCode = str;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        language = str;
    }
    public List<AgreementTypes> getAgreementTypes() {
        return agreementTypes;
    }
    public void setAgreementTypes(final List<AgreementTypes> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        agreementTypes = list;
    }
    public String getServer() {
        return server;
    }
    public void setServer(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        server = str;
    }
    public String getErpType() {
        return erpType;
    }
    public void setErpType(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        erpType = str;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        userName = str;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        password = str;
    }
    public List<Pair<AgreementTypes, String>> getContentHashList() {
        return contentHashList;
    }
    public void setContentHashList(final List<Pair<AgreementTypes, String>> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        contentHashList = list;
    }
    public List<Pair<AgreementTypes, String>> getAgreementTokenList() {
        return agreementTokenList;
    }
    public void setAgreementTokenList(final List<Pair<AgreementTypes, String>> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        agreementTokenList = list;
    }
    public List<Pair<AgreementTypes, String>> getEndDateList() {
        return endDateList;
    }
    public void setEndDateList(final List<Pair<AgreementTypes, String>> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        endDateList = list;
    }
    public PrivacyPolicyLibParams(final Parcel r15) {

        throw new UnsupportedOperationException("Method not decompiled: com.example.privacy_policy_lib.core.model.PrivacyPolicyLibParams.<init>(android.os.Parcel):void");
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeByte(isProduction ? (byte) 1 : 0);
        parcel.writeString(contractor);
        parcel.writeString(itemCode);
        parcel.writeString(language);
        parcel.writeList(agreementTypes);
        parcel.writeString(server);
        parcel.writeString(erpType);
        parcel.writeString(userName);
        parcel.writeString(password);
        parcel.writeList(contentHashList);
        parcel.writeList(agreementTokenList);
        parcel.writeList(endDateList);
    }
    public static final class CREATOR implements Parcelable.Creator<PrivacyPolicyLibParams> {
        public   CREATOR(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }
        public PrivacyPolicyLibParams createFromParcel(final Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new PrivacyPolicyLibParams(parcel);
        }

        public PrivacyPolicyLibParams[] newArray(final int i2) {
            return new PrivacyPolicyLibParams[i2];
        }
    }
}
