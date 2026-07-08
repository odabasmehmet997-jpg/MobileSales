package org.springframework.web.util;

import org.springframework.util.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UriComponentsBuilder {
    private String fragment;
    private String host;
    private String port;
    private String scheme;
    private String ssp;
    private String userInfo;
    private static final Pattern QUERY_PARAM_PATTERN = Pattern.compile("([^&=]+)(=?)([^&]+)?");
    private static final Pattern URI_PATTERN = Pattern.compile("^(([^:/?#]+):)?(//(([^@\\[/?#]*)@)?(\\[[\\p{XDigit}\\:\\.]*[%\\p{Alnum}]*\\]|[^\\[/?#:]*)(:(\\d*(?:\\{[^/]+?\\})?))?)?([^?#]*)(\\?([^#]*))?(#(.*))?");
    private static final Pattern HTTP_URL_PATTERN = Pattern.compile("^(?i)(http|https):(//(([^@\\[/?#]*)@)?(\\[[\\p{XDigit}\\:\\.]*[%\\p{Alnum}]*\\]|[^\\[/?#:]*)(:(\\d*(?:\\{[^/]+?\\})?))?)?([^?#]*)(\\?(.*))?");
    private CompositePathComponentBuilder pathBuilder = new CompositePathComponentBuilder();
    private final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap();
    private interface PathComponentBuilder {
        HierarchicalUriComponents.PathComponent build();
    }
    protected UriComponentsBuilder() {
    }
    public static UriComponentsBuilder fromUriString(final String str) {
        Assert.hasLength(str, "'uri' must not be empty");
        final Matcher matcher = UriComponentsBuilder.URI_PATTERN.matcher(str);
        if (matcher.matches()) {
            final UriComponentsBuilder uriComponentsBuilder = new UriComponentsBuilder();
            final String group = matcher.group(2);
            final String group2 = matcher.group(5);
            final String group3 = matcher.group(6);
            final String group4 = matcher.group(8);
            final String group5 = matcher.group(9);
            final String group6 = matcher.group(11);
            final String group7 = matcher.group(13);
            final boolean z = StringUtils.hasLength(group) && !str.substring(group.length()).startsWith(":/");
            uriComponentsBuilder.scheme(group);
            if (z) {
                String substring = str.substring(group.length()).substring(1);
                if (StringUtils.hasLength(group7)) {
                    substring = substring.substring(0, substring.length() - (group7.length() + 1));
                }
                uriComponentsBuilder.schemeSpecificPart(substring);
            } else {
                uriComponentsBuilder.userInfo(group2);
                uriComponentsBuilder.host(group3);
                if (StringUtils.hasLength(group4)) {
                    uriComponentsBuilder.port(group4);
                }
                uriComponentsBuilder.path(group5);
                uriComponentsBuilder.query(group6);
            }
            if (StringUtils.hasText(group7)) {
                uriComponentsBuilder.fragment(group7);
            }
            return uriComponentsBuilder;
        }
        throw new IllegalArgumentException("[" + str + "] is not a valid URI");
    }
    public UriComponents build() {
        return this.build(false);
    }
    public UriComponents build(final boolean z) {
        if (null != this.ssp) {
            return new OpaqueUriComponents(scheme, ssp, fragment);
        }
        return new HierarchicalUriComponents(scheme, userInfo, host, port, pathBuilder.build(), queryParams, fragment, z, true);
    }
    private void resetHierarchicalComponents() {
        userInfo = null;
        host = null;
        port = null;
        pathBuilder = new CompositePathComponentBuilder();
        queryParams.clear();
    }
    private void resetSchemeSpecificPart() {
        ssp = null;
    }
    public UriComponentsBuilder scheme(final String str) {
        scheme = str;
        return this;
    }
    public UriComponentsBuilder schemeSpecificPart(final String str) {
        ssp = str;
        this.resetHierarchicalComponents();
        return this;
    }
    public UriComponentsBuilder userInfo(final String str) {
        userInfo = str;
        this.resetSchemeSpecificPart();
        return this;
    }
    public UriComponentsBuilder host(final String str) {
        host = str;
        this.resetSchemeSpecificPart();
        return this;
    }
    public UriComponentsBuilder port(final String str) {
        port = str;
        this.resetSchemeSpecificPart();
        return this;
    }
    public UriComponentsBuilder path(final String str) {
        pathBuilder.addPath(str);
        this.resetSchemeSpecificPart();
        return this;
    }
    public UriComponentsBuilder query(final String str) {
        if (null != str) {
            final Matcher matcher = UriComponentsBuilder.QUERY_PARAM_PATTERN.matcher(str);
            while (matcher.find()) {
                final String group = matcher.group(1);
                final String group2 = matcher.group(2);
                String group3 = matcher.group(3);
                if (null == group3) {
                    group3 = StringUtils.hasLength(group2) ? "" : null;
                }
                this.queryParam(group, group3);
            }
        } else {
            queryParams.clear();
        }
        this.resetSchemeSpecificPart();
        return this;
    }
    public UriComponentsBuilder queryParam(final String str, final Object... objArr) {
        Assert.notNull(str, "'name' must not be null");
        if (!ObjectUtils.isEmpty(objArr)) {
            final int length = objArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                final Object obj = objArr[i2];
                queryParams.add(str, null != obj ? obj.toString() : null);
            }
        } else {
            queryParams.add(str, null);
        }
        this.resetSchemeSpecificPart();
        return this;
    }
    public UriComponentsBuilder fragment(final String str) {
        if (null != str) {
            Assert.hasLength(str, "'fragment' must not be empty");
            fragment = str;
        } else {
            fragment = null;
        }
        return this;
    }
    private static class CompositePathComponentBuilder implements PathComponentBuilder {
        private final LinkedList<PathComponentBuilder> componentBuilders = new LinkedList<>();

        public void addPath(String str) {
            if (StringUtils.hasText(str)) {
                final PathSegmentComponentBuilder pathSegmentComponentBuilder = this.getLastBuilder(PathSegmentComponentBuilder.class);
                FullPathComponentBuilder fullPathComponentBuilder = this.getLastBuilder(FullPathComponentBuilder.class);
                if (null != pathSegmentComponentBuilder && !str.startsWith("/")) {
                    str = "/" + str;
                }
                if (null == fullPathComponentBuilder) {
                    fullPathComponentBuilder = new FullPathComponentBuilder();
                    componentBuilders.add(fullPathComponentBuilder);
                }
                fullPathComponentBuilder.append(str);
            }
        }

        private <T> T getLastBuilder(final Class<T> cls) {
            if (componentBuilders.isEmpty()) {
                return null;
            }
            final T t = (T) componentBuilders.getLast();
            if (cls.isInstance(t)) {
                return t;
            }
            return null;
        }

        @Override // org.springframework.web.util.UriComponentsBuilder.PathComponentBuilder
        public HierarchicalUriComponents.PathComponent build() {
            final ArrayList arrayList = new ArrayList(componentBuilders.size());
            final Iterator<PathComponentBuilder> it = componentBuilders.iterator();
            while (it.hasNext()) {
                final HierarchicalUriComponents.PathComponent build = it.next().build();
                if (null != build) {
                    arrayList.add(build);
                }
            }
            if (arrayList.isEmpty()) {
                return HierarchicalUriComponents.NULL_PATH_COMPONENT;
            }
            if (1 == arrayList.size()) {
                return (HierarchicalUriComponents.PathComponent) arrayList.get(0);
            }
            return new HierarchicalUriComponents.PathComponentComposite(arrayList);
        }
    }
    private static class FullPathComponentBuilder implements PathComponentBuilder {
        private final StringBuilder path;

        private FullPathComponentBuilder() {
            path = new StringBuilder();
        }

        public void append(final String str) {
            path.append(str);
        }

        @Override // org.springframework.web.util.UriComponentsBuilder.PathComponentBuilder
        public HierarchicalUriComponents.PathComponent build() {
            if (0 == this.path.length()) {
                return null;
            }
            String sb = path.toString();
            while (true) {
                final int indexOf = sb.indexOf("//");
                if (-1 != indexOf) {
                    sb = sb.substring(0, indexOf) + sb.substring(indexOf + 1);
                } else {
                    return new HierarchicalUriComponents.FullPathComponent(sb);
                }
            }
        }
    }
    private static class PathSegmentComponentBuilder implements PathComponentBuilder {
        private final List<String> pathSegments = new LinkedList();

        private PathSegmentComponentBuilder() {
        }

        @Override // org.springframework.web.util.UriComponentsBuilder.PathComponentBuilder
        public HierarchicalUriComponents.PathComponent build() {
            if (pathSegments.isEmpty()) {
                return null;
            }
            return new HierarchicalUriComponents.PathSegmentComponent(pathSegments);
        }
    }
}
