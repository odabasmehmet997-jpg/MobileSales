package com.proje.mobilesales.core.preferences;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.proje.mobilesales.R;



public class SimpleSpinnerPreference extends SpinnerPreference {
    private final LayoutInflater mLayoutInflater;

    public SimpleSpinnerPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
    @SuppressLint("ResourceType")
    public SimpleSpinnerPreference(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mLayoutInflater = LayoutInflater.from(getContext());
    }
    protected View createDropDownView(int i2, ViewGroup viewGroup) {
        return this.mLayoutInflater.inflate(R.layout.support_simple_spinner_dropdown_item, viewGroup, false);
    }
    protected void bindDropDownView(int i2, View view) {
        ((TextView) view.findViewById(R.id.text1)).setText(this.mEntries[i2]);
    }
}
