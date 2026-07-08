package com.proje.mobilesales.features.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
 
public class MainActivityExternalSyntheticBackport1 {

    public static List m573m(final Object[] objArr) {
        final ArrayList arrayList = new ArrayList(objArr.length);
        for (final Object obj : objArr) {
            Objects.requireNonNull(obj);
            arrayList.add(obj);
        }
        return Collections.unmodifiableList(arrayList);
    }
}
