package com.proje.mobilesales.features.sales.view.serilot;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.product.model.database.ItemUnits;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import com.proje.mobilesales.features.sales.repository.SalesSeriLotRepository;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.proje.mobilesales.core.utils.AppUtils.createLayoutInflater;

public final class SalesSeriLotAdapter extends RecyclerView.Adapter {
    public static final Companion Companion = new Companion(null);
    private static final String STATE_RANGE_SELECT = "state:rangeSelect";
    private static final String STATE_SERI_LOT_LIST = "state:seriLotList";
    private static final String TAG = "SalesSeriLotAdapter";
    private int checked;
    private int itemRef;
    private List<ItemUnits> itemUnitList;
    private Context mContext;
    private ArrayAdapter<String> mDataAdapter;
    private int mFirstItemSelectPosition;
    private final boolean mHasOrderRef;
    private final boolean mIsDivUnit;
    private int mLastItemSelectPosition;
    private LayoutInflater mLayoutInflater;
    private boolean mLot;
    private final double mOrderAvailableAmount;
    private boolean mRangeSelect;
    private final SalesType mSalesType;
    private ArrayList<SalesSeriLotActivity.SeriLotCheck> mSelectedItemList;
    private ArrayList<SalesSeriLotActivity.SeriLotCheck> mSeriLotChecks;
    private final SalesSeriLotActivity.SeriLotSelectListener mSeriLotSelectListener;
    private final SalesSeriLotRepository repository;
    private final SalesSeriLotViewModel viewModel;
    private ViewHolder holder;
    private int i2;

    public Context getMContext() {
        return this.mContext;
    }

    public void setMContext(Context context) {
        this.mContext = context;
    }

    public boolean getMLot() {
        return this.mLot;
    }

    public void setMLot(boolean z) {
        this.mLot = z;
    }

    public SalesSeriLotAdapter(Context context, boolean z, boolean z2, double d2, boolean z3, SalesType salesType, SalesSeriLotActivity.SeriLotSelectListener seriLotSelectListener, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : context, (i2 & 2) == 0 && z, (i2 & 4) == 0 && z2, (i2 & 8) != 0 ? 0.0d : d2, (i2 & 16) == 0 && z3, (i2 & 32) != 0 ? SalesType.FREE : salesType, seriLotSelectListener);
    }

    public SalesSeriLotAdapter(Context context, boolean z, boolean z2, double d2, boolean z3, SalesType salesType, SalesSeriLotActivity.SeriLotSelectListener seriLotSelectListener) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        Intrinsics.checkNotNullParameter(seriLotSelectListener, "seriLotSelectListener");
        this.mContext = context;
        this.mLot = z;
        SalesSeriLotRepository salesSeriLotRepository = new SalesSeriLotRepository();
        this.repository = salesSeriLotRepository;
        this.viewModel = new SalesSeriLotViewModel(salesSeriLotRepository);
        this.itemUnitList = new ArrayList();
        this.mSeriLotChecks = new ArrayList<>();
        this.checked = 0;
        this.mRangeSelect = false;
        this.mSeriLotSelectListener = seriLotSelectListener;
        this.mHasOrderRef = z2;
        this.mOrderAvailableAmount = d2;
        this.mIsDivUnit = z3;
        this.mSelectedItemList = new ArrayList<>();
        this.mSalesType = salesType;
    }

    public ArrayList<SalesSeriLotActivity.SeriLotCheck> getMSeriLotChecks() {
        return this.mSeriLotChecks;
    }

    private void setMSeriLotChecks(ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList) {
        this.mSeriLotChecks = arrayList;
    }

    public ArrayList<SalesSeriLotActivity.SeriLotCheck> getMSelectedItemList() {
        return this.mSelectedItemList;
    }

    private void setMSelectedItemList(ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList) {
        this.mSelectedItemList = arrayList;
    }

    public int getChecked() {
        return this.checked;
    }

    private void setChecked(int i2) {
        this.checked = i2;
    }

    public int getItemRef() {
        return this.itemRef;
    }

    void setItemRef(int i2) {
        this.itemRef = i2;
    }

    public List<ItemUnits> getItemUnitList() {
        return this.itemUnitList;
    }

    private void setItemUnitList(List<ItemUnits> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.itemUnitList = list;
    }

    private LayoutInflater getMLayoutInflater() {
        return this.mLayoutInflater;
    }

    private void setMLayoutInflater(LayoutInflater layoutInflater) {
        this.mLayoutInflater = layoutInflater;
    }
 
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        Context context = recyclerView.getContext();
        this.mContext = context;
        this.mLayoutInflater = createLayoutInflater(context);
    } 
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onDetachedFromRecyclerView(recyclerView);
        this.mContext = null;
    }

    public SalesSeriLotActivity.SeriLotCheck getItem(int i2) {
        ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList = this.mSeriLotChecks;
        Intrinsics.checkNotNull(arrayList);
        SalesSeriLotActivity.SeriLotCheck seriLotCheck = arrayList.get(i2);
        Intrinsics.checkNotNullExpressionValue(seriLotCheck, "get(...)");
        return seriLotCheck;
    } 
    public void onBindViewHolder(final ViewHolder holder) {
        this.holder = holder;
        String convertIntToString;
        String convertIntToString2 = "";
        Intrinsics.checkNotNullParameter(holder, "holder");
        final SalesSeriLotActivity.SeriLotCheck item = getItem(i2);
        TextView tvCode = holder.getTvCode();
        Serilot serilot = item.serilot;
        Intrinsics.checkNotNull(serilot);
        tvCode.setText(serilot.code);
        TextView tvExpDate = holder.getTvExpDate();
        Serilot serilot2 = item.serilot;
        Intrinsics.checkNotNull(serilot2);
        tvExpDate.setText(serilot2.expDate);
        AppCompatTextView tvAmount = holder.getTvAmount();
        if (this.mIsDivUnit) {
            Serilot serilot3 = item.serilot;
            Intrinsics.checkNotNull(serilot3);
            convertIntToString = StringUtils.convertDoubleToStringWithThreeDigits(Double.valueOf(serilot3.reAmount));
        } else {
            Serilot serilot4 = item.serilot;
            Intrinsics.checkNotNull(serilot4);
            convertIntToString = StringUtils.convertIntToString((int) serilot4.reAmount);
        }
        tvAmount.setText(convertIntToString);
        Context context = this.mContext;
        Intrinsics.checkNotNull(context);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, R.layout.layout_product_spinner_item, getUnitCodes());
        this.mDataAdapter = arrayAdapter;
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        AppCompatSpinner tvUnit = holder.getTvUnit();
        ArrayAdapter<String> arrayAdapter2 = this.mDataAdapter;
        Integer num = null;
        if (arrayAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDataAdapter");
            arrayAdapter2 = null;
        }
        tvUnit.setAdapter(arrayAdapter2);
        Serilot serilot5 = item.serilot;
        Intrinsics.checkNotNull(serilot5);
        String str = serilot5.unit;
        ArrayAdapter<String> arrayAdapter3 = this.mDataAdapter;
        if (arrayAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDataAdapter");
            arrayAdapter3 = null;
        }
        Iterator<Integer> it = RangesKt.until(0, arrayAdapter3.getCount()).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Integer next = it.next();
            int intValue = next.intValue();
            ArrayAdapter<String> arrayAdapter4 = this.mDataAdapter;
            if (arrayAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mDataAdapter");
                arrayAdapter4 = null;
            }
            if (Intrinsics.areEqual(arrayAdapter4.getItem(intValue), str)) {
                num = next;
                break;
            }
        }
        Integer num2 = num;
        if (num2 != null) {
            holder.getTvUnit().setSelection(num2.intValue());
        }
        holder.getTvUnit().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            private boolean mIsDivUnit;

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
            public void onItemSelected(AdapterView<?> adapterView, View view, int i3, long j2) {
                Intrinsics.checkNotNullParameter(view, "view");
                String obj = holder.getTvUnit().getSelectedItem().toString();
                Serilot serilot6 = item.serilot;
                Intrinsics.checkNotNull(serilot6);
                serilot6.unit = obj;
                Serilot serilot7 = item.serilot;
                Intrinsics.checkNotNull(serilot7);
                double d2 = (serilot7.reAmount * this.getItemUnitList().get(i3).convfact1) / this.getItemUnitList().get(i3).convfact2;
                holder.getTvAmount().setText(!this.mIsDivUnit ? StringUtils.convertIntToString((int) d2) : StringUtils.convertDoubleToStringWithThreeDigits(Double.valueOf(d2)));
                holder.getWatcher().setReAmount(String.valueOf(d2));
            }

            private List<Object> getItemUnitList() {
                return java.util.Collections.emptyList();
            }
        });
        TextView tvCode2 = holder.getTvCode();
        Serilot serilot6 = item.serilot;
        Intrinsics.checkNotNull(serilot6);
        tvCode2.setText(serilot6.code);
        holder.getCbListItem().setChecked(item.isChecked);
        holder.getEdtUserAmount().setEnabled(item.isChecked);
        holder.getWatcher().setActive(false);
        holder.getWatcher().setError(false);
        holder.getWatcher().setEditText(holder.getEdtUserAmount());
        holder.getEdtUserAmount().setInputType(this.mIsDivUnit ? InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL : InputType.TYPE_CLASS_NUMBER);
        AppCompatEditText edtUserAmount = holder.getEdtUserAmount();
        if (this.mIsDivUnit) {
            Serilot serilot7 = item.serilot;
            Intrinsics.checkNotNull(serilot7);
            StringUtils.convertDoubleToString(Double.valueOf(serilot7.amount));
        } else {
            Serilot serilot8 = item.serilot;
            Intrinsics.checkNotNull(serilot8);
            StringUtils.convertIntToString((int) serilot8.amount);
        }
        edtUserAmount.setText(convertIntToString2, TextView.BufferType.EDITABLE);
        holder.getWatcher().setPosition(i2);
        holder.getWatcher().setActive(true);
        if (!this.mLot) {
            holder.getEdtUserAmount().setVisibility(View.GONE);
            holder.getTvExpDate().setVisibility(View.GONE);
        } else {
            holder.getEdtUserAmount().setVisibility(View.VISIBLE);
        }
        if (this.viewModel.erpType() == ErpType.NETSIS) {
            holder.getExpDateScrollView().setVisibility(View.GONE);
        }
        holder.getLnContainer().setOnLongClickListener(new View.OnLongClickListener() {  
            public boolean onLongClick(View view) {
                boolean onBindViewHolderlambda2;
                onBindViewHolderlambda2 = SalesSeriLotAdapter.onBindViewHolderlambda2(SalesSeriLotAdapter.this, i2, holder, view);
                return onBindViewHolderlambda2;
            }
        });
        holder.getCbListItem().setOnClickListener(new View.OnClickListener() {   
            public void onClick(View view) {
                SalesSeriLotAdapter.onBindViewHolderlambda3(SalesSeriLotAdapter.this, i2, holder, view);
            }
        });
    }
 
    public static boolean onBindViewHolderlambda2(SalesSeriLotAdapter this0, int i2, ViewHolder holder, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (this0.mRangeSelect) {
            this0.rangeSelectItemPosition(i2, holder.getCbListItem(), holder.getEdtUserAmount());
            return true;
        }
        this0.selectItemPosition(i2, holder.getCbListItem(), holder.getEdtUserAmount());
        return true;
    }
    public static void onBindViewHolderlambda3(SalesSeriLotAdapter this0, int i2, ViewHolder holder, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (this0.mRangeSelect) {
            this0.rangeSelectItemPosition(i2, holder.getCbListItem(), holder.getEdtUserAmount());
        } else {
            this0.selectItemPosition(i2, holder.getCbListItem(), holder.getEdtUserAmount());
        }
    }

    private  List<String> getUnitCodes() {
        ArrayList arrayList = new ArrayList();
        List<ItemUnits> itemUnits = this.viewModel.getBaseErp().getLogoSqlHelper().getItemUnits(this.itemRef);
        Intrinsics.checkNotNull(itemUnits, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.product.model.database.ItemUnits>");
        this.itemUnitList = itemUnits;
        if (itemUnits != null && !itemUnits.isEmpty()) {
            Iterator<ItemUnits> it = this.itemUnitList.iterator();
            while (it.hasNext()) {
                String str = it.next().code;
                Intrinsics.checkNotNull(str);
                arrayList.add(str);
            }
        }
        return arrayList;
    }
 
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater layoutInflater = this.mLayoutInflater;
        Intrinsics.checkNotNull(layoutInflater);
        View inflate = layoutInflater.inflate(R.layout.list_checkbox_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ViewHolder(this, inflate);
    }
  
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        
    }

    public long getItemId(int i2) {
        ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList = this.mSeriLotChecks;
        Intrinsics.checkNotNull(arrayList);
        Intrinsics.checkNotNull(arrayList.get(i2).serilot);
        return arrayList.get(i2).serilot.logicalRef;    
    }

    public int getItemCount() {
        ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList = this.mSeriLotChecks;
        if (arrayList == null) {
            return 0;
        }
        Intrinsics.checkNotNull(arrayList);
        return arrayList.size();
    }

    private void selectItemPosition(int i2, AppCompatCheckBox appCompatCheckBox, AppCompatEditText appCompatEditText) {
        ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList = this.mSeriLotChecks;
        Intrinsics.checkNotNull(arrayList);
        if (!arrayList.get(i2).isChecked) {
            if (!SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(this.mSalesType) && !this.mLot && this.mHasOrderRef) {
                double d2 = this.mOrderAvailableAmount;
                if (d2 > 0.0d && this.checked + 1 > d2) {
                    appCompatCheckBox.setChecked(false);
                    Toast.makeText(this.mContext, R.string.str_order_available_amount_high_error, Toast.LENGTH_LONG).show();
                    return;
                }
            }
            ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList2 = this.mSeriLotChecks;
            Intrinsics.checkNotNull(arrayList2);
            arrayList2.get(i2).isChecked = true;
            appCompatCheckBox.setChecked(true);
            appCompatEditText.setEnabled(true);
            appCompatEditText.setSelectAllOnFocus(true);
            appCompatEditText.clearFocus();
            appCompatEditText.requestFocus();
            appCompatEditText.setSelectAllOnFocus(true);
            ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList3 = this.mSeriLotChecks;
            Intrinsics.checkNotNull(arrayList3);
            SalesSeriLotActivity.SeriLotCheck seriLotCheck = arrayList3.get(i2);
            Intrinsics.checkNotNullExpressionValue(seriLotCheck, "get(...)");
            addToSelectedItemList(seriLotCheck);
            this.checked++;
        } else {
            ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList4 = this.mSeriLotChecks;
            Intrinsics.checkNotNull(arrayList4);
            arrayList4.get(i2).isChecked = false;
            appCompatEditText.setText("0");
            appCompatCheckBox.setChecked(false);
            appCompatEditText.setEnabled(false);
            ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList5 = this.mSeriLotChecks;
            Intrinsics.checkNotNull(arrayList5);
            SalesSeriLotActivity.SeriLotCheck seriLotCheck2 = arrayList5.get(i2);
            Intrinsics.checkNotNullExpressionValue(seriLotCheck2, "get(...)");
            removeFromSelectedList(seriLotCheck2);
            this.checked--;
        }
        updateChecked();
    }

    public void addToSelectedItemList(SalesSeriLotActivity.SeriLotCheck seriLotCheck) {
        Intrinsics.checkNotNullParameter(seriLotCheck, "seriLotCheck");
        if (isSelected(seriLotCheck)) {
            return;
        }
        ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList = this.mSelectedItemList;
        Intrinsics.checkNotNull(arrayList);
        arrayList.add(seriLotCheck);
    }

    private void removeFromSelectedList(SalesSeriLotActivity.SeriLotCheck seriLotCheck) {
        ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList = this.mSelectedItemList;
        Intrinsics.checkNotNull(arrayList);
        for (SalesSeriLotActivity.SeriLotCheck next : arrayList) {
            Serilot serilot = next.serilot;
            Intrinsics.checkNotNull(serilot);
            int i2 = serilot.logicalRef;
            Serilot serilot2 = seriLotCheck.serilot;
            Intrinsics.checkNotNull(serilot2);
            if (i2 == serilot2.logicalRef) {
                ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList2 = this.mSelectedItemList;
                Intrinsics.checkNotNull(arrayList2);
                arrayList2.remove(next);
                return;
            }
        }
    }

    private void rangeSelectItemPosition(int i2, AppCompatCheckBox appCompatCheckBox, AppCompatEditText appCompatEditText) {
        int i3 = this.mFirstItemSelectPosition;
        if (i3 == -1) {
            selectItemPosition(i2, appCompatCheckBox, appCompatEditText);
            this.mFirstItemSelectPosition = i2;
        } else if (i3 == i2) {
            selectItemPosition(i2, appCompatCheckBox, appCompatEditText);
            this.mFirstItemSelectPosition = -1;
        } else if (i3 != -1) {
            this.mLastItemSelectPosition = i2;
            selectRange();
        }
    }

    public void setList(ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList) {
        if (arrayList != null) {
            this.mSeriLotChecks = arrayList;
            this.checked = 0;
        }
        controlCheck();
        updateChecked();
        notifyDataSetChanged();
    }

    private void controlCheck() {
        ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList = this.mSelectedItemList;
        Intrinsics.checkNotNull(arrayList);
        this.checked = arrayList.size();
    }
    public void updateChecked() {
        this.mSeriLotSelectListener.onSelectChangedListener(this.checked);
    }
    public  Pair<Double, Boolean> isItemChecked(int i2) {
        ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList = this.mSelectedItemList;
        Double valueOf = Double.valueOf(0.0d);
        if (arrayList != null) {
            Intrinsics.checkNotNull(arrayList);
            if (arrayList.size() != 0) {
                Pair<Double, Boolean> pair = new Pair<>(valueOf, Boolean.FALSE);
                for (SalesSeriLotActivity.SeriLotCheck next : this.mSelectedItemList) {
                    Serilot serilot = next.serilot;
                    Intrinsics.checkNotNull(serilot);
                    if (serilot.logicalRef == i2) {
                        Serilot serilot2 = next.serilot;
                        Intrinsics.checkNotNull(serilot2);
                        return new Pair<>(Double.valueOf(serilot2.amount), Boolean.valueOf(next.isChecked));
                    }
                }
                return pair;
            }
        }
        return new Pair<>(valueOf, Boolean.FALSE);
    }

    public void selectAll() {
        if (this.mSeriLotChecks == null) {
            return;
        }
        if (!SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(this.mSalesType) && this.mHasOrderRef && this.mOrderAvailableAmount > 0.0d) {
            Intrinsics.checkNotNull(this.mSeriLotChecks);
            if (this.mSeriLotChecks.size() > this.mOrderAvailableAmount) {
                Toast.makeText(this.mContext, R.string.str_order_available_amount_high_error, Toast.LENGTH_LONG).show();
                return;
            }
        }
        ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList = this.mSeriLotChecks;
        Intrinsics.checkNotNull(arrayList);
        this.checked = arrayList.size();
        for (SalesSeriLotActivity.SeriLotCheck next : this.mSeriLotChecks) {    
            next.isChecked = true;
            Intrinsics.checkNotNull(next);
            addToSelectedItemList(next);
        }
        updateChecked();
        notifyDataSetChanged();
    }

    private boolean isSelected(SalesSeriLotActivity.SeriLotCheck seriLotCheck) {
        ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList = this.mSelectedItemList;
        Intrinsics.checkNotNull(arrayList);
        for (SalesSeriLotActivity.SeriLotCheck next : arrayList) {
            Serilot serilot = next.serilot;
            Intrinsics.checkNotNull(serilot);
            int i2 = serilot.logicalRef;
            Serilot serilot2 = seriLotCheck.serilot;
            Intrinsics.checkNotNull(serilot2);
            if (i2 == serilot2.logicalRef) {
                return true;
            }
        }
        return false;
    }

    public void unSelectAll() {
        ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList = this.mSeriLotChecks;
        if (arrayList == null) {
            return;
        }
        this.checked = 0;
        Intrinsics.checkNotNull(arrayList);
        for (SalesSeriLotActivity.SeriLotCheck next : arrayList) {
            next.isChecked = false;
            Intrinsics.checkNotNull(next);
            removeFromSelectedList(next);
        }
        if (this.mRangeSelect) {
            resetFirstLastSelectItemPosition();
        }
        updateChecked();
        notifyDataSetChanged();
    }

    public void selectRange() {
        if (this.mSeriLotChecks == null) {
            return;
        }
        int i2 = this.mFirstItemSelectPosition;
        int i3 = this.mLastItemSelectPosition;
        int i4 = Math.min(i2, i3);
        if (i2 <= i3) {
            i2 = i3;
        }
        if (i4 <= i2) {
            while (i4 <= i2) {
                ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList = this.mSeriLotChecks;
                Intrinsics.checkNotNull(arrayList);
                SalesSeriLotActivity.SeriLotCheck seriLotCheck = arrayList.get(i4);
                Intrinsics.checkNotNullExpressionValue(seriLotCheck, "get(...)");
                SalesSeriLotActivity.SeriLotCheck seriLotCheck2 = seriLotCheck;
                if (!seriLotCheck2.isChecked) {
                    seriLotCheck2.isChecked = true;
                    addToSelectedItemList(seriLotCheck2);
                    this.checked++;
                }
                if (i4 == i2) {
                    break;
                } else {
                    i4++;
                }
            }
        }
        resetFirstLastSelectItemPosition();
        updateChecked();
        notifyDataSetChanged();
    }

    private void resetFirstLastSelectItemPosition() {
        this.mFirstItemSelectPosition = -1;
        this.mLastItemSelectPosition = -1;
    }

    public List<SalesSeriLotActivity.SeriLotCheck> getmSeriLotChecks() {
        return this.mSeriLotChecks;
    }

    public  void setmSeriLotChecks(SalesSeriLotActivity.SeriLotCheck seriLotChecks) {
        Intrinsics.checkNotNullParameter(seriLotChecks, "seriLotChecks");
        ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList = this.mSeriLotChecks;
        Intrinsics.checkNotNull(arrayList);
        if (arrayList.size() > 0) {
            ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList2 = this.mSeriLotChecks;
            Intrinsics.checkNotNull(arrayList2);
            Iterator<SalesSeriLotActivity.SeriLotCheck> it = arrayList2.iterator();
            while (it.hasNext()) {
                Serilot serilot = it.next().serilot;
                Intrinsics.checkNotNull(serilot);
                String str = serilot.code;
                Serilot serilot2 = seriLotChecks.serilot;
                Intrinsics.checkNotNull(serilot2);
                if (Intrinsics.areEqual(str, serilot2.code)) {
                    return;
                }
            }
            ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList3 = this.mSeriLotChecks;
            Intrinsics.checkNotNull(arrayList3);
            arrayList3.add(seriLotChecks);
            return;
        }
        ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList4 = this.mSeriLotChecks;
        Intrinsics.checkNotNull(arrayList4);
        arrayList4.add(seriLotChecks);
    }

    public ArrayList<Serilot> getCheckedSeriLotList() {
        ArrayList<Serilot> arrayList = new ArrayList<>();
        ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList2 = this.mSelectedItemList;
        if (arrayList2 == null) {
            return arrayList;
        }
        Intrinsics.checkNotNull(arrayList2);
        Iterator<SalesSeriLotActivity.SeriLotCheck> it = arrayList2.iterator();
        while (it.hasNext()) {
            SalesSeriLotActivity.SeriLotCheck next = it.next();
            if (next.isChecked) {
                if (!this.mLot) {
                    Serilot serilot = next.serilot;
                    Intrinsics.checkNotNull(serilot);
                    serilot.amount = 1.0d;
                }
                Serilot serilot2 = next.serilot;
                Intrinsics.checkNotNull(serilot2);
                if (serilot2.amount > 0.0d) {
                    Serilot serilot3 = next.serilot;
                    Intrinsics.checkNotNull(serilot3);
                    arrayList.add(serilot3);
                }
            }
        }
        return arrayList;
    }

    private void firstItemSelectPositionControl() {
        if (this.mFirstItemSelectPosition != -1) {
            ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList = this.mSeriLotChecks;
            Intrinsics.checkNotNull(arrayList);
            arrayList.get(this.mFirstItemSelectPosition).isChecked = false;
            notifyDataSetChanged();
        }
    }

    public boolean isRangeSelect() {
        return this.mRangeSelect;
    }

    public void setRangeSelect(boolean z) {
        this.mRangeSelect = z;
        resetFirstLastSelectItemPosition();
    }

    public boolean isAllChecked() {
        ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList = this.mSelectedItemList;
        Intrinsics.checkNotNull(arrayList);
        if (arrayList.size() <= 0) {
            return false;
        }
        int i2 = this.checked;
        ArrayList<SalesSeriLotActivity.SeriLotCheck> arrayList2 = this.mSelectedItemList;
        Intrinsics.checkNotNull(arrayList2);
        return i2 == arrayList2.size();
    }

    public Bundle outState() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(STATE_SERI_LOT_LIST, this.mSeriLotChecks);
        bundle.putBoolean(STATE_RANGE_SELECT, this.mRangeSelect);
        return bundle;
    }

    public void saveState(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        Log.d(TAG, "saveState: open");
        this.mSeriLotChecks = bundle.getParcelableArrayList(STATE_SERI_LOT_LIST);
        this.mRangeSelect = bundle.getBoolean(STATE_RANGE_SELECT, false);
        notifyDataSetChanged();
    }

    /* compiled from: SalesSeriLotAdapter.kt */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatCheckBox cbListItem;
        private AppCompatEditText edtUserAmount;
        private HorizontalScrollView expDateScrollView;
        private LinearLayout lnContainer;
        final SalesSeriLotAdapter this0;
        private AppCompatTextView tvAmount;
        private TextView tvCode;
        private TextView tvExpDate;
        private AppCompatSpinner tvUnit;
        private MutableWatcher watcher;

        
        public ViewHolder(SalesSeriLotAdapter salesSeriLotAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this0 = salesSeriLotAdapter;
            View findViewById = itemView.findViewById(R.id.linearCheckList);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.LinearLayout");
            this.lnContainer = (LinearLayout) findViewById;
            View findViewById2 = itemView.findViewById(R.id.tvCode);
            Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
            this.tvCode = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.tvExpDate);
            Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
            this.tvExpDate = (TextView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.edtUserAmount);
            Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatEditText");
            this.edtUserAmount = (AppCompatEditText) findViewById4;
            View findViewById5 = itemView.findViewById(R.id.tvAmount);
            Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            this.tvAmount = (AppCompatTextView) findViewById5;
            View findViewById6 = itemView.findViewById(R.id.tvUnit);
            Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatSpinner");
            this.tvUnit = (AppCompatSpinner) findViewById6;
            View findViewById7 = itemView.findViewById(R.id.expDateScrollView);
            Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
            this.expDateScrollView = (HorizontalScrollView) findViewById7;
            View findViewById8 = itemView.findViewById(R.id.cbListItem);
            Intrinsics.checkNotNull(findViewById8, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatCheckBox");
            this.cbListItem = (AppCompatCheckBox) findViewById8;
            MutableWatcher mutableWatcher = salesSeriLotAdapter.new MutableWatcher();
            this.watcher = mutableWatcher;
            this.edtUserAmount.addTextChangedListener(mutableWatcher);
            this.tvExpDate.setMovementMethod(new ScrollingMovementMethod());
            this.tvCode.setMovementMethod(new ScrollingMovementMethod());
        }

        public TextView getTvCode() {
            return this.tvCode;
        }

        public void setTvCode(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tvCode = textView;
        }

        public TextView getTvExpDate() {
            return this.tvExpDate;
        }

        public void setTvExpDate(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tvExpDate = textView;
        }

        public AppCompatEditText getEdtUserAmount() {
            return this.edtUserAmount;
        }

        public void setEdtUserAmount(AppCompatEditText appCompatEditText) {
            Intrinsics.checkNotNullParameter(appCompatEditText, "<set-?>");
            this.edtUserAmount = appCompatEditText;
        }

        public AppCompatTextView getTvAmount() {
            return this.tvAmount;
        }

        public void setTvAmount(AppCompatTextView appCompatTextView) {
            Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
            this.tvAmount = appCompatTextView;
        }

        public AppCompatSpinner getTvUnit() {
            return this.tvUnit;
        }

        public void setTvUnit(AppCompatSpinner appCompatSpinner) {
            Intrinsics.checkNotNullParameter(appCompatSpinner, "<set-?>");
            this.tvUnit = appCompatSpinner;
        }

        public AppCompatCheckBox getCbListItem() {
            return this.cbListItem;
        }

        public void setCbListItem(AppCompatCheckBox appCompatCheckBox) {
            Intrinsics.checkNotNullParameter(appCompatCheckBox, "<set-?>");
            this.cbListItem = appCompatCheckBox;
        }

        public LinearLayout getLnContainer() {
            return this.lnContainer;
        }

        public void setLnContainer(LinearLayout linearLayout) {
            Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
            this.lnContainer = linearLayout;
        }

        public MutableWatcher getWatcher() {
            return this.watcher;
        }

        public void setWatcher(MutableWatcher mutableWatcher) {
            Intrinsics.checkNotNullParameter(mutableWatcher, "<set-?>");
            this.watcher = mutableWatcher;
        }

        public HorizontalScrollView getExpDateScrollView() {
            return this.expDateScrollView;
        }

        public void setExpDateScrollView(HorizontalScrollView horizontalScrollView) {
            Intrinsics.checkNotNullParameter(horizontalScrollView, "<set-?>");
            this.expDateScrollView = horizontalScrollView;
        }
    }

    /* compiled from: SalesSeriLotAdapter.kt */
    public final class MutableWatcher implements TextWatcher {
        private boolean mActive;
        private AppCompatEditText mEditText;
        private boolean mError;
        private int mPosition;
        private double mReAmount;

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable s) {
            Intrinsics.checkNotNullParameter(s, "s");
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence s, int i2, int i3, int i4) {
            Intrinsics.checkNotNullParameter(s, "s");
        }

        public MutableWatcher() {
        }

        public void setPosition(int i2) {
            this.mPosition = i2;
        }

        public void setActive(boolean z) {
            this.mActive = z;
        }

        public void setError(boolean z) {
            this.mError = z;
        }

        public void setEditText(AppCompatEditText appCompatEditText) {
            this.mEditText = appCompatEditText;
        }

        public void setReAmount(String editText) {
            Intrinsics.checkNotNullParameter(editText, "editText");
            this.mReAmount = StringUtils.convertStringToDouble(editText);
        }

        private double getSelectedTotalAmount() {
            ArrayList<SalesSeriLotActivity.SeriLotCheck> mSelectedItemList = SalesSeriLotAdapter.this.getMSelectedItemList();
            Intrinsics.checkNotNull(mSelectedItemList);
            Iterator<SalesSeriLotActivity.SeriLotCheck> it = mSelectedItemList.iterator();
            double d2 = 0.0d;
            while (it.hasNext()) {
                Serilot serilot = it.next().serilot;
                Intrinsics.checkNotNull(serilot);
                d2 += serilot.amount;
            }
            return d2;
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence s, int i2, int i3, int i4) {
            String convertIntToString;
            Intrinsics.checkNotNullParameter(s, "s");
            if (this.mActive) {
                double convertStringToDouble = StringUtils.convertStringToDouble(s.toString());
                SalesSeriLotActivity.SeriLotCheck item = SalesSeriLotAdapter.this.getItem(this.mPosition);
                Serilot serilot = item.serilot;
                Intrinsics.checkNotNull(serilot);
                serilot.amount = 0.0d;
                if (convertStringToDouble <= this.mReAmount || SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(SalesSeriLotAdapter.this.mSalesType)) {
                    if (!SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(SalesSeriLotAdapter.this.mSalesType) && SalesSeriLotAdapter.this.mHasOrderRef && SalesSeriLotAdapter.this.mOrderAvailableAmount > 0.0d && (convertStringToDouble > SalesSeriLotAdapter.this.mOrderAvailableAmount || getSelectedTotalAmount() + convertStringToDouble > SalesSeriLotAdapter.this.mOrderAvailableAmount)) {
                        Toast.makeText(SalesSeriLotAdapter.this.getMContext(), R.string.str_order_available_amount_high_error, Toast.LENGTH_LONG).show();
                        AppCompatEditText appCompatEditText = this.mEditText;
                        Intrinsics.checkNotNull(appCompatEditText);
                        appCompatEditText.setText("0");
                        return;
                    }
                    Serilot serilot2 = item.serilot;
                    Intrinsics.checkNotNull(serilot2);
                    serilot2.amount = convertStringToDouble;
                    return;
                }
                Toast.makeText(SalesSeriLotAdapter.this.getMContext(), R.string.str_lot_amount_reamount_high_error, Toast.LENGTH_LONG).show();
                AppCompatEditText appCompatEditText2 = this.mEditText;
                Intrinsics.checkNotNull(appCompatEditText2);
                if (SalesSeriLotAdapter.this.mIsDivUnit) {
                    Serilot serilot3 = item.serilot;
                    Intrinsics.checkNotNull(serilot3);
                    convertIntToString = StringUtils.convertDoubleToString(Double.valueOf(serilot3.amount));
                } else {
                    Serilot serilot4 = item.serilot;
                    Intrinsics.checkNotNull(serilot4);
                    convertIntToString = StringUtils.convertIntToString((int) serilot4.amount);
                }
                appCompatEditText2.setText(convertIntToString);
            }
        }
    }

    /* compiled from: SalesSeriLotAdapter.kt */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
