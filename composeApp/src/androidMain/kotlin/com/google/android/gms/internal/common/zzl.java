package com.google.android.gms.internal.common;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
final class zzl extends zzk {
    private final char zza;

    zzl(char c2) {
        this.zza = c2;
    }

    public String toString() {
        char[] cArr = {'\\', 'u', 0, 0, 0, 0};
        int i2 = this.zza;
        for (int i3 = 0; 4 > i3; i3++) {
            cArr[5 - i3] = "0123456789ABCDEF".charAt(i2 & 15);
            i2 >>= 4;
        }
        String copyValueOf = String.copyValueOf(cArr);
        return "CharMatcher.is('" + copyValueOf + "')";
    }

    public boolean zza(char c2) {
        return c2 == this.zza;
    }
}
