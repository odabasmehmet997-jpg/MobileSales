package com.proje.mobilesales.features.visit.view.activity;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.ActivityModule_ProvideAlertDialogBuilderFactory;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseFicheActivity;
import com.proje.mobilesales.core.base.BaseFicheFragment;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.AnalyticsOperationType;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.activity.MainActivity;
import com.proje.mobilesales.features.dbmodel.ShipAddress;
import com.proje.mobilesales.features.model.FicheImageProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import com.proje.mobilesales.features.sales.view.detail.SalesDetailActivity;
import com.proje.mobilesales.features.visit.model.Visit;
import com.proje.mobilesales.features.visit.repository.VisitRepository;
import com.proje.mobilesales.features.visit.view.fragment.VisitEnterFragment;
import com.proje.mobilesales.features.visit.viewmodel.VisitViewModel;
import java.lang.ref.WeakReference;
import java.util.*;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PrimitiveCompanionObjects;

public final class VisitEnterActivityNew extends BaseFicheActivity {
    public static final String EXTRA_VISIT = VisitEnterActivityNew.class.getName() + ".EXTRA_VISIT";
    private static final String STATE_VISIT = "state:visit";
    public AlertDialogBuilder<?> mAlertDialogBuilder;
    private Visit mVisit;
    private final VisitRepository repository = new VisitRepository();
    private final VisitViewModel viewModel = new VisitViewModel(repository);
    public enum WhenMappings {
        ;
        public static final int[] EnumSwitchMapping0;

        static {
            final int[] iArr = new int[FicheMode.values().length];
            try {
                iArr[FicheMode.COPY.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                iArr[FicheMode.NEW.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                iArr[FicheMode.EDIT.ordinal()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
            try {
                iArr[FicheMode.ANALYSE.ordinal()] = 4;
            } catch (final NoSuchFieldError unused4) {
            }
            EnumSwitchMapping0 = iArr;
        }
    }
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.getActivityComponent().inject(this);
        final Context context = ContextUtils.getmContext();
        final Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mAlertDialogBuilder = new AlertDialogBuilder.Impl(context, (BaseInjectableActivity) activity);
        if ((mCustomerOperation.getFicheMode() == FicheMode.NEW || mCustomerOperation.getFicheMode() == FicheMode.COPY) && !Objects.requireNonNull(viewModel).getBaseErp().checkRouteVisitOutOfOrder(this, mCustomerRef, routePlanRef, routeDayRef)) {
            Toast.makeText(this, this.getString(R.string.str_comply_before_route), Toast.LENGTH_LONG).show();
            super.onBackPressed();
        }
        this.setContentView(R.layout.activity_visit);
        this.setToolbar();
        final ActionBar supportActionBar = this.getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.setTitle(this.getVisitTitle());
        if (null != bundle) {
            mVisit = bundle.getParcelable(VisitEnterActivityNew.STATE_VISIT);
            return;
        }
        final Bundle extras = this.getIntent().getExtras();
        Intrinsics.checkNotNull(extras);
        mVisit = extras.getParcelable(VisitEnterActivityNew.EXTRA_VISIT);
        if (this.getSalesFicheMode() == FicheMode.EDIT && -1 != MainActivity.sFicheRef && null == this.mVisit) {
            this.createVisit();
        }
        this.initFiche();
        this.initUserTitle();
        this.getSupportFragmentManager().beginTransaction().replace(R.id.list, this.instantiateVisitFragment(), SalesDetailActivity.SALES_DETAIL_FRAGMENT_TAG).commit();
    }
    private void createVisit() {
        final Cursor query = viewModel.getBaseErp().getLogoSqlBriteDatabase().query(viewModel.getBaseErp().getLogoSqlHelper().getVisitSql(this, MainActivity.sFicheRef));
        if (null != query) {
            try {
                if (query.moveToFirst()) {
                    final Visit visit = new Visit();
                    mVisit = visit;
                    Intrinsics.checkNotNull(visit);
                    visit.setId(query.getInt(Integer.parseInt(String.valueOf(query.getColumnIndex("ID")))));
                    if (viewModel.erpType() == ErpType.NETSIS) {
                        final Visit visit2 = mVisit;
                        Intrinsics.checkNotNull(visit2);
                        visit2.setClCode(query.getString(Integer.parseInt(String.valueOf(query.getColumnIndex("CODE")))));
                    } else {
                        final Visit visit3 = mVisit;
                        Intrinsics.checkNotNull(visit3);
                        visit3.setClRef(query.getInt(Integer.parseInt(String.valueOf(query.getColumnIndex("CLREF")))));
                    }
                    final Visit visit4 = mVisit;
                    Intrinsics.checkNotNull(visit4);
                    visit4.setNote(new FicheStringProp(query.getString(Integer.parseInt(String.valueOf(query.getColumnIndex("NOTE"))))));
                    final Visit visit5 = mVisit;
                    Intrinsics.checkNotNull(visit5);
                    visit5.setReason(new FicheRefProp(-1, -1, query.getString(Integer.parseInt(String.valueOf(query.getColumnIndex("REASON"))))));
                    final Visit visit6 = mVisit;
                    Intrinsics.checkNotNull(visit6);
                    visit6.setShipAddress(new FicheRefProp(-1, -1, query.getString(Integer.parseInt(String.valueOf(query.getColumnIndex("EXPLANATION"))))));
                    final Visit visit7 = mVisit;
                    Intrinsics.checkNotNull(visit7);
                    visit7.setUserTitle(new FicheRefProp());
                    final Visit visit8 = mVisit;
                    Intrinsics.checkNotNull(visit8);
                    visit8.setImage(new FicheImageProp());
                    query.close();
                    return;
                }
            } catch (final Throwable th) {
                if (!query.isClosed()) {
                    query.close();
                }
                throw th;
            }
        }
        Toast.makeText(this, this.getString(R.string.str_data_not_found), Toast.LENGTH_LONG).show();
        this.onBackPressed();
        if (null != query) {
        }
    }
    private String getVisitTitle() {
        final int i2;
        final String upperCase;
        final FicheMode ficheMode = mCustomerOperation.getFicheMode();
        try {
            if (null == ficheMode) {
                i2 = -1;
            } else {
                try {
                    i2 = WhenMappings.EnumSwitchMapping0[ficheMode.ordinal()];
                } catch (final Exception e2) {
                    Log.e(MobileSales.TAG, "getVisitTitle: ", e2);
                    return "";
                }
            }
            if (1 == i2 || 2 == i2) {
                final String catStringSpace = StringUtils.catStringSpace(this.getString(R.string.str_new), this.getString(R.string.str_visit));
                final Locale locale = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
                upperCase = catStringSpace.toUpperCase(locale);
                Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            } else if (3 == i2) {
                final String catStringSpace2 = StringUtils.catStringSpace(this.getString(R.string.str_edit), this.getString(R.string.str_visit));
                final Locale locale2 = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale2, "getDefault(...)");
                upperCase = catStringSpace2.toUpperCase(locale2);
                Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            } else {
                if (4 != i2) {
                    return "";
                }
                final String catStringSpace3 = StringUtils.catStringSpace(this.getString(R.string.str_analyze), this.getString(R.string.str_visit));
                final Locale locale3 = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale3, "getDefault(...)");
                upperCase = catStringSpace3.toUpperCase(locale3);
                Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            }
            return upperCase;
        } catch (final Throwable unused) {
            return "";
        }
    }
    private Fragment instantiateVisitFragment() {
        final Bundle bundle = new Bundle();
        bundle.putInt(BaseFicheFragment.EXTRA_CUSTOMER_REF, mCustomerRef);
        final Fragment instantiate = Fragment.instantiate(this, VisitEnterFragment.class.getName(), bundle);
        Intrinsics.checkNotNullExpressionValue(instantiate, "instantiate(...)");
        return instantiate;
    }
    protected void initFiche() {
        if (this.getSalesFicheMode() == FicheMode.NEW) {
            final Visit visit = new Visit();
            mVisit = visit;
            Intrinsics.checkNotNull(visit);
            visit.setClCode(viewModel.getBaseErp().getLogoSqlHelper().getClCode(mCustomerRef));
            final Visit visit2 = mVisit;
            Intrinsics.checkNotNull(visit2);
            visit2.setClRef(mCustomerRef);
            final Visit visit3 = mVisit;
            Intrinsics.checkNotNull(visit3);
            visit3.setDate(DateAndTimeUtils.getNowDateTime());
            final Visit visit4 = mVisit;
            Intrinsics.checkNotNull(visit4);
            visit4.setShipAddress(new FicheRefProp());
            if (viewModel.erpType() != ErpType.NETSIS && viewModel.getBaseErp().getRouteNewSystem()) {
                final int routeShipRef = Preferences.getRouteShipRef(ContextUtils.getmContext());
                final ISqlHelper logoSqlHelper = viewModel.getBaseErp().getLogoSqlHelper();
                final Visit visit5 = mVisit;
                Intrinsics.checkNotNull(visit5);
                final List table = logoSqlHelper.getTable(ShipAddress.class, "CLREF=? AND LOGICALREF=?", new String[]{String.valueOf(visit5.getClRef()), String.valueOf(routeShipRef)});
                Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.ShipAddress>");
                if (!table.isEmpty()) {
                    final ShipAddress shipAddress = (ShipAddress) table.get(0);
                    final Visit visit6 = mVisit;
                    Intrinsics.checkNotNull(visit6);
                    final FicheRefProp shipAddress2 = visit6.getShipAddress();
                    Intrinsics.checkNotNull(shipAddress2);
                    FicheStringProp.setDefinition(shipAddress.getCode() + shipAddress.getName() + shipAddress.getAddr1() + shipAddress.getAddr2() + shipAddress.getCity());
                    final Visit visit7 = mVisit;
                    Intrinsics.checkNotNull(visit7);
                    final FicheRefProp shipAddress3 = visit7.getShipAddress();
                    Intrinsics.checkNotNull(shipAddress3);
                    shipAddress3.setLogicalRef(shipAddress.getLogicalRef());
                }
            }
            final Visit visit8 = mVisit;
            Intrinsics.checkNotNull(visit8);
            visit8.setNote(new FicheStringProp());
            final Visit visit9 = mVisit;
            Intrinsics.checkNotNull(visit9);
            visit9.setReason(new FicheRefProp());
            final Visit visit10 = mVisit;
            Intrinsics.checkNotNull(visit10);
            visit10.setUserTitle(new FicheRefProp());
            final Visit visit11 = mVisit;
            Intrinsics.checkNotNull(visit11);
            visit11.setImage(new FicheImageProp());
            final Visit visit12 = mVisit;
            Intrinsics.checkNotNull(visit12);
            visit12.setLatitude(String.valueOf(this.getLatitude()));
            final Visit visit13 = mVisit;
            Intrinsics.checkNotNull(visit13);
            visit13.setLongitude(String.valueOf(this.getLongitude()));
            return;
        }
        if (this.getSalesFicheMode() == FicheMode.COPY || this.getSalesFicheMode() == FicheMode.EDIT) {
            final Visit visit14 = mVisit;
            Intrinsics.checkNotNull(visit14);
            visit14.setDate(DateAndTimeUtils.getNowDateTime());
            final Visit visit15 = mVisit;
            Intrinsics.checkNotNull(visit15);
            visit15.setLatitude(String.valueOf(this.getLatitude()));
            final Visit visit16 = mVisit;
            Intrinsics.checkNotNull(visit16);
            visit16.setLongitude(String.valueOf(this.getLongitude()));
        }
    }
    protected void saveFiche() {
        if (this.isAutoDateTimeAndZoneEnabled()) {
            final Visit visit = mVisit;
            Intrinsics.checkNotNull(visit);
            final String visitSaveControl = visit.visitSaveControl();
            if (!TextUtils.isEmpty(visitSaveControl)) {
                final AlertDialogBuilder<?> alertDialogBuilder = mAlertDialogBuilder;
                Intrinsics.checkNotNull(alertDialogBuilder);
                alertDialogBuilder.setTitle(R.string.str_visit_error_message_title).setMessage(visitSaveControl).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialogInterface, final int i2) {
                        saveFichelambda0(dialogInterface, i2);
                    }
                }).create().show();
                return;
            }
            if (this.getSalesFicheMode() == FicheMode.EDIT) {
                final Visit visit2 = this.mVisit;
                Intrinsics.checkNotNull(visit2);
                viewModel.updateVisitFiche(visit2, new VisitActivityFicheSaveListener(this));
            } else {
                final Visit visit3 = this.mVisit;
                Intrinsics.checkNotNull(visit3);
                viewModel.saveVisitFiche(visit3, new VisitActivityFicheSaveListener(this));
            }
            this.baseLogOperationFirebaseAnalyticsData(AnalyticsOperationType.VISIT);
        }
    }
    public static void saveFichelambda0(final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }
    void initUserTitle() {
        final Visit visit = mVisit;
        if (null != visit) {
            Intrinsics.checkNotNull(visit);
        }
        if (this.getSalesFicheMode() != FicheMode.NEW) {
            Toast.makeText(this, this.getString(R.string.str_fiche_delete_erp), Toast.LENGTH_LONG).show();
            super.onBackPressed();
            return;
        }
        final Visit visit2 = mVisit;
        Intrinsics.checkNotNull(visit2);
        final FicheRefProp userTitle = visit2.getUserTitle();
        Intrinsics.checkNotNull(userTitle);
        if (-1 < userTitle.getLogicalRef()) {
            final String[] stringArray = this.getResources().getStringArray(R.array.visit_user_title_values);
            final Visit visit3 = mVisit;
            Intrinsics.checkNotNull(visit3);
            final FicheRefProp userTitle2 = visit3.getUserTitle();
            Intrinsics.checkNotNull(userTitle2);
            final String str = stringArray[userTitle2.getLogicalRef()];
            final Visit visit4 = this.mVisit;
            Intrinsics.checkNotNull(visit4);
            final FicheRefProp userTitle3 = visit4.getUserTitle();
            Intrinsics.checkNotNull(userTitle3);
            FicheStringProp.setDefinition(str);
            final Visit visit5 = this.mVisit;
            Intrinsics.checkNotNull(visit5);
            final FicheRefProp userTitle4 = visit5.getUserTitle();
            Intrinsics.checkNotNull(userTitle4);
            final Visit visit6 = mVisit;
            Intrinsics.checkNotNull(visit6);
            final FicheRefProp userTitle5 = visit6.getUserTitle();
            Intrinsics.checkNotNull(userTitle5);
            userTitle4.setWhich(userTitle5.getLogicalRef());
        }
    }
    public void onBackPressed() {
        if (this.getSalesFicheMode() == FicheMode.ANALYSE) {
            super.onBackPressed();
        } else if (this.getSalesFicheMode() == FicheMode.EDIT) {
            this.cancelEditFicheDialog();
        } else {
            this.cancelFicheDialog();
        }
    }
    private void cancelEditFicheDialog() {
        final AlertDialogBuilder<?> alertDialogBuilder = mAlertDialogBuilder;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.setTitle("").setMessage(R.string.str_return_edit_fiche_question).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.visit.view.activity.VisitEnterActivityNewExternalSyntheticLambda4
            public void VisitEnterActivityNewExternalSyntheticLambda4() {
            }

            public void onClick(final DialogInterface dialogInterface, final int i2) {
                cancelEditFicheDialoglambda1(VisitEnterActivityNew.this, dialogInterface, i2);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                cancelEditFicheDialoglambda2(dialogInterface, i2);
            }
        }).setNeutralButton(R.string.str_save, new DialogInterface.OnClickListener() {
            public void VisitEnterActivityNewExternalSyntheticLambda6() {
            }

            public void onClick(final DialogInterface dialogInterface, final int i2) {
                cancelEditFicheDialoglambda3(VisitEnterActivityNew.this, dialogInterface, i2);
            }
        }).create().show();
    }
    public static void cancelEditFicheDialoglambda1(final VisitEnterActivityNew this0, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        this0.mVisit = null;
        this0.onBackPressed();
        dialogInterface.dismiss();
    }
    public static void cancelEditFicheDialoglambda2(final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        dialogInterface.dismiss();
    }
    public static void cancelEditFicheDialoglambda3(final VisitEnterActivityNew this0, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        if (-3 == i2) {
            dialogInterface.dismiss();
            this0.saveFiche();
        }
    }
    private void cancelFicheDialog() {
        final AlertDialogBuilder<?> alertDialogBuilder = mAlertDialogBuilder;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.setTitle(R.string.str_cancel_visit_fiche_title).setMessage(R.string.str_return_visit_fiche_question).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.visit.view.activity.VisitEnterActivityNewExternalSyntheticLambda1
            public void VisitEnterActivityNewExternalSyntheticLambda1() {
            }

            public void onClick(final DialogInterface dialogInterface, final int i2) {
                cancelFicheDialoglambda4(VisitEnterActivityNew.this, dialogInterface, i2);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                cancelFicheDialoglambda5(dialogInterface, i2);
            }
        }).setNeutralButton(R.string.str_save, new DialogInterface.OnClickListener() {
            public void VisitEnterActivityNewExternalSyntheticLambda3() {
            }

            public void onClick(final DialogInterface dialogInterface, final int i2) {
                cancelFicheDialoglambda6(VisitEnterActivityNew.this, dialogInterface, i2);
            }
        }).create().show();
    }
    public static void cancelFicheDialoglambda4(final VisitEnterActivityNew this0, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        this0.mVisit = null;
        this0.onBackPressed();
        dialogInterface.dismiss();
    }
    public static void cancelFicheDialoglambda5(final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        dialogInterface.dismiss();
    }
    public static void cancelFicheDialoglambda6(final VisitEnterActivityNew this0, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        if (-3 == i2) {
            dialogInterface.dismiss();
            this0.saveFiche();
        }
    }
    public void onSaveInstanceState(final Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putParcelable(VisitEnterActivityNew.STATE_VISIT, mVisit);
        super.onSaveInstanceState(outState);
    }
    public Visit getmVisit() {
        return mVisit;
    }
    public boolean onCreateOptionsMenu(final Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        if (this.getSalesFicheMode() != FicheMode.ANALYSE) {
            this.getMenuInflater().inflate(R.menu.menu_add_photo, menu);
            if (menu.findItem(R.id.menu_photo) != null) {
                menu.findItem(R.id.menu_photo).setVisible(!Preferences.isDemo(ContextUtils.getmContext()));
            }
            this.getMenuInflater().inflate(R.menu.menu_visit, menu);
            if (menu.findItem(R.id.menu_fiche_photo) != null) {
                menu.findItem(R.id.menu_fiche_photo).setVisible(false);
            }
            if (menu.findItem(R.id.menu_fiche_not) != null) {
                menu.findItem(R.id.menu_fiche_not).setVisible(false);
            }
        }
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (16908332 == item.getItemId()) {
            this.onBackPressed();
        } else if (item.getItemId() == R.id.menu_fiche_save && this.checkOneClick()) {
            this.saveFiche();
        }
        return super.onOptionsItemSelected(item);
    }
    protected void onActivityResult(final int i2, final int i3, final Intent intent) {
        super.onActivityResult(i2, i3, intent);
    }
    public void onSaveResult(final boolean z, final String str) {
        if (z) {
            Toast.makeText(this, this.getString(R.string.str_fiche_save_successful), Toast.LENGTH_LONG).show();
            this.insertRouteProcess(mCustomerOperation);
            this.finish();
        } else {
            final PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
            final String format = String.format("%s %s", Arrays.copyOf(new Object[]{this.getString(R.string.str_fiche_save_on_error), str}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            Toast.makeText(this, format, Toast.LENGTH_LONG).show();
        }
    }
    private class VisitActivityFicheSaveListener(WeakReference<VisitEnterActivityNew> mVisitEnterActivityNew) implements ResponseListener<Boolean> {
        private ActivityModule_ProvideAlertDialogBuilderFactory mVisitEnterActivityNew;

        private VisitActivityFicheSaveListener(final VisitEnterActivityNew mVisitEnterActivityNew) {
            this(new VisitEnterActivityNew());

        }
        public void onResponse(final PrintSlipModel bool) {
            if (this.mVisitEnterActivityNew.get() != null) {
                final AlertDialogBuilder visitEnterActivityNew = this.mVisitEnterActivityNew.get();
                Intrinsics.checkNotNull(visitEnterActivityNew);
                if (visitEnterActivityNew.isActivityDestroyed()) {
                    return;
                }
                final AlertDialogBuilder visitEnterActivityNew2 = this.mVisitEnterActivityNew.get();
                Intrinsics.checkNotNull(visitEnterActivityNew2);
                Intrinsics.checkNotNull(bool);
                visitEnterActivityNew2.onSaveResult(bool.booleanValue(), "");
            }
        }
        public void onFailure(final Throwable throwable) {
            Intrinsics.checkNotNullParameter(throwable, "throwable");
        }
        public void onError(final String errorMessage) {
            Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
            Log.d("AA", "onError: " + errorMessage);
            if (this.mVisitEnterActivityNew.get() == null) {
                return;
            }
            final AlertDialogBuilder visitEnterActivityNew = this.mVisitEnterActivityNew.get();
            Intrinsics.checkNotNull(visitEnterActivityNew);
            if (visitEnterActivityNew.isActivityDestroyed()) {
                return;
            }
            final AlertDialogBuilder visitEnterActivityNew2 = this.mVisitEnterActivityNew.get();
            Intrinsics.checkNotNull(visitEnterActivityNew2);
            visitEnterActivityNew2.onSaveResult(false, errorMessage);
        }

        public void setmVisitEnterActivityNew(ActivityModule_ProvideAlertDialogBuilderFactory mVisitEnterActivityNew) {
            this.mVisitEnterActivityNew = mVisitEnterActivityNew;
        }
    }
    private VisitEnterActivityNew get() {
        return null;
    }
}




