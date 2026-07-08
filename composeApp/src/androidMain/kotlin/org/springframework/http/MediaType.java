package org.springframework.http;

import androidx.webkit.ProxyConfig;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.util.StringUtils;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.*;

public class MediaType implements Comparable<MediaType> {
    public static final MediaType ALL;
    public static final MediaType APPLICATION_ATOM_XML;
    public static final MediaType APPLICATION_FORM_URLENCODED;
    public static final MediaType APPLICATION_JSON;
    public static final MediaType APPLICATION_OCTET_STREAM;
    public static final MediaType APPLICATION_RSS_XML;
    public static final MediaType APPLICATION_WILDCARD_XML;
    public static final MediaType APPLICATION_XHTML_XML;
    public static final MediaType APPLICATION_XML;
    public static final MediaType IMAGE_GIF;
    public static final MediaType IMAGE_JPEG;
    public static final MediaType IMAGE_PNG;
    public static final MediaType MULTIPART_FORM_DATA;
    public static final Comparator<MediaType> QUALITY_VALUE_COMPARATOR;
    public static final Comparator<MediaType> SPECIFICITY_COMPARATOR;
    public static final MediaType TEXT_HTML;
    public static final MediaType TEXT_PLAIN;
    public static final MediaType TEXT_XML;
    private static final BitSet TOKEN;
    private final Map<String, String> parameters;
    private final String subtype;
    private final String type;
    static {
        final BitSet bitSet = new BitSet(128);
        for (int i2 = 0; 31 >= i2; i2++) {
            bitSet.set(i2);
        }
        bitSet.set(127);
        final BitSet bitSet2 = new BitSet(128);
        bitSet2.set(40);
        bitSet2.set(41);
        bitSet2.set(60);
        bitSet2.set(62);
        bitSet2.set(64);
        bitSet2.set(44);
        bitSet2.set(59);
        bitSet2.set(58);
        bitSet2.set(92);
        bitSet2.set(34);
        bitSet2.set(47);
        bitSet2.set(91);
        bitSet2.set(93);
        bitSet2.set(63);
        bitSet2.set(61);
        bitSet2.set(123);
        bitSet2.set(125);
        bitSet2.set(32);
        bitSet2.set(9);
        final BitSet bitSet3 = new BitSet(128);
        TOKEN = bitSet3;
        bitSet3.set(0, 128);
        bitSet3.andNot(bitSet);
        bitSet3.andNot(bitSet2);
        ALL = MediaType.valueOf("*/*");
        APPLICATION_ATOM_XML = MediaType.valueOf("application/atom+xml");
        APPLICATION_RSS_XML = MediaType.valueOf("application/rss+xml");
        APPLICATION_FORM_URLENCODED = MediaType.valueOf("application/x-www-form-urlencoded");
        APPLICATION_JSON = MediaType.valueOf("application/json");
        APPLICATION_OCTET_STREAM = MediaType.valueOf("application/octet-stream");
        APPLICATION_XHTML_XML = MediaType.valueOf("application/xhtml+xml");
        APPLICATION_XML = MediaType.valueOf("application/xml");
        APPLICATION_WILDCARD_XML = MediaType.valueOf("application/*+xml");
        IMAGE_GIF = MediaType.valueOf("image/gif");
        IMAGE_JPEG = MediaType.valueOf("image/jpeg");
        IMAGE_PNG = MediaType.valueOf("image/png");
        MULTIPART_FORM_DATA = MediaType.valueOf("multipart/form-data");
        TEXT_HTML = MediaType.valueOf("text/html");
        TEXT_PLAIN = MediaType.valueOf("text/plain");
        TEXT_XML = MediaType.valueOf("text/xml");
        SPECIFICITY_COMPARATOR = new Comparator<MediaType>() { // from class: org.springframework.http.MediaType.1
            @Override // java.util.Comparator
            public int compare(final MediaType mediaType, final MediaType mediaType2) {
                if (mediaType.isWildcardType() && !mediaType2.isWildcardType()) {
                    return 1;
                }
                if (mediaType2.isWildcardType() && !mediaType.isWildcardType()) {
                    return -1;
                }
                if (!mediaType.getType().equals(mediaType2.getType())) {
                    return 0;
                }
                if (mediaType.isWildcardSubtype() && !mediaType2.isWildcardSubtype()) {
                    return 1;
                }
                if (mediaType2.isWildcardSubtype() && !mediaType.isWildcardSubtype()) {
                    return -1;
                }
                if (!mediaType.getSubtype().equals(mediaType2.getSubtype())) {
                    return 0;
                }
                final int compare = Double.compare(mediaType2.getQualityValue(), mediaType.getQualityValue());
                if (0 != compare) {
                    return compare;
                }
                final int size = mediaType.parameters.size();
                final int size2 = mediaType2.parameters.size();
                if (size2 < size) {
                    return -1;
                }
                return size2 == size ? 0 : 1;
            }
        };
        QUALITY_VALUE_COMPARATOR = new Comparator<MediaType>() { // from class: org.springframework.http.MediaType.2
            @Override // java.util.Comparator
            public int compare(final MediaType mediaType, final MediaType mediaType2) {
                final int compare = Double.compare(mediaType2.getQualityValue(), mediaType.getQualityValue());
                if (0 != compare) {
                    return compare;
                }
                if (mediaType.isWildcardType() && !mediaType2.isWildcardType()) {
                    return 1;
                }
                if (mediaType2.isWildcardType() && !mediaType.isWildcardType()) {
                    return -1;
                }
                if (!mediaType.getType().equals(mediaType2.getType())) {
                    return 0;
                }
                if (mediaType.isWildcardSubtype() && !mediaType2.isWildcardSubtype()) {
                    return 1;
                }
                if (mediaType2.isWildcardSubtype() && !mediaType.isWildcardSubtype()) {
                    return -1;
                }
                if (!mediaType.getSubtype().equals(mediaType2.getSubtype())) {
                    return 0;
                }
                final int size = mediaType.parameters.size();
                final int size2 = mediaType2.parameters.size();
                if (size2 < size) {
                    return -1;
                }
                return size2 == size ? 0 : 1;
            }
        };
    }
    public MediaType(final String str, final String str2, final Map<String, String> map) {
        Assert.hasLength(str, "type must not be empty");
        Assert.hasLength(str2, "subtype must not be empty");
        this.checkToken(str);
        this.checkToken(str2);
        final Locale locale = Locale.ENGLISH;
        type = str.toLowerCase(locale);
        subtype = str2.toLowerCase(locale);
        if (!CollectionUtils.isEmpty(map)) {
            final LinkedCaseInsensitiveMap linkedCaseInsensitiveMap = new LinkedCaseInsensitiveMap(map.size(), locale);
            for (final Map.Entry<String, String> entry : map.entrySet()) {
                final String key = entry.getKey();
                final String value = entry.getValue();
                this.checkParameters(key, value);
                linkedCaseInsensitiveMap.put(key, value);
            }
            parameters = Collections.unmodifiableMap(linkedCaseInsensitiveMap);
            return;
        }
        parameters = Collections.emptyMap();
    }
    private void checkToken(final String str) {
        for (int i2 = 0; i2 < str.length(); i2++) {
            final char charAt = str.charAt(i2);
            if (!MediaType.TOKEN.get(charAt)) {
                throw new IllegalArgumentException("Invalid token character '" + charAt + "' in token \"" + str + "\"");
            }
        }
    }
    private void checkParameters(final String str, final String str2) {
        Assert.hasLength(str, "parameter attribute must not be empty");
        Assert.hasLength(str2, "parameter value must not be empty");
        this.checkToken(str);
        if ("q".equals(str)) {
            final String unquote = this.unquote(str2);
            final double parseDouble = Double.parseDouble(unquote);
            Assert.isTrue(0.0d <= parseDouble && 1.0d >= parseDouble, "Invalid quality value \"" + unquote + "\": should be between 0.0 and 1.0");
            return;
        }
        if ("charset".equals(str)) {
            Charset.forName(this.unquote(str2));
        } else {
            if (this.isQuotedString(str2)) {
                return;
            }
            this.checkToken(str2);
        }
    }
    private boolean isQuotedString(final String str) {
        if (2 > str.length()) {
            return false;
        }
        return (str.startsWith("\"") && str.endsWith("\"")) || (str.startsWith("'") && str.endsWith("'"));
    }
    private String unquote(final String str) {
        if (null == str) {
            return null;
        }
        return this.isQuotedString(str) ? str.substring(1, str.length() - 1) : str;
    }
    public String getType() {
        return type;
    }
    public boolean isWildcardType() {
        return ProxyConfig.MATCH_ALL_SCHEMES.equals(type);
    }
    public String getSubtype() {
        return subtype;
    }
    public boolean isWildcardSubtype() {
        return ProxyConfig.MATCH_ALL_SCHEMES.equals(subtype) || subtype.startsWith("*+");
    }
    public double getQualityValue() {
        final String parameter = this.getParameter("q");
        if (null != parameter) {
            return Double.parseDouble(this.unquote(parameter));
        }
        return 1.0d;
    }
    public String getParameter(final String str) {
        return parameters.get(str);
    }
    public int compareTo(final MediaType mediaType) {
        final int compareToIgnoreCase = type.compareToIgnoreCase(mediaType.type);
        if (0 != compareToIgnoreCase) {
            return compareToIgnoreCase;
        }
        final int compareToIgnoreCase2 = subtype.compareToIgnoreCase(mediaType.subtype);
        if (0 != compareToIgnoreCase2) {
            return compareToIgnoreCase2;
        }
        final int size = parameters.size() - mediaType.parameters.size();
        if (0 != size) {
            return size;
        }
        final Comparator comparator = String.CASE_INSENSITIVE_ORDER;
        final TreeSet treeSet = new TreeSet(comparator);
        treeSet.addAll(parameters.keySet());
        final TreeSet treeSet2 = new TreeSet(comparator);
        treeSet2.addAll(mediaType.parameters.keySet());
        final Iterator it = treeSet.iterator();
        final Iterator it2 = treeSet2.iterator();
        while (it.hasNext()) {
            final String str = (String) it.next();
            final String str2 = (String) it2.next();
            final int compareToIgnoreCase3 = str.compareToIgnoreCase(str2);
            if (0 != compareToIgnoreCase3) {
                return compareToIgnoreCase3;
            }
            final String str3 = parameters.get(str);
            String str4 = mediaType.parameters.get(str2);
            if (null == str4) {
                str4 = "";
            }
            final int compareTo = str3.compareTo(str4);
            if (0 != compareTo) {
                return compareTo;
            }
        }
        return 0;
    }
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaType mediaType)) {
            return false;
        }
        return type.equalsIgnoreCase(mediaType.type) && subtype.equalsIgnoreCase(mediaType.subtype) && parameters.equals(mediaType.parameters);
    }
    public int hashCode() {
        return (((type.hashCode() * 31) + subtype.hashCode()) * 31) + parameters.hashCode();
    }
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        this.appendTo(sb);
        return sb.toString();
    }
    private void appendTo(final StringBuilder sb) {
        sb.append(type);
        sb.append('/');
        sb.append(subtype);
        this.appendTo(parameters, sb);
    }
    private void appendTo(final Map<String, String> map, final StringBuilder sb) {
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(';');
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
        }
    }
    public static MediaType valueOf(final String str) {
        return MediaType.parseMediaType(str);
    }
    public static MediaType parseMediaType(final String str) {
        final LinkedHashMap linkedHashMap;
        Assert.hasLength(str, "'mediaType' must not be empty");
        final String[] strArr = StringUtils.tokenizeToStringArray(str, ";");
        String trim = strArr[0].trim();
        if (ProxyConfig.MATCH_ALL_SCHEMES.equals(trim)) {
            trim = "*/*";
        }
        final int indexOf = trim.indexOf(47);
        if (-1 == indexOf) {
            throw new InvalidMediaTypeException(str, "does not contain '/'");
        }
        if (indexOf == trim.length() - 1) {
            throw new InvalidMediaTypeException(str, "does not contain subtype after '/'");
        }
        final String substring = trim.substring(0, indexOf);
        final String substring2 = trim.substring(indexOf + 1);
        if (ProxyConfig.MATCH_ALL_SCHEMES.equals(substring) && !ProxyConfig.MATCH_ALL_SCHEMES.equals(substring2)) {
            throw new InvalidMediaTypeException(str, "wildcard type is legal only in '*/*' (all media types)");
        }
        if (1 < strArr.length) {
            linkedHashMap = new LinkedHashMap(strArr.length - 1);
            for (int i2 = 1; i2 < strArr.length; i2++) {
                final String str2 = strArr[i2];
                final int indexOf2 = str2.indexOf(61);
                if (-1 != indexOf2) {
                    linkedHashMap.put(str2.substring(0, indexOf2), str2.substring(indexOf2 + 1));
                }
            }
        } else {
            linkedHashMap = null;
        }
        try {
            return new MediaType(substring, substring2, linkedHashMap);
        } catch (final UnsupportedCharsetException e2) {
            throw new InvalidMediaTypeException(str, "unsupported charset '" + e2.getCharsetName() + "'");
        } catch (final IllegalArgumentException e3) {
            throw new InvalidMediaTypeException(str, e3.getMessage());
        }
    }
    public static List<MediaType> parseMediaTypes(final String str) {
        if (!StringUtils.hasLength(str)) {
            return Collections.emptyList();
        }
        final String[] split = str.split(",\\s*");
        final ArrayList arrayList = new ArrayList(split.length);
        for (final String str2 : split) {
            arrayList.add(MediaType.parseMediaType(str2));
        }
        return arrayList;
    }
    public static String toString(final Collection<MediaType> collection) {
        final StringBuilder sb = new StringBuilder();
        final Iterator<MediaType> it = collection.iterator();
        while (it.hasNext()) {
            it.next().appendTo(sb);
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
