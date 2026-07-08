package com.proje.mobilesales.features.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.IdRes;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.interfaces.MasterPassCardResultListener;
import com.proje.mobilesales.core.masterpass.MasterPassCardItem;
import java.util.List;

public class MasterPassCardRecyclerViewAdapter extends RecyclerView.Adapter<MasterPassCardRecyclerViewAdapter.ViewHolder> {
    private MasterPassCardResultListener mCardResultListener;
    private BottomSheetDialog mSearchDialog;
    private final List<MasterPassCardItem> mValues;

    public MasterPassCardRecyclerViewAdapter(List<MasterPassCardItem> list) {
        this.mValues = list;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_master_pass_card, viewGroup, false));
    }
    public void onBindViewHolder(final ViewHolder viewHolder, final int i2) {
        viewHolder.mItem = this.mValues.get(i2);
        viewHolder.mCardNameView.setText(this.mValues.get(i2).getName());
        viewHolder.mCardNumberView.setText(this.mValues.get(i2).getMaskedPan());
        ((AppCompatCheckedTextView) viewHolder.getViewById(R.id.chkSelected)).setChecked(viewHolder.mItem.isSelected());
        if (this.mCardResultListener != null) {
            viewHolder.getBaseView().setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MasterPassCardRecyclerViewAdapter.this.mCardResultListener.onSelected(MasterPassCardRecyclerViewAdapter.this.getSearchDialog(), viewHolder.mItem, i2);
                }
            });
        }
    }
    public int getItemCount() {
        return this.mValues.size();
    }

    public MasterPassCardResultListener getCardResultListener() {
        return this.mCardResultListener;
    }

    public void setCardResultListener(MasterPassCardResultListener masterPassCardResultListener) {
        this.mCardResultListener = masterPassCardResultListener;
    }

    public BottomSheetDialog getSearchDialog() {
        return this.mSearchDialog;
    }

    public void setSearchDialog(BottomSheetDialog bottomSheetDialog) {
        this.mSearchDialog = bottomSheetDialog;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mCardNameView;
        public final TextView mCardNumberView;
        public MasterPassCardItem mItem;
        public final View mView;

        public ViewHolder(View view) {
            super(view);
            this.mView = view;
            this.mCardNameView = view.findViewById(R.id.card_name);
            this.mCardNumberView = view.findViewById(R.id.card_number);
        }

        public View getBaseView() {
            return this.mView;
        }

        public <T> T getViewById(@IdRes int i2) {
            return (T) this.mView.findViewById(i2);
        }

        public String toString() {
            return super.toString() + " '" + this.mCardNameView.getText() + "'";
        }
    }
}
