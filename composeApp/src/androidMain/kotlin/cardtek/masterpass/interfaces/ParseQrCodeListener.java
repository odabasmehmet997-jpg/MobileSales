package cardtek.masterpass.interfaces;

import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.results.ParseQrCodeResult;

public interface ParseQrCodeListener {
    void onInternalError(InternalError internalError);
    void onServiceError(ServiceError serviceError);
    void onSuccess(ParseQrCodeResult parseQrCodeResult);
}
