package org.springframework.http;

import androidx.webkit.ProxyConfig;
import com.fasterxml.jackson.core.JsonFactory;
import org.springframework.util.Assert;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.net.URI;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HttpHeaders implements MultiValueMap<String, String>, Serializable {
    public static final String ACCEPT = "Accept";
    public static final String ACCEPT_CHARSET = "Accept-Charset";
    public static final String ACCEPT_ENCODING = "Accept-Encoding";
    public static final String ACCEPT_LANGUAGE = "Accept-Language";
    public static final String ACCEPT_RANGES = "Accept-Ranges";
    public static final String AGE = "Age";
    public static final String ALLOW = "Allow";
    public static final String AUTHORIZATION = "Authorization";
    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String CONNECTION = "Connection";
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String CONTENT_LANGUAGE = "Content-Language";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_LOCATION = "Content-Location";
    public static final String CONTENT_RANGE = "Content-Range";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String COOKIE = "Cookie";
    public static final String DATE = "Date";
    public static final String ETAG = "ETag";
    public static final String EXPECT = "Expect";
    public static final String EXPIRES = "Expires";
    public static final String FROM = "From";
    public static final String HOST = "Host";
    public static final String IF_MATCH = "If-Match";
    public static final String IF_MODIFIED_SINCE = "If-Modified-Since";
    public static final String IF_NONE_MATCH = "If-None-Match";
    public static final String IF_RANGE = "If-Range";
    public static final String IF_UNMODIFIED_SINCE = "If-Unmodified-Since";
    public static final String LAST_MODIFIED = "Last-Modified";
    public static final String LINK = "Link";
    public static final String LOCATION = "Location";
    public static final String MAX_FORWARDS = "Max-Forwards";
    public static final String ORIGIN = "Origin";
    public static final String PRAGMA = "Pragma";
    public static final String PROXY_AUTHENTICATE = "Proxy-Authenticate";
    public static final String PROXY_AUTHORIZATION = "Proxy-Authorization";
    public static final String RANGE = "Range";
    public static final String REFERER = "Referer";
    public static final String RETRY_AFTER = "Retry-After";
    public static final String SERVER = "Server";
    public static final String SET_COOKIE = "Set-Cookie";
    public static final String SET_COOKIE2 = "Set-Cookie2";
    public static final String TE = "TE";
    public static final String TRAILER = "Trailer";
    public static final String TRANSFER_ENCODING = "Transfer-Encoding";
    public static final String UPGRADE = "Upgrade";
    public static final String USER_AGENT = "User-Agent";
    public static final String VARY = "Vary";
    public static final String VIA = "Via";
    public static final String WARNING = "Warning";
    public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
    private static final long serialVersionUID = -8578554704772377436L;
    private final Map<String, List<String>> headers;
    private static final String[] DATE_FORMATS = {"EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM dd HH:mm:ss yyyy"};
    private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    public HttpHeaders() {
        this(new LinkedCaseInsensitiveMap(8, Locale.ENGLISH), false);
    }
    private HttpHeaders(final Map<String, List<String>> map, final boolean z) {
        Assert.notNull(map, "'headers' must not be null");
        if (z) {
            final LinkedCaseInsensitiveMap linkedCaseInsensitiveMap = new LinkedCaseInsensitiveMap(map.size(), Locale.ENGLISH);
            for (final Map.Entry<String, List<String>> entry : map.entrySet()) {
                linkedCaseInsensitiveMap.put(entry.getKey(), Collections.unmodifiableList(entry.getValue()));
            }
            headers = Collections.unmodifiableMap(linkedCaseInsensitiveMap);
            return;
        }
        headers = map;
    }
    public void setAccept(final List<MediaType> list) {
        this.set(HttpHeaders.ACCEPT, MediaType.toString(list));
    }
    public List<MediaType> getAccept() {
        final String first = this.getFirst(HttpHeaders.ACCEPT);
        final List<MediaType> parseMediaTypes = null != first ? MediaType.parseMediaTypes(first) : Collections.emptyList();
        if (1 != parseMediaTypes.size()) {
            return parseMediaTypes;
        }
        final List<String> list = this.get(HttpHeaders.ACCEPT);
        assert list != null;
        return 1 < list.size() ? MediaType.parseMediaTypes(StringUtils.collectionToCommaDelimitedString(list)) : parseMediaTypes;
    }
    public void setAcceptCharset(final List<Charset> list) {
        final StringBuilder sb = new StringBuilder();
        final Iterator<Charset> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next().name().toLowerCase(Locale.ENGLISH));
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        this.set(HttpHeaders.ACCEPT_CHARSET, sb.toString());
    }
    public List<Charset> getAcceptCharset() {
        final var arrayList = new ArrayList();
        final String first = this.getFirst(HttpHeaders.ACCEPT_CHARSET);
        if (null != first) {
            final String[] split = first.split(",\\s*");
            final int length = split.length;
            for (int i2 = 0; i2 < length; i2++) {
                String str = split[i2];
                final int indexOf = str.indexOf(59);
                if (-1 != indexOf) {
                    str = str.substring(0, indexOf);
                }
                if (!str.equals(ProxyConfig.MATCH_ALL_SCHEMES)) {
                    arrayList.add(Charset.forName(str));
                }
            }
        }
        return arrayList;
    }
    public void setAcceptEncoding(final List<ContentCodingType> list) {
        this.set(HttpHeaders.ACCEPT_ENCODING, ContentCodingType.toString(list));
    }
    public void setAcceptEncoding(final ContentCodingType contentCodingType) {
        this.setAcceptEncoding(Collections.singletonList(contentCodingType));
    }
    public List<ContentCodingType> getAcceptEncoding() {
        final String first = this.getFirst(HttpHeaders.ACCEPT_ENCODING);
        return null != first ? ContentCodingType.parseCodingTypes(first) : Collections.emptyList();
    }
    public void setAcceptLanguage(final String str) {
        this.set(HttpHeaders.ACCEPT_LANGUAGE, str);
    }
    public String getAcceptLanguage() {
        return this.getFirst(HttpHeaders.ACCEPT_LANGUAGE);
    }
    public void setAllow(final Set<HttpMethod> set) {
        this.set(HttpHeaders.ALLOW, StringUtils.collectionToCommaDelimitedString(set));
    }
    public Set<HttpMethod> getAllow() {
        final String first = this.getFirst(HttpHeaders.ALLOW);
        if (!StringUtils.isEmpty(first)) {
            final ArrayList arrayList = new ArrayList(5);
            for (final String str : first.split(",\\s*")) {
                arrayList.add(HttpMethod.valueOf(str));
            }
            return EnumSet.copyOf(arrayList);
        }
        return EnumSet.noneOf(HttpMethod.class);
    }
    public void setAuthorization(final HttpAuthentication httpAuthentication) {
        this.set(HttpHeaders.AUTHORIZATION, httpAuthentication.getHeaderValue());
    }
    public String getAuthorization() {
        return this.getFirst(HttpHeaders.AUTHORIZATION);
    }
    public void setCacheControl(final String str) {
        this.set(HttpHeaders.CACHE_CONTROL, str);
    }
    public String getCacheControl() {
        return this.getFirst(HttpHeaders.CACHE_CONTROL);
    }
    public void setConnection(final String str) {
        this.set(HttpHeaders.CONNECTION, str);
    }
    public void setConnection(final List<String> list) {
        this.set(HttpHeaders.CONNECTION, this.toCommaDelimitedString(list));
    }
    public List<String> getConnection() {
        return this.getFirstValueAsList(HttpHeaders.CONNECTION);
    }
    public void setContentDispositionFormData(final String str, final String str2) {
        Assert.notNull(str, "'name' must not be null");
        final StringBuilder sb = new StringBuilder("form-data; name=\"");
        sb.append(str);
        sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
        if (null != str2) {
            sb.append("; filename=\"");
            sb.append(str2);
            sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
        }
        this.set(HttpHeaders.CONTENT_DISPOSITION, sb.toString());
    }
    public void setContentEncoding(final List<ContentCodingType> list) {
        this.set(HttpHeaders.CONTENT_ENCODING, ContentCodingType.toString(list));
    }
    public void setContentEncoding(final ContentCodingType contentCodingType) {
        this.setContentEncoding(Collections.singletonList(contentCodingType));
    }
    public List<ContentCodingType> getContentEncoding() {
        final String first = this.getFirst(HttpHeaders.CONTENT_ENCODING);
        return null != first ? ContentCodingType.parseCodingTypes(first) : Collections.emptyList();
    }
    public void setContentLength(final long j2) {
        this.set(HttpHeaders.CONTENT_LENGTH, Long.toString(j2));
    }
    public long getContentLength() {
        final String first = this.getFirst(HttpHeaders.CONTENT_LENGTH);
        return null != first ? Long.parseLong(first) : -1L;
    }
    public void setContentType(final MediaType mediaType) {
        Assert.isTrue(!mediaType.isWildcardType(), "'Content-Type' cannot contain wildcard type '*'");
        Assert.isTrue(!mediaType.isWildcardSubtype(), "'Content-Type' cannot contain wildcard subtype '*'");
        this.set(HttpHeaders.CONTENT_TYPE, mediaType.toString());
    }
    public MediaType getContentType() {
        final String first = this.getFirst(HttpHeaders.CONTENT_TYPE);
        return null != first ? MediaType.parseMediaType(first) : null;
    }
    public void setDate(final long j2) {
        this.setDate(HttpHeaders.DATE, j2);
    }
    public long getDate() {
        final String first = this.getFirst(HttpHeaders.DATE);
        return null != first ? Long.parseLong(first) : -1L;
    }
    public void setETag(final String str) {
        if (null != str) {
            Assert.isTrue(str.startsWith("\"") || str.startsWith("W/"), "Invalid eTag, does not start with W/ or \"");
            Assert.isTrue(str.endsWith("\""), "Invalid eTag, does not end with \"");
        }
        this.set(HttpHeaders.ETAG, str);
    }
    public String getETag() {
        return this.getFirst(HttpHeaders.ETAG);
    }
    public void setExpires(final long j2) {
        this.setDate(HttpHeaders.EXPIRES, j2);
    }
    public long getExpires() {
        try {
            return this.getFirstDate(HttpHeaders.EXPIRES);
        } catch (final IllegalArgumentException unused) {
            return -1L;
        }
    }
    public void setIfModifiedSince(final long j2) {
        this.setDate(HttpHeaders.IF_MODIFIED_SINCE, j2);
    }
    public long getIfNotModifiedSince() {
        return this.getIfModifiedSince();
    }

    public long getIfModifiedSince() {
        return this.getFirstDate(HttpHeaders.IF_MODIFIED_SINCE);
    }

    public void setIfNoneMatch(final String str) {
        this.set(HttpHeaders.IF_NONE_MATCH, str);
    }

    public void setIfNoneMatch(final List<String> list) {
        this.set(HttpHeaders.IF_NONE_MATCH, this.toCommaDelimitedString(list));
    }

    protected String toCommaDelimitedString(final List<String> list) {
        final StringBuilder sb = new StringBuilder();
        final Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public List<String> getIfNoneMatch() {
        return this.getFirstValueAsList(HttpHeaders.IF_NONE_MATCH);
    }

    protected List<String> getFirstValueAsList(final String str) {
        final ArrayList arrayList = new ArrayList();
        final String first = this.getFirst(str);
        if (null != first) {
            Collections.addAll(arrayList, first.split(",\\s*"));
        }
        return arrayList;
    }

    public void setLastModified(final long j2) {
        this.setDate(HttpHeaders.LAST_MODIFIED, j2);
    }

    public long getLastModified() {
        return this.getFirstDate(HttpHeaders.LAST_MODIFIED);
    }

    public void setLocation(final URI uri) {
        this.set(HttpHeaders.LOCATION, uri.toASCIIString());
    }

    public URI getLocation() {
        final String first = this.getFirst(HttpHeaders.LOCATION);
        if (null != first) {
            return URI.create(first);
        }
        return null;
    }

    public void setOrigin(final String str) {
        this.set(HttpHeaders.ORIGIN, str);
    }

    public String getOrigin() {
        return this.getFirst(HttpHeaders.ORIGIN);
    }

    public void setPragma(final String str) {
        this.set(HttpHeaders.PRAGMA, str);
    }

    public String getPragma() {
        return this.getFirst(HttpHeaders.PRAGMA);
    }

    public void setUserAgent(final String str) {
        this.set(HttpHeaders.USER_AGENT, str);
    }

    public String getUserAgent() {
        return this.getFirst(HttpHeaders.USER_AGENT);
    }

    public void setUpgrade(final String str) {
        this.set(HttpHeaders.UPGRADE, str);
    }

    public String getUpgrade() {
        return this.getFirst(HttpHeaders.UPGRADE);
    }

    public long getFirstDate(final String str) {
        final String first = this.getFirst(str);
        if (null == first) {
            return -1L;
        }
        for (final String str2 : HttpHeaders.DATE_FORMATS) {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2, Locale.US);
            simpleDateFormat.setTimeZone(HttpHeaders.GMT);
            try {
                return simpleDateFormat.parse(first).getTime();
            } catch (final ParseException unused) {
            }
        }
        throw new IllegalArgumentException("Cannot parse date value \"" + first + "\" for \"" + str + "\" header");
    }

    public void setDate(final String str, final long j2) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(HttpHeaders.DATE_FORMATS[0], Locale.US);
        simpleDateFormat.setTimeZone(HttpHeaders.GMT);
        this.set(str, simpleDateFormat.format(new Date(j2)));
    }

    public String getFirst(final String str) {
        final List<String> list = headers.get(str);
        if (null != list) {
            return list.get(0);
        }
        return null;
    }
    public void add(final String str, final String str2) {
        List<String> list = headers.get(str);
        if (null == list) {
            list = new LinkedList<>();
            headers.put(str, list);
        }
        list.add(str2);
    }

    public void set(final String str, final String str2) {
        final LinkedList linkedList = new LinkedList();
        linkedList.add(str2);
        headers.put(str, linkedList);
    }

    public void setAll(final Map<String, String> map) {
        for (final Entry<String, String> entry : map.entrySet()) {
            this.set(entry.getKey(), entry.getValue());
        }
    }

    public Map<String, String> toSingleValueMap() {
        final LinkedHashMap linkedHashMap = new LinkedHashMap(headers.size());
        for (final Entry<String, List<String>> entry : headers.entrySet()) {
            linkedHashMap.put(entry.getKey(), entry.getValue().get(0));
        }
        return linkedHashMap;
    }

    
    public int size() {
        return headers.size();
    }

    
    public boolean isEmpty() {
        return headers.isEmpty();
    }

    
    public boolean containsKey(final Object obj) {
        return headers.containsKey(obj);
    }

    
    public boolean containsValue(final Object obj) {
        return headers.containsValue(obj);
    }

    
    public List<String> get(final Object obj) {
        return headers.get(obj);
    }

    
    public List<String> put(final String str, final List<String> list) {
        return headers.put(str, list);
    }

    
    public List<String> remove(final Object obj) {
        return headers.remove(obj);
    }

    
    public void putAll(final Map<? extends String, ? extends List<String>> map) {
        headers.putAll(map);
    }
    public void clear() {
        headers.clear();
    }
    public Set<String> keySet() {
        return headers.keySet();
    }
    public Collection<List<String>> values() {
        return headers.values();
    }
    public Set<Entry<String, List<String>>> entrySet() {
        return headers.entrySet();
    }
 
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof HttpHeaders) {
            return headers.equals(((HttpHeaders) obj).headers);
        }
        return false;
    } 
    public int hashCode() {
        return headers.hashCode();
    }

    public String toString() {
        return headers.toString();
    }

    public static HttpHeaders readOnlyHttpHeaders(final HttpHeaders httpHeaders) {
        return new HttpHeaders(httpHeaders, true);
    }
}
