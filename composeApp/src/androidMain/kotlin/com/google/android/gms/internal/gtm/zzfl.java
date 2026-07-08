package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzfl extends zzfr {
    private final String zzb;
    private final int zzc;
    private final int zzd;

    zzfl(String str, boolean z, int i2, zzfh zzfh, zzfi zzfi, int i3, zzfk zzfk) {
        this.zzb = str;
        this.zzc = i2;
        this.zzd = i3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof final zzfr zzfr) {
            if (this.zzb.equals(zzfr.zzc())) {
                zzfr.zzd();
                int i2 = this.zzc;
                int zze = zzfr.zze();
                if (0 == i2) {
                    throw null;
                } else if (i2 == zze) {
                    zzfr.zza();
                    zzfr.zzb();
                    int i3 = this.zzd;
                    int zzf = zzfr.zzf();
                    if (0 == i3) {
                        throw null;
                    } else return 1 == zzf;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.zzb.hashCode() ^ 1000003;
        int i2 = this.zzc;
        if (0 != i2) {
            int i3 = (((hashCode * 1000003) ^ 1237) * 1000003) ^ i2;
            if (0 != zzd) {
                return (i3 * 583896283) ^ 1;
            }
            throw null;
        }
        throw null;
    }

    public String toString() {
        int i2 = this.zzc;
        String str = "null";
        String str2 = 1 != i2 ? 2 != i2 ? 3 != i2 ? 4 != i2 ? str : "NO_CHECKS" : "SKIP_SECURITY_CHECK" : "SKIP_COMPLIANCE_CHECK" : "ALL_CHECKS";
        if (1 == zzd) {
            str = "READ_AND_WRITE";
        }
        String str3 = this.zzb;
        return "FileComplianceOptions{fileOwner=" + str3 + ", hasDifferentDmaOwner=false, fileChecks=" + str2 + ", dataForwardingNotAllowedResolver=null, multipleProductIdGroupsResolver=null, filePurpose=" + str + "}";
    }

    public zzfh zza() {
        return null;
    }

    public zzfi zzb() {
        return null;
    }

    public String zzc() {
        return this.zzb;
    }

    public boolean zzd() {
        return false;
    }

    public int zze() {
        return this.zzc;
    }

    public int zzf() {
        return this.zzd;
    }
}
