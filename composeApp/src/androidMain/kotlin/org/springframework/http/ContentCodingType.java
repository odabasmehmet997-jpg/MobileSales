package org.springframework.http;

import androidx.webkit.ProxyConfig;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.util.StringUtils;

import java.util.*;

public class ContentCodingType implements Comparable<ContentCodingType> {
    public static final ContentCodingType ALL;
    public static final ContentCodingType GZIP;
    public static final ContentCodingType IDENTITY;
    public static final Comparator<ContentCodingType> QUALITY_VALUE_COMPARATOR;
    private static final BitSet TOKEN;
    private final Map<String, String> parameters;
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
        ALL = ContentCodingType.valueOf(ProxyConfig.MATCH_ALL_SCHEMES);
        IDENTITY = ContentCodingType.valueOf("identity");
        GZIP = ContentCodingType.valueOf("gzip");
        QUALITY_VALUE_COMPARATOR = new Comparator<ContentCodingType>() { // from class: org.springframework.http.ContentCodingType.1
            @Override // java.util.Comparator
            public int compare(final ContentCodingType contentCodingType, final ContentCodingType contentCodingType2) {
                final int compare = Double.compare(contentCodingType2.getQualityValue(), contentCodingType.getQualityValue());
                if (0 != compare) {
                    return compare;
                }
                if (contentCodingType.isWildcardType() && !contentCodingType2.isWildcardType()) {
                    return 1;
                }
                if (contentCodingType2.isWildcardType() && !contentCodingType.isWildcardType()) {
                    return -1;
                }
                contentCodingType.getType().equals(contentCodingType2.getType());
                return 0;
            }
        };
    }
    public ContentCodingType(final String str, final Map<String, String> map) {
        Assert.hasLength(str, "'type' must not be empty");
        this.checkToken(str);
        final Locale locale = Locale.ENGLISH;
        type = str.toLowerCase(locale);
        if (!CollectionUtils.isEmpty(map)) {
            final LinkedCaseInsensitiveMap linkedCaseInsensitiveMap = new LinkedCaseInsensitiveMap(map.size(), locale);
            for (final Map.Entry<String, String> entry : map.entrySet()) {
                final String key = entry.getKey();
                final String value = entry.getValue();
                this.checkParameters(key, value);
                linkedCaseInsensitiveMap.put( key, this.unquote(value));
            }
            parameters = Collections.unmodifiableMap(linkedCaseInsensitiveMap);
            return;
        }
        parameters = Collections.emptyMap();
    }
    private void checkToken(final String str) {
        for (int i2 = 0; i2 < str.length(); i2++) {
            final char charAt = str.charAt(i2);
            if (!ContentCodingType.TOKEN.get(charAt)) {
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
        if (this.isQuotedString(str2)) {
            return;
        }
        this.checkToken(str2);
    }
    private boolean isQuotedString(final String str) {
        return 1 < str.length() && str.startsWith("\"") && str.endsWith("\"");
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
    public double getQualityValue() {
        final String parameter = this.getParameter("q");
        if (null != parameter) {
            return Double.parseDouble(parameter);
        }
        return 1.0d;
    }
    public String getParameter(final String str) {
        return parameters.get(str);
    }
    public int compareTo(final ContentCodingType contentCodingType) {
        final int compareToIgnoreCase = type.compareToIgnoreCase(contentCodingType.type);
        if (0 != compareToIgnoreCase) {
            return compareToIgnoreCase;
        }
        final int size = parameters.size() - contentCodingType.parameters.size();
        if (0 != size) {
            return size;
        }
        final Comparator comparator = String.CASE_INSENSITIVE_ORDER;
        final TreeSet treeSet = new TreeSet(comparator);
        treeSet.addAll(parameters.keySet());
        final TreeSet treeSet2 = new TreeSet(comparator);
        treeSet2.addAll(contentCodingType.parameters.keySet());
        final Iterator it = treeSet.iterator();
        final Iterator it2 = treeSet2.iterator();
        while (it.hasNext()) {
            final String str = (String) it.next();
            final String str2 = (String) it2.next();
            final int compareToIgnoreCase2 = str.compareToIgnoreCase(str2);
            if (0 != compareToIgnoreCase2) {
                return compareToIgnoreCase2;
            }
            final String str3 = parameters.get(str);
            String str4 = contentCodingType.parameters.get(str2);
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
        if (!(obj instanceof ContentCodingType contentCodingType)) {
            return false;
        }
        return type.equalsIgnoreCase(contentCodingType.type) && parameters.equals(contentCodingType.parameters);
    }
    public int hashCode() {
        return (type.hashCode() * 31) + parameters.hashCode();
    }
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        this.appendTo(sb);
        return sb.toString();
    }
    private void appendTo(final StringBuilder sb) {
        sb.append(type);
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
    public static ContentCodingType valueOf(final String str) {
        return ContentCodingType.parseCodingType(str);
    }
    public static ContentCodingType parseCodingType(final String str) {
        final LinkedHashMap linkedHashMap;
        Assert.hasLength(str, "'codingType' must not be empty");
        final String[] strArr = StringUtils.tokenizeToStringArray(str, ";");
        final String trim = strArr[0].trim();
        if (1 < strArr.length) {
            linkedHashMap = new LinkedHashMap(strArr.length - 1);
            for (int i2 = 1; i2 < strArr.length; i2++) {
                final String str2 = strArr[i2];
                final int indexOf = str2.indexOf(61);
                if (-1 != indexOf) {
                    linkedHashMap.put(str2.substring(0, indexOf), str2.substring(indexOf + 1));
                }
            }
        } else {
            linkedHashMap = null;
        }
        return new ContentCodingType(trim, linkedHashMap);
    }
    public static List<ContentCodingType> parseCodingTypes(final String str) {
        if (!StringUtils.hasLength(str)) {
            return Collections.emptyList();
        }
        final String[] split = str.split(",");
        final ArrayList arrayList = new ArrayList(split.length);
        for (final String str2 : split) {
            arrayList.add(ContentCodingType.parseCodingType(str2));
        }
        return arrayList;
    }
    public static String toString(final Collection<ContentCodingType> collection) {
        final StringBuilder sb = new StringBuilder();
        final Iterator<ContentCodingType> it = collection.iterator();
        while (it.hasNext()) {
            it.next().appendTo(sb);
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
