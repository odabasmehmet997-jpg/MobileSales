package org.springframework.web.util;

import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class UriComponents implements Serializable {
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final Pattern NAMES_PATTERN = Pattern.compile("\\{([^/]+?)\\}");
    private final String fragment;
    private final String scheme;
    public interface UriTemplateVariables {
        Object SKIP_VALUE = UriTemplateVariables.class;

        Object getValue(String str);
    }
    public abstract UriComponents encode(String str) throws UnsupportedEncodingException;
    abstract UriComponents expandInternal(UriTemplateVariables uriTemplateVariables);
    public abstract String getHost();
    public abstract String getPath();
    public abstract List<String> getPathSegments();
    public abstract int getPort();
    public abstract String getQuery();
    public abstract MultiValueMap<String, String> getQueryParams();
    public abstract String getSchemeSpecificPart();
    public abstract String getUserInfo();
    public abstract UriComponents normalize();
    public abstract URI toUri();
    public abstract String toUriString();
    protected UriComponents(final String str, final String str2) {
        scheme = str;
        fragment = str2;
    }
    public final String getScheme() {
        return scheme;
    }
    public final String getFragment() {
        return fragment;
    }
    public final UriComponents encode() {
        try {
            return this.encode(UriComponents.DEFAULT_ENCODING);
        } catch (final UnsupportedEncodingException e2) {
            throw new IllegalStateException(e2);
        }
    }
    public final UriComponents expand(final Map<String, ?> map) {
        Assert.notNull(map, "'uriVariables' must not be null");
        return this.expandInternal(new MapTemplateVariables(map));
    }
    public final UriComponents expand(final Object... objArr) {
        Assert.notNull(objArr, "'uriVariableValues' must not be null");
        return this.expandInternal(new VarArgsTemplateVariables(objArr));
    }
    public final UriComponents expand(final UriTemplateVariables uriTemplateVariables) {
        Assert.notNull(uriTemplateVariables, "'uriVariables' must not be null");
        return this.expandInternal(uriTemplateVariables);
    }
    public final String toString() {
        return this.toUriString();
    }
    static String expandUriComponent(final String str, final UriTemplateVariables uriTemplateVariables) {
        if (null == str) {
            return null;
        }
        if (-1 == str.indexOf(123)) {
            return str;
        }
        final Matcher matcher = UriComponents.NAMES_PATTERN.matcher(str);
        final StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            final Object value = uriTemplateVariables.getValue(UriComponents.getVariableName(matcher.group(1)));
            if (!UriTemplateVariables.SKIP_VALUE.equals(value)) {
                matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement(UriComponents.getVariableValueAsString(value)));
            }
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }
    private static String getVariableName(final String str) {
        final int indexOf = str.indexOf(58);
        return -1 != indexOf ? str.substring(0, indexOf) : str;
    }
    private static String getVariableValueAsString(final Object obj) {
        return null != obj ? obj.toString() : "";
    }
    private record MapTemplateVariables(Map<String, ?> uriVariables) implements UriTemplateVariables {

            public Object getValue(final String str) {
                if (!uriVariables.containsKey(str)) {
                    throw new IllegalArgumentException("Map has no value for '" + str + "'");
                }
                return uriVariables.get(str);
            }
        }
    private record VarArgsTemplateVariables(Iterator<Object> valueIterator) implements UriTemplateVariables {
            private VarArgsTemplateVariables(final Object... valueIterator) {
                this(Arrays.asList(valueIterator).iterator());
            }
            public Object getValue(final String str) {
                if (!valueIterator.hasNext()) {
                    throw new IllegalArgumentException("Not enough variable values available to expand '" + str + "'");
                }
                return valueIterator.next();
            }
        }
}
