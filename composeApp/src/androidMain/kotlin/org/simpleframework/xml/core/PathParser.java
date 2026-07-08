package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.Style;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


class PathParser implements Expression {
    protected boolean attribute;
    protected String cache;
    protected int count;
    protected char[] data;
    protected String location;
    protected int off;
    protected String path;
    protected int start;
    protected Style style;
    protected Type type;
    protected Cache<String> attributes = new ConcurrentCache();
    protected Cache<String> elements = new ConcurrentCache();
    protected List<Integer> indexes = new ArrayList();
    protected List<String> prefixes = new ArrayList();
    protected List<String> names = new ArrayList();
    protected StringBuilder builder = new StringBuilder();
    private boolean isSpecial(final char c2) {
        return '_' == c2 || '-' == c2 || ':' == c2;
    }
    public PathParser(final String str, final Type type, final Format format) throws Exception {
        style = format.style();
        this.type = type;
        path = str;
        this.parse(str);
    }
    public boolean isEmpty() {
        return this.isEmpty(location);
    }
    public boolean isPath() {
        return 1 < this.names.size();
    }
    public boolean isAttribute() {
        return attribute;
    }
    public int getIndex() {
        return indexes.get(0).intValue();
    }
    public String getPrefix() {
        return prefixes.get(0);
    }
    public String getFirst() {
        return names.get(0);
    }
    public String getLast() {
        return names.get(names.size() - 1);
    }
    public String getPath() {
        return location;
    }
    public String getElement(final String str) {
        if (!this.isEmpty(location)) {
            String fetch = elements.fetch(str);
            if (null == fetch && null != (fetch = getElementPath(this.location, str))) {
                elements.cache(str, fetch);
            }
            return fetch;
        }
        return style.getElement(str);
    }
    protected String getElementPath(final String str, final String str2) {
        final String element = style.getElement(str2);
        if (this.isEmpty(element)) {
            return str;
        }
        if (this.isEmpty(str)) {
            return element;
        }
        return str + "/" + element + "[1]";
    }
    public String getAttribute(final String str) {
        if (!this.isEmpty(location)) {
            String fetch = attributes.fetch(str);
            if (null == fetch && null != (fetch = getAttributePath(this.location, str))) {
                attributes.cache(str, fetch);
            }
            return fetch;
        }
        return style.getAttribute(str);
    }
    protected String getAttributePath(final String str, final String str2) {
        final String attribute = style.getAttribute(str2);
        if (this.isEmpty(str)) {
            return attribute;
        }
        return str + "/@" + attribute;
    }
    public Iterator<String> iterator() {
        return names.iterator();
    }
    public Expression getPath(final int i2) {
        return this.getPath(i2, 0);
    }
    public Expression getPath(final int i2, final int i3) {
        final int size = (names.size() - 1) - i3;
        if (size >= i2) {
            return new PathSection(i2, size);
        }
        return new PathSection(i2, i2);
    }
    private void parse(final String str) throws Exception {
        if (null != str) {
            final int length = str.length();
            count = length;
            final char[] cArr = new char[length];
            data = cArr;
            str.getChars(0, length, cArr, 0);
        }
        this.path();
    }
    private void path() throws Exception {
        final char c2 = data[off];
        if ('/' == c2) {
            throw new PathException("Path '%s' in %s references document root", path, type);
        }
        if ('.' == c2) {
            this.skip();
        }
        while (off < count) {
            if (attribute) {
                throw new PathException("Path '%s' in %s references an invalid attribute", path, type);
            }
            this.segment();
        }
        this.truncate();
        this.build();
    }
    private void build() {
        final int size = names.size();
        final int i2 = size - 1;
        for (int i3 = 0; i3 < size; i3++) {
            final String str = prefixes.get(i3);
            final String str2 = names.get(i3);
            final int intValue = indexes.get(i3).intValue();
            if (0 < i3) {
                builder.append('/');
            }
            if (attribute && i3 == i2) {
                builder.append('@');
                builder.append(str2);
            } else {
                if (null != str) {
                    builder.append(str);
                    builder.append(':');
                }
                builder.append(str2);
                builder.append('[');
                builder.append(intValue);
                builder.append(']');
            }
        }
        location = builder.toString();
    }
    private void skip() throws Exception {
        final char[] cArr = data;
        if (1 < cArr.length) {
            final int i2 = off;
            if ('/' != cArr[i2 + 1]) {
                throw new PathException("Path '%s' in %s has an illegal syntax", path, type);
            }
            off = i2 + 1;
        }
        final int i3 = off + 1;
        off = i3;
        start = i3;
    }
    private void segment() throws Exception {
        final char c2 = data[off];
        if ('/' == c2) {
            throw new PathException("Invalid path expression '%s' in %s", path, type);
        }
        if ('@' == c2) {
            this.attribute();
        } else {
            this.element();
        }
        this.align();
    }
    private void element() throws Exception {
        final int i2 = off;
        int i3 = 0;
        while (true) {
            final int i4 = off;
            if (i4 >= count) {
                break;
            }
            final char[] cArr = data;
            off = i4 + 1;
            final char c2 = cArr[i4];
            if (this.isValid(c2)) {
                i3++;
            } else if ('@' == c2) {
                off--;
            } else if ('[' == c2) {
                this.index();
            } else if ('/' != c2) {
                throw new PathException("Illegal character '%s' in element for '%s' in %s", Character.valueOf(c2), path, type);
            }
        }
        this.element(i2, i3);
    }
    private void attribute() throws Exception {
        char c2;
        final int i2 = off + 1;
        off = i2;
        do {
            final int i3 = off;
            if (i3 >= count) {
                if (i3 <= i2) {
                    throw new PathException("Attribute reference in '%s' for %s is empty", path, type);
                }
                attribute = true;
                this.attribute(i2, i3 - i2);
                return;
            }
            final char[] cArr = data;
            off = i3 + 1;
            c2 = cArr[i3];
        } while (this.isValid(c2));
        throw new PathException("Illegal character '%s' in attribute for '%s' in %s", Character.valueOf(c2), path, type);
    }
    private void index() throws Exception {
        int i2 = 0;
        if ('[' == this.data[this.off - 1]) {
            while (true) {
                final int i3 = off;
                if (i3 >= count) {
                    break;
                }
                final char[] cArr = data;
                off = i3 + 1;
                final char c2 = cArr[i3];
                if (!this.isDigit(c2)) {
                    break;
                } else {
                    i2 = ((i2 * 10) + c2) - 48;
                }
            }
        }
        final char[] cArr2 = data;
        final int i4 = off;
        off = i4 + 1;
        if (']' != cArr2[i4 - 1]) {
            throw new PathException("Invalid index for path '%s' in %s", path, type);
        }
        indexes.add(Integer.valueOf(i2));
    }
    private void truncate() throws Exception {
        final int i2 = off;
        final int i3 = i2 - 1;
        final char[] cArr = data;
        if (i3 >= cArr.length) {
            off = i2 - 1;
        } else if ('/' == cArr[i2 - 1]) {
            off = i2 - 1;
        }
    }
    private void align() throws Exception {
        if (names.size() > indexes.size()) {
            indexes.add(1);
        }
    }
    private boolean isEmpty(final String str) {
        return null == str || 0 == str.length();
    }
    private boolean isDigit(final char c2) {
        return Character.isDigit(c2);
    }
    private boolean isValid(final char c2) {
        return this.isLetter(c2) || this.isSpecial(c2);
    }
    private boolean isLetter(final char c2) {
        return Character.isLetterOrDigit(c2);
    }
    private void element(final int i2, final int i3) {
        final String str = new String(data, i2, i3);
        if (0 < i3) {
            this.element(str);
        }
    }
    private void attribute(final int i2, final int i3) {
        final String str = new String(data, i2, i3);
        if (0 < i3) {
            this.attribute(str);
        }
    }
    private void element(String str) {
        final String str2;
        final int indexOf = str.indexOf(58);
        if (0 < indexOf) {
            str2 = str.substring(0, indexOf);
            str = str.substring(indexOf + 1);
        } else {
            str2 = null;
        }
        final String element = style.getElement(str);
        prefixes.add(str2);
        names.add(element);
    }
    private void attribute(final String str) {
        final String attribute = style.getAttribute(str);
        prefixes.add(null);
        names.add(attribute);
    }
    public String toString() {
        final int i2 = off;
        final int i3 = start;
        final int i4 = i2 - i3;
        if (null == this.cache) {
            cache = new String(data, i3, i4);
        }
        return cache;
    }
    private class PathSection implements Expression {
        private final int begin;
        private final List<String> cache = new ArrayList();
        private final int end;
        private String path;
        private String section;

        public PathSection(final int i2, final int i3) {
            begin = i2;
            end = i3;
        }

        
        public boolean isEmpty() {
            return begin == end;
        }

        
        public boolean isPath() {
            return 1 <= this.end - this.begin;
        }

        
        public boolean isAttribute() {
            final PathParser pathParser = PathParser.this;
            return pathParser.attribute && end >= pathParser.names.size() - 1;
        }

        
        public String getPath() {
            if (null == this.section) {
                section = this.getCanonicalPath();
            }
            return section;
        }

        
        public String getElement(final String str) {
            final String path = this.getPath();
            return null != path ? getElementPath(path, str) : str;
        }

        
        public String getAttribute(final String str) {
            final String path = this.getPath();
            return null != path ? getAttributePath(path, str) : str;
        }

        
        public int getIndex() {
            return indexes.get(begin).intValue();
        }

        
        public String getPrefix() {
            return prefixes.get(begin);
        }

        
        public String getFirst() {
            return names.get(begin);
        }

        
        public String getLast() {
            return names.get(end);
        }

        
        public Expression getPath(final int i2) {
            return this.getPath(i2, 0);
        }

        
        public Expression getPath(final int i2, final int i3) {
            return PathParser.this.new PathSection(begin + i2, end - i3);
        }

        @Override // java.lang.Iterable
        public Iterator<String> iterator() {
            if (cache.isEmpty()) {
                for (int i2 = begin; i2 <= end; i2++) {
                    final String str = names.get(i2);
                    if (null != str) {
                        cache.add(str);
                    }
                }
            }
            return cache.iterator();
        }

        private String getCanonicalPath() {
            int i2 = 0;
            int i3 = 0;
            while (i2 < begin) {
                i3 = location.indexOf(47, i3 + 1);
                i2++;
            }
            int i4 = i3;
            while (i2 <= end) {
                i4 = location.indexOf(47, i4 + 1);
                if (-1 == i4) {
                    i4 = location.length();
                }
                i2++;
            }
            return location.substring(i3 + 1, i4);
        }

        private String getFragment() {
            int i2 = start;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                if (i3 > end) {
                    break;
                }
                final PathParser pathParser = PathParser.this;
                if (i2 >= pathParser.count) {
                    i2++;
                    break;
                }
                final int i5 = i2 + 1;
                if ('/' == pathParser.data[i2] && (i3 = i3 + 1) == begin) {
                    i2 = i5;
                    i4 = i2;
                } else {
                    i2 = i5;
                }
            }
            return new String(data, i4, (i2 - 1) - i4);
        }

        
        public String toString() {
            if (null == this.path) {
                path = this.getFragment();
            }
            return path;
        }
    }
}
