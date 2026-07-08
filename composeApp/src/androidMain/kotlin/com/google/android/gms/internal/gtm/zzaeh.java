package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
enum zzaeh {
    ;

    static String zza(zzyx zzyx) {
        StringBuilder sb = new StringBuilder(zzyx.zzd());
        for (int i2 = 0; i2 < zzyx.zzd(); i2++) {
            byte zza = zzyx.zza(i2);
            if (34 == zza) {
                sb.append("\\\"");
            } else if (39 == zza) {
                sb.append("\\'");
            } else if (92 != zza) {
                switch (zza) {
                    case 7:
                        sb.append("\\a");
                        break;
                    case 8:
                        sb.append("\\b");
                        break;
                    case 9:
                        sb.append("\\t");
                        break;
                    case 10:
                        sb.append("\\n");
                        break;
                    case 11:
                        sb.append("\\v");
                        break;
                    case 12:
                        sb.append("\\f");
                        break;
                    case 13:
                        sb.append("\\r");
                        break;
                    default:
                        if (32 <= zza && 126 >= zza) {
                            sb.append((char) zza);
                            break;
                        } else {
                            sb.append('\\');
                            sb.append((char) (((zza >>> 6) & 3) + 48));
                            sb.append((char) (((zza >>> 3) & 7) + 48));
                            sb.append((char) ((zza & 7) + 48));
                            break;
                        }
                        break;
                }
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }
}
