package com.proje.mobilesales.features.cabinoperation.model.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
 
public final class CabinEnums {
 
    public static final class CabinLocInfoEnum extends Enum<CabinLocInfoEnum> {
        private static final  EnumEntries ENTRIES;
        private static final   CabinLocInfoEnum[] VALUES;
        private int mValue;
        public static final CabinLocInfoEnum NOTAVAIABLE = new CabinLocInfoEnum("NOTAVAIABLE", 0, 0);
        public static final CabinLocInfoEnum CUSTOMER = new CabinLocInfoEnum("CUSTOMER", 1, 1);
        public static final CabinLocInfoEnum STORAGE = new CabinLocInfoEnum("STORAGE", 2, 2);

        private static    CabinLocInfoEnum[] values() {
            return new CabinLocInfoEnum[]{NOTAVAIABLE, CUSTOMER, STORAGE};
        }

        public static EnumEntries<CabinLocInfoEnum> getEntries() {
            return ENTRIES;
        }

        public static CabinLocInfoEnum valueOf(String str) {
            return Enum.valueOf(CabinLocInfoEnum.class, str);
        }

        public static CabinLocInfoEnum[] values() {
            return VALUES.clone();
        }

        private CabinLocInfoEnum(String str, int i2, int i3) {
            super(str, i2);
            this.mValue = i3;
        }

        public int getMValue() {
            return this.mValue;
        }

        public void setMValue(int i2) {
            this.mValue = i2;
        }

        static {
            CabinLocInfoEnum[] values = values();
            VALUES = values;
            ENTRIES = EnumEntriesKt.enumEntries(values);
        }

        public int getmValue() {
            return this.mValue;
        }

        public void setmValue(int i2) {
            this.mValue = i2;
        }
    } 
    public static final class CabinStatus extends Enum<CabinStatus> {
        private static final  EnumEntries ENTRIES;
        private static final CabinStatus[] VALUES;
        private int mValue;
        public static final CabinStatus NOTAVAIABLE = new CabinStatus("NOTAVAIABLE", 0, 0);
        public static final CabinStatus ACTIVE = new CabinStatus("ACTIVE", 1, 1);
        public static final CabinStatus DEFECTIVE = new CabinStatus("DEFECTIVE", 2, 2);
        public static final CabinStatus DELETED = new CabinStatus("DELETED", 3, 3);

        private static CabinStatus[] values() {
            return new CabinStatus[]{NOTAVAIABLE, ACTIVE, DEFECTIVE, DELETED};
        }

        public static EnumEntries<CabinStatus> getEntries() {
            return ENTRIES;
        }

        public static CabinStatus valueOf(String str) {
            return Enum.valueOf(CabinStatus.class, str);
        }

        public static CabinStatus[] values() {
            return VALUES.clone();
        }

        private CabinStatus(String str, int i2, int i3) {
            super(str, i2);
            this.mValue = i3;
        }

        public int getMValue() {
            return this.mValue;
        }

        public void setMValue(int i2) {
            this.mValue = i2;
        }

        static {
            CabinStatus[] values = values();
            VALUES = values;
            ENTRIES = EnumEntriesKt.enumEntries(values);
        }

        public int getmValue() {
            return this.mValue;
        }

        public void setmValue(int i2) {
            this.mValue = i2;
        }
    }
 
    public static final class CabinTransTrCode extends Enum<CabinTransTrCode> {
        private static final  EnumEntries ENTRIES;
        private static final  CabinTransTrCode[] VALUES;
        private int mValue;
        public static final CabinTransTrCode NOTAVAIABLE = new CabinTransTrCode("NOTAVAIABLE", 0, 0);
        public static final CabinTransTrCode ROAD = new CabinTransTrCode("ROAD", 1, 1);
        public static final CabinTransTrCode STORAGE = new CabinTransTrCode("STORAGE", 2, 2);
        public static final CabinTransTrCode CUSTOMER = new CabinTransTrCode("CUSTOMER", 3, 3);

        private static CabinTransTrCode[] values() {
            return new CabinTransTrCode[]{NOTAVAIABLE, ROAD, STORAGE, CUSTOMER};
        }

        public static EnumEntries<CabinTransTrCode> getEntries() {
            return ENTRIES;
        }

        public static CabinTransTrCode valueOf(String str) {
            return Enum.valueOf(CabinTransTrCode.class, str);
        }

        public static CabinTransTrCode[] values() {
            return VALUES.clone();
        }

        private CabinTransTrCode(String str, int i2, int i3) {
            super(str, i2);
            this.mValue = i3;
        }

        public int getMValue() {
            return this.mValue;
        }

        public void setMValue(int i2) {
            this.mValue = i2;
        }

        static {
            CabinTransTrCode[] values = values();
            VALUES = values;
            ENTRIES = EnumEntriesKt.enumEntries(values);
        }

        public int getmValue() {
            return this.mValue;
        }

        public void setmValue(int i2) {
            this.mValue = i2;
        }
    }
}
