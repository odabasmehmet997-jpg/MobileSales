package org.greenrobot.eventbus;

class AsyncPoster implements Runnable, Poster {
    private final EventBus eventBus;
    private final PendingPostQueue queue = new PendingPostQueue();
    AsyncPoster(final EventBus eventBus) {
        this.eventBus = eventBus;
    }
    public void enqueue(final Subscription subscription, final Object obj) {
        queue.enqueue(PendingPost.obtainPendingPost(subscription, obj));
        eventBus.getExecutorService().execute(this);
    }
    public void run() {
        final PendingPost poll = queue.poll();
        if (null == poll) {
            throw new IllegalStateException("No pending post available");
        }
        eventBus.invokeSubscriber(poll);
    }
}
