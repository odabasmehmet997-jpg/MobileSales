package com.proje.mobilesales.features.product.view.tree;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.messaging.Constants;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.TintableTextView;
import com.proje.mobilesales.features.product.model.ProductTreeItem;
import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;

import static com.proje.mobilesales.core.utils.AppUtils.createLayoutInflater;

public final class ProductTreeRecyclerViewAdapter extends RecyclerView.Adapter<ProductTreeRecyclerViewAdapter.ItemViewHolder> {
    private ArrayList<ProductTreeItem> dataList;
    private Context mContext;
    public LayoutInflater mLayoutInflater;
    private final ProductTreeItemSelectedListener mProductTreeItemSelectedListener;
 
    public interface ProductTreeItemSelectedListener {
        void productTreeItemSelected(ProductTreeItem productTreeItem);
    }

    public ProductTreeRecyclerViewAdapter(ProductTreeItemSelectedListener productTreeItemSelectedListener) {
        Intrinsics.checkNotNullParameter(productTreeItemSelectedListener, "mListener");
        this.mProductTreeItemSelectedListener = productTreeItemSelectedListener;
    }

    public LayoutInflater getMLayoutInflater() {
        LayoutInflater layoutInflater = this.mLayoutInflater;
        if (layoutInflater != null) {
            return layoutInflater;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mLayoutInflater");
        return null;
    }
    public void setMLayoutInflater(LayoutInflater layoutInflater) {
        Intrinsics.checkNotNullParameter(layoutInflater, "<set-?>");
        this.mLayoutInflater = layoutInflater;
    }
    public Context getMContext() {
        return this.mContext;
    }
    public void setMContext(Context context) {
        this.mContext = context;
    }
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        Context context = recyclerView.getContext();
        this.mContext = context;
        LayoutInflater createLayoutInflater = createLayoutInflater(context);
        Intrinsics.checkNotNullExpressionValue(createLayoutInflater, "createLayoutInflater(...)");
        setMLayoutInflater(createLayoutInflater);
    }
    public ProductTreeItem getProperty(int i) {
        ArrayList<ProductTreeItem> arrayList = this.dataList;
        if (arrayList == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dataList");
            arrayList = null;
        }
        ProductTreeItem productTreeItem = arrayList.get(i);
        Intrinsics.checkNotNullExpressionValue(productTreeItem, "get(...)");
        return productTreeItem;
    }

    public void setData(ArrayList<ProductTreeItem> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        this.dataList = arrayList;
        notifyDataSetChanged();
    }

    public boolean isAttached() {
        return this.mContext != null;
    }

    public void initDisplayOptions(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (isAttached()) {
            notifyDataSetChanged();
        }
    } 
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        View inflate = getMLayoutInflater().inflate(R.layout.item_product_tree, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ItemViewHolder(inflate);
    }
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        Intrinsics.checkNotNullParameter(itemViewHolder, "holder");
        bindData(itemViewHolder, getProperty(i), i);
    }
    public void bindData(ItemViewHolder itemViewHolder, ProductTreeItem productTreeItem, int i) {
        Intrinsics.checkNotNullParameter(itemViewHolder, "holder");
        Intrinsics.checkNotNullParameter(productTreeItem, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        itemViewHolder.getTxtTreeItemDesc().setText(productTreeItem.getDescription());
        itemViewHolder.getTxtTreeItemDesc().setOnClickListener(new View.OnClickListener(productTreeItem) {
            public final ProductTreeItem f1;
            {
                this.f1 = productTreeItem;
            }
            public void onClick(View view) {
                ProductTreeRecyclerViewAdapter.bindDatalambda0(ProductTreeRecyclerViewAdapter.this, productTreeItem, view);
            }
        });
    }
    public static void bindDatalambda0(ProductTreeRecyclerViewAdapter productTreeRecyclerViewAdapter, ProductTreeItem productTreeItem, View view) {
        Intrinsics.checkNotNullParameter(productTreeRecyclerViewAdapter, "this0");
        Intrinsics.checkNotNullParameter(productTreeItem, "data");
        productTreeRecyclerViewAdapter.mProductTreeItemSelectedListener.productTreeItemSelected(productTreeItem);
    }
    public int getItemCount() {
        ArrayList<ProductTreeItem> arrayList = this.dataList;
        if (arrayList == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dataList");
            arrayList = null;
        }
        return arrayList.size();
    }
    public static final class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TintableTextView txtTreeItemDesc;

        public ItemViewHolder(View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "itemView");
            View findViewById = view.findViewById(R.id.txt_treeItemDesc);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            this.txtTreeItemDesc = (TintableTextView) findViewById;
        }

        public  TintableTextView getTxtTreeItemDesc() {
            return this.txtTreeItemDesc;
        }
    }
}
