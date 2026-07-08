package com.google.android.gms.internal.measurement;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzbk extends zzaw {
    zzbk() {
        this.zza.add(zzbl.ASSIGN);
        this.zza.add(zzbl.CONST);
        this.zza.add(zzbl.CREATE_ARRAY);
        this.zza.add(zzbl.CREATE_OBJECT);
        this.zza.add(zzbl.EXPRESSION_LIST);
        this.zza.add(zzbl.GET);
        this.zza.add(zzbl.GET_INDEX);
        this.zza.add(zzbl.GET_PROPERTY);
        this.zza.add(zzbl.NULL);
        this.zza.add(zzbl.SET_PROPERTY);
        this.zza.add(zzbl.TYPEOF);
        this.zza.add(zzbl.UNDEFINED);
        this.zza.add(zzbl.VAR);
    }

    public zzap zza(String str, zzg zzg, List list) {
        String str2;
        final zzbl zzbl = com.google.android.gms.internal.measurement.zzbl.ADD;
        int ordinal = zzh.zze(str).ordinal();
        int i2 = 0;
        if (3 == ordinal) {
            zzh.zzh(com.google.android.gms.internal.measurement.zzbl.ASSIGN.name(), 2, list);
            zzap zzb = zzg.zzb((zzap) list.get(0));
            if (!(zzb instanceof zzat)) {
                throw new IllegalArgumentException(String.format("Expected string for assign var. got %s", zzb.getClass().getCanonicalName()));
            } else if (zzg.zzh(zzb.zzi())) {
                zzap zzb2 = zzg.zzb((zzap) list.get(1));
                zzg.zzg(zzb.zzi(), zzb2);
                return zzb2;
            } else {
                throw new IllegalArgumentException(String.format("Attempting to assign undefined value %s", zzb.zzi()));
            }
        } else if (14 == ordinal) {
            zzh.zzi(com.google.android.gms.internal.measurement.zzbl.CONST.name(), 2, list);
            if (0 == list.size() % 2) {
                while (i2 < list.size() - 1) {
                    zzap zzb3 = zzg.zzb((zzap) list.get(i2));
                    if (zzb3 instanceof zzat) {
                        zzg.zzf(zzb3.zzi(), zzg.zzb((zzap) list.get(i2 + 1)));
                        i2 += 2;
                    } else {
                        throw new IllegalArgumentException(String.format("Expected string for const name. got %s", zzb3.getClass().getCanonicalName()));
                    }
                }
                return zzap.zzf;
            }
            throw new IllegalArgumentException(String.format("CONST requires an even number of arguments, found %s", Integer.valueOf(list.size())));
        } else if (24 == ordinal) {
            zzh.zzi(com.google.android.gms.internal.measurement.zzbl.EXPRESSION_LIST.name(), 1, list);
            zzap zzap = com.google.android.gms.internal.measurement.zzap.zzf;
            while (i2 < list.size()) {
                zzap = zzg.zzb((zzap) list.get(i2));
                if (!(zzap instanceof zzag)) {
                    i2++;
                } else {
                    throw new IllegalStateException("ControlValue cannot be in an expression list");
                }
            }
            return zzap;
        } else if (33 == ordinal) {
            zzh.zzh(com.google.android.gms.internal.measurement.zzbl.GET.name(), 1, list);
            zzap zzb4 = zzg.zzb((zzap) list.get(0));
            if (zzb4 instanceof zzat) {
                return zzg.zzd(zzb4.zzi());
            }
            throw new IllegalArgumentException(String.format("Expected string for get var. got %s", zzb4.getClass().getCanonicalName()));
        } else if (49 == ordinal) {
            zzh.zzh(com.google.android.gms.internal.measurement.zzbl.NULL.name(), 0, list);
            return zzap.zzg;
        } else if (58 == ordinal) {
            zzh.zzh(com.google.android.gms.internal.measurement.zzbl.SET_PROPERTY.name(), 3, list);
            zzap zzb5 = zzg.zzb((zzap) list.get(0));
            zzap zzb6 = zzg.zzb((zzap) list.get(1));
            zzap zzb7 = zzg.zzb((zzap) list.get(2));
            if (zzb5 == zzap.zzf || zzb5 == zzap.zzg) {
                throw new IllegalStateException(String.format("Can't set property %s of %s", zzb6.zzi(), zzb5.zzi()));
            }
            if ((zzb5 instanceof zzae) && (zzb6 instanceof zzah)) {
                ((zzae) zzb5).zzq(zzb6.zzh(this.zzd, 3, list).intValue(), zzb7);
            } else if (zzb5 instanceof zzal) {
                ((zzal) zzb5).zzr(zzb6.zzi(), zzb7);
            }
            return zzb7;
        } else if (17 != ordinal) {
            if (18 != ordinal) {
                if (35 == ordinal || 36 == ordinal) {
                    zzh.zzh(com.google.android.gms.internal.measurement.zzbl.GET_PROPERTY.name(), 2, list);
                    zzap zzb8 = zzg.zzb((zzap) list.get(0));
                    zzap zzb9 = zzg.zzb((zzap) list.get(1));
                    if ((zzb8 instanceof zzae) && zzh.zzk(zzb9)) {
                        return ((zzae) zzb8).zze(zzb9.zzh(this.zzd, 3, list).intValue());
                    }
                    if (zzb8 instanceof zzal) {
                        return ((zzal) zzb8).zzf(zzb9.zzi());
                    }
                    if (zzb8 instanceof zzat) {
                        if (Name.LENGTH.equals(zzb9.zzi())) {
                            return new zzah(Double.valueOf(zzb8.zzi().length()));
                        }
                        if (zzh.zzk(zzb9) && zzb9.zzh(this.zzd, 3, list).doubleValue() < zzb8.zzi().length()) {
                            return new zzat(String.valueOf(zzb8.zzi().charAt(zzb9.zzh(this.zzd, 3, list).intValue())));
                        }
                    }
                    return zzap.zzf;
                }
                switch (ordinal) {
                    case 62:
                        zzh.zzh(com.google.android.gms.internal.measurement.zzbl.TYPEOF.name(), 1, list);
                        zzap zzb10 = zzg.zzb((zzap) list.get(0));
                        if (zzb10 instanceof zzau) {
                            str2 = "undefined";
                        } else if (zzb10 instanceof zzaf) {
                            str2 = TypedValues.Custom.S_BOOLEAN;
                        } else if (zzb10 instanceof zzah) {
                            str2 = "number";
                        } else if (zzb10 instanceof zzat) {
                            str2 = TypedValues.Custom.S_STRING;
                        } else if (zzb10 instanceof zzao) {
                            str2 = "function";
                        } else if ((zzb10 instanceof zzaq) || (zzb10 instanceof zzag)) {
                            throw new IllegalArgumentException(String.format("Unsupported value type %s in typeof", zzb10));
                        } else {
                            str2 = "object";
                        }
                        return new zzat(str2);
                    case 63:
                        zzh.zzh(com.google.android.gms.internal.measurement.zzbl.UNDEFINED.name(), 0, list);
                        return zzap.zzf;
                    case 64:
                        zzh.zzi(com.google.android.gms.internal.measurement.zzbl.VAR.name(), 1, list);
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            zzap zzb11 = zzg.zzb((zzap) it.next());
                            if (zzb11 instanceof zzat) {
                                zzg.zze(zzb11.zzi(), zzap.zzf);
                            } else {
                                throw new IllegalArgumentException(String.format("Expected string for var name. got %s", zzb11.getClass().getCanonicalName()));
                            }
                        }
                        return zzap.zzf;
                    default:
                        return this.zzb(str);
                }
            } else if (list.isEmpty()) {
                return new zzam();
            } else {
                if (0 == list.size() % 2) {
                    zzam zzam = new zzam();
                    while (i2 < list.size() - 1) {
                        zzap zzb12 = zzg.zzb((zzap) list.get(i2));
                        zzap zzb13 = zzg.zzb((zzap) list.get(i2 + 1));
                        if ((zzb12 instanceof zzag) || (zzb13 instanceof zzag)) {
                            throw new IllegalStateException("Failed to evaluate map entry");
                        }
                        zzam.zzr(zzb12.zzi(), zzb13);
                        i2 += 2;
                    }
                    return zzam;
                }
                throw new IllegalArgumentException(String.format("CREATE_OBJECT requires an even number of arguments, found %s", Integer.valueOf(list.size())));
            }
        } else if (list.isEmpty()) {
            return new zzae();
        } else {
            zzae zzae = new zzae();
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                zzap zzb14 = zzg.zzb((zzap) it2.next());
                if (!(zzb14 instanceof zzag)) {
                    zzae.zzq(i2, zzb14);
                    i2++;
                } else {
                    throw new IllegalStateException("Failed to evaluate array element");
                }
            }
            return zzae;
        }
    }
}
