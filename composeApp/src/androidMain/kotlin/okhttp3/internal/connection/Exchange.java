package okhttp3.internal.connection;

import com.proje.mobilesales.features.sales.view.newadd.T;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.*;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.ws.RealWebSocket;
import okio.*;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.util.zip.DataFormatException;

public final class Exchange {
    private final RealCall call;
    private final ExchangeCodec codec;
    private final RealConnection connection;
    private final EventListener eventListener;
    private final ExchangeFinder finder;
    private boolean hasFailure;
    private boolean isDuplex;
    public Exchange(RealCall call, EventListener eventListener, ExchangeFinder finder, ExchangeCodec codec) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(eventListener, "eventListener");
        Intrinsics.checkNotNullParameter(finder, "finder");
        Intrinsics.checkNotNullParameter(codec, "codec");
        this.call = call;
        this.eventListener = eventListener;
        this.finder = finder;
        this.codec = codec;
        this.connection = codec.getConnection();
    }
    public Call<T> getCallokhttp() {
        return this.call;
    }
    public EventListener getEventListenerokhttp() {
        return this.eventListener;
    }
    public ExchangeFinder getFinderokhttp() {
        return this.finder;
    }
    public boolean isDuplexokhttp() {
        return this.isDuplex;
    }
    public boolean getHasFailureokhttp() {
        return this.hasFailure;
    }
    public RealConnection getConnectionokhttp() {
        return this.connection;
    }
    public boolean isCoalescedConnectionokhttp() {
        return !Intrinsics.areEqual(this.finder.getAddressokhttp().url().host(), this.connection.route().address().url().host());
    }
    public void writeRequestHeaders(Request request) throws IOException {
        Intrinsics.checkNotNullParameter(request, "request");
        try {
            this.eventListener.requestHeadersStart(this.call);
            this.codec.writeRequestHeaders(request);
            this.eventListener.requestHeadersEnd(this.call, request);
        } catch (IOException e2) {
            this.eventListener.requestFailed(this.call, e2);
            trackFailure(e2);
            throw e2;
        }
    }
    public Sink createRequestBody(Request request, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(request, "request");
        this.isDuplex = z;
        RequestBody requestBodyBody = request.body();
        Intrinsics.checkNotNull(requestBodyBody);
        long jContentLength = requestBodyBody.contentLength();
        this.eventListener.requestBodyStart(this.call);
        return new RequestBodySink(this, this.codec.createRequestBody(request, jContentLength), jContentLength);
    }
    public void flushRequest() throws IOException {
        try {
            this.codec.flushRequest();
        } catch (IOException e2) {
            this.eventListener.requestFailed(this.call, e2);
            trackFailure(e2);
            throw e2;
        }
    }
    public void finishRequest() throws IOException {
        try {
            this.codec.finishRequest();
        } catch (IOException e2) {
            this.eventListener.requestFailed(this.call, e2);
            trackFailure(e2);
            throw e2;
        }
    }
    public void responseHeadersStart() {
        this.eventListener.responseHeadersStart(this.call);
    }
    public Response.Builder readResponseHeaders(boolean z) throws IOException {
        try {
            Response.Builder responseHeaders = this.codec.readResponseHeaders(z);
            if (null != responseHeaders) {
                responseHeaders.initExchangeokhttp(this);
            }
            return responseHeaders;
        } catch (IOException e2) {
            this.eventListener.responseFailed(this.call, e2);
            trackFailure(e2);
            throw e2;
        }
    }
    public void responseHeadersEnd(Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        this.eventListener.responseHeadersEnd(this.call, response);
    }
    public ResponseBody openResponseBody(Response response) throws IOException {
        Intrinsics.checkNotNullParameter(response, "response");
        try {
            String strHeaderdefault = Response.headerdefault(response, HttpHeaders.CONTENT_TYPE, null, 2, null);
            long jReportedContentLength = this.codec.reportedContentLength(response);
            return new RealResponseBody(strHeaderdefault, jReportedContentLength, Okio.buffer(new ResponseBodySource(this, this.codec.openResponseBodySource(response), jReportedContentLength)));
        } catch (IOException e2) {
            this.eventListener.responseFailed(this.call, e2);
            trackFailure(e2);
            throw e2;
        }
    }
    public Headers trailers() throws IOException {
        return this.codec.trailers();
    }
    public RealWebSocket.Streams newWebSocketStreams() throws SocketException {
        this.call.timeoutEarlyExit();
        return this.codec.getConnection().newWebSocketStreamsokhttp(this);
    }
    public void webSocketUpgradeFailed() {
        bodyComplete(-1L, true, true, null);
    }
    public void noNewExchangesOnConnection() {
        this.codec.getConnection().noNewExchangesokhttp();
    }
    public void cancel() {
        this.codec.cancel();
    }
    public void detachWithViolence() {
        this.codec.cancel();
        this.call.messageDoneokhttp(this, true, true, null);
    }
    private void trackFailure(IOException iOException) {
        this.hasFailure = true;
        this.finder.trackFailure(iOException);
        this.codec.getConnection().trackFailureokhttp(this.call, iOException);
    }
    public <E extends IOException> E bodyComplete(long j2, boolean z, boolean z2, E e2) {
        if (null != e2) {
            trackFailure(e2);
        }
        if (z2) {
            if (null != e2) {
                this.eventListener.requestFailed(this.call, e2);
            } else {
                this.eventListener.requestBodyEnd(this.call, j2);
            }
        }
        if (z) {
            if (null != e2) {
                this.eventListener.responseFailed(this.call, e2);
            } else {
                this.eventListener.responseBodyEnd(this.call, j2);
            }
        }
        return this.call.messageDoneokhttp(this, z2, z, e2);
    }
    public void noRequestBody() {
        this.call.messageDoneokhttp(this, true, false, null);
    }
    private final class RequestBodySink extends ForwardingSink {
        final Exchange this0;
        private final long contentLength;
        private long bytesReceived;
        private boolean closed;
        private boolean completed;
        public RequestBodySink(Exchange this0, Sink delegate, long j2) {
            super(delegate);
            Intrinsics.checkNotNullParameter(this0, "this0");
            Intrinsics.checkNotNullParameter(delegate, "delegate");
            this.this0 = this0;
            this.contentLength = j2;
        }
        public void write(Buffer source, long j2) throws IOException {
            Intrinsics.checkNotNullParameter(source, "source");
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            long j3 = this.contentLength;
            if (-1 != j3 && this.bytesReceived + j2 > j3) {
                throw new ProtocolException("expected " + this.contentLength + " bytes but received " + (this.bytesReceived + j2));
            }
            try {
                super.write(source, j2);
                this.bytesReceived += j2;
            } catch (IOException e2) {
                throw complete(e2);
            }
        }
        public void flush() throws IOException {
            try {
                super.flush();
            } catch (IOException e2) {
                throw complete(e2);
            }
        }
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            this.closed = true;
            long j2 = this.contentLength;
            if (-1 != j2 && this.bytesReceived != j2) {
                throw new ProtocolException("unexpected end of stream");
            }
            try {
                super.close();
                complete(null);
            } catch (IOException e2) {
                throw complete(e2);
            }
        }

        private <E extends IOException> E complete(E e2) {
            if (this.completed) {
                return e2;
            }
            this.completed = true;
            return this.this0.bodyComplete(this.bytesReceived, false, true, e2);
        }
    }
    public final class ResponseBodySource extends ForwardingSource {
        private long bytesReceived;
        private boolean closed;
        private boolean completed;
        private final long contentLength;
        private boolean invokeStartEvent;
        final   Exchange this$0;
        public ResponseBodySource(Exchange this$0, Source delegate, long j2) {
            super(delegate);
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(delegate, "delegate");
            this.this$0 = this$0;
            this.contentLength = j2;
            this.invokeStartEvent = true;
            if (j2 == 0) {
                complete(null);
            }
        }
        public long read(Buffer sink, long j2) throws IOException {
            Intrinsics.checkNotNullParameter(sink, "sink");
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            try {
                long j3 = 0;
                try {
                    j3 = delegate().read(sink, j2);
                } catch (DataFormatException e) {
                    throw new RuntimeException(e);
                }
                if (this.invokeStartEvent) {
                    this.invokeStartEvent = false;
                    this.this$0.getEventListenerokhttp().responseBodyStart(this.this$0.getCallokhttp());
                }
                if (j3 == -1) {
                    complete(null);
                    return -1L;
                }
                long j4 = this.bytesReceived + j3;
                long j5 = this.contentLength;
                if (j5 != -1 && j4 > j5) {
                    throw new ProtocolException("expected " + this.contentLength + " bytes but received " + j4);
                }
                this.bytesReceived = j4;
                if (j4 == j5) {
                    complete(null);
                }
                return j3;
            } catch (IOException e2) {
                throw complete(e2);
            }
        }
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            this.closed = true;
            try {
                super.close();
                complete(null);
            } catch (IOException e2) {
                throw complete(e2);
            }
        }
        public  <E extends IOException> E complete(E e2) {
            if (this.completed) {
                return e2;
            }
            this.completed = true;
            if (e2 == null && this.invokeStartEvent) {
                this.invokeStartEvent = false;
                this.this$0.getEventListenerokhttp().responseBodyStart(this.this$0.getCallokhttp());
            }
            return this.this$0.bodyComplete(this.bytesReceived, true, false, e2);
        }
    }
}
