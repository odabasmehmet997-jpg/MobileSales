package com.proje.mobilesales.features.collections.chequeanddeedfiche.adapter;

import android.R;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.view.ActionMode;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.interfaces.ActionModeDelegate;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.utils.MenuTintDelegate;
import com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: ChequeDeedDetailListRecyclerViewAdapter.kt */

public final class ChequeDeedDetailListRecyclerViewAdaptermActionModeCallback1 implements ActionMode.Callback {
    private boolean mPendingClear;
    final ChequeDeedDetailListRecyclerViewAdapter this0;

    @Override // androidx.appcompat.view.ActionMode.Callback
    public boolean onPrepareActionMode(final ActionMode actionMode, final Menu menu) {
        Intrinsics.checkNotNullParameter(actionMode, "actionMode");
        Intrinsics.checkNotNullParameter(menu, "menu");
        return false;
    }

    ChequeDeedDetailListRecyclerViewAdaptermActionModeCallback1(final ChequeDeedDetailListRecyclerViewAdapter chequeDeedDetailListRecyclerViewAdapter) {
        this0 = chequeDeedDetailListRecyclerViewAdapter;
    }

    @Override // androidx.appcompat.view.ActionMode.Callback
    public boolean onCreateActionMode(final ActionMode actionMode, final Menu menu) {
        final MenuTintDelegate menuTintDelegate;
        Intrinsics.checkNotNullParameter(actionMode, "actionMode");
        Intrinsics.checkNotNullParameter(menu, "menu");
        actionMode.getMenuInflater().inflate(R.menu.menu_action_sales_line, menu);
        menuTintDelegate = this0.mMenuTintDelegate;
        Intrinsics.checkNotNull(menuTintDelegate);
        menuTintDelegate.onOptionsMenuCreated(menu);
        return true;
    }

    @Override // androidx.appcompat.view.ActionMode.Callback
    public boolean onActionItemClicked(ActionMode actionMode, final MenuItem menuItem) {
        final AlertDialogBuilder message;
        final AlertDialogBuilder negativeButton;
        final Dialog create;
        Intrinsics.checkNotNullParameter(actionMode, "actionMode");
        Intrinsics.checkNotNullParameter(menuItem, "menuItem");
        if (R.id.menu_clear == menuItem.getItemId()) {
            final AlertDialogBuilder<?> alertDialogBuilder = this0.mAlertDialogBuilder;
            if (null == alertDialogBuilder || null == (message = alertDialogBuilder.setMessage(R.string.str_confirm_clear_selected))) {
                return true;
            }
            ChequeDeedDetailListRecyclerViewAdapter chequeDeedDetailListRecyclerViewAdapter = this0;
            final AlertDialogBuilder positiveButton = message.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.collections.chequeanddeedfiche.adapter.ChequeDeedDetailListRecyclerViewAdaptermActionModeCallback1ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(final DialogInterface dialogInterface, final int i2) {
                    onActionItemClickedlambda0(ChequeDeedDetailListRecyclerViewAdaptermActionModeCallback1.this, chequeDeedDetailListRecyclerViewAdapter, actionMode, dialogInterface, i2);
                }
            });
            if (null == positiveButton || null == (negativeButton = positiveButton.setNegativeButton(R.string.cancel, null)) || null == (create = negativeButton.create())) {
                return true;
            }
            create.show();
            return true;
        }
        if (R.id.menu_select_all != menuItem.getItemId()) {
            return false;
        }
        this0.selectAll();
        return false;
    }

    
    public static void onActionItemClickedlambda0(final ChequeDeedDetailListRecyclerViewAdaptermActionModeCallback1 this0, final ChequeDeedDetailListRecyclerViewAdapter this1, final ActionMode actionMode, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(this1, "this1");
        Intrinsics.checkNotNullParameter(actionMode, "actionMode");
        this0.mPendingClear = true;
        this1.removeSelection();
        actionMode.finish();
    }

    @Override // androidx.appcompat.view.ActionMode.Callback
    @SuppressLint("NotifyDataSetChanged")
    public void onDestroyActionMode(final ActionMode actionMode) {
        final ActionModeDelegate actionModeDelegate;
        final SparseBooleanArray sparseBooleanArray;
        Intrinsics.checkNotNullParameter(actionMode, "actionMode");
        if (this0.isAttached()) {
            actionModeDelegate = this0.mActionModeDelegate;
            Intrinsics.checkNotNull(actionModeDelegate);
            actionModeDelegate.stopActionMode();
            if (!mPendingClear) {
                sparseBooleanArray = this0.mSelected;
                sparseBooleanArray.clear();
            } else {
                mPendingClear = false;
            }
            this0.notifyDataSetChanged();
        }
    }
}
