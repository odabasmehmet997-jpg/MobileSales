package com.proje.mobilesales.features.model;

import com.proje.mobilesales.core.enums.CustomerReportNames;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
public class CustomerReports {
    private String paramValue = "";
    private final Map<CustomerReportNames, Boolean> reportStatusMap = new LinkedHashMap();

    public CustomerReports() {
        for (final CustomerReportNames customerReportNames : CustomerReportNames.getEntries()) {
            reportStatusMap.put(customerReportNames, Boolean.FALSE);
        }
    }

    public final String getParamValue() {
        return paramValue;
    }

    public final void setParamValue(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        paramValue = str;
    }

    public final void updateReportStatus() {
        Object obj;
        final List<String> splitdefault = StringsKt.split(paramValue, new String[]{","}, false, 0);
        final ArrayList<String> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(splitdefault, 10));
        for (final String str : splitdefault) {
            arrayList.add(StringsKt.trim(str).toString());
        }
        for (final String str2 : arrayList) {
            final Iterator<CustomerReportNames> it = (Iterator<CustomerReportNames>) CustomerReportNames.getEntries();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual(((CustomerReportNames) obj).getValue(), str2)) {
                    break;
                }
            }
            final CustomerReportNames customerReportNames = (CustomerReportNames) obj;
            if (null != customerReportNames) {
                reportStatusMap.put(customerReportNames, Boolean.TRUE);
            }
        }
    }

    public final boolean isReportEnabled(final CustomerReportNames customerReportNames) {
        Intrinsics.checkNotNullParameter(customerReportNames, "report");
        final Boolean bool = reportStatusMap.get(customerReportNames);
        if (null != bool) {
            return bool.booleanValue();
        }
        return false;
    }

    private class E {
    }
}