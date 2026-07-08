package com.google.android.gms.location;

import com.google.android.gms.common.internal.Preconditions;

import java.io.Serializable;
import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzk implements Comparator, Serializable {
    zzk() {
    }

    public int compare(final Object obj, final Object obj2) {
        final DetectedActivity detectedActivity = (DetectedActivity) obj;
        final DetectedActivity detectedActivity2 = (DetectedActivity) obj2;
        Preconditions.checkNotNull(detectedActivity);
        Preconditions.checkNotNull(detectedActivity2);
        final int compareTo = Integer.valueOf(detectedActivity2.getConfidence()).compareTo(Integer.valueOf(detectedActivity.getConfidence()));
        return 0 == compareTo ? Integer.valueOf(detectedActivity.getType()).compareTo(Integer.valueOf(detectedActivity2.getType())) : compareTo;
    }
}
