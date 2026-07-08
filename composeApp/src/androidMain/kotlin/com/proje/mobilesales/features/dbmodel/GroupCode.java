package com.proje.mobilesales.features.dbmodel;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class GroupCode {

    private String group_code;

    public GroupCode() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static  GroupCode copydefault(GroupCode groupCode, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = groupCode.group_code;
        }
        return groupCode.copy(str);
    }

    public String component1() {
        return this.group_code;
    }

    public GroupCode copy(String str) {
        return new GroupCode(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof GroupCode) && Intrinsics.areEqual(this.group_code, ((GroupCode) obj).group_code);
    }

    public int hashCode() {
        String str = this.group_code;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public String toString() {
        return "GroupCode(group_code=" + this.group_code + ')';
    }

    public GroupCode(String str) {
        this.group_code = str;
    }

    public  GroupCode(String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str);
    }
    public   String getGroup_code() {
        return this.group_code;
    }

    public  void setGroup_code(String str) {
        this.group_code = str;
    }
}
