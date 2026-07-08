package com.proje.mobilesales.features.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseResult;
import com.proje.mobilesales.core.base.BaseServiceResult;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.OrderStatus;
import com.proje.mobilesales.core.enums.TransferOperationName;
import com.proje.mobilesales.core.interfaces.IEditSendFicheListener;
import com.proje.mobilesales.core.view.AnimatedExpandableListView;
import com.proje.mobilesales.features.model.GroupItem;
import com.proje.mobilesales.features.sales.model.Sales;
import java.util.List;



public class ExpandableAdapterCopy<T> extends AnimatedExpandableListView.AnimatedExpandableListAdapter {
    private final BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
    private final IEditSendFicheListener callbackListener;
    private final Context context;
    private final LayoutInflater inflater;
    private final List<GroupItem> mGroupItems;

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i2, int i3) {
        return i3;
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i2) {
        return i2;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return true;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i2, int i3) {
        return true;
    }

    
    public ExpandableAdapterCopy(Context context, List<GroupItem> list) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.mGroupItems = list;
        try {
            this.callbackListener = (IEditSendFicheListener) context;
        } catch (ClassCastException unused) {
            throw new ClassCastException("Activity must implement IEditSendFicheListener.");
        }
    }

    @Override // android.widget.ExpandableListAdapter
    public BaseResult getChild(int i2, int i3) {
        return (BaseResult) this.mGroupItems.get(i2).getItemList().get(i3);
    }

    @Override // com.proje.mobilesales.core.view.AnimatedExpandableListView.AnimatedExpandableListAdapter
    public View getRealChildView(int i2, int i3, boolean z, View view, ViewGroup viewGroup) {
        View view2;
        ChildHolder childHolder;
        if (view == null) {
            childHolder = new ChildHolder();
            view2 = this.inflater.inflate(R.layout.send_error_item, viewGroup, false);
            childHolder.tvItemError = view2.findViewById(R.id.tvSendErrorItem);
            childHolder.imItemSpinner = view2.findViewById(R.id.imSpinner);
            Toolbar toolbar = view2.findViewById(R.id.sendErrorToolbar);
            childHolder.itemToolbar = toolbar;
            toolbar.inflateMenu(R.menu.menu_edit);
            view2.setTag(childHolder);
        } else {
            view2 = view;
            childHolder = (ChildHolder) view.getTag();
        }
        BaseResult child = getChild(i2, i3);
        GroupItem group = getGroup(i2);
        if (child.isCompleted()) {
            if (!child.isSuccess()) {
                childHolder.tvItemError.setText(child.getErrorString());
                childHolder.tvItemError.setVisibility(View.VISIBLE);
                childHolder.imItemSpinner.setVisibility(View.VISIBLE);
                childHolder.imItemSpinner.setImageResource(R.drawable.error_outline);
                setToolbarVisibility(group, child, childHolder.itemToolbar);
            } else if (child.isSuccess()) {
                childHolder.tvItemError.setVisibility(View.INVISIBLE);
                childHolder.imItemSpinner.setVisibility(View.VISIBLE);
                childHolder.imItemSpinner.setImageResource(R.drawable.ok2);
                childHolder.itemToolbar.setVisibility(View.GONE);
            } else {
                childHolder.tvItemError.setVisibility(View.GONE);
                childHolder.imItemSpinner.setVisibility(View.GONE);
                childHolder.itemToolbar.setVisibility(View.GONE);
            }
        } else {
            childHolder.tvItemError.setVisibility(View.INVISIBLE);
            childHolder.imItemSpinner.setVisibility(View.INVISIBLE);
            childHolder.itemToolbar.setVisibility(View.INVISIBLE);
        }
        childHolder.itemToolbar.setOnMenuItemClickListener(toolbarMenuListener(child, group));
        return view2;
    } 
    class C26321 implements Toolbar.OnMenuItemClickListener {
        final BaseResult valbaseResult,r2;
        final GroupItem valgroupItem,r3;

        C26321(BaseResult baseResult, GroupItem groupItem, BaseResult valbaseResult, GroupItem valgroupItem) {
            this.valbaseResult = valbaseResult;
            this.valgroupItem = valgroupItem;
              r2 = baseResult;
              r3 = groupItem;
        }
        public boolean onMenuItemClick(MenuItem menuItem) {
            ExpandableAdapterCopy.this.callbackListener.editSelectedFiche(r2, r3);
            return false;
        }
    }

    @NonNull
    private Toolbar.OnMenuItemClickListener toolbarMenuListener(BaseResult baseResult, GroupItem groupItem) {
        return new Toolbar.OnMenuItemClickListener() { // from class: com.proje.mobilesales.features.adapter.ExpandableAdapterCopy.1
            final BaseResult valbaseResult = null;
            BaseResult r2 = null;
            GroupItem valgroupItem,r3 = null;

            void C26321(BaseResult baseResult2, GroupItem groupItem2) {
                r2 = baseResult2;
                r3 = groupItem2;
            }

            @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                ExpandableAdapterCopy.this.callbackListener.editSelectedFiche(r2, r3);
                return false;
            }
        };
    }

    @Override // com.proje.mobilesales.core.view.AnimatedExpandableListView.AnimatedExpandableListAdapter
    public int getRealChildrenCount(int i2) {
        return this.mGroupItems.get(i2).getItemList().size();
    }

    @Override // android.widget.ExpandableListAdapter
    public GroupItem getGroup(int i2) {
        return this.mGroupItems.get(i2);
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return this.mGroupItems.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i2, boolean z, View view, ViewGroup viewGroup) {
        View view2;
        GroupHolder groupHolder;
        if (view == null) {
            groupHolder = new GroupHolder();
            view2 = this.inflater.inflate(R.layout.all_send_logo_row, viewGroup, false);
            groupHolder.tvArpName = view2.findViewById(R.id.tvClCard);
            groupHolder.tvTitle = view2.findViewById(R.id.tvTitle);
            groupHolder.tvStatus = view2.findViewById(R.id.tvStatus);
            groupHolder.imSpinner = view2.findViewById(R.id.imSpinner);
            groupHolder.progressBar1 = view2.findViewById(R.id.progressBar1);
            view2.setTag(groupHolder);
        } else {
            view2 = view;
            groupHolder = (GroupHolder) view.getTag();
        }
        GroupItem group = getGroup(i2);
        BaseResult baseResult = (BaseResult) group.getItemList().get(0);
        String string = this.context.getString(group.getTransferOperationName().getResId());
        groupHolder.tvArpName.setText(group.getTitle());
        groupHolder.tvTitle.setText(string);
        groupHolder.progressBar1.setVisibility(View.VISIBLE);
        groupHolder.imSpinner.setVisibility(View.GONE);
        if (group.isComplete()) {
            groupHolder.progressBar1.setVisibility(View.GONE);
            groupHolder.imSpinner.setVisibility(View.VISIBLE);
            if (baseResult.isCompleted()) {
                if (!baseResult.isSuccess()) {
                    groupHolder.imSpinner.setImageResource(R.drawable.error_outline);
                    groupHolder.tvStatus.setText(this.context.getString(R.string.str_transfer_failed));
                } else {
                    groupHolder.tvStatus.setText(this.context.getString(R.string.str_data_transfer_completed));
                }
            }
        } else {
            groupHolder.tvStatus.setText(this.context.getString(R.string.str_data_transferring));
        }
        return view2;
    }

    private class ChildHolder {
        public AppCompatImageView imItemSpinner;
        public Toolbar itemToolbar;
        public AppCompatTextView tvItemError;

        private ChildHolder() {
        }

        ChildHolder(ExpandableAdapterCopy expandableAdapterCopy, C26321 c26321) {
            this();
        }
    }

    public class GroupHolder {
        public AppCompatImageView imSpinner;
        public ProgressBar progressBar1;
        public AppCompatTextView tvArpName;
        public AppCompatTextView tvStatus;
        public AppCompatTextView tvTitle;

        public GroupHolder() {
        }
    }

    /* renamed from: com.proje.mobilesales.features.adapter.ExpandableAdapterCopy2 */
    static class C26332 {
        static final int[] SwitchMapcomprojemobilesalescoreenumsTransferOperationName;

        static {
            int[] iArr = new int[TransferOperationName.values().length];
            SwitchMapcomprojemobilesalescoreenumsTransferOperationName = iArr;
            try {
                iArr[TransferOperationName.INVOICE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.RETURN_INVOICE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.RETAIL_INVOICE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.RETAIL_RETURN_INVOICE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.DISPATCH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.RETURN_DISPATCH.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.ORDER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.ONE_TO_ONE_CHANGE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.CASH.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.CREDIT_CARD.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.CHEQUE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.DEED.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.CASE_CASH.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.DEMAND.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    private void setToolbarVisibility(GroupItem groupItem, BaseResult baseResult, Toolbar toolbar) {
        boolean invoiceEditRight;
        switch (C26332.SwitchMapcomprojemobilesalescoreenumsTransferOperationName[groupItem.getTransferOperationName().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                invoiceEditRight = this.baseErp.getInvoiceEditRight();
                break;
            case 5:
            case 6:
                invoiceEditRight = this.baseErp.getDispatchEditRight();
                break;
            case 7:
                invoiceEditRight = getOrderOrDemandEditRight(baseResult);
                break;
            case 8:
                invoiceEditRight = this.baseErp.getDispatchEditRight();
                break;
            case 9:
                invoiceEditRight = this.baseErp.getCashEditRight();
                break;
            case 10:
                invoiceEditRight = this.baseErp.getCreditCardEditRight();
                break;
            case 11:
                invoiceEditRight = this.baseErp.getChequeEditRight();
                break;
            case 12:
                invoiceEditRight = this.baseErp.getDeedEditRight();
                break;
            case 13:
                invoiceEditRight = this.baseErp.getCaseCashEditRight();
                break;
            case 14:
                invoiceEditRight = getOrderOrDemandEditRight(baseResult);
                break;
            default:
                invoiceEditRight = false;
                break;
        }
        if (invoiceEditRight) {
            toolbar.setVisibility(View.VISIBLE);
        } else {
            toolbar.setVisibility(View.INVISIBLE);
        }
    }

    private boolean getOrderOrDemandEditRight(BaseResult baseResult) {
        if (!(baseResult instanceof BaseServiceResult)) {
            return false;
        }
        if (((Sales) ((BaseServiceResult) baseResult).getData()).getSalesStatus() == OrderStatus.OFFER.getmStatus()) {
            return this.baseErp.getOrderOfferFicheMenuRights().isEdit();
        }
        return this.baseErp.getOrderDispatchableFicheMenuRights().isEdit();
    }
}
