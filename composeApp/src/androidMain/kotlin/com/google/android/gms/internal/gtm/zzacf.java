package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public abstract class zzacf<MessageType extends zzacf<MessageType, BuilderType>, BuilderType extends zzaca<MessageType, BuilderType>> extends zzyh<MessageType, BuilderType> {
    private static final Map zza = new ConcurrentHashMap();
    protected zzaen zzc = zzaen.zzc();
    private int zzd = -1;

    public static zzace zzab(zzadl zzadl, zzadl zzadl2, zzaci zzaci, int i2, zzaex zzaex, boolean z, Class cls) {
        return new zzace(zzadl, zzadu.zze(), zzadl2, new zzacd(null, i2, zzaex, true, false), cls);
    }

    public static zzace zzac(zzadl zzadl, Object obj, zzadl zzadl2, zzaci zzaci, int i2, zzaex zzaex, Class cls) {
        return new zzace(zzadl, obj, zzadl2, new zzacd(zzaci, i2, zzaex, false, false), cls);
    }

    static zzacf zzad(Class cls) {
        Map map = zza;
        zzacf zzacf = (zzacf) map.get(cls);
        if (null == zzacf) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzacf = (zzacf) map.get(cls);
            } catch (ClassNotFoundException e2) {
                throw new IllegalStateException("Class initialization cannot fail.", e2);
            }
        }
        if (null == zzacf) {
            zzacf = (zzacf) ((zzacf) zzaet.zze(cls)).zzb(6, null, null);
            if (null != zzacf) {
                map.put(cls, zzacf);
            } else {
                throw new IllegalStateException();
            }
        }
        return zzacf;
    }

    protected static zzacf zzaf(zzacf zzacf, InputStream inputStream, zzabq zzabq) throws zzacq {
        zzyz zzyz = new zzyz(inputStream, 4096, null);
        zzacf zzae = zzacf.zzae();
        try {
            zzadx zzb = zzadt.zza().zzb(zzae.getClass());
            zzb.zzh(zzae, zzzc.zzq(zzyz), zzabq);
            zzb.zzf(zzae);
            zze(zzae);
            return zzae;
        } catch (zzacq e2) {
            if (e2.zzb()) {
                throw new zzacq(e2);
            }
            throw e2;
        } catch (zzael e3) {
            throw e3.zza();
        } catch (IOException e4) {
            if (e4.getCause() instanceof zzacq) {
                throw ((zzacq) e4.getCause());
            }
            throw new zzacq(e4);
        } catch (RuntimeException e5) {
            if (e5.getCause() instanceof zzacq) {
                throw ((zzacq) e5.getCause());
            }
            throw e5;
        }
    }

    protected static zzacf zzag(zzacf zzacf, byte[] bArr, zzabq zzabq) throws zzacq {
        zzacf zzf = zzf(zzacf, bArr, 0, bArr.length, zzabq);
        zze(zzf);
        return zzf;
    }

    protected static zzack zzah() {
        return zzacg.zzf();
    }

    protected static zzacn zzai() {
        return zzadu.zze();
    }

    protected static zzacn zzaj(zzacn zzacn) {
        int size = zzacn.size();
        return zzacn.zzd(size + size);
    }

    static Object zzak(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e2);
        } catch (InvocationTargetException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    protected static Object zzal(zzadl zzadl, String str, Object[] objArr) {
        return new zzadv(zzadl, str, objArr);
    }

    protected static void zzao(Class cls, zzacf zzacf) {
        zzacf.zzan();
        zza.put(cls, zzacf);
    }

    protected static final boolean zzaq(zzacf zzacf, boolean z) {
        byte byteValue = ((Byte) zzacf.zzb(1, null, null)).byteValue();
        if (1 == byteValue) {
            return true;
        }
        if (0 == byteValue) {
            return false;
        }
        boolean zzl = zzadt.zza().zzb(zzacf.getClass()).zzl(zzacf);
        if (z) {
            zzacf.zzb(2, !zzl ? null : zzacf, null);
        }
        return zzl;
    }

    private final int zzc(zzadx zzadx) {
        return zzadt.zza().zzb(getClass()).zza(this);
    }

    private static zzacf zze(zzacf zzacf) throws zzacq {
        if (null == zzacf || zzaq(zzacf, true)) {
            return zzacf;
        }
        throw new zzael(zzacf).zza();
    }

    private static zzacf zzf(zzacf zzacf, byte[] bArr, int i2, int i3, zzabq zzabq) throws zzacq {
        if (0 == i3) {
            return zzacf;
        }
        zzacf zzae = zzacf.zzae();
        try {
            zzadx zzb = zzadt.zza().zzb(zzae.getClass());
            zzb.zzi(zzae, bArr, 0, i3, new zzyl(zzabq));
            zzb.zzf(zzae);
            return zzae;
        } catch (zzacq e2) {
            if (e2.zzb()) {
                throw new zzacq(e2);
            }
            throw e2;
        } catch (zzael e3) {
            throw e3.zza();
        } catch (IOException e4) {
            if (e4.getCause() instanceof zzacq) {
                throw ((zzacq) e4.getCause());
            }
            throw new zzacq(e4);
        } catch (IndexOutOfBoundsException unused) {
            throw new zzacq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj || getClass() != obj.getClass()) {
            return false;
        }
        return zzadt.zza().zzb(getClass()).zzk(this, obj);
    }

    public final int hashCode() {
        if (zzar()) {
            return zzX();
        }
        int i2 = this.zzb;
        if (0 != i2) {
            return i2;
        }
        int zzX = zzX();
        this.zzb = zzX;
        return zzX;
    }

    public final String toString() {
        return zzadn.zza(this, super.toString());
    }

    
    public final int zzQ(zzadx zzadx) {
        if (zzar()) {
            int zza2 = zzadx.zza(this);
            if (0 <= zza2) {
                return zza2;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + zza2);
        }
        int i2 = this.zzd & Integer.MAX_VALUE;
        if (Integer.MAX_VALUE != i2) {
            return i2;
        }
        int zza3 = zzadx.zza(this);
        if (0 <= zza3) {
            this.zzd = (this.zzd & Integer.MIN_VALUE) | zza3;
            return zza3;
        }
        throw new IllegalStateException("serialized size must be non-negative, was " + zza3);
    }

    
    public final int zzX() {
        return zzadt.zza().zzb(getClass()).zzb(this);
    }

    
    public final zzaca zzZ() {
        return (zzaca) zzb(5, null, null);
    }

    public final zzaca zzaa() {
        zzaca zzaca = (zzaca) zzb(5, null, null);
        zzaca.zzA(this);
        return zzaca;
    }

    
    public final zzacf zzae() {
        return (zzacf) zzb(4, null, null);
    }

    
    public final void zzam() {
        zzadt.zza().zzb(getClass()).zzf(this);
        zzan();
    }

    
    public final void zzan() {
        this.zzd &= Integer.MAX_VALUE;
    }

    
    public final void zzap(int i2) {
        this.zzd = (this.zzd & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    
    public final boolean zzar() {
        return 0 != (zzd & Integer.MIN_VALUE);
    }

    public final zzadk zzav() {
        return (zzaca) zzb(5, null, null);
    }

    public final zzadk zzaw() {
        zzaca zzaca = (zzaca) zzb(5, null, null);
        zzaca.zzA(this);
        return zzaca;
    }

    public final void zzax(zzzi zzzi) throws IOException {
        zzadt.zza().zzb(getClass()).zzj(this, zzzj.zza(zzzi));
    }

    public final zzadl zzay() {
        return (zzacf) zzb(6, null, null);
    }

    public final boolean zzaz() {
        return zzaq(this, true);
    }

    
    public abstract Object zzb(int i2, Object obj, Object obj2);

    public final int zzY() {
        int i2;
        if (zzar()) {
            i2 = zzc(null);
            if (0 > i2) {
                throw new IllegalStateException("serialized size must be non-negative, was " + i2);
            }
        } else {
            i2 = this.zzd & Integer.MAX_VALUE;
            if (Integer.MAX_VALUE == i2) {
                i2 = zzc(null);
                if (0 <= i2) {
                    this.zzd = (this.zzd & Integer.MIN_VALUE) | i2;
                } else {
                    throw new IllegalStateException("serialized size must be non-negative, was " + i2);
                }
            }
        }
        return i2;
    }
}
