package cardtek.masterpass.cardIO;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import cardtek.masterpass.management.b;
import cardtek.masterpass.util.MasterPassInfo;
import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class MasterPassCardIOActivity extends Activity {
    private static final int CardIOrequestCode = 3611;
    protected void onActivityResult(int i2, int i3, Intent intent) {
        String str;
        String str2;
        super.onActivityResult(i2, i3, intent);
        if (i2 == CardIOrequestCode) {
            if (intent == null || !intent.hasExtra("io.card.payment.scanResult")) {
                b.sk.a();
            } else {
                CreditCard parcelableExtra = intent.getParcelableExtra("io.card.payment.scanResult");
                String str3 = parcelableExtra.cardNumber;
                if (parcelableExtra.isExpiryValid()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(parcelableExtra.expiryMonth);
                    str = sb.toString();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(parcelableExtra.expiryYear);
                    str2 = sb2.toString();
                } else {
                    str = "";
                    str2 = str;
                }
                b.sk.a(str3, str, str2);
            }
            finish();
        }
    }

    public void onCreate(Bundle bundle, PersistableBundle persistableBundle) {
        super.onCreate(bundle, persistableBundle);
    }
    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        Intent intent = new Intent(this, CardIOActivity.class);
        intent.putExtra("io.card.payment.guideColor", MasterPassInfo.getCardIOFrameColor());
        intent.putExtra("io.card.payment.hideLogo", true);
        intent.putExtra("io.card.payment.suppressConfirmation", true);
        intent.putExtra("io.card.payment.suppressManual", true);
        if (!MasterPassInfo.getCardIOInfoLanguage().isEmpty()) {
            intent.putExtra("io.card.payment.languageOrLocale", MasterPassInfo.getCardIOInfoLanguage());
        }
        startActivityForResult(intent, CardIOrequestCode);
    }
}
