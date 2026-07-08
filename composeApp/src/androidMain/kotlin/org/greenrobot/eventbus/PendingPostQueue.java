package org.greenrobot.eventbus;

final class PendingPostQueue {
    private PendingPost head;
    private PendingPost tail;

    PendingPostQueue() {
    }

    synchronized void enqueue(final PendingPost pendingPost) {
        try {
            if (null == pendingPost) {
                throw new NullPointerException("null cannot be enqueued");
            }
            final PendingPost pendingPost2 = tail;
            if (null != pendingPost2) {
                pendingPost2.next = pendingPost;
                tail = pendingPost;
            } else if (null == this.head) {
                tail = pendingPost;
                head = pendingPost;
            } else {
                throw new IllegalStateException("Head present, but no tail");
            }
            this.notifyAll();
        } catch (final Throwable th) {
            throw th;
        }
    }
    synchronized PendingPost poll() {
        final PendingPost pendingPost;
        pendingPost = head;
        if (null != pendingPost) {
            final PendingPost pendingPost2 = pendingPost.next;
            head = pendingPost2;
            if (null == pendingPost2) {
                tail = null;
            }
        }
        return pendingPost;
    }
    synchronized PendingPost poll(final int i2) throws InterruptedException {
        try {
            if (null == this.head) {
                this.wait(i2);
            }
        } catch (final Throwable th) {
            throw th;
        }
        return this.poll();
    }
}
