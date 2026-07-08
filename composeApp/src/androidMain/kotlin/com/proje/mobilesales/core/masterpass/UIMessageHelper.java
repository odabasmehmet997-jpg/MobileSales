package com.proje.mobilesales.core.masterpass;

import com.proje.mobilesales.R;
import com.proje.mobilesales.core.utils.ContextUtils;

public class UIMessageHelper {

    public static AccountStatusResult getAccountStatusResult(String str) {
        char c2 = 1;
        AccountStatusResult accountStatusResult = new AccountStatusResult();
        if (str.length() >= 7) {
            String substring = str.substring(0, 7);
            if (substring.charAt(1) == '0') {
                accountStatusResult.setMessage(ContextUtils.getmContext().getString(R.string.str_you_dont_have_masterpass_account));
                accountStatusResult.setAccountStatus(AccountStatus.CannotBeUsed);
                return accountStatusResult;
            }
            if (substring.charAt(1) == '2') {
                accountStatusResult.setMessage(ContextUtils.getmContext().getString(R.string.str_dont_have_any_card_linked_with_masterpass));
                accountStatusResult.setAccountStatus(AccountStatus.CannotBeUsed);
                return accountStatusResult;
            }
            switch (substring.hashCode()) {
                case 1070509616:
                    if (substring.equals("0000000")) {
                        c2 = 0;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1099138767:
                    break;
                case 1099139728:
                    if (substring.equals("0100100")) {
                        c2 = 2;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1099168558:
                    if (substring.equals("0101000")) {
                        c2 = 3;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1099169519:
                    if (substring.equals("0101100")) {
                        c2 = 4;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1100062289:
                    if (substring.equals("0110001")) {
                        c2 = 5;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1100063250:
                    if (substring.equals("0110101")) {
                        c2 = 6;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1100092079:
                    if (substring.equals("0111000")) {
                        c2 = 7;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1100092080:
                    if (substring.equals("0111001")) {
                        c2 = '\b';
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1100093040:
                    if (substring.equals("0111100")) {
                        c2 = '\t';
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1958013298:
                    if (substring.equals("1000001")) {
                        c2 = '\n';
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1986642449:
                    if (substring.equals("1100001")) {
                        c2 = 11;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1986643410:
                    if (substring.equals("1100101")) {
                        c2 = '\f';
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1986672239:
                    if (substring.equals("1101000")) {
                        c2 = '\r';
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1986673201:
                    if (substring.equals("1101101")) {
                        c2 = 14;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1987565970:
                    if (substring.equals("1110001")) {
                        c2 = 15;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1987566930:
                    if (substring.equals("1110100")) {
                        c2 = 16;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1987595760:
                    if (substring.equals("1111000")) {
                        c2 = 17;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1987595761:
                    if (substring.equals("1111001")) {
                        c2 = 18;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1987595792:
                    if (substring.equals("1111011")) {
                        c2 = 19;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1987596753:
                    if (substring.equals("1111111")) {
                        c2 = 20;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                default:
                    c2 = '\uffff';
                    break;
            }
            switch (c2) {
                case 0:
                    accountStatusResult.setMessage(ContextUtils.getmContext().getString(R.string.str_you_dont_have_masterpass_account));
                    accountStatusResult.setAccountStatus(AccountStatus.CannotBeUsed);
                    break;
                case 1:
                    accountStatusResult.setMessage(ContextUtils.getmContext().getString(R.string.str_dont_have_any_card_linked_with_masterpass));
                    accountStatusResult.setAccountStatus(AccountStatus.CannotBeUsed);
                    break;
                case 2:
                    accountStatusResult.setMessage("MasterPass hesab\u0131n\u0131z bloklu ve hesab\u0131n\u0131za kay\u0131tl\u0131 herhangi bir kart\u0131n\u0131z bulunmamaktad\u0131r");
                    accountStatusResult.setAccountStatus(AccountStatus.CannotBeUsed);
                    break;
                case 3:
                    accountStatusResult.setMessage("\u00dcye i\u015fyerine linkli MasterPass hesab\u0131n\u0131z var fakat bu hesapta kay\u0131tl\u0131 kart\u0131n\u0131z bulunmamaktad\u0131r");
                    accountStatusResult.setAccountStatus(AccountStatus.CannotBeUsed);
                    break;
                case 4:
                    accountStatusResult.setMessage("\u00dcye i\u015fyerine linkli MasterPass hesab\u0131n\u0131z var fakat bu hesap bloklu ve hesapta kay\u0131tl\u0131 kart\u0131n\u0131z bulunmamaktad\u0131r");
                    accountStatusResult.setAccountStatus(AccountStatus.CannotBeUsed);
                    break;
                case 5:
                    accountStatusResult.setMessage(ContextUtils.getmContext().getString(R.string.str_link_your_card_with_masterpass));
                    accountStatusResult.setAccountStatus(AccountStatus.NotLinked);
                    break;
                case 6:
                    accountStatusResult.setMessage("MasterPass hesab\u0131n\u0131z bulunmaktad\u0131r ve bu hesap bloklu");
                    accountStatusResult.setAccountStatus(AccountStatus.CannotBeUsed);
                    break;
                case 7:
                case '\b':
                case 17:
                case 18:
                    accountStatusResult.setMessage("\u00dcye i\u015fyerine linkli MasterPass hesab\u0131n\u0131z ve bu hesapta kay\u0131tl\u0131 en az bir kart\u0131n\u0131z bulunmaktad\u0131r.");
                    accountStatusResult.setAccountStatus(AccountStatus.Linked);
                    break;
                case '\t':
                    accountStatusResult.setMessage("\u00dcye i\u015fyerine linkli MasterPass hesab\u0131n\u0131z var ve bu hesap bloklu");
                    accountStatusResult.setAccountStatus(AccountStatus.CannotBeUsed);
                    break;
                case '\n':
                    accountStatusResult.setMessage("\u00dcye i\u015fyerine kay\u0131tl\u0131 en az bir kart\u0131n\u0131z var fakat MasterPass hesab\u0131n\u0131z bulunmamaktad\u0131r");
                    accountStatusResult.setAccountStatus(AccountStatus.CannotBeUsed);
                    break;
                case 11:
                    accountStatusResult.setMessage("\u00dcye i\u015fyerinde kay\u0131tl\u0131 en az bir kart\u0131n\u0131z ve Masterpass hesab\u0131n\u0131z var fakat Masterpass hesab\u0131n\u0131zda kart bulunmamaktad\u0131r");
                    accountStatusResult.setAccountStatus(AccountStatus.CannotBeUsed);
                    break;
                case '\f':
                    accountStatusResult.setMessage("\u00dcye i\u015fyerinde kay\u0131tl\u0131 en az bir kart\u0131n\u0131z ve Masterpass hesab\u0131n\u0131z var fakat Masterpass hesab\u0131n\u0131zda kart bulunmamaktad\u0131r");
                    accountStatusResult.setAccountStatus(AccountStatus.CannotBeUsed);
                    break;
                case '\r':
                    accountStatusResult.setMessage("Masterpass hesab\u0131n\u0131zda kart\u0131n\u0131z bulunmamaktad\u0131r fakat  Masterpass hesab\u0131n\u0131z \u00fcye i\u015fyerine linkli.");
                    accountStatusResult.setAccountStatus(AccountStatus.CannotBeUsed);
                    break;
                case 14:
                    accountStatusResult.setMessage("Masterpass hesab\u0131n\u0131zda kart bulunmamaktad\u0131r. Masterpass hesab\u0131n\u0131z \u00fcye i\u015fyerine linkli ve bu hesap bloklu.");
                    accountStatusResult.setAccountStatus(AccountStatus.CannotBeUsed);
                    break;
                case 15:
                    accountStatusResult.setMessage("\u00dcye i\u015fyerine kay\u0131tl\u0131 MasterPass hesab\u0131n\u0131z bulunmaktad\u0131r.");
                    accountStatusResult.setAccountStatus(AccountStatus.Linked);
                    break;
                case 16:
                    accountStatusResult.setMessage("Masterpass hesab\u0131n\u0131z bulunmaktad\u0131r fakat hesab\u0131n\u0131z bloklu.");
                    accountStatusResult.setAccountStatus(AccountStatus.CannotBeUsed);
                    break;
                case 19:
                    accountStatusResult.setMessage("Masterpass hesab\u0131 \u00fcye i\u015fyerine linkli. Fakat telefon numaras\u0131 ba\u015fka bir \u00fcye i\u015fyeri taraf\u0131dan g\u00fcncellenmi\u015f.");
                    accountStatusResult.setAccountStatus(AccountStatus.CannotBeUsed);
                    break;
                case 20:
                    accountStatusResult.setMessage("Masterpass hesab\u0131n\u0131z bloklu");
                    accountStatusResult.setAccountStatus(AccountStatus.CannotBeUsed);
                    break;
            }
            if (accountStatusResult.getAccountStatus() == null) {
                accountStatusResult.setMessage(ContextUtils.getmContext().getString(R.string.str_masterpass_account_not_suitable));
                accountStatusResult.setAccountStatus(AccountStatus.CannotBeUsed);
            }
            return accountStatusResult;
        }
        accountStatusResult.setMessage(ContextUtils.getmContext().getString(R.string.str_masterpass_account_not_suitable));
        accountStatusResult.setAccountStatus(AccountStatus.CannotBeUsed);
        return accountStatusResult;
    }
}
