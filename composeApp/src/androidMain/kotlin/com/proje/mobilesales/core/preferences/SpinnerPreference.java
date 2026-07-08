package com.proje.mobilesales.core.preferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.proje.mobilesales.R;

public abstract class SpinnerPreference extends Preference {
    String[] mEntries;
    String[] mEntryValues;
    int mSelection;
    protected abstract void bindDropDownView(int i2, View view);
    protected abstract View createDropDownView(int i2, ViewGroup viewGroup);
    public SpinnerPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
    public SpinnerPreference(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mEntries = new String[0];
        this.mEntryValues = new String[0];
        this.mSelection = 0;
        setWidgetLayoutResource(R.layout.preference_spinner);
        init(context, attributeSet);
    }
    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SpinnerPref);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            this.mEntries = context.getResources().getStringArray(resourceId);
        }
        @SuppressLint("ResourceType") int resourceId2 = obtainStyledAttributes.getResourceId(1, 0);
        if (resourceId2 != 0) {
            this.mEntryValues = context.getResources().getStringArray(resourceId2);
        }
        obtainStyledAttributes.recycle();
    }
    protected Object onGetDefaultValue(TypedArray typedArray, int i2) {
        return typedArray.getString(i2);
    }
    protected void onSetInitialValue(boolean z, Object obj) {
        super.onSetInitialValue(z, obj);
        String persistedString = z ? getPersistedString(null) : (String) obj;
        int i2 = 0;
        while (i2 < this.mEntryValues.length) {
            if (TextUtils.equals(this.mEntryValues[i2], persistedString)) {
                this.mSelection = i2;
                return;
            }
            i2++;
        }
    }
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        final Spinner spinner = (Spinner) preferenceViewHolder.findViewById(R.id.spinner);
        preferenceViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.core.preferences.SpinnerPreferenceExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                spinner.performClick();
            }
        });
        spinner.setAdapter(new SpinnerAdapter() { // from class: com.proje.mobilesales.core.preferences.SpinnerPreference.1
            @Override // android.widget.Adapter
            public Object getItem(int i2) {
                return null;
            }

            @Override // android.widget.Adapter
            public long getItemId(int i2) {
                return i2;
            }

            @Override // android.widget.Adapter
            public int getItemViewType(int i2) {
                return 0;
            }

            @Override // android.widget.Adapter
            public int getViewTypeCount() {
                return 1;
            }

            @Override // android.widget.Adapter
            public boolean hasStableIds() {
                return true;
            }

            @Override // android.widget.Adapter
            public boolean isEmpty() {
                return false;
            }

            @Override // android.widget.Adapter
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            }

            @Override // android.widget.Adapter
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            }

            @Override // android.widget.SpinnerAdapter
            public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = SpinnerPreference.this.createDropDownView(i2, viewGroup);
                }
                SpinnerPreference.this.bindDropDownView(i2, view);
                return view;
            }

            @Override // android.widget.Adapter
            public int getCount() {
                return SpinnerPreference.this.mEntries.length;
            }

            @Override // android.widget.Adapter
            public View getView(int i2, View view, ViewGroup viewGroup) {
                return getDropDownView(i2, view, viewGroup);
            }
        });
        spinner.setSelection(this.mSelection);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.proje.mobilesales.core.preferences.SpinnerPreference.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j2) {
                SpinnerPreference spinnerPreference = SpinnerPreference.this;
                spinnerPreference.mSelection = i2;
                spinnerPreference.persistString(spinnerPreference.mEntryValues[i2]);
            }
        });
    }
}
