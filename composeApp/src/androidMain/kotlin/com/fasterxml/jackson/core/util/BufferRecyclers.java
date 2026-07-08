package com.fasterxml.jackson.core.util;

import java.lang.ref.SoftReference;

public enum BufferRecyclers {
    ;
    private static final ThreadLocalBufferManager _bufferRecyclerTracker;
    private static final ThreadLocal<SoftReference<BufferRecycler>> _recyclerRef;

    static {
        boolean zEquals;
        try {
            zEquals = "true".equals(System.getProperty("com.fasterxml.jackson.core.util.BufferRecyclers.trackReusableBuffers"));
        } catch (final SecurityException unused) {
            zEquals = false;
        }
        _bufferRecyclerTracker = zEquals ? ThreadLocalBufferManager.instance() : null;
        _recyclerRef = new ThreadLocal<>();
    }

    public static BufferRecycler getBufferRecycler() {
        final SoftReference<BufferRecycler> softReference;
        final ThreadLocal<SoftReference<BufferRecycler>> threadLocal = BufferRecyclers._recyclerRef;
        final SoftReference<BufferRecycler> softReference2 = threadLocal.get();
        BufferRecycler bufferRecycler = null == softReference2 ? null : softReference2.get();
        if (null == bufferRecycler) {
            bufferRecycler = new BufferRecycler();
            final ThreadLocalBufferManager threadLocalBufferManager = BufferRecyclers._bufferRecyclerTracker;
            if (null != threadLocalBufferManager) {
                softReference = threadLocalBufferManager.wrapAndTrack(bufferRecycler);
            } else {
                softReference = new SoftReference<>(bufferRecycler);
            }
            threadLocal.set(softReference);
        }
        return bufferRecycler;
    }
}
