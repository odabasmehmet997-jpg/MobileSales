package com.proje.mobilesales.core.printutil;

import android.R;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintBaseModel;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.service.PrintService;
import com.proje.mobilesales.core.tigerwcf.RESULTXML;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.FicheTypeControlUtils;
import com.proje.mobilesales.features.activity.PrintPreviewActivity;
import com.proje.mobilesales.features.dbmodel.DesignJson;
import com.proje.mobilesales.features.model.PrintValueMultiple;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

import static com.proje.mobilesales.core.utils.AppUtils.isConnected;
import static com.proje.mobilesales.core.utils.AppUtils.isMyServiceRunning;

public class PrinterUtility {
    private static final String NOTIFICATION_GROUP_KEY = "group";
    private static final String TAG = "PrinterUtility";
    private final BaseErp mBaseErp;
    Context mContext;
    int mCustomerId;
    int mFicheId;
    boolean mOnlinePrint;
    int mPrintFileId;
    String mPrintFileName;
    ProgressDialog mProgressDialogBuilder;
    int mWareHouse = 0;
    com.proje.mobilesales.features.model.PrintSettings printSettings;
    public PrinterUtility(BaseErp baseErp) {
        this.mBaseErp = baseErp;
    }
    public BaseErp getBaseErp() {
        BaseErp baseErp = this.mBaseErp;
        return baseErp == null ? ErpCreator.getInstance().getmBaseErp() : baseErp;
    }
    public void print(int i2, int i3, FicheType ficheType, boolean z, int i4) {
        Log.d(TAG, "log() called with: ficheId = [" + i2 + "], ficheType = [" + ficheType + "]");
        if (i2 == -1 || FicheTypeControlUtils.isFicheTypeFree(ficheType)) {
            return;
        }
        this.mContext = ContextUtils.getmContext();
        boolean checkConnectionWithoutMessage = ContextUtils.checkConnectionWithoutMessage();
        this.mOnlinePrint = checkConnectionWithoutMessage;
        if (z) {
            z = checkConnectionWithoutMessage;
        }
        this.mOnlinePrint = z;
        if (getBaseErp().getErpType() == ErpType.NETSIS) {
            i2 = i3;
        }
        if (this.mOnlinePrint) {
            this.mFicheId = i2;
        } else {
            this.mFicheId = i3;
        }
        this.mCustomerId = i4;
        ProgressDialog progressDialog = new ProgressDialog(this.mContext);
        this.mProgressDialogBuilder = progressDialog;
        progressDialog.setMessage(this.mContext.getString(R.string.str_please_wait));
        this.mProgressDialogBuilder.show();
        getPrintSettings(ficheType);
        com.proje.mobilesales.features.model.PrintSettings printSettings = this.printSettings;
        if (printSettings != null) {
            getPrintDesignFileNames(printSettings.getFicheType());
        }
    }
    public void printTransport(int i2, FicheType ficheType) {
        Log.d(TAG, "printTransport() called with: itemRef = [" + i2 + "], ficheType = [" + ficheType + "]");
        if (FicheTypeControlUtils.isFicheTypeFree(ficheType)) {
            return;
        }
        Log.d(TAG, "log: starting");
        this.mContext = ContextUtils.getmContext();
        this.mOnlinePrint = getBaseErp().getOnlinePrint();
        this.mFicheId = i2;
        ProgressDialog progressDialog = new ProgressDialog(this.mContext);
        this.mProgressDialogBuilder = progressDialog;
        progressDialog.setMessage(this.mContext.getString(R.string.str_please_wait));
        this.mProgressDialogBuilder.show();
        getPrintSettings(ficheType);
        com.proje.mobilesales.features.model.PrintSettings printSettings = this.printSettings;
        if (printSettings != null) {
            choiceWareHouse(ficheType, printSettings.isDontShowPreview());
        }
    }
    private void choiceWareHouse(final FicheType ficheType, final boolean z) {
        this.mProgressDialogBuilder.dismiss();
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        final Cursor query = getBaseErp().getLogoSqlBriteDatabase().query(this.mContext.getString(R.string.qry_get_print_warehouse));
        if (query != null) {
            builder.setSingleChoiceItems(query, -1, "CODE", new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.core.printutil.PrinterUtilityExternalSyntheticLambda2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    PrinterUtility.this.lambdachoiceWareHouse0(query, dialogInterface, i2);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.core.printutil.PrinterUtilityExternalSyntheticLambda3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    PrinterUtility.this.lambdachoiceWareHouse1(z, ficheType, dialogInterface, i2);
                }
            }).create().show();
        } else {
            showMessage(R.string.str_warehouse_not_found);
        }
    }
    public void lambdachoiceWareHouse0(Cursor cursor, DialogInterface dialogInterface, int i2) {
        if (cursor.moveToPosition(i2)) {
            this.mFicheId = cursor.getInt(cursor.getColumnIndex("_id"));
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        getPrintDesignFileNames(this.printSettings.getFicheType());
        dialogInterface.dismiss();
    }
    public void lambdachoiceWareHouse1(boolean z, FicheType ficheType, DialogInterface dialogInterface, int i2) {
        if (z) {
            getPrintDesignFileNames(ficheType);
        }
        dialogInterface.dismiss();
    }
    private void getPrintDesignFileNames(FicheType ficheType) {
        this.mProgressDialogBuilder.setMessage(this.mContext.getString(R.string.str_print_design_files));
        this.mProgressDialogBuilder.show();
        if (this.mOnlinePrint) {
            getBaseErp().getOnlinePrintFileNameList(ficheType, new PrintFileNameListResponseListener(this));
        } else {
            getBaseErp().getOfflinePrintFileNameList(ficheType, new PrintFileNameListResponseListener(this));
        }
    }
    public void showDesignFiles(final List<DesignJson> list) {
        this.mProgressDialogBuilder.dismiss();
        if (list != null && list.size() > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
            builder.setTitle(this.mContext.getString(R.string.str_designs));
            final ArrayAdapter arrayAdapter = new ArrayAdapter(this.mContext, R.layout.select_dialog_item);
            Iterator<DesignJson> it = list.iterator();
            while (it.hasNext()) {
                arrayAdapter.add(it.next().getName());
            }
            builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.core.printutil.PrinterUtilityExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    PrinterUtility.this.lambdashowDesignFiles2(list, arrayAdapter, dialogInterface, i2);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.core.printutil.PrinterUtilityExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    PrinterUtility.this.lambdashowDesignFiles3(dialogInterface, i2);
                }
            }).create().show();
            return;
        }
        showMessage(R.string.str_print_design_file_not_found);
    }
    public void lambdashowDesignFiles2(List list, ArrayAdapter arrayAdapter, DialogInterface dialogInterface, int i2) {
        if (i2 == -1 || list.size() >= i2) {
            dialogInterface.dismiss();
        }
        this.mPrintFileName = (String) arrayAdapter.getItem(i2);
        if (this.mBaseErp.getErpType() == ErpType.TIGER) {
            getPrintValueHeaderAndDetail(this.mFicheId);
        } else {
            getPrintNValueHeaderAndDetail(this.mFicheId);
        }
        dialogInterface.dismiss();
    }
    public void lambdashowDesignFiles3(DialogInterface dialogInterface, int i2) {
        Log.d(TAG, "onClick: process cancel by user");
        showMessage(R.string.str_process_cancel);
        this.mPrintFileName = "cancel";
        dialogInterface.dismiss();
    }
    private void getPrintValueHeaderAndDetail(int i2) {
        this.mProgressDialogBuilder.setMessage(this.mContext.getString(R.string.str_please_wait));
        this.mProgressDialogBuilder.show();
        FicheType ficheType = this.printSettings.getFicheType();
        if (this.mOnlinePrint) {
            if (FicheTypeControlUtils.isFicheTypeInvoiceOrRetailInvoice(ficheType)) {
                getBaseErp().getInvoicePrintValues(this.mFicheId, new PrintValueResponseListener(this), this.mCustomerId);
                return;
            }
            if (FicheTypeControlUtils.isFicheTypeOrder(ficheType)) {
                getBaseErp().getOrderPrintValues(this.mFicheId, new PrintValueResponseListener(this), this.mCustomerId);
                return;
            }
            if (FicheTypeControlUtils.isFicheTypeCashReceipt(ficheType)) {
                getBaseErp().getCashPrintValues(this.mFicheId, new PrintValueResponseListener(this));
                return;
            }
            if (FicheTypeControlUtils.isFicheTypeCreditReceipt(ficheType)) {
                getBaseErp().getCreditCardPrintValues(this.mFicheId, new PrintValueResponseListener(this));
                return;
            }
            if (FicheTypeControlUtils.isFicheTypeCheckReceipt(ficheType)) {
                getBaseErp().getChequePrintValues(this.mFicheId, new PrintValueResponseListener(this));
                return;
            }
            if (FicheTypeControlUtils.isFicheTypeDeedReceipt(ficheType)) {
                getBaseErp().getDeedPrintValues(this.mFicheId, new PrintValueResponseListener(this));
                return;
            }
            if (FicheTypeControlUtils.isFicheTypeDeliveryNote(ficheType)) {
                getBaseErp().getDeliveryNotePrintValues(-1, this.mFicheId, new PrintValueResponseListener(this));
                return;
            }
            if (FicheTypeControlUtils.isFicheTypeDispatch(ficheType)) {
                getBaseErp().getDispatchPrintValues(this.mFicheId, new PrintValueResponseListener(this), this.mCustomerId);
                return;
            } else if (FicheTypeControlUtils.isFicheTypeCaseReceipt(ficheType)) {
                getBaseErp().getCasePrintValues(this.mFicheId, new PrintValueResponseListener(this));
                return;
            } else {
                if (FicheTypeControlUtils.isFicheTypeWhTransfer(ficheType)) {
                    getBaseErp().getWhTransferPrintValues(this.mFicheId, new PrintValueResponseListener(this), this.mCustomerId);
                    return;
                }
                return;
            }
        }
        if (isConnected(this.mContext)) {
            getBaseErp().getOnlineUpdateCustomer(new CustomerOnlineUpdateListener(this), this.mCustomerId);
        } else {
            printValueOffline();
        }
    }
    public void printValue(PrintValueMultiple printValueMultiple) {
        this.mProgressDialogBuilder.dismiss();
        if (printValueMultiple != null) {
            if (!checkResponseDetail(printValueMultiple.getDetailResultXml())) {
                PrintPreviewActivity.sPrintValue = PrintFicheProcess.PrintFiche(this.printSettings.getFicheType().getmValue(), this.printSettings.getDetailLineSize(), this.printSettings.isShowHeader(), this.printSettings.isShowFooter(), this.mPrintFileName, this.printSettings.getUseTurkishCharacter(), printValueMultiple.getHeaderResultXml(), printValueMultiple.getDetailResultXml(), printValueMultiple.getDiscountResultXml());
                Intent intent = new Intent(this.mContext, PrintPreviewActivity.class);
                intent.putExtra(PrintPreviewActivity.EXTRA_PRINT_SETTINGS, this.printSettings);
                intent.putExtra(PrintPreviewActivity.EXTRA_PRINT_CLREF, this.mCustomerId);
                this.mContext.startActivity(intent);
                return;
            }
            showMessage(R.string.str_get_print_value_not_found);
            return;
        }
        showMessage(R.string.str_get_print_value_error);
    }
    private void getPrintNValueHeaderAndDetail(int i2) {
        this.mProgressDialogBuilder.setMessage(this.mContext.getString(R.string.str_please_wait));
        this.mProgressDialogBuilder.show();
        FicheType ficheType = this.printSettings.getFicheType();
        if (FicheTypeControlUtils.isFicheTypeInvoiceOrRetailInvoice(ficheType)) {
            getBaseErp().getInvoicePrintValues(this.mFicheId, new NPrintValueResponseListener(this), this.mCustomerId);
            return;
        }
        if (FicheTypeControlUtils.isFicheTypeOrder(ficheType)) {
            getBaseErp().getOrderPrintValues(this.mFicheId, new NPrintValueResponseListener(this), this.mCustomerId);
            return;
        }
        if (FicheTypeControlUtils.isFicheTypeDispatch(ficheType)) {
            getBaseErp().getDispatchPrintValues(this.mFicheId, new NPrintValueResponseListener(this), this.mCustomerId);
            return;
        }
        if (FicheTypeControlUtils.isFicheTypeCashReceipt(ficheType)) {
            getBaseErp().getCashPrintValues(this.mFicheId, new NPrintValueResponseListener(this));
            return;
        }
        if (FicheTypeControlUtils.isFicheTypeCreditReceipt(ficheType)) {
            getBaseErp().getCreditCardPrintValues(this.mFicheId, new NPrintValueResponseListener(this));
            return;
        }
        if (FicheTypeControlUtils.isFicheTypeCheckReceipt(ficheType)) {
            getBaseErp().getChequePrintValues(this.mFicheId, new NPrintValueResponseListener(this));
            return;
        }
        if (FicheTypeControlUtils.isFicheTypeDeedReceipt(ficheType)) {
            getBaseErp().getDeedPrintValues(this.mFicheId, new NPrintValueResponseListener(this));
            return;
        }
        if (FicheTypeControlUtils.isFicheTypeDeliveryNote(ficheType)) {
            getBaseErp().getDeliveryNotePrintValues(-1, this.mFicheId, new NPrintValueResponseListener(this));
        } else if (FicheTypeControlUtils.isFicheTypeCaseReceipt(ficheType)) {
            getBaseErp().getCasePrintValues(this.mFicheId, new NPrintValueResponseListener(this));
        } else if (FicheTypeControlUtils.isFicheTypeWhTransfer(ficheType)) {
            getBaseErp().getWhTransferPrintValues(this.mFicheId, new NPrintValueResponseListener(this), this.mCustomerId);
        }
    }
    public void printNValue(PrintBaseModel printBaseModel) {
        this.mProgressDialogBuilder.dismiss();
        if (printBaseModel != null) {
            if (!checkNResponseDetail(printBaseModel)) {
                PrintPreviewActivity.sPrintValue = NPrintFicheProcess.PrintFiche(this.printSettings.getFicheType().getmValue(), this.printSettings.getDetailLineSize(), this.printSettings.isShowHeader(), this.printSettings.isShowFooter(), this.mPrintFileName, this.printSettings.getUseTurkishCharacter(), printBaseModel);
                Intent intent = new Intent(this.mContext, PrintPreviewActivity.class);
                intent.putExtra(PrintPreviewActivity.EXTRA_PRINT_SETTINGS, this.printSettings);
                intent.putExtra(PrintPreviewActivity.EXTRA_PRINT_CLREF, this.mCustomerId);
                this.mContext.startActivity(intent);
                return;
            }
            showMessage(R.string.str_get_print_value_not_found);
            return;
        }
        showMessage(R.string.str_get_print_value_error);
    }
    private boolean checkNResponseDetail(PrintBaseModel printBaseModel) {
        return printBaseModel == null || !printBaseModel.hasData();
    }
    private boolean checkResponseDetail(RESULTXML resultxml) {
        return resultxml == null || resultxml.getResultLine() == null || resultxml.getResultLine().size() <= 0;
    }
    public void printValueOffline() {
        this.mProgressDialogBuilder.dismiss();
        PrintPreviewActivity.sPrintValue = PrintFicheProcess.PrintFiche(this.printSettings.getFicheType().getmValue(), this.mFicheId, this.printSettings.getDetailLineSize(), this.printSettings.isShowHeader(), this.printSettings.isShowFooter(), this.mPrintFileName, this.printSettings.getUseTurkishCharacter(), this.mCustomerId);
        Intent intent = new Intent(this.mContext, PrintPreviewActivity.class);
        intent.putExtra(PrintPreviewActivity.EXTRA_PRINT_SETTINGS, this.printSettings);
        intent.putExtra(PrintPreviewActivity.EXTRA_PRINT_CLREF, this.mCustomerId);
        this.mContext.startActivity(intent);
    }

    private record PrintFileNameListResponseListener(
            WeakReference<PrinterUtility> mPrinterUtility) implements ResponseListener<List<DesignJson>> {
            private PrintFileNameListResponseListener(PrinterUtility mPrinterUtility) {
                this.mPrinterUtility = new WeakReference<>(mPrinterUtility);
            }

        public void onResponse(PrintSlipModel list) {
                if (this.mPrinterUtility.get() == null || !isMyServiceRunning(this.mPrinterUtility.get().mContext, PrintService.class)) {
                    return;
                }
                this.mPrinterUtility.get().showDesignFiles(list);
            }

        public void onFailure(Throwable throwable) {
            }

        public void onError(String str) {
                if (this.mPrinterUtility.get() == null || !isMyServiceRunning(this.mPrinterUtility.get().mContext, PrintService.class)) {
                    return;
                }
                this.mPrinterUtility.get().showMessage(str);
            }
        }

    private record PrintValueResponseListener(
            WeakReference<PrinterUtility> mPrinterUtility) implements ResponseListener<PrintValueMultiple> {
            private PrintValueResponseListener(PrinterUtility mPrinterUtility) {
                this.mPrinterUtility = new WeakReference<>(mPrinterUtility);
            }

        public void onResponse(PrintSlipModel printValueMultiple) {
                if (this.mPrinterUtility.get() == null || !isMyServiceRunning(this.mPrinterUtility.get().mContext, PrintService.class)) {
                    return;
                }
                this.mPrinterUtility.get().printValue(printValueMultiple);
            }

        public void onFailure(Throwable throwable) {
            }

        public void onError(String str) {
                if (this.mPrinterUtility.get() == null || !isMyServiceRunning(this.mPrinterUtility.get().mContext, PrintService.class)) {
                    return;
                }
                this.mPrinterUtility.get().showMessage(str);
            }
        }

    private record NPrintValueResponseListener(
            WeakReference<PrinterUtility> mPrinterUtility) implements ResponseListener<PrintBaseModel> {
            private NPrintValueResponseListener(PrinterUtility mPrinterUtility) {
                this.mPrinterUtility = new WeakReference<>(mPrinterUtility);
            }

        public void onResponse(PrintSlipModel printBaseModel) {
                if (this.mPrinterUtility.get() == null || !isMyServiceRunning(this.mPrinterUtility.get().mContext, PrintService.class)) {
                    return;
                }
                if (!TextUtils.isEmpty(printBaseModel.getErrorDesc())) {
                    this.mPrinterUtility.get().showMessage(printBaseModel.getErrorDesc());
                }
                this.mPrinterUtility.get().printNValue(printBaseModel);
            }

        public void onFailure(Throwable throwable) {

            }

            public void onError(String str) {
                if (this.mPrinterUtility.get() == null || !isMyServiceRunning(this.mPrinterUtility.get().mContext, PrintService.class)) {
                    return;
                }
                this.mPrinterUtility.get().showMessage(str);
            }
        }

    private record CustomerOnlineUpdateListener(
            WeakReference<PrinterUtility> mPrinterUtility) implements ResponseListener<Boolean> {
            private CustomerOnlineUpdateListener(PrinterUtility mPrinterUtility) {
                this.mPrinterUtility = new WeakReference<>(mPrinterUtility);
            }

        public void onResponse(PrintSlipModel bool) {
                if (this.mPrinterUtility.get() == null || !isMyServiceRunning(this.mPrinterUtility.get().mContext, PrintService.class)) {
                    return;
                }
                this.mPrinterUtility.get().printValueOffline();
            }

        public void onFailure(Throwable throwable) {
            }

            public void onError(String str) {
                if (this.mPrinterUtility.get() == null || !isMyServiceRunning(this.mPrinterUtility.get().mContext, PrintService.class)) {
                    return;
                }
                this.mPrinterUtility.get().printValueOffline();
            }
        }
    private void getPrintSettings(FicheType ficheType) {
        com.proje.mobilesales.features.model.PrintSettings printSettings = new com.proje.mobilesales.features.model.PrintSettings();
        this.printSettings = printSettings;
        printSettings.setUseTurkishCharacter(Preferences.getPrintPreviewUseTurkishCharacter(this.mContext));
        this.printSettings.setDontShowPreview(Preferences.getShowPrintPreview(this.mContext));
        this.printSettings.setPreviewTime(Preferences.getPrintPreviewWaitTime(this.mContext));
        this.printSettings.setStartCode(Preferences.getPrintPreviewStartCode(this.mContext));
        this.printSettings.setEndCode(Preferences.getPrintPreviewEndCode(this.mContext));
        this.printSettings.setRemoveBlankLines(Preferences.getUseRemoveBlankLines(this.mContext));
        this.printSettings.setPrintLanguage(Preferences.getPrintLanguage(this.mContext));
        this.printSettings.setPrintDestination(Preferences.getPrintDestination(this.mContext));
        this.printSettings.setPrintType(Preferences.getPrintType(this.mContext));
        this.printSettings.setFontId(Preferences.getPPLZFontId(this.mContext));
        this.printSettings.setFontHeight(Preferences.getPPLZFontHeight(this.mContext));
        this.printSettings.setFontWidth(Preferences.getPPLZFontWidth(this.mContext));
        this.printSettings.setFontSpace(Preferences.getPPLZFontSpace(this.mContext));
        this.printSettings.setFontMargin(Preferences.getPPLZFontMargin(this.mContext));
        if (FicheTypeControlUtils.isFicheTypeOrder(ficheType)) {
            this.printSettings.setFicheType(ficheType);
            this.printSettings.setFicheTypee(ficheType.getmValue());
            this.printSettings.setShowHeader(Preferences.getOrderPrintShowHeader(this.mContext));
            this.printSettings.setShowFooter(Preferences.getOrderPrintShowFooter(this.mContext));
            this.printSettings.setDetailLineSize(Preferences.getOrderDetailPrintLineSize(this.mContext));
            this.printSettings.setPrinterFirst(Preferences.getOrderPrinterChoice(this.mContext));
            return;
        }
        if (FicheTypeControlUtils.isFicheTypeOnlyInvoice(ficheType)) {
            com.proje.mobilesales.features.model.PrintSettings printSettings2 = this.printSettings;
            FicheType ficheType2 = FicheType.INVOICE;
            printSettings2.setFicheTypee(ficheType2.getmValue());
            this.printSettings.setFicheType(ficheType2);
            this.printSettings.setShowHeader(Preferences.getInvoicePrintShowHeader(this.mContext));
            this.printSettings.setShowFooter(Preferences.getInvoicePrintShowFooter(this.mContext));
            this.printSettings.setDetailLineSize(Preferences.getInvoiceDetailPrintLineSize(this.mContext));
            this.printSettings.setPrinterFirst(Preferences.getInvoicePrinterChoice(this.mContext));
            return;
        }
        if (FicheTypeControlUtils.isFicheTypeOnlyRetailInvoice(ficheType)) {
            com.proje.mobilesales.features.model.PrintSettings printSettings3 = this.printSettings;
            FicheType ficheType3 = FicheType.INVOICE;
            printSettings3.setFicheTypee(ficheType3.getmValue());
            this.printSettings.setFicheType(ficheType3);
            this.printSettings.setShowHeader(Preferences.getRetailInvoicePrintShowHeader(this.mContext));
            this.printSettings.setShowFooter(Preferences.getRetailInvoicePrintShowFooter(this.mContext));
            this.printSettings.setDetailLineSize(Preferences.getRetailInvoiceDetailPrintLineSize(this.mContext));
            this.printSettings.setPrinterFirst(Preferences.getRetailInvoicePrinterChoice(this.mContext));
            return;
        }
        if (FicheTypeControlUtils.isFicheTypeDispatch(ficheType)) {
            this.printSettings.setFicheTypee(ficheType.getmValue());
            this.printSettings.setFicheType(ficheType);
            this.printSettings.setShowHeader(Preferences.getDispatchPrintShowHeader(this.mContext));
            this.printSettings.setShowFooter(Preferences.getDispatchPrintShowFooter(this.mContext));
            this.printSettings.setDetailLineSize(Preferences.getDispatchDetailPrintLineSize(this.mContext));
            this.printSettings.setPrinterFirst(Preferences.getDispatchPrinterChoice(this.mContext));
            return;
        }
        if (FicheTypeControlUtils.isFicheTypeCashReceipt(ficheType)) {
            this.printSettings.setFicheTypee(ficheType.getmValue());
            this.printSettings.setFicheType(ficheType);
            this.printSettings.setShowHeader(Preferences.getCashPrintShowHeader(this.mContext));
            this.printSettings.setShowFooter(Preferences.getCashPrintShowFooter(this.mContext));
            this.printSettings.setDetailLineSize(Preferences.getCashDetailPrintLineSize(this.mContext));
            this.printSettings.setPrinterFirst(Preferences.getCashPrinterChoice(this.mContext));
            return;
        }
        if (FicheTypeControlUtils.isFicheTypeCreditReceipt(ficheType)) {
            this.printSettings.setFicheTypee(ficheType.getmValue());
            this.printSettings.setFicheType(ficheType);
            this.printSettings.setShowHeader(Preferences.getCreditCardPrintShowHeader(this.mContext));
            this.printSettings.setShowFooter(Preferences.getCreditCardPrintShowFooter(this.mContext));
            this.printSettings.setDetailLineSize(Preferences.getCreditCardDetailPrintLineSize(this.mContext));
            this.printSettings.setPrinterFirst(Preferences.getCreditCardPrinterChoice(this.mContext));
            return;
        }
        if (FicheTypeControlUtils.isFicheTypeCheckReceipt(ficheType)) {
            this.printSettings.setFicheTypee(ficheType.getmValue());
            this.printSettings.setFicheType(ficheType);
            this.printSettings.setShowHeader(Preferences.getChequePrintShowHeader(this.mContext));
            this.printSettings.setShowFooter(Preferences.getChequePrintShowFooter(this.mContext));
            this.printSettings.setDetailLineSize(Preferences.getChequeDetailPrintLineSize(this.mContext));
            this.printSettings.setPrinterFirst(Preferences.getChequePrinterChoice(this.mContext));
            return;
        }
        if (FicheTypeControlUtils.isFicheTypeDeedReceipt(ficheType)) {
            this.printSettings.setFicheTypee(ficheType.getmValue());
            this.printSettings.setFicheType(ficheType);
            this.printSettings.setShowHeader(Preferences.getDeedPrintShowHeader(this.mContext));
            this.printSettings.setShowFooter(Preferences.getDeedPrintShowFooter(this.mContext));
            this.printSettings.setDetailLineSize(Preferences.getDeedDetailPrintLineSize(this.mContext));
            this.printSettings.setPrinterFirst(Preferences.getDeedPrinterChoice(this.mContext));
            return;
        }
        if (FicheTypeControlUtils.isFicheTypeDeliveryNote(ficheType)) {
            this.printSettings.setFicheTypee(ficheType.getmValue());
            this.printSettings.setFicheType(ficheType);
            this.printSettings.setShowHeader(false);
            this.printSettings.setShowFooter(false);
            this.printSettings.setDetailLineSize(Preferences.getDeliveryNoteDetailPrintLineSize(this.mContext));
            this.printSettings.setPrinterFirst(Preferences.getInvoicePrinterChoice(this.mContext));
            return;
        }
        if (FicheTypeControlUtils.isFicheTypeCaseReceipt(ficheType)) {
            this.printSettings.setFicheTypee(ficheType.getmValue());
            this.printSettings.setFicheType(ficheType);
            this.printSettings.setShowHeader(Preferences.getCasePrintShowHeader(this.mContext));
            this.printSettings.setShowFooter(Preferences.getCasePrintShowFooter(this.mContext));
            this.printSettings.setDetailLineSize(Preferences.getCaseDetailPrintLineSize(this.mContext));
            this.printSettings.setPrinterFirst(Preferences.getCasePrinterChoice(this.mContext));
            return;
        }
        if (FicheTypeControlUtils.isFicheTypeWhTransfer(ficheType)) {
            this.printSettings.setFicheTypee(ficheType.getmValue());
            this.printSettings.setFicheType(ficheType);
            this.printSettings.setShowHeader(Preferences.getWhTransferPrintShowHeader(this.mContext));
            this.printSettings.setShowFooter(Preferences.getWhTransferPrintShowFooter(this.mContext));
            this.printSettings.setDetailLineSize(Preferences.getWhTransferDetailPrintLineSize(this.mContext));
            this.printSettings.setPrinterFirst(Preferences.getWhTransferPrinterChoice(this.mContext));
        }
    }
    public void showMessage(String str) {
        this.mProgressDialogBuilder.dismiss();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this.mContext, str, 1).show();
    }
    private void showMessage(int i2) {
        Context context = this.mContext;
        Toast.makeText(context, context.getString(i2), 1).show();
    }
}
