package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzyy extends zzzb {
    private final byte[] zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj = Integer.MAX_VALUE;

    zzyy(byte[] bArr, int i2, int i3, boolean z, zzza zzza) {
        super(null);
        this.zze = bArr;
        this.zzf = i3;
        this.zzh = 0;
    }

    private void zzJ() {
        int i2 = this.zzf + this.zzg;
        this.zzf = i2;
        int i3 = this.zzj;
        if (i2 > i3) {
            int i4 = i2 - i3;
            this.zzg = i4;
            this.zzf = i2 - i4;
            return;
        }
        this.zzg = 0;
    }

    public void zzA(int i2) {
        this.zzj = i2;
        zzJ();
    }

    public boolean zzC() throws IOException {
        return this.zzh == this.zzf;
    }

    public boolean zzD() throws IOException {
        return 0 != this.zzr();
    }

    public byte zza() throws IOException {
        int i2 = this.zzh;
        if (i2 != this.zzf) {
            byte[] bArr = this.zze;
            this.zzh = i2 + 1;
            return bArr[i2];
        }
        throw new zzacq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public double zzb() throws IOException {
        return Double.longBitsToDouble(zzq());
    }

    public float zzc() throws IOException {
        return Float.intBitsToFloat(zzi());
    }

    public int zzd() {
        return this.zzh;
    }

    public int zzf() throws IOException {
        return zzj();
    }

    public int zzg() throws IOException {
        return zzi();
    }

    public int zzh() throws IOException {
        return zzj();
    }

    public int zzi() throws IOException {
        int i2 = this.zzh;
        if (4 <= zzf - i2) {
            byte[] bArr = this.zze;
            this.zzh = i2 + 4;
            int i3 = (bArr[i2 + 1] & 255) << 8;
            return ((bArr[i2 + 3] & 255) << 24) | i3 | (bArr[i2] & 255) | ((bArr[i2 + 2] & 255) << 16);
        }
        throw new zzacq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public int zzk() throws IOException {
        return zzi();
    }

    public int zzl() throws IOException {
        return zzzb.zzF(zzj());
    }

    public int zzm() throws IOException {
        if (zzC()) {
            this.zzi = 0;
            return 0;
        }
        int zzj2 = zzj();
        this.zzi = zzj2;
        if (0 != (zzj2 >>> 3)) {
            return zzj2;
        }
        throw new zzacq("Protocol message contained an invalid tag (zero).");
    }

    public int zzn() throws IOException {
        return zzj();
    }

    public long zzo() throws IOException {
        return zzq();
    }

    public long zzp() throws IOException {
        return zzr();
    }

    public long zzq() throws IOException {
        int i2 = this.zzh;
        if (8 <= zzf - i2) {
            byte[] bArr = this.zze;
            this.zzh = i2 + 8;
            long j2 = bArr[i2 + 2];
            long j3 = bArr[i2 + 3];
            long j4 = bArr[i2 + 4];
            long j5 = bArr[i2 + 5];
            long j6 = bArr[i2 + 6];
            long j7 = (((long) bArr[i2]) & 255) | ((((long) bArr[i2 + 1]) & 255) << 8) | ((j2 & 255) << 16) | ((j3 & 255) << 24);
            return ((((long) bArr[i2 + 7]) & 255) << 56) | j7 | ((j4 & 255) << 32) | ((j5 & 255) << 40) | ((j6 & 255) << 48);
        }
        throw new zzacq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    
    public long zzs() throws IOException {
        long j2 = 0;
        for (int i2 = 0; 64 > i2; i2 += 7) {
            byte zza = zza();
            j2 |= ((long) (zza & Byte.MAX_VALUE)) << i2;
            if (0 == (zza & 128)) {
                return j2;
            }
        }
        throw new zzacq("CodedInputStream encountered a malformed varint.");
    }

    public long zzt() throws IOException {
        return zzq();
    }

    public long zzu() throws IOException {
        return zzzb.zzG(zzr());
    }

    public long zzv() throws IOException {
        return zzr();
    }

    public zzyx zzw() throws IOException {
        int zzj2 = zzj();
        if (0 < zzj2) {
            int i2 = this.zzf;
            int i3 = this.zzh;
            if (zzj2 <= i2 - i3) {
                zzyx zzj3 = zzyx.zzj(this.zze, i3, zzj2);
                this.zzh += zzj2;
                return zzj3;
            }
        }
        if (0 == zzj2) {
            return zzyx.zzb;
        }
        if (0 < zzj2) {
            int i4 = this.zzf;
            int i5 = this.zzh;
            if (zzj2 <= i4 - i5) {
                int i6 = zzj2 + i5;
                this.zzh = i6;
                return new zzyv(Arrays.copyOfRange(this.zze, i5, i6));
            }
        }
        if (0 >= zzj2) {
            throw new zzacq("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        throw new zzacq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public String zzx() throws IOException {
        int zzj2 = zzj();
        if (0 < zzj2) {
            int i2 = this.zzf;
            int i3 = this.zzh;
            if (zzj2 <= i2 - i3) {
                String str = new String(this.zze, i3, zzj2, zzaco.zza);
                this.zzh += zzj2;
                return str;
            }
        }
        if (0 == zzj2) {
            return "";
        }
        if (0 > zzj2) {
            throw new zzacq("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        throw new zzacq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public String zzy() throws IOException {
        int zzj2 = zzj();
        if (0 < zzj2) {
            int i2 = this.zzf;
            int i3 = this.zzh;
            if (zzj2 <= i2 - i3) {
                String zzd = zzaew.zzd(this.zze, i3, zzj2);
                this.zzh += zzj2;
                return zzd;
            }
        }
        if (0 == zzj2) {
            return "";
        }
        if (0 >= zzj2) {
            throw new zzacq("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        throw new zzacq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public void zzz(int i2) throws zzacq {
        if (this.zzi != i2) {
            throw new zzacq("Protocol message end-group tag did not match expected tag.");
        }
    }

    public int zze(int i2) throws zzacq {
        if (0 <= i2) {
            int i3 = i2 + this.zzh;
            if (0 <= i3) {
                int i4 = this.zzj;
                if (i3 <= i4) {
                    this.zzj = i3;
                    zzJ();
                    return i4;
                }
                throw new zzacq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            }
            throw new zzacq("Failed to parse the message.");
        }
        throw new zzacq("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    public void zzB(int i2) throws IOException {
        if (0 <= i2) {
            int i3 = this.zzf;
            int i4 = this.zzh;
            if (i2 <= i3 - i4) {
                this.zzh = i4 + i2;
                return;
            }
        }
        if (0 > i2) {
            throw new zzacq("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        throw new zzacq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public int zzj() throws IOException {
        byte b2;
        byte b3;
        int i2 = this.zzh;
        int i3 = this.zzf;
        if (i3 != i2) {
            byte[] bArr = this.zze;
            int i4 = i2 + 1;
            byte b4 = bArr[i2];
            if (0 <= b4) {
                this.zzh = i4;
                return b4;
            } else if (9 <= i3 - i4) {
                int i5 = i2 + 2;
                byte b5 = (bArr[i4] << 7) ^ b4;
                if (0 > b5) {
                    b2 = b5 ^ Byte.MIN_VALUE;
                } else {
                    int i6 = i2 + 3;
                    byte b6 = (bArr[i5] << 14) ^ b5;
                    if (0 <= b6) {
                        b3 = b6 ^ 16256;
                    } else {
                        int i7 = i2 + 4;
                        byte b7 = b6 ^ (bArr[i6] << 21);
                        if (0 > b7) {
                            b2 = -2080896 ^ b7;
                        } else {
                            i6 = i2 + 5;
                            byte b8 = bArr[i7];
                            byte b9 = (b7 ^ (b8 << 28)) ^ 266354560;
                            if (0 > b8) {
                                i7 = i2 + 6;
                                if (0 > bArr[i6]) {
                                    i6 = i2 + 7;
                                    if (0 > bArr[i7]) {
                                        i7 = i2 + 8;
                                        if (0 > bArr[i6]) {
                                            i6 = i2 + 9;
                                            if (0 > bArr[i7]) {
                                                int i8 = i2 + 10;
                                                if (0 <= bArr[i6]) {
                                                    byte b10 = b9;
                                                    i5 = i8;
                                                    b2 = b10;
                                                }
                                            }
                                        }
                                    }
                                }
                                b2 = b9;
                            }
                            b3 = b9;
                        }
                        i5 = i7;
                    }
                    i5 = i6;
                }
                this.zzh = i5;
                return b2;
            }
        }
        return (int) zzs();
    }

    public boolean zzE(int i2) throws IOException {
        int i3 = i2 & 7;
        int i4 = 0;
        if (0 == i3) {
            if (10 <= zzf - zzh) {
                while (10 > i4) {
                    byte[] bArr = this.zze;
                    int i5 = this.zzh;
                    this.zzh = i5 + 1;
                    if (0 > bArr[i5]) {
                        i4++;
                    }
                }
                throw new zzacq("CodedInputStream encountered a malformed varint.");
            }
            while (10 > i4) {
                if (0 > this.zza()) {
                    i4++;
                }
            }
            throw new zzacq("CodedInputStream encountered a malformed varint.");
            return true;
        } else if (1 == i3) {
            zzB(8);
            return true;
        } else if (2 == i3) {
            zzB(zzj());
            return true;
        } else if (3 == i3) {
            zzI();
            zzz(((i2 >>> 3) << 3) | 4);
            return true;
        } else if (4 == i3) {
            return false;
        } else {
            if (5 == i3) {
                zzB(4);
                return true;
            }
            throw new zzacp("Protocol message tag had invalid wire type.");
        }
    }

    public long zzr() throws IOException {
        long j2;
        long j3;
        long j4;
        int i2 = this.zzh;
        int i3 = this.zzf;
        if (i3 != i2) {
            byte[] bArr = this.zze;
            int i4 = i2 + 1;
            byte b2 = bArr[i2];
            if (0 <= b2) {
                this.zzh = i4;
                return b2;
            } else if (9 <= i3 - i4) {
                int i5 = i2 + 2;
                byte b3 = (bArr[i4] << 7) ^ b2;
                if (0 > b3) {
                    j2 = b3 ^ Byte.MIN_VALUE;
                } else {
                    int i6 = i2 + 3;
                    byte b4 = (bArr[i5] << 14) ^ b3;
                    if (0 <= b4) {
                        j2 = b4 ^ 16256;
                    } else {
                        int i7 = i2 + 4;
                        byte b5 = b4 ^ (bArr[i6] << 21);
                        if (0 > b5) {
                            i5 = i7;
                            j2 = -2080896 ^ b5;
                        } else {
                            i6 = i2 + 5;
                            long j5 = (((long) bArr[i7]) << 28) ^ b5;
                            if (0 <= j5) {
                                j2 = j5 ^ 266354560;
                            } else {
                                i5 = i2 + 6;
                                long j6 = (((long) bArr[i6]) << 35) ^ j5;
                                if (0 > j6) {
                                    j4 = -34093383808L;
                                } else {
                                    int i8 = i2 + 7;
                                    long j7 = j6 ^ (((long) bArr[i5]) << 42);
                                    if (0 <= j7) {
                                        j3 = j7 ^ 4363953127296L;
                                    } else {
                                        i5 = i2 + 8;
                                        j6 = j7 ^ (((long) bArr[i8]) << 49);
                                        if (0 > j6) {
                                            j4 = -558586000294016L;
                                        } else {
                                            i8 = i2 + 9;
                                            long j8 = (j6 ^ (((long) bArr[i5]) << 56)) ^ 71499008037633920L;
                                            if (0 > j8) {
                                                i5 = i2 + 10;
                                                if (0 <= ((long) bArr[i8])) {
                                                    j2 = j8;
                                                }
                                            } else {
                                                j3 = j8;
                                            }
                                        }
                                    }
                                    i5 = i8;
                                }
                                j2 = j6 ^ j4;
                            }
                        }
                    }
                    i5 = i6;
                }
                this.zzh = i5;
                return j2;
            }
        }
        return zzs();
    }
}
