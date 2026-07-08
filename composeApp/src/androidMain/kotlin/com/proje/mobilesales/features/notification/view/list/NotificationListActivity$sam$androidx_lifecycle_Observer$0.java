package com.proje.mobilesales.features.notification.view.list;

import androidx.lifecycle.Observer;
import kotlin.Function;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationListActivity.kt */

final class NotificationListActivitysamandroidx_lifecycle_Observer0 implements Observer, FunctionAdapter {
    private final Function1 function;

    
    public NotificationListActivitysamandroidx_lifecycle_Observer0(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "function");
        this.function = function1;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Observer) || !(obj instanceof FunctionAdapter)) {
            return false;
        }
        return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
    }

    
    public Function<?> getFunctionDelegate() {
        return this.function;
    }

    public int hashCode() {
        return getFunctionDelegate().hashCode();
    }

    
    public void onChanged(Object obj) {
        this.function.invoke(obj);
    }
}
