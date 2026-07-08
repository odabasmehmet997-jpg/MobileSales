package cardtek.masterpass.interfaces;

import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.results.ResendOtpResult;

public interface ResendOtpListener {
    void onInternalError(InternalError internalError);
    void onServiceError(ServiceError serviceError);
    void onSuccess(ResendOtpResult resendOtpResult);
}
