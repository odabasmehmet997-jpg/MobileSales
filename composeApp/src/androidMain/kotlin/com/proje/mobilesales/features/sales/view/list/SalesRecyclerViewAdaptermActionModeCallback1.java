package com.proje.mobilesales.features.sales.view.list;

import android.R;
import android.content.DialogInterface;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.view.ActionMode;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.ActionModeDelegate;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter;
import com.proje.mobilesales.features.sales.model.SalesShort;
import kotlin.jvm.internal.Intrinsics;
 
public final class SalesRecyclerViewAdaptermActionModeCallback1 implements ActionMode.Callback {
    private boolean mPendingClear;
    final   SalesRecyclerViewAdapter this0;
 
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        Intrinsics.checkNotNullParameter(actionMode, "actionMode");
        Intrinsics.checkNotNullParameter(menu, "menu");
        return false;
    }

    SalesRecyclerViewAdaptermActionModeCallback1(SalesRecyclerViewAdapter salesRecyclerViewAdapter) {
        this.this0 = salesRecyclerViewAdapter;
    } 
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        Intrinsics.checkNotNullParameter(actionMode, "actionMode");
        Intrinsics.checkNotNullParameter(menu, "menu");
        actionMode.getMenuInflater().inflate(R.menu.menu_action_sales_line, menu);
        this.this0.getMMenuTintDelegate().onOptionsMenuCreated(menu);
        return true;
    } 
    public boolean onActionItemClicked(final ActionMode actionMode, MenuItem menuItem) {
        SparseBooleanArray sparseBooleanArray;
        SparseBooleanArray sparseBooleanArray2;
        SparseBooleanArray sparseBooleanArray3;
        boolean isOrderAbleToDelete;
        Intrinsics.checkNotNullParameter(actionMode, "actionMode");
        Intrinsics.checkNotNullParameter(menuItem, "menuItem");
        if (menuItem.getItemId() == R.id.menu_clear) {
            AlertDialogBuilder<?> mAlertDialogBuilder = this.this0.getMAlertDialogBuilder();
            Intrinsics.checkNotNull(mAlertDialogBuilder);
            AlertDialogBuilder message = mAlertDialogBuilder.setMessage(R.string.str_confirm_clear_selected);
            final SalesRecyclerViewAdapter salesRecyclerViewAdapter = this.this0;
            message.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i2) {
                    SalesRecyclerViewAdaptermActionModeCallback1.onActionItemClickedlambda0(SalesRecyclerViewAdaptermActionModeCallback1.this, salesRecyclerViewAdapter, actionMode, dialogInterface, i2);
                }
            }).setNegativeButton(R.string.cancel, null).create().show();
            return true;
        }
        if (menuItem.getItemId() != R.id.menu_select_all) {
            return false;
        }
        this.this0.selectAll();
        sparseBooleanArray = this.this0.mSelected;
        for (int size = sparseBooleanArray.size() - 1; -1 < size; size--) {
            sparseBooleanArray2 = this.this0.mSelected;
            if (sparseBooleanArray2.valueAt(size)) {
                SalesRecyclerViewAdapter salesRecyclerViewAdapter2 = this.this0;
                sparseBooleanArray3 = salesRecyclerViewAdapter2.mSelected;
                SalesShort item = salesRecyclerViewAdapter2.getItem(sparseBooleanArray3.keyAt(size));
                SalesRecyclerViewAdapter salesRecyclerViewAdapter3 = this.this0;
                Intrinsics.checkNotNull(item);
                isOrderAbleToDelete = salesRecyclerViewAdapter3.isOrderAbleToDelete(item.getSalesOrderStatus());
                if (isOrderAbleToDelete) {
                    this.this0.getSalesRefList().add(Integer.valueOf(item.getLogicalRef()));
                }
            }
        }
        return false;
    } 
    public static void onActionItemClickedlambda0(SalesRecyclerViewAdaptermActionModeCallback1 this0, SalesRecyclerViewAdapter this1, ActionMode actionMode, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(this1, "this1");
        Intrinsics.checkNotNullParameter(actionMode, "actionMode");
        this0.mPendingClear = true;
        this1.removeSelection();
        this1.viewModel.deleteSalesFicheListLocal(this1.getSalesRefList(), SalesType.ORDER, new SalesRecyclerViewAdapter.DeleteLocalCallBackList(this1));
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
                this.this0.getSalesRefList().clear();
            } else {
                this.mPendingClear = false;
            }
            this.this0.notifyDataSetChanged();
        }
    }
}
