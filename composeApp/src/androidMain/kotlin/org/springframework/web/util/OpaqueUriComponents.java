package org.springframework.web.util;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

final class OpaqueUriComponents extends UriComponents {
    private static final MultiValueMap<String, String> QUERY_PARAMS_NONE = new LinkedMultiValueMap(0);
    private final String ssp;
    public UriComponents encode(final String str) throws UnsupportedEncodingException {
        return this;
    }
    public String getHost() {
        return null;
    }
    public String getPath() {
        return null;
    }
    public int getPort() {
        return -1;
    }
    public String getQuery() {
        return null;
    }
    public String getUserInfo() {
        return null;
    }
    public UriComponents normalize() {
        return this;
    }
    OpaqueUriComponents(final String str, final String str2, final String str3) {
        super(str, str3);
        ssp = str2;
    }
    public String getSchemeSpecificPart() {
        return ssp;
    }
    public List<String> getPathSegments() {
        return Collections.emptyList();
    }
    public MultiValueMap<String, String> getQueryParams() {
        return OpaqueUriComponents.QUERY_PARAMS_NONE;
    }
    protected UriComponents expandInternal(final UriTemplateVariables uriTemplateVariables) {
        return new OpaqueUriComponents(expandUriComponent(this.getScheme(), uriTemplateVariables), expandUriComponent(this.ssp, uriTemplateVariables), expandUriComponent(this.getFragment(), uriTemplateVariables));
    }
    public String toUriString() {
        final StringBuilder sb = new StringBuilder();
        if (null != getScheme()) {
            sb.append(this.getScheme());
            sb.append(':');
        }
        final String str = ssp;
        if (null != str) {
            sb.append(str);
        }
        if (null != getFragment()) {
            sb.append('#');
            sb.append(this.getFragment());
        }
        return sb.toString();
    }
    public URI toUri() {
        try {
            return new URI(this.getScheme(), ssp, this.getFragment());
        } catch (final URISyntaxException e2) {
            throw new IllegalStateException("Could not create URI object: " + e2.getMessage(), e2);
        }
    }
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OpaqueUriComponents opaqueUriComponents)) {
            return false;
        }
        return ObjectUtils.nullSafeEquals(this.getScheme(), opaqueUriComponents.getScheme()) && ObjectUtils.nullSafeEquals(ssp, opaqueUriComponents.ssp) && ObjectUtils.nullSafeEquals(this.getFragment(), opaqueUriComponents.getFragment());
    }
    public int hashCode() {
        return (((ObjectUtils.nullSafeHashCode(this.getScheme()) * 31) + ObjectUtils.nullSafeHashCode(ssp)) * 31) + ObjectUtils.nullSafeHashCode(this.getFragment());
    }
}
