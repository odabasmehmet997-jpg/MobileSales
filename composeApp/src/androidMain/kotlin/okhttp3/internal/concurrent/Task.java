package okhttp3.internal.concurrent;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.http2.*;
import okhttp3.internal.ws.RealWebSocket;

import java.io.IOException;
import java.util.Set;

public abstract class Task {
    private boolean cancelable = false;
    private String name = "";
    public Set currentPushRequests;
    public PushObserver pushObserver;
    public long intervalPongsReceived;
    public long intervalPingsSent;
    protected DiskLruCache this0;
    private long nextExecuteNanoTime;
    private TaskQueue queue;
    protected Task(String name, RealWebSocket z, long i2, DefaultConstructorMarker defaultConstructorMarker) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.cancelable = false;
        this.nextExecuteNanoTime = -1L;
    }
    protected Task(String str, RealWebSocket z) {
        Intrinsics.checkNotNullParameter(str, "str");
        Intrinsics.checkNotNullParameter(z, "z");
        this(str, z, -1L, null);
    }
    protected Task(final String s, final Http2Connection http2Connection, final DiskLruCache nanos) {
        Intrinsics.checkNotNullParameter(s, "s");
        Intrinsics.checkNotNullParameter(http2Connection, "http2Connection");
        Intrinsics.checkNotNullParameter(nanos, "nanos");
        this.cancelable = false;
        this.name = "";
    }
    public abstract long runOnce();
    public final String getName() {
        return this.name;
    }
    public final boolean getCancelable() {
        return this.cancelable;
    }
    public final TaskQueue getQueue() {
        return this.queue;
    }
    public final void setQueue(TaskQueue taskQueue) {
        this.queue = taskQueue;
    }
    public final long getNextExecuteNanoTime() {
        return this.nextExecuteNanoTime;
    }
    public final void setNextExecuteNanoTimeokhttp(long j2) {
        this.nextExecuteNanoTime = j2;
    }
    public final void initQueueokhttp(TaskQueue queue) {
        Intrinsics.checkNotNullParameter(queue, "queue");
        TaskQueue taskQueue = this.queue;
        if (taskQueue == queue) {
            return;
        }
        if (null != taskQueue) {
            throw new IllegalStateException("task is in multiple queues");
        }
        this.queue = queue;
    }
    public String toString() {
        return this.name;
    }
    public Http2Writer getWriter() {
        return null;
    }
    public void applyAndAckSettings(final boolean clearPreviousinlined, final Settings settingsinlined) {
    }
    public void writeSynResetokhttp(final int streamIdinlined, final ErrorCode errorCodeinlined) {
    }
    public void failConnection(final IOException e2) {
    }
    public void writePing(final boolean b, final int i, final int i1) {
    }

    public void writePingFrameokhttp() {
    }
}
