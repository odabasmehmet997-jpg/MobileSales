package com.proje.mobilesales.features.penetration.view.list;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.enums.WorkTimeControlProcessType;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.PopupMenu;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.features.adapter.EndlessListRecyclerViewAdapter;
import com.proje.mobilesales.features.adapter.LoadingViewHolder;
import com.proje.mobilesales.features.penetration.model.database.PenetrationShort;
import com.proje.mobilesales.features.penetration.repository.PenetrationListRepository;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import com.proje.mobilesales.features.visit.model.VisitInfoShort;import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class PenetrationRecyclerViewAdapter extends EndlessListRecyclerViewAdapter<RecyclerView.ViewHolder, PenetrationShort> {
    public AlertDialogBuilder<?> mCopyPenetrationAlertDialogBuilder;
    private final FicheMode mLastFicheMode = null;
    private PenetrationListFragment mPenetrationListFragment;
    public PopupMenu mPopupMenu;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    private int mSavedPenetrationID;
    private final List<PenetrationShort> mPenetrationShorts = new ArrayList();
    private final PenetrationListRepository repository = new PenetrationListRepository();
    private final PenetrationListViewModel viewModel = new PenetrationListViewModel(this.repository);

    public static void showCopyAndDeletePenetrationDialoglambda4(DialogInterface dialogInterface, int i) {
    }
    protected void bindItem(RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
    }

    protected void handleItemClick(Object obj, RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(obj, "item");
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
    }

    protected boolean isItemAvailable(Object obj) {
        return obj != null;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        if (i == this.VIEW_TYPE_ITEM) {
            View inflate = this.mInflater.inflate(R.layout.item_penetration, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
            return new PenetrationViewHolder(inflate);
        }
        LoadingViewHolder loadingViewHolder = i == this.VIEW_TYPE_LOADING ? new LoadingViewHolder(this.mInflater.inflate(R.layout.item_loading, viewGroup, false)) : null;
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
        Context context2 = this.mContext;
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mCopyPenetrationAlertDialogBuilder = new AlertDialogBuilder.Impl(context2, (BaseInjectableActivity) activity);
        Context context3 = this.mContext;
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(context3, (BaseInjectableActivity) activity2);
        this.mPopupMenu = new PopupMenu.Impl();
    }
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onDetachedFromRecyclerView(recyclerView);
        this.mPenetrationShorts.clear();
    }
    protected void clearViewHolder(RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        if (viewHolder instanceof PenetrationViewHolder) {
            ((PenetrationViewHolder) viewHolder).getMPenetrationView().reset();
        }
    }
    public void insertProgressBar() {
        if (!this.mPenetrationShorts.contains(null)) {
            this.mPenetrationShorts.add(null);
            notifyItemInserted(getItemCount() - 1);
        }
    }
    public void removeProgressBar() {
        int indexOf = this.mPenetrationShorts.indexOf(null);
        if (indexOf != -1) {
            this.mPenetrationShorts.remove(indexOf);
            notifyItemRemoved(indexOf);
        }
    }
    public void addItem(ArrayList<ArrayList<VisitInfoShort>> list) {
        Intrinsics.checkNotNullParameter(list, FirebaseAnalytics.Param.ITEMS);
        removeProgressBar();
        this.mPenetrationShorts.addAll(list);
        this.mRecyclerView.getRecycledViewPool().clear();
        notifyDataSetChanged();
    }
    protected void restartAdapterAndScroll() {
        if (getItemCount() > 0) {
            this.mPenetrationShorts.clear();
        }
        notifyDataSetChanged();
    }
    public long getItemId(int i) {
        PenetrationShort item = getItem(i);
        return item != null ? (long) item.getId() : super.getItemId(i);
    }
    public PenetrationShort getItem(int i) {
        return this.mPenetrationShorts.get(i);
    }
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        if (viewHolder instanceof PenetrationViewHolder penetrationViewHolder3) {
            PenetrationShort item = getItem(i);
            if (this.mCardViewEnabled) {
                penetrationViewHolder3.mCardView.setCardElevation((float) this.mCardElevation);
                penetrationViewHolder3.mCardView.setRadius((float) this.mCardRadius);
                penetrationViewHolder3.mCardView.setUseCompatPadding(true);
            } else {
                penetrationViewHolder3.mCardView.setCardElevation(0.0f);
                penetrationViewHolder3.mCardView.setRadius(0.0f);
                penetrationViewHolder3.mCardView.setUseCompatPadding(false);
            }
            clearViewHolder(viewHolder);
            if (!isItemAvailable(item)) {
                loadItem(viewHolder.getAdapterPosition());
                return;
            }
            PenetrationView mPenetrationView = penetrationViewHolder3.getMPenetrationView();
            Intrinsics.checkNotNull(item);
            mPenetrationView.setPenetration(item);
            penetrationViewHolder3.getMPenetrationView().setOnClickListener(new View.OnClickListener(item) {
                public final   PenetrationShort f1 = null;

                public void onClick(View view) {
                    PenetrationRecyclerViewAdapter.onBindViewHolderlambda0(PenetrationRecyclerViewAdapter.this, this.f1, view);
                }
            });
            penetrationViewHolder3.getMPenetrationView().getmMoreOption().setOnClickListener(new View.OnClickListener(viewHolder, item, i) {
                public final  RecyclerView.ViewHolder f1 = null;
                public final  PenetrationShort f2 = null;
                public final int f3 = 0;

                public void onClick(View view) {
                    PenetrationRecyclerViewAdapter.onBindViewHolderlambda1(PenetrationRecyclerViewAdapter.this, this.f1, this.f2, this.f3, view);
                }
            });
            penetrationViewHolder3.getMPenetrationView().setOnLongClickListener(new View.OnLongClickListener(item, i) { // from class: com.proje.mobilesales.features.penetration.view.list.PenetrationRecyclerViewAdapterExternalSyntheticLambda3
                public final PenetrationShort f1 = null;
                public final int f2 = 0;

                public boolean onLongClick(View view) {
                    return PenetrationRecyclerViewAdapter.onBindViewHolderlambda2(PenetrationRecyclerViewAdapter.this, this.f1, this.f2, view);
                }
            });
        } else if (viewHolder instanceof LoadingViewHolder) {
            ((LoadingViewHolder) viewHolder).progressBar.setIndeterminate(true);
        }
    }
    public static void onBindViewHolderlambda0(PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter, PenetrationShort penetrationShort, View view) {
        Intrinsics.checkNotNullParameter(penetrationRecyclerViewAdapter, "this0");
        penetrationRecyclerViewAdapter.getPenetrationFiche(penetrationShort.getId(), FicheMode.ANALYSE);
    }
    public static void onBindViewHolderlambda1(PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter, RecyclerView.ViewHolder viewHolder, PenetrationShort penetrationShort, int i, View view) {
        Intrinsics.checkNotNullParameter(penetrationRecyclerViewAdapter, "this0");
        Intrinsics.checkNotNullParameter(viewHolder, "penetrationViewHolder");
        penetrationRecyclerViewAdapter.showMoreOptions(((PenetrationViewHolder) viewHolder).getMPenetrationView(), penetrationShort, i);
    }
    public static boolean onBindViewHolderlambda2(PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter, PenetrationShort penetrationShort, int i, View view) {
        Intrinsics.checkNotNullParameter(penetrationRecyclerViewAdapter, "this0");
        Intrinsics.checkNotNullParameter(view, "view");
        penetrationRecyclerViewAdapter.showMoreOptions(view, penetrationShort, i);
        return true;
    }

    private void showMoreOptions(View view, PenetrationShort penetrationShort, int i) {
        PopupMenu popupMenu = this.mPopupMenu;
        Intrinsics.checkNotNull(popupMenu);
        popupMenu.create(this.mContext, view, 0).inflate(R.menu.menu_context_sales_list).setMenuItemVisible(R.id.menu_contextual_cancel, false).setMenuItemVisible(R.id.menu_contextual_delete, !penetrationShort.isTransfer()).setMenuItemVisible(R.id.menu_contextual_send, !penetrationShort.isTransfer()).setMenuItemVisible(R.id.menu_contextual_send_email, false).setMenuItemVisible(R.id.menu_contextual_status_change, false).setMenuItemVisible(R.id.menu_contextual_print, false).setMenuItemVisible(R.id.menu_contextual_edit, !penetrationShort.isTransfer()).setMenuItemVisible(R.id.menu_contextual_edocument_pdf, false).setMenuItemVisible(R.id.menu_contextual_save_pdf, false).setMenuItemVisible(R.id.menu_contextual_updateAsNotTransfered, false).setMenuItemVisible(R.id.menu_contextual_update_order_fiche_status, false).setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(penetrationShort, i) { // from class: com.proje.mobilesales.features.penetration.view.list.PenetrationRecyclerViewAdapterExternalSyntheticLambda0
            public final PenetrationShort f1 = null;
            public final int f2 = 0;

            public boolean onMenuItemClick(MenuItem menuItem) {
                return PenetrationRecyclerViewAdapter.showMoreOptionslambda3(PenetrationRecyclerViewAdapter.this, this.f1, this.f2, menuItem);
            }
        }).show();
    }


    public static boolean showMoreOptionslambda3(PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter, PenetrationShort penetrationShort, int i, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(penetrationRecyclerViewAdapter, "this0");
        Intrinsics.checkNotNullParameter(penetrationShort, "penetrationShort");
        Intrinsics.checkNotNullParameter(menuItem, "menuItem");
        if (menuItem.getItemId() == R.id.menu_contextual_edit) {
            ProgressDialogBuilder<?> progressDialogBuilder = penetrationRecyclerViewAdapter.mProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder);
            progressDialogBuilder.setMessage(penetrationRecyclerViewAdapter.mContext.getString(R.string.str_please_wait)).show();
            penetrationRecyclerViewAdapter.viewModel.getCheckRemoteWorkTimeControl(WorkTimeControlProcessType.Penetration, new CheckWorkTimeListener(penetrationRecyclerViewAdapter, menuItem.getItemId(), penetrationShort, i));
            return true;
        } else if (menuItem.getItemId() == R.id.menu_contextual_copy) {
            ProgressDialogBuilder<?> progressDialogBuilder2 = penetrationRecyclerViewAdapter.mProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder2);
            progressDialogBuilder2.setMessage(penetrationRecyclerViewAdapter.mContext.getString(R.string.str_please_wait)).show();
            penetrationRecyclerViewAdapter.viewModel.getCheckRemoteWorkTimeControl(WorkTimeControlProcessType.Penetration, new CheckWorkTimeListener(penetrationRecyclerViewAdapter, menuItem.getItemId(), penetrationShort, i));
            return true;
        } else if (menuItem.getItemId() == R.id.menu_contextual_send) {
            ProgressDialogBuilder<?> progressDialogBuilder3 = penetrationRecyclerViewAdapter.mProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder3);
            progressDialogBuilder3.setMessage(penetrationRecyclerViewAdapter.mContext.getString(R.string.str_please_wait)).show();
            penetrationRecyclerViewAdapter.viewModel.getCheckRemoteWorkTimeControl(WorkTimeControlProcessType.TransferSend, new CheckWorkTimeListener(penetrationRecyclerViewAdapter, menuItem.getItemId(), penetrationShort, i));
            return true;
        } else if (menuItem.getItemId() != R.id.menu_contextual_delete) {
            return false;
        } else {
            penetrationRecyclerViewAdapter.deletePenetration(penetrationShort.getId(), i);
            return true;
        }
    }

    public void deletePenetration(int i, int i2) {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.setMessage(this.mContext.getString(R.string.str_please_wait)).show();
        PenetrationShort penetrationShort = new PenetrationShort();
        penetrationShort.setId(i);
        this.viewModel.getCheckRemoteWorkTimeControl(WorkTimeControlProcessType.Penetration, new CheckWorkTimeListener(this, R.id.menu_contextual_delete, penetrationShort, i2));
    }

    public static final class CheckWorkTimeListener implements ResponseListener<String> {
        private final WeakReference<PenetrationRecyclerViewAdapter> mAdapter;
        private final int mMenuId;
        private final PenetrationShort mPenetrationShort;
        private final int mPosition;

        public CheckWorkTimeListener(PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter, int i, PenetrationShort penetrationShort, int i2) {
            Intrinsics.checkNotNullParameter(penetrationShort, "penetrationShort");
            this.mAdapter = new WeakReference<>(penetrationRecyclerViewAdapter);
            this.mMenuId = i;
            this.mPenetrationShort = penetrationShort;
            this.mPosition = i2;
        }

        public void onResponse(PrintSlipModel str) {
            if (this.mAdapter.get() != null) {
                PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter = this.mAdapter.get();
                Intrinsics.checkNotNull(penetrationRecyclerViewAdapter);
                if (penetrationRecyclerViewAdapter.isAttached()) {
                    PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter2 = this.mAdapter.get();
                    Intrinsics.checkNotNull(penetrationRecyclerViewAdapter2);
                    ProgressDialogBuilder<?> progressDialogBuilder = penetrationRecyclerViewAdapter2.mProgressDialogBuilder;
                    Intrinsics.checkNotNull(progressDialogBuilder);
                    progressDialogBuilder.dismiss();
                    if (!TextUtils.isEmpty(str)) {
                        PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter3 = this.mAdapter.get();
                        Intrinsics.checkNotNull(penetrationRecyclerViewAdapter3);
                        PenetrationListFragment penetrationListFragment = penetrationRecyclerViewAdapter3.mPenetrationListFragment;
                        Intrinsics.checkNotNull(penetrationListFragment);
                        Toast.makeText(penetrationListFragment.getActivity(), str, 0).show();
                        return;
                    }
                    switch (this.mMenuId) {
                        case R.id.menu_contextual_copy:
                            PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter4 = this.mAdapter.get();
                            Intrinsics.checkNotNull(penetrationRecyclerViewAdapter4);
                            ISqlHelper logoSqlHelper = penetrationRecyclerViewAdapter4.viewModel.getBaseErp().getLogoSqlHelper();
                            PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter5 = this.mAdapter.get();
                            Intrinsics.checkNotNull(penetrationRecyclerViewAdapter5);
                            PenetrationListFragment penetrationListFragment2 = penetrationRecyclerViewAdapter5.mPenetrationListFragment;
                            Intrinsics.checkNotNull(penetrationListFragment2);
                            Cursor isCustomerEnterPenetrationToday = logoSqlHelper.isCustomerEnterPenetrationToday(penetrationListFragment2.getmCustomerRef(), this.mPenetrationShort.getPenetrationID());
                            PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter6 = this.mAdapter.get();
                            Intrinsics.checkNotNull(penetrationRecyclerViewAdapter6);
                            penetrationRecyclerViewAdapter6.mSavedPenetrationID = 0;
                            if (isCustomerEnterPenetrationToday == null || !isCustomerEnterPenetrationToday.moveToFirst()) {
                                PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter7 = this.mAdapter.get();
                                Intrinsics.checkNotNull(penetrationRecyclerViewAdapter7);
                                penetrationRecyclerViewAdapter7.getPenetrationFiche(this.mPenetrationShort.getId(), FicheMode.COPY);
                                return;
                            }
                            PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter8 = this.mAdapter.get();
                            Intrinsics.checkNotNull(penetrationRecyclerViewAdapter8);
                            penetrationRecyclerViewAdapter8.mSavedPenetrationID = isCustomerEnterPenetrationToday.getInt(isCustomerEnterPenetrationToday.getColumnIndex("ID"));
                            PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter9 = this.mAdapter.get();
                            Intrinsics.checkNotNull(penetrationRecyclerViewAdapter9);
                            penetrationRecyclerViewAdapter9.showCopyAndDeletePenetrationDialog(this.mPenetrationShort.getId(), this.mPenetrationShort.isTransfer(), this.mPenetrationShort.getGuid());
                            return;
                        case R.id.menu_contextual_delete:
                            PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter10 = this.mAdapter.get();
                            Intrinsics.checkNotNull(penetrationRecyclerViewAdapter10);
                            penetrationRecyclerViewAdapter10.viewModel.deletePenetrationFicheLocal(this.mPenetrationShort.getId(), this.mPosition, new DeleteLocalCallBack(this.mAdapter.get()));
                            return;
                        case R.id.menu_contextual_edit:
                            PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter11 = this.mAdapter.get();
                            Intrinsics.checkNotNull(penetrationRecyclerViewAdapter11);
                            penetrationRecyclerViewAdapter11.getPenetrationFiche(this.mPenetrationShort.getId(), FicheMode.EDIT);
                            return;
                        case R.id.menu_contextual_send:
                            PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter12 = this.mAdapter.get();
                            Intrinsics.checkNotNull(penetrationRecyclerViewAdapter12);
                            penetrationRecyclerViewAdapter12.viewModel.getBaseErp().insertFicheNoParamBroadcastMessage(this.mPenetrationShort.getId(), FicheType.PENETRATION.getmValue());
                            return;
                        default:
                    }
                }
            }
        }
        public void onError(String str) {
            Intrinsics.checkNotNullParameter(str, "errorMessage");
            if (this.mAdapter.get() != null) {
                PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter = this.mAdapter.get();
                Intrinsics.checkNotNull(penetrationRecyclerViewAdapter);
                if (penetrationRecyclerViewAdapter.isAttached()) {
                    PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter2 = this.mAdapter.get();
                    Intrinsics.checkNotNull(penetrationRecyclerViewAdapter2);
                    ProgressDialogBuilder<?> progressDialogBuilder = penetrationRecyclerViewAdapter2.mProgressDialogBuilder;
                    Intrinsics.checkNotNull(progressDialogBuilder);
                    progressDialogBuilder.dismiss();
                }
            }
        }
    }
    public void showCopyAndDeletePenetrationDialog(int i, boolean z, String str) {
        AlertDialogBuilder<?> alertDialogBuilder = this.mCopyPenetrationAlertDialogBuilder;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.setMessage(StringsKt.trimIndent("\n    " + this.mContext.getString(R.string.str_enter_only_one_form_day) + "\n    " + this.mContext.getString(R.string.str_question_delete_old_entry_add) + "\n    ")).setTitle(R.string.str_warning).setNegativeButton(17039360, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.penetration.view.list.PenetrationRecyclerViewAdapterExternalSyntheticLambda4
            public void onClick(DialogInterface dialogInterface, int i2) {
                PenetrationRecyclerViewAdapter.showCopyAndDeletePenetrationDialoglambda4(dialogInterface, i2);
            }
        }).setPositiveButton(17039370, new DialogInterface.OnClickListener(z, this, str, i) {
            public final boolean f0 = false;
            public final PenetrationRecyclerViewAdapter f1 = null;
            public final String f2 = "";
            public final int f3 = 0;

            public void onClick(DialogInterface dialogInterface, int i2) {
                PenetrationRecyclerViewAdapter.showCopyAndDeletePenetrationDialoglambda5(this.f0, this.f1, this.f2, this.f3, dialogInterface, i2);
            }
        }).create().show();
    }
    public static void showCopyAndDeletePenetrationDialoglambda5(boolean z, PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter, String str, int i, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(penetrationRecyclerViewAdapter, "this0");
        if (z) {
            penetrationRecyclerViewAdapter.viewModel.getBaseErp().deletePenetrationRemote(str, new ResponseListener<Boolean>(penetrationRecyclerViewAdapter, i) { // from class: com.proje.mobilesales.features.penetration.view.list.PenetrationRecyclerViewAdaptershowCopyAndDeletePenetrationDialog21
                final int id = 0;
                final PenetrationRecyclerViewAdapter this0 = null;
                 public void onResponse(PrintSlipModel bool) {
                    this.this0.getPenetrationFiche(this.id, FicheMode.COPY);
                }
                public void onError(String str2) {
                    Intrinsics.checkNotNullParameter(str2, "errorMessage");
                    Toast.makeText(this.this0.mContext, str2, 1).show();
                }
            });
        } else {
            penetrationRecyclerViewAdapter.getPenetrationFiche(i, FicheMode.COPY);
        }
    }

    public   void getPenetrationFiche(int i, FicheMode ficheMode) {
        PenetrationListFragment penetrationListFragment = this.mPenetrationListFragment;
        Intrinsics.checkNotNull(penetrationListFragment);
        Intrinsics.checkNotNull(ficheMode);
        penetrationListFragment.openPenetrationFiche(i, ficheMode);
    }
    public int getItemCount() {
        return this.mPenetrationShorts.size();
    }

    public   void setPenetrationListFragment(PenetrationListFragment penetrationListFragment) {
        Intrinsics.checkNotNullParameter(penetrationListFragment, "penetrationListFragment");
        this.mPenetrationListFragment = penetrationListFragment;
    }
    public void onDeleted(int i) {
        if (i == -1) {
            Toast.makeText(this.mContext, R.string.exp_27_database_fiche_delete_error, 0).show();
            return;
        }
        PenetrationListFragment penetrationListFragment = this.mPenetrationListFragment;
        Intrinsics.checkNotNull(penetrationListFragment);
        penetrationListFragment.restartLoader();
    }
    public static final class DeleteLocalCallBack implements ResponseListener<Integer> {
        private final WeakReference<PenetrationRecyclerViewAdapter> mAdapter;

        public DeleteLocalCallBack(PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter) {
            this.mAdapter = new WeakReference<>(penetrationRecyclerViewAdapter);
        }

        public void onResponse(PrintSlipModel num) {
            if (this.mAdapter.get() != null) {
                PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter = this.mAdapter.get();
                Intrinsics.checkNotNull(penetrationRecyclerViewAdapter);
                if (penetrationRecyclerViewAdapter.isAttached()) {
                    PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter2 = this.mAdapter.get();
                    Intrinsics.checkNotNull(penetrationRecyclerViewAdapter2);
                    Intrinsics.checkNotNull(num);
                    penetrationRecyclerViewAdapter2.onDeleted(num.intValue());
                }
            }
        }
        public void onError(String str) {
            Intrinsics.checkNotNullParameter(str, "errorMessage");
            if (this.mAdapter.get() != null) {
                PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter = this.mAdapter.get();
                Intrinsics.checkNotNull(penetrationRecyclerViewAdapter);
                if (penetrationRecyclerViewAdapter.isAttached()) {
                    PenetrationRecyclerViewAdapter penetrationRecyclerViewAdapter2 = this.mAdapter.get();
                    Intrinsics.checkNotNull(penetrationRecyclerViewAdapter2);
                    penetrationRecyclerViewAdapter2.onDeleted(-1);
                }
            }
        }
    }
}
