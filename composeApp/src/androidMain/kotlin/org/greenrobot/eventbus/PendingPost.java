package org.greenrobot.eventbus;

import java.util.ArrayList;
import java.util.List;

final class PendingPost {
    private static final List<PendingPost> pendingPostPool = new ArrayList();
    Object event;
    PendingPost next;
    Subscription subscription;
    private PendingPost(final Object obj, final Subscription subscription) {
        event = obj;
        this.subscription = subscription;
    }
    static PendingPost obtainPendingPost(final Subscription subscription, final Object obj) {
        final List<PendingPost> list = PendingPost.pendingPostPool;
        synchronized (list) {
            try {
                final int size = list.size();
                if (0 < size) {
                    final PendingPost remove = list.remove(size - 1);
                    remove.event = obj;
                    remove.subscription = subscription;
                    remove.next = null;
                    return remove;
                }
                return new PendingPost(obj, subscription);
            } catch (final Throwable th) {
                throw th;
            }
        }
    }
    static void releasePendingPost(final PendingPost pendingPost) {
        pendingPost.event = null;
        pendingPost.subscription = null;
        pendingPost.next = null;
        final List<PendingPost> list = PendingPost.pendingPostPool;
        synchronized (list) {
            try {
                if (10000 > list.size()) {
                    list.add(pendingPost);
                }
            } catch (final Throwable th) {
                throw th;
            }
        }
    }
}
