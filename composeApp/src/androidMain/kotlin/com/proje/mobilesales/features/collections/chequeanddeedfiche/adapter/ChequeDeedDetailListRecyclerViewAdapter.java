package com.proje.mobilesales.features.collections.chequeanddeedfiche.adapter;

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
import androidx.appcompat.view.ActionMode;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import com.proje.mobilesales.R;
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
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeedFicheDetail;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.view.activity.ChequeAndDeedFicheActivity;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.view.activity.ChequeDeedFicheDetailEnterActivity;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.view.fragment.ChequeDeedFicheDetailListFragment;
import java.util.ArrayList;
import java.util.Collections;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: ChequeDeedDetailListRecyclerViewAdapter.kt */

public final class ChequeDeedDetailListRecyclerViewAdapter extends ListRecyclerViewAdapter<ChequeDeedViewHolder, ChequeDeedFicheDetail> {
    private ActionModeDelegate mActionModeDelegate;
    public AlertDialogBuilder<?> mAlertDialogBuilder;
    private int mBackgroundColor;
    private FicheMode mFicheMode;
    private ChequeDeedFicheDetailListFragment mFragment;
    private int mHighlightColor;
    private MenuTintDelegate mMenuTintDelegate;
    private ArrayList<ChequeDeedFicheDetail> mChequeDeedFicheDetail = new ArrayList<>();
    private int mPendingAdd = -1;
    private final ActionMode.Callback mActionModeCallback = new ChequeDeedDetailListRecyclerViewAdaptermActionModeCallback1(this);

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter
    private void bindItem(final ChequeDeedViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
    }

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter
    protected boolean isItemAvailable(final ChequeDeedFicheDetail item) {
        Intrinsics.checkNotNullParameter(item, "item");
        return true;
    }

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter, com.proje.mobilesales.features.adapter.IListRecyclerView
    public void restoreState(final Bundle savedState) {
        Intrinsics.checkNotNullParameter(savedState, "savedState");
    }

    public ChequeDeedDetailListRecyclerViewAdapter(final ActionModeDelegate actionModeDelegate) {
        mActionModeDelegate = actionModeDelegate;
    }

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        final Context context = recyclerView.getContext();
        mContext = context;
        if (context instanceof BaseInjectableActivity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
            ((BaseInjectableActivity) context).getActivityComponent().inject(this);
        }
        final MenuTintDelegate menuTintDelegate = new MenuTintDelegate();
        mMenuTintDelegate = menuTintDelegate;
        Intrinsics.checkNotNull(menuTintDelegate);
        menuTintDelegate.onActivityCreated(mContext);
        final TypedArray obtainStyledAttributes = mContext.getTheme().obtainStyledAttributes(new int[]{R.attr.textColorTertiary, R.attr.textColorSecondary, R.attr.colorCardBackground, R.attr.colorCardHighlight, R.attr.colorCardAlert});
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
        mBackgroundColor = ContextCompat.getColor(mContext, obtainStyledAttributes.getResourceId(2, 0));
        mHighlightColor = ContextCompat.getColor(mContext, obtainStyledAttributes.getResourceId(3, 0));
        final Context context2 = mContext;
        final Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mAlertDialogBuilder = new AlertDialogBuilder.Impl(context2, (BaseInjectableActivity) activity);
    }

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(final RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onDetachedFromRecyclerView(recyclerView);
        mActionModeDelegate = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ChequeDeedViewHolder onCreateViewHolder(final ViewGroup parent, final int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        final View inflate = mInflater.inflate(R.layout.item_chequedeed_detail_row, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ChequeDeedViewHolder(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ChequeDeedViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ChequeDeedFicheDetail chequeDeedDetail = this.getChequeDeedDetail(i2);
        if (this.isItemAvailable(chequeDeedDetail)) {
            this.clear(holder);
            this.bindData(holder, chequeDeedDetail);
            final View itemView = holder.itemView;
            Intrinsics.checkNotNullExpressionValue(itemView, "itemView");
            this.setChecked(i2, itemView);
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.collections.chequeanddeedfiche.adapter.ChequeDeedDetailListRecyclerViewAdapterExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public void onClick(final View view) {
                    onBindViewHolderlambda0(ChequeDeedDetailListRecyclerViewAdapter.this, chequeDeedDetail, i2, holder, view);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.proje.mobilesales.features.collections.chequeanddeedfiche.adapter.ChequeDeedDetailListRecyclerViewAdapterExternalSyntheticLambda2
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(final View view) {
                    final boolean onBindViewHolderlambda1;
                    onBindViewHolderlambda1 = onBindViewHolderlambda1(ChequeDeedDetailListRecyclerViewAdapter.this, holder, view);
                    return onBindViewHolderlambda1;
                }
            });
        }
    }

    
    public static void onBindViewHolderlambda0(final ChequeDeedDetailListRecyclerViewAdapter this0, final ChequeDeedFicheDetail mChequeDeedFicheDetail, final int i2, final ChequeDeedViewHolder holder, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(mChequeDeedFicheDetail, "mChequeDeedFicheDetail");
        Intrinsics.checkNotNullParameter(holder, "holder");
        final ActionModeDelegate actionModeDelegate = this0.mActionModeDelegate;
        Intrinsics.checkNotNull(actionModeDelegate);
        if (!actionModeDelegate.isInActionMode()) {
            this0.openItem(mChequeDeedFicheDetail, i2);
        } else {
            this0.toggle(holder.getAdapterPosition());
        }
    }

    
    public static boolean onBindViewHolderlambda1(final ChequeDeedDetailListRecyclerViewAdapter this0, final ChequeDeedViewHolder holder, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(holder, "holder");
        final ActionModeDelegate actionModeDelegate = this0.mActionModeDelegate;
        Intrinsics.checkNotNull(actionModeDelegate);
        if (!actionModeDelegate.startActionMode(this0.mActionModeCallback)) {
            return false;
        }
        this0.toggle(holder.getAdapterPosition());
        return true;
    }

    private void openItem(final ChequeDeedFicheDetail chequeDeedFicheDetail, final int i2) {
        final Intent intent = new Intent(mContext, ChequeDeedFicheDetailEnterActivity.class);
        final ChequeAndDeedFicheActivity.Companion companion = ChequeAndDeedFicheActivity.Companion;
        intent.putExtra(companion.getCHEQUE_DEED_DETAIL(), chequeDeedFicheDetail);
        final ChequeDeedFicheDetailListFragment chequeDeedFicheDetailListFragment = mFragment;
        Intrinsics.checkNotNull(chequeDeedFicheDetailListFragment);
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE, chequeDeedFicheDetailListFragment.getCustomerOperation());
        final String str = IntentExtraName.EXTRA_CUSTOMER_REF;
        final ChequeDeedFicheDetailListFragment chequeDeedFicheDetailListFragment2 = mFragment;
        Intrinsics.checkNotNull(chequeDeedFicheDetailListFragment2);
        intent.putExtra(str, chequeDeedFicheDetailListFragment2.getCustomerRef());
        intent.putExtra(companion.getCHEQUE_DEED_DETAIL_POSITION(), i2);
        final String str2 = IntentExtraName.INVOICE_PAYMENTLINE_REF;
        final ChequeDeedFicheDetailListFragment chequeDeedFicheDetailListFragment3 = mFragment;
        Intrinsics.checkNotNull(chequeDeedFicheDetailListFragment3);
        intent.putExtra(str2, chequeDeedFicheDetailListFragment3.getInvoiceRef());
        final ChequeDeedFicheDetailListFragment chequeDeedFicheDetailListFragment4 = mFragment;
        Intrinsics.checkNotNull(chequeDeedFicheDetailListFragment4);
        final FragmentActivity activity = chequeDeedFicheDetailListFragment4.getActivity();
        Intrinsics.checkNotNull(activity);
        activity.startActivityForResult(intent, 2);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void initDisplayOptions(final Context context) {
        if (this.isAttached()) {
            this.notifyDataSetChanged();
        }
    }

    public void bindData(final ChequeDeedViewHolder holder, final ChequeDeedFicheDetail property) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(property, "property");
        final TextView txtTutar = holder.getTxtTutar();
        final String ficheStringProp = property.getTotal().toString();
        Intrinsics.checkNotNullExpressionValue(ficheStringProp, "toString(...)");
        txtTutar.setText(StringUtils.dFormat(ficheStringProp));
        holder.getTxtAccountNo().setText(property.getBankName().toString());
        holder.getTxtBranch().setText(property.getBankBranchName().toString());
        holder.getTxtExpiry().setText(property.getDueDate().toString());
        holder.getTxtDebtor().setText(property.getDebited().toString());
    }

    public void setChecked(final int i2, final View holderView) {
        Intrinsics.checkNotNullParameter(holderView, "holderView");
        if (this.isSelected(i2)) {
            holderView.setBackgroundColor(mHighlightColor);
        } else {
            holderView.setBackgroundColor(mBackgroundColor);
        }
    }

    public void clear(final ChequeDeedViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.reset();
    }

    private ChequeDeedFicheDetail getChequeDeedDetail(final int i2) {
        final ArrayList<ChequeDeedFicheDetail> arrayList = mChequeDeedFicheDetail;
        Intrinsics.checkNotNull(arrayList);
        final ChequeDeedFicheDetail chequeDeedFicheDetail = arrayList.get(i2);
        Intrinsics.checkNotNullExpressionValue(chequeDeedFicheDetail, "get(...)");
        return chequeDeedFicheDetail;
    }

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter, com.proje.mobilesales.features.adapter.IListRecyclerView
    public Bundle saveState() {
        return new Bundle();
    }

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter
    private void clearViewHolder(final ChequeDeedViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.reset();
    }

    
    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter
    protected ChequeDeedFicheDetail getItem(final int i2) {
        final ArrayList<ChequeDeedFicheDetail> arrayList = mChequeDeedFicheDetail;
        if (null == arrayList) {
            return null;
        }
        Intrinsics.checkNotNull(arrayList);
        return arrayList.get(i2);
    }

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter
    private void handleItemClick(final ChequeDeedFicheDetail item, final ChequeDeedViewHolder holder) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (this.isSelected(holder.getAdapterPosition())) {
            this.notifyItemChanged(holder.getAdapterPosition());
            final int i2 = mLastSelectedPosition;
            if (0 <= i2) {
                this.notifyItemChanged(i2);
            }
            mLastSelectedPosition = holder.getAdapterPosition();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        final ArrayList<ChequeDeedFicheDetail> arrayList = mChequeDeedFicheDetail;
        Intrinsics.checkNotNull(arrayList);
        return arrayList.size();
    }

    public void setmFragment(final ChequeDeedFicheDetailListFragment chequeDeedFicheDetailListFragment) {
        mFragment = chequeDeedFicheDetailListFragment;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setChequeDeedDetailList(final ArrayList<ChequeDeedFicheDetail> arrayList) {
        if (null == arrayList || arrayList.isEmpty()) {
            return;
        }
        mChequeDeedFicheDetail = arrayList;
        if (0 != this.mSelected.size()) {
            final ArrayList arrayList2 = new ArrayList();
            Collections.sort(arrayList2);
            mSelected.clear();
            int size = arrayList2.size() - 1;
            if (0 > size) {
                return;
            }
            while (true) {
                final int i2 = size - 1;
                this.notifyItemRemoved(((Number) arrayList2.get(size)).intValue());
                if (0 > i2) {
                    return;
                } else {
                    size = i2;
                }
            }
        } else {
            final int i3 = mPendingAdd;
            if (0 <= i3) {
                this.notifyItemInserted(i3);
                mPendingAdd = -1;
            } else {
                this.notifyDataSetChanged();
            }
        }
    }

    public void setmFicheMode(final FicheMode ficheMode) {
        mFicheMode = ficheMode;
        this.setSingleSwipe();
    }

    private void setSingleSwipe() {
        if (mFicheMode != FicheMode.ANALYSE) {
            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback() { // from class: com.proje.mobilesales.features.collections.chequeanddeedfiche.adapter.ChequeDeedDetailListRecyclerViewAdaptersetSingleSwipe1
                @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
                public boolean onMove(final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder, final RecyclerView.ViewHolder target) {
                    Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                    Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
                    Intrinsics.checkNotNullParameter(target, "target");
                    return false;
                }

                {
                    super(0, 12);
                }

                @Override // androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
                public int getSwipeDirs(final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder) {
                    final ActionModeDelegate actionModeDelegate;
                    Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                    Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
                    actionModeDelegate = mActionModeDelegate;
                    Intrinsics.checkNotNull(actionModeDelegate);
                    if (actionModeDelegate.isInActionMode()) {
                        return 0;
                    }
                    return super.getSwipeDirs(recyclerView, viewHolder);
                }

                @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
                public void onSwiped(final RecyclerView.ViewHolder viewHolder, final int i2) {
                    Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
                    dismiss(viewHolder.getAdapterPosition());
                }
            }).attachToRecyclerView(mRecyclerView);
        }
    }

    
    public void dismiss(int i2) {
        ChequeDeedFicheDetail item = this.getItem(i2);
        final ArrayList<ChequeDeedFicheDetail> arrayList = mChequeDeedFicheDetail;
        if (null == arrayList || arrayList.isEmpty()) {
            return;
        }
        final ArrayList<ChequeDeedFicheDetail> arrayList2 = mChequeDeedFicheDetail;
        Intrinsics.checkNotNull(arrayList2);
        arrayList2.remove(i2);
        this.notifyItemRemoved(i2);
        final ChequeDeedFicheDetailListFragment chequeDeedFicheDetailListFragment = mFragment;
        Intrinsics.checkNotNull(chequeDeedFicheDetailListFragment);
        final ChequeAndDeedFicheActivity chequeDeedFicheActivity = chequeDeedFicheDetailListFragment.getChequeDeedFicheActivity();
        if (null != chequeDeedFicheActivity) {
            chequeDeedFicheActivity.setTotalLayoutWithNotify();
        }
        Snackbar.make(mRecyclerView, R.string.str_remove_at_detail_list, 0).setAction(R.string.str_undo, new View.OnClickListener() { // from class: com.proje.mobilesales.features.collections.chequeanddeedfiche.adapter.ChequeDeedDetailListRecyclerViewAdapterExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                dismisslambda2(ChequeDeedDetailListRecyclerViewAdapter.this, i2, item, view);
            }
        }).show();
    }

    
    public static void dismisslambda2(final ChequeDeedDetailListRecyclerViewAdapter this0, final int i2, final ChequeDeedFicheDetail chequeDeedFicheDetail, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.mPendingAdd = i2;
        final ArrayList<ChequeDeedFicheDetail> arrayList = this0.mChequeDeedFicheDetail;
        Intrinsics.checkNotNull(arrayList);
        Intrinsics.checkNotNull(chequeDeedFicheDetail);
        arrayList.add(i2, chequeDeedFicheDetail);
        this0.notifyItemInserted(i2);
        final ChequeDeedFicheDetailListFragment chequeDeedFicheDetailListFragment = this0.mFragment;
        Intrinsics.checkNotNull(chequeDeedFicheDetailListFragment);
        final ChequeAndDeedFicheActivity chequeDeedFicheActivity = chequeDeedFicheDetailListFragment.getChequeDeedFicheActivity();
        if (null != chequeDeedFicheActivity) {
            chequeDeedFicheActivity.setTotalLayoutWithNotify();
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void toggle(final int i2) {
        if (this.isSelected(i2)) {
            mSelected.delete(i2);
        } else {
            mSelected.put(i2, true);
        }
        this.notifyDataSetChanged();
    }

    
    @SuppressLint("NotifyDataSetChanged")
    public void removeSelection() {
        final ArrayList<ChequeDeedFicheDetail> arrayList = mChequeDeedFicheDetail;
        if (null == arrayList || arrayList.isEmpty()) {
            return;
        }
        int size = mSelected.size();
        while (true) {
            size--;
            if (-1 >= size) {
                break;
            }
            if (mSelected.valueAt(size)) {
                final ChequeDeedFicheDetail item = this.getItem(mSelected.keyAt(size));
                Intrinsics.checkNotNull(item);
                final ArrayList<ChequeDeedFicheDetail> arrayList2 = mChequeDeedFicheDetail;
                Intrinsics.checkNotNull(arrayList2);
                arrayList2.remove(item);
            }
        }
        final ChequeDeedFicheDetailListFragment chequeDeedFicheDetailListFragment = mFragment;
        Intrinsics.checkNotNull(chequeDeedFicheDetailListFragment);
        final ChequeAndDeedFicheActivity chequeDeedFicheActivity = chequeDeedFicheDetailListFragment.getChequeDeedFicheActivity();
        if (null != chequeDeedFicheActivity) {
            chequeDeedFicheActivity.setTotalLayoutWithNotify();
        }
        mSelected.clear();
        this.notifyDataSetChanged();
    }

    /* compiled from: ChequeDeedDetailListRecyclerViewAdapter.kt */
    public static final class ChequeDeedViewHolder extends ItemViewHolder {
        private final LinearLayout lnDetailContainer;
        private final TextView txtAccountNo;
        private final TextView txtBranch;
        private final TextView txtDebtor;
        private final TextView txtExpiry;
        private final TextView txtTutar;

        
        public ChequeDeedViewHolder(final View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            final View findViewById = itemView.findViewById(R.id.ln_detailContainer);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            lnDetailContainer = (LinearLayout) findViewById;
            final View findViewById2 = itemView.findViewById(R.id.txt_amount);
            Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
            txtTutar = (TextView) findViewById2;
            final View findViewById3 = itemView.findViewById(R.id.txt_expiry);
            Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
            txtExpiry = (TextView) findViewById3;
            final View findViewById4 = itemView.findViewById(R.id.txt_branch);
            Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.TextView");
            txtBranch = (TextView) findViewById4;
            final View findViewById5 = itemView.findViewById(R.id.txt_accountNo);
            Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.TextView");
            txtAccountNo = (TextView) findViewById5;
            final View findViewById6 = itemView.findViewById(R.id.txt_debtor);
            Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type android.widget.TextView");
            txtDebtor = (TextView) findViewById6;
        }

        public TextView getTxtTutar() {
            return txtTutar;
        }

        public TextView getTxtExpiry() {
            return txtExpiry;
        }

        public TextView getTxtBranch() {
            return txtBranch;
        }

        public TextView getTxtAccountNo() {
            return txtAccountNo;
        }

        public TextView getTxtDebtor() {
            return txtDebtor;
        }

        public void reset() {
            txtTutar.setText("");
            txtExpiry.setText("");
            txtBranch.setText("");
            txtAccountNo.setText("");
            txtDebtor.setText("");
        }
    }
}
