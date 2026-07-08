package com.proje.mobilesales.core.enums;

import com.proje.mobilesales.core.utils.StringUtils;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class NewCustomerRequiredFields {
    private static final  EnumEntries ENTRIES;
    private static final  NewCustomerRequiredFields[] VALUES;
    public static final NewCustomerRequiredFields ADDRESS2;
    public static final Companion Companion;
    public static final NewCustomerRequiredFields DISCOUNTRATE;
    public static final NewCustomerRequiredFields EMAIL;
    public static final NewCustomerRequiredFields EXPIRY;

    /* renamed from: ID */
    public static final NewCustomerRequiredFields f1173ID;
    public static final NewCustomerRequiredFields PAYMENTPLAN;
    public static final NewCustomerRequiredFields PAYMENTTYPE;
    public static final NewCustomerRequiredFields RELATEDEMAIL;
    public static final NewCustomerRequiredFields RELATEDPERSON;
    public static final NewCustomerRequiredFields RELATEDTELNO;
    public static final NewCustomerRequiredFields RELATEDTITLE;
    public static final NewCustomerRequiredFields SPECIALCODE1;
    public static final NewCustomerRequiredFields SPECIALCODE2;
    public static final NewCustomerRequiredFields SPECIALCODE3;
    public static final NewCustomerRequiredFields SPECIALCODE4;
    public static final NewCustomerRequiredFields SPECIALCODE5;
    public static final NewCustomerRequiredFields TAXNO;
    public static final NewCustomerRequiredFields TAXOFFICE;
    public static final NewCustomerRequiredFields TELNO1;
    public static final NewCustomerRequiredFields TELNO2;
    public static final NewCustomerRequiredFields WARRANTYCODE;
    public static final NewCustomerRequiredFields ZIPCODE;
    private final int mType;
    private final Integer tigerType;
    public static final NewCustomerRequiredFields TITLE = new NewCustomerRequiredFields("TITLE", 0, 1, null, 2, null);
    public static final NewCustomerRequiredFields COUNTRY = new NewCustomerRequiredFields("COUNTRY", 7, 8, 28);
    public static final NewCustomerRequiredFields PROVINCE = new NewCustomerRequiredFields("PROVINCE", 8, 9, 29);
    public static final NewCustomerRequiredFields DISTRICT = new NewCustomerRequiredFields("DISTRICT", 9, 10, 30);
    public static final NewCustomerRequiredFields FAXNO = new NewCustomerRequiredFields("FAXNO", 10, 11, null, 2, null);
    public static final NewCustomerRequiredFields ADDRESS1 = new NewCustomerRequiredFields("ADDRESS1", 11, 12, null, 2, null);

    private static NewCustomerRequiredFields[] values() {
        return new NewCustomerRequiredFields[]{TITLE, f1173ID, TAXNO, TAXOFFICE, TELNO1, TELNO2, EMAIL, COUNTRY, PROVINCE, DISTRICT, FAXNO, ADDRESS1, ADDRESS2, ZIPCODE, EXPIRY, DISCOUNTRATE, PAYMENTPLAN, PAYMENTTYPE, RELATEDPERSON, RELATEDTITLE, RELATEDTELNO, RELATEDEMAIL, SPECIALCODE1, SPECIALCODE2, SPECIALCODE3, SPECIALCODE4, SPECIALCODE5, WARRANTYCODE};
    }

    public static EnumEntries<NewCustomerRequiredFields> getEntries() {
        return ENTRIES;
    }

    public static NewCustomerRequiredFields valueOf(String str) {
        return Enum.valueOf(NewCustomerRequiredFields.class, str);
    }

    public static NewCustomerRequiredFields[] values() {
        return VALUES.clone();
    }

    private NewCustomerRequiredFields(String str, int i2, int i3, Integer num) {
        this.mType = i3;
        this.tigerType = num;
    }

     NewCustomerRequiredFields(String str, int i2, int i3, Integer num, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i2, i3, (i4 & 2) != 0 ? null : num);
    }

    static {
        int i2 = 2;
        DefaultConstructorMarker defaultConstructorMarker = null;
        Integer num = null;
        f1173ID = new NewCustomerRequiredFields("ID", 1, 2, num, i2, defaultConstructorMarker);
        int i3 = 2;
        DefaultConstructorMarker defaultConstructorMarker2 = null;
        Integer num2 = null;
        TAXNO = new NewCustomerRequiredFields("TAXNO", 2, 3, num2, i3, defaultConstructorMarker2);
        TAXOFFICE = new NewCustomerRequiredFields("TAXOFFICE", 3, 4, num, i2, defaultConstructorMarker);
        TELNO1 = new NewCustomerRequiredFields("TELNO1", 4, 5, num2, i3, defaultConstructorMarker2);
        TELNO2 = new NewCustomerRequiredFields("TELNO2", 5, 6, num, i2, defaultConstructorMarker);
        EMAIL = new NewCustomerRequiredFields("EMAIL", 6, 7, num2, i3, defaultConstructorMarker2);
        int i4 = 2;
        DefaultConstructorMarker defaultConstructorMarker3 = null;
        Integer num3 = null;
        ADDRESS2 = new NewCustomerRequiredFields("ADDRESS2", 12, 13, num3, i4, defaultConstructorMarker3);
        int i5 = 2;
        DefaultConstructorMarker defaultConstructorMarker4 = null;
        Integer num4 = null;
        ZIPCODE = new NewCustomerRequiredFields("ZIPCODE", 13, 14, num4, i5, defaultConstructorMarker4);
        EXPIRY = new NewCustomerRequiredFields("EXPIRY", 14, 15, num3, i4, defaultConstructorMarker3);
        DISCOUNTRATE = new NewCustomerRequiredFields("DISCOUNTRATE", 15, 16, num4, i5, defaultConstructorMarker4);
        PAYMENTPLAN = new NewCustomerRequiredFields("PAYMENTPLAN", 16, 17, num3, i4, defaultConstructorMarker3);
        PAYMENTTYPE = new NewCustomerRequiredFields("PAYMENTTYPE", 17, 18, num4, i5, defaultConstructorMarker4);
        RELATEDPERSON = new NewCustomerRequiredFields("RELATEDPERSON", 18, 19, num3, i4, defaultConstructorMarker3);
        RELATEDTITLE = new NewCustomerRequiredFields("RELATEDTITLE", 19, 20, num4, i5, defaultConstructorMarker4);
        RELATEDTELNO = new NewCustomerRequiredFields("RELATEDTELNO", 20, 21, num3, i4, defaultConstructorMarker3);
        RELATEDEMAIL = new NewCustomerRequiredFields("RELATEDEMAIL", 21, 22, num4, i5, defaultConstructorMarker4);
        SPECIALCODE1 = new NewCustomerRequiredFields("SPECIALCODE1", 22, 23, num3, i4, defaultConstructorMarker3);
        SPECIALCODE2 = new NewCustomerRequiredFields("SPECIALCODE2", 23, 24, num4, i5, defaultConstructorMarker4);
        SPECIALCODE3 = new NewCustomerRequiredFields("SPECIALCODE3", 24, 25, num3, i4, defaultConstructorMarker3);
        SPECIALCODE4 = new NewCustomerRequiredFields("SPECIALCODE4", 25, 26, num4, i5, defaultConstructorMarker4);
        SPECIALCODE5 = new NewCustomerRequiredFields("SPECIALCODE5", 26, 27, num3, i4, defaultConstructorMarker3);
        WARRANTYCODE = new NewCustomerRequiredFields("WARRANTYCODE", 27, 31, num4, i5, defaultConstructorMarker4);
        NewCustomerRequiredFields[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
        Companion = new Companion(null);
    }

    public int getmType(ErpType erpType) {
        Integer num;
        Intrinsics.checkNotNullParameter(erpType, "erpType");
        return (erpType != ErpType.TIGER || (num = this.tigerType) == null) ? this.mType : num.intValue();
    }

    /* compiled from: NewCustomerRequiredFields.kt */
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public NewCustomerRequiredFields fromNewCustomerRequiredFields(String str, ErpType erpType) {
            Intrinsics.checkNotNullParameter(erpType, "erpType");
            for (NewCustomerRequiredFields newCustomerRequiredFields : NewCustomerRequiredFields.getEntries()) {
                if (newCustomerRequiredFields.getmType(erpType) == StringUtils.convertStringToInt(str)) {
                    return newCustomerRequiredFields;
                }
            }
            return NewCustomerRequiredFields.TITLE;
        }
    }
}
