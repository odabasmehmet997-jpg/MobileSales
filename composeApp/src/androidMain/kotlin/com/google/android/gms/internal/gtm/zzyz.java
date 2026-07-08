package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzyz extends zzzb {
    private final InputStream zze;
    private final byte[] zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl = Integer.MAX_VALUE;

    zzyz(final InputStream inputStream, final int i2, final zzza zzza) {
        super(null);
        final byte[] bArr = zzaco.zzb;
        zze = inputStream;
        zzf = new byte[4096];
        zzg = 0;
        zzi = 0;
        zzk = 0;
    }

    private List zzJ(int i2) throws IOException {
        final ArrayList arrayList = new ArrayList();
        while (0 < i2) {
            final int min = Math.min(i2, 4096);
            final byte[] bArr = new byte[min];
            int i3 = 0;
            while (i3 < min) {
                final int read = zze.read(bArr, i3, min - i3);
                if (-1 != read) {
                    zzk += read;
                    i3 += read;
                } else {
                    throw new zzacq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                }
            }
            i2 -= min;
            arrayList.add(bArr);
        }
        return arrayList;
    }

    private void zzK() {
        final int i2 = zzg + zzh;
        zzg = i2;
        final int i3 = zzk + i2;
        final int i4 = zzl;
        if (i3 > i4) {
            final int i5 = i3 - i4;
            zzh = i5;
            zzg = i2 - i5;
            return;
        }
        zzh = 0;
    }

    private void zzL(final int i2) throws IOException {
        if (this.zzM(i2)) {
            return;
        }
        if (i2 > (Integer.MAX_VALUE - zzk) - zzi) {
            throw new zzacq("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
        }
        throw new zzacq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    private boolean zzM(final int i2) throws IOException {
        final int i3 = zzi;
        final int i4 = i3 + i2;
        int i5 = zzg;
        if (i4 > i5) {
            int i6 = zzk;
            if (i2 > (Integer.MAX_VALUE - i6) - i3 || i6 + i3 + i2 > zzl) {
                return false;
            }
            if (0 < i3) {
                if (i5 > i3) {
                    final byte[] bArr = zzf;
                    System.arraycopy(bArr, i3, bArr, 0, i5 - i3);
                }
                i6 = zzk + i3;
                zzk = i6;
                i5 = zzg - i3;
                zzg = i5;
                zzi = 0;
            }
            try {
                final int read = zze.read(zzf, i5, Math.min(4096 - i5, (Integer.MAX_VALUE - i6) - i5));
                if (0 == read || -1 > read || 4096 < read) {
                    throw new IllegalStateException(zze.getClass() + "#read(byte[]) returned invalid result: " + read + "\nThe InputStream implementation is buggy.");
                } else if (0 >= read) {
                    return false;
                } else {
                    zzg += read;
                    this.zzK();
                    if (zzg >= i2) {
                        return true;
                    }
                    return this.zzM(i2);
                }
            } catch (final zzacq e2) {
                e2.zza();
                throw e2;
            }
        } else {
            throw new IllegalStateException("refillBuffer() called when " + i2 + " bytes were already available in buffer");
        }
    }

    private byte[] zzN(final int i2, final boolean z) throws IOException {
        final byte[] zzO = this.zzO(i2);
        if (null != zzO) {
            return zzO;
        }
        final int i3 = zzi;
        final int i4 = zzg;
        int i5 = i4 - i3;
        zzk += i4;
        zzi = 0;
        zzg = 0;
        final List<byte[]> zzJ = this.zzJ(i2 - i5);
        final byte[] bArr = new byte[i2];
        System.arraycopy(zzf, i3, bArr, 0, i5);
        for (final byte[] bArr2 : zzJ) {
            final int length = bArr2.length;
            System.arraycopy(bArr2, 0, bArr, i5, length);
            i5 += length;
        }
        return bArr;
    }

    private byte[] zzO(final int i2) throws IOException {
        if (0 == i2) {
            return zzaco.zzb;
        }
        final int i3 = zzk;
        final int i4 = zzi;
        final int i5 = i3 + i4 + i2;
        if (0 >= -2147483647 + i5) {
            final int i6 = zzl;
            if (i5 <= i6) {
                int i7 = zzg - i4;
                final int i8 = i2 - i7;
                if (4096 <= i8) {
                    try {
                        if (i8 > zze.available()) {
                            return null;
                        }
                    } catch (final zzacq e2) {
                        e2.zza();
                        throw e2;
                    }
                }
                final byte[] bArr = new byte[i2];
                System.arraycopy(zzf, zzi, bArr, 0, i7);
                zzk += zzg;
                zzi = 0;
                zzg = 0;
                while (i7 < i2) {
                    try {
                        final int read = zze.read(bArr, i7, i2 - i7);
                        if (-1 != read) {
                            zzk += read;
                            i7 += read;
                        } else {
                            throw new zzacq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                        }
                    } catch (final zzacq e3) {
                        e3.zza();
                        throw e3;
                    }
                }
                return bArr;
            }
            this.zzB((i6 - i3) - i4);
            throw new zzacq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        throw new zzacq("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
    }

    public void zzA(final int i2) {
        zzl = i2;
        this.zzK();
    }

    public void zzB(final int i2) throws IOException {
        final int i3 = zzg;
        final int i4 = zzi;
        int i5 = i3 - i4;
        if (i2 <= i5 && 0 <= i2) {
            zzi = i4 + i2;
        } else if (0 <= i2) {
            final int i6 = zzk;
            final int i7 = i6 + i4;
            final int i8 = zzl;
            if (i7 + i2 <= i8) {
                zzk = i7;
                zzg = 0;
                zzi = 0;
                while (i5 < i2) {
                    try {
                        final long j2 = i2 - i5;
                        final long skip = zze.skip(j2);
                        final int i9 = (0 < skip ? 1 : (0 == skip ? 0 : -1));
                        if (0 > i9 || skip > j2) {
                            throw new IllegalStateException(zze.getClass() + "#skip returned invalid result: " + skip + "\nThe InputStream implementation is buggy.");
                        } else if (0 == i9) {
                            break;
                        } else {
                            i5 += (int) skip;
                        }
                    } catch (final zzacq e2) {
                        e2.zza();
                        throw e2;
                    } catch (final Throwable th) {
                        zzk += i5;
                        this.zzK();
                        throw th;
                    }
                }
                zzk += i5;
                this.zzK();
                if (i5 < i2) {
                    final int i10 = zzg;
                    int i11 = i10 - zzi;
                    zzi = i10;
                    this.zzL(1);
                    while (true) {
                        final int i12 = i2 - i11;
                        final int i13 = zzg;
                        if (i12 > i13) {
                            i11 += i13;
                            zzi = i13;
                            this.zzL(1);
                        } else {
                            zzi = i12;
                            return;
                        }
                    }
                }
            } else {
                this.zzB((i8 - i6) - i4);
                throw new zzacq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            }
        } else {
            throw new zzacq("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
    }

    public boolean zzC() throws IOException {
        return zzi == zzg && !this.zzM(1);
    }

    public boolean zzD() throws IOException {
        return 0 != zzr();
    }

    public byte zza() throws IOException {
        if (zzi == zzg) {
            this.zzL(1);
        }
        final byte[] bArr = zzf;
        final int i2 = zzi;
        zzi = i2 + 1;
        return bArr[i2];
    }

    public double zzb() throws IOException {
        return Double.longBitsToDouble(this.zzq());
    }

    public float zzc() throws IOException {
        return Float.intBitsToFloat(this.zzi());
    }

    public int zzd() {
        return zzk + zzi;
    }

    public int zzf() throws IOException {
        return this.zzj();
    }

    public int zzg() throws IOException {
        return this.zzi();
    }

    public int zzh() throws IOException {
        return this.zzj();
    }

    public int zzi() throws IOException {
        int i2 = zzi;
        if (4 > this.zzg - i2) {
            this.zzL(4);
            i2 = zzi;
        }
        final byte[] bArr = zzf;
        zzi = i2 + 4;
        final int i3 = (bArr[i2 + 1] & 255) << 8;
        return ((bArr[i2 + 3] & 255) << 24) | i3 | (bArr[i2] & 255) | ((bArr[i2 + 2] & 255) << 16);
    }

    public int zzk() throws IOException {
        return this.zzi();
    }

    public int zzl() throws IOException {
        return zzF(this.zzj());
    }

    public int zzm() throws IOException {
        if (this.zzC()) {
            zzj = 0;
            return 0;
        }
        final int zzj2 = this.zzj();
        zzj = zzj2;
        if (0 != (zzj2 >>> 3)) {
            return zzj2;
        }
        throw new zzacq("Protocol message contained an invalid tag (zero).");
    }

    public int zzn() throws IOException {
        return this.zzj();
    }

    public long zzo() throws IOException {
        return this.zzq();
    }

    public long zzp() throws IOException {
        return this.zzr();
    }

    public long zzq() throws IOException {
        int i2 = zzi;
        if (8 > this.zzg - i2) {
            this.zzL(8);
            i2 = zzi;
        }
        final byte[] bArr = zzf;
        zzi = i2 + 8;
        final long j2 = bArr[i2 + 2];
        final long j3 = bArr[i2 + 3];
        final long j4 = bArr[i2 + 4];
        final long j5 = bArr[i2 + 5];
        final long j6 = bArr[i2 + 6];
        final long j7 = (((long) bArr[i2]) & 255) | ((((long) bArr[i2 + 1]) & 255) << 8) | ((j2 & 255) << 16) | ((j3 & 255) << 24);
        return ((((long) bArr[i2 + 7]) & 255) << 56) | j7 | ((j4 & 255) << 32) | ((j5 & 255) << 40) | ((j6 & 255) << 48);
    }

    
    public long zzs() throws IOException {
        long j2 = 0;
        for (int i2 = 0; 64 > i2; i2 += 7) {
            final byte zza = this.zza();
            j2 |= ((long) (zza & Byte.MAX_VALUE)) << i2;
            if (0 == (zza & 128)) {
                return j2;
            }
        }
        throw new zzacq("CodedInputStream encountered a malformed varint.");
    }

    public long zzt() throws IOException {
        return this.zzq();
    }

    public long zzu() throws IOException {
        return zzG(this.zzr());
    }

    public long zzv() throws IOException {
        return this.zzr();
    }

    public zzyx zzw() throws IOException {
        final int zzj2 = this.zzj();
        final int i2 = zzg;
        final int i3 = zzi;
        if (zzj2 <= i2 - i3 && 0 < zzj2) {
            final zzyx zzj3 = zzyx.zzj(zzf, i3, zzj2);
            zzi += zzj2;
            return zzj3;
        } else if (0 == zzj2) {
            return zzyx.zzb;
        } else {
            if (0 <= zzj2) {
                final byte[] zzO = this.zzO(zzj2);
                if (null != zzO) {
                    return zzyx.zzj(zzO, 0, zzO.length);
                }
                final int i4 = zzi;
                final int i5 = zzg;
                int i6 = i5 - i4;
                zzk += i5;
                zzi = 0;
                zzg = 0;
                final List<byte[]> zzJ = this.zzJ(zzj2 - i6);
                final byte[] bArr = new byte[zzj2];
                System.arraycopy(zzf, i4, bArr, 0, i6);
                for (final byte[] bArr2 : zzJ) {
                    final int length = bArr2.length;
                    System.arraycopy(bArr2, 0, bArr, i6, length);
                    i6 += length;
                }
                return new zzyv(bArr);
            }
            throw new zzacq("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
    }

    public String zzx() throws IOException {
        final int zzj2 = this.zzj();
        if (0 < zzj2) {
            final int i2 = zzg;
            final int i3 = zzi;
            if (zzj2 <= i2 - i3) {
                final String str = new String(zzf, i3, zzj2, zzaco.zza);
                zzi += zzj2;
                return str;
            }
        }
        if (0 == zzj2) {
            return "";
        }
        if (0 > zzj2) {
            throw new zzacq("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        } else if (zzj2 > zzg) {
            return new String(this.zzN(zzj2, false), zzaco.zza);
        } else {
            this.zzL(zzj2);
            final String str2 = new String(zzf, zzi, zzj2, zzaco.zza);
            zzi += zzj2;
            return str2;
        }
    }

    public String zzy() throws IOException {
        final byte[] bArr;
        final int zzj2 = this.zzj();
        int i2 = zzi;
        final int i3 = zzg;
        if (zzj2 <= i3 - i2 && 0 < zzj2) {
            bArr = zzf;
            zzi = i2 + zzj2;
        } else if (0 == zzj2) {
            return "";
        } else {
            if (0 <= zzj2) {
                i2 = 0;
                if (zzj2 <= i3) {
                    this.zzL(zzj2);
                    bArr = zzf;
                    zzi = zzj2;
                } else {
                    bArr = this.zzN(zzj2, false);
                }
            } else {
                throw new zzacq("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
        }
        return zzaew.zzd(bArr, i2, zzj2);
    }

    public void zzz(final int i2) throws zzacq {
        if (zzj != i2) {
            throw new zzacq("Protocol message end-group tag did not match expected tag.");
        }
    }

    public int zze(final int i2) throws zzacq {
        if (0 <= i2) {
            final int i3 = i2 + zzk + zzi;
            if (0 <= i3) {
                final int i4 = zzl;
                if (i3 <= i4) {
                    zzl = i3;
                    this.zzK();
                    return i4;
                }
                throw new zzacq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            }
            throw new zzacq("Failed to parse the message.");
        }
        throw new zzacq("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    public int zzj() throws IOException {
        byte b2;
        final byte b3;
        final int i2 = zzi;
        final int i3 = zzg;
        if (i3 != i2) {
            final byte[] bArr = zzf;
            final int i4 = i2 + 1;
            final byte b4 = bArr[i2];
            if (0 <= b4) {
                zzi = i4;
                return b4;
            } else if (9 <= i3 - i4) {
                int i5 = i2 + 2;
                final byte b5 = (bArr[i4] << 7) ^ b4;
                if (0 > b5) {
                    b2 = b5 ^ Byte.MIN_VALUE;
                } else {
                    int i6 = i2 + 3;
                    final byte b6 = (bArr[i5] << 14) ^ b5;
                    if (0 <= b6) {
                        b3 = b6 ^ 16256;
                    } else {
                        int i7 = i2 + 4;
                        final byte b7 = b6 ^ (bArr[i6] << 21);
                        if (0 > b7) {
                            b2 = -2080896 ^ b7;
                        } else {
                            i6 = i2 + 5;
                            final byte b8 = bArr[i7];
                            final byte b9 = (b7 ^ (b8 << 28)) ^ 266354560;
                            if (0 > b8) {
                                i7 = i2 + 6;
                                if (0 > bArr[i6]) {
                                    i6 = i2 + 7;
                                    if (0 > bArr[i7]) {
                                        i7 = i2 + 8;
                                        if (0 > bArr[i6]) {
                                            i6 = i2 + 9;
                                            if (0 > bArr[i7]) {
                                                final int i8 = i2 + 10;
                                                if (0 <= bArr[i6]) {
                                                    final byte b10 = b9;
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
                zzi = i5;
                return b2;
            }
        }
        return (int) this.zzs();
    }

    public boolean zzE(final int i2) throws IOException {
        final int i3 = i2 & 7;
        int i4 = 0;
        if (0 == i3) {
            if (10 <= this.zzg - this.zzi) {
                while (10 > i4) {
                    final byte[] bArr = zzf;
                    final int i5 = zzi;
                    zzi = i5 + 1;
                    if (0 > bArr[i5]) {
                        i4++;
                    }
                }
                throw new zzacq("CodedInputStream encountered a malformed varint.");
            }
            while (10 > i4) {
                if (0 > zza()) {
                    i4++;
                }
            }
            throw new zzacq("CodedInputStream encountered a malformed varint.");
            return true;
        } else if (1 == i3) {
            this.zzB(8);
            return true;
        } else if (2 == i3) {
            this.zzB(this.zzj());
            return true;
        } else if (3 == i3) {
            this.zzI();
            this.zzz(((i2 >>> 3) << 3) | 4);
            return true;
        } else if (4 == i3) {
            return false;
        } else {
            if (5 == i3) {
                this.zzB(4);
                return true;
            }
            throw new zzacp("Protocol message tag had invalid wire type.");
        }
    }

    public long zzr() throws IOException {
        long j2;
        final long j3;
        long j4;
        final int i2 = zzi;
        final int i3 = zzg;
        if (i3 != i2) {
            final byte[] bArr = zzf;
            final int i4 = i2 + 1;
            final byte b2 = bArr[i2];
            if (0 <= b2) {
                zzi = i4;
                return b2;
            } else if (9 <= i3 - i4) {
                int i5 = i2 + 2;
                final byte b3 = (bArr[i4] << 7) ^ b2;
                if (0 > b3) {
                    j2 = b3 ^ Byte.MIN_VALUE;
                } else {
                    int i6 = i2 + 3;
                    final byte b4 = (bArr[i5] << 14) ^ b3;
                    if (0 <= b4) {
                        j2 = b4 ^ 16256;
                    } else {
                        final int i7 = i2 + 4;
                        final byte b5 = b4 ^ (bArr[i6] << 21);
                        if (0 > b5) {
                            i5 = i7;
                            j2 = -2080896 ^ b5;
                        } else {
                            i6 = i2 + 5;
                            final long j5 = (((long) bArr[i7]) << 28) ^ b5;
                            if (0 <= j5) {
                                j2 = j5 ^ 266354560;
                            } else {
                                i5 = i2 + 6;
                                long j6 = (((long) bArr[i6]) << 35) ^ j5;
                                if (0 > j6) {
                                    j4 = -34093383808L;
                                } else {
                                    int i8 = i2 + 7;
                                    final long j7 = j6 ^ (((long) bArr[i5]) << 42);
                                    if (0 <= j7) {
                                        j3 = j7 ^ 4363953127296L;
                                    } else {
                                        i5 = i2 + 8;
                                        j6 = j7 ^ (((long) bArr[i8]) << 49);
                                        if (0 > j6) {
                                            j4 = -558586000294016L;
                                        } else {
                                            i8 = i2 + 9;
                                            final long j8 = (j6 ^ (((long) bArr[i5]) << 56)) ^ 71499008037633920L;
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
                zzi = i5;
                return j2;
            }
        }
        return this.zzs();
    }
}
