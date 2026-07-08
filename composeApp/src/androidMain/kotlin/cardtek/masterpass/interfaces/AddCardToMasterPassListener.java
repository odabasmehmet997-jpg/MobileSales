package cardtek.masterpass.interfaces;

import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.response.ServiceResult;
import cardtek.masterpass.results.AddCardToMasterPassResult;

public interface AddCardToMasterPassListener {
    void onInternalError(InternalError internalError);
    void onServiceError(ServiceError serviceError);
    void onSuccess(AddCardToMasterPassResult addCardToMasterPassResult);
    void onVerifyUser(ServiceResult serviceResult);
}
