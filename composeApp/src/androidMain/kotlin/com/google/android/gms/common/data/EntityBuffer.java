package com.google.android.gms.common.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
;
import java.util.ArrayList;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public abstract class EntityBuffer<T> extends AbstractDataBuffer<T> {
    private boolean zaa;
    private ArrayList zab;

    private final void zab() {
        synchronized (this) {
            if (!this.zaa) {
                int count = Preconditions.checkNotNull(this.mDataHolder).getCount();
                ArrayList arrayList = new ArrayList();
                this.zab = arrayList;
                if (0 < count) {
                    arrayList.add(0);
                    String primaryDataMarkerColumn = getPrimaryDataMarkerColumn();
                    String string = this.mDataHolder.getString(primaryDataMarkerColumn, 0, this.mDataHolder.getWindowIndex(0));
                    int i2 = 1;
                    while (i2 < count) {
                        int windowIndex = this.mDataHolder.getWindowIndex(i2);
                        String string2 = this.mDataHolder.getString(primaryDataMarkerColumn, i2, windowIndex);
                        if (null != string2) {
                            if (!string2.equals(string)) {
                                this.zab.add(Integer.valueOf(i2));
                                string = string2;
                            }
                            i2++;
                        } else {
                            throw new NullPointerException("Missing value for markerColumn: " + primaryDataMarkerColumn + ", at row: " + i2 + ", for window: " + windowIndex);
                        }
                    }
                }
                this.zaa = true;
            }
        }
    }

    
    @NonNull
    @KeepForSdk
    public final T get(final int i2) {
        final int intValue;
        final int intValue2;
        this.zab();
        final int zaa2 = this.zaa(i2);
        int i3 = 0;
        if (0 <= i2 && i2 != zab.size()) {
            if (i2 == zab.size() - 1) {
                intValue = Preconditions.checkNotNull(mDataHolder).getCount();
                intValue2 = ((Integer) zab.get(i2)).intValue();
            } else {
                intValue = ((Integer) zab.get(i2 + 1)).intValue();
                intValue2 = ((Integer) zab.get(i2)).intValue();
            }
            final int i4 = intValue - intValue2;
            if (1 == i4) {
                final int zaa3 = this.zaa(i2);
                final int windowIndex = Preconditions.checkNotNull(mDataHolder).getWindowIndex(zaa3);
                final String childDataMarkerColumn = this.getChildDataMarkerColumn();
                if (null == childDataMarkerColumn || null != this.mDataHolder.getString(childDataMarkerColumn, zaa3, windowIndex)) {
                    i3 = 1;
                }
            } else {
                i3 = i4;
            }
        }
        return this.getEntry(zaa2, i3);
    }

    
    @KeepForSdk
    @Nullable
    public String getChildDataMarkerColumn() {
        return null;
    }

    @KeepForSdk
    public int getCount() {
        this.zab();
        return zab.size();
    }

    
    @NonNull
    @KeepForSdk
    public abstract T getEntry(int i2, int i3);

    
    @NonNull
    @KeepForSdk
    public abstract String getPrimaryDataMarkerColumn();

    
    public final int zaa(final int i2) {
        if (0 <= i2 && i2 < zab.size()) {
            return ((Integer) zab.get(i2)).intValue();
        }
        throw new IllegalArgumentException("Position " + i2 + " is out of bounds for this buffer");
    }
}
