package com.proje.mobilesales.features.visit.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.enums.WorkTimeControlProcessType;
import com.proje.mobilesales.core.interfaces.PopupMenu;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.features.adapter.EndlessListRecyclerViewAdapter;
import com.proje.mobilesales.features.adapter.LoadingViewHolder;
import com.proje.mobilesales.features.visit.model.Visit;
import com.proje.mobilesales.features.visit.model.VisitInfoShort;
import com.proje.mobilesales.features.visit.repository.VisitRepository;
import com.proje.mobilesales.features.visit.view.fragment.VisitListFragment;
import com.proje.mobilesales.features.visit.viewmodel.VisitViewModel;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public class VisitRecyclerViewAdapter extends EndlessListRecyclerViewAdapter<RecyclerView.ViewHolder, VisitInfoShort> {
    private Activity mActivity;
    private FicheMode mLastFicheMode;
    public PopupMenu mPopupMenu;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    private VisitListFragment mVisitListFragment;
    private final VisitRepository repository =  new VisitRepository();
    private final VisitViewModel viewModel = new VisitViewModel(repository);
    private final List<VisitInfoShort> mVisitInfoShorts = new ArrayList();
    protected void bindItem(RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
    }
    protected void handleItemClick(VisitInfoShort item, RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(holder, "holder");
    }
    protected boolean isItemAvailable(VisitInfoShort item) {
        Intrinsics.checkNotNullParameter(item, "item");
        return true;
    }
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (viewType == this.VIEW_TYPE_ITEM) {
            View inflate = this.mInflater.inflate(R.layout.item_visit, parent, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
            return new VisitViewHolder(inflate);
        }
        LoadingViewHolder loadingViewHolder = viewType == this.VIEW_TYPE_LOADING ? new LoadingViewHolder(this.mInflater.inflate(R.layout.item_loading, parent, false)) : null;
        Intrinsics.checkNotNull(loadingViewHolder);
        return loadingViewHolder;
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
        Context context2 = ContextUtils.getmContext();
        if (context2 instanceof BaseInjectableActivity) {
            Intrinsics.checkNotNull(context2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
            ((BaseInjectableActivity) context2).getActivityComponent().inject(this);
        }
          context2 = ContextUtils.getmContext();
        Activity activity = ContextUtils.getmActivity();
        if (activity instanceof BaseInjectableActivity) {
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
            this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(context2, (BaseInjectableActivity) activity);
        }
        this.mPopupMenu = new PopupMenu.Impl();
    }
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onDetachedFromRecyclerView(recyclerView);
        this.mVisitInfoShorts.clear();
    }
    protected void clearViewHolder(RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder instanceof VisitViewHolder) {
            ((VisitViewHolder) holder).mVisitView.reset();
        }
    }
    public void insertProgressBar() {
        if (this.mVisitInfoShorts.contains(null)) {
            return;
        }
        this.mVisitInfoShorts.add(null);
        notifyItemInserted(getItemCount() - 1);
    }
    public void removeProgressBar() {
        int indexOf = this.mVisitInfoShorts.indexOf(null);
        if (indexOf != -1) {
            this.mVisitInfoShorts.remove(indexOf);
            notifyItemRemoved(indexOf);
        }
    }
    public void addItem(ArrayList<ArrayList<VisitInfoShort>> list) {
        removeProgressBar();
        if (list != null) {
            this.mVisitInfoShorts.addAll(list);
            this.mRecyclerView.getRecycledViewPool().clear();
            notifyDataSetChanged();
        }
    }
    protected void restartAdapterAndScroll() {
        if (getItemCount() > 0) {
            this.mVisitInfoShorts.clear();
        }
        notifyDataSetChanged();
    }
    public long getItemId(int i2) {
        VisitInfoShort item = getItem(i2);
        return item != null ? item.getId() : super.getItemId(i2);
    }
    protected VisitInfoShort getItem(int i2) {
        return this.mVisitInfoShorts.get(i2);
    }
    protected void handleItemClick(Object o, RecyclerView.ViewHolder viewHolder) {

    }
    protected boolean isItemAvailable(Object o) {
        return false;
    }
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder instanceof VisitViewHolder visitViewHolder3) {
            VisitInfoShort item = getItem(i2);
            if (this.mCardViewEnabled) {
                visitViewHolder3.mCardView.setCardElevation(this.mCardElevation);
                visitViewHolder3.mCardView.setRadius(this.mCardRadius);
                visitViewHolder3.mCardView.setUseCompatPadding(true);
            } else {
                visitViewHolder3.mCardView.setCardElevation(0.0f);
                visitViewHolder3.mCardView.setRadius(0.0f);
                visitViewHolder3.mCardView.setUseCompatPadding(false);
            }
            clearViewHolder(holder);
            Intrinsics.checkNotNull(item);
            if (!isItemAvailable(item)) {
                loadItem(holder.getAdapterPosition());
                return;
            }
            visitViewHolder3.mVisitView.setVisit(item);
            visitViewHolder3.mVisitView.setOnClickListener(new View.OnClickListener() {
                public final VisitInfoShort f1 = null;

                public   void onClick(View view) {
                    VisitRecyclerViewAdapter.onBindViewHolderlambda0(VisitRecyclerViewAdapter.this, f1, view);
                }
            });
            visitViewHolder3.mVisitView.getmMoreOption().setOnClickListener(new View.OnClickListener() {
                public final RecyclerView.ViewHolder f1 = null;
                public final VisitInfoShort f2 = null;
                public final int f3 = 0;

                public   void onClick(View view) {
                    VisitRecyclerViewAdapter.onBindViewHolderlambda1(VisitRecyclerViewAdapter.this, f1, f2, f3, view);
                }
            });
            visitViewHolder3.mVisitView.setOnLongClickListener(new View.OnLongClickListener() {
                public final VisitInfoShort f1 = null;
                public final int f2 = 0;

                public   boolean onLongClick(View view) {
                    boolean onBindViewHolderlambda2;
                    onBindViewHolderlambda2 = VisitRecyclerViewAdapter.onBindViewHolderlambda2(VisitRecyclerViewAdapter.this, f1, f2, view);
                    return onBindViewHolderlambda2;
                }
            });
            return;
        }
        Object holder2 = null;
        if (holder2 instanceof LoadingViewHolder) {
            ((LoadingViewHolder) holder2).progressBar.setIndeterminate(true);
        }
    }
    public static  void onBindViewHolderlambda0(VisitRecyclerViewAdapter this0, VisitInfoShort visitInfoShort, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.getVisitFiche(visitInfoShort.getId(), FicheMode.ANALYSE);
    }
    public static  void onBindViewHolderlambda1(VisitRecyclerViewAdapter this0, RecyclerView.ViewHolder holder, VisitInfoShort visitInfoShort, int i2, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(holder, "holder");
        this0.showMoreOptions(((VisitViewHolder) holder).mVisitView, visitInfoShort, i2);
    }
    public static boolean onBindViewHolderlambda2(VisitRecyclerViewAdapter this0, VisitInfoShort visitInfoShort, int i2, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(view, "view");
        this0.showMoreOptions(view, visitInfoShort, i2);
        return true;
    }
    private  void showMoreOptions(View view, VisitInfoShort visitInfoShort, int i2) {
        PopupMenu popupMenu = this.mPopupMenu;
        Intrinsics.checkNotNull(popupMenu);
        popupMenu.create(this.mContext, view, 0).inflate(R.menu.menu_context_sales_list).setMenuItemVisible(R.id.menu_contextual_send, !visitInfoShort.getTransfer()).setMenuItemVisible(R.id.menu_contextual_status_change, false).setMenuItemVisible(R.id.menu_contextual_print, false).setMenuItemVisible(R.id.menu_contextual_send_email, false).setMenuItemVisible(R.id.menu_contextual_edit, !visitInfoShort.getTransfer()).setMenuItemVisible(R.id.menu_contextual_edocument_pdf, false).setMenuItemVisible(R.id.menu_contextual_save_pdf, false).setMenuItemVisible(R.id.menu_contextual_updateAsNotTransfered, false).setMenuItemVisible(R.id.menu_contextual_update_order_fiche_status, false).setMenuItemVisible(R.id.menu_contextual_cancel, false).setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.proje.mobilesales.features.visit.adapter.VisitRecyclerViewAdapterExternalSyntheticLambda0
            public final VisitInfoShort f1 = null;
            public final int f2 = 0;

            public boolean onMenuItemClick(MenuItem menuItem) {
                boolean showMoreOptionslambda3;
                showMoreOptionslambda3 = VisitRecyclerViewAdapter.showMoreOptionslambda3(VisitRecyclerViewAdapter.this, f1, f2, menuItem);
                return showMoreOptionslambda3;
            }
        }).show();
    }
    public static boolean showMoreOptionslambda3(VisitRecyclerViewAdapter this0, VisitInfoShort visitInfoShort, int i2, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(visitInfoShort, "visitInfoShort");
        Intrinsics.checkNotNullParameter(menuItem, "menuItem");
        if (menuItem.getItemId() == R.id.menu_contextual_edit) {
            ProgressDialogBuilder<?> progressDialogBuilder = this0.mProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder);
            progressDialogBuilder.setMessage(this0.mContext.getString(R.string.str_please_wait)).show();
            this0.viewModel.getBaseErp().checkRemoteWorkTimeControl(WorkTimeControlProcessType.Visit, new CheckWorkTimeListener(this0, menuItem.getItemId(), visitInfoShort, i2));
            return true;
        }
        if (menuItem.getItemId() == R.id.menu_contextual_copy) {
            ProgressDialogBuilder<?> progressDialogBuilder2 = this0.mProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder2);
            progressDialogBuilder2.setMessage(this0.mContext.getString(R.string.str_please_wait)).show();
            this0.viewModel.getBaseErp().checkRemoteWorkTimeControl(WorkTimeControlProcessType.Visit, new CheckWorkTimeListener(this0, menuItem.getItemId(), visitInfoShort, i2));
            return true;
        }
        if (menuItem.getItemId() == R.id.menu_contextual_send) {
            ProgressDialogBuilder<?> progressDialogBuilder3 = this0.mProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder3);
            progressDialogBuilder3.setMessage(this0.mContext.getString(R.string.str_please_wait)).show();
            this0.viewModel.getBaseErp().checkRemoteWorkTimeControl(WorkTimeControlProcessType.TransferSend, new CheckWorkTimeListener(this0, menuItem.getItemId(), visitInfoShort, i2));
            return true;
        }
        if (menuItem.getItemId() != R.id.menu_contextual_delete) {
            return false;
        }
        ProgressDialogBuilder<?> progressDialogBuilder4 = this0.mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder4);
        progressDialogBuilder4.setMessage(this0.mContext.getString(R.string.str_please_wait)).show();
        this0.viewModel.getBaseErp().checkRemoteWorkTimeControl(WorkTimeControlProcessType.Visit, new CheckWorkTimeListener(this0, menuItem.getItemId(), visitInfoShort, i2));
        return true;
    }

    private record CheckWorkTimeListener(WeakReference<VisitRecyclerViewAdapter> mAdapter, int mMenuId,
                                         VisitInfoShort mVisitInfoShort,
                                         int mPosition) implements ResponseListener<String> {
            private CheckWorkTimeListener(VisitRecyclerViewAdapter mAdapter, int mMenuId, VisitInfoShort mVisitInfoShort, int mPosition) {
                Intrinsics.checkNotNullParameter(mVisitInfoShort, "mVisitInfoShort");
                this.mMenuId = mMenuId;
                this.mVisitInfoShort = mVisitInfoShort;
                this.mPosition = mPosition;
                this.mAdapter = new WeakReference<>(mAdapter);
            }

            public void onResponse(PrintSlipModel str) {
                if (this.mAdapter.get() != null) {
                    VisitRecyclerViewAdapter visitRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(visitRecyclerViewAdapter);
                    if (visitRecyclerViewAdapter.isAttached()) {
                        VisitRecyclerViewAdapter visitRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(visitRecyclerViewAdapter2);
                        ProgressDialogBuilder<?> progressDialogBuilder = visitRecyclerViewAdapter2.mProgressDialogBuilder;
                        Intrinsics.checkNotNull(progressDialogBuilder);
                        progressDialogBuilder.dismiss();
                        if (!TextUtils.isEmpty(str)) {
                            VisitRecyclerViewAdapter visitRecyclerViewAdapter3 = this.mAdapter.get();
                            Intrinsics.checkNotNull(visitRecyclerViewAdapter3);
                            Toast.makeText(visitRecyclerViewAdapter3.mActivity, str, Toast.LENGTH_SHORT).show();
                        }
                        switch (this.mMenuId) {
                            case R.id.menu_contextual_copy /* 2131297308 */:
                                VisitRecyclerViewAdapter visitRecyclerViewAdapter4 = this.mAdapter.get();
                                Intrinsics.checkNotNull(visitRecyclerViewAdapter4);
                                visitRecyclerViewAdapter4.getVisitFiche(this.mVisitInfoShort.getId(), FicheMode.COPY);
                                break;
                            case R.id.menu_contextual_delete /* 2131297311 */:
                                if (this.mVisitInfoShort.getAuto() == 0) {
                                    VisitRecyclerViewAdapter visitRecyclerViewAdapter5 = this.mAdapter.get();
                                    Intrinsics.checkNotNull(visitRecyclerViewAdapter5);
                                    visitRecyclerViewAdapter5.viewModel.deleteVisitFicheLocal(this.mVisitInfoShort.getId(), this.mPosition, new DeleteLocalCallBack(this.mAdapter.get()));
                                    break;
                                } else {
                                    Toast.makeText(ContextUtils.getmContext(), R.string.exp_93_error_auto_visit_operations, Toast.LENGTH_LONG).show();
                                    break;
                                }
                            case R.id.menu_contextual_edit /* 2131297313 */:
                                VisitRecyclerViewAdapter visitRecyclerViewAdapter6 = this.mAdapter.get();
                                Intrinsics.checkNotNull(visitRecyclerViewAdapter6);
                                visitRecyclerViewAdapter6.getVisitFiche(this.mVisitInfoShort.getId(), FicheMode.EDIT);
                                break;
                            case R.id.menu_contextual_send /* 2131297322 */:
                                if (this.mVisitInfoShort.getAuto() == 0) {
                                    VisitRecyclerViewAdapter visitRecyclerViewAdapter7 = this.mAdapter.get();
                                    Intrinsics.checkNotNull(visitRecyclerViewAdapter7);
                                    visitRecyclerViewAdapter7.viewModel.getBaseErp().insertFicheNoParamBroadcastMessage(this.mVisitInfoShort.getId(), FicheType.VISIT.getmValue());
                                    break;
                                } else {
                                    Toast.makeText(ContextUtils.getmContext(), R.string.exp_93_error_auto_visit_operations, Toast.LENGTH_LONG).show();
                                    break;
                                }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mAdapter.get() != null) {
                    VisitRecyclerViewAdapter visitRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(visitRecyclerViewAdapter);
                    if (visitRecyclerViewAdapter.isAttached()) {
                        VisitRecyclerViewAdapter visitRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(visitRecyclerViewAdapter2);
                        ProgressDialogBuilder<?> progressDialogBuilder = visitRecyclerViewAdapter2.mProgressDialogBuilder;
                        Intrinsics.checkNotNull(progressDialogBuilder);
                        progressDialogBuilder.dismiss();
                    }
                }
            }
        }
    public int getItemCount() {
        return this.mVisitInfoShorts.size();
    }
    public void setmVisitListFragment(VisitListFragment visitListFragment) {
        this.mVisitListFragment = visitListFragment;
    }
    public void getVisitFiche(int i2, FicheMode ficheMode) {
        this.mLastFicheMode = ficheMode;
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.setMessage(this.mContext.getString(R.string.str_fiche_loading)).setOnCancelClickListener().show();
        this.viewModel.getVisitExam(i2, new VisitRecyclerSalesFicheGet(this));
    }
    public static void getVisitFichelambda4(VisitRecyclerViewAdapter this0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        this0.mLastFicheMode = null;
        dialogInterface.dismiss();
    }
    public void onDeleted(int i2) {
        if (i2 == -1) {
            Toast.makeText(this.mContext, R.string.exp_27_database_fiche_delete_error, Toast.LENGTH_SHORT).show();
            return;
        }
        VisitListFragment visitListFragment = this.mVisitListFragment;
        Intrinsics.checkNotNull(visitListFragment);
        visitListFragment.restartLoader();
    }
    public void onVisitGet(Visit visit, String str) {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
        if (visit != null) {
            if (this.mLastFicheMode != null) {
                VisitListFragment visitListFragment = this.mVisitListFragment;
                Intrinsics.checkNotNull(visitListFragment);
                FicheMode ficheMode = this.mLastFicheMode;
                Intrinsics.checkNotNull(ficheMode);
                visitListFragment.openVisitFiche(visit, ficheMode);
                return;
            }
            Context context = this.mContext;
            Toast.makeText(context, context.getString(R.string.str_cancel_process), Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(this.mContext, str, Toast.LENGTH_LONG).show();
    }
    public void setmActivity(Activity activity) {
        this.mActivity = activity;
    }

    private record VisitRecyclerSalesFicheGet(
            WeakReference<VisitRecyclerViewAdapter> mAdapter) implements ResponseListener<Visit> {
            private VisitRecyclerSalesFicheGet(VisitRecyclerViewAdapter mAdapter) {
                this.mAdapter = new WeakReference<>(mAdapter);
            }

        public void onResponse(PrintSlipModel visit) {
                if (this.mAdapter.get() != null) {
                    VisitRecyclerViewAdapter visitRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(visitRecyclerViewAdapter);
                    if (visitRecyclerViewAdapter.isAttached()) {
                        VisitRecyclerViewAdapter visitRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(visitRecyclerViewAdapter2);
                        visitRecyclerViewAdapter2.onVisitGet(visit, "");
                    }
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mAdapter.get() != null) {
                    VisitRecyclerViewAdapter visitRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(visitRecyclerViewAdapter);
                    if (visitRecyclerViewAdapter.isAttached()) {
                        VisitRecyclerViewAdapter visitRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(visitRecyclerViewAdapter2);
                        visitRecyclerViewAdapter2.onVisitGet(null, errorMessage);
                    }
                }
            }
        }

    private record DeleteLocalCallBack(
            WeakReference<VisitRecyclerViewAdapter> mAdapter) implements ResponseListener<Integer> {
            private DeleteLocalCallBack(VisitRecyclerViewAdapter mAdapter) {
                this.mAdapter = new WeakReference<>(mAdapter);
            }

        public void onResponse(PrintSlipModel num) {
                if (this.mAdapter.get() != null) {
                    VisitRecyclerViewAdapter visitRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(visitRecyclerViewAdapter);
                    if (visitRecyclerViewAdapter.isAttached()) {
                        VisitRecyclerViewAdapter visitRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(visitRecyclerViewAdapter2);
                        Intrinsics.checkNotNull(num);
                        visitRecyclerViewAdapter2.onDeleted(num.intValue());
                    }
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mAdapter.get() != null) {
                    VisitRecyclerViewAdapter visitRecyclerViewAdapter = this.mAdapter.get();
                    Intrinsics.checkNotNull(visitRecyclerViewAdapter);
                    if (visitRecyclerViewAdapter.isAttached()) {
                        VisitRecyclerViewAdapter visitRecyclerViewAdapter2 = this.mAdapter.get();
                        Intrinsics.checkNotNull(visitRecyclerViewAdapter2);
                        visitRecyclerViewAdapter2.onDeleted(-1);
                    }
                }
            }
        }
}
