package com.google.android.gms.location;

import com.google.android.gms.common.internal.Preconditions;

import java.io.Serializable;
import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzg implements Comparator, Serializable {
    zzg() {
    }

    public int compare(final Object obj, final Object obj2) {
        final ActivityTransition activityTransition = (ActivityTransition) obj;
        final ActivityTransition activityTransition2 = (ActivityTransition) obj2;
        Preconditions.checkNotNull(activityTransition);
        Preconditions.checkNotNull(activityTransition2);
        final int activityType = activityTransition.getActivityType();
        final int activityType2 = activityTransition2.getActivityType();
        if (activityType != activityType2) {
            return activityType >= activityType2 ? 1 : -1;
        }
        final int transitionType = activityTransition.getTransitionType();
        final int transitionType2 = activityTransition2.getTransitionType();
        if (transitionType == transitionType2) {
            return 0;
        }
        return transitionType >= transitionType2 ? 1 : -1;
    }
}
