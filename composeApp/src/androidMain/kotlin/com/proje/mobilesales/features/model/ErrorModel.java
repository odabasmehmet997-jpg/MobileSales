package com.proje.mobilesales.features.model;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public record ErrorModel(String message, List<Integer> resourceIds) {
    public static ErrorModel copydefault(final ErrorModel errorModel, String str, List list, final int i2, final Object obj) {
        if (0 != (i2 & 1)) {
            str = errorModel.message;
        }
        if (0 != (i2 & 2)) {
            list = errorModel.resourceIds;
        }
        return errorModel.copy(str, list);
    }

    public String component1() {
        return message;
    }

    public List<Integer> component2() {
        return resourceIds;
    }

    public ErrorModel copy(final String str, final List<Integer> resourceIds) {
        Intrinsics.checkNotNullParameter(resourceIds, "resourceIds");
        return new ErrorModel(str, resourceIds);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ErrorModel errorModel)) {
            return false;
        }
        return Intrinsics.areEqual(message, errorModel.message) && Intrinsics.areEqual(resourceIds, errorModel.resourceIds);
    }

    public int hashCode() {
        final String str = message;
        return ((null == str ? 0 : str.hashCode()) * 31) + resourceIds.hashCode();
    }

    public String toString() {
        return "ErrorModel(message=" + message + ", resourceIds=" + resourceIds + ')';
    }

    public ErrorModel {
        Intrinsics.checkNotNullParameter(resourceIds, "resourceIds");
    }

    public ErrorModel(final String str, final List list, final int i2, final DefaultConstructorMarker defaultConstructorMarker) {
        this(str, 0 != (i2 & 2) ? new ArrayList() : list);
    }
}
