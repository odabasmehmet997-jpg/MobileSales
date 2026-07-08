package com.proje.mobilesales.features.adapter;

import android.view.View;
import android.widget.ProgressBar;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;

public class LoadingViewHolder extends RecyclerView.ViewHolder {
    public ProgressBar progressBar;

    public LoadingViewHolder(View view) {
        super(view);
        this.progressBar = view.findViewById(R.id.progressBar);
    }
}
