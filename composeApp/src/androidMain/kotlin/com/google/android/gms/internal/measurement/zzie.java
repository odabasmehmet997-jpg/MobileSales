package com.google.android.gms.internal.measurement;

import java.io.Serializable;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzie(Object zza) implements Serializable, zzib {

    public boolean equals(Object obj) {
        if (!(obj instanceof zzie)) {
            return false;
        }
        Object obj2 = this.zza;
        Object obj3 = ((zzie) obj).zza;
        return obj2 == obj3 || obj2.equals(obj3);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza});
    }

    public String toString() {
        String obj = this.zza.toString();
        final String sb = "Suppliers.ofInstance(" +
                obj +
                ")";
        return sb;
    }
}
