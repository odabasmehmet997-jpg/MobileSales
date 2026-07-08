package com.proje.mobilesales.features.model;

import com.proje.mobilesales.features.dbmodel.DefOrder;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: FormGroupModel.kt */

public final class FormGroupModel {
    private List<DefOrder> groupForms;
    private final String groupName;

    public static FormGroupModel copydefault(final FormGroupModel formGroupModel, String str, final int i2, final Object obj) {
        if (0 != (i2 & 1)) {
            str = formGroupModel.groupName;
        }
        return formGroupModel.copy(str);
    }

    public String component1() {
        return groupName;
    }

    public FormGroupModel copy(final String groupName) {
        Intrinsics.checkNotNullParameter(groupName, "groupName");
        return new FormGroupModel(groupName);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof FormGroupModel) && Intrinsics.areEqual(groupName, ((FormGroupModel) obj).groupName);
    }

    public int hashCode() {
        return groupName.hashCode();
    }

    public String toString() {
        return "FormGroupModel(groupName=" + groupName + ')';
    }

    public FormGroupModel(final String groupName) {
        Intrinsics.checkNotNullParameter(groupName, "groupName");
        this.groupName = groupName;
        groupForms = new ArrayList();
    }

    public String getGroupName() {
        return groupName;
    }

    public List<DefOrder> getGroupForms() {
        return groupForms;
    }

    public void setGroupForms(final List<DefOrder> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        groupForms = list;
    }

    public void addForm(final DefOrder defOrder) {
        Intrinsics.checkNotNullParameter(defOrder, "defOrder");
        groupForms.add(defOrder);
    }

    public void addAllForms(final List<? extends DefOrder> defOrderList) {
        Intrinsics.checkNotNullParameter(defOrderList, "defOrderList");
        groupForms.addAll(defOrderList);
    }
}
