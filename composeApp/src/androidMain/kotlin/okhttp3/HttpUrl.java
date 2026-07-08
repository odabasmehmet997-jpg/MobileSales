package okhttp3;

import androidx.webkit.ProxyConfig;
import com.fasterxml.jackson.core.JsonFactory;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.features.sales.view.newadd.T;
import io.reactivex.internal.util.OpenHashSet;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal.HostnamesKt;
import okhttp3.internal.Util;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import okio.Buffer;
import org.kxml2.wap.Wbxml;

import java.io.EOFException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.activity.CashCreditFicheDetailEnterActivity.r2;
import static kotlin.jvm.internal.Intrinsics.*;
import static kotlin.text.StringsKt.*;

public final class HttpUrl {
    public static final String FORM_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#&!(),~";
    public static final String FRAGMENT_ENCODE_SET = "";
    public static final String FRAGMENT_ENCODE_SET_URI = " \"#<>\\^`{|}";
    public static final String PASSWORD_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";
    public static final String PATH_SEGMENT_ENCODE_SET = " \"<>^`{}|/\\?#";
    public static final String PATH_SEGMENT_ENCODE_SET_URI = "[]";
    public static final String QUERY_COMPONENT_ENCODE_SET = " !\"#&'(),/:;<=>?@[]\\^`{|}~";
    public static final String QUERY_COMPONENT_ENCODE_SET_URI = "\\^`{|}";
    public static final String QUERY_COMPONENT_REENCODE_SET = " \"'<>#&=";
    public static final String QUERY_ENCODE_SET = " \"'<>#";
    public static final String USERNAME_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";
    public static final Companion Companion = new Companion(null);
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final String fragment;
    private final String host;
    private final boolean isHttps;
    private final String password;
    private final List<String> pathSegments;
    private final int port;
    private final List<String> queryNamesAndValues;
    private final String scheme;
    private final String url;
    private final String username;
  private Object encodedUsername;

  public HttpUrl(final String scheme, final String username, final String password, final String host, final int i2, final List<String> pathSegments, final List<String> list, final String str, final String url) {
        checkNotNullParameter(scheme, "scheme");
        checkNotNullParameter(username, "username");
        checkNotNullParameter(password, "password");
        checkNotNullParameter(host, "host");
        checkNotNullParameter(pathSegments, "pathSegments");
        checkNotNullParameter(url, "url");
        this.scheme = scheme;
        this.username = username;
        this.password = password;
        this.host = host;
        port = i2;
        this.pathSegments = pathSegments;
        queryNamesAndValues = list;
        fragment = str;
        this.url = url;
        isHttps = areEqual(scheme, ProxyConfig.MATCH_HTTPS);
    }
    public static int defaultPort(final String str) {
        return HttpUrl.Companion.defaultPort(str);
    }
    public static HttpUrl get(final String str) {
        return HttpUrl.Companion.get(str);
    }
    public static HttpUrl get(final URI uri) {
        return HttpUrl.Companion.get(uri);
    }
    public static HttpUrl get(final URL url) {
        return HttpUrl.Companion.get(url);
    }
    public static HttpUrl parse(final String str) {
        return HttpUrl.Companion.parse(str);
    }
    public String scheme() {
        return scheme;
    }
    public String username() {
        return username;
    }
    public String password() {
        return password;
    }
    public String host() {
        return host;
    }
    public int port() {
        return port;
    }
    public List<String> pathSegments() {
        return pathSegments;
    }
    public String fragment() {
        return fragment;
    }
    public boolean isHttps() {
        return isHttps;
    }
    public URL url() {
        try {
            return new URL(url);
        } catch (final MalformedURLException e2) {
            throw new RuntimeException(e2);
        }
    }
    public URI uri() {
        final String string = this.newBuilder().reencodeForUriokhttp().toString();
        try {
            return new URI(string);
        } catch (final URISyntaxException e2) {
            try {
                final URI uriCreate = URI.create(new Regex("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]").replace(string, ""));
                checkNotNullExpressionValue(uriCreate, "{\n      // Unlikely edge\u2026Unexpected!\n      }\n    }");
                return uriCreate;
            } catch (final Exception unused) {
                throw new RuntimeException(e2);
            }
        }
    }
    public String encodedUsername() {
        if (0 == this.username.length()) {
            return "";
        }
        final int length = scheme.length() + 3;
        final String str = url;
        final String strSubstring = url.substring(length, Util.delimiterOffset(str, ":@", length, str.length()));
        checkNotNullExpressionValue(strSubstring, "this as java.lang.String\u2026ing(startIndex, endIndex)");
        return strSubstring;
    }
    public String encodedPassword() {
        if (0 == this.password.length()) {
            return "";
        }
        final String strSubstring = url.substring(indexOf((CharSequence) url, ':', scheme.length() + 3, false) + 1, indexOf((CharSequence) url, '@', 0, false));
        checkNotNullExpressionValue(strSubstring, "this as java.lang.String\u2026ing(startIndex, endIndex)");
        return strSubstring;
    }
    public int pathSize() {
        return pathSegments.size();
    }
    public String encodedPath() {
        final int iIndexOfdefault = indexOf((CharSequence) url, '/', scheme.length() + 3, false);
        final String str = url;
        final String strSubstring = url.substring(iIndexOfdefault, Util.delimiterOffset(str, "?#", iIndexOfdefault, str.length()));
        checkNotNullExpressionValue(strSubstring, "this as java.lang.String\u2026ing(startIndex, endIndex)");
        return strSubstring;
    }
    public List<String> encodedPathSegments() {
        int iIndexOfdefault = indexOf((CharSequence) url, '/', scheme.length() + 3, false);
        final String str = url;
        final int iDelimiterOffset = Util.delimiterOffset(str, "?#", iIndexOfdefault, str.length());
        final ArrayList arrayList = new ArrayList();
        while (iIndexOfdefault < iDelimiterOffset) {
            final int i2 = iIndexOfdefault + 1;
            final int iDelimiterOffset2 = Util.delimiterOffset(url, '/', i2, iDelimiterOffset);
            final String strSubstring = url.substring(i2, iDelimiterOffset2);
            checkNotNullExpressionValue(strSubstring, "this as java.lang.String\u2026ing(startIndex, endIndex)");
            arrayList.add(strSubstring);
            iIndexOfdefault = iDelimiterOffset2;
        }
        return arrayList;
    }
    public String encodedQuery() {
        if (null == this.queryNamesAndValues) {
            return null;
        }
        final int iIndexOfdefault = indexOf((CharSequence) url, '?', 0, false) + 1;
        final String str = url;
        final String strSubstring = url.substring(iIndexOfdefault, Util.delimiterOffset(str, '#', iIndexOfdefault, str.length()));
        checkNotNullExpressionValue(strSubstring, "this as java.lang.String\u2026ing(startIndex, endIndex)");
        return strSubstring;
    }
    public String query() {
        if (null == this.queryNamesAndValues) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        HttpUrl.Companion.toQueryStringokhttp(queryNamesAndValues, sb);
        return sb.toString();
    }
    public int querySize() {
        final List<String> list = queryNamesAndValues;
        if (null != list) {
            return list.size() / 2;
        }
        return 0;
    }
    public String queryParameter(final String name) {
        checkNotNullParameter(name, "name");
        final List<String> list = queryNamesAndValues;
        if (null == list) {
            return null;
        }
        final IntProgression progressionsStep = RangesKt.step(RangesKt.until(0, list.size()), 2);
        int first = progressionsStep.getFirst();
        final int last = progressionsStep.getLast();
        final int step = progressionsStep.getStep();
        if ((0 < step && first <= last) || (0 > step && last <= first)) {
            while (true) {
                final int i2 = first + step;
                if (areEqual(name, queryNamesAndValues.get(first))) {
                    return queryNamesAndValues.get(first + 1);
                }
                if (first == last) {
                    break;
                }
                first = i2;
            }
        }
        return null;
    }
    public Set<String> queryParameterNames() {
        if (null == this.queryNamesAndValues) {
            return SetsKt.emptySet();
        }
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        final IntProgression progressionsStep = RangesKt.step(RangesKt.until(0, queryNamesAndValues.size()), 2);
        int first = progressionsStep.getFirst();
        final int last = progressionsStep.getLast();
        final int step = progressionsStep.getStep();
        if ((0 < step && first <= last) || (0 > step && last <= first)) {
            while (true) {
                final int i2 = first + step;
                final String str = queryNamesAndValues.get(first);
                checkNotNull(str);
                linkedHashSet.add(str);
                if (first == last) {
                    break;
                }
                first = i2;
            }
        }
        final Set<String> setUnmodifiableSet = Collections.unmodifiableSet(linkedHashSet);
        checkNotNullExpressionValue(setUnmodifiableSet, "unmodifiableSet(result)");
        return setUnmodifiableSet;
    }
    public List<String> queryParameterValues(final String name) {
        checkNotNullParameter(name, "name");
        if (null == this.queryNamesAndValues) {
            return CollectionsKt.emptyList();
        }
        final ArrayList arrayList = new ArrayList();
        final IntProgression progressionsStep = RangesKt.step(RangesKt.until(0, queryNamesAndValues.size()), 2);
        int first = progressionsStep.getFirst();
        final int last = progressionsStep.getLast();
        final int step = progressionsStep.getStep();
        if ((0 < step && first <= last) || (0 > step && last <= first)) {
            while (true) {
                final int i2 = first + step;
                if (areEqual(name, queryNamesAndValues.get(first))) {
                    arrayList.add(queryNamesAndValues.get(first + 1));
                }
                if (first == last) {
                    break;
                }
                first = i2;
            }
        }
        final List<String> listUnmodifiableList = Collections.unmodifiableList(arrayList);
        checkNotNullExpressionValue(listUnmodifiableList, "unmodifiableList(result)");
        return listUnmodifiableList;
    }
    public String queryParameterName(final int i2) {
        final List<String> list = queryNamesAndValues;
        if (null == list) {
            throw new IndexOutOfBoundsException();
        }
        final String str = list.get(i2 * 2);
        checkNotNull(str);
        return str;
    }
    public String queryParameterValue(final int i2) {
        final List<String> list = queryNamesAndValues;
        if (null == list) {
            throw new IndexOutOfBoundsException();
        }
        return list.get((i2 * 2) + 1);
    }
    public String encodedFragment() {
        if (null == this.fragment) {
            return null;
        }
        final String strSubstring = url.substring(indexOf((CharSequence) url, '#', 0, false) + 1);
        checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
        return strSubstring;
    }
    public String redact() {
        final Builder builderNewBuilder = this.newBuilder("/...");
        checkNotNull(builderNewBuilder);
        return builderNewBuilder.username("").password("").build().toString();
    }
    public HttpUrl resolve(final String link) {
        checkNotNullParameter(link, "link");
        final Builder builderNewBuilder = this.newBuilder(link);
        if (null == builderNewBuilder) {
            return null;
        }
        return builderNewBuilder.build();
    }
    public Builder newBuilder() {
        final Builder builder = new Builder();
        builder.setSchemeokhttp(scheme);
        builder.setEncodedUsernameokhttp(this.encodedUsername());
        builder.setEncodedPasswordokhttp(this.encodedPassword());
        builder.setHostokhttp(host);
        builder.setPortokhttp(port != HttpUrl.Companion.defaultPort(scheme) ? port : -1);
        builder.getEncodedPathSegmentsokhttp().clear();
        builder.getEncodedPathSegmentsokhttp().addAll(this.encodedPathSegments());
        builder.encodedQuery(this.encodedQuery());
        builder.setEncodedFragmentokhttp(this.encodedFragment());
        return builder;
    }
    public Builder newBuilder(final String link) {
        checkNotNullParameter(link, "link");
        try {
            return new Builder().parseokhttp(this, link);
        } catch (final IllegalArgumentException unused) {
            return null;
        }
    }
    public boolean equals(final Object obj) {
        return (obj instanceof HttpUrl) && areEqual(((HttpUrl) obj).url, url);
    }
    public int hashCode() {
        return url.hashCode();
    }
    public String toString() {
        return url;
    }
    public String topPrivateDomain() {
        if (Util.canParseAsIpAddress(host)) {
            return null;
        }
      try {
        return PublicSuffixDatabase.Companion.get().getEffectiveTldPlusOne(host);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    public URL deprecated_url() {
        return this.url();
    }
    public URI deprecated_uri() {
        return this.uri();
    }
    public String deprecated_scheme() {
        return scheme;
    }
    public String encodedUsername() {
        return this.encodedUsername();
    }
    public String deprecated_encodedPassword() {
        return this.encodedPassword();
    }
    public String deprecated_password() {
        return password;
    }
    public String m1773deprecated_host() {
        return host;
    }
    public int m1777deprecated_port() {
        return port;
    }

    public int m1776deprecated_pathSize() {
        return this.pathSize();
    }
    public String m1768deprecated_encodedPath() {
        return this.encodedPath();
    }
    public List<String> m1769deprecated_encodedPathSegments() {
        return this.encodedPathSegments();
    }
    public List<String> m1775deprecated_pathSegments() {
        return pathSegments;
    }
    public String m1770deprecated_encodedQuery() {
        return this.encodedQuery();
    }
    public String m1778deprecated_query() {
        return this.query();
    }
    public int m1780deprecated_querySize() {
        return this.querySize();
    }
    public Set<String> m1779deprecated_queryParameterNames() {
        return this.queryParameterNames();
    }
    public String m1766deprecated_encodedFragment() {
        return this.encodedFragment();
    }
    public String m1772deprecated_fragment() {
        return fragment;
    }
    public static final class Builder {
        public static final Companion Companion = new Companion(null);
        public static final String INVALID_HOST = "Invalid URL host";
        private final List<String> encodedPathSegments;
        private String encodedFragment;
        private List<String> encodedQueryNamesAndValues;
        private String host;
        private String scheme;
        private String encodedUsername = "";
        private String encodedPassword = "";
        private int port = -1;
        private HostnamesKt hostnames;
        private PrintSlipModel r0;
        public Builder() {
            final ArrayList arrayList = new ArrayList();
            encodedPathSegments = arrayList;
            arrayList.add("");
        }
        public String getSchemeokhttp() {
            return scheme;
        }
        public void setSchemeokhttp(final String str) {
            scheme = str;
        }
        public String getEncodedUsernameokhttp() {
            return encodedUsername;
        }
        public void setEncodedUsernameokhttp(final String str) {
            checkNotNullParameter(str, "<set-?>");
            encodedUsername = str;
        }
        public String getEncodedPasswordokhttp() {
            return encodedPassword;
        }
        public void setEncodedPasswordokhttp(final String str) {
            checkNotNullParameter(str, "<set-?>");
            encodedPassword = str;
        }
        public String getHostokhttp() {
            return host;
        }
        public void setHostokhttp(final String str) {
            host = str;
        }
        public int getPortokhttp() {
            return port;
        }
        public void setPortokhttp(final int i2) {
            port = i2;
        }
        public List<String> getEncodedPathSegmentsokhttp() {
            return encodedPathSegments;
        }
        public List<String> getEncodedQueryNamesAndValuesokhttp() {
            return encodedQueryNamesAndValues;
        }
        public void setEncodedQueryNamesAndValuesokhttp(final List<String> list) {
            encodedQueryNamesAndValues = list;
        }
        public String getEncodedFragmentokhttp() {
            return encodedFragment;
        }
        public void setEncodedFragmentokhttp(final String str) {
            encodedFragment = str;
        }
        public Builder scheme(final String scheme) {
            checkNotNullParameter(scheme, "scheme");
            if (StringsKt.equals(scheme, ProxyConfig.MATCH_HTTP, true)) {
                this.scheme = ProxyConfig.MATCH_HTTP;
            } else if (StringsKt.equals(scheme, ProxyConfig.MATCH_HTTPS, true)) {
                this.scheme = ProxyConfig.MATCH_HTTPS;
            } else {
                throw new IllegalArgumentException(stringPlus("unexpected scheme: ", scheme));
            }
            return this;
        }
        public Builder username(final String username) {
            checkNotNullParameter(username, "username");
            this.setEncodedUsernameokhttp(Builder.Companion.canonicalizeokhttpdefault(HttpUrl.Companion, username, 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, null, 251, null));
            return this;
        }
        public Builder encodedUsername(final String encodedUsername) {
            checkNotNullParameter(encodedUsername, "encodedUsername");
            this.setEncodedUsernameokhttp(Builder.Companion.canonicalizeokhttpdefault(HttpUrl.Companion, encodedUsername, 0, 0, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 243, null));
            return this;
        }
        public Builder password(final String password) {
            checkNotNullParameter(password, "password");
            this.setEncodedPasswordokhttp(Builder.Companion.canonicalizeokhttpdefault(HttpUrl.Companion, password, 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, null, 251, null));
            return this;
        }
        public Builder encodedPassword(final String encodedPassword) {
            checkNotNullParameter(encodedPassword, "encodedPassword");
            this.setEncodedPasswordokhttp(Builder.Companion.canonicalizeokhttpdefault(HttpUrl.Companion, encodedPassword, 0, 0, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 243, null));
            return this;
        }
        public Builder host(final String host) {
            checkNotNullParameter(host, "host");
            final String canonicalHost = hostnames.toCanonicalHost((String) Builder.Companion.percentDecodeokhttpdefault(HttpUrl.Companion, host, 0, 0, false, 7, null));
            if (null == canonicalHost) {
                throw new IllegalArgumentException(stringPlus("unexpected host: ", host));
            }
            this.host = canonicalHost;
            return this;
        }
        public Builder port(final int i2) {
            if (1 > i2 || 65536 <= i2) {
                throw new IllegalArgumentException(stringPlus("unexpected port: ", Integer.valueOf(i2)));
            }
            this.port = i2;
            return this;
        }
        private int effectivePort() {
            final int i2 = port;
            if (-1 != i2) {
                return i2;
            }
            final Companion companion = HttpUrl.Companion;
            final String str = scheme;
            checkNotNull(str);
          final int i = ((HttpUrl.Companion) companion).defaultPort(str);
          return i;
        }

        public Builder addPathSegment(final String pathSegment) {
            checkNotNullParameter(pathSegment, "pathSegment");
            this.push(pathSegment, 0, pathSegment.length(), false, false);
            return this;
        }

        public Builder addPathSegments(final String pathSegments) {
            checkNotNullParameter(pathSegments, "pathSegments");
            return this.addPathSegments(pathSegments, false);
        }

        public Builder addEncodedPathSegment(final String encodedPathSegment) {
            checkNotNullParameter(encodedPathSegment, "encodedPathSegment");
            this.push(encodedPathSegment, 0, encodedPathSegment.length(), false, true);
            return this;
        }

        public Builder addEncodedPathSegments(final String encodedPathSegments) {
            checkNotNullParameter(encodedPathSegments, "encodedPathSegments");
            return this.addPathSegments(encodedPathSegments, true);
        }

        private Builder addPathSegments(final String str, final boolean z) {
            int i2 = 0;
            do {
                final int iDelimiterOffset = Util.delimiterOffset(str, "/\\", i2, str.length());
                this.push(str, i2, iDelimiterOffset, iDelimiterOffset < str.length(), z);
                i2 = iDelimiterOffset + 1;
            } while (i2 <= str.length());
            return this;
        }

        public Builder setPathSegment(final int i2, final String pathSegment) {
            checkNotNullParameter(pathSegment, "pathSegment");
            final String strCanonicalizeokhttpdefault = Builder.Companion.canonicalizeokhttpdefault(HttpUrl.Companion, pathSegment, 0, 0, PATH_SEGMENT_ENCODE_SET, false, false, false, false, null, 251, null);
            if (this.isDot(strCanonicalizeokhttpdefault) || this.isDotDot(strCanonicalizeokhttpdefault)) {
                throw new IllegalArgumentException(stringPlus("unexpected path segment: ", pathSegment));
            }
            this.encodedPathSegments.set(i2, strCanonicalizeokhttpdefault);
            return this;
        }

        public Builder setEncodedPathSegment(final int i2, final String encodedPathSegment) {
            checkNotNullParameter(encodedPathSegment, "encodedPathSegment");
            final String strCanonicalizeokhttpdefault = Builder.Companion.canonicalizeokhttpdefault(HttpUrl.Companion, encodedPathSegment, 0, 0, PATH_SEGMENT_ENCODE_SET, true, false, false, false, null, 243, null);
            this.encodedPathSegments.set(i2, strCanonicalizeokhttpdefault);
            if (this.isDot(strCanonicalizeokhttpdefault) || this.isDotDot(strCanonicalizeokhttpdefault)) {
                throw new IllegalArgumentException(stringPlus("unexpected path segment: ", encodedPathSegment));
            }
            return this;
        }

        public Builder removePathSegment(final int i2) {
            this.encodedPathSegments.remove(i2);
            if (this.encodedPathSegments.isEmpty()) {
                this.encodedPathSegments.add("");
            }
            return this;
        }

        public Builder encodedPath(final String encodedPath) {
            checkNotNullParameter(encodedPath, "encodedPath");
            if (!startsWith(encodedPath, "/", false)) {
                throw new IllegalArgumentException(stringPlus("unexpected encodedPath: ", encodedPath));
            }
            this.resolvePath(encodedPath, 0, encodedPath.length());
            return this;
        }

        public Builder query(final String str) {
            List<String> queryNamesAndValuesokhttp = null;
            if (null != str) {
                final HttpUrl.Companion companion = HttpUrl.Companion;
                final String strCanonicalizeokhttpdefault = Builder.Companion.canonicalizeokhttpdefault(companion, str, 0, 0, QUERY_ENCODE_SET, false, false, true, false, null, 219, null);
                if (null != strCanonicalizeokhttpdefault) {
                    queryNamesAndValuesokhttp = companion.toQueryNamesAndValuesokhttp(strCanonicalizeokhttpdefault);
                }
            }
            this.encodedQueryNamesAndValues = queryNamesAndValuesokhttp;
            return this;
        }

        public Builder encodedQuery(final String str) {
            List<String> queryNamesAndValuesokhttp = null;
            if (null != str) {
                final HttpUrl.Companion companion = HttpUrl.Companion;
                final String strCanonicalizeokhttpdefault = Builder.Companion.canonicalizeokhttpdefault(companion, str, 0, 0, QUERY_ENCODE_SET, true, false, true, false, null, 211, null);
                if (null != strCanonicalizeokhttpdefault)
                  queryNamesAndValuesokhttp = ((HttpUrl.Companion) companion).toQueryNamesAndValuesokhttp(strCanonicalizeokhttpdefault);
            }
            this.encodedQueryNamesAndValues = queryNamesAndValuesokhttp;
            return this;
        }

        public Builder addQueryParameter(final String name, final String str) {
            checkNotNullParameter(name, "name");
            if (null == encodedQueryNamesAndValues) {
                this.encodedQueryNamesAndValues = new ArrayList();
            }
            final List<String> encodedQueryNamesAndValuesokhttp = this.encodedQueryNamesAndValues;
            checkNotNull(encodedQueryNamesAndValuesokhttp);
            final HttpUrl.Companion companion = HttpUrl.Companion;
            encodedQueryNamesAndValuesokhttp.add(Builder.Companion.canonicalizeokhttpdefault(companion, name, 0, 0, QUERY_COMPONENT_ENCODE_SET, false, false, true, false, null, 219, null));
            final List<String> encodedQueryNamesAndValuesokhttp2 = this.encodedQueryNamesAndValues;
            checkNotNull(encodedQueryNamesAndValuesokhttp2);
            encodedQueryNamesAndValuesokhttp2.add(null == str ? null : Builder.Companion.canonicalizeokhttpdefault(companion, str, 0, 0, QUERY_COMPONENT_ENCODE_SET, false, false, true, false, null, 219, null));
            return this;
        }

        public Builder addEncodedQueryParameter(final String encodedName, final String str) {
            checkNotNullParameter(encodedName, "encodedName");
            if (null == encodedQueryNamesAndValues) {
                this.encodedQueryNamesAndValues = new ArrayList();
            }
            final List<String> encodedQueryNamesAndValuesokhttp = this.encodedQueryNamesAndValues;
            checkNotNull(encodedQueryNamesAndValuesokhttp);
            final HttpUrl.Companion companion = HttpUrl.Companion;
            encodedQueryNamesAndValuesokhttp.add(Builder.Companion.canonicalizeokhttpdefault(companion, encodedName, 0, 0, QUERY_COMPONENT_REENCODE_SET, true, false, true, false, null, 211, null));
            final List<String> encodedQueryNamesAndValuesokhttp2 = this.encodedQueryNamesAndValues;
            checkNotNull(encodedQueryNamesAndValuesokhttp2);
            encodedQueryNamesAndValuesokhttp2.add(null == str ? null : Builder.Companion.canonicalizeokhttpdefault(companion, str, 0, 0, QUERY_COMPONENT_REENCODE_SET, true, false, true, false, null, 211, null));
            return this;
        }

        public Builder setQueryParameter(final String name, final String str) {
            checkNotNullParameter(name, "name");
            this.removeAllQueryParameters(name);
            this.addQueryParameter(name, str);
            return this;
        }

        public Builder setEncodedQueryParameter(final String encodedName, final String str) {
            checkNotNullParameter(encodedName, "encodedName");
            this.removeAllEncodedQueryParameters(encodedName);
            this.addEncodedQueryParameter(encodedName, str);
            return this;
        }

        public Builder removeAllQueryParameters(final String name) {
            checkNotNullParameter(name, "name");
            if (null == encodedQueryNamesAndValues) {
                return this;
            }
            this.removeAllCanonicalQueryParameters(Builder.Companion.canonicalizeokhttpdefault(HttpUrl.Companion, name, 0, 0, QUERY_COMPONENT_ENCODE_SET, false, false, true, false, null, 219, null));
            return this;
        }

        public Builder removeAllEncodedQueryParameters(final String encodedName) {
            checkNotNullParameter(encodedName, "encodedName");
            if (null == encodedQueryNamesAndValues) {
                return this;
            }
            this.removeAllCanonicalQueryParameters(Builder.Companion.canonicalizeokhttpdefault(HttpUrl.Companion, encodedName, 0, 0, QUERY_COMPONENT_REENCODE_SET, true, false, true, false, null, 211, null));
            return this;
        }

        private void removeAllCanonicalQueryParameters(final String str) {
            final List<String> list = encodedQueryNamesAndValues;
            checkNotNull(list);
            int size = list.size() - 2;
          ProgressionUtilKt progressionUtil = null;
          final int progressionLastElement = progressionUtil.getProgressionLastElement(size, 0, -2);
            if (progressionLastElement > size) {
                return;
            }
            while (true) {
                final int i2 = size - 2;
                final List<String> list2 = encodedQueryNamesAndValues;
                checkNotNull(list2);
                if (areEqual(str, list2.get(size))) {
                    final List<String> list3 = encodedQueryNamesAndValues;
                    checkNotNull(list3);
                    list3.remove(size + 1);
                    final List<String> list4 = encodedQueryNamesAndValues;
                    checkNotNull(list4);
                    list4.remove(size);
                    final List<String> list5 = encodedQueryNamesAndValues;
                    checkNotNull(list5);
                    if (list5.isEmpty()) {
                        encodedQueryNamesAndValues = null;
                        return;
                    }
                }
                if (size == progressionLastElement) {
                    return;
                } else {
                    size = i2;
                }
            }
        }

        public Builder fragment(final String str) {
            this.encodedFragment = null == str ? null : Builder.Companion.canonicalizeokhttpdefault(HttpUrl.Companion, str, 0, 0, "", false, false, false, true, null, 187, null);
            return this;
        }

        public Builder encodedFragment(final String str) {
            this.encodedFragment = null == str ? null : Builder.Companion.canonicalizeokhttpdefault(HttpUrl.Companion, str, 0, 0, "", true, false, false, true, null, 179, null);
            return this;
        }

        public Builder reencodeForUriokhttp() {
            final String hostokhttp = this.host;
            this.host = null == hostokhttp ? null : new Regex("[\"<>^`{|}]").replace(hostokhttp, "");
            final int size = this.encodedPathSegments.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                this.encodedPathSegments.set(i3, Builder.Companion.canonicalizeokhttpdefault(HttpUrl.Companion, this.encodedPathSegments.get(i3), 0, 0, PATH_SEGMENT_ENCODE_SET_URI, true, true, false, false, null, 227, null));
            }
            final List<String> encodedQueryNamesAndValuesokhttp = this.encodedQueryNamesAndValues;
            if (null != encodedQueryNamesAndValuesokhttp) {
                final int size2 = encodedQueryNamesAndValuesokhttp.size();
                while (i2 < size2) {
                    final int i4 = i2 + 1;
                    final String str = encodedQueryNamesAndValuesokhttp.get(i2);
                    encodedQueryNamesAndValuesokhttp.set(i2, null == str ? null : Builder.Companion.canonicalizeokhttpdefault(HttpUrl.Companion, str, 0, 0, QUERY_COMPONENT_ENCODE_SET_URI, true, true, true, false, null, Wbxml.OPAQUE, null));
                    i2 = i4;
                }
            }
            final String encodedFragmentokhttp = this.encodedFragment;
            this.encodedFragment = null != encodedFragmentokhttp ? Builder.Companion.canonicalizeokhttpdefault(HttpUrl.Companion, encodedFragmentokhttp, 0, 0, FRAGMENT_ENCODE_SET_URI, true, true, false, true, null, 163, null) : null;
            return this;
        }

        public HttpUrl build() {
            final ArrayList arrayList;
            final String str = scheme;
            if (null == str) {
                throw new IllegalStateException("scheme == null");
            }
            final HttpUrl.Companion companion = HttpUrl.Companion;
            final String strPercentDecodeokhttpdefault = (String) Builder.Companion.percentDecodeokhttpdefault(companion, encodedUsername, 0, 0, false, 7, null);
            final String strPercentDecodeokhttpdefault2 = (String) Builder.Companion.percentDecodeokhttpdefault(companion, encodedPassword, 0, 0, false, 7, null);
            final String str2 = host;
            if (null == str2) {
                throw new IllegalStateException("host == null");
            }
            final int iEffectivePort = this.effectivePort();
            final List<String> list = encodedPathSegments;
            final ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            final Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                arrayList2.add(Builder.Companion.percentDecodeokhttpdefault(HttpUrl.Companion, (String) it.next(), 0, 0, false, 7, null));
            }
            final List<String> list2 = encodedQueryNamesAndValues;
            if (null == list2) {
                arrayList = null;
            } else {
                arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                for (final String str3 : list2) {
                    arrayList.add(null == str3 ? null : Builder.Companion.percentDecodeokhttpdefault(HttpUrl.Companion, str3, 0, 0, true, 3, null));
                }
            }
            final String str4 = encodedFragment;
            return new HttpUrl(str, strPercentDecodeokhttpdefault, strPercentDecodeokhttpdefault2, str2, iEffectivePort, arrayList2, arrayList, null == str4 ? null : (String) Builder.Companion.percentDecodeokhttpdefault(HttpUrl.Companion, str4, 0, 0, false, 7, null), this.toString());
        }
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            if (null != scheme) {
                sb.append(this.scheme);
                sb.append("://");
            } else {
                sb.append("//");
            }
            if (0 < encodedUsername.length() || 0 < encodedPassword.length()) {
                sb.append(this.encodedUsername);
                if (0 < encodedPassword.length()) {
                    sb.append(':');
                    sb.append(this.encodedPassword);
                }
                sb.append('@');
            }
            if (null != host) {
                final String hostokhttp = this.host;
                checkNotNull(hostokhttp);
                if (StringsKt.contains((CharSequence) hostokhttp, ':', false)) {
                    sb.append('[');
                    sb.append(this.host);
                    sb.append(']');
                } else {
                    sb.append(this.host);
                }
            }
            if (-1 != port || null != scheme) {
                final int iEffectivePort = this.effectivePort();
                if (null != scheme) {
                    final Companion companion = HttpUrl.Companion;
                    final String schemeokhttp = this.scheme;
                    checkNotNull(schemeokhttp);
                    if (iEffectivePort != ((HttpUrl.Companion) companion).defaultPort(schemeokhttp)) {
                        sb.append(':');
                        sb.append(iEffectivePort);
                    }
                }
            }
            final Companion companion2 = HttpUrl.Companion;
            ((HttpUrl.Companion) companion2).toPathStringokhttp(this.encodedPathSegments, sb);
            if (null != encodedQueryNamesAndValues) {
                sb.append('?');
                final List<String> encodedQueryNamesAndValuesokhttp = this.encodedQueryNamesAndValues;
                checkNotNull(encodedQueryNamesAndValuesokhttp);
                companion2.toQueryStringokhttp(encodedQueryNamesAndValuesokhttp, sb);
            }
            if (null != encodedFragment) {
                sb.append('#');
                sb.append(this.encodedFragment);
            }
            final String string = sb.toString();
            checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
            return string;
        }

        public Builder parseokhttp(final HttpUrl httpUrl, final String str) throws NumberFormatException {
            int iDelimiterOffset;
            final int i2;
            int i3;
            boolean z;
            int i4;
            boolean z2;
            String input = str;
            checkNotNullParameter(input, "input");
            int iIndexOfFirstNonAsciiWhitespacedefault = Util.indexOfFirstNonAsciiWhitespacedefault(input, 0, 0, 3, null);
            int iIndexOfLastNonAsciiWhitespacedefault = Util.indexOfLastNonAsciiWhitespacedefault(input, iIndexOfFirstNonAsciiWhitespacedefault, 0, 2, null);
            final Companion companion = Builder.Companion;
            final int iSchemeDelimiterOffset = companion.schemeDelimiterOffset(input, iIndexOfFirstNonAsciiWhitespacedefault, iIndexOfLastNonAsciiWhitespacedefault);
            boolean z3 = true;
            char c2 = '\uffff';
            if (-1 != iSchemeDelimiterOffset) {
                if (startsWith(input, "https:", iIndexOfFirstNonAsciiWhitespacedefault, true)) {
                    scheme = ProxyConfig.MATCH_HTTPS;
                    iIndexOfFirstNonAsciiWhitespacedefault += 6;
                } else if (startsWith(input, "http:", iIndexOfFirstNonAsciiWhitespacedefault, true)) {
                    scheme = ProxyConfig.MATCH_HTTP;
                    iIndexOfFirstNonAsciiWhitespacedefault += 5;
                } else {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("Expected URL scheme 'http' or 'https' but was '");
                    final String strSubstring = input.substring(0, iSchemeDelimiterOffset);
                    checkNotNullExpressionValue(strSubstring, "this as java.lang.String\u2026ing(startIndex, endIndex)");
                    sb.append(strSubstring);
                    sb.append('\'');
                    throw new IllegalArgumentException(sb.toString());
                }
            } else if (null != httpUrl) {
                scheme = httpUrl.scheme();
            } else {
                if (6 < str.length()) {
                    input = stringPlus(take(input, 6), "...");
                }
                throw new IllegalArgumentException(stringPlus("Expected URL scheme 'http' or 'https' but no scheme was found for ", input));
            }
            final int iSlashCount = companion.slashCount(input, iIndexOfFirstNonAsciiWhitespacedefault, iIndexOfLastNonAsciiWhitespacedefault);
            char c3 = '?';
            char c4 = '#';
            if (2 <= iSlashCount || null == httpUrl || !areEqual(httpUrl.scheme(), scheme)) {
                boolean z4 = false;
                boolean z5 = false;
                int i5 = iIndexOfFirstNonAsciiWhitespacedefault + iSlashCount;
                while (true) {
                    iDelimiterOffset = Util.delimiterOffset(input, "@/\\?#", i5, iIndexOfLastNonAsciiWhitespacedefault);
                    final char cCharAt = iDelimiterOffset != iIndexOfLastNonAsciiWhitespacedefault ? input.charAt(iDelimiterOffset) : c2;
                    if (cCharAt == c2 || cCharAt == c4 || '/' == cCharAt || '\\' == cCharAt || cCharAt == c3) {
                        break;
                    }
                    if ('@' == cCharAt) {
                        if (!z4) {
                            final int iDelimiterOffset2 = Util.delimiterOffset(input, ':', i5, iDelimiterOffset);
                            final HttpUrl.Companion companion2 = HttpUrl.Companion;
                            z = z3;
                            String strCanonicalizeokhttpdefault = Builder.Companion.canonicalizeokhttpdefault(companion2, str, i5, iDelimiterOffset2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 240, null);
                            if (z5) {
                                strCanonicalizeokhttpdefault = encodedUsername + "%40" + strCanonicalizeokhttpdefault;
                            }
                            encodedUsername = strCanonicalizeokhttpdefault;
                            if (iDelimiterOffset2 != iDelimiterOffset) {
                                i3 = iDelimiterOffset;
                                encodedPassword = Builder.Companion.canonicalizeokhttpdefault(companion2, str, iDelimiterOffset2 + 1, iDelimiterOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 240, null);
                                z2 = z;
                            } else {
                                i3 = iDelimiterOffset;
                                z2 = z4;
                            }
                            z4 = z2;
                            i4 = iIndexOfLastNonAsciiWhitespacedefault;
                            z5 = z;
                        } else {
                            i3 = iDelimiterOffset;
                            z = z3;
                            final StringBuilder sb2 = new StringBuilder();
                            sb2.append(encodedPassword);
                            sb2.append("%40");
                            i4 = iIndexOfLastNonAsciiWhitespacedefault;
                            sb2.append(Builder.Companion.canonicalizeokhttpdefault(HttpUrl.Companion, str, i5, i3, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 240, null));
                            encodedPassword = sb2.toString();
                        }
                        i5 = i3 + 1;
                        z3 = z;
                        iIndexOfLastNonAsciiWhitespacedefault = i4;
                        c4 = '#';
                        c3 = '?';
                        c2 = '\uffff';
                    }
                }
                i2 = iIndexOfLastNonAsciiWhitespacedefault;
                final Companion companion3 = Builder.Companion;
                final int iPortColonOffset = companion3.portColonOffset(input, i5, iDelimiterOffset);
                final int i6 = iPortColonOffset + 1;
                if (i6 < iDelimiterOffset) {
                    host = hostnames.toCanonicalHost((String) Builder.Companion.percentDecodeokhttpdefault(HttpUrl.Companion, str, i5, iPortColonOffset, false, 4, null));
                    final int port = companion3.parsePort(input, i6, iDelimiterOffset);
                    this.port = port;
                    if (-1 == port) {
                        final StringBuilder sb3 = new StringBuilder();
                        sb3.append("Invalid URL port: \"");
                        final String strSubstring2 = input.substring(i6, iDelimiterOffset);
                        checkNotNullExpressionValue(strSubstring2, "this as java.lang.String\u2026ing(startIndex, endIndex)");
                        sb3.append(strSubstring2);
                        sb3.append(JsonFactory.DEFAULT_QUOTE_CHAR);
                        throw new IllegalArgumentException(sb3.toString());
                    }
                } else {
                    final HttpUrl.Companion companion4 = HttpUrl.Companion;
                    host = hostnames.toCanonicalHost((String) Builder.Companion.percentDecodeokhttpdefault(companion4, str, i5, iPortColonOffset, false, 4, null));
                    final String str2 = scheme;
                    checkNotNull(str2);
                    port = companion4.defaultPort(str2);
                }
                if (null == this.host) {
                    final StringBuilder sb4 = new StringBuilder();
                    sb4.append("Invalid URL host: \"");
                    final String strSubstring3 = input.substring(i5, iPortColonOffset);
                    checkNotNullExpressionValue(strSubstring3, "this as java.lang.String\u2026ing(startIndex, endIndex)");
                    sb4.append(strSubstring3);
                    sb4.append(JsonFactory.DEFAULT_QUOTE_CHAR);
                    throw new IllegalArgumentException(sb4.toString());
                }
                iIndexOfFirstNonAsciiWhitespacedefault = iDelimiterOffset;
            } else {
                encodedUsername = httpUrl.encodedUsername();
                encodedPassword = httpUrl.encodedPassword();
                host = httpUrl.host();
                port = httpUrl.port();
                encodedPathSegments.clear();
                encodedPathSegments.addAll(httpUrl.encodedPathSegments());
                if (iIndexOfFirstNonAsciiWhitespacedefault == iIndexOfLastNonAsciiWhitespacedefault || '#' == input.charAt(iIndexOfFirstNonAsciiWhitespacedefault)) {
                    this.encodedQuery(httpUrl.encodedQuery());
                }
                i2 = iIndexOfLastNonAsciiWhitespacedefault;
            }
            final int i7 = i2;
            int iDelimiterOffset3 = Util.delimiterOffset(input, "?#", iIndexOfFirstNonAsciiWhitespacedefault, i7);
            this.resolvePath(input, iIndexOfFirstNonAsciiWhitespacedefault, iDelimiterOffset3);
            if (iDelimiterOffset3 < i7 && '?' == input.charAt(iDelimiterOffset3)) {
                final int iDelimiterOffset4 = Util.delimiterOffset(input, '#', iDelimiterOffset3, i7);
                final HttpUrl.Companion companion5 = HttpUrl.Companion;
                encodedQueryNamesAndValues = ((HttpUrl.Companion) companion5).toQueryNamesAndValuesokhttp(Builder.Companion.canonicalizeokhttpdefault(companion5, str, iDelimiterOffset3 + 1, iDelimiterOffset4, QUERY_ENCODE_SET, true, false, true, false, null, 208, null));
                iDelimiterOffset3 = iDelimiterOffset4;
            }
            if (iDelimiterOffset3 < i7 && '#' == input.charAt(iDelimiterOffset3)) {
                encodedFragment = Builder.Companion.canonicalizeokhttpdefault(HttpUrl.Companion, str, iDelimiterOffset3 + 1, i7, "", true, false, false, true, null, 176, null);
            }
            return this;
        }

        private void resolvePath(final String str, int i2, final int i3) {
            if (i2 == i3) {
                return;
            }
            final char cCharAt = str.charAt(i2);
            if ('/' == cCharAt || '\\' == cCharAt) {
                encodedPathSegments.clear();
                encodedPathSegments.add("");
                i2++;
            } else {
                final List<String> list = encodedPathSegments;
                list.set(list.size() - 1, "");
            }
            while (true) {
                int i4 = i2;
                while (i4 < i3) {
                    i2 = Util.delimiterOffset(str, "/\\", i4, i3);
                    final boolean z = i2 < i3;
                    this.push(str, i4, i2, z, true);
                    if (z) {
                        i4 = i2 + 1;
                    }
                }
                return;
            }
        }

        private void push(final String str, final int i2, final int i3, final boolean z, final boolean z2) {
            final String strCanonicalizeokhttpdefault = Builder.Companion.canonicalizeokhttpdefault(HttpUrl.Companion, str, i2, i3, PATH_SEGMENT_ENCODE_SET, z2, false, false, false, null, 240, null);
            if (this.isDot(strCanonicalizeokhttpdefault)) {
                return;
            }
            if (this.isDotDot(strCanonicalizeokhttpdefault)) {
                this.pop();
                return;
            }
          OpenHashSet<Object> r2 = null;
          if (0 == this.encodedPathSegments.get(r2.size() - 1).length()) {
                encodedPathSegments.set(r2.size() - 1, strCanonicalizeokhttpdefault);
            } else {
                encodedPathSegments.add(strCanonicalizeokhttpdefault);
            }
            if (z) {
                encodedPathSegments.add("");
            }
        }

        private boolean isDot(final String str) {
            return areEqual(str, ".") || StringsKt.equals(str, "%2e", true);
        }

        private boolean isDotDot(final String str) {
            return areEqual(str, "..") || StringsKt.equals(str, "%2e.", true) || StringsKt.equals(str, ".%2e", true) || StringsKt.equals(str, "%2e%2e", true);
        }

        private void pop() {
            if (0 == this.encodedPathSegments.remove(r0.size() - 1).length() && !encodedPathSegments.isEmpty()) {
                encodedPathSegments.set(r0.size() - 1, "");
            } else {
                encodedPathSegments.add("");
            }
        }

        public static final class Companion {
            public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            private int schemeDelimiterOffset(final String str, final int i2, final int i3) {
                if (2 > i3 - i2) {
                    return -1;
                }
                final char cCharAt = str.charAt(i2);
                if ((0 > compare(cCharAt, 97) || 0 < compare(cCharAt, 122)) && (0 > compare(cCharAt, 65) || 0 < compare(cCharAt, 90))) {
                    return -1;
                }
                int i4 = i2 + 1;
                while (i4 < i3) {
                    final int i5 = i4 + 1;
                    final char cCharAt2 = str.charAt(i4);
                    if (('a' > cCharAt2 || '{' <= cCharAt2) && (('A' > cCharAt2 || '[' <= cCharAt2) && !(('0' <= cCharAt2 && ':' > cCharAt2) || '+' == cCharAt2 || '-' == cCharAt2 || '.' == cCharAt2))) {
                        if (':' == cCharAt2) {
                            return i4;
                        }
                        return -1;
                    }
                    i4 = i5;
                }
                return -1;
            }

            private int slashCount(final String str, int i2, final int i3) {
                int i4 = 0;
                while (i2 < i3) {
                    final int i5 = i2 + 1;
                    final char cCharAt = str.charAt(i2);
                    if ('\\' != cCharAt && '/' != cCharAt) {
                        break;
                    }
                    i4++;
                    i2 = i5;
                }
                return i4;
            }

            private int portColonOffset(final String str, int i2, final int i3) {
                while (i2 < i3) {
                    final char cCharAt = str.charAt(i2);
                    if ('[' == cCharAt) {
                        do {
                            i2++;
                            if (i2 < i3) {
                            }
                        } while (']' != str.charAt(i2));
                    } else if (':' == cCharAt) {
                        return i2;
                    }
                    i2++;
                }
                return i3;
            }

            private int parsePort(final String str, final int i2, final int i3) throws NumberFormatException {
                try {
                    final int i4 = Integer.parseInt(Builder.Companion.canonicalizeokhttpdefault(HttpUrl.Companion, str, i2, i3, "", false, false, false, false, null, 248, null));
                    if (1 > i4 || 65536 <= i4) {
                        return -1;
                    }
                    return i4;
                } catch (final NumberFormatException unused) {
                    return -1;
                }
            }

          public String canonicalizeokhttpdefault(HttpUrl.Companion companion, String encodedUsername, int i, int i1, String s, boolean b, boolean b1, boolean b2, boolean b3, Object o, int i2, Object o1) {
            return encodedUsername;
          }

          public Object percentDecodeokhttpdefault(HttpUrl.Companion companion, String host, int i, int i1, boolean b, int i2, Object o) {
            return o;
          }

          public void toQueryStringokhttp(List<String> encodedQueryNamesAndValuesokhttp, StringBuilder sb) {

          }
        }
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static String percentDecodeokhttpdefault(final Companion companion, final String str, int i2, int i3, boolean z, final int i4, final Object obj) {
            if (0 != (i4 & 1)) {
                i2 = 0;
            }
            if (0 != (i4 & 2)) {
                i3 = str.length();
            }
            if (0 != (i4 & 4)) {
                z = false;
            }
            return companion.percentDecodeokhttp(str, i2, i3, z);
        }

        public static String canonicalizeokhttpdefault(final Companion companion, final String str, final int i2, final int i3, final String str2, final boolean z, final boolean z2, final boolean z3, final boolean z4, final Charset charset, final int i4, final Object obj) {
          try {
            return companion.canonicalizeokhttp(str, 0 != (i4 & 1) ? 0 : i2, 0 != (i4 & 2) ? str.length() : i3, str2, 0 == (i4 & 8) && z, 0 == (i4 & 16) && z2, 0 == (i4 & 32) && z3, 0 == (i4 & 64) && z4, 0 != (i4 & 128) ? null : charset);
          } catch (EOFException e) {
            throw new RuntimeException(e);
          }
        }

        public int defaultPort(final String scheme) {
            checkNotNullParameter(scheme, "scheme");
            if (areEqual(scheme, ProxyConfig.MATCH_HTTP)) {
                return 80;
            }
            return areEqual(scheme, ProxyConfig.MATCH_HTTPS) ? 443 : -1;
        }

        public void toPathStringokhttp(final List<String> list, final StringBuilder out) {
            checkNotNullParameter(list, "<this>");
            checkNotNullParameter(out, "out");
            final int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                out.append('/');
                out.append(list.get(i2));
            }
        }

        public void toQueryStringokhttp(final List<String> list, final StringBuilder out) {
            checkNotNullParameter(list, "<this>");
            checkNotNullParameter(out, "out");
            final IntProgression progressionsStep = RangesKt.step(RangesKt.until(0, list.size()), 2);
            int first = progressionsStep.getFirst();
            final int last = progressionsStep.getLast();
            final int step = progressionsStep.getStep();
            if ((0 >= step || first > last) && (0 <= step || last > first)) {
                return;
            }
            while (true) {
                final int i2 = first + step;
                final String str = list.get(first);
                final String str2 = list.get(first + 1);
                if (0 < first) {
                    out.append('&');
                }
                out.append(str);
                if (null != str2) {
                    out.append('=');
                    out.append(str2);
                }
                if (first == last) {
                    return;
                } else {
                    first = i2;
                }
            }
        }

        public List<String> toQueryNamesAndValuesokhttp(final String str) {
            checkNotNullParameter(str, "<this>");
            final ArrayList arrayList = new ArrayList();
            int i2 = 0;
            while (i2 <= str.length()) {
                int iIndexOfdefault = indexOf((CharSequence) str, '&', i2, false);
                if (-1 == iIndexOfdefault) {
                    iIndexOfdefault = str.length();
                }
                final int i3 = iIndexOfdefault;
                final int iIndexOfdefault2 = indexOf((CharSequence) str, '=', i2, false);
                if (-1 == iIndexOfdefault2 || iIndexOfdefault2 > i3) {
                    final String strSubstring = str.substring(i2, i3);
                    checkNotNullExpressionValue(strSubstring, "this as java.lang.String\u2026ing(startIndex, endIndex)");
                    arrayList.add(strSubstring);
                    arrayList.add(null);
                } else {
                    final String strSubstring2 = str.substring(i2, iIndexOfdefault2);
                    checkNotNullExpressionValue(strSubstring2, "this as java.lang.String\u2026ing(startIndex, endIndex)");
                    arrayList.add(strSubstring2);
                    final String strSubstring3 = str.substring(iIndexOfdefault2 + 1, i3);
                    checkNotNullExpressionValue(strSubstring3, "this as java.lang.String\u2026ing(startIndex, endIndex)");
                    arrayList.add(strSubstring3);
                }
                i2 = i3 + 1;
            }
            return arrayList;
        }

        public HttpUrl get(final String str) {
            checkNotNullParameter(str, "<this>");
            return new Builder().parseokhttp(null, str).build();
        }

        public HttpUrl parse(final String str) {
            checkNotNullParameter(str, "<this>");
            try {
                return this.get(str);
            } catch (final IllegalArgumentException unused) {
                return null;
            }
        }

        public HttpUrl get(final URL url) {
            checkNotNullParameter(url, "<this>");
            final String string = url.toString();
            checkNotNullExpressionValue(string, "toString()");
            return this.parse(string);
        }

        public HttpUrl get(final URI uri) {
            checkNotNullParameter(uri, "<this>");
            final String string = uri.toString();
            checkNotNullExpressionValue(string, "toString()");
            return this.parse(string);
        }


        public HttpUrl m1785deprecated_get(final String url) {
            checkNotNullParameter(url, "url");
            return this.get(url);
        }
        public HttpUrl m1788deprecated_parse(final String url) {
            checkNotNullParameter(url, "url");
            return this.parse(url);
        }


        public HttpUrl m1787deprecated_get(final URL url) {
            checkNotNullParameter(url, "url");
            return this.get(url);
        }


        public HttpUrl m1786deprecated_get(final URI uri) {
            checkNotNullParameter(uri, "uri");
            return this.get(uri);
        }

        public String percentDecodeokhttp(final String str, final int i2, final int i3, final boolean z) {
            checkNotNullParameter(str, "<this>");
            int i4 = i2;
            while (i4 < i3) {
                final int i5 = i4 + 1;
                final char cCharAt = str.charAt(i4);
                if ('%' == cCharAt || ('+' == cCharAt && z)) {
                    final Buffer buffer = new Buffer();
                    buffer.writeUtf8(str, i2, i4);
                    this.writePercentDecoded(buffer, str, i4, i3, z);
                    return buffer.readUtf8();
                }
                i4 = i5;
            }
            final String strSubstring = str.substring(i2, i3);
            checkNotNullExpressionValue(strSubstring, "this as java.lang.String\u2026ing(startIndex, endIndex)");
            return strSubstring;
        }

        private void writePercentDecoded(final Buffer buffer, final String str, int i2, final int i3, final boolean z) {
            int i4;
            while (i2 < i3) {
                final int iCodePointAt = str.codePointAt(i2);
                if (37 == iCodePointAt && (i4 = i2 + 2) < i3) {
                    final int hexDigit = Util.parseHexDigit(str.charAt(i2 + 1));
                    final int hexDigit2 = Util.parseHexDigit(str.charAt(i4));
                    if (-1 != hexDigit && -1 != hexDigit2) {
                        buffer.writeByte((hexDigit << 4) + hexDigit2);
                        i2 = Character.charCount(37) + i4;
                    } else {
                        buffer.writeUtf8CodePoint(37);
                        i2 += Character.charCount(37);
                    }
                } else if (43 == iCodePointAt && z) {
                    buffer.writeByte(32);
                    i2++;
                } else {
                    buffer.writeUtf8CodePoint(iCodePointAt);
                    i2 += Character.charCount(iCodePointAt);
                }
            }
        }

        private boolean isPercentEncoded(final String str, final int i2, final int i3) {
            final int i4 = i2 + 2;
            return i4 < i3 && '%' == str.charAt(i2) && -1 != Util.parseHexDigit(str.charAt(i2 + 1)) && -1 != Util.parseHexDigit(str.charAt(i4));
        }


        public String canonicalizeokhttp(final String str, final int i2, final int i3, final String encodeSet, final boolean z, final boolean z2, final boolean z3, final boolean z4, final Charset charset) throws EOFException {
            checkNotNullParameter(str, "<this>");
            checkNotNullParameter(encodeSet, "encodeSet");
            int iCharCount = i2;
            while (iCharCount < i3) {
                final int iCodePointAt = str.codePointAt(iCharCount);
                if (32 <= iCodePointAt && 127 != iCodePointAt && ((128 > iCodePointAt || z4) && !contains((CharSequence) encodeSet, (char) iCodePointAt, false))) {
                    if (37 != iCodePointAt) {
                        if (43 == iCodePointAt || !z3) {
                            iCharCount += Character.charCount(iCodePointAt);
                        }
                    } else if (z) {
                        if (z2) {
                            if (this.isPercentEncoded(str, iCharCount, i3)) {
                            }
                        }
                        if (43 == iCodePointAt) {
                        }
                        iCharCount += Character.charCount(iCodePointAt);
                    }
                    final Buffer buffer = new Buffer();
                    buffer.writeUtf8(str, i2, iCharCount);
                    this.writeCanonicalized(buffer, str, iCharCount, i3, encodeSet, z, z2, z3, z4, charset);
                    return buffer.readUtf8();
                }
                final Buffer buffer2 = new Buffer();
                buffer2.writeUtf8(str, i2, iCharCount);
                this.writeCanonicalized(buffer2, str, iCharCount, i3, encodeSet, z, z2, z3, z4, charset);
                return buffer2.readUtf8();
            }
            final String strSubstring = str.substring(i2, i3);
            checkNotNullExpressionValue(strSubstring, "this as java.lang.String\u2026ing(startIndex, endIndex)");
            return strSubstring;
        }

        private void writeCanonicalized(final Buffer buffer, final String str, final int i2, final int i3, final String str2, final boolean z, final boolean z2, final boolean z3, final boolean z4, final Charset charset) throws EOFException {
            int iCharCount = i2;
            Buffer buffer2 = null;
            while (iCharCount < i3) {
                final int iCodePointAt = str.codePointAt(iCharCount);
                if (!z || (9 != iCodePointAt && 10 != iCodePointAt && 12 != iCodePointAt && 13 != iCodePointAt)) {
                    if (43 == iCodePointAt && z3) {
                        buffer.writeUtf8(z ? "+" : "%2B");
                    } else if (32 > iCodePointAt || 127 == iCodePointAt || ((128 <= iCodePointAt && !z4) || contains((CharSequence) str2, (char) iCodePointAt, false))) {
                        if (null == buffer2) {
                            buffer2 = new Buffer();
                        }
                        if (null != charset || areEqual(charset, StandardCharsets.UTF_8)) {
                            buffer2.writeUtf8CodePoint(iCodePointAt);
                        } else {
                            buffer2.writeString(str, iCharCount, Character.charCount(iCodePointAt) + iCharCount, charset);
                        }
                        while (!buffer2.exhausted()) {
                            final byte b2 = buffer2.readByte();
                            buffer.writeByte(37);
                            buffer.writeByte(HEX_DIGITS[((b2 & 255) >> 4) & 15]);
                            buffer.writeByte(HEX_DIGITS[b2 & 15]);
                        }
                    } else if (37 != iCodePointAt) {
                        buffer.writeUtf8CodePoint(iCodePointAt);
                    } else {
                        if (z) {
                            if (z2) {
                                if (!this.isPercentEncoded(str, iCharCount, i3)) {
                                }
                            }
                            buffer.writeUtf8CodePoint(iCodePointAt);
                        }
                        if (null == buffer2) {
                        }
                        if (null != charset) {
                            buffer2.writeUtf8CodePoint(iCodePointAt);
                            while (!buffer2.exhausted()) {
                            }
                        }
                    }
                }
                iCharCount += Character.charCount(iCodePointAt);
            }
        }
    }
}
