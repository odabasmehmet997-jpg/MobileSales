package cardtek.masterpass.management;

import a.b;
import cardtek.masterpass.attributes.MasterPassEditText;
import cardtek.masterpass.nfc.a;
import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.NfcReaderResult;
import cardtek.masterpass.util.CardType;
import e.c;
import java.text.SimpleDateFormat;

/**
 * @param sZ synthetic
 */ /* loaded from: classes.dex */
record p(o sZ) implements a {

    @Override // cardtek.masterpass.nfc.a
    public void a(c cVar) {
        NfcReaderResult nfcReaderResult;
        CardType cardType;
        String str = cVar.cardNumber;
        int parseInt = Integer.parseInt(new SimpleDateFormat("yyyy").format(cVar.f32io));
        int parseInt2 = Integer.parseInt(new SimpleDateFormat("MM").format(cVar.f32io));
        o oVar = this.sZ;
        if (oVar.sO != null) {
            oVar.sY.runOnUiThread(new q(this, str));
            nfcReaderResult = new NfcReaderResult();
            nfcReaderResult.setCardNumber(this.sZ.sO);
            b bVar = cVar.ip;
            if (bVar == b.MASTER_CARD) {
                cardType = CardType.MASTERCARD;
            } else if (bVar == b.VISA) {
                cardType = CardType.VISA;
            } else if (bVar == b.AMERICAN_EXPRESS) {
                cardType = CardType.AMEX;
            }
            nfcReaderResult.setCardType(cardType);
        } else {
            MasterPassEditText masterPassEditText = new MasterPassEditText(this.sZ.sz.sg);
            masterPassEditText.setType(cardtek.masterpass.attributes.b.CARDNUMBER);
            this.sZ.sz.sj.setText(masterPassEditText, str);
            nfcReaderResult = new NfcReaderResult();
            nfcReaderResult.setCardNumber(masterPassEditText);
            b bVar2 = cVar.ip;
            if (bVar2 == b.MASTER_CARD) {
                cardType = CardType.MASTERCARD;
            } else if (bVar2 == b.VISA) {
                cardType = CardType.VISA;
            } else if (bVar2 == b.AMERICAN_EXPRESS) {
                cardType = CardType.AMEX;
            }
            nfcReaderResult.setCardType(cardType);
        }
        nfcReaderResult.setExpireMonth(parseInt2);
        nfcReaderResult.setExpireYear(parseInt);
        nfcReaderResult.setIssuerIdentificationNumber(cVar.cardNumber.substring(0, 6));
        this.sZ.sX.onCardReadSuccess(nfcReaderResult);
        cardtek.masterpass.nfc.b unused = this.sZ.sz.so;
        cardtek.masterpass.nfc.b.disableReaderMode(this.sZ.sY);
    }

    @Override // cardtek.masterpass.nfc.a
    public void onError() {
        this.sZ.sX.onCardReadFail();
    }

    @Override // cardtek.masterpass.nfc.a
    public void p() {
        InternalError internalError = new InternalError();
        cardtek.masterpass.util.a aVar = cardtek.masterpass.util.a.E017;
        internalError.setErrorCode(aVar.name);
        internalError.setErrorDesc(aVar.value);
        this.sZ.sX.onInternalError(internalError);
    }
}
