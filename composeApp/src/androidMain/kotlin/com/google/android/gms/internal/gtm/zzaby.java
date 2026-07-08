package com.google.android.gms.internal.gtm;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ServiceConfigurationError;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public abstract class zzaby {
    static zzabq zzb(final Class cls) {
        final String str;
        final Class<zzaby> cls2 = zzaby.class;
        final ClassLoader classLoader = cls2.getClassLoader();
        if (cls.equals(zzabq.class)) {
            str = "com.google.android.gms.internal.gtm.zzyn";
        } else if (cls.getPackage().equals(cls2.getPackage())) {
            str = String.format("%s.BlazeGenerated%sLoader", cls.getPackage().getName(), cls.getSimpleName());
        } else {
            throw new IllegalArgumentException(cls.getName());
        }
        try {
            return (zzabq) cls.cast(((zzaby) Class.forName(str, true, classLoader).getConstructor((Class[]) null).newInstance((Object[]) null)).zza());
        } catch (final NoSuchMethodException e2) {
            throw new IllegalStateException(e2);
        } catch (final InstantiationException e3) {
            throw new IllegalStateException(e3);
        } catch (final IllegalAccessException e4) {
            throw new IllegalStateException(e4);
        } catch (final InvocationTargetException e5) {
            throw new IllegalStateException(e5);
        } catch (final ClassNotFoundException unused) {
            final ArrayList arrayList = new ArrayList();
            for (final zzaby zza : Collections.singletonList(zzafb.class.getDeclaredConstructor((Class[]) null).newInstance((Object[]) null))) {
                try {
                    arrayList.add(cls.cast(zza.zza()));
                } catch (final ServiceConfigurationError e6) {
                    Logger.getLogger(zzzi.class.getName()).logp(Level.SEVERE, "com.google.protobuf.GeneratedExtensionRegistryLoader", "load", "Unable to load " + cls.getSimpleName(), e6);
                }
            }
            if (1 == arrayList.size()) {
                return (zzabq) arrayList.get(0);
            }
            if (0 == arrayList.size()) {
                return null;
            }
            try {
                return (zzabq) cls.getMethod("combine", new Class[]{Collection.class}).invoke((Object) null, new Object[]{arrayList});
            } catch (final NoSuchMethodException e7) {
                throw new IllegalStateException(e7);
            } catch (final IllegalAccessException e8) {
                throw new IllegalStateException(e8);
            } catch (final InvocationTargetException e9) {
                throw new IllegalStateException(e9);
            }
        }
    }

    
    public abstract zzabq zza();
}
