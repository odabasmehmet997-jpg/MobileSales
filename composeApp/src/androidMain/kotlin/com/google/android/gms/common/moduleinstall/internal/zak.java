package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @param zaa synthetic
 */ /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public record zak(AtomicReference zaa) implements SuccessContinuation {
    public Task then(Object obj) {
        Void voidR = (Void) obj;
        final int i2 = zay.r8clinit;
        AtomicReference atomicReference = this.zaa;
        if (null != atomicReference.get()) {
            return Tasks.forResult((ModuleInstallResponse) atomicReference.get());
        }
        return Tasks.forException(new ApiException(Status.RESULT_INTERNAL_ERROR));
    }
}
