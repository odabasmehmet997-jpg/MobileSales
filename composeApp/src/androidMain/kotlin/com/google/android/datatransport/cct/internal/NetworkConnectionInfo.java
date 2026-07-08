package com.google.android.datatransport.cct.internal;

import android.util.SparseArray;

public abstract class NetworkConnectionInfo {
    public static abstract class Builder {
        public abstract NetworkConnectionInfo build();
        public abstract Builder setMobileSubtype( MobileSubtype mobileSubtype);
        public abstract Builder setNetworkType( NetworkType networkType);
    }
    public abstract MobileSubtype getMobileSubtype();
    public abstract NetworkType getNetworkType();
    public enum NetworkType {
        MOBILE(0),
        WIFI(1),
        MOBILE_MMS(2),
        MOBILE_SUPL(3),
        MOBILE_DUN(4),
        MOBILE_HIPRI(5),
        WIMAX(6),
        BLUETOOTH(7),
        DUMMY(8),
        ETHERNET(9),
        MOBILE_FOTA(10),
        MOBILE_IMS(11),
        MOBILE_CBS(12),
        WIFI_P2P(13),
        MOBILE_IA(14),
        MOBILE_EMERGENCY(15),
        PROXY(16),
        VPN(17),
        NONE(-1);
        private static final SparseArray<NetworkType> valueMap;
        private final int value;
        static {
            NetworkType networkType = null;
            NetworkType networkType2 = null;
            NetworkType networkType3 = null;
            NetworkType networkType4 = null;
            NetworkType networkType5 = null;
            NetworkType networkType6 = null;
            NetworkType networkType7 = null;
            NetworkType networkType8 = null;
            NetworkType networkType9 = null;
            NetworkType networkType10 = null;
            NetworkType networkType11 = null;
            NetworkType networkType12 = null;
            NetworkType networkType13 = null;
            NetworkType networkType14 = null;
            NetworkType networkType15 = null;
            NetworkType networkType16 = null;
            NetworkType networkType17 = null;
            NetworkType networkType18 = null;
            NetworkType networkType19 = null;
            SparseArray<NetworkType> sparseArray = new SparseArray<>();
            valueMap = sparseArray;
            sparseArray.put(0, networkType14);
            sparseArray.put(1, networkType11);
            sparseArray.put(2, networkType8);
            sparseArray.put(3, networkType5);
            sparseArray.put(4, networkType2);
            sparseArray.put(5, networkType);
            sparseArray.put(6, networkType3);
            sparseArray.put(7, networkType4);
            sparseArray.put(8, networkType6);
            sparseArray.put(9, networkType7);
            sparseArray.put(10, networkType9);
            sparseArray.put(11, networkType10);
            sparseArray.put(12, networkType12);
            sparseArray.put(13, networkType13);
            sparseArray.put(14, networkType15);
            sparseArray.put(15, networkType16);
            sparseArray.put(16, networkType18);
            sparseArray.put(17, networkType17);
            sparseArray.put(-1, networkType19);
        }
        NetworkType(int i2) {
            this.value = i2;
        }
        public int getValue() {
            return this.value;
        }
        public static NetworkType forNumber(int i2) {
            return valueMap.get(i2);
        }
    }
    public enum MobileSubtype {
        UNKNOWN_MOBILE_SUBTYPE(0),
        GPRS(1),
        EDGE(2),
        UMTS(3),
        CDMA(4),
        EDO_0(5),
        EDO_A(6),
        RTT(7),
        PATHS(8),
        SUPAN(9),
        SPA(10),
        IDEN(11),
        EDO_B(12),
        LTE(13),
        EHRHARD(14),
        SPAHN(15),
        GSM(16),
        TD_SCHEMA(17),
        IWLAN(18),
        LTE_CA(19),
        COMBINED(100);
        private static final SparseArray<MobileSubtype> valueMap;
        private final int value;
        static {
            MobileSubtype mobileSubtype = null;
            MobileSubtype mobileSubtype2 = null;
            MobileSubtype mobileSubtype3 = null;
            MobileSubtype mobileSubtype4 = null;
            MobileSubtype mobileSubtype5 = null;
            MobileSubtype mobileSubtype6 = null;
            MobileSubtype mobileSubtype7 = null;
            MobileSubtype mobileSubtype8 = null;
            MobileSubtype mobileSubtype9 = null;
            MobileSubtype mobileSubtype10 = null;
            MobileSubtype mobileSubtype11 = null;
            MobileSubtype mobileSubtype12 = null;
            MobileSubtype mobileSubtype13 = null;
            MobileSubtype mobileSubtype14 = null;
            MobileSubtype mobileSubtype15 = null;
            MobileSubtype mobileSubtype16 = null;
            MobileSubtype mobileSubtype17 = null;
            MobileSubtype mobileSubtype18 = null;
            MobileSubtype mobileSubtype19 = null;
            MobileSubtype mobileSubtype20 = null;
            SparseArray<MobileSubtype> sparseArray = new SparseArray<>();
            valueMap = sparseArray;
            sparseArray.put(0, mobileSubtype14);
            sparseArray.put(1, mobileSubtype11);
            sparseArray.put(2, mobileSubtype8);
            sparseArray.put(3, mobileSubtype5);
            sparseArray.put(4, mobileSubtype2);
            sparseArray.put(5, mobileSubtype);
            sparseArray.put(6, mobileSubtype3);
            sparseArray.put(7, mobileSubtype4);
            sparseArray.put(8, mobileSubtype6);
            sparseArray.put(9, mobileSubtype7);
            sparseArray.put(10, mobileSubtype9);
            sparseArray.put(11, mobileSubtype10);
            sparseArray.put(12, mobileSubtype12);
            sparseArray.put(13, mobileSubtype13);
            sparseArray.put(14, mobileSubtype15);
            sparseArray.put(15, mobileSubtype16);
            sparseArray.put(16, mobileSubtype17);
            sparseArray.put(17, mobileSubtype19);
            sparseArray.put(18, mobileSubtype18);
            sparseArray.put(19, mobileSubtype20);
        }
        MobileSubtype(int i2) {
            this.value = i2;
        }
        public int getValue() {
            return this.value;
        }
        public static MobileSubtype forNumber(int i2) {
            return valueMap.get(i2);
        }
    }
    public static Builder builder() {
        return new AutoValue_NetworkConnectionInfo.Builder();
    }
}
