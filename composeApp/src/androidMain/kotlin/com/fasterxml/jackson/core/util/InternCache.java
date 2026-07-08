package com.fasterxml.jackson.core.util;

import java.util.concurrent.ConcurrentHashMap;

public final class InternCache extends ConcurrentHashMap<String, String> {
    private static final int MAX_ENTRIES = 180;
    public static final InternCache instance = new InternCache();
    private static final long serialVersionUID = 1;
    private final Object lock;

    private InternCache() {
        super(InternCache.MAX_ENTRIES, 0.8f, 4);
        lock = new Object();
    }

    public String intern(final String str) {
        final String str2 = this.get(str);
        if (null != str2) {
            return str2;
        }
        if (InternCache.MAX_ENTRIES <= size()) {
            synchronized (lock) {
                if (size() >= MAX_ENTRIES) {
                    clear();
                }
            }
        }
        final String strIntern = str.intern();
        this.put(strIntern, strIntern);
        return strIntern;
    }
}
