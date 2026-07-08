package com.proje.mobilesales.core.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import androidx.annotation.Nullable;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.CommunicationModule;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.asynctask.EmailAsyncTask;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.emailreplacer.EmailObject;
import com.proje.mobilesales.core.enums.CommunicationType;
import com.proje.mobilesales.core.enums.DataObjectType;
import com.proje.mobilesales.core.enums.EmailTemplateType;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.ICommunication;
import com.proje.mobilesales.core.interfaces.Injectable;
import com.proje.mobilesales.core.interfaces.di.CommunicationComponent;
import com.proje.mobilesales.core.interfaces.di.IComponent;
import com.proje.mobilesales.core.sql.ISqlBriteDatabase;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.FicheTypeControlUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.Cabin;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.CabinTrans;
import com.proje.mobilesales.features.collections.casefiche.model.database.CaseCash;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditX;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.database.CashCredit;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeed;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.database.Chequedeed;
import com.proje.mobilesales.features.customer.model.CustomerBeforeBalance;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.dbmodel.Invoice;
import com.proje.mobilesales.features.dbmodel.Order;
import com.proje.mobilesales.features.dbmodel.WhTransfer;
import com.proje.mobilesales.features.model.GroupItem;
import com.proje.mobilesales.features.model.UserSettings;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.settings.model.MatterSettings;
import com.proje.mobilesales.features.settings.model.enums.MatterArea;
import com.proje.mobilesales.features.visit.model.database.VisitInfo;
import java.util.Iterator;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Response;

public abstract class BaseCommunication<T> implements ICommunication, Injectable {
    private static final String TAG = "BaseCommunication";
    private final CommunicationComponent mCommunicationComponent;
    protected CommunicationType mCommunicationType;

    public Context mContext;
    protected ISqlBriteDatabase mLogoSqlBriteDatabase;
    public CommunicationComponent getCommunicationComponent() {
        return this.mCommunicationComponent;
    }
    public BaseCommunication(@Nullable CommunicationType communicationType, @Nullable ISqlBriteDatabase iSqlBriteDatabase, UserSettings userSettings) {
        this.mCommunicationComponent = MobileSales.getInstance().getAppComponent().plus(new CommunicationModule(ContextUtils.getmContext(), userSettings.getServerAddress()));
        this.mCommunicationType = communicationType;
        this.mLogoSqlBriteDatabase = iSqlBriteDatabase;
    } 
    public IComponent getComponent() {
        return getCommunicationComponent();
    }
    protected final boolean updateFicheDiffErrorAndFicheRef(int i2, DataObjectType dataObjectType, int i3) {
        int i4;
        ContentValues contentValues = null;
        Class cls;
        DataObjectType dataObjectType2;
        try {
            contentValues = new ContentValues();
            contentValues.put(this.mContext.getString(R.string.column_transfer), 2);
            contentValues.put(this.mContext.getString(R.string.column_fiche_ref), Integer.valueOf(i3));
        } catch (Exception e2) {
            Log.e(TAG, "updateFicheTransferAndFicheRef: ", e2);
            i4 = 0;
        }
        if (dataObjectType != DataObjectType.ADDORDER && dataObjectType != DataObjectType.ADDREQEST) {
            DataObjectType dataObjectType3 = DataObjectType.ADDINVOICE;
            cls = Invoice.class;
            if (dataObjectType != dataObjectType3 && dataObjectType != (dataObjectType2 = DataObjectType.ADDDISPATCH) && dataObjectType != dataObjectType3 && dataObjectType != dataObjectType2) {
                if (dataObjectType != DataObjectType.ADDCASH && dataObjectType != DataObjectType.ADDCREDIT) {
                    if (dataObjectType == DataObjectType.ADDCASECASH) {
                        cls = CaseCash.class;
                    } else {
                        if (dataObjectType != DataObjectType.ADDCHEQUE && dataObjectType != DataObjectType.ADDDEED) {
                            cls = dataObjectType == DataObjectType.ADDWHTRANSFER ? WhTransfer.class : null;
                        }
                        cls = Chequedeed.class;
                    }
                }
                cls = CashCredit.class;
            }
            assert contentValues != null;
            i4 = this.mLogoSqlBriteDatabase.update(cls, contentValues, this.mContext.getString(R.string.update_fiche_where_logical_ref_cond), String.valueOf(i2));
            return i4 != 1;
        }
        cls = Order.class;
        contentValues.put(this.mContext.getString(R.string.column_edit), 0);
        i4 = this.mLogoSqlBriteDatabase.update(cls, contentValues, this.mContext.getString(R.string.update_fiche_where_logical_ref_cond), String.valueOf(i2));
        return i4 != 1;
    }
    protected final int getIsTransfer(int i2, DataObjectType dataObjectType) {
        Cursor cursor = null;
        Class cls = null;
        DataObjectType dataObjectType2;
        int i3 = 0;
        try {
            cursor = null;
        } catch (Exception e2) {
            Log.e(TAG, "updateFicheTransferAndFicheRef: ", e2);
        }
        try {
            if (dataObjectType != DataObjectType.ADDORDER && dataObjectType != DataObjectType.ADDREQEST) {
                DataObjectType dataObjectType3 = DataObjectType.ADDINVOICE;
                cls = Invoice.class;
                if (dataObjectType != dataObjectType3 && dataObjectType != (dataObjectType2 = DataObjectType.ADDDISPATCH) && dataObjectType != dataObjectType3 && dataObjectType != dataObjectType2) {
                    if (dataObjectType != DataObjectType.ADDCASH && dataObjectType != DataObjectType.ADDCREDIT) {
                        if (dataObjectType == DataObjectType.ADDCASECASH) {
                            cls = CaseCash.class;
                        } else {
                            if (dataObjectType != DataObjectType.ADDCHEQUE && dataObjectType != DataObjectType.ADDDEED) {
                                cls = dataObjectType == DataObjectType.ADDWHTRANSFER ? WhTransfer.class : null;
                            }
                            cls = Chequedeed.class;
                        }
                    }
                    cls = CashCredit.class;
                }
                cursor = this.mLogoSqlBriteDatabase.query("SELECT ISTRANSFER FROM " + this.mLogoSqlBriteDatabase.getSqlCreator().getTableName(cls) + " WHERE LOGICALREF = " + i2);
                if (cursor != null && cursor.moveToFirst()) {
                    i3 = cursor.getInt(0);
                }
                return i3;
            }
            cursor = this.mLogoSqlBriteDatabase.query("SELECT ISTRANSFER FROM " + this.mLogoSqlBriteDatabase.getSqlCreator().getTableName(cls) + " WHERE LOGICALREF = " + i2);
            if (cursor != null) {
                i3 = cursor.getInt(0);
            }
            return i3;
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }
    protected final boolean updateFicheTransferAndFicheRef(int i2, DataObjectType dataObjectType, int i3) {
        int i4;
        ContentValues contentValues = null;
        Class cls;
        DataObjectType dataObjectType2;
        try {
            contentValues = new ContentValues();
            contentValues.put(this.mContext.getString(R.string.column_transfer), 1);
            contentValues.put(this.mContext.getString(R.string.column_fiche_ref), Integer.valueOf(i3));
        } catch (Exception e2) {
            Log.e(TAG, "updateFicheTransferAndFicheRef: ", e2);
            i4 = 0;
        }
        if (dataObjectType != DataObjectType.ADDORDER && dataObjectType != DataObjectType.ADDREQEST) {
            DataObjectType dataObjectType3 = DataObjectType.ADDINVOICE;
            cls = Invoice.class;
            if (dataObjectType != dataObjectType3 && dataObjectType != (dataObjectType2 = DataObjectType.ADDDISPATCH) && dataObjectType != dataObjectType3 && dataObjectType != dataObjectType2) {
                if (dataObjectType != DataObjectType.ADDCASH && dataObjectType != DataObjectType.ADDCREDIT) {
                    if (dataObjectType == DataObjectType.ADDCASECASH) {
                        cls = CaseCash.class;
                    } else {
                        if (dataObjectType != DataObjectType.ADDCHEQUE && dataObjectType != DataObjectType.ADDDEED) {
                            cls = dataObjectType == DataObjectType.ADDWHTRANSFER ? WhTransfer.class : null;
                        }
                        cls = Chequedeed.class;
                    }
                }
                cls = CashCredit.class;
            }
            i4 = this.mLogoSqlBriteDatabase.update(cls, contentValues, this.mContext.getString(R.string.update_fiche_where_logical_ref_cond), String.valueOf(i2));
            return i4 != 1;
        }
        cls = Order.class;
        contentValues.put(this.mContext.getString(R.string.column_edit), 0);
        i4 = this.mLogoSqlBriteDatabase.update(cls, contentValues, this.mContext.getString(R.string.update_fiche_where_logical_ref_cond), String.valueOf(i2));
        return i4 != 1;
    }
    protected final boolean updateFicheTransferAndFicheNo(int i2, DataObjectType dataObjectType, String str) {
        int i3;
        ContentValues contentValues = null;
        Class cls;
        try {
            contentValues = new ContentValues();
            contentValues.put(this.mContext.getString(R.string.column_transfer), 1);
            contentValues.put(this.mContext.getString(R.string.column_fiche_no), str);
        } catch (Exception e2) {
            Log.e(TAG, "updateFicheTransferAndFicheRef: ", e2);
            i3 = 0;
        }
        if (dataObjectType != DataObjectType.ADDORDER && dataObjectType != DataObjectType.ADDREQEST) {
            if (dataObjectType != DataObjectType.ADDINVOICE && dataObjectType != DataObjectType.ADDDISPATCH) {
                if (dataObjectType != DataObjectType.ADDCASH && dataObjectType != DataObjectType.ADDCREDIT) {
                    if (dataObjectType == DataObjectType.ADDCASECASH) {
                        cls = CaseCash.class;
                    } else {
                        if (dataObjectType != DataObjectType.ADDCHEQUE && dataObjectType != DataObjectType.ADDDEED) {
                            cls = dataObjectType == DataObjectType.ADDWHTRANSFER ? WhTransfer.class : null;
                        }
                        cls = Chequedeed.class;
                    }
                    return this.mLogoSqlBriteDatabase.update(cls, contentValues, this.mContext.getString(R.string.update_fiche_where_logical_ref_cond), String.valueOf(i2)) != 1;
                }
                cls = CashCredit.class;
                return this.mLogoSqlBriteDatabase.update(cls, contentValues, this.mContext.getString(R.string.update_fiche_where_logical_ref_cond), String.valueOf(i2)) != 1;
            }
            cls = Invoice.class;
            return this.mLogoSqlBriteDatabase.update(cls, contentValues, this.mContext.getString(R.string.update_fiche_where_logical_ref_cond), String.valueOf(i2)) != 1;
        }
        cls = Order.class;
        contentValues.put(this.mContext.getString(R.string.column_edit), 0);
        return this.mLogoSqlBriteDatabase.update(cls, contentValues, this.mContext.getString(R.string.update_fiche_where_logical_ref_cond), String.valueOf(i2)) != 1;
    }
    protected final boolean updateCancelFicheTransferByFicheRef(int i2, DataObjectType dataObjectType) {
        ContentValues contentValues = null;
        Class<?> cls;
        DataObjectType dataObjectType2;
        try {
            contentValues = new ContentValues();
            contentValues.put(this.mContext.getString(R.string.column_cancel), 1);
        } catch (Exception e2) {
            Log.e(TAG, "updateFicheTransferByFicheRef: ", e2);
            return false;
        }
        if (dataObjectType != DataObjectType.ADDORDER && dataObjectType != DataObjectType.ADDREQEST) {
            DataObjectType dataObjectType3 = DataObjectType.ADDINVOICE;
            cls = Invoice.class;
            if (dataObjectType != dataObjectType3 && dataObjectType != (dataObjectType2 = DataObjectType.ADDDISPATCH) && dataObjectType != dataObjectType3 && dataObjectType != dataObjectType2) {
                if (dataObjectType != DataObjectType.ADDCASH && dataObjectType != DataObjectType.ADDCREDIT) {
                    if (dataObjectType == DataObjectType.ADDCASECASH) {
                        cls = CaseCash.class;
                    } else {
                        if (dataObjectType != DataObjectType.ADDCHEQUE && dataObjectType != DataObjectType.ADDDEED) {
                            cls = null;
                        }
                        cls = Chequedeed.class;
                    }
                }
                cls = CashCredit.class;
            }
            return this.mLogoSqlBriteDatabase.update(cls, contentValues, this.mContext.getString(R.string.update_fiche_where_fiche_ref_cond), String.valueOf(i2)) != 1;
        }
        cls = Order.class;
        contentValues.put(this.mContext.getString(R.string.column_edit), 0);
        return this.mLogoSqlBriteDatabase.update(cls, contentValues, this.mContext.getString(R.string.update_fiche_where_fiche_ref_cond), String.valueOf(i2)) != 1;
    }
    public String sendMail(EmailObject emailObject) throws Exception {
        if (emailObject.getHtml() != null && emailObject.getHtml() != "") {
            try {
                if (emailObject.getTo() != null) {
                    try {
                        EmailAsyncTask emailAsyncTask = new EmailAsyncTask(emailObject.isSendHTMLContent());
                        emailAsyncTask.get_to().addAll(StringUtils.arrayToList(emailObject.getTo()));
                        emailAsyncTask.set_subject(emailObject.getSubject());
                        emailAsyncTask.setBody(emailObject.getHtml());
                        if (emailObject.getFiles() != null) {
                            for (String str : emailObject.getFiles()) {
                                emailAsyncTask.addAttachment(ContextUtils.getmContext().getCacheDir() + "/emailAttacments/" + str, str);
                            }
                        }
                        if (emailAsyncTask.send()) {
                            Log.i(TAG, "doInBackground: email send");
                            String stringResource = ContextUtils.getStringResource(R.string.str_send_email_success);
                            if (emailObject.getFiles() != null) {
                                for (String s : emailObject.getFiles()) {
                                    ContextUtils.deleteNoteOnSD(ContextUtils.getmContext().getCacheDir() + "/emailAttacments/" + s);
                                }
                            }
                            return stringResource;
                        }
                        Log.i(TAG, "doInBackground: email send failed");
                        String stringResource2 = ContextUtils.getStringResource(R.string.str_send_email_failed);
                        if (emailObject.getFiles() != null) {
                            for (String s : emailObject.getFiles()) {
                                ContextUtils.deleteNoteOnSD(ContextUtils.getmContext().getCacheDir() + "/emailAttacments/" + s);
                            }
                        }
                        return stringResource2;
                    } catch (Exception e2) {
                        Log.e(TAG, "sendEmail: ", e2);
                        String message = e2.getMessage();
                        if (emailObject.getFiles() != null) {
                            Iterator<String> it3 = emailObject.getFiles().iterator();
                            while (it3.hasNext()) {
                                ContextUtils.deleteNoteOnSD(ContextUtils.getmContext().getCacheDir() + "/emailAttacments/" + it3.next());
                            }
                        }
                        return message;
                    }
                }
            } catch (Throwable th) {
                if (emailObject.getFiles() != null) {
                    Iterator<String> it4 = emailObject.getFiles().iterator();
                    while (it4.hasNext()) {
                        ContextUtils.deleteNoteOnSD(ContextUtils.getmContext().getCacheDir() + "/emailAttacments/" + it4.next());
                    }
                }
                throw th;
            }
        }
        return "";
    }
    public String sendMail(DataObjectType dataObjectType, Object obj, CustomerBeforeBalance customerBeforeBalance, String str) throws Exception {
        EmailObject replaceHtml;
        try {
            if (!ErpCreator.getInstance().getmBaseErp().canSendDataTypeMail(dataObjectType)) {
                return "";
            }
            if (!ErpCreator.getInstance().getmBaseErp().sendCustomerFicheMail() && !ErpCreator.getInstance().getmBaseErp().sendOtherMail()) {
                return "";
            }
            switch (C24611.SwitchMapcomprojemobilesalescoreenumsDataObjectType[dataObjectType.ordinal()]) {
                case 1:
                case 2:
                case 3:
                    Sales sales = (Sales) obj;
                    sales.setFicheNo(str);
                    if (sales.getClRef() == 0) {
                        sales.setClRef(ErpCreator.getInstance().getmBaseErp().getCustomerClRef(sales.getClCode()));
                    }
                    replaceHtml = sales.replaceHtml(EmailTemplateType.Unknown, customerBeforeBalance);
                    break;
                case 4:
                    CashCreditX cashCreditX = (CashCreditX) obj;
                    if (cashCreditX.getCashCredit().clRef == 0) {
                        cashCreditX.getCashCredit().clRef = ErpCreator.getInstance().getmBaseErp().getCustomerClRef(cashCreditX.getCashCredit().clCode);
                    }
                    replaceHtml = cashCreditX.replaceHtml(EmailTemplateType.CashCollection, customerBeforeBalance);
                    break;
                case 5:
                    CashCreditX cashCreditX2 = (CashCreditX) obj;
                    if (cashCreditX2.getCashCredit().clRef == 0) {
                        cashCreditX2.getCashCredit().clRef = ErpCreator.getInstance().getmBaseErp().getCustomerClRef(cashCreditX2.getCashCredit().clCode);
                    }
                    replaceHtml = cashCreditX2.replaceHtml(EmailTemplateType.CreditCollection, customerBeforeBalance);
                    break;
                case 6:
                    ChequeDeed chequeDeed = (ChequeDeed) obj;
                    chequeDeed.getChequedeed().ficheNo = str;
                    if (chequeDeed.getChequedeed().clRef == 0) {
                        chequeDeed.getChequedeed().clRef = ErpCreator.getInstance().getmBaseErp().getCustomerClRef(chequeDeed.getChequedeed().clCode);
                    }
                    replaceHtml = chequeDeed.replaceHtml(EmailTemplateType.CheckCollection, customerBeforeBalance);
                    break;
                case 7:
                    ChequeDeed chequeDeed2 = (ChequeDeed) obj;
                    if (chequeDeed2.getChequedeed().clRef == 0) {
                        chequeDeed2.getChequedeed().clRef = ErpCreator.getInstance().getmBaseErp().getCustomerClRef(chequeDeed2.getChequedeed().clCode);
                    }
                    replaceHtml = chequeDeed2.replaceHtml(EmailTemplateType.BillCollection, customerBeforeBalance);
                    break;
                case 8:
                    CaseCash caseCash = (CaseCash) obj;
                    if (caseCash.clRef == 0) {
                        caseCash.clRef = ErpCreator.getInstance().getmBaseErp().getCustomerClRef(caseCash.clCode);
                    }
                    replaceHtml = caseCash.replaceHtml(EmailTemplateType.CaseCollection, customerBeforeBalance);
                    break;
                default:
                    replaceHtml = null;
                    break;
            }
            if (replaceHtml != null && replaceHtml.getHtml() != null && replaceHtml.getHtml() != "" && replaceHtml.getTo() != null) {
                replaceHtml.setSendHTMLContent(true);
                ErpCreator.getInstance().getmBaseErp().sendMail(replaceHtml, false);
            }
            return "";
        } catch (Exception e2) {
            Log.e(TAG, "sendEmail: ", e2);
            return e2.getMessage();
        }
    }
    protected void lambdasendFicheRx32(GroupItem f1, boolean f2) {
    }
    public abstract void lambdasendCashAndCredit120(GroupItem f1, boolean f2);
    public abstract void lambdasendCaseCashFiche70(GroupItem groupItem, boolean z);
    static  class C24611 {
        static final  int[] SwitchMapcomprojemobilesalescoreenumsDataObjectType;

        static {
            int[] iArr = new int[DataObjectType.values().length];
            SwitchMapcomprojemobilesalescoreenumsDataObjectType = iArr;
            try {
                iArr[DataObjectType.ADDORDER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsDataObjectType[DataObjectType.ADDDISPATCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsDataObjectType[DataObjectType.ADDINVOICE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsDataObjectType[DataObjectType.ADDCASH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsDataObjectType[DataObjectType.ADDCREDIT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsDataObjectType[DataObjectType.ADDCHEQUE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsDataObjectType[DataObjectType.ADDDEED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsDataObjectType[DataObjectType.ADDCASECASH.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }
    protected final boolean updateFicheTransferAndFicheRef(int i2, ProcessType processType) {
        int i3;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(this.mContext.getString(R.string.column_transfer), 1);
            i3 = this.mLogoSqlBriteDatabase.update(processType.getaClass(), contentValues, this.mContext.getString(R.string.update_fiche_where_id_ref_cond), String.valueOf(i2));
        } catch (Exception e2) {
            Log.e(TAG, "updateFicheTransferAndFicheRef: ", e2);
            i3 = 0;
        }
        return i3 == 1;
    }
    protected final String getMaxMatterNo(FicheType ficheType, MatterSettings matterSettings) {
        int i2;
        Class<?> cls;
        String string = "";
        String str = "";
        String str2 = "";
        Cursor cursor = null;
        try {
            if (FicheTypeControlUtils.isFicheTypeOrder(ficheType)) {
                cls = Order.class;
            } else {
                i2 = 1;
                if (!FicheTypeControlUtils.isFicheTypeOnlyInvoice(ficheType)) {
                    if (!FicheTypeControlUtils.isFicheTypeDispatch(ficheType)) {
                        Class<?> cls2 = Chequedeed.class;
                        if (!FicheTypeControlUtils.isFicheTypeCheckReceipt(ficheType)) {
                            if (!FicheTypeControlUtils.isFicheTypeDeedReceipt(ficheType)) {
                                cls2 = CashCredit.class;
                                if (!FicheTypeControlUtils.isFicheTypeCashReceipt(ficheType)) {
                                    if (!FicheTypeControlUtils.isFicheTypeCreditReceipt(ficheType)) {
                                        if (FicheTypeControlUtils.isFicheTypeCaseReceipt(ficheType)) {
                                            cls = CaseCash.class;
                                        } else if (FicheTypeControlUtils.isFicheTypeOnlyRetailInvoice(ficheType)) {
                                            i2 = 3;
                                        } else {
                                            cls = null;
                                        }
                                    }
                                }
                            }
                            cls = cls2;
                            if (matterSettings.getMatterArea() != MatterArea.FICHE_NO) {
                                string = ContextUtils.getmContext().getString(R.string.column_fiche_no);
                            } else {
                                string = ContextUtils.getmContext().getString(R.string.column_doc_no);
                            }
                            str = "";
                            if (i2 > -1) {
                                str2 = "";
                            } else {
                                str2 = " AND FTYPE=" + i2;
                            }
                            cursor = this.mLogoSqlBriteDatabase.query(ContextUtils.getmContext().getString(R.string.qry_get_max_matter_no, matterSettings.getFirstMatterNo(), matterSettings.getLastMatterNo(), string, this.mLogoSqlBriteDatabase.getSqlCreator().getTableName(cls), str2));
                            if (cursor != null && cursor.moveToFirst()) {
                                str = cursor.getString(0);
                            }
                            return str;
                        }
                        i2 = 0;
                        cls = cls2;
                        if (matterSettings.getMatterArea() != MatterArea.FICHE_NO) {
                        }
                        str = "";
                        if (i2 > -1) {
                        }
                        cursor = this.mLogoSqlBriteDatabase.query(ContextUtils.getmContext().getString(R.string.qry_get_max_matter_no, matterSettings.getFirstMatterNo(), matterSettings.getLastMatterNo(), string, this.mLogoSqlBriteDatabase.getSqlCreator().getTableName(cls), str2));
                        if (cursor != null) {
                            str = cursor.getString(0);
                        }
                        return str;
                    }
                    i2 = 0;
                }
                cls = Invoice.class;
                if (matterSettings.getMatterArea() != MatterArea.FICHE_NO) {
                }
                str = "";
                if (i2 > -1) {
                }
                cursor = this.mLogoSqlBriteDatabase.query(ContextUtils.getmContext().getString(R.string.qry_get_max_matter_no, matterSettings.getFirstMatterNo(), matterSettings.getLastMatterNo(), string, this.mLogoSqlBriteDatabase.getSqlCreator().getTableName(cls), str2));
                if (cursor != null) {
                }
                return str;
            }
            cursor = this.mLogoSqlBriteDatabase.query(ContextUtils.getmContext().getString(R.string.qry_get_max_matter_no, matterSettings.getFirstMatterNo(), matterSettings.getLastMatterNo(), string, this.mLogoSqlBriteDatabase.getSqlCreator().getTableName(cls), str2));
            if (cursor != null) {
            }
            return str;
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

    }
    protected final boolean updateFiche(Sales sales, SalesType salesType) {
        return ErpCreator.getInstance().getmBaseErp().updateSales(sales, salesType, false);
    }
    protected final boolean updateChequeDeed(ChequeDeed chequeDeed, boolean z) {
        return ErpCreator.getInstance().getmBaseErp().updateChequDeed(chequeDeed);
    }
    public CommunicationType getCommunicationType() {
        return this.mCommunicationType;
    }
    protected final boolean updateClCard(int i2, int i3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.mContext.getString(R.string.column_transfer), 1);
        contentValues.put(this.mContext.getString(R.string.column_logical_ref), Integer.valueOf(i2));
        contentValues.put(this.mContext.getString(R.string.column_active), 0);
        int update = this.mLogoSqlBriteDatabase.update(ClCard.class, contentValues, this.mContext.getString(R.string.update_fiche_where_logical_ref_cond), String.valueOf(i3));
        if (update == 1) {
            updateFichesCLRefAfterUpdateClCard(i2, i3);
        }
        return update == 1;
    }
    private void updateFichesCLRefAfterUpdateClCard(int i2, int i3) {
        Class<?>[] clsArr = {Order.class, Invoice.class, Chequedeed.class, CashCredit.class, CaseCash.class};
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.mContext.getString(R.string.column_clref), Integer.valueOf(i2));
        for (int i4 = 0; i4 < 5; i4++) {
            this.mLogoSqlBriteDatabase.update(clsArr[i4], contentValues, this.mContext.getString(R.string.update_fiche_where_cl_ref_cond), String.valueOf(i3));
        }
    }
    protected void updateVisitAfterTransfer(int i2, int i3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.mContext.getString(R.string.column_logical_ref), Integer.valueOf(i3));
        this.mLogoSqlBriteDatabase.update(VisitInfo.class, contentValues, this.mContext.getString(R.string.update_fiche_where_id_ref_cond), String.valueOf(i2));
    }
    protected void updateCabinTransAfterTransfer(int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.mContext.getString(R.string.column_transfer), 1);
        this.mLogoSqlBriteDatabase.update(CabinTrans.class, contentValues, this.mContext.getString(R.string.update_fiche_where_id_ref_cond), String.valueOf(i2));
    }
    protected void updateCabinAfterTransfer(int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.mContext.getString(R.string.column_transfer), 1);
        this.mLogoSqlBriteDatabase.update(Cabin.class, contentValues, this.mContext.getString(R.string.update_fiche_where_id_ref_cond), String.valueOf(i2));
    }
    public Response createEmptyErrorResponse(String str) {
        return Response.error(ResponseBody.create("empty  response", null), new okhttp3.Response(new Request.Builder().url("http://localhost/").build(), Protocol.HTTP_2, str, 512, null, new Headers.Builder().build(), null, null, null, null, 0L, 0L, null));
    }
}
