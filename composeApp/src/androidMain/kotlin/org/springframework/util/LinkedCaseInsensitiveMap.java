package org.springframework.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class LinkedCaseInsensitiveMap<V> extends LinkedHashMap<String, V> {
    private final Map<String, String> caseInsensitiveKeys;
    private final Locale locale;
    public LinkedCaseInsensitiveMap() {
        this(null);
    }
    public LinkedCaseInsensitiveMap(final Locale locale) {
        caseInsensitiveKeys = new HashMap();
        this.locale = locale == null ? Locale.getDefault() : locale;
    }
    public LinkedCaseInsensitiveMap(final int i2) {
        this(i2, null);
    }
    public LinkedCaseInsensitiveMap(final int i2, final Locale locale) {
        super(i2);
        caseInsensitiveKeys = new HashMap(i2);
        this.locale = locale == null ? Locale.getDefault() : locale;
    }
    public V put(final String str, final V v) {
        final String put = caseInsensitiveKeys.put(this.convertKey(str), str);
        if (put != null && !put.equals(str)) {
            super.remove(put);
        }
        return super.put(str, v);
    }
    public void putAll(final Map<? extends String, ? extends V> map) {
        if (map.isEmpty()) {
            return;
        }
        for (final Entry<? extends String, ? extends V> entry : map.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }
    public boolean containsKey(final Object obj) {
        return obj instanceof String && caseInsensitiveKeys.containsKey(this.convertKey((String) obj));
    }
    public V get(final Object obj) {
        if (obj instanceof String) {
            return super.get(caseInsensitiveKeys.get(this.convertKey((String) obj)));
        }
        return null;
    }
    public V remove(final Object obj) {
        if (obj instanceof String) {
            return super.remove(caseInsensitiveKeys.remove(this.convertKey((String) obj)));
        }
        return null;
    }
    public void clear() {
        caseInsensitiveKeys.clear();
        super.clear();
    }
    protected String convertKey(final String str) {
        return str.toLowerCase(locale);
    }
}
