package io.reactivex.internal.schedulers;

import com.proje.mobilesales.features.sales.view.newadd.T;
import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Function;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.UnicastProcessor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;



public class SchedulerWhen extends Scheduler implements Disposable {
    private final Scheduler actualScheduler = null;
    private final Disposable disposable;
    private final FlowableProcessor<Flowable<Completable>> workerProcessor = null;
    static final Disposable SUBSCRIBED = new SubscribedDisposable();
    static final Disposable DISPOSED = Disposables.disposed();
    public void dispose() {
        this.disposable.dispose();
    }
    public boolean isDisposed() {
        return this.disposable.isDisposed();
    }
    public void onError(Throwable th) {
    }
    public <T> Observable<T> observeOn(Scheduler scheduler) {
        return null;
    }
    public Worker createWorker() {
        Worker createWorker = this.actualScheduler.createWorker();
        FlowableProcessor<T> serialized = UnicastProcessor.create();
        Flowable<Completable> map = serialized.map(new CreateWorkerFunction(createWorker));
        QueueWorker queueWorker = new QueueWorker(serialized, createWorker);
        this.workerProcessor.onNext(map);
        return queueWorker;
    }

    static abstract class ScheduledAction extends AtomicReference<Disposable> implements Disposable {
        protected abstract Disposable callActual(Worker worker, CompletableObserver completableObserver);

        ScheduledAction() {
            super(SchedulerWhen.SUBSCRIBED);
        }

        void call(Worker worker, CompletableObserver completableObserver) {
            Disposable disposable;
            Disposable disposable2 = get ();
            if (disposable2 != SchedulerWhen.DISPOSED && disposable2 == (disposable = SchedulerWhen.SUBSCRIBED)) {
                Disposable callActual = callActual (worker, completableObserver);
                if (compareAndSet (disposable, callActual)) {
                    return;
                }
                callActual.dispose();
            }
        }
        public boolean isDisposed() {
            return get ().isDisposed();
        }
        public void dispose() {
            Disposable disposable;
            Disposable disposable2 = SchedulerWhen.DISPOSED;
            do {
                disposable = get ();
                if (disposable == SchedulerWhen.DISPOSED) {
                    return;
                }
            } while (!compareAndSet (disposable, disposable2));
            if (disposable != SchedulerWhen.SUBSCRIBED) {
                disposable.dispose();
            }
        }
    }

    static class ImmediateAction extends ScheduledAction {
        private final Runnable action;

        ImmediateAction(Runnable runnable) {
            this.action = runnable;
        }

        protected Disposable callActual(Worker worker, CompletableObserver completableObserver) {
            return worker.schedule(new OnCompletedAction(this.action, completableObserver));
        }

        @Override
        public void onError(Throwable th) {

        }

        @Override
        public <T> Observable<T> observeOn(Scheduler scheduler) {
            return null;
        }
    }

    static class DelayedAction extends ScheduledAction {
        private final Runnable action;
        private final long delayTime;
        private final TimeUnit unit;

        DelayedAction(Runnable runnable, long j2, TimeUnit timeUnit) {
            this.action = runnable;
            this.delayTime = j2;
            this.unit = timeUnit;
        }
        protected Disposable callActual(Worker worker, CompletableObserver completableObserver) {
            return worker.schedule(new OnCompletedAction(this.action, completableObserver), this.delayTime, this.unit);
        }

        @Override
        public void onError(Throwable th) {

        }

        @Override
        public <T> Observable<T> observeOn(Scheduler scheduler) {
            return null;
        }
    }

    record OnCompletedAction(Runnable action, CompletableObserver actionCompletable) implements Runnable {

        @Override // java.lang.Runnable
            public void run() {
                try {
                    this.action.run();
                } finally {
                    this.actionCompletable.onComplete();
                }
            }
        }

    public record CreateWorkerFunction(Worker actualWorker) implements Function<ScheduledAction, Completable> {

        @Override
        public Completable apply(ScheduledAction scheduledAction) {
            return new WorkerCompletable(scheduledAction);
        }

        @Override
        public Completable apply(Object t) throws Exception {
            return null;
        }

        @Override
        public Object invoke(Object obj) {
            return null;
        }

        final class WorkerCompletable extends Completable {
                final ScheduledAction action;

                WorkerCompletable(ScheduledAction scheduledAction) {
                    this.action = scheduledAction;
                }

                @Override // io.reactivex.Completable
                protected void subscribeActual(CompletableObserver completableObserver) {
                    completableObserver.onSubscribe(this.action);
                    this.action.call(CreateWorkerFunction.this.actualWorker, completableObserver);
                }
            }
        }

    static final class QueueWorker extends Worker {
        private final FlowableProcessor<T> actionProcessor;
        private final Worker actualWorker;
        private final AtomicBoolean unsubscribed = new AtomicBoolean();

        QueueWorker(FlowableProcessor<T> flowableProcessor, Worker worker) {
            this.actionProcessor = flowableProcessor;
            this.actualWorker = worker;
        }

        public void dispose() {
            if (this.unsubscribed.compareAndSet(false, true)) {
                this.actionProcessor.onComplete();
                this.actualWorker.dispose();
            }
        }
        public boolean isDisposed() {
            return this.unsubscribed.get();
        }

        @Override
        public void onError(Throwable th) {

        }

        @Override
        public <T> Observable<T> observeOn(Scheduler scheduler) {
            return null;
        }

        public Disposable schedule(Runnable runnable, long j2, TimeUnit timeUnit) {
            DelayedAction delayedAction = new DelayedAction(runnable, j2, timeUnit);
            this.actionProcessor.onNext(delayedAction);
            return delayedAction;
        }

        public Disposable schedule(Runnable runnable) {
            ImmediateAction immediateAction = new ImmediateAction(runnable);
            this.actionProcessor.onNext(immediateAction);
            return immediateAction;
        }
    }

    static final class SubscribedDisposable implements Disposable {
        public void dispose() {
        }
        public boolean isDisposed() {
            return false;
        }

        @Override
        public void onError(Throwable th) {

        }

        @Override
        public <T> Observable<T> observeOn(Scheduler scheduler) {
            return null;
        }

        public <T> Flowable<T> observeOn(FlowableScheduler flowableScheduler) {
            return null;
        }

        SubscribedDisposable() {
        }
    }
}
