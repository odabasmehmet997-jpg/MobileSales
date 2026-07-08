package com.proje.mobilesales.features.privacy;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.view.ActionMode;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseFicheActivity;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.AnalyticsOperationType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.enums.WorkTimeControlProcessType;
import com.proje.mobilesales.core.interfaces.ActionModeDelegate;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.PermissionUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.view.SnappyLinearLayoutManager;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.model.*;
import com.proje.mobilesales.features.penetration.model.Penetration;
import com.proje.mobilesales.features.penetration.model.PenetrationLine;
import com.proje.mobilesales.features.penetration.model.PenetrationTypeEnum;
import com.proje.mobilesales.features.penetration.model.database.PenetrationDetail;
import com.proje.mobilesales.features.penetration.repository.PenetrationLineRepository;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import com.proje.mobilesales.features.penetration.view.detail.PenetrationLineRecyclerViewAdapter;
import com.proje.mobilesales.features.penetration.view.detail.PenetrationLineViewModel;
import kotlin.Unit;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.TypeIntrinsics;


abstract   class PenetrationActivity extends BaseFicheActivity implements ActionModeDelegate {
    public static final int REQUEST_CAMERA_CODE = 997;
    public static final int REQUEST_IMAGE_CODE = 998;
    public static final int REQUEST_SELECT_FILE_CODE = 996;
    private static final String STATE_ADAPTER = "state:adapter";
    private static final String STATE_ENTER_PRICE = "state:enterPrice";
    private static final String STATE_EXIST = "state:exist";
    private static final String STATE_ORDER_TYPE = "state:orderType";
    private static final String STATE_PENETRATION = "state:penetration";
    private static final String STATE_PENETRATION_ID = "state:mPenetrationId";
    private static final String STATE_USER_CHOICE = "state:mUserChoice";
    private ActionMode mActionMode;
    private PenetrationLineRecyclerViewAdapter mAdapter;
    private static AlertDialogBuilder<?> mAlertDialogBuilder;
    private final int mAutoVisitId;
    private CoordinatorLayout mCoordinatorLayout;
    private boolean mEnterPrice;
    private boolean mExist;
    private FicheMode mLastFicheMode;
    private int mOrderType;
    private int mPenetrationId;
    private int mPenetrationRef;
    private AlertDialogBuilder<?> mPhotoAlertDialogBuilder;
    private ProgressDialogBuilder<?> mProgressDialogBuilder;
    private RecyclerView mRecyclerView;
    private int mSavedPenetrationID;
    private String mUserChoice;
    private Penetration penetration;
    private final ActivityResultLauncher<PickVisualMediaRequest> photoPickerLauncher;
    private final PenetrationLineRepository repository = new PenetrationLineRepository();
    private final PenetrationLineViewModel viewModel = new PenetrationLineViewModel(this.repository);
    public static final Companion Companion = new Companion(null);
    private static final String EXTRA_PENETRATION_ID = PenetrationActivity.class.getName() + ".EXTRA_PENETRATION_ID";
    private static final String EXTRA_PENETRATION = PenetrationActivity.class.getName() + ".EXTRA_PENETRATION";
    private static PenetrationActivity PenetrationActivity;

    public PenetrationActivity(int mAutoVisitId, PenetrationActivity penetrationActivity) {
        this.mAutoVisitId = mAutoVisitId;
        PenetrationActivity = penetrationActivity;
        ActivityResultLauncher<PickVisualMediaRequest> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), new ActivityResultCallback() {
            public void onActivityResult(Object obj) {
                photoPickerLauncherlambda5(PenetrationActivity.this, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.photoPickerLauncher = registerForActivityResult;
    }

    public final AlertDialogBuilder<?> getMAlertDialogBuilder() {
        return mAlertDialogBuilder;
    }

    public final void setMAlertDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        mAlertDialogBuilder = alertDialogBuilder;
    }

    public final ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        return this.mProgressDialogBuilder;
    }

    public final void setMProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        this.mProgressDialogBuilder = progressDialogBuilder;
    }

    public final AlertDialogBuilder<?> getMPhotoAlertDialogBuilder() {
        return this.mPhotoAlertDialogBuilder;
    }

    public final void setMPhotoAlertDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        this.mPhotoAlertDialogBuilder = alertDialogBuilder;
    }

    public final CoordinatorLayout getMCoordinatorLayout() {
        return this.mCoordinatorLayout;
    }

    public final void setMCoordinatorLayout(CoordinatorLayout coordinatorLayout) {
        this.mCoordinatorLayout = coordinatorLayout;
    }

    public final RecyclerView getMRecyclerView() {
        return this.mRecyclerView;
    }

    public final void setMRecyclerView(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
    }

    public final PenetrationLineRecyclerViewAdapter getMAdapter() {
        return this.mAdapter;
    }

    public final void setMAdapter(PenetrationLineRecyclerViewAdapter penetrationLineRecyclerViewAdapter) {
        this.mAdapter = penetrationLineRecyclerViewAdapter;
    }

    public final ActionMode getMActionMode() {
        return this.mActionMode;
    }

    public final void setMActionMode(ActionMode actionMode) {
        this.mActionMode = actionMode;
    }

    public final Penetration getPenetration() {
        return this.penetration;
    }
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_penetration);
        if (!this.viewModel.getBaseErp().checkRouteVisitOutOfOrder(this, this.customerRef, this.routePlanRef, this.routeDayRef)) {
            Toast.makeText(this, getString(R.string.str_comply_before_route), Toast.LENGTH_LONG).show();
            super.onBackPressed();
        }
        setToolbar();
        ActionBar supportActionBar = getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.setTitle(getPenetrationTitle());
        if (bundle != null) {
            this.penetration = bundle.getParcelable(STATE_PENETRATION);
            this.mPenetrationId = bundle.getInt(STATE_PENETRATION_ID);
            this.mOrderType = bundle.getInt(STATE_ORDER_TYPE);
            this.mEnterPrice = bundle.getBoolean(STATE_ENTER_PRICE);
            this.mExist = bundle.getBoolean(STATE_EXIST);
            this.mUserChoice = bundle.getString(STATE_USER_CHOICE);
        } else {
            Bundle extras = getIntent().getExtras();
            Intrinsics.checkNotNull(extras);
            this.mPenetrationRef = extras.getInt(EXTRA_PENETRATION, 0);
            CustomerOperation customerOperation = this.mCustomerOperation;
            FicheMode ficheMode = null;
            if ((customerOperation != null ? customerOperation.getFicheMode() : null) == FicheMode.COPY) {
                this.mSavedPenetrationID = this.mPenetrationRef;
            }
            Bundle extras2 = getIntent().getExtras();
            Intrinsics.checkNotNull(extras2);
            this.mPenetrationId = extras2.getInt(EXTRA_PENETRATION_ID);
            FicheMode salesFicheMode = getSalesFicheMode();
            Intrinsics.checkNotNullExpressionValue(salesFicheMode, "getSalesFicheMode(...)");
            PenetrationLineRecyclerViewAdapter penetrationLineRecyclerViewAdapter =
                    new PenetrationLineRecyclerViewAdapter(this, this);
            this.mAdapter = penetrationLineRecyclerViewAdapter;
            Intrinsics.checkNotNull(penetrationLineRecyclerViewAdapter);
            penetrationLineRecyclerViewAdapter.setCardViewEnabled(true);
            int i2 = this.mPenetrationRef;
            CustomerOperation customerOperation2 = this.mCustomerOperation;
            if (customerOperation2 != null) {
                ficheMode = customerOperation2.getFicheMode();
            }
            getPenetrationFiche(i2, ficheMode);
        }
        initView();
        createProgressDialogBuilder();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mAlertDialogBuilder = new AlertDialogBuilder.Impl(this, (BaseInjectableActivity) activity);
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mPhotoAlertDialogBuilder = new AlertDialogBuilder.Impl(this, (BaseInjectableActivity) activity2);
        Activity activity3 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(this, (BaseInjectableActivity) activity3);
    }
    public final void getPenetrationFiche(int i2, FicheMode ficheMode) {
        this.mLastFicheMode = ficheMode;
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        if (progressDialogBuilder != null) {
            progressDialogBuilder.setMessage(getString(R.string.str_fiche_loading)).setOnCancelClickListener().show();
        }
        this.viewModel.getPenetrationExam(i2, new PenetrationRecyclerSalesFicheGet(this));
    }
    public static final void getPenetrationFichelambda0(PenetrationActivity penetrationActivity, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(penetrationActivity, "this0");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        penetrationActivity.mLastFicheMode = null;
        penetrationActivity.mSavedPenetrationID = 0;
        dialogInterface.dismiss();
    }

    public static final class PenetrationRecyclerSalesFicheGet implements ResponseListener<Penetration> {
        private final WeakReference<PenetrationActivity> mActivity;

        public PenetrationRecyclerSalesFicheGet(PenetrationActivity penetrationActivity) {
            this.mActivity = new WeakReference<>(penetrationActivity);
        }
        public void onResponse(PrintSlipModel penetration) {
            if (this.mActivity.get() != null) {
                PenetrationActivity penetrationActivity = this.mActivity.get();
                Intrinsics.checkNotNull(penetrationActivity);
                if (!penetrationActivity.isActivityDestroyed()) {
                    PenetrationActivity penetrationActivity2 = this.mActivity.get();
                    Intrinsics.checkNotNull(penetrationActivity2);
                    penetrationActivity2.onPenetrationGet(penetration, "");
                }
            }
        }

        @Override
        public void onFailure(Throwable throwable) {

        }

        public void onError(String str) {
            Intrinsics.checkNotNullParameter(str, "errorMessage");
            if (this.mActivity.get() != null) {
                PenetrationActivity penetrationActivity = this.mActivity.get();
                Intrinsics.checkNotNull(penetrationActivity);
                if (!penetrationActivity.isActivityDestroyed()) {
                    PenetrationActivity penetrationActivity2 = this.mActivity.get();
                    Intrinsics.checkNotNull(penetrationActivity2);
                    penetrationActivity2.onPenetrationGet(null, str);
                }
            }
        }
    }
    public final void onPenetrationGet(Penetration penetration, String str) {
        int i2;
        this.mProgressDialogBuilder.dismiss();
        if (penetration != null) {
            FicheMode ficheMode = this.mLastFicheMode;
            if (ficheMode != null) {
                if (ficheMode == FicheMode.COPY && (i2 = this.mSavedPenetrationID) > 0) {
                    deletePenetration(i2, 0);
                }
                this.penetration = penetration;
                initFiche();
                initParameters();
                getPenetrationListItems();
                Penetration penetration2 = this.penetration;
                Intrinsics.checkNotNull(penetration2);
                setPenetrationLines(penetration2.getPenetrations());
                return;
            }
            Toast.makeText(this, getString(R.string.str_cancel_process), Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    public final void deletePenetration(int i2, int i3) {
        this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
        this.viewModel.getCheckRemoteWorkTimeControl(WorkTimeControlProcessType.Penetration, new CheckWorkTimeListener(this, i2, i3));
    }
    public static final class CheckWorkTimeListener implements ResponseListener<String> {
        private final WeakReference<PenetrationActivity> mActivityWeakReference;
        private final int mId;
        private final int mPosition;

        public CheckWorkTimeListener(PenetrationActivity penetrationActivity, int i2, int i3) {
            this.mActivityWeakReference = new WeakReference<>(penetrationActivity);
            this.mId = i2;
            this.mPosition = i3;
        }

        public void onResponse(PrintSlipModel str) {
            if (this.mActivityWeakReference.get() != null) {
                PenetrationActivity penetrationActivity = this.mActivityWeakReference.get();
                Intrinsics.checkNotNull(penetrationActivity);
                if (!penetrationActivity.isActivityDestroyed()) {
                    PenetrationActivity penetrationActivity2 = this.mActivityWeakReference.get();
                    Intrinsics.checkNotNull(penetrationActivity2);
                    penetrationActivity2.mProgressDialogBuilder.dismiss();
                    if (!TextUtils.isEmpty(str)) {
                        Toast.makeText(this.mActivityWeakReference.get(), str, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    PenetrationActivity penetrationActivity3 = this.mActivityWeakReference.get();
                    Intrinsics.checkNotNull(penetrationActivity3);
                    penetrationActivity3.viewModel.deletePenetrationFicheLocal(this.mId, this.mPosition, new DeleteLocalCallBack(this.mActivityWeakReference.get()));
                }
            }
        }

        @Override
        public void onFailure(Throwable throwable) {

        }

        public void onError(String str) {
            Intrinsics.checkNotNullParameter(str, "errorMessage");
            if (this.mActivityWeakReference.get() != null) {
                PenetrationActivity penetrationActivity = this.mActivityWeakReference.get();
                Intrinsics.checkNotNull(penetrationActivity);
                if (!penetrationActivity.isActivityDestroyed()) {
                    PenetrationActivity penetrationActivity2 = this.mActivityWeakReference.get();
                    Intrinsics.checkNotNull(penetrationActivity2);
                    penetrationActivity2.mProgressDialogBuilder.dismiss();
                }
            }
        }
    }
    public static   class DeleteLocalCallBack implements ResponseListener<Integer> {
        private final WeakReference<PenetrationActivity> mAdapter;

        public DeleteLocalCallBack(PenetrationActivity penetrationActivity) {
            this.mAdapter = new WeakReference<>(penetrationActivity);
        }

        public void onResponse(PrintSlipModel num) {
            if (this.mAdapter.get() != null) {
                PenetrationActivity penetrationActivity = this.mAdapter.get();
                Intrinsics.checkNotNull(penetrationActivity);
                if (!penetrationActivity.isActivityDestroyed()) {
                    PenetrationActivity penetrationActivity2 = this.mAdapter.get();
                    Intrinsics.checkNotNull(penetrationActivity2);
                    Intrinsics.checkNotNull(num);
                    penetrationActivity2.onDeleted(num.intValue());
                }
            }
        }

        @Override
        public void onFailure(Throwable throwable) {

        }

        public void onError(String str) {
            Intrinsics.checkNotNullParameter(str, "errorMessage");
            if (this.mAdapter.get() != null) {
                PenetrationActivity penetrationActivity = this.mAdapter.get();
                Intrinsics.checkNotNull(penetrationActivity);
                if (!penetrationActivity.isActivityDestroyed()) {
                    PenetrationActivity penetrationActivity2 = this.mAdapter.get();
                    Intrinsics.checkNotNull(penetrationActivity2);
                    penetrationActivity2.onDeleted(-1);
                }
            }
        }
    }
    public final void onDeleted(int i2) {
        if (i2 == -1) {
            Toast.makeText(this, (int) R.string.exp_27_database_fiche_delete_error, Toast.LENGTH_SHORT).show();
        }
    }
    private final void initParameters() {
        ISqlHelper logoSqlHelper = this.viewModel.getBaseErp().getLogoSqlHelper();
        Penetration penetration = this.penetration;
        Intrinsics.checkNotNull(penetration);
        List asMutableList = TypeIntrinsics.asMutableList(logoSqlHelper.getTable(com.proje.mobilesales.features.penetration.model.database.Penetration.class, "LOGICALREF=?", new String[]{String.valueOf(penetration.getPenetrationId())}));
        if (asMutableList != null && !asMutableList.isEmpty()) {
            this.mOrderType = 2;
            boolean z = false;
            this.mExist = ((com.proje.mobilesales.features.penetration.model.database.Penetration) asMutableList.get(0)).getPtype() == PenetrationTypeEnum.VARYOK.getValue();
            if (((com.proje.mobilesales.features.penetration.model.database.Penetration) asMutableList.get(0)).getPtype() == PenetrationTypeEnum.MIKTAR.getValue()) {
                z = true;
            }
            this.mEnterPrice = z;
        }
    }
    private final void initView() {
        this.mCoordinatorLayout = findViewById(R.id.cord_frame);
        RecyclerView recyclerView = findViewById(R.id.rcwList);
        this.mRecyclerView = recyclerView;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setLayoutManager(new SnappyLinearLayoutManager(this));
        RecyclerView recyclerView2 = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView2);
        recyclerView2.setHasFixedSize(true);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.cardview_vertical_margin);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.cardview_horizontal_margin);
        int dimensionPixelSize3 = getResources().getDimensionPixelSize(R.dimen.divider);
        RecyclerView recyclerView3 = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView3);
        recyclerView3.addItemDecoration(new RecyclerView.ItemDecoration() {
            final  int divider = 0;
            final  int horizontalMargin = 0;
            final  int verticalMargin = 0;
            final  PenetrationActivity this0 = null;

            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView4, RecyclerView.State state) {
                Intrinsics.checkNotNullParameter(rect, "outRect");
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(recyclerView4, "parent");
                Intrinsics.checkNotNullParameter(state, "state");
                PenetrationLineRecyclerViewAdapter mAdapter = this.this0.getMAdapter();
                Intrinsics.checkNotNull(mAdapter);
                if (mAdapter.isCardViewEnabled()) {
                    int i2 = this.horizontalMargin;
                    rect.set(i2, this.verticalMargin, i2, 0);
                    return;
                }
                rect.set(0, 0, 0, this.divider);
            }
        });
        RecyclerView recyclerView4 = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView4);
        recyclerView4.setAdapter(this.mAdapter);
    }

    private final void createProgressDialogBuilder() {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        if (progressDialogBuilder != null) {
            progressDialogBuilder.setMessage(getString(R.string.str_please_wait));
        }
    }
    private final Unit getPenetrationListItems() {
        String str;
        if (getSalesFicheMode() != FicheMode.ANALYSE) {
            this.mProgressDialogBuilder.show();
            int i2 = this.mOrderType;
            if (i2 == 0) {
                str = "ITEMCODE";
            } else if (i2 == 1) {
                str = "ITEMNAME";
            } else if (i2 == 2) {
                str = "SEQUENCE";
            } else {
                str = "";
            }

        }
        return Unit.INSTANCE;
    }
    protected final void initFiche() {
        if (getSalesFicheMode() == FicheMode.NEW) {
            Penetration penetration = new Penetration();
            this.penetration = penetration;
            Intrinsics.checkNotNull(penetration);
            penetration.setClRef(this.mCustomerRef);
            Penetration penetration2 = this.penetration;
            Intrinsics.checkNotNull(penetration2);
            penetration2.setClCode(this.viewModel.getBaseErp().getLogoSqlHelper().getClCode(this.mCustomerRef));
            Penetration penetration3 = this.penetration;
            Intrinsics.checkNotNull(penetration3);
            penetration3.setFicheDate(DateAndTimeUtils.getNowDateTime());
            Penetration penetration4 = this.penetration;
            Intrinsics.checkNotNull(penetration4);
            penetration4.setPenetrationId(this.mPenetrationId);
            Penetration penetration5 = this.penetration;
            Intrinsics.checkNotNull(penetration5);
            penetration5.setPenetrations(new ArrayList());
            Penetration penetration6 = this.penetration;
            Intrinsics.checkNotNull(penetration6);
            penetration6.setImage(new FicheImageProp());
            Penetration penetration7 = this.penetration;
            Intrinsics.checkNotNull(penetration7);
            penetration7.setNot(new FicheStringProp());
            Penetration penetration8 = this.penetration;
            Intrinsics.checkNotNull(penetration8);
            String uuid = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
            String upperCase = uuid.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            penetration8.setPnt_GUID(upperCase);
            return;
        }
        FicheMode salesFicheMode = getSalesFicheMode();
        FicheMode ficheMode = FicheMode.COPY;
        if (salesFicheMode == ficheMode || getSalesFicheMode() == FicheMode.EDIT) {
            Penetration penetration9 = this.penetration;
            Intrinsics.checkNotNull(penetration9);
            penetration9.setFicheDate(DateAndTimeUtils.getNowDateTime());
            Penetration penetration10 = this.penetration;
            Intrinsics.checkNotNull(penetration10);
            penetration10.setTransfer(false);
            if (getSalesFicheMode() == ficheMode) {
                Penetration penetration11 = this.penetration;
                Intrinsics.checkNotNull(penetration11);
                String uuid2 = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(uuid2, "toString(...)");
                Locale locale2 = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale2, "getDefault(...)");
                String upperCase2 = uuid2.toUpperCase(locale2);
                Intrinsics.checkNotNullExpressionValue(upperCase2, "toUpperCase(...)");
                penetration11.setPnt_GUID(upperCase2);
            }
        }
    }
    public boolean onPrepareOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        CustomerOperation customerOperation = this.mCustomerOperation;
        if ((customerOperation != null ? customerOperation.getFicheMode() : null) == FicheMode.ANALYSE) {
            menu.findItem(R.id.menu_fiche_save).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        getMenuInflater().inflate(R.menu.menu_visit, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
        }
        if (menuItem.getItemId() == R.id.menu_fiche_save && checkOneClick()) {
            saveFiche();
        }
        if (menuItem.getItemId() == R.id.menu_fiche_not) {
            Penetration penetration = this.penetration;
            Intrinsics.checkNotNull(penetration);
            showNotDialog(penetration.getNot(), getSalesFicheMode());
        }
        if (menuItem.getItemId() == R.id.menu_fiche_photo) {
            Penetration penetration2 = this.penetration;
            Intrinsics.checkNotNull(penetration2);
            showImageActivity(penetration2.getImage(), -1, getSalesFicheMode());
        }
        return super.onOptionsItemSelected(menuItem);
    }
    protected void saveFiche() {
        if (isAutoDateTimeAndZoneEnabled()) {
            Penetration penetration = this.penetration;
            Intrinsics.checkNotNull(penetration);
            PenetrationLineRecyclerViewAdapter penetrationLineRecyclerViewAdapter = this.mAdapter;
            Intrinsics.checkNotNull(penetrationLineRecyclerViewAdapter);
            penetration.setPenetrations(TypeIntrinsics.asMutableList(penetrationLineRecyclerViewAdapter.getPenetrationLines()));
            if (getSalesFicheMode() == FicheMode.EDIT) {
                PenetrationLineViewModel penetrationLineViewModel = this.viewModel;
                Penetration penetration2 = this.penetration;
                Intrinsics.checkNotNull(penetration2);
                penetrationLineViewModel.updatePenetrationFiche(penetration2, new PenetrationActivityFicheSaveListener(this));
            } else {
                PenetrationLineViewModel penetrationLineViewModel2 = this.viewModel;
                Penetration penetration3 = this.penetration;
                Intrinsics.checkNotNull(penetration3);
                penetrationLineViewModel2.savePenetrationFiche(penetration3, new PenetrationActivityFicheSaveListener(this));
            }
            baseLogOperationFirebaseAnalyticsData(AnalyticsOperationType.PENETRATION);
        }
    }
    private final String getPenetrationTitle() {
        String upperCase;
        CustomerOperation customerOperation = this.mCustomerOperation;
        FicheMode ficheMode = customerOperation != null ? customerOperation.getFicheMode() : null;
        try {
            try {
                if (!(ficheMode == FicheMode.COPY || ficheMode == FicheMode.NEW)) {
                    if (ficheMode == FicheMode.EDIT) {
                        String catStringSpace = StringUtils.catStringSpace(getString(R.string.str_edit), getString(R.string.activity_title_penetration));
                        Locale locale = Locale.getDefault();
                        Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
                        upperCase = catStringSpace.toUpperCase(locale);
                        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                    } else if (ficheMode != FicheMode.ANALYSE) {
                        return "";
                    } else {
                        String catStringSpace2 = StringUtils.catStringSpace(getString(R.string.str_analyze), getString(R.string.activity_title_penetration));
                        Locale locale2 = Locale.getDefault();
                        Intrinsics.checkNotNullExpressionValue(locale2, "getDefault(...)");
                        upperCase = catStringSpace2.toUpperCase(locale2);
                        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                    }
                    return upperCase;
                }
                String catStringSpace3 = StringUtils.catStringSpace(getString(R.string.str_new), getString(R.string.activity_title_penetration));
                Locale locale3 = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale3, "getDefault(...)");
                upperCase = catStringSpace3.toUpperCase(locale3);
                Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                return upperCase;
            } catch (Exception e2) {
                Log.e(MobileSales.TAG, "getPenetrationTitle: ", e2);
                return "";
            }
        } catch (Throwable unused) {
            return "";
        }
    }

    @SuppressLint("ResourceType")
    private static final void cancelFicheDialog() {
        AlertDialogBuilder<?> alertDialogBuilder = mAlertDialogBuilder;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.setTitle((int) R.string.str_cancel_penetration_fiche_title).setMessage(R.string.str_return_penetration_fiche_question).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i2) {
                PenetrationActivity.cancelFicheDialoglambda1(PenetrationActivity, dialogInterface, i2);
            }
        }).setNegativeButton(17039360, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i2) {
                cancelFicheDialoglambda2(dialogInterface, i2);
            }
        }).setNeutralButton(R.string.str_save, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i2) {
                cancelFicheDialoglambda3(PenetrationActivity, dialogInterface, i2);
            }
        }).create().show();
    }
    @SuppressLint("ResourceType")
    public final void cancelFicheDialoglambda1(PenetrationActivity penetrationActivity, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(penetrationActivity, "this0");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        penetrationActivity.penetration = null;
         onBackPressed();
        dialogInterface.dismiss();
    }
    public static final void cancelFicheDialoglambda2(DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        dialogInterface.dismiss();
    }
    public static final void cancelFicheDialoglambda3(PenetrationActivity penetrationActivity, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(penetrationActivity, "this0");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        if (-3 == i2) {
            dialogInterface.dismiss();
            penetrationActivity.saveFiche();
        }
    }
    @SuppressLint("ResourceType")
    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        bundle.putParcelable(STATE_PENETRATION, this.penetration);
        bundle.putInt(STATE_PENETRATION_ID, this.mPenetrationId);
        PenetrationLineRecyclerViewAdapter penetrationLineRecyclerViewAdapter = this.mAdapter;
        Intrinsics.checkNotNull(penetrationLineRecyclerViewAdapter);
        bundle.putBundle("state:adapter", penetrationLineRecyclerViewAdapter.saveState());
        bundle.putInt(STATE_ORDER_TYPE, this.mOrderType);
        bundle.putBoolean(STATE_EXIST, this.mExist);
        bundle.putBoolean(STATE_ENTER_PRICE, this.mEnterPrice);
        bundle.putString(STATE_USER_CHOICE, this.mUserChoice);
        super.onSaveInstanceState(bundle);
    }

    private final void setPenetrationLines(List<PenetrationLine> list) {
        Log.d(MobileSales.TAG, "setPenetrationLines: " + this.mExist);
        PenetrationLineRecyclerViewAdapter penetrationLineRecyclerViewAdapter = this.mAdapter;
        Intrinsics.checkNotNull(penetrationLineRecyclerViewAdapter);
        penetrationLineRecyclerViewAdapter.setExist(this.mExist);
        PenetrationLineRecyclerViewAdapter penetrationLineRecyclerViewAdapter2 = this.mAdapter;
        Intrinsics.checkNotNull(penetrationLineRecyclerViewAdapter2);
        penetrationLineRecyclerViewAdapter2.setPenetrationLines(list);
        Penetration penetration = this.penetration;
        Intrinsics.checkNotNull(penetration);
        penetration.setExist(this.mExist);
    }
    public final void setPenetrationLine(List<PenetrationDetail> list) {
        if (list != null && !list.isEmpty()) {
            if (getSalesFicheMode() == FicheMode.NEW) {
                for (PenetrationDetail penetrationDetail : list) {
                    PenetrationLine penetrationLine = new PenetrationLine();
                    penetrationLine.setPenetrationDetailId(penetrationDetail.getLogicalRef());
                    penetrationLine.setProductCode(penetrationDetail.getItemCode());
                    penetrationLine.setProductName(penetrationDetail.getItemName());
                    penetrationLine.setProductRef(StringUtils.convertStringToInt(penetrationDetail.getItemRef()));
                    penetrationLine.setLineNr(penetrationDetail.getSequence());
                    penetrationLine.setCurrency(new FicheDiscountRefProp());
                    penetrationLine.setAmount(new FicheDiscountRefProp());
                    penetrationLine.setPrice(new FicheDiscountRefProp());
                    penetrationLine.setExist(new FicheBooleanProp());
                    penetrationLine.setImage(new FicheImageProp());
                    penetrationLine.setNot(new FicheStringProp());
                    penetrationLine.setImageActive(StringUtils.convertIntToBoolean(penetrationDetail.getPerPic()));
                    penetrationLine.setNotActive(StringUtils.convertIntToBoolean(penetrationDetail.getPerNote()));
                    penetrationLine.setPriceActive(StringUtils.convertIntToBoolean(penetrationDetail.getPerPrice()));
                    Penetration penetration = this.penetration;
                    Intrinsics.checkNotNull(penetration);
                    List<PenetrationLine> penetrations = penetration.getPenetrations();
                    if (penetrations != null) {
                        penetrations.add(penetrationLine);
                    }
                }
            }
            PenetrationLineRecyclerViewAdapter penetrationLineRecyclerViewAdapter = this.mAdapter;
            Intrinsics.checkNotNull(penetrationLineRecyclerViewAdapter);
            penetrationLineRecyclerViewAdapter.setExist(this.mExist);
            PenetrationLineRecyclerViewAdapter penetrationLineRecyclerViewAdapter2 = this.mAdapter;
            Intrinsics.checkNotNull(penetrationLineRecyclerViewAdapter2);
            Penetration penetration2 = this.penetration;
            Intrinsics.checkNotNull(penetration2);
            penetrationLineRecyclerViewAdapter2.setPenetrationLines(penetration2.getPenetrations());
        }
        this.mProgressDialogBuilder.dismiss();
    }
    public final void showToast(String str) {
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(this, str, Toast.LENGTH_LONG).show();
        }
    }
    public boolean startActionMode(ActionMode.Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (getSalesFicheMode() == FicheMode.ANALYSE) {
            return false;
        }
        if (this.mActionMode != null) {
            return true;
        }
        this.mActionMode = startSupportActionMode(callback);
        return true;
    }
    public boolean isInActionMode() {
        return this.mActionMode != null;
    }
    public void stopActionMode() {
        this.mActionMode = null;
    }
    public abstract static  class PenetrationLineResponseListener implements ResponseListener<List<? extends PenetrationDetail>> {
        private WeakReference<PenetrationActivity> mPenetrationActivity;

        public PenetrationLineResponseListener(PenetrationActivity penetrationActivity) {
            this.mPenetrationActivity = new WeakReference<>(penetrationActivity);
        }
        public final WeakReference<PenetrationActivity> getMPenetrationActivity() {
            return this.mPenetrationActivity;
        }

        public final void setMPenetrationActivity(WeakReference<PenetrationActivity> weakReference) {
            Intrinsics.checkNotNullParameter(weakReference, "<set-?>");
            this.mPenetrationActivity = weakReference;
        }

        public void onResponse(List<PenetrationDetail> list) {
            if (this.mPenetrationActivity.get() != null) {
                PenetrationActivity penetrationActivity = this.mPenetrationActivity.get();
                Intrinsics.checkNotNull(penetrationActivity);
                if (!penetrationActivity.isActivityDestroyed()) {
                    PenetrationActivity penetrationActivity2 = this.mPenetrationActivity.get();
                    Intrinsics.checkNotNull(penetrationActivity2);
                    penetrationActivity2.setPenetrationLine(list);
                }
            }
        }
        public void onError(String str) {
            Intrinsics.checkNotNullParameter(str, "errorMessage");
            if (this.mPenetrationActivity.get() != null) {
                PenetrationActivity penetrationActivity = this.mPenetrationActivity.get();
                Intrinsics.checkNotNull(penetrationActivity);
                if (!penetrationActivity.isActivityDestroyed()) {
                    PenetrationActivity penetrationActivity2 = this.mPenetrationActivity.get();
                    Intrinsics.checkNotNull(penetrationActivity2);
                    penetrationActivity2.mProgressDialogBuilder.dismiss();
                    PenetrationActivity penetrationActivity3 = this.mPenetrationActivity.get();
                    Intrinsics.checkNotNull(penetrationActivity3);
                    penetrationActivity3.showToast(str);
                }
            }
        }
    }

    public final void onSaveResult(boolean z, String str) {
        if (z) {
            String string = getString(R.string.str_fiche_save_successful);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            showToast(string);
            insertRouteProcess(this.mCustomerOperation);
            finish();
            return;
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%s %s", Arrays.copyOf(new Object[]{getString(R.string.str_fiche_save_on_error), str}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        showToast(format);
    }
    @SuppressLint("ResourceType")
    public static final class PenetrationActivityFicheSaveListener implements ResponseListener<Boolean> {
        private final WeakReference<PenetrationActivity> mPenetrationActivityWeakReference;

        public PenetrationActivityFicheSaveListener(PenetrationActivity penetrationActivity) {
            this.mPenetrationActivityWeakReference = new WeakReference<>(penetrationActivity);
        }

        public void onResponse(PrintSlipModel bool) {
            if (this.mPenetrationActivityWeakReference.get() != null) {
                PenetrationActivity penetrationActivity = this.mPenetrationActivityWeakReference.get();
                Intrinsics.checkNotNull(penetrationActivity);
                if (!penetrationActivity.isActivityDestroyed()) {
                    PenetrationActivity penetrationActivity2 = this.mPenetrationActivityWeakReference.get();
                    Intrinsics.checkNotNull(penetrationActivity2);
                    Intrinsics.checkNotNull(bool);
                    penetrationActivity2.onSaveResult(bool.booleanValue(), "");
                }
            }
        }

        @Override
        public void onFailure(Throwable throwable) {

        }

        public void onError(String str) {
            Intrinsics.checkNotNullParameter(str, "errorMessage");
            Log.d("AA", "onError: " + str);
            if (this.mPenetrationActivityWeakReference.get() != null) {
                PenetrationActivity penetrationActivity = this.mPenetrationActivityWeakReference.get();
                Intrinsics.checkNotNull(penetrationActivity);
                if (!penetrationActivity.isActivityDestroyed()) {
                    PenetrationActivity penetrationActivity2 = this.mPenetrationActivityWeakReference.get();
                    Intrinsics.checkNotNull(penetrationActivity2);
                    penetrationActivity2.onSaveResult(false, str);
                }
            }
        }
    }
    public static final void photoPickerLauncherlambda5(PenetrationActivity penetrationActivity, Uri uri) {
        Intrinsics.checkNotNullParameter(penetrationActivity, "this0");
        if (uri != null) {
            penetrationActivity.onImagePicked(uri);
        }
    }
    private final void startUserChooseIntent() {
        if (Intrinsics.areEqual(this.mUserChoice, getString(R.string.str_choose_from_library))) {
            if (Build.VERSION.SDK_INT >= 33) {
                this.photoPickerLauncher.launch(new PickVisualMediaRequest.Builder().setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE).build());
            } else if (PermissionUtils.checkPermission(this, "android.permission.READ_EXTERNAL_STORAGE", getString(R.string.str_read_disk_permission_for_save_picture))) {
                createGalleryIntent();
            }
        } else if (Intrinsics.areEqual(this.mUserChoice, getString(R.string.str_take_photo)) && PermissionUtils.checkPermission(this, "android.permission.CAMERA", getString(R.string.str_camera_permission_for_take_photo))) {
            createCameraIntent();
        }
    }
    private final void onImagePicked(Uri uri) {
        try {
            InputStream openInputStream = getContentResolver().openInputStream(uri);
            FicheImageProp ficheImageProp = null;
            byte[] readBytes = openInputStream != null ? ByteStreamsKt.readBytes(openInputStream) : null;
            if (readBytes != null) {
                PenetrationLineRecyclerViewAdapter penetrationLineRecyclerViewAdapter = this.mAdapter;
                if (penetrationLineRecyclerViewAdapter != null) {
                    Intrinsics.checkNotNull(penetrationLineRecyclerViewAdapter);
                    if (penetrationLineRecyclerViewAdapter.getPenetrationLines() != null) {
                        PenetrationLineRecyclerViewAdapter penetrationLineRecyclerViewAdapter2 = this.mAdapter;
                        Intrinsics.checkNotNull(penetrationLineRecyclerViewAdapter2);
                        List<PenetrationLine> penetrationLines = penetrationLineRecyclerViewAdapter2.getPenetrationLines();
                        Intrinsics.checkNotNull(penetrationLines);
                        if (!penetrationLines.isEmpty()) {
                            Penetration penetration = this.penetration;
                            if (penetration != null) {
                                ficheImageProp = penetration.getImage();
                            }
                            if (ficheImageProp != null) {
                                ficheImageProp.setImageArray(readBytes);
                            }
                        }
                    }
                }
                PenetrationLineRecyclerViewAdapter penetrationLineRecyclerViewAdapter3 = this.mAdapter;
                if (penetrationLineRecyclerViewAdapter3 != null) {
                    penetrationLineRecyclerViewAdapter3.notifyDataSetChanged();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private final void createCameraIntent() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 997);
        }
    }

    private final void createGalleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(Intent.createChooser(intent, "Select File"), 996);
    }

    @Override
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 998 && i3 == -1) {
            onImageResult(intent);
        }
    }

    @SuppressLint("ResourceType")
    private final void onImageResult(Intent intent) {
        if (intent != null) {
            try {
                Bundle extras = intent.getExtras();
                Intrinsics.checkNotNull(extras);
                FicheImageProp ficheImageProp = extras.getParcelable(IntentExtraName.EXTRAS_IMAGE);
                Bundle extras2 = intent.getExtras();
                Intrinsics.checkNotNull(extras2);
                int i2 = extras2.getInt(IntentExtraName.EXTRAS_POSITON, -1);
                if (i2 != -1) {
                    PenetrationLineRecyclerViewAdapter penetrationLineRecyclerViewAdapter = this.mAdapter;
                    Intrinsics.checkNotNull(penetrationLineRecyclerViewAdapter);
                    List<PenetrationLine> penetrationLines = penetrationLineRecyclerViewAdapter.getPenetrationLines();
                    Intrinsics.checkNotNull(penetrationLines);
                    FicheImageProp image = penetrationLines.get(i2).getImage();
                    Intrinsics.checkNotNull(image);
                    Intrinsics.checkNotNull(ficheImageProp);
                    image.setImageArray(ficheImageProp.getImageArray());
                    PenetrationLineRecyclerViewAdapter penetrationLineRecyclerViewAdapter2 = this.mAdapter;
                    Intrinsics.checkNotNull(penetrationLineRecyclerViewAdapter2);
                    penetrationLineRecyclerViewAdapter2.notifyDataSetChanged();
                    return;
                }
                Penetration penetration = this.penetration;
                Intrinsics.checkNotNull(penetration);
                FicheImageProp image2 = penetration.getImage();
                Intrinsics.checkNotNull(image2);
                Intrinsics.checkNotNull(ficheImageProp);
                image2.setImageArray(ficheImageProp.getImageArray());
            } catch (Exception e2) {
                while (true) {
                    e2.printStackTrace();
                    return;
                }
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        String str;
        Intrinsics.checkNotNullParameter(strArr, "permissions");
        Intrinsics.checkNotNullParameter(iArr, "grantResults");
        if (i2 == 1071 || i2 == 1074) {
            if ((iArr.length == 0) || iArr[0] != 0) {
                Toast.makeText(this, getString(R.string.str_permissions_denied), Toast.LENGTH_LONG).show();
            } else {
                if (i2 == 1071) {
                    str = getString(R.string.str_choose_from_library);
                } else {
                    str = getString(R.string.str_take_photo);
                }
                this.mUserChoice = str;
                startUserChooseIntent();
            }
        }
        super.onRequestPermissionsResult(i2, strArr, iArr);
    }
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
        private Companion() {
        }
        public String getEXTRA_PENETRATION_ID() {
            return EXTRA_PENETRATION_ID;
        }
        public String getEXTRA_PENETRATION() {
            return EXTRA_PENETRATION;
        }
    }
}
