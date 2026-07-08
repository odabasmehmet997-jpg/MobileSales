package com.google.android.gms.internal.maps;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
enum zzbd {
    ;

    static void zza(final Object obj, final Object obj2) {
        if (null == obj) {
            throw new NullPointerException("null key in entry: null=" + obj2);
        } else if (null == obj2) {
            final String obj3 = obj.toString();
            throw new NullPointerException("null value in entry: " + obj3 + "=null");
        }
    }
}
