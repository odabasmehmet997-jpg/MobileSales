package com.proje.mobilesales.features.cabinoperation.adapter;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.PopupMenu;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.features.adapter.EndlessListRecyclerViewAdapter;
import com.proje.mobilesales.features.adapter.ItemViewHolder;
import com.proje.mobilesales.features.adapter.LoadingViewHolder;
import com.proje.mobilesales.features.cabinoperation.interfaces.ICabinOperationListener;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.Cabin;
import com.proje.mobilesales.features.cabinoperation.model.enums.CabinEnums;
import java.util.ArrayList;
import java.util.List;

import com.proje.mobilesales.features.visit.model.VisitInfoShort;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class CabinListRecyclerViewAdapter extends EndlessListRecyclerViewAdapter<RecyclerView.ViewHolder, Cabin> {
    private ICabinOperationListener iCabinOperationListener;
    public AlertDialogBuilder<?> mAlertDialogBuilder;
    private int mBackgroundColor;
    private final List<Cabin> mCabins = new ArrayList();
    private int mNotDeliveredCabinColor;
    private int mOnRoadCabinColor;
    public PopupMenu mPopupMenu;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;

    protected void bindItem(RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
    }

    protected void handleItemClick(Object item, RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(holder, "holder");
    }
    protected boolean isItemAvailable(Object item) {
        Intrinsics.checkNotNullParameter(item, "item");
        return true;
    }
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (i2 == this.VIEW_TYPE_ITEM) {
            View inflate = this.mInflater.inflate(R.layout.item_cabin, parent, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
            return new CabinViewHolder(this, inflate);
        }
        LoadingViewHolder loadingViewHolder = i2 == this.VIEW_TYPE_LOADING ? new LoadingViewHolder(this.mInflater.inflate(R.layout.item_loading, parent, false)) : null;
        Intrinsics.checkNotNull(loadingViewHolder);
        return loadingViewHolder;
    }
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        Context context = this.mContext;
        if (context instanceof BaseInjectableActivity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
            ((BaseInjectableActivity) context).getActivityComponent().inject(this);
        }
        TypedArray obtainStyledAttributes = this.mContext.getTheme().obtainStyledAttributes(new int[]{R.attr.textColorTertiary, R.attr.textColorSecondary, R.attr.colorCardBackground, R.attr.colorCardHighlight});
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
        this.mNotDeliveredCabinColor = ContextCompat.getColor(this.mContext, R.color.redA200);
        this.mOnRoadCabinColor = ContextCompat.getColor(this.mContext, R.color.greenA700);
        this.mBackgroundColor = ContextCompat.getColor(this.mContext, obtainStyledAttributes.getResourceId(2, 0));
        this.mPopupMenu = new PopupMenu.Impl();
        Context context2 = ContextUtils.getmContext();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(context2, (BaseInjectableActivity) activity);
        Context context3 = ContextUtils.getmContext();
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilder = new AlertDialogBuilder.Impl(context3, (BaseInjectableActivity) activity2);
    }
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onDetachedFromRecyclerView(recyclerView);
        List<Cabin> list = this.mCabins;
        if (list != null) {
            list.clear();
        }
    }
    protected void clearViewHolder(RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder instanceof CabinViewHolder cabinViewHolder) {
            cabinViewHolder.getTxtCabinCode().setText("");
            cabinViewHolder.getTxtCabinModel().setText("");
            cabinViewHolder.getTxtCabinBrand().setText("");
        }
    }
    public void insertProgressBar() {
        List<Cabin> list = this.mCabins;
        Intrinsics.checkNotNull(list);
        if (list.contains(null)) {
            return;
        }
        this.mCabins.add(null);
        notifyItemInserted(getItemCount() - 1);
    }
    public void removeProgressBar() {
        List<Cabin> list = this.mCabins;
        Intrinsics.checkNotNull(list);
        int indexOf = list.indexOf(null);
        if (indexOf != -1) {
            this.mCabins.remove(indexOf);
            notifyItemRemoved(indexOf);
        }
    }
    @SuppressLint({"NotifyDataSetChanged"})
    public void addItem(ArrayList<ArrayList<VisitInfoShort>> list) {
        removeProgressBar();
        if (list != null) {
            List<Cabin> list2 = this.mCabins;
            Intrinsics.checkNotNull(list2);
            list2.addAll(list);
            this.mRecyclerView.getRecycledViewPool().clear();
            notifyDataSetChanged();
        }
    }

    protected void restartAdapterAndScroll() {
        if (getItemCount() > 0) {
            List<Cabin> list = this.mCabins;
            Intrinsics.checkNotNull(list);
            list.clear();
        }
        notifyDataSetChanged();
    }

    public long getItemId(int i2) {
        return getItem(i2) != null ? r0.f1223id : super.getItemId(i2);
    }

    protected Cabin getItem(int i2) {
        List<Cabin> list = this.mCabins;
        if (list != null) {
            return list.get(i2);
        }
        return null;
    }
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder instanceof CabinViewHolder cabinViewHolder3) {
            Cabin item = getItem(i2);
            if (this.mCardViewEnabled) {
                cabinViewHolder3.mCardView.setCardElevation(this.mCardElevation);
                cabinViewHolder3.mCardView.setRadius(this.mCardRadius);
                cabinViewHolder3.mCardView.setUseCompatPadding(true);
            } else {
                cabinViewHolder3.mCardView.setCardElevation(0.0f);
                cabinViewHolder3.mCardView.setRadius(0.0f);
                cabinViewHolder3.mCardView.setUseCompatPadding(false);
            }
            clearViewHolder(holder);
            Intrinsics.checkNotNull(item);
            if (!isItemAvailable(item)) {
                loadItem(holder.getAdapterPosition());
                return;
            }
            cabinViewHolder3.getTxtCabinBrand().setText(item.brand);
            cabinViewHolder3.getTxtCabinModel().setText(item.model);
            cabinViewHolder3.getTxtCabinCode().setText(item.code);
            setBackGroundColor(cabinViewHolder3.getLnCabinContainer(), item);
            setCabinOperationClickListener(cabinViewHolder3.getImgCabinOperation(), item);
            return;
        }
        if (holder instanceof LoadingViewHolder) {
            ((LoadingViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    public int getItemCount() {
        List<Cabin> list = this.mCabins;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void setCabinOperationListener(ICabinOperationListener iCabinOperationListener) {
        this.iCabinOperationListener = iCabinOperationListener;
    }

    private void setBackGroundColor(RelativeLayout relativeLayout, Cabin cabin) {
        if (cabin.isCabinOnCustomerButNotDelivered()) {
            relativeLayout.setBackgroundColor(this.mNotDeliveredCabinColor);
        } else if (cabin.isCabinReceivedFromCustomerAndOnTheRoad()) {
            relativeLayout.setBackgroundColor(this.mOnRoadCabinColor);
        } else {
            relativeLayout.setBackgroundColor(this.mBackgroundColor);
        }
    }
    private void setCabinOperationClickListener(final ImageView imageView, final Cabin cabin) {
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CabinListRecyclerViewAdapter.setCabinOperationClickListenerlambda2(CabinListRecyclerViewAdapter.this, imageView, cabin, view);
            }
        });
    }
    public static void setCabinOperationClickListenerlambda2(final CabinListRecyclerViewAdapter this0, ImageView operationView, final Cabin cabin, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(operationView, "operationView");
        Intrinsics.checkNotNullParameter(cabin, "cabin");
        PopupMenu popupMenu = this0.mPopupMenu;
        Intrinsics.checkNotNull(popupMenu);
        popupMenu.create(this0.mContext, operationView, 5).inflate(R.menu.menu_cabin_operation).setMenuItemVisible(R.id.menu_cabin_receive, cabin.isCabinOnCustomerAndDelivered()).setMenuItemVisible(R.id.menu_cabin_deliver, cabin.isCabinOnCustomerButNotDelivered() || cabin.isCabinReceivedFromCustomerAndOnTheRoad()).setMenuItemVisible(R.id.menu_cabin_add, false).setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.proje.mobilesales.features.cabinoperation.adapter.CabinListRecyclerViewAdapterExternalSyntheticLambda0

            public boolean onMenuItemClick(MenuItem menuItem) {
                boolean cabinOperationClickListenerlambda2lambda1;
                cabinOperationClickListenerlambda2lambda1 = CabinListRecyclerViewAdapter.setCabinOperationClickListenerlambda2lambda1(CabinListRecyclerViewAdapter.this, cabin, menuItem);
                return cabinOperationClickListenerlambda2lambda1;
            }
        }).show();
    }

    public static boolean setCabinOperationClickListenerlambda2lambda1(final CabinListRecyclerViewAdapter this0, final Cabin cabin, MenuItem item) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(cabin, "cabin");
        Intrinsics.checkNotNullParameter(item, "item");
        if (!ContextUtils.checkAutoTimeAndTimeZoneEnabledAndShowDialog()) {
            return false;
        }
        if (item.getItemId() == R.id.menu_cabin_receive) {
            AlertDialogBuilder<?> alertDialogBuilder = this0.mAlertDialogBuilder;
            Intrinsics.checkNotNull(alertDialogBuilder);
            alertDialogBuilder.setTitle(R.string.str_received_reason).setSingleChoice(R.array.array_cabin_received_reason, -1, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i2) {
                    CabinListRecyclerViewAdapter.setCabinOperationClickListenerlambda2lambda1lambda0(CabinListRecyclerViewAdapter.this, cabin, dialogInterface, i2);
                }
            }).create().show();
            return true;
        }
        if (item.getItemId() != R.id.menu_cabin_deliver) {
            return false;
        }
        if (cabin.isCabinOnCustomerButNotDelivered()) {
            ICabinOperationListener iCabinOperationListener = this0.iCabinOperationListener;
            Intrinsics.checkNotNull(iCabinOperationListener);
            iCabinOperationListener.getCabinDeliverToCustomer(cabin);
        } else if (cabin.isCabinReceivedFromCustomerAndOnTheRoad()) {
            ICabinOperationListener iCabinOperationListener2 = this0.iCabinOperationListener;
            Intrinsics.checkNotNull(iCabinOperationListener2);
            iCabinOperationListener2.getCabinDeliverToStorage(cabin);
        }
        return true;
    }
    public static void setCabinOperationClickListenerlambda2lambda1lambda0(CabinListRecyclerViewAdapter this0, Cabin cabin, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(cabin, "cabin");
        int i3 = CabinEnums.CabinStatus.ACTIVE.getmValue();
        if (StringsKt.equals(this0.mContext.getResources().getStringArray(R.array.array_cabin_received_reason)[i2], this0.mContext.getString(R.string.str_defective), true)) {
            i3 = CabinEnums.CabinStatus.DEFECTIVE.getmValue();
        }
        ICabinOperationListener iCabinOperationListener = this0.iCabinOperationListener;
        Intrinsics.checkNotNull(iCabinOperationListener);
        iCabinOperationListener.getCabinReceivedFromCustomer(cabin, i3);
        dialogInterface.dismiss();
    }

    public final class CabinViewHolder extends ItemViewHolder {
        private final ImageView imgCabinOperation;
        private final RelativeLayout lnCabinContainer;
        final CabinListRecyclerViewAdapter this0;
        private final TextView txtCabinBrand;
        private final TextView txtCabinCode;
        private final TextView txtCabinModel;

        public CabinViewHolder(CabinListRecyclerViewAdapter cabinListRecyclerViewAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this0 = cabinListRecyclerViewAdapter;
            View findViewById = itemView.findViewById(R.id.ln_CabinContainer);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            this.lnCabinContainer = (RelativeLayout) findViewById;
            View findViewById2 = itemView.findViewById(R.id.txt_CabinBrand);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
            this.txtCabinBrand = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.txt_CabinModel);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
            this.txtCabinModel = (TextView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.txt_CabinCode);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
            this.txtCabinCode = (TextView) findViewById4;
            View findViewById5 = itemView.findViewById(R.id.btn_cabinOperation);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
            this.imgCabinOperation = (ImageView) findViewById5;
        }

        public RelativeLayout getLnCabinContainer() {
            return this.lnCabinContainer;
        }

        public TextView getTxtCabinBrand() {
            return this.txtCabinBrand;
        }

        public TextView getTxtCabinModel() {
            return this.txtCabinModel;
        }

        public TextView getTxtCabinCode() {
            return this.txtCabinCode;
        }

        public ImageView getImgCabinOperation() {
            return this.imgCabinOperation;
        }
    }
}
