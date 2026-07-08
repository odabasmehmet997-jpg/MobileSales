package com.proje.mobilesales.features.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.features.model.TransferGetItem;
import java.util.List;
 
public class TransferGetAdapter extends RecyclerView.Adapter<TransferGetAdapter.ViewHolder> {
    List<TransferGetItem> list;

    public TransferGetAdapter(List<TransferGetItem> list) {
        this.list = list;
    }
 
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_transfer_get, viewGroup, false));
    }
 
    public void onBindViewHolder(ViewHolder viewHolder, int i2) {
        TransferGetItem transferGetItem = this.list.get(i2);
        viewHolder.twGetTitle.setText(ContextUtils.getStringResource(transferGetItem.getTransferGetType().resId));
        if (transferGetItem.isSelect()) {
            viewHolder.mImageView.setImageResource(R.drawable.ic_checkbox_marked_circle_grey600_48dp);
            viewHolder.twGetTitle.setTextColor(ContextCompat.getColor(ContextUtils.getmContext(), R.color.subMenu1));
        } else {
            viewHolder.mImageView.setImageResource(R.drawable.ic_checkbox_blank_circle_grey600_48dp);
            viewHolder.twGetTitle.setTextColor(ContextCompat.getColor(ContextUtils.getmContext(), R.color.subMenu4));
        }
        viewHolder.mImageView.setOnClickListener(new View.OnClickListener() { 
            final ViewHolder valholder = null;
            ViewHolder r3 = null;
            TransferGetItem r2 = null;
            final TransferGetItem valtempTransferGetItem = null;


            void ViewOnClickListenerC26371(TransferGetItem transferGetItem2, ViewHolder viewHolder2) {
                r2 = transferGetItem2;
                r3 = viewHolder2;
            }
            public void onClick(View view) {
                boolean isSelect = r2.isSelect();
                boolean z = !isSelect;
                if (!isSelect) {
                    r3.mImageView.setImageResource(R.drawable.ic_checkbox_marked_circle_grey600_48dp);
                    r3.twGetTitle.setTextColor(ContextCompat.getColor(ContextUtils.getmContext(), R.color.subMenu1));
                } else {
                    r3.mImageView.setImageResource(R.drawable.ic_checkbox_blank_circle_grey600_48dp);
                    r3.twGetTitle.setTextColor(ContextCompat.getColor(ContextUtils.getmContext(), R.color.subMenu4));
                }
                r2.setSelect(z);
            }
        });
        ViewHolder viewHolder2 = null;
        viewHolder2.twGetTitle.setOnClickListener(new View.OnClickListener() {
            final ViewHolder valholder = null;
            ViewHolder r3 = null;
            TransferGetItem r2 = null;
            final TransferGetItem valtempTransferGetItem = null;
            void ViewOnClickListenerC26382(TransferGetItem transferGetItem2, ViewHolder viewHolder2) {
                r2 = transferGetItem2;
                r3 = viewHolder2;
            }
            public void onClick(View view) {
                boolean isSelect = r2.isSelect();
                boolean z = !isSelect;
                if (!isSelect) {
                    r3.mImageView.setImageResource(R.drawable.ic_checkbox_marked_circle_grey600_48dp);
                    r3.twGetTitle.setTextColor(ContextCompat.getColor(ContextUtils.getmContext(), R.color.subMenu1));
                } else {
                    r3.mImageView.setImageResource(R.drawable.ic_checkbox_blank_circle_grey600_48dp);
                    r3.twGetTitle.setTextColor(ContextCompat.getColor(ContextUtils.getmContext(), R.color.subMenu4));
                }
                r2.setSelect(z);
            }
        });
    }
    class ViewOnClickListenerC26371 implements View.OnClickListener {
        final ViewHolder valholder;
        ViewHolder r3 = null;
        TransferGetItem r2 = null;
        final TransferGetItem valtempTransferGetItem;

        ViewOnClickListenerC26371(ViewHolder valholder, TransferGetItem transferGetItem2, ViewHolder viewHolder2, TransferGetItem valtempTransferGetItem) {
            this.valholder = valholder;
            r2 = transferGetItem2;
            r3 = viewHolder2;
            this.valtempTransferGetItem = valtempTransferGetItem;
        }

        public void onClick(View view) {
            boolean isSelect = r2.isSelect();
            boolean z = !isSelect;
            if (!isSelect) {
                r3.mImageView.setImageResource(R.drawable.ic_checkbox_marked_circle_grey600_48dp);
                r3.twGetTitle.setTextColor(ContextCompat.getColor(ContextUtils.getmContext(), R.color.subMenu1));
            } else {
                r3.mImageView.setImageResource(R.drawable.ic_checkbox_blank_circle_grey600_48dp);
                r3.twGetTitle.setTextColor(ContextCompat.getColor(ContextUtils.getmContext(), R.color.subMenu4));
            }
            r2.setSelect(z);
        }
    }
    class ViewOnClickListenerC26382 implements View.OnClickListener {
        final ViewHolder valholder;
        final TransferGetItem valtempTransferGetItem;
        ViewHolder r3 = null;
        TransferGetItem r2 = null;
        ViewOnClickListenerC26382(ViewHolder valholder, TransferGetItem transferGetItem2, ViewHolder viewHolder2, TransferGetItem valtempTransferGetItem, TransferGetItem valtempTransferGetItem1) {
            this.valholder = valholder;
            r2 = transferGetItem2;
            r3 = viewHolder2;
            this.valtempTransferGetItem = valtempTransferGetItem1;
        }

        public void onClick(View view) {
            boolean isSelect = r2.isSelect();
            boolean z = !isSelect;
            if (!isSelect) {
                r3.mImageView.setImageResource(R.drawable.ic_checkbox_marked_circle_grey600_48dp);
                r3.twGetTitle.setTextColor(ContextCompat.getColor(ContextUtils.getmContext(), R.color.subMenu1));
            } else {
                r3.mImageView.setImageResource(R.drawable.ic_checkbox_blank_circle_grey600_48dp);
                r3.twGetTitle.setTextColor(ContextCompat.getColor(ContextUtils.getmContext(), R.color.subMenu4));
            }
            r2.setSelect(z);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView imgGetResult;
        private AppCompatImageView mImageView;
        private AppCompatTextView twGetTitle;

        private void findViews(View view) {
            this.mImageView = view.findViewById(R.id.imgGetAdd);
            this.twGetTitle = view.findViewById(R.id.twGetTitle);
            this.imgGetResult = view.findViewById(R.id.imgGetResult);
        }

        public ViewHolder(View view) {
            super(view);
            findViews(view);
        }
    }
}
