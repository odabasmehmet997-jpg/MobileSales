package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.schedulers.SchedulerWhen;
import io.reactivex.internal.subscribers.BasicFuseableConditionalSubscriber;
import io.reactivex.internal.subscribers.BasicFuseableSubscriber;
import org.reactivestreams.Subscriber;

public final class FlowableMap<T, U> extends AbstractFlowableWithUpstream<T, U> {
    Function<? super T, ? extends U> mapper = null;
    public FlowableMap(Flowable<T> flowable, Function<? super T, ? extends U> function) {
        super();
        this.mapper = function;
    }
    public <T> FlowableMap(Flowable<T> tFlowable, SchedulerWhen.CreateWorkerFunction function, Function<? super T, ? extends U> mapper) {
        super();

    }
    public <T> FlowableMap(Flowable<T> tFlowable, SchedulerWhen.CreateWorkerFunction function) {
    }
    protected void subscribeActual(Subscriber<? super U> subscriber) {
        if (subscriber instanceof ConditionalSubscriber) {
            this.source.subscribe(new MapConditionalSubscriber((ConditionalSubscriber) subscriber, this.mapper));
        } else {
            this.source.subscribe(new MapSubscriber(subscriber, this.mapper));
        }
    }
    static final class MapSubscriber<T, U> extends BasicFuseableSubscriber<T, U> {
        final Function<? super T, ? extends U> mapper;

        MapSubscriber(Subscriber<? super U> subscriber, Function<? super T, ? extends U> function) {
            super(subscriber);
            this.mapper = function;
        }
        public void onNext(Object t) {
            if (done) {
                return;
            }
            if (0 != sourceMode) {
                downstream.onNext(null);
                return;
            }
            try {
                downstream.onNext(ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper function returned a null value."));
            } catch (Throwable th) {
                this.fail(th);
            }
        }
        public int requestFusion(int i2) {
            return this.transitiveBoundaryFusion(i2);
        }
        public U poll() throws Exception {
            T poll = qs.poll();
            if (null != poll) {
                return ObjectHelper.requireNonNull(this.mapper.apply(poll), "The mapper function returned a null value.");
            }
            return null;
        }
    }
    static final class MapConditionalSubscriber<T, U> extends BasicFuseableConditionalSubscriber<T, U> {
        final Function<? super T, ? extends U> mapper;

        MapConditionalSubscriber(ConditionalSubscriber<? super U> conditionalSubscriber, Function<? super T, ? extends U> function) {
            super(conditionalSubscriber);
            this.mapper = function;
        }
        public void onNext(Object t) {
            if (done) {
                return;
            }
            if (0 != sourceMode) {
                downstream.onNext(null);
                return;
            }
            try {
                downstream.onNext(ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper function returned a null value."));
            } catch (Throwable th) {
                this.fail(th);
            }
        }
        public boolean tryOnNext(T t) {
            if (done) {
                return false;
            }
            try {
                return downstream.tryOnNext(ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper function returned a null value."));
            } catch (Throwable th) {
                this.fail(th);
                return true;
            }
        }

        @Override
        public int requestFusion(int i2) {
            return this.transitiveBoundaryFusion(i2);
        }

        @Override
        public U poll() throws Exception {
            T poll = qs.poll();
            if (null == poll) {
                return null;
            }
            return ObjectHelper.requireNonNull(this.mapper.apply(poll), "The mapper function returned a null value.");
        }
    }
}
