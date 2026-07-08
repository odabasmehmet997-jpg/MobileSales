package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @param zza synthetic
 */ /* compiled from: com.google.android.gms:play-services-tasks@@18.1.0 */
record zzaa(Collection zza) implements Continuation {
    public Object then(@NonNull Task task) throws Exception {
        ArrayList arrayList = new ArrayList();
        for (Task result : this.zza) {
            arrayList.add(result.getResult());
        }
        return arrayList;
    }
}
