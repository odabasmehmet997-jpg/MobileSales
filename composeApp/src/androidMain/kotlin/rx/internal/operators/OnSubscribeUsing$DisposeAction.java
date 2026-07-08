package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;


final class OnSubscribeUsing$DisposeAction<Resource> extends AtomicBoolean implements Action0, Subscription {
    private static final long serialVersionUID = 4262875056400218316L;
    private Action1<? super Resource> dispose;
    private Resource resource;

    OnSubscribeUsing$DisposeAction(Action1<? super Resource> action1, Resource resource) {
        this.dispose = action1;
        this.resource = resource;
        lazySet(false);
    }

    /*  WARN: Type inference failed for: r0v2, types: [Resource, rx.functions.Action1<? super Resource>] */
    @Override // rx.functions.Action0
    public void call() {
        if (compareAndSet(false, true)) {
            ?? r0 = (Resource) null;
            try {
                this.dispose.call(this.resource);
            } finally {
                this.resource = null;
                this.dispose = null;
            }
        }
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return get();
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        call();
    }
}
