package rx.internal.producers;

import androidx.core.location.LocationRequestCompat;
import rx.Producer;

public final class ProducerArbiter implements Producer {
    static final Producer NULL_PRODUCER = new Producer() {
        public void request(long j2) {
        }
    };
    Producer currentProducer;
    boolean emitting;
    long missedProduced;
    Producer missedProducer;
    long missedRequested;
    long requested;
    public void request(long j2) {
        if (j2 < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        }
        if (j2 == 0) {
            return;
        }
        synchronized (this) {
            if (this.emitting) {
                this.missedRequested += j2;
                return;
            }
            this.emitting = true;
            try {
                long j3 = this.requested + j2;
                if (j3 < 0) {
                    j3 = LocationRequestCompat.PASSIVE_INTERVAL;
                }
                this.requested = j3;
                Producer producer = this.currentProducer;
                if (producer != null) {
                    producer.request(j2);
                }
                emitLoop();
            } catch (Throwable th) {
                synchronized (this) {
                    this.emitting = false;
                    throw th;
                }
            }
        }
    }
    public void setProducer(Producer producer) {
        synchronized (this) {
            if (this.emitting) {
                if (producer == null) {
                    producer = NULL_PRODUCER;
                }
                this.missedProducer = producer;
                return;
            }
            this.emitting = true;
            try {
                this.currentProducer = producer;
                if (producer != null) {
                    producer.request(this.requested);
                }
                emitLoop();
            } catch (Throwable th) {
                synchronized (this) {
                    this.emitting = false;
                    throw th;
                }
            }
        }
    }
    public void emitLoop() {
        while (true) {
            synchronized (this) {
                try {
                    long j2 = this.missedRequested;
                    long j3 = this.missedProduced;
                    Producer producer = this.missedProducer;
                    if (j2 == 0 && j3 == 0 && producer == null) {
                        this.emitting = false;
                        return;
                    }
                    this.missedRequested = 0L;
                    this.missedProduced = 0L;
                    this.missedProducer = null;
                    long j4 = this.requested;
                    if (j4 != LocationRequestCompat.PASSIVE_INTERVAL) {
                        long j5 = j4 + j2;
                        if (j5 < 0 || j5 == LocationRequestCompat.PASSIVE_INTERVAL) {
                            this.requested = LocationRequestCompat.PASSIVE_INTERVAL;
                            j4 = Long.MAX_VALUE;
                        } else {
                            j4 = j5 - j3;
                            if (j4 < 0) {
                                throw new IllegalStateException("more produced than requested");
                            }
                            this.requested = j4;
                        }
                    }
                    if (producer != null) {
                        if (producer == NULL_PRODUCER) {
                            this.currentProducer = null;
                        } else {
                            this.currentProducer = producer;
                            producer.request(j4);
                        }
                    } else {
                        Producer producer2 = this.currentProducer;
                        if (producer2 != null && j2 != 0) {
                            producer2.request(j2);
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }
}
