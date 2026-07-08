package com.proje.mobilesales.features.customer.view.operation;

import android.R;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.*;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.reportparser.Report;
import com.proje.mobilesales.core.reportparser.ReportConstParamProp;
import com.proje.mobilesales.core.reportparser.ReportDesignerParser;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.widget.TintableTextView;
import com.proje.mobilesales.features.activity.MainActivity;
import com.proje.mobilesales.features.adapter.IListRecyclerView;
import com.proje.mobilesales.features.cabinoperation.view.activity.CabinListActivity;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.collections.receipt.view.activity.ReceiptFicheListActivity;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.customer.repository.CustomerOperationRepository;
import com.proje.mobilesales.features.model.CustomerReports;
import com.proje.mobilesales.features.penetration.view.list.PenetrationListActivity;
import com.proje.mobilesales.features.reports.util.ReportUtil;
import com.proje.mobilesales.features.reports.view.activity.*;
import com.proje.mobilesales.features.sales.view.list.SalesListActivity;
import com.proje.mobilesales.features.userreport.model.database.UserReports;
import com.proje.mobilesales.features.userreport.view.activity.UserReportsActivity;
import com.proje.mobilesales.features.userreport.view.activity.UserReportsFormActivity;
import com.proje.mobilesales.features.userreport.view.activity.UserReportsGridActivity;
import com.proje.mobilesales.features.visit.view.activity.VisitListActivityNew;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static com.proje.mobilesales.core.utils.AppUtils.createLayoutInflater;

public final class CustomerOperationRecyclerViewAdapter extends RecyclerView.Adapter<CustomerOperationRecyclerViewAdapter.ItemViewHolder> implements IListRecyclerView {
    public static final Companion Companion = new Companion(null);
    private static final String STATE_CUSTOMER_OPERATION_LIST = "state:customerOperation";
    private static final String STATE_CUSTOMER_REF = "state:customerRef";
    private static final String STATE_LAST_CUSTOMER_MENU = "state:lastCustomerMenu";
    private static final String STATE_SUB_MENU = "state:subMenu";
    private static final String TAG = "CustomerOperationRecyclerViewAdapter";
    private int cabinRef;
    private String loadingText;
    private AlertDialogBuilder<?> mAlertDialogBuilder;
    private int mCardBackgroundColorResId;
    private int mCardElevation;
    private int mCardHighlightColorResId;
    private int mCardRadius;
    private boolean mCardViewEnabled;
    private Context mContext;
    private ArrayList<CustomerOperation> mCustomerOperations;
    private int mCustomerRef;
    private CustomerMenuType mLastMenuType;
    private LayoutInflater mLayoutInflater;
    private ProgressDialogBuilder<?> mProgressDialogBuilder;
    private RecyclerView mRecyclerView;
    private int mSecondaryTextColorResId;
    private int mShipRef;
    private int mTertiaryTextColorResId;
    private boolean openSubMenu;
    private final CustomerOperationRepository repository;
    private int routeDayRef;
    private int routePlanRef;
    private int routeUserCustomerRef;
    private final CustomerOperationViewModel viewModel;
 
    public   class WhenMappings {
        public static final  int[] EnumSwitchMapping0;

        static {
            int[] iArr = new int[CustomerMenuType.values().length];
            try {
                iArr[CustomerMenuType.REPORTS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CustomerMenuType.RECIPT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            EnumSwitchMapping0 = iArr;
        }
    }

    public CustomerOperationRecyclerViewAdapter() {
        CustomerOperationRepository customerOperationRepository = new CustomerOperationRepository();
        this.repository = customerOperationRepository;
        this.viewModel = new CustomerOperationViewModel(customerOperationRepository);
        this.mCardViewEnabled = true;
        this.mCustomerOperations = new ArrayList<>();
        this.mLastMenuType = CustomerMenuType.CUSTOMER_MAIN;
    }

    public int getMShipRef() {
        return this.mShipRef;
    }

    public void setMShipRef(int i2) {
        this.mShipRef = i2;
    }

    public int getCabinRef() {
        return this.cabinRef;
    }

    public void setCabinRef(int i2) {
        this.cabinRef = i2;
    }

    public boolean getOpenSubMenu() {
        return this.openSubMenu;
    }

    public void setOpenSubMenu(boolean z) {
        this.openSubMenu = z;
    }

    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        return this.mProgressDialogBuilder;
    }

    public void setMProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        this.mProgressDialogBuilder = progressDialogBuilder;
    }

    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        return this.mAlertDialogBuilder;
    }

    public void setMAlertDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        this.mAlertDialogBuilder = alertDialogBuilder;
    } 
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        Context context = recyclerView.getContext();
        this.mContext = context;
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(context, (BaseInjectableActivity) activity);
        Context context2 = this.mContext;
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilder = new AlertDialogBuilder.Impl(context2, (BaseInjectableActivity) activity2);
        Context context3 = this.mContext;
        if (context3 instanceof BaseInjectableActivity) {
            Intrinsics.checkNotNull(context3, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
            ((BaseInjectableActivity) context3).getActivityComponent().inject(this);
        }
        this.mRecyclerView = recyclerView;
        Context context4 = this.mContext;
        Intrinsics.checkNotNull(context4);
        this.loadingText = context4.getString(R.string.loading_text);
        this.mLayoutInflater = createLayoutInflater(this.mContext);
        Context context5 = this.mContext;
        Intrinsics.checkNotNull(context5);
        this.mCardElevation = context5.getResources().getDimensionPixelSize(R.dimen.cardview_default_elevation);
        Context context6 = this.mContext;
        Intrinsics.checkNotNull(context6);
        this.mCardRadius = context6.getResources().getDimensionPixelSize(R.dimen.cardview_default_radius);
        Context context7 = this.mContext;
        Intrinsics.checkNotNull(context7);
        TypedArray obtainStyledAttributes = context7.obtainStyledAttributes(new int[]{R.attr.textColorTertiary, R.attr.textColorSecondary, R.attr.colorCardBackground, R.attr.colorCardHighlight});
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
        this.mTertiaryTextColorResId = obtainStyledAttributes.getInt(0, 0);
        this.mSecondaryTextColorResId = obtainStyledAttributes.getInt(1, 0);
        this.mCardBackgroundColorResId = obtainStyledAttributes.getInt(2, 0);
        this.mCardHighlightColorResId = obtainStyledAttributes.getInt(3, 0);
        obtainStyledAttributes.recycle();
    }
 
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onDetachedFromRecyclerView(recyclerView);
        this.mRecyclerView = null;
        this.mContext = null;
    }
 
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater layoutInflater = this.mLayoutInflater;
        Intrinsics.checkNotNull(layoutInflater);
        View inflate = layoutInflater.inflate(R.layout.item_customer_operation, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ItemViewHolder(inflate);
    }
 
    public void onBindViewHolder(ItemViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        CustomerOperation customerOperation = getCustomerOperation(i2);
        if (customerOperation == null) {
            return;
        }
        if (this.mCardViewEnabled) {
            holder.getMCardView().setCardElevation(this.mCardElevation);
            holder.getMCardView().setRadius(this.mCardRadius);
            holder.getMCardView().setUseCompatPadding(true);
        } else {
            holder.getMCardView().setCardElevation(0.0f);
            holder.getMCardView().setRadius(0.0f);
            holder.getMCardView().setUseCompatPadding(false);
        }
        clear(holder);
        holder.getMTintTxtOperation().setText(customerOperation.getOperationName());
        holder.getMTintTxtOperation().setCustomerOperationLeftDraw(customerOperation.getOperationDrawableResId());
        if (customerOperation.getHasSubMenu()) {
            holder.getMCardView().setOnClickListener(new View.OnClickListener() { 
                public final  CustomerOperation f1 = null;
                public void onClick(View view) {
                    CustomerOperationRecyclerViewAdapter.onBindViewHolderlambda0(CustomerOperationRecyclerViewAdapter.this, f1, view);
                }
            });
        } else {
            holder.getMCardView().setOnClickListener(new View.OnClickListener() { 
                public final  CustomerOperation f1 = null;
 
                public void onClick(View view) {
                    CustomerOperationRecyclerViewAdapter.onBindViewHolderlambda1(CustomerOperationRecyclerViewAdapter.this, f1, view);
                }
            });
        }
    }

    public static void onBindViewHolderlambda0(CustomerOperationRecyclerViewAdapter this0, CustomerOperation customerOperation, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (!this0.isCustomerAbleToCreateSales() && customerOperation.getMenuType() == CustomerMenuType.SALES) {
            this0.getSalesAuthorizationMessage();
        } else {
            this0.toggleOpenSubMenu(customerOperation);
        }
    }

    public static void onBindViewHolderlambda1(CustomerOperationRecyclerViewAdapter this0, CustomerOperation customerOperation, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (!this0.isCustomerAbleToCreateSales() && customerOperation.getMenuType() == CustomerMenuType.SALES_ORDER) {
            this0.getSalesAuthorizationMessage();
        } else {
            if (this0.canContinueIfEInvoiceOperation(customerOperation)) {
                this0.startActivity(customerOperation);
                return;
            }
            AlertDialogBuilder<?> alertDialogBuilder = this0.mAlertDialogBuilder;
            Intrinsics.checkNotNull(alertDialogBuilder);
            alertDialogBuilder.setMessage(R.string.str_you_cant_create_invoice_for_einvoice_customers).setPositiveButton(R.string.str_okey, null).show();
        }
    }

    private boolean canContinueIfEInvoiceOperation(CustomerOperation customerOperation) {
        return (customerOperation.getSalesType() != SalesType.INVOICE && customerOperation.getSalesType() != SalesType.RETURN_INVOICE && customerOperation.getSalesType() != SalesType.RETAIL_INVOICE && customerOperation.getSalesType() != SalesType.RETAIL_RETURN_INVOICE && customerOperation.getSalesType() != SalesType.ONE_TO_ONE_CHANGE) || this.viewModel.getClEInvoiceUser(this.mCustomerRef) != 1 || this.viewModel.getCanCreateInvoiceForEInvoiceCustomer();
    }
 
    private void startActivity(CustomerOperation customerOperation) {
        Intent intent;
        if (ContextUtils.checkAutoTimeAndTimeZoneEnabledAndShowDialog() && customerOperation.getActivity() != null) {
            MainActivity.sFicheRef = -1;
            customerOperation.setFicheMode(FicheMode.NEW);
            if (Intrinsics.areEqual(customerOperation.getActivity(), UserReportsActivity.class)) {
                ReportDesignerParser reportDesignerParser = new ReportDesignerParser();
                CustomerOperationViewModel customerOperationViewModel = this.viewModel;
                Bundle bundle = customerOperation.getBundle();
                Intrinsics.checkNotNull(bundle);
                Report parseReportXml = reportDesignerParser.parseReportXml(customerOperationViewModel.getReportXML(bundle.getInt("UserReportId")));
                if (parseReportXml == null) {
                    Context context = this.mContext;
                    Intrinsics.checkNotNull(context);
                    Toast.makeText(context, context.getString(R.string.exp_84_unsupported_report_type), 0).show();
                    return;
                }
                int saveObject = this.viewModel.getSaveObject(parseReportXml);
                Context context2 = this.mContext;
                ReportConstParamProp reportConstParamProp = ReportConstParamProp.Cari;
                intent = new Intent(context2, ReportUtil.getReportFromType(parseReportXml, reportConstParamProp, this.mCustomerRef));
                ComponentName component = intent.getComponent();
                Intrinsics.checkNotNull(component);
                if (!Intrinsics.areEqual(component.getClassName(), UserReportsGridActivity.class.getName())) {
                    ComponentName component2 = intent.getComponent();
                    Intrinsics.checkNotNull(component2);
                    if (!Intrinsics.areEqual(component2.getClassName(), UserReportsFormActivity.class.getName())) {
                        ComponentName component3 = intent.getComponent();
                        Intrinsics.checkNotNull(component3);
                    }
                }
                intent.putExtra("ReportConstParamProp", reportConstParamProp);
                intent.putExtra("bigdata:synccode", saveObject);
                intent.putExtra("ReportName", customerOperation.getOperationName());
                intent.putExtra("ShowMailButton", true);
            } else {
                intent = new Intent(this.mContext, customerOperation.getActivity());
            }
            intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF, this.mCustomerRef);
            intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE, customerOperation);
            intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_SHIPREF, this.mShipRef);
            intent.putExtra(IntentExtraName.EXTRA_ROUTEPLAN_REF, this.routePlanRef);
            intent.putExtra(IntentExtraName.EXTRA_ROUTEDAY_REF, this.routeDayRef);
            intent.putExtra(IntentExtraName.EXTRA_ROUTEUSERCUSTOMER_REF, this.routeUserCustomerRef);
            intent.putExtra(IntentExtraName.EXTRA_CABIN_REF, this.cabinRef);
            Context context3 = this.mContext;
            if (context3 instanceof Activity) {
                Intrinsics.checkNotNull(context3, "null cannot be cast to non-null type android.app.Activity");
                ((Activity) context3).startActivityForResult(intent, IntentExtraName.CUSTOMER_OPERATION_REQUEST_CODE);
            } else {
                Intrinsics.checkNotNull(context3);
                context3.startActivity(intent);
            }
        }
    }

    private void clear(ItemViewHolder itemViewHolder) {
        itemViewHolder.getMTintTxtOperation().setText(this.loadingText);
    }

    private CustomerOperation getCustomerOperation(int i2) {
        ArrayList<CustomerOperation> arrayList = this.mCustomerOperations;
        if (arrayList != null) {
            Intrinsics.checkNotNull(arrayList);
            if (arrayList.size() != 0) {
                ArrayList<CustomerOperation> arrayList2 = this.mCustomerOperations;
                Intrinsics.checkNotNull(arrayList2);
                return arrayList2.get(i2);
            }
        }
        return null;
    } 
    public int getItemCount() {
        ArrayList<CustomerOperation> arrayList = this.mCustomerOperations;
        if (arrayList == null) {
            return 0;
        }
        Intrinsics.checkNotNull(arrayList);
        return arrayList.size();
    } 
    public boolean isCardViewEnabled() {
        return this.mCardViewEnabled;
    } 
    public void setCardViewEnabled(boolean z) {
        this.mCardViewEnabled = z;
    } 
    public void restoreState(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.mCardViewEnabled = bundle.getBoolean(IListRecyclerView.STATE_CARD_VIEW_ENABLED);
        ArrayList<CustomerOperation> parcelableArrayList = bundle.getParcelableArrayList(STATE_CUSTOMER_OPERATION_LIST);
        Intrinsics.checkNotNull(parcelableArrayList);
        this.mCustomerOperations = parcelableArrayList;
        this.mCustomerRef = bundle.getInt("state:customerRef");
        this.openSubMenu = bundle.getBoolean(STATE_SUB_MENU);
        Serializable serializable = bundle.getSerializable(STATE_LAST_CUSTOMER_MENU);
        Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.proje.mobilesales.core.enums.CustomerMenuType");
        this.mLastMenuType = (CustomerMenuType) serializable;
    } 
    public Bundle saveState() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(IListRecyclerView.STATE_CARD_VIEW_ENABLED, this.mCardViewEnabled);
        bundle.putParcelableArrayList(STATE_CUSTOMER_OPERATION_LIST, this.mCustomerOperations);
        bundle.putInt("state:customerRef", this.mCustomerRef);
        bundle.putSerializable(STATE_LAST_CUSTOMER_MENU, this.mLastMenuType);
        loadMenu(this.mLastMenuType);
        return bundle;
    }

    public boolean toggleOpenSubMenu(CustomerOperation customerOperation) {
        if (!ContextUtils.checkAutoTimeAndTimeZoneEnabledAndShowDialog()) {
            return false;
        }
        boolean z = this.openSubMenu;
        if (!z && customerOperation != null) {
            CustomerMenuType menuType = customerOperation.getMenuType();
            int i2 = menuType == null ? -1 : WhenMappings.EnumSwitchMapping0[menuType.ordinal()];
            if (i2 == 1) {
                ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
                Intrinsics.checkNotNull(progressDialogBuilder);
                Context context = this.mContext;
                Intrinsics.checkNotNull(context);
                progressDialogBuilder.setMessage(context.getString(R.string.str_please_wait)).show();
                CustomerOperationViewModel customerOperationViewModel = this.viewModel;
                WorkTimeControlProcessType workTimeControlProcessType = WorkTimeControlProcessType.Report;
                CustomerMenuType menuType2 = customerOperation.getMenuType();
                Intrinsics.checkNotNull(menuType2);
                customerOperationViewModel.getCheckRemoteWorkTimeControl(workTimeControlProcessType, new CheckWorkTimeListener(this, menuType2));
            } else if (i2 == 2) {
                ProgressDialogBuilder<?> progressDialogBuilder2 = this.mProgressDialogBuilder;
                Intrinsics.checkNotNull(progressDialogBuilder2);
                Context context2 = this.mContext;
                Intrinsics.checkNotNull(context2);
                progressDialogBuilder2.setMessage(context2.getString(R.string.str_please_wait)).show();
                CustomerOperationViewModel customerOperationViewModel2 = this.viewModel;
                WorkTimeControlProcessType workTimeControlProcessType2 = WorkTimeControlProcessType.Receipt;
                CustomerMenuType menuType3 = customerOperation.getMenuType();
                Intrinsics.checkNotNull(menuType3);
                customerOperationViewModel2.getCheckRemoteWorkTimeControl(workTimeControlProcessType2, new CheckWorkTimeListener(this, menuType3));
            } else {
                this.openSubMenu = true;
                CustomerMenuType menuType4 = customerOperation.getMenuType();
                Intrinsics.checkNotNull(menuType4);
                this.mLastMenuType = menuType4;
                loadMenu(customerOperation.getMenuType());
            }
        } else {
            if (!z) {
                return false;
            }
            this.openSubMenu = false;
            CustomerMenuType customerMenuType = CustomerMenuType.CUSTOMER_MAIN;
            this.mLastMenuType = customerMenuType;
            loadMenu(customerMenuType);
        }
        return true;
    }

    private record CheckWorkTimeListener(WeakReference<CustomerOperationRecyclerViewAdapter> mAdapter,
                                         CustomerMenuType mCustomerMenuType) implements ResponseListener<String> {
            public class WhenMappings {
                public static final int[] EnumSwitchMapping0;

                static {
                    int[] iArr = new int[CustomerMenuType.values().length];
                    try {
                        iArr[CustomerMenuType.REPORTS.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[CustomerMenuType.RECIPT.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    EnumSwitchMapping0 = iArr;
                }
            }

            private CheckWorkTimeListener(CustomerOperationRecyclerViewAdapter mAdapter, CustomerMenuType mCustomerMenuType) {
                Intrinsics.checkNotNullParameter(mAdapter, "customerOperationRecyclerViewAdapter");
                Intrinsics.checkNotNullParameter(mCustomerMenuType, "menuType");
                this.mAdapter = new WeakReference<>(mAdapter);
                this.mCustomerMenuType = mCustomerMenuType;
            }

        public void onResponse(PrintSlipModel str) {
                if (this.mAdapter.get() != null) {
                    CustomerOperationRecyclerViewAdapter customerOperationRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(customerOperationRecyclerViewAdapter);
                    if (customerOperationRecyclerViewAdapter.mContext != null) {
                        CustomerOperationRecyclerViewAdapter customerOperationRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(customerOperationRecyclerViewAdapter2);
                        ProgressDialogBuilder<?> mProgressDialogBuilder = customerOperationRecyclerViewAdapter2.getMProgressDialogBuilder();
                        Intrinsics.checkNotNull(mProgressDialogBuilder);
                        mProgressDialogBuilder.dismiss();
                        if (!TextUtils.isEmpty(str)) {
                            CustomerOperationRecyclerViewAdapter customerOperationRecyclerViewAdapter3 = this.mAdapter.get();
                            Intrinsics.checkNotNull(customerOperationRecyclerViewAdapter3);
                            Toast.makeText(customerOperationRecyclerViewAdapter3.mContext, str, 0).show();
                            return;
                        }
                        int i2 = WhenMappings.EnumSwitchMapping0[this.mCustomerMenuType.ordinal()];
                        if (i2 == 1 || i2 == 2) {
                            CustomerOperationRecyclerViewAdapter customerOperationRecyclerViewAdapter4 = this.mAdapter.get();
                            Intrinsics.checkNotNull(customerOperationRecyclerViewAdapter4);
                            customerOperationRecyclerViewAdapter4.setOpenSubMenu(true);
                            CustomerOperationRecyclerViewAdapter customerOperationRecyclerViewAdapter5 = this.mAdapter.get();
                            Intrinsics.checkNotNull(customerOperationRecyclerViewAdapter5);
                            customerOperationRecyclerViewAdapter5.mLastMenuType = this.mCustomerMenuType;
                            CustomerOperationRecyclerViewAdapter customerOperationRecyclerViewAdapter6 = this.mAdapter.get();
                            Intrinsics.checkNotNull(customerOperationRecyclerViewAdapter6);
                            customerOperationRecyclerViewAdapter6.loadMenu(this.mCustomerMenuType);
                            return;
                        }
                        Log.e(CustomerOperationRecyclerViewAdapter.TAG, "onResponse");
                    }
                }
            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mAdapter.get() != null) {
                    CustomerOperationRecyclerViewAdapter customerOperationRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(customerOperationRecyclerViewAdapter);
                    if (customerOperationRecyclerViewAdapter.mContext != null) {
                        CustomerOperationRecyclerViewAdapter customerOperationRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(customerOperationRecyclerViewAdapter2);
                        ProgressDialogBuilder<?> mProgressDialogBuilder = customerOperationRecyclerViewAdapter2.getMProgressDialogBuilder();
                        Intrinsics.checkNotNull(mProgressDialogBuilder);
                        mProgressDialogBuilder.dismiss();
                    }
                }
            }
        }

    public void loadMenu(CustomerMenuType customerMenuType) {
        ArrayList<CustomerOperation> arrayList = this.mCustomerOperations;
        if (arrayList != null) {
            arrayList.clear();
        }
        notifyDataSetChanged();
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.removeAllViews();
        }
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.invalidate();
        }
        if (customerMenuType == CustomerMenuType.CUSTOMER_MAIN) {
            customerMenuList();
        } else if (customerMenuType == CustomerMenuType.REPORTS) {
            createReportsList();
        } else if (customerMenuType == CustomerMenuType.RECIPT) {
            createReceiptList();
        } else if (customerMenuType == CustomerMenuType.SALES) {
            createSalesList();
        }
        notifyDataSetChanged();
        RecyclerView recyclerView3 = this.mRecyclerView;
        if (recyclerView3 != null) {
            recyclerView3.invalidate();
        }
    }

    private void customerMenuList() {
        if (this.viewModel.getIsVisit()) {
            ArrayList<CustomerOperation> arrayList = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList);
            Context context = this.mContext;
            Intrinsics.checkNotNull(context);
            arrayList.add(new CustomerOperation(context.getString(R.string.str_visit), R.drawable.ic_file_chart_grey600_24dp, VisitListActivityNew.class, false, null, null, null, null, FicheType.VISIT, null, 736, null));
        }
        if (this.viewModel.getIsPenetration()) {
            ArrayList<CustomerOperation> arrayList2 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList2);
            Context context2 = this.mContext;
            Intrinsics.checkNotNull(context2);
            arrayList2.add(new CustomerOperation(context2.getString(R.string.str_customer_penetration), R.drawable.ic_file_chart_grey600_24dp, PenetrationListActivity.class, false, null, null, null, null, FicheType.PENETRATION, null, 736, null));
        }
        if (this.viewModel.getIsCustomerReport()) {
            ArrayList<CustomerOperation> arrayList3 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList3);
            Context context3 = this.mContext;
            Intrinsics.checkNotNull(context3);
            arrayList3.add(new CustomerOperation(context3.getString(R.string.activity_title_report), R.drawable.ic_file_chart_grey600_24dp, null, true, CustomerMenuType.REPORTS, null, null, null, null, null, 992, null));
        }
        if (this.viewModel.getIsReceipt()) {
            ArrayList<CustomerOperation> arrayList4 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList4);
            Context context4 = this.mContext;
            Intrinsics.checkNotNull(context4);
            arrayList4.add(new CustomerOperation(context4.getString(R.string.str_collections), R.drawable.ic_cart_plus_grey600_24dp, null, true, CustomerMenuType.RECIPT, null, null, null, null, null, 992, null));
        }
        if (this.viewModel.getIsSales()) {
            ArrayList<CustomerOperation> arrayList5 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList5);
            Context context5 = this.mContext;
            Intrinsics.checkNotNull(context5);
            arrayList5.add(new CustomerOperation(context5.getString(R.string.str_sales), R.drawable.ic_cart_plus_grey600_24dp, null, true, CustomerMenuType.SALES, null, null, null, null, null, 992, null));
        }
        if (this.viewModel.getIsOrder()) {
            ArrayList<CustomerOperation> arrayList6 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList6);
            Context context6 = this.mContext;
            Intrinsics.checkNotNull(context6);
            arrayList6.add(new CustomerOperation(context6.getString(R.string.str_customer_sales_order), R.drawable.ic_cart_plus_grey600_24dp, SalesListActivity.class, false, CustomerMenuType.SALES_ORDER, SalesType.ORDER, null, null, FicheType.ORDER, null, TypedValues.TransitionType.TYPE_AUTO_TRANSITION, null));
        }
        ArrayList<CustomerOperation> arrayList7 = this.mCustomerOperations;
        Intrinsics.checkNotNull(arrayList7);
        Context context7 = this.mContext;
        Intrinsics.checkNotNull(context7);
        arrayList7.add(new CustomerOperation(context7.getString(R.string.str_cabin_operations), R.drawable.ic_file_chart_grey600_24dp, CabinListActivity.class, false, null, null, null, null, FicheType.CABIN, null, 736, null));
    }

    private void createReceiptList() {
        if (this.viewModel.getIsCash()) {
            ArrayList<CustomerOperation> arrayList = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList);
            Context context = this.mContext;
            Intrinsics.checkNotNull(context);
            arrayList.add(new CustomerOperation(context.getString(R.string.str_cash_receipt), 0, ReceiptFicheListActivity.class, false, null, null, ReceiptType.CASH, null, FicheType.CASH, null, 698, null));
        }
        if (this.viewModel.getIsCase()) {
            ArrayList<CustomerOperation> arrayList2 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList2);
            Context context2 = this.mContext;
            Intrinsics.checkNotNull(context2);
            arrayList2.add(new CustomerOperation(context2.getString(R.string.str_case_receipt), 0, ReceiptFicheListActivity.class, false, null, null, ReceiptType.CASE, null, FicheType.CASE_CASH, null, 698, null));
        }
        if (this.viewModel.getIsCreditCard()) {
            ArrayList<CustomerOperation> arrayList3 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList3);
            Context context3 = this.mContext;
            Intrinsics.checkNotNull(context3);
            arrayList3.add(new CustomerOperation(context3.getString(R.string.str_credit_cart_receipt), 0, ReceiptFicheListActivity.class, false, null, null, ReceiptType.CREDIT, null, FicheType.CREDIT_CART, null, 698, null));
        }
        if (this.viewModel.getIsCheque()) {
            ArrayList<CustomerOperation> arrayList4 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList4);
            Context context4 = this.mContext;
            Intrinsics.checkNotNull(context4);
            arrayList4.add(new CustomerOperation(context4.getString(R.string.str_cheque_receipt), 0, ReceiptFicheListActivity.class, false, null, null, ReceiptType.CHEQUE, null, FicheType.CHEQUE, null, 698, null));
        }
        if (this.viewModel.getIsDeed()) {
            ArrayList<CustomerOperation> arrayList5 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList5);
            Context context5 = this.mContext;
            Intrinsics.checkNotNull(context5);
            arrayList5.add(new CustomerOperation(context5.getString(R.string.str_bill_receipt), 0, ReceiptFicheListActivity.class, false, null, null, ReceiptType.DEED, null, FicheType.DEED, null, 698, null));
        }
    }

    private void createSalesList() {
        if (this.viewModel.getIsInvoice()) {
            ArrayList<CustomerOperation> arrayList = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList);
            Context context = this.mContext;
            Intrinsics.checkNotNull(context);
            arrayList.add(new CustomerOperation(context.getString(R.string.str_invoice), 0, SalesListActivity.class, false, CustomerMenuType.SALES_INVOICE, SalesType.INVOICE, null, null, FicheType.INVOICE, null, 714, null));
        }
        if (this.viewModel.getIsReturnInvoice()) {
            ArrayList<CustomerOperation> arrayList2 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList2);
            Context context2 = this.mContext;
            Intrinsics.checkNotNull(context2);
            arrayList2.add(new CustomerOperation(context2.getString(R.string.str_return_invoice), 0, SalesListActivity.class, false, CustomerMenuType.SALES_RETURN_INVOICE, SalesType.RETURN_INVOICE, null, null, FicheType.INVOICE, null, 714, null));
        }
        if (this.viewModel.getIsRetailInvoice()) {
            ArrayList<CustomerOperation> arrayList3 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList3);
            Context context3 = this.mContext;
            Intrinsics.checkNotNull(context3);
            String string = context3.getString(R.string.str_sales_retail_invoice);
            CustomerMenuType customerMenuType = CustomerMenuType.SALES_RETAIL_INVOICE;
            SalesType salesType = SalesType.RETAIL_INVOICE;
            FicheType ficheType = FicheType.RETAILINVOICE;
            arrayList3.add(new CustomerOperation(string, 0, SalesListActivity.class, false, customerMenuType, salesType, null, null, ficheType, null, 714, null));
            ArrayList<CustomerOperation> arrayList4 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList4);
            Context context4 = this.mContext;
            Intrinsics.checkNotNull(context4);
            arrayList4.add(new CustomerOperation(context4.getString(R.string.str_sales_retail_return_invoice), 0, SalesListActivity.class, false, CustomerMenuType.SALES_RETAIL_RETURN_INVOICE, SalesType.RETAIL_RETURN_INVOICE, null, null, ficheType, null, 714, null));
        }
        if (this.viewModel.getIsDispatch()) {
            ArrayList<CustomerOperation> arrayList5 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList5);
            Context context5 = this.mContext;
            Intrinsics.checkNotNull(context5);
            arrayList5.add(new CustomerOperation(context5.getString(R.string.str_dispatch), 0, SalesListActivity.class, false, CustomerMenuType.SALES_DISPATCH, SalesType.DISPATCH, null, null, FicheType.DISPATCH, null, 714, null));
        }
        if (this.viewModel.getIsReturnDispatch()) {
            ArrayList<CustomerOperation> arrayList6 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList6);
            Context context6 = this.mContext;
            Intrinsics.checkNotNull(context6);
            arrayList6.add(new CustomerOperation(context6.getString(R.string.str_return_dispatch), 0, SalesListActivity.class, false, CustomerMenuType.SALES_RETURN_DISPATCH, SalesType.RETURN_DISPATCH, null, null, FicheType.DISPATCH, null, 714, null));
        }
        if (this.viewModel.getIsOneToOneChange()) {
            ArrayList<CustomerOperation> arrayList7 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList7);
            Context context7 = this.mContext;
            Intrinsics.checkNotNull(context7);
            arrayList7.add(new CustomerOperation(context7.getString(R.string.str_one_to_one_change), 0, SalesListActivity.class, false, CustomerMenuType.SALES_ONE_TO_ONE_CHANGE, SalesType.ONE_TO_ONE_CHANGE, null, null, this.viewModel.erpType() == ErpType.NETSIS ? FicheType.INVOICE : FicheType.DISPATCH, null, 714, null));
        }
    }

    private boolean isCustomerAbleToCreateSales() {
        List<ClCard> tableForCustomers = this.viewModel.getTableForCustomers("LOGICALREF=?", new String[]{String.valueOf(this.mCustomerRef)});
        return this.viewModel.erpType() != ErpType.TIGER || tableForCustomers.isEmpty() || tableForCustomers.get(0).getSalesDistribution() == CustomerSalesAuthorization.AUTHORIZED_FOR_SALES.getMValue();
    }

    private void getSalesAuthorizationMessage() {
        AlertDialogBuilder<?> alertDialogBuilder = this.mAlertDialogBuilder;
        Intrinsics.checkNotNull(alertDialogBuilder);
        Context context = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context);
        alertDialogBuilder.setMessage(context.getString(R.string.exp_customer_sales_authorization, this.viewModel.getSqlHelper().getClName(this.mCustomerRef))).setPositiveButton(R.string.str_okey, null).show();
    }

    private void addIf(List<CustomerOperation> list, boolean z, CustomerOperation customerOperation) {
        if (z) {
            list.add(customerOperation);
        }
    }

    private void createReportsList() {
        for (UserReports userReports : this.viewModel.getTableForUserReports(UserReports.class, "RTYPE=?", new String[]{"5"}, null, null, "ID DESC")) {
            Bundle bundle = new Bundle();
            bundle.putInt("UserReportId", userReports.f1283id);
            ArrayList<CustomerOperation> arrayList = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList);
            String str = userReports.reportName;
            Intrinsics.checkNotNull(str);
            arrayList.add(new CustomerOperation(str, UserReportsActivity.class, bundle));
        }
        CustomerReports customerReports = this.viewModel.getCustomerReports();
        ArrayList<CustomerOperation> arrayList2 = this.mCustomerOperations;
        Intrinsics.checkNotNull(arrayList2);
        boolean isReportEnabled = customerReports.isReportEnabled(CustomerReportNames.ACCOUNT_EXTRACT_REPORT);
        Context context = this.mContext;
        Intrinsics.checkNotNull(context);
        String string = context.getString(R.string.str_account_extract);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        addIf(arrayList2, isReportEnabled, new CustomerOperation(string, ReportExtractActivity.class, CustomerMenuType.FILLREPORTEXTRACT));
        if (this.viewModel.erpType() == ErpType.GO || this.viewModel.erpType() == ErpType.TIGER) {
            ArrayList<CustomerOperation> arrayList3 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList3);
            boolean isReportEnabled2 = customerReports.isReportEnabled(CustomerReportNames.DEBT_TACKING_REPORT);
            Context context2 = this.mContext;
            Intrinsics.checkNotNull(context2);
            String string2 = context2.getString(R.string.str_debt_tracking);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            addIf(arrayList3, isReportEnabled2, new CustomerOperation(string2, ReportWcfDebitFollowActivity.class, CustomerMenuType.FILLREPORTDEBITFOLLOW));
            ArrayList<CustomerOperation> arrayList4 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList4);
            boolean isReportEnabled3 = customerReports.isReportEnabled(CustomerReportNames.DETAILED_SLIP_LIST_REPORT);
            Context context3 = this.mContext;
            Intrinsics.checkNotNull(context3);
            String string3 = context3.getString(R.string.str_detailed_slip_list);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
            addIf(arrayList4, isReportEnabled3, new CustomerOperation(string3, ReportWcfCollectionsListActivity.class, CustomerMenuType.FILLREPORTCOLLECTIONSLIST));
            ArrayList<CustomerOperation> arrayList5 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList5);
            boolean isReportEnabled4 = customerReports.isReportEnabled(CustomerReportNames.ORDER_REPORT);
            Context context4 = this.mContext;
            Intrinsics.checkNotNull(context4);
            String string4 = context4.getString(R.string.str_order_report);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
            addIf(arrayList5, isReportEnabled4, new CustomerOperation(string4, ReportWcfOrderListActivity.class, CustomerMenuType.FILLREPORTORDERLIST));
            ArrayList<CustomerOperation> arrayList6 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList6);
            boolean isReportEnabled5 = customerReports.isReportEnabled(CustomerReportNames.INVOICE_AVERAGE_EXPIRY_REPORT);
            Context context5 = this.mContext;
            Intrinsics.checkNotNull(context5);
            String string5 = context5.getString(R.string.str_average_invoice_expiry);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
            addIf(arrayList6, isReportEnabled5, new CustomerOperation(string5, ReportWcfInvoiceAvgTimeActivity.class, CustomerMenuType.FILLREPORTINVOICEAVGTIME));
            ArrayList<CustomerOperation> arrayList7 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList7);
            boolean isReportEnabled6 = customerReports.isReportEnabled(CustomerReportNames.AVERAGE_DATE_REPORT);
            Context context6 = this.mContext;
            Intrinsics.checkNotNull(context6);
            String string6 = context6.getString(R.string.str_average_date_report);
            Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
            addIf(arrayList7, isReportEnabled6, new CustomerOperation(string6, ReportWcfAvgCalTimeActivity.class, CustomerMenuType.FILLREPORTAVGCALC));
            ArrayList<CustomerOperation> arrayList8 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList8);
            boolean isReportEnabled7 = customerReports.isReportEnabled(CustomerReportNames.SALES_REPORT_ORDER);
            Context context7 = this.mContext;
            Intrinsics.checkNotNull(context7);
            String string7 = context7.getString(R.string.str_sales_report_order);
            Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
            addIf(arrayList8, isReportEnabled7, new CustomerOperation(string7, ReportWCFSalesOrdInvActivity.class, CustomerMenuType.FILLREPORTSALESORD));
            ArrayList<CustomerOperation> arrayList9 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList9);
            boolean isReportEnabled8 = customerReports.isReportEnabled(CustomerReportNames.SALES_REPORT_INVOICE);
            Context context8 = this.mContext;
            Intrinsics.checkNotNull(context8);
            String string8 = context8.getString(R.string.str_sales_report_invoice);
            Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
            addIf(arrayList9, isReportEnabled8, new CustomerOperation(string8, ReportWCFSalesOrdInvActivity.class, CustomerMenuType.FILLREPORTSALESINV));
            ArrayList<CustomerOperation> arrayList10 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList10);
            boolean isReportEnabled9 = customerReports.isReportEnabled(CustomerReportNames.ORDERS_REPORT);
            Context context9 = this.mContext;
            Intrinsics.checkNotNull(context9);
            String string9 = context9.getString(R.string.str_orders);
            Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
            addIf(arrayList10, isReportEnabled9, new CustomerOperation(string9, ReportWCFAllReportActivity.class, CustomerMenuType.FILLREPORTORDER));
            ArrayList<CustomerOperation> arrayList11 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList11);
            boolean isReportEnabled10 = customerReports.isReportEnabled(CustomerReportNames.INVOICES_REPORT);
            Context context10 = this.mContext;
            Intrinsics.checkNotNull(context10);
            String string10 = context10.getString(R.string.str_invoices);
            Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
            addIf(arrayList11, isReportEnabled10, new CustomerOperation(string10, ReportWCFAllReportActivity.class, CustomerMenuType.FILLREPORTINVOICE));
            ArrayList<CustomerOperation> arrayList12 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList12);
            boolean isReportEnabled11 = customerReports.isReportEnabled(CustomerReportNames.REFUND_INVOICES_REPORT);
            Context context11 = this.mContext;
            Intrinsics.checkNotNull(context11);
            String string11 = context11.getString(R.string.str_refund_invoices);
            Intrinsics.checkNotNullExpressionValue(string11, "getString(...)");
            addIf(arrayList12, isReportEnabled11, new CustomerOperation(string11, ReportWCFAllReportActivity.class, CustomerMenuType.FILLREPORTRETURNINVOICE));
            ArrayList<CustomerOperation> arrayList13 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList13);
            boolean isReportEnabled12 = customerReports.isReportEnabled(CustomerReportNames.CASH_COLLECTIONS_REPORT);
            Context context12 = this.mContext;
            Intrinsics.checkNotNull(context12);
            String string12 = context12.getString(R.string.str_cash_collections);
            Intrinsics.checkNotNullExpressionValue(string12, "getString(...)");
            addIf(arrayList13, isReportEnabled12, new CustomerOperation(string12, ReportWCFAllReportActivity.class, CustomerMenuType.FILLREPORTCASH));
            ArrayList<CustomerOperation> arrayList14 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList14);
            boolean isReportEnabled13 = customerReports.isReportEnabled(CustomerReportNames.CREDIT_CARD_COLLECTIONS_REPORT);
            Context context13 = this.mContext;
            Intrinsics.checkNotNull(context13);
            String string13 = context13.getString(R.string.str_credit_cart_collections);
            Intrinsics.checkNotNullExpressionValue(string13, "getString(...)");
            addIf(arrayList14, isReportEnabled13, new CustomerOperation(string13, ReportWCFAllReportActivity.class, CustomerMenuType.FILLREPORTCREDIT));
            ArrayList<CustomerOperation> arrayList15 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList15);
            boolean isReportEnabled14 = customerReports.isReportEnabled(CustomerReportNames.CASE_COLLECTIONS_REPORT);
            Context context14 = this.mContext;
            Intrinsics.checkNotNull(context14);
            String string14 = context14.getString(R.string.str_case_collections);
            Intrinsics.checkNotNullExpressionValue(string14, "getString(...)");
            addIf(arrayList15, isReportEnabled14, new CustomerOperation(string14, ReportWCFAllReportActivity.class, CustomerMenuType.FILLREPORTCASHCASE));
            ArrayList<CustomerOperation> arrayList16 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList16);
            boolean isReportEnabled15 = customerReports.isReportEnabled(CustomerReportNames.CHECK_COLLECTIONS_REPORT);
            Context context15 = this.mContext;
            Intrinsics.checkNotNull(context15);
            String string15 = context15.getString(R.string.str_check_collections);
            Intrinsics.checkNotNullExpressionValue(string15, "getString(...)");
            addIf(arrayList16, isReportEnabled15, new CustomerOperation(string15, ReportWCFAllReportActivity.class, CustomerMenuType.FILLREPORTCHEQUE));
            ArrayList<CustomerOperation> arrayList17 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList17);
            boolean isReportEnabled16 = customerReports.isReportEnabled(CustomerReportNames.PAYROLL_COLLECTIONS_REPORT);
            Context context16 = this.mContext;
            Intrinsics.checkNotNull(context16);
            String string16 = context16.getString(R.string.str_payroll_note_collections);
            Intrinsics.checkNotNullExpressionValue(string16, "getString(...)");
            addIf(arrayList17, isReportEnabled16, new CustomerOperation(string16, ReportWCFAllReportActivity.class, CustomerMenuType.FILLREPORTDEED));
            ArrayList<CustomerOperation> arrayList18 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList18);
            boolean isReportEnabled17 = customerReports.isReportEnabled(CustomerReportNames.VEHICLE_STOCK_STATUS_REPORT);
            Context context17 = this.mContext;
            Intrinsics.checkNotNull(context17);
            String string17 = context17.getString(R.string.str_vehicle_stock_status);
            Intrinsics.checkNotNullExpressionValue(string17, "getString(...)");
            addIf(arrayList18, isReportEnabled17, new CustomerOperation(string17, ReportWCFVehicleStatusActivity.class, CustomerMenuType.FILLREPORTVEHICLESTATUS));
            ArrayList<CustomerOperation> arrayList19 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList19);
            boolean isReportEnabled18 = customerReports.isReportEnabled(CustomerReportNames.SALES_SUMMARY_REPORT);
            Context context18 = this.mContext;
            Intrinsics.checkNotNull(context18);
            String string18 = context18.getString(R.string.str_sales_summary_report);
            Intrinsics.checkNotNullExpressionValue(string18, "getString(...)");
            addIf(arrayList19, isReportEnabled18, new CustomerOperation(string18, ReportWCFSalesSummaryActivity.class, CustomerMenuType.FILLREPORTSALESUMMARY));
            ArrayList<CustomerOperation> arrayList20 = this.mCustomerOperations;
            Intrinsics.checkNotNull(arrayList20);
            boolean isReportEnabled19 = customerReports.isReportEnabled(CustomerReportNames.ORDER_TOTALS_REPORT);
            Context context19 = this.mContext;
            Intrinsics.checkNotNull(context19);
            String string19 = context19.getString(R.string.str_order_totals_report);
            Intrinsics.checkNotNullExpressionValue(string19, "getString(...)");
            addIf(arrayList20, isReportEnabled19, new CustomerOperation(string19, ReportWCFSalesOrdInvActivity.class, CustomerMenuType.FILLREPORTORDERTOTAL));
        }
    }

    public void setmCustomerRef(int i2) {
        this.mCustomerRef = i2;
        loadMenu(CustomerMenuType.CUSTOMER_MAIN);
    }

    public void setRouteRef(int i2, int i3, int i4) {
        this.routeDayRef = i2;
        this.routePlanRef = i3;
        this.routeUserCustomerRef = i4;
    } 
    public static final class ItemViewHolder extends RecyclerView.ViewHolder {
        private final CardView mCardView;
        private final TintableTextView mTintTxtOperation; 
        public ItemViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.mCardView = (CardView) itemView;
            View findViewById = itemView.findViewById(R.id.txt_customer_operation);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type com.proje.mobilesales.core.widget.TintableTextView");
            this.mTintTxtOperation = (TintableTextView) findViewById;
        }

        public   CardView getMCardView() {
            return this.mCardView;
        }

        public TintableTextView getMTintTxtOperation() {
            return this.mTintTxtOperation;
        }
    } 
    public static final class Companion {
        public   Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
