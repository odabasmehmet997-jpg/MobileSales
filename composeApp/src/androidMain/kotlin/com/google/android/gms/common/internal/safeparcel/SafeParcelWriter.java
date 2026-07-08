package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.core.internal.view.SupportMenu;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public enum SafeParcelWriter {
    ;

    public static int beginObjectHeader(@NonNull Parcel parcel) {
        return zza(parcel, 20293);
    }

    public static void finishObjectHeader(@NonNull Parcel parcel, int i2) {
        zzb(parcel, i2);
    }

    public static void writeBoolean(@NonNull Parcel parcel, int i2, boolean z) {
        zzc(parcel, i2, 4);
        parcel.writeInt(z ? 1 : 0);
    }

    public static void writeBooleanObject(@NonNull Parcel parcel, int i2, @NonNull Boolean bool, boolean z) {
        if (null != bool) {
            zzc(parcel, i2, 4);
            parcel.writeInt(bool.booleanValue() ? 1 : 0);
        } else if (z) {
            zzc(parcel, i2, 0);
        }
    }

    public static void writeBundle(@NonNull Parcel parcel, int i2, @NonNull Bundle bundle, boolean z) {
        if (null != bundle) {
            int zza = zza(parcel, i2);
            parcel.writeBundle(bundle);
            zzb(parcel, zza);
        } else if (z) {
            zzc(parcel, i2, 0);
        }
    }

    public static void writeByte(@NonNull Parcel parcel, int i2, byte b2) {
        zzc(parcel, i2, 4);
        parcel.writeInt(b2);
    }

    public static void writeByteArray(@NonNull Parcel parcel, int i2, @NonNull byte[] bArr, boolean z) {
        if (null != bArr) {
            int zza = zza(parcel, i2);
            parcel.writeByteArray(bArr);
            zzb(parcel, zza);
        } else if (z) {
            zzc(parcel, i2, 0);
        }
    }

    public static void writeDouble(@NonNull Parcel parcel, int i2, double d2) {
        zzc(parcel, i2, 8);
        parcel.writeDouble(d2);
    }

    public static void writeDoubleObject(@NonNull Parcel parcel, int i2, @NonNull Double d2, boolean z) {
        if (null != d2) {
            zzc(parcel, i2, 8);
            parcel.writeDouble(d2.doubleValue());
        } else if (z) {
            zzc(parcel, i2, 0);
        }
    }

    public static void writeFloat(@NonNull Parcel parcel, int i2, float f2) {
        zzc(parcel, i2, 4);
        parcel.writeFloat(f2);
    }

    public static void writeFloatArray(@NonNull Parcel parcel, int i2, @NonNull float[] fArr, boolean z) {
        if (null != fArr) {
            int zza = zza(parcel, i2);
            parcel.writeFloatArray(fArr);
            zzb(parcel, zza);
        } else if (z) {
            zzc(parcel, i2, 0);
        }
    }

    public static void writeFloatObject(@NonNull Parcel parcel, int i2, @NonNull Float f2, boolean z) {
        if (null != f2) {
            zzc(parcel, i2, 4);
            parcel.writeFloat(f2.floatValue());
        } else if (z) {
            zzc(parcel, i2, 0);
        }
    }

    public static void writeIBinder(@NonNull Parcel parcel, int i2, @NonNull IBinder iBinder, boolean z) {
        if (null != iBinder) {
            int zza = zza(parcel, i2);
            parcel.writeStrongBinder(iBinder);
            zzb(parcel, zza);
        } else if (z) {
            zzc(parcel, i2, 0);
        }
    }

    public static void writeInt(@NonNull Parcel parcel, int i2, int i3) {
        zzc(parcel, i2, 4);
        parcel.writeInt(i3);
    }

    public static void writeIntArray(@NonNull Parcel parcel, int i2, @NonNull int[] iArr, boolean z) {
        if (null != iArr) {
            int zza = zza(parcel, i2);
            parcel.writeIntArray(iArr);
            zzb(parcel, zza);
        } else if (z) {
            zzc(parcel, i2, 0);
        }
    }

    public static void writeIntegerObject(@NonNull Parcel parcel, int i2, @NonNull Integer num, boolean z) {
        if (null != num) {
            zzc(parcel, i2, 4);
            parcel.writeInt(num.intValue());
        } else if (z) {
            zzc(parcel, i2, 0);
        }
    }

    public static void writeList(@NonNull Parcel parcel, int i2, @NonNull List list, boolean z) {
        if (null != list) {
            int zza = zza(parcel, i2);
            parcel.writeList(list);
            zzb(parcel, zza);
        } else if (z) {
            zzc(parcel, i2, 0);
        }
    }

    public static void writeLong(@NonNull Parcel parcel, int i2, long j2) {
        zzc(parcel, i2, 8);
        parcel.writeLong(j2);
    }

    public static void writeLongObject(@NonNull Parcel parcel, int i2, @NonNull Long l, boolean z) {
        if (null != l) {
            zzc(parcel, i2, 8);
            parcel.writeLong(l.longValue());
        } else if (z) {
            zzc(parcel, i2, 0);
        }
    }

    public static void writeParcel(@NonNull Parcel parcel, int i2, @NonNull Parcel parcel2, boolean z) {
        if (null != parcel2) {
            int zza = zza(parcel, i2);
            parcel.appendFrom(parcel2, 0, parcel2.dataSize());
            zzb(parcel, zza);
        } else if (z) {
            zzc(parcel, i2, 0);
        }
    }

    public static void writeParcelable(@NonNull Parcel parcel, int i2, @NonNull Parcelable parcelable, int i3, boolean z) {
        if (null != parcelable) {
            int zza = zza(parcel, i2);
            parcelable.writeToParcel(parcel, i3);
            zzb(parcel, zza);
        } else if (z) {
            zzc(parcel, i2, 0);
        }
    }

    public static void writeShort(@NonNull Parcel parcel, int i2, short s) {
        zzc(parcel, i2, 4);
        parcel.writeInt(s);
    }

    public static void writeString(@NonNull Parcel parcel, int i2, @NonNull String str, boolean z) {
        if (null != str) {
            int zza = zza(parcel, i2);
            parcel.writeString(str);
            zzb(parcel, zza);
        } else if (z) {
            zzc(parcel, i2, 0);
        }
    }

    public static void writeStringArray(@NonNull Parcel parcel, int i2, @NonNull String[] strArr, boolean z) {
        if (null != strArr) {
            int zza = zza(parcel, i2);
            parcel.writeStringArray(strArr);
            zzb(parcel, zza);
        } else if (z) {
            zzc(parcel, i2, 0);
        }
    }

    public static void writeStringList(@NonNull Parcel parcel, int i2, @NonNull List<String> list, boolean z) {
        if (null != list) {
            int zza = zza(parcel, i2);
            parcel.writeStringList(list);
            zzb(parcel, zza);
        } else if (z) {
            zzc(parcel, i2, 0);
        }
    }

    public static <T extends Parcelable> void writeTypedArray(@NonNull Parcel parcel, int i2, @NonNull T[] tArr, int i3, boolean z) {
        if (null != tArr) {
            int zza = zza(parcel, i2);
            parcel.writeInt(r7);
            for (T t : tArr) {
                if (null == t) {
                    parcel.writeInt(0);
                } else {
                    zzd(parcel, t, i3);
                }
            }
            zzb(parcel, zza);
        } else if (z) {
            zzc(parcel, i2, 0);
        }
    }

    public static <T extends Parcelable> void writeTypedList(@NonNull Parcel parcel, int i2, @NonNull List<T> list, boolean z) {
        if (null != list) {
            int zza = zza(parcel, i2);
            int size = list.size();
            parcel.writeInt(size);
            for (int i3 = 0; i3 < size; i3++) {
                Parcelable parcelable = list.get(i3);
                if (null == parcelable) {
                    parcel.writeInt(0);
                } else {
                    zzd(parcel, parcelable, 0);
                }
            }
            zzb(parcel, zza);
        } else if (z) {
            zzc(parcel, i2, 0);
        }
    }

    private static int zza(Parcel parcel, int i2) {
        parcel.writeInt(i2 | SupportMenu.CATEGORY_MASK);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    private static void zzb(Parcel parcel, int i2) {
        int dataPosition = parcel.dataPosition();
        parcel.setDataPosition(i2 - 4);
        parcel.writeInt(dataPosition - i2);
        parcel.setDataPosition(dataPosition);
    }

    private static void zzc(Parcel parcel, int i2, int i3) {
        parcel.writeInt(i2 | (i3 << 16));
    }

    private static void zzd(Parcel parcel, Parcelable parcelable, int i2) {
        int dataPosition = parcel.dataPosition();
        parcel.writeInt(1);
        int dataPosition2 = parcel.dataPosition();
        parcelable.writeToParcel(parcel, i2);
        int dataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition3 - dataPosition2);
        parcel.setDataPosition(dataPosition3);
    }
}
