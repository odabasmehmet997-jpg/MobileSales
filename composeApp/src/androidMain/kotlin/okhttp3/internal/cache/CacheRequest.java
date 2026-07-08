package okhttp3.internal.cache;

import okio.Sink;

import java.io.IOException;

public interface CacheRequest {
    void abort();
    Sink body() throws IOException;
}
