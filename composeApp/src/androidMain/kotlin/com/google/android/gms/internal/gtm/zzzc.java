package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzzc implements zzadw {
    private final zzzb zza;
    private int zzb;
    private int zzc;
    private int zzd;

    private zzzc(final zzzb zzzb) {
        final byte[] bArr = zzaco.zzb;
        zza = zzzb;
        zzzb.zzc = this;
    }

    private Object zzR(final zzadx zzadx, final zzabq zzabq) throws IOException {
        final Object zze = zzadx.zze();
        this.zzT(zze, zzadx, zzabq);
        zzadx.zzf(zze);
        return zze;
    }

    private Object zzS(final zzadx zzadx, final zzabq zzabq) throws IOException {
        final Object zze = zzadx.zze();
        this.zzU(zze, zzadx, zzabq);
        zzadx.zzf(zze);
        return zze;
    }

    private void zzT(final Object obj, final zzadx zzadx, final zzabq zzabq) throws IOException {
        final int i2 = zzc;
        zzc = ((zzb >>> 3) << 3) | 4;
        try {
            zzadx.zzh(obj, this, zzabq);
            if (zzb != zzc) {
                throw new zzacq("Failed to parse the message.");
            }
        } finally {
            zzc = i2;
        }
    }

    private void zzU(final Object obj, final zzadx zzadx, final zzabq zzabq) throws IOException {
        final zzzb zzzb = zza;
        final int zzn = zzzb.zzn();
        if (zzzb.zza < zzzb.zzb) {
            final int zze = zzzb.zze(zzn);
            zza.zza++;
            zzadx.zzh(obj, this, zzabq);
            zza.zzz(0);
            final zzzb zzzb2 = zza;
            zzzb2.zza--;
            zzzb2.zzA(zze);
            return;
        }
        throw new zzacq("Protocol message had too many levels of nesting.  May be malicious.  Use setRecursionLimit() to increase the recursion depth limit.");
    }

    private void zzV(final int i2) throws IOException {
        if (zza.zzd() != i2) {
            throw new zzacq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
    }

    private void zzW(final int i2) throws IOException {
        if ((zzb & 7) != i2) {
            throw new zzacp("Protocol message tag had invalid wire type.");
        }
    }

    private static void zzX(final int i2) throws IOException {
        if (0 != (i2 & 3)) {
            throw new zzacq("Failed to parse the message.");
        }
    }

    private static void zzY(final int i2) throws IOException {
        if (0 != (i2 & 7)) {
            throw new zzacq("Failed to parse the message.");
        }
    }

    public static zzzc zzq(final zzzb zzzb) {
        final zzzc zzzc = zzzb.zzc;
        return null != zzzc ? zzzc : new zzzc(zzzb);
    }

    public void zzA(final List list) throws IOException {
        int i2;
        int zzm;
        if (list instanceof zzacg zzacg) {
            final int i3 = zzb & 7;
            if (0 == i3) {
                do {
                    zzacg.zzh(zza.zzf());
                    if (!zza.zzC()) {
                        i2 = zza.zzm();
                    } else {
                        return;
                    }
                } while (i2 == zzb);
            } else if (2 == i3) {
                final zzzb zzzb = zza;
                final int zzd2 = zzzb.zzd() + zzzb.zzn();
                do {
                    zzacg.zzh(zza.zzf());
                } while (zza.zzd() < zzd2);
                this.zzV(zzd2);
                return;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        } else {
            final int i4 = zzb & 7;
            if (0 == i4) {
                do {
                    list.add(Integer.valueOf(zza.zzf()));
                    if (!zza.zzC()) {
                        zzm = zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == zzb);
                i2 = zzm;
            } else if (2 == i4) {
                final zzzb zzzb2 = zza;
                final int zzd3 = zzzb2.zzd() + zzzb2.zzn();
                do {
                    list.add(Integer.valueOf(zza.zzf()));
                } while (zza.zzd() < zzd3);
                this.zzV(zzd3);
                return;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        }
        zzd = i2;
    }

    public void zzB(final List list) throws IOException {
        int i2;
        int zzm;
        if (list instanceof zzacg zzacg) {
            final int i3 = zzb & 7;
            if (2 == i3) {
                final int zzn = zza.zzn();
                zzzc.zzX(zzn);
                final int zzd2 = zza.zzd() + zzn;
                do {
                    zzacg.zzh(zza.zzg());
                } while (zza.zzd() < zzd2);
                return;
            } else if (5 == i3) {
                do {
                    zzacg.zzh(zza.zzg());
                    if (!zza.zzC()) {
                        i2 = zza.zzm();
                    } else {
                        return;
                    }
                } while (i2 == zzb);
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        } else {
            final int i4 = zzb & 7;
            if (2 == i4) {
                final int zzn2 = zza.zzn();
                zzzc.zzX(zzn2);
                final int zzd3 = zza.zzd() + zzn2;
                do {
                    list.add(Integer.valueOf(zza.zzg()));
                } while (zza.zzd() < zzd3);
                return;
            } else if (5 == i4) {
                do {
                    list.add(Integer.valueOf(zza.zzg()));
                    if (!zza.zzC()) {
                        zzm = zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == zzb);
                i2 = zzm;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        }
        zzd = i2;
    }

    public void zzC(final List list) throws IOException {
        int i2;
        int zzm;
        if (list instanceof zzada zzada) {
            final int i3 = zzb & 7;
            if (1 == i3) {
                do {
                    zzada.zzf(zza.zzo());
                    if (!zza.zzC()) {
                        i2 = zza.zzm();
                    } else {
                        return;
                    }
                } while (i2 == zzb);
            } else if (2 == i3) {
                final int zzn = zza.zzn();
                zzzc.zzY(zzn);
                final int zzd2 = zzn + zza.zzd();
                do {
                    zzada.zzf(zza.zzo());
                } while (zza.zzd() < zzd2);
                return;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        } else {
            final int i4 = zzb & 7;
            if (1 == i4) {
                do {
                    list.add(Long.valueOf(zza.zzo()));
                    if (!zza.zzC()) {
                        zzm = zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == zzb);
                i2 = zzm;
            } else if (2 == i4) {
                final int zzn2 = zza.zzn();
                zzzc.zzY(zzn2);
                final int zzd3 = zzn2 + zza.zzd();
                do {
                    list.add(Long.valueOf(zza.zzo()));
                } while (zza.zzd() < zzd3);
                return;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        }
        zzd = i2;
    }

    public void zzD(final List list) throws IOException {
        int i2;
        int zzm;
        if (list instanceof zzabx zzabx) {
            final int i3 = zzb & 7;
            if (2 == i3) {
                final int zzn = zza.zzn();
                zzzc.zzX(zzn);
                final int zzd2 = zza.zzd() + zzn;
                do {
                    zzabx.zzf(zza.zzc());
                } while (zza.zzd() < zzd2);
                return;
            } else if (5 == i3) {
                do {
                    zzabx.zzf(zza.zzc());
                    if (!zza.zzC()) {
                        i2 = zza.zzm();
                    } else {
                        return;
                    }
                } while (i2 == zzb);
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        } else {
            final int i4 = zzb & 7;
            if (2 == i4) {
                final int zzn2 = zza.zzn();
                zzzc.zzX(zzn2);
                final int zzd3 = zza.zzd() + zzn2;
                do {
                    list.add(Float.valueOf(zza.zzc()));
                } while (zza.zzd() < zzd3);
                return;
            } else if (5 == i4) {
                do {
                    list.add(Float.valueOf(zza.zzc()));
                    if (!zza.zzC()) {
                        zzm = zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == zzb);
                i2 = zzm;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        }
        zzd = i2;
    }

    @Deprecated
    public void zzE(final List list, final zzadx zzadx, final zzabq zzabq) throws IOException {
        int zzm;
        final int i2 = zzb;
        if (3 == (i2 & 7)) {
            do {
                list.add(this.zzR(zzadx, zzabq));
                if (!zza.zzC() && 0 == this.zzd) {
                    zzm = zza.zzm();
                } else {
                    return;
                }
            } while (zzm == i2);
            zzd = zzm;
            return;
        }
        throw new zzacp("Protocol message tag had invalid wire type.");
    }

    public void zzF(final List list) throws IOException {
        int i2;
        int zzm;
        if (list instanceof zzacg zzacg) {
            final int i3 = zzb & 7;
            if (0 == i3) {
                do {
                    zzacg.zzh(zza.zzh());
                    if (!zza.zzC()) {
                        i2 = zza.zzm();
                    } else {
                        return;
                    }
                } while (i2 == zzb);
            } else if (2 == i3) {
                final zzzb zzzb = zza;
                final int zzd2 = zzzb.zzd() + zzzb.zzn();
                do {
                    zzacg.zzh(zza.zzh());
                } while (zza.zzd() < zzd2);
                this.zzV(zzd2);
                return;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        } else {
            final int i4 = zzb & 7;
            if (0 == i4) {
                do {
                    list.add(Integer.valueOf(zza.zzh()));
                    if (!zza.zzC()) {
                        zzm = zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == zzb);
                i2 = zzm;
            } else if (2 == i4) {
                final zzzb zzzb2 = zza;
                final int zzd3 = zzzb2.zzd() + zzzb2.zzn();
                do {
                    list.add(Integer.valueOf(zza.zzh()));
                } while (zza.zzd() < zzd3);
                this.zzV(zzd3);
                return;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        }
        zzd = i2;
    }

    public void zzG(final List list) throws IOException {
        int i2;
        int zzm;
        if (list instanceof zzada zzada) {
            final int i3 = zzb & 7;
            if (0 == i3) {
                do {
                    zzada.zzf(zza.zzp());
                    if (!zza.zzC()) {
                        i2 = zza.zzm();
                    } else {
                        return;
                    }
                } while (i2 == zzb);
            } else if (2 == i3) {
                final zzzb zzzb = zza;
                final int zzd2 = zzzb.zzd() + zzzb.zzn();
                do {
                    zzada.zzf(zza.zzp());
                } while (zza.zzd() < zzd2);
                this.zzV(zzd2);
                return;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        } else {
            final int i4 = zzb & 7;
            if (0 == i4) {
                do {
                    list.add(Long.valueOf(zza.zzp()));
                    if (!zza.zzC()) {
                        zzm = zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == zzb);
                i2 = zzm;
            } else if (2 == i4) {
                final zzzb zzzb2 = zza;
                final int zzd3 = zzzb2.zzd() + zzzb2.zzn();
                do {
                    list.add(Long.valueOf(zza.zzp()));
                } while (zza.zzd() < zzd3);
                this.zzV(zzd3);
                return;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        }
        zzd = i2;
    }

    public void zzH(final List list, final zzadx zzadx, final zzabq zzabq) throws IOException {
        int zzm;
        final int i2 = zzb;
        if (2 == (i2 & 7)) {
            do {
                list.add(this.zzS(zzadx, zzabq));
                if (!zza.zzC() && 0 == this.zzd) {
                    zzm = zza.zzm();
                } else {
                    return;
                }
            } while (zzm == i2);
            zzd = zzm;
            return;
        }
        throw new zzacp("Protocol message tag had invalid wire type.");
    }

    public void zzI(final List list) throws IOException {
        int i2;
        int zzm;
        if (list instanceof zzacg zzacg) {
            final int i3 = zzb & 7;
            if (2 == i3) {
                final int zzn = zza.zzn();
                zzzc.zzX(zzn);
                final int zzd2 = zza.zzd() + zzn;
                do {
                    zzacg.zzh(zza.zzk());
                } while (zza.zzd() < zzd2);
                return;
            } else if (5 == i3) {
                do {
                    zzacg.zzh(zza.zzk());
                    if (!zza.zzC()) {
                        i2 = zza.zzm();
                    } else {
                        return;
                    }
                } while (i2 == zzb);
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        } else {
            final int i4 = zzb & 7;
            if (2 == i4) {
                final int zzn2 = zza.zzn();
                zzzc.zzX(zzn2);
                final int zzd3 = zza.zzd() + zzn2;
                do {
                    list.add(Integer.valueOf(zza.zzk()));
                } while (zza.zzd() < zzd3);
                return;
            } else if (5 == i4) {
                do {
                    list.add(Integer.valueOf(zza.zzk()));
                    if (!zza.zzC()) {
                        zzm = zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == zzb);
                i2 = zzm;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        }
        zzd = i2;
    }

    public void zzJ(final List list) throws IOException {
        int i2;
        int zzm;
        if (list instanceof zzada zzada) {
            final int i3 = zzb & 7;
            if (1 == i3) {
                do {
                    zzada.zzf(zza.zzt());
                    if (!zza.zzC()) {
                        i2 = zza.zzm();
                    } else {
                        return;
                    }
                } while (i2 == zzb);
            } else if (2 == i3) {
                final int zzn = zza.zzn();
                zzzc.zzY(zzn);
                final int zzd2 = zzn + zza.zzd();
                do {
                    zzada.zzf(zza.zzt());
                } while (zza.zzd() < zzd2);
                return;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        } else {
            final int i4 = zzb & 7;
            if (1 == i4) {
                do {
                    list.add(Long.valueOf(zza.zzt()));
                    if (!zza.zzC()) {
                        zzm = zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == zzb);
                i2 = zzm;
            } else if (2 == i4) {
                final int zzn2 = zza.zzn();
                zzzc.zzY(zzn2);
                final int zzd3 = zzn2 + zza.zzd();
                do {
                    list.add(Long.valueOf(zza.zzt()));
                } while (zza.zzd() < zzd3);
                return;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        }
        zzd = i2;
    }

    public void zzK(final List list) throws IOException {
        int i2;
        int zzm;
        if (list instanceof zzacg zzacg) {
            final int i3 = zzb & 7;
            if (0 == i3) {
                do {
                    zzacg.zzh(zza.zzl());
                    if (!zza.zzC()) {
                        i2 = zza.zzm();
                    } else {
                        return;
                    }
                } while (i2 == zzb);
            } else if (2 == i3) {
                final zzzb zzzb = zza;
                final int zzd2 = zzzb.zzd() + zzzb.zzn();
                do {
                    zzacg.zzh(zza.zzl());
                } while (zza.zzd() < zzd2);
                this.zzV(zzd2);
                return;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        } else {
            final int i4 = zzb & 7;
            if (0 == i4) {
                do {
                    list.add(Integer.valueOf(zza.zzl()));
                    if (!zza.zzC()) {
                        zzm = zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == zzb);
                i2 = zzm;
            } else if (2 == i4) {
                final zzzb zzzb2 = zza;
                final int zzd3 = zzzb2.zzd() + zzzb2.zzn();
                do {
                    list.add(Integer.valueOf(zza.zzl()));
                } while (zza.zzd() < zzd3);
                this.zzV(zzd3);
                return;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        }
        zzd = i2;
    }

    public void zzL(final List list) throws IOException {
        int i2;
        int zzm;
        if (list instanceof zzada zzada) {
            final int i3 = zzb & 7;
            if (0 == i3) {
                do {
                    zzada.zzf(zza.zzu());
                    if (!zza.zzC()) {
                        i2 = zza.zzm();
                    } else {
                        return;
                    }
                } while (i2 == zzb);
            } else if (2 == i3) {
                final zzzb zzzb = zza;
                final int zzd2 = zzzb.zzd() + zzzb.zzn();
                do {
                    zzada.zzf(zza.zzu());
                } while (zza.zzd() < zzd2);
                this.zzV(zzd2);
                return;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        } else {
            final int i4 = zzb & 7;
            if (0 == i4) {
                do {
                    list.add(Long.valueOf(zza.zzu()));
                    if (!zza.zzC()) {
                        zzm = zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == zzb);
                i2 = zzm;
            } else if (2 == i4) {
                final zzzb zzzb2 = zza;
                final int zzd3 = zzzb2.zzd() + zzzb2.zzn();
                do {
                    list.add(Long.valueOf(zza.zzu()));
                } while (zza.zzd() < zzd3);
                this.zzV(zzd3);
                return;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        }
        zzd = i2;
    }

    public void zzM(final List list, final boolean z) throws IOException {
        int zzm;
        int i2;
        if (2 == (this.zzb & 7)) {
            if ((list instanceof zzacx zzacx) && !z) {
                do {
                    this.zzp();
                    zzacx.zza();
                    if (!zza.zzC()) {
                        i2 = zza.zzm();
                    } else {
                        return;
                    }
                } while (i2 == zzb);
            } else {
                do {
                    list.add(z ? this.zzu() : this.zzt());
                    if (!zza.zzC()) {
                        zzm = zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == zzb);
                i2 = zzm;
            }
            zzd = i2;
            return;
        }
        throw new zzacp("Protocol message tag had invalid wire type.");
    }

    public void zzN(final List list) throws IOException {
        int i2;
        int zzm;
        if (list instanceof zzacg zzacg) {
            final int i3 = zzb & 7;
            if (0 == i3) {
                do {
                    zzacg.zzh(zza.zzn());
                    if (!zza.zzC()) {
                        i2 = zza.zzm();
                    } else {
                        return;
                    }
                } while (i2 == zzb);
            } else if (2 == i3) {
                final zzzb zzzb = zza;
                final int zzd2 = zzzb.zzd() + zzzb.zzn();
                do {
                    zzacg.zzh(zza.zzn());
                } while (zza.zzd() < zzd2);
                this.zzV(zzd2);
                return;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        } else {
            final int i4 = zzb & 7;
            if (0 == i4) {
                do {
                    list.add(Integer.valueOf(zza.zzn()));
                    if (!zza.zzC()) {
                        zzm = zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == zzb);
                i2 = zzm;
            } else if (2 == i4) {
                final zzzb zzzb2 = zza;
                final int zzd3 = zzzb2.zzd() + zzzb2.zzn();
                do {
                    list.add(Integer.valueOf(zza.zzn()));
                } while (zza.zzd() < zzd3);
                this.zzV(zzd3);
                return;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        }
        zzd = i2;
    }

    public void zzO(final List list) throws IOException {
        int i2;
        int zzm;
        if (list instanceof zzada zzada) {
            final int i3 = zzb & 7;
            if (0 == i3) {
                do {
                    zzada.zzf(zza.zzv());
                    if (!zza.zzC()) {
                        i2 = zza.zzm();
                    } else {
                        return;
                    }
                } while (i2 == zzb);
            } else if (2 == i3) {
                final zzzb zzzb = zza;
                final int zzd2 = zzzb.zzd() + zzzb.zzn();
                do {
                    zzada.zzf(zza.zzv());
                } while (zza.zzd() < zzd2);
                this.zzV(zzd2);
                return;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        } else {
            final int i4 = zzb & 7;
            if (0 == i4) {
                do {
                    list.add(Long.valueOf(zza.zzv()));
                    if (!zza.zzC()) {
                        zzm = zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == zzb);
                i2 = zzm;
            } else if (2 == i4) {
                final zzzb zzzb2 = zza;
                final int zzd3 = zzzb2.zzd() + zzzb2.zzn();
                do {
                    list.add(Long.valueOf(zza.zzv()));
                } while (zza.zzd() < zzd3);
                this.zzV(zzd3);
                return;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        }
        zzd = i2;
    }

    public boolean zzP() throws IOException {
        this.zzW(0);
        return zza.zzD();
    }

    public boolean zzQ() throws IOException {
        final int i2;
        if (zza.zzC() || (i2 = zzb) == zzc) {
            return false;
        }
        return zza.zzE(i2);
    }

    public double zza() throws IOException {
        this.zzW(1);
        return zza.zzb();
    }

    public float zzb() throws IOException {
        this.zzW(5);
        return zza.zzc();
    }

    public int zzc() throws IOException {
        int i2 = zzd;
        if (0 != i2) {
            zzb = i2;
            zzd = 0;
        } else {
            i2 = zza.zzm();
            zzb = i2;
        }
        if (0 == i2 || i2 == zzc) {
            return Integer.MAX_VALUE;
        }
        return i2 >>> 3;
    }

    public int zzd() {
        return zzb;
    }

    public int zze() throws IOException {
        this.zzW(0);
        return zza.zzf();
    }

    public int zzf() throws IOException {
        this.zzW(5);
        return zza.zzg();
    }

    public int zzg() throws IOException {
        this.zzW(0);
        return zza.zzh();
    }

    public int zzh() throws IOException {
        this.zzW(5);
        return zza.zzk();
    }

    public int zzi() throws IOException {
        this.zzW(0);
        return zza.zzl();
    }

    public int zzj() throws IOException {
        this.zzW(0);
        return zza.zzn();
    }

    public long zzk() throws IOException {
        this.zzW(1);
        return zza.zzo();
    }

    public long zzl() throws IOException {
        this.zzW(0);
        return zza.zzp();
    }

    public long zzm() throws IOException {
        this.zzW(1);
        return zza.zzt();
    }

    public long zzn() throws IOException {
        this.zzW(0);
        return zza.zzu();
    }

    public long zzo() throws IOException {
        this.zzW(0);
        return zza.zzv();
    }

    public zzyx zzp() throws IOException {
        this.zzW(2);
        return zza.zzw();
    }

    @Deprecated
    public Object zzr(final Class cls, final zzabq zzabq) throws IOException {
        this.zzW(3);
        return this.zzR(zzadt.zza().zzb(cls), zzabq);
    }

    public Object zzs(final Class cls, final zzabq zzabq) throws IOException {
        this.zzW(2);
        return this.zzS(zzadt.zza().zzb(cls), zzabq);
    }

    public String zzt() throws IOException {
        this.zzW(2);
        return zza.zzx();
    }

    public String zzu() throws IOException {
        this.zzW(2);
        return zza.zzy();
    }

    public void zzv(final Object obj, final zzadx zzadx, final zzabq zzabq) throws IOException {
        this.zzW(3);
        this.zzT(obj, zzadx, zzabq);
    }

    public void zzw(final Object obj, final zzadx zzadx, final zzabq zzabq) throws IOException {
        this.zzW(2);
        this.zzU(obj, zzadx, zzabq);
    }

    public void zzx(final List list) throws IOException {
        int i2;
        int zzm;
        if (list instanceof zzyo zzyo) {
            final int i3 = zzb & 7;
            if (0 == i3) {
                do {
                    zzyo.zze(zza.zzD());
                    if (!zza.zzC()) {
                        i2 = zza.zzm();
                    } else {
                        return;
                    }
                } while (i2 == zzb);
            } else if (2 == i3) {
                final zzzb zzzb = zza;
                final int zzd2 = zzzb.zzd() + zzzb.zzn();
                do {
                    zzyo.zze(zza.zzD());
                } while (zza.zzd() < zzd2);
                this.zzV(zzd2);
                return;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        } else {
            final int i4 = zzb & 7;
            if (0 == i4) {
                do {
                    list.add(Boolean.valueOf(zza.zzD()));
                    if (!zza.zzC()) {
                        zzm = zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == zzb);
                i2 = zzm;
            } else if (2 == i4) {
                final zzzb zzzb2 = zza;
                final int zzd3 = zzzb2.zzd() + zzzb2.zzn();
                do {
                    list.add(Boolean.valueOf(zza.zzD()));
                } while (zza.zzd() < zzd3);
                this.zzV(zzd3);
                return;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        }
        zzd = i2;
    }

    public void zzy(final List list) throws IOException {
        int zzm;
        if (2 == (this.zzb & 7)) {
            do {
                list.add(this.zzp());
                if (!zza.zzC()) {
                    zzm = zza.zzm();
                } else {
                    return;
                }
            } while (zzm == zzb);
            zzd = zzm;
            return;
        }
        throw new zzacp("Protocol message tag had invalid wire type.");
    }

    public void zzz(final List list) throws IOException {
        int i2;
        int zzm;
        if (list instanceof zzabn zzabn) {
            final int i3 = zzb & 7;
            if (1 == i3) {
                do {
                    zzabn.zzf(zza.zzb());
                    if (!zza.zzC()) {
                        i2 = zza.zzm();
                    } else {
                        return;
                    }
                } while (i2 == zzb);
            } else if (2 == i3) {
                final int zzn = zza.zzn();
                zzzc.zzY(zzn);
                final int zzd2 = zzn + zza.zzd();
                do {
                    zzabn.zzf(zza.zzb());
                } while (zza.zzd() < zzd2);
                return;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        } else {
            final int i4 = zzb & 7;
            if (1 == i4) {
                do {
                    list.add(Double.valueOf(zza.zzb()));
                    if (!zza.zzC()) {
                        zzm = zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == zzb);
                i2 = zzm;
            } else if (2 == i4) {
                final int zzn2 = zza.zzn();
                zzzc.zzY(zzn2);
                final int zzd3 = zzn2 + zza.zzd();
                do {
                    list.add(Double.valueOf(zza.zzb()));
                } while (zza.zzd() < zzd3);
                return;
            } else {
                throw new zzacp("Protocol message tag had invalid wire type.");
            }
        }
        zzd = i2;
    }
}
