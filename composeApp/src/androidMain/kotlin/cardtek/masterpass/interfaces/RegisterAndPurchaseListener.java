package cardtek.masterpass.interfaces;

import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.response.ServiceResult;
import cardtek.masterpass.results.RegisterAndPurchaseResult;

/* loaded from: classes.dex */
public interface RegisterAndPurchaseListener {
    void onInternalError(InternalError internalError);

    void onServiceError(ServiceError serviceError);

    void onSuccess(RegisterAndPurchaseResult registerAndPurchaseResult);

    void onVerifyUser(ServiceResult serviceResult);
}
