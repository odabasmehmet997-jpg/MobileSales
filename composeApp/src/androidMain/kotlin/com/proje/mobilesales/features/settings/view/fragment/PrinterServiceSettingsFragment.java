package com.proje.mobilesales.features.settings.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.SwitchPreferenceCompat;
import com.proje.mobilesales.R;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.CommunicationModule;
import com.proje.mobilesales.core.asynctask.TransferAutoAsyncTask;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.di.ActivityComponent;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.service.PrinterName;
import com.proje.mobilesales.core.service.PrinterNames;
import com.proje.mobilesales.core.service.PrinterPublicFactory;
import com.proje.mobilesales.core.service.PrinterServiceApi;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.features.reports.view.fragment.ItemReportsFragmentloadItemReportsFromLocal1;
import com.proje.mobilesales.features.settings.view.activity.PreferenceActivity;
import com.proje.mobilesales.features.userreport.view.activity.UserReportsGridActivity;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import okhttp3.Call;

public final class PrinterServiceSettingsFragment extends InjectableSettingFragment {
    private ListPreference mPreferenceDefaultPrinter;
    private PrinterServiceApi mPrintersApi;
    private ProgressDialogBuilder<?> mProgressDialogBuilder1;
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityComponent activityComponent = getActivityComponent();
        if (activityComponent != null) {
            activityComponent.inject(this);
        }
        Activity activity = ContextUtils.getmActivity();
        if (activity instanceof BaseInjectableActivity) {
            this.mProgressDialogBuilder1 = new ProgressDialogBuilder.Impl(requireContext(), (BaseInjectableActivity) activity);
        }
    }
    public void onCreatePreferences(Bundle bundle, String str) {
        Bundle arguments = getArguments();
        Intrinsics.checkNotNull(arguments);
        assert arguments != null;
        addPreferencesFromResource(arguments.getInt(PreferenceActivity.EXTRA_PREFERENCES));
        SwitchPreferenceCompat mPreferenceUsePrinterService = getPreferenceScreen().findPreference(getString(R.string.pref_use_printer_service_key));
        EditTextPreference mPreferencePrinterServiceAddress = getPreferenceScreen().findPreference(getString(R.string.pref_printer_service_address_key));
        ListPreference listPreference = getPreferenceScreen().findPreference(getString(R.string.pref_default_printer_key));
        this.mPreferenceDefaultPrinter = listPreference;
        Intrinsics.checkNotNull(listPreference);
        assert listPreference != null;
        listPreference.setVisible(!TextUtils.isEmpty(Preferences.getPrinterServiceAddress(getContext())));
        String printerServiceAddress = Preferences.getPrinterServiceAddress(getContext());
        Intrinsics.checkNotNullExpressionValue(printerServiceAddress, "getPrinterServiceAddress(...)");
        changeEnabledPrinterListPreference(printerServiceAddress);
        EditTextPreference editTextPreference = mPreferencePrinterServiceAddress;
        Intrinsics.checkNotNull(editTextPreference);
        editTextPreference.setOnPreferenceChangeListener((preference, obj) -> PrinterServiceSettingsFragment.onCreatePreferenceslambda0(this, preference, obj));
        ListPreference listPreference2 = this.mPreferenceDefaultPrinter;
        Intrinsics.checkNotNull(listPreference2);
        listPreference2.setOnPreferenceClickListener(preference -> PrinterServiceSettingsFragment.onCreatePreferenceslambda1(this, preference));
    }
    public static boolean onCreatePreferenceslambda0(PrinterServiceSettingsFragment this0, Preference preference, Object newValue) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(preference, "preference");
        Intrinsics.checkNotNullParameter(newValue, "newValue");
        String string = this0.getString(R.string.str_please_wait);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this0.showProgressDialog(string);
        if (!ContextUtils.checkConnectionWithoutMessage()) {
            Toast.makeText(this0.getContext(), this0.getString(R.string.str_check_internet_connection), Toast.LENGTH_SHORT).show();
        }
        this0.changeEnabledPrinterListPreference(newValue.toString());
        this0.hideProgressDialog();
        return true;
    }
    public static boolean onCreatePreferenceslambda1(PrinterServiceSettingsFragment this0, Preference preference) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        String printerServiceAddress = Preferences.getPrinterServiceAddress(this0.getContext());
        Intrinsics.checkNotNullExpressionValue(printerServiceAddress, "getPrinterServiceAddress(...)");
        this0.changeEnabledPrinterListPreference(printerServiceAddress);
        return true;
    }
    private void changeEnabledPrinterListPreference(String str) {
        if (TextUtils.isEmpty(str)) {
            ListPreference listPreference = this.mPreferenceDefaultPrinter;
            Intrinsics.checkNotNull(listPreference);
            if (!listPreference.isVisible()) {
                Toast.makeText(getContext(), getString(R.string.str_printer_service_address_must_be_entered), Toast.LENGTH_SHORT).show();
            }
            clearPrinterListAndHide();
            return;
        }
        ListPreference listPreference2 = this.mPreferenceDefaultPrinter;
        Intrinsics.checkNotNull(listPreference2);
        listPreference2.setVisible(true);
        try {
            showDialog(str);
        } catch (IOException e2) {
            hideProgressDialog();
            clearPrinterListAndHide();
            e2.printStackTrace();
            Toast.makeText(getContext(), getString(R.string.str_printer_service_could_not_be_accessed), Toast.LENGTH_SHORT).show();
        }
    }
    private boolean createService(String str) {
        if (TextUtils.isEmpty(Preferences.getPrinterServiceAddress(getContext())) || str.isEmpty()) {
            return false;
        }
        try {
            Call.Factory factoryProvidePrintersPublicCallFactory = new CommunicationModule(null, null).providePrintersPublicCallFactory();
            Intrinsics.checkNotNullExpressionValue(factoryProvidePrintersPublicCallFactory, "providePrintersPublicCallFactory(...)");
            this.mPrintersApi = new PrinterPublicFactory.Impl(factoryProvidePrintersPublicCallFactory).rxEnabled(true).create(str, PrinterServiceApi.class);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }
    private void clearPrinterListAndHide() {
        ListPreference listPreference = this.mPreferenceDefaultPrinter;
        Intrinsics.checkNotNull(listPreference);
        listPreference.setVisible(false);
        ListPreference listPreference2 = this.mPreferenceDefaultPrinter;
        Intrinsics.checkNotNull(listPreference2);
        listPreference2.setValue(null);
    }
    private void showProgressDialog(String str) {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder1;
        if (progressDialogBuilder != null) {
            Intrinsics.checkNotNull(progressDialogBuilder);
            progressDialogBuilder.setMessage(str).setCancelable(true).show();
        }
    }
    private void hideProgressDialog() {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder1;
        if (progressDialogBuilder != null) {
            Intrinsics.checkNotNull(progressDialogBuilder);
            progressDialogBuilder.dismiss();
        }
    }
    private void showDialog(String str) throws IOException {
        if (!ContextUtils.checkConnectionWithoutMessage()) {
            Toast.makeText(getContext(), getString(R.string.str_check_internet_connection), Toast.LENGTH_SHORT).show();
        }
        if (createService(str)) {
            PrinterServiceApi printerServiceApi = this.mPrintersApi;
            Intrinsics.checkNotNull(printerServiceApi);
            Maybe<PrinterNames> printer = printerServiceApi.getPrinter();
            Intrinsics.checkNotNull(printer);
            final MutablePropertyReference1Impl anonymousClass1 = new MutablePropertyReference1Impl() {
                public Object get(Object obj) {
                     return Boolean.valueOf(((PrinterNames) obj).isSuccess());
                }
                public void set(Object obj, Object obj2) {
                    ((PrinterNames) obj).setSuccess(((Boolean) obj2).booleanValue());
                }
            };
            Maybe maybeFilter = printer.filter(new Predicate() {
                public boolean test(Object obj) {
                    return PrinterServiceSettingsFragment.showDialoglambda2(anonymousClass1, obj);
                }
            });
            final TransferAutoAsyncTask.AnonymousClass2 anonymousClass2 = new TransferAutoAsyncTask.AnonymousClass2() {
                public ArrayList<PrinterName> invoke(Object p1) {
                    return null;
                }
                public ArrayList<PrinterName> invoke(PrinterNames printerNames) {
                    Intrinsics.checkNotNullParameter(printerNames, "printerNames");
                    ArrayList<PrinterName> arrayList = new ArrayList<>();
                    List<PrinterName> result = printerNames.getResult();
                    Intrinsics.checkNotNull(result);
                    for (PrinterName printerName : result) {
                        arrayList.add(printerName);
                    }
                    return arrayList;
                }
            };
            Maybe<R> map = maybeFilter.map(new Function() {
                public   Object apply(Object obj) {
                    return PrinterServiceSettingsFragment.showDialoglambda3(anonymousClass2, obj);
                }
                public Object invoke(Object obj) {
                    return null;
                }
            });
            final ItemReportsFragmentloadItemReportsFromLocal1.AnonymousClass3 anonymousClass3 = new ItemReportsFragmentloadItemReportsFromLocal1.AnonymousClass3() {
                public Boolean invoke(Object p1) {
                    return null;
                }
                public Boolean invoke2(List<PrinterName> list) {
                    return Boolean.valueOf(!(list == null || list.isEmpty()));
                }

                public Boolean invoke(List<? extends PrinterName> list) {
                    return invoke2((List<PrinterName>) list);
                }
            };
            Maybe maybeFilter2 = map.filter(new Predicate() {
                public boolean test(Object obj) {
                    return PrinterServiceSettingsFragment.showDialoglambda4(anonymousClass3, obj);
                }
            });
            final AnonymousClass4 anonymousClass4 = new AnonymousClass4() {
                public Unit invoke(Object p1) {
                    return null;
                }

                public Unit invoke(Throwable th) {
                    invoke2(th);
                    return Unit.INSTANCE;
                }

                public void invoke2(Throwable throwable) {
                    Intrinsics.checkNotNullParameter(throwable, "throwable");
                    ContextUtils.showToast(throwable.getMessage());
                }
            };
            Maybe maybeSubscribeOn = maybeFilter2.doOnError(new Consumer() {
                public void accept(Object obj) {
                    PrinterServiceSettingsFragment.showDialoglambda5((Function1) anonymousClass4, obj);
                }
                public Object invoke(Object obj) {
                    return null;
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
            final Function1<List<? extends PrinterName>, Unit> function1 = new Function1<List<? extends PrinterName>, Unit>() {
                public Unit invoke(Object p1) {
                    return null;
                }
                public Unit invoke(List<? extends PrinterName> list) {
                    invoke2((List<PrinterName>) list);
                    return Unit.INSTANCE;
                }
                public void invoke2(List<PrinterName> list) {
                    PrinterServiceSettingsFragment.this.loadPrinters(list);
                }
            };
            Consumer consumer = new Consumer() {
                public void accept(Object obj) {
                    PrinterServiceSettingsFragment.showDialoglambda6(function1, obj);
                }
                public Object invoke(Object obj) {
                    return null;
                }
            };
            final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() {
                public Unit invoke(Object p1) {
                    return null;
                }
                public Unit invoke(Throwable th) {
                    invoke2(th);
                    return Unit.INSTANCE;
                }
                public void invoke2(Throwable th) {
                    Toast.makeText(PrinterServiceSettingsFragment.this.getContext(), PrinterServiceSettingsFragment.this.getString(R.string.str_printer_service_could_not_be_accessed), Toast.LENGTH_LONG).show();
                    ProgressDialogBuilder progressDialogBuilder = PrinterServiceSettingsFragment.this.mProgressDialogBuilder1;
                    Intrinsics.checkNotNull(progressDialogBuilder);
                    progressDialogBuilder.dismiss();
                }
            };
            maybeSubscribeOn.subscribe(consumer, new Consumer() {
                public void accept(Object p0) {
                    PrinterServiceSettingsFragment.showDialoglambda7(function12, p0);
                }
                public Object invoke(Object p0) {
                    return null;
                }
            }, new Action() {
                private PrinterServiceSettingsFragment f0;

                public void run() {
                    PrinterServiceSettingsFragment.showDialoglambda8(this.f0);
                }
            });
            return;
        }
        clearPrinterListAndHide();
        Toast.makeText(getContext(), getString(R.string.str_printer_service_could_not_be_accessed), Toast.LENGTH_LONG).show();
    }
    public static boolean showDialoglambda2(Function1 tmp0, Object p0) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        Intrinsics.checkNotNullParameter(p0, "p0");
        return ((Boolean) tmp0.invoke(p0)).booleanValue();
    }
    public static ArrayList showDialoglambda3(TransferAutoAsyncTask.AnonymousClass2 tmp0, Object p0) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ArrayList) tmp0.invoke(p0);
    }
    public static boolean showDialoglambda4(ItemReportsFragmentloadItemReportsFromLocal1.AnonymousClass3 tmp0, Object p0) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        Intrinsics.checkNotNullParameter(p0, "p0");
        return ((Boolean) tmp0.invoke(p0)).booleanValue();
    }
    public static void showDialoglambda5(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    }
    public static void showDialoglambda6(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    }
    public static void showDialoglambda7(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    }
    public static void showDialoglambda8(PrinterServiceSettingsFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        ProgressDialogBuilder<?> progressDialogBuilder = this0.mProgressDialogBuilder1;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
    }
    private CharSequence[] convertListToChar(List<PrinterName> list) {
        CharSequence[] charSequenceArr = new CharSequence[list.size()];
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            PrinterName printerName = list.get(i2);
            Intrinsics.checkNotNull(printerName);
            charSequenceArr[i2] = printerName.name;
        }
        return charSequenceArr;
    }
    public void loadPrinters(List<PrinterName> list) {
        if (list != null && !list.isEmpty()) {
            ListPreference listPreference = this.mPreferenceDefaultPrinter;
            Intrinsics.checkNotNull(listPreference);
            listPreference.setEntries(convertListToChar(list));
            ListPreference listPreference2 = this.mPreferenceDefaultPrinter;
            Intrinsics.checkNotNull(listPreference2);
            listPreference2.setEntryValues(convertListToChar(list));
            ListPreference listPreference3 = this.mPreferenceDefaultPrinter;
            Intrinsics.checkNotNull(listPreference3);
            if (TextUtils.isEmpty(listPreference3.getValue())) {
                return;
            }
            Stream<PrinterName> stream = list.stream();
            final Function1<PrinterName, Boolean> function1 = new Function1<PrinterName, Boolean>() {
                private PrinterServiceSettingsFragment this0;
                public Boolean invoke(Object p1) {
                    return null;
                }
                public Boolean invoke(PrinterName printerName) {
                    Intrinsics.checkNotNull(printerName);
                    String str = printerName.name;
                    ListPreference listPreference4 = this.this0.mPreferenceDefaultPrinter;
                    Intrinsics.checkNotNull(listPreference4);
                    return Boolean.valueOf(Intrinsics.areEqual(str, listPreference4.getValue()));
                }
            };
            List list2 = (List) stream.filter((java.util.function.Predicate) obj -> PrinterServiceSettingsFragment.loadPrinterslambda9(function1, obj)).collect(Collectors.toList());
            if (list2 == null || list2.size() <= 0) {
                ListPreference listPreference4 = this.mPreferenceDefaultPrinter;
                Intrinsics.checkNotNull(listPreference4);
                listPreference4.setValue(null);
                return;
            } else {
                ListPreference listPreference5 = this.mPreferenceDefaultPrinter;
                Intrinsics.checkNotNull(listPreference5);
                ListPreference listPreference6 = this.mPreferenceDefaultPrinter;
                Intrinsics.checkNotNull(listPreference6);
                listPreference5.setSummary(listPreference6.getValue());
                return;
            }
        }
        ListPreference listPreference7 = this.mPreferenceDefaultPrinter;
        Intrinsics.checkNotNull(listPreference7);
        listPreference7.setEntries(R.array.pref_printer_service_list_entries);
        ListPreference listPreference8 = this.mPreferenceDefaultPrinter;
        Intrinsics.checkNotNull(listPreference8);
        listPreference8.setEntryValues(R.array.pref_printer_service_list_values);
        Toast.makeText(getContext(), getString(R.string.str_no_accessible_printer_found), Toast.LENGTH_LONG).show();
    }
    public static boolean loadPrinterslambda9(Function1 tmp0, Object obj) {
        return UserReportsGridActivity.prepareFooterlambda4(tmp0, obj);
    }
    public void onDestroy() {
        super.onDestroy();
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder1;
        if (progressDialogBuilder != null) {
            Intrinsics.checkNotNull(progressDialogBuilder);
            progressDialogBuilder.dismiss();
            this.mProgressDialogBuilder1 = null;
        }
    }
}