package cardtek.masterpass.interfaces;

import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.response.ServiceResult;
import cardtek.masterpass.results.PurchaseResult;

/* loaded from: classes.dex */
public interface PurchaseListener {
    void onInternalError(InternalError internalError);

    void onServiceError(ServiceError serviceError);

    void onSuccess(PurchaseResult purchaseResult);

    void onVerifyUser(ServiceResult serviceResult);
}
