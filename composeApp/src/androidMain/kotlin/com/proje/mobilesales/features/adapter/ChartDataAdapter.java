package com.proje.mobilesales.features.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.proje.mobilesales.core.base.ChartItem;
import java.util.List;
public class ChartDataAdapter extends ArrayAdapter<ChartItem> {
    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 3;
    }

    public ChartDataAdapter(Context context, List<ChartItem> list) {
        super(context, 0, list);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        return getItem(i2).getView(i2, view, getContext());
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i2) {
        return getItem(i2).getItemType();
    }
}
