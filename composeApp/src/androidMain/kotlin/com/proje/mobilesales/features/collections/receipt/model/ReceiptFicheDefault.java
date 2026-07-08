package com.proje.mobilesales.features.collections.receipt.model;

import android.text.TextUtils;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;


/* compiled from: ReceiptFicheDefault.kt */

public final class ReceiptFicheDefault {
    private String defaultBank;
    private String defaultCase;
    private String defaultCyphCode;
    private String defaultDepartment;
    private String defaultDivision;
    private String defaultProjectCode;
    private String defaultSpeCode;
    private String defaultTradingGroup;

    public String getDefaultDivision() {
        return defaultDivision;
    }

    public String getDefaultDepartment() {
        return defaultDepartment;
    }

    public String getDefaultSpeCode() {
        return defaultSpeCode;
    }

    public String getDefaultProjectCode() {
        return defaultProjectCode;
    }

    public String getDefaultCyphCode() {
        return defaultCyphCode;
    }

    public String getDefaultTradingGroup() {
        return defaultTradingGroup;
    }

    public String getDefaultBank() {
        return defaultBank;
    }

    public ReceiptFicheDefault() {
    }

    public ReceiptFicheDefault(final String str, final String str2, final String str3, final String str4, final String str5, final String str6, final String str7, final String str8) {
        defaultDivision = str;
        defaultDepartment = str2;
        defaultSpeCode = str3;
        defaultProjectCode = str4;
        defaultCyphCode = str5;
        defaultTradingGroup = str6;
        defaultCase = str7;
        defaultBank = str8;
    }

    public String getDefaultCaseWithoutFirmOrBranch(final String firmOrBranch) {
        Intrinsics.checkNotNullParameter(firmOrBranch, "firmOrBranch");
        if (TextUtils.isEmpty(firmOrBranch)) {
            return defaultCase;
        }
        final String str = defaultCase;
        Intrinsics.checkNotNull(str);
        return StringsKt.replacedefault(str, firmOrBranch + ',', "", false, 4, (Object) null);
    }
}
