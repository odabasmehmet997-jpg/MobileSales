package io.reactivex.internal.util;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.functions.ObjectHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.Serializable;


public enum NotificationLite {
    COMPLETE;

    public static <T> T getValue(final Object obj) {
        return ( T ) obj;
    }

    public static <T> Object next(final T t) {
        return t;
    }

    record ErrorNotification(Throwable f41e) implements Serializable {
            private static final long serialVersionUID = -8759979445933046293L;

        public String toString() {
                return "NotificationLite.Error[" + f41e + "]";
            }

        public boolean equals(final Object obj) {
                if (obj instanceof ErrorNotification) {
                    return ObjectHelper.equals(f41e, ((ErrorNotification) obj).f41e);
                }
                return false;
            }
        }

    record SubscriptionNotification(Subscription upstream) implements Serializable {
            private static final long serialVersionUID = -1322257508628817540L;

        public String toString() {
                return "NotificationLite.Subscription[" + upstream + "]";
            }
        }

    record DisposableNotification(Disposable upstream) implements Serializable {
            private static final long serialVersionUID = -7482590109178395495L;

        public String toString() {
                return "NotificationLite.Disposable[" + upstream + "]";
            }
        }

    public static Object complete() {
        return NotificationLite.COMPLETE;
    }

    public static Object error(final Throwable th) {
        return new ErrorNotification(th);
    }

    public static Object subscription(final Subscription subscription) {
        return new SubscriptionNotification(subscription);
    }

    public static Object disposable(final Disposable disposable) {
        return new DisposableNotification(disposable);
    }

    public static boolean isComplete(final Object obj) {
        return NotificationLite.COMPLETE == obj;
    }

    public static boolean isError(final Object obj) {
        return obj instanceof ErrorNotification;
    }

    public static boolean isSubscription(final Object obj) {
        return obj instanceof SubscriptionNotification;
    }

    public static boolean isDisposable(final Object obj) {
        return obj instanceof DisposableNotification;
    }

    public static Throwable getError(final Object obj) {
        return ((ErrorNotification) obj).f41e;
    }

    public static Subscription getSubscription(final Object obj) {
        return ((SubscriptionNotification) obj).upstream;
    }

    public static Disposable getDisposable(final Object obj) {
        return ((DisposableNotification) obj).upstream;
    }

    public static <T> boolean accept(final Object obj, final Subscriber<? super T> subscriber) {
        if (NotificationLite.COMPLETE == obj) {
            subscriber.onComplete();
            return true;
        }
        if (obj instanceof ErrorNotification) {
            subscriber.onError(((ErrorNotification) obj).f41e);
            return true;
        }
        subscriber.onNext(obj);
        return false;
    }

    public static <T> boolean accept(final Object obj, final Observer<? super T> observer) {
        if (NotificationLite.COMPLETE == obj) {
            observer.onComplete();
            return true;
        }
        if (obj instanceof ErrorNotification) {
            observer.onError(((ErrorNotification) obj).f41e);
            return true;
        }
        observer.onNext(obj);
        return false;
    }

    public static <T> boolean acceptFull(final Object obj, final Subscriber<? super T> subscriber) {
        if (NotificationLite.COMPLETE == obj) {
            subscriber.onComplete();
            return true;
        }
        if (obj instanceof ErrorNotification) {
            subscriber.onError(((ErrorNotification) obj).f41e);
            return true;
        }
        if (obj instanceof SubscriptionNotification) {
            subscriber.onSubscribe(((SubscriptionNotification) obj).upstream);
            return false;
        }
        subscriber.onNext(obj);
        return false;
    }

    public static <T> boolean acceptFull(final Object obj, final Observer<? super T> observer) {
        if (NotificationLite.COMPLETE == obj) {
            observer.onComplete();
            return true;
        }
        if (obj instanceof ErrorNotification) {
            observer.onError(((ErrorNotification) obj).f41e);
            return true;
        }
        if (obj instanceof DisposableNotification) {
            observer.onSubscribe(((DisposableNotification) obj).upstream);
            return false;
        }
        observer.onNext(obj);
        return false;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "NotificationLite.Complete";
    }
}
