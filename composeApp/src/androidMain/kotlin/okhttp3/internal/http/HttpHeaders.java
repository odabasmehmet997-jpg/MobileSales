package okhttp3.internal.http;

import io.reactivex.internal.disposables.DisposableContainer;
import kotlin.collections.MapsKt;
import kotlin.text.StringsKt;
import okhttp3.*;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.ByteString;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static kotlin.jvm.internal.Intrinsics.areEqual;
import static kotlin.jvm.internal.Intrinsics.checkNotNullParameter;

public class HttpHeaders {
    private static final ByteString QUOTED_STRING_DELIMITERS = ByteString.Companion.encodeUtf8("\"\\");
    private static final ByteString TOKEN_DELIMITERS = ByteString.Companion.encodeUtf8("\t ,=");
    private static DisposableContainer challenges;
    public static List<Challenge> parseChallenges(final Headers headers, final String headerName) {
        checkNotNullParameter(headers, "<this>");
        checkNotNullParameter(headerName, "headerName");
        ArrayList<Challenge> challenges = new ArrayList<>();
        int size = headers.size();
        int i2 = 0;
        while (i2 < size) {
            int i3 = i2 + 1;
            if (StringsKt.equals(headerName, headers.name(i2), true)) {
                try {
                    readChallengeHeader(new Buffer().writeUtf8(headers.value(i2)), challenges);
                } catch (EOFException e2) {
                    Platform.Companion.get().log("Unable to parse challenge", 5, e2);
                }
            }
            i2 = i3;
        }
        return challenges;
    }
    private static void readChallengeHeader(Buffer buffer, List<Challenge> list) throws EOFException {
        String token;
        int iSkipAll;
        LinkedHashMap linkedHashMap = null;
        while (true) {
            String token2 = null;
            while (true) {
                if (null == token2) {
                    skipCommasAndWhitespace(buffer);
                    token2 = readToken(buffer);
                    if (null == token2) {
                        return;
                    }
                }
                boolean zSkipCommasAndWhitespace = skipCommasAndWhitespace(buffer);
                token = readToken(buffer);
                if (null == token) {
                    if (buffer.exhausted()) {
                        list.add(new Challenge(token2, MapsKt.emptyMap()));
                        return;
                    }
                    return;
                }
                iSkipAll = Util.skipAll(buffer, (byte) 61);
                boolean zSkipCommasAndWhitespace2 = skipCommasAndWhitespace(buffer);
                if (zSkipCommasAndWhitespace || (!zSkipCommasAndWhitespace2 && !buffer.exhausted())) {
                    linkedHashMap = new LinkedHashMap();
                    int iSkipAll2 = iSkipAll + Util.skipAll(buffer, (byte) 61);
                    while (true) {
                        if (null == token) {
                            token = readToken(buffer);
                            if (skipCommasAndWhitespace(buffer)) {
                                break;
                            }
                            iSkipAll2 = Util.skipAll(buffer, (byte) 61);
                            if (0 != iSkipAll2) {
                                break;
                            }
                            if (1 < iSkipAll2 || skipCommasAndWhitespace(buffer)) {
                                return;
                            }
                            String quotedString = startsWith(buffer, (byte) 34) ? readQuotedString(buffer) : readToken(buffer);
                            if (null == quotedString || null != linkedHashMap.put(token, quotedString)) {
                                return;
                            }
                            if (!skipCommasAndWhitespace(buffer) && !buffer.exhausted()) {
                                return;
                            } else {
                                token = null;
                            }
                        } else if (0 != iSkipAll2) {
                        }
                    }
                }
                HttpHeaders.challenges.add(new Challenge(token2, linkedHashMap));
                token2 = token;
            }
        }
    }
    private static boolean skipCommasAndWhitespace(Buffer buffer) throws EOFException {
        boolean z = false;
        while (!buffer.exhausted()) {
            byte b2 = buffer.getByte(0L);
            if (44 == b2) {
                buffer.readByte();
                z = true;
            } else {
                if (32 != b2 && 9 != b2) {
                    break;
                }
                buffer.readByte();
            }
        }
        return z;
    }
    private static boolean startsWith(Buffer buffer, byte b2) {
        return !buffer.exhausted() && buffer.getByte(0L) == b2;
    }
    private static String readQuotedString(Buffer buffer) throws EOFException {
        if (34 != buffer.readByte()) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        Buffer buffer2 = new Buffer();
        while (true) {
            long jIndexOfElement = buffer.indexOfElement(QUOTED_STRING_DELIMITERS);
            if (-1 == jIndexOfElement) {
                return null;
            }
            if (34 == buffer.getByte(jIndexOfElement)) {
                buffer2.write(buffer, jIndexOfElement);
                buffer.readByte();
                return buffer2.readUtf8();
            }
            if (buffer.size() == jIndexOfElement + 1) {
                return null;
            }
            buffer2.write(buffer, jIndexOfElement);
            buffer.readByte();
            buffer2.write(buffer, 1L);
        }
    }
    private static String readToken(Buffer buffer) {
        long jIndexOfElement = buffer.indexOfElement(TOKEN_DELIMITERS);
        if (-1 == jIndexOfElement) {
            jIndexOfElement = buffer.size();
        }
        if (0 != jIndexOfElement) {
            try {
                return buffer.readUtf8(jIndexOfElement);
            } catch (EOFException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
    public static void receiveHeaders(CookieJar cookieJar, HttpUrl url, Headers headers) {
        checkNotNullParameter(cookieJar, "<this>");
        checkNotNullParameter(url, "url");
        checkNotNullParameter(headers, "headers");
        if (cookieJar == CookieJar.NO_COOKIES) {
            return;
        }
        List<Cookie> all = Cookie.Companion.parseAll(url, headers);
        if (all.isEmpty()) {
            return;
        }
        cookieJar.saveFromResponse(url, all);
    }
    public static boolean promisesBody(Response response) {
        checkNotNullParameter(response, "<this>");
        if (areEqual(response.request().method(), "HEAD")) {
            return false;
        }
        int iCode = response.code();
        return ((100 > iCode || 200 <= iCode) && 204 != iCode && 304 != iCode) || -1 != Util.headersContentLength(response) || StringsKt.equals("chunked", Response.headerdefault(response, org.springframework.http.HttpHeaders.TRANSFER_ENCODING, null, 2, null), true);
    }
    public static boolean hasBody(Response response) {
        checkNotNullParameter(response, "response");
        return promisesBody(response);
    }
}
