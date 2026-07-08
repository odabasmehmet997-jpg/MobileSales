package com.proje.mobilesales.features.collections.cashandcreditcardfiche.adapter;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.view.ActionMode;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.interfaces.ActionModeDelegate;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.MenuTintDelegate;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.adapter.ItemViewHolder;
import com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditFicheDetail;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.activity.CashAndCreditCardFicheActivity;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.activity.CashCreditFicheDetailEnterActivity;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheDetailListFragment;
import java.util.ArrayList;
import java.util.Collections;
import kotlin.jvm.internal.Intrinsics;

public final class CashCreditDetailListRecyclerViewAdapter extends ListRecyclerViewAdapter<CashCreditDetailListRecyclerViewAdapter.CashCreditViewHolder, CashCreditFicheDetail> {
    ActionModeDelegate mActionModeDelegate;
    private AlertDialogBuilder<?> mAlertDialogBuilder;
    private int mBackgroundColor;
    private FicheMode mFicheMode;
    private CashCreditFicheDetailListFragment mFragment;
    private int mHighlightColor;
    MenuTintDelegate mMenuTintDelegate;
    private ArrayList<CashCreditFicheDetail> mCashCreditFicheDetail = new ArrayList<>();
    private int mPendingAdd = -1;
    private final ActionMode.Callback mActionModeCallback = new CashCreditDetailListRecyclerViewAdaptermActionModeCallback1(this);
    public void bindItem(CashCreditViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
    }

    public boolean isItemAvailable(CashCreditFicheDetail item) {
        Intrinsics.checkNotNullParameter(item, "item");
        return true;
    }
    public void restoreState(Bundle savedState) {
        Intrinsics.checkNotNullParameter(savedState, "savedState");
    }

    public CashCreditDetailListRecyclerViewAdapter(ActionModeDelegate actionModeDelegate) {
        this.mActionModeDelegate = actionModeDelegate;
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
        if (context instanceof BaseInjectableActivity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
            ((BaseInjectableActivity) context).getActivityComponent().inject(this);
        }
        MenuTintDelegate menuTintDelegate = new MenuTintDelegate();
        this.mMenuTintDelegate = menuTintDelegate;
        Intrinsics.checkNotNull(menuTintDelegate);
        menuTintDelegate.onActivityCreated(this.mContext);
        TypedArray typedArrayObtainStyledAttributes = this.mContext.getTheme().obtainStyledAttributes(new int[]{R.attr.textColorTertiary, R.attr.textColorSecondary, com.proje.mobilesales.R.attr.colorCardBackground, com.proje.mobilesales.R.attr.colorCardHighlight, com.proje.mobilesales.R.attr.colorCardAlert});
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "obtainStyledAttributes(...)");
        this.mBackgroundColor = ContextCompat.getColor(this.mContext, typedArrayObtainStyledAttributes.getResourceId(2, 0));
        this.mHighlightColor = ContextCompat.getColor(this.mContext, typedArrayObtainStyledAttributes.getResourceId(3, 0));
        Context context2 = this.mContext;
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilder = new AlertDialogBuilder.Impl(context2, (BaseInjectableActivity) activity);
    }
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onDetachedFromRecyclerView(recyclerView);
        this.mActionModeDelegate = null;
    }
    public CashCreditViewHolder onCreateViewHolder(ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View viewInflate = this.mInflater.inflate(com.proje.mobilesales.R.layout.item_cashcredit_detail_row, parent, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflate(...)");
        return new CashCreditViewHolder(viewInflate);
    }
    public void onBindViewHolder(final CashCreditViewHolder holder, final int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final CashCreditFicheDetail cashCreditDetail = getCashCreditDetail(i2);
        if (isItemAvailable(cashCreditDetail)) {
            clear(holder);
            bindData(holder, cashCreditDetail);
            View itemView = holder.itemView;
            Intrinsics.checkNotNullExpressionValue(itemView, "itemView");
            setChecked(i2, itemView);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    CashCreditDetailListRecyclerViewAdapter.onBindViewHolderlambda0(this.f0, cashCreditDetail, i2, holder, view);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    return CashCreditDetailListRecyclerViewAdapter.onBindViewHolderlambda1(this.f0, holder, view);
                }
            });
        }
    }
    public static void onBindViewHolderlambda0(CashCreditDetailListRecyclerViewAdapter this0, CashCreditFicheDetail mCashCreditFicheDetail, int i2, CashCreditViewHolder holder, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(mCashCreditFicheDetail, "mCashCreditFicheDetail");
        Intrinsics.checkNotNullParameter(holder, "holder");
        ActionModeDelegate actionModeDelegate = this0.mActionModeDelegate;
        Intrinsics.checkNotNull(actionModeDelegate);
        if (!actionModeDelegate.isInActionMode()) {
            this0.openItem(mCashCreditFicheDetail, i2);
        } else {
            this0.toggle(holder.getAdapterPosition());
        }
    }
    public static boolean onBindViewHolderlambda1(CashCreditDetailListRecyclerViewAdapter this0, CashCreditViewHolder holder, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(holder, "holder");
        ActionModeDelegate actionModeDelegate = this0.mActionModeDelegate;
        Intrinsics.checkNotNull(actionModeDelegate);
        if (!actionModeDelegate.startActionMode(this0.mActionModeCallback)) {
            return false;
        }
        this0.toggle(holder.getAdapterPosition());
        return true;
    }

    private void openItem(CashCreditFicheDetail cashCreditFicheDetail, int i2) {
        Intent intent = new Intent(this.mContext, CashCreditFicheDetailEnterActivity.class);
        CashAndCreditCardFicheActivity.Companion companion = CashAndCreditCardFicheActivity.Companion;
        intent.putExtra(companion.getCASH_CREDIT_DETAIL(), cashCreditFicheDetail);
        CashCreditFicheDetailListFragment cashCreditFicheDetailListFragment = this.mFragment;
        Intrinsics.checkNotNull(cashCreditFicheDetailListFragment);
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE, cashCreditFicheDetailListFragment.getCustomerOperation());
        String str = IntentExtraName.EXTRA_CUSTOMER_REF;
        CashCreditFicheDetailListFragment cashCreditFicheDetailListFragment2 = this.mFragment;
        Intrinsics.checkNotNull(cashCreditFicheDetailListFragment2);
        intent.putExtra(str, cashCreditFicheDetailListFragment2.getCustomerRef());
        intent.putExtra(companion.getCASH_CREDIT_DETAIL_POSITION(), i2);
        String str2 = IntentExtraName.INVOICE_PAYMENTLINE_REF;
        CashCreditFicheDetailListFragment cashCreditFicheDetailListFragment3 = this.mFragment;
        Intrinsics.checkNotNull(cashCreditFicheDetailListFragment3);
        intent.putExtra(str2, cashCreditFicheDetailListFragment3.getInvoiceRef());
        CashCreditFicheDetailListFragment cashCreditFicheDetailListFragment4 = this.mFragment;
        Intrinsics.checkNotNull(cashCreditFicheDetailListFragment4);
        FragmentActivity activity = cashCreditFicheDetailListFragment4.getActivity();
        Intrinsics.checkNotNull(activity);
        activity.startActivityForResult(intent, 2);
    }
    public void initDisplayOptions(Context context) {
        if (isAttached()) {
            notifyDataSetChanged();
        }
    }
    public void bindData(CashCreditViewHolder holder, CashCreditFicheDetail property) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(property, "property");
        holder.getTxtMatbuzNo().setText(property.getDocNo().toString());
        TextView txtTutar = holder.getTxtTutar();
        String string = property.getTotal().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        txtTutar.setText(StringUtils.dFormat(string));
    }
    public void setChecked(int i2, View holderView) {
        Intrinsics.checkNotNullParameter(holderView, "holderView");
        if (isSelected(i2)) {
            holderView.setBackgroundColor(this.mHighlightColor);
        } else {
            holderView.setBackgroundColor(this.mBackgroundColor);
        }
    }
    public void clear(CashCreditViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.reset();
    }

    private CashCreditFicheDetail getCashCreditDetail(int i2) {
        ArrayList<CashCreditFicheDetail> arrayList = this.mCashCreditFicheDetail;
        Intrinsics.checkNotNull(arrayList);
        CashCreditFicheDetail cashCreditFicheDetail = arrayList.get(i2);
        Intrinsics.checkNotNullExpressionValue(cashCreditFicheDetail, "get(...)");
        return cashCreditFicheDetail;
    }
    public Bundle saveState() {
        return new Bundle();
    }
    public void clearViewHolder(CashCreditViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.reset();
    }
    public CashCreditFicheDetail getItem(int i2) {
        ArrayList<CashCreditFicheDetail> arrayList = this.mCashCreditFicheDetail;
        if (arrayList == null) {
            return null;
        }
        Intrinsics.checkNotNull(arrayList);
        return arrayList.get(i2);
    }
    public void handleItemClick(CashCreditFicheDetail item, CashCreditViewHolder holder) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (isSelected(holder.getAdapterPosition())) {
            notifyItemChanged(holder.getAdapterPosition());
            int i2 = this.mLastSelectedPosition;
            if (i2 >= 0) {
                notifyItemChanged(i2);
            }
            this.mLastSelectedPosition = holder.getAdapterPosition();
        }
    }
    public int getItemCount() {
        ArrayList<CashCreditFicheDetail> arrayList = this.mCashCreditFicheDetail;
        Intrinsics.checkNotNull(arrayList);
        return arrayList.size();
    }
    public void setmFragment(CashCreditFicheDetailListFragment cashCreditFicheDetailListFragment) {
        this.mFragment = cashCreditFicheDetailListFragment;
    }
    public void setCashCreditDetailList(ArrayList<CashCreditFicheDetail> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        this.mCashCreditFicheDetail = arrayList;
        if (this.mSelected.size() != 0) {
            ArrayList arrayList2 = new ArrayList();
            Collections.sort(arrayList2);
            this.mSelected.clear();
            int size = arrayList2.size() - 1;
            if (size < 0) {
                return;
            }
            while (true) {
                int i2 = size - 1;
                notifyItemRemoved(((Number) arrayList2.get(size)).intValue());
                if (i2 < 0) {
                    return;
                } else {
                    size = i2;
                }
            }
        } else {
            int i3 = this.mPendingAdd;
            if (i3 >= 0) {
                notifyItemInserted(i3);
                this.mPendingAdd = -1;
            } else {
                notifyDataSetChanged();
            }
        }
    }

    public void setmFicheMode(FicheMode ficheMode) {
        this.mFicheMode = ficheMode;
        setSingleSwipe();
    }

    private void setSingleSwipe() {
        if (this.mFicheMode != FicheMode.ANALYSE) {
            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback() {
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                    Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
                    Intrinsics.checkNotNullParameter(target, "target");
                    return false;
                }
                public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                    Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                    Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
                    ActionModeDelegate actionModeDelegate = CashCreditDetailListRecyclerViewAdapter.this.mActionModeDelegate;
                    Intrinsics.checkNotNull(actionModeDelegate);
                    if (actionModeDelegate.isInActionMode()) {
                        return 0;
                    }
                    return super.getSwipeDirs(recyclerView, viewHolder);
                }
                public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                    Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                    Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
                    ArrayList arrayList = CashCreditDetailListRecyclerViewAdapter.this.mCashCreditFicheDetail;
                    Intrinsics.checkNotNull(arrayList);
                    if (((CashCreditFicheDetail) arrayList.get(viewHolder.getAdapterPosition())).component11()) {
                        return 0;
                    }
                    return super.getMovementFlags(recyclerView, viewHolder);
                }
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int i2) {
                    Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
                    CashCreditDetailListRecyclerViewAdapter.this.dismiss(viewHolder.getAdapterPosition());
                }
            }).attachToRecyclerView(this.mRecyclerView);
        }
    }
    public void dismiss(final int i2) {
        final CashCreditFicheDetail item = getItem(i2);
        ArrayList<CashCreditFicheDetail> arrayList = this.mCashCreditFicheDetail;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        ArrayList<CashCreditFicheDetail> arrayList2 = this.mCashCreditFicheDetail;
        Intrinsics.checkNotNull(arrayList2);
        arrayList2.remove(i2);
        notifyItemRemoved(i2);
        CashCreditFicheDetailListFragment cashCreditFicheDetailListFragment = this.mFragment;
        Intrinsics.checkNotNull(cashCreditFicheDetailListFragment);
        CashAndCreditCardFicheActivity cashCreditFicheActivity = cashCreditFicheDetailListFragment.getCashCreditFicheActivity();
        if (cashCreditFicheActivity != null) {
            cashCreditFicheActivity.setTotalLayoutWithNotify();
        }
        Snackbar snackbarMake = Snackbar.make(this.mRecyclerView, com.proje.mobilesales.R.string.str_remove_at_detail_list, 0);
        CashCreditFicheDetailListFragment cashCreditFicheDetailListFragment2 = this.mFragment;
        Intrinsics.checkNotNull(cashCreditFicheDetailListFragment2);
        Snackbar backgroundTint = snackbarMake.setBackgroundTint(cashCreditFicheDetailListFragment2.getResources().getColor(com.proje.mobilesales.R.color.colorAccent));
        CashCreditFicheDetailListFragment cashCreditFicheDetailListFragment3 = this.mFragment;
        Intrinsics.checkNotNull(cashCreditFicheDetailListFragment3);
        Snackbar actionTextColor = backgroundTint.setActionTextColor(cashCreditFicheDetailListFragment3.getResources().getColor(com.proje.mobilesales.R.color.white));
        CashCreditFicheDetailListFragment cashCreditFicheDetailListFragment4 = this.mFragment;
        Intrinsics.checkNotNull(cashCreditFicheDetailListFragment4);
        actionTextColor.setTextColor(cashCreditFicheDetailListFragment4.getResources().getColor(com.proje.mobilesales.R.color.white)).setAction(com.proje.mobilesales.R.string.str_undo, new View.OnClickListener() {
            public void onClick(View view) {
                CashCreditDetailListRecyclerViewAdapter.dismisslambda2(this.f0, i2, item, view);
            }
        }).show();
    }
    public static void dismisslambda2(CashCreditDetailListRecyclerViewAdapter this0, int i2, CashCreditFicheDetail cashCreditFicheDetail, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.mPendingAdd = i2;
        ArrayList<CashCreditFicheDetail> arrayList = this0.mCashCreditFicheDetail;
        Intrinsics.checkNotNull(arrayList);
        Intrinsics.checkNotNull(cashCreditFicheDetail);
        arrayList.add(i2, cashCreditFicheDetail);
        this0.notifyItemInserted(i2);
        CashCreditFicheDetailListFragment cashCreditFicheDetailListFragment = this0.mFragment;
        Intrinsics.checkNotNull(cashCreditFicheDetailListFragment);
        CashAndCreditCardFicheActivity cashCreditFicheActivity = cashCreditFicheDetailListFragment.getCashCreditFicheActivity();
        if (cashCreditFicheActivity != null) {
            cashCreditFicheActivity.setTotalLayoutWithNotify();
        }
    }

    @SuppressLint({"NotifyDataSetChanged"})
    private void toggle(int i2) {
        if (isSelected(i2)) {
            this.mSelected.delete(i2);
        } else {
            this.mSelected.put(i2, true);
        }
        notifyDataSetChanged();
    }
    @SuppressLint({"NotifyDataSetChanged"})
    public void removeSelection() {
        ArrayList<CashCreditFicheDetail> arrayList = this.mCashCreditFicheDetail;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        boolean z = false;
        for (int size = this.mSelected.size() - 1; -1 < size; size--) {
            if (this.mSelected.valueAt(size)) {
                CashCreditFicheDetail item = getItem(this.mSelected.keyAt(size));
                Intrinsics.checkNotNull(item);
                if (item.isPayedOnline()) {
                    z = true;
                } else {
                    ArrayList<CashCreditFicheDetail> arrayList2 = this.mCashCreditFicheDetail;
                    Intrinsics.checkNotNull(arrayList2);
                    arrayList2.remove(item);
                }
            }
        }
        CashCreditFicheDetailListFragment cashCreditFicheDetailListFragment = this.mFragment;
        Intrinsics.checkNotNull(cashCreditFicheDetailListFragment);
        CashAndCreditCardFicheActivity cashCreditFicheActivity = cashCreditFicheDetailListFragment.getCashCreditFicheActivity();
        if (cashCreditFicheActivity != null) {
            cashCreditFicheActivity.setTotalLayoutWithNotify();
        }
        this.mSelected.clear();
        notifyDataSetChanged();
        if (z) {
            Context context = this.mContext;
            CashCreditFicheDetailListFragment cashCreditFicheDetailListFragment2 = this.mFragment;
            Intrinsics.checkNotNull(cashCreditFicheDetailListFragment2);
            Toast.makeText(context, cashCreditFicheDetailListFragment2.getString(com.proje.mobilesales.R.string.str_cannot_delete_online_paid_lines), 1).show();
        }
    }
    public static final class CashCreditViewHolder extends ItemViewHolder {
        private final LinearLayout lnDetailContainer;
        private final TextView txtMatbuzNo;
        private final TextView txtTutar;
        public CashCreditViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View viewFindViewById = itemView.findViewById(com.proje.mobilesales.R.id.ln_detailContainer);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            this.lnDetailContainer = (LinearLayout) viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(com.proje.mobilesales.R.id.txt_amount);
            Intrinsics.checkNotNull(viewFindViewById2, "null cannot be cast to non-null type android.widget.TextView");
            this.txtTutar = (TextView) viewFindViewById2;
            View viewFindViewById3 = itemView.findViewById(com.proje.mobilesales.R.id.txt_MakbuzNo);
            Intrinsics.checkNotNull(viewFindViewById3, "null cannot be cast to non-null type android.widget.TextView");
            this.txtMatbuzNo = (TextView) viewFindViewById3;
        }

        public TextView getTxtTutar() {
            return this.txtTutar;
        }

        public TextView getTxtMatbuzNo() {
            return this.txtMatbuzNo;
        }

        public void reset() {
            this.txtTutar.setText("");
            this.txtMatbuzNo.setText("");
        }
    }
}
