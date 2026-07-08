package cardtek.masterpass.nfc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import cardtek.masterpass.util.MasterPassInfo;

import java.io.IOException;

public final class b implements NfcAdapter.ReaderCallback, g.b {
    public static int tM = 387;
    public Context sg;
    private IsoDep tN;
    a tO;
    public static void disableReaderMode(Activity activity) {
        NfcAdapter defaultAdapter;
        if (activity != null && (defaultAdapter = NfcAdapter.getDefaultAdapter(activity)) != null && !activity.isDestroyed()) {
            defaultAdapter.disableReaderMode(activity);
        }
    }
    public void onTagDiscovered(Tag tag) {
        boolean z = false;
        try {
            for (String str : tag.getTechList()) {
                if (str.equalsIgnoreCase(IsoDep.class.getName())) {
                    IsoDep isoDep = IsoDep.get(tag);
                    this.tN = isoDep;
                    if (isoDep != null) {
                        isoDep.connect();
                        this.tN.setTimeout(15000);
                        a aVar = new a(this) {
                            @Override
                            public void onError() {

                            }

                            @Override
                            public void p() {

                            }

                            @Override
                            public void a(c cVar) {

                            }
                        };
                        String str2 = MasterPassInfo.TAG;
                        byte[] transceive = aVar.rA.transceive(new c(a.a.SELECT, aVar.rB ? a.ry : a.rz).h());
                        a.c cVar = a.c.SW_9000;
                        if (e.a(transceive, cVar)) {
                            byte[] b2 = f.b(transceive, c.b.dS);
                            if (b2 != null) {
                                int j2 = h.b.j(b2);
                                g.b bVar = aVar.rA;
                                a.a aVar2 = a.a.READ_RECORD;
                                int i2 = (j2 << 3) | 4;
                                byte[] transceive2 = bVar.transceive(new c(aVar2, j2, i2, 0).h());
                                transceive = e.a(transceive2, a.c.SW_6C) ? aVar.rA.transceive(new c(aVar2, j2, i2, transceive2[transceive2.length - 1]).h()) : transceive2;
                            }
                            if (e.a(transceive, cVar)) {
                                z = false;
                                for (byte[] bArr : a.d(transceive)) {
                                    String str3 = MasterPassInfo.TAG;
                                    byte[] b3 = f.b(transceive, c.b.dK);
                                    z = aVar.a(bArr, b3 != null ? new String(b3) : null);
                                    if (z) {
                                        break;
                                    }
                                }
                                if (!z) {
                                    aVar.rC.it = true;
                                }
                            }
                        }
                        if (!z) {
                            aVar.f();
                        }
                        this.tO.a(aVar.rC);
                        return;
                    }
                    return;
                }
            }
            this.tO.p();
        } catch (Exception e2) {
            e2.printStackTrace();
            this.tO.onError();
        }
    }

    @Override
    public byte[] transceive(byte[] bArr) throws IOException {
        return this.tN.transceive(bArr);
    }
}
