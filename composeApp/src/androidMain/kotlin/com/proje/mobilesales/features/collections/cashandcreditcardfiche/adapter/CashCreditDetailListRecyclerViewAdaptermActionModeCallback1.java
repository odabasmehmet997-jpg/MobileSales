package com.proje.mobilesales.features.collections.cashandcreditcardfiche.adapter;

import android.R;
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
 
public final class CashCreditDetailListRecyclerViewAdaptermActionModeCallback1 implements ActionMode.Callback {
    private boolean mPendingClear;
    final  CashCreditDetailListRecyclerViewAdapter this0;
    public boolean onPrepareActionMode(final ActionMode actionMode, final Menu menu) {
        Intrinsics.checkNotNullParameter(actionMode, "actionMode");
        Intrinsics.checkNotNullParameter(menu, "menu");
        return false;
    }
    CashCreditDetailListRecyclerViewAdaptermActionModeCallback1(final CashCreditDetailListRecyclerViewAdapter cashCreditDetailListRecyclerViewAdapter) {
        this0 = cashCreditDetailListRecyclerViewAdapter;
    }
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
    public boolean onActionItemClicked(ActionMode actionMode, final MenuItem menuItem) {
        final AlertDialogBuilder message;
        final AlertDialogBuilder negativeButton;
        final Dialog create;
        Intrinsics.checkNotNullParameter(actionMode, "actionMode");
        Intrinsics.checkNotNullParameter(menuItem, "menuItem");
        if (R.id.menu_clear == menuItem.getItemId()) {
            final AlertDialogBuilder<?> mAlertDialogBuilder = this0.getMAlertDialogBuilder();
            if (null == mAlertDialogBuilder || null == (message = mAlertDialogBuilder.setMessage(R.string.str_confirm_clear_selected))) {
                return true;
            }
            CashCreditDetailListRecyclerViewAdapter cashCreditDetailListRecyclerViewAdapter = this0;
            final AlertDialogBuilder positiveButton = message.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int i2) {
                    onActionItemClickedlambda0(CashCreditDetailListRecyclerViewAdaptermActionModeCallback1.this, cashCreditDetailListRecyclerViewAdapter, actionMode, dialogInterface, i2);
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
    public static void onActionItemClickedlambda0(final CashCreditDetailListRecyclerViewAdaptermActionModeCallback1 this0, final CashCreditDetailListRecyclerViewAdapter this1, final ActionMode actionMode, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(this1, "this1");
        Intrinsics.checkNotNullParameter(actionMode, "actionMode");
        this0.mPendingClear = true;
        this1.removeSelection();
        actionMode.finish();
    }
    public void onDestroyActionMode(final ActionMode actionMode) {
        final ActionModeDelegate actionModeDelegate;
        final SparseBooleanArray sparseBooleanArray;
        Intrinsics.checkNotNullParameter(actionMode, "actionMode");
        if (this0.isAttached()) {
            actionModeDelegate = this0.mActionModeDelegate;
            Intrinsics.checkNotNull(actionModeDelegate);
            actionModeDelegate.stopActionMode();
            if (!mPendingClear) {
                sparseBooleanArray = this0.getSelected();
                sparseBooleanArray.clear();
            } else {
                mPendingClear = false;
            }
            this0.notifyDataSetChanged();
        }
    }
}
