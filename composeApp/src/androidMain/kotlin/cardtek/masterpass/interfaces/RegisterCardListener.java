package cardtek.masterpass.interfaces;

import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.response.ServiceResult;
import cardtek.masterpass.results.RegisterCardResult;

public interface RegisterCardListener {
    void onInternalError(InternalError internalError);
    void onServiceError(ServiceError serviceError);
    void onSuccess(RegisterCardResult registerCardResult);
    void onVerifyUser(ServiceResult serviceResult);
}
