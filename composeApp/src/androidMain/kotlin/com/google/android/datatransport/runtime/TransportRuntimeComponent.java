package com.google.android.datatransport.runtime;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.BindsInstance;
import com.google.android.datatransport.runtime.dagger.Component;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import java.io.Closeable;
import java.io.IOException;
import javax.inject.Singleton;
abstract class TransportRuntimeComponent implements Closeable {

    interface Builder {
        TransportRuntimeComponent build();
        Builder setApplicationContext(Context context);
    }
    abstract EventStore getEventStore();
    abstract TransportRuntime getTransportRuntime();
    TransportRuntimeComponent() {
    }
    public void close() throws IOException {
        getEventStore().close();
    }
}
