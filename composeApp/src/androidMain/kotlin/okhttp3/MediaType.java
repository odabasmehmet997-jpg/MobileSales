package okhttp3;

import com.fasterxml.jackson.core.JsonFactory;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static kotlin.jvm.internal.Intrinsics.areEqual;
import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public final class MediaType {
    public static final Companion Companion = new Companion(null);
    private static final String QUOTED = "\"([^\"]*)\"";
    private static final String TOKEN = "([a-zA-Z0-9-!#%&'*+.^_`{|}~]+)";
    private static final Pattern TYPE_SUBTYPE = Pattern.compile("([a-zA-Z0-9-!#%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#%&'*+.^_`{|}~]+)");
    private static final Pattern PARAMETER = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");
    private final String mediaType;
    private final String[] parameterNamesAndValues;
    private final String subtype;
    private final String type;
    public MediaType(final String str, final String str2, final String str3, final String[] strArr, final DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, strArr);
    }
    private MediaType(final String str, final String str2, final String str3, final String[] strArr) {
        mediaType = str;
        type = str2;
        subtype = str3;
        parameterNamesAndValues = strArr;
    }
    public static MediaType get(final String str) {
        return MediaType.Companion.get(str);
    }
    public static MediaType parse(final String str) {
        return MediaType.Companion.parse(str);
    }
    public static Charset charsetdefault(final MediaType mediaType, Charset charset, final int i2, final Object obj) {
        if (0 != (i2 & 1)) {
            charset = null;
        }
        return mediaType.charset(charset);
    }
    public Charset charset() {
        return MediaType.charsetdefault(this, null, 1, null);
    }
    public String type() {
        return type;
    }
    public String subtype() {
        return subtype;
    }
    public Charset charset(final Charset charset) {
        final String strParameter = this.parameter("charset");
        if (null == strParameter) {
            return charset;
        }
        try {
            return Charset.forName(strParameter);
        } catch (final IllegalArgumentException unused) {
            return charset;
        }
    }
    public String parameter(final String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        int i2 = 0;
      ProgressionUtilKt progressionUtil = null;
      final int progressionLastElement = progressionUtil.getProgressionLastElement(0, parameterNamesAndValues.length - 1, 2);
        if (0 > progressionLastElement) {
            return null;
        }
        while (true) {
            final int i3 = i2 + 2;
            if (StringsKt.equals(parameterNamesAndValues[i2], name, true)) {
                return parameterNamesAndValues[i2 + 1];
            }
            if (i2 == progressionLastElement) {
                return null;
            }
            i2 = i3;
        }
    }
    public String m1790deprecated_type() {
        return type;
    }
    public String m1789deprecated_subtype() {
        return subtype;
    }
    public String toString() {
        return mediaType;
    }
    public boolean equals(final Object obj) {
        return (obj instanceof MediaType) && areEqual(((MediaType) obj).mediaType, mediaType);
    }
    public int hashCode() {
        return mediaType.hashCode();
    }

    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public MediaType get(final String str) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            final Matcher matcher = TYPE_SUBTYPE.matcher(str);
            if (!matcher.lookingAt()) {
                throw new IllegalArgumentException(("No subtype found for: \"" + str + JsonFactory.DEFAULT_QUOTE_CHAR));
            }
            final String strGroup = matcher.group(1);
            checkNotNullExpressionValue(strGroup, "typeSubtype.group(1)");
            final Locale US = Locale.US;
            checkNotNullExpressionValue(US, "US");
            final String lowerCase = strGroup.toLowerCase(US);
            checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            final String strGroup2 = matcher.group(2);
            checkNotNullExpressionValue(strGroup2, "typeSubtype.group(2)");
            checkNotNullExpressionValue(US, "US");
            final String lowerCase2 = strGroup2.toLowerCase(US);
            checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
            final ArrayList arrayList = new ArrayList();
            final Matcher matcher2 = PARAMETER.matcher(str);
            int iEnd = matcher.end();
            while (iEnd < str.length()) {
                matcher2.region(iEnd, str.length());
                if (!matcher2.lookingAt()) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("Parameter is not formatted correctly: \"");
                    final String strSubstring = str.substring(iEnd);
                    checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
                    sb.append(strSubstring);
                    sb.append("\" for: \"");
                    sb.append(str);
                    sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
                    throw new IllegalArgumentException(sb.toString());
                }
                final String strGroup3 = matcher2.group(1);
                if (null == strGroup3) {
                    iEnd = matcher2.end();
                } else {
                    String strGroup4 = matcher2.group(2);
                    if (null == strGroup4) {
                        strGroup4 = matcher2.group(3);
                    } else if (StringsKt.startsWith(strGroup4, "'", false) && StringsKt.endsWith  (strGroup4, "'", false) && 2 < strGroup4.length()) {
                        strGroup4 = strGroup4.substring(1, strGroup4.length() - 1);
                        checkNotNullExpressionValue(strGroup4, "this as java.lang.String\u2026ing(startIndex, endIndex)");
                    }
                    arrayList.add(strGroup3);
                    arrayList.add(strGroup4);
                    iEnd = matcher2.end();
                }
            }
            final Object[] array = arrayList.toArray(new String[0]);
            if (null == array) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            }
            return new MediaType(str, lowerCase, lowerCase2, (String[]) array, null);
        }
        public MediaType parse(final String str) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            try {
                return this.get(str);
            } catch (final IllegalArgumentException unused) {
                return null;
            }
        }
        public MediaType m1791deprecated_get(final String mediaType) {
            Intrinsics.checkNotNullParameter(mediaType, "mediaType");
            return this.get(mediaType);
        }
        public MediaType m1792deprecated_parse(final String mediaType) {
            Intrinsics.checkNotNullParameter(mediaType, "mediaType");
            return this.parse(mediaType);
        }
    }
}
