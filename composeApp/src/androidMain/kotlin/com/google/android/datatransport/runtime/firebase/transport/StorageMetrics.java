package com.google.android.datatransport.runtime.firebase.transport;
public final class StorageMetrics {
    private static final StorageMetrics DEFAULT_INSTANCE = new Builder().build();
    private final long current_cache_size_bytes_;
    private final long max_cache_size_bytes_;
    StorageMetrics(long j2, long j3) {
        this.current_cache_size_bytes_ = j2;
        this.max_cache_size_bytes_ = j3;
    }
    public static Builder newBuilder() {
        return new Builder();
    }
    public long getCurrentCacheSizeBytes() {
        return this.current_cache_size_bytes_;
    }
    public long getMaxCacheSizeBytes() {
        return this.max_cache_size_bytes_;
    }
    public static final class Builder {
        private long current_cache_size_bytes_ = 0;
        private long max_cache_size_bytes_ = 0;

        Builder() {}

        public StorageMetrics build() {
            return new StorageMetrics(this.current_cache_size_bytes_, this.max_cache_size_bytes_);
        }

        public Builder setCurrentCacheSizeBytes(long j2) {
            this.current_cache_size_bytes_ = j2;
            return this;
        }

        public Builder setMaxCacheSizeBytes(long j2) {
            this.max_cache_size_bytes_ = j2;
            return this;
        }
    }
}
