package rx;

import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observers.SafeCompletableSubscriber;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.Subscriptions;


public class Completable {
    static final Completable COMPLETE = new Completable(new OnSubscribe() {
        public void call(CompletableSubscriber completableSubscriber) {
            completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
            completableSubscriber.onCompleted();
        }
    }, false);
    static final Completable NEVER = new Completable(new OnSubscribe() {
        public void call(CompletableSubscriber completableSubscriber) {
            completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
        }
    }, false);
    private final OnSubscribe onSubscribe;
    public interface OnSubscribe extends Action1<CompletableSubscriber> {
    }
    public interface Operator extends Func1<CompletableSubscriber, CompletableSubscriber> {
    }
    public static Completable create(OnSubscribe onSubscribe) {
        requireNonNull(onSubscribe);
        try {
            return new Completable(onSubscribe);
        } catch (NullPointerException e2) {
            throw e2;
        } catch (Throwable th) {
            RxJavaHooks.onError(th);
            throw toNpe(th);
        }
    }
    public static Completable fromObservable(final Observable<?> observable) {
        requireNonNull(observable);
        return create(new OnSubscribe() {
            public void call(final CompletableSubscriber completableSubscriber) {
                Subscriber<Object> subscriber = new Subscriber<Object>() {
                    public void onNext(Object obj) {
                    }
                    public void onCompleted() {
                        completableSubscriber.onCompleted();
                    }
                    public void onError(Throwable th) {
                        completableSubscriber.onError(th);
                    }
                };
                completableSubscriber.onSubscribe(subscriber);
                observable.unsafeSubscribe(subscriber);
            }
        });
    }
    static <T> T requireNonNull(T t) {
        t.getClass();
        return t;
    }
    static NullPointerException toNpe(Throwable th) {
        NullPointerException nullPointerException = new NullPointerException("Actually not, but can't pass out an exception otherwise...");
        nullPointerException.initCause(th);
        return nullPointerException;
    }
    protected Completable(OnSubscribe onSubscribe) {
        this.onSubscribe = RxJavaHooks.onCreate(onSubscribe);
    }
    protected Completable(OnSubscribe onSubscribe, boolean z) {
        this.onSubscribe = z ? RxJavaHooks.onCreate(onSubscribe) : onSubscribe;
    }
    public final void unsafeSubscribe(CompletableSubscriber completableSubscriber) {
        requireNonNull(completableSubscriber);
        try {
            RxJavaHooks.onCompletableStart(this, this.onSubscribe).call(completableSubscriber);
        } catch (NullPointerException e2) {
            throw e2;
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            Throwable thOnCompletableError = RxJavaHooks.onCompletableError(th);
            RxJavaHooks.onError(thOnCompletableError);
            throw toNpe(thOnCompletableError);
        }
    }
    public final void subscribe(CompletableSubscriber completableSubscriber) {
        if (!(completableSubscriber instanceof SafeCompletableSubscriber)) {
            completableSubscriber = new SafeCompletableSubscriber(completableSubscriber);
        }
        unsafeSubscribe(completableSubscriber);
    }
}
