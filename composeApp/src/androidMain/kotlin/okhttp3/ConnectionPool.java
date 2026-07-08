package okhttp3;

import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.RealConnectionPool;

import java.util.concurrent.TimeUnit;

public final class ConnectionPool {
    private final RealConnectionPool delegate;
    public ConnectionPool(final RealConnectionPool delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
    }
    public ConnectionPool(final int i2, final long j2, final TimeUnit timeUnit) {
        this(new RealConnectionPool(TaskRunner.INSTANCE, i2, j2, timeUnit));
        Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
    }
    public ConnectionPool() {
        this(5, 5L, TimeUnit.MINUTES);
    }
    public RealConnectionPool getDelegateokhttp() {
        return delegate;
    }
    public int idleConnectionCount() {
        return delegate.idleConnectionCount();
    }
    public int connectionCount() {
        return delegate.connectionCount();
    }
    public void evictAll() {
        delegate.evictAll();
    }
    public RealConnectionPool getDelegate() {
        return null;
    }
}
