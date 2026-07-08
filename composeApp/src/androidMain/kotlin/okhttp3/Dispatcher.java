package okhttp3;

import com.proje.mobilesales.C2442R;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealCall;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static kotlin.jvm.internal.Intrinsics.*;

public final class Dispatcher {
    private final ArrayDeque<RealCall.AsyncCall> readyAsyncCalls;
    private final ArrayDeque<RealCall.AsyncCall> runningAsyncCalls;
    private final ArrayDeque<RealCall> runningSyncCalls;
    private ExecutorService executorServiceOrNull;
    private Runnable idleCallback;
    private int maxRequests;
    private int maxRequestsPerHost;

    public Dispatcher() {
        maxRequests = 64;
        maxRequestsPerHost = 5;
        readyAsyncCalls = new ArrayDeque<>();
        runningAsyncCalls = new ArrayDeque<>();
        runningSyncCalls = new ArrayDeque<>();
    }
    public Dispatcher(final ExecutorService executorService) {
        this();
        Intrinsics.checkNotNullParameter(executorService, "executorService");
        executorServiceOrNull = executorService;
    }
    public synchronized int getMaxRequests() {
        return maxRequests;
    }
    public void setMaxRequests(final int i2) {
        if (1 > i2) {
            throw new IllegalArgumentException(stringPlus("max < 1: ", Integer.valueOf(i2)));
        }
        synchronized (this) {
            maxRequests = i2;
            final Unit unit = Unit.INSTANCE;
        }
        this.promoteAndExecute();
    }
    public synchronized int getMaxRequestsPerHost() {
        return maxRequestsPerHost;
    }
    public void setMaxRequestsPerHost(final int i2) {
        if (1 > i2) {
            throw new IllegalArgumentException(stringPlus("max < 1: ", Integer.valueOf(i2)));
        }
        synchronized (this) {
            maxRequestsPerHost = i2;
            final Unit unit = Unit.INSTANCE;
        }
        this.promoteAndExecute();
    }
    public synchronized Runnable getIdleCallback() {
        return idleCallback;
    }
    public synchronized void setIdleCallback(final Runnable runnable) {
        idleCallback = runnable;
    }
    public synchronized ExecutorService executorService() {
        final ExecutorService executorService;
        try {
            if (null == this.executorServiceOrNull) {
                executorServiceOrNull = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory(stringPlus(Util.okHttpName, " Dispatcher"), false));
            }
            executorService = executorServiceOrNull;
            checkNotNull(executorService);
        } catch (final Throwable th) {
            throw th;
        }
        return executorService;
    }
    public void enqueueokhttp(final RealCall.AsyncCall call) {
        final RealCall.AsyncCall asyncCallFindExistingCallWithHost;
        Intrinsics.checkNotNullParameter(call, "call");
        synchronized (this) {
            try {
                readyAsyncCalls.add(call);
                if (!call.getCall().getForWebSocket() && null != (asyncCallFindExistingCallWithHost = findExistingCallWithHost(call.getHost()))) {
                    call.reuseCallsPerHostFrom(asyncCallFindExistingCallWithHost);
                }
                final Unit unit = Unit.INSTANCE;
            } catch (final Throwable th) {
                throw th;
            }
        }
        this.promoteAndExecute();
    }
    private RealCall.AsyncCall findExistingCallWithHost(final String str) {
        for (RealCall.AsyncCall next : runningAsyncCalls) {
            if (areEqual(next.getHost(), str)) {
                return next;
            }
        }
        for (RealCall.AsyncCall next2 : readyAsyncCalls) {
            if (areEqual(next2.getHost(), str)) {
                return next2;
            }
        }
        return null;
    }
    public synchronized void cancelAll() {
        try {
            for (RealCall.AsyncCall readyAsyncCall : readyAsyncCalls) {
                readyAsyncCall.getCall().cancel();
            }
            for (RealCall.AsyncCall runningAsyncCall : runningAsyncCalls) {
                runningAsyncCall.getCall().cancel();
            }
            for (RealCall runningSyncCall : runningSyncCalls) {
                runningSyncCall.cancel();
            }
        } catch (final Throwable th) {
            throw th;
        }
    }
    public synchronized void executedokhttp(final RealCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        runningSyncCalls.add(call);
    }
    public void finishedokhttp(final RealCall.AsyncCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        call.getCallsPerHost().decrementAndGet();
        this.finished(runningAsyncCalls, call);
    }
    public void finishedokhttp(final RealCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.finished(runningSyncCalls, call);
    }
    private <T> void finished(final Deque<T> deque, final T t) {
        final Runnable idleCallback;
        synchronized (this) {
            if (!deque.remove(t)) {
                throw new AssertionError("Call wasn't in-flight!");
            }
            idleCallback = this.idleCallback;
            final Unit unit = Unit.INSTANCE;
        }
        if (this.promoteAndExecute() || null == idleCallback) {
            return;
        }
        idleCallback.run();
    }
    public synchronized List<Call<C2442R>> queuedCalls() {
        final List<Call<C2442R>> listUnmodifiableList;
        try {
            final ArrayDeque<RealCall.AsyncCall> arrayDeque = readyAsyncCalls;
            final ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayDeque, 10));
            final Iterator<RealCall.AsyncCall> it = arrayDeque.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getCall());
            }
            listUnmodifiableList = Collections.unmodifiableList(arrayList);
            checkNotNullExpressionValue(listUnmodifiableList, "unmodifiableList(readyAsyncCalls.map { it.call })");
        } catch (final Throwable th) {
            throw th;
        }
        return listUnmodifiableList;
    }
    public synchronized List<Call<C2442R>> runningCalls() {
        final List<Call<C2442R>> listUnmodifiableList;
        try {
            final ArrayDeque<RealCall> arrayDeque = runningSyncCalls;
            final ArrayDeque<RealCall.AsyncCall> arrayDeque2 = runningAsyncCalls;
            final ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayDeque2, 10));
            final Iterator<RealCall.AsyncCall> it = arrayDeque2.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getCall());
            }
            listUnmodifiableList = Collections.unmodifiableList(CollectionsKt.plus((Collection) arrayDeque, arrayList));
            checkNotNullExpressionValue(listUnmodifiableList, "unmodifiableList(running\u2026yncCalls.map { it.call })");
        } catch (final Throwable th) {
            throw th;
        }
        return listUnmodifiableList;
    }
    public synchronized int queuedCallsCount() {
        return readyAsyncCalls.size();
    }
    public synchronized int runningCallsCount() {
        return runningAsyncCalls.size() + runningSyncCalls.size();
    } 
    public ExecutorService m1754deprecated_executorService() {
        return this.executorService();
    }
    private boolean promoteAndExecute() {
        return false;
    }
}
