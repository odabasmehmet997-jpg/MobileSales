package okhttp3;

import com.fasterxml.jackson.core.JsonFactory;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import org.springframework.http.HttpHeaders;

import java.util.concurrent.TimeUnit;

import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;
import static kotlin.jvm.internal.Intrinsics.stringPlus;

public final class CacheControl {
    public static final Companion Companion = new Companion(null);
    public static final CacheControl FORCE_NETWORK = new Builder().noCache().build();
    public static final CacheControl FORCE_CACHE = new Builder().onlyIfCached().maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS).build();
    private final boolean immutable;
    private final boolean isPrivate;
    private final boolean isPublic;
    private final int maxAgeSeconds;
    private final int maxStaleSeconds;
    private final int minFreshSeconds;
    private final boolean mustRevalidate;
    private final boolean noCache;
    private final boolean noStore;
    private final boolean noTransform;
    private final boolean onlyIfCached;
    private final int sMaxAgeSeconds;
    private String headerValue;
    public CacheControl(final boolean z, final boolean z2, final int i2, final int i3, final boolean z3, final boolean z4, final boolean z5, final int i4, final int i5, final boolean z6, final boolean z7, final boolean z8, final String str, final DefaultConstructorMarker defaultConstructorMarker) {
        this(z, z2, i2, i3, z3, z4, z5, i4, i5, z6, z7, z8, str);
    }
    private CacheControl(final boolean z, final boolean z2, final int i2, final int i3, final boolean z3, final boolean z4, final boolean z5, final int i4, final int i5, final boolean z6, final boolean z7, final boolean z8, final String str) {
        noCache = z;
        noStore = z2;
        maxAgeSeconds = i2;
        sMaxAgeSeconds = i3;
        isPrivate = z3;
        isPublic = z4;
        mustRevalidate = z5;
        maxStaleSeconds = i4;
        minFreshSeconds = i5;
        onlyIfCached = z6;
        noTransform = z7;
        immutable = z8;
        headerValue = str;
    }
    public static CacheControl parse(final Headers headers) {
        return CacheControl.Companion.parse(headers);
    }
    public boolean noCache() {
        return noCache;
    }
    public boolean noStore() {
        return noStore;
    }
    public int maxAgeSeconds() {
        return maxAgeSeconds;
    }
    public int sMaxAgeSeconds() {
        return sMaxAgeSeconds;
    }
    public boolean isPrivate() {
        return isPrivate;
    }
    public boolean isPublic() {
        return isPublic;
    }
    public boolean mustRevalidate() {
        return mustRevalidate;
    }
    public int maxStaleSeconds() {
        return maxStaleSeconds;
    }
    public int minFreshSeconds() {
        return minFreshSeconds;
    }
    public boolean onlyIfCached() {
        return onlyIfCached;
    }
    public boolean noTransform() {
        return noTransform;
    }
    public boolean immutable() {
        return immutable;
    }
    public boolean m1732deprecated_noCache() {
        return noCache;
    }
    public boolean m1733deprecated_noStore() {
        return noStore;
    }
    public int m1728deprecated_maxAgeSeconds() {
        return maxAgeSeconds;
    }
    public int m1736deprecated_sMaxAgeSeconds() {
        return sMaxAgeSeconds;
    }
    public boolean m1731deprecated_mustRevalidate() {
        return mustRevalidate;
    }
    public int m1729deprecated_maxStaleSeconds() {
        return maxStaleSeconds;
    }
    public int m1730deprecated_minFreshSeconds() {
        return minFreshSeconds;
    }
    public boolean m1735deprecated_onlyIfCached() {
        return onlyIfCached;
    }
    public boolean m1734deprecated_noTransform() {
        return noTransform;
    }
    public boolean m1727deprecated_immutable() {
        return immutable;
    }
    public String toString() {
        final String str = headerValue;
        if (null != str) {
            return str;
        }
        final StringBuilder sb = new StringBuilder();
        if (this.noCache()) {
            sb.append("no-cache, ");
        }
        if (this.noStore()) {
            sb.append("no-store, ");
        }
        if (-1 != maxAgeSeconds()) {
            sb.append("max-age=");
            sb.append(this.maxAgeSeconds());
            sb.append(", ");
        }
        if (-1 != sMaxAgeSeconds()) {
            sb.append("s-maxage=");
            sb.append(this.sMaxAgeSeconds());
            sb.append(", ");
        }
        if (this.isPrivate) {
            sb.append("private, ");
        }
        if (this.isPublic) {
            sb.append("public, ");
        }
        if (this.mustRevalidate()) {
            sb.append("must-revalidate, ");
        }
        if (-1 != maxStaleSeconds()) {
            sb.append("max-stale=");
            sb.append(this.maxStaleSeconds());
            sb.append(", ");
        }
        if (-1 != minFreshSeconds()) {
            sb.append("min-fresh=");
            sb.append(this.minFreshSeconds());
            sb.append(", ");
        }
        if (this.onlyIfCached()) {
            sb.append("only-if-cached, ");
        }
        if (this.noTransform()) {
            sb.append("no-transform, ");
        }
        if (this.immutable()) {
            sb.append("immutable, ");
        }
        if (0 == sb.length()) {
            return "";
        }
        sb.delete(sb.length() - 2, sb.length());
        final String string = sb.toString();
        checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        headerValue = string;
        return string;
    }
    public static final class Builder {
        private boolean immutable;
        private int maxAgeSeconds = -1;
        private int maxStaleSeconds = -1;
        private int minFreshSeconds = -1;
        private boolean noCache;
        private boolean noStore;
        private boolean noTransform;
        private boolean onlyIfCached;

        private int clampToInt(final long j2) {
            if (2147483647L < j2) {
                return Integer.MAX_VALUE;
            }
            return (int) j2;
        }

        public Builder noCache() {
            noCache = true;
            return this;
        }

        public Builder noStore() {
            noStore = true;
            return this;
        }

        public Builder maxAge(final int i2, final TimeUnit timeUnit) {
            Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
            if (0 > i2) {
                throw new IllegalArgumentException(stringPlus("maxAge < 0: ", Integer.valueOf(i2)));
            }
            maxAgeSeconds = this.clampToInt(timeUnit.toSeconds(i2));
            return this;
        }

        public Builder maxStale(final int i2, final TimeUnit timeUnit) {
            Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
            if (0 > i2) {
                throw new IllegalArgumentException(stringPlus("maxStale < 0: ", Integer.valueOf(i2)));
            }
            maxStaleSeconds = this.clampToInt(timeUnit.toSeconds(i2));
            return this;
        }

        public Builder minFresh(final int i2, final TimeUnit timeUnit) {
            Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
            if (0 > i2) {
                throw new IllegalArgumentException(stringPlus("minFresh < 0: ", Integer.valueOf(i2)));
            }
            minFreshSeconds = this.clampToInt(timeUnit.toSeconds(i2));
            return this;
        }

        public Builder onlyIfCached() {
            onlyIfCached = true;
            return this;
        }

        public Builder noTransform() {
            noTransform = true;
            return this;
        }

        public Builder immutable() {
            immutable = true;
            return this;
        }

        public CacheControl build() {
            return new CacheControl(noCache, noStore, maxAgeSeconds, -1, false, false, false, maxStaleSeconds, minFreshSeconds, onlyIfCached, noTransform, immutable, null, null);
        }
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        static int indexOfElementdefault(final Companion companion, final String str, final String str2, int i2, final int i3, final Object obj) {
            if (0 != (i3 & 2)) {
                i2 = 0;
            }
            return companion.indexOfElement(str, str2, i2);
        }

        /*  WARN: Removed duplicated region for block: B:15:0x004d  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public CacheControl parse(final Headers headers) {
            int i2;
            int iIndexOfElement;
            String string;
            Headers headers2 = headers;
            Intrinsics.checkNotNullParameter(headers2, "headers");
            final int size = headers.size();
            boolean z = true;
            boolean z2 = true;
            int i3 = 0;
            String str = null;
            boolean z3 = false;
            boolean z4 = false;
            int nonNegativeInt = -1;
            int nonNegativeInt2 = -1;
            boolean z5 = false;
            boolean z6 = false;
            boolean z7 = false;
            int nonNegativeInt3 = -1;
            int nonNegativeInt4 = -1;
            boolean z8 = false;
            boolean z9 = false;
            boolean z10 = false;
            while (i3 < size) {
                final int i4 = i3 + 1;
                final String strName = headers2.name(i3);
                final String strValue = headers2.value(i3);
                if (!StringsKt.equals(strName, HttpHeaders.CACHE_CONTROL, z)) {
                    if (!StringsKt.equals(strName, HttpHeaders.PRAGMA, z)) {
                        headers2 = headers;
                        i3 = i4;
                    }
                } else {
                    if (null == str) {
                        str = strValue;
                    }
                    i2 = 0;
                    while (i2 < strValue.length()) {
                        final int iIndexOfElement2 = this.indexOfElement(strValue, "=,;", i2);
                        final String strSubstring = strValue.substring(i2, iIndexOfElement2);
                        checkNotNullExpressionValue(strSubstring, "this as java.lang.String\u2026ing(startIndex, endIndex)");
                        final String string2 = StringsKt.trim(strSubstring).toString();
                        if (iIndexOfElement2 == strValue.length() || ',' == strValue.charAt(iIndexOfElement2) || ';' == strValue.charAt(iIndexOfElement2)) {
                            iIndexOfElement = iIndexOfElement2 + 1;
                            string = null;
                        } else {
                            final int iIndexOfNonWhitespace = Util.indexOfNonWhitespace(strValue, iIndexOfElement2 + 1);
                            if (iIndexOfNonWhitespace < strValue.length() && '\"' == strValue.charAt(iIndexOfNonWhitespace)) {
                                final int i5 = iIndexOfNonWhitespace + 1;
                                final int iIndexOfdefault = StringsKt.indexOf(strValue, JsonFactory.DEFAULT_QUOTE_CHAR, i5, false);
                                string = strValue.substring(i5, iIndexOfdefault);
                                checkNotNullExpressionValue(string, "this as java.lang.String\u2026ing(startIndex, endIndex)");
                                iIndexOfElement = iIndexOfdefault + 1;
                            } else {
                                iIndexOfElement = this.indexOfElement(strValue, ",;", iIndexOfNonWhitespace);
                                final String strSubstring2 = strValue.substring(iIndexOfNonWhitespace, iIndexOfElement);
                                checkNotNullExpressionValue(strSubstring2, "this as java.lang.String\u2026ing(startIndex, endIndex)");
                                string = StringsKt.trim(strSubstring2).toString();
                            }
                        }
                        if (StringsKt.equals("no-cache", string2, true)) {
                            i2 = iIndexOfElement;
                            z = true;
                            z3 = true;
                        } else if (StringsKt.equals("no-store", string2, true)) {
                            i2 = iIndexOfElement;
                            z = true;
                            z4 = true;
                        } else {
                            if (StringsKt.equals("max-age", string2, true)) {
                                nonNegativeInt = Util.toNonNegativeInt(string, -1);
                            } else if (StringsKt.equals("s-maxage", string2, true)) {
                                nonNegativeInt2 = Util.toNonNegativeInt(string, -1);
                            } else if (StringsKt.equals("private", string2, true)) {
                                i2 = iIndexOfElement;
                                z = true;
                                z5 = true;
                            } else if (StringsKt.equals("public", string2, true)) {
                                i2 = iIndexOfElement;
                                z = true;
                                z6 = true;
                            } else if (StringsKt.equals("must-revalidate", string2, true)) {
                                i2 = iIndexOfElement;
                                z = true;
                                z7 = true;
                            } else if (StringsKt.equals("max-stale", string2, true)) {
                                nonNegativeInt3 = Util.toNonNegativeInt(string, Integer.MAX_VALUE);
                            } else if (StringsKt.equals("min-fresh", string2, true)) {
                                nonNegativeInt4 = Util.toNonNegativeInt(string, -1);
                            } else if (StringsKt.equals("only-if-cached", string2, true)) {
                                i2 = iIndexOfElement;
                                z = true;
                                z8 = true;
                            } else if (StringsKt.equals("no-transform", string2, true)) {
                                i2 = iIndexOfElement;
                                z = true;
                                z9 = true;
                            } else if (StringsKt.equals("immutable", string2, true)) {
                                i2 = iIndexOfElement;
                                z = true;
                                z10 = true;
                            }
                            i2 = iIndexOfElement;
                            z = true;
                        }
                    }
                    headers2 = headers;
                    i3 = i4;
                }
                z2 = false;
                i2 = 0;
                while (i2 < strValue.length()) {
                }
                headers2 = headers;
                i3 = i4;
            }
            return new CacheControl(z3, z4, nonNegativeInt, nonNegativeInt2, z5, z6, z7, nonNegativeInt3, nonNegativeInt4, z8, z9, z10, !z2 ? null : str, null);
        }

        private int indexOfElement(final String str, final String str2, int i2) {
            final int length = str.length();
            while (i2 < length) {
                final int i3 = i2 + 1;
                if (StringsKt.contains(str2, str.charAt(i2), false)) {
                    return i2;
                }
                i2 = i3;
            }
            return str.length();
        }
    }
}
