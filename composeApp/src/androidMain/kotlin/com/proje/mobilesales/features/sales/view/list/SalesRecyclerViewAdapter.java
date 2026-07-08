package com.proje.mobilesales.features.sales.view.list;

import android.R;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.view.ActionMode;
import androidx.cardview.widget.CardView;
import androidx.collection.LongSparseArray;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.edocument.EDocumentResponse;
import com.proje.mobilesales.core.enums.*;
import com.proje.mobilesales.core.interfaces.*;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.utils.*;
import com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter;
import com.proje.mobilesales.features.customer.utils.CustomerRestrictionUtil;
import com.proje.mobilesales.features.dbmodel.Invoice;
import com.proje.mobilesales.features.dbmodel.Order;
import com.proje.mobilesales.features.dbmodel.WhTransfer;
import com.proje.mobilesales.features.model.EDocInfoModel;
import com.proje.mobilesales.features.model.FicheMenuRights;
import com.proje.mobilesales.features.model.FicheQueryParams;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesFicheMenuRights;
import com.proje.mobilesales.features.sales.model.SalesShort;
import com.proje.mobilesales.features.sales.model.UpdatedOrderStatus;
import com.proje.mobilesales.features.sales.repository.SalesListRepository;
import com.proje.mobilesales.features.settings.view.activity.PrintPreferencesActivity;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.proje.mobilesales.core.utils.AppUtils.isConnected;

public final class SalesRecyclerViewAdapter extends ListRecyclerViewAdapter<SalesViewHolder, SalesShort> {
    public static final Companion Companion = new Companion(null);
    private static final String STATE_DISPLAY_OPTIONS = "state:displayOptions";
    private static final String STATE_LAST_SALES_FICHE_MODE = "state:lastSalesFicheMode";
    private static final String STATE_SALES_FICHE_MENU_RIGHTS = "state:salesFicheMenuRights";
    private boolean isEArchiveCustomer;
    private final ActionMode.Callback mActionModeCallback;
    ActionModeDelegate mActionModeDelegate;
    private AlertDialogBuilder<?> mAlertDialogBuilder;
    private EDocInfoModel mEDocInfoModel;
    private final LongSparseArray<Integer> mItemPositions;
    private FicheMode mLastFicheMode;
    public MenuTintDelegate mMenuTintDelegate;
    private PopupMenu mPopupMenu;
    private ProgressDialogBuilder<?> mProgressDialogBuilder;
    private SalesFicheMenuRights mSalesFicheMenuRights;
    private SalesListFragment mSalesListFragment;
    private List<SalesShort> mSalesShorts;
    private boolean mShowSalesDetail;
    private final SalesListRepository repository;
    private ArrayList<Integer> salesRefList;
    private final SalesListViewModel viewModel;

    public class WhenMappings {
        public static final  int[] EnumSwitchMapping0;

        static {
            int[] iArr = new int[CustomerMenuType.values().length];
            try {
                iArr[CustomerMenuType.SALES_DISPATCH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CustomerMenuType.SALES_INVOICE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CustomerMenuType.SALES_RETAIL_INVOICE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            EnumSwitchMapping0 = iArr;
        }
    }

    protected void bindItem(SalesViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
    }

    protected void handleItemClick(SalesShort item, SalesViewHolder holder) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(holder, "holder");
    }
    protected boolean isItemAvailable(SalesShort item) {
        Intrinsics.checkNotNullParameter(item, "item");
        return true;
    }

    public SalesRecyclerViewAdapter(ActionModeDelegate actionModeDelegate) {
        this.mActionModeDelegate = actionModeDelegate;
        SalesListRepository salesListRepository = new SalesListRepository();
        this.repository = salesListRepository;
        this.viewModel = new SalesListViewModel(salesListRepository);
        this.mPopupMenu = new PopupMenu.Impl();
        this.mItemPositions = new LongSparseArray<>(0);
        this.salesRefList = new ArrayList<>();
        this.mActionModeCallback = new SalesRecyclerViewAdaptermActionModeCallback1(this);
    }

    public PopupMenu getMPopupMenu() {
        return this.mPopupMenu;
    }

    public void setMPopupMenu(PopupMenu popupMenu) {
        this.mPopupMenu = popupMenu;
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

    public boolean isEArchiveCustomer() {
        return this.isEArchiveCustomer;
    }

    public void setEArchiveCustomer(boolean z) {
        this.isEArchiveCustomer = z;
    }

    public MenuTintDelegate getMMenuTintDelegate() {
        MenuTintDelegate menuTintDelegate = this.mMenuTintDelegate;
        if (menuTintDelegate != null) {
            return menuTintDelegate;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mMenuTintDelegate");
        return null;
    }

    public void setMMenuTintDelegate(MenuTintDelegate menuTintDelegate) {
        Intrinsics.checkNotNullParameter(menuTintDelegate, "<set-?>");
        this.mMenuTintDelegate = menuTintDelegate;
    }

    public ArrayList<Integer> getSalesRefList() {
        return this.salesRefList;
    }

    public void setSalesRefList(ArrayList<Integer> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.salesRefList = arrayList;
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
        Context context2 = this.mContext;
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(context2, (BaseInjectableActivity) activity);
        Context context3 = this.mContext;
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilder = new AlertDialogBuilder.Impl(context3, (BaseInjectableActivity) activity2);
        setMMenuTintDelegate(new MenuTintDelegate());
        getMMenuTintDelegate().onActivityCreated(this.mContext);
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onDetachedFromRecyclerView(recyclerView);
        if (this.mSalesShorts != null) {
            this.mSalesShorts = null;
        }
    }

    public SalesViewHolder onCreateViewHolder(ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = this.mInflater.inflate(R.layout.item_sales, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new SalesViewHolder(inflate);
    }

    protected void clearViewHolder(SalesViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.mSalesView.reset();
    }

    protected SalesShort getItem(int i2) {
        List<SalesShort> list = this.mSalesShorts;
        if (list == null) {
            return null;
        }
        Intrinsics.checkNotNull(list);
        return list.get(i2);
    }

    public long getItemId(int i2) {
        if (getItem(i2) != null) {
            return r3.getLogicalRef();
        }
        return 0L;
    }

    public void onBindViewHolder(final SalesViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final SalesShort item = getItem(i2);
        if (this.mCardViewEnabled) {
            CardView cardView = holder.mCardView;
            Intrinsics.checkNotNull(cardView);
            cardView.setCardElevation(this.mCardElevation);
            holder.mCardView.setRadius(this.mCardRadius);
            holder.mCardView.setUseCompatPadding(true);
        } else {
            CardView cardView2 = holder.mCardView;
            Intrinsics.checkNotNull(cardView2);
            cardView2.setCardElevation(0.0f);
            holder.mCardView.setRadius(0.0f);
            holder.mCardView.setUseCompatPadding(false);
        }
        clearViewHolder(holder);
        Intrinsics.checkNotNull(item);
        if (isItemAvailable(item)) {
            Log.d(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "onBindViewHolder: " + item.getOrderState());
            holder.mSalesView.setSales(item);
            holder.mSalesView.setSalesShowDetail(this.mShowSalesDetail);
            holder.mSalesView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SalesRecyclerViewAdapter.onBindViewHolderlambda0(SalesRecyclerViewAdapter.this, item, holder, view);
                }
            });
            holder.mSalesView.setChecked(isSelected(i2));
            holder.mSalesView.setOrderStatusClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SalesRecyclerViewAdapter.onBindViewHolderlambda1(SalesRecyclerViewAdapter.this, item, view);
                }
            });
            setMoreOptionsRight(holder, item, i2);
            if (item.getSalesType() == SalesType.ORDER.getMValue()) {
                holder.mSalesView.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        boolean onBindViewHolderlambda2;
                        onBindViewHolderlambda2 = SalesRecyclerViewAdapter.onBindViewHolderlambda2(SalesRecyclerViewAdapter.this, item, holder, view);
                        return onBindViewHolderlambda2;
                    }
                });
            }
        }
    }
    public static void onBindViewHolderlambda0(SalesRecyclerViewAdapter this0, SalesShort salesShort, SalesViewHolder holder, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(holder, "holder");
        ActionModeDelegate actionModeDelegate = this0.mActionModeDelegate;
        Boolean valueOf = actionModeDelegate != null ? Boolean.valueOf(actionModeDelegate.isInActionMode()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue()) {
            if (salesShort.getSalesType() == SalesType.ORDER.getMValue()) {
                if (!this0.isOrderAbleToDelete(salesShort.getSalesOrderStatus())) {
                    Toast.makeText(this0.mContext, R.string.exp_not_have_delete_menu_rights, 0).show();
                    return;
                }
                int logicalRef = salesShort.getLogicalRef();
                if (this0.salesRefList.contains(Integer.valueOf(logicalRef))) {
                    this0.salesRefList.remove(Integer.valueOf(logicalRef));
                } else {
                    this0.salesRefList.add(Integer.valueOf(logicalRef));
                }
                this0.toggle(holder.getBindingAdapterPosition());
                return;
            }
            return;
        }
        this0.getSalesFiche(salesShort, FicheMode.ANALYSE);
    }

    public static void onBindViewHolderlambda1(SalesRecyclerViewAdapter this0, SalesShort salesShort, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.updateOrderStatus(salesShort);
    }

    public static boolean onBindViewHolderlambda2(SalesRecyclerViewAdapter this0, SalesShort salesShort, SalesViewHolder holder, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(holder, "holder");
        ActionModeDelegate actionModeDelegate = this0.mActionModeDelegate;
        Intrinsics.checkNotNull(actionModeDelegate);
        if (actionModeDelegate.startActionMode(this0.mActionModeCallback)) {
            if (!this0.isOrderAbleToDelete(salesShort.getSalesOrderStatus())) {
                Toast.makeText(this0.mContext, R.string.exp_not_have_delete_menu_rights, 0).show();
            } else {
                int logicalRef = salesShort.getLogicalRef();
                if (this0.salesRefList.contains(Integer.valueOf(logicalRef))) {
                    this0.salesRefList.remove(Integer.valueOf(logicalRef));
                } else {
                    this0.salesRefList.add(Integer.valueOf(logicalRef));
                }
                this0.toggle(holder.getBindingAdapterPosition());
                return true;
            }
        }
        return false;
    }

    public boolean isOrderAbleToDelete(OrderStatus orderStatus) {
        if (orderStatus == OrderStatus.OFFER) {
            SalesFicheMenuRights salesFicheMenuRights = this.mSalesFicheMenuRights;
            Intrinsics.checkNotNull(salesFicheMenuRights);
            FicheMenuRights mOrderOfferMenuRights = salesFicheMenuRights.getMOrderOfferMenuRights();
            Intrinsics.checkNotNull(mOrderOfferMenuRights);
        }
        if (orderStatus == OrderStatus.DISPATCHABLE) {
            SalesFicheMenuRights salesFicheMenuRights2 = this.mSalesFicheMenuRights;
            Intrinsics.checkNotNull(salesFicheMenuRights2);
            FicheMenuRights mOrderDispatchableMenuRights = salesFicheMenuRights2.getMOrderDispatchableMenuRights();
            Intrinsics.checkNotNull(mOrderDispatchableMenuRights);
        }
        if (orderStatus == OrderStatus.UNDISPATCHABLE) {
            SalesFicheMenuRights salesFicheMenuRights3 = this.mSalesFicheMenuRights;
            Intrinsics.checkNotNull(salesFicheMenuRights3);
            FicheMenuRights mOrderUnDispatchableMenuRights = salesFicheMenuRights3.getMOrderUnDispatchableMenuRights();
            Intrinsics.checkNotNull(mOrderUnDispatchableMenuRights);
            return mOrderUnDispatchableMenuRights.isDelete();
        }
        return false;
    }

    private void toggle(int i2) {
        if (isSelected(i2)) {
            this.mSelected.delete(i2);
        } else {
            this.mSelected.put(i2, true);
        }
        notifyItemChanged(i2);
    }

    
    public void removeSelection() {
        List<SalesShort> list = this.mSalesShorts;
        if (list == null) {
            return;
        }
        Intrinsics.checkNotNull(list);
        if (list.isEmpty()) {
            return;
        }
        int size = this.mSelected.size();
        while (true) {
            size--;
            if (-1 < size) {
                if (this.mSelected.valueAt(size)) {
                    SalesShort item = getItem(this.mSelected.keyAt(size));
                    List<SalesShort> list2 = this.mSalesShorts;
                    Intrinsics.checkNotNull(list2);
                    TypeIntrinsics.asMutableCollection(CollectionsKt.toMutableList((Collection) list2)).remove(item);
                }
            } else {
                this.mSelected.clear();
                notifyDataSetChanged();
                return;
            }
        }
    }

    private void updateOrderStatus(SalesShort salesShort) {
        if (ContextUtils.checkConnectionWithoutMessage()) {
            ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder);
            Context context = this.mContext;
            Intrinsics.checkNotNull(context);
            progressDialogBuilder.setMessage(context.getString(R.string.str_please_wait)).show();
            this.viewModel.getOrderFicheStatus(getSalesFicheRef(salesShort), new OrderFicheStatusListener(this, salesShort));
            return;
        }
        Toast.makeText(this.mContext, ContextUtils.getStringResource(R.string.exp_23_internet_connection_not_found), 1).show();
    }

    private ArrayList<String> getSalesFicheRef(SalesShort salesShort) {
        ArrayList<String> arrayList = new ArrayList<>();
        String ficheId = salesShort.getFicheId();
        Intrinsics.checkNotNull(ficheId);
        arrayList.add(ficheId);
        return arrayList;
    }

    private void setMoreOptionsRight(final SalesViewHolder salesViewHolder, final SalesShort salesShort, final int i2) {
        if (salesShort.getType() == 1) {
            if (salesShort.getSalesOrderStatus() == OrderStatus.OFFER) {
                salesViewHolder.mSalesView.getmMoreOption().setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesRecyclerViewAdapterExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        SalesRecyclerViewAdapter.setMoreOptionsRightlambda3(SalesRecyclerViewAdapter.this, salesShort, i2, view);
                    }
                });
                salesViewHolder.mSalesView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesRecyclerViewAdapterExternalSyntheticLambda1
                    @Override // android.view.View.OnLongClickListener
                    public boolean onLongClick(View view) {
                        boolean moreOptionsRightlambda4;
                        moreOptionsRightlambda4 = SalesRecyclerViewAdapter.setMoreOptionsRightlambda4(SalesRecyclerViewAdapter.this, salesShort, i2, view);
                        return moreOptionsRightlambda4;
                    }
                });
                return;
            } else if (salesShort.getSalesOrderStatus() == OrderStatus.DISPATCHABLE) {
                salesViewHolder.mSalesView.getmMoreOption().setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesRecyclerViewAdapterExternalSyntheticLambda2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        SalesRecyclerViewAdapter.setMoreOptionsRightlambda5(SalesRecyclerViewAdapter.this, salesShort, i2, view);
                    }
                });
                salesViewHolder.mSalesView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesRecyclerViewAdapterExternalSyntheticLambda3
                    @Override // android.view.View.OnLongClickListener
                    public boolean onLongClick(View view) {
                        boolean moreOptionsRightlambda6;
                        moreOptionsRightlambda6 = SalesRecyclerViewAdapter.setMoreOptionsRightlambda6(SalesRecyclerViewAdapter.this, salesShort, i2, view);
                        return moreOptionsRightlambda6;
                    }
                });
                return;
            } else {
                if (salesShort.getSalesOrderStatus() == OrderStatus.UNDISPATCHABLE) {
                    salesViewHolder.mSalesView.getmMoreOption().setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesRecyclerViewAdapterExternalSyntheticLambda4
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            SalesRecyclerViewAdapter.setMoreOptionsRightlambda7(SalesRecyclerViewAdapter.this, salesShort, i2, view);
                        }
                    });
                    salesViewHolder.mSalesView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesRecyclerViewAdapterExternalSyntheticLambda5
                        @Override // android.view.View.OnLongClickListener
                        public boolean onLongClick(View view) {
                            boolean moreOptionsRightlambda8;
                            moreOptionsRightlambda8 = SalesRecyclerViewAdapter.setMoreOptionsRightlambda8(SalesRecyclerViewAdapter.this, salesShort, i2, view);
                            return moreOptionsRightlambda8;
                        }
                    });
                    return;
                }
                return;
            }
        }
        if (salesShort.getType() == 2) {
            SalesListFragment salesListFragment = getmSalesListFragment();
            Intrinsics.checkNotNull(salesListFragment);
            if (FicheTypeControlUtils.isFicheTypeDispatch(salesListFragment.getmCustomerOperation().getFicheType())) {
                salesViewHolder.mSalesView.getmMoreOption().setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesRecyclerViewAdapterExternalSyntheticLambda6
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        SalesRecyclerViewAdapter.setMoreOptionsRightlambda9(SalesRecyclerViewAdapter.this, salesViewHolder, salesShort, i2, view);
                    }
                });
                salesViewHolder.mSalesView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesRecyclerViewAdapterExternalSyntheticLambda7
                    @Override // android.view.View.OnLongClickListener
                    public boolean onLongClick(View view) {
                        boolean moreOptionsRightlambda10;
                        moreOptionsRightlambda10 = SalesRecyclerViewAdapter.setMoreOptionsRightlambda10(SalesRecyclerViewAdapter.this, salesShort, i2, view);
                        return moreOptionsRightlambda10;
                    }
                });
                return;
            }
        }
        salesViewHolder.mSalesView.getmMoreOption().setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesRecyclerViewAdapterExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SalesRecyclerViewAdapter.setMoreOptionsRightlambda11(SalesRecyclerViewAdapter.this, salesViewHolder, salesShort, i2, view);
            }
        });
        salesViewHolder.mSalesView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesRecyclerViewAdapterExternalSyntheticLambda9
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                boolean moreOptionsRightlambda12;
                moreOptionsRightlambda12 = SalesRecyclerViewAdapter.setMoreOptionsRightlambda12(SalesRecyclerViewAdapter.this, salesShort, i2, view);
                return moreOptionsRightlambda12;
            }
        });
    }

    
    public static void setMoreOptionsRightlambda3(SalesRecyclerViewAdapter this0, SalesShort salesShort, int i2, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(salesShort, "salesShort");
        Intrinsics.checkNotNullParameter(view, "view");
        SalesFicheMenuRights salesFicheMenuRights = this0.mSalesFicheMenuRights;
        Intrinsics.checkNotNull(salesFicheMenuRights);
        this0.showMoreOptions(view, salesShort, salesFicheMenuRights.getMOrderOfferMenuRights(), i2);
    }

    
    public static boolean setMoreOptionsRightlambda4(SalesRecyclerViewAdapter this0, SalesShort salesShort, int i2, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(salesShort, "salesShort");
        Intrinsics.checkNotNullParameter(view, "view");
        SalesFicheMenuRights salesFicheMenuRights = this0.mSalesFicheMenuRights;
        Intrinsics.checkNotNull(salesFicheMenuRights);
        this0.showMoreOptions(view, salesShort, salesFicheMenuRights.getMOrderOfferMenuRights(), i2);
        return true;
    }

    
    public static void setMoreOptionsRightlambda5(SalesRecyclerViewAdapter this0, SalesShort salesShort, int i2, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(salesShort, "salesShort");
        Intrinsics.checkNotNullParameter(view, "view");
        SalesFicheMenuRights salesFicheMenuRights = this0.mSalesFicheMenuRights;
        Intrinsics.checkNotNull(salesFicheMenuRights);
        this0.showMoreOptions(view, salesShort, salesFicheMenuRights.getMOrderDispatchableMenuRights(), i2);
    }

    
    public static boolean setMoreOptionsRightlambda6(SalesRecyclerViewAdapter this0, SalesShort salesShort, int i2, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(salesShort, "salesShort");
        Intrinsics.checkNotNullParameter(view, "view");
        SalesFicheMenuRights salesFicheMenuRights = this0.mSalesFicheMenuRights;
        Intrinsics.checkNotNull(salesFicheMenuRights);
        this0.showMoreOptions(view, salesShort, salesFicheMenuRights.getMOrderDispatchableMenuRights(), i2);
        return true;
    }

    
    public static void setMoreOptionsRightlambda7(SalesRecyclerViewAdapter this0, SalesShort salesShort, int i2, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(salesShort, "salesShort");
        Intrinsics.checkNotNullParameter(view, "view");
        SalesFicheMenuRights salesFicheMenuRights = this0.mSalesFicheMenuRights;
        Intrinsics.checkNotNull(salesFicheMenuRights);
        this0.showMoreOptions(view, salesShort, salesFicheMenuRights.getMOrderUnDispatchableMenuRights(), i2);
    }

    
    public static boolean setMoreOptionsRightlambda8(SalesRecyclerViewAdapter this0, SalesShort salesShort, int i2, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(salesShort, "salesShort");
        Intrinsics.checkNotNullParameter(view, "view");
        SalesFicheMenuRights salesFicheMenuRights = this0.mSalesFicheMenuRights;
        Intrinsics.checkNotNull(salesFicheMenuRights);
        this0.showMoreOptions(view, salesShort, salesFicheMenuRights.getMOrderUnDispatchableMenuRights(), i2);
        return true;
    }

    
    public static void setMoreOptionsRightlambda9(SalesRecyclerViewAdapter this0, SalesViewHolder holder, SalesShort salesShort, int i2, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(salesShort, "salesShort");
        SalesView salesView = holder.mSalesView;
        SalesFicheMenuRights salesFicheMenuRights = this0.mSalesFicheMenuRights;
        Intrinsics.checkNotNull(salesFicheMenuRights);
        this0.showMoreOptions(salesView, salesShort, salesFicheMenuRights.getDispatchMenuRights(), i2);
    }

    
    public static boolean setMoreOptionsRightlambda10(SalesRecyclerViewAdapter this0, SalesShort salesShort, int i2, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(salesShort, "salesShort");
        Intrinsics.checkNotNullParameter(view, "view");
        SalesFicheMenuRights salesFicheMenuRights = this0.mSalesFicheMenuRights;
        Intrinsics.checkNotNull(salesFicheMenuRights);
        this0.showMoreOptions(view, salesShort, salesFicheMenuRights.getDispatchMenuRights(), i2);
        return true;
    }

    
    public static void setMoreOptionsRightlambda11(SalesRecyclerViewAdapter this0, SalesViewHolder holder, SalesShort salesShort, int i2, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(salesShort, "salesShort");
        SalesView salesView = holder.mSalesView;
        SalesFicheMenuRights salesFicheMenuRights = this0.mSalesFicheMenuRights;
        Intrinsics.checkNotNull(salesFicheMenuRights);
        this0.showMoreOptions(salesView, salesShort, salesFicheMenuRights.getMInvoiceMenuRights(), i2);
    }

    
    public static boolean setMoreOptionsRightlambda12(SalesRecyclerViewAdapter this0, SalesShort salesShort, int i2, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(salesShort, "salesShort");
        Intrinsics.checkNotNullParameter(view, "view");
        SalesFicheMenuRights salesFicheMenuRights = this0.mSalesFicheMenuRights;
        Intrinsics.checkNotNull(salesFicheMenuRights);
        this0.showMoreOptions(view, salesShort, salesFicheMenuRights.getMInvoiceMenuRights(), i2);
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<SalesShort> list = this.mSalesShorts;
        if (list == null) {
            return 0;
        }
        Intrinsics.checkNotNull(list);
        return list.size();
    }

    public void setSalesList(List<SalesShort> list) {
        this.mSalesShorts = list;
        this.mItemPositions.clear();
        List<SalesShort> list2 = this.mSalesShorts;
        if (list2 != null) {
            Intrinsics.checkNotNull(list2);
            Iterator<SalesShort> it = list2.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                this.mItemPositions.put(it.next().component1(), Integer.valueOf(i2));
                i2++;
            }
        }
        notifyDataSetChanged();
    }

    public void setmShowSalesDetail(boolean z) {
        this.mShowSalesDetail = z;
    }

    private void showMoreOptions(View view, final SalesShort salesShort, FicheMenuRights ficheMenuRights, final int i2) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        SalesListFragment salesListFragment;
        SalesType.Companion companion = SalesType.Companion;
        boolean isSalesTypeWhTransfer = SalesUtils.isSalesTypeWhTransfer(companion.fromSalesType(salesShort.getType()));
        PopupMenu popupMenu = this.mPopupMenu;
        Intrinsics.checkNotNull(popupMenu);
        PopupMenu inflate = popupMenu.create(this.mContext, view, 0).inflate(R.menu.menu_context_sales_list);
        if (!salesShort.isCancel() && !salesShort.isTransfer()) {
            Intrinsics.checkNotNull(ficheMenuRights);
        }
        if (SalesUtils.isSalesTypeOrder(companion.fromSalesType(salesShort.getType()))) {
            SalesFicheMenuRights salesFicheMenuRights = this.mSalesFicheMenuRights;
            Intrinsics.checkNotNull(salesFicheMenuRights);
            if (salesFicheMenuRights.isEditOfferStateOrder()) {
            }
        }
        boolean z5 = false;
        PopupMenu menuItemVisible = inflate.setMenuItemVisible(R.id.menu_contextual_edit, z5);
        Intrinsics.checkNotNull(ficheMenuRights);
        PopupMenu menuItemVisible2 = menuItemVisible.setMenuItemVisible(R.id.menu_contextual_delete, ficheMenuRights.isDelete()).setMenuItemVisible(R.id.menu_contextual_copy, ficheMenuRights.isCopy()).setMenuItemVisible(R.id.menu_contextual_send, SalesUtils.showTransferOption(this.viewModel.erpType(), salesShort) && Connectivity.isConnected(this.mContext) && !salesShort.isTransfer()).setMenuItemVisible(R.id.menu_contextual_send_email, isConnected(this.mContext) && salesShort.isTransfer() && !isSalesTypeWhTransfer).setMenuItemVisible(R.id.menu_contextual_status_change, false);
        SalesListFragment salesListFragment2 = getmSalesListFragment();
        Intrinsics.checkNotNull(salesListFragment2);
        if (salesListFragment2.getmCustomerOperation().getSalesType() != SalesType.DEMAND) {
            if (salesShort.isCancel()) {
                SalesFicheMenuRights salesFicheMenuRights2 = getmSalesFicheMenuRights();
                Intrinsics.checkNotNull(salesFicheMenuRights2);
            }
            z = true;
            PopupMenu menuItemVisible3 = menuItemVisible2.setMenuItemVisible(R.id.menu_contextual_print, z);
            SalesFicheMenuRights salesFicheMenuRights3 = getmSalesFicheMenuRights();
            Intrinsics.checkNotNull(salesFicheMenuRights3);
            FicheMenuRights mInvoiceMenuRights = salesFicheMenuRights3.getMInvoiceMenuRights();
            Intrinsics.checkNotNull(mInvoiceMenuRights);
            PopupMenu menuItemVisible4 = menuItemVisible3.setMenuItemVisible(R.id.menu_contextual_cancel, !mInvoiceMenuRights.isCancel() && !salesShort.isCancel() && SalesUtils.isSalesTypeInvoiceOrReturnInvoiceOrRetailInvOrRetailReturnInv(companion.fromSalesType(salesShort.getType())) && isConnected(this.mContext) && salesShort.isTransfer()).setMenuItemVisible(R.id.menu_contextual_edocument_pdf, isEdocumentPdfVisible(salesShort));
            if (this.viewModel.erpType() != ErpType.NETSIS) {
                SalesListFragment salesListFragment3 = getmSalesListFragment();
                Intrinsics.checkNotNull(salesListFragment3);
                if (salesListFragment3.getmCustomerOperation().getMenuType() != CustomerMenuType.SALES_DISPATCH || !this.viewModel.getUseEDispatch(-1)) {
                    SalesListFragment salesListFragment4 = getmSalesListFragment();
                    Intrinsics.checkNotNull(salesListFragment4);
                    if (salesListFragment4.getmCustomerOperation().getMenuType() == CustomerMenuType.SALES_INVOICE) {
                        if (!this.viewModel.firmUseEInvoice()) {
                        }
                    }
                }
                z2 = true;
                PopupMenu menuItemVisible5 = menuItemVisible4.setMenuItemVisible(R.id.menu_contextual_edocument_draft_pdf, z2);
                if (salesShort.isTransfer()) {
                    SalesListFragment salesListFragment5 = getmSalesListFragment();
                    Intrinsics.checkNotNull(salesListFragment5);
                    if (salesListFragment5.getmCustomerOperation().getMenuType() != CustomerMenuType.SALES_DISPATCH || !this.viewModel.getUseEDispatch(-1)) {
                        SalesListFragment salesListFragment6 = getmSalesListFragment();
                        Intrinsics.checkNotNull(salesListFragment6);
                        if (salesListFragment6.getmCustomerOperation().getMenuType() == CustomerMenuType.SALES_INVOICE) {
                            SalesListFragment salesListFragment7 = this.mSalesListFragment;
                            Intrinsics.checkNotNull(salesListFragment7);
                        }
                        SalesListFragment salesListFragment8 = getmSalesListFragment();
                        Intrinsics.checkNotNull(salesListFragment8);
                    }
                    z3 = true;
                    PopupMenu menuItemVisible6 = menuItemVisible5.setMenuItemVisible(R.id.menu_contextual_edocument_draft_pdf_3_inc, z3).setMenuItemVisible(R.id.menu_contextual_send_edocument, isSendEdocumentVisible(salesShort));
                    if (isConnected(this.mContext) && !isSalesTypeWhTransfer) {
                        salesListFragment = getmSalesListFragment();
                        Intrinsics.checkNotNull(salesListFragment);
                        if (salesListFragment.getmCustomerOperation().getMenuType() != CustomerMenuType.SALES_DEMAND) {
                            z4 = true;
                            PopupMenu menuItemVisible7 = menuItemVisible6.setMenuItemVisible(R.id.menu_contextual_save_pdf, z4).setMenuItemVisible(R.id.menu_contextual_updateAsNotTransfered, false).setMenuItemVisible(R.id.menu_contextual_create_draft_edocument, canCreateDraftEdocument(salesShort)).setMenuItemVisible(R.id.menu_contextual_create_draft_and_send_edocument, canCreateDraftEdocument(salesShort)).setMenuItemVisible(R.id.menu_contextual_print_with_printer_service, !salesShort.isTransfer() && Preferences.getUsePrinterService(this.mContext) && !TextUtils.isEmpty(Preferences.getDefaultPrinter(this.mContext)));
                            SalesListFragment salesListFragment9 = getmSalesListFragment();
                            Intrinsics.checkNotNull(salesListFragment9);
                            menuItemVisible7.setMenuItemVisible(R.id.menu_contextual_update_order_fiche_status, salesListFragment9.getmCustomerOperation().getMenuType() == CustomerMenuType.SALES_ORDER).setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesRecyclerViewAdapterExternalSyntheticLambda17
                                @Override // com.proje.mobilesales.core.interfaces.PopupMenu.OnMenuItemClickListener
                                public boolean onMenuItemClick(MenuItem menuItem) {
                                    boolean showMoreOptionslambda13;
                                    showMoreOptionslambda13 = SalesRecyclerViewAdapter.showMoreOptionslambda13(SalesRecyclerViewAdapter.this, salesShort, i2, menuItem);
                                    return showMoreOptionslambda13;
                                }
                            }).show();
                        }
                    }
                    z4 = false;
                    PopupMenu menuItemVisible72 = menuItemVisible6.setMenuItemVisible(R.id.menu_contextual_save_pdf, z4).setMenuItemVisible(R.id.menu_contextual_updateAsNotTransfered, false).setMenuItemVisible(R.id.menu_contextual_create_draft_edocument, canCreateDraftEdocument(salesShort)).setMenuItemVisible(R.id.menu_contextual_create_draft_and_send_edocument, canCreateDraftEdocument(salesShort)).setMenuItemVisible(R.id.menu_contextual_print_with_printer_service, !salesShort.isTransfer() && Preferences.getUsePrinterService(this.mContext) && !TextUtils.isEmpty(Preferences.getDefaultPrinter(this.mContext)));
                    SalesListFragment salesListFragment92 = getmSalesListFragment();
                    Intrinsics.checkNotNull(salesListFragment92);
                    menuItemVisible72.setMenuItemVisible(R.id.menu_contextual_update_order_fiche_status, salesListFragment92.getmCustomerOperation().getMenuType() == CustomerMenuType.SALES_ORDER).setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesRecyclerViewAdapterExternalSyntheticLambda17
                        @Override // com.proje.mobilesales.core.interfaces.PopupMenu.OnMenuItemClickListener
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            boolean showMoreOptionslambda13;
                            showMoreOptionslambda13 = SalesRecyclerViewAdapter.showMoreOptionslambda13(SalesRecyclerViewAdapter.this, salesShort, i2, menuItem);
                            return showMoreOptionslambda13;
                        }
                    }).show();
                }
                z3 = false;
                PopupMenu menuItemVisible62 = menuItemVisible5.setMenuItemVisible(R.id.menu_contextual_edocument_draft_pdf_3_inc, z3).setMenuItemVisible(R.id.menu_contextual_send_edocument, isSendEdocumentVisible(salesShort));
                if (isConnected(this.mContext)) {
                    salesListFragment = getmSalesListFragment();
                    Intrinsics.checkNotNull(salesListFragment);
                    if (salesListFragment.getmCustomerOperation().getMenuType() != CustomerMenuType.SALES_DEMAND) {
                    }
                }
                z4 = false;
                PopupMenu menuItemVisible722 = menuItemVisible62.setMenuItemVisible(R.id.menu_contextual_save_pdf, z4).setMenuItemVisible(R.id.menu_contextual_updateAsNotTransfered, false).setMenuItemVisible(R.id.menu_contextual_create_draft_edocument, canCreateDraftEdocument(salesShort)).setMenuItemVisible(R.id.menu_contextual_create_draft_and_send_edocument, canCreateDraftEdocument(salesShort)).setMenuItemVisible(R.id.menu_contextual_print_with_printer_service, !salesShort.isTransfer() && Preferences.getUsePrinterService(this.mContext) && !TextUtils.isEmpty(Preferences.getDefaultPrinter(this.mContext)));
                SalesListFragment salesListFragment922 = getmSalesListFragment();
                Intrinsics.checkNotNull(salesListFragment922);
                menuItemVisible722.setMenuItemVisible(R.id.menu_contextual_update_order_fiche_status, salesListFragment922.getmCustomerOperation().getMenuType() == CustomerMenuType.SALES_ORDER).setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesRecyclerViewAdapterExternalSyntheticLambda17
                    @Override // com.proje.mobilesales.core.interfaces.PopupMenu.OnMenuItemClickListener
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        boolean showMoreOptionslambda13;
                        showMoreOptionslambda13 = SalesRecyclerViewAdapter.showMoreOptionslambda13(SalesRecyclerViewAdapter.this, salesShort, i2, menuItem);
                        return showMoreOptionslambda13;
                    }
                }).show();
            }
            z2 = false;
            PopupMenu menuItemVisible52 = menuItemVisible4.setMenuItemVisible(R.id.menu_contextual_edocument_draft_pdf, z2);
            if (salesShort.isTransfer()) {
            }
            z3 = false;
            PopupMenu menuItemVisible622 = menuItemVisible52.setMenuItemVisible(R.id.menu_contextual_edocument_draft_pdf_3_inc, z3).setMenuItemVisible(R.id.menu_contextual_send_edocument, isSendEdocumentVisible(salesShort));
            if (isConnected(this.mContext)) {
            }
            z4 = false;
            PopupMenu menuItemVisible7222 = menuItemVisible622.setMenuItemVisible(R.id.menu_contextual_save_pdf, z4).setMenuItemVisible(R.id.menu_contextual_updateAsNotTransfered, false).setMenuItemVisible(R.id.menu_contextual_create_draft_edocument, canCreateDraftEdocument(salesShort)).setMenuItemVisible(R.id.menu_contextual_create_draft_and_send_edocument, canCreateDraftEdocument(salesShort)).setMenuItemVisible(R.id.menu_contextual_print_with_printer_service, !salesShort.isTransfer() && Preferences.getUsePrinterService(this.mContext) && !TextUtils.isEmpty(Preferences.getDefaultPrinter(this.mContext)));
            SalesListFragment salesListFragment9222 = getmSalesListFragment();
            Intrinsics.checkNotNull(salesListFragment9222);
            menuItemVisible7222.setMenuItemVisible(R.id.menu_contextual_update_order_fiche_status, salesListFragment9222.getmCustomerOperation().getMenuType() == CustomerMenuType.SALES_ORDER).setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesRecyclerViewAdapterExternalSyntheticLambda17
                @Override // com.proje.mobilesales.core.interfaces.PopupMenu.OnMenuItemClickListener
                public boolean onMenuItemClick(MenuItem menuItem) {
                    boolean showMoreOptionslambda13;
                    showMoreOptionslambda13 = SalesRecyclerViewAdapter.showMoreOptionslambda13(SalesRecyclerViewAdapter.this, salesShort, i2, menuItem);
                    return showMoreOptionslambda13;
                }
            }).show();
        }
        z = false;
        PopupMenu menuItemVisible32 = menuItemVisible2.setMenuItemVisible(R.id.menu_contextual_print, z);
        SalesFicheMenuRights salesFicheMenuRights32 = getmSalesFicheMenuRights();
        Intrinsics.checkNotNull(salesFicheMenuRights32);
        FicheMenuRights mInvoiceMenuRights2 = salesFicheMenuRights32.getMInvoiceMenuRights();
        Intrinsics.checkNotNull(mInvoiceMenuRights2);
        PopupMenu menuItemVisible42 = menuItemVisible32.setMenuItemVisible(R.id.menu_contextual_cancel, !mInvoiceMenuRights2.isCancel() && !salesShort.isCancel() && SalesUtils.isSalesTypeInvoiceOrReturnInvoiceOrRetailInvOrRetailReturnInv(companion.fromSalesType(salesShort.getType())) && isConnected(this.mContext) && salesShort.isTransfer()).setMenuItemVisible(R.id.menu_contextual_edocument_pdf, isEdocumentPdfVisible(salesShort));
        if (this.viewModel.erpType() != ErpType.NETSIS) {
        }
        z2 = false;
        PopupMenu menuItemVisible522 = menuItemVisible42.setMenuItemVisible(R.id.menu_contextual_edocument_draft_pdf, z2);
        if (salesShort.isTransfer()) {
        }
        z3 = false;
        PopupMenu menuItemVisible6222 = menuItemVisible522.setMenuItemVisible(R.id.menu_contextual_edocument_draft_pdf_3_inc, z3).setMenuItemVisible(R.id.menu_contextual_send_edocument, isSendEdocumentVisible(salesShort));
        if (isConnected(this.mContext)) {
        }
        z4 = false;
        PopupMenu menuItemVisible72222 = menuItemVisible6222.setMenuItemVisible(R.id.menu_contextual_save_pdf, z4).setMenuItemVisible(R.id.menu_contextual_updateAsNotTransfered, false).setMenuItemVisible(R.id.menu_contextual_create_draft_edocument, canCreateDraftEdocument(salesShort)).setMenuItemVisible(R.id.menu_contextual_create_draft_and_send_edocument, canCreateDraftEdocument(salesShort)).setMenuItemVisible(R.id.menu_contextual_print_with_printer_service, !salesShort.isTransfer() && Preferences.getUsePrinterService(this.mContext) && !TextUtils.isEmpty(Preferences.getDefaultPrinter(this.mContext)));
        SalesListFragment salesListFragment92222 = getmSalesListFragment();
        Intrinsics.checkNotNull(salesListFragment92222);
        menuItemVisible72222.setMenuItemVisible(R.id.menu_contextual_update_order_fiche_status, salesListFragment92222.getmCustomerOperation().getMenuType() == CustomerMenuType.SALES_ORDER).setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesRecyclerViewAdapterExternalSyntheticLambda17
            @Override // com.proje.mobilesales.core.interfaces.PopupMenu.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                boolean showMoreOptionslambda13;
                showMoreOptionslambda13 = SalesRecyclerViewAdapter.showMoreOptionslambda13(SalesRecyclerViewAdapter.this, salesShort, i2, menuItem);
                return showMoreOptionslambda13;
            }
        }).show();
    }

    
    public static boolean showMoreOptionslambda13(SalesRecyclerViewAdapter this0, SalesShort salesShort, int i2, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(salesShort, "salesShort");
        Intrinsics.checkNotNullParameter(menuItem, "menuItem");
        if (menuItem.getItemId() == R.id.menu_contextual_edit) {
            ProgressDialogBuilder<?> progressDialogBuilder = this0.mProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder);
            progressDialogBuilder.setMessage(this0.mContext.getString(R.string.str_please_wait)).show();
            this0.viewModel.getCheckRemoteWorkTimeControl(this0.getWorkTimeControlProcessType(salesShort), new CheckWorkTimeListener(this0, menuItem.getItemId(), salesShort, i2));
            return true;
        }
        if (menuItem.getItemId() == R.id.menu_contextual_copy) {
            CustomerRestrictionUtil customerRestrictionUtil = CustomerRestrictionUtil.INSTANCE;
            SalesListFragment salesListFragment = this0.mSalesListFragment;
            Intrinsics.checkNotNull(salesListFragment);
            int i3 = salesListFragment.getmCustomerRef();
            SalesListFragment salesListFragment2 = this0.getmSalesListFragment();
            Intrinsics.checkNotNull(salesListFragment2);
            SalesType salesType = salesListFragment2.getmCustomerOperation().getSalesType();
            Intrinsics.checkNotNull(salesType);
            if (!customerRestrictionUtil.hasCustomerRestriction(i3, salesType)) {
                ProgressDialogBuilder<?> progressDialogBuilder2 = this0.mProgressDialogBuilder;
                Intrinsics.checkNotNull(progressDialogBuilder2);
                progressDialogBuilder2.setMessage(this0.mContext.getString(R.string.str_please_wait)).show();
                this0.viewModel.getCheckRemoteWorkTimeControl(this0.getWorkTimeControlProcessType(salesShort), new CheckWorkTimeListener(this0, menuItem.getItemId(), salesShort, i2));
                return true;
            }
        }
        if (menuItem.getItemId() == R.id.menu_contextual_send) {
            ProgressDialogBuilder<?> progressDialogBuilder3 = this0.mProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder3);
            progressDialogBuilder3.setMessage(this0.mContext.getString(R.string.str_please_wait)).show();
            this0.viewModel.getCheckRemoteWorkTimeControl(WorkTimeControlProcessType.TransferSend, new CheckWorkTimeListener(this0, menuItem.getItemId(), salesShort, i2));
            return true;
        }
        if (menuItem.getItemId() == R.id.menu_contextual_delete) {
            ProgressDialogBuilder<?> progressDialogBuilder4 = this0.mProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder4);
            progressDialogBuilder4.setMessage(this0.mContext.getString(R.string.str_please_wait)).show();
            this0.viewModel.getCheckRemoteWorkTimeControl(this0.getWorkTimeControlProcessType(salesShort), new CheckWorkTimeListener(this0, menuItem.getItemId(), salesShort, i2));
            return true;
        }
        if (menuItem.getItemId() == R.id.menu_contextual_send_email) {
            this0.getSalesFiche(salesShort, FicheMode.SENDMAIL);
            return true;
        }
        if (menuItem.getItemId() == R.id.menu_contextual_status_change) {
            return true;
        }
        if (menuItem.getItemId() == R.id.menu_contextual_print) {
            SalesListFragment salesListFragment3 = this0.getmSalesListFragment();
            Intrinsics.checkNotNull(salesListFragment3);
            FicheType ficheType = salesListFragment3.getmCustomerOperation().getFicheType();
            int convertStringToInt = StringUtils.convertStringToInt(salesShort.getFicheId());
            int logicalRef = salesShort.getLogicalRef();
            boolean isTransfer = salesShort.isTransfer();
            SalesListFragment salesListFragment4 = this0.getmSalesListFragment();
            Intrinsics.checkNotNull(salesListFragment4);
            this0.printFiche(ficheType, convertStringToInt, logicalRef, isTransfer, salesListFragment4.getmCustomerRef());
        }
        if (menuItem.getItemId() == R.id.menu_contextual_cancel) {
            ProgressDialogBuilder<?> progressDialogBuilder5 = this0.mProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder5);
            progressDialogBuilder5.setMessage(this0.mContext.getString(R.string.str_please_wait)).show();
            this0.viewModel.getCheckRemoteWorkTimeControl(this0.getWorkTimeControlProcessType(salesShort), new CheckWorkTimeListener(this0, menuItem.getItemId(), salesShort, i2));
        }
        if (menuItem.getItemId() == R.id.menu_contextual_edocument_pdf) {
            SalesListFragment salesListFragment5 = this0.getmSalesListFragment();
            Intrinsics.checkNotNull(salesListFragment5);
            salesListFragment5.setSelectedSalesItem(salesShort);
            SalesListFragment salesListFragment6 = this0.getmSalesListFragment();
            Intrinsics.checkNotNull(salesListFragment6);
            salesListFragment6.setSelectedSalesItemPdfOperation(PdfOperation.EDocumentPdf);
            SalesListFragment salesListFragment7 = this0.getmSalesListFragment();
            Intrinsics.checkNotNull(salesListFragment7);
            BottomSheetDialog bottomSheetDialog = salesListFragment7.getBottomSheetDialog();
            Intrinsics.checkNotNull(bottomSheetDialog);
            bottomSheetDialog.show();
        }
        if (menuItem.getItemId() == R.id.menu_contextual_save_pdf) {
            SalesListFragment salesListFragment8 = this0.getmSalesListFragment();
            Intrinsics.checkNotNull(salesListFragment8);
            salesListFragment8.setSelectedSalesItem(salesShort);
            SalesListFragment salesListFragment9 = this0.getmSalesListFragment();
            Intrinsics.checkNotNull(salesListFragment9);
            salesListFragment9.setSelectedSalesItemPdfOperation(PdfOperation.SalesPdf);
            SalesListFragment salesListFragment10 = this0.getmSalesListFragment();
            Intrinsics.checkNotNull(salesListFragment10);
            BottomSheetDialog bottomSheetDialog2 = salesListFragment10.getBottomSheetDialog();
            Intrinsics.checkNotNull(bottomSheetDialog2);
            bottomSheetDialog2.show();
        }
        if (menuItem.getItemId() == R.id.menu_contextual_updateAsNotTransfered) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("ISTRANSFER", 0);
            SalesType.Companion companion = SalesType.Companion;
            if (SalesUtils.isSalesTypeOrder(companion.fromSalesType(salesShort.getType()))) {
                this0.viewModel.getSqlBriteDatabase().update(Order.class, contentValues, "LOGICALREF = " + salesShort.getLogicalRef(), null);
            } else if (SalesUtils.isSalesTypeWhTransfer(companion.fromSalesType(salesShort.getType()))) {
                this0.viewModel.getSqlBriteDatabase().update(WhTransfer.class, contentValues, "LOGICALREF = " + salesShort.getLogicalRef(), null);
            } else {
                this0.viewModel.getSqlBriteDatabase().update(Invoice.class, contentValues, "LOGICALREF = " + salesShort.getLogicalRef(), null);
            }
            SalesListFragment salesListFragment11 = this0.mSalesListFragment;
            Intrinsics.checkNotNull(salesListFragment11);
            salesListFragment11.restartLoader();
        }
        if (menuItem.getItemId() == R.id.menu_contextual_create_draft_edocument) {
            this0.createDraftEDocument(salesShort);
        }
        if (menuItem.getItemId() == R.id.menu_contextual_create_draft_and_send_edocument) {
            this0.createDraftAndSendEDocument(salesShort);
        }
        if (menuItem.getItemId() == R.id.menu_contextual_send_edocument) {
            this0.sendEdocument(salesShort);
        }
        if (menuItem.getItemId() == R.id.menu_contextual_edocument_draft_pdf) {
            SalesListFragment salesListFragment12 = this0.getmSalesListFragment();
            Intrinsics.checkNotNull(salesListFragment12);
            salesListFragment12.setSelectedSalesItem(salesShort);
            SalesListFragment salesListFragment13 = this0.getmSalesListFragment();
            Intrinsics.checkNotNull(salesListFragment13);
            salesListFragment13.setSelectedSalesItemPdfOperation(PdfOperation.EDocumentDraftPdf);
            SalesListFragment salesListFragment14 = this0.getmSalesListFragment();
            Intrinsics.checkNotNull(salesListFragment14);
            BottomSheetDialog bottomSheetDialog3 = salesListFragment14.getBottomSheetDialog();
            Intrinsics.checkNotNull(bottomSheetDialog3);
            bottomSheetDialog3.show();
        }
        if (menuItem.getItemId() == R.id.menu_contextual_edocument_draft_pdf_3_inc) {
            SalesListFragment salesListFragment15 = this0.getmSalesListFragment();
            Intrinsics.checkNotNull(salesListFragment15);
            salesListFragment15.setSelectedSalesItem(salesShort);
            SalesListFragment salesListFragment16 = this0.getmSalesListFragment();
            Intrinsics.checkNotNull(salesListFragment16);
            salesListFragment16.setSelectedSalesItemPdfOperation(PdfOperation.EDocumentDraftPdfThreeInc);
            SalesListFragment salesListFragment17 = this0.getmSalesListFragment();
            Intrinsics.checkNotNull(salesListFragment17);
            BottomSheetDialog bottomSheetDialog4 = salesListFragment17.getBottomSheetDialog();
            Intrinsics.checkNotNull(bottomSheetDialog4);
            bottomSheetDialog4.show();
        }
        if (menuItem.getItemId() == R.id.menu_contextual_print_with_printer_service) {
            SalesListFragment salesListFragment18 = this0.getmSalesListFragment();
            Intrinsics.checkNotNull(salesListFragment18);
            salesListFragment18.setSelectedSalesItem(salesShort);
            if (TextUtils.isEmpty(Preferences.getPrinterServiceAddress(this0.mContext))) {
                Toast.makeText(this0.mContext, R.string.str_printer_service_must_be_entered_for_print, 1).show();
            } else if (TextUtils.isEmpty(Preferences.getDefaultPrinter(this0.mContext))) {
                Toast.makeText(this0.mContext, R.string.str_could_not_get_designs, 1).show();
            } else {
                Intent intent = new Intent(this0.mContext, PrintPreferencesActivity.class);
                SalesListFragment salesListFragment19 = this0.getmSalesListFragment();
                Intrinsics.checkNotNull(salesListFragment19);
                SalesShort selectedSalesItem = salesListFragment19.getSelectedSalesItem();
                Intrinsics.checkNotNull(selectedSalesItem);
                intent.putExtra("ficheId", String.valueOf(selectedSalesItem.getFicheId()));
                SalesListFragment salesListFragment20 = this0.getmSalesListFragment();
                Intrinsics.checkNotNull(salesListFragment20);
                SalesShort selectedSalesItem2 = salesListFragment20.getSelectedSalesItem();
                Intrinsics.checkNotNull(selectedSalesItem2);
                intent.putExtra("logicalRef", String.valueOf(selectedSalesItem2.getLogicalRef()));
                this0.mContext.startActivity(intent);
            }
        }
        if (menuItem.getItemId() == R.id.menu_contextual_update_order_fiche_status) {
            this0.updateOrderStatus(salesShort);
        }
        return false;
    }

    
    public void getOrderFicheStatus(ArrayList<UpdatedOrderStatus> arrayList, SalesShort salesShort) {
        ContentValues contentValues = new ContentValues();
        String ficheId = this.viewModel.erpType() == ErpType.NETSIS ? salesShort.getFicheId() : salesShort.getFicheNo();
        if (arrayList.size() == 0 && salesShort.isTransfer()) {
            salesShort.setOrderFicheStatus(UpdatedOrderFicheStatus.DELETED.getStatusString());
            contentValues.put("ORDERFICHESTATUS", salesShort.getOrderFicheStatus());
            this.viewModel.getSqlBriteDatabase().update(Order.class, contentValues, "FICHENO = '" + ficheId + '\'');
        }
        Iterator<UpdatedOrderStatus> it = arrayList.iterator();
        while (it.hasNext()) {
            UpdatedOrderStatus next = it.next();
            salesShort.setOrderFicheStatus(next.getOrderFicheStatus());
            contentValues.put("ORDERFICHESTATUS", salesShort.getOrderFicheStatus());
            if (this.viewModel.erpType() == ErpType.NETSIS && StringsKt.equalsdefault(salesShort.getFicheId(), next.getFicheNo(), false, 2, null)) {
                this.viewModel.getSqlBriteDatabase().update(Order.class, contentValues, "FICHENO = '" + salesShort.getFicheId() + '\'');
            } else if (this.viewModel.erpType() == ErpType.TIGER && StringsKt.equalsdefault(salesShort.getFicheId(), String.valueOf(next.getLogicalRef()), false, 2, null)) {
                this.viewModel.getSqlBriteDatabase().update(Order.class, contentValues, "FICHEREF = " + salesShort.getFicheId());
            }
        }
        SalesListFragment salesListFragment = this.mSalesListFragment;
        Intrinsics.checkNotNull(salesListFragment);
        salesListFragment.restartLoader();
    }

    /* compiled from: SalesRecyclerViewAdapter.kt */
        private record OrderFicheStatusListener(WeakReference<SalesRecyclerViewAdapter> mAdapter,
                                                SalesShort mSalesShort) implements ResponseListener<ArrayList<UpdatedOrderStatus>> {
            private OrderFicheStatusListener(SalesRecyclerViewAdapter mAdapter, SalesShort mSalesShort) {
                Intrinsics.checkNotNullParameter(mAdapter, "salesRecyclerViewAdapter");
                Intrinsics.checkNotNullParameter(mSalesShort, "mSalesShort");
                this.mSalesShort = mSalesShort;
                this.mAdapter = new WeakReference<>(mAdapter);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(PrintSlipModel arrayList) {
                if (this.mAdapter.get() != null) {
                    SalesRecyclerViewAdapter salesRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(salesRecyclerViewAdapter);
                    if (salesRecyclerViewAdapter.isAttached()) {
                        SalesRecyclerViewAdapter salesRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(salesRecyclerViewAdapter2);
                        ProgressDialogBuilder<?> mProgressDialogBuilder = salesRecyclerViewAdapter2.getMProgressDialogBuilder();
                        Intrinsics.checkNotNull(mProgressDialogBuilder);
                        mProgressDialogBuilder.dismiss();
                        SalesRecyclerViewAdapter salesRecyclerViewAdapter3 = this.mAdapter.get();
                        Intrinsics.checkNotNull(salesRecyclerViewAdapter3);
                        Intrinsics.checkNotNull(arrayList);
                        salesRecyclerViewAdapter3.getOrderFicheStatus(arrayList, this.mSalesShort);
                    }
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mAdapter.get() != null) {
                    SalesRecyclerViewAdapter salesRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(salesRecyclerViewAdapter);
                    if (salesRecyclerViewAdapter.isAttached()) {
                        SalesRecyclerViewAdapter salesRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(salesRecyclerViewAdapter2);
                        ProgressDialogBuilder<?> mProgressDialogBuilder = salesRecyclerViewAdapter2.getMProgressDialogBuilder();
                        Intrinsics.checkNotNull(mProgressDialogBuilder);
                        mProgressDialogBuilder.dismiss();
                    }
                }
            }
        }

    private WorkTimeControlProcessType getWorkTimeControlProcessType(SalesShort salesShort) {
        return SalesUtils.isSalesTypeOrder(SalesType.Companion.fromSalesType(salesShort.getType())) ? WorkTimeControlProcessType.Order : WorkTimeControlProcessType.Sales;
    }

    private boolean isSendEdocumentVisible(SalesShort salesShort) {
        if (!isConnected(this.mContext) || this.viewModel.erpType() == ErpType.NETSIS || !this.viewModel.canSendEDocuments()) {
            return false;
        }
        if (salesShort.isTransfer()) {
            SalesListFragment salesListFragment = getmSalesListFragment();
            Intrinsics.checkNotNull(salesListFragment);
            if (salesListFragment.getmCustomerOperation().getMenuType() == CustomerMenuType.SALES_DISPATCH && this.viewModel.getUseEDispatch(-1) && this.viewModel.canUseEDocumentOperations()) {
                return true;
            }
        }
        if (!this.viewModel.canUseEDocumentOperations() || !salesShort.isTransfer()) {
            return false;
        }
        SalesListFragment salesListFragment2 = getmSalesListFragment();
        Intrinsics.checkNotNull(salesListFragment2);
        if (salesListFragment2.getmCustomerOperation().getMenuType() != CustomerMenuType.SALES_INVOICE) {
            SalesListFragment salesListFragment3 = getmSalesListFragment();
            Intrinsics.checkNotNull(salesListFragment3);
            if (salesListFragment3.getmCustomerOperation().getMenuType() != CustomerMenuType.SALES_RETAIL_INVOICE) {
                return false;
            }
        }
        return salesShort.getErpInvoiceType() > 0;
    }

    private boolean isEdocumentPdfVisible(SalesShort salesShort) {
        if (!isConnected(this.mContext) || !salesShort.isTransfer() || !this.viewModel.canUseEDocumentOperations()) {
            return false;
        }
        SalesListFragment salesListFragment = getmSalesListFragment();
        Intrinsics.checkNotNull(salesListFragment);
        CustomerMenuType menuType = salesListFragment.getmCustomerOperation().getMenuType();
        int i2 = menuType == null ? -1 : WhenMappings.EnumSwitchMapping0[menuType.ordinal()];
        if (i2 == 1) {
            return this.viewModel.getUseEDispatch(-1);
        }
        if (i2 != 2) {
            return false;
        }
        if (this.viewModel.erpType() != ErpType.NETSIS) {
            return salesShort.getErpInvoiceType() > 0;
        } else {
            EDocInfoModel eDocInfoModel = this.mEDocInfoModel;
            Intrinsics.checkNotNull(eDocInfoModel);
            if (!eDocInfoModel.isAcceptEInvoice()) {
                EDocInfoModel eDocInfoModel2 = this.mEDocInfoModel;
                Intrinsics.checkNotNull(eDocInfoModel2);
                return eDocInfoModel2.isAcceptEArchive();
            }
        }
        return true;
    }

    private boolean canCreateDraftEdocument(SalesShort salesShort) {
        if (!isConnected(this.mContext) || !salesShort.isTransfer() || !this.viewModel.canSendEDocuments() || this.viewModel.erpType() != ErpType.NETSIS) {
            return false;
        }
        SalesListFragment salesListFragment = getmSalesListFragment();
        Intrinsics.checkNotNull(salesListFragment);
        CustomerMenuType menuType = salesListFragment.getmCustomerOperation().getMenuType();
        int i2 = menuType == null ? -1 : WhenMappings.EnumSwitchMapping0[menuType.ordinal()];
        if (i2 == 1) {
            EDocInfoModel eDocInfoModel = this.mEDocInfoModel;
            Intrinsics.checkNotNull(eDocInfoModel);
            return eDocInfoModel.isAcceptEDespatch();
        }
        if (i2 != 2) {
            return false;
        }
        EDocInfoModel eDocInfoModel2 = this.mEDocInfoModel;
        Intrinsics.checkNotNull(eDocInfoModel2);
        if (!eDocInfoModel2.isAcceptEInvoice()) {
            EDocInfoModel eDocInfoModel3 = this.mEDocInfoModel;
            Intrinsics.checkNotNull(eDocInfoModel3);
            return eDocInfoModel3.isAcceptEArchive();
        }
        return true;
    }

    
    public int getType() {
        SalesListFragment salesListFragment = getmSalesListFragment();
        Intrinsics.checkNotNull(salesListFragment);
        SalesType salesType = salesListFragment.getmCustomerOperation().getSalesType();
        Intrinsics.checkNotNull(salesType);
        if (SalesUtils.isSalesTypeOrder(salesType)) {
            FTypeControlUtils.setMainFType(FType.siparis);
            FTypeControlUtils.setMainFatirsTuru(FaturaIrsaliyeTuru.Normal);
        } else if (SalesUtils.isSalesTypeDemand(salesType)) {
            FTypeControlUtils.setMainFType(FType.talep);
            FTypeControlUtils.setMainFatirsTuru(FaturaIrsaliyeTuru.Iade);
        } else if (SalesUtils.isSalesTypeOnlyInvoice(salesType)) {
            FTypeControlUtils.setMainFType(FType.fatura);
            FTypeControlUtils.setMainFatirsTuru(FaturaIrsaliyeTuru.Normal);
        } else if (SalesUtils.isSalesTypeOnlyReturnInvoice(salesType)) {
            FTypeControlUtils.setMainFType(FType.fatura);
            FTypeControlUtils.setMainFatirsTuru(FaturaIrsaliyeTuru.Iade);
        } else if (SalesUtils.isSalesTypeDispatch(salesType)) {
            FTypeControlUtils.setMainFType(FType.irsaliye);
            FTypeControlUtils.setMainFatirsTuru(FaturaIrsaliyeTuru.Normal);
        } else if (SalesUtils.isSalesTypeReturnDispatch(salesType)) {
            FTypeControlUtils.setMainFType(FType.irsaliye);
            FTypeControlUtils.setMainFatirsTuru(FaturaIrsaliyeTuru.Iade);
        } else if (SalesUtils.isSalesTypeOneToOne(salesType)) {
            FTypeControlUtils.setMainFType(FType.birebir);
            FTypeControlUtils.setMainFatirsTuru(FaturaIrsaliyeTuru.Normal);
        } else if (SalesUtils.isSalesTypeOnlyRetailInvoice(salesType)) {
            FTypeControlUtils.setMainFType(FType.perakendefatura);
            FTypeControlUtils.setMainFatirsTuru(FaturaIrsaliyeTuru.Normal);
        } else if (SalesUtils.isSalesTypeOnlyRetailReturnInvoice(salesType)) {
            FTypeControlUtils.setMainFType(FType.perakendefatura);
            FTypeControlUtils.setMainFatirsTuru(FaturaIrsaliyeTuru.Iade);
        } else if (SalesUtils.isSalesTypeWhTransfer(salesType)) {
            FTypeControlUtils.setMainFType(FType.whtransfer);
            FTypeControlUtils.setMainFatirsTuru(FaturaIrsaliyeTuru.Normal);
        }
        return FTypeControlUtils.getMainFType().ordinal();
    }

    private void printFiche(FicheType ficheType, int i2, int i3, int i4) {
        printFiche(ficheType, i2, i3, true, i4);
    }

    private void printFiche(FicheType ficheType, int i2, int i3, boolean z, int i4) {
        SalesListViewModel salesListViewModel = this.viewModel;
        Context mContext = this.mContext;
        Intrinsics.checkNotNullExpressionValue(mContext, "mContext");
        salesListViewModel.printFiche(mContext, ficheType, i2, i3, z, i4);
    }

    public void setmSalesListFragment(SalesListFragment salesListFragment) {
        this.mSalesListFragment = salesListFragment;
    }

    public SalesListFragment getmSalesListFragment() {
        return this.mSalesListFragment;
    }

    public void setmSalesFicheMenuRights(SalesFicheMenuRights salesFicheMenuRights) {
        this.mSalesFicheMenuRights = salesFicheMenuRights;
    }

    public SalesFicheMenuRights getmSalesFicheMenuRights() {
        return this.mSalesFicheMenuRights;
    }

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter, com.proje.mobilesales.features.adapter.IListRecyclerView
    public Bundle saveState() {
        Bundle saveState = super.saveState();
        saveState.putBoolean(STATE_DISPLAY_OPTIONS, this.mShowSalesDetail);
        saveState.putParcelable(STATE_SALES_FICHE_MENU_RIGHTS, this.mSalesFicheMenuRights);
        saveState.putSerializable(STATE_LAST_SALES_FICHE_MODE, this.mLastFicheMode);
        Intrinsics.checkNotNull(saveState);
        return saveState;
    }

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter, com.proje.mobilesales.features.adapter.IListRecyclerView
    public void restoreState(Bundle savedState) {
        Intrinsics.checkNotNullParameter(savedState, "savedState");
        super.restoreState(savedState);
        this.mShowSalesDetail = savedState.getBoolean(STATE_DISPLAY_OPTIONS, false);
        this.mSalesFicheMenuRights = savedState.getParcelable(STATE_SALES_FICHE_MENU_RIGHTS);
        this.mLastFicheMode = FicheMode.values()[savedState.getInt(STATE_LAST_SALES_FICHE_MODE, 0)];
    }

    public void getSalesFiche(SalesShort salesShort, FicheMode ficheMode) {
        Intrinsics.checkNotNullParameter(salesShort, "salesShort");
        Intrinsics.checkNotNullParameter(ficheMode, "ficheMode");
        this.mLastFicheMode = ficheMode;
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.setMessage(this.mContext.getString(R.string.str_fiche_loading)).setOnCancelClickListener().show();
        SalesType.Companion companion = SalesType.Companion;
        if (SalesUtils.isSalesTypeOrder(companion.fromSalesType(salesShort.getType()))) {
            if (ficheMode == FicheMode.EDIT && salesShort.isTransfer()) {
                SalesFicheMenuRights salesFicheMenuRights = this.mSalesFicheMenuRights;
                Intrinsics.checkNotNull(salesFicheMenuRights);
                if (salesFicheMenuRights.isEditOfferStateOrder()) {
                    if (!ContextUtils.checkConnectionWithoutMessage() && this.viewModel.erpType() == ErpType.NETSIS) {
                        this.viewModel.getSalesOrderOne(salesShort.getLogicalRef(), new SalesRecyclerSalesFicheGet(this));
                        return;
                    }
                    SalesListViewModel salesListViewModel = this.viewModel;
                    String ficheId = salesShort.getFicheId();
                    Intrinsics.checkNotNull(ficheId);
                    SalesListFragment salesListFragment = getmSalesListFragment();
                    Intrinsics.checkNotNull(salesListFragment);
                    salesListViewModel.getIsOrderStatusStillEditable(new FicheQueryParams(ficheId, salesListFragment.getmCustomerRef(), 0), new CheckOfferOrderState(this, salesShort.getLogicalRef()));
                    return;
                }
            }
            this.viewModel.getSalesOrderOne(salesShort.getLogicalRef(), new SalesRecyclerSalesFicheGet(this));
            return;
        }
        if (SalesUtils.isSalesTypeWhTransfer(companion.fromSalesType(salesShort.getType()))) {
            this.viewModel.getSalesWhTransfer(salesShort.getLogicalRef(), new SalesRecyclerSalesFicheGet(this));
        } else {
            this.viewModel.getSalesInvoiceExam(salesShort.getLogicalRef(), new SalesRecyclerSalesFicheGet(this));
        }
    }

    
    public static void getSalesFichelambda14(SalesRecyclerViewAdapter this0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        this0.mLastFicheMode = null;
        dialogInterface.dismiss();
    }

    /* compiled from: SalesRecyclerViewAdapter.kt */
        private record CheckWorkTimeListener(WeakReference<SalesRecyclerViewAdapter> mAdapter, int mMenuId,
                                             SalesShort mSalesShort, int mPosition) implements ResponseListener<String> {
            private CheckWorkTimeListener(SalesRecyclerViewAdapter mAdapter, int mMenuId, SalesShort mSalesShort, int mPosition) {
                Intrinsics.checkNotNullParameter(mSalesShort, "mSalesShort");
                this.mMenuId = mMenuId;
                this.mSalesShort = mSalesShort;
                this.mPosition = mPosition;
                this.mAdapter = new WeakReference<>(mAdapter);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(PrintSlipModel str) {
                if (this.mAdapter.get() != null) {
                    SalesRecyclerViewAdapter salesRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(salesRecyclerViewAdapter);
                    if (salesRecyclerViewAdapter.isAttached()) {
                        SalesRecyclerViewAdapter salesRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(salesRecyclerViewAdapter2);
                        ProgressDialogBuilder<?> mProgressDialogBuilder = salesRecyclerViewAdapter2.getMProgressDialogBuilder();
                        Intrinsics.checkNotNull(mProgressDialogBuilder);
                        mProgressDialogBuilder.dismiss();
                        if (!TextUtils.isEmpty(str)) {
                            SalesRecyclerViewAdapter salesRecyclerViewAdapter3 = this.mAdapter.get();
                            Intrinsics.checkNotNull(salesRecyclerViewAdapter3);
                            SalesListFragment salesListFragment = salesRecyclerViewAdapter3.mSalesListFragment;
                            Intrinsics.checkNotNull(salesListFragment);
                            Toast.makeText(salesListFragment.getActivity(), str, 0).show();
                        }
                        switch (this.mMenuId) {
                            case R.id.menu_contextual_cancel /* 2131297307 */:
                                SalesRecyclerViewAdapter salesRecyclerViewAdapter4 = this.mAdapter.get();
                                Intrinsics.checkNotNull(salesRecyclerViewAdapter4);
                                SalesListFragment salesListFragment2 = salesRecyclerViewAdapter4.mSalesListFragment;
                                Intrinsics.checkNotNull(salesListFragment2);
                                if (!salesListFragment2.getCustomerEInvoice()) {
                                    SalesRecyclerViewAdapter salesRecyclerViewAdapter5 = this.mAdapter.get();
                                    Intrinsics.checkNotNull(salesRecyclerViewAdapter5);
                                    salesRecyclerViewAdapter5.viewModel.cancelInvoiceFiche(StringUtils.convertStringToInt(this.mSalesShort.getFicheId()), new CancelInvoiceFicheListener(this.mAdapter.get(), this.mSalesShort));
                                    break;
                                } else {
                                    SalesRecyclerViewAdapter salesRecyclerViewAdapter6 = this.mAdapter.get();
                                    Intrinsics.checkNotNull(salesRecyclerViewAdapter6);
                                    SalesListFragment salesListFragment3 = salesRecyclerViewAdapter6.mSalesListFragment;
                                    Intrinsics.checkNotNull(salesListFragment3);
                                    Toast.makeText(salesListFragment3.getActivity(), R.string.str_invoice_fiche_not_cancel_einvoice, 1).show();
                                    break;
                                }
                            case R.id.menu_contextual_copy /* 2131297308 */:
                                SalesRecyclerViewAdapter salesRecyclerViewAdapter7 = this.mAdapter.get();
                                Intrinsics.checkNotNull(salesRecyclerViewAdapter7);
                                salesRecyclerViewAdapter7.getSalesFiche(this.mSalesShort, FicheMode.COPY);
                                break;
                            case R.id.menu_contextual_delete /* 2131297311 */:
                                SalesRecyclerViewAdapter salesRecyclerViewAdapter8 = this.mAdapter.get();
                                Intrinsics.checkNotNull(salesRecyclerViewAdapter8);
                                AlertDialogBuilder<?> mAlertDialogBuilder = salesRecyclerViewAdapter8.getMAlertDialogBuilder();
                                Intrinsics.checkNotNull(mAlertDialogBuilder);
                                mAlertDialogBuilder.setTitle(R.string.str_warning).setMessage(R.string.str_fiche_delete_confirm).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesRecyclerViewAdapterCheckWorkTimeListenerExternalSyntheticLambda0
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int i2) {
                                        CheckWorkTimeListener.onResponselambda0(CheckWorkTimeListener.this, dialogInterface, i2);
                                    }
                                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesRecyclerViewAdapterCheckWorkTimeListenerExternalSyntheticLambda1
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int i2) {
                                        CheckWorkTimeListener.onResponselambda1(dialogInterface, i2);
                                    }
                                }).create().show();
                                break;
                            case R.id.menu_contextual_edit /* 2131297313 */:
                                SalesRecyclerViewAdapter salesRecyclerViewAdapter9 = this.mAdapter.get();
                                Intrinsics.checkNotNull(salesRecyclerViewAdapter9);
                                salesRecyclerViewAdapter9.getSalesFiche(this.mSalesShort, FicheMode.EDIT);
                                break;
                            case R.id.menu_contextual_send /* 2131297322 */:
                                SalesRecyclerViewAdapter salesRecyclerViewAdapter10 = this.mAdapter.get();
                                Intrinsics.checkNotNull(salesRecyclerViewAdapter10);
                                SalesListFragment salesListFragment4 = salesRecyclerViewAdapter10.mSalesListFragment;
                                Intrinsics.checkNotNull(salesListFragment4);
                                if (!salesListFragment4.isFicheInTransfer(this.mSalesShort.getLogicalRef())) {
                                    SalesRecyclerViewAdapter salesRecyclerViewAdapter11 = this.mAdapter.get();
                                    Intrinsics.checkNotNull(salesRecyclerViewAdapter11);
                                    SalesListViewModel salesListViewModel = salesRecyclerViewAdapter11.viewModel;
                                    int logicalRef = this.mSalesShort.getLogicalRef();
                                    SalesRecyclerViewAdapter salesRecyclerViewAdapter12 = this.mAdapter.get();
                                    Intrinsics.checkNotNull(salesRecyclerViewAdapter12);
                                    salesListViewModel.insertFicheNoParamBroadcastMessage(logicalRef, salesRecyclerViewAdapter12.getType());
                                    break;
                                }
                                break;
                        }
                    }
                }
            }


        public static void onResponselambda0(CheckWorkTimeListener this0, DialogInterface dialog, int i2) {
                Intrinsics.checkNotNullParameter(this0, "this0");
                Intrinsics.checkNotNullParameter(dialog, "dialog");
                SalesRecyclerViewAdapter salesRecyclerViewAdapter = this0.mAdapter.get();
                Intrinsics.checkNotNull(salesRecyclerViewAdapter);
                SalesListViewModel salesListViewModel = salesRecyclerViewAdapter.viewModel;
                int logicalRef = this0.mSalesShort.getLogicalRef();
                SalesRecyclerViewAdapter salesRecyclerViewAdapter2 = this0.mAdapter.get();
                Intrinsics.checkNotNull(salesRecyclerViewAdapter2);
                SalesListFragment salesListFragment = salesRecyclerViewAdapter2.getmSalesListFragment();
                Intrinsics.checkNotNull(salesListFragment);
                salesListViewModel.deleteSalesFicheLocal(logicalRef, salesListFragment.getmCustomerOperation().getSalesType(), this0.mPosition, new DeleteLocalCallBack(this0.mAdapter.get()));
                dialog.dismiss();
            }


        public static void onResponselambda1(DialogInterface dialog, int i2) {
                Intrinsics.checkNotNullParameter(dialog, "dialog");
                dialog.dismiss();
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mAdapter.get() != null) {
                    SalesRecyclerViewAdapter salesRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(salesRecyclerViewAdapter);
                    if (salesRecyclerViewAdapter.isAttached()) {
                        SalesRecyclerViewAdapter salesRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(salesRecyclerViewAdapter2);
                        ProgressDialogBuilder<?> mProgressDialogBuilder = salesRecyclerViewAdapter2.getMProgressDialogBuilder();
                        Intrinsics.checkNotNull(mProgressDialogBuilder);
                        mProgressDialogBuilder.dismiss();
                    }
                }
            }
        }

    /* compiled from: SalesRecyclerViewAdapter.kt */
        private record SalesRecyclerSalesFicheGet(
            WeakReference<SalesRecyclerViewAdapter> mAdapter) implements ResponseListener<Sales> {
            private SalesRecyclerSalesFicheGet(SalesRecyclerViewAdapter mAdapter) {
                this.mAdapter = new WeakReference<>(mAdapter);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(PrintSlipModel sales) {
                if (this.mAdapter.get() != null) {
                    SalesRecyclerViewAdapter salesRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(salesRecyclerViewAdapter);
                    if (salesRecyclerViewAdapter.isAttached()) {
                        SalesRecyclerViewAdapter salesRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(salesRecyclerViewAdapter2);
                        salesRecyclerViewAdapter2.onSalesGet(sales, "");
                    }
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mAdapter.get() != null) {
                    SalesRecyclerViewAdapter salesRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(salesRecyclerViewAdapter);
                    if (salesRecyclerViewAdapter.isAttached()) {
                        SalesRecyclerViewAdapter salesRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(salesRecyclerViewAdapter2);
                        salesRecyclerViewAdapter2.onSalesGet(null, errorMessage);
                    }
                }
            }
        }

    
    public void onSalesGet(Sales sales, String str) {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
        if (sales != null) {
            if (this.mLastFicheMode != null) {
                SalesListFragment salesListFragment = this.mSalesListFragment;
                Intrinsics.checkNotNull(salesListFragment);
                FicheMode ficheMode = this.mLastFicheMode;
                Intrinsics.checkNotNull(ficheMode);
                salesListFragment.openSalesFiche(sales, ficheMode);
                return;
            }
            Context context = this.mContext;
            Toast.makeText(context, context.getString(R.string.str_cancel_process), 1).show();
            return;
        }
        Toast.makeText(this.mContext, str, 1).show();
    }

    
    public void onDeleted(int i2) {
        if (i2 == -1) {
            Toast.makeText(this.mContext, R.string.exp_27_database_fiche_delete_error, 0).show();
            return;
        }
        SalesListFragment salesListFragment = this.mSalesListFragment;
        Intrinsics.checkNotNull(salesListFragment);
        salesListFragment.restartLoader();
    }

    /* compiled from: SalesRecyclerViewAdapter.kt */
        private record DeleteLocalCallBack(
            WeakReference<SalesRecyclerViewAdapter> mAdapter) implements ResponseListener<Integer> {
            private DeleteLocalCallBack(SalesRecyclerViewAdapter mAdapter) {
                this.mAdapter = new WeakReference<>(mAdapter);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(PrintSlipModel num) {
                if (this.mAdapter.get() != null) {
                    SalesRecyclerViewAdapter salesRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(salesRecyclerViewAdapter);
                    if (salesRecyclerViewAdapter.isAttached()) {
                        SalesRecyclerViewAdapter salesRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(salesRecyclerViewAdapter2);
                        Intrinsics.checkNotNull(num);
                        salesRecyclerViewAdapter2.onDeleted(num.intValue());
                    }
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mAdapter.get() != null) {
                    SalesRecyclerViewAdapter salesRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(salesRecyclerViewAdapter);
                    if (salesRecyclerViewAdapter.isAttached()) {
                        SalesRecyclerViewAdapter salesRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(salesRecyclerViewAdapter2);
                        salesRecyclerViewAdapter2.onDeleted(-1);
                    }
                }
            }
        }

    /* compiled from: SalesRecyclerViewAdapter.kt */
        private record DeleteLocalCallBackList(
            WeakReference<SalesRecyclerViewAdapter> mAdapter) implements ResponseListener<List<? extends Integer>> {
            private DeleteLocalCallBackList(SalesRecyclerViewAdapter mAdapter) {
                this.mAdapter = new WeakReference<>(mAdapter);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(PrintSlipModel list) {
                onResponse2((List<Integer>) list);
            }

            /* renamed from: onResponse, reason: avoid collision after fix types in other method */
            public void onResponse2(List<Integer> list) {
                if (this.mAdapter.get() != null) {
                    SalesRecyclerViewAdapter salesRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(salesRecyclerViewAdapter);
                    if (salesRecyclerViewAdapter.isAttached()) {
                        SalesRecyclerViewAdapter salesRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(salesRecyclerViewAdapter2);
                        Intrinsics.checkNotNull(list);
                        salesRecyclerViewAdapter2.onDeletedList(list);
                    }
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mAdapter.get() != null) {
                    SalesRecyclerViewAdapter salesRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(salesRecyclerViewAdapter);
                    if (salesRecyclerViewAdapter.isAttached()) {
                        SalesRecyclerViewAdapter salesRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(salesRecyclerViewAdapter2);
                        salesRecyclerViewAdapter2.onDeletedList(CollectionsKt.emptyList());
                    }
                }
            }
        }

    
    public void checkOrderStatusResult(boolean z, int i2, String str) {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
        if (z) {
            this.viewModel.getSalesOrderOne(i2, new SalesRecyclerSalesFicheGet(this));
            return;
        }
        Context context = this.mContext;
        if (TextUtils.isEmpty(str)) {
            str = this.mContext.getString(R.string.str_order_status_offer_error);
        }
        Toast.makeText(context, str, 1).show();
    }

    /* compiled from: SalesRecyclerViewAdapter.kt */
        private record CheckOfferOrderState(WeakReference<SalesRecyclerViewAdapter> mAdapter,
                                            int mFicheRef) implements ResponseListener<Boolean> {
            private CheckOfferOrderState(SalesRecyclerViewAdapter mAdapter, int mFicheRef) {
                this.mFicheRef = mFicheRef;
                this.mAdapter = new WeakReference<>(mAdapter);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(PrintSlipModel bool) {
                if (this.mAdapter.get() != null) {
                    SalesRecyclerViewAdapter salesRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(salesRecyclerViewAdapter);
                    if (salesRecyclerViewAdapter.isAttached()) {
                        SalesRecyclerViewAdapter salesRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(salesRecyclerViewAdapter2);
                        Intrinsics.checkNotNull(bool);
                        salesRecyclerViewAdapter2.checkOrderStatusResult(bool.booleanValue(), this.mFicheRef, null);
                    }
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mAdapter.get() != null) {
                    SalesRecyclerViewAdapter salesRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(salesRecyclerViewAdapter);
                    if (salesRecyclerViewAdapter.isAttached()) {
                        SalesRecyclerViewAdapter salesRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(salesRecyclerViewAdapter2);
                        salesRecyclerViewAdapter2.checkOrderStatusResult(false, this.mFicheRef, errorMessage);
                    }
                }
            }
        }

    /* compiled from: SalesRecyclerViewAdapter.kt */
        private record CancelInvoiceFicheListener(WeakReference<SalesRecyclerViewAdapter> mAdapter,
                                                  SalesShort mSalesShort) implements ResponseListener<Boolean> {
            private CancelInvoiceFicheListener(SalesRecyclerViewAdapter mAdapter, SalesShort mSalesShort) {
                Intrinsics.checkNotNullParameter(mSalesShort, "mSalesShort");
                this.mSalesShort = mSalesShort;
                this.mAdapter = new WeakReference<>(mAdapter);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(PrintSlipModel bool) {
                if (this.mAdapter.get() != null) {
                    SalesRecyclerViewAdapter salesRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(salesRecyclerViewAdapter);
                    if (!salesRecyclerViewAdapter.isAttached() || bool == null) {
                        return;
                    }
                    SalesRecyclerViewAdapter salesRecyclerViewAdapter2 = this.mAdapter.get();
                    Intrinsics.checkNotNull(salesRecyclerViewAdapter2);
                    salesRecyclerViewAdapter2.cancelFicheResult(this.mSalesShort, bool.booleanValue(), "");
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mAdapter.get() != null) {
                    SalesRecyclerViewAdapter salesRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(salesRecyclerViewAdapter);
                    if (salesRecyclerViewAdapter.isAttached()) {
                        SalesRecyclerViewAdapter salesRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(salesRecyclerViewAdapter2);
                        salesRecyclerViewAdapter2.cancelFicheResult(this.mSalesShort, false, errorMessage);
                    }
                }
            }
        }

    
    public void cancelFicheResult(SalesShort salesShort, boolean z, String str) {
        String string;
        TextUtils.isEmpty(str);
        if (z) {
            Integer num = this.mItemPositions.get(salesShort.getLogicalRef());
            if (num != null && num.intValue() >= 0 && num.intValue() < getItemCount()) {
                salesShort.setCancel(true);
                notifyItemChanged(num.intValue());
            }
            string = this.mContext.getString(R.string.str_fiche_cancel_success);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        } else {
            string = this.mContext.getString(R.string.str_fiche_cancel_failed);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        }
        AlertDialogBuilder<?> alertDialogBuilder = this.mAlertDialogBuilder;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.setMessage(string).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesRecyclerViewAdapterExternalSyntheticLambda10
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                SalesRecyclerViewAdapter.cancelFicheResultlambda16(dialogInterface, i2);
            }
        }).create().show();
    }

    
    public static void cancelFicheResultlambda16(DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        dialogInterface.dismiss();
    }

    private void createDraftEDocument(SalesShort salesShort) {
        if (ContextUtils.checkInternetConnection()) {
            ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder);
            progressDialogBuilder.setMessage(this.mContext.getString(R.string.str_please_wait)).show();
            SalesListViewModel salesListViewModel = this.viewModel;
            int logicalRef = salesShort.getLogicalRef();
            SalesListFragment salesListFragment = getmSalesListFragment();
            Intrinsics.checkNotNull(salesListFragment);
            salesListViewModel.createEDocumentDraft(logicalRef, salesListFragment.getmCustomerOperation().getFicheType(), new CreateDraftEDocumentListener(this, salesShort));
        }
    }

    private void createDraftAndSendEDocument(SalesShort salesShort) {
        if (ContextUtils.checkInternetConnection()) {
            ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder);
            progressDialogBuilder.setMessage(this.mContext.getString(R.string.str_please_wait)).show();
            SalesListViewModel salesListViewModel = this.viewModel;
            int logicalRef = salesShort.getLogicalRef();
            SalesListFragment salesListFragment = getmSalesListFragment();
            Intrinsics.checkNotNull(salesListFragment);
            salesListViewModel.createEDocumentDraftAndSend(logicalRef, salesListFragment.getmCustomerOperation().getFicheType(), new CreateDraftAndSendEDocumentListener(this, salesShort));
        }
    }

    
    public void onCreateDraftEDocument(SalesShort salesShort, EDocumentResponse eDocumentResponse, String str, EDocStatus eDocStatus) {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
        if (eDocumentResponse != null) {
            if (eDocumentResponse.isSuccess()) {
                Integer num = this.mItemPositions.get(salesShort.getLogicalRef());
                if (num == null || num.intValue() < 0 || num.intValue() >= getItemCount()) {
                    return;
                }
                salesShort.setEDocStatus(eDocStatus.ordinal());
                notifyItemChanged(num.intValue());
                return;
            }
            if (!TextUtils.isEmpty(eDocumentResponse.getErrorDesc())) {
                AlertDialogBuilder<?> alertDialogBuilder = this.mAlertDialogBuilder;
                Intrinsics.checkNotNull(alertDialogBuilder);
                alertDialogBuilder.setMessage(eDocumentResponse.getErrorDesc()).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesRecyclerViewAdapterExternalSyntheticLambda18
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        SalesRecyclerViewAdapter.onCreateDraftEDocumentlambda17(dialogInterface, i2);
                    }
                }).create().show();
                return;
            } else {
                Context context = this.mContext;
                Toast.makeText(context, context.getString(R.string.exp_17_send_error), 1).show();
                return;
            }
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this.mContext, str, 1).show();
    }

    
    public static void onCreateDraftEDocumentlambda17(DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        dialogInterface.dismiss();
    }

    /* compiled from: SalesRecyclerViewAdapter.kt */
        private record CreateDraftEDocumentListener(WeakReference<SalesRecyclerViewAdapter> mAdapter,
                                                    SalesShort mSalesShort) implements ResponseListener<EDocumentResponse> {
            private CreateDraftEDocumentListener(SalesRecyclerViewAdapter mAdapter, SalesShort mSalesShort) {
                Intrinsics.checkNotNullParameter(mSalesShort, "mSalesShort");
                this.mSalesShort = mSalesShort;
                this.mAdapter = new WeakReference<>(mAdapter);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(PrintSlipModel eDocumentResponse) {
                if (this.mAdapter.get() != null) {
                    SalesRecyclerViewAdapter salesRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(salesRecyclerViewAdapter);
                    if (salesRecyclerViewAdapter.isAttached()) {
                        SalesRecyclerViewAdapter salesRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(salesRecyclerViewAdapter2);
                        salesRecyclerViewAdapter2.onCreateDraftEDocument(this.mSalesShort, eDocumentResponse, "", EDocStatus.Draft);
                    }
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mAdapter.get() != null) {
                    SalesRecyclerViewAdapter salesRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(salesRecyclerViewAdapter);
                    if (salesRecyclerViewAdapter.isAttached()) {
                        SalesRecyclerViewAdapter salesRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(salesRecyclerViewAdapter2);
                        salesRecyclerViewAdapter2.onCreateDraftEDocument(this.mSalesShort, null, errorMessage, EDocStatus.Draft);
                    }
                }
            }
        }

    /* compiled from: SalesRecyclerViewAdapter.kt */
        private record CreateDraftAndSendEDocumentListener(WeakReference<SalesRecyclerViewAdapter> mAdapter,
                                                           SalesShort mSalesShort) implements ResponseListener<EDocumentResponse> {
            private CreateDraftAndSendEDocumentListener(SalesRecyclerViewAdapter mAdapter, SalesShort mSalesShort) {
                Intrinsics.checkNotNullParameter(mSalesShort, "mSalesShort");
                this.mSalesShort = mSalesShort;
                this.mAdapter = new WeakReference<>(mAdapter);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(PrintSlipModel eDocumentResponse) {
                if (this.mAdapter.get() != null) {
                    SalesRecyclerViewAdapter salesRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(salesRecyclerViewAdapter);
                    if (salesRecyclerViewAdapter.isAttached()) {
                        SalesRecyclerViewAdapter salesRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(salesRecyclerViewAdapter2);
                        salesRecyclerViewAdapter2.onCreateDraftEDocument(this.mSalesShort, eDocumentResponse, "", EDocStatus.Send);
                    }
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mAdapter.get() != null) {
                    SalesRecyclerViewAdapter salesRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(salesRecyclerViewAdapter);
                    if (salesRecyclerViewAdapter.isAttached()) {
                        SalesRecyclerViewAdapter salesRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(salesRecyclerViewAdapter2);
                        salesRecyclerViewAdapter2.onCreateDraftEDocument(this.mSalesShort, null, errorMessage, EDocStatus.Send);
                    }
                }
            }
        }

    private void sendEdocument(SalesShort salesShort) {
        if (ContextUtils.checkInternetConnection()) {
            ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder);
            progressDialogBuilder.setMessage(this.mContext.getString(R.string.str_please_wait)).show();
            SalesListFragment salesListFragment = getmSalesListFragment();
            Intrinsics.checkNotNull(salesListFragment);
            CustomerMenuType menuType = salesListFragment.getmCustomerOperation().getMenuType();
            int i2 = menuType == null ? -1 : WhenMappings.EnumSwitchMapping0[menuType.ordinal()];
            if (i2 == 1) {
                this.viewModel.sendRecvEDispatchDocuments(StringUtils.convertStringToInt(salesShort.getFicheId()), salesShort.getLogicalRef(), new SendEDocumentsListener(this, salesShort));
                return;
            }
            if (i2 == 2 || i2 == 3) {
                if (salesShort.getErpInvoiceType() == ErpInvoiceType.EInvoice.getmValue()) {
                    this.viewModel.sendRecvEInvoiceDocuments(StringUtils.convertStringToInt(salesShort.getFicheId()), salesShort.getLogicalRef(), new SendEDocumentsListener(this, salesShort));
                } else {
                    this.viewModel.sendEArchiveDocuments(StringUtils.convertStringToInt(salesShort.getFicheId()), salesShort.getLogicalRef(), new SendEDocumentsListener(this, salesShort));
                }
            }
        }
    }

    /* compiled from: SalesRecyclerViewAdapter.kt */
        private record SendEDocumentsListener(WeakReference<SalesRecyclerViewAdapter> mAdapter,
                                              SalesShort mSalesShort) implements ResponseListener<EDocumentResponse> {
            private SendEDocumentsListener(SalesRecyclerViewAdapter mAdapter, SalesShort mSalesShort) {
                Intrinsics.checkNotNullParameter(mSalesShort, "mSalesShort");
                this.mSalesShort = mSalesShort;
                this.mAdapter = new WeakReference<>(mAdapter);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(PrintSlipModel eDocumentResponse) {
                if (this.mAdapter.get() != null) {
                    SalesRecyclerViewAdapter salesRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(salesRecyclerViewAdapter);
                    if (salesRecyclerViewAdapter.isAttached()) {
                        SalesRecyclerViewAdapter salesRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(salesRecyclerViewAdapter2);
                        salesRecyclerViewAdapter2.onSendEDocument(this.mSalesShort, eDocumentResponse, "");
                    }
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mAdapter.get() != null) {
                    SalesRecyclerViewAdapter salesRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(salesRecyclerViewAdapter);
                    if (salesRecyclerViewAdapter.isAttached()) {
                        SalesRecyclerViewAdapter salesRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(salesRecyclerViewAdapter2);
                        salesRecyclerViewAdapter2.onSendEDocument(this.mSalesShort, null, errorMessage);
                    }
                }
            }
        }

    
    public void onSendEDocument(final SalesShort salesShort, EDocumentResponse eDocumentResponse, String str) {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
        if (eDocumentResponse != null) {
            if (eDocumentResponse.isSuccess()) {
                AlertDialogBuilder<?> alertDialogBuilder = this.mAlertDialogBuilder;
                Intrinsics.checkNotNull(alertDialogBuilder);
                alertDialogBuilder.setMessage(eDocumentResponse.getMessage()).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesRecyclerViewAdapterExternalSyntheticLambda15
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        SalesRecyclerViewAdapter.onSendEDocumentlambda18(SalesRecyclerViewAdapter.this, salesShort, dialogInterface, i2);
                    }
                }).create().show();
                return;
            } else if (!TextUtils.isEmpty(eDocumentResponse.getErrorDesc())) {
                AlertDialogBuilder<?> alertDialogBuilder2 = this.mAlertDialogBuilder;
                Intrinsics.checkNotNull(alertDialogBuilder2);
                alertDialogBuilder2.setMessage(eDocumentResponse.getErrorDesc()).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesRecyclerViewAdapterExternalSyntheticLambda16
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        SalesRecyclerViewAdapter.onSendEDocumentlambda19(dialogInterface, i2);
                    }
                }).create().show();
                return;
            } else {
                Context context = this.mContext;
                Toast.makeText(context, context.getString(R.string.exp_17_send_error), 1).show();
                return;
            }
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this.mContext, str, 1).show();
    }

    
    public static void onSendEDocumentlambda18(SalesRecyclerViewAdapter this0, SalesShort salesShort, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(salesShort, "salesShort");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        dialogInterface.dismiss();
        Integer num = this0.mItemPositions.get(salesShort.getLogicalRef());
        if (num == null || num.intValue() < 0 || num.intValue() >= this0.getItemCount()) {
            return;
        }
        salesShort.setEDocStatus(EDocStatus.Send.ordinal());
        this0.notifyItemChanged(num.intValue());
    }

    
    public static void onSendEDocumentlambda19(DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        dialogInterface.dismiss();
    }

    public void setEDocInfoModel(EDocInfoModel eDocInfoModel) {
        this.mEDocInfoModel = eDocInfoModel;
    }

    /* compiled from: SalesRecyclerViewAdapter.kt */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    
    public void onDeletedList(List<Integer> list) {
        if (list == null || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (((Number) it.next()).intValue() == -1) {
                    Toast.makeText(this.mContext, R.string.exp_27_database_fiche_delete_error, 0).show();
                    return;
                }
            }
        }
        SalesListFragment salesListFragment = this.mSalesListFragment;
        Intrinsics.checkNotNull(salesListFragment);
        salesListFragment.restartLoader();
    }
}
