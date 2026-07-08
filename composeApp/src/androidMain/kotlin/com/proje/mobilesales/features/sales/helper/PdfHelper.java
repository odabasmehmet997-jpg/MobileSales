package com.proje.mobilesales.features.sales.helper;

import android.R;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.UriPermission;
import android.net.Uri;
import android.print.PrintAttributes;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.documentfile.provider.DocumentFile;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.edocument.ShowEDocumentResponse;
import com.proje.mobilesales.core.emailreplacer.EmailObject;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.PdfExportOption;
import com.proje.mobilesales.core.enums.PdfOperation;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.CreatePdf;
import com.proje.mobilesales.core.utils.EDocumentHtmlReplacer;
import com.proje.mobilesales.core.utils.FilePath;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import com.proje.mobilesales.features.customer.model.CustomerBeforeBalance;
import com.proje.mobilesales.features.dbmodel.EmailTemplate;
import com.proje.mobilesales.features.dbmodel.Firm;
import com.proje.mobilesales.features.dbmodel.Invoice;
import com.proje.mobilesales.features.dbmodel.Order;
import com.proje.mobilesales.features.model.EDocumentPdfContent;
import com.proje.mobilesales.features.model.EmailReplacerModel;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.repository.BaseSalesRepository;
import com.proje.mobilesales.features.sales.view.BaseSalesViewModel;

import java.io.*;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

import static androidx.browser.customtabs.CustomTabsClient.getPackageName;
import static com.proje.mobilesales.core.enums.NetsisFicheTypeNo.Companion.WhenMappings.EnumSwitchMapping0;

public final class PdfHelper {
    private String cacheReportPdfFilePath;
    private String cacheReportPdfFolderPath;
    private String cacheSalesPdfFilePath;
    private String cacheSalesPdfFolderPath;
    private final Context context;
    private boolean isEArchiveCustomer;
    private View lnClearSavePathPdf;
    private View lnDownloadPdf;
    private View lnSharePdf;
    private View lnShowPdf;
    private BottomSheetDialog mBottomSheetDialog;
    private AlertDialogBuilder<?> mClearPathDialogBuilder;
    private PdfExportOption mPdfExportOption;
    private ProgressDialogBuilder<?> mProgressDialogBuilder;
    private String mSalesPdfName;
    private SharedPreferencesHelper mSharedPreferencesHelper;
    private final String pdfEmailToAddress;
    private final BaseSalesRepository repository;
    private final Sales sales;
    private PdfOperation selectedSalesItemPdfOperation;
    private final BaseSalesViewModel viewModel;

    public static void clearPathlambda4(DialogInterface dialogInterface, int i2) {
    }
    public PdfHelper(Context context, Sales sales) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sales, "sales");
        this.context = context;
        this.sales = sales;
        BaseSalesRepository baseSalesRepository = new BaseSalesRepository();
        this.repository = baseSalesRepository;
        this.viewModel = new BaseSalesViewModel(baseSalesRepository);
        this.pdfEmailToAddress = "pdf@pdf.com";
        this.selectedSalesItemPdfOperation = PdfOperation.EDocumentPdf;
    }
    public PdfExportOption getMPdfExportOption() {
        return this.mPdfExportOption;
    }
    public void setMPdfExportOption(PdfExportOption pdfExportOption) {
        this.mPdfExportOption = pdfExportOption;
    }
    public PdfOperation getSelectedSalesItemPdfOperation() {
        return this.selectedSalesItemPdfOperation;
    }
    public void setSelectedSalesItemPdfOperation(PdfOperation pdfOperation) {
        Intrinsics.checkNotNullParameter(pdfOperation, "<set-?>");
        this.selectedSalesItemPdfOperation = pdfOperation;
    }
    public SharedPreferencesHelper getMSharedPreferencesHelper() {
        return this.mSharedPreferencesHelper;
    }
    public void setMSharedPreferencesHelper(SharedPreferencesHelper sharedPreferencesHelper) {
        this.mSharedPreferencesHelper = sharedPreferencesHelper;
    }
    public void setupBottomSheetDialog(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Context context = this.context;
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mClearPathDialogBuilder = new AlertDialogBuilder.Impl(context, (BaseInjectableActivity) activity2);
        this.mSharedPreferencesHelper = new SharedPreferencesHelper(activity);
        this.cacheReportPdfFolderPath = this.context.getCacheDir().getAbsolutePath() + "/eDespatchPdf";
        this.cacheSalesPdfFolderPath = this.context.getCacheDir().getAbsolutePath() + "/salesPdf";
        String str = this.cacheReportPdfFolderPath;
        Intrinsics.checkNotNull(str);
        clearExistingReports(str);
        String str2 = this.cacheSalesPdfFolderPath;
        Intrinsics.checkNotNull(str2);
        clearExistingReports(str2);
        BottomSheetDialog bottomSheetDialog = null;
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.fragment_pdf_bottom_sheet_dialog, null);
        this.lnSharePdf = inflate.findViewById(R.id.ln_share_pdf);
        this.lnDownloadPdf = inflate.findViewById(R.id.ln_download_pdf);
        this.lnShowPdf = inflate.findViewById(R.id.ln_show_pdf);
        this.lnClearSavePathPdf = inflate.findViewById(R.id.ln_clear_pdf_path);
        Context context2 = this.context;
        Activity activity3 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(context2, (BaseInjectableActivity) activity3);
        BottomSheetDialog bottomSheetDialog2 = new BottomSheetDialog(activity);
        this.mBottomSheetDialog = bottomSheetDialog2;
        bottomSheetDialog2.setContentView(inflate);
        BottomSheetDialog bottomSheetDialog3 = this.mBottomSheetDialog;
        if (bottomSheetDialog3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBottomSheetDialog");
        } else {
            bottomSheetDialog = bottomSheetDialog3;
        }
        bottomSheetDialog.show();
        boolean z = Intrinsics.areEqual(this.viewModel.getSqlHelper().getLogoParamValue("USEEARCHIVE"), BuildConfig.NETSIS_DEMO_PASSWORD);
        this.isEArchiveCustomer = z;
        View view = this.lnShowPdf;
        Intrinsics.checkNotNull(view);
        view.setOnClickListener(new View.OnClickListener() {  
            public void onClick(View view2) {
                PdfHelper.setupBottomSheetDialoglambda0(PdfHelper.this, view2);
            }
        });
        View view2 = this.lnSharePdf;
        Intrinsics.checkNotNull(view2);
        view2.setOnClickListener(new View.OnClickListener() {  
            public void onClick(View view3) {
                PdfHelper.setupBottomSheetDialoglambda1(PdfHelper.this, view3);
            }
        });
        View view3 = this.lnDownloadPdf;
        Intrinsics.checkNotNull(view3);
        view3.setOnClickListener(new View.OnClickListener() {  
            public void onClick(View view4) {
                PdfHelper.setupBottomSheetDialoglambda2(PdfHelper.this, view4);
            }
        });
        View view4 = this.lnClearSavePathPdf;
        Intrinsics.checkNotNull(view4);
        view4.setOnClickListener(new View.OnClickListener() {  
            public void onClick(View view5) {
                PdfHelper.setupBottomSheetDialoglambda3(PdfHelper.this, view5);
            }
        });
    }
    public static void setupBottomSheetDialoglambda0(PdfHelper this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.mPdfExportOption = PdfExportOption.Preview;
        this0.getPdf();
    }
    public static void setupBottomSheetDialoglambda1(PdfHelper this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.mPdfExportOption = PdfExportOption.Share;
        this0.getPdf();
    }
    public static void setupBottomSheetDialoglambda2(PdfHelper this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.mPdfExportOption = PdfExportOption.Download;
        this0.getPdf();
    }

    
    public static void setupBottomSheetDialoglambda3(PdfHelper this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.clearPath();
    }

    private void getPdf() {
        PdfOperation pdfOperation = this.selectedSalesItemPdfOperation;
        if (pdfOperation == PdfOperation.EDocumentPdf || pdfOperation == PdfOperation.EDocumentDraftPdf || pdfOperation == PdfOperation.EDocumentDraftPdfThreeInc) {
            getEDocumentSalesForPdf();
        } else {
            getSalesFicheForPdf();
        }
        BottomSheetDialog bottomSheetDialog = this.mBottomSheetDialog;
        if (bottomSheetDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBottomSheetDialog");
            bottomSheetDialog = null;
        }
        bottomSheetDialog.dismiss();
    }

    private void clearPath() {
        PdfOperation pdfOperation = this.selectedSalesItemPdfOperation;
        if (pdfOperation == PdfOperation.EDocumentPdf || pdfOperation == PdfOperation.EDocumentDraftPdf || pdfOperation == PdfOperation.EDocumentDraftPdfThreeInc) {
            SharedPreferencesHelper sharedPreferencesHelper = this.mSharedPreferencesHelper;
            Intrinsics.checkNotNull(sharedPreferencesHelper);
            sharedPreferencesHelper.removeEDespatchPdfPath();
        } else {
            SharedPreferencesHelper sharedPreferencesHelper2 = this.mSharedPreferencesHelper;
            Intrinsics.checkNotNull(sharedPreferencesHelper2);
            sharedPreferencesHelper2.removeSalesPdfPath();
        }
        BottomSheetDialog bottomSheetDialog = this.mBottomSheetDialog;
        AlertDialogBuilder<?> alertDialogBuilder = null;
        if (bottomSheetDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBottomSheetDialog");
            bottomSheetDialog = null;
        }
        bottomSheetDialog.dismiss();
        AlertDialogBuilder<?> alertDialogBuilder2 = this.mClearPathDialogBuilder;
        if (alertDialogBuilder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mClearPathDialogBuilder");
        } else {
            alertDialogBuilder = alertDialogBuilder2;
        }
        alertDialogBuilder.setMessage(this.context.getString(R.string.str_pdf_save_path_cleared)).setCancelable(false).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.helper.PdfHelperExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                PdfHelper.clearPathlambda4(dialogInterface, i2);
            }
        }).create().show();
    }

    private void clearExistingReports(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                File[] listFiles = file.listFiles();
                Intrinsics.checkNotNull(listFiles);
                for (File file2 : listFiles) {
                    file2.delete();
                }
                return;
            }
            file.mkdir();
        } catch (Exception e2) {
            Log.e("clearExistingReports", "clearExistingReports", e2);
        }
    }

    private void getEDocumentSalesForPdf() {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        if (progressDialogBuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
            progressDialogBuilder = null;
        }
        progressDialogBuilder.setMessage(this.context.getString(R.string.str_fiche_loading)).setCancelable(false).show();
        PdfOperation pdfOperation = this.selectedSalesItemPdfOperation;
        if (pdfOperation == PdfOperation.EDocumentDraftPdf) {
            if (this.sales.getmSalesType() == SalesType.DISPATCH) {
                BaseSalesViewModel baseSalesViewModel = this.viewModel;
                String ficheNo = this.sales.getFicheNo();
                Intrinsics.checkNotNull(ficheNo);
                baseSalesViewModel.getEDocumentContent(ficheNo, new EDocumentPdfContentListener(this, "edespatch_html_template", "edespatch_line_template", PrintAttributes.MediaSize.ISO_A4, this.sales.getmSalesType()), this.sales.getmSalesType(), Boolean.valueOf(this.isEArchiveCustomer));
                return;
            }
            if (this.sales.getmSalesType() == SalesType.INVOICE) {
                if (this.isEArchiveCustomer) {
                    BaseSalesViewModel baseSalesViewModel2 = this.viewModel;
                    String ficheNo2 = this.sales.getFicheNo();
                    Intrinsics.checkNotNull(ficheNo2);
                    baseSalesViewModel2.getEDocumentContent(ficheNo2, new EDocumentPdfContentListener(this, "earchive_invoice_html_template", "einvoice_line_template", PrintAttributes.MediaSize.ISO_A4, this.sales.getmSalesType()), this.sales.getmSalesType(), Boolean.valueOf(this.isEArchiveCustomer));
                    return;
                }
                BaseSalesViewModel baseSalesViewModel3 = this.viewModel;
                String ficheNo3 = this.sales.getFicheNo();
                Intrinsics.checkNotNull(ficheNo3);
                baseSalesViewModel3.getEDocumentContent(ficheNo3, new EDocumentPdfContentListener(this, "einvoice_html_template", "einvoice_line_template", PrintAttributes.MediaSize.ISO_A4, this.sales.getmSalesType()), this.sales.getmSalesType(), Boolean.valueOf(this.isEArchiveCustomer));
                return;
            }
            return;
        }
        if (pdfOperation == PdfOperation.EDocumentDraftPdfThreeInc) {
            if (this.sales.getmSalesType() == SalesType.INVOICE) {
                if (this.isEArchiveCustomer) {
                    BaseSalesViewModel baseSalesViewModel4 = this.viewModel;
                    String ficheNo4 = this.sales.getFicheNo();
                    Intrinsics.checkNotNull(ficheNo4);
                    baseSalesViewModel4.getEDocumentContent(ficheNo4, new EDocumentPdfContentListener(this, "earchive_invoice_3_inc_html_template", "edocument_line_template_for_3_inc", setThreeIncPdfMediaSize(), this.sales.getmSalesType()), this.sales.getmSalesType(), Boolean.valueOf(this.isEArchiveCustomer));
                    return;
                }
                BaseSalesViewModel baseSalesViewModel5 = this.viewModel;
                String ficheNo5 = this.sales.getFicheNo();
                Intrinsics.checkNotNull(ficheNo5);
                baseSalesViewModel5.getEDocumentContent(ficheNo5, new EDocumentPdfContentListener(this, "einvoice_3_inc_html_template", "edocument_line_template_for_3_inc", setThreeIncPdfMediaSize(), this.sales.getmSalesType()), this.sales.getmSalesType(), Boolean.valueOf(this.isEArchiveCustomer));
                return;
            }
            if (this.sales.getmSalesType() == SalesType.DISPATCH) {
                if (this.viewModel.erpType() == ErpType.NETSIS) {
                    BaseSalesViewModel baseSalesViewModel6 = this.viewModel;
                    String ficheNo6 = this.sales.getFicheNo();
                    Intrinsics.checkNotNull(ficheNo6);
                    baseSalesViewModel6.getEDocumentContent(ficheNo6, new EDocumentPdfContentListener(this, "edespatch_3_inc_html_template_netsis", "edocument_line_template_for_3_inc", setThreeIncPdfMediaSize(), this.sales.getmSalesType()), this.sales.getmSalesType(), Boolean.valueOf(this.isEArchiveCustomer));
                    return;
                }
                BaseSalesViewModel baseSalesViewModel7 = this.viewModel;
                String ficheNo7 = this.sales.getFicheNo();
                Intrinsics.checkNotNull(ficheNo7);
                baseSalesViewModel7.getEDocumentContent(ficheNo7, new EDocumentPdfContentListener(this, "edespatch_3_inc_html_template", "edocument_line_template_for_3_inc", setThreeIncPdfMediaSize(), this.sales.getmSalesType()), this.sales.getmSalesType(), Boolean.valueOf(this.isEArchiveCustomer));
                return;
            }
            return;
        }
        BaseSalesViewModel baseSalesViewModel8 = this.viewModel;
        Sales sales = this.sales;
        baseSalesViewModel8.getEDocument(sales, sales.getFicheType(), new ShowEDocumentContentListener(this));
    }

    private PrintAttributes.MediaSize setThreeIncPdfMediaSize() {
        return new PrintAttributes.MediaSize(BuildConfig.NETSIS_DEMO_PASSWORD, BuildConfig.NETSIS_DEMO_PASSWORD, 2900, 7800);
    }

    private void getSalesFicheForPdf() {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        if (progressDialogBuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
            progressDialogBuilder = null;
        }
        progressDialogBuilder.setMessage(this.context.getString(R.string.str_fiche_loading)).setCancelable(false).show();
        onSalesGet(this.sales);
    }

    private record ShowEDocumentContentListener(
            WeakReference<PdfHelper> pdfHelperWeakReference) implements ResponseListener<ShowEDocumentResponse> {
            private ShowEDocumentContentListener(PdfHelper pdfHelperWeakReference) {
                Intrinsics.checkNotNullParameter(pdfHelperWeakReference, "pdfHelper");
                this.pdfHelperWeakReference = new WeakReference<>(pdfHelperWeakReference);
            }


        public void onResponse(PrintSlipModel showEDocumentResponse) {
                PdfHelper pdfHelper = this.pdfHelperWeakReference.get();
                if (pdfHelper != null) {
                    pdfHelper.onShowEDocumentContent(showEDocumentResponse, "");
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }


            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                PdfHelper pdfHelper = this.pdfHelperWeakReference.get();
                if (pdfHelper != null) {
                    pdfHelper.onShowEDocumentContent(null, errorMessage);
                }
            }
        }

    
    public void onShowEDocumentContent(ShowEDocumentResponse showEDocumentResponse, String str) {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        ProgressDialogBuilder<?> progressDialogBuilder2 = null;
        if (progressDialogBuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
            progressDialogBuilder = null;
        }
        progressDialogBuilder.dismiss();
        if (showEDocumentResponse != null) {
            if (!showEDocumentResponse.isSuccess()) {
                new AlertDialog.Builder(this.context).setMessage(showEDocumentResponse.getErrorDesc()).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.helper.PdfHelperExternalSyntheticLambda5
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        PdfHelper.onShowEDocumentContentlambda5(dialogInterface, i2);
                    }
                }).create().show();
                return;
            }
            if (showEDocumentResponse.isSuccess() && TextUtils.isEmpty(showEDocumentResponse.getHtmlContent())) {
                Context context = this.context;
                Toast.makeText(context, context.getString(R.string.str_get_print_value_not_found), Toast.LENGTH_LONG).show();
                return;
            }
            try {
                this.cacheReportPdfFilePath = "";
                ProgressDialogBuilder<?> progressDialogBuilder3 = this.mProgressDialogBuilder;
                if (progressDialogBuilder3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
                    progressDialogBuilder3 = null;
                }
                progressDialogBuilder3.setMessage(this.context.getString(R.string.str_please_wait)).setCancelable(true).show();
                String ettn = showEDocumentResponse.getEttn();
                Intrinsics.checkNotNullExpressionValue(ettn, "getEttn(...)");
                String htmlContent = showEDocumentResponse.getHtmlContent();
                Intrinsics.checkNotNullExpressionValue(htmlContent, "getHtmlContent(...)");
                PrintAttributes.MediaSize ISO_A4 = PrintAttributes.MediaSize.ISO_A4;
                Intrinsics.checkNotNullExpressionValue(ISO_A4, "ISO_A4");
                createPdfFromHtmlContent(ettn, htmlContent, ISO_A4);
                return;
            } catch (Exception e2) {
                Log.e("onShowEDocumentContent", "exportPdf", e2);
                ProgressDialogBuilder<?> progressDialogBuilder4 = this.mProgressDialogBuilder;
                if (progressDialogBuilder4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
                } else {
                    progressDialogBuilder2 = progressDialogBuilder4;
                }
                progressDialogBuilder2.dismiss();
                Context context2 = this.context;
                Toast.makeText(context2, context2.getString(R.string.str_get_print_value_error), Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Toast.makeText(this.context, str, Toast.LENGTH_LONG).show();
    }

    
    public static void onShowEDocumentContentlambda5(DialogInterface dialog, int i2) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }

    private void createPdfFromHtmlContent(String str, String str2, PrintAttributes.MediaSize mediaSize) {
        CreatePdf content = new CreatePdf(this.context).setPdfName(str).openPrintDialog(false).setContentBaseUrl(null).setPageSize(mediaSize).setContent(str2);
        String str3 = this.cacheReportPdfFolderPath;
        Intrinsics.checkNotNull(str3);
        content.setFilePath(str3).setCallbackListener(new CreatePdf.PdfCallbackListener() {
            class WhenMappings {
                public final int[] EnumSwitchMapping0;
                {
                    int[] iArr = new int[PdfExportOption.values().length];
                    try {
                        iArr[PdfExportOption.Share.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[PdfExportOption.Preview.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[PdfExportOption.Download.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    EnumSwitchMapping0 = iArr;
                }
            }

            public void onFailure(String s) {
                Context context;
                Context context2;
                Intrinsics.checkNotNullParameter(s, "s");
                ProgressDialogBuilder progressDialogBuilder = PdfHelper.this.mProgressDialogBuilder;
                if (progressDialogBuilder == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
                    progressDialogBuilder = null;
                }
                progressDialogBuilder.dismiss();
                context = PdfHelper.this.context;
                context2 = PdfHelper.this.context;
                Toast.makeText(context, context2.getString(R.string.str_get_print_value_error), Toast.LENGTH_SHORT).show();
            }

            public void onSuccess(String s) {
                Context context;
                Context context2;
                Context context3;
                Context context4;
                Context context5;
                Intrinsics.checkNotNullParameter(s, "s");
                ProgressDialogBuilder progressDialogBuilder = PdfHelper.this.mProgressDialogBuilder;
                if (progressDialogBuilder == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
                    progressDialogBuilder = null;
                }
                progressDialogBuilder.dismiss();
                PdfHelper.this.cacheReportPdfFilePath = s;
                context = PdfHelper.this.context;
                Uri uriForFile = FileProvider.getUriForFile(context, "com.proje.mobilesales.fileprovider", new File(s));
                PdfExportOption mPdfExportOption = PdfHelper.this.getMPdfExportOption();
                int i2 = mPdfExportOption == null ? -1 :  EnumSwitchMapping0[mPdfExportOption.ordinal()];
                if (i2 == 1) {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("application/pdf");
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.setDataAndType(uriForFile, "application/pdf");
                    intent.putExtra("android.intent.extra.STREAM", uriForFile);
                    context2 = PdfHelper.this.context;
                    context3 = PdfHelper.this.context;
                    context2.startActivity(Intent.createChooser(intent, context3.getString(R.string.menu_pdf_share)));
                    return;
                }
                if (i2 != 2) {
                    if (i2 != 3) {
                        return;
                    }
                    PdfHelper.this.createPdfToSelectedFolder();
                    return;
                }
                try {
                    Intent intent2 = new Intent();
                    intent2.setAction("android.intent.action.VIEW").setClassName(  getPackageName(), "com.proje.mobilesales.features.settings.view.activity.PreferenceActivity");
                    intent2.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    Intent intent = intent2.setDataAndType(uriForFile, "application/pdf");
                    intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context5 = PdfHelper.this.context;
                    context5.startActivity(intent2);
                } catch (ActivityNotFoundException e2) {
                    context4 = PdfHelper.this.context;
                    Toast.makeText(context4, R.string.str_no_application_found_to_open_pdf, Toast.LENGTH_LONG).show();
                    Log.e("UserReportsActivity", "Activity not found for pdf", e2);
                }
            }
        }).create();
    }

    private record EDocumentPdfContentListener(WeakReference<PdfHelper> pdfHelperWeakReference, String mTemplateType,
                                               String mAssetName, PrintAttributes.MediaSize mMediaSize,
                                               SalesType mSalesType) implements ResponseListener<EDocumentPdfContent> {
            private EDocumentPdfContentListener(PdfHelper pdfHelperWeakReference, String mTemplateType, String mAssetName, PrintAttributes.MediaSize mMediaSize, SalesType mSalesType) {
                Intrinsics.checkNotNullParameter(pdfHelperWeakReference, "pdfHelper");
                Intrinsics.checkNotNullParameter(mTemplateType, "templateType");
                Intrinsics.checkNotNullParameter(mAssetName, "assetName");
                this.pdfHelperWeakReference = new WeakReference<>(pdfHelperWeakReference);
                this.mTemplateType = mTemplateType;
                this.mMediaSize = mMediaSize;
                this.mAssetName = mAssetName;
                this.mSalesType = mSalesType;
            }


        public void onResponse(PrintSlipModel eDocumentPdfContent) {
                PdfHelper pdfHelper = this.pdfHelperWeakReference.get();
                if (pdfHelper != null) {
                    pdfHelper.onGetEDocumentPdfContent(eDocumentPdfContent, "", this.mTemplateType, this.mAssetName, this.mMediaSize, this.mSalesType);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }


            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                PdfHelper pdfHelper = this.pdfHelperWeakReference.get();
                if (pdfHelper != null) {
                    pdfHelper.onGetEDocumentPdfContent(null, errorMessage, this.mTemplateType, this.mAssetName, this.mMediaSize, this.mSalesType);
                }
            }
        }

    
    public void onGetEDocumentPdfContent(EDocumentPdfContent eDocumentPdfContent, String str, String str2, String str3, PrintAttributes.MediaSize mediaSize, SalesType salesType) {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        if (progressDialogBuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
            progressDialogBuilder = null;
        }
        progressDialogBuilder.dismiss();
        if (eDocumentPdfContent != null) {
            if (!TextUtils.isEmpty(eDocumentPdfContent.getErrorDesc())) {
                Toast.makeText(this.context, eDocumentPdfContent.getErrorDesc(), Toast.LENGTH_LONG).show();
                return;
            }
            if (this.viewModel.erpType() != ErpType.NETSIS) {
                BaseSalesViewModel baseSalesViewModel = this.viewModel;
                String firmNr = baseSalesViewModel.user().getFirmNr();
                Intrinsics.checkNotNullExpressionValue(firmNr, "getFirmNr(...)");
                List<Firm> tableForFirmFromSqlHelper = null;
                try {
                    tableForFirmFromSqlHelper = baseSalesViewModel.getTableForFirmFromSqlHelper(Firm.class, "NR=?", new String[]{firmNr});
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                eDocumentPdfContent.setFirm((tableForFirmFromSqlHelper == null || tableForFirmFromSqlHelper.get(0) == null) ? new Firm() : tableForFirmFromSqlHelper.get(0));
            }
            try {
                eDocumentPdfContent.setLocalCurrencyCode(this.viewModel.getLocalCurrencyCode());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Intrinsics.checkNotNull(mediaSize);
            exportEDocumentPdf(eDocumentPdfContent, str2, str3, mediaSize, salesType);
            return;
        }
        Toast.makeText(this.context, str, Toast.LENGTH_LONG).show();
    }

    private void exportEDocumentPdf(EDocumentPdfContent eDocumentPdfContent, String str, String str2, PrintAttributes.MediaSize mediaSize, SalesType salesType) {
        String invoiceNo;
        this.cacheReportPdfFilePath = "";
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        ProgressDialogBuilder<?> progressDialogBuilder2 = null;
        if (progressDialogBuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
            progressDialogBuilder = null;
        }
        progressDialogBuilder.setMessage(this.context.getString(R.string.str_please_wait)).show();
        if (this.viewModel.erpType() == ErpType.NETSIS) {
            invoiceNo = eDocumentPdfContent.getEDocumentPdfHeaderNetsis().getFicheNo();
        } else if (salesType == SalesType.DISPATCH) {
            invoiceNo = eDocumentPdfContent.getEDespatchPdfHeader().getFicheNo();
        } else {
            invoiceNo = salesType == SalesType.INVOICE ? eDocumentPdfContent.getEInvoicePdfHeader().getInvoiceNo() : null;
        }
        try {
            String prepareHtml = new EDocumentHtmlReplacer(eDocumentPdfContent).prepareHtml(str, str2, salesType);
            String str3 = this.cacheReportPdfFolderPath;
            Intrinsics.checkNotNull(str3);
            clearExistingReports(str3);
            Intrinsics.checkNotNull(invoiceNo);
            Intrinsics.checkNotNull(prepareHtml);
            createPdfFromHtmlContent(invoiceNo, prepareHtml, mediaSize);
        } catch (Exception e2) {
            Log.e("exportEDocumentPdf", "exportPdf", e2);
            ProgressDialogBuilder<?> progressDialogBuilder3 = this.mProgressDialogBuilder;
            if (progressDialogBuilder3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
            } else {
                progressDialogBuilder2 = progressDialogBuilder3;
            }
            progressDialogBuilder2.dismiss();
            Context context = this.context;
            Toast.makeText(context, context.getString(R.string.str_get_print_value_error), Toast.LENGTH_SHORT).show();
        }
    }

    private void onSalesGet(Sales sales) {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        ProgressDialogBuilder<?> progressDialogBuilder2 = null;
        if (progressDialogBuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
            progressDialogBuilder = null;
        }
        progressDialogBuilder.dismiss();
        if (sales != null) {
            try {
                if (this.viewModel.getTableForEmailTemplateFromSqlHelper(EmailTemplate.class, "FORMTYPE=?", new String[]{String.valueOf(sales.getEmailTemplateType().getmValue())}).isEmpty()) {
                    Context context = this.context;
                    Toast.makeText(context, context.getString(R.string.str_not_found_any_email_form_design), Toast.LENGTH_LONG).show();
                    return;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ProgressDialogBuilder<?> progressDialogBuilder3 = this.mProgressDialogBuilder;
            if (progressDialogBuilder3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
            } else {
                progressDialogBuilder2 = progressDialogBuilder3;
            }
            progressDialogBuilder2.setMessage(this.context.getString(R.string.str_please_wait)).show();
            this.mSalesPdfName = sales.getFicheNo();
            Class cls = SalesUtils.isSalesTypeOrderOrDemand(sales.getmSalesType()) ? Order.class : Invoice.class;
            EmailReplacerModel emailReplacerModel = new EmailReplacerModel();
            emailReplacerModel.setEmailReplacer(sales);
            emailReplacerModel.setFicheRef(sales.getFicheRef());
            emailReplacerModel.setClRef(sales.getClRef());
            emailReplacerModel.setEmail(this.pdfEmailToAddress);
            this.viewModel.getCustomerNowAndBeforeBalance(sales.getClRef(), cls, sales.getFicheRef(), new CustomerNowAndBeforeResponseListener(this, emailReplacerModel));
        }
    }

    private record CustomerNowAndBeforeResponseListener(WeakReference<PdfHelper> pdfHelperWeakReference,
                                                        WeakReference<EmailReplacerModel> emailReplacerModelWeakReference) implements ResponseListener<ArrayList<CustomerBeforeBalance>> {
            private CustomerNowAndBeforeResponseListener(PdfHelper pdfHelperWeakReference, EmailReplacerModel emailReplacerModelWeakReference) {
                Intrinsics.checkNotNullParameter(pdfHelperWeakReference, "pdfHelper");
                Intrinsics.checkNotNullParameter(emailReplacerModelWeakReference, "emailReplacerModel");
                this.pdfHelperWeakReference = new WeakReference<>(pdfHelperWeakReference);
                this.emailReplacerModelWeakReference = new WeakReference<>(emailReplacerModelWeakReference);
            }

        public void onResponse(PrintSlipModel arrayList) {
                PdfHelper pdfHelper = this.pdfHelperWeakReference.get();
                if (pdfHelper != null) {
                    ProgressDialogBuilder progressDialogBuilder = null;
                    if (arrayList != null && !arrayList.isEmpty()) {
                        PdfHelper pdfHelper2 = this.pdfHelperWeakReference.get();
                        Intrinsics.checkNotNull(pdfHelper2);
                        ProgressDialogBuilder progressDialogBuilder2 = pdfHelper2.mProgressDialogBuilder;
                        if (progressDialogBuilder2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
                        } else {
                            progressDialogBuilder = progressDialogBuilder2;
                        }
                        progressDialogBuilder.dismiss();
                        pdfHelper.onCustomerNowAndBefore(this.emailReplacerModelWeakReference.get(), arrayList);
                        return;
                    }
                    PdfHelper pdfHelper3 = this.pdfHelperWeakReference.get();
                    Intrinsics.checkNotNull(pdfHelper3);
                    ProgressDialogBuilder progressDialogBuilder3 = pdfHelper3.mProgressDialogBuilder;
                    if (progressDialogBuilder3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
                        progressDialogBuilder3 = null;
                    }
                    progressDialogBuilder3.dismiss();
                    pdfHelper.onCustomerNowAndBefore(this.emailReplacerModelWeakReference.get(), null);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                PdfHelper pdfHelper = this.pdfHelperWeakReference.get();
                if (pdfHelper != null) {
                    Log.d("CustomerNowAndBefore", "onError: " + errorMessage);
                    pdfHelper.onCustomerNowAndBefore(this.emailReplacerModelWeakReference.get(), null);
                }
            }
        }
    public void onCustomerNowAndBefore(EmailReplacerModel emailReplacerModel, ArrayList<CustomerBeforeBalance> arrayList) {
        CustomerBeforeBalance customerBeforeBalance;
        if (emailReplacerModel != null) {
            ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
            if (progressDialogBuilder == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
                progressDialogBuilder = null;
            }
            progressDialogBuilder.setMessage(this.context.getString(R.string.str_applying_email_form_design)).show();
            BaseSalesViewModel baseSalesViewModel = this.viewModel;
            if (arrayList == null || arrayList.isEmpty()) {
                customerBeforeBalance = new CustomerBeforeBalance(0.0d, 0.0d, 3, null);
            } else {
                CustomerBeforeBalance customerBeforeBalance2 = arrayList.get(0);
                Intrinsics.checkNotNullExpressionValue(customerBeforeBalance2, "get(...)");
                customerBeforeBalance = customerBeforeBalance2;
            }
            baseSalesViewModel.replaceSalesFicheHtml(emailReplacerModel, customerBeforeBalance, new ReplaceHtmlResponseListener(this));
            return;
        }
        Context context = this.context;
        Toast.makeText(context, context.getString(R.string.str_get_print_value_error), Toast.LENGTH_LONG).show();
    }

    private record ReplaceHtmlResponseListener(
            WeakReference<PdfHelper> pdfHelperWeakReference) implements ResponseListener<EmailObject> {
            private ReplaceHtmlResponseListener(PdfHelper pdfHelperWeakReference) {
                Intrinsics.checkNotNullParameter(pdfHelperWeakReference, "pdfHelper");
                this.pdfHelperWeakReference = new WeakReference<>(pdfHelperWeakReference);
            }

        public void onResponse(PrintSlipModel emailObject) {
                PdfHelper pdfHelper = this.pdfHelperWeakReference.get();
                if (pdfHelper != null) {
                    ProgressDialogBuilder progressDialogBuilder = null;
                    if (emailObject != null) {
                        PdfHelper pdfHelper2 = this.pdfHelperWeakReference.get();
                        Intrinsics.checkNotNull(pdfHelper2);
                        ProgressDialogBuilder progressDialogBuilder2 = pdfHelper2.mProgressDialogBuilder;
                        if (progressDialogBuilder2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
                        } else {
                            progressDialogBuilder = progressDialogBuilder2;
                        }
                        progressDialogBuilder.dismiss();
                        pdfHelper.onReplaceHtml("", emailObject);
                        return;
                    }
                    PdfHelper pdfHelper3 = this.pdfHelperWeakReference.get();
                    Intrinsics.checkNotNull(pdfHelper3);
                    ProgressDialogBuilder progressDialogBuilder3 = pdfHelper3.mProgressDialogBuilder;
                    if (progressDialogBuilder3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
                        progressDialogBuilder3 = null;
                    }
                    progressDialogBuilder3.dismiss();
                    pdfHelper.onReplaceHtml("Error replacing HTML", null);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                PdfHelper pdfHelper = this.pdfHelperWeakReference.get();
                if (pdfHelper != null) {
                    Log.d("ReplaceHtml", "onError: " + errorMessage);
                    pdfHelper.onReplaceHtml(errorMessage, null);
                }
            }
        }
    public void onReplaceHtml(String str, EmailObject emailObject) {
        if (str.length() == 0 && emailObject != null) {
            if (emailObject.getTo() != null) {
                String[] to = emailObject.getTo();
                Intrinsics.checkNotNullExpressionValue(to, "getTo(...)");
                if (!(to.length == 0) && Intrinsics.areEqual(emailObject.getTo()[0], this.pdfEmailToAddress)) {
                    exportSalesPdf(emailObject.getHtml());
                    return;
                }
            }
            BaseErpActivity baseErpActivity = (BaseErpActivity) this.context;
            Intrinsics.checkNotNull(baseErpActivity);
            baseErpActivity.sendFicheMail(emailObject);
            return;
        }
        Context context = this.context;
        Toast.makeText(context, context.getString(R.string.str_send_email_failed), Toast.LENGTH_LONG).show();
    }
    private void exportSalesPdf(String str) {
        this.cacheSalesPdfFilePath = "";
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        ProgressDialogBuilder<?> progressDialogBuilder2 = null;
        if (progressDialogBuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
            progressDialogBuilder = null;
        }
        progressDialogBuilder.setMessage(this.context.getString(R.string.str_please_wait)).show();
        try {
            CreatePdf createPdf = new CreatePdf(this.context);
            String str2 = this.mSalesPdfName;
            Intrinsics.checkNotNull(str2);
            CreatePdf contentBaseUrl = createPdf.setPdfName(str2).openPrintDialog(false).setContentBaseUrl(null);
            PrintAttributes.MediaSize ISO_A4 = PrintAttributes.MediaSize.ISO_A4;
            Intrinsics.checkNotNullExpressionValue(ISO_A4, "ISO_A4");
            CreatePdf pageSize = contentBaseUrl.setPageSize(ISO_A4);
            Intrinsics.checkNotNull(str);
            CreatePdf content = pageSize.setContent(str);
            String str3 = this.cacheSalesPdfFolderPath;
            Intrinsics.checkNotNull(str3);
            content.setFilePath(str3);
            content.setCallbackListener(new CreatePdf.PdfCallbackListener() {
                class WhenMappings {
                    public   final int[] EnumSwitchMapping0;

                    {
                        int[] iArr = new int[PdfExportOption.values().length];
                        try {
                            iArr[PdfExportOption.Share.ordinal()] = 1;
                        } catch (NoSuchFieldError unused) {
                        }
                        try {
                            iArr[PdfExportOption.Preview.ordinal()] = 2;
                        } catch (NoSuchFieldError unused2) {
                        }
                        try {
                            iArr[PdfExportOption.Download.ordinal()] = 3;
                        } catch (NoSuchFieldError unused3) {
                        }
                        EnumSwitchMapping0 = iArr;
                    }
                }

                public void onFailure(String s) {
                    Context context;
                    Context context2;
                    Intrinsics.checkNotNullParameter(s, "s");
                    ProgressDialogBuilder progressDialogBuilder3 = PdfHelper.this.mProgressDialogBuilder;
                    if (progressDialogBuilder3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
                        progressDialogBuilder3 = null;
                    }
                    progressDialogBuilder3.dismiss();
                    context = PdfHelper.this.context;
                    context2 = PdfHelper.this.context;
                    Toast.makeText(context, context2.getString(R.string.str_get_print_value_error), Toast.LENGTH_SHORT).show();
                }

                public void onSuccess(String s) {
                    Context context;
                    Context context2;
                    Context context3;
                    Context context4;
                    Context context5;
                    Intrinsics.checkNotNullParameter(s, "s");
                    ProgressDialogBuilder progressDialogBuilder3 = PdfHelper.this.mProgressDialogBuilder;
                    if (progressDialogBuilder3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
                        progressDialogBuilder3 = null;
                    }
                    progressDialogBuilder3.dismiss();
                    PdfHelper.this.cacheSalesPdfFilePath = s;
                    context = PdfHelper.this.context;
                    Uri uriForFile = FileProvider.getUriForFile(context, "com.proje.mobilesales.fileprovider", new File(s));
                    PdfExportOption mPdfExportOption = PdfHelper.this.getMPdfExportOption();
                    int i2 = mPdfExportOption == null ? -1 : EnumSwitchMapping0[mPdfExportOption.ordinal()];
                    if (i2 == 1) {
                        Intent intent = new Intent("android.intent.action.SEND");
                        intent.setType("application/pdf");
                        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        intent.setDataAndType(uriForFile, "application/pdf");
                        intent.putExtra("android.intent.extra.STREAM", uriForFile);
                        context2 = PdfHelper.this.context;
                        context3 = PdfHelper.this.context;
                        context2.startActivity(Intent.createChooser(intent, context3.getString(R.string.menu_pdf_share)));
                        return;
                    }
                    if (i2 != 2) {
                        if (i2 != 3) {
                            return;
                        }
                        PdfHelper.this.createPdfToSelectedFolder();
                        return;
                    }
                    try {
                        Intent intent2 = new Intent();
                        intent2.setAction("android.intent.action.VIEW").setClassName(/* TODO: provide the application ID. For example: */ getPackageName(), "com.proje.mobilesales.features.settings.view.activity.PreferenceActivity");
                        intent2.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        intent2.setDataAndType(uriForFile, "application/pdf");
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context5 = PdfHelper.this.context;
                        context5.startActivity(intent2);
                    } catch (ActivityNotFoundException e2) {
                        context4 = PdfHelper.this.context;
                        Toast.makeText(context4, R.string.str_no_application_found_to_open_pdf, Toast.LENGTH_LONG).show();
                        Log.e("PdfHelper", "Activity not found for pdf", e2);
                    }
                }
            });
            content.create();
        } catch (Exception e2) {
            Log.e("PdfHelper", "exportPdf", e2);
            ProgressDialogBuilder<?> progressDialogBuilder3 = this.mProgressDialogBuilder;
            if (progressDialogBuilder3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
            } else {
                progressDialogBuilder2 = progressDialogBuilder3;
            }
            progressDialogBuilder2.dismiss();
            Context context = this.context;
            Toast.makeText(context, context.getString(R.string.str_get_print_value_error), Toast.LENGTH_SHORT).show();
        }
    }
    private Context getPackageName() {
        return null;
    }
    private boolean hasGrantedUri() {
        Uri eDespatchPdfPath;
        PdfOperation pdfOperation = this.selectedSalesItemPdfOperation;
        if (pdfOperation == PdfOperation.EDocumentPdf || pdfOperation == PdfOperation.EDocumentDraftPdf) {
            SharedPreferencesHelper sharedPreferencesHelper = this.mSharedPreferencesHelper;
            Intrinsics.checkNotNull(sharedPreferencesHelper);
            eDespatchPdfPath = sharedPreferencesHelper.getEDespatchPdfPath();
        } else {
            SharedPreferencesHelper sharedPreferencesHelper2 = this.mSharedPreferencesHelper;
            Intrinsics.checkNotNull(sharedPreferencesHelper2);
            eDespatchPdfPath = sharedPreferencesHelper2.getSalesPdfPath();
        }
        if (eDespatchPdfPath == null) {
            return false;
        }
        List<UriPermission> persistedUriPermissions = this.context.getContentResolver().getPersistedUriPermissions();
        Intrinsics.checkNotNullExpressionValue(persistedUriPermissions, "getPersistedUriPermissions(...)");
        Iterator<UriPermission> it = persistedUriPermissions.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(it.next().getUri().toString(), eDespatchPdfPath.toString())) {
                return true;
            }
        }
        return false;
    }
    public void createPdfToSelectedFolder() {
        Uri eDespatchPdfPath;
        if (!hasGrantedUri()) {
            Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            Context context = this.context;
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            ((Activity) context).startActivityForResult(intent, IntentExtraName.PDF_REQUEST_CODE);
            return;
        }
        PdfOperation pdfOperation = this.selectedSalesItemPdfOperation;
        if (pdfOperation == PdfOperation.EDocumentPdf || pdfOperation == PdfOperation.EDocumentDraftPdf) {
            SharedPreferencesHelper sharedPreferencesHelper = this.mSharedPreferencesHelper;
            Intrinsics.checkNotNull(sharedPreferencesHelper);
            eDespatchPdfPath = sharedPreferencesHelper.getEDespatchPdfPath();
        } else {
            SharedPreferencesHelper sharedPreferencesHelper2 = this.mSharedPreferencesHelper;
            Intrinsics.checkNotNull(sharedPreferencesHelper2);
            eDespatchPdfPath = sharedPreferencesHelper2.getSalesPdfPath();
        }
        copyPdfFromCacheToSelectedFolder(eDespatchPdfPath);
    }
    public void copyPdfFromCacheToSelectedFolder(Uri uri) {
        PdfOperation pdfOperation = null;
        String str;
        try {
            try {
                pdfOperation = this.selectedSalesItemPdfOperation;
            } catch (Exception e2) {
                Log.e("PdfHelper", "copyPdfFromCacheToSelectedFolder", e2);
            }
            if (pdfOperation != PdfOperation.EDocumentPdf && pdfOperation != PdfOperation.EDocumentDraftPdf) {
                str = this.cacheSalesPdfFilePath;
                Intrinsics.checkNotNull(str);
                File file = new File(str);
                int length = (int) file.length();
                byte[] bArr = new byte[length];
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                bufferedInputStream.read(bArr, 0, length);
                bufferedInputStream.close();
                Context context = this.context;
                Intrinsics.checkNotNull(uri);
                DocumentFile fromTreeUri = DocumentFile.fromTreeUri(context, uri);
                Intrinsics.checkNotNull(fromTreeUri);
                DocumentFile createFile = fromTreeUri.createFile("application/pdf", file.getName());
                ContentResolver contentResolver = this.context.getContentResolver();
                Intrinsics.checkNotNull(createFile);
                OutputStream openOutputStream = contentResolver.openOutputStream(createFile.getUri());
                Intrinsics.checkNotNull(openOutputStream);
                openOutputStream.write(bArr);
                openOutputStream.close();
                file.delete();
                Context context2 = this.context;
                Uri uri2 = createFile.getUri();
                Intrinsics.checkNotNullExpressionValue(uri2, "getUri(...)");
                Toast.makeText(context2, getPdfDownloadPath(uri2), Toast.LENGTH_SHORT).show();
                this.cacheReportPdfFilePath = "";
                this.cacheSalesPdfFilePath = "";
            }
            str = this.cacheReportPdfFilePath;
            Intrinsics.checkNotNull(str);
            File file2 = new File(str);
            int length2 = (int) file2.length();
            byte[] bArr2 = new byte[length2];
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file2));
            bufferedInputStream2.read(bArr2, 0, length2);
            bufferedInputStream2.close();
            Context context3 = this.context;
            Intrinsics.checkNotNull(uri);
            DocumentFile fromTreeUri2 = DocumentFile.fromTreeUri(context3, uri);
            Intrinsics.checkNotNull(fromTreeUri2);
            DocumentFile createFile2 = fromTreeUri2.createFile("application/pdf", file2.getName());
            ContentResolver contentResolver2 = this.context.getContentResolver();
            Intrinsics.checkNotNull(createFile2);
            OutputStream openOutputStream2 = contentResolver2.openOutputStream(createFile2.getUri());
            Intrinsics.checkNotNull(openOutputStream2);
            openOutputStream2.write(bArr2);
            openOutputStream2.close();
            file2.delete();
            Context context22 = this.context;
            Uri uri22 = createFile2.getUri();
            Intrinsics.checkNotNullExpressionValue(uri22, "getUri(...)");
            Toast.makeText(context22, getPdfDownloadPath(uri22), Toast.LENGTH_SHORT).show();
            this.cacheReportPdfFilePath = "";
            this.cacheSalesPdfFilePath = "";
        } catch (Throwable th) {
            this.cacheReportPdfFilePath = "";
            this.cacheSalesPdfFilePath = "";
            try {
                throw th;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private String getPdfDownloadPath(Uri uri) {
        try {
            String path = FilePath.getPath(this.context, uri);
            if (path != null) {
                return path;
            }
            String string = this.context.getString(R.string.str_pdf_report_saved);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return string;
        } catch (Exception e2) {
            Log.e("PdfHelper", "getPdfDownloadPath", e2);
            String string2 = this.context.getString(R.string.str_pdf_report_saved);
            Intrinsics.checkNotNull(string2);
            return string2;
        }
    }
}
