package com.proje.mobilesales.features.product.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.BuildConfig;
import java.util.List;
import java.util.ListIterator;

import com.proje.mobilesales.core.base.BaseDbSalesFiche;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlinx.parcelize.Parceler;

public class ProductSearchOption implements Parcelable {
    private boolean isBarcode;
    private boolean isCode;
    private boolean isDefinition;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<ProductSearchOption> CREATOR = new Creator();

    public static final class Creator implements Parcelable.Creator<ProductSearchOption> {
        public ProductSearchOption createFromParcel(final Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return Companion.create(parcel);
        }
        public ProductSearchOption[] newArray(final int i) {
            return new ProductSearchOption[i];
        }
    }

    public ProductSearchOption() {
        this(false, false, false, 7, null);
    }
    public int describeContents() {
        return 0;
    }
    public void writeToParcel(final Parcel parcel, final int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        ProductSearchOption.Companion.write(this, parcel, i);
    }

    public ProductSearchOption(final boolean z, final boolean z2, final boolean z3) {
        isBarcode = z;
        isCode = z2;
        isDefinition = z3;
    }

    public  ProductSearchOption(final boolean z, final boolean z2, final boolean z3, final int i, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 == (i & 1) && z, 0 == (i & 2) && z2, 0 == (i & 4) && z3);
    }

    public final boolean isBarcode() {
        return isBarcode;
    }

    public final void setBarcode(final boolean z) {
        isBarcode = z;
    }

    public final boolean isCode() {
        return isCode;
    }

    public final void setCode(final boolean z) {
        isCode = z;
    }

    public final boolean isDefinition() {
        return isDefinition;
    }

    public final void setDefinition(final boolean z) {
        isDefinition = z;
    }

    public ProductSearchOption(final String str) {
        this(false, false, false, 7, null);
        List list;
        Intrinsics.checkNotNullParameter(str, "options");
        final List<String> split = new Regex("\\,").split(str, 0);
        if (!split.isEmpty()) {
            final ListIterator<String> listIterator = split.listIterator(split.size());
            while (listIterator.hasPrevious()) {
                if (0 != listIterator.previous().length()) {
                    list = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                    break;
                }
            }
        }
        list = CollectionsKt.emptyList();
        final String[] strArr = (String[]) list.toArray(new String[0]);
        for (final String str2 : strArr) {
            int length = str2.length() - 1;
            int i = 0;
            boolean z = false;
            while (i <= length) {
                final boolean z2 = 0 >= Intrinsics.compare(str2.charAt(!z ? i : length), 32);
                if (!z) {
                    if (!z2) {
                        z = true;
                    } else {
                        i++;
                    }
                } else if (!z2) {
                    break;
                } else {
                    length--;
                }
            }
            if (Intrinsics.areEqual(str2.subSequence(i, length + 1).toString(), "0")) {
                isBarcode = true;
            } else {
                int length2 = str2.length() - 1;
                int i2 = 0;
                boolean z3 = false;
                while (i2 <= length2) {
                    final boolean z4 = 0 >= Intrinsics.compare(str2.charAt(!z3 ? i2 : length2), 32);
                    if (!z3) {
                        if (!z4) {
                            z3 = true;
                        } else {
                            i2++;
                        }
                    } else if (!z4) {
                        break;
                    } else {
                        length2--;
                    }
                }
                if (Intrinsics.areEqual(str2.subSequence(i2, length2 + 1).toString(), BuildConfig.NETSIS_DEMO_PASSWORD)) {
                    isCode = true;
                } else {
                    int length3 = str2.length() - 1;
                    int i3 = 0;
                    boolean z5 = false;
                    while (i3 <= length3) {
                        final boolean z6 = 0 >= Intrinsics.compare(str2.charAt(!z5 ? i3 : length3), 32);
                        if (!z5) {
                            if (!z6) {
                                z5 = true;
                            } else {
                                i3++;
                            }
                        } else if (!z6) {
                            break;
                        } else {
                            length3--;
                        }
                    }
                    if (Intrinsics.areEqual(str2.subSequence(i3, length3 + 1).toString(), ExifInterface.GPS_MEASUREMENT_2D)) {
                        isDefinition = true;
                    }
                }
            }
        }
    }
    protected ProductSearchOption(final Parcel parcel) {
        this(false, false, false, 7, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        final boolean z = false;
        isBarcode = 0 != parcel.readByte();
        isCode = 0 != parcel.readByte();
        isDefinition = 0 != parcel.readByte() || z;
    }
    public static final class Companion implements Parceler<ProductSearchOption> {
        public   Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public BaseDbSalesFiche[] newArray(final int i) {
            return  DefaultImpls.newArray(this, i);
        }

        public void write(final ProductSearchOption productSearchOption, final Parcel parcel, final int i) {
            Intrinsics.checkNotNullParameter(productSearchOption, "<this>");
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            parcel.writeByte(productSearchOption.isBarcode() ? (byte) 1 : 0);
            parcel.writeByte(productSearchOption.isCode() ? (byte) 1 : 0);
            parcel.writeByte(productSearchOption.isDefinition() ? (byte) 1 : 0);
        }

        public ProductSearchOption create(final Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new ProductSearchOption(parcel);
        }
    }
}
