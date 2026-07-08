package com.proje.mobilesales.features.sales.view.detail;

import android.R;
import android.content.DialogInterface;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.view.ActionMode;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.interfaces.ActionModeDelegate;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter;
import kotlin.jvm.internal.Intrinsics;
 
public final class SalesDetailLineRecyclerViewAdaptermActionModeCallback1 implements ActionMode.Callback {
    private boolean mPendingClear;
    final  SalesDetailLineRecyclerViewAdapter this0;
 
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        Intrinsics.checkNotNullParameter(actionMode, "actionMode");
        Intrinsics.checkNotNullParameter(menu, "menu");
        return false;
    }

    SalesDetailLineRecyclerViewAdaptermActionModeCallback1(SalesDetailLineRecyclerViewAdapter salesDetailLineRecyclerViewAdapter) {
        this.this0 = salesDetailLineRecyclerViewAdapter;
    }
 
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        Intrinsics.checkNotNullParameter(actionMode, "actionMode");
        Intrinsics.checkNotNullParameter(menu, "menu");
        actionMode.getMenuInflater().inflate(R.menu.menu_action_sales_line, menu);
        this.this0.getMMenuTintDelegate().onOptionsMenuCreated(menu);
        return true;
    }
 
    public boolean onActionItemClicked(final ActionMode actionMode, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(actionMode, "actionMode");
        Intrinsics.checkNotNullParameter(menuItem, "menuItem");
        if (menuItem.getItemId() == R.id.menu_clear) {
            AlertDialogBuilder<?> mAlertDialogBuilder = this.this0.getMAlertDialogBuilder();
            Intrinsics.checkNotNull(mAlertDialogBuilder);
            AlertDialogBuilder message = mAlertDialogBuilder.setMessage(R.string.str_confirm_clear_selected);
            final SalesDetailLineRecyclerViewAdapter salesDetailLineRecyclerViewAdapter = this.this0;
            message.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { 
                public void onClick(DialogInterface dialogInterface, int i2) {
                    SalesDetailLineRecyclerViewAdaptermActionModeCallback1.onActionItemClickedlambda0(SalesDetailLineRecyclerViewAdaptermActionModeCallback1.this, salesDetailLineRecyclerViewAdapter, actionMode, dialogInterface, i2);
                }
            }).setNegativeButton(R.string.cancel, null).create().show();
            return true;
        }
        if (menuItem.getItemId() != R.id.menu_select_all) {
            return false;
        }
        this.this0.selectAll();
        return false;
    }
 
    public static void onActionItemClickedlambda0(SalesDetailLineRecyclerViewAdaptermActionModeCallback1 this0, SalesDetailLineRecyclerViewAdapter this1, ActionMode actionMode, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(this1, "this1");
        Intrinsics.checkNotNullParameter(actionMode, "actionMode");
        this0.mPendingClear = true;
        this1.removeSelection();
        actionMode.finish();
    }
 
    public void onDestroyActionMode(ActionMode actionMode) {
        ActionModeDelegate actionModeDelegate;
        SparseBooleanArray sparseBooleanArray;
        Intrinsics.checkNotNullParameter(actionMode, "actionMode");
        if (this.this0.isAttached()) {
            actionModeDelegate = this.this0.mActionModeDelegate;
            Intrinsics.checkNotNull(actionModeDelegate);
            actionModeDelegate.stopActionMode();
            if (!this.mPendingClear) {
                sparseBooleanArray = this.this0.mSelected;
                sparseBooleanArray.clear();
            } else {
                this.mPendingClear = false;
            }
            this.this0.notifyDataSetChanged();
        }
    }
}
