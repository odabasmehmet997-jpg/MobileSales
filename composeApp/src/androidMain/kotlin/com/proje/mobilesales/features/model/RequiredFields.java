package com.proje.mobilesales.features.model;

import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.utils.SalesUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class RequiredFields {
    public static final RequiredFields NONE = new RequiredFields();
    private List<String> parameterSplitContent;
    private SalesType salesType = null;

    public RequiredFields(final String parameterValue, final SalesType salesType) {
        Intrinsics.checkNotNullParameter(parameterValue, "parameterValue");
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        this.salesType = salesType;
        parameterSplitContent = CollectionsKt.emptyList();
        final List splitdefault = StringsKt.split( parameterValue, new String[]{","}, false, 0);
        final ArrayList arrayList = new ArrayList();
        for (final Object obj : splitdefault) {
            if (0 < ((String) obj).length()) {
                arrayList.add(obj);
            }
        }
        final ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        final Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(StringsKt.trim((String) it.next()).toString());
        }
        parameterSplitContent = arrayList2;
    }

    public RequiredFields() {

    }

    public boolean isTradeGroupRequired() {
        return this.checkRequirement(BuildConfig.NETSIS_DEMO_PASSWORD, "20");
    }

    public boolean isPaymentPlanRequired() {
        return this.checkRequirement(ExifInterface.GPS_MEASUREMENT_2D, "21");
    }

    public boolean isDocumentNoRequired() {
        return this.checkRequirement(ExifInterface.GPS_MEASUREMENT_3D, "9");
    }

    public boolean isSpecialCodeRequired() {
        return this.checkRequirement("4", "6");
    }

    public boolean isAuthorizationCodeRequired() {
        return this.checkRequirement("5", "7");
    }

    public boolean isDocumentTrackingNoRequired() {
        return this.checkRequirement("6", "10");
    }

    public boolean isShipTypeRequired() {
        return this.checkRequirement("7", "19");
    }

    public boolean isShippingAccountRequired() {
        return this.checkRequirement("8", "15");
    }

    public boolean isShippingAddressRequired() {
        return this.checkRequirement("9", "16");
    }

    public boolean isShipAgentRequired() {
        return this.checkRequirement("10", "18");
    }

    public boolean isExplanation1Required() {
        return RequiredFields.checkRequirementdefault(this, "11", null, 2, null);
    }

    public boolean isExplanation2Required() {
        return RequiredFields.checkRequirementdefault(this, "12", null, 2, null);
    }

    public boolean isExplanation3Required() {
        return RequiredFields.checkRequirementdefault(this, "13", null, 2, null);
    }

    public boolean isExplanation4Required() {
        return RequiredFields.checkRequirementdefault(this, "14", null, 2, null);
    }

    public boolean isProjectCodeRequired() {
        return this.checkRequirement("15", "22");
    }

    public boolean isDeliveryDateRequired() {
        return SalesUtils.INSTANCE.isSalesOrOrder(salesType) && parameterSplitContent.contains("16");
    }

    public boolean isInvoiceAddressRequired() {
        return RequiredFields.checkRequirementdefault(this, "17", null, 2, null);
    }

    public boolean isBranchRequired() {
        return this.checkRequirement("18", BuildConfig.NETSIS_DEMO_PASSWORD);
    }

    public boolean isDivisionRequired() {
        return this.checkRequirement("19", ExifInterface.GPS_MEASUREMENT_2D);
    }

    public boolean isFactoryRequired() {
        return this.checkRequirement("20", ExifInterface.GPS_MEASUREMENT_3D);
    }

    public boolean isWarehouseRequired() {
        return this.checkRequirement("21", "4");
    }

    public boolean isInvoiceTypeRequired() {
        return this.checkRequirement("22", "5");
    }

    public boolean isCustomerOrderNoRequired() {
        return SalesUtils.INSTANCE.isSalesOrOrder(salesType) && parameterSplitContent.contains("23");
    }

    public boolean isCabinRequired() {
        return SalesUtils.isSalesTypeInvoiceOrDispatch(salesType) && parameterSplitContent.contains("8");
    }

    static boolean checkRequirementdefault(final RequiredFields requiredFields, final String str, String str2, final int i2, final Object obj) {
        if (0 != (i2 & 2)) {
            str2 = str;
        }
        return requiredFields.checkRequirement(str, str2);
    }

    private boolean checkRequirement(final String str, final String str2) {
        if (salesType == SalesType.ORDER) {
            return parameterSplitContent.contains(str);
        }
        return parameterSplitContent.contains(str2);
    }
}
