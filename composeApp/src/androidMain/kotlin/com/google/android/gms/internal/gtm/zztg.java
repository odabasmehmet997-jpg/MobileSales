package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
enum zztg {
    ;

    static void zza(Object obj, Object obj2) {
        if (null == obj) {
            throw new NullPointerException("null key in entry: null=" + obj2);
        } else if (null == obj2) {
            String obj3 = obj.toString();
            throw new NullPointerException("null value in entry: " + obj3 + "=null");
        }
    }
}
