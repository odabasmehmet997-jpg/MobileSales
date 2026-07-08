package com.gu.toolargetool;

import android.os.Bundle;
import android.os.Parcel;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static androidx.core.graphics.Insets.add;
import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;
public class TooLargeToolKt {
    public static int sizeAsParcel(Bundle paramBundle) {
        Intrinsics.checkNotNullParameter(paramBundle, "bundle");
        Parcel parcel = Parcel.obtain();
        final String str = "Parcel.obtain()";
        checkNotNullExpressionValue(parcel, str);
        try {
            parcel.writeBundle(paramBundle);
            return parcel.dataSize();
        } finally {
            parcel.recycle();
        }
    }
    public static SizeTree sizeTreeFromBundle(Bundle paramBundle) {
        Intrinsics.checkNotNullParameter(paramBundle, "bundle");
        int i = paramBundle.size();
        SizeTree sizeTree = null;
        Bundle bundle = new Bundle();
        int j = sizeAsParcel(paramBundle);
        Set<String> set = bundle.keySet();
        Iterator<String> iterator = set.iterator();
        while (true) {
            boolean bool = iterator.hasNext();
            if (bool) {
                String str1 = iterator.next();
                str1 = str1;
                paramBundle.remove(str1);
                int n = sizeAsParcel(paramBundle);
                j -= n;
                SizeTree sizeTree1 = new SizeTree();
                final String str2 = "key";
                checkNotNullExpressionValue(str1, str2);
                List list = CollectionsKt.emptyList();
                sizeTree1(str1, j, list);
                add(sizeTree1);
                j = n;
                continue;
            }
            paramBundle.putAll(bundle);
            sizeTree = new SizeTree();
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("Bundle");
            int k = System.identityHashCode(paramBundle);
            stringBuilder.append(k);
            String str = stringBuilder.toString();
            int m = sizeAsParcel(paramBundle);
            return sizeTree;
        }
    }
    private static void sizeTree1(String str1, int j, List list) {
    }
    private static void add(SizeTree sizeTree1) {
    }
}
