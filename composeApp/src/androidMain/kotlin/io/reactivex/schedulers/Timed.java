package io.reactivex.schedulers;

import io.reactivex.internal.functions.ObjectHelper;
import java.util.concurrent.TimeUnit;

public final class Timed<T> {
    final long time;
    final TimeUnit unit;
    final T value;
    public Timed(T t, long j2, TimeUnit timeUnit) {
        this.value = t;
        this.time = j2;
        this.unit = ObjectHelper.requireNonNull(timeUnit, "unit is null");
    }
    public T value() {
        return this.value;
    }
    public long time() {
        return this.time;
    }
    public boolean equals(Object obj) {
        if (!(obj instanceof Timed timed)) {
            return false;
        }
        return ObjectHelper.equals(this.value, timed.value) && this.time == timed.time && ObjectHelper.equals(this.unit, timed.unit);
    }
    public int hashCode() {
        T t = this.value;
        int hashCode = null != t ? t.hashCode() : 0;
        long j2 = this.time;
        return (((hashCode * 31) + ((int) (j2 ^ (j2 >>> 31)))) * 31) + this.unit.hashCode();
    }
    public String toString() {
        return "Timed[time=" + this.time + ", unit=" + this.unit + ", value=" + this.value + "]";
    }
}
