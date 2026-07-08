package com.proje.mobilesales.features.activity;

import android.Manifest;
import android.R;
import android.annotation.SuppressLint;
import android.content.*;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.location.LocationRequestCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.RecyclerView;
import com.example.privacy_policy_lib.ContractsFragment;
import com.example.privacy_policy_lib.PrivacyPolicyBuilder;
import com.example.privacy_policy_lib.core.AgreementTypes;
import com.example.privacy_policy_lib.core.PrivacyPolicyManager;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.example.privacy_policy_lib.core.model.PrivacyPolicyLibParams;
import com.example.privacy_policy_lib.core.model.PrivacyPolicyState;
import com.google.gson.Gson;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseDrawerActivity;
import com.proje.mobilesales.core.base.BaseSelectResult;
import com.proje.mobilesales.core.enums.*;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.service.DataSyncService;
import com.proje.mobilesales.core.service.DataTransferSyncService;
import com.proje.mobilesales.core.service.LocationUpdatesService;
import com.proje.mobilesales.core.service.PrintService;
import com.proje.mobilesales.core.sql.ISqlManager;
import com.proje.mobilesales.core.tigerwcf.TigerServiceResult;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.adapter.ScheduleRcyclerviewAdapter;
import com.proje.mobilesales.features.customer.view.list.CustomerListActivity;
import com.proje.mobilesales.features.dbmodel.LogoParam;
import com.proje.mobilesales.features.licence.LicenseUtils;
import com.proje.mobilesales.features.model.Response;
import com.proje.mobilesales.features.model.USER;
import com.proje.mobilesales.features.notification.view.list.NotificationListActivity;
import com.proje.mobilesales.features.product.view.list.ProductListActivity;
import com.proje.mobilesales.features.reports.view.activity.ReportAllActivity;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.view.distribution.DistributionListActivity;
import com.proje.mobilesales.features.sales.view.validation.OrderValidationListActivity;
import com.proje.mobilesales.features.survey.repository.SurveyRepository;
import com.proje.mobilesales.features.survey.service.SurveyApiService;
import com.proje.mobilesales.features.survey.viewmodel.SurveyViewModel;
import com.proje.mobilesales.features.todo.model.database.TodoInfoDb;
import com.proje.mobilesales.features.tools.view.activity.OtherMenuActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.UnknownNullability;
import org.zakariya.stickyheaders.StickyHeaderLayoutManager;
import permissions.dispatcher.PermissionRequest;

import javax.inject.Inject;
import java.lang.ref.WeakReference;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.proje.mobilesales.core.utils.AppUtils.*;

public class MainActivity extends BaseDrawerActivity {
    private static final String CONTRACTOR = "MOBILE";
    public static volatile int DetailRef = -1;
    private static final String ERP_NETSIS_TEXT = "Netsis";
    private static final String ERP_TIGER_TEXT = "Tiger";
    private static final String ITEM_CODE_PRIVACY_POLICY = "mobileSales_privacyPolicy";
    private static final String LANGUAGE_ENG = "ENG";
    public static volatile int sFicheRef = -1;
    private final BroadcastReceiver gpsLocationReceiver = new BroadcastReceiver() {  
        public void onReceive(Context context, Intent intent) {
            if (MainActivity.this.baseErp.getErpRights().isGps() && MainActivity.this.baseErp.useGps()) {
                MainActivityPermissionsDispatcher.startGpsWithPermissionCheck(MainActivity.this);
            }
        }
    };
    private FrameLayout list;
    private View llTodoMesaj;
    private View lnMenuContainer;
    private View lyMainFirst;
    private View lyMainSecond;
    private View lyMainThird;

    @Inject
    AlertDialogBuilder mAlertDialogBuilder;

    @ColorInt
    int mDisableColor;

    @Inject
    ProgressDialogBuilder mProgressDialogBuilder;

    @Inject
    SharedPreferencesHelper mSharedPreferencesHelper;
    private RecyclerView rvToDo;
    ScheduleRcyclerviewAdapter scheduleRcyclerviewAdapter;

    @Inject
    ISqlManager sqlManager;
    private SurveyRepository surveyRepository;
    private SurveyViewModel surveyViewModel;
    private List<TodoInfoDb> todoInfoDbList;
    private Toolbar toolbar;
    Intent transferServiceIntent;
    private TextView txtCustomer;
    private TextView txtDistribution;
    private TextView txtOrder;
    private TextView txtProduct;
    private TextView txtReport;
    private TextView txtRequest;
    private TextView txtTransfer;
    private TextView txtUtil;
    private TextView txtVehicleTransfer;
    public static volatile FaturaIrsaliyeTuru fatirsTuru = FaturaIrsaliyeTuru.Normal;
    public static volatile FType fType = FType.siparis;
    public static volatile boolean getDataFlag = true;
    private static boolean firstShowCurr = false;
    private static final List<AgreementTypes> AGREEMENT_TYPES = MainActivityExternalSyntheticBackport1.m573m(new Object[]{AgreementTypes.GENERALAGREEMENT});

    
    public static Pair lambdalaunchPolicyUI10(Pair pair, Pair pair2) {
        return pair2;
    }

    
    public static Pair lambdalaunchPolicyUI11(Pair pair) {
        return pair;
    }

    
    public static Pair lambdalaunchPolicyUI12(Pair pair, Pair pair2) {
        return pair2;
    }

    
    public static Pair lambdalaunchPolicyUI7(Pair pair) {
        return pair;
    }

    
    public static Pair lambdalaunchPolicyUI8(Pair pair, Pair pair2) {
        return pair2;
    }

    
    public static Pair lambdalaunchPolicyUI9(Pair pair) {
        return pair;
    }

    
    public static void lambdaonBackPressed28(DialogInterface dialogInterface, int r1) {
    }

    
    public static void lambdaonResume15(DialogInterface dialogInterface, int r1) {
    }

    
    public void onError(String str) {
    }
    public void onLocationServiceBound() {
        super.onLocationServiceBound();
        MainActivityPermissionsDispatcher.startGpsWithPermissionCheck(this);
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        this.scheduleRcyclerviewAdapter = new ScheduleRcyclerviewAdapter(getApplicationContext());
        initView();
        setToolbar();
        getSupportActionBar().setTitle(this.baseErp.getUser().getFirmName().toUpperCase());
        setMenu();
        startMyService(this, PrintService.class);
        startMyService(this, DataSyncService.class);
        registerReceiver(this.gpsLocationReceiver, new IntentFilter("android.location.PROVIDERS_CHANGED"));
        USER.firmano = this.baseErp.getUser().getFirmNr();
        USER.username = this.baseErp.getUser().getUserName();
        USER.userid = this.baseErp.getUser().getUserId();
        USER.userRolRef = this.baseErp.getUser().getUserRoleId();
        USER.usercode = this.baseErp.getUser().getCode();
        USER.periodNr = this.baseErp.getUser().getPeridodNr();
        USER.type = StringUtils.convertStringToInt(this.baseErp.getUser().getType());
        USER.vehicleRef = this.baseErp.getUser().getVehicleRef();
        USER.email = this.baseErp.getUser().getEmail();
        showPoliciesIfNecessary();
        if (LicenseUtils.getLicenseKey().isEmpty()) {
            return;
        }
        showSurveyPopup();
    }

    private void showSurveyPopup() {
        SurveyRepository surveyRepository = new SurveyRepository(new SurveyApiService());
        this.surveyRepository = surveyRepository;
        SurveyViewModel surveyViewModel = new SurveyViewModel(surveyRepository);
        this.surveyViewModel = surveyViewModel;
        surveyViewModel.getDataFromAPI();
        this.surveyViewModel.getShowDialogLiveData().observe(this, surveyItem -> {
            if (surveyItem == null || !surveyItem.getWillGetSurvey().equals("true") || surveyItem.getSurveyLink().isEmpty()) {
                return;
            }
            View viewInflate = MainActivity.this.getLayoutInflater().inflate(R.layout.survey_alert_dialog, null);
            final CheckBox checkBox = viewInflate.findViewById(R.id.checkBox);
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(R.string.str_survey_information);
            builder.setView(viewInflate);
            builder.setMessage(String.format(ContextUtils.getStringResource(R.string.str_have_survey), surveyItem.getSurveyName()));
            builder.setPositiveButton(R.string.str_okey, new DialogInterface.OnClickListener() {
                @SuppressLint("UnsafeImplicitIntentLaunch")
                public void onClick(DialogInterface dialogInterface, int r4) {
                    MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(surveyItem.getSurveyLink())));
                    dialogInterface.dismiss();
                }
            }).setNegativeButton(R.string.str_cancel, (dialogInterface, r2) -> {
                dialogInterface.dismiss();
                if (checkBox.isChecked()) {
                    MainActivity.this.surveyViewModel.postSurveyDataFromAPI(surveyItem);
                }
            }).create().show();
        });
    }

    private void showPoliciesIfNecessary() {
        PrivacyPolicyLibParams agreementParams = Preferences.getAgreementParams(ContextUtils.getmContext());
        LocalDateTime localDateTime = null;
        if (agreementParams == null) {
            ArrayList arrayList = new ArrayList();
            for (AgreementTypes agreementType : AGREEMENT_TYPES) {
                arrayList.add(new Pair<>(agreementType, ""));
            }
            launchPolicyUI(null, arrayList);
            return;
        }
        String lastPolicyCheckDate = Preferences.getLastPolicyCheckDate(this);
        LocalDateTime localDateTimeNow = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            localDateTimeNow = LocalDateTime.now();
        }
        DateTimeFormatter dateTimeFormatter = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        }
        try {
            if (!lastPolicyCheckDate.isEmpty()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    localDateTime = LocalDateTime.parse(lastPolicyCheckDate, dateTimeFormatter);
                }
            }
        } catch (Exception e2) {
            Log.e("showPoliciesIfNecessary", e2.toString());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if ((localDateTime != null ? ChronoUnit.DAYS.between(localDateTime, localDateTimeNow) : LocationRequestCompat.PASSIVE_INTERVAL) >= 1) {
                controlAgreements(agreementParams, localDateTimeNow);
                Preferences.setLastPolicyCheckDate(this, localDateTimeNow.toString());
            }
        }
    }

    @SuppressLint({"CheckResult"})
    private void controlAgreements(final PrivacyPolicyLibParams privacyPolicyLibParams, final LocalDateTime localDateTime) {
        final List<Pair<AgreementTypes, String>> agreementTokenList = privacyPolicyLibParams.getAgreementTokenList();
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        final ArrayList arrayList3 = new ArrayList();
        Observable.fromIterable(agreementTokenList).flatMap(new Function() {
            public Object apply(Object obj) {
                try {
                    return MainActivity.lambdacontrolAgreements1(localDateTime, arrayList, (Pair) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).toList().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            private MainActivity f0;

            public void accept(Object obj) throws Exception {
                this.f0.lambdacontrolAgreements5(agreementTokenList, arrayList2, arrayList3, arrayList, privacyPolicyLibParams, (List) obj);
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        });
    }

    
    public static ObservableSource lambdacontrolAgreements1(final LocalDateTime localDateTime, final List list, final Pair pair) throws Exception {
        return PrivacyPolicyManager.getCurrentApprovedAgreementContentHashByTokenRx(true, (String) pair.getSecond()).toObservable().map(new Function() {
            public Object apply(Object obj) {
                try {
                    return MainActivity.lambdacontrolAgreements0(localDateTime, list, pair, (GetCurrentApprovedAgreementContentHashByTokenResponse) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).onErrorReturnItem(pair);
    }

    
    public static Pair lambdacontrolAgreements0(LocalDateTime localDateTime, List list, Pair pair, GetCurrentApprovedAgreementContentHashByTokenResponse getCurrentApprovedAgreementContentHashByTokenResponse) throws Exception {
        String endDate = getCurrentApprovedAgreementContentHashByTokenResponse.getBody().getContentResponse().getResult().getEndDate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (endDate != null && !endDate.isEmpty() && localDateTime.isAfter(LocalDateTime.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME))) {
                list.add(pair);
            }
        }
        return pair;
    }

    
    public void lambdacontrolAgreements5(List list, List list2, List list3, List list4, PrivacyPolicyLibParams privacyPolicyLibParams, List list5) throws Exception {
        final Set set = (Set) list.stream().map(new MainActivityExternalSyntheticLambda20()).collect(Collectors.toSet());
        list2.addAll((Collection) ((List) AGREEMENT_TYPES.stream().filter(new Predicate() {
            public boolean test(Object obj) {
                return MainActivity.lambdacontrolAgreements2(set, (AgreementTypes) obj);
            }
        }).collect(Collectors.toList())).stream().map(new java.util.function.Function() {
            public Object apply(Object obj) {
                return MainActivity.lambdacontrolAgreements3((AgreementTypes) obj);
            }
        }).collect(Collectors.toList()));
        for (Object o : list3) {
            final AgreementTypes agreementTypes = (AgreementTypes) o;
            if (list4.stream().noneMatch(new Predicate() {
                public boolean test(Object obj) {
                    return MainActivity.lambdacontrolAgreements4(agreementTypes, (Pair) obj);
                }
            })) {
                list4.add(new Pair(agreementTypes, ""));
            }
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list4);
        arrayList.addAll(list2);
        if (arrayList.isEmpty()) {
            return;
        }
        launchPolicyUI(privacyPolicyLibParams, arrayList);
    }

    
    public static boolean lambdacontrolAgreements2(Set set, AgreementTypes agreementTypes) {
        return !set.contains(agreementTypes);
    }

    
    public static Pair lambdacontrolAgreements3(AgreementTypes agreementTypes) {
        return new Pair(agreementTypes, "");
    }

    
    public static boolean lambdacontrolAgreements4(AgreementTypes agreementTypes, Pair pair) {
        return pair.getFirst().equals(agreementTypes);
    }

    private void launchPolicyUI(PrivacyPolicyLibParams privacyPolicyLibParams, List<Pair<AgreementTypes, String>> list) {
        if (list.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        if (privacyPolicyLibParams != null) {
            for (Pair<AgreementTypes, String> pair : privacyPolicyLibParams.getContentHashList()) {
                arrayList.add(new Pair(pair.getFirst(), pair.getSecond()));
            }
            for (Pair<AgreementTypes, String> pair2 : privacyPolicyLibParams.getAgreementTokenList()) {
                arrayList2.add(new Pair(pair2.getFirst(), pair2.getSecond()));
            }
            for (Pair<AgreementTypes, String> pair3 : privacyPolicyLibParams.getEndDateList()) {
                arrayList3.add(new Pair(pair3.getFirst(), pair3.getSecond()));
            }
        }
        List list2 = (List) list.stream().map(new MainActivityExternalSyntheticLambda20()).collect(Collectors.toList());
        final String upperCase = this.mSharedPreferencesHelper.getApplicationLanguage().equalsIgnoreCase(Locale.ENGLISH.getLanguage()) ? LANGUAGE_ENG : this.mSharedPreferencesHelper.getApplicationLanguage().toUpperCase();
        ErpType erpType = this.baseErp.getErpType();
        ErpType erpType2 = ErpType.TIGER;
        ContractsFragment contractsFragmentCreateContractsFragment = PrivacyPolicyBuilder.createContractsFragment(new PrivacyPolicyLibParams(true, CONTRACTOR, ITEM_CODE_PRIVACY_POLICY, upperCase, list2, erpType == erpType2 ? Preferences.getTigerServerAddress(this) : Preferences.getNetsisServerAddress(this), this.baseErp.getErpType() == erpType2 ? ERP_TIGER_TEXT : ERP_NETSIS_TEXT, this.baseErp.getUser().getUserName(), this.baseErp.getUser().getPassword(), arrayList, arrayList2, arrayList3), new Function0() {
            private MainActivity f0;

            public Object invoke() {
                return this.f0.lambdalaunchPolicyUI13(upperCase);
            }
        });
        this.toolbar.setVisibility(View.GONE);
        this.list.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, contractsFragmentCreateContractsFragment).addToBackStack(null).commit();
    }

    
    public Unit lambdalaunchPolicyUI13(String str) {
        String tigerServerAddress;
        String erpType;
        String userName;
        String password;
        PrivacyPolicyLibParams agreementParams = Preferences.getAgreementParams(ContextUtils.getmContext());
        ArrayList arrayList = new ArrayList();
        if (agreementParams != null) {
            arrayList.addAll(agreementParams.getAgreementTypes());
        }
        arrayList.addAll(PrivacyPolicyState.getParams().getAgreementTypes());
        List list = (List) arrayList.stream().distinct().collect(Collectors.toList());
        List arrayList2 = new ArrayList();
        if (agreementParams != null) {
            arrayList2 = (List) agreementParams.getContentHashList().stream().map(new java.util.function.Function() {
                public Object apply(Object obj) {
                    return MainActivity.lambdalaunchPolicyUI6((Pair) obj);
                }
            }).collect(Collectors.toList());
        }
        if (agreementParams != null) {
            arrayList2.addAll(agreementParams.getContentHashList());
        }
        arrayList2.addAll(PrivacyPolicyState.getParams().getContentHashList());
        ArrayList arrayList3 = new ArrayList(((Map) arrayList2.stream().collect(Collectors.toMap(new MainActivityExternalSyntheticLambda20(), new java.util.function.Function() {
            public Object apply(Object obj) {
                return MainActivity.lambdalaunchPolicyUI7((Pair) obj);
            }
        }, (obj, obj2) -> MainActivity.lambdalaunchPolicyUI8((Pair) obj, (Pair) obj2)))).values());
        ArrayList arrayList4 = new ArrayList();
        if (agreementParams != null) {
            arrayList4.addAll(agreementParams.getAgreementTokenList());
        }
        arrayList4.addAll(PrivacyPolicyState.getParams().getAgreementTokenList());
        ArrayList arrayList5 = new ArrayList(((Map) arrayList4.stream().collect(Collectors.toMap(new MainActivityExternalSyntheticLambda20(), new java.util.function.Function() {
            public Object apply(Object obj) {
                return MainActivity.lambdalaunchPolicyUI9((Pair) obj);
            }
        }, (obj, obj2) -> MainActivity.lambdalaunchPolicyUI10((Pair) obj, (Pair) obj2)))).values());
        ArrayList arrayList6 = new ArrayList();
        if (agreementParams != null) {
            arrayList6.addAll(agreementParams.getEndDateList());
        }
        arrayList6.addAll(PrivacyPolicyState.getParams().getEndDateList());
        ArrayList arrayList7 = new ArrayList(((Map) arrayList6.stream().collect(Collectors.toMap(new MainActivityExternalSyntheticLambda20(), new java.util.function.Function() {
            public Object apply(Object obj) {
                return MainActivity.lambdalaunchPolicyUI11((Pair) obj);
            }
        }, (obj, obj2) -> MainActivity.lambdalaunchPolicyUI12((Pair) obj, (Pair) obj2)))).values());
        String contractor = agreementParams != null ? agreementParams.getContractor() : CONTRACTOR;
        String itemCode = agreementParams != null ? agreementParams.getItemCode() : ITEM_CODE_PRIVACY_POLICY;
        String language = agreementParams != null ? agreementParams.getLanguage() : str;
        if (agreementParams != null) {
            tigerServerAddress = agreementParams.getServer();
        } else {
            tigerServerAddress = this.baseErp.getErpType() == ErpType.TIGER ? Preferences.getTigerServerAddress(this) : Preferences.getNetsisServerAddress(this);
        }
        String str2 = tigerServerAddress;
        if (agreementParams != null) {
            erpType = agreementParams.getErpType();
        } else {
            erpType = this.baseErp.getErpType() == ErpType.TIGER ? ERP_TIGER_TEXT : ERP_NETSIS_TEXT;
        }
        String str3 = erpType;
        if (agreementParams != null) {
            userName = agreementParams.getUserName();
        } else {
            userName = this.baseErp.getUser().getUserName();
        }
        String str4 = userName;
        if (agreementParams != null) {
            password = agreementParams.getPassword();
        } else {
            password = this.baseErp.getUser().getPassword();
        }
        Preferences.setPrivacyPolicyParams(ContextUtils.getmContext(), new Gson().toJson(new PrivacyPolicyLibParams(true, contractor, itemCode, language, list, str2, str3, str4, password, arrayList3, arrayList5, arrayList7)));
        this.toolbar.setVisibility(View.VISIBLE);
        this.list.setVisibility(View.VISIBLE);
        return null;
    }

    
    public static Pair lambdalaunchPolicyUI6(Pair pair) {
        return new Pair(pair.getFirst(), pair.getSecond());
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.findItem(R.id.menu_notifications).setVisible(!Preferences.isDemo(this));
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("WrongConstant")
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menu_notifications) {
            Intent intent = new Intent(this, NotificationListActivity.class);
            intent.setFlags(268566528);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onResume() {
        super.onResume();
        if (!firstShowCurr) {
            ErpType erpType = this.baseErp.getErpType();
            ErpType erpType2 = ErpType.TIGER;
            if ((erpType == erpType2 && this.baseErp.getLogoSqlHelper().getTable(LogoParam.class).size() > 0) || this.baseErp.getErpType() != erpType2) {
                if (this.baseErp.askUserForExchangeRateTransfer()) {
                    this.mAlertDialogBuilder.setMessage(getString(R.string.str_question_transfer_daily_currency_information)).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        private MainActivity f0;

                        public void onClick(DialogInterface dialogInterface, int r2) {
                            this.f0.lambdaonResume14(dialogInterface, r2);
                        }
                    }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int r2) {
                            MainActivity.lambdaonResume15(dialogInterface, r2);
                        }
                    }).create().show();
                    firstShowCurr = true;
                } else {
                    firstShowCurr = true;
                    this.baseErp.checkRemoteWorkTimeControl(WorkTimeControlProcessType.TransferGet, new CheckWorkTimeListener(this));
                }
            }
        }
        this.sqlManager.getTodoList(this.baseErp.getLogoSqlHelper().getTodoListSql(this), new MainActivityTodoResponseListener(this));
        setWhTransferVisibility();
    }

    
    public void lambdaonResume14(DialogInterface dialogInterface, int r3) {
        this.baseErp.checkRemoteWorkTimeControl(WorkTimeControlProcessType.TransferGet, new CheckWorkTimeListener(this));
    }

    void initView() {
        this.mDisableColor = ContextCompat.getColor(this, R.color.grey200);
        this.txtOrder = findViewById(R.id.txtOrder);
        this.txtRequest = findViewById(R.id.txtRequest);
        this.txtDistribution = findViewById(R.id.txtDistribution);
        this.txtCustomer = findViewById(R.id.txtCustomer);
        this.txtProduct = findViewById(R.id.txtProduct);
        this.txtUtil = findViewById(R.id.txtUtil);
        this.txtReport = findViewById(R.id.txtReport);
        this.txtTransfer = findViewById(R.id.txtTransfer);
        this.txtVehicleTransfer = findViewById(R.id.txtWhTransfer);
        this.rvToDo = findViewById(R.id.rv_to_do);
        this.lyMainThird = findViewById(R.id.lyMainThird);
        this.lyMainFirst = findViewById(R.id.lyMainFirst);
        this.lyMainSecond = findViewById(R.id.lyMainSecond);
        this.lnMenuContainer = findViewById(R.id.lnMenuContainer);
        this.llTodoMesaj = findViewById(R.id.llTodoMesaj);
        this.list = findViewById(R.id.list);
        this.toolbar = findViewById(R.id.toolbar);
    }

    void setMenu() {
        if (this.baseErp.getUser().getType().equals(ExifInterface.GPS_MEASUREMENT_2D)) {
            this.txtOrder.setOnClickListener(new View.OnClickListener() {
                private MainActivity f0;

                public void onClick(View view) {
                    this.f0.lambdasetMenu16(view);
                }
            });
        } else {
            this.txtOrder.setClickable(false);
            this.txtOrder.setBackgroundColor(this.mDisableColor);
        }
        if (this.baseErp.isDemand()) {
            this.txtRequest.setOnClickListener(new View.OnClickListener() {
                private MainActivity f0;

                public void onClick(View view) {
                    this.f0.lambdasetMenu17(view);
                }
            });
        } else {
            this.txtRequest.setClickable(false);
            this.txtRequest.setBackgroundColor(this.mDisableColor);
        }
        if (this.baseErp.isDist() && this.baseErp.getErpType() != ErpType.NETSIS) {
            this.txtDistribution.setOnClickListener(new View.OnClickListener() {
                private MainActivity f0;

                public void onClick(View view) {
                    this.f0.lambdasetMenu18(view);
                }
            });
        } else {
            this.txtDistribution.setClickable(false);
            this.txtDistribution.setBackgroundColor(this.mDisableColor);
        }
        if (this.baseErp.isProducts()) {
            this.txtProduct.setOnClickListener(new View.OnClickListener() {
                private MainActivity f0;

                public void onClick(View view) {
                    this.f0.lambdasetMenu19(view);
                }
            });
        } else {
            this.txtProduct.setClickable(false);
            this.txtProduct.setBackgroundColor(this.mDisableColor);
        }
        setWhTransferVisibility();
        this.txtCustomer.setOnClickListener(new View.OnClickListener() {
            public void setF0(MainActivity f0) {
                this.f0 = f0;
            }

            private MainActivity f0;

            public void onClick(View view) {
                this.f0.lambdasetMenu20(view);
            }
        });
        this.txtTransfer.setOnClickListener(new View.OnClickListener() {
            private MainActivity f0;

            public void onClick(View view) {
                this.f0.lambdasetMenu21(view);
            }
        });
        if (this.baseErp.isReport()) {
            this.txtReport.setOnClickListener(new View.OnClickListener() {
                private MainActivity f0;

                public void onClick(View view) {
                    this.f0.lambdasetMenu22(view);
                }
            });
        } else {
            this.txtReport.setClickable(false);
            this.txtReport.setBackgroundColor(this.mDisableColor);
        }
        if (this.baseErp.isOtherMenu()) {
            this.txtUtil.setOnClickListener(new View.OnClickListener() {
                private MainActivity f0;

                public void onClick(View view) {
                    this.f0.lambdasetMenu23(view);
                }
            });
        } else {
            this.txtUtil.setClickable(false);
            this.txtUtil.setBackgroundColor(this.mDisableColor);
        }
    }

    
    public void lambdasetMenu16(View view) {
        startActivity(OrderValidationListActivity.class);
    }

    
    public void lambdasetMenu17(View view) {
        openRequest();
    }

    
    public void lambdasetMenu18(View view) {
        Intent intent = new Intent(this, DistributionListActivity.class);
        intent.putExtra(IntentExtraName.EXTRA_SALES_TYPE, SalesType.FREE);
        startActivity(intent);
    }

    
    public void lambdasetMenu19(View view) {
        Intent intent = new Intent(this, ProductListActivity.class);
        intent.putExtra(ProductListActivity.EXTRA_WAREHOUSE_NR, -1);
        startActivity(intent);
    }

    
    public void lambdasetMenu20(View view) {
        checkDailyInformationReceived(CustomerListActivity.class, true);
    }

    
    public void lambdasetMenu21(View view) {
        startActivity(TransferActivity.class);
    }

    
    public void lambdasetMenu22(View view) {
        startActivity(ReportAllActivity.class);
    }

    
    public void lambdasetMenu23(View view) {
        startActivity(OtherMenuActivity.class);
    }
     public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setWhTransferVisibility();
    }

    private void setWhTransferVisibility() {
        int r0 = getResources().getConfiguration().orientation;
        if (this.baseErp.isWhTransfer()) {
            this.llTodoMesaj.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, r0 != 1 ? 0.35f : 0.465f));
            this.lnMenuContainer.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, r0 != 1 ? 0.65f : 0.535f));
            this.lyMainThird.setVisibility(View.VISIBLE);
            this.lyMainFirst.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, 0.33f));
            this.lyMainSecond.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, 0.33f));
            this.txtVehicleTransfer.setOnClickListener(new View.OnClickListener() {
                private MainActivity f0;

                public void onClick(View view) {
                    this.f0.lambdasetWhTransferVisibility24(view);
                }
            });
            return;
        }
        this.llTodoMesaj.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, r0 != 1 ? 0.5f : 0.535f));
        this.lnMenuContainer.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, r0 != 1 ? 0.5f : 0.465f));
        this.lyMainFirst.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, 0.5f));
        this.lyMainSecond.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, 0.5f));
        this.lyMainThird.setVisibility(View.GONE);
    }

    
    public void lambdasetWhTransferVisibility24(View view) {
        openWhTransfer();
    }
     @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE})
     public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        syncData();
    }

    
    @RequiresPermission(allOf = {Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE})
    public void getCurrRates() {
        if (ContextUtils.checkConnectionWithoutMessage()) {
            this.mProgressDialogBuilder.setMessage(getString(R.string.type_get_curr_rate)).show();
            this.baseErp.getCurrRate(new CurrRatesResponseListener(this));
        }
    }

    private record CheckWorkTimeListener(WeakReference<MainActivity> mActivity) implements ResponseListener<String> {
            private CheckWorkTimeListener(MainActivity mActivity) {
                this(new WeakReference<>(mActivity));
            }

        @RequiresPermission(allOf = {Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE})
        public void onResponse(@Nullable @UnknownNullability PrintSlipModel str) {
                if (this.mActivity.get() == null || this.mActivity.get().isActivityDestroyed()) {
                    return;
                }
                this.mActivity.get().mProgressDialogBuilder.dismiss();
                if (TextUtils.isEmpty((CharSequence) str)) {
                    this.mActivity.get().getCurrRates();
                } else {
                    Toast.makeText(this.mActivity.get(), str.size(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

        @Override
        public void onResponse(Boolean aBoolean) {

        }

        @Override
        public void onResponse(Sales sales) {

        }

        @Override
        public void onResponse(TigerServiceResult tigerServiceResult) {

        }

        @Override
        public void onResponse(String obj) {

        }

        @Override
        public void onResponse(ArrayList<String> obj) {

        }

        @Override
        public void onResponse() {

        }

        @Override
        public void onResponse(Integer integer) {

        }

        public void onError(String str) {
                if (this.mActivity.get() == null || this.mActivity.get().isActivityDestroyed()) {
                    return;
                }
                this.mActivity.get().mProgressDialogBuilder.dismiss();
            }
        }

    private record CurrRatesResponseListener(WeakReference<MainActivity> mActivity) implements ResponseListener {
            private CurrRatesResponseListener(MainActivity mActivity) {
                this(new WeakReference<>(mActivity));
            }

            public void onResponse(@Nullable @UnknownNullability PrintSlipModel obj) {
                if (this.mActivity.get() == null || this.mActivity.get().isActivityDestroyed()) {
                    return;
                }
                this.mActivity.get().mProgressDialogBuilder.dismiss();
                Toast.makeText(this.mActivity.get(), this.mActivity.get().getString(R.string.str_curr_rate_success), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

        @Override
        public void onResponse(Boolean aBoolean) {

        }

        @Override
        public void onResponse(Sales sales) {

        }

        @Override
        public void onResponse(TigerServiceResult tigerServiceResult) {

        }

        @Override
        public void onResponse(Object obj) {

        }

        @Override
        public void onResponse(ArrayList obj) {

        }

        @Override
        public void onResponse() {

        }

        @Override
        public void onResponse(Integer integer) {

        }

        public void onError(String str) {
                if (this.mActivity.get() == null || this.mActivity.get().isActivityDestroyed()) {
                    return;
                }
                this.mActivity.get().mProgressDialogBuilder.dismiss();
            }
        }

    void syncData() {
        if (this.transferServiceIntent == null) {
            Intent intent = new Intent(this, DataTransferSyncService.class);
            this.transferServiceIntent = intent;
            startService(intent);
        }
    }

    void startGps() {
        LocationUpdatesService locationUpdatesService = this.mLocationUpdatesServiceService;
        if (locationUpdatesService == null || locationUpdatesService.isRequestingLocationUpdates()) {
            return;
        }
        this.mLocationUpdatesServiceService.requestLocationUpdates();
    }

    public void showRationaleForAllPermission(final PermissionRequest permissionRequest) {
        new AlertDialog.Builder(this).setMessage(getString(R.string.str_use_gps_permission)).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int r2) {
                permissionRequest.proceed();
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int r2) {
                permissionRequest.cancel();
            }
        }).show();
    }

    void showDeniedForStorage() {
        Toast.makeText(this, getString(R.string.str_for_permission_deny, getString(R.string.str_location_access)), Toast.LENGTH_SHORT).show();
    }

    void showNeverAskForStorage() {
        Toast.makeText(this, getString(R.string.str_for_permission_never_ask, getString(R.string.str_location_access)), Toast.LENGTH_SHORT).show();
    }
     @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE})
     public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
     public void onDestroy() {
        Log.d("MainActivity", "MainActivity Destroyed");
        LocationUpdatesService locationUpdatesService = this.mLocationUpdatesServiceService;
        if (locationUpdatesService != null) {
            locationUpdatesService.removeLocationUpdates();
        } else {
            stopMyService(this, LocationUpdatesService.class);
        }
        stopMyService(this, PrintService.class);
        stopMyService(this, DataTransferSyncService.class);
        stopMyService(this, DataSyncService.class);
        try {
            unregisterReceiver(this.gpsLocationReceiver);
        } catch (Exception ignored) {
        }
        AlertDialogBuilder alertDialogBuilder = this.mAlertDialogBuilder;
        if (alertDialogBuilder != null) {
            alertDialogBuilder.dismiss();
            this.mAlertDialogBuilder = null;
        }
        ProgressDialogBuilder progressDialogBuilder = this.mProgressDialogBuilder;
        if (progressDialogBuilder != null) {
            progressDialogBuilder.dismiss();
            this.mProgressDialogBuilder = null;
        }
        super.onDestroy();
    }
     public void onBackPressed() {
         super.onBackPressed();
         this.mAlertDialogBuilder.setMessage(R.string.str_exit_app).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
             private MainActivity f0;
            public void onClick(DialogInterface dialogInterface, int r2) {
                this.f0.lambdaonBackPressed27(dialogInterface, r2);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int r2) {
                MainActivity.lambdaonBackPressed28(dialogInterface, r2);
            }
        }).create().show();
    }

    
    public void lambdaonBackPressed27(DialogInterface dialogInterface, int r2) {
        firstShowCurr = false;
        exitApplication(this, LoginActivity.class);
    }

    public void baseResultMessage(BaseSelectResult baseSelectResult) {
        if (baseSelectResult.getProcessType() == ProcessType.GETCURRRATE) {
            if (baseSelectResult.isSuccess()) {
                Toast.makeText(this, getString(R.string.str_curr_rate_success), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, baseSelectResult.getErrorString(), Toast.LENGTH_LONG).show();
            }
        }
    }
    public void responseMessage(Response response) {
        if (response.isSuccess()) {
            Toast.makeText(this, getString(R.string.str_curr_rate_success), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, response.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void onTodoLoads(PrintSlipModel arrayList) {
        List<TodoInfoDb> list = this.todoInfoDbList;
        if (list != null) {
            list.clear();
        }
        this.todoInfoDbList = (List<TodoInfoDb>) arrayList;
        if (arrayList.isEmpty()) {
            return;
        }
        this.scheduleRcyclerviewAdapter.setTodos(this.todoInfoDbList);
        this.rvToDo.setLayoutManager(new StickyHeaderLayoutManager());
        this.rvToDo.setAdapter(this.scheduleRcyclerviewAdapter);
    }

    private record MainActivityTodoResponseListener( WeakReference<MainActivity> mMainActivityWeakReference) implements ResponseListener<ArrayList<TodoInfoDb>> {
            private MainActivityTodoResponseListener(MainActivity mMainActivityWeakReference) {
                this(new WeakReference<>(mMainActivityWeakReference));
            }

        public void onResponse(PrintSlipModel arrayList) {
                this.mMainActivityWeakReference.get().onTodoLoads(arrayList);
            }

        public void onFailure(Throwable throwable) {

            }

        @Override
        public void onResponse(Boolean aBoolean) {

        }

        @Override
        public void onResponse(Sales sales) {

        }

        @Override
        public void onResponse(TigerServiceResult tigerServiceResult) {

        }

        @Override
        public void onResponse(ArrayList<ArrayList<TodoInfoDb>> obj) {

        }

        @Override
        public void onResponse() {

        }

        @Override
        public void onResponse(Integer integer) {

        }

        public void onError(String str) {
                Log.d("AA", "onError: " + str);
                MainActivity mainActivity = this.mMainActivityWeakReference.get();
                if (mainActivity != null) {
                    mainActivity.onError(str);
                }
            }

        @Override
        public boolean equals(@Nullable Object obj) {
            return false;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @NonNull
        @Override
        public String toString() {
            return "";
        }
    }

    public void onRequestPermissionsResult(int r1,  String[] strArr,  int[] r3) {
        super.onRequestPermissionsResult(r1, strArr, r3);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, r1, r3);
    }
}
