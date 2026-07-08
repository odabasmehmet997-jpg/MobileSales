package com.proje.mobilesales.core.enums;

import androidx.annotation.StringRes;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class DeleteDataParamEnum extends Enum<DeleteDataParamEnum> {
    private static final  EnumEntries ENTRIES;
    private static final  DeleteDataParamEnum[] VALUES;
    private int mDeleteQry;
    private int mResId;
    private int mValue;
    public static final DeleteDataParamEnum ORDER = new DeleteDataParamEnum(DailyInfo.ORDER, 0, 1, R.string.qry_delete_orders, R.string.str_sales_order);
    public static final DeleteDataParamEnum VISIT = new DeleteDataParamEnum(DailyInfo.VISIT, 1, 2, R.string.qry_delete_visit, R.string.str_visit);
    public static final DeleteDataParamEnum RECEIPT = new DeleteDataParamEnum("RECEIPT", 2, 3, R.string.qry_delete_receipt, R.string.str_receipt);
    public static final DeleteDataParamEnum INVOICE = new DeleteDataParamEnum("INVOICE", 3, 4, R.string.qry_delete_invoice, R.string.str_sales_invoice);
    public static final DeleteDataParamEnum RETURN_INVOICE = new DeleteDataParamEnum("RETURN_INVOICE", 4, 5, R.string.qry_delete_return_invoice, R.string.str_sales_return_invoice);
    public static final DeleteDataParamEnum DISPATCH = new DeleteDataParamEnum("DISPATCH", 5, 6, R.string.qry_delete_dispatch, R.string.str_sales_dispatch);
    public static final DeleteDataParamEnum RETURN_DISPATCH = new DeleteDataParamEnum("RETURN_DISPATCH", 6, 7, R.string.qry_delete_return_dispatch, R.string.str_sales_return_dispatch);
    public static final DeleteDataParamEnum ONETOONE = new DeleteDataParamEnum("ONETOONE", 7, 8, R.string.qry_delete_one_to_one, R.string.str_sales_one_to_one_change);
    public static final DeleteDataParamEnum PENETRATION = new DeleteDataParamEnum("PENETRATION", 8, 9, R.string.qry_delete_penetration, R.string.str_customer_penetration);
    public static final DeleteDataParamEnum DEMAND = new DeleteDataParamEnum(DailyInfo.DEMAND, 9, 10, R.string.qry_delete_demand, R.string.str_sales_demand);
    public static final Companion Companion = new Companion(null);

    private static DeleteDataParamEnum[] values() {
        return new DeleteDataParamEnum[]{ORDER, VISIT, RECEIPT, INVOICE, RETURN_INVOICE, DISPATCH, RETURN_DISPATCH, ONETOONE, PENETRATION, DEMAND};
    }

    public static String[] getDeleteDocumentTypes(String[] strArr) {
        return Companion.getDeleteDocumentTypes(strArr);
    }

    public static String getDeleteQueries(String[] strArr) {
        return Companion.getDeleteQueries(strArr);
    }

    public static EnumEntries<DeleteDataParamEnum> getEntries() {
        return ENTRIES;
    }

    public static DeleteDataParamEnum valueOf(String str) {
        return Enum.valueOf(DeleteDataParamEnum.class, str);
    }


    private DeleteDataParamEnum(String str, @StringRes int i2, @StringRes int i3, int i4, int i5) {
        super(str,i2);
        this.mValue = i3;
        this.mDeleteQry = i4;
        this.mResId = i5;
    }

    public int getMValue() {
        return this.mValue;
    }

    public void setMValue(int i2) {
        this.mValue = i2;
    }

    public int getMDeleteQry() {
        return this.mDeleteQry;
    }

    public void setMDeleteQry(int i2) {
        this.mDeleteQry = i2;
    }

    public int getMResId() {
        return this.mResId;
    }

    public void setMResId(int i2) {
        this.mResId = i2;
    }

    static {
        DeleteDataParamEnum[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
 
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public String[] getDeleteDocumentTypes(String[] strArr) {
            Intrinsics.checkNotNullParameter(strArr, "selectedValues");
            String[] strArr2 = new String[strArr.length];
            int length = strArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                strArr2[i2] = ContextUtils.getStringResource(getDeleteDocumentResId(StringUtils.convertStringToInt(strArr[i2])));
            }
            return strArr2;
        }

        private int getDeleteDocumentResId(int i2) {
            for (DeleteDataParamEnum deleteDataParamEnum : DeleteDataParamEnum.getEntries()) {
                if (deleteDataParamEnum.getMValue() == i2) {
                    return deleteDataParamEnum.getMResId();
                }
            }
            return -1;
        }

        public String getDeleteQueries(String[] strArr) {
            Intrinsics.checkNotNullParameter(strArr, "selectedValuesArry");
            String str = "";
            for (String str2 : strArr) {
                str = str + ContextUtils.getStringResource(getDeleteQueryFromID(StringUtils.convertStringToInt(str2)));
            }
            return str;
        }

        private int getDeleteQueryFromID(int i2) {
            for (DeleteDataParamEnum deleteDataParamEnum : DeleteDataParamEnum.getEntries()) {
                if (deleteDataParamEnum.getMValue() == i2) {
                    return deleteDataParamEnum.getMDeleteQry();
                }
            }
            return -1;
        }
    }
}
