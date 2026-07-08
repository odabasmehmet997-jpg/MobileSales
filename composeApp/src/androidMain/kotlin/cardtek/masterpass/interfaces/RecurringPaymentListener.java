package cardtek.masterpass.interfaces;

import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.response.ServiceResult;
import cardtek.masterpass.results.RecurringPaymentResult;

/* loaded from: classes.dex */
public interface RecurringPaymentListener {
    void onInternalError(InternalError internalError);

    void onServiceError(ServiceError serviceError);

    void onSuccess(RecurringPaymentResult recurringPaymentResult);

    void onVerifyUser(ServiceResult serviceResult);
}
