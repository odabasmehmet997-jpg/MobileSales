package cardtek.masterpass.interfaces;

import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.response.ServiceResult;
import cardtek.masterpass.results.MoneySendResult;

public interface MoneySendListener {
    void onInternalError(InternalError internalError);
    void onServiceError(ServiceError serviceError);
    void onSuccess(MoneySendResult moneySendResult);
    void onVerifyUser(ServiceResult serviceResult);
}
