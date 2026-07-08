package com.proje.mobilesales.core.enums;

import com.proje.mobilesales.core.sql.SqlLiteVariable;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;


public final class ObjectType extends Enum<ObjectType> {
    private static final   EnumEntries ENTRIES;
    private static final   ObjectType[] VALUES;
    private int value;
    public static final ObjectType STRING = new ObjectType("STRING", 0, 0);
    public static final ObjectType INT = new ObjectType("INT", 1, 1);
    public static final ObjectType FLOAT = new ObjectType("FLOAT", 2, 2);
    public static final ObjectType DOUBLE = new ObjectType(SqlLiteVariable._DOUBLE, 3, 3);
    public static final ObjectType BOOL = new ObjectType("BOOL", 4, 4);
    public static final Companion Companion = new Companion(null);
    private static ObjectType[] values() {
        return new ObjectType[]{STRING, INT, FLOAT, DOUBLE, BOOL};
    }
    public static ObjectType fromObjectType(int i) {
        return Companion.fromObjectType(i);
    }
    public static EnumEntries<ObjectType> getEntries() {
        return ENTRIES;
    }
    public static ObjectType valueOf(String str) {
        return Enum.valueOf(ObjectType.class, str);
    }
    public static ObjectType[] values() {
        return VALUES.clone();
    }
    private ObjectType(String str, int i, int i2) {
        super(str,i);
        this.value = i2;
    }
    public int getValue() {
        return this.value;
    }
    public void setValue(int i) {
        this.value = i;
    }
    static {
        ObjectType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public ObjectType fromObjectType(int i) {
            for (ObjectType objectType : ObjectType.getEntries()) {
                if (objectType.getValue() == i) {
                    return objectType;
                }
            }
            return ObjectType.STRING;
        }
    }
}

