package com.google.android.gms.tagmanager;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzfo extends Number implements Comparable {
    private double zza;
    private long zzb;
    private final boolean zzc = false;

    private zzfo(double d2) {
        this.zza = d2;
    }

    public static zzfo zzc(Double d2) {
        return new zzfo(d2.doubleValue());
    }

    public static zzfo zzd(long j2) {
        return new zzfo(j2);
    }

    public static com.google.android.gms.tagmanager.zzfo zze(java.lang.String r3) throws java.lang.NumberFormatException {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzfo.zze(java.lang.String):com.google.android.gms.tagmanager.zzfo");
    }

    public byte byteValue() {
        return (byte) ((int) zzb());
    }

    public double doubleValue() {
        return this.zzc ? (double) this.zzb : this.zza;
    }

    public boolean equals(Object obj) {
        return (obj instanceof zzfo) && compareTo((zzfo) obj) == 0;
    }

    public float floatValue() {
        return (float) doubleValue();
    }

    public int hashCode() {
        long zzb2 = zzb();
        return (int) (zzb2 ^ (zzb2 >>> 32));
    }

    public int intValue() {
        return (int) zzb();
    }

    public long longValue() {
        return zzb();
    }

    public short shortValue() {
        return (short) ((int) zzb());
    }

    public String toString() {
        return this.zzc ? Long.toString(this.zzb) : Double.toString(this.zza);
    }

    public long zzb() {
        return this.zzc ? this.zzb : (long) this.zza;
    }

    public boolean zzf() {
        return !this.zzc;
    }

    public boolean zzg() {
        return this.zzc;
    }

    private zzfo(long j2) {
        this.zzb = j2;
    }

    public int compareTo(zzfo zzfo) {
        if (!this.zzc || !zzfo.zzc) {
            return Double.compare(doubleValue(), zzfo.doubleValue());
        }
        return Long.compare(this.zzb, zzfo.zzb);
    }
}
