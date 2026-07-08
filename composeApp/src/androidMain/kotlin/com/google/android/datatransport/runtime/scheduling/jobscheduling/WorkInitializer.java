package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.Iterator;
import java.util.concurrent.Executor;

public class WorkInitializer {
    private final Executor executor;
    private final SynchronizationGuard guard;
    private final WorkScheduler scheduler;
    private final EventStore store;
    WorkInitializer(Executor executor, EventStore eventStore, WorkScheduler workScheduler, SynchronizationGuard synchronizationGuard) {
        this.executor = executor;
        this.store = eventStore;
        this.scheduler = workScheduler;
        this.guard = synchronizationGuard;
    }
    public void ensureContextsScheduled() {
        this.executor.execute(new Runnable() {
            public void run() {
                lambdaensureContextsScheduled1();
            }
        });
    }
    public  void lambdaensureContextsScheduled1() {
        this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() {
            public Object execute() {
                return lambdaensureContextsScheduled0();
            }
        });
    }
    public  Object lambdaensureContextsScheduled0() {
        Iterator<TransportContext> it = this.store.loadActiveContexts().iterator();
        while (it.hasNext()) {
            this.scheduler.schedule(it.next(), 1);
        }
        return null;
    }
}
