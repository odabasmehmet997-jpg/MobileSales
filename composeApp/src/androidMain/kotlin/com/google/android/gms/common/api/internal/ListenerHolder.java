package com.google.android.gms.common.api.internal;

import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.concurrent.HandlerExecutor;
import java.util.concurrent.Executor;

public final class ListenerHolder<L> {
    private final Executor zaa;
    private volatile Object zab;
    private volatile ListenerKey zac;

    public static final class ListenerKey<L> {
        private final Object zaa;
        private final String zab;

        ListenerKey(final L l, final String str) {
            zaa = l;
            zab = str;
        }
        public boolean equals( final Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ListenerKey listenerKey)) {
                return false;
            }
            return zaa == listenerKey.zaa && zab.equals(listenerKey.zab);
        }
        public int hashCode() {
            return (System.identityHashCode(zaa) * 31) + zab.hashCode();
        }

        public String toIdString() {
            final int identityHashCode = System.identityHashCode(zaa);
            return zab + "@" + identityHashCode;
        }
    }
    public interface Notifier<L> {
        void notifyListener(  L l);
        void onNotifyListenerFailed();
    }
    ListenerHolder( final Looper looper, final L l,  final String str) {
        zaa = new HandlerExecutor(looper);
        zab = Preconditions.checkNotNull(l, "Listener must not be null");
        zac = new ListenerKey(l, Preconditions.checkNotEmpty(str));
    }
    public void clear() {
        zab = null;
        zac = null;
    }
    public ListenerKey<L> getListenerKey() {
        return zac;
    }
    public void notifyListener( final Notifier<? super L> notifier) {
        Preconditions.checkNotNull(notifier, "Notifier must not be null");
        zaa.execute(new zacb(this, notifier));
    }
    public void zaa(final Notifier notifier) {
        final Object obj = zab;
        if (obj == null) {
            notifier.onNotifyListenerFailed();
            return;
        }
        try {
            notifier.notifyListener(obj);
        } catch (final RuntimeException e2) {
            notifier.onNotifyListenerFailed();
            throw e2;
        }
    }
    ListenerHolder( final Executor executor,  final L l,  final String str) {
        zaa = Preconditions.checkNotNull(executor, "Executor must not be null");
        zab = Preconditions.checkNotNull(l, "Listener must not be null");
        zac = new ListenerKey(l, Preconditions.checkNotEmpty(str));
    }
}
