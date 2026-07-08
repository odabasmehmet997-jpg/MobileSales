package com.proje.mobilesales.features.settings.view.activity;

import android.R;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.gson.Gson;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.CommunicationModule;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.service.PrintItem;
import com.proje.mobilesales.core.service.PrintParameters;
import com.proje.mobilesales.core.service.PrintResponse;
import com.proje.mobilesales.core.service.PrinterName;
import com.proje.mobilesales.core.service.PrinterNames;
import com.proje.mobilesales.core.service.PrinterPublicFactory;
import com.proje.mobilesales.core.service.PrinterServiceApi;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import com.proje.mobilesales.databinding.ActivityPrintPreferencesBinding;
import com.proje.mobilesales.features.reports.model.ReportItem;
import com.proje.mobilesales.features.reports.model.ResponseError;
import com.proje.mobilesales.features.reports.view.activity.ReportDesignListActivity;
import com.proje.mobilesales.features.settings.repository.PrintPreferencesRepository;
import com.proje.mobilesales.features.settings.viewmodel.PrintPreferencesViewModel;
import com.proje.mobilesales.features.settings.viewmodel.PrintPreferencesViewModelBuilder;
import dagger.Lazy;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;
 
public final class PrintPreferencesActivity extends BaseErpActivity {
    private final Lazy bindingdelegate;
    private int checkedIndex;
    private String ficheId;
    private CharSequence[] listOfPrinters;
    private String logicalRef;
    private PrinterServiceApi mPrintersApi;
    private final PrintPreferencesViewModel printPreferencesViewModel;
    private ReportItem reportItem;
    public PrintPreferencesActivity() {
        final PrintPreferencesRepository printPreferencesRepository = new PrintPreferencesRepository();
        printPreferencesViewModel = new PrintPreferencesViewModelBuilder().setRepository(printPreferencesRepository).createPrintPreferencesViewModel();
        bindingdelegate = (Lazy) LazyKt.lazy(new Function0<ActivityPrintPreferencesBinding>() {
            @Override
            public ActivityPrintPreferencesBinding invoke() {
                return ActivityPrintPreferencesBinding.inflate(getLayoutInflater());
            }
        });
        checkedIndex = -1;
        ficheId = "";
        logicalRef = "";
    }
    public ActivityPrintPreferencesBinding getBinding() {
        return (ActivityPrintPreferencesBinding) bindingdelegate.getValue();
    }
    public String getFicheId() {
        return ficheId;
    }
    public void setFicheId(final String str) {
        ficheId = str;
    }
    public String getLogicalRef() {
        return logicalRef;
    }
    public void setLogicalRef(final String str) {
        logicalRef = str;
    }
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
         this.setContentView(this.getBinding().getRoot());
         this.setToolbar();
         this.setDefaultPrinter();
        ficheId = this.getIntent().getStringExtra("ficheId");
        logicalRef = this.getIntent().getStringExtra("logicalRef");
        final Call.Factory providePrintersPublicCallFactory = new CommunicationModule(null, null).providePrintersPublicCallFactory();
        Intrinsics.checkNotNullExpressionValue(providePrintersPublicCallFactory, "providePrintersPublicCallFactory(...)");
        mPrintersApi = new PrinterPublicFactory.Impl(providePrintersPublicCallFactory).rxEnabled(true).create(Preferences.getPrinterServiceAddress(this), PrinterServiceApi.class);
         this.getBinding().lnDefaultPrinter.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.settings.view.activity.PrintPreferencesActivityExternalSyntheticLambda8
            public void PrintPreferencesActivityExternalSyntheticLambda8() {
            }
            public void onClick(final View view) {
                onCreatelambda0(PrintPreferencesActivity.this, view);
            }
        });
         this.getBinding().lnReportDesign.setOnClickListener(new View.OnClickListener() {
            public  void PrintPreferencesActivityExternalSyntheticLambda9() {
            }
            public void onClick(final View view) {
                onCreatelambda1(PrintPreferencesActivity.this, view);
            }
        });
         this.getBinding().btnPrint.setOnClickListener(new View.OnClickListener() {
            public void PrintPreferencesActivityExternalSyntheticLambda10() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                onCreatelambda2(PrintPreferencesActivity.this, view);
            }
        });
    }
    public static void onCreatelambda0(final PrintPreferencesActivity this0, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.getPrinterService();
    }
    public static void onCreatelambda1(final PrintPreferencesActivity this0, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.startActivityForResult(new Intent(this0, ReportDesignListActivity.class), 999);
    }
    public static void onCreatelambda2(final PrintPreferencesActivity this0, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.preparePrint();
    }
    private void setDefaultPrinter() {
        this.getBinding().txtChoosePrinter.setText(Preferences.getDefaultPrinter(this));
    }
    @SuppressLint("CheckResult")
    private void getPrinterService() {
        PrinterServiceApi printerServiceApi = mPrintersApi;
        if (null == printerServiceApi) {
            Intrinsics.throwUninitializedPropertyAccessException("mPrintersApi");
            printerServiceApi = null;
        }
        final Maybe<PrinterNames> printer = printerServiceApi.getPrinter();
        Intrinsics.checkNotNull(printer);
        printer.filter((Predicate) obj -> {
            final boolean printerServicelambda3;
            printerServicelambda3 = getPrinterServicelambda3(Function1.this, obj);
            return printerServicelambda3;
        }).map((Function) obj -> {
            final List printerServicelambda4;
            printerServicelambda4 = getPrinterServicelambda4(Function1.this, obj);
            return printerServicelambda4;
        }).filter(obj -> {
            final boolean printerServicelambda5;
            printerServicelambda5 = getPrinterServicelambda5(Function1.this, obj);
            return printerServicelambda5;
        }).doOnError((Consumer) obj -> {
            getPrinterServicelambda6(Function1.this, obj);
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe((Consumer) obj -> {
            getPrinterServicelambda7(Function1.this, obj);
        });
    }
    public static boolean getPrinterServicelambda3(final Function1 tmp0, final Object p0) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        Intrinsics.checkNotNullParameter(p0, "p0");
        return ((Boolean) tmp0.invoke(p0)).booleanValue();
    }
    public static List getPrinterServicelambda4(final Function1 tmp0, final Object p0) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (List) tmp0.invoke(p0);
    }
    public static boolean getPrinterServicelambda5(final Function1 tmp0, final Object p0) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        Intrinsics.checkNotNullParameter(p0, "p0");
        return ((Boolean) tmp0.invoke(p0)).booleanValue();
    }
    public static void getPrinterServicelambda6(final Function1 tmp0, final Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    }
    public static void getPrinterServicelambda7(final Function1 tmp0, final Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    }
    public void loadPrinters(final List<PrinterName> list) {
        if (list == null || list.isEmpty()) {
            Toast.makeText(this, this.getString(R.string.str_no_accessible_printer_found), Toast.LENGTH_LONG).show();
            return;
        }
        int i2 = 0;
        for (final Object obj : list) {
            final int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (Intrinsics.areEqual(((PrinterName) obj).name, this.getBinding().txtChoosePrinter.getText())) {
                checkedIndex = i2;
            }
            i2 = i3;
        }
        listOfPrinters = this.convertListToChar(list);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.str_printer_choice);
        builder.setSingleChoiceItems(listOfPrinters, checkedIndex, new DialogInterface.OnClickListener() {
            public void PrintPreferencesActivityExternalSyntheticLambda11() {
            }

            public void onClick(final DialogInterface dialogInterface, final int i4) {
                loadPrinterslambda9(PrintPreferencesActivity.this, dialogInterface, i4);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i4) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
    public static void loadPrinterslambda9(final PrintPreferencesActivity this0, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final CharSequence[] charSequenceArr = this0.listOfPrinters;
        Intrinsics.checkNotNull(charSequenceArr);
        this0.getBinding().txtChoosePrinter.setText(charSequenceArr[i2]);
        dialogInterface.dismiss();
    }
    private CharSequence[] convertListToChar(final List<PrinterName> list) {
        final CharSequence[] charSequenceArr = new CharSequence[list.size()];
        final int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            charSequenceArr[i2] = list.get(i2).name;
        }
        return charSequenceArr;
    }
    protected void onActivityResult(final int i2, final int i3, final Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (-1 == i3 && 999 == i2) {
            final ReportItem reportItem = null != intent ? (ReportItem) intent.getParcelableExtra("999") : null;
            this.reportItem = reportItem;
            if (null == reportItem) {
                Toast.makeText(this, this.getString(R.string.str_report_must_be_designed), Toast.LENGTH_LONG).show();
                return;
            }
            final AppCompatTextView appCompatTextView = this.getBinding().txtChooseDesign;
            final ReportItem reportItem2 = this.reportItem;
            appCompatTextView.setText(null != reportItem2 ? reportItem2.description : null);
        }
    }
    private void preparePrint() {
        if (!Intrinsics.areEqual(this.getBinding().txtChooseDesign.getText(), this.getResources().getString(R.string.str_choose_report_design))) {
            final ArrayList<PrintParameters> arrayList = new ArrayList<>();
            final PrintParameters printParameters = new PrintParameters(null, null, 3, null);
            printParameters.setKey(printPreferencesViewModel.erpType() == ErpType.NETSIS ? "Fisno" : "Id");
            printParameters.setValue(ficheId);
            arrayList.add(printParameters);
            final PrintItem printItem = new PrintItem(0, null, 0, null, null, 31, null);
            final ReportItem reportItem = this.reportItem;
            Intrinsics.checkNotNull(reportItem);
            printItem.setReportId(reportItem.getId());
            printItem.setPrinterName(this.getBinding().txtChoosePrinter.getText().toString());
            printItem.setNumberOfCopies(1);
            printItem.setCulture(new SharedPreferencesHelper().getApplicationLanguage());
            printItem.setParameters(arrayList);
            this.print(printItem);
            return;
        }
        Toast.makeText(this, R.string.str_report_must_be_designed, Toast.LENGTH_SHORT).show();
    }
    @SuppressLint("CheckResult")
    private void print(final PrintItem printItem) {
        mProgressDialogBuilder.setMessage(this.getString(R.string.str_please_wait)).show();
        PrinterServiceApi printerServiceApi = mPrintersApi;
        if (null == printerServiceApi) {
            Intrinsics.throwUninitializedPropertyAccessException("mPrintersApi");
            printerServiceApi = null;
        }
        printerServiceApi.print(printItem).doOnError(new Consumer() {
            public   void PrintPreferencesActivityExternalSyntheticLambda0() {
            }
            public void accept(final Object obj) {
                printlambda11(Function1.this, obj);
            }

            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer() {
            public void PrintPreferencesActivityExternalSyntheticLambda1() {
            }
            public void accept(final Object obj) {
                printlambda12(Function1.this, obj);
            }
            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        }, new Consumer() {
            public   void PrintPreferencesActivityExternalSyntheticLambda2() {
            }

            public void accept(final Object obj) {
                printlambda13(Function1.this, obj);
            }

            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        });
    }
    public static void printlambda11(final Function1 tmp0, final Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    }
    public static void printlambda12(final Function1 tmp0, final Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    }
    public static void printlambda13(final Function1 tmp0, final Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    }
    public void handleResults(final PrintResponse printResponse) {
        mProgressDialogBuilder.dismiss();
        Intrinsics.checkNotNull(printResponse);
        if (printResponse.isResult() && printResponse.isSuccess()) {
            Toast.makeText(this, R.string.str_document_printed_success, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, this.getString(R.string.str_document_failed_to_print), Toast.LENGTH_LONG).show();
        }
    }
    public void handleError(final Throwable th) {
        mProgressDialogBuilder.dismiss();
        if (th instanceof SocketTimeoutException) {
            Toast.makeText(this, R.string.exp_28_socket_timeout, Toast.LENGTH_LONG).show();
            return;
        }
        if (th instanceof HttpException httpException) {
            if (null != httpException.response()) {
                final Response<?> response = httpException.response();
                Intrinsics.checkNotNull(response);
                if (null != response.errorBody()) {
                    final Gson gson = new Gson();
                    final Response<?> response2 = httpException.response();
                    Intrinsics.checkNotNull(response2);
                    final ResponseBody errorBody = response2.errorBody();
                    Intrinsics.checkNotNull(errorBody);
                    final ArrayList<ResponseError> errors = gson.fromJson(errorBody.charStream(), PrintResponse.class).getErrors();
                    final ResponseError responseError = null != errors ? errors.get(0) : null;
                    Intrinsics.checkNotNull(responseError);
                    Toast.makeText(this, responseError.getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
        Toast.makeText(this, null != th ? th.getMessage() : null, Toast.LENGTH_LONG).show();
    }
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (16908332 == item.getItemId()) {
            this.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
