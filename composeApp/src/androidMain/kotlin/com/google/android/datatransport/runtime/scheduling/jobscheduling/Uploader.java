package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.runtime.EncodedPayload;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.backends.BackendRequest;
import com.google.android.datatransport.runtime.backends.BackendResponse;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics;
import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.scheduling.persistence.ClientHealthMetricsStore;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.PersistedEvent;
import com.google.android.datatransport.runtime.synchronization.SynchronizationException;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.android.datatransport.runtime.time.Monotonic;
import com.google.android.datatransport.runtime.time.WallTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;

public class Uploader {
    private final BackendRegistry backendRegistry;
    private final ClientHealthMetricsStore clientHealthMetricsStore;
    private final Clock clock;
    private final Context context;
    private final EventStore eventStore;
    private final Executor executor;
    private final SynchronizationGuard guard;
    private final Clock uptimeClock;
    private final WorkScheduler workScheduler;
    public Uploader(Context context, BackendRegistry backendRegistry, EventStore eventStore, WorkScheduler workScheduler, Executor executor, SynchronizationGuard synchronizationGuard, @WallTime Clock clock, @Monotonic Clock clock2, ClientHealthMetricsStore clientHealthMetricsStore) {
        this.context = context;
        this.backendRegistry = backendRegistry;
        this.eventStore = eventStore;
        this.workScheduler = workScheduler;
        this.executor = executor;
        this.guard = synchronizationGuard;
        this.clock = clock;
        this.uptimeClock = clock2;
        this.clientHealthMetricsStore = clientHealthMetricsStore;
    }
    boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public void upload(final TransportContext transportContext, final int i2, final Runnable runnable) {
        this.executor.execute(new Runnable() {
            private Uploader f0;

            public void run() {
                this.f0.lambdaupload1(transportContext, i2, runnable);
            }
        });
    }
    public void lambdaupload1(final TransportContext transportContext, final int i2, Runnable runnable) {
        try {
            try {
                SynchronizationGuard synchronizationGuard = this.guard;
                final EventStore eventStore = this.eventStore;
                Objects.requireNonNull(eventStore);
                synchronizationGuard.runCriticalSection(new SynchronizationGuard.CriticalSection() {
                    public Object execute() {
                        return Integer.valueOf(eventStore.cleanUp());
                    }
                });
                if (!isNetworkAvailable()) {
                    this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() {
                        private Uploader f0;

                        public Object execute() {
                            return this.f0.lambdaupload0(transportContext, i2);
                        }
                    });
                } else {
                    logAndUpdateState(transportContext, i2);
                }
            } catch (SynchronizationException unused) {
                this.workScheduler.schedule(transportContext, i2 + 1);
            }
            runnable.run();
        } catch (Throwable th) {
            runnable.run();
            throw th;
        }
    }
    public Object lambdaupload0(TransportContext transportContext, int i2) {
        this.workScheduler.schedule(transportContext, i2 + 1);
        return null;
    }
    void logAndUpdateState(final TransportContext transportContext, int i2) {
        BackendResponse backendResponseSend;
        TransportBackend transportBackend = this.backendRegistry.get(transportContext.getBackendName());
        long jMax = 0;
        while (true) {
            final long j2 = jMax;
            while (((Boolean) this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() {
                private Uploader f0;

                public Object execute() {
                    return this.f0.lambdalogAndUpdateState2(transportContext);
                }
            })).booleanValue()) {
                final Iterable iterable = (Iterable) this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() {
                    private Uploader f0;

                    public Object execute() {
                        return this.f0.lambdalogAndUpdateState3(transportContext);
                    }
                });
                if (!iterable.iterator().hasNext()) {
                    return;
                }
                if (transportBackend == null) {
                    Logging.d("Uploader", "Unknown backend for %s, deleting event batch for it...", transportContext);
                    backendResponseSend = BackendResponse.fatalError();
                } else {
                    ArrayList arrayList = new ArrayList();
                    Iterator it = iterable.iterator();
                    while (it.hasNext()) {
                        arrayList.add(((PersistedEvent) it.next()).getEvent());
                    }
                    if (transportContext.shouldUploadClientHealthMetrics()) {
                        arrayList.add(createMetricsEvent(transportBackend));
                    }
                    backendResponseSend = transportBackend.send(BackendRequest.builder().setEvents(arrayList).setExtras(transportContext.getExtras()).build());
                }
                if (backendResponseSend.getStatus() == BackendResponse.Status.TRANSIENT_ERROR) {
                    this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() {
                        private Uploader f0;

                        public Object execute() {
                            return this.f0.lambdalogAndUpdateState4(iterable, transportContext, j2);
                        }
                    });
                    this.workScheduler.schedule(transportContext, i2 + 1, true);
                    return;
                }
                this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() {
                    private Uploader f0;

                    public Object execute() {
                        return this.f0.lambdalogAndUpdateState5(iterable);
                    }
                });
                if (backendResponseSend.getStatus() == BackendResponse.Status.OK) {
                    jMax = Math.max(j2, backendResponseSend.getNextRequestWaitMillis());
                    if (transportContext.shouldUploadClientHealthMetrics()) {
                        this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() {
                            private Uploader f0;

                            public Object execute() {
                                return this.f0.lambdalogAndUpdateState6();
                            }
                        });
                    }
                } else if (backendResponseSend.getStatus() == BackendResponse.Status.INVALID_PAYLOAD) {
                    final HashMap map = new HashMap();
                    Iterator it2 = iterable.iterator();
                    while (it2.hasNext()) {
                        String transportName = ((PersistedEvent) it2.next()).getEvent().getTransportName();
                        if (!map.containsKey(transportName)) {
                            map.put(transportName, 1);
                        } else {
                            map.put(transportName, Integer.valueOf(((Integer) map.get(transportName)).intValue() + 1));
                        }
                    }
                    this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() {
                        private Uploader f0;

                        public Object execute() {
                            return this.f0.lambdalogAndUpdateState7(map);
                        }
                    });
                }
            }
            this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() {
                private Uploader f0;

                public Object execute() {
                    return this.f0.lambdalogAndUpdateState8(transportContext, j2);
                }
            });
            return;
        }
    }
    public Object lambdalogAndUpdateState8(TransportContext transportContext, long j2) {
        Iterable<PersistedEvent> iterable = null;
        this.eventStore.recordFailure(iterable);
        this.eventStore.recordNextCallTime(transportContext, this.clock.getTime() + j2);
        return null;
    }
    public Boolean lambdalogAndUpdateState2(TransportContext transportContext) {
        return Boolean.valueOf(this.eventStore.hasPendingEventsFor(transportContext));
    }
    public Iterable lambdalogAndUpdateState3(TransportContext transportContext) {
        return this.eventStore.loadBatch(transportContext);
    }
    public Object lambdalogAndUpdateState4(Iterable iterable, TransportContext transportContext, long j2) {
        this.eventStore.recordFailure(iterable);
        this.eventStore.recordNextCallTime(transportContext, this.clock.getTime() + j2);
        return null;
    }
    public Object lambdalogAndUpdateState5(Iterable iterable) {
        this.eventStore.recordSuccess(iterable);
        return null;
    }
    public Object lambdalogAndUpdateState6() {
        this.clientHealthMetricsStore.resetClientMetrics();
        return null;
    }
    public Object lambdalogAndUpdateState7(Map map) {
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            this.clientHealthMetricsStore.recordLogEventDropped(((Integer) entry.getValue()).intValue(), LogEventDropped.Reason.INVALID_PAYLOD, (String) entry.getKey());
        }
        return null;
    }
    public EventInternal createMetricsEvent(TransportBackend transportBackend) {
        SynchronizationGuard synchronizationGuard = this.guard;
        final ClientHealthMetricsStore clientHealthMetricsStore = this.clientHealthMetricsStore;
        Objects.requireNonNull(clientHealthMetricsStore);
        return transportBackend.decorate(EventInternal.builder().setEventMillis(this.clock.getTime()).setUptimeMillis(this.uptimeClock.getTime()).setTransportName("GDT_CLIENT_METRICS").setEncodedPayload(new EncodedPayload(Encoding.of("proto"), ((ClientMetrics) synchronizationGuard.runCriticalSection(new SynchronizationGuard.CriticalSection() { 
            public Object execute() {
                return clientHealthMetricsStore.loadClientMetrics();
            }
        })).toByteArray())).build());
    }
}
