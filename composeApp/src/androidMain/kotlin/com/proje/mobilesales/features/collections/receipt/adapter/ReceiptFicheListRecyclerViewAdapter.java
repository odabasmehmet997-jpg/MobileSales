package com.proje.mobilesales.features.collections.receipt.adapter;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.collection.LongSparseArray;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.FType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.enums.WorkTimeControlProcessType;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.PopupMenu;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.utils.Connectivity;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.FTypeControlUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.adapter.ItemViewHolder;
import com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.database.CashCreditDetail;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.collections.receipt.repository.ReceiptFicheListRepository;
import com.proje.mobilesales.features.collections.receipt.view.fragment.ReceiptFicheListFragment;
import com.proje.mobilesales.features.collections.receipt.viewmodel.ReceiptFicheListViewModel;
import com.proje.mobilesales.features.customer.utils.CustomerRestrictionUtil;
import com.proje.mobilesales.features.model.FicheMenuRights;
import com.proje.mobilesales.features.model.FicheShort;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: ReceiptFicheListRecyclerViewAdapter.kt */

public final class ReceiptFicheListRecyclerViewAdapter extends ListRecyclerViewAdapter<FicheViewHolder, FicheShort> {
    public static final Companion Companion = new Companion(null);
    private static final String STATE_DISPLAY_OPTIONS = "state:displayOptions";
    private static final String STATE_LAST_SALES_FICHE_MODE = "state:lastSalesFicheMode";
    private static final String STATE_SALES_FICHE_MENU_RIGHTS = "state:salesFicheMenuRights";
    public FicheMenuRights ficheMenuRights;
    private List<? extends FicheShort> ficheShorts;
    private AlertDialogBuilder<?> mAlertDialogBuilder;
    private FicheMode mLastFicheMode;
    private PopupMenu mPopupMenu;
    private ProgressDialogBuilder<?> mProgressDialogBuilder;
    private boolean mShowSalesDetail;
    public ReceiptFicheListFragment receiptFicheListFragment;
    private final ReceiptFicheListRepository receiptFicheListRepository = new ReceiptFicheListRepository();
    private final ReceiptFicheListViewModel receiptFicheListViewModel = new ReceiptFicheListViewModel(receiptFicheListRepository);
    private final LongSparseArray<Integer> mItemPositions = new LongSparseArray<>(0, 1, null);

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter
    private void bindItem(final FicheViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
    }

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter
    private void handleItemClick(final FicheShort item, final FicheViewHolder holder) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(holder, "holder");
    }

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter
    protected boolean isItemAvailable(final FicheShort item) {
        Intrinsics.checkNotNullParameter(item, "item");
        return true;
    }

    public PopupMenu getMPopupMenu() {
        return mPopupMenu;
    }

    public void setMPopupMenu(final PopupMenu popupMenu) {
        mPopupMenu = popupMenu;
    }

    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        return mProgressDialogBuilder;
    }

    public void setMProgressDialogBuilder(final ProgressDialogBuilder<?> progressDialogBuilder) {
        mProgressDialogBuilder = progressDialogBuilder;
    }

    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        return mAlertDialogBuilder;
    }

    public void setMAlertDialogBuilder(final AlertDialogBuilder<?> alertDialogBuilder) {
        mAlertDialogBuilder = alertDialogBuilder;
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
        final Context context2 = ContextUtils.getmContext();
        final Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mAlertDialogBuilder = new AlertDialogBuilder.Impl(context2, (BaseInjectableActivity) activity);
        final Context context3 = ContextUtils.getmContext();
        final Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mProgressDialogBuilder = new ProgressDialogBuilder.Impl(context3, (BaseInjectableActivity) activity2);
        mPopupMenu = new PopupMenu.Impl();
    }

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(final RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onDetachedFromRecyclerView(recyclerView);
        if (null != this.ficheShorts) {
            ficheShorts = null;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public FicheViewHolder onCreateViewHolder(final ViewGroup parent, final int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        final View inflate = mInflater.inflate(R.layout.item_receipt, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new FicheViewHolder(this, inflate);
    }

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter
    private void clearViewHolder(final FicheViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getTxtTotal().setText("");
        holder.getTxtFicheDate().setText("");
    }

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter
    protected FicheShort getItem(final int i2) {
        final List<? extends FicheShort> list = ficheShorts;
        if (null != list) {
            Intrinsics.checkNotNull(list);
            if (null != list.get(i2)) {
                final List<? extends FicheShort> list2 = ficheShorts;
                Intrinsics.checkNotNull(list2);
                return list2.get(i2);
            }
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(final int i2) {
        Intrinsics.checkNotNull(this.getItem(i2));
        return r3.getLogicalRef();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final FicheViewHolder holder, final int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final FicheShort item = this.getItem(i2);
        if (mCardViewEnabled) {
            holder.mCardView.setCardElevation(mCardElevation);
            holder.mCardView.setRadius(mCardRadius);
            holder.mCardView.setUseCompatPadding(true);
        } else {
            holder.mCardView.setCardElevation(0.0f);
            holder.mCardView.setRadius(0.0f);
            holder.mCardView.setUseCompatPadding(false);
        }
        this.clearViewHolder(holder);
        Intrinsics.checkNotNull(item);
        if (this.isItemAvailable(item)) {
            holder.getTxtTotal().setText(StringUtils.dFormat(item.getTotal()));
            holder.getTxtFicheDate().setText(DateAndTimeUtils.convertStringDate(item.getFicheDate(), "dd.MM.yyyy HH:mm:ss", "dd.MM.yyyy"));
            holder.getTxxFicheRef().setText(item.getFicheRef());
            holder.getImgFicheTransfer().setVisibility(item.isTransfer() ? 0 : 4);
            holder.getTxtExplanation().setText(item.getExplanation());
            holder.getMMoreOptions().setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.collections.receipt.adapter.ReceiptFicheListRecyclerViewAdapterExternalSyntheticLambda1
                public final ReceiptFicheListRecyclerViewAdapter.FicheViewHolder f1;
                public final FicheShort f2;
                public final int f3;

                public ReceiptFicheListRecyclerViewAdapterExternalSyntheticLambda1(final ReceiptFicheListRecyclerViewAdapter.FicheViewHolder holder2, final FicheShort item2, final int i22) {
                    r2 = holder2;
                    r3 = item2;
                    r4 = i22;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(final View view) {
                    onBindViewHolderlambda0(ReceiptFicheListRecyclerViewAdapter.this, r2, r3, r4, view);
                }
            });
            holder2.getLnFicheContainer().setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.collections.receipt.adapter.ReceiptFicheListRecyclerViewAdapterExternalSyntheticLambda2
                public final FicheShort f1;

                public ReceiptFicheListRecyclerViewAdapterExternalSyntheticLambda2(final FicheShort item2) {
                    r2 = item2;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(final View view) {
                    onBindViewHolderlambda1(ReceiptFicheListRecyclerViewAdapter.this, r2, view);
                }
            });
        }
    }

    public static void onBindViewHolderlambda0(final ReceiptFicheListRecyclerViewAdapter this0, final FicheViewHolder holder, final FicheShort ficheShort, final int i2, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(holder, "holder");
        this0.showMoreOptions(holder.getLnFicheContainer(), ficheShort, this0.ficheMenuRights, i2);
    }

    public static void onBindViewHolderlambda1(final ReceiptFicheListRecyclerViewAdapter this0, final FicheShort ficheShort, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final ReceiptFicheListFragment receiptFicheListFragment = this0.receiptFicheListFragment;
        Intrinsics.checkNotNull(receiptFicheListFragment);
        receiptFicheListFragment.openReceiptFiche(ficheShort.getLogicalRef(), FicheMode.ANALYSE);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        final List<? extends FicheShort> list = ficheShorts;
        if (null == list) {
            return 0;
        }
        Intrinsics.checkNotNull(list);
        return list.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setFicheList(final List<? extends FicheShort> list) {
        ficheShorts = list;
        mItemPositions.clear();
        final List<? extends FicheShort> list2 = ficheShorts;
        if (null != list2) {
            Intrinsics.checkNotNull(list2);
            final Iterator<? extends FicheShort> it = list2.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                mItemPositions.put(it.next().getLogicalRef(), Integer.valueOf(i2));
                i2++;
            }
        }
        this.notifyDataSetChanged();
    }

    public void setmShowSalesDetail(final boolean z) {
        mShowSalesDetail = z;
    }

    private void showMoreOptions(final View view, final FicheShort ficheShort, final FicheMenuRights ficheMenuRights, final int i2) {
        boolean z;
        final PopupMenu popupMenu = mPopupMenu;
        Intrinsics.checkNotNull(popupMenu);
        final PopupMenu inflate = popupMenu.create(mContext, view, 0).inflate(R.menu.menu_context_sales_list);
        if (!ficheShort.isTransfer()) {
            Intrinsics.checkNotNull(ficheMenuRights);
            if (ficheMenuRights.isEdit()) {
                z = true;
                final PopupMenu menuItemVisible = inflate.setMenuItemVisible(R.id.menu_contextual_edit, z);
                Intrinsics.checkNotNull(ficheMenuRights);
                menuItemVisible.setMenuItemVisible(R.id.menu_contextual_delete, ficheMenuRights.isDelete()).setMenuItemVisible(R.id.menu_contextual_copy, ficheMenuRights.isCopy()).setMenuItemVisible(R.id.menu_contextual_send, ficheShort.isTransfer() && Connectivity.isConnected(mContext)).setMenuItemVisible(R.id.menu_contextual_send_email, !ficheShort.isTransfer() && Connectivity.isConnected(mContext)).setMenuItemVisible(R.id.menu_contextual_status_change, false).setMenuItemVisible(R.id.menu_contextual_print, true).setMenuItemVisible(R.id.menu_contextual_cancel, false).setMenuItemVisible(R.id.menu_contextual_edocument_pdf, false).setMenuItemVisible(R.id.menu_contextual_save_pdf, false).setMenuItemVisible(R.id.menu_contextual_updateAsNotTransfered, false).setMenuItemVisible(R.id.menu_contextual_update_order_fiche_status, false).setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.proje.mobilesales.features.collections.receipt.adapter.ReceiptFicheListRecyclerViewAdapterExternalSyntheticLambda0
                    public final FicheShort f1;
                    public final int f2;

                    public ReceiptFicheListRecyclerViewAdapterExternalSyntheticLambda0(final FicheShort ficheShort2, final int i22) {
                        r2 = ficheShort2;
                        r3 = i22;
                    }

                    @Override // com.proje.mobilesales.core.interfaces.PopupMenu.OnMenuItemClickListener
                    public boolean onMenuItemClick(final MenuItem menuItem) {
                        final boolean showMoreOptionslambda2;
                        showMoreOptionslambda2 = showMoreOptionslambda2(ReceiptFicheListRecyclerViewAdapter.this, r2, r3, menuItem);
                        return showMoreOptionslambda2;
                    }
                }).show();
            }
        }
        z = false;
        final PopupMenu menuItemVisible2 = inflate.setMenuItemVisible(R.id.menu_contextual_edit, z);
        Intrinsics.checkNotNull(ficheMenuRights);
        menuItemVisible2.setMenuItemVisible(R.id.menu_contextual_delete, ficheMenuRights.isDelete()).setMenuItemVisible(R.id.menu_contextual_copy, ficheMenuRights.isCopy()).setMenuItemVisible(R.id.menu_contextual_send, ficheShort2.isTransfer() && Connectivity.isConnected(mContext)).setMenuItemVisible(R.id.menu_contextual_send_email, !ficheShort2.isTransfer() && Connectivity.isConnected(mContext)).setMenuItemVisible(R.id.menu_contextual_status_change, false).setMenuItemVisible(R.id.menu_contextual_print, true).setMenuItemVisible(R.id.menu_contextual_cancel, false).setMenuItemVisible(R.id.menu_contextual_edocument_pdf, false).setMenuItemVisible(R.id.menu_contextual_save_pdf, false).setMenuItemVisible(R.id.menu_contextual_updateAsNotTransfered, false).setMenuItemVisible(R.id.menu_contextual_update_order_fiche_status, false).setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.proje.mobilesales.features.collections.receipt.adapter.ReceiptFicheListRecyclerViewAdapterExternalSyntheticLambda0
            public final FicheShort f1;
            public final int f2;

            public ReceiptFicheListRecyclerViewAdapterExternalSyntheticLambda0(final FicheShort ficheShort2, final int i22) {
                r2 = ficheShort2;
                r3 = i22;
            }

            @Override // com.proje.mobilesales.core.interfaces.PopupMenu.OnMenuItemClickListener
            public boolean onMenuItemClick(final MenuItem menuItem) {
                final boolean showMoreOptionslambda2;
                showMoreOptionslambda2 = showMoreOptionslambda2(ReceiptFicheListRecyclerViewAdapter.this, r2, r3, menuItem);
                return showMoreOptionslambda2;
            }
        }).show();
    }

    public static boolean showMoreOptionslambda2(final ReceiptFicheListRecyclerViewAdapter this0, final FicheShort ficheShort, final int i2, final MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(ficheShort, "ficheShort");
        Intrinsics.checkNotNullParameter(menuItem, "menuItem");
        if (R.id.menu_contextual_edit == menuItem.getItemId()) {
            final ProgressDialogBuilder<?> progressDialogBuilder = this0.mProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder);
            progressDialogBuilder.setMessage(this0.mContext.getString(R.string.str_please_wait)).show();
            this0.receiptFicheListViewModel.getBaseErp().checkRemoteWorkTimeControl(WorkTimeControlProcessType.Receipt, new CheckWorkTimeListener(this0, menuItem.getItemId(), ficheShort, i2));
            return true;
        }
        if (R.id.menu_contextual_copy == menuItem.getItemId()) {
            final ReceiptFicheListFragment receiptFicheListFragment = this0.receiptFicheListFragment;
            Intrinsics.checkNotNull(receiptFicheListFragment);
            if (!CustomerRestrictionUtil.hasCustomerRestrictionForReceipt(receiptFicheListFragment.getmCustomerRef())) {
                final ProgressDialogBuilder<?> progressDialogBuilder2 = this0.mProgressDialogBuilder;
                Intrinsics.checkNotNull(progressDialogBuilder2);
                progressDialogBuilder2.setMessage(this0.mContext.getString(R.string.str_please_wait)).show();
                this0.receiptFicheListViewModel.getBaseErp().checkRemoteWorkTimeControl(WorkTimeControlProcessType.Receipt, new CheckWorkTimeListener(this0, menuItem.getItemId(), ficheShort, i2));
                return true;
            }
        }
        if (R.id.menu_contextual_send == menuItem.getItemId()) {
            final ProgressDialogBuilder<?> progressDialogBuilder3 = this0.mProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder3);
            progressDialogBuilder3.setMessage(this0.mContext.getString(R.string.str_please_wait)).show();
            this0.receiptFicheListViewModel.getBaseErp().checkRemoteWorkTimeControl(WorkTimeControlProcessType.TransferSend, new CheckWorkTimeListener(this0, menuItem.getItemId(), ficheShort, i2));
            return true;
        }
        if (R.id.menu_contextual_delete == menuItem.getItemId()) {
            final ProgressDialogBuilder<?> progressDialogBuilder4 = this0.mProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder4);
            progressDialogBuilder4.setMessage(this0.mContext.getString(R.string.str_please_wait)).show();
            this0.receiptFicheListViewModel.getBaseErp().checkRemoteWorkTimeControl(WorkTimeControlProcessType.Receipt, new CheckWorkTimeListener(this0, menuItem.getItemId(), ficheShort, i2));
            return true;
        }
        if (R.id.menu_contextual_send_email == menuItem.getItemId()) {
            final ReceiptFicheListFragment receiptFicheListFragment2 = this0.receiptFicheListFragment;
            Intrinsics.checkNotNull(receiptFicheListFragment2);
            receiptFicheListFragment2.openReceiptFiche(ficheShort.getLogicalRef(), FicheMode.SENDMAIL);
            return true;
        }
        if (R.id.menu_contextual_status_change == menuItem.getItemId()) {
            return true;
        }
        if (R.id.menu_contextual_print == menuItem.getItemId()) {
            final ReceiptFicheListFragment receiptFicheListFragment3 = this0.receiptFicheListFragment;
            Intrinsics.checkNotNull(receiptFicheListFragment3);
            final FicheType ficheType = receiptFicheListFragment3.getmCustomerOperation().getFicheType();
            final int convertStringToInt = StringUtils.convertStringToInt(ficheShort.getFicheRef());
            final int logicalRef = ficheShort.getLogicalRef();
            final boolean isTransfer = ficheShort.isTransfer();
            final ReceiptFicheListFragment receiptFicheListFragment4 = this0.receiptFicheListFragment;
            Intrinsics.checkNotNull(receiptFicheListFragment4);
            this0.printFiche(ficheType, convertStringToInt, logicalRef, isTransfer, receiptFicheListFragment4.getmCustomerRef());
        }
        return R.id.menu_contextual_cancel == menuItem.getItemId();
    }

    private void printFiche(final FicheType ficheType, final int i2, final int i3, final boolean z, final int i4) {
        receiptFicheListViewModel.getBaseErp().printFiche(mContext, ficheType, i2, i3, z, i4);
    }

    public int getType() {
        final ReceiptFicheListFragment receiptFicheListFragment = this.receiptFicheListFragment;
        Intrinsics.checkNotNull(receiptFicheListFragment);
        final ReceiptType receiptType = receiptFicheListFragment.getmCustomerOperation().getReceiptType();
        if (receiptType == ReceiptType.CASH) {
            FTypeControlUtils.setMainFType(FType.nakit);
        } else if (receiptType == ReceiptType.CREDIT) {
            FTypeControlUtils.setMainFType(FType.kredikarti);
        } else if (receiptType == ReceiptType.CASE) {
            FTypeControlUtils.setMainFType(FType.nakitkasa);
        } else if (receiptType == ReceiptType.CHEQUE) {
            FTypeControlUtils.setMainFType(FType.cek);
        } else if (receiptType == ReceiptType.DEED) {
            FTypeControlUtils.setMainFType(FType.senet);
        }
        return FTypeControlUtils.getMainFType().ordinal();
    }

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter, com.proje.mobilesales.features.adapter.IListRecyclerView
    public Bundle saveState() {
        final Bundle saveState = super.saveState();
        saveState.putBoolean(ReceiptFicheListRecyclerViewAdapter.STATE_DISPLAY_OPTIONS, mShowSalesDetail);
        saveState.putParcelable(ReceiptFicheListRecyclerViewAdapter.STATE_SALES_FICHE_MENU_RIGHTS, ficheMenuRights);
        saveState.putSerializable(ReceiptFicheListRecyclerViewAdapter.STATE_LAST_SALES_FICHE_MODE, mLastFicheMode);
        Intrinsics.checkNotNull(saveState);
        return saveState;
    }

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter, com.proje.mobilesales.features.adapter.IListRecyclerView
    public void restoreState(final Bundle savedState) {
        Intrinsics.checkNotNullParameter(savedState, "savedState");
        super.restoreState(savedState);
        mShowSalesDetail = savedState.getBoolean(ReceiptFicheListRecyclerViewAdapter.STATE_DISPLAY_OPTIONS, false);
        ficheMenuRights = savedState.getParcelable(ReceiptFicheListRecyclerViewAdapter.STATE_SALES_FICHE_MENU_RIGHTS);
        mLastFicheMode = FicheMode.values()[savedState.getInt(ReceiptFicheListRecyclerViewAdapter.STATE_LAST_SALES_FICHE_MODE, 0)];
    }

    public void onDeleted(final int i2) {
        if (-1 == i2) {
            Toast.makeText(mContext, R.string.exp_27_database_fiche_delete_error, 0).show();
            return;
        }
        final ReceiptFicheListFragment receiptFicheListFragment = this.receiptFicheListFragment;
        Intrinsics.checkNotNull(receiptFicheListFragment);
        receiptFicheListFragment.restartLoader();
    }

    /* compiled from: ReceiptFicheListRecyclerViewAdapter.kt */
        private record CheckWorkTimeListener(WeakReference<ReceiptFicheListRecyclerViewAdapter> mAdapter, int mMenuId,
                                             FicheShort mFicheShort, int mPosition) implements ResponseListener<String> {
            private CheckWorkTimeListener(final ReceiptFicheListRecyclerViewAdapter mAdapter, final int mMenuId, final FicheShort mFicheShort, final int mPosition) {
                Intrinsics.checkNotNullParameter(mFicheShort, "mFicheShort");
                this.mMenuId = mMenuId;
                this.mFicheShort = mFicheShort;
                this.mPosition = mPosition;
                this.mAdapter = new WeakReference<>(mAdapter);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(final String str) {
                if (null != this.mAdapter.get()) {
                    final ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter = mAdapter.get();
                    Intrinsics.checkNotNull(receiptFicheListRecyclerViewAdapter);
                    if (receiptFicheListRecyclerViewAdapter.isAttached()) {
                        final ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter2 = mAdapter.get();
                        Intrinsics.checkNotNull(receiptFicheListRecyclerViewAdapter2);
                        final ProgressDialogBuilder<?> mProgressDialogBuilder = receiptFicheListRecyclerViewAdapter2.getMProgressDialogBuilder();
                        Intrinsics.checkNotNull(mProgressDialogBuilder);
                        mProgressDialogBuilder.dismiss();
                        if (!TextUtils.isEmpty(str)) {
                            final ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter3 = mAdapter.get();
                            Intrinsics.checkNotNull(receiptFicheListRecyclerViewAdapter3);
                            final ReceiptFicheListFragment receiptFicheListFragment = receiptFicheListRecyclerViewAdapter3.receiptFicheListFragment;
                            Intrinsics.checkNotNull(receiptFicheListFragment);
                            Toast.makeText(receiptFicheListFragment.getActivity(), str, 0).show();
                        }
                        switch (mMenuId) {
                            case R.id.menu_contextual_copy /* 2131297308 */:
                                final ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter4 = mAdapter.get();
                                Intrinsics.checkNotNull(receiptFicheListRecyclerViewAdapter4);
                                final ReceiptFicheListFragment receiptFicheListFragment2 = receiptFicheListRecyclerViewAdapter4.receiptFicheListFragment;
                                Intrinsics.checkNotNull(receiptFicheListFragment2);
                                receiptFicheListFragment2.openReceiptFiche(mFicheShort.getLogicalRef(), FicheMode.COPY);
                                break;
                            case R.id.menu_contextual_delete /* 2131297311 */:
                                final ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter5 = mAdapter.get();
                                Intrinsics.checkNotNull(receiptFicheListRecyclerViewAdapter5);
                                final AlertDialogBuilder<?> mAlertDialogBuilder = receiptFicheListRecyclerViewAdapter5.getMAlertDialogBuilder();
                                Intrinsics.checkNotNull(mAlertDialogBuilder);
                                mAlertDialogBuilder.setTitle(R.string.str_warning).setMessage(R.string.str_fiche_delete_confirm).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.collections.receipt.adapter.ReceiptFicheListRecyclerViewAdapterCheckWorkTimeListenerExternalSyntheticLambda0
                                    public DialogInterfaceOnClickListenerC2653x49ba3b2d() {
                                    }

                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(final DialogInterface dialogInterface, final int i2) {
                                        onResponselambda0(CheckWorkTimeListener.this, dialogInterface, i2);
                                    }
                                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.collections.receipt.adapter.ReceiptFicheListRecyclerViewAdapterCheckWorkTimeListenerExternalSyntheticLambda1
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(final DialogInterface dialogInterface, final int i2) {
                                        onResponselambda1(dialogInterface, i2);
                                    }
                                }).create().show();
                                break;
                            case R.id.menu_contextual_edit /* 2131297313 */:
                                final ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter6 = mAdapter.get();
                                Intrinsics.checkNotNull(receiptFicheListRecyclerViewAdapter6);
                                final ReceiptFicheListFragment receiptFicheListFragment3 = receiptFicheListRecyclerViewAdapter6.receiptFicheListFragment;
                                Intrinsics.checkNotNull(receiptFicheListFragment3);
                                receiptFicheListFragment3.openReceiptFiche(mFicheShort.getLogicalRef(), FicheMode.EDIT);
                                break;
                            case R.id.menu_contextual_send /* 2131297322 */:
                                final ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter7 = mAdapter.get();
                                Intrinsics.checkNotNull(receiptFicheListRecyclerViewAdapter7);
                                final ReceiptFicheListFragment receiptFicheListFragment4 = receiptFicheListRecyclerViewAdapter7.receiptFicheListFragment;
                                Intrinsics.checkNotNull(receiptFicheListFragment4);
                                if (!receiptFicheListFragment4.isFicheInTransfer(mFicheShort.getLogicalRef())) {
                                    final ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter8 = mAdapter.get();
                                    Intrinsics.checkNotNull(receiptFicheListRecyclerViewAdapter8);
                                    final BaseErp<?> baseErp = receiptFicheListRecyclerViewAdapter8.receiptFicheListViewModel.getBaseErp();
                                    final int logicalRef = mFicheShort.getLogicalRef();
                                    final ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter9 = mAdapter.get();
                                    Intrinsics.checkNotNull(receiptFicheListRecyclerViewAdapter9);
                                    baseErp.insertFicheNoParamBroadcastMessage(logicalRef, receiptFicheListRecyclerViewAdapter9.getType());
                                    break;
                                }
                                break;
                        }
                    }
                }
            }

            public static void onResponselambda0(final CheckWorkTimeListener this0, final DialogInterface dialog, final int i2) {
                Intrinsics.checkNotNullParameter(this0, "this0");
                Intrinsics.checkNotNullParameter(dialog, "dialog");
                final ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter = this0.mAdapter.get();
                Intrinsics.checkNotNull(receiptFicheListRecyclerViewAdapter);
                if (receiptFicheListRecyclerViewAdapter.hasOnlinePaidItem(this0.mFicheShort.getLogicalRef())) {
                    dialog.dismiss();
                    final ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter2 = this0.mAdapter.get();
                    Intrinsics.checkNotNull(receiptFicheListRecyclerViewAdapter2);
                    final ReceiptFicheListFragment receiptFicheListFragment = receiptFicheListRecyclerViewAdapter2.receiptFicheListFragment;
                    Intrinsics.checkNotNull(receiptFicheListFragment);
                    final Context context = receiptFicheListFragment.getContext();
                    final ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter3 = this0.mAdapter.get();
                    Intrinsics.checkNotNull(receiptFicheListRecyclerViewAdapter3);
                    final ReceiptFicheListFragment receiptFicheListFragment2 = receiptFicheListRecyclerViewAdapter3.receiptFicheListFragment;
                    Intrinsics.checkNotNull(receiptFicheListFragment2);
                    Toast.makeText(context, receiptFicheListFragment2.getString(R.string.str_cannot_delete_online_paid_lines), 1).show();
                    return;
                }
                final ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter4 = this0.mAdapter.get();
                Intrinsics.checkNotNull(receiptFicheListRecyclerViewAdapter4);
                final ReceiptFicheListViewModel receiptFicheListViewModel = receiptFicheListRecyclerViewAdapter4.receiptFicheListViewModel;
                final int logicalRef = this0.mFicheShort.getLogicalRef();
                final ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter5 = this0.mAdapter.get();
                Intrinsics.checkNotNull(receiptFicheListRecyclerViewAdapter5);
                final ReceiptFicheListFragment receiptFicheListFragment3 = receiptFicheListRecyclerViewAdapter5.receiptFicheListFragment;
                Intrinsics.checkNotNull(receiptFicheListFragment3);
                final ReceiptType receiptType = receiptFicheListFragment3.getmCustomerOperation().getReceiptType();
                Intrinsics.checkNotNull(receiptType);
                receiptFicheListViewModel.deleteReceiptFicheLocal(logicalRef, receiptType, this0.mPosition, new DeleteLocalCallBack(this0.mAdapter.get()));
                dialog.dismiss();
            }

            public static void onResponselambda1(final DialogInterface dialog, final int i2) {
                Intrinsics.checkNotNullParameter(dialog, "dialog");
                dialog.dismiss();
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (null != this.mAdapter.get()) {
                    final ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter = mAdapter.get();
                    Intrinsics.checkNotNull(receiptFicheListRecyclerViewAdapter);
                    if (receiptFicheListRecyclerViewAdapter.isAttached()) {
                        final ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter2 = mAdapter.get();
                        Intrinsics.checkNotNull(receiptFicheListRecyclerViewAdapter2);
                        final ProgressDialogBuilder<?> mProgressDialogBuilder = receiptFicheListRecyclerViewAdapter2.getMProgressDialogBuilder();
                        Intrinsics.checkNotNull(mProgressDialogBuilder);
                        mProgressDialogBuilder.dismiss();
                    }
                }
            }
        }

    public boolean hasOnlinePaidItem(final int i2) {
        final ReceiptFicheListFragment receiptFicheListFragment = this.receiptFicheListFragment;
        Intrinsics.checkNotNull(receiptFicheListFragment);
        if (receiptFicheListFragment.getmCustomerOperation().getReceiptType() != ReceiptType.CREDIT) {
            return false;
        }
        final List table = receiptFicheListViewModel.getBaseErp().getLogoSqlHelper().getTable(CashCreditDetail.class, "CASHCREDITID=? AND PAYEDONLINE=?", new String[]{String.valueOf(i2), BuildConfig.NETSIS_DEMO_PASSWORD});
        return !(null == table || table.isEmpty());
    }

    /* compiled from: ReceiptFicheListRecyclerViewAdapter.kt */
        private record DeleteLocalCallBack(
            WeakReference<ReceiptFicheListRecyclerViewAdapter> mAdapter) implements ResponseListener<Integer> {
            private DeleteLocalCallBack(final ReceiptFicheListRecyclerViewAdapter mAdapter) {
                this.mAdapter = new WeakReference<>(mAdapter);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(final Integer num) {
                if (null != this.mAdapter.get()) {
                    final ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter = mAdapter.get();
                    Intrinsics.checkNotNull(receiptFicheListRecyclerViewAdapter);
                    if (receiptFicheListRecyclerViewAdapter.isAttached()) {
                        final ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter2 = mAdapter.get();
                        Intrinsics.checkNotNull(receiptFicheListRecyclerViewAdapter2);
                        Intrinsics.checkNotNull(num);
                        receiptFicheListRecyclerViewAdapter2.onDeleted(num.intValue());
                    }
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (null != this.mAdapter.get()) {
                    final ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter = mAdapter.get();
                    Intrinsics.checkNotNull(receiptFicheListRecyclerViewAdapter);
                    if (receiptFicheListRecyclerViewAdapter.isAttached()) {
                        final ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter2 = mAdapter.get();
                        Intrinsics.checkNotNull(receiptFicheListRecyclerViewAdapter2);
                        receiptFicheListRecyclerViewAdapter2.onDeleted(-1);
                    }
                }
            }
        }

    /* compiled from: ReceiptFicheListRecyclerViewAdapter.kt */
    public final class FicheViewHolder extends ItemViewHolder {
        private final ImageView imgFicheTransfer;
        private final LinearLayout lnFicheContainer;
        private final View mMoreOptions;
        final ReceiptFicheListRecyclerViewAdapter this0;
        private final TextView txtExplanation;
        private final TextView txtFicheDate;
        private final TextView txtTotal;
        private final TextView txxFicheRef;

        
        public FicheViewHolder(final ReceiptFicheListRecyclerViewAdapter receiptFicheListRecyclerViewAdapter, final View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this0 = receiptFicheListRecyclerViewAdapter;
            final View findViewById = itemView.findViewById(R.id.ln_ficheContainer);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            lnFicheContainer = (LinearLayout) findViewById;
            final View findViewById2 = itemView.findViewById(R.id.txt_total);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
            txtTotal = (TextView) findViewById2;
            final View findViewById3 = itemView.findViewById(R.id.txt_fichedate);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
            txtFicheDate = (TextView) findViewById3;
            final View findViewById4 = itemView.findViewById(R.id.txt_ficheref);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
            txxFicheRef = (TextView) findViewById4;
            final View findViewById5 = itemView.findViewById(R.id.txt_explanation);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
            txtExplanation = (TextView) findViewById5;
            final View findViewById6 = itemView.findViewById(R.id.button_more);
            Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
            mMoreOptions = findViewById6;
            final View findViewById7 = itemView.findViewById(R.id.img_fiche_transfer);
            Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
            imgFicheTransfer = (ImageView) findViewById7;
        }

        public TextView getTxtTotal() {
            return txtTotal;
        }

        public TextView getTxtFicheDate() {
            return txtFicheDate;
        }

        public TextView getTxxFicheRef() {
            return txxFicheRef;
        }

        public TextView getTxtExplanation() {
            return txtExplanation;
        }

        public ImageView getImgFicheTransfer() {
            return imgFicheTransfer;
        }

        public View getMMoreOptions() {
            return mMoreOptions;
        }

        public LinearLayout getLnFicheContainer() {
            return lnFicheContainer;
        }
    }

    /* compiled from: ReceiptFicheListRecyclerViewAdapter.kt */
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
