package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
enum zzmg {
    ;

    static String zza(final zziy zziy) {
        final StringBuilder sb = new StringBuilder(zziy.zzd());
        for (int i2 = 0; i2 < zziy.zzd(); i2++) {
            final byte zza = zziy.zza(i2);
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
