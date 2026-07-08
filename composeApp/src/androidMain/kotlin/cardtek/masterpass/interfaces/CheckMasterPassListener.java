package cardtek.masterpass.interfaces;

import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.results.CheckMasterPassResult;

public interface CheckMasterPassListener {
    void onInternalError(InternalError internalError);
    void onServiceError(ServiceError serviceError);
    void onSuccess(CheckMasterPassResult checkMasterPassResult);
}
