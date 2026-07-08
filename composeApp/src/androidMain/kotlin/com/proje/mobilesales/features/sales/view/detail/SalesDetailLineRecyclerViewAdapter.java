package com.proje.mobilesales.features.sales.view.detail;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.view.ActionMode;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import com.google.common.base.Strings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.interfaces.ActionModeDelegate;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.AlternativeItemForCampaignPromotion;
import com.proje.mobilesales.core.interfaces.ItemSurplusDiscountListener;
import com.proje.mobilesales.core.interfaces.PopupMenu;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.MenuTintDelegate;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter;
import com.proje.mobilesales.features.dbmodel.Units;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.repository.SalesDetailRepository;
import com.proje.mobilesales.features.sales.view.variant.SalesVariantActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

public final class SalesDetailLineRecyclerViewAdapter extends ListRecyclerViewAdapter<SalesDetailLineViewHolder, SalesDetail> {
    public AlternativeItemForCampaignPromotion alternativeItemForCampaignPromotion;
    private boolean isShowCurrencyPrice;
    public ItemSurplusDiscountListener itemSurplusDiscountListener;
    private final ActionMode.Callback mActionModeCallback;
    ActionModeDelegate mActionModeDelegate;
    private AlertDialogBuilder<?> mAlertDialogBuilder;
    private FicheMode mFicheMode;
    private SalesDetailListFragment mFragment;
    public MenuTintDelegate mMenuTintDelegate;
    private int mPendingAdd;
    private PopupMenu mPopupMenu;
    private ArrayList<SalesDetail> mSalesDetails;
    private boolean mVatCanBeChange;
    private boolean mVatDefaultChecked;
    private final SalesDetailRepository repository;
    private final SalesDetailViewModel viewModel;
    protected void bindItem(final SalesDetailLineViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
    }

    protected boolean isItemAvailable(final SalesDetail salesDetail) {
        return null != salesDetail;
    }

    public SalesDetailLineRecyclerViewAdapter(final ActionModeDelegate actionModeDelegate) {
        mActionModeDelegate = actionModeDelegate;
        final SalesDetailRepository salesDetailRepository = new SalesDetailRepository();
        repository = salesDetailRepository;
        viewModel = new SalesDetailViewModel(salesDetailRepository);
        mPendingAdd = -1;
        mActionModeCallback = new SalesDetailLineRecyclerViewAdaptermActionModeCallback1(this);
    }

    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        return mAlertDialogBuilder;
    }

    public void setMAlertDialogBuilder(final AlertDialogBuilder<?> alertDialogBuilder) {
        mAlertDialogBuilder = alertDialogBuilder;
    }

    public PopupMenu getMPopupMenu() {
        return mPopupMenu;
    }

    public void setMPopupMenu(final PopupMenu popupMenu) {
        mPopupMenu = popupMenu;
    }

    public int getMPendingAdd() {
        return mPendingAdd;
    }

    public void setMPendingAdd(final int i2) {
        mPendingAdd = i2;
    }

    public boolean getMVatCanBeChange() {
        return mVatCanBeChange;
    }

    public void setMVatCanBeChange(final boolean z) {
        mVatCanBeChange = z;
    }

    public boolean getMVatDefaultChecked() {
        return mVatDefaultChecked;
    }

    public void setMVatDefaultChecked(final boolean z) {
        mVatDefaultChecked = z;
    }

    public boolean isShowCurrencyPrice() {
        return isShowCurrencyPrice;
    }

    public void setShowCurrencyPrice(final boolean z) {
        isShowCurrencyPrice = z;
    }

    public FicheMode getMFicheMode() {
        return mFicheMode;
    }

    public void setMFicheMode(final FicheMode ficheMode) {
        mFicheMode = ficheMode;
    }

    public SalesDetailListFragment getMFragment() {
        return mFragment;
    }

    public void setMFragment(final SalesDetailListFragment salesDetailListFragment) {
        mFragment = salesDetailListFragment;
    }

    public ArrayList<SalesDetail> getMSalesDetails() {
        return mSalesDetails;
    }

    public void setMSalesDetails(final ArrayList<SalesDetail> arrayList) {
        mSalesDetails = arrayList;
    }

    public MenuTintDelegate getMMenuTintDelegate() {
        final MenuTintDelegate menuTintDelegate = mMenuTintDelegate;
        if (null != menuTintDelegate) {
            return menuTintDelegate;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mMenuTintDelegate");
        return null;
    }

    public void setMMenuTintDelegate(final MenuTintDelegate menuTintDelegate) {
        Intrinsics.checkNotNullParameter(menuTintDelegate, "<set-?>");
        mMenuTintDelegate = menuTintDelegate;
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        final Context context = mContext;
        final Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mAlertDialogBuilder = new AlertDialogBuilder.Impl(context, (BaseInjectableActivity) activity);
        mPopupMenu = new PopupMenu.Impl();
        final Context context2 = recyclerView.getContext();
        mContext = context2;
        if (context2 instanceof BaseInjectableActivity) {
            Intrinsics.checkNotNull(context2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
            ((BaseInjectableActivity) context2).getActivityComponent().inject(this);
        }
        this.setMMenuTintDelegate(new MenuTintDelegate());
        this.getMMenuTintDelegate().onActivityCreated(mContext);
    }
    public void dismiss(int i2) {
        final Boolean bool;
        SalesDetail item = this.getItem(i2);
        final ArrayList<SalesDetail> arrayList = mSalesDetails;
        if (null != arrayList) {
            Intrinsics.checkNotNull(arrayList);
            if (0 == arrayList.size()) {
                return;
            }
            final SalesDetailListFragment salesDetailListFragment = mFragment;
            if (null != salesDetailListFragment) {
                Intrinsics.checkNotNull(item);
                bool = Boolean.valueOf(salesDetailListFragment.checkShouldReApplyCampaignForDelete(item));
            } else {
                bool = null;
            }
            final ArrayList<SalesDetail> arrayList2 = mSalesDetails;
            Intrinsics.checkNotNull(arrayList2);
            arrayList2.remove(i2);
            this.notifyItemRemoved(i2);
            final SalesDetailListFragment salesDetailListFragment2 = mFragment;
            if (null != salesDetailListFragment2) {
                salesDetailListFragment2.setTotalLayoutWithNotify();
            }
            if (Intrinsics.areEqual(bool, Boolean.TRUE)) {
                final SalesDetailListFragment salesDetailListFragment3 = mFragment;
                if (null != salesDetailListFragment3) {
                    salesDetailListFragment3.notifyReApplyCampaign();
                    return;
                }
                return;
            }
            final Snackbar action = Snackbar.make(mRecyclerView, R.string.str_remove_at_product_list, 0).setAction(R.string.str_undo, new View.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.detail.SalesDetailLineRecyclerViewAdapterExternalSyntheticLambda5

                public void onClick(final View view) {
                    dismisslambda0(SalesDetailLineRecyclerViewAdapter.this, i2, item, view);
                }
            });
            Intrinsics.checkNotNullExpressionValue(action, "setAction(...)");
            final View view = action.getView();
            Intrinsics.checkNotNullExpressionValue(view, "getView(...)");
            ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(ContextCompat.getColor(mContext, R.color.white));
            ((TextView) view.findViewById(R.id.snackbar_action)).setTextColor(ContextCompat.getColor(mContext, R.color.colorAccentLight));
            action.show();
        }
    }

    public static void dismisslambda0(final SalesDetailLineRecyclerViewAdapter this0, final int i2, final SalesDetail salesDetail, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.mPendingAdd = i2;
        final ArrayList<SalesDetail> arrayList = this0.mSalesDetails;
        Intrinsics.checkNotNull(arrayList);
        Intrinsics.checkNotNull(salesDetail);
        arrayList.add(i2, salesDetail);
        this0.notifyItemInserted(i2);
        final SalesDetailListFragment salesDetailListFragment = this0.mFragment;
        if (null != salesDetailListFragment) {
            salesDetailListFragment.setTotalLayoutWithNotify();
        }
    }
    public void onDetachedFromRecyclerView(final RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onDetachedFromRecyclerView(recyclerView);
        mActionModeDelegate = null;
    }
    public long getItemId(final int i2) {
        final SalesDetail item = this.getItem(i2);
        if (null != item) {
            return item.getId();
        }
        return 0L;
    }
    public SalesDetailLineViewHolder onCreateViewHolder(final ViewGroup parent, final int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        final View inflate = mInflater.inflate(R.layout.item_sales_line, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new SalesDetailLineViewHolder(inflate);
    }
    public int getItemCount() {
        final ArrayList<SalesDetail> arrayList = mSalesDetails;
        if (null == arrayList) {
            return 0;
        }
        Intrinsics.checkNotNull(arrayList);
        return arrayList.size();
    }

    protected SalesDetail getItem(final int i2) {
        final ArrayList<SalesDetail> arrayList = mSalesDetails;
        if (null == arrayList) {
            return null;
        }
        Intrinsics.checkNotNull(arrayList);
        return arrayList.get(i2);
    }
    protected void handleItemClick(final SalesDetail salesDetail, final SalesDetailLineViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (this.isSelected(holder.getBindingAdapterPosition())) {
            this.notifyItemChanged(holder.getBindingAdapterPosition());
            final int i2 = mLastSelectedPosition;
            if (0 <= i2) {
                this.notifyItemChanged(i2);
            }
            mLastSelectedPosition = holder.getBindingAdapterPosition();
        }
    }
    protected void clearViewHolder(final SalesDetailLineViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.mSalesDetailLineView.reset();
    }

    public void onBindViewHolder(SalesDetailLineViewHolder holder, @SuppressLint("RecyclerView") int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        SalesDetail item = this.getItem(i2);
        if (this.isItemAvailable(item)) {
            final SalesDetailLineView salesDetailLineView = holder.mSalesDetailLineView;
            Intrinsics.checkNotNull(item);
            salesDetailLineView.setNegativeStockAlert(item.isNegativeStockAlertControl());
            holder.mSalesDetailLineView.setVaryantProductAlert(SalesUtils.isVaryantProductAlert(item, viewModel.erpType()));
            holder.mSalesDetailLineView.setSalesDetail(item);
            holder.mSalesDetailLineView.setChecked(this.isSelected(i2));
            final ImageButton btnProductInfo = holder.mSalesDetailLineView.getBtnProductInfo();
            Intrinsics.checkNotNullExpressionValue(btnProductInfo, "getBtnProductInfo(...)");
            this.setVisibilityProductInfoButton(btnProductInfo, item.getItemRef());
            final TextView txtProductTotalWithCurrency = holder.mSalesDetailLineView.getTxtProductTotalWithCurrency();
            Intrinsics.checkNotNullExpressionValue(txtProductTotalWithCurrency, "getTxtProductTotalWithCurrency(...)");
            String str = item.curCodeStr;
            if (null == str) {
                str = "";
            }
            this.setVisibilityCurrencyPriceText(txtProductTotalWithCurrency, str);
            final SalesDetailLineView salesDetailLineView2 = holder.mSalesDetailLineView;
            final ImageView btnProductOperation = salesDetailLineView2.getBtnProductOperation();
            Intrinsics.checkNotNullExpressionValue(btnProductOperation, "getBtnProductOperation(...)");
            this.setProductOperationClickListener(salesDetailLineView2, btnProductOperation, item, i2);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view) {
                    onBindViewHolderlambda1(SalesDetailLineRecyclerViewAdapter.this, item, i2, holder, view);
                }
            });
            final SalesDetailListFragment salesDetailListFragment = mFragment;
            Intrinsics.checkNotNull(salesDetailListFragment);
            if (SalesUtils.isSalesTypeOrder(salesDetailListFragment.getSalesType())) {
                return;
            }
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(final View view) {
                    final boolean onBindViewHolderlambda2;
                    onBindViewHolderlambda2 = onBindViewHolderlambda2(SalesDetailLineRecyclerViewAdapter.this, item, holder, view);
                    return onBindViewHolderlambda2;
                }
            });
        }
    }

    public static void onBindViewHolderlambda1(final SalesDetailLineRecyclerViewAdapter this0, final SalesDetail salesDetail, final int i2, final SalesDetailLineViewHolder holder, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(holder, "holder");
        final ActionModeDelegate actionModeDelegate = this0.mActionModeDelegate;
        final Boolean valueOf = null != actionModeDelegate ? Boolean.valueOf(actionModeDelegate.isInActionMode()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (!valueOf.booleanValue()) {
            if (!salesDetail.isHasVariant() || -1 != salesDetail.getVariant().getLogicalRef()) {
                this0.openItem(salesDetail, i2);
                return;
            }
            final List<Units> tableForUnitsFromSqlHelper = this0.viewModel.getTableForUnitsFromSqlHelper(Units.class, "LOGICALREF=?", new String[]{String.valueOf(salesDetail.getUnit().getLogicalRef())});
            boolean z = null != tableForUnitsFromSqlHelper && !tableForUnitsFromSqlHelper.isEmpty() && 1 == tableForUnitsFromSqlHelper.get(0).getDivUnit();
            this0.openMultiVariantSelect(salesDetail, z);
            return;
        }
        this0.toggle(salesDetail.getName(), holder.getBindingAdapterPosition());
    }
    public static boolean onBindViewHolderlambda2(final SalesDetailLineRecyclerViewAdapter this0, final SalesDetail salesDetail, final SalesDetailLineViewHolder holder, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(holder, "holder");
        final ActionModeDelegate actionModeDelegate = this0.mActionModeDelegate;
        Intrinsics.checkNotNull(actionModeDelegate);
        if (!actionModeDelegate.startActionMode(this0.mActionModeCallback)) {
            return false;
        }
        this0.toggle(salesDetail.getName(), holder.getBindingAdapterPosition());
        return true;
    }

    private void openMultiVariantSelect(final SalesDetail salesDetail, final boolean z) {
        final int logicalRef;
        final Intent intent = new Intent(mContext, SalesVariantActivity.class);
        intent.putExtra(SalesVariantActivity.EXTRA_ITEM_REF, salesDetail.getItemRef());
        intent.putExtra(SalesVariantActivity.EXTRA_ITEM_CODE, salesDetail.getCode());
        intent.putExtra(SalesVariantActivity.EXTRA_VARIANT_REF, salesDetail.getVariant().getLogicalRef());
        intent.putExtra(SalesVariantActivity.EXTRA_VARIANT_CODE, salesDetail.getVariant().getCode());
        intent.putExtra(SalesVariantActivity.EXTRA_DETAIL_ID, salesDetail.getId());
        intent.putExtra(SalesVariantActivity.EXTRA_DIV_UNIT, z);
        final String str = SalesVariantActivity.EXTRA_WHNR;
        final SalesDetailListFragment salesDetailListFragment = mFragment;
        Intrinsics.checkNotNull(salesDetailListFragment);
        if (SalesUtils.isSalesTypeWhTransfer(salesDetailListFragment.getSalesType())) {
            logicalRef = viewModel.user().getWarehouseNr();
        } else {
            final SalesDetailListFragment salesDetailListFragment2 = mFragment;
            Intrinsics.checkNotNull(salesDetailListFragment2);
            logicalRef = salesDetailListFragment2.getmSales().getWareHouse().getLogicalRef();
        }
        intent.putExtra(str, logicalRef);
        final SalesDetailListFragment salesDetailListFragment3 = mFragment;
        Intrinsics.checkNotNull(salesDetailListFragment3);
        salesDetailListFragment3.startActivityForResult(intent, 103);
    }

    private void openItem(final SalesDetail salesDetail, final int i2) {
        final int logicalRef;
        final int logicalRef2;
        final int logicalRef3;
        if (null == this.mFragment) {
            return;
        }
        final Intent intent = new Intent(mContext, SalesDetailActivity.class);
        final String str = SalesDetailActivity.EXTRA_CUSTOMER_REF;
        final SalesDetailListFragment salesDetailListFragment = mFragment;
        Intrinsics.checkNotNull(salesDetailListFragment);
        intent.putExtra(str, salesDetailListFragment.getCustomerRef());
        intent.putExtra(SalesDetailActivity.EXTRA_SALES_DETAIL, salesDetail);
        intent.putExtra(SalesDetailActivity.EXTRA_SALES_DETAIL_POSITION, i2);
        final String str2 = SalesDetailActivity.EXTRA_FICHE_DETAIL_FIELDS;
        final SalesDetailListFragment salesDetailListFragment2 = mFragment;
        Intrinsics.checkNotNull(salesDetailListFragment2);
        intent.putExtra(str2, salesDetailListFragment2.getmSalesFicheDetail());
        intent.putExtra(SalesDetailActivity.EXTRA_SALES_DETAIL_LIST_SIZE, this.doPromotionCondition(i2));
        final String str3 = SalesDetailActivity.EXTRA_FICHE_USER_RIGHTS;
        final SalesDetailListFragment salesDetailListFragment3 = mFragment;
        Intrinsics.checkNotNull(salesDetailListFragment3);
        intent.putExtra(str3, salesDetailListFragment3.getSalesFicheUserRights());
        final String str4 = SalesDetailActivity.EXTRA_SALES_FICHE_MODE;
        final SalesDetailListFragment salesDetailListFragment4 = mFragment;
        Intrinsics.checkNotNull(salesDetailListFragment4);
        intent.putExtra(str4, salesDetailListFragment4.getSalesFicheMode());
        final String str5 = SalesDetailActivity.EXTRA_WAREHOUSE_NR;
        final SalesDetailListFragment salesDetailListFragment5 = mFragment;
        Intrinsics.checkNotNull(salesDetailListFragment5);
        if (SalesUtils.isSalesTypeWhTransfer(salesDetailListFragment5.getSalesType())) {
            logicalRef = viewModel.user().getWarehouseNr();
        } else {
            final SalesDetailListFragment salesDetailListFragment6 = mFragment;
            Intrinsics.checkNotNull(salesDetailListFragment6);
            logicalRef = salesDetailListFragment6.getmSales().getWareHouse().getLogicalRef();
        }
        intent.putExtra(str5, logicalRef);
        final String str6 = SalesDetailActivity.EXTRA_PAYMENT_REF;
        final SalesDetailListFragment salesDetailListFragment7 = mFragment;
        Intrinsics.checkNotNull(salesDetailListFragment7);
        intent.putExtra(str6, salesDetailListFragment7.getmSales().getPayPlan().getLogicalRef());
        final String str7 = SalesDetailActivity.EXTRA_TRADE_GROUP;
        final SalesDetailListFragment salesDetailListFragment8 = mFragment;
        Intrinsics.checkNotNull(salesDetailListFragment8);
        intent.putExtra(str7, salesDetailListFragment8.getmSales().getTradeGroup().getDefinition());
        final String str8 = SalesDetailActivity.EXTRA_PROJECT_REF;
        final SalesDetailListFragment salesDetailListFragment9 = mFragment;
        Intrinsics.checkNotNull(salesDetailListFragment9);
        intent.putExtra(str8, salesDetailListFragment9.getmSales().getProjectCode().getLogicalRef());
        intent.putExtra(SalesDetailActivity.EXTRA_VAT_CHANGE, this.mVatCanBeChange);
        final String str9 = SalesDetailActivity.EXTRA_VAT_DEFAULT;
        intent.putExtra(str9, this.mVatDefaultChecked);
        intent.putExtra(str9, this.mVatDefaultChecked);
        final String str10 = SalesDetailActivity.EXTRA_NOT_USE_GATTRIBKDV;
        final SalesDetailListFragment salesDetailListFragment10 = mFragment;
        Intrinsics.checkNotNull(salesDetailListFragment10);
        intent.putExtra(str10, salesDetailListFragment10.getmSales().isNotUseGattribKdv());
        final String str11 = SalesDetailActivity.EXTRA_SALES_CREATED_DATE;
        final SalesDetailListFragment salesDetailListFragment11 = mFragment;
        Intrinsics.checkNotNull(salesDetailListFragment11);
        intent.putExtra(str11, salesDetailListFragment11.getmSales().getFicheCreateDate());
        final String str12 = SalesDetailActivity.EXTRA_FACTORY_REF;
        final SalesDetailListFragment salesDetailListFragment12 = mFragment;
        Intrinsics.checkNotNull(salesDetailListFragment12);
        if (SalesUtils.isSalesTypeWhTransfer(salesDetailListFragment12.getSalesType())) {
            logicalRef2 = viewModel.user().getFactNr();
        } else {
            final SalesDetailListFragment salesDetailListFragment13 = mFragment;
            Intrinsics.checkNotNull(salesDetailListFragment13);
            logicalRef2 = salesDetailListFragment13.getmSales().getFactory().getLogicalRef();
        }
        intent.putExtra(str12, logicalRef2);
        final String str13 = SalesDetailActivity.EXTRA_BRANCH_REF;
        final SalesDetailListFragment salesDetailListFragment14 = mFragment;
        Intrinsics.checkNotNull(salesDetailListFragment14);
        if (SalesUtils.isSalesTypeWhTransfer(salesDetailListFragment14.getSalesType())) {
            logicalRef3 = viewModel.user().getBranchNr();
        } else {
            final SalesDetailListFragment salesDetailListFragment15 = mFragment;
            Intrinsics.checkNotNull(salesDetailListFragment15);
            logicalRef3 = salesDetailListFragment15.getmSales().getBranch().getLogicalRef();
        }
        intent.putExtra(str13, logicalRef3);
        final String str14 = SalesDetailActivity.EXTRA_HEADER_FIRSTDISCOUNT_CARD_CODE;
        final SalesDetailListFragment salesDetailListFragment16 = mFragment;
        Intrinsics.checkNotNull(salesDetailListFragment16);
        intent.putExtra(str14, salesDetailListFragment16.getmSales().getDiscountCard(0).getCode());
        try {
            final SalesDetailListFragment salesDetailListFragment17 = mFragment;
            Intrinsics.checkNotNull(salesDetailListFragment17);
            final Sales m1480clone = salesDetailListFragment17.getmSales().clone();
            final ArrayList<SalesDetail> mSalesDetailList = m1480clone.getMSalesDetailList();
            if (null != mSalesDetailList) {
                mSalesDetailList.clear();
            }
            intent.putExtra(SalesDetailActivity.EXTRA_SALES_WITHOUT_DETAILS, m1480clone);
        } catch (final Exception e2) {
            Log.e("SalesLineRecyclerView", "Error on cloning sales", e2);
        }
        final SalesDetailListFragment salesDetailListFragment18 = mFragment;
        if (null != salesDetailListFragment18) {
            salesDetailListFragment18.startActivityForResult(intent, 102);
        }
    }

    private boolean doPromotionCondition(final int i2) {
        final ArrayList<SalesDetail> arrayList = mSalesDetails;
        if (null == arrayList) {
            return false;
        }
        Intrinsics.checkNotNull(arrayList);
        return 1 < arrayList.size() && 0 != i2;
    }

    public void setSalesDetails(final ArrayList<SalesDetail> arrayList) {
        if (null == arrayList) {
            return;
        }
        mSalesDetails = arrayList;
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

    public void setmFragment(final SalesDetailListFragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        mFragment = fragment;
    }

    public void setVatParams(final boolean z, final boolean z2) {
        mVatCanBeChange = z;
        mVatDefaultChecked = z2;
    }

    public boolean ismVatCanBeChange() {
        return mVatCanBeChange;
    }

    public boolean ismVatDefaultChecked() {
        return mVatDefaultChecked;
    }

    private void toggle(final String str, final int i2) {
        if (this.isSelected(i2)) {
            mSelected.delete(i2);
        } else {
            mSelected.put(i2, true);
        }
        this.notifyItemChanged(i2);
    }

    public void removeSelection() {
        final ArrayList<SalesDetail> arrayList = mSalesDetails;
        if (null != arrayList) {
            Intrinsics.checkNotNull(arrayList);
            if (0 == arrayList.size()) {
                return;
            }
            boolean z = false;
            for (int size = mSelected.size() - 1; -1 < size; size--) {
                if (mSelected.valueAt(size)) {
                    final SalesDetail item = this.getItem(mSelected.keyAt(size));
                    if (!z) {
                        final SalesDetailListFragment salesDetailListFragment = mFragment;
                        Intrinsics.checkNotNull(salesDetailListFragment);
                        Intrinsics.checkNotNull(item);
                        z = salesDetailListFragment.checkShouldReApplyCampaignForDelete(item);
                    }
                    final ArrayList<SalesDetail> arrayList2 = mSalesDetails;
                    Intrinsics.checkNotNull(arrayList2);
                    TypeIntrinsics.asMutableCollection(arrayList2).remove(item);
                }
            }
            final SalesDetailListFragment salesDetailListFragment2 = mFragment;
            if (null != salesDetailListFragment2) {
                salesDetailListFragment2.setTotalLayoutWithNotify();
            }
            mSelected.clear();
            this.notifyDataSetChanged();
            if (z) {
                final SalesDetailListFragment salesDetailListFragment3 = mFragment;
                Intrinsics.checkNotNull(salesDetailListFragment3);
                salesDetailListFragment3.notifyReApplyCampaign();
            }
        }
    }

    public void setmFicheMode(final FicheMode ficheMode) {
        mFicheMode = ficheMode;
        this.setSingleSwipe();
    }

    private void setSingleSwipe() {
        if (mFicheMode != FicheMode.ANALYSE) {
            new ItemTouchHelper(new SimpleCallback() {
                public boolean onMove(final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder, final RecyclerView.ViewHolder target) {
                    Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                    Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
                    Intrinsics.checkNotNullParameter(target, "target");
                    return false;
                }
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
                public void onSwiped(final RecyclerView.ViewHolder viewHolder, final int i2) {
                    Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
                    dismiss(viewHolder.getBindingAdapterPosition());
                }
            }).attachToRecyclerView(mRecyclerView);
        }
    }

    public void setItemSurplusDiscountListener(final ItemSurplusDiscountListener itemSurplusDiscountListener) {
        this.itemSurplusDiscountListener = itemSurplusDiscountListener;
    }

    public void setAlternativeItemForCampaignPromotion(final AlternativeItemForCampaignPromotion alternativeItemForCampaignPromotion) {
        this.alternativeItemForCampaignPromotion = alternativeItemForCampaignPromotion;
    }

    private void setVisibilityProductInfoButton(final ImageButton imageButton, int i2) {
        final int customerRef;
        final ISqlHelper<?> sqlHelper = viewModel.getSqlHelper();
        final SalesDetailListFragment salesDetailListFragment = mFragment;
        if (null == salesDetailListFragment) {
            customerRef = 0;
        } else {
            Intrinsics.checkNotNull(salesDetailListFragment);
            customerRef = salesDetailListFragment.getCustomerRef();
        }
        String customerConditionCode = sqlHelper.getCustomerConditionCode(customerRef);
        Intrinsics.checkNotNullExpressionValue(customerConditionCode, "getCustomerConditionCode(...)");
        if (viewModel.erpType() == ErpType.NETSIS && !TextUtils.isEmpty(customerConditionCode)) {
            imageButton.setVisibility(View.VISIBLE);
            imageButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view) {
                    setVisibilityProductInfoButtonlambda3(SalesDetailLineRecyclerViewAdapter.this, i2, customerConditionCode, view);
                }
            });
        } else {
            imageButton.setVisibility(View.GONE);
        }
    }
    public static void setVisibilityProductInfoButtonlambda3(final SalesDetailLineRecyclerViewAdapter this0, final int i2, final String customerConditionCode, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(customerConditionCode, "customerConditionCode");
        this0.setProductInfoClickListener(i2, customerConditionCode);
    }

    private void setVisibilityCurrencyPriceText(final TextView textView, final String str) {
        try {
            if (!isShowCurrencyPrice || Strings.isNullOrEmpty(str) || viewModel.getLocalCurrencyCode().equals(str)) {
                return;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        textView.setVisibility(View.VISIBLE);
    }

    private void setProductInfoClickListener(final int i2, final String str) {
        final ItemSurplusDiscountListener itemSurplusDiscountListener = this.itemSurplusDiscountListener;
        if (null != itemSurplusDiscountListener) {
            itemSurplusDiscountListener.getItemSurplusDiscount(i2, str);
        }
    }

    private void setProductOperationClickListener(final View view, ImageView imageView, SalesDetail salesDetail, int i2) {
        if (mFicheMode == FicheMode.ANALYSE || null == this.alternativeItemForCampaignPromotion || null == salesDetail) {
            return;
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view2) {
                setProductOperationClickListenerlambda5(SalesDetailLineRecyclerViewAdapter.this, imageView, salesDetail, i2, view2);
            }
        });
    }

    private void setProductOperationClickListenerlambda5(SalesDetailLineRecyclerViewAdapter this0, final ImageView operationView, SalesDetail salesDetail, int i2, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(operationView, "operationView");
        final PopupMenu popupMenu = this0.mPopupMenu;
        Intrinsics.checkNotNull(popupMenu);
        popupMenu.create(this0.mContext, operationView, 5).inflate(R.menu.menu_sales_line_operation).setMenuItemVisible(R.id.menu_alternative_promotion, true).setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(final MenuItem menuItem) {
                final boolean productOperationClickListenerlambda5lambda4;
                productOperationClickListenerlambda5lambda4 = setProductOperationClickListenerlambda5lambda4(SalesDetailLineRecyclerViewAdapter.this, salesDetail, i2, menuItem);
                return productOperationClickListenerlambda5lambda4;
            }
        }).show();
    }

    public static boolean setProductOperationClickListenerlambda5lambda4(final SalesDetailLineRecyclerViewAdapter this0, final SalesDetail salesDetail, final int i2, final MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (R.id.menu_alternative_promotion != menuItem.getItemId()) {
            return false;
        }
        final AlternativeItemForCampaignPromotion alternativeItemForCampaignPromotion = this0.alternativeItemForCampaignPromotion;
        Intrinsics.checkNotNull(alternativeItemForCampaignPromotion);
        alternativeItemForCampaignPromotion.getAlternativeItemForCampaignPromotion(salesDetail, i2);
        return true;
    }
}
