package com.google.android.gms.tagmanager;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
record zzay(String zza, byte[] zzb) {

    public String toString() {
        int hashCode = Arrays.hashCode(this.zzb);
        return "KeyAndSerialized: key = " + this.zza + " serialized hash = " + hashCode;
    }
}
