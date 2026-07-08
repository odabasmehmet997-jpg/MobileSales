package com.proje.mobilesales.core.preferences;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.proje.mobilesales.R;

import java.io.File;
import java.util.List;

class FileAdapter extends ArrayAdapter<File> {
    private final LayoutInflater inflater;
    public FileAdapter(Context context, List<File> list) {
        super(context, 0, list);
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public View getView(int i2, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (textView == null) {
            textView = (TextView) this.inflater.inflate(R.layout.simple_list_item_1, viewGroup, false);
        }
        fillViews(textView, getItem(i2));
        return textView;
    }
    private void fillViews(TextView textView, File file) {
        textView.setText(file.getName());
    }
}
