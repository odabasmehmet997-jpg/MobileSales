package org.greenrobot.eventbus;

import java.util.logging.Level;



final class BackgroundPoster implements Runnable, Poster {
    private final EventBus eventBus;
    private volatile boolean executorRunning;
    private final PendingPostQueue queue = new PendingPostQueue();

    BackgroundPoster(final EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override // org.greenrobot.eventbus.Poster
    public void enqueue(final Subscription subscription, final Object obj) {
        final PendingPost obtainPendingPost = PendingPost.obtainPendingPost(subscription, obj);
        synchronized (this) {
            try {
                queue.enqueue(obtainPendingPost);
                if (!executorRunning) {
                    executorRunning = true;
                    eventBus.getExecutorService().execute(this);
                }
            } catch (final Throwable th) {
                throw th;
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        while (true) {
            try {
                try {
                    PendingPost poll = queue.poll(1000);
                    if (null == poll) {
                        synchronized (this) {
                            poll = queue.poll();
                            if (null == poll) {
                                executorRunning = false;
                                executorRunning = false;
                                return;
                            }
                        }
                    }
                    eventBus.invokeSubscriber(poll);
                } catch (final InterruptedException e2) {
                    eventBus.getLogger().log(Level.WARNING, Thread.currentThread().getName() + " was interruppted", e2);
                    executorRunning = false;
                    return;
                }
            } catch (final Throwable th) {
                executorRunning = false;
                throw th;
            }
        }
    }
}
