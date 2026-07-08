package cardtek.masterpass.interfaces;

import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.response.ServiceResult;
import cardtek.masterpass.results.ValidateTransactionResult;

/* loaded from: classes.dex */
public interface ValidateTransactionListener {
    void onInternalError(InternalError internalError);

    void onServiceError(ServiceError serviceError);

    void onSuccess(ValidateTransactionResult validateTransactionResult);

    void onVerifyUser(ServiceResult serviceResult);
}
