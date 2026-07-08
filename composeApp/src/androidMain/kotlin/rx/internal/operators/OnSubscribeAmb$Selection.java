package rx.internal.operators;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;


final class OnSubscribeAmb$Selection<T> extends AtomicReference<OnSubscribeAmb$AmbSubscriber<T>> {
    final Collection<OnSubscribeAmb$AmbSubscriber<T>> ambSubscribers = new ConcurrentLinkedQueue();

    OnSubscribeAmb$Selection() {
    }

    public void unsubscribeLosers() {
        OnSubscribeAmb$AmbSubscriber<T> onSubscribeAmb$AmbSubscriber = get();
        if (onSubscribeAmb$AmbSubscriber != null) {
            unsubscribeOthers(onSubscribeAmb$AmbSubscriber);
        }
    }

    public void unsubscribeOthers(OnSubscribeAmb$AmbSubscriber<T> onSubscribeAmb$AmbSubscriber) {
        for (OnSubscribeAmb$AmbSubscriber<T> onSubscribeAmb$AmbSubscriber2 : this.ambSubscribers) {
            if (onSubscribeAmb$AmbSubscriber2 != onSubscribeAmb$AmbSubscriber) {
                onSubscribeAmb$AmbSubscriber2.unsubscribe();
            }
        }
        this.ambSubscribers.clear();
    }
}
