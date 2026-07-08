package com.example.privacy_policy_lib;

import androidx.lifecycle.Observer;
import kotlin.Function;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.Intrinsics;
 
final class PrivacyPolicyDialogFragmentsamandroidx_lifecycle_Observer0 implements Observer, FunctionAdapter {
    private final Function1 function;
    PrivacyPolicyDialogFragmentsamandroidx_lifecycle_Observer0(Function1 function) {
        Intrinsics.checkNotNullParameter(function, "function");
        this.function = function;
    }
    public   boolean equals(Object obj) {
        if ((obj instanceof Observer) && (obj instanceof FunctionAdapter)) {
            return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
        }
        return false;
    }
    public   Function<?> getFunctionDelegate() {
        return ( Function<?> ) this.function;
    }
    public   int hashCode() {
        return getFunctionDelegate().hashCode();
    }
    public   void onChanged(Object obj) {
        this.function.invoke(obj);
    }
}
