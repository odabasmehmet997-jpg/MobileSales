package cardtek.masterpass.nfc;

import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.NfcReaderResult;

public interface MasterPassNfcReaderListener {
    void onCardReadFail();
    void onCardReadSuccess(NfcReaderResult nfcReaderResult);
    void onInternalError(InternalError internalError);
}
