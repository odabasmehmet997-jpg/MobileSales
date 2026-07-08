package cardtek.masterpass.management;

import android.app.Activity;
import android.nfc.NfcAdapter;
import cardtek.masterpass.attributes.MasterPassEditText;
import cardtek.masterpass.nfc.MasterPassNfcReaderListener;
import cardtek.masterpass.nfc.b;
import cardtek.masterpass.nfc.c;
import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.util.a;

public final class o implements Runnable {
    MasterPassEditText sO = null;
    MasterPassNfcReaderListener sX = null;
    Activity sY = null;
    b sz = null;
    public o(b bVar, MasterPassNfcReaderListener masterPassNfcReaderListener, Activity activity, MasterPassEditText masterPassEditText) {
        this.sz = bVar;
        this.sX = masterPassNfcReaderListener;
        this.sY = activity;
        this.sO = masterPassEditText;
    }
    public o(cardtek.masterpass.management.b b, MasterPassNfcReaderListener masterPassNfcReaderListener, Activity activity, MasterPassEditText masterPassEditText) {
    }
    @Override
    public void run() {
        NfcAdapter defaultAdapter;
        try {
            if (!c.a(this.sz.sg)) {
                InternalError internalError = new InternalError();
                a aVar = a.E015;
                internalError.setErrorCode(aVar.name);
                internalError.setErrorDesc(aVar.value);
                this.sX.onInternalError(internalError);
            }
            if (!c.b(this.sz.sg)) {
                InternalError internalError2 = new InternalError();
                a aVar2 = a.E016;
                internalError2.setErrorCode(aVar2.name);
                internalError2.setErrorDesc(aVar2.value);
                this.sX.onInternalError(internalError2);
            }
            Activity activity = this.sY;
            if (!(activity == null || (defaultAdapter = NfcAdapter.getDefaultAdapter(activity)) == null || activity.isDestroyed())) {
                NfcAdapter.ReaderCallback bVar = null;
                defaultAdapter.enableReaderMode(activity, bVar, b.tM, null);
            }
        } catch (Exception e2) {
            InternalError internalError3 = new InternalError();
            a aVar3 = a.E000;
            internalError3.setErrorCode(aVar3.name);
            internalError3.setErrorDesc(aVar3.value);
            this.sX.onInternalError(internalError3);
            e2.printStackTrace();
        }
    }
}
