package com.google.android.gms.common.moduleinstall.internal;

import android.os.Parcelable;
import com.google.android.gms.common.Feature;

import java.io.Serializable;
import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zab implements Comparator, Serializable {
    public static final zab zaa = new zab();

    private zab() {
    }

    public int compare(Object obj, Object obj2) {
        Feature feature = (Feature) obj;
        Feature feature2 = (Feature) obj2;
        Parcelable.Creator<ApiFeatureRequest> creator = ApiFeatureRequest.CREATOR;
        if (!feature.getName().equals(feature2.getName())) {
            return feature.getName().compareTo(feature2.getName());
        }
        return (feature.getVersion() > feature2.getVersion() ? 1 : (feature.getVersion() == feature2.getVersion() ? 0 : -1));
    }
}
