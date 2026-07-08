package com.proje.mobilesales.features.penetration.view.detail;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.interfaces.ActionModeDelegate;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.PopupMenu;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.MenuTintDelegate;
import com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter;
import com.proje.mobilesales.features.penetration.model.PenetrationLine;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
 
public final class PenetrationLineRecyclerViewAdapter extends ListRecyclerViewAdapter<PenetrationLineViewHolder, PenetrationLine> {
    private ActionModeDelegate mActionModeDelegate;
    private PenetrationActivity mActivity;
    private AlertDialogBuilder<?> mAlertDialogBuilder;
    private boolean mExist;
    private FicheMode mFicheMode;
    private MenuTintDelegate mMenuTintDelegate;
    private List<PenetrationLine> mPenetrationLines;
    private AlertDialogBuilder<?> mPhotoShowAlertDialogBuilder;
    private PopupMenu mPopupMenu;
    private int mPendingAdd = -1;
    private final ArrayList<String> mSelectedPenetrations = new ArrayList<>();

    public PenetrationLineRecyclerViewAdapter(com.proje.mobilesales.features.privacy.PenetrationActivity actionModeDelegate, com.proje.mobilesales.features.privacy.PenetrationActivity penetrationActivity) {
    }

    public void bindItem(PenetrationLineViewHolder penetrationLineViewHolder) {
    }
    public int getItemViewType(int i) {
        return i;
    }
    public void handleItemClick(PenetrationLine penetrationLine, PenetrationLineViewHolder penetrationLineViewHolder) {
    }
    public boolean isItemAvailable(PenetrationLine penetrationLine) {
        return penetrationLine != null;
    }
    public PenetrationActivity getMActivity() {
        return this.mActivity;
    }
    public void setMActivity(PenetrationActivity penetrationActivity) {
        Intrinsics.checkNotNullParameter(penetrationActivity, "<set-?>");
        this.mActivity = penetrationActivity;
    }
    public FicheMode getMFicheMode() {
        return this.mFicheMode;
    }
    public void setMFicheMode(FicheMode ficheMode) {
        Intrinsics.checkNotNullParameter(ficheMode, "<set-?>");
        this.mFicheMode = ficheMode;
    }
    public PenetrationLineRecyclerViewAdapter(ActionModeDelegate actionModeDelegate, PenetrationActivity penetrationActivity, FicheMode ficheMode) {
        Intrinsics.checkNotNullParameter(penetrationActivity, "mActivity");
        Intrinsics.checkNotNullParameter(ficheMode, "mFicheMode");
        this.mActionModeDelegate = actionModeDelegate;
        this.mActivity = penetrationActivity;
        this.mFicheMode = ficheMode;
    }

    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        return this.mAlertDialogBuilder;
    }

    public void setMAlertDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        this.mAlertDialogBuilder = alertDialogBuilder;
    }

    public AlertDialogBuilder<?> getMPhotoShowAlertDialogBuilder() {
        return this.mPhotoShowAlertDialogBuilder;
    }

    public void setMPhotoShowAlertDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        this.mPhotoShowAlertDialogBuilder = alertDialogBuilder;
    }

    public PopupMenu getMPopupMenu() {
        return this.mPopupMenu;
    }

    public void setMPopupMenu(PopupMenu popupMenu) {
        this.mPopupMenu = popupMenu;
    }
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        Context context = this.mContext;
        if (context instanceof BaseInjectableActivity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
            ((BaseInjectableActivity) context).getActivityComponent().inject(this);
        }
        MenuTintDelegate menuTintDelegate = new MenuTintDelegate();
        this.mMenuTintDelegate = menuTintDelegate;
        Intrinsics.checkNotNull(menuTintDelegate);
        menuTintDelegate.onActivityCreated(this.mContext);
        Context context2 = this.mContext;
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilder = new AlertDialogBuilder.Impl(context2, (BaseInjectableActivity) activity);
        Context context3 = this.mContext;
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mPhotoShowAlertDialogBuilder = new AlertDialogBuilder.Impl(context3, (BaseInjectableActivity) activity2);
        this.mPopupMenu = new PopupMenu.Impl();
    }
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onDetachedFromRecyclerView(recyclerView);
        this.mActionModeDelegate = null;
    }
    public void clearViewHolder(PenetrationLineViewHolder penetrationLineViewHolder) {
        PenetrationLineView penetrationLineView;
        if (penetrationLineViewHolder != null && (penetrationLineView = penetrationLineViewHolder.mPenetrationLineView) != null) {
            penetrationLineView.reset();
        }
    }
    public PenetrationLine getItem(int i) {
        List<PenetrationLine> list = this.mPenetrationLines;
        if (list == null) {
            return null;
        }
        Intrinsics.checkNotNull(list);
        return list.get(i);
    }
    public PenetrationLineViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        View inflate = this.mInflater.inflate(R.layout.item_penetration_list, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new PenetrationLineViewHolder(inflate);
    }

    public void onBindViewHolder(PenetrationLineViewHolder penetrationLineViewHolder, int i) {
        Intrinsics.checkNotNullParameter(penetrationLineViewHolder, "holder");
        PenetrationLine item = getItem(i);
        if (item == null) {
            return;
        }
        if (isItemAvailable(item)) {
            FicheMode ficheMode = this.mActivity.getmCustomerOperation().getFicheMode();
            if (ficheMode == null) {
                return;
            }
            PenetrationLineView penetrationLineView = penetrationLineViewHolder.mPenetrationLineView;
            Intrinsics.checkNotNull(item);
            penetrationLineView.setPenetrationLine(item, this.mExist, ficheMode);
            Intrinsics.checkNotNull(item);
            if (item.isImageActive()) {
                penetrationLineViewHolder.mPenetrationLineView.getImgBtnPenetrationCam().setOnClickListener(new View.OnClickListener(item, i, ficheMode) {
                    public final  PenetrationLine f1 = null;
                    public final  int f2 = 0;
                    public final  FicheMode f3 = null;
                    public void onClick(View view) {
                        PenetrationLineRecyclerViewAdapter.onBindViewHolderlambda0(PenetrationLineRecyclerViewAdapter.this, this.f1, this.f2, this.f3, view);
                    }
                });
            } else {
                penetrationLineViewHolder.mPenetrationLineView.getImgBtnPenetrationCam().setOnClickListener(null);
            }
            if (item.isNotActive()) {
                penetrationLineViewHolder.mPenetrationLineView.getImgBtnPenetrationNot().setOnClickListener(new View.OnClickListener(item, ficheMode) { 
                    public final   PenetrationLine f1 = null;
                    public final  FicheMode f2 = null;

                    public void onClick(View view) {
                        PenetrationLineRecyclerViewAdapter.onBindViewHolderlambda1(PenetrationLineRecyclerViewAdapter.this, this.f1, this.f2, view);
                    }
                });
            } else {
                penetrationLineViewHolder.mPenetrationLineView.getImgBtnPenetrationNot().setOnClickListener(null);
            }
        }
    }

    public static   void onBindViewHolderlambda0(PenetrationLineRecyclerViewAdapter penetrationLineRecyclerViewAdapter, PenetrationLine penetrationLine, int i, FicheMode ficheMode, View view) {
        Intrinsics.checkNotNullParameter(penetrationLineRecyclerViewAdapter, "this0");
        penetrationLineRecyclerViewAdapter.mActivity.showImageActivity(penetrationLine.getImage(), i, ficheMode);
    }
    public static   void onBindViewHolderlambda1(PenetrationLineRecyclerViewAdapter penetrationLineRecyclerViewAdapter, PenetrationLine penetrationLine, FicheMode ficheMode, View view) {
        Intrinsics.checkNotNullParameter(penetrationLineRecyclerViewAdapter, "this0");
        penetrationLineRecyclerViewAdapter.mActivity.showNotDialog(penetrationLine.getNot(), ficheMode);
    }

    public   List<PenetrationLine> getPenetrationLines() {
        return this.mPenetrationLines;
    }

    public   void setPenetrationLines(List<PenetrationLine> list) {
        if (list != null) {
            this.mPenetrationLines = list;
            notifyDataSetChanged();
            List<PenetrationLine> list2 = this.mPenetrationLines;
            Intrinsics.checkNotNull(list2);
            if (!list2.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                Collections.sort(arrayList);
                this.mSelected.clear();
                this.mSelectedPenetrations.clear();
                int size = arrayList.size() - 1;
                if (size >= 0) {
                    while (true) {
                        int i = size - 1;
                        notifyItemRemoved(((Number) arrayList.get(size)).intValue());
                        if (i >= 0) {
                            size = i;
                        } else {
                            return;
                        }
                    }
                }
            } else {
                int i2 = this.mPendingAdd;
                if (i2 >= 0) {
                    notifyItemInserted(i2);
                    this.mPendingAdd = -1;
                    return;
                }
                notifyDataSetChanged();
            }
        }
    }
    public int getItemCount() {
        List<PenetrationLine> list = this.mPenetrationLines;
        if (list == null) {
            return 0;
        }
        Intrinsics.checkNotNull(list);
        return list.size();
    }
    public long getItemId(int i) {
        PenetrationLine item = getItem(i);
        return item != null ? item.getUniqueId() : super.getItemId(i);
    }
     public void restoreState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "savedState");
        super.restoreState(bundle);
    }
     public Bundle saveState() {
        Bundle saveState = super.saveState();
        Intrinsics.checkNotNullExpressionValue(saveState, "saveState(...)");
        return saveState;
    }

    public   void setExist(boolean z) {
        this.mExist = z;
    }
}
