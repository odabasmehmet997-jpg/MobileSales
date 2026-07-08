package okhttp3;

import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import static kotlin.jvm.internal.Intrinsics.areEqual;
import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public final class Challenge {
    private final Map<String, String> authParams;
    private final String scheme;
    public Challenge(final String scheme, final Map<String, String> authParams) {
        String lowerCase;
        Intrinsics.checkNotNullParameter(scheme, "scheme");
        Intrinsics.checkNotNullParameter(authParams, "authParams");
        this.scheme = scheme;
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (final Map.Entry<String, String> entry : authParams.entrySet()) {
            final String key = entry.getKey();
            final String value = entry.getValue();
            if (null == key) {
                lowerCase = null;
            } else {
                final Locale US = Locale.US;
                checkNotNullExpressionValue(US, "US");
                lowerCase = key.toLowerCase(US);
                checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            }
            linkedHashMap.put(lowerCase, value);
        }
        final Map<String, String> mapUnmodifiableMap = Collections.unmodifiableMap(linkedHashMap);
        checkNotNullExpressionValue(mapUnmodifiableMap, "unmodifiableMap<String?, String>(newAuthParams)");
        this.authParams = mapUnmodifiableMap;
    }
    public Challenge(final String scheme, final String realm) {
        Intrinsics.checkNotNullParameter(scheme, "scheme");
        Intrinsics.checkNotNullParameter(realm, "realm");
        final Map mapSingletonMap = Collections.singletonMap("realm", realm);
        checkNotNullExpressionValue(mapSingletonMap, "singletonMap(\"realm\", realm)");
        this(scheme, (Map<String, String>) mapSingletonMap);
    }
    public String scheme() {
        return scheme;
    }
    public Map<String, String> authParams() {
        return authParams;
    }
    public String realm() {
        return authParams.get("realm");
    }
    public Charset charset() {
        final String str = authParams.get("charset");
        if (null != str) {
            try {
                final Charset charsetForName = Charset.forName(str);
                checkNotNullExpressionValue(charsetForName, "forName(charset)");
                return charsetForName;
            } catch (final Exception unused) {
            }
        }
        final Charset ISO_8859_1 = StandardCharsets.ISO_8859_1;
        checkNotNullExpressionValue(ISO_8859_1, "ISO_8859_1");
        return ISO_8859_1;
    }
    public Challenge withCharset(final Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "charset");
        final Map mutableMap = MapsKt.toMutableMap(authParams);
        final String strName = charset.name();
        checkNotNullExpressionValue(strName, "charset.name()");
        mutableMap.put("charset", strName);
        return new Challenge(scheme, (Map<String, String>) mutableMap);
    }
    public String m1740deprecated_scheme() {
        return scheme;
    }
    public Map<String, String> m1737deprecated_authParams() {
        return authParams;
    }
    public String m1739deprecated_realm() {
        return this.realm();
    }
    public Charset m1738deprecated_charset() {
        return this.charset();
    }
    public boolean equals(final Object obj) {
        if (obj instanceof Challenge challenge) {
            return areEqual(challenge.scheme, scheme) && areEqual(challenge.authParams, authParams);
        }
        return false;
    }
    public int hashCode() {
        return ((899 + scheme.hashCode()) * 31) + authParams.hashCode();
    }
    public String toString() {
        return scheme + " authParams=" + authParams;
    }
    public void dispose() {
    }
}
