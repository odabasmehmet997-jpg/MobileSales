package cardtek.masterpass.interfaces;

import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.results.DeleteCardResult;

/* loaded from: classes.dex */
public interface DeleteCardListener {
    void onInternalError(InternalError internalError);

    void onServiceError(ServiceError serviceError);

    void onSuccess(DeleteCardResult deleteCardResult);
}
