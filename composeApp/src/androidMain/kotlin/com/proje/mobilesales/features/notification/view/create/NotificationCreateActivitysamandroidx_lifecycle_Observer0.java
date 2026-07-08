package com.proje.mobilesales.features.notification.view.create;

import androidx.lifecycle.Observer;
import kotlin.Function;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.Intrinsics;
 
final class NotificationCreateActivitysamandroidx_lifecycle_Observer0 implements Observer, FunctionAdapter {
    private final  Function1 function;

    NotificationCreateActivitysamandroidx_lifecycle_Observer0(final Function1 function) {
        Intrinsics.checkNotNullParameter(function, "function");
        this.function = function;
    }
    public   boolean equals(final Object obj) {
        if ((obj instanceof Observer) && (obj instanceof FunctionAdapter)) {
            return Intrinsics.areEqual(this.getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
        }
        return false;
    }
    public   Function<?> getFunctionDelegate() {
        return (Function<?>) function;
    }
    public   int hashCode() {
        return this.getFunctionDelegate().hashCode();
    }
    public   void onChanged(final Object obj) {
        function.invoke(obj);
    }
}
