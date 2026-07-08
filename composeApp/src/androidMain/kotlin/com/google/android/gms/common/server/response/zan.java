package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ShowFirstParty
@SafeParcelable.Class
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zan extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zan> CREATOR = new zao();
    @SafeParcelable.VersionField
    final int zaa;
    private final HashMap zab;
    @SafeParcelable.Field
    private final String zac;

    @SafeParcelable.Constructor
    zan(@SafeParcelable.Param int i2, @SafeParcelable.Param ArrayList arrayList, @SafeParcelable.Param String str) {
        this.zaa = i2;
        HashMap hashMap = new HashMap();
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            zal zal = (zal) arrayList.get(i3);
            String str2 = zal.zab;
            HashMap hashMap2 = new HashMap();
            int size2 = Preconditions.checkNotNull(zal.zac).size();
            for (int i4 = 0; i4 < size2; i4++) {
                zam zam = (zam) zal.zac.get(i4);
                hashMap2.put(zam.zab, zam.zac);
            }
            hashMap.put(str2, hashMap2);
        }
        this.zab = hashMap;
        this.zac = Preconditions.checkNotNull(str);
        zad();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String str : this.zab.keySet()) {
            sb.append(str);
            sb.append(":\n");
            Map map = (Map) this.zab.get(str);
            for (String str2 : map.keySet()) {
                sb.append("  ");
                sb.append(str2);
                sb.append(": ");
                sb.append(map.get(str2));
            }
        }
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        ArrayList arrayList = new ArrayList();
        for (String str : this.zab.keySet()) {
            arrayList.add(new zal(str, (Map) this.zab.get(str)));
        }
        SafeParcelWriter.writeTypedList(parcel, 2, arrayList, false);
        SafeParcelWriter.writeString(parcel, 3, this.zac, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public String zaa() {
        return this.zac;
    }

    @Nullable
    public Map zab(String str) {
        return (Map) this.zab.get(str);
    }

    public void zad() {
        for (String str : this.zab.keySet()) {
            Map map = (Map) this.zab.get(str);
            for (String str2 : map.keySet()) {
                ((FastJsonResponse.Field) map.get(str2)).zai(this);
            }
        }
    }
}
