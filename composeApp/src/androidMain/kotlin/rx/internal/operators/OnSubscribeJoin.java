package rx.internal.operators;

import java.util.HashMap;
import java.util.Map;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.SerialSubscription;


public final class OnSubscribeJoin<TLeft, TRight, TLeftDuration, TRightDuration, R> implements Observable.OnSubscribe<R> {

    final class ResultSink extends HashMap<Integer, TLeft> {
        private static final long serialVersionUID = 3491669543549085380L;
        boolean leftDone;
        int leftId;
        boolean rightDone;
        int rightId;
        final Subscriber<? super R> subscriber;
        final /* synthetic */ OnSubscribeJoin this$0;
        final CompositeSubscription group = new CompositeSubscription();
        final Map<Integer, TRight> rightMap = new HashMap();

        HashMap<Integer, TLeft> leftMap() {
            return this;
        }

        public ResultSink(OnSubscribeJoin onSubscribeJoin, Subscriber<? super R> subscriber) {
            this.subscriber = subscriber;
        }

        public void run() {
            this.subscriber.add(this.group);
            LeftSubscriber leftSubscriber = new LeftSubscriber();
            RightSubscriber rightSubscriber = new RightSubscriber();
            this.group.add(leftSubscriber);
            this.group.add(rightSubscriber);
            throw null;
        }

        final class LeftSubscriber extends Subscriber<TLeft> {
            LeftSubscriber() {
            }

            @Override // rx.Observer
            public void onNext(Object tleft) {
                ResultSink resultSink;
                synchronized (ResultSink.this) {
                    ResultSink resultSink2 = ResultSink.this;
                    int i2 = resultSink2.leftId;
                    resultSink2.leftId = i2 + 1;
                    resultSink2.leftMap().put(Integer.valueOf(i2), tleft);
                    resultSink = ResultSink.this;
                    int i3 = resultSink.rightId;
                }
                try {
                    resultSink.getClass();
                    throw null;
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                ResultSink.this.subscriber.onError(th);
                ResultSink.this.subscriber.unsubscribe();
            }

            @Override // rx.Observer
            public void onCompleted() {
                boolean z;
                synchronized (ResultSink.this) {
                    try {
                        ResultSink resultSink = ResultSink.this;
                        z = true;
                        resultSink.leftDone = true;
                        if (!resultSink.rightDone && !resultSink.leftMap().isEmpty()) {
                            z = false;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                if (z) {
                    ResultSink.this.subscriber.onCompleted();
                    ResultSink.this.subscriber.unsubscribe();
                } else {
                    ResultSink.this.group.remove(this);
                }
            }
        }

        final class RightSubscriber extends Subscriber<TRight> {
            RightSubscriber() {
            }

            @Override // rx.Observer
            public void onNext(Object tright) {
                synchronized (ResultSink.this) {
                    ResultSink resultSink = ResultSink.this;
                    int i2 = resultSink.rightId;
                    resultSink.rightId = i2 + 1;
                    resultSink.rightMap.put(Integer.valueOf(i2), tright);
                    int i3 = ResultSink.this.leftId;
                }
                ResultSink.this.group.add(new SerialSubscription());
                try {
                    ResultSink.this.getClass();
                    throw null;
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                ResultSink.this.subscriber.onError(th);
                ResultSink.this.subscriber.unsubscribe();
            }

            @Override // rx.Observer
            public void onCompleted() {
                boolean z;
                synchronized (ResultSink.this) {
                    try {
                        ResultSink resultSink = ResultSink.this;
                        z = true;
                        resultSink.rightDone = true;
                        if (!resultSink.leftDone && !resultSink.rightMap.isEmpty()) {
                            z = false;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                if (z) {
                    ResultSink.this.subscriber.onCompleted();
                    ResultSink.this.subscriber.unsubscribe();
                } else {
                    ResultSink.this.group.remove(this);
                }
            }
        }
    }
}
