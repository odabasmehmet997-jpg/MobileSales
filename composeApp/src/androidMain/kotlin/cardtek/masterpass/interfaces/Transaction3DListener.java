package cardtek.masterpass.interfaces;

import cardtek.masterpass.management.ServiceResponse;
import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.results.ValidateTransaction3DResult;

/* loaded from: classes.dex */
public interface Transaction3DListener {
    void onInternalError(InternalError internalError);

    void onServiceError(ServiceError serviceError);

    void onServiceResponse(ServiceResponse serviceResponse);

    void onSuccess(ValidateTransaction3DResult validateTransaction3DResult);
}
