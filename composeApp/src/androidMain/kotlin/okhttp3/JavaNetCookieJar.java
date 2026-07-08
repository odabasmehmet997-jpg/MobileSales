package okhttp3;

import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;
import org.springframework.http.HttpHeaders;
import java.io.IOException;
import java.net.CookieHandler;
import java.util.*;

import static kotlin.jvm.internal.Intrinsics.checkNotNull;
import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public final class JavaNetCookieJar implements CookieJar {
    private final CookieHandler cookieHandler;
     private Internal internal;
    private Object Tuples3;
    public JavaNetCookieJar(final CookieHandler cookieHandler) {
        Intrinsics.checkNotNullParameter(cookieHandler, "cookieHandler");
        this.cookieHandler = cookieHandler;
    }
    public void saveFromResponse(final HttpUrl url, final List<Cookie> cookies) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(cookies, "cookies");
        final ArrayList arrayList = new ArrayList();
        final Iterator<Cookie> it = cookies.iterator();
        while (it.hasNext()) {
            arrayList.add(internal.cookieToString(it.next(), true));
        }
        try {
            cookieHandler.put(url.uri(), MapsKt.mapOf());
        } catch (final IOException e2) {
            final Platform platform = Platform.Companion.get();
            final StringBuilder sb = new StringBuilder();
            sb.append("Saving cookies failed for ");
            final HttpUrl httpUrlResolve = url.resolve("/...");
            checkNotNull(httpUrlResolve);
            sb.append(httpUrlResolve);
            platform.log(sb.toString(), 5, e2);
        }
    }
    public List<Cookie> loadForRequest(final HttpUrl url) {
        Intrinsics.checkNotNullParameter(url, "url");
        try {
            final Map<String, List<String>> cookieHeaders = cookieHandler.get(url.uri(), MapsKt.emptyMap());
            checkNotNullExpressionValue(cookieHeaders, "cookieHeaders");
            ArrayList arrayList = null;
            for (final Map.Entry<String, List<String>> entry : cookieHeaders.entrySet()) {
                final String key = entry.getKey();
                final List<String> value = entry.getValue();
                if (StringsKt.equals(HttpHeaders.COOKIE, key, true) || StringsKt.equals("Cookie2", key, true)) {
                    checkNotNullExpressionValue(value, "value");
                    if (!value.isEmpty()) {
                        for (final String header : value) {
                            if (null == arrayList) {
                                arrayList = new ArrayList();
                            }
                            checkNotNullExpressionValue(header, "header");
                            arrayList.addAll(this.decodeHeaderAsJavaNetCookies(url, header));
                        }
                    }
                }
            }
            if (null != arrayList) {
                final List<Cookie> listUnmodifiableList = Collections.unmodifiableList(arrayList);
                checkNotNullExpressionValue(listUnmodifiableList, "Collections.unmodifiableList(cookies)");
                return listUnmodifiableList;
            }
            return CollectionsKt.emptyList();
        } catch (final IOException e2) {
            final Platform platform = Platform.Companion.get();
            final StringBuilder sb = new StringBuilder();
            sb.append("Loading cookies failed for ");
            final HttpUrl httpUrlResolve = url.resolve("/...");
            checkNotNull(httpUrlResolve);
            sb.append(httpUrlResolve);
            platform.log(sb.toString(), 5, e2);
            return CollectionsKt.emptyList();
        }
    }
    private List<Cookie> decodeHeaderAsJavaNetCookies(final HttpUrl httpUrl, final String str) {
        String strSubstring;
        final ArrayList arrayList = new ArrayList();
        final int length = str.length();
        int i2 = 0;
        while (i2 < length) {
            final int iDelimiterOffset = Util.delimiterOffset(str, ";,", i2, length);
            final int iDelimiterOffset2 = Util.delimiterOffset(str, '=', i2, iDelimiterOffset);
            final String strTrimSubstring = Util.trimSubstring(str, i2, iDelimiterOffset2);
            if (!StringsKt.startsWith(strTrimSubstring, "", false)) {
                if (iDelimiterOffset2 < iDelimiterOffset) {
                    strSubstring = Util.trimSubstring(str, iDelimiterOffset2 + 1, iDelimiterOffset);
                } else {
                    strSubstring = "";
                }
                if (StringsKt.startsWith(strSubstring, "\"", false) && StringsKt.endsWith(strSubstring, "\"", false)) {
                    strSubstring = strSubstring.substring(1, strSubstring.length() - 1);
                    checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                }
                arrayList.add(new Cookie.Builder().name(strTrimSubstring).value(strSubstring).domain(httpUrl.host()).build());
            }
            i2 = iDelimiterOffset + 1;
        }
        return arrayList;
    }
}
