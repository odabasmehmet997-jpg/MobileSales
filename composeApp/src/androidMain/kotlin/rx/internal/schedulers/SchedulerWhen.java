package rx.internal.schedulers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.internal.operators.BufferUntilSubscriber;
import rx.observers.SerializedObserver;
import rx.subscriptions.Subscriptions;

public class SchedulerWhen extends Scheduler implements Subscription {
    static final Subscription SUBSCRIBED = new Subscription() {
        public boolean isUnsubscribed() {
            return false;
        }

        public void unsubscribe() {
        }
    };
    static final Subscription UNSUBSCRIBED = Subscriptions.unsubscribed();
    private final Scheduler actualScheduler = null;
    private final Subscription subscription = null;
    private final Observer<Observable<Completable>> workerObserver = null;
    public void unsubscribe() {
        this.subscription.unsubscribe();
    }
    public boolean isUnsubscribed() {
        return this.subscription.isUnsubscribed();
    }
    public Worker createWorker() {
        final Worker workerCreateWorker = this.actualScheduler.createWorker();
        BufferUntilSubscriber bufferUntilSubscriberCreate = BufferUntilSubscriber.create();
        final SerializedObserver serializedObserver = new SerializedObserver(bufferUntilSubscriberCreate);
        Object map = bufferUntilSubscriberCreate.map(new Func1<ScheduledAction, Completable>() {
            public Completable call(final ScheduledAction scheduledAction) {
                return Completable.create(new Completable.OnSubscribe() {
                    public void call(CompletableSubscriber completableSubscriber) {
                        completableSubscriber.onSubscribe(scheduledAction);
                        scheduledAction.call(workerCreateWorker, completableSubscriber);
                    }
                });
            }
        });
        Worker worker = new Worker() {
            private final AtomicBoolean unsubscribed = new AtomicBoolean();
            public void unsubscribe() {
                if (this.unsubscribed.compareAndSet(false, true)) {
                    workerCreateWorker.unsubscribe();
                    serializedObserver.onCompleted();
                }
            }
            public boolean isUnsubscribed() {
                return this.unsubscribed.get();
            }
            public Subscription schedule(Action0 action0, long j2, TimeUnit timeUnit) {
                DelayedAction delayedAction = new DelayedAction(action0, j2, timeUnit);
                serializedObserver.onNext(delayedAction);
                return delayedAction;
            }
            public Subscription schedule(Action0 action0) {
                ImmediateAction immediateAction = new ImmediateAction(action0);
                serializedObserver.onNext(immediateAction);
                return immediateAction;
            }
        };
        this.workerObserver.onNext(map);
        return worker;
    }
    static abstract class ScheduledAction extends AtomicReference<Subscription> implements Subscription {
        protected abstract Subscription callActual(Worker worker, CompletableSubscriber completableSubscriber);

        public ScheduledAction() {
            super(SchedulerWhen.SUBSCRIBED);
        }
        public void call(Worker worker, CompletableSubscriber completableSubscriber) {
            Subscription subscription;
            Subscription subscription2 = get();
            if (subscription2 != SchedulerWhen.UNSUBSCRIBED && subscription2 == (subscription = SchedulerWhen.SUBSCRIBED)) {
                Subscription subscriptionCallActual = callActual(worker, completableSubscriber);
                if (compareAndSet(subscription, subscriptionCallActual)) {
                    return;
                }
                subscriptionCallActual.unsubscribe();
            }
        }
        public boolean isUnsubscribed() {
            return get().isUnsubscribed();
        }
        public void unsubscribe() {
            Subscription subscription;
            Subscription subscription2 = SchedulerWhen.UNSUBSCRIBED;
            do {
                subscription = get();
                if (subscription == SchedulerWhen.UNSUBSCRIBED) {
                    return;
                }
            } while (!compareAndSet(subscription, subscription2));
            if (subscription != SchedulerWhen.SUBSCRIBED) {
                subscription.unsubscribe();
            }
        }
    }
    static class ImmediateAction extends ScheduledAction {
        private final Action0 action;

        public ImmediateAction(Action0 action0) {
            this.action = action0;
        }
        protected Subscription callActual(Worker worker, CompletableSubscriber completableSubscriber) {
            return worker.schedule(new OnCompletedAction(this.action, completableSubscriber));
        }
    }
    static class DelayedAction extends ScheduledAction {
        private final Action0 action;
        private final long delayTime;
        private final TimeUnit unit;

        public DelayedAction(Action0 action0, long j2, TimeUnit timeUnit) {
            this.action = action0;
            this.delayTime = j2;
            this.unit = timeUnit;
        }
        protected Subscription callActual(Worker worker, CompletableSubscriber completableSubscriber) {
            return worker.schedule(new OnCompletedAction(this.action, completableSubscriber), this.delayTime, this.unit);
        }
    }
    static class OnCompletedAction implements Action0 {
        private final Action0 action;
        private final CompletableSubscriber actionCompletable;

        public OnCompletedAction(Action0 action0, CompletableSubscriber completableSubscriber) {
            this.action = action0;
            this.actionCompletable = completableSubscriber;
        }
        public void call() {
            try {
                this.action.call();
            } finally {
                this.actionCompletable.onCompleted();
            }
        }
    }
}
