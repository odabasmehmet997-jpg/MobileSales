package cardtek.masterpass.cardIO;

import cardtek.masterpass.response.InternalError;

public interface MasterPassCardIOListener {
    void onCancelled();
    void onInternalError(InternalError internalError);
    void onSuccess(String str, String str2);
}
