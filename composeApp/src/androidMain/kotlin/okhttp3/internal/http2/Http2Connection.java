package okhttp3.internal.http2;

import com.google.android.material.CR;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.platform.Platform;
import okio.*;

import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.zip.DataFormatException;

import static ads_mobile_sdk.ln.r2;
import static kotlin.jvm.internal.Intrinsics.stringPlus;
import static kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException;

public final class Http2Connection implements Closeable {
    public static final int AWAIT_PING = 3;
    public static final Companion Companion = new Companion(null);
    private static final Settings DEFAULT_SETTINGS;
    public static final int DEGRADED_PING = 2;
    public static final int DEGRADED_PONG_TIMEOUT_NS = 1000000000;
    public static final int INTERVAL_PING = 1;
    public static final int OKHTTP_CLIENT_WINDOW_SIZE = 16777216;
    private long awaitPingsSent;
    private long awaitPongsReceived;
    private final boolean client;
    private final String connectionName;
    private final Set<Integer> currentPushRequests;
    private long degradedPingsSent;
    private long degradedPongDeadlineNs;
    private long degradedPongsReceived;
    private long intervalPingsSent;
    private long intervalPongsReceived;
    private boolean isShutdown;
    private int lastGoodStreamId;
    private final Listener listener;
    private int nextStreamId;
    private final Settings okHttpSettings;
    private Settings peerSettings;
    private final PushObserver pushObserver;
    private final TaskQueue pushQueue;
    private long readBytesAcknowledged;
    private long readBytesTotal;
    private final ReaderRunnable readerRunnable;
    private final TaskQueue settingsListenerQueue;
    private final Socket socket;
    private final Map<Integer, Http2Stream> streams;
    private final TaskRunner taskRunner;
    private long writeBytesMaximum = r2.getInitialWindowSize();
    private long writeBytesTotal;
    private final Http2Writer writer;
    private final TaskQueue writerQueue;
    public boolean pushedStreamokhttp(final int i2) {
        return 0 != i2 && 0 == (i2 & 1);
    }
    public void start() throws IOException {
        Http2Connection.startdefault(this, false, null, 3, null);
    }
    public void start(final boolean z) throws IOException {
        Http2Connection.startdefault(this, z, null, 2, null);
    }
    public Http2Connection(final Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        final boolean clientokhttp = builder.getClientokhttp();
        client = clientokhttp;
        listener = builder.getListenerokhttp();
        streams = new LinkedHashMap();
        final String connectionNameokhttp = builder.getConnectionNameokhttp();
        connectionName = connectionNameokhttp;
        nextStreamId = builder.getClientokhttp() ? 3 : 2;
        final TaskRunner taskRunnerokhttp = builder.getTaskRunnerokhttp();
        taskRunner = taskRunnerokhttp;
        final TaskQueue newQueue = taskRunnerokhttp.newQueue();
        writerQueue = newQueue;
        pushQueue = taskRunnerokhttp.newQueue();
        settingsListenerQueue = taskRunnerokhttp.newQueue();
        pushObserver = builder.getPushObserverokhttp();
        final Settings settings = new Settings();
        if (builder.getClientokhttp()) {
            settings.set(7, 16777216);
        }
        okHttpSettings = settings;
        peerSettings = Http2Connection.DEFAULT_SETTINGS;
        socket = builder.getSocketokhttp();
        writer = new Http2Writer(builder.getSinkokhttp(), clientokhttp);
        readerRunnable = new ReaderRunnable(this, new Http2Reader(builder.getSourceokhttp(), clientokhttp));
        currentPushRequests = new LinkedHashSet();
        if (0 != builder.getPingIntervalMillisokhttp()) {
            final long nanos = TimeUnit.MILLISECONDS.toNanos(builder.getPingIntervalMillisokhttp());
            final long nanos2 = 0;
            newQueue.schedule(new Task(stringPlus(connectionNameokhttp, " ping"), this, nanos) {
                String name = "";
                long pingIntervalNanosinlined;
                Task this0;
                public void Http2Connectionspecialinlinedschedule1(final String str, final long nanos2) {
                    name = str;
                    this0 = this;
                    pingIntervalNanosinlined = nanos2;
                }
                public long runOnce() {
                    final long j2;
                    final long j3;
                    final boolean z;
                    synchronized (this0) {
                        final long j4 = this0.intervalPongsReceived;
                        j2 = this0.intervalPingsSent;
                        if (j4 < j2) {
                            z = true;
                        } else {
                            j3 = this0.intervalPingsSent;
                            this0.intervalPingsSent = j3 + 1;
                            z = false;
                        }
                    }
                    if (z) {
                        this0.failConnection(null);
                        return -1L;
                    }
                    this0.writePing(false, 1, 0);
                    return pingIntervalNanosinlined;
                }
            }, nanos2);
        }
    }
    public boolean getClientokhttp() {
        return client;
    }
    public Listener getListenerokhttp() {
        return listener;
    }
    public Map<Integer, Http2Stream> getStreamsokhttp() {
        return streams;
    }
    public String getConnectionNameokhttp() {
        return connectionName;
    }
    public int getLastGoodStreamIdokhttp() {
        return lastGoodStreamId;
    }
    public void setLastGoodStreamIdokhttp(final int i2) {
        lastGoodStreamId = i2;
    }
    public int getNextStreamIdokhttp() {
        return nextStreamId;
    }
    public void setNextStreamIdokhttp(final int i2) {
        nextStreamId = i2;
    }
    public Settings getOkHttpSettings() {
        return okHttpSettings;
    }
    public Settings getPeerSettings() {
        return peerSettings;
    }
    public void setPeerSettings(final Settings settings) {
        Intrinsics.checkNotNullParameter(settings, "<set-?>");
        peerSettings = settings;
    }
    public long getReadBytesTotal() {
        return readBytesTotal;
    }
    public long getReadBytesAcknowledged() {
        return readBytesAcknowledged;
    }
    public long getWriteBytesTotal() {
        return writeBytesTotal;
    }
    public long getWriteBytesMaximum() {
        return writeBytesMaximum;
    }
    public Socket getSocketokhttp() {
        return socket;
    }
    public Http2Writer getWriter() {
        return writer;
    }
    public ReaderRunnable getReaderRunnable() {
        return readerRunnable;
    }
    public synchronized int openStreamCount() {
        return streams.size();
    }
    public synchronized Http2Stream getStream(final int i2) {
        return streams.get(Integer.valueOf(i2));
    }
    public synchronized Http2Stream removeStreamokhttp(final int i2) {
        final Http2Stream remove;
        remove = streams.remove(Integer.valueOf(i2));
        this.notifyAll();
        return remove;
    }
    public synchronized void updateConnectionFlowControlokhttp(final long j2) {
        final long j3 = readBytesTotal + j2;
        readBytesTotal = j3;
        final long j4 = j3 - readBytesAcknowledged;
        if (j4 >= okHttpSettings.getInitialWindowSize() / 2) {
            this.writeWindowUpdateLaterokhttp(0, j4);
            readBytesAcknowledged += j4;
        }
    }
    public Http2Stream pushStream(final int i2, final List<Header> requestHeaders, final boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        if (client) {
            throw new IllegalStateException("Client cannot push requests.");
        }
        return this.newStream(i2, requestHeaders, z);
    }
    public Http2Stream newStream(final List<Header> requestHeaders, final boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        return this.newStream(0, requestHeaders, z);
    }
    private Http2Stream newStream(final int i2, final List<Header> list, final boolean z) throws IOException {
        final int nextStreamIdokhttp;
        final Http2Stream http2Stream;
        boolean z2 = true;
        final boolean z3 = !z;
        synchronized (writer) {
            try {
                synchronized (this) {
                    if (1073741823 < nextStreamId) {
                        this.shutdown(ErrorCode.REFUSED_STREAM);
                    }
                    if (isShutdown) {
                        throw new ConnectionShutdownException();
                    }
                    nextStreamIdokhttp = this.nextStreamId;
                    this.nextStreamId = this.nextStreamId + 2;
                    http2Stream = new Http2Stream(nextStreamIdokhttp, this, z3, false, null);
                    if (z && this.writeBytesTotal < this.writeBytesMaximum && http2Stream.getWriteBytesTotal() < http2Stream.getWriteBytesMaximum()) {
                        z2 = false;
                    }
                    if (http2Stream.isOpen()) {
                        this.streams.put(Integer.valueOf(nextStreamIdokhttp), http2Stream);
                    }
                    final Unit unit = Unit.INSTANCE;
                }
                if (0 == i2) {
                    this.writer.headers(z3, nextStreamIdokhttp, list);
                } else {
                    if (this.client) {
                        throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
                    }
                    this.writer.pushPromise(i2, nextStreamIdokhttp, list);
                }
            } catch (final Throwable th) {
                throw th;
            }
        }
        if (z2) {
            writer.flush();
        }
        return http2Stream;
    }
    public void writeHeadersokhttp(final int i2, final boolean z, final List<Header> alternating) throws IOException {
        Intrinsics.checkNotNullParameter(alternating, "alternating");
        writer.headers(z, i2, alternating);
    }
    public void writeData(final int i2, final boolean z, final Buffer buffer, long j2) throws IOException {
        int min;
        long j3;
        if (0 == j2) {
            writer.data(z, i2, buffer, 0);
            return;
        }
        while (0 < j2) {
            synchronized (this) {
                while (this.writeBytesTotal >= this.writeBytesMaximum) {
                    try {
                        try {
                            if (!this.streams.containsKey(Integer.valueOf(i2))) {
                                throw new IOException("stream closed");
                            }
                            this.wait();
                        } catch (final InterruptedException unused) {
                            Thread.currentThread().interrupt();
                            throw new InterruptedIOException();
                        }
                    } catch (final Throwable th) {
                        throw th;
                    }
                }
                min = Math.min((int) Math.min(j2, this.writeBytesMaximum - this.writeBytesTotal), this.writer.maxDataLength());
                j3 = min;
                writeBytesTotal = this.writeBytesTotal + j3;
                final Unit unit = Unit.INSTANCE;
            }
            j2 -= j3;
            writer.data(z && 0 == j2, i2, buffer, min);
        }
    }
    public void writeSynResetLaterokhttp(final int i2, final ErrorCode errorCode) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        writerQueue.schedule(new Task(connectionName + '[' + i2 + "] writeSynReset", z, this) {
            boolean cancelable;
            ErrorCode errorCodeinlined;
            String name = "";
            int streamIdinlined;
            Task this0;
            public void Http2ConnectionwriteSynResetLaterinlinedexecutedefault1(final String str, final boolean z, final int i22, final ErrorCode errorCode2) {
                name = str;
                cancelable = z;
                this0 = this;
                streamIdinlined = i22;
                errorCodeinlined = errorCode2;
            }
            public long runOnce() {
                this0.writeSynResetokhttp(streamIdinlined, errorCodeinlined);
                return -1L;
            }
        }, 0L);
    }
    public void writeSynResetokhttp(final int i2, final ErrorCode statusCode) throws IOException {
        Intrinsics.checkNotNullParameter(statusCode, "statusCode");
        writer.rstStream(i2, statusCode);
    }
    public void writeWindowUpdateLaterokhttp(final int i2, final long j2) {
        writerQueue.schedule(new Task(connectionName + '[' + i2 + "] windowUpdate", this, i2) {
            boolean cancelable;
            String name = "";
            int streamIdinlined;
            long unacknowledgedBytesReadinlined;
            Task this0;
            public void C3137x3eab89f6(final String str, final boolean z, final int i22, final long j22) {
                name = str;
                cancelable = z;
                this0 = this;
                streamIdinlined = i22;
                unacknowledgedBytesReadinlined = j22;
            }
            public long runOnce() {
                try {
                    this0.getWriter().windowUpdate(streamIdinlined, unacknowledgedBytesReadinlined);
                    return -1L;
                } catch (final IOException e2) {
                    this0.failConnection(e2);
                    return -1L;
                }
            }
        }, 0L);
    }
    public void writePing(final boolean z, final int i2, final int i3) {
        try {
            writer.ping(z, i2, i3);
        } catch (final IOException e2) {
            this.failConnection(e2);
        }
    }
    public void writePingAndAwaitPong() throws InterruptedException {
        this.writePing();
        this.awaitPong();
    }
    public void writePing() throws InterruptedException {
        synchronized (this) {
            awaitPingsSent++;
        }
        this.writePing(false, 3, 1330343787);
    }
    public synchronized void awaitPong() throws InterruptedException {
        while (awaitPongsReceived < awaitPingsSent) {
            this.wait();
        }
    }
    public void flush() throws IOException {
        writer.flush();
    }
    public void shutdown(final ErrorCode statusCode) throws IOException {
        Intrinsics.checkNotNullParameter(statusCode, "statusCode");
        synchronized (writer) {
            final Ref.IntRef refIntRef = new Ref.IntRef();
            synchronized (this) {
                if (isShutdown) {
                    return;
                }
                isShutdown = true;
                refIntRef.element = this.lastGoodStreamId;
                final Unit unit = Unit.INSTANCE;
                this.writer.goAway(refIntRef.element, statusCode, Util.EMPTY_BYTE_ARRAY);
            }
        }
    }
    public void close() {
        this.closeokhttp(ErrorCode.NO_ERROR, ErrorCode.CANCEL, null);
    }
    public void failConnection(final IOException iOException) {
        final ErrorCode errorCode = ErrorCode.PROTOCOL_ERROR;
        this.closeokhttp(errorCode, errorCode, iOException);
    }
    public static void startdefault(final Http2Connection http2Connection, boolean z, TaskRunner taskRunner, final int i2, final Object obj) throws IOException {
        if (0 != (i2 & 1)) {
            z = true;
        }
        if (0 != (i2 & 2)) {
            taskRunner = TaskRunner.INSTANCE;
        }
        http2Connection.start(z, taskRunner);
    }
    public void start(final boolean z, final TaskRunner taskRunner) throws IOException {
        Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        if (z) {
            writer.connectionPreface();
            writer.settings(okHttpSettings);
            if (65535 != this.okHttpSettings.getInitialWindowSize()) {
                writer.windowUpdate(0, this.readBytesTotal - 65535);
            }
        }
        taskRunner.newQueue().schedule(new TaskQueueexecute1(connectionName, true, readerRunnable), 0L);
    }
    public void setSettings(final Settings settings) throws IOException {
        Intrinsics.checkNotNullParameter(settings, "settings");
        synchronized (writer) {
            synchronized (this) {
                if (isShutdown) {
                    throw new ConnectionShutdownException();
                }
                this.okHttpSettings.merge(settings);
                final Unit unit = Unit.INSTANCE;
            }
            this.writer.settings(settings);
        }
    }
    public synchronized boolean isHealthy(final long j2) {
        if (isShutdown) {
            return false;
        }
        if (degradedPongsReceived < degradedPingsSent) {
            return j2 < degradedPongDeadlineNs;
        }
        return true;
    }
    public void sendDegradedPingLaterokhttp() {
        synchronized (this) {
            final long j2 = degradedPongsReceived;
            final long j3 = degradedPingsSent;
            if (j2 < j3) {
                return;
            }
            degradedPingsSent = j3 + 1;
            degradedPongDeadlineNs = System.nanoTime() + Http2Connection.DEGRADED_PONG_TIMEOUT_NS;
            final Unit unit = Unit.INSTANCE;
            writerQueue.schedule(new Task(stringPlus(connectionName, " ping"), true, this) {
                boolean cancelable;
                String name = "";
                Task this0;
                public void Http2ConnectionsendDegradedPingLaterinlinedexecutedefault1(final String str, final boolean z) {
                    name = str;
                    cancelable = z;
                    this0 = this;
                }
                public long runOnce() {
                    this0.writePing(false, 2, 0);
                    return -1L;
                }
            }, 0L);
        }
    }
    public static final class Builder {
        private boolean client;
        public String connectionName;
        private Listener listener;
        private int pingIntervalMillis;
        private PushObserver pushObserver;
        public BufferedSink sink;
        public Socket socket;
        public BufferedSource source;
        private final TaskRunner taskRunner;
        public Builder socket(final Socket socket) throws IOException {
            Intrinsics.checkNotNullParameter(socket, "socket");
            return Builder.socketdefault(this, socket, null, null, null, 14, null);
        }
        public Builder socket(final Socket socket, final String peerName) throws IOException {
            Intrinsics.checkNotNullParameter(socket, "socket");
            Intrinsics.checkNotNullParameter(peerName, "peerName");
            return Builder.socketdefault(this, socket, peerName, null, null, 12, null);
        }
        public Builder socket(final Socket socket, final String peerName, final BufferedSource source) throws IOException {
            Intrinsics.checkNotNullParameter(socket, "socket");
            Intrinsics.checkNotNullParameter(peerName, "peerName");
            Intrinsics.checkNotNullParameter(source, "source");
            return Builder.socketdefault(this, socket, peerName, source, null, 8, null);
        }
        public Builder(final boolean z, final TaskRunner taskRunner) {
            Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
            client = z;
            this.taskRunner = taskRunner;
            listener = Listener.REFUSE_INCOMING_STREAMS;
            pushObserver = PushObserver.CANCEL;
        }
        public boolean getClientokhttp() {
            return client;
        }
        public void setClientokhttp(final boolean z) {
            client = z;
        }
        public TaskRunner getTaskRunnerokhttp() {
            return taskRunner;
        }
        public Socket getSocketokhttp() {
            final Socket socket = this.socket;
            if (null != socket) {
                return socket;
            }
            throwUninitializedPropertyAccessException("socket");
            return null;
        }
        public void setSocketokhttp(final Socket socket) {
            Intrinsics.checkNotNullParameter(socket, "<set-?>");
            this.socket = socket;
        }
        public String getConnectionNameokhttp() {
            final String str = connectionName;
            if (null != str) {
                return str;
            }
            throwUninitializedPropertyAccessException("connectionName");
            return null;
        }
        public void setConnectionNameokhttp(final String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            connectionName = str;
        }

        public  BufferedSource getSourceokhttp() {
            final BufferedSource bufferedSource = source;
            if (null != bufferedSource) {
                return bufferedSource;
            }
            throwUninitializedPropertyAccessException("source");
            return null;
        }

        public void setSourceokhttp(final BufferedSource bufferedSource) {
            Intrinsics.checkNotNullParameter(bufferedSource, "<set-?>");
            source = bufferedSource;
        }

        public BufferedSink getSinkokhttp() {
            final BufferedSink bufferedSink = sink;
            if (null != bufferedSink) {
                return bufferedSink;
            }
            throwUninitializedPropertyAccessException("sink");
            return null;
        }

        public void setSinkokhttp(final BufferedSink bufferedSink) {
            Intrinsics.checkNotNullParameter(bufferedSink, "<set-?>");
            sink = bufferedSink;
        }

        public Listener getListenerokhttp() {
            return listener;
        }

        public void setListenerokhttp(final Listener listener) {
            Intrinsics.checkNotNullParameter(listener, "<set-?>");
            this.listener = listener;
        }

        public PushObserver getPushObserverokhttp() {
            return pushObserver;
        }

        public void setPushObserverokhttp(final PushObserver pushObserver) {
            Intrinsics.checkNotNullParameter(pushObserver, "<set-?>");
            this.pushObserver = pushObserver;
        }

        public int getPingIntervalMillisokhttp() {
            return pingIntervalMillis;
        }

        public void setPingIntervalMillisokhttp(final int i2) {
            pingIntervalMillis = i2;
        }

        public static Builder socketdefault(final Builder builder, final Socket socket, String str, BufferedSource bufferedSource, BufferedSink bufferedSink, final int i2, final Object obj) throws IOException {
            if (0 != (i2 & 2)) {
                str = Util.peerName(socket);
            }
            if (0 != (i2 & 4)) {
                bufferedSource = Okio.buffer(Okio.source(socket));
            }
            if (0 != (i2 & 8)) {
                bufferedSink = Okio.buffer(Okio.sink(socket));
            }
            return builder.socket(socket, str, bufferedSource, bufferedSink);
        }

        public Builder socket(final Socket socket, final String peerName, final BufferedSource source, final BufferedSink sink) throws IOException {
            final String stringPlus;
            Intrinsics.checkNotNullParameter(socket, "socket");
            Intrinsics.checkNotNullParameter(peerName, "peerName");
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(sink, "sink");
            this.setSocketokhttp(socket);
            if (this.client) {
                stringPlus = Util.okHttpName + ' ' + peerName;
            } else {
                stringPlus = stringPlus("MockWebServer ", peerName);
            }
            this.setConnectionNameokhttp(stringPlus);
            this.setSourceokhttp(source);
            this.setSinkokhttp(sink);
            return this;
        }

        public Builder listener(final Listener listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            this.setListenerokhttp(listener);
            return this;
        }

        public Builder pushObserver(final PushObserver pushObserver) {
            Intrinsics.checkNotNullParameter(pushObserver, "pushObserver");
            this.setPushObserverokhttp(pushObserver);
            return this;
        }

        public Builder pingIntervalMillis(final int i2) {
            this.pingIntervalMillis = i2;
            return this;
        }

        public Http2Connection build() {
            return new Http2Connection(this);
        }
    }
    public class ReaderRunnable implements Http2Reader.Handler, Function0<Unit> {
        private final ErrorCode reader;
        final Http2Connection this0;
        public void ackSettings() {
        }

        public void alternateService(final int i2, final String origin, final ByteString protocol, final String host, final int i3, final long j2) {
            Intrinsics.checkNotNullParameter(origin, "origin");
            Intrinsics.checkNotNullParameter(protocol, "protocol");
            Intrinsics.checkNotNullParameter(host, "host");
        }
        public void priority(final int i2, final int i3, final int i4, final boolean z) {
        }

        public ReaderRunnable(final Http2Connection this0, final Http2Reader reader) {
            Intrinsics.checkNotNullParameter(this0, "this0");
            Intrinsics.checkNotNullParameter(reader, "reader");
            this.this0 = this0;
            this.reader = reader;
        }
        public Unit invoke() {
            this.invoke2();
            return Unit.INSTANCE;
        }

        public final ErrorCode getReaderokhttp() {
            return reader;
        }
        public void invoke2() {
            ErrorCode errorCode = null;
            ErrorCode errorCode2 = null;
            ErrorCode errorCode3 = ErrorCode.INTERNAL_ERROR;
            IOException e2 = null;
            try {
                try {
                    reader.readConnectionPreface(this);
                    while (reader.nextFrame(false, this)) {
                    }
                    errorCode2 = ErrorCode.NO_ERROR;
                } catch (Throwable th) {
                    th = th;
                    errorCode = errorCode3;
                    this0.closeokhttp(errorCode, errorCode3, e2);
                    Util.closeQuietly(reader);
                    throw th;
                }
                try {
                    this0.closeokhttp(errorCode2, ErrorCode.CANCEL, null);
                    errorCode = errorCode2;
                } catch (final IOException e4) {
                    e2 = e4;
                    final ErrorCode errorCode4 = ErrorCode.PROTOCOL_ERROR;
                    final Http2Connection http2Connection = this0;
                    this.this0.closeokhttp(errorCode4, errorCode4, e2);
                    errorCode = http2Connection;
                    errorCode3 = reader;
                    Util.closeQuietly((CloseableKt) errorCode3);
                }
                errorCode3 = reader;
                Util.closeQuietly((CloseableKt) errorCode3);
            } catch (final Throwable th2) {

                this0.closeokhttp(errorCode, errorCode3, e2);
                Util.closeQuietly(reader);

            }
        }
        public void data(final boolean z, final int i2, final BufferedSource source, final int i3) throws IOException {
            Intrinsics.checkNotNullParameter(source, "source");
            if (this0.pushedStreamokhttp(i2)) {
                this0.pushDataLaterokhttp(i2, source, i3, z);
                return;
            }
            final Http2Stream stream = this0.getStream(i2);
            if (null == stream) {
                this0.writeSynResetLaterokhttp(i2, ErrorCode.PROTOCOL_ERROR);
                final long j2 = i3;
                this0.updateConnectionFlowControlokhttp(j2);
                source.skip(j2);
                return;
            }
            stream.receiveData(source, i3);
            if (z) {
                stream.receiveHeaders(Util.EMPTY_HEADERS, true);
            }
        }
        public void headers(final boolean z, final int i2, final int i3, final List<Header> headerBlock) {
            Intrinsics.checkNotNullParameter(headerBlock, "headerBlock");
            if (this0.pushedStreamokhttp(i2)) {
                this0.pushHeadersLaterokhttp(i2, headerBlock, z);
                return;
            }
            final Http2Connection http2Connection = this0;
            synchronized (http2Connection) {
                final Http2Stream stream = http2Connection.getStream(i2);
                if (null == stream) {
                    if (http2Connection.isShutdown) {
                        return;
                    }
                    if (i2 <= http2Connection.getLastGoodStreamIdokhttp()) {
                        return;
                    }
                    if (i2 % 2 == http2Connection.getNextStreamIdokhttp() % 2) {
                        return;
                    }
                    final Http2Stream http2Stream = new Http2Stream(i2, http2Connection, false, z, Util.toHeaders(headerBlock));
                    http2Connection.setLastGoodStreamIdokhttp(i2);
                    http2Connection.getStreamsokhttp().put(Integer.valueOf(i2), http2Stream);
                    http2Connection.taskRunner.newQueue().schedule(new Task(http2Connection.getConnectionNameokhttp() + '[' + i2 + "] onStream", z, this) {
                        boolean cancelable;
                        String name = "";
                        Http2Stream newStreaminlined;
                        Http2Connection this0;
                        public void C3135x74e0874(final String str, final boolean z2, final Http2Connection http2Connection2, final Http2Stream http2Stream2) {
                            name = str;
                            cancelable = z2;
                            this0 = http2Connection2;
                            newStreaminlined = http2Stream2;
                        }
                        public long runOnce() {
                            try {
                                this0.getListenerokhttp().onStream(newStreaminlined);
                                return -1L;
                            } catch (final IOException e2) {
                                Platform.Companion.get().log(stringPlus("Http2Connection.Listener failure for ", this0.getConnectionNameokhttp()), 4, e2);
                                try {
                                    newStreaminlined.close(ErrorCode.PROTOCOL_ERROR, e2);
                                    return -1L;
                                } catch (final IOException unused) {
                                    return -1L;
                                }
                            }
                        }
                    }, 0L);
                    return;
                }
                final Unit unit = Unit.INSTANCE;
                stream.receiveHeaders(Util.toHeaders(headerBlock), z);
            }
        }
        public void rstStream(final int i2, final ErrorCode errorCode) {
            Intrinsics.checkNotNullParameter(errorCode, "errorCode");
            if (this0.pushedStreamokhttp(i2)) {
                this0.pushResetLaterokhttp(i2, errorCode);
                return;
            }
            final Http2Stream removeStreamokhttp = this0.removeStreamokhttp(i2);
            if (null == removeStreamokhttp) {
                return;
            }
            removeStreamokhttp.receiveRstStream(errorCode);
        }
        public void settings(final boolean z, final Settings settings) {
            Intrinsics.checkNotNullParameter(settings, "settings");
            this0.writerQueue.schedule(new Task(stringPlus(this0.getConnectionNameokhttp(), " applyAndAckSettings"), z, this) {
                boolean cancelable;
                boolean clearPreviousinlined;
                String name = "";
                Settings settingsinlined;
                Task this0;
                public void C3136x8b30c3bb(final String str, final boolean z2, final boolean z3, final Settings settings2) {
                    name = str;
                    cancelable = z2;
                    this0 = this;
                    clearPreviousinlined = z3;
                    settingsinlined = settings2;
                }
                public long runOnce() {
                    this0.applyAndAckSettings(clearPreviousinlined, settingsinlined);
                    return -1L;
                }
            }, 0L);
        }
        public final void applyAndAckSettings(final boolean z, final CR.string settings) {
            final Settings r13;
            final long initialWindowSize;
            int i2;
            Http2Stream[] http2StreamArr;
            Intrinsics.checkNotNullParameter(settings, "settings");
            final Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
            final Http2Writer writer = this0.getWriter();
            final Http2Connection http2Connection = this0;
            synchronized (writer) {
                synchronized (http2Connection) {
                    try {
                        final Settings peerSettings = http2Connection.getPeerSettings();
                        if (z) {
                            r13 = settings;
                        } else {
                            final Settings settings2 = new Settings();
                            settings2.merge(peerSettings);
                            settings2.merge(settings);
                            r13 = settings2;
                        }
                        refObjectRef.element = r13;
                        initialWindowSize = r13.getInitialWindowSize() - peerSettings.getInitialWindowSize();
                        i2 = 0;
                        if (0 != initialWindowSize && !http2Connection.getStreamsokhttp().isEmpty()) {
                            final Object[] array = http2Connection.getStreamsokhttp().values().toArray(new Http2Stream[0]);
                            if (null == array) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                            }
                            http2StreamArr = (Http2Stream[]) array;
                            http2Connection.setPeerSettings((Settings) refObjectRef.element);
                            http2Connection.settingsListenerQueue.schedule(new Task(stringPlus(http2Connection.getConnectionNameokhttp(), " onSettings"), z, this) {
                                boolean cancelable;
                                String name = "";
                                Ref.ObjectRef newPeerSettingsinlined;
                                Http2Connection this0;
                                public void C3134x99a7d6ec(final String str, final boolean z2, final Http2Connection http2Connection2, final Ref.ObjectRef refObjectRef2) {
                                    name = str;
                                    cancelable = z2;
                                    this0 = http2Connection2;
                                    newPeerSettingsinlined = refObjectRef2;
                                }
                                public long runOnce() {
                                    this0.getListenerokhttp().onSettings(this0, (Settings) newPeerSettingsinlined.element);
                                    return -1L;
                                }
                            }, 0L);
                            final Unit unit = Unit.INSTANCE;
                        }
                        http2StreamArr = null;
                        http2Connection2.setPeerSettings((Settings) refObjectRef2.element);
                        http2Connection2.settingsListenerQueue.schedule(new Task(stringPlus(http2Connection2.getConnectionNameokhttp(), " onSettings"), true, http2Connection2, refObjectRef2) { // from class: okhttp3.internal.http2.Http2ConnectionReaderRunnableapplyAndAckSettingslambda-7lambda-6inlinedexecutedefault1
                            boolean cancelable;
                            String name = "";
                            Ref.ObjectRef newPeerSettingsinlined;
                            Http2Connection this0;
                            public void C3134x99a7d6ec(final String str, final boolean z2, final Http2Connection http2Connection2, final Ref.ObjectRef refObjectRef2) {
                                name = str;
                                cancelable = z2;
                                this0 = http2Connection2;
                                newPeerSettingsinlined = refObjectRef2;
                            }
                            public long runOnce() {
                                this0.getListenerokhttp().onSettings(this0, (Settings) newPeerSettingsinlined.element);
                                return -1L;
                            }
                        }, 0L);
                        final Unit unit2 = Unit.INSTANCE;
                    } catch (final Throwable th) {
                        throw th;
                    }
                }
                try {
                    http2Connection2.getWriter().applyAndAckSettings((Settings) refObjectRef2.element);
                } catch (final IOException e2) {
                    http2Connection2.failConnection(e2);
                }
                final Unit unit3 = Unit.INSTANCE;
            }
            if (null != http2StreamArr) {
                final int length = http2StreamArr.length;
                while (i2 < length) {
                    final Http2Stream http2Stream = http2StreamArr[i2];
                    i2++;
                    synchronized (http2Stream) {
                        http2Stream.addBytesToWriteWindow(initialWindowSize);
                        final Unit unit4 = Unit.INSTANCE;
                    }
                }
            }
        }
        public void ping(final boolean z, final int i2, final int i3) {
            if (!z) {
                this0.writerQueue.schedule(new Task(stringPlus(this0.getConnectionNameokhttp(), " ping"), z, this) {
                    boolean cancelable;
                    String name = "";
                    int payload1inlined;
                    int payload2inlined;
                    Http2Connection this0;
                    public void Http2ConnectionReaderRunnablepinginlinedexecutedefault1(final String str, final boolean z2, final Http2Connection http2Connection, final int i22, final int i32) {
                        name = str;
                        cancelable = z2;
                        this0 = http2Connection;
                        payload1inlined = i22;
                        payload2inlined = i32;
                    }
                    public long runOnce() {
                        this0.writePing(true, payload1inlined, payload2inlined);
                        return -1L;
                    }
                }, 0L);
                return;
            }
            final Http2Connection http2Connection = this0;
            synchronized (http2Connection) {
                try {
                    if (1 == i22) {
                        http2Connection.intervalPongsReceived++;
                    } else if (2 == i22) {
                        http2Connection.degradedPongsReceived++;
                    } else {
                        if (3 == i22) {
                            http2Connection.awaitPongsReceived++;
                            http2Connection.notifyAll();
                        }
                        final Unit unit = Unit.INSTANCE;
                    }
                } catch (final Throwable th) {
                    throw th;
                }
            }
        }
        public void goAway(final int i2, final ErrorCode errorCode, final ByteString debugData) {
            int i3;
            final Object[] array;
            Intrinsics.checkNotNullParameter(errorCode, "errorCode");
            Intrinsics.checkNotNullParameter(debugData, "debugData");
            debugData.size();
            final Http2Connection http2Connection = this0;
            synchronized (http2Connection) {
                i3 = 0;
                array = http2Connection.getStreamsokhttp().values().toArray(new Http2Stream[0]);
                if (null == array) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                }
                http2Connection.isShutdown = true;
                final Unit unit = Unit.INSTANCE;
            }
            final Http2Stream[] http2StreamArr = (Http2Stream[]) array;
            final int length = http2StreamArr.length;
            while (i3 < length) {
                final Http2Stream http2Stream = http2StreamArr[i3];
                i3++;
                if (http2Stream.getId() > i2 && http2Stream.isLocallyInitiated()) {
                    http2Stream.receiveRstStream(ErrorCode.REFUSED_STREAM);
                    this0.removeStreamokhttp(http2Stream.getId());
                }
            }
        }
        public void windowUpdate(final int i2, final long j2) {
            if (0 == i2) {
                final Http2Connection http2Connection = this0;
                synchronized (http2Connection) {
                    http2Connection.writeBytesMaximum = http2Connection.getWriteBytesMaximum() + j2;
                    http2Connection.notifyAll();
                    final Unit unit = Unit.INSTANCE;
                }
                return;
            }
            final Http2Stream stream = this0.getStream(i2);
            if (null != stream) {
                synchronized (stream) {
                    stream.addBytesToWriteWindow(j2);
                    final Unit unit2 = Unit.INSTANCE;
                }
            }
        }
        public void pushPromise(final int i2, final int i3, final List<Header> requestHeaders) {
            Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
            this0.pushRequestLaterokhttp(i3, requestHeaders);
        }
    }
    public void closeokhttp(final ErrorCode connectionCode, final ErrorCode streamCode, final IOException iOException) {
        int i2;
        final Object[] objArr;
        Intrinsics.checkNotNullParameter(connectionCode, "connectionCode");
        Intrinsics.checkNotNullParameter(streamCode, "streamCode");
        if (!Util.assertionsEnabled || !Thread.holdsLock(this)) {
            try {
                this.shutdown(connectionCode);
            } catch (final IOException unused) {
            }
            synchronized (this) {
                try {
                    if (this.streams.isEmpty()) {
                        objArr = null;
                    } else {
                        objArr = this.streams.values().toArray(new Http2Stream[0]);
                        if (null == objArr) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                        }
                        this.streams.clear();
                    }
                    final Unit unit = Unit.INSTANCE;
                } catch (final Throwable th) {
                    throw th;
                }
            }
            final Http2Stream[] http2StreamArr = (Http2Stream[]) objArr;
            if (null != http2StreamArr) {
                for (final Http2Stream http2Stream : http2StreamArr) {
                    try {
                        http2Stream.close(streamCode, iOException);
                    } catch (final IOException unused2) {
                    }
                }
            }
            try {
                this.writer.close();
            } catch (final IOException unused3) {
            }
            try {
                this.socket.close();
            } catch (final IOException unused4) {
            }
            writerQueue.shutdown();
            pushQueue.shutdown();
            settingsListenerQueue.shutdown();
            return;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
    }
    public void pushRequestLaterokhttp(final int i2, final List<Header> requestHeaders) {
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        synchronized (this) {
            if (currentPushRequests.contains(Integer.valueOf(i2))) {
                this.writeSynResetLaterokhttp(i2, ErrorCode.PROTOCOL_ERROR);
                return;
            }
            currentPushRequests.add(Integer.valueOf(i2));
            pushQueue.schedule(new Task(connectionName + '[' + i2 + "] onRequest", z, this) {
                boolean cancelable;
                String name = "";
                List requestHeadersinlined = Collections.emptyList();
                int streamIdinlined;
                Task this0;
                public void Http2ConnectionpushRequestLaterinlinedexecutedefault1(final String str, final boolean z, final int i22, final List requestHeaders2) {
                    name = str;
                    cancelable = z;
                    this0 = this;
                    streamIdinlined = i22;
                    requestHeadersinlined = requestHeaders2;
                }
                public long runOnce() {
                    final PushObserver pushObserver;
                    final Set set;
                    pushObserver = this0.pushObserver;
                    if (!pushObserver.onRequest(streamIdinlined, requestHeadersinlined)) {
                        return -1L;
                    }
                    try {
                        this0.getWriter().rstStream(streamIdinlined, ErrorCode.CANCEL);
                        synchronized (this0) {
                            set = this0.currentPushRequests;
                            set.remove(Integer.valueOf(streamIdinlined));
                        }
                        return -1L;
                    } catch (final IOException unused) {
                        return -1L;
                    }
                }
            }, 0L);
        }
    }
    public void pushHeadersLaterokhttp(final int i2, final List<Header> requestHeaders, final boolean z) {
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        pushQueue.schedule(new Task(connectionName + '[' + i2 + "] onHeaders", z, this) {
            boolean cancelable;
            boolean inFinishedinlined;
            String name = "";
            List requestHeadersinlined = Collections.emptyList();
            int streamIdinlined;
            Task this0;
            public void Http2ConnectionpushHeadersLaterinlinedexecutedefault1(final String str, final boolean z2, final int i22, final List requestHeaders2, final boolean z3) {
                name = str;
                cancelable = z2;
                this0 = this;
                streamIdinlined = i22;
                requestHeadersinlined = requestHeaders2;
                inFinishedinlined = z3;
            }
            public long runOnce() {
                final PushObserver pushObserver;
                final Set set;
                pushObserver = this0.pushObserver;
                final boolean onHeaders = pushObserver.onHeaders(streamIdinlined, requestHeadersinlined, inFinishedinlined);
                if (onHeaders) {
                    try {
                        this0.getWriter().rstStream(streamIdinlined, ErrorCode.CANCEL);
                    } catch (final IOException unused) {
                        return -1L;
                    }
                }
                if (!onHeaders && !inFinishedinlined) {
                    return -1L;
                }
                synchronized (this0) {
                    set = this0.currentPushRequests;
                    set.remove(Integer.valueOf(streamIdinlined));
                }
                return -1L;
            }
        }, 0L);
    }
    public void pushDataLaterokhttp(final int i2, final BufferedSource source, final int i3, final boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        final Buffer buffer = new Buffer();
        final long j2 = i3;
        source.require(j2);
        try {
            source.read(buffer, j2);
        } catch (final DataFormatException e) {
            throw new RuntimeException(e);
        }
        pushQueue.schedule(new Task(connectionName + '[' + i2 + "] onData", z, this) {
            Buffer bufferinlined;
            int byteCountinlined;
            boolean cancelable;
            boolean inFinishedinlined;
            String name = "";
            int streamIdinlined;
            Task this0;
            public void Http2ConnectionpushDataLaterinlinedexecutedefault1(final String str, final boolean z2, final int i22, final Buffer buffer2, final int i32, final boolean z3) {
                name = str;
                cancelable = z2;
                this0 = this;
                streamIdinlined = i22;
                bufferinlined = buffer2;
                byteCountinlined = i32;
                inFinishedinlined = z3;
            }
            public long runOnce() {
                final PushObserver pushObserver;
                final Set set;
                try {
                    pushObserver = this0.pushObserver;
                    final boolean onData = pushObserver.onData(streamIdinlined, bufferinlined, byteCountinlined, inFinishedinlined);
                    if (onData) {
                        this0.getWriter().rstStream(streamIdinlined, ErrorCode.CANCEL);
                    }
                    if (!onData && !inFinishedinlined) {
                        return -1L;
                    }
                    synchronized (this0) {
                        set = this0.currentPushRequests;
                        set.remove(Integer.valueOf(streamIdinlined));
                    }
                    return -1L;
                } catch (final IOException unused) {
                    return -1L;
                }
            }
        }, 0L);
    }
    public void pushResetLaterokhttp(final int i2, final ErrorCode errorCode) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        pushQueue.schedule(new Task(connectionName + '[' + i2 + "] onReset", z, this) {
            boolean cancelable;
            ErrorCode errorCodeinlined;
            String name = "";
            int streamIdinlined;
            Task this0;
            public void Http2ConnectionpushResetLaterinlinedexecutedefault1(final String str, final boolean z, final int i22, final ErrorCode errorCode2) {
                name = str;
                cancelable = z;
                this0 = this;
                streamIdinlined = i22;
                errorCodeinlined = errorCode2;
            }
            public long runOnce() {
                final PushObserver pushObserver;
                final Set set;
                pushObserver = this0.pushObserver;
                pushObserver.onReset(streamIdinlined, errorCodeinlined);
                synchronized (this0) {
                    set = this0.currentPushRequests;
                    set.remove(Integer.valueOf(streamIdinlined));
                    final Unit unit = Unit.INSTANCE;
                }
                return -1L;
            }
        }, 0L);
    }
    public static abstract class Listener {
        public static final Companion Companion = new Companion(null);
        public static final Listener REFUSE_INCOMING_STREAMS = new Listener() {
            void Http2ConnectionListenerCompanionREFUSE_INCOMING_STREAMS1() {
            }
            public void onStream(final Http2Stream stream) throws IOException {
                Intrinsics.checkNotNullParameter(stream, "stream");
                stream.close(ErrorCode.REFUSED_STREAM, null);
            }
        };
        public void onSettings(final Http2Connection connection, final Settings settings) {
            Intrinsics.checkNotNullParameter(connection, "connection");
            Intrinsics.checkNotNullParameter(settings, "settings");
        }
        public abstract void onStream(Http2Stream http2Stream) throws IOException;
        public static final class Companion {
            public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
        }
        private Companion() {
        }
        public Settings getDEFAULT_SETTINGS() {
            return DEFAULT_SETTINGS;
        }
    }
    static {
        final Settings settings = new Settings();
        settings.set(7, 65535);
        settings.set(5, 16384);
        DEFAULT_SETTINGS = settings;
    }
}

