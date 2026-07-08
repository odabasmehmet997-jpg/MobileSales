package com.proje.mobilesales.features.sales.view.variant;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.databinding.ListVariantItemBinding;
import com.proje.mobilesales.features.model.ItemVariantStock;
import com.proje.mobilesales.features.sales.model.SalesVariantCheck;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SalesVariantAdapter extends BaseAdapter {
    public static final Companion Companion = new Companion(null);
    private static final String STATE_VARIANT_CHECK_LIST = "state:variantCheckList";
    private static final String TAG = "SalesVariantAdapter";
    private ArrayList<SalesVariantCheck> items;
    private int mChecked;
    private Context mContext;
    private boolean mDivUnit;
    private final SalesVariantActivity.SalesVariantListener mSalesVariantListener;
    public Context getMContext() {
        return this.mContext;
    }
    public void setMContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.mContext = context;
    }
    public boolean getMDivUnit() {
        return this.mDivUnit;
    }
    public void setMDivUnit(boolean z) {
        this.mDivUnit = z;
    }
    public SalesVariantAdapter(Context mContext, SalesVariantActivity.SalesVariantListener mSalesVariantListener, boolean z) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(mSalesVariantListener, "mSalesVariantListener");
        this.mContext = mContext;
        this.mSalesVariantListener = mSalesVariantListener;
        this.mDivUnit = z;
        this.items = new ArrayList<>();
    }
    public int getMChecked() {
        return this.mChecked;
    }
    public void setMChecked(int i2) {
        this.mChecked = i2;
    }
    public ArrayList<SalesVariantCheck> getItems() {
        return this.items;
    }
    public void setItems(ArrayList<SalesVariantCheck> arrayList) {
        this.items = arrayList;
    }
    public int getCount() {
        ArrayList<SalesVariantCheck> arrayList = this.items;
        if (arrayList == null) {
            return 0;
        }
        Intrinsics.checkNotNull(arrayList);
        return arrayList.size();
    }
    public SalesVariantCheck getItem(int i2) {
        ArrayList<SalesVariantCheck> arrayList = this.items;
        Intrinsics.checkNotNull(arrayList);
        SalesVariantCheck salesVariantCheck = arrayList.get(i2);
        Intrinsics.checkNotNullExpressionValue(salesVariantCheck, "get(...)");
        return salesVariantCheck;
    }
    public long getItemId(int i2) {
        Intrinsics.checkNotNull(this.items);
        return i2;
    }
    public View getView(final int i2, View view, ViewGroup parent) {
        View view2;
        final ViewHolder viewHolder;
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater from = LayoutInflater.from(this.mContext);
        if (view == null) {
            viewHolder = new ViewHolder();
            ListVariantItemBinding inflate = ListVariantItemBinding.inflate(from);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
            viewHolder.setLnContainer(inflate.linearCheckList);
            viewHolder.setTvCode(inflate.tvCode);
            TextView tvCode = viewHolder.getTvCode();
            Intrinsics.checkNotNull(tvCode);
            tvCode.setMovementMethod(new ScrollingMovementMethod());
            viewHolder.setTvName(inflate.tvVariantName);
            TextView tvName = viewHolder.getTvName();
            Intrinsics.checkNotNull(tvName);
            tvName.setMovementMethod(new ScrollingMovementMethod());
            viewHolder.setEdtUserAmount(inflate.edtUserAmount);
            viewHolder.setCbListItem(inflate.cbListItem);
            viewHolder.setMWatcher(new MutableWatcher());
            AppCompatEditText edtUserAmount = viewHolder.getEdtUserAmount();
            Intrinsics.checkNotNull(edtUserAmount);
            edtUserAmount.addTextChangedListener(viewHolder.getMWatcher());
            viewHolder.setTxtActualStock(inflate.txtProductActualStock);
            viewHolder.setTxtRealStock(inflate.txtRealStock);
            view2 = inflate.getRoot();
            Intrinsics.checkNotNullExpressionValue(view2, "getRoot(...)");
            view2.setTag(viewHolder);
        } else {
            Object tag = view.getTag();
            Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type com.proje.mobilesales.features.sales.view.variant.SalesVariantAdapter.ViewHolder");
            ViewHolder viewHolder2 = (ViewHolder) tag;
            view2 = view;
            viewHolder = viewHolder2;
        }
        AppCompatCheckBox cbListItem = viewHolder.getCbListItem();
        Intrinsics.checkNotNull(cbListItem);
        cbListItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view3) {
                SalesVariantAdapter.getViewlambda0(SalesVariantAdapter.this, i2, viewHolder);
            }
        });
        SalesVariantCheck item = getItem(i2);
        TextView tvCode2 = viewHolder.getTvCode();
        Intrinsics.checkNotNull(tvCode2);
        ItemVariantStock mVariant = item.getMVariant();
        Intrinsics.checkNotNull(mVariant);
        tvCode2.setText(mVariant.getVarintCode());
        TextView tvName2 = viewHolder.getTvName();
        Intrinsics.checkNotNull(tvName2);
        ItemVariantStock mVariant2 = item.getMVariant();
        Intrinsics.checkNotNull(mVariant2);
        tvName2.setText(mVariant2.getVariantName());
        AppCompatCheckBox cbListItem2 = viewHolder.getCbListItem();
        Intrinsics.checkNotNull(cbListItem2);
        cbListItem2.setChecked(item.isChecked());
        MutableWatcher mWatcher = viewHolder.getMWatcher();
        Intrinsics.checkNotNull(mWatcher);
        mWatcher.setActive(false);
        MutableWatcher mWatcher2 = viewHolder.getMWatcher();
        Intrinsics.checkNotNull(mWatcher2);
        mWatcher2.setError();
        MutableWatcher mWatcher3 = viewHolder.getMWatcher();
        Intrinsics.checkNotNull(mWatcher3);
        mWatcher3.setEditText();
        if (item.isDivUnit()) {
            AppCompatEditText edtUserAmount2 = viewHolder.getEdtUserAmount();
            Intrinsics.checkNotNull(edtUserAmount2);
            ItemVariantStock mVariant3 = item.getMVariant();
            Intrinsics.checkNotNull(mVariant3);
            edtUserAmount2.setText(StringUtils.convertDoubleToString(mVariant3.getAmount()), TextView.BufferType.EDITABLE);
            AppCompatEditText edtUserAmount3 = viewHolder.getEdtUserAmount();
            Intrinsics.checkNotNull(edtUserAmount3);
            edtUserAmount3.setInputType(8194);
        } else {
            AppCompatEditText edtUserAmount4 = viewHolder.getEdtUserAmount();
            Intrinsics.checkNotNull(edtUserAmount4);
            ItemVariantStock mVariant4 = item.getMVariant();
            Intrinsics.checkNotNull(mVariant4);
            edtUserAmount4.setText(StringUtils.convertIntToString((int) mVariant4.getAmount()), TextView.BufferType.EDITABLE);
            AppCompatEditText edtUserAmount5 = viewHolder.getEdtUserAmount();
            Intrinsics.checkNotNull(edtUserAmount5);
            edtUserAmount5.setInputType(2);
        }
        MutableWatcher mWatcher4 = viewHolder.getMWatcher();
        Intrinsics.checkNotNull(mWatcher4);
        mWatcher4.setPosition(i2);
        MutableWatcher mWatcher5 = viewHolder.getMWatcher();
        Intrinsics.checkNotNull(mWatcher5);
        mWatcher5.setActive(true);
        if (!item.isEnabled()) {
            TextView tvCode3 = viewHolder.getTvCode();
            Intrinsics.checkNotNull(tvCode3);
            TextView tvCode4 = viewHolder.getTvCode();
            Intrinsics.checkNotNull(tvCode4);
            tvCode3.setPaintFlags(tvCode4.getPaintFlags() | 16);
            TextView tvName3 = viewHolder.getTvName();
            Intrinsics.checkNotNull(tvName3);
            TextView tvName4 = viewHolder.getTvName();
            Intrinsics.checkNotNull(tvName4);
            tvName3.setPaintFlags(tvName4.getPaintFlags() | 16);
        } else {
            TextView tvCode5 = viewHolder.getTvCode();
            Intrinsics.checkNotNull(tvCode5);
            TextView tvCode6 = viewHolder.getTvCode();
            Intrinsics.checkNotNull(tvCode6);
            tvCode5.setPaintFlags(tvCode6.getPaintFlags() & (-17));
            TextView tvName5 = viewHolder.getTvName();
            Intrinsics.checkNotNull(tvName5);
            TextView tvName6 = viewHolder.getTvName();
            Intrinsics.checkNotNull(tvName6);
            tvName5.setPaintFlags(tvName6.getPaintFlags() & (-17));
        }
        LinearLayout lnContainer = viewHolder.getLnContainer();
        Intrinsics.checkNotNull(lnContainer);
        int childCount = lnContainer.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            LinearLayout lnContainer2 = viewHolder.getLnContainer();
            Intrinsics.checkNotNull(lnContainer2);
            lnContainer2.getChildAt(i3).setEnabled(item.isEnabled());
        }
        AppCompatEditText edtUserAmount6 = viewHolder.getEdtUserAmount();
        Intrinsics.checkNotNull(edtUserAmount6);
        edtUserAmount6.setEnabled(item.isChecked());
        TextView txtActualStock = viewHolder.getTxtActualStock();
        Intrinsics.checkNotNull(txtActualStock);
        ItemVariantStock mVariant5 = item.getMVariant();
        Intrinsics.checkNotNull(mVariant5);
        txtActualStock.setText(StringUtils.formatStock(mVariant5.getVariantActualStok()));
        TextView txtRealStock = viewHolder.getTxtRealStock();
        Intrinsics.checkNotNull(txtRealStock);
        ItemVariantStock mVariant6 = item.getMVariant();
        Intrinsics.checkNotNull(mVariant6);
        txtRealStock.setText(StringUtils.formatStock(mVariant6.getVariantRealStok()));
        return view2;
    }
    public static void getViewlambda0(SalesVariantAdapter this0, int i2, ViewHolder view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(view, "view");
        this0.selectItemPosition(i2, view.getCbListItem(), view.getEdtUserAmount());
    }
    private void selectItemPosition(int i2, AppCompatCheckBox appCompatCheckBox, AppCompatEditText appCompatEditText) {
        ArrayList<SalesVariantCheck> arrayList = this.items;
        Intrinsics.checkNotNull(arrayList);
        if (!arrayList.get(i2).isChecked()) {
            ArrayList<SalesVariantCheck> arrayList2 = this.items;
            Intrinsics.checkNotNull(arrayList2);
            arrayList2.get(i2).setChecked(true);
            Intrinsics.checkNotNull(appCompatCheckBox);
            appCompatCheckBox.setChecked(true);
            Intrinsics.checkNotNull(appCompatEditText);
            appCompatEditText.setEnabled(true);
            appCompatEditText.setSelectAllOnFocus(true);
            appCompatEditText.clearFocus();
            appCompatEditText.requestFocus();
            appCompatEditText.setSelectAllOnFocus(true);
            appCompatEditText.setText(BuildConfig.NETSIS_DEMO_PASSWORD);
            this.mChecked++;
        } else {
            ArrayList<SalesVariantCheck> arrayList3 = this.items;
            Intrinsics.checkNotNull(arrayList3);
            arrayList3.get(i2).setChecked(false);
            Intrinsics.checkNotNull(appCompatEditText);
            appCompatEditText.setText("0");
            Intrinsics.checkNotNull(appCompatCheckBox);
            appCompatCheckBox.setChecked(false);
            appCompatEditText.setEnabled(false);
            this.mChecked--;
        }
        updateChecked();
    }
    public void updateChecked() {
        this.mSalesVariantListener.onSelectChangedListener(this.mChecked);
    }
    public void saveState(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        Log.d(TAG, "saveState: open");
        this.items = bundle.getParcelableArrayList(STATE_VARIANT_CHECK_LIST);
        notifyDataSetChanged();
    }
    public static final class ViewHolder {
        private AppCompatCheckBox cbListItem;
        private AppCompatEditText edtUserAmount;
        private LinearLayout lnContainer;
        private MutableWatcher mWatcher;
        private TextView tvCode;
        private TextView tvName;
        private TextView txtActualStock;
        private TextView txtRealStock;
        public TextView getTvCode() {
            return this.tvCode;
        }
        public void setTvCode(TextView textView) {
            this.tvCode = textView;
        }
        public TextView getTvName() {
            return this.tvName;
        }
        public void setTvName(TextView textView) {
            this.tvName = textView;
        }
        public AppCompatEditText getEdtUserAmount() {
            return this.edtUserAmount;
        }
        public void setEdtUserAmount(AppCompatEditText appCompatEditText) {
            this.edtUserAmount = appCompatEditText;
        }
        public AppCompatCheckBox getCbListItem() {
            return this.cbListItem;
        }
        public void setCbListItem(AppCompatCheckBox appCompatCheckBox) {
            this.cbListItem = appCompatCheckBox;
        }
        public MutableWatcher getMWatcher() {
            return this.mWatcher;
        }
        public void setMWatcher(MutableWatcher mutableWatcher) {
            this.mWatcher = mutableWatcher;
        }
        public LinearLayout getLnContainer() {
            return this.lnContainer;
        }
        public void setLnContainer(LinearLayout linearLayout) {
            this.lnContainer = linearLayout;
        }
        public TextView getTxtActualStock() {
            return this.txtActualStock;
        }
        public void setTxtActualStock(TextView textView) {
            this.txtActualStock = textView;
        }
        public TextView getTxtRealStock() {
            return this.txtRealStock;
        }
        public void setTxtRealStock(TextView textView) {
            this.txtRealStock = textView;
        }
    }
    public final class MutableWatcher implements TextWatcher {
        private boolean mActive;
        private int mPosition;
        public MutableWatcher() {
        }
        public void afterTextChanged(Editable s) {
            Intrinsics.checkNotNullParameter(s, "s");
        }
        public void beforeTextChanged(CharSequence s, int i2, int i3, int i4) {
            Intrinsics.checkNotNullParameter(s, "s");
        }
        public void setPosition(int i2) {
            this.mPosition = i2;
        }
        public void setActive(boolean z) {
            this.mActive = z;
        }
        public void setError() {
        }
        public void setEditText() {
        }
        public void onTextChanged(CharSequence s, int i2, int i3, int i4) {
            Intrinsics.checkNotNullParameter(s, "s");
            if (this.mActive) {
                double convertStringToDouble = StringUtils.convertStringToDouble(s.toString());
                ItemVariantStock mVariant = SalesVariantAdapter.this.getItem(this.mPosition).getMVariant();
                Intrinsics.checkNotNull(mVariant);
                mVariant.setAmount(convertStringToDouble);
            }
        }
    }
    public boolean isAllChecked() {
        ArrayList<SalesVariantCheck> arrayList = this.items;
        Intrinsics.checkNotNull(arrayList);
        if (arrayList.isEmpty()) {
            return true;
        }
        int i2 = this.mChecked;
        ArrayList<SalesVariantCheck> arrayList2 = this.items;
        Intrinsics.checkNotNull(arrayList2);
        return i2 != arrayList2.size();
    }
    public SalesVariantCheck findItem(int i2) {
        ArrayList<SalesVariantCheck> arrayList = this.items;
        Intrinsics.checkNotNull(arrayList);
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            ArrayList<SalesVariantCheck> arrayList2 = this.items;
            Intrinsics.checkNotNull(arrayList2);
            SalesVariantCheck salesVariantCheck = arrayList2.get(i3);
            Intrinsics.checkNotNullExpressionValue(salesVariantCheck, "get(...)");
            ItemVariantStock mVariant = salesVariantCheck.getMVariant();
            Intrinsics.checkNotNull(mVariant);
            if (mVariant.getVariantRef() == i2) {
                return salesVariantCheck;
            }
        }
        return null;
    }
    public int getCheckedCount() {
        ArrayList<SalesVariantCheck> arrayList = this.items;
        Intrinsics.checkNotNull(arrayList);
        int size = arrayList.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            ArrayList<SalesVariantCheck> arrayList2 = this.items;
            Intrinsics.checkNotNull(arrayList2);
            if (arrayList2.get(i3).component1()) {
                i2++;
            }
        }
        return i2;
    }
    public void setList(ArrayList<SalesVariantCheck> arrayList) {
        if (arrayList != null) {
            this.items = arrayList;
            this.mChecked = getCheckedCount();
        }
        updateChecked();
        notifyDataSetChanged();
    }
    public void selectAll(boolean z) {
        int i2;
        ArrayList<SalesVariantCheck> arrayList = this.items;
        if (arrayList == null) {
            return;
        }
        if (z) {
            Intrinsics.checkNotNull(arrayList);
            i2 = arrayList.size();
        } else {
            i2 = 0;
        }
        this.mChecked = i2;
        ArrayList<SalesVariantCheck> arrayList2 = this.items;
        Intrinsics.checkNotNull(arrayList2);
        for (SalesVariantCheck next : arrayList2) {
            if (!next.isChecked() && z) {
                ItemVariantStock mVariant = next.getMVariant();
                Intrinsics.checkNotNull(mVariant);
                mVariant.setAmount(1.0d);
            }
            if (next.isChecked() && !z) {
                ItemVariantStock mVariant2 = next.getMVariant();
                Intrinsics.checkNotNull(mVariant2);
                mVariant2.setAmount(0.0d);
            }
            next.setChecked(z);
        }
        updateChecked();
        notifyDataSetChanged();
    }
    public Bundle outState() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(STATE_VARIANT_CHECK_LIST, this.items);
        return bundle;
    }
    public static final class Companion {
        public   Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
        private Companion() {
        }
    }
}
