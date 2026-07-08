package com.proje.mobilesales.features.sales.view.validation;

import android.R;
import android.content.DialogInterface;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.view.ActionMode;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.OrderStatus;
import com.proje.mobilesales.core.interfaces.ActionModeDelegate;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.utils.MenuTintDelegate;
import com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter;
import kotlin.jvm.internal.Intrinsics;
 
public final class OrderValidationRecyclerViewAdaptermActionModeCallback1 implements ActionMode.Callback {
    private boolean mPendingClear;
    final  OrderValidationRecyclerViewAdapter this0;

    OrderValidationRecyclerViewAdaptermActionModeCallback1(OrderValidationRecyclerViewAdapter orderValidationRecyclerViewAdapter) {
        this.this0 = orderValidationRecyclerViewAdapter;
    } 
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        MenuTintDelegate menuTintDelegate;
        Intrinsics.checkNotNullParameter(actionMode, "actionMode");
        Intrinsics.checkNotNullParameter(menu, "menu");
        actionMode.getMenuInflater().inflate(R.menu.menu_action_order_validation, menu);
        menuTintDelegate = this.this0.mMenuTintDelegate;
        Intrinsics.checkNotNull(menuTintDelegate);
        menuTintDelegate.onOptionsMenuCreated(menu);
        return true;
    }
 
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        SalesValidationViewModel salesValidationViewModel;
        Intrinsics.checkNotNullParameter(actionMode, "actionMode");
        Intrinsics.checkNotNullParameter(menu, "menu");
        if (menu.hasVisibleItems()) {
            int size = menu.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (menu.getItem(i2).getItemId() == R.id.menu_undispatchable) {
                    salesValidationViewModel = this.this0.viewModel;
                    if (salesValidationViewModel.erpType() == ErpType.NETSIS) {
                        menu.getItem(i2).setVisible(false);
                    }
                }
            }
        }
        return false;
    }
    public boolean onActionItemClicked(final ActionMode actionMode, MenuItem menuItem) {
        SalesValidationViewModel salesValidationViewModel;
        Intrinsics.checkNotNullParameter(actionMode, "actionMode");
        Intrinsics.checkNotNullParameter(menuItem, "menuItem");
        if (menuItem.getItemId() == R.id.menu_dispatchable) {
            AlertDialogBuilder<?> mAlertDialogBuilder = this.this0.getMAlertDialogBuilder();
            Intrinsics.checkNotNull(mAlertDialogBuilder);
            salesValidationViewModel = this.this0.viewModel;
            AlertDialogBuilder message = mAlertDialogBuilder.setMessage(salesValidationViewModel.erpType() == ErpType.NETSIS ? R.string.str_confirm_order_status_change_accept_selected : R.string.str_change_order_status_dispatchable_selected);
            final OrderValidationRecyclerViewAdapter orderValidationRecyclerViewAdapter = this.this0;
            message.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i2) {
                    OrderValidationRecyclerViewAdaptermActionModeCallback1.onActionItemClickedlambda0(OrderValidationRecyclerViewAdaptermActionModeCallback1.this, orderValidationRecyclerViewAdapter, actionMode, dialogInterface, i2);
                }
            }).setNegativeButton(R.string.cancel, null).create().show();
            return true;
        }
        if (menuItem.getItemId() != R.id.menu_undispatchable) {
            return false;
        }
        AlertDialogBuilder<?> mAlertDialogBuilder2 = this.this0.getMAlertDialogBuilder();
        Intrinsics.checkNotNull(mAlertDialogBuilder2);
        AlertDialogBuilder message2 = mAlertDialogBuilder2.setMessage(R.string.str_change_order_status_undispatchable_selected);
        final OrderValidationRecyclerViewAdapter orderValidationRecyclerViewAdapter2 = this.this0;
        message2.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i2) {
                OrderValidationRecyclerViewAdaptermActionModeCallback1.onActionItemClickedlambda1(OrderValidationRecyclerViewAdaptermActionModeCallback1.this, orderValidationRecyclerViewAdapter2, actionMode, dialogInterface, i2);
            }
        }).setNegativeButton(R.string.cancel, null).create().show();
        return true;
    }
    public static void onActionItemClickedlambda0(OrderValidationRecyclerViewAdaptermActionModeCallback1 this0, OrderValidationRecyclerViewAdapter this1, ActionMode actionMode, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(this1, "this1");
        Intrinsics.checkNotNullParameter(actionMode, "actionMode");
        this0.mPendingClear = true;
        this1.updateStatus(OrderStatus.DISPATCHABLE);
        actionMode.finish();
    }
    public static void onActionItemClickedlambda1(OrderValidationRecyclerViewAdaptermActionModeCallback1 this0, OrderValidationRecyclerViewAdapter this1, ActionMode actionMode, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(this1, "this1");
        Intrinsics.checkNotNullParameter(actionMode, "actionMode");
        this0.mPendingClear = true;
        this1.updateStatus(OrderStatus.UNDISPATCHABLE);
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
