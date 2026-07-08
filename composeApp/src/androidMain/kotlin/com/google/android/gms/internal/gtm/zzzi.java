package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public abstract class zzzi extends zzyp {
    public static final int r8clinit = 0;
    private static final Logger zza = Logger.getLogger(zzzi.class.getName());
    
    public static final boolean zzb = zzaet.zzx();
    zzzj zze;

    private zzzi() {
        throw null;
    }

    zzzi(zzzh zzzh) {
    }

    static int zzA(zzadl zzadl, zzadx zzadx) {
        int zzQ = ((zzyh) zzadl).zzQ(zzadx);
        return zzC(zzQ) + zzQ;
    }

    public static int zzB(String str) {
        int i2;
        try {
            i2 = zzaew.zzc(str);
        } catch (zzaev unused) {
            i2 = str.getBytes(zzaco.zza).length;
        }
        return zzC(i2) + i2;
    }

    public static int zzC(int i2) {
        return (352 - (Integer.numberOfLeadingZeros(i2) * 9)) >>> 6;
    }

    public static int zzD(long j2) {
        return (640 - (Long.numberOfLeadingZeros(j2) * 9)) >>> 6;
    }

    @Deprecated
    static int zzy(int i2, zzadl zzadl, zzadx zzadx) {
        int zzC = zzC(i2 << 3);
        return zzC + zzC + ((zzyh) zzadl).zzQ(zzadx);
    }

    public static int zzz(zzadl zzadl) {
        int zzY = zzadl.zzY();
        return zzC(zzY) + zzY;
    }

    
    public final void zzE(String str, zzaev zzaev) throws IOException {
        zza.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzaev);
        byte[] bytes = str.getBytes(zzaco.zza);
        try {
            int length = bytes.length;
            zzu(length);
            zza(bytes, 0, length);
        } catch (IndexOutOfBoundsException e2) {
            throw new zzzf(e2);
        }
    }

    public abstract void zzI() throws IOException;

    public abstract void zzJ(byte b2) throws IOException;

    public abstract void zzK(int i2, boolean z) throws IOException;

    public abstract void zzL(int i2, zzyx zzyx) throws IOException;

    public abstract void zza(byte[] bArr, int i2, int i3) throws IOException;

    public abstract int zzb();

    public abstract void zzh(int i2, int i3) throws IOException;

    public abstract void zzi(int i2) throws IOException;

    public abstract void zzj(int i2, long j2) throws IOException;

    public abstract void zzk(long j2) throws IOException;

    public abstract void zzl(int i2, int i3) throws IOException;

    public abstract void zzm(int i2) throws IOException;

    
    public abstract void zzn(int i2, zzadl zzadl, zzadx zzadx) throws IOException;

    public abstract void zzo(int i2, zzadl zzadl) throws IOException;

    public abstract void zzp(int i2, zzyx zzyx) throws IOException;

    public abstract void zzq(int i2, String str) throws IOException;

    public abstract void zzs(int i2, int i3) throws IOException;

    public abstract void zzt(int i2, int i3) throws IOException;

    public abstract void zzu(int i2) throws IOException;

    public abstract void zzv(int i2, long j2) throws IOException;

    public abstract void zzw(long j2) throws IOException;
}
