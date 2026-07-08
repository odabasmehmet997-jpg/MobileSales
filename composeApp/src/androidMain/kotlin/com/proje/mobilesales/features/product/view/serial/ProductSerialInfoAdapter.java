package com.proje.mobilesales.features.product.view.serial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.databinding.SerialInfoItemBinding;
import com.proje.mobilesales.features.product.model.database.ItemSerialInfo;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;
import java.util.List;

import static com.proje.mobilesales.core.utils.AppUtils.createLayoutInflater;

public final class ProductSerialInfoAdapter extends RecyclerView.Adapter<ProductSerialInfoAdapter.ViewHolder> {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private final List<ItemSerialInfo> serialInfoList;

    public ProductSerialInfoAdapter() {
        this(null, 1, null);
    }
    public  ProductSerialInfoAdapter(Context context, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : context);
    }

    public Context getMContext() {
        return this.mContext;
    }

    public void setMContext(Context context) {
        this.mContext = context;
    }

    public ProductSerialInfoAdapter(Context context) {
        this.mContext = context;
        this.serialInfoList = new ArrayList();
    }
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        Context context = recyclerView.getContext();
        this.mContext = context;
        this.mLayoutInflater = createLayoutInflater(context);
    }

    public   void addItem(ArrayList<ItemSerialInfo> arrayList) {
        if (arrayList != null) {
            this.serialInfoList.clear();
            this.serialInfoList.addAll(arrayList);
            notifyDataSetChanged();
        }
    }
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onDetachedFromRecyclerView(recyclerView);
        this.mContext = null;
    }

    public   ItemSerialInfo getItem(int i) {
        return this.serialInfoList.get(i);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        viewHolder.bind(getItem(i));
    }
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        LayoutInflater layoutInflater = this.mLayoutInflater;
        Intrinsics.checkNotNull(layoutInflater);
        SerialInfoItemBinding inflate = SerialInfoItemBinding.inflate(layoutInflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ViewHolder(this, inflate);
    }
    public int getItemCount() {
        return this.serialInfoList.size();
    }

    public final class ViewHolder extends RecyclerView.ViewHolder {
        private final SerialInfoItemBinding binding;
        final  ProductSerialInfoAdapter this0;
        public ViewHolder(ProductSerialInfoAdapter productSerialInfoAdapter, SerialInfoItemBinding serialInfoItemBinding) {
            super(serialInfoItemBinding.getRoot());
            Intrinsics.checkNotNullParameter(serialInfoItemBinding, "binding");
            this.this0 = productSerialInfoAdapter;
            this.binding = serialInfoItemBinding;
        }

        public   void bind(ItemSerialInfo itemSerialInfo) {
            Intrinsics.checkNotNullParameter(itemSerialInfo, "serialInfo");
            this.binding.txtSerialNo1.setText(itemSerialInfo.serialNumber1);
            this.binding.txtSerialNo2.setText(itemSerialInfo.serialNumber2);
            this.binding.txtProductStock.setText(itemSerialInfo.amount);
        }
    }
}
