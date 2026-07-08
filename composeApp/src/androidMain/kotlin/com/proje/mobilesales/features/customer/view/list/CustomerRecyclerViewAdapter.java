package com.proje.mobilesales.features.customer.view.list;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.RiskAlert;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.PermissionUtils;
import com.proje.mobilesales.features.activity.ImageViewActivity;
import com.proje.mobilesales.features.adapter.EndlessListRecyclerViewAdapter;
import com.proje.mobilesales.features.adapter.LoadingViewHolder;
import com.proje.mobilesales.features.customer.model.Customer;
import com.proje.mobilesales.features.customer.view.general.CustomerActivity;
import com.proje.mobilesales.features.gpsinfo.view.activity.GpsInfoActivity;
import com.proje.mobilesales.features.visit.model.VisitInfoShort;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;
import java.util.List;

import static com.proje.mobilesales.core.utils.AppUtils.makeContactIntent;
import static com.proje.mobilesales.core.utils.AppUtils.makeEmailIntent;


/* compiled from: CustomerRecyclerViewAdapter.kt */

public final class CustomerRecyclerViewAdapter extends EndlessListRecyclerViewAdapter<RecyclerView.ViewHolder, Customer> {
    public static final Companion Companion = new Companion(null);
    private static final String STATE_DISPLAY_OPTIONS = "state:displayOptions";
    private Activity activity;
    private final List<Customer> customerCursor = new ArrayList();
    private int customerSelectType;
    private AlertDialogBuilder<?> mAlertDialogBuilder;
    private ProgressDialog progressDialog;
    private boolean showCustomerDetail;

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter
    protected void bindItem(final RecyclerView.ViewHolder viewHolder) {
    }

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter
    protected void handleItemClick(final Object obj, final RecyclerView.ViewHolder viewHolder) {
    }

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter
    protected boolean isItemAvailable(final Object obj) {
        return null != obj;
    }

    public List<Customer> getCustomerCursor() {
        return customerCursor;
    }

    public int getCustomerSelectType() {
        return customerSelectType;
    }

    public void setCustomerSelectType(final int i2) {
        customerSelectType = i2;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(final Activity activity) {
        this.activity = activity;
    }

    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        return mAlertDialogBuilder;
    }

    public void setMAlertDialogBuilder(final AlertDialogBuilder<?> alertDialogBuilder) {
        mAlertDialogBuilder = alertDialogBuilder;
    }

    public void setDisplayOptions(final boolean z) {
        showCustomerDetail = z;
    }

    @Override // com.proje.mobilesales.features.adapter.EndlessListRecyclerViewAdapter, com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        final Context context = mContext;
        if (context instanceof BaseInjectableActivity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
            ((BaseInjectableActivity) context).getActivityComponent().inject(this);
        }
        final Context context2 = mContext;
        final Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mAlertDialogBuilder = new AlertDialogBuilder.Impl(context2, (BaseInjectableActivity) activity);
    }

    @Override // com.proje.mobilesales.features.adapter.EndlessListRecyclerViewAdapter, com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(final RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onDetachedFromRecyclerView(recyclerView);
        customerCursor.clear();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (i2 == VIEW_TYPE_ITEM) {
            final View inflate = mInflater.inflate(R.layout.item_customer, parent, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
            return new CustomerViewHolder(inflate);
        }
        return new LoadingViewHolder(mInflater.inflate(R.layout.item_loading, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder instanceof CustomerViewHolder customerViewHolder3) {
            Customer item = this.getItem(i2);
            if (mCardViewEnabled) {
                final CardView cardView = customerViewHolder3.mCardView;
                Intrinsics.checkNotNull(cardView);
                cardView.setCardElevation(mCardElevation);
                customerViewHolder3.mCardView.setRadius(mCardRadius);
                customerViewHolder3.mCardView.setUseCompatPadding(true);
            } else {
                final CardView cardView2 = customerViewHolder3.mCardView;
                Intrinsics.checkNotNull(cardView2);
                cardView2.setCardElevation(0.0f);
                customerViewHolder3.mCardView.setRadius(0.0f);
                customerViewHolder3.mCardView.setUseCompatPadding(false);
            }
            this.clearViewHolder(holder);
            if (!this.isItemAvailable(item)) {
                this.loadItem(holder.getAdapterPosition());
                return;
            }
            customerViewHolder3.mCustomerView.setCustomerShowDetail(showCustomerDetail);
            final CustomerView customerView = customerViewHolder3.mCustomerView;
            Intrinsics.checkNotNull(item);
            customerView.setCustomer(item);
            customerViewHolder3.mCustomerView.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerRecyclerViewAdapterExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public void onClick(final View view) {
                    onBindViewHolderlambda0(CustomerRecyclerViewAdapter.this, item, view);
                }
            });
            customerViewHolder3.mCustomerView.setOnEmailClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerRecyclerViewAdapterExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public void onClick(final View view) {
                    onBindViewHolderlambda1(CustomerRecyclerViewAdapter.this, item, view);
                }
            });
            customerViewHolder3.mCustomerView.setOnTelClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerRecyclerViewAdapterExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public void onClick(final View view) {
                    onBindViewHolderlambda2(CustomerRecyclerViewAdapter.this, item, view);
                }
            });
            customerViewHolder3.mCustomerView.setOnMapClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerRecyclerViewAdapterExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public void onClick(final View view) {
                    onBindViewHolderlambda3(CustomerRecyclerViewAdapter.this, item, view);
                }
            });
            customerViewHolder3.mCustomerView.setOnImageClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerRecyclerViewAdapterExternalSyntheticLambda7
                @Override // android.view.View.OnClickListener
                public void onClick(final View view) {
                    onBindViewHolderlambda4(CustomerRecyclerViewAdapter.this, item, view);
                }
            });
            customerViewHolder3.mCustomerView.setOnCustomerInfoNotListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerRecyclerViewAdapterExternalSyntheticLambda8
                @Override // android.view.View.OnClickListener
                public void onClick(final View view) {
                    onBindViewHolderlambda5(CustomerRecyclerViewAdapter.this, item, view);
                }
            });
            return;
        }
        if (holder instanceof LoadingViewHolder) {
            ((LoadingViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }


    public static void onBindViewHolderlambda0(final CustomerRecyclerViewAdapter this0, final Customer customer, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.openItem(customer);
    }


    public static void onBindViewHolderlambda1(final CustomerRecyclerViewAdapter this0, final Customer customer, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.clickEmail(customer);
    }


    public static void onBindViewHolderlambda2(final CustomerRecyclerViewAdapter this0, final Customer customer, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.clickTel(customer);
    }


    public static void onBindViewHolderlambda3(final CustomerRecyclerViewAdapter this0, final Customer customer, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.openMap(customer);
    }


    public static void onBindViewHolderlambda4(final CustomerRecyclerViewAdapter this0, final Customer customer, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.openImageView(customer);
    }


    public static void onBindViewHolderlambda5(final CustomerRecyclerViewAdapter this0, final Customer customer, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.openInfoNot(customer);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return customerCursor.size();
    }

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter
    protected void clearViewHolder(final RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof CustomerViewHolder customerViewHolder) {
            customerViewHolder.mCustomerView.reset();
            customerViewHolder.itemView.setOnClickListener(null);
            customerViewHolder.itemView.setOnLongClickListener(null);
        }
    }

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter
    protected Customer getItem(final int i2) {
        return customerCursor.get(i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(final int i2) {
        if (null != getItem(i2)) {
            return ErpCreator.getInstance().getmBaseErp().getRouteNewSystem() ? r0.getRouteUserCustomerRef() + r0.getRoutePlanRef() : r0.getLogicalRef();
        }
        return super.getItemId(i2);
    }

    @Override // com.proje.mobilesales.features.adapter.EndlessListRecyclerViewAdapter
    public void insertProgressBar() {
        if (customerCursor.contains(null)) {
            return;
        }
        customerCursor.add(null);
        this.notifyItemInserted(this.getItemCount() - 1);
    }

    @Override // com.proje.mobilesales.features.adapter.EndlessListRecyclerViewAdapter
    public void removeProgressBar() {
        final int indexOf = customerCursor.indexOf(null);
        if (-1 != indexOf) {
            customerCursor.remove(indexOf);
            this.notifyItemRemoved(indexOf);
        }
    }

    @Override // com.proje.mobilesales.features.adapter.EndlessListRecyclerViewAdapter
    public void restartAdapterAndScroll() {
        if (0 < getItemCount()) {
            customerCursor.clear();
        }
        this.notifyDataSetChanged();
    }

    @Override // com.proje.mobilesales.features.adapter.EndlessListRecyclerViewAdapter
    public void addItem(final ArrayList<ArrayList<VisitInfoShort>> list) {
        this.removeProgressBar();
        if (null != list) {
            customerCursor.addAll(list);
            mRecyclerView.getRecycledViewPool().clear();
            this.notifyDataSetChanged();
        }
    }

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter, com.proje.mobilesales.features.adapter.IListRecyclerView
    public Bundle saveState() {
        final Bundle saveState = super.saveState();
        saveState.putBoolean(CustomerRecyclerViewAdapter.STATE_DISPLAY_OPTIONS, showCustomerDetail);
        Intrinsics.checkNotNull(saveState);
        return saveState;
    }

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter, com.proje.mobilesales.features.adapter.IListRecyclerView
    public void restoreState(final Bundle bundle) {
        super.restoreState(bundle);
        if (null != bundle) {
            showCustomerDetail = bundle.getBoolean(CustomerRecyclerViewAdapter.STATE_DISPLAY_OPTIONS);
        }
    }

    public void openFirstCustomer(final int i2) {
        if (0 < this.customerCursor.size()) {
            final Customer customer = customerCursor.get(0);
            Intrinsics.checkNotNull(customer);
            this.checkCustomerSentAllData(customer, i2);
        }
    }

    public void clearScrollListeners() {
        mRecyclerView.clearOnScrollListeners();
    }

    private void openItem(final Customer customer) {
        if (0 > customer.getLogicalRef()) {
            this.checkCustomerUpdateBeforeActivity(customer);
        }
        if (0 == this.customerSelectType) {
            this.checkCustomerSentAllData(customer);
            return;
        }
        final Intent intent = new Intent();
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF, ErpCreator.getInstance().getmBaseErp().getErpType() == ErpType.NETSIS ? ErpCreator.getInstance().getmBaseErp().getCustomerClRef(customer.getCode()) : customer.getLogicalRef());
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_TITLE, customer.getTitle());
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_CODE, customer.getCode());
        if (2 == this.customerSelectType) {
            intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_ITEM, customer);
        }
        final Activity activity = this.activity;
        Intrinsics.checkNotNull(activity);
        activity.setResult(-1, intent);
        final Activity activity2 = this.activity;
        Intrinsics.checkNotNull(activity2);
        activity2.finish();
    }

    private void startCustomerActivity(final Customer customer, final int i2) {
        final Intent putExtra = new Intent(mContext, CustomerActivity.class).putExtra(CustomerActivity.EXTRA_CUSTOMER_REF, customer.getLogicalRef()).putExtra(CustomerActivity.EXTRA_CUSTOMER_CODE, customer.getCode()).putExtra(CustomerActivity.EXTRA_CUSTOMER_NAME, customer.getTitle()).putExtra(CustomerActivity.EXTRA_CUSTOMER_SHIPREF, customer.getShipRef()).putExtra(IntentExtraName.EXTRA_ROUTEDAY_REF, customer.getRouteDayRef()).putExtra(IntentExtraName.EXTRA_ROUTEPLAN_REF, customer.getRoutePlanRef()).putExtra(IntentExtraName.EXTRA_ROUTEUSERCUSTOMER_REF, customer.getRouteUserCustomerRef()).putExtra(CustomerActivity.EXTRA_CUSTOMER_CABIN_REF, i2);
        Intrinsics.checkNotNullExpressionValue(putExtra, "putExtra(...)");
        final Context context = mContext;
        if (context instanceof Activity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            ((Activity) context).startActivityForResult(putExtra, IntentExtraName.CUSTOMER_OPERATION_REQUEST_CODE);
        } else {
            context.startActivity(putExtra);
        }
    }

    private void checkCustomerSentAllData(final Customer customer) {
        this.checkCustomerSentAllData(customer, 0);
    }

    private void checkCustomerSentAllData(Customer customer, int i2) {
        final String customerUnsentDataTypes = ErpCreator.getInstance().getmBaseErp().getCustomerUnsentDataTypes(customer.getLogicalRef());
        Intrinsics.checkNotNull(customerUnsentDataTypes);
        if (0 < customerUnsentDataTypes.length()) {
            if (ErpCreator.getInstance().getmBaseErp().getControlSentDailyInformation() == RiskAlert.NOTIFY) {
                final AlertDialogBuilder<?> alertDialogBuilder = mAlertDialogBuilder;
                Intrinsics.checkNotNull(alertDialogBuilder);
                alertDialogBuilder.setMessage(mContext.getString(R.string.str_customer_unsent_data, customerUnsentDataTypes)).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerRecyclerViewAdapterExternalSyntheticLambda0
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(final DialogInterface dialogInterface, final int i3) {
                        checkCustomerSentAllDatalambda6(dialogInterface, i3);
                    }
                }).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerRecyclerViewAdapterExternalSyntheticLambda1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(final DialogInterface dialogInterface, final int i3) {
                        checkCustomerSentAllDatalambda7(CustomerRecyclerViewAdapter.this, customer, i2, dialogInterface, i3);
                    }
                }).create().show();
                return;
            } else {
                if (ErpCreator.getInstance().getmBaseErp().getControlSentDailyInformation() == RiskAlert.ABORT) {
                    final AlertDialogBuilder<?> alertDialogBuilder2 = mAlertDialogBuilder;
                    Intrinsics.checkNotNull(alertDialogBuilder2);
                    alertDialogBuilder2.setMessage(mContext.getString(R.string.str_customer_unsent_data_not_continue, customerUnsentDataTypes)).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.list.CustomerRecyclerViewAdapterExternalSyntheticLambda2
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(final DialogInterface dialogInterface, final int i3) {
                            checkCustomerSentAllDatalambda8(dialogInterface, i3);
                        }
                    }).create().show();
                    return;
                }
                this.startCustomerActivity(customer, i2);
                return;
            }
        }
        this.startCustomerActivity(customer, i2);
    }


    public static void checkCustomerSentAllDatalambda6(final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }


    public static void checkCustomerSentAllDatalambda7(final CustomerRecyclerViewAdapter this0, final Customer item, final int i2, final DialogInterface dialogInterface, final int i3) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(item, "item");
        this0.startCustomerActivity(item, i2);
    }


    public static void checkCustomerSentAllDatalambda8(final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }

    private void checkCustomerUpdateBeforeActivity(final Customer customer) {
        int i2 = 0;
        final Cursor query = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query("SELECT LOGICALREF FROM CLCARD WHERE CODE = '" + customer.getCode() + '\'');
        if (null != query) {
            try {
                try {
                    if (query.moveToFirst()) {
                        i2 = query.getInt(0);
                    }
                } catch (final Exception e2) {
                    Log.e("CustomerRecyclerView", "CheckCustomerUpdateBeforeActivity: ", e2);
                    if (0 >= i2 || i2 == customer.getLogicalRef()) {
                        return;
                    }
                    customer.setLogicalRef(i2);
                }
            } finally {
                query.close();
            }
        }
    }

    private void openMap(final Customer customer) {
        if (PermissionUtils.checkPermission(activity, "android.permission.ACCESS_FINE_LOCATION", mContext.getString(R.string.str_location_permission_for_customer_on_map))) {
            if (0 == this.customerSelectType) {
                final Context context = mContext;
                final Intent putExtra = new Intent(mContext, GpsInfoActivity.class).putExtra(GpsInfoActivity.EXTRA_CUSTOMER_REF, customer.getLogicalRef());
                final GpsInfoActivity.Companion companion = GpsInfoActivity.Companion;
                context.startActivity(putExtra.putExtra(companion.getEXTRA_CUSTOMER_TITLE(), customer.getTitle()).putExtra(companion.getEXTRA_CUSTOMER_CODE(), customer.getCode()));
                return;
            }
            final Intent intent = new Intent();
            intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF, customer.getLogicalRef());
            intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_TITLE, customer.getTitle());
            intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_CODE, customer.getTitle());
            final Activity activity = this.activity;
            Intrinsics.checkNotNull(activity);
            activity.setResult(-1, intent);
            final Activity activity2 = this.activity;
            Intrinsics.checkNotNull(activity2);
            activity2.finish();
        }
    }

    private void openImageView(final Customer customer) {
        mContext.startActivity(new Intent(mContext, ImageViewActivity.class).putExtra(IntentExtraName.EXTRA_ITEM_ID, customer.getLogicalRef()).putExtra(ImageViewActivity.EXTRA_CUSTOMER, true));
    }

    private void openInfoNot(final Customer customer) {
        final String infoNote = customer.getInfoNote();
        Intrinsics.checkNotNull(infoNote);
        if (0 < infoNote.length()) {
            new AlertDialog.Builder(activity).setTitle(R.string.str_customer_list_info_not).setMessage(customer.getInfoNote()).setPositiveButton(R.string.ok, null).create().show();
        }
    }

    private void clickEmail(final Customer customer) {
        if (TextUtils.isEmpty(customer.getEmail())) {
            return;
        }
        final ProgressDialog progressDialog = this.progressDialog;
        if (null == progressDialog) {
            final Context context = mContext;
            this.progressDialog = ProgressDialog.show(context, null, context.getString(R.string.str_preparing), true, true);
        } else {
            Intrinsics.checkNotNull(progressDialog);
            progressDialog.show();
        }
        final Intent makeEmailIntent = makeEmailIntent(new String[]{customer.getEmail()});
        final ProgressDialog progressDialog2 = this.progressDialog;
        if (null != progressDialog2) {
            progressDialog2.dismiss();
        }
        if (null != makeEmailIntent.resolveActivity(this.mContext.getPackageManager())) {
            mContext.startActivity(makeEmailIntent);
        }
    }

    private void clickTel(final Customer customer) {
        if (TextUtils.isEmpty(customer.getTel())) {
            return;
        }
        final ProgressDialog progressDialog = this.progressDialog;
        if (null == progressDialog) {
            final Context context = mContext;
            this.progressDialog = ProgressDialog.show(context, null, context.getString(R.string.str_preparing), true, true);
        } else {
            Intrinsics.checkNotNull(progressDialog);
            progressDialog.show();
        }
        final String tel = customer.getTel();
        Intrinsics.checkNotNull(tel);
        final Intent makeContactIntent = makeContactIntent(tel);
        final ProgressDialog progressDialog2 = this.progressDialog;
        if (null != progressDialog2) {
            progressDialog2.dismiss();
        }
        if (null != makeContactIntent.resolveActivity(this.mContext.getPackageManager())) {
            mContext.startActivity(makeContactIntent);
        }
    }

    /* compiled from: CustomerRecyclerViewAdapter.kt */
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
