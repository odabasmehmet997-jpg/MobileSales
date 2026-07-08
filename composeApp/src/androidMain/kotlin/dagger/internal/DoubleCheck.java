package dagger.internal;

import dagger.Lazy;
import javax.inject.Provider;

public final class DoubleCheck<T> implements Provider<T>, Lazy<T> {
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance = DoubleCheck.UNINITIALIZED;
    private volatile Provider<T> provider;
    private DoubleCheck(final Provider<T> provider) {
        this.provider = provider;
    }
    public T get() {
        T t = (T) instance;
        final Object obj = DoubleCheck.UNINITIALIZED;
        if (t == obj) {
            synchronized (this) {
                t = (T) this.instance;
                if (t == obj) {
                    t = this.provider.get();
                    this.instance = reentrantCheck(this.instance, t);
                    this.provider = null;
                }
            }
        }
        return t;
    }
    @Override
    public Object getValue() {
        return null;
    }
    private static Object reentrantCheck(final Object obj, final Object obj2) {
        if (obj == DoubleCheck.UNINITIALIZED || obj == obj2) {
            return obj2;
        }
        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj + " & " + obj2 + ". This is likely due to a circular dependency.");
    }
    public static <P extends Provider<T>, T> Provider<T> provider(final P p) {
        Preconditions.checkNotNull(p);
        return p instanceof DoubleCheck ? p : new DoubleCheck(p);
    }
}
