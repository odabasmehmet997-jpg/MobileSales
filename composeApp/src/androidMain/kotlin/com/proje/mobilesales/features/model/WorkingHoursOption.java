package com.proje.mobilesales.features.model;

import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.BuildConfig;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
public class WorkingHoursOption {
    private final boolean dataEntry;
    private final boolean login;
    private final boolean order;
    private final boolean penetration;
    private final boolean receipt;
    private final boolean report;
    private final boolean sales;
    private final boolean transferGet;
    private final boolean transferSend;
    private final boolean visit;

    public WorkingHoursOption(final String str) {
        final List list = (List) Arrays.stream(str.split("\\,")).map(new Function() {
            public Object apply(final Object obj) {
                return ((String) obj).trim();
            }
        }).collect(Collectors.toList());
        login = list.contains(BuildConfig.NETSIS_DEMO_PASSWORD);
        dataEntry = list.contains(ExifInterface.GPS_MEASUREMENT_2D);
        transferGet = list.contains(ExifInterface.GPS_MEASUREMENT_3D);
        transferSend = list.contains("4");
        report = list.contains("5");
        visit = list.contains("6");
        penetration = list.contains("7");
        receipt = list.contains("8");
        sales = list.contains("9");
        order = list.contains("10");
    }

    public boolean isLogin() {
        return login;
    }

    public boolean isDataEntry() {
        return dataEntry;
    }

    public boolean isTransferGet() {
        return transferGet;
    }

    public boolean isTransferSend() {
        return transferSend;
    }

    public boolean isReport() {
        return report;
    }

    public boolean isVisit() {
        return visit;
    }

    public boolean isPenetration() {
        return penetration;
    }

    public boolean isReceipt() {
        return receipt;
    }

    public boolean isSales() {
        return sales;
    }

    public boolean isOrder() {
        return order;
    }
}
