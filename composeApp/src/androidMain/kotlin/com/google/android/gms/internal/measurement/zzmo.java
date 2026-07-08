package com.google.android.gms.internal.measurement;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzmo implements PrivilegedExceptionAction {
    zzmo() {
    }

    public static Unsafe zza() throws Exception {
        final Class<Unsafe> cls = Unsafe.class;
        for (Field field : cls.getDeclaredFields()) {
            field.setAccessible(true);
            Object obj = field.get(null);
            if (cls.isInstance(obj)) {
                return cls.cast(obj);
            }
        }
        return null;
    }

    public Object run() throws Exception {
        return zza();
    }
}
