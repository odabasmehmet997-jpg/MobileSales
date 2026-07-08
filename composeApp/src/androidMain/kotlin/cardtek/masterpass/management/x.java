package cardtek.masterpass.management;

import cardtek.masterpass.interfaces.Transaction3DListener;
import cardtek.masterpass.interfaces.ValidateTransaction3DListener;
import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.response.ServiceResult;
import cardtek.masterpass.results.ValidateTransaction3DResult;

/* loaded from: classes.dex */
public final class x implements Transaction3DListener {
    final /* synthetic */ b sz;
    final /* synthetic */ ValidateTransaction3DListener tm;

    public x(b bVar, ValidateTransaction3DListener validateTransaction3DListener) {
        this.sz = bVar;
        this.tm = validateTransaction3DListener;
    }

    @Override // cardtek.masterpass.interfaces.Transaction3DListener
    public void onInternalError(InternalError internalError) {
        this.tm.onInternalError(internalError);
    }

    @Override // cardtek.masterpass.interfaces.Transaction3DListener
    public void onServiceError(ServiceError serviceError) {
        this.tm.onServiceError(serviceError);
    }

    @Override // cardtek.masterpass.interfaces.Transaction3DListener
    public void onServiceResponse(ServiceResponse serviceResponse) {
        ServiceResponse unused = b.sm = serviceResponse;
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setResponseCode(b.sm.getResponseCode());
        serviceResult.setResponseDesc(b.sm.getResponseDesc());
        this.tm.onVerifyUser(serviceResult);
    }

    @Override // cardtek.masterpass.interfaces.Transaction3DListener
    public void onSuccess(ValidateTransaction3DResult validateTransaction3DResult) {
        this.tm.onSuccess(validateTransaction3DResult);
    }
}
