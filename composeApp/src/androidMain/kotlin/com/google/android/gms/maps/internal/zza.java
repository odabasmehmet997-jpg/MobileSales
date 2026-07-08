package com.google.android.gms.maps.internal;

import androidx.annotation.Nullable;

    public class zza {

        public static byte zza(@Nullable Boolean bool) {
        if (null != bool) {
            return !bool.booleanValue() ? (byte) 0 : 1;
        }
        return -1;
    }

    public static Boolean zzb(byte b2) {
        if (0 == b2) {
            return Boolean.FALSE;
        }
        if (1 != b2) {
            return null;
        }
        return Boolean.TRUE;
    }
}
