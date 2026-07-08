package com.proje.mobilesales.features.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.widget.AppCompatTextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.model.BarcodeNew;
import java.util.ArrayList;

public class BarcodeAdapter extends BaseAdapter {
    ArrayList<BarcodeNew> mBarcodeList = new ArrayList<>();
    Context mContext;

    public static class BarcodeView {
        public AppCompatTextView txtViewCode;
        public AppCompatTextView txtViewName;
    }

    public BarcodeAdapter(Context context) {
        this.mContext = context;
    }

    public int getCount() {
        ArrayList<BarcodeNew> arrayList = this.mBarcodeList;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }
    public BarcodeNew getItem(int i2) {
        return this.mBarcodeList.get(i2);
    }

    public long getItemId(int i2) {
        return this.mBarcodeList.get(i2).getItemRef();
    }

    public View getView(int i2, View view, ViewGroup viewGroup) {
        View view2;
        BarcodeView barcodeView;
        LayoutInflater from = LayoutInflater.from(this.mContext);
        if (view == null) {
            barcodeView = new BarcodeView();
            view2 = from.inflate(R.layout.row_barcode_view, null);
            barcodeView.txtViewCode = view2.findViewById(R.id.txt_itemCode);
            barcodeView.txtViewName = view2.findViewById(R.id.txt_itemName);
            view2.setTag(barcodeView);
        } else {
            view2 = view;
            barcodeView = (BarcodeView) view.getTag();
        }
        BarcodeNew item = getItem(i2);
        if (item != null) {
            barcodeView.txtViewName.setText(item.getName());
            barcodeView.txtViewCode.setText(item.getCode());
        }
        return view2;
    }

    public void addItems(BarcodeNew barcodeNew) {
        this.mBarcodeList.add(barcodeNew);
        notifyDataSetChanged();
    }
}
