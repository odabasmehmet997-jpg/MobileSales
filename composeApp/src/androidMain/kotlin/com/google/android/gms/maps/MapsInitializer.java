package com.google.android.gms.maps;

import android.content.Context;
import androidx.annotation.Nullable;


public class MapsInitializer {
    private static final String zza = "MapsInitializer";
    private static final boolean zzb = false;
    private static final Renderer zzc = Renderer.LEGACY;
    public enum Renderer {
        LEGACY,
        LATEST
    }
    public static synchronized int initialize(final Context context) {
        final int initialize;
        synchronized (MapsInitializer.class) {
            initialize = MapsInitializer.initialize(context, null, null);
        }
        return initialize;
    }
    public static synchronized int initialize(final Context r5, @Nullable final Renderer r6, @Nullable final OnMapsSdkInitializedCallback r7) {
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.MapsInitializer.initialize(android.content.Context, com.google.android.gms.maps.MapsInitializerRenderer, com.google.android.gms.maps.OnMapsSdkInitializedCallback):int");
    }
}
