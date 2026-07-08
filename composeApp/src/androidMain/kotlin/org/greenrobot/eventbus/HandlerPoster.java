package org.greenrobot.eventbus;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

public class HandlerPoster extends Handler implements Poster {
    private final EventBus eventBus;
    private boolean handlerActive;
    private final int maxMillisInsideHandleMessage;
    private final PendingPostQueue queue;
    protected HandlerPoster(final EventBus eventBus, final Looper looper, final int i2) {
        super(looper);
        this.eventBus = eventBus;
        maxMillisInsideHandleMessage = i2;
        queue = new PendingPostQueue();
    }
    public void enqueue(final Subscription subscription, final Object obj) {
        final PendingPost obtainPendingPost = PendingPost.obtainPendingPost(subscription, obj);
        synchronized (this) {
            this.queue.enqueue(obtainPendingPost);
            if (!this.handlerActive) {
                this.handlerActive = true;
                if (!sendMessage(obtainMessage())) {
                    throw new EventBusException("Could not send handler message");
                }
            }
        }
    }
    public void handleMessage(final Message message) {
        try {
            final long uptimeMillis = SystemClock.uptimeMillis();
            do {
                PendingPost poll = queue.poll();
                if (null == poll) {
                    synchronized (this) {
                        poll = queue.poll();
                        if (null == poll) {
                            handlerActive = false;
                            return;
                        }
                    }
                }
                eventBus.invokeSubscriber(poll);
            } while (SystemClock.uptimeMillis() - uptimeMillis < maxMillisInsideHandleMessage);
            if (!this.sendMessage(this.obtainMessage())) {
                throw new EventBusException("Could not send handler message");
            }
            handlerActive = true;
        } catch (final Throwable th) {
            handlerActive = false;
            throw th;
        }
    }
}
