package cardtek.masterpass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import cardtek.masterpass.attributes.MasterPassEditText;
import cardtek.masterpass.attributes.MasterPassWebView;
import cardtek.masterpass.cardIO.MasterPassCardIOActivity;
import cardtek.masterpass.cardIO.MasterPassCardIOListener;
import cardtek.masterpass.interfaces.*;
import cardtek.masterpass.management.*;
import cardtek.masterpass.nfc.MasterPassNfcReaderListener;
import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.util.ActionType;
import cardtek.masterpass.util.ValueType;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

import java.util.Collections;
import java.util.List;

public class MasterPassServices {
    private final b serviceImpl;

    public MasterPassServices(Context context, String str) {
        this.serviceImpl = new b(context, str);
    }

    public void addCardToMasterPass(String str, String str2, String str3, AddCardToMasterPassListener addCardToMasterPassListener) {
        new CrashlyticsReport.Session.Event.Application.Execution.Thread() {
            public void start() {
            }

            @NonNull
            @Override
            public String getName() {
                return "";
            }

            @Override
            public int getImportance() {
                return 0;
            }

            @NonNull
            @Override
            public List<Frame> getFrames() {
                return Collections.emptyList();
            }
        }.start();
    }

    public void checkMasterPass(String str, String str2, CheckMasterPassListener checkMasterPassListener) {
        new CrashlyticsReport.Session.Event.Application.Execution.Thread() {
            public void start() {
            }

            @NonNull
            @Override
            public String getName() {
                return "";
            }

            @Override
            public int getImportance() {
                return 0;
            }

            @NonNull
            @Override
            public List<Frame> getFrames() {
                return Collections.emptyList();
            }
        }.start();
    }

    public void completeRegister(String str, String str2, String str3, CompleteRegisterListener completeRegisterListener) {
        new CrashlyticsReport.Session.Event.Application.Execution.Thread() {
            public void start() {
            }

            @NonNull
            @Override
            public String getName() {
                return "";
            }

            @Override
            public int getImportance() {
                return 0;
            }

            @NonNull
            @Override
            public List<Frame> getFrames() {
                return Collections.emptyList();
            }
        }.start();
    }

    public void deleteCard(String str, String str2, String str3, DeleteCardListener deleteCardListener) {
        new CrashlyticsReport.Session.Event.Application.Execution.Thread() {
            public void start() {
            }

            @NonNull
            @Override
            public String getName() {
                return "";
            }

            @Override
            public int getImportance() {
                return 0;
            }

            @NonNull
            @Override
            public List<Frame> getFrames() {
                return Collections.emptyList();
            }
        }.start();
    }

    public void directPurchase(String str, MasterPassEditText masterPassEditText, int i2, int i3, int i4, String str2, String str3, CompoundButton compoundButton, DirectPurchaseListener directPurchaseListener) {
        this.serviceImpl.directPurchase(str, masterPassEditText, i2, i3, i4, str2, str3, null, "", "", "", null, compoundButton, directPurchaseListener);
    }

    public void directPurchase(String str, MasterPassEditText masterPassEditText, int i2, int i3, int i4, String str2, String str3, Integer num, String str4, String str5, String str6, MasterPassEditText masterPassEditText2, CompoundButton compoundButton, DirectPurchaseListener directPurchaseListener) {
        this.serviceImpl.directPurchase(str, masterPassEditText, i2, i3, i4, str2, str3, num, str4, str5, str6, masterPassEditText2, compoundButton, directPurchaseListener);
    }

    public void disableNfcReaderMode(Activity activity) {
        cardtek.masterpass.nfc.b.disableReaderMode(activity);
    }

    public void forgotPassword(String str, String str2, String str3, MasterPassEditText masterPassEditText, ForgotPasswordListener forgotPasswordListener) {
        this.serviceImpl.forgotPassword(str, str2, str3, masterPassEditText, forgotPasswordListener);
    }

    public void forgotPassword(String str, String str2, String str3, ForgotPasswordListener forgotPasswordListener) {

    }

    public void getCardUniqueID(String str, MasterPassEditText masterPassEditText, String str2, CardUniqueIDListener cardUniqueIDListener) {
        new Thread(new s(this.serviceImpl, masterPassEditText, cardUniqueIDListener, str, str2)).start();
    }

    public void getCards(String str, String str2, GetCardsListener getCardsListener) {
        new Thread(new t(this.serviceImpl, str, str2, getCardsListener)).start();
    }

    public String getLastToken() {
        return b.sm.getToken();
    }

    public void initiateRecurringPayment(String str, String str2, int i2, String str3, ActionType actionType, String str4, String str5, RecurringPaymentListener recurringPaymentListener) {
        new Thread(new l(this.serviceImpl, str2, recurringPaymentListener, str, i2, str4, str3, actionType, str5)).start();
    }

    public void linkCardToClient(String str, String str2, LinkCardToClientListener linkCardToClientListener) {
        new Thread(new y(this.serviceImpl, str, str2, linkCardToClientListener)).start();
    }

    public void moneySend(String str, cardtek.masterpass.util.b bVar, String str2, String str3, int i2, String str4, String str5, MoneySendListener moneySendListener) {
        this.serviceImpl.moneySend(str, bVar, str2, str3, i2, str4, str5, 0, "", "", null, moneySendListener);
    }

    public void moneySend(String str, cardtek.masterpass.util.b bVar, String str2, String str3, int i2, String str4, String str5, Integer num, String str6, String str7, MasterPassEditText masterPassEditText, MoneySendListener moneySendListener) {
        this.serviceImpl.moneySend(str, bVar, str2, str3, i2, str4, str5, num, str6, str7, masterPassEditText, moneySendListener);
    }

    public void parseQrCode(String str, String str2, ParseQrCodeListener parseQrCodeListener) {
        new Thread(new k(this.serviceImpl, str, str2, parseQrCodeListener)).start();
    }

    public void purchase(String str, String str2, int i2, String str3, String str4, PurchaseListener purchaseListener) {
        this.serviceImpl.purchase(str, str2, i2, str3, str4, null, "", "", null, purchaseListener);
    }

    public void purchase(String str, String str2, int i2, String str3, String str4, Integer num, String str5, String str6, MasterPassEditText masterPassEditText, PurchaseListener purchaseListener) {
        this.serviceImpl.purchase(str, str2, i2, str3, str4, num, str5, str6, masterPassEditText, purchaseListener);
    }

    public void registerAndPurchase(String str, MasterPassEditText masterPassEditText, int i2, int i3, int i4, String str2, String str3, String str4, CompoundButton compoundButton, RegisterAndPurchaseListener registerAndPurchaseListener) {
        this.serviceImpl.registerAndPurchase(str, masterPassEditText, i2, i3, i4, str2, str3, str4, null, "", "", "", null, compoundButton, registerAndPurchaseListener);
    }

    public void registerAndPurchase(String str, MasterPassEditText masterPassEditText, int i2, int i3, int i4, String str2, String str3, String str4, Integer num, String str5, String str6, String str7, MasterPassEditText masterPassEditText2, CompoundButton compoundButton, RegisterAndPurchaseListener registerAndPurchaseListener) {
        this.serviceImpl.registerAndPurchase(str, masterPassEditText, i2, i3, i4, str2, str3, str4, num, str5, str6, str7, masterPassEditText2, compoundButton, registerAndPurchaseListener);
    }

    public void registerCard(String str, MasterPassEditText masterPassEditText, int i2, int i3, String str2, String str3, CompoundButton compoundButton, RegisterCardListener registerCardListener) {
        this.serviceImpl.registerCard(str, masterPassEditText, i2, i3, str2, str3, "", null, compoundButton, registerCardListener);
    }

    public void registerCard(String str, MasterPassEditText masterPassEditText, int i2, int i3, String str2, String str3, String str4, MasterPassEditText masterPassEditText2, CompoundButton compoundButton, RegisterCardListener registerCardListener) {
        this.serviceImpl.registerCard(str, masterPassEditText, i2, i3, str2, str3, str4, masterPassEditText2, compoundButton, registerCardListener);
    }

    public void resendOtp(String str, ResendOtpListener resendOtpListener) {
        new Thread(new e(this.serviceImpl, str, resendOtpListener)).start();
    }

    public void setLastToken(String str) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setToken(str);
        b.sm = serviceResponse;
    }

    public void setMsisdn(String str) {
        this.serviceImpl.sl = str;
    }

    public void startCardIO(FragmentActivity fragmentActivity, MasterPassEditText masterPassEditText, MasterPassCardIOListener masterPassCardIOListener) {
        b bVar = this.serviceImpl;
        try {
            fragmentActivity.startActivity(new Intent(fragmentActivity, MasterPassCardIOActivity.class));
            b.sk = new j(bVar, masterPassEditText, masterPassCardIOListener);
        } catch (Exception e2) {
            InternalError internalError = new InternalError();
            a aVar = null;
            internalError.setErrorCode(aVar.name);
            internalError.setErrorDesc(aVar.value);
            masterPassCardIOListener.onInternalError(internalError);
            e2.printStackTrace();
        }
    }

    public void startNfcReader(Activity activity, MasterPassEditText masterPassEditText, MasterPassNfcReaderListener masterPassNfcReaderListener) {
        this.serviceImpl.startNfcReader(activity, masterPassEditText, masterPassNfcReaderListener);
    }

    public void startNfcReader(Activity activity, MasterPassNfcReaderListener masterPassNfcReaderListener) {
        this.serviceImpl.startNfcReader(activity, null, masterPassNfcReaderListener);
    }

    public void updateUser(String str, String str2, ValueType valueType, String str3, UpdateUserListerner updateUserListerner) {
        this.serviceImpl.updateUser(str, "", str2, valueType, str3, updateUserListerner);
    }

    public void updateUser(String str, String str2, String str3, ValueType valueType, String str4, UpdateUserListerner updateUserListerner) {
        this.serviceImpl.updateUser(str, str2, str3, valueType, str4, updateUserListerner);
    }

    public void validateTransaction(MasterPassEditText masterPassEditText, String str, ValidateTransactionListener validateTransactionListener) {
        new Thread(new w(this.serviceImpl, masterPassEditText, validateTransactionListener, str)).start();
    }

    public void validateTransaction3D(MasterPassWebView masterPassWebView, ValidateTransaction3DListener validateTransaction3DListener) {
        a aVar;
        String str;
        try {
            masterPassWebView.loadUrl(b.sm, new x(this.serviceImpl, validateTransaction3DListener));
        } catch (Exception e2) {
            InternalError internalError = new InternalError();
            a a = null;
            aVar = a;
            internalError.setErrorCode(aVar.name);
            str = aVar.value;
            internalError.setErrorDesc(str);
            validateTransaction3DListener.onInternalError(internalError);
            e2.printStackTrace();
        }
    }

    public void verifyMPin(String str, String str2, VerifyMPinListener verifyMPinListener) {
        new Thread(new f(this.serviceImpl, str, str2, verifyMPinListener)).start();
    }

    private static class a {
        public String name;
        public String value;
        private static a a;
        public static final a E001 = a;
    }

    private class i {
    }
}
