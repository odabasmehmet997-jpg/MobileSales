package com.proje.mobilesales.features.sales.view.validation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.StringRes;
import androidx.appcompat.view.ActionMode;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.DataObjectType;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.enums.OrderStatus;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.ActionModeDelegate;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.PopupMenu;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.sales.NetsisSlipType;
import com.proje.mobilesales.core.sql.ISqlBriteDatabase;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.MenuTintDelegate;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.adapter.EndlessListRecyclerViewAdapter;
import com.proje.mobilesales.features.adapter.LoadingViewHolder;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.dbmodel.ManagerOrder;
import com.proje.mobilesales.features.model.*;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.repository.SalesValidationRepository;
import com.proje.mobilesales.features.sales.view.list.SalesViewHolder;
import com.proje.mobilesales.features.sales.view.newadd.SalesActivityNew;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.proje.mobilesales.features.visit.model.VisitInfoShort;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class OrderValidationRecyclerViewAdapter extends EndlessListRecyclerViewAdapter<RecyclerView.ViewHolder, ManagerOrder> {
    public static final Companion Companion = new Companion(null);
    private static final String STATE_ITEM_LIST = "state:itemList";
    private final ActionMode.Callback mActionModeCallback;
    ActionModeDelegate mActionModeDelegate;
    private AlertDialogBuilder<?> mAlertDialogBuilder;
    MenuTintDelegate mMenuTintDelegate;
    private PopupMenu mPopupMenu;
    private ProgressDialogBuilder<?> mProgressDialogBuilder;
    private ArrayList<ManagerOrder> managerOrders;
    private final SalesValidationRepository repository;
    final SalesValidationViewModel viewModel;

    protected void bindItem(RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
    }

    protected void handleItemClick(Object item, RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(holder, "holder");
    }

    protected boolean isItemAvailable(Object obj) {
        return obj != null;
    }

    public OrderValidationRecyclerViewAdapter(ActionModeDelegate actionModeDelegate) {
        this.mActionModeDelegate = actionModeDelegate;
        SalesValidationRepository salesValidationRepository = new SalesValidationRepository();
        this.repository = salesValidationRepository;
        this.viewModel = new SalesValidationViewModel(salesValidationRepository);
        this.managerOrders = new ArrayList<>();
        this.mActionModeCallback = new OrderValidationRecyclerViewAdaptermActionModeCallback1(this);
    }

    public PopupMenu getMPopupMenu() {
        return this.mPopupMenu;
    }

    public void setMPopupMenu(PopupMenu popupMenu) {
        this.mPopupMenu = popupMenu;
    }

    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        return this.mAlertDialogBuilder;
    }

    public void setMAlertDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        this.mAlertDialogBuilder = alertDialogBuilder;
    }

    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        return this.mProgressDialogBuilder;
    }

    public void setMProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        this.mProgressDialogBuilder = progressDialogBuilder;
    }
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        Context context = this.mContext;
        if (context instanceof BaseInjectableActivity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
            ((BaseInjectableActivity) context).getActivityComponent().inject(this);
        }
        Context context2 = this.mContext;
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(context2, (BaseInjectableActivity) activity);
        Context context3 = this.mContext;
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilder = new AlertDialogBuilder.Impl(context3, (BaseInjectableActivity) activity2);
        this.mPopupMenu = new PopupMenu.Impl();
        MenuTintDelegate menuTintDelegate = new MenuTintDelegate();
        this.mMenuTintDelegate = menuTintDelegate;
        Intrinsics.checkNotNull(menuTintDelegate);
        menuTintDelegate.onActivityCreated(this.mContext);
    }
     public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onDetachedFromRecyclerView(recyclerView);
        ArrayList<ManagerOrder> arrayList = this.managerOrders;
        if (arrayList != null) {
            Intrinsics.checkNotNull(arrayList);
            arrayList.clear();
        }
        this.mActionModeDelegate = null;
    }
     public long getItemId(int i2) {
        ManagerOrder item = getItem(i2);
        return item != null ? item.getId() : super.getItemId(i2);
    }
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (i2 == this.VIEW_TYPE_ITEM) {
            View inflate = this.mInflater.inflate(R.layout.item_sales_validation_view, parent, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
            return new SalesValidationViewHolder(inflate);
        }
        LoadingViewHolder loadingViewHolder = i2 == this.VIEW_TYPE_LOADING ? new LoadingViewHolder(this.mInflater.inflate(R.layout.item_loading, parent, false)) : null;
        Intrinsics.checkNotNull(loadingViewHolder);
        return loadingViewHolder;
    }

    public void onBindViewHolder(int i2, final RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder instanceof SalesValidationViewHolder salesValidationViewHolder4) {
            final ManagerOrder item = getItem(i2);
            if (this.mCardViewEnabled) {
                salesValidationViewHolder4.mCardView.setCardElevation(this.mCardElevation);
                salesValidationViewHolder4.mCardView.setRadius(this.mCardRadius);
                salesValidationViewHolder4.mCardView.setUseCompatPadding(true);
            } else {
                salesValidationViewHolder4.mCardView.setCardElevation(0.0f);
                salesValidationViewHolder4.mCardView.setRadius(0.0f);
                salesValidationViewHolder4.mCardView.setUseCompatPadding(false);
            }
            clearViewHolder(holder);
            if (isItemAvailable(item)) {
                SalesValidationView mSalesValidationView = salesValidationViewHolder4.getMSalesValidationView();
                Intrinsics.checkNotNull(item);
                mSalesValidationView.setSales(item);
                salesValidationViewHolder4.getMSalesValidationView().getMoreOption().setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        OrderValidationRecyclerViewAdapter.onBindViewHolderlambda0(OrderValidationRecyclerViewAdapter.this, holder, item, i2, view);
                    }
                });
            }
            salesValidationViewHolder4.getMSalesValidationView().setChecked(isSelected(i2));
            salesValidationViewHolder4.getMSalesValidationView().setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    OrderValidationRecyclerViewAdapter.onBindViewHolderlambda1(OrderValidationRecyclerViewAdapter.this, item, i2, view);
                }
            });
            salesValidationViewHolder4.getMSalesValidationView().setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    boolean onBindViewHolderlambda2;
                    onBindViewHolderlambda2 = OrderValidationRecyclerViewAdapter.onBindViewHolderlambda2(OrderValidationRecyclerViewAdapter.this, i2, view);
                    return onBindViewHolderlambda2;
                }
            });
            return;
        }
        if (holder instanceof LoadingViewHolder) {
            ((LoadingViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }
    public static void onBindViewHolderlambda0(OrderValidationRecyclerViewAdapter this0, RecyclerView.ViewHolder salesViewHolder, ManagerOrder managerOrder, int i2, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(salesViewHolder, "salesViewHolder");
        this0.showMoreOptions(((SalesValidationViewHolder) salesViewHolder).getMSalesValidationView(), managerOrder, i2);
    }

    public static void onBindViewHolderlambda1(OrderValidationRecyclerViewAdapter this0, ManagerOrder managerOrder, int i2, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        ActionModeDelegate actionModeDelegate = this0.mActionModeDelegate;
        Intrinsics.checkNotNull(actionModeDelegate);
        if (!actionModeDelegate.isInActionMode()) {
            Intrinsics.checkNotNull(managerOrder);
            this0.openItemAnalyse(managerOrder, i2);
        } else {
            this0.toggle(i2);
        }
    }

    public static boolean onBindViewHolderlambda2(OrderValidationRecyclerViewAdapter this0, int i2, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        ActionModeDelegate actionModeDelegate = this0.mActionModeDelegate;
        Intrinsics.checkNotNull(actionModeDelegate);
        if (!actionModeDelegate.startActionMode(this0.mActionModeCallback)) {
            return false;
        }
        this0.toggle(i2);
        return true;
    }

    private void showMoreOptions(View view, final ManagerOrder managerOrder, final int i2) {
        PopupMenu popupMenu = this.mPopupMenu;
        Intrinsics.checkNotNull(popupMenu);
        PopupMenu inflate = popupMenu.create(this.mContext, view, 5).inflate(R.menu.menu_context_order_validation);
        ErpType erpType = this.viewModel.erpType();
        ErpType erpType2 = ErpType.TIGER;
        PopupMenu menuItemVisible = inflate.setMenuItemVisible(R.id.menu_contextual_undispatchable, erpType == erpType2).setMenuItemVisible(R.id.menu_contextual_analyse, this.viewModel.erpType() == erpType2);
        ErpType erpType3 = this.viewModel.erpType();
        ErpType erpType4 = ErpType.NETSIS;
        menuItemVisible.setMenuItemVisible(R.id.menu_contextual_reject, erpType3 == erpType4).setMenuItemTitle(R.id.menu_contextual_dispatchable, this.viewModel.erpType() == erpType4 ? R.string.str_accept : R.string.menu_dispatchable).setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.proje.mobilesales.features.sales.view.validation.OrderValidationRecyclerViewAdapterExternalSyntheticLambda3
            @Override // com.proje.mobilesales.core.interfaces.PopupMenu.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                boolean showMoreOptionslambda3;
                showMoreOptionslambda3 = OrderValidationRecyclerViewAdapter.showMoreOptionslambda3(OrderValidationRecyclerViewAdapter.this, managerOrder, i2, menuItem);
                return showMoreOptionslambda3;
            }
        }).show();
    }

    public static boolean showMoreOptionslambda3(OrderValidationRecyclerViewAdapter this0, ManagerOrder managerOrder, int i2, MenuItem item) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == R.id.menu_contextual_analyse) {
            Intrinsics.checkNotNull(managerOrder);
            this0.openItemAnalyse(managerOrder, i2);
            return true;
        }
        if (item.getItemId() == R.id.menu_contextual_dispatchable) {
            ArrayList arrayList = new ArrayList();
            Intrinsics.checkNotNull(managerOrder);
            arrayList.add(Integer.valueOf(managerOrder.getLogicalRef()));
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(Integer.valueOf(i2));
            this0.updateStatus(arrayList, arrayList2, OrderStatus.DISPATCHABLE);
            return true;
        }
        if (item.getItemId() == R.id.menu_contextual_undispatchable) {
            ArrayList arrayList3 = new ArrayList();
            Intrinsics.checkNotNull(managerOrder);
            arrayList3.add(Integer.valueOf(managerOrder.getLogicalRef()));
            ArrayList arrayList4 = new ArrayList();
            arrayList4.add(Integer.valueOf(i2));
            this0.updateStatus(arrayList3, arrayList4, OrderStatus.UNDISPATCHABLE);
            return true;
        }
        if (item.getItemId() != R.id.menu_contextual_reject) {
            return false;
        }
        this0.rejectOrder(i2);
        return false;
    }

    private void openItemAnalyse(ManagerOrder managerOrder, int i2) {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.setMessage(this.mContext.getString(R.string.str_preparing)).setCancelable(false).show();
        ArrayList<?> arrayList = new ArrayList<>();
        arrayList.add(this.viewModel.erpType() == ErpType.NETSIS ? buildNetsisOrderKey(managerOrder) : String.valueOf(managerOrder.getLogicalRef()));
        this.viewModel.readOrderFiche(arrayList, DataObjectType.ADDORDER, initSales(managerOrder), null, new OrderValidationAnalyseCallBack(this, i2));
    }

    private String buildNetsisOrderKey(ManagerOrder managerOrder) {
        return NetsisSlipType.ftSSip.ordinal() + ';' + managerOrder.getFicheNo() + ';' + managerOrder.getcCode();
    }

    private Sales initSales(ManagerOrder managerOrder) {
        Sales sales = new Sales(SalesType.ORDER.getmValue());
        sales.setLogicalRef(managerOrder.getLogicalRef());
        sales.setOrderType(0);
        sales.setFicheNo(managerOrder.getFicheNo());
        sales.setFicheCreateDate(managerOrder.getDate());
        sales.setGDate(DateAndTimeUtils.getNowDateTime());
        sales.setLatitude(ContextUtils.getLatitude());
        sales.setLongitude(ContextUtils.getLongitude());
        String str = managerOrder.getcCode();
        if (str.length() == 0) {
            str = managerOrder.getcCode2();
        }
        Intrinsics.checkNotNullExpressionValue(str, "ifEmpty(...)");
        sales.setClCode(str);
        List<ClCard> tableForClCard = this.viewModel.getTableForClCard(ClCard.class, "CODE=?", new String[]{sales.getClCode()});
        if (tableForClCard != null && !tableForClCard.isEmpty()) {
            sales.setClRef(tableForClCard.get(0).getLogicalRef());
        }
        sales.setDivision(new FicheRefProp());
        sales.setFactory(new FicheRefProp());
        sales.setBranch(new FicheRefProp());
        sales.setWareHouse(new FicheRefProp());
        sales.setSpeCode(new FicheRefProp());
        sales.setWarrantyCode(new FicheRefProp());
        sales.setDocumentNo(new FicheStringProp());
        sales.setDocumentTrackingNo(new FicheStringProp());
        sales.setCustomerOrderNo(new FicheStringProp());
        sales.setDeliveryDate(new FicheDateProp());
        sales.setPaymentOrder(new FicheBooleanProp());
        sales.setExplanation1(new FicheStringProp());
        sales.setExplanation2(new FicheStringProp());
        sales.setExplanation3(new FicheStringProp());
        sales.setExplanation4(new FicheStringProp());
        sales.setShipAccount(new FicheDiscountRefProp());
        sales.setShipAddress(new FicheDiscountRefProp());
        sales.setShipAgent(new FicheRefProp());
        sales.setShipType(new FicheRefProp());
        sales.setTradeGroup(new FicheRefProp());
        sales.setPayPlan(new FicheDiscountRefProp());
        sales.setProjectCode(new FicheDiscountRefProp());
        sales.setLatitude(ContextUtils.getLatitude());
        sales.setLongitude(ContextUtils.getLongitude());
        sales.setConsignee(new FicheBooleanProp());
        sales.setIncludeVat(new FicheBooleanProp());
        try {
            sales.setSaleVatCanBeChange(this.viewModel.getVATEnabled());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        sales.setSaleVatDefaultChecked(this.viewModel.getVATDefaultValue());
        sales.setEInvoiceSGKDocumentNo(new FicheStringProp());
        sales.setTaxPayerCode(new FicheStringProp());
        sales.setTaxPayerName(new FicheStringProp());
        sales.setEInvoiceTypSgk(new FicheRefProp());
        sales.setBeginDate(new FicheDateProp());
        sales.setEndDate(new FicheDateProp());
        sales.setCabin(new FicheDiscountRefProp());
        sales.setEDocSerial(new FicheRefProp());
        sales.setEDespatch(new FicheBooleanProp());
        loadDefaultValue(sales, managerOrder);
        return sales;
    }

    private void loadDefaultValue(Sales sales, ManagerOrder managerOrder) {
        if (managerOrder.getBranchNr() == 0) {
            loadFicheDefaultParameter(sales.getBranch(), managerOrder.getBranchNr(), R.string.column_code, this.mContext.getString(R.string.qry_branch_where));
        }
        if (managerOrder.getDivisNr() == 0) {
            loadFicheDefaultParameter(sales.getDivision(), managerOrder.getDivisNr(), R.string.column_code, this.mContext.getString(R.string.qry_division_where));
        }
        if (managerOrder.getWareHouseNr() == 0) {
            loadFicheDefaultParameter(sales.getWareHouse(), managerOrder.getWareHouseNr(), R.string.column_code, this.mContext.getString(R.string.qry_warehouse_nr_where));
        }
        if (managerOrder.getFactNr() == 0) {
            loadFicheDefaultParameter(sales.getFactory(), managerOrder.getFactNr(), R.string.column_code, this.mContext.getString(R.string.qry_factory_where));
        }
    }

    private void loadFicheDefaultParameter(FicheRefProp ficheRefProp, int i2, @StringRes int i3, String str) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        if (i2 == -1) {
            return;
        }
        ficheRefProp.setLogicalRef(i2);
        Cursor cursor = null;
        try {
            try {
                ISqlBriteDatabase<?> sqlBriteDatabase = this.viewModel.getSqlBriteDatabase();
                Intrinsics.checkNotNull(str);
                cursor = sqlBriteDatabase.query(str, StringUtils.convertIntToString(i2));
                if (cursor.moveToFirst()) {
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex(this.mContext.getString(i3))));
                }
            } catch (Exception e2) {
                Log.e(MobileSales.TAG, "loadFicheDefaultParameter: ", e2);
                if (cursor == null) {
                    return;
                }
            }
            cursor.close();
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }
    protected void clearViewHolder(RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder instanceof SalesViewHolder) {
            ((SalesViewHolder) holder).mSalesView.reset();
        }
    }
    public void insertProgressBar() {
        ArrayList<ManagerOrder> arrayList = this.managerOrders;
        Intrinsics.checkNotNull(arrayList);
        if (arrayList.contains(null)) {
            return;
        }
        ArrayList<ManagerOrder> arrayList2 = this.managerOrders;
        Intrinsics.checkNotNull(arrayList2);
        arrayList2.add(null);
        notifyItemInserted(getItemCount() - 1);
    }
    public void removeProgressBar() {
        ArrayList<ManagerOrder> arrayList = this.managerOrders;
        Intrinsics.checkNotNull(arrayList);
        int indexOf = arrayList.indexOf(null);
        if (indexOf != -1) {
            ArrayList<ManagerOrder> arrayList2 = this.managerOrders;
            Intrinsics.checkNotNull(arrayList2);
            arrayList2.remove(indexOf);
            notifyItemRemoved(indexOf);
            notifyDataSetChanged();
        }
    }

    public void addItem(ArrayList<ArrayList<VisitInfoShort>> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        removeProgressBar();
        ArrayList<ManagerOrder> arrayList = this.managerOrders;
        Intrinsics.checkNotNull(arrayList);
        arrayList.addAll(items);
        notifyDataSetChanged();
    }
    protected void restartAdapterAndScroll() {
        if (getItemCount() > 0) {
            ArrayList<ManagerOrder> arrayList = this.managerOrders;
            Intrinsics.checkNotNull(arrayList);
            arrayList.clear();
        }
        notifyDataSetChanged();
    }
    protected ManagerOrder getItem(int i2) {
        ArrayList<ManagerOrder> arrayList = this.managerOrders;
        if (arrayList == null) {
            return null;
        }
        Intrinsics.checkNotNull(arrayList);
        return arrayList.get(i2);
    }
     public Bundle saveState() {
        Bundle saveState = super.saveState();
        saveState.putParcelableArrayList(STATE_ITEM_LIST, this.managerOrders);
        Intrinsics.checkNotNull(saveState);
        return saveState;
    }
    public void restoreState(Bundle savedState) {
        Intrinsics.checkNotNullParameter(savedState, "savedState");
        super.restoreState(savedState);
        this.managerOrders = savedState.getParcelableArrayList(STATE_ITEM_LIST);
    }
    public int getItemCount() {
        ArrayList<ManagerOrder> arrayList = this.managerOrders;
        if (arrayList == null) {
            return 0;
        }
        Intrinsics.checkNotNull(arrayList);
        return arrayList.size();
    }

    private void updateStatus(List<Integer> list, List<Integer> list2, OrderStatus orderStatus) {
        showLoadingProgress();
        if (this.viewModel.erpType() == ErpType.NETSIS) {
            ArrayList arrayList = new ArrayList();
            Iterator<Integer> it = list2.iterator();
            while (it.hasNext()) {
                int intValue = it.next().intValue();
                ArrayList<ManagerOrder> arrayList2 = this.managerOrders;
                Intrinsics.checkNotNull(arrayList2);
                if (arrayList2.get(intValue) != null) {
                    ArrayList<ManagerOrder> arrayList3 = this.managerOrders;
                    Intrinsics.checkNotNull(arrayList3);
                    ManagerOrder managerOrder = arrayList3.get(intValue);
                    Intrinsics.checkNotNull(managerOrder);
                    String ficheNo = managerOrder.getFicheNo();
                    Intrinsics.checkNotNullExpressionValue(ficheNo, "getFicheNo(...)");
                    arrayList.add(ficheNo);
                }
            }
            if (orderStatus == OrderStatus.DISPATCHABLE) {
                this.viewModel.updateOrderDispatchable(arrayList, new OrderValidationDispatchCallBack(this, list2, R.string.str_updated_status_order_dispatchable));
                return;
            } else {
                if (orderStatus == OrderStatus.UNDISPATCHABLE) {
                    this.viewModel.updateOrderUnDispatchable(arrayList, new OrderValidationDispatchCallBack(this, list2, R.string.str_updated_status_order_undispatchable));
                    return;
                }
                return;
            }
        }
        if (orderStatus == OrderStatus.DISPATCHABLE) {
            this.viewModel.updateOrderDispatchable(list, new OrderValidationDispatchCallBack(this, list2, R.string.str_updated_status_order_dispatchable));
        } else if (orderStatus == OrderStatus.UNDISPATCHABLE) {
            this.viewModel.updateOrderUnDispatchable(list, new OrderValidationDispatchCallBack(this, list2, R.string.str_updated_status_order_undispatchable));
        }
    }

    private void showLoadingProgress() {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.setMessage(this.mContext.getString(R.string.str_please_wait)).setCancelable(false).show();
    }

    private void rejectOrder(final int i2) {
        ArrayList<ManagerOrder> arrayList = this.managerOrders;
        Intrinsics.checkNotNull(arrayList);
        if (arrayList.get(i2) == null) {
            return;
        }
        showLoadingProgress();
        SalesValidationViewModel salesValidationViewModel = this.viewModel;
        ArrayList<ManagerOrder> arrayList2 = this.managerOrders;
        Intrinsics.checkNotNull(arrayList2);
        ManagerOrder managerOrder = arrayList2.get(i2);
        Intrinsics.checkNotNull(managerOrder);
        String ficheNo = managerOrder.getFicheNo();
        Intrinsics.checkNotNullExpressionValue(ficheNo, "getFicheNo(...)");
        ArrayList<ManagerOrder> arrayList3 = this.managerOrders;
        Intrinsics.checkNotNull(arrayList3);
        ManagerOrder managerOrder2 = arrayList3.get(i2);
        Intrinsics.checkNotNull(managerOrder2);
        String str = managerOrder2.getcCode();
        Intrinsics.checkNotNullExpressionValue(str, "getcCode(...)");
        salesValidationViewModel.rejectOrder(ficheNo, str, new OrderValidationDispatchCallBack(this, new ArrayList<Integer>(i2) { // from class: com.proje.mobilesales.features.sales.view.validation.OrderValidationRecyclerViewAdapterrejectOrder1
            {
                add(Integer.valueOf(i2));
            }

            public  boolean contains(Integer num) {
                return super.contains(num);
            }
            public boolean contains(Object obj) {
                if (obj instanceof Integer) {
                    return contains((Integer) obj);
                }
                return false;
            }

            public int getSize() {
                return super.size();
            }

            public int indexOf(Integer num) {
                return super.indexOf(num);
            }

            public int indexOf(Object obj) {
                if (obj instanceof Integer) {
                    return indexOf((Integer) obj);
                }
                return -1;
            }

            public int lastIndexOf(Integer num) {
                return super.lastIndexOf(num);
            }

            public int lastIndexOf(Object obj) {
                if (obj instanceof Integer) {
                    return lastIndexOf((Integer) obj);
                }
                return -1;
            }

            public Integer remove(int i3) {
                return removeAt(i3);
            }

            public boolean remove(Integer num) {
                return super.remove(num);
            }

            public boolean remove(Object obj) {
                if (obj == null || obj instanceof Integer) {
                    return remove((Integer) obj);
                }
                return false;
            }

            public Integer removeAt(int i3) {
                return super.remove(i3);
            }

            public int size() {
                return getSize();
            }
        }, R.string.str_order_rejected));
    }

    public void onResult(Boolean bool, String str, List<Integer> list, @StringRes int i2) {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
        Intrinsics.checkNotNull(bool);
        if (bool.booleanValue()) {
            Toast.makeText(this.mContext, ContextUtils.getStringResource(R.string.str_done), Toast.LENGTH_LONG).show();
            removeItemUpdate(list);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this.mContext, str, Toast.LENGTH_LONG).show();
    }

    public void onAnalyseResponse(Sales sales, String str, int i2) {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
        if (sales != null) {
            CustomerOperation customerOperation = new CustomerOperation(sales.getmSalesType(), sales.getFicheType(), FicheMode.ANALYSE);
            Intent intent = new Intent(this.mContext, SalesActivityNew.class);
            intent.putExtra(SalesActivityNew.EXTRA_CUSTOMER_REF, sales.getClRef());
            try {
                intent.putExtra("bigdata:synccode", this.viewModel.getSaveObjectWithSales(sales));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            intent.putExtra(SalesActivityNew.EXTRA_CUSTOMER_OPERATION, customerOperation);
            this.mContext.startActivity(intent);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this.mContext, str, Toast.LENGTH_LONG).show();
    }

    private void removeItemUpdate(List<Integer> list) {
        try {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                ArrayList<ManagerOrder> arrayList = this.managerOrders;
                Intrinsics.checkNotNull(arrayList);
                ManagerOrder managerOrder = arrayList.get(list.get(i2).intValue());
                ArrayList<ManagerOrder> arrayList2 = this.managerOrders;
                Intrinsics.checkNotNull(arrayList2);
                arrayList2.remove(managerOrder);
            }
            this.mSelected.clear();
            notifyDataSetChanged();
        } catch (Exception e2) {
            Log.e(MobileSales.TAG, "removeItemUpdate: ", e2);
        }
    }

    private void toggle(int i2) {
        if (isSelected(i2)) {
            this.mSelected.delete(i2);
        } else {
            this.mSelected.put(i2, true);
        }
        notifyItemChanged(i2);
    }
    public void updateStatus(OrderStatus orderStatus) {
        if (getItemCount() == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        try {
            for (int size = this.mSelected.size() - 1; -1 < size; size--) {
                if (this.mSelected.valueAt(size)) {
                    ManagerOrder item = getItem(this.mSelected.keyAt(size));
                    arrayList.add(Integer.valueOf(item != null ? item.getLogicalRef() : 0));
                    arrayList2.add(Integer.valueOf(this.mSelected.keyAt(size)));
                }
            }
            updateStatus(arrayList, arrayList2, orderStatus);
        } catch (Exception e2) {
            Log.e(MobileSales.TAG, "removeSelection: ", e2);
        }
    }

    private record OrderValidationDispatchCallBack(WeakReference<OrderValidationRecyclerViewAdapter> mAdapter,
                                                   List<Integer> mPositions,
                                                   int mToastMessageRes) implements ResponseListener<Boolean> {
            private OrderValidationDispatchCallBack(OrderValidationRecyclerViewAdapter mAdapter, List<Integer> mPositions, @StringRes int mToastMessageRes) {
                Intrinsics.checkNotNullParameter(mPositions, "mPositions");
                this.mPositions = mPositions;
                this.mToastMessageRes = mToastMessageRes;
                this.mAdapter = new WeakReference<>(mAdapter);
            }

        public void onResponse(PrintSlipModel bool) {
                if (this.mAdapter.get() != null) {
                    OrderValidationRecyclerViewAdapter orderValidationRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(orderValidationRecyclerViewAdapter);
                    if (orderValidationRecyclerViewAdapter.isAttached()) {
                        OrderValidationRecyclerViewAdapter orderValidationRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(orderValidationRecyclerViewAdapter2);
                        orderValidationRecyclerViewAdapter2.onResult(bool, "", this.mPositions, this.mToastMessageRes);
                    }
                }
            }

            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mAdapter.get() != null) {
                    OrderValidationRecyclerViewAdapter orderValidationRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(orderValidationRecyclerViewAdapter);
                    if (orderValidationRecyclerViewAdapter.isAttached()) {
                        OrderValidationRecyclerViewAdapter orderValidationRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(orderValidationRecyclerViewAdapter2);
                        orderValidationRecyclerViewAdapter2.onResult(Boolean.FALSE, errorMessage, this.mPositions, -1);
                    }
                }
            }
        }

    private record OrderValidationAnalyseCallBack(WeakReference<OrderValidationRecyclerViewAdapter> mAdapter,
                                                  int mPosition) implements ResponseListener<Sales> {
            private OrderValidationAnalyseCallBack(OrderValidationRecyclerViewAdapter mAdapter, int mPosition) {
                this.mPosition = mPosition;
                this.mAdapter = new WeakReference<>(mAdapter);
            }

            public void onResponse(PrintSlipModel sales) {
                if (this.mAdapter.get() != null) {
                    OrderValidationRecyclerViewAdapter orderValidationRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(orderValidationRecyclerViewAdapter);
                    if (orderValidationRecyclerViewAdapter.isAttached()) {
                        OrderValidationRecyclerViewAdapter orderValidationRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(orderValidationRecyclerViewAdapter2);
                        orderValidationRecyclerViewAdapter2.onAnalyseResponse(sales, "", this.mPosition);
                    }
                }
            }

            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mAdapter.get() != null) {
                    OrderValidationRecyclerViewAdapter orderValidationRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(orderValidationRecyclerViewAdapter);
                    if (orderValidationRecyclerViewAdapter.isAttached()) {
                        OrderValidationRecyclerViewAdapter orderValidationRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(orderValidationRecyclerViewAdapter2);
                        orderValidationRecyclerViewAdapter2.onAnalyseResponse(null, errorMessage, this.mPosition);
                    }
                }
            }
        }

    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
