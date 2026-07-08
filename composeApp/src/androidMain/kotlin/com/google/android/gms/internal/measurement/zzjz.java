package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
public abstract class zzjz<MessageType extends zzjz<MessageType, BuilderType>, BuilderType extends zzjv<MessageType, BuilderType>> extends zzih<MessageType, BuilderType> {
    private static final Map zza = new ConcurrentHashMap();
    protected zzmj zzc = zzmj.zzc();
    protected int zzd = -1;

    static Object zzbC(final Method method, final Object obj, final Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (final IllegalAccessException e2) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e2);
        } catch (final InvocationTargetException e3) {
            final Throwable cause = e3.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    protected static Object zzbD(final zzlg zzlg, final String str, final Object[] objArr) {
        return new zzlq(zzlg, str, objArr);
    }

    protected static void zzbE(final Class cls, final zzjz zzjz) {
        zza.put(cls, zzjz);
    }

    static zzjz zzbu(final Class cls) {
        final Map map = zzjz.zza;
        zzjz zzjz = (zzjz) map.get(cls);
        if (null == zzjz) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzjz = (zzjz) map.get(cls);
            } catch (final ClassNotFoundException e2) {
                throw new IllegalStateException("Class initialization cannot fail.", e2);
            }
        }
        if (null == zzjz) {
            zzjz = (zzjz) ((zzjz) zzms.zze(cls)).zzl(6, null, null);
            if (null != zzjz) {
                map.put(cls, zzjz);
            } else {
                throw new IllegalStateException();
            }
        }
        return zzjz;
    }

    protected static zzke zzbv() {
        return zzka.zzf();
    }

    protected static zzkf zzbw() {
        return zzkv.zzf();
    }

    protected static zzkf zzbx(final zzkf zzkf) {
        final int size = zzkf.size();
        return zzkf.zze(0 == size ? 10 : size + size);
    }

    protected static zzkg zzby() {
        return zzlp.zze();
    }

    protected static zzkg zzbz(final zzkg zzkg) {
        final int size = zzkg.size();
        return zzkg.zzd(0 == size ? 10 : size + size);
    }

    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (null != obj && this.getClass() == obj.getClass()) {
            return zzlo.zza().zzb(this.getClass()).zzi(this, obj);
        }
        return false;
    }

    public final int hashCode() {
        final int i2 = zzb;
        if (0 != i2) {
            return i2;
        }
        final int zzb = zzlo.zza().zzb(this.getClass()).zzb(this);
        this.zzb = zzb;
        return zzb;
    }

    public final String toString() {
        return zzli.zza(this, super.toString());
    }

    public final zzlf zzbA() {
        return (zzjv) this.zzl(5, null, null);
    }

    public final zzlf zzbB() {
        final zzjv zzjv = (zzjv) this.zzl(5, null, null);
        zzjv.zzaw(this);
        return zzjv;
    }

    public final void zzbF(final zzjg zzjg) throws IOException {
        zzlo.zza().zzb(this.getClass()).zzm(this, zzjh.zza(zzjg));
    }

    public final zzlg zzbJ() {
        return (zzjz) this.zzl(6, null, null);
    }

    
    public final int zzbm() {
        return zzd;
    }

    
    public final void zzbp(final int i2) {
        zzd = i2;
    }

    public final int zzbr() {
        final int i2 = zzd;
        if (-1 != i2) {
            return i2;
        }
        final int zza2 = zzlo.zza().zzb(this.getClass()).zza(this);
        zzd = zza2;
        return zza2;
    }

    
    public final zzjv zzbs() {
        return (zzjv) this.zzl(5, null, null);
    }

    public final zzjv zzbt() {
        final zzjv zzjv = (zzjv) this.zzl(5, null, null);
        zzjv.zzaw(this);
        return zzjv;
    }

    
    public abstract Object zzl(int i2, Object obj, Object obj2);
}
