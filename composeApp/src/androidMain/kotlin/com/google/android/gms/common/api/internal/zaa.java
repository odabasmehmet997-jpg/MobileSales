package com.google.android.gms.common.api.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class zaa extends LifecycleCallback {
    private List zaa;

    zaa(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment);
    }

    public   void onStop() {
        List list;
        synchronized (this) {
            list = this.zaa;
            this.zaa = new ArrayList();
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
    }
}
