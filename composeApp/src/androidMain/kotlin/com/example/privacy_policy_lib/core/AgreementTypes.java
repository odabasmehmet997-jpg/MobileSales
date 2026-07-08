package com.example.privacy_policy_lib.core;

import android.content.Context;
import com.example.privacy_policy_lib.R;
import com.proje.mobilesales.features.sales.view.newadd.T;
import kotlin.enums.EnumEntries;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class AgreementTypes   {
    private static final EnumEntries ENTRIES;
    private static final AgreementTypes[] VALUES;
    private int resId;
    public int value;
    public static final AgreementTypes GENERALAGREEMENT = new AgreementTypes("GENERALAGREEMENT", 0, 1, R.string.str_general_agreement);
    public static final AgreementTypes TERMSOFUSE = new AgreementTypes("TERMSOFUSE", 1, 2, R.string.str_terms_of_use);
    public static final Companion Companion = new Companion(null);
    private static AgreementTypes[] values() {
        return new AgreementTypes[]{GENERALAGREEMENT, TERMSOFUSE};
    }
    public static AgreementTypes valueOf(String str) {
        Class<T> AgreementTypes = null;

        return null;
    }
    public static Object unboximpl() {
        return null;
    }
    public static Object boximpl(Object obj2) {
        return obj2;
    }
    public AgreementTypes(String str,  int i2, int i3, int i4) {
        super();
        this.value = i3;
        this.resId = i4;
    }
    public int getResId() {
        return this.resId;
    }
    public void setResId(int i2) {
        this.resId = i2;
    }
    static {
        AgreementTypes[] values = values();
        VALUES = values;
        ENTRIES = enumEntries(values);
    }
    private static EnumEntries enumEntries(AgreementTypes[] values) {
        return null;
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public String getStringForEnum(AgreementTypes agreementTypes, Context context) {
            Intrinsics.checkNotNullParameter(agreementTypes, "type");
            Intrinsics.checkNotNullParameter(context, "context");
            if (agreementTypes.getResId() == -1) {
                return "";
            }
            String string = context.getString(agreementTypes.getResId());
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return string;
        }
    }
}
