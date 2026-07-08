package com.google.android.gms.internal.measurement;

import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzfy extends zzjz {
    public static final int zza = 0;
    
    public static final zzfy zze;
    private boolean zzA;
    private String zzB = "";
    private long zzC;
    private int zzD;
    private String zzE = "";
    private String zzF = "";
    private boolean zzG;
    
    public zzkg zzH = zzjz.zzby();
    private String zzI = "";
    private int zzJ;
    private int zzK;
    private int zzL;
    private String zzM = "";
    private long zzN;
    private long zzO;
    private String zzP = "";
    private final String zzQ = "";
    private int zzR;
    private String zzS = "";
    private zzgb zzT;
    private zzke zzU = zzjz.zzbv();
    private long zzV;
    private long zzW;
    private final String zzX = "";
    private String zzY = "";
    private int zzZ;
    private boolean zzaa;
    private final String zzab = "";
    private boolean zzac;
    private zzfu zzad;
    private final String zzae = "";
    private int zzf;
    private int zzg;
    private int zzh;
    
    public zzkg zzi = zzjz.zzby();
    private zzkg zzj = zzjz.zzby();
    private long zzk;
    private long zzl;
    private long zzm;
    private long zzn;
    private long zzo;
    private String zzp = "";
    private String zzq = "";
    private String zzr = "";
    private String zzs = "";
    private int zzt;
    private String zzu = "";
    private String zzv = "";
    private String zzw = "";
    private long zzx;
    private long zzy;
    private String zzz = "";

    static {
        zzfy zzfy = new zzfy();
        zze = zzfy;
        zzjz.zzbE(zzfy.class, zzfy);
    }

    private zzfy() {
    }

    static void zzP(zzfy zzfy, long j2) {
        zzfy.zzf |= BasicMeasure.EXACTLY;
        zzfy.zzO = j2;
    }

    static void zzQ(zzfy zzfy) {
        zzfy.zzf &= Integer.MAX_VALUE;
        zzfy.zzP = zze.zzP;
    }

    static void zzR(zzfy zzfy, int i2) {
        zzfy.zzg |= 2;
        zzfy.zzR = i2;
    }

    static void zzS(zzfy zzfy, int i2, zzfo zzfo) {
        zzfo.getClass();
        zzfy.zzbG();
        zzfy.zzi.set(i2, zzfo);
    }

    static void zzT(zzfy zzfy, String str) {
        str.getClass();
        zzfy.zzg |= 4;
        zzfy.zzS = str;
    }

    static void zzU(zzfy zzfy, zzgb zzgb) {
        zzgb.getClass();
        zzfy.zzT = zzgb;
        zzfy.zzg |= 8;
    }

    static void zzV(zzfy zzfy, Iterable iterable) {
        zzke zzke = zzfy.zzU;
        if (!zzke.zzc()) {
            int size = zzke.size();
            zzfy.zzU = zzke.zzg(0 == size ? 10 : size + size);
        }
        zzih.zzbo(iterable, zzfy.zzU);
    }

    static void zzW(zzfy zzfy, zzfo zzfo) {
        zzfo.getClass();
        zzfy.zzbG();
        zzfy.zzi.add(zzfo);
    }

    static void zzX(zzfy zzfy, long j2) {
        zzfy.zzg |= 16;
        zzfy.zzV = j2;
    }

    static void zzY(zzfy zzfy, long j2) {
        zzfy.zzg |= 32;
        zzfy.zzW = j2;
    }

    static void zzZ(zzfy zzfy, String str) {
        zzfy.zzg |= 128;
        zzfy.zzY = str;
    }

    static void zzaA(zzfy zzfy, boolean z) {
        zzfy.zzf |= 131072;
        zzfy.zzA = z;
    }

    static void zzaB(zzfy zzfy) {
        zzfy.zzf &= -131073;
        zzfy.zzA = false;
    }

    static void zzaC(zzfy zzfy, String str) {
        str.getClass();
        zzfy.zzf |= 262144;
        zzfy.zzB = str;
    }

    static void zzaD(zzfy zzfy) {
        zzfy.zzf &= -262145;
        zzfy.zzB = zze.zzB;
    }

    static void zzaE(zzfy zzfy, long j2) {
        zzfy.zzf |= 524288;
        zzfy.zzC = j2;
    }

    static void zzaF(zzfy zzfy, int i2) {
        zzfy.zzf |= 1048576;
        zzfy.zzD = i2;
    }

    static void zzaG(zzfy zzfy, String str) {
        zzfy.zzf |= 2097152;
        zzfy.zzE = str;
    }

    static void zzaH(zzfy zzfy) {
        zzfy.zzf &= -2097153;
        zzfy.zzE = zze.zzE;
    }

    static void zzaI(zzfy zzfy, String str) {
        str.getClass();
        zzfy.zzf |= 4194304;
        zzfy.zzF = str;
    }

    static void zzaJ(zzfy zzfy, boolean z) {
        zzfy.zzf |= 8388608;
        zzfy.zzG = z;
    }

    static void zzaK(zzfy zzfy, Iterable iterable) {
        zzkg zzkg = zzfy.zzH;
        if (!zzkg.zzc()) {
            zzfy.zzH = zzjz.zzbz(zzkg);
        }
        zzih.zzbo(iterable, zzfy.zzH);
    }

    static void zzaM(zzfy zzfy, String str) {
        str.getClass();
        zzfy.zzf |= 16777216;
        zzfy.zzI = str;
    }

    static void zzaN(zzfy zzfy, int i2) {
        zzfy.zzf |= 33554432;
        zzfy.zzJ = i2;
    }

    static void zzaO(zzfy zzfy, int i2) {
        zzfy.zzf |= 1;
        zzfy.zzh = 1;
    }

    static void zzaP(zzfy zzfy) {
        zzfy.zzf &= -268435457;
        zzfy.zzM = zze.zzM;
    }

    static void zzaQ(zzfy zzfy, long j2) {
        zzfy.zzf |= 536870912;
        zzfy.zzN = j2;
    }

    static void zzaa(zzfy zzfy, Iterable iterable) {
        zzfy.zzbG();
        zzih.zzbo(iterable, zzfy.zzi);
    }

    static void zzac(zzfy zzfy, int i2) {
        zzfy.zzbG();
        zzfy.zzi.remove(i2);
    }

    static void zzad(zzfy zzfy, int i2, zzgh zzgh) {
        zzgh.getClass();
        zzfy.zzbH();
        zzfy.zzj.set(i2, zzgh);
    }

    static void zzae(zzfy zzfy, zzgh zzgh) {
        zzgh.getClass();
        zzfy.zzbH();
        zzfy.zzj.add(zzgh);
    }

    static void zzaf(zzfy zzfy, Iterable iterable) {
        zzfy.zzbH();
        zzih.zzbo(iterable, zzfy.zzj);
    }

    static void zzag(zzfy zzfy, int i2) {
        zzfy.zzbH();
        zzfy.zzj.remove(i2);
    }

    static void zzah(zzfy zzfy, long j2) {
        zzfy.zzf |= 2;
        zzfy.zzk = j2;
    }

    static void zzai(zzfy zzfy, long j2) {
        zzfy.zzf |= 4;
        zzfy.zzl = j2;
    }

    static void zzaj(zzfy zzfy, long j2) {
        zzfy.zzf |= 8;
        zzfy.zzm = j2;
    }

    static void zzak(zzfy zzfy, long j2) {
        zzfy.zzf |= 16;
        zzfy.zzn = j2;
    }

    static void zzal(zzfy zzfy) {
        zzfy.zzf &= -17;
        zzfy.zzn = 0;
    }

    static void zzam(zzfy zzfy, long j2) {
        zzfy.zzf |= 32;
        zzfy.zzo = j2;
    }

    static void zzan(zzfy zzfy) {
        zzfy.zzf &= -33;
        zzfy.zzo = 0;
    }

    static void zzao(zzfy zzfy, String str) {
        zzfy.zzf |= 64;
        zzfy.zzp = "android";
    }

    static void zzap(zzfy zzfy, String str) {
        str.getClass();
        zzfy.zzf |= 128;
        zzfy.zzq = str;
    }

    static void zzaq(zzfy zzfy, String str) {
        str.getClass();
        zzfy.zzf |= 256;
        zzfy.zzr = str;
    }

    static void zzar(zzfy zzfy, String str) {
        str.getClass();
        zzfy.zzf |= 512;
        zzfy.zzs = str;
    }

    static void zzas(zzfy zzfy, int i2) {
        zzfy.zzf |= 1024;
        zzfy.zzt = i2;
    }

    static void zzat(zzfy zzfy, String str) {
        str.getClass();
        zzfy.zzf |= 2048;
        zzfy.zzu = str;
    }

    static void zzau(zzfy zzfy, String str) {
        str.getClass();
        zzfy.zzf |= 4096;
        zzfy.zzv = str;
    }

    static void zzav(zzfy zzfy, String str) {
        str.getClass();
        zzfy.zzf |= 8192;
        zzfy.zzw = str;
    }

    static void zzaw(zzfy zzfy, long j2) {
        zzfy.zzf |= 16384;
        zzfy.zzx = j2;
    }

    static void zzax(zzfy zzfy, long j2) {
        zzfy.zzf |= 32768;
        zzfy.zzy = 60000;
    }

    static void zzay(zzfy zzfy, String str) {
        str.getClass();
        zzfy.zzf |= 65536;
        zzfy.zzz = str;
    }

    static void zzaz(zzfy zzfy) {
        zzfy.zzf &= -65537;
        zzfy.zzz = zze.zzz;
    }

    private void zzbG() {
        zzkg zzkg = this.zzi;
        if (!zzkg.zzc()) {
            this.zzi = zzjz.zzbz(zzkg);
        }
    }

    private void zzbH() {
        zzkg zzkg = this.zzj;
        if (!zzkg.zzc()) {
            this.zzj = zzjz.zzbz(zzkg);
        }
    }

    public static zzfx zzu() {
        return (zzfx) zze.zzbs();
    }

    public String zzA() {
        return this.zzu;
    }

    public String zzB() {
        return this.zzw;
    }

    public String zzC() {
        return this.zzY;
    }

    public String zzD() {
        return this.zzr;
    }

    public String zzE() {
        return this.zzP;
    }

    public String zzF() {
        return this.zzI;
    }

    public String zzG() {
        return this.zzF;
    }

    public String zzH() {
        return this.zzE;
    }

    public String zzI() {
        return this.zzq;
    }

    public String zzJ() {
        return this.zzp;
    }

    public String zzK() {
        return this.zzz;
    }

    public String zzL() {
        return this.zzs;
    }

    public List zzM() {
        return this.zzH;
    }

    public List zzN() {
        return this.zzi;
    }

    public List zzO() {
        return this.zzj;
    }

    public int zza() {
        return this.zzJ;
    }

    public boolean zzaR() {
        return this.zzA;
    }

    public boolean zzaS() {
        return this.zzG;
    }

    public boolean zzaT() {
        return 0 != (zzf & BasicMeasure.EXACTLY);
    }

    public boolean zzaU() {
        return 0 != (zzf & 33554432);
    }

    public boolean zzaV() {
        return 0 != (zzf & 1048576);
    }

    public boolean zzaW() {
        return 0 != (zzf & 536870912);
    }

    public boolean zzaX() {
        return 0 != (zzg & 128);
    }

    public boolean zzaY() {
        return 0 != (zzf & 524288);
    }

    public boolean zzaZ() {
        return 0 != (zzg & 16);
    }

    public int zzb() {
        return this.zzD;
    }

    public boolean zzba() {
        return 0 != (zzf & 8);
    }

    public boolean zzbb() {
        return 0 != (zzf & 16384);
    }

    public boolean zzbc() {
        return 0 != (zzf & 131072);
    }

    public boolean zzbd() {
        return 0 != (zzf & 32);
    }

    public boolean zzbe() {
        return 0 != (zzf & 16);
    }

    public boolean zzbf() {
        return 0 != (zzf & 1);
    }

    public boolean zzbg() {
        return 0 != (zzg & 2);
    }

    public boolean zzbh() {
        return 0 != (zzf & 8388608);
    }

    public boolean zzbi() {
        return 0 != (zzf & 4);
    }

    public boolean zzbj() {
        return 0 != (zzf & 1024);
    }

    public boolean zzbk() {
        return 0 != (zzf & 2);
    }

    public boolean zzbl() {
        return 0 != (zzf & 32768);
    }

    public int zzc() {
        return this.zzi.size();
    }

    public int zzd() {
        return this.zzh;
    }

    public int zze() {
        return this.zzR;
    }

    public int zzf() {
        return this.zzt;
    }

    public int zzg() {
        return this.zzj.size();
    }

    public long zzh() {
        return this.zzO;
    }

    public long zzi() {
        return this.zzN;
    }

    public long zzj() {
        return this.zzC;
    }

    public long zzk() {
        return this.zzV;
    }

    
    public Object zzl(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzjz.zzbD(zze, "\u00012\u0000\u0002\u0001?2\u0000\u0004\u0000\u0001င\u0000\u0002\u001b\u0003\u001b\u0004ဂ\u0001\u0005ဂ\u0002\u0006ဂ\u0003\u0007ဂ\u0005\bဈ\u0006\tဈ\u0007\nဈ\b\u000bဈ\t\fင\n\rဈ\u000b\u000eဈ\f\u0010ဈ\r\u0011ဂ\u000e\u0012ဂ\u000f\u0013ဈ\u0010\u0014ဇ\u0011\u0015ဈ\u0012\u0016ဂ\u0013\u0017င\u0014\u0018ဈ\u0015\u0019ဈ\u0016\u001aဂ\u0004\u001cဇ\u0017\u001d\u001b\u001eဈ\u0018\u001fင\u0019 င\u001a!င\u001b\"ဈ\u001c#ဂ\u001dဂ\u001e%ဈ\u001f&ဈ 'င!)ဈ\",ဉ#-\u001d.ဂ/ဂ%2ဈ&4ဈ'5ဌ(7ဇ)9ဈ*:ဇ+;ဉ,?ဈ-", new Object[]{"zzf", "zzg", "zzh", "zzi", zzfo.class, "zzj", zzgh.class, "zzk", "zzl", "zzm", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzA", "zzB", "zzC", "zzD", "zzE", "zzF", "zzn", "zzG", "zzH", zzfk.class, "zzI", "zzJ", "zzK", "zzL", "zzM", "zzN", "zzO", "zzP", "zzQ", "zzR", "zzS", "zzT", "zzU", "zzV", "zzW", "zzX", "zzY", "zzZ", zzfg.zza, "zzaa", "zzab", "zzac", "zzad", "zzae"});
        } else if (3 == i3) {
            return new zzfy();
        } else {
            if (4 == i3) {
                return new zzfx(null);
            }
            if (5 != i3) {
                return null;
            }
            return zze;
        }
    }

    public long zzm() {
        return this.zzm;
    }

    public long zzn() {
        return this.zzx;
    }

    public long zzo() {
        return this.zzo;
    }

    public long zzp() {
        return this.zzn;
    }

    public long zzq() {
        return this.zzl;
    }

    public long zzr() {
        return this.zzk;
    }

    public long zzs() {
        return this.zzy;
    }

    public zzfo zzt(int i2) {
        return (zzfo) this.zzi.get(i2);
    }

    public zzgh zzw(int i2) {
        return (zzgh) this.zzj.get(i2);
    }

    public String zzx() {
        return this.zzS;
    }

    public String zzy() {
        return this.zzv;
    }

    public String zzz() {
        return this.zzB;
    }
}
