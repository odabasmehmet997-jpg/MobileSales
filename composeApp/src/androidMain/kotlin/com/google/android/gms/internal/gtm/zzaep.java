package com.google.android.gms.internal.gtm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzaep implements PrivilegedExceptionAction {
    zzaep() {
    }

    public Object run() throws Exception {
        final Class<Unsafe> cls = Unsafe.class;
        for (final Field field : cls.getDeclaredFields()) {
            field.setAccessible(true);
            final Object obj = field.get(null);
            if (cls.isInstance(obj)) {
                return cls.cast(obj);
            }
        }
        return null;
    }
}
