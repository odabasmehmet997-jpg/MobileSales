package org.springframework.web.util;

import org.springframework.util.*;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.*;

final class HierarchicalUriComponents extends UriComponents {
    static final PathComponent NULL_PATH_COMPONENT = new PathComponent() { 
        public PathComponent encode(final String str) throws UnsupportedEncodingException {
            return this;
        }

        public boolean equals(final Object obj) {
            return this == obj;
        }
        public PathComponent expand(UriComponents.UriTemplateVariables uriTemplateVariables) {
            return this;
        }
        public String path() {
            return null;
        }

        public int hashCode() {
            return 42;
        }
        public void verify() {
        }

        public List<String> pathSegments() {
            return Collections.emptyList();
        }
    };
    private static final char PATH_DELIMITER = '/';
    private final boolean encoded;
    private final String host;
    private final PathComponent path;
    private final String port;
    private final MultiValueMap<String, String> queryParams;
    private final String userInfo;
    interface PathComponent extends Serializable {
        PathComponent encode(String str) throws UnsupportedEncodingException;

        PathComponent expand(UriComponents.UriTemplateVariables uriTemplateVariables);

        String path();

        List<String> pathSegments();

        void verify();
        }
    public String getSchemeSpecificPart() {
        return null;
    }
    HierarchicalUriComponents(final String str, final String str2, final String str3, final String str4, final PathComponent pathComponent, final MultiValueMap<String, String> multiValueMap, final String str5, final boolean z, final boolean z2) {
        super(str, str5);
        userInfo = str2;
        host = str3;
        port = str4;
        path = null == pathComponent ? HierarchicalUriComponents.NULL_PATH_COMPONENT : pathComponent;
        queryParams = CollectionUtils.unmodifiableMultiValueMap(null == multiValueMap ? new LinkedMultiValueMap<>(0) : multiValueMap);
        encoded = z;
        if (z2) {
            this.verify();
        }
    }
    public String getUserInfo() {
        return userInfo;
    }
    public String getHost() {
        return host;
    }
    public int getPort() {
        final String str = port;
        if (null == str) {
            return -1;
        }
        if (str.contains("{")) {
            throw new IllegalStateException("The port contains a URI variable but has not been expanded yet: " + port);
        }
        return Integer.parseInt(port);
    }
    public String getPath() {
        return path.path();
    }
    public List<String> getPathSegments() {
        return path.pathSegments();
    }
    public String getQuery() {
        if (queryParams.isEmpty()) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        for (final Map.Entry<String, List<String>> entry : queryParams.entrySet()) {
            final String key = entry.getKey();
            final List list = entry.getValue();
            if (CollectionUtils.isEmpty(list)) {
                if (0 != sb.length()) {
                    sb.append('&');
                }
                sb.append(key);
            } else {
                for (final Object obj : list) {
                    if (0 != sb.length()) {
                        sb.append('&');
                    }
                    sb.append(key);
                    if (null != obj) {
                        sb.append('=');
                        sb.append(obj);
                    }
                }
            }
        }
        return sb.toString();
    }
    public MultiValueMap<String, String> getQueryParams() {
        return queryParams;
    }
    public HierarchicalUriComponents encode(final String str) throws UnsupportedEncodingException {
        Assert.hasLength(str, "Encoding must not be empty");
        if (encoded) {
            return this;
        }
        final String encodeUriComponent = HierarchicalUriComponents.encodeUriComponent(this.getScheme(), str, Type.SCHEME);
        final String encodeUriComponent2 = HierarchicalUriComponents.encodeUriComponent(userInfo, str, Type.USER_INFO);
        final String encodeUriComponent3 = HierarchicalUriComponents.encodeUriComponent(host, str, this.getHostType());
        final PathComponent encode = path.encode(str);
        final LinkedMultiValueMap linkedMultiValueMap = new LinkedMultiValueMap(queryParams.size());
        for (final Map.Entry<String, List<String>> entry : queryParams.entrySet()) {
            final String encodeUriComponent4 = HierarchicalUriComponents.encodeUriComponent(entry.getKey(), str, Type.QUERY_PARAM);
            final ArrayList arrayList = new ArrayList(entry.getValue().size());
            final Iterator it = ((List) entry.getValue()).iterator();
            while (it.hasNext()) {
                arrayList.add(HierarchicalUriComponents.encodeUriComponent((String) it.next(), str, Type.QUERY_PARAM));
            }
            linkedMultiValueMap.put(encodeUriComponent4, arrayList);
        }
        return new HierarchicalUriComponents(encodeUriComponent, encodeUriComponent2, encodeUriComponent3, port, encode, linkedMultiValueMap, HierarchicalUriComponents.encodeUriComponent(this.getFragment(), str, Type.FRAGMENT), true, false);
    }
    static String encodeUriComponent(final String str, final String str2, final Type type) throws UnsupportedEncodingException {
        if (null == str) {
            return null;
        }
        Assert.hasLength(str2, "Encoding must not be empty");
        return new String(HierarchicalUriComponents.encodeBytes(str.getBytes(str2), type), StandardCharsets.US_ASCII);
    }
    private static byte[] encodeBytes(final byte[] bArr, final Type type) {
        Assert.notNull(bArr, "Source must not be null");
        Assert.notNull(type, "Type must not be null");
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
        final int length = bArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            byte b2 = bArr[i2];
            if (0 > b2) {
                b2 = (byte) (b2 + 256);
            }
            if (type.isAllowed(b2)) {
                byteArrayOutputStream.write(b2);
            } else {
                byteArrayOutputStream.write(37);
                final char upperCase = Character.toUpperCase(Character.forDigit((b2 >> 4) & 15, 16));
                final char upperCase2 = Character.toUpperCase(Character.forDigit(b2 & 15, 16));
                byteArrayOutputStream.write(upperCase);
                byteArrayOutputStream.write(upperCase2);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
    private Type getHostType() {
        final String str = host;
        return (null == str || !str.startsWith("[")) ? Type.HOST_IPV4 : Type.HOST_IPV6;
    }
    private void verify() {
        if (encoded) {
            HierarchicalUriComponents.verifyUriComponent(this.getScheme(), Type.SCHEME);
            HierarchicalUriComponents.verifyUriComponent(userInfo, Type.USER_INFO);
            HierarchicalUriComponents.verifyUriComponent(host, this.getHostType());
            path.verify();
            for (final Map.Entry<String, List<String>> entry : queryParams.entrySet()) {
                HierarchicalUriComponents.verifyUriComponent(entry.getKey(), Type.QUERY_PARAM);
                final Iterator it = ((List) entry.getValue()).iterator();
                while (it.hasNext()) {
                    HierarchicalUriComponents.verifyUriComponent((String) it.next(), Type.QUERY_PARAM);
                }
            }
            HierarchicalUriComponents.verifyUriComponent(this.getFragment(), Type.FRAGMENT);
        }
    }
    public static void verifyUriComponent(final String str, final Type type) {
        if (null == str) {
            return;
        }
        final int length = str.length();
        int i2 = 0;
        while (i2 < length) {
            final char charAt = str.charAt(i2);
            if ('%' == charAt) {
                final int i3 = i2 + 2;
                if (i3 >= length) {
                    throw new IllegalArgumentException("Invalid encoded sequence \"" + str.substring(i2) + "\"");
                }
                final char charAt2 = str.charAt(i2 + 1);
                final char charAt3 = str.charAt(i3);
                final int digit = Character.digit(charAt2, 16);
                final int digit2 = Character.digit(charAt3, 16);
                if (-1 == digit || -1 == digit2) {
                    throw new IllegalArgumentException("Invalid encoded sequence \"" + str.substring(i2) + "\"");
                }
                i2 = i3;
            } else if (!type.isAllowed(charAt)) {
                throw new IllegalArgumentException("Invalid character '" + charAt + "' for " + type.name() + " in \"" + str + "\"");
            }
            i2++;
        }
    }
    protected HierarchicalUriComponents expandInternal(UriComponents.UriTemplateVariables uriTemplateVariables) {
        Assert.state(!encoded, "Cannot expand an already encoded UriComponents object");
        final String expandUriComponent = expandUriComponent(this.getScheme(), uriTemplateVariables);
        final String expandUriComponent2 = expandUriComponent(userInfo, uriTemplateVariables);
        final String expandUriComponent3 = expandUriComponent(host, uriTemplateVariables);
        final String expandUriComponent4 = expandUriComponent(port, uriTemplateVariables);
        final PathComponent expand = path.expand(uriTemplateVariables);
        final LinkedMultiValueMap linkedMultiValueMap = new LinkedMultiValueMap(queryParams.size());
        for (final Map.Entry<String, List<String>> entry : queryParams.entrySet()) {
            final String expandUriComponent5 = expandUriComponent(entry.getKey(), uriTemplateVariables);
            final ArrayList arrayList = new ArrayList(entry.getValue().size());
            final Iterator it = ((List) entry.getValue()).iterator();
            while (it.hasNext()) {
                arrayList.add(expandUriComponent((String) it.next(), uriTemplateVariables));
            }
            linkedMultiValueMap.put(expandUriComponent5,arrayList);
        }
        return new HierarchicalUriComponents(expandUriComponent, expandUriComponent2, expandUriComponent3, expandUriComponent4, expand, linkedMultiValueMap, expandUriComponent(this.getFragment(), uriTemplateVariables), false, false);
    }
    public UriComponents normalize() {
        return new HierarchicalUriComponents(this.getScheme(), userInfo, host, port, new FullPathComponent(StringUtils.cleanPath(this.getPath())), queryParams, this.getFragment(), encoded, false);
    }
    public String toUriString() {
        final StringBuilder sb = new StringBuilder();
        if (null != getScheme()) {
            sb.append(this.getScheme());
            sb.append(':');
        }
        if (null != this.userInfo || null != this.host) {
            sb.append("//");
            final String str = userInfo;
            if (null != str) {
                sb.append(str);
                sb.append('@');
            }
            final String str2 = host;
            if (null != str2) {
                sb.append(str2);
            }
            if (-1 != getPort()) {
                sb.append(':');
                sb.append(port);
            }
        }
        final String path = this.getPath();
        if (StringUtils.hasLength(path)) {
            if (0 != sb.length() && '/' != path.charAt(0)) {
                sb.append(HierarchicalUriComponents.PATH_DELIMITER);
            }
            sb.append(path);
        }
        final String query = this.getQuery();
        if (null != query) {
            sb.append('?');
            sb.append(query);
        }
        if (null != getFragment()) {
            sb.append('#');
            sb.append(this.getFragment());
        }
        return sb.toString();
    }
    public URI toUri() {
        try {
            if (encoded) {
                return new URI(this.toString());
            }
            String path = this.getPath();
            if (StringUtils.hasLength(path) && '/' != path.charAt(0) && (null != getScheme() || null != getUserInfo() || null != getHost() || -1 != getPort())) {
                path = HierarchicalUriComponents.PATH_DELIMITER + path;
            }
            return new URI(this.getScheme(), this.userInfo, this.host, this.getPort(), path, this.getQuery(), this.getFragment());
        } catch (final URISyntaxException e2) {
            throw new IllegalStateException("Could not create URI object: " + e2.getMessage(), e2);
        }
    }
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HierarchicalUriComponents hierarchicalUriComponents)) {
            return false;
        }
        return ObjectUtils.nullSafeEquals(this.getScheme(), hierarchicalUriComponents.getScheme()) && ObjectUtils.nullSafeEquals(this.userInfo, hierarchicalUriComponents.userInfo) && ObjectUtils.nullSafeEquals(this.host, hierarchicalUriComponents.host) && this.getPort() == hierarchicalUriComponents.getPort() && path.equals(hierarchicalUriComponents.path) && queryParams.equals(hierarchicalUriComponents.queryParams) && ObjectUtils.nullSafeEquals(this.getFragment(), hierarchicalUriComponents.getFragment());
    }
    public int hashCode() {
        return (((((((((((ObjectUtils.nullSafeHashCode(this.getScheme()) * 31) + ObjectUtils.nullSafeHashCode(userInfo)) * 31) + ObjectUtils.nullSafeHashCode(host)) * 31) + ObjectUtils.nullSafeHashCode(port)) * 31) + path.hashCode()) * 31) + queryParams.hashCode()) * 31) + ObjectUtils.nullSafeHashCode(this.getFragment());
    }
    enum Type {
        SCHEME { // from class: org.springframework.web.util.HierarchicalUriComponents.Type.1
            @Override // org.springframework.web.util.HierarchicalUriComponents.Type
            public boolean isAllowed(final int i2) {
                return this.isAlpha(i2) || this.isDigit(i2) || 43 == i2 || 45 == i2 || 46 == i2;
            }
        },
        AUTHORITY { // from class: org.springframework.web.util.HierarchicalUriComponents.Type.2
            @Override // org.springframework.web.util.HierarchicalUriComponents.Type
            public boolean isAllowed(final int i2) {
                return this.isUnreserved(i2) || this.isSubDelimiter(i2) || 58 == i2 || 64 == i2;
            }
        },
        USER_INFO { // from class: org.springframework.web.util.HierarchicalUriComponents.Type.3
            @Override // org.springframework.web.util.HierarchicalUriComponents.Type
            public boolean isAllowed(final int i2) {
                return this.isUnreserved(i2) || this.isSubDelimiter(i2) || 58 == i2;
            }
        },
        HOST_IPV4 { // from class: org.springframework.web.util.HierarchicalUriComponents.Type.4
            @Override // org.springframework.web.util.HierarchicalUriComponents.Type
            public boolean isAllowed(final int i2) {
                return this.isUnreserved(i2) || this.isSubDelimiter(i2);
            }
        },
        HOST_IPV6 { // from class: org.springframework.web.util.HierarchicalUriComponents.Type.5
            @Override // org.springframework.web.util.HierarchicalUriComponents.Type
            public boolean isAllowed(final int i2) {
                return this.isUnreserved(i2) || this.isSubDelimiter(i2) || 91 == i2 || 93 == i2 || 58 == i2;
            }
        },
        PORT { // from class: org.springframework.web.util.HierarchicalUriComponents.Type.6
            @Override // org.springframework.web.util.HierarchicalUriComponents.Type
            public boolean isAllowed(final int i2) {
                return this.isDigit(i2);
            }
        },
        PATH { // from class: org.springframework.web.util.HierarchicalUriComponents.Type.7
            @Override // org.springframework.web.util.HierarchicalUriComponents.Type
            public boolean isAllowed(final int i2) {
                return this.isPchar(i2) || 47 == i2;
            }
        },
        PATH_SEGMENT { // from class: org.springframework.web.util.HierarchicalUriComponents.Type.8
            @Override // org.springframework.web.util.HierarchicalUriComponents.Type
            public boolean isAllowed(final int i2) {
                return this.isPchar(i2);
            }
        },
        QUERY { // from class: org.springframework.web.util.HierarchicalUriComponents.Type.9
            @Override // org.springframework.web.util.HierarchicalUriComponents.Type
            public boolean isAllowed(final int i2) {
                return this.isPchar(i2) || 47 == i2 || 63 == i2;
            }
        },
        QUERY_PARAM { // from class: org.springframework.web.util.HierarchicalUriComponents.Type.10
            @Override // org.springframework.web.util.HierarchicalUriComponents.Type
            public boolean isAllowed(final int i2) {
                if (61 == i2 || 43 == i2 || 38 == i2) {
                    return false;
                }
                return this.isPchar(i2) || 47 == i2 || 63 == i2;
            }
        },
        FRAGMENT { // from class: org.springframework.web.util.HierarchicalUriComponents.Type.11
            @Override // org.springframework.web.util.HierarchicalUriComponents.Type
            public boolean isAllowed(final int i2) {
                return this.isPchar(i2) || 47 == i2 || 63 == i2;
            }
        };

        public abstract boolean isAllowed(int i2);

        protected boolean isAlpha(final int i2) {
            return (97 <= i2 && 122 >= i2) || (65 <= i2 && 90 >= i2);
        }

        protected boolean isDigit(final int i2) {
            return 48 <= i2 && 57 >= i2;
        }

        protected boolean isGenericDelimiter(final int i2) {
            return 58 == i2 || 47 == i2 || 63 == i2 || 35 == i2 || 91 == i2 || 93 == i2 || 64 == i2;
        }

        protected boolean isSubDelimiter(final int i2) {
            return 33 == i2 || 36 == i2 || 38 == i2 || 39 == i2 || 40 == i2 || 41 == i2 || 42 == i2 || 43 == i2 || 44 == i2 || 59 == i2 || 61 == i2;
        }

        protected boolean isReserved(final char c2) {
            return this.isGenericDelimiter(c2) || this.isReserved(c2);
        }

        protected boolean isUnreserved(final int i2) {
            return this.isAlpha(i2) || this.isDigit(i2) || 45 == i2 || 46 == i2 || 95 == i2 || 126 == i2;
        }

        protected boolean isPchar(final int i2) {
            return this.isUnreserved(i2) || this.isSubDelimiter(i2) || 58 == i2 || 64 == i2;
        }
    }
    record FullPathComponent(String path) implements PathComponent {

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
            public List<String> pathSegments() {
                return Collections.unmodifiableList(Arrays.asList(StringUtils.tokenizeToStringArray(path, String.valueOf(PATH_DELIMITER))));
            }

            @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
            public PathComponent encode(final String str) throws UnsupportedEncodingException {
                return new FullPathComponent(encodeUriComponent(this.path, str, Type.PATH));
            }

            @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
            public void verify() {
                verifyUriComponent(path, Type.PATH);
            }

            @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
            public PathComponent expand(UriTemplateVariables uriTemplateVariables) {
                return new FullPathComponent(expandUriComponent(this.path, uriTemplateVariables));
            }

            public boolean equals(final Object obj) {
                return this == obj || ((obj instanceof FullPathComponent) && this.path.equals(((FullPathComponent) obj).path));
            }

    }
    record PathSegmentComponent(List<String> pathSegments) implements PathComponent {
            PathSegmentComponent(final List<String> pathSegments) {
                this.pathSegments = Collections.unmodifiableList(new ArrayList(pathSegments));
            }

            @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
            public String path() {
                final StringBuilder sb = new StringBuilder();
                sb.append(PATH_DELIMITER);
                final Iterator<String> it = pathSegments.iterator();
                while (it.hasNext()) {
                    sb.append(it.next());
                    if (it.hasNext()) {
                        sb.append(PATH_DELIMITER);
                    }
                }
                return sb.toString();
            }

            @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
            public PathComponent encode(final String str) throws UnsupportedEncodingException {
                final List<String> pathSegments = this.pathSegments;
                final ArrayList arrayList = new ArrayList(pathSegments.size());
                final Iterator<String> it = pathSegments.iterator();
                while (it.hasNext()) {
                    arrayList.add(encodeUriComponent(it.next(), str, Type.PATH_SEGMENT));
                }
                return new PathSegmentComponent(arrayList);
            }

            @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
            public void verify() {
                final Iterator<String> it = this.pathSegments.iterator();
                while (it.hasNext()) {
                    verifyUriComponent(it.next(), Type.PATH_SEGMENT);
                }
            }

            @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
            public PathComponent expand(UriTemplateVariables uriTemplateVariables) {
                final List<String> pathSegments = this.pathSegments;
                final ArrayList arrayList = new ArrayList(pathSegments.size());
                final Iterator<String> it = pathSegments.iterator();
                while (it.hasNext()) {
                    arrayList.add(expandUriComponent(it.next(), uriTemplateVariables));
                }
                return new PathSegmentComponent(arrayList);
            }

            public boolean equals(final Object obj) {
                return this == obj || ((obj instanceof PathSegmentComponent) && this.pathSegments.equals(((PathSegmentComponent) obj).pathSegments));
            }

    }
    static final class PathComponentComposite implements PathComponent {
        private final List<PathComponent> pathComponents;

        public PathComponentComposite(final List<PathComponent> list) {
            pathComponents = list;
        }

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public String path() {
            final StringBuilder sb = new StringBuilder();
            final Iterator<PathComponent> it = pathComponents.iterator();
            while (it.hasNext()) {
                sb.append(it.next().path());
            }
            return sb.toString();
        }

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public List<String> pathSegments() {
            final ArrayList arrayList = new ArrayList();
            final Iterator<PathComponent> it = pathComponents.iterator();
            while (it.hasNext()) {
                arrayList.addAll(it.next().pathSegments());
            }
            return arrayList;
        }

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public PathComponent encode(final String str) throws UnsupportedEncodingException {
            final ArrayList arrayList = new ArrayList(pathComponents.size());
            final Iterator<PathComponent> it = pathComponents.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().encode(str));
            }
            return new PathComponentComposite(arrayList);
        }

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public void verify() {
            final Iterator<PathComponent> it = pathComponents.iterator();
            while (it.hasNext()) {
                it.next().verify();
            }
        }

        @Override // org.springframework.web.util.HierarchicalUriComponents.PathComponent
        public PathComponent expand(final UriComponents.UriTemplateVariables uriTemplateVariables) {
            final ArrayList arrayList = new ArrayList(pathComponents.size());
            final Iterator<PathComponent> it = pathComponents.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().expand(uriTemplateVariables));
            }
            return new PathComponentComposite(arrayList);
        }
    }
}
