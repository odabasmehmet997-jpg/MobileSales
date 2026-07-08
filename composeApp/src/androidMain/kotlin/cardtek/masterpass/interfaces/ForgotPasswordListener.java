package cardtek.masterpass.interfaces;

import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.response.ServiceResult;

/* loaded from: classes.dex */
public interface ForgotPasswordListener {
    void onInternalError(InternalError internalError);

    void onServiceError(ServiceError serviceError);

    void onVerifyUser(ServiceResult serviceResult);
}
