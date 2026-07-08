package io.reactivex.internal.util;

import io.reactivex.Observer;
import io.reactivex.functions.Predicate;
import org.reactivestreams.Subscriber;

public class AppendOnlyLinkedArrayList<T> {
    final int capacity;
    final Object[] head;
    int offset;
    Object[] tail;
    public interface NonThrowingPredicate<T> extends Predicate<T> {
        boolean test(T t);
    }
    public AppendOnlyLinkedArrayList(final int i2) {
        capacity = i2;
        final Object[] objArr = new Object[i2 + 1];
        head = objArr;
        tail = objArr;
    }
    public void add(final Object t) {
        final int i2 = capacity;
        int i3 = offset;
        if (i3 == i2) {
            final Object[] objArr = new Object[i2 + 1];
            tail[i2] = objArr;
            tail = objArr;
            i3 = 0;
        }
        tail[i3] = t;
        offset = i3 + 1;
    }
    public void setFirst(final Object    t) {
        head[0] = t;
    }
    public void forEachWhile(final NonThrowingPredicate<Object> nonThrowingPredicate) {
        final int i2 = capacity;
        for (Object[] objArr = head; null != objArr; objArr = (Object[]) objArr[i2]) {
            for (int i3 = 0; i3 < i2; i3++) {
                final Object obj = objArr[i3];
                if (null == obj) {
                    break;
                } else {
                    if (nonThrowingPredicate.test(obj)) {
                        return;
                    }
                }
            }
        }
    }
    public <U> boolean accept(final Subscriber<? super U> subscriber) {
        Object[] objArr = head;
        final int i2 = capacity;
        while (true) {
            if (null == objArr) {
                return false;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                final Object[] objArr2 = ( Object[] ) objArr[i3];
                if (null == objArr2) {
                    break;
                }
                if (NotificationLite.acceptFull(objArr2, subscriber)) {
                    return true;
                }
            }
            objArr = ( Object[] ) objArr[i2];
        }
    }
    public <U> boolean accept(final Observer<? super U> observer) {
        Object[] objArr = head;
        final int i2 = capacity;
        while (true) {
            if (null == objArr) {
                return false;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                final Object[] objArr2 = ( Object[] ) objArr[i3];
                if (null == objArr2) {
                    break;
                }
                if (NotificationLite.acceptFull(objArr2, observer)) {
                    return true;
                }
            }
            objArr = ( Object[] ) objArr[i2];
        }
    }
}
