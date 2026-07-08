package com.fasterxml.jackson.core.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class ThreadLocalBufferManager {
    private final Object RELEASE_LOCK = new Object();
    private final Map<SoftReference<BufferRecycler>, Boolean> _trackedRecyclers = new ConcurrentHashMap();
    private final ReferenceQueue<BufferRecycler> _refQueue = new ReferenceQueue<>();

    ThreadLocalBufferManager() {
    }

    public static ThreadLocalBufferManager instance() {
        return ThreadLocalBufferManagerHolder.manager;
    }

    public SoftReference<BufferRecycler> wrapAndTrack(final BufferRecycler bufferRecycler) {
        final SoftReference<BufferRecycler> softReference = new SoftReference<>(bufferRecycler, _refQueue);
        _trackedRecyclers.put(softReference, Boolean.TRUE);
        this.removeSoftRefsClearedByGc();
        return softReference;
    }

    private void removeSoftRefsClearedByGc() {
        while (true) {
            final SoftReference softReference = (SoftReference) _refQueue.poll();
            if (null == softReference) {
                return;
            } else {
                _trackedRecyclers.remove(softReference);
            }
        }
    }

    private enum ThreadLocalBufferManagerHolder {
        ;
        static final ThreadLocalBufferManager manager = new ThreadLocalBufferManager();

    }
}
