package cardtek.masterpass.management;

import android.app.Activity;
import android.content.Context;
import android.widget.CompoundButton;
import androidx.annotation.RequiresApi;
import cardtek.masterpass.attributes.MasterPassEditText;
import cardtek.masterpass.cardIO.a;
import cardtek.masterpass.interfaces.DirectPurchaseListener;
import cardtek.masterpass.interfaces.ForgotPasswordListener;
import cardtek.masterpass.interfaces.MoneySendListener;
import cardtek.masterpass.interfaces.PurchaseListener;
import cardtek.masterpass.interfaces.RegisterAndPurchaseListener;
import cardtek.masterpass.interfaces.RegisterCardListener;
import cardtek.masterpass.interfaces.UpdateUserListerner;
import cardtek.masterpass.nfc.MasterPassNfcReaderListener;
import cardtek.masterpass.util.ValueType;

/* loaded from: classes.dex */
public final class b {
    public static a sk = null;
    public static ServiceResponse sm = null;
    private static final boolean sn = false;
    private Context sg;
    a sh;
    EncryptionHelper si = new EncryptionHelper();
    private final MasterPassTextHelper sj = new MasterPassTextHelper();
    public String sl;
    private cardtek.masterpass.nfc.b so;
    public b(Context context, String str) {
        this.sg = context;
        this.sh = new a(context) {
            @Override
            public void a() {

            }

            @Override
            public void a(String str, String str2, String str3) {

            }
        };
        this.sl = str;
    }
    public b(String sx, String encData, String sy) {
    }
    public void directPurchase(String str, MasterPassEditText masterPassEditText, int i2, int i3, int i4, String str2, String str3, Integer num, String str4, String str5, String str6, MasterPassEditText masterPassEditText2, CompoundButton compoundButton, DirectPurchaseListener directPurchaseListener) {
        new Thread(new g(this, compoundButton, directPurchaseListener, masterPassEditText, masterPassEditText2, i2, i3, str6, str, i4, str2, str3, num, str4, str5)).start();
    }
    public void forgotPassword(String str, String str2, String str3, MasterPassEditText masterPassEditText, ForgotPasswordListener forgotPasswordListener) {
        new Thread(new z(this, masterPassEditText, forgotPasswordListener, str, str2, str3)).start();
    }
    public void moneySend(String str, cardtek.masterpass.util.b bVar, String str2, String str3, int i2, String str4, String str5, Integer num, String str6, String str7, MasterPassEditText masterPassEditText, MoneySendListener moneySendListener) {
        new Thread(new r(this, bVar, str2, moneySendListener, str3, masterPassEditText, str, i2, str4, str5, num, str6, str7)).start();
    }
    public void purchase(String str, String str2, int i2, String str3, String str4, Integer num, String str5, String str6, MasterPassEditText masterPassEditText, PurchaseListener purchaseListener) {
        new Thread(new u(this, str2, purchaseListener, masterPassEditText, str, i2, str3, str4, num, str5, str6)).start();
    }
    public void registerAndPurchase(String str, MasterPassEditText masterPassEditText, int i2, int i3, int i4, String str2, String str3, String str4, Integer num, String str5, String str6, String str7, MasterPassEditText masterPassEditText2, CompoundButton compoundButton, RegisterAndPurchaseListener registerAndPurchaseListener) {
        new Thread(new i(this, compoundButton, registerAndPurchaseListener, masterPassEditText, masterPassEditText2, i2, i3, str7, str3, str, i4, str2, str4, num, str5, str6)).start();
    }
    public void registerCard(String str, MasterPassEditText masterPassEditText, int i2, int i3, String str2, String str3, String str4, MasterPassEditText masterPassEditText2, CompoundButton compoundButton, RegisterCardListener registerCardListener) {
        new Thread(new c(this, compoundButton, registerCardListener, masterPassEditText, masterPassEditText2, i2, i3, str4, str2, str, str3)).start();
    }
    @RequiresApi(api = 19)
    public void startNfcReader(Activity activity, MasterPassEditText masterPassEditText, MasterPassNfcReaderListener masterPassNfcReaderListener) {
        new Thread(new o(this, masterPassNfcReaderListener, activity, masterPassEditText)).start();
    }
    public void updateUser(String str, String str2, String str3, ValueType valueType, String str4, UpdateUserListerner updateUserListerner) {
        new Thread(new d(this, str, str2, str3, valueType, str4, updateUserListerner)).start();
    }
}
