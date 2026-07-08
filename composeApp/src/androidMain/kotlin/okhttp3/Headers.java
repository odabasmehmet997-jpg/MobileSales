package okhttp3;

import android.os.Build;
import com.google.android.gms.common.api.DataBufferResponse;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.proje.mobilesales.features.sales.view.newadd.T;
import kotlin.Tuples3;
import kotlin.collections.CollectionsKt;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PrimitiveCompanionObjects;
import kotlin.jvm.internal.markers.KMarkers;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import okhttp3.internal.http.DatesKt;

import java.time.Instant;
import java.util.*;

import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;
import static kotlin.jvm.internal.Intrinsics.stringPlus;
import static kotlin.jvm.internal.PrimitiveCompanionObjects.*;
import static kotlin.text.StringsKt.getCASE_INSENSITIVE_ORDER;

public final class Headers implements Iterable<Tuples<String, String>>, KMarkers {
    public static final Companion Companion = new Companion(null);
    private final String[] namesAndValues;
    private DataBufferResponse<Object, AbstractDataBuffer<T>> ArrayIterator2;

    public Headers(final String[] strArr, final DefaultConstructorMarker defaultConstructorMarker) {
        this(strArr);
    }

    private Headers(final String[] strArr) {
        namesAndValues = strArr;
    }
    public static Headers of(final Map<String, String> map) {
        return Headers.Companion.of(map);
    }
    public static Headers of(final String... strArr) {
        return Headers.Companion.of(strArr);
    }
    public static Headers of(String contentDisposition, String s, String s1, String transferEncoding) {
        return Headers.Companion.of(contentDisposition, s, s1, transferEncoding);
    }

    public String get(final String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return Headers.Companion.get(namesAndValues, name);
    }

    public Date getDate(final String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        final String str = this.get(name);
        if (null == str) {
            return null;
        }
        DatesKt dates = null;
        return DatesKt.toHttpDateOrNull(str);
    }
    public Instant getInstant(final String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        final Date date = this.getDate(name);
        if (null == date) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return date.toInstant();
        }
        return null;
    }

    public int size() {
        return namesAndValues.length / 2;
    }

    public int m1763deprecated_size() {
        return this.size();
    }

    public String name(final int i2) {
        return namesAndValues[i2 * 2];
    }

    public String value(final int i2) {
        return namesAndValues[(i2 * 2) + 1];
    }

    public Set<String> names() {
        final TreeSet treeSet = new TreeSet(getCASE_INSENSITIVE_ORDER(INSTANCE));
        final int size = this.size();
        for (int i2 = 0; i2 < size; i2++) {
            treeSet.add(this.name(i2));
        }
        final Set<String> setUnmodifiableSet = Collections.unmodifiableSet(treeSet);
        checkNotNullExpressionValue(setUnmodifiableSet, "unmodifiableSet(result)");
        return setUnmodifiableSet;
    }

    private Collection getCASE_INSENSITIVE_ORDER(PrimitiveCompanionObjects instance) {
        return Collections.emptyList();
    }

    public List<String> values(final String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        final int size = this.size();
        ArrayList arrayList = null;
        int i2 = 0;
        while (i2 < size) {
            final int i3 = i2 + 1;
            if (StringsKt.equals(name, this.name(i2), true)) {
                if (null == arrayList) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(this.value(i2));
            }
            i2 = i3;
        }
        if (null != arrayList) {
            final List<String> listUnmodifiableList = Collections.unmodifiableList(arrayList);
            checkNotNullExpressionValue(listUnmodifiableList, "{\n      Collections.unmodifiableList(result)\n    }");
            return listUnmodifiableList;
        }
        return CollectionsKt.emptyList();
    }

    public long byteCount() {
        final String[] strArr = namesAndValues;
        long length = strArr.length * 2L;
        for (int i2 = 0; i2 < strArr.length; i2++) {
            length += namesAndValues[i2].length();
        }
        return length;
    }
    public Iterator<Tuples<String, String>> iterator() {
        final int size = this.size();
        final Tuples[] tuplesArr = new Tuples[size];
        for (int i2 = 0; i2 < size; i2++) tuplesArr[i2] = Tuples3.m637to(this.name(i2), this.value(i2));
        return ArrayIterator2.iterator(tuplesArr);
    }
    public Builder newBuilder() {
        final Builder builder = new Builder();
        CollectionsKt.addAll(builder.getNamesAndValuesokhttp(), namesAndValues);
        return builder;
    }
    public boolean equals(final Object obj) {
        return (obj instanceof Headers) && Arrays.equals(namesAndValues, ((Headers) obj).namesAndValues);
    }
    public int hashCode() {
        return Arrays.hashCode(namesAndValues);
    }
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        final int size = this.size();
        int i2 = 0;
        while (i2 < size) {
            final int i3 = i2 + 1;
            final String strName = this.name(i2);
            String strValue = this.value(i2);
            sb.append(strName);
            sb.append(": ");
            if (Util.isSensitiveHeader(strName)) {
                strValue = "\u2588\u2588";
            }
            sb.append(strValue);
            sb.append(SqlLiteVariable._NEW_LINE);
            i2 = i3;
        }
        final String string = sb.toString();
        checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
    public Map<String, List<String>> toMultimap() {
        final TreeMap treeMap = new TreeMap((Comparator) getCASE_INSENSITIVE_ORDER(INSTANCE));
        final int size = this.size();
        int i2 = 0;
        while (i2 < size) {
            final int i3 = i2 + 1;
            final String strName = this.name(i2);
            final Locale US = Locale.US;
            checkNotNullExpressionValue(US, "US");
            final String lowerCase = strName.toLowerCase(US);
            checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            List<String> arrayList = (List<String>) treeMap.get(lowerCase);
            if (arrayList == null) {
                arrayList = new ArrayList<>(2);
                treeMap.put(lowerCase, arrayList);
            }
            arrayList.add(this.value(i2));
            i2 = i3;
        }
        return treeMap;
    }
    public static final class Builder {
        private final List<String> namesAndValues = new ArrayList<>(20);

        public List<String> getNamesAndValuesokhttp() {
            return namesAndValues;
        }

        public Builder addLenientokhttp(final String line) {
            Intrinsics.checkNotNullParameter(line, "line");
            final int iIndexOfdefault = StringsKt.indexOf( line, ':', 1, false);
            if (-1 != iIndexOfdefault) {
                final String strSubstring = line.substring(0, iIndexOfdefault);
                checkNotNullExpressionValue(strSubstring, "this as java.lang.String\u2026ing(startIndex, endIndex)");
                final String strSubstring2 = line.substring(iIndexOfdefault + 1);
                checkNotNullExpressionValue(strSubstring2, "this as java.lang.String).substring(startIndex)");
                this.addLenientokhttp(strSubstring, strSubstring2);
            } else if (':' == line.charAt(0)) {
                final String strSubstring3 = line.substring(1);
                checkNotNullExpressionValue(strSubstring3, "this as java.lang.String).substring(startIndex)");
                this.addLenientokhttp("", strSubstring3);
            } else {
                this.addLenientokhttp("", line);
            }
            return this;
        }

        public Builder add(final String line) {
            Intrinsics.checkNotNullParameter(line, "line");
            final int iIndexOfdefault = StringsKt.indexOf(line, ':', 0, false);
            if (-1 == iIndexOfdefault) {
                throw new IllegalArgumentException(stringPlus("Unexpected header: ", line));
            }
            final String strSubstring = line.substring(0, iIndexOfdefault);
            checkNotNullExpressionValue(strSubstring, "this as java.lang.String\u2026ing(startIndex, endIndex)");
            final String string = StringsKt.trim(strSubstring).toString();
            final String strSubstring2 = line.substring(iIndexOfdefault + 1);
            checkNotNullExpressionValue(strSubstring2, "this as java.lang.String).substring(startIndex)");
            this.add(string, strSubstring2);
            return this;
        }

        public Builder add(final String name, final String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            final Companion companion = Companion;
            companion.checkName(name);
            companion.checkValue(value, name);
            this.addLenientokhttp(name, value);
            return this;
        }

        public Builder addUnsafeNonAscii(final String name, final String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Companion.checkName(name);
            this.addLenientokhttp(name);
            return this;
        }

        public Builder addAll(final Headers headers) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            final int size = headers.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.addLenientokhttp(headers.name(i2));
            }
            return this;
        }

        public Builder add(final String name, final Date value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            DatesKt dates = null;
            add(name, DatesKt.toHttpDateString(value));
            return this;
        }
        public Builder add(final String name, final Instant value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                this.add(name, new Date(value.toEpochMilli()));
            }
            return this;
        }

        public Builder set(final String name, final Date value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            DatesKt dates = null;
            set(name, DatesKt.toHttpDateString(value));
            return this;
        }
        public Builder set(final String name, final Instant value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return this.set(name, new Date(value.toEpochMilli()));
            }
        }

        public Builder addLenientokhttp(final String name, final String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            this.namesAndValues.add(name);
            this.namesAndValues.add(StringsKt.trim(value).toString());
            return this;
        }

        public Builder removeAll(final String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            int i2 = 0;
            while (i2 < this.namesAndValues.size()) {
                if (StringsKt.equals(name, this.namesAndValues.get(i2), true)) {
                    this.namesAndValues.remove(i2);
                    this.namesAndValues.remove(i2);
                    i2 -= 2;
                }
                i2 += 2;
            }
            return this;
        }

        public Builder set(final String name, final String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            final Companion companion = Companion;
            companion.checkName(name);
            companion.checkValue(value, name);
            this.removeAll(name);
            this.addLenientokhttp(name, value);
            return this;
        }

        public String get(final String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            int size = namesAndValues.size() - 2;
            ProgressionUtilKt progressionUtil = null;
            final int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(size, 0, -2);
            if (progressionLastElement > size) {
                return null;
            }
            while (true) {
                final int i2 = size - 2;
                if (StringsKt.equals(name, namesAndValues.get(size), true)) {
                    return namesAndValues.get(size + 1);
                }
                if (size == progressionLastElement) {
                    return null;
                }
                size = i2;
            }
        }
        public Headers build() {
            final Object[] array = namesAndValues.toArray(new String[0]);
            if (null == array) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            }
            return new Headers((String[]) array, null);
        }

        public void addLenient(String s) {
        }
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
        private String get(final String[] strArr, final String str) {
            int length = strArr.length - 2;
            ProgressionUtilKt progressionUtil = null;
            final int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(length, 0, -2);
            if (progressionLastElement > length) {
                return null;
            }
            while (true) {
                final int i2 = length - 2;
                if (StringsKt.equals(str, strArr[length], true)) {
                    return strArr[length + 1];
                }
                if (length == progressionLastElement) {
                    return null;
                }
                length = i2;
            }
        }

        public Headers m648of(final String... namesAndValues) {
            Intrinsics.checkNotNullParameter(namesAndValues, "namesAndValues");
            if (0 != namesAndValues.length % 2) {
                throw new IllegalArgumentException("Expected alternating header names and values");
            }
            final String[] strArr = namesAndValues.clone();
            final int length = strArr.length;
            int i2 = 0;
            int i3 = 0;
            while (i3 < length) {
                final int i4 = i3 + 1;
                final String str = strArr[i3];
                if (null == str) {
                    throw new IllegalArgumentException("Headers cannot be null");
                }
                strArr[i3] = StringsKt.trim(str).toString();
                i3 = i4;
            }
            ProgressionUtilKt progressionUtil = null;
            final int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(0, strArr.length - 1, 2);
            if (0 <= progressionLastElement) {
                while (true) {
                    final int i5 = i2 + 2;
                    final String str2 = strArr[i2];
                    final String str3 = strArr[i2 + 1];
                    this.checkName(str2);
                    this.checkValue(str3, str2);
                    if (i2 == progressionLastElement) {
                        break;
                    }
                    i2 = i5;
                }
            }
            return new Headers(strArr, null);
        }
        public Headers m1765deprecated_of(final String... namesAndValues) {
            Intrinsics.checkNotNullParameter(namesAndValues, "namesAndValues");
            return this.m648of(Arrays.copyOf(namesAndValues, namesAndValues.length));
        }
        public Headers m647of(final Map<String, String> map) {
            Intrinsics.checkNotNullParameter(map, "<this>");
            final String[] strArr = new String[map.size() * 2];
            int i2 = 0;
            for (final Map.Entry<String, String> entry : map.entrySet()) {
                final String key = entry.getKey();
                final String value = entry.getValue();
                final String string = StringsKt.trim(key).toString();
                final String string2 = StringsKt.trim(value).toString();
                this.checkName(string);
                this.checkValue(string2, string);
                strArr[i2] = string;
                strArr[i2 + 1] = string2;
                i2 += 2;
            }
            return new Headers(strArr, null);
        }
        public Headers m1764deprecated_of(final Map<String, String> headers) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            return this.m647of(headers);
        }

        private void checkName(final String str) {
            if (0 >= str.length()) {
                throw new IllegalArgumentException("name is empty");
            }
            final int length = str.length();
            int i2 = 0;
            while (i2 < length) {
                final int i3 = i2 + 1;
                final char cCharAt = str.charAt(i2);
                if ('!' > cCharAt || '\u007f' <= cCharAt) {
                    throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(cCharAt), Integer.valueOf(i2), str));
                }
                i2 = i3;
            }
        }

        private void checkValue(final String str, final String str2) {
            final int length = str.length();
            int i2 = 0;
            while (i2 < length) {
                final int i3 = i2 + 1;
                final char cCharAt = str.charAt(i2);
                if ('\t' != cCharAt && (' ' > cCharAt || '\u007f' <= cCharAt)) {
                    throw new IllegalArgumentException(stringPlus(Util.format("Unexpected char %#04x at %d in %s value", Integer.valueOf(cCharAt), Integer.valueOf(i2), str2), Util.isSensitiveHeader(str2) ? "" : stringPlus(": ", str)));
                }
                i2 = i3;
            }
        }

        public Headers of(Map<String, String> map) {
            return null;
        }

        public Headers of(String contentDisposition, String s, String s1, String transferEncoding) {
            return null;
        }

        public Headers of(String[] strings) {
        }
    }
}
