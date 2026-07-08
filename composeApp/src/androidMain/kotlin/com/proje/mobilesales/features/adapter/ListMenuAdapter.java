package com.proje.mobilesales.features.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import java.util.List;
public class ListMenuAdapter extends RecyclerView.Adapter<ListMenuAdapter.ViewHolder> {
    private final List<String> mMenuList;

    public static void lambdaonCreateViewHolder0(View view) {
    }

    public ListMenuAdapter(List<String> list) {
        this.mMenuList = list;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_transfer_row, viewGroup, false));
        viewHolder.mTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ListMenuAdapter.lambdaonCreateViewHolder0(view);
            }
        });
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i2) {
        viewHolder.mTextView.setText(this.mMenuList.get(i2));
    }

    public int getItemCount() {
        return this.mMenuList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView mTextView;

        public ViewHolder(View view) {
            super(view);
            this.mTextView = view.findViewById(R.id.twMenuItem);
        }
    }
}
