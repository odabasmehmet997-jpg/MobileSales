package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.core.internal.view.SupportMenu;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public enum SafeParcelReader {
    ;

    /* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
    public static class ParseException extends RuntimeException {
        /*  WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public ParseException(@androidx.annotation.NonNull java.lang.String r3, @androidx.annotation.NonNull android.os.Parcel r4) {
            /*
                r2 = this;
                int r0 = r4.dataPosition()
                int r4 = r4.dataSize()
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                r1.append(r3)
                java.lang.String r3 = " Parcel: pos="
                r1.append(r3)
                r1.append(r0)
                java.lang.String r3 = " size="
                r1.append(r3)
                r1.append(r4)
                java.lang.String r3 = r1.toString()
                r2.<init>(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException.<init>(java.lang.String, android.os.Parcel):void");
        }
    }

    @NonNull
    public static BigDecimal createBigDecimal(@NonNull Parcel parcel, int i2) {
        int readSize = readSize(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (0 == readSize) {
            return null;
        }
        byte[] createByteArray = parcel.createByteArray();
        int readInt = parcel.readInt();
        parcel.setDataPosition(dataPosition + readSize);
        return new BigDecimal(new BigInteger(createByteArray), readInt);
    }

    @NonNull
    public static BigDecimal[] createBigDecimalArray(@NonNull Parcel parcel, int i2) {
        int readSize = readSize(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (0 == readSize) {
            return null;
        }
        int readInt = parcel.readInt();
        BigDecimal[] bigDecimalArr = new BigDecimal[readInt];
        for (int i3 = 0; i3 < readInt; i3++) {
            byte[] createByteArray = parcel.createByteArray();
            bigDecimalArr[i3] = new BigDecimal(new BigInteger(createByteArray), parcel.readInt());
        }
        parcel.setDataPosition(dataPosition + readSize);
        return bigDecimalArr;
    }

    @NonNull
    public static BigInteger createBigInteger(@NonNull Parcel parcel, int i2) {
        int readSize = readSize(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (0 == readSize) {
            return null;
        }
        byte[] createByteArray = parcel.createByteArray();
        parcel.setDataPosition(dataPosition + readSize);
        return new BigInteger(createByteArray);
    }

    @NonNull
    public static BigInteger[] createBigIntegerArray(@NonNull Parcel parcel, int i2) {
        int readSize = readSize(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (0 == readSize) {
            return null;
        }
        int readInt = parcel.readInt();
        BigInteger[] bigIntegerArr = new BigInteger[readInt];
        for (int i3 = 0; i3 < readInt; i3++) {
            bigIntegerArr[i3] = new BigInteger(parcel.createByteArray());
        }
        parcel.setDataPosition(dataPosition + readSize);
        return bigIntegerArr;
    }

    @NonNull
    public static boolean[] createBooleanArray(@NonNull Parcel parcel, int i2) {
        int readSize = readSize(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (0 == readSize) {
            return null;
        }
        boolean[] createBooleanArray = parcel.createBooleanArray();
        parcel.setDataPosition(dataPosition + readSize);
        return createBooleanArray;
    }

    @NonNull
    public static Bundle createBundle(@NonNull Parcel parcel, int i2) {
        int readSize = readSize(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (0 == readSize) {
            return null;
        }
        Bundle readBundle = parcel.readBundle();
        parcel.setDataPosition(dataPosition + readSize);
        return readBundle;
    }

    @NonNull
    public static byte[] createByteArray(@NonNull Parcel parcel, int i2) {
        int readSize = readSize(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (0 == readSize) {
            return null;
        }
        byte[] createByteArray = parcel.createByteArray();
        parcel.setDataPosition(dataPosition + readSize);
        return createByteArray;
    }

    @NonNull
    public static double[] createDoubleArray(@NonNull Parcel parcel, int i2) {
        int readSize = readSize(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (0 == readSize) {
            return null;
        }
        double[] createDoubleArray = parcel.createDoubleArray();
        parcel.setDataPosition(dataPosition + readSize);
        return createDoubleArray;
    }

    @NonNull
    public static float[] createFloatArray(@NonNull Parcel parcel, int i2) {
        int readSize = readSize(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (0 == readSize) {
            return null;
        }
        float[] createFloatArray = parcel.createFloatArray();
        parcel.setDataPosition(dataPosition + readSize);
        return createFloatArray;
    }

    @NonNull
    public static int[] createIntArray(@NonNull Parcel parcel, int i2) {
        int readSize = readSize(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (0 == readSize) {
            return null;
        }
        int[] createIntArray = parcel.createIntArray();
        parcel.setDataPosition(dataPosition + readSize);
        return createIntArray;
    }

    @NonNull
    public static long[] createLongArray(@NonNull Parcel parcel, int i2) {
        int readSize = readSize(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (0 == readSize) {
            return null;
        }
        long[] createLongArray = parcel.createLongArray();
        parcel.setDataPosition(dataPosition + readSize);
        return createLongArray;
    }

    @NonNull
    public static Parcel createParcel(@NonNull Parcel parcel, int i2) {
        int readSize = readSize(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (0 == readSize) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        obtain.appendFrom(parcel, dataPosition, readSize);
        parcel.setDataPosition(dataPosition + readSize);
        return obtain;
    }

    @NonNull
    public static Parcel[] createParcelArray(@NonNull Parcel parcel, int i2) {
        int readSize = readSize(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (0 == readSize) {
            return null;
        }
        int readInt = parcel.readInt();
        Parcel[] parcelArr = new Parcel[readInt];
        for (int i3 = 0; i3 < readInt; i3++) {
            int readInt2 = parcel.readInt();
            if (0 != readInt2) {
                int dataPosition2 = parcel.dataPosition();
                Parcel obtain = Parcel.obtain();
                obtain.appendFrom(parcel, dataPosition2, readInt2);
                parcelArr[i3] = obtain;
                parcel.setDataPosition(dataPosition2 + readInt2);
            } else {
                parcelArr[i3] = null;
            }
        }
        parcel.setDataPosition(dataPosition + readSize);
        return parcelArr;
    }

    @NonNull
    public static <T extends Parcelable> T createParcelable(@NonNull Parcel parcel, int i2, @NonNull Parcelable.Creator<T> creator) {
        int readSize = readSize(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (0 == readSize) {
            return null;
        }
        T t = creator.createFromParcel(parcel);
        parcel.setDataPosition(dataPosition + readSize);
        return t;
    }

    @NonNull
    public static String createString(@NonNull Parcel parcel, int i2) {
        int readSize = readSize(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (0 == readSize) {
            return null;
        }
        String readString = parcel.readString();
        parcel.setDataPosition(dataPosition + readSize);
        return readString;
    }

    @NonNull
    public static String[] createStringArray(@NonNull Parcel parcel, int i2) {
        int readSize = readSize(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (0 == readSize) {
            return null;
        }
        String[] createStringArray = parcel.createStringArray();
        parcel.setDataPosition(dataPosition + readSize);
        return createStringArray;
    }

    @NonNull
    public static ArrayList<String> createStringList(@NonNull Parcel parcel, int i2) {
        int readSize = readSize(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (0 == readSize) {
            return null;
        }
        ArrayList<String> createStringArrayList = parcel.createStringArrayList();
        parcel.setDataPosition(dataPosition + readSize);
        return createStringArrayList;
    }

    @NonNull
    public static <T> T[] createTypedArray(@NonNull Parcel parcel, int i2, @NonNull Parcelable.Creator<T> creator) {
        int readSize = readSize(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (0 == readSize) {
            return null;
        }
        T[] createTypedArray = parcel.createTypedArray(creator);
        parcel.setDataPosition(dataPosition + readSize);
        return createTypedArray;
    }

    @NonNull
    public static <T> ArrayList<T> createTypedList(@NonNull Parcel parcel, int i2, @NonNull Parcelable.Creator<T> creator) {
        int readSize = readSize(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (0 == readSize) {
            return null;
        }
        ArrayList<T> createTypedArrayList = parcel.createTypedArrayList(creator);
        parcel.setDataPosition(dataPosition + readSize);
        return createTypedArrayList;
    }

    public static void ensureAtEnd(@NonNull Parcel parcel, int i2) {
        if (parcel.dataPosition() != i2) {
            throw new ParseException("Overread allowed size end=" + i2, parcel);
        }
    }

    public static int getFieldId(int i2) {
        return (char) i2;
    }

    public static boolean readBoolean(@NonNull Parcel parcel, int i2) {
        zzb(parcel, i2, 4);
        return 0 != parcel.readInt();
    }

    @NonNull
    public static Boolean readBooleanObject(@NonNull Parcel parcel, int i2) {
        int readSize = readSize(parcel, i2);
        if (0 == readSize) {
            return null;
        }
        zza(parcel, i2, readSize, 4);
        return Boolean.valueOf(0 != parcel.readInt());
    }

    public static byte readByte(@NonNull Parcel parcel, int i2) {
        zzb(parcel, i2, 4);
        return (byte) parcel.readInt();
    }

    public static double readDouble(@NonNull Parcel parcel, int i2) {
        zzb(parcel, i2, 8);
        return parcel.readDouble();
    }

    @NonNull
    public static Double readDoubleObject(@NonNull Parcel parcel, int i2) {
        int readSize = readSize(parcel, i2);
        if (0 == readSize) {
            return null;
        }
        zza(parcel, i2, readSize, 8);
        return Double.valueOf(parcel.readDouble());
    }

    public static float readFloat(@NonNull Parcel parcel, int i2) {
        zzb(parcel, i2, 4);
        return parcel.readFloat();
    }

    @NonNull
    public static Float readFloatObject(@NonNull Parcel parcel, int i2) {
        int readSize = readSize(parcel, i2);
        if (0 == readSize) {
            return null;
        }
        zza(parcel, i2, readSize, 4);
        return Float.valueOf(parcel.readFloat());
    }

    public static int readHeader(@NonNull Parcel parcel) {
        return parcel.readInt();
    }

    @NonNull
    public static IBinder readIBinder(@NonNull Parcel parcel, int i2) {
        int readSize = readSize(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (0 == readSize) {
            return null;
        }
        IBinder readStrongBinder = parcel.readStrongBinder();
        parcel.setDataPosition(dataPosition + readSize);
        return readStrongBinder;
    }

    public static int readInt(@NonNull Parcel parcel, int i2) {
        zzb(parcel, i2, 4);
        return parcel.readInt();
    }

    @NonNull
    public static Integer readIntegerObject(@NonNull Parcel parcel, int i2) {
        int readSize = readSize(parcel, i2);
        if (0 == readSize) {
            return null;
        }
        zza(parcel, i2, readSize, 4);
        return Integer.valueOf(parcel.readInt());
    }

    public static void readList(@NonNull Parcel parcel, int i2, @NonNull List list, @NonNull ClassLoader classLoader) {
        int readSize = readSize(parcel, i2);
        int dataPosition = parcel.dataPosition();
        if (0 != readSize) {
            parcel.readList(list, classLoader);
            parcel.setDataPosition(dataPosition + readSize);
        }
    }

    public static long readLong(@NonNull Parcel parcel, int i2) {
        zzb(parcel, i2, 8);
        return parcel.readLong();
    }

    @NonNull
    public static Long readLongObject(@NonNull Parcel parcel, int i2) {
        int readSize = readSize(parcel, i2);
        if (0 == readSize) {
            return null;
        }
        zza(parcel, i2, readSize, 8);
        return Long.valueOf(parcel.readLong());
    }

    public static short readShort(@NonNull Parcel parcel, int i2) {
        zzb(parcel, i2, 4);
        return (short) parcel.readInt();
    }

    public static int readSize(@NonNull Parcel parcel, int i2) {
        return -65536 != (i2 & SupportMenu.CATEGORY_MASK) ? (char) (i2 >> 16) : parcel.readInt();
    }

    public static void skipUnknownField(@NonNull Parcel parcel, int i2) {
        parcel.setDataPosition(parcel.dataPosition() + readSize(parcel, i2));
    }

    public static int validateObjectHeader(@NonNull Parcel parcel) {
        int readHeader = readHeader(parcel);
        int readSize = readSize(parcel, readHeader);
        int fieldId = getFieldId(readHeader);
        int dataPosition = parcel.dataPosition();
        if (20293 == fieldId) {
            int i2 = readSize + dataPosition;
            if (i2 >= dataPosition && i2 <= parcel.dataSize()) {
                return i2;
            }
            throw new ParseException("Size read is invalid start=" + dataPosition + " end=" + i2, parcel);
        }
        throw new ParseException("Expected object header. Got 0x" + Integer.toHexString(readHeader), parcel);
    }

    private static void zza(Parcel parcel, int i2, int i3, int i4) {
        if (i3 != i4) {
            String hexString = Integer.toHexString(i3);
            throw new ParseException("Expected size " + i4 + " got " + i3 + " (0x" + hexString + ")", parcel);
        }
    }

    private static void zzb(Parcel parcel, int i2, int i3) {
        int readSize = readSize(parcel, i2);
        if (readSize != i3) {
            String hexString = Integer.toHexString(readSize);
            throw new ParseException("Expected size " + i3 + " got " + readSize + " (0x" + hexString + ")", parcel);
        }
    }
}
