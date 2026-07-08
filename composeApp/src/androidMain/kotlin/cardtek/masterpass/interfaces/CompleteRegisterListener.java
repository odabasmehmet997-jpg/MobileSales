package cardtek.masterpass.interfaces;

import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.response.ServiceResult;
import cardtek.masterpass.results.CompleteRegisterResult;

public interface CompleteRegisterListener {
    void onInternalError(InternalError internalError);
    void onServiceError(ServiceError serviceError);
    void onSuccess(CompleteRegisterResult completeRegisterResult);
    void onVerifyUser(ServiceResult serviceResult);
}
