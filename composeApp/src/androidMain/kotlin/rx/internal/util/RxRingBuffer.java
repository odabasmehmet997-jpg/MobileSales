package rx.internal.util;

import java.util.Queue;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.internal.operators.NotificationLite;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.unsafe.SpmcArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;

public class RxRingBuffer implements Subscription {
    public static final int SIZE;
    private final Queue<Object> queue;
    private final int size;
    public volatile Object terminalState;
    static {
        int i2 = PlatformDependent.isAndroid() ? 16 : 128;
        String property = System.getProperty("rx.ring-buffer.size");
        if (property != null) {
            try {
                i2 = Integer.parseInt(property);
            } catch (NumberFormatException e2) {
                System.err.println("Failed to set 'rx.ring-buffer.size' with value " + property + " => " + e2.getMessage());
            }
        }
        SIZE = i2;
    }
    public static RxRingBuffer getSpscInstance() {
        if (UnsafeAccess.isUnsafeAvailable()) {
            return new RxRingBuffer(false, SIZE);
        }
        return new RxRingBuffer();
    }
    public static RxRingBuffer getSpmcInstance() {
        if (UnsafeAccess.isUnsafeAvailable()) {
            return new RxRingBuffer(true, SIZE);
        }
        return new RxRingBuffer();
    }
    private RxRingBuffer(Queue<Object> queue, int i2) {
        this.queue = queue;
        this.size = i2;
    }
    private RxRingBuffer(boolean z, int i2) {
        this.queue = z ? new SpmcArrayQueue<>(i2) : new SpscArrayQueue<>(i2);
        this.size = i2;
    }
    public synchronized void release() {
    }
    public void unsubscribe() {
        release();
    }

    RxRingBuffer() {
        int i2 = SIZE;
        this(new SpscAtomicArrayQueue<>(i2), i2);
    }
    public void onNext(Object obj) throws MissingBackpressureException {
        boolean z;
        boolean z2;
        synchronized (this) {
            try {
                Queue<Object> queue = this.queue;
                z = true;
                z2 = false;
                if (queue != null) {
                    z = false;
                    z2 = !queue.offer(NotificationLite.next(obj));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (z) {
            throw new IllegalStateException("This instance has been unsubscribed and the queue is no longer usable.");
        }
        if (z2) {
            throw new MissingBackpressureException();
        }
    }
    public void onCompleted() {
        if (this.terminalState == null) {
            this.terminalState = NotificationLite.completed();
        }
    }
    public boolean isEmpty() {
        Queue<Object> queue = this.queue;
        return queue == null || queue.isEmpty();
    }
    public Object poll() {
        synchronized (this) {
            try {
                Queue<Object> queue = this.queue;
                if (queue == null) {
                    return null;
                }
                Object objPoll = queue.poll();
                Object obj = this.terminalState;
                if (objPoll == null && obj != null && queue.peek() == null) {
                    this.terminalState = null;
                    objPoll = obj;
                }
                return objPoll;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
    public Object peek() {
        synchronized (this) {
            try {
                Queue<Object> queue = this.queue;
                if (queue == null) {
                    return null;
                }
                Object objPeek = queue.peek();
                Object obj = this.terminalState;
                if (objPeek == null && obj != null && queue.peek() == null) {
                    objPeek = obj;
                }
                return objPeek;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
    public boolean isCompleted(Object obj) {
        return NotificationLite.isCompleted(obj);
    }
    public Object getValue(Object obj) {
        return NotificationLite.getValue(obj);
    }
    public boolean isUnsubscribed() {
        return this.queue == null;
    }
}
