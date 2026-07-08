package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzadp implements zzadx {
    private final zzadl zza;
    private final zzaem zzb;
    private final boolean zzc;
    private final zzabr zzd;

    private zzadp(final zzaem zzaem, final zzabr zzabr, final zzadl zzadl) {
        zzb = zzaem;
        zzc = zzadl instanceof zzacc;
        zzd = zzabr;
        zza = zzadl;
    }

    static zzadp zzc(final zzaem zzaem, final zzabr zzabr, final zzadl zzadl) {
        return new zzadp(zzaem, zzabr, zzadl);
    }

    public int zza(final Object obj) {
        final int zzb2 = ((zzacf) obj).zzc.zzb();
        return zzc ? zzb2 + ((zzacc) obj).zza.zzc() : zzb2;
    }

    public int zzb(final Object obj) {
        final int hashCode = ((zzacf) obj).zzc.hashCode();
        return zzc ? (hashCode * 53) + ((zzacc) obj).zza.zza.hashCode() : hashCode;
    }

    public Object zze() {
        final zzadl zzadl = zza;
        if (zzadl instanceof zzacf) {
            return ((zzacf) zzadl).zzae();
        }
        return zzadl.zzav().zzE();
    }

    public void zzf(final Object obj) {
        zzb.zzi(obj);
        zzd.zza(obj);
    }

    public void zzg(final Object obj, final Object obj2) {
        zzadz.zzq(zzb, obj, obj2);
        if (zzc) {
            zzadz.zzp(zzd, obj, obj2);
        }
    }

    public void zzh(final Object obj, final zzadw zzadw, final zzabq zzabq) throws IOException {
        boolean z;
        final zzaem zzaem = zzb;
        final Object zza2 = zzaem.zza(obj);
        final zzabv zzU = ((zzacc) obj).zzU();
        while (true) {
            if (Integer.MAX_VALUE == zzadw.zzc()) {
                break;
            }
            final int zzd2 = zzadw.zzd();
            final zzabr zzabr = zzd;
            if (11 != zzd2) {
                if (2 == (zzd2 & 7)) {
                    final zzace zzb2 = zzabq.zzb(zza, zzd2 >>> 3);
                    if (null != zzb2) {
                        zzabr.zzb(zzadw, zzb2, zzabq, zzU);
                    } else {
                        z = zzaem.zzk(zza2, zzadw, 0);
                    }
                } else {
                    z = zzadw.zzQ();
                }
                if (!z) {
                    break;
                }
            } else {
                zzace zzace = null;
                zzyx zzyx = null;
                int i2 = 0;
                while (true) {
                    try {
                        if (Integer.MAX_VALUE == zzadw.zzc()) {
                            break;
                        }
                        final int zzd3 = zzadw.zzd();
                        if (16 == zzd3) {
                            i2 = zzadw.zzj();
                            zzace = zzabq.zzb(zza, i2);
                        } else if (26 == zzd3) {
                            if (null != zzace) {
                                zzabr.zzb(zzadw, zzace, zzabq, zzU);
                            } else {
                                zzyx = zzadw.zzp();
                            }
                        } else if (!zzadw.zzQ()) {
                            break;
                        }
                    } catch (final Throwable th) {
                        zzaem.zzj(obj, zza2);
                        throw th;
                    }
                }
                if (12 != zzadw.zzd()) {
                    throw new zzacq("Protocol message end-group tag did not match expected tag.");
                } else if (null != zzyx) {
                    if (null != zzace) {
                        final zzadk zzav = zzace.zzc.zzav();
                        final zzzb zzH = zzzb.zzH(((zzyv) zzyx).zza, 0, zzyx.zzd(), true);
                        zzav.zzy(zzH, zzabq);
                        zzU.zzk(zzace.zzd, zzav.zzE());
                        zzH.zzz(0);
                    } else {
                        zzaem.zzg(zza2, i2, zzyx);
                    }
                }
            }
        }
        zzaem.zzj(obj, zza2);
    }

    public void zzi(final Object obj, final byte[] bArr, int i2, final int i3, final zzyl zzyl) throws IOException {
        final zzacf zzacf = (zzacf) obj;
        zzaen zzaen = zzacf.zzc;
        if (zzaen == com.google.android.gms.internal.gtm.zzaen.zzc()) {
            zzaen = com.google.android.gms.internal.gtm.zzaen.zzf();
            zzacf.zzc = zzaen;
        }
        final zzabv zzU = ((zzacc) obj).zzU();
        zzace zzace = null;
        while (i2 < i3) {
            int zzi = zzym.zzi(bArr, i2, zzyl);
            final int i4 = zzyl.zza;
            if (11 == i4) {
                int i5 = 0;
                zzyx zzyx = null;
                while (zzi < i3) {
                    zzi = zzym.zzi(bArr, zzi, zzyl);
                    final int i6 = zzyl.zza;
                    final int i7 = i6 >>> 3;
                    final int i8 = i6 & 7;
                    if (2 != i7) {
                        if (3 == i7) {
                            if (null != zzace) {
                                zzi = zzym.zzd(zzadt.zza().zzb(zzace.zzc.getClass()), bArr, zzi, i3, zzyl);
                                zzU.zzk(zzace.zzd, zzyl.zzc);
                            } else if (2 == i8) {
                                zzi = zzym.zza(bArr, zzi, zzyl);
                                zzyx = (zzyx) zzyl.zzc;
                            }
                        }
                    } else if (0 == i8) {
                        zzi = zzym.zzi(bArr, zzi, zzyl);
                        i5 = zzyl.zza;
                        zzace = zzyl.zzd.zzb(zza, i5);
                    }
                    if (12 == i6) {
                        break;
                    }
                    zzi = zzym.zzo(i6, bArr, zzi, i3, zzyl);
                }
                if (null != zzyx) {
                    zzaen.zzj((i5 << 3) | 2, zzyx);
                }
                i2 = zzi;
            } else if (2 == (i4 & 7)) {
                final zzace zzb2 = zzyl.zzd.zzb(zza, i4 >>> 3);
                if (null != zzb2) {
                    i2 = zzym.zzd(zzadt.zza().zzb(zzb2.zzc.getClass()), bArr, zzi, i3, zzyl);
                    zzU.zzk(zzb2.zzd, zzyl.zzc);
                } else {
                    i2 = zzym.zzh(i4, bArr, zzi, i3, zzaen, zzyl);
                }
                zzace = zzb2;
            } else {
                i2 = zzym.zzo(i4, bArr, zzi, i3, zzyl);
            }
        }
        if (i2 != i3) {
            throw new zzacq("Failed to parse the message.");
        }
    }

    public void zzj(final Object obj, final zzaez zzaez) throws IOException {
        final Iterator zzg = ((zzacc) obj).zza.zzg();
        while (zzg.hasNext()) {
            final Map.Entry entry = (Map.Entry) zzg.next();
            final zzabu zzabu = (zzabu) entry.getKey();
            if (zzaey.MESSAGE != zzabu.zze() || zzabu.zzg()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            zzabu.zzf();
            if (entry instanceof zzacs) {
                zzaez.zzw(zzabu.zza(), ((zzacs) entry).zza().zzb());
            } else {
                zzaez.zzw(zzabu.zza(), entry.getValue());
            }
        }
        ((zzacf) obj).zzc.zzk(zzaez);
    }

    public boolean zzk(final Object obj, final Object obj2) {
        if (!((zzacf) obj).zzc.equals(((zzacf) obj2).zzc)) {
            return false;
        }
        if (zzc) {
            return ((zzacc) obj).zza.equals(((zzacc) obj2).zza);
        }
        return true;
    }

    public boolean zzl(final Object obj) {
        return ((zzacc) obj).zza.zzm();
    }
}
