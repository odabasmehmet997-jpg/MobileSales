package cardtek.masterpass.interfaces;

import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.response.ServiceResult;
import cardtek.masterpass.results.DirectPurchaseResult;

/* loaded from: classes.dex */
public interface DirectPurchaseListener {
    void onInternalError(InternalError internalError);

    void onServiceError(ServiceError serviceError);

    void onSuccess(DirectPurchaseResult directPurchaseResult);

    void onVerifyUser(ServiceResult serviceResult);
}
