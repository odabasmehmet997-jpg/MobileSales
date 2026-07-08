package cardtek.masterpass.interfaces;

import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.response.ServiceResult;

public interface LinkCardToClientListener {
    void onInternalError(InternalError internalError);
    void onServiceError(ServiceError serviceError);
    void onVerifyUser(ServiceResult serviceResult);
}
