package cardtek.masterpass.nfc;

import android.content.Context;
import android.nfc.NfcAdapter;

public final class c {
    public static boolean a(Context context) {
        try {
            return NfcAdapter.getDefaultAdapter(context) != null;
        } catch (UnsupportedOperationException unused) {
            return false;
        }
    }
    public static boolean b(Context context) {
        try {
            return NfcAdapter.getDefaultAdapter(context).isEnabled();
        } catch (UnsupportedOperationException unused) {
            return false;
        }
    }
}
