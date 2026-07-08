package cardtek.masterpass.interfaces;

import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.response.ServiceResult;
import cardtek.masterpass.results.ValidateTransaction3DResult;

/* loaded from: classes.dex */
public interface ValidateTransaction3DListener {
    void onInternalError(InternalError internalError);

    void onServiceError(ServiceError serviceError);

    void onSuccess(ValidateTransaction3DResult validateTransaction3DResult);

    void onVerifyUser(ServiceResult serviceResult);
}
