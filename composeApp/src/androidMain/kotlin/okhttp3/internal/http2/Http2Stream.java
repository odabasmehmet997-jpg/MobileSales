package okhttp3.internal.http2;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;
import okhttp3.internal.Util;
import okio.*;

import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.zip.DataFormatException;

import static kotlin.jvm.internal.Intrinsics.*;

public final class Http2Stream {
    public static final Companion Companion = new Companion(null);
    public static final long EMIT_BUFFER_SIZE = 16384;
    private final Http2Connection connection;
    private final ArrayDeque<Headers> headersQueue;
    private final int f2049id;
    private final StreamTimeout readTimeout;
    private final FramingSink sink;
    private final FramingSource source;
    private final StreamTimeout writeTimeout;
    private ErrorCode errorCode;
    private IOException errorException;
    private boolean hasResponseHeaders;
    private long readBytesAcknowledged;
    private long readBytesTotal;
    private long writeBytesMaximum;
    private long writeBytesTotal;

    public Http2Stream(final int i2, final Http2Connection connection, final boolean z, final boolean z2, final Headers headers) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        f2049id = i2;
        this.connection = connection;
        writeBytesMaximum = connection.getPeerSettings().getInitialWindowSize();
        final ArrayDeque<Headers> arrayDeque = new ArrayDeque<>();
        headersQueue = arrayDeque;
        source = new FramingSource(this, connection.getOkHttpSettings().getInitialWindowSize(), z2);
        sink = new FramingSink(this, z);
        readTimeout = new StreamTimeout(this);
        writeTimeout = new StreamTimeout(this);
        if (null != headers) {
            if (this.isLocallyInitiated()) {
                throw new IllegalStateException("locally-initiated streams shouldn't have headers yet");
            }
            arrayDeque.add(headers);
        } else if (!this.isLocallyInitiated()) {
            throw new IllegalStateException("remotely-initiated streams should have headers");
        }
    }

    public int getId() {
        return f2049id;
    }

    public Http2Connection getConnection() {
        return connection;
    }

    public long getReadBytesTotal() {
        return readBytesTotal;
    }

    public void setReadBytesTotalokhttp(final long j2) {
        readBytesTotal = j2;
    }

    public long getReadBytesAcknowledged() {
        return readBytesAcknowledged;
    }

    public void setReadBytesAcknowledgedokhttp(final long j2) {
        readBytesAcknowledged = j2;
    }

    public long getWriteBytesTotal() {
        return writeBytesTotal;
    }

    public void setWriteBytesTotalokhttp(final long j2) {
        writeBytesTotal = j2;
    }

    public long getWriteBytesMaximum() {
        return writeBytesMaximum;
    }

    public void setWriteBytesMaximumokhttp(final long j2) {
        writeBytesMaximum = j2;
    }

    public FramingSource getSourceokhttp() {
        return source;
    }

    public FramingSink getSinkokhttp() {
        return sink;
    }

    public StreamTimeout getReadTimeoutokhttp() {
        return readTimeout;
    }

    public StreamTimeout getWriteTimeoutokhttp() {
        return writeTimeout;
    }

    public synchronized ErrorCode getErrorCodeokhttp() {
        return errorCode;
    }

    public void setErrorCodeokhttp(final ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public IOException getErrorExceptionokhttp() {
        return errorException;
    }

    public void setErrorExceptionokhttp(final IOException iOException) {
        errorException = iOException;
    }

    public synchronized boolean isOpen() {
        try {
            if (null != this.errorCode) {
                return false;
            }
            if (source.getFinishedokhttp() || source.getClosedokhttp()) {
                if (sink.getFinished() || sink.getClosed()) {
                    return !hasResponseHeaders;
                }
            }
            return true;
        } catch (final Throwable th) {
            throw th;
        }
    }

    public boolean isLocallyInitiated() {
        return connection.getClientokhttp() == (1 == (this.f2049id & 1));
    }

    public synchronized Headers takeHeaders() throws IOException {
        final Headers headersRemoveFirst;
        readTimeout.enter();
        while (headersQueue.isEmpty() && null == this.errorCode) {
            try {
                this.waitForIookhttp();
            } catch (final Throwable th) {
                readTimeout.exitAndThrowIfTimedOut();
                try {
                    throw th;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        readTimeout.exitAndThrowIfTimedOut();
        if (!headersQueue.isEmpty()) {
            headersRemoveFirst = headersQueue.removeFirst();
            checkNotNullExpressionValue(headersRemoveFirst, "headersQueue.removeFirst()");
        } else {
            final IOException iOException = errorException;
            if (null != iOException) {
                throw iOException;
            }
            final ErrorCode errorCode = this.errorCode;
            checkNotNull(errorCode);
            throw new StreamResetException(errorCode);
        }
        return headersRemoveFirst;
    }

    public synchronized Headers trailers() throws IOException {
        Headers trailers;
        if (source.getFinishedokhttp() && source.getReceiveBuffer().exhausted() && source.getReadBuffer().exhausted()) {
            trailers = source.getTrailers();
            if (null == trailers) {
                trailers = Util.EMPTY_HEADERS;
            }
        } else {
            if (null != this.errorCode) {
                final IOException iOException = errorException;
                if (null != iOException) {
                    throw iOException;
                }
                final ErrorCode errorCode = this.errorCode;
                checkNotNull(errorCode);
                throw new StreamResetException(errorCode);
            }
            throw new IllegalStateException("too early; can't read the trailers yet");
        }
        return trailers;
    }

    public void enqueueTrailers(final Headers trailers) {
        Intrinsics.checkNotNullParameter(trailers, "trailers");
        synchronized (this) {
            if (this.sink.getFinished()) {
                throw new IllegalStateException("already finished");
            }
            if (0 == trailers.size()) {
                throw new IllegalArgumentException("trailers.size() == 0");
            }
            this.sink.setTrailers(trailers);
            final Unit unit = Unit.INSTANCE;
        }
    }

    public Timeout readTimeout() {
        return readTimeout;
    }

    public Timeout writeTimeout() {
        return writeTimeout;
    }

    public Source getSource() {
        return source;
    }

    public Sink getSink() {
        synchronized (this) {
            try {
                if (!hasResponseHeaders && !this.isLocallyInitiated()) {
                    throw new IllegalStateException("reply before requesting the sink");
                }
                final Unit unit = Unit.INSTANCE;
            } catch (final Throwable th) {
                throw th;
            }
        }
        return sink;
    }

    public void close(final ErrorCode rstStatusCode, final IOException iOException) throws IOException {
        Intrinsics.checkNotNullParameter(rstStatusCode, "rstStatusCode");
        if (this.closeInternal(rstStatusCode, iOException)) {
            connection.writeSynResetokhttp(f2049id, rstStatusCode);
        }
    }

    public void closeLater(final ErrorCode errorCode) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        if (this.closeInternal(errorCode, null)) {
            connection.writeSynResetLaterokhttp(f2049id, errorCode);
        }
    }

    public synchronized void receiveRstStream(final ErrorCode errorCode) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        if (null == this.errorCode) {
            this.errorCode = errorCode;
            this.notifyAll();
        }
    }

    public void waitForIookhttp() throws InterruptedException, InterruptedIOException {
        try {
            this.wait();
        } catch (final InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }

    private boolean closeInternal(final ErrorCode errorCode, final IOException iOException) {
        if (!Util.assertionsEnabled || !Thread.holdsLock(this)) {
            synchronized (this) {
                if (null != this.errorCode) {
                    return false;
                }
                if (this.source.getFinishedokhttp() && this.sink.getFinished()) {
                    return false;
                }
                this.errorCode = errorCode;
                this.errorException = iOException;
                this.notifyAll();
                final Unit unit = Unit.INSTANCE;
                connection.removeStreamokhttp(f2049id);
                return true;
            }
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
    }

    public void cancelStreamIfNecessaryokhttp() throws IOException {
        final boolean z;
        final boolean zIsOpen;
        if (!Util.assertionsEnabled || !Thread.holdsLock(this)) {
            synchronized (this) {
                try {
                    z = !this.source.getFinishedokhttp() && this.source.getClosedokhttp() && (this.sink.getFinished() || this.sink.getClosed());
                    zIsOpen = this.isOpen();
                    final Unit unit = Unit.INSTANCE;
                } catch (final Throwable th) {
                    throw th;
                }
            }
            if (z) {
                this.close(ErrorCode.CANCEL, null);
                return;
            } else {
                if (zIsOpen) {
                    return;
                }
                connection.removeStreamokhttp(f2049id);
                return;
            }
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
    }

    public void receiveData(final BufferedSource source, final int i2) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        if (!Util.assertionsEnabled || !Thread.holdsLock(this)) {
            this.source.receiveokhttp(source, i2);
            return;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
    }

    public void receiveHeaders(final Headers headers, final boolean z) {
        final boolean zIsOpen;
        Intrinsics.checkNotNullParameter(headers, "headers");
        if (!Util.assertionsEnabled || !Thread.holdsLock(this)) {
            synchronized (this) {
                try {
                    if (!hasResponseHeaders || !z) {
                        hasResponseHeaders = true;
                        headersQueue.add(headers);
                    } else {
                        this.source.setTrailers(headers);
                    }
                    if (z) {
                        this.source.setFinishedokhttp(true);
                    }
                    zIsOpen = this.isOpen();
                    this.notifyAll();
                    final Unit unit = Unit.INSTANCE;
                } catch (final Throwable th) {
                    throw th;
                }
            }
            if (zIsOpen) {
                return;
            }
            connection.removeStreamokhttp(f2049id);
            return;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
    }

    public void writeHeaders(final List<Header> responseHeaders, final boolean z, boolean z2) throws IOException {
        final boolean z3;
        Intrinsics.checkNotNullParameter(responseHeaders, "responseHeaders");
        if (!Util.assertionsEnabled || !Thread.holdsLock(this)) {
            synchronized (this) {
                try {
                    hasResponseHeaders = true;
                    if (z) {
                        this.sink.setFinished(true);
                    }
                    final Unit unit = Unit.INSTANCE;
                } catch (final Throwable th) {
                    throw th;
                }
            }
            if (!z2) {
                synchronized (connection) {
                    z3 = this.connection.getWriteBytesTotal() >= this.connection.getWriteBytesMaximum();
                }
                z2 = z3;
            }
            connection.writeHeadersokhttp(f2049id, z, responseHeaders);
            if (z2) {
                connection.flush();
                return;
            }
            return;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
    }

    public void addBytesToWriteWindow(final long j2) {
        writeBytesMaximum += j2;
        if (0 < j2) {
            this.notifyAll();
        }
    }

    public void checkOutNotClosedokhttp() throws IOException {
        if (sink.getClosed()) {
            throw new IOException("stream closed");
        }
        if (sink.getFinished()) {
            throw new IOException("stream finished");
        }
        if (null != this.errorCode) {
            final IOException iOException = errorException;
            if (null != iOException) {
                throw iOException;
            }
            final ErrorCode errorCode = this.errorCode;
            checkNotNull(errorCode);
            throw new StreamResetException(errorCode);
        }
    }

    /* compiled from: Http2Stream.kt */
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* compiled from: Http2Stream.kt */
    public final class FramingSource implements Source {
        final Http2Stream this0;
        private final long maxByteCount;
        private final Buffer readBuffer;
        private final Buffer receiveBuffer;
        private boolean closed;
        private boolean finished;
        private Headers trailers;

        public FramingSource(final Http2Stream this0, final long j2, final boolean z) {
            Intrinsics.checkNotNullParameter(this0, "this0");
            this.this0 = this0;
            maxByteCount = j2;
            finished = z;
            receiveBuffer = new Buffer();
            readBuffer = new Buffer();
        }

        public boolean getFinishedokhttp() {
            return finished;
        }

        public void setFinishedokhttp(final boolean z) {
            finished = z;
        }

        public Buffer getReceiveBuffer() {
            return receiveBuffer;
        }

        public Buffer getReadBuffer() {
            return readBuffer;
        }

        public Headers getTrailers() {
            return trailers;
        }

        public void setTrailers(final Headers headers) {
            trailers = headers;
        }

        public boolean getClosedokhttp() {
            return closed;
        }

        public void setClosedokhttp(final boolean z) {
            closed = z;
        }

        @Override // okio.Source
        public long read(final Buffer sink, final long j2) throws IOException {
            IOException errorExceptionokhttp;
            boolean z;
            long j3;
            Intrinsics.checkNotNullParameter(sink, "sink");
            long j4 = 0;
            if (0 > j2) {
                throw new IllegalArgumentException(stringPlus("byteCount < 0: ", Long.valueOf(j2)));
            }
            while (true) {
                final Http2Stream http2Stream = this0;
                synchronized (http2Stream) {
                    http2Stream.getReadTimeoutokhttp().enter();
                    if (null == http2Stream.getErrorCodeokhttp() || finished) {
                        errorExceptionokhttp = null;
                    } else {
                        errorExceptionokhttp = http2Stream.getErrorExceptionokhttp();
                        if (null == errorExceptionokhttp) {
                            ErrorCode errorCodeokhttp = http2Stream.getErrorCodeokhttp();
                            checkNotNull(errorCodeokhttp);
                            errorExceptionokhttp = new StreamResetException(errorCodeokhttp);
                        }
                    }
                    if (closed) {
                        throw new IOException("stream closed");
                    }
                    z = false;
                    if (readBuffer.size() > j4) {
                        j3 = readBuffer.read(sink, Math.min(j2, readBuffer.size()));
                        http2Stream.setReadBytesTotalokhttp(http2Stream.getReadBytesTotal() + j3);
                        long readBytesTotal = http2Stream.getReadBytesTotal() - http2Stream.getReadBytesAcknowledged();
                        if (null == errorExceptionokhttp && readBytesTotal >= http2Stream.getConnection().getOkHttpSettings().getInitialWindowSize() / 2) {
                            http2Stream.getConnection().writeWindowUpdateLaterokhttp(http2Stream.getId(), readBytesTotal);
                            http2Stream.setReadBytesAcknowledgedokhttp(http2Stream.getReadBytesTotal());
                        }
                    } else {
                        if (!finished && null == errorExceptionokhttp) {
                            try {
                                http2Stream.waitForIookhttp();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            z = true;
                        }
                        j3 = -1;
                    }
                    http2Stream.getReadTimeoutokhttp().exitAndThrowIfTimedOut();
                    Unit unit = Unit.INSTANCE;
                }
                if (!z) {
                    if (-1 != j3) {
                        this.updateConnectionFlowControl(j3);
                        return j3;
                    }
                    if (null == errorExceptionokhttp) {
                        return -1L;
                    }
                    throw errorExceptionokhttp;
                }
                j4 = 0;
            }
        }

        private void updateConnectionFlowControl(final long j2) {
            final Http2Stream http2Stream = this0;
            if (!Util.assertionsEnabled || !Thread.holdsLock(http2Stream)) {
                this0.getConnection().updateConnectionFlowControlokhttp(j2);
                return;
            }
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + http2Stream);
        }

        public void receiveokhttp(final BufferedSource source, long j2) throws IOException {
            boolean finishedokhttp;
            boolean z;
            long size;
            Intrinsics.checkNotNullParameter(source, "source");
            final Http2Stream http2Stream = this0;
            if (Util.assertionsEnabled && Thread.holdsLock(http2Stream)) {
                throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + http2Stream);
            }
            while (0 < j2) {
                synchronized (this0) {
                    finishedokhttp = this.finished;
                    z = this.readBuffer.size() + j2 > maxByteCount;
                    final Unit unit = Unit.INSTANCE;
                }
                if (z) {
                    source.skip(j2);
                    this0.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
                    return;
                }
                if (finishedokhttp) {
                    source.skip(j2);
                    return;
                }
                final long j3;
                try {
                    j3 = source.read(receiveBuffer, j2);
                } catch (DataFormatException e) {
                    throw new RuntimeException(e);
                }
                if (-1 == j3) {
                    throw new EOFException();
                }
                j2 -= j3;
                final Http2Stream http2Stream2 = this0;
                synchronized (http2Stream2) {
                    try {
                        if (this.closed) {
                            size = this.receiveBuffer.size();
                            this.receiveBuffer.clear();
                        } else {
                            final boolean z2 = 0 == readBuffer.size();
                            this.readBuffer.writeAll(this.receiveBuffer);
                            if (z2) {
                                http2Stream2.notifyAll();
                            }
                            size = 0;
                        }
                    } catch (final Throwable th) {
                        throw th;
                    }
                }
                if (0 < size) {
                    this.updateConnectionFlowControl(size);
                }
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this0.getReadTimeoutokhttp();
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            final long size;
            final Http2Stream http2Stream = this0;
            synchronized (http2Stream) {
                this.closed = true;
                size = this.readBuffer.size();
                this.readBuffer.clear();
                http2Stream.notifyAll();
                final Unit unit = Unit.INSTANCE;
            }
            if (0 < size) {
                this.updateConnectionFlowControl(size);
            }
            this0.cancelStreamIfNecessaryokhttp();
        }
    }

    /* compiled from: Http2Stream.kt */
    public final class FramingSink implements Sink {
        private final Buffer sendBuffer;
        private boolean closed;
        private boolean finished;
        private Headers trailers;

        public FramingSink(final Http2Stream this0, final boolean z) {
            Intrinsics.checkNotNullParameter(this0, "this0");
            Http2Stream.this = this0;
            finished = z;
            sendBuffer = new Buffer();
        }

        public FramingSink(final boolean z, final int i2, final DefaultConstructorMarker defaultConstructorMarker) {
            this(Http2Stream.this, 0 == (i2 & 1) && z);
        }

        public boolean getFinished() {
            return finished;
        }

        public void setFinished(final boolean z) {
            finished = z;
        }

        public Headers getTrailers() {
            return trailers;
        }

        public void setTrailers(final Headers headers) {
            trailers = headers;
        }

        public boolean getClosed() {
            return closed;
        }

        public void setClosed(final boolean z) {
            closed = z;
        }

        @Override // okio.Sink
        public void write(final Buffer source, final long j2) throws IOException {
            Intrinsics.checkNotNullParameter(source, "source");
            final Http2Stream http2Stream = Http2Stream.this;
            if (!Util.assertionsEnabled || !Thread.holdsLock(http2Stream)) {
                sendBuffer.write(source, j2);
                while (Http2Stream.EMIT_BUFFER_SIZE <= this.sendBuffer.size()) {
                    this.emitFrame(false);
                }
            } else {
                throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + http2Stream);
            }
        }

        private void emitFrame(final boolean z) throws IOException {
            final long jMin;
            final boolean z2;
            Http2Stream http2Stream = Http2Stream.this;
            synchronized (http2Stream) {
                try {
                    http2Stream.getWriteTimeoutokhttp().enter();
                    while (http2Stream.getWriteBytesTotal() >= http2Stream.getWriteBytesMaximum() && !this.finished && !this.closed && null == http2Stream.getErrorCodeokhttp()) {
                        try {
                            http2Stream.waitForIookhttp();
                        } finally {
                            http2Stream.getWriteTimeoutokhttp().exitAndThrowIfTimedOut();
                        }
                    }
                    http2Stream.getWriteTimeoutokhttp().exitAndThrowIfTimedOut();
                    http2Stream.checkOutNotClosedokhttp();
                    jMin = Math.min(http2Stream.getWriteBytesMaximum() - http2Stream.getWriteBytesTotal(), sendBuffer.size());
                    http2Stream.setWriteBytesTotalokhttp(http2Stream.getWriteBytesTotal() + jMin);
                    z2 = z && jMin == sendBuffer.size();
                    final Unit unit = Unit.INSTANCE;
                } catch (final Throwable th) {
                    try {
                        throw th;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            getWriteTimeoutokhttp().enter();
            try {
                getConnection().writeData(getId(), z2, sendBuffer, jMin);
            } finally {
                http2Stream = Http2Stream.this;
            }
        }
        public void flush() throws IOException {
            final Http2Stream http2Stream = Http2Stream.this;
            if (!Util.assertionsEnabled || !Thread.holdsLock(http2Stream)) {
                final Http2Stream http2Stream2 = Http2Stream.this;
                synchronized (http2Stream2) {
                    http2Stream2.checkOutNotClosedokhttp();
                    final Unit unit = Unit.INSTANCE;
                }
                while (0 < this.sendBuffer.size()) {
                    this.emitFrame(false);
                    getConnection().flush();
                }
                return;
            }
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + http2Stream);
        }
        public Timeout timeout() {
            return getWriteTimeoutokhttp();
        }
        public void close() throws IOException {
            final Http2Stream http2Stream = Http2Stream.this;
            if (!Util.assertionsEnabled || !Thread.holdsLock(http2Stream)) {
                final Http2Stream http2Stream2 = Http2Stream.this;
                synchronized (http2Stream2) {
                    if (this.closed) {
                        return;
                    }
                    final boolean z = null == http2Stream2.getErrorCodeokhttp();
                    final Unit unit = Unit.INSTANCE;
                    if (!getSinkokhttp().finished) {
                        final boolean z2 = 0 < this.sendBuffer.size();
                        if (null != this.trailers) {
                            while (0 < this.sendBuffer.size()) {
                                this.emitFrame(false);
                            }
                            final Http2Connection connection = getConnection();
                            final int id = getId();
                            final Headers headers = trailers;
                            checkNotNull(headers);
                            connection.writeHeadersokhttp(id, z, Util.toHeaderList(headers));
                        } else if (z2) {
                            while (0 < this.sendBuffer.size()) {
                                this.emitFrame(true);
                            }
                        } else if (z) {
                            getConnection().writeData(getId(), true, null, 0L);
                        }
                    }
                    synchronized (Http2Stream.this) {
                        this.closed = true;
                        final Unit unit2 = Unit.INSTANCE;
                    }
                    getConnection().flush();
                    cancelStreamIfNecessaryokhttp();
                    return;
                }
            }
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + http2Stream);
        }
    }
    public final class StreamTimeout extends AsyncTimeout {
        final Http2Stream this0;

        public StreamTimeout(final Http2Stream this0) {
            Intrinsics.checkNotNullParameter(this0, "this0");
            this.this0 = this0;
        }
        protected void timedOut() {
            this0.closeLater(ErrorCode.CANCEL);
            this0.getConnection().sendDegradedPingLaterokhttp();
        }
        protected IOException newTimeoutException(final IOException iOException) {
            final SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (null != iOException) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        public void exitAndThrowIfTimedOut() throws IOException {
            if (this.exit()) {
                throw this.newTimeoutException(null);
            }
        }
    }
}
