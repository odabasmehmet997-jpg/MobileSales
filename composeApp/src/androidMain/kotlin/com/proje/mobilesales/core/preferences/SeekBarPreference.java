package com.proje.mobilesales.core.preferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.proje.mobilesales.R;

public class SeekBarPreference extends Preference implements SeekBar.OnSeekBarChangeListener {
    private int mProgress;
    private SeekBar mSeekBar;
    private TextView mTextView;
    private boolean mTrackingTouch;
    private final int maxFontValue;
    private final int minFontValue;
    public void onStartTrackingTouch(SeekBar seekBar) {
    }
    public SeekBarPreference(Context context) {
        this(context, null, 0);
    }
    public SeekBarPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
    @SuppressLint("ResourceType")
    public SeekBarPreference(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        setLayoutResource(R.layout.preference_seekbar);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SeekBarValuePref);
        try {
            this.minFontValue = obtainStyledAttributes.getInt(1, 0);
            this.maxFontValue = obtainStyledAttributes.getInt(0, 0);
            obtainStyledAttributes.recycle();
            setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
                public boolean onPreferenceChange(Preference preference, Object obj) {
                    return SeekBarPreference.this.lambdanew0(preference, obj);
                }
            });
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }
    public boolean lambdanew0(Preference preference, Object obj) {
        setTextViewValue(String.valueOf(obj));
        return true;
    } 
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        this.mSeekBar = (SeekBar) preferenceViewHolder.findViewById(R.id.seekBar);
        this.mTextView = (TextView) preferenceViewHolder.findViewById(R.id.txtSeekBarProgress);
        this.mSeekBar.setMax(this.maxFontValue);
        int i2 = this.mProgress;
        if (i2 == 0) {
            i2 = this.minFontValue;
        }
        this.mProgress = i2;
        this.mSeekBar.setProgress(i2);
        this.mSeekBar.setOnSeekBarChangeListener(this);
        this.mSeekBar.setEnabled(isEnabled());
        setTextViewValue(String.valueOf(this.mProgress));
    } 
    public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
        int i3 = this.minFontValue;
        if (i2 < i3) {
            seekBar.setProgress(i3);
        }
        syncProgress(seekBar);
    } 
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.getProgress() != this.mProgress) {
            syncProgress(seekBar);
        }
        setSummary(String.valueOf(this.mProgress));
    } 
    protected void onSetInitialValue(boolean z, Object obj) {
        super.onSetInitialValue(z, obj);
        int persistedInt = z ? getPersistedInt(this.mProgress) : ((Integer) obj).intValue();
        setProgress(persistedInt);
        setSummary(String.valueOf(persistedInt));
    } 
    protected Object onGetDefaultValue(TypedArray typedArray, int i2) {
        return Integer.valueOf(this.minFontValue);
    }
    private void setTextViewValue(String str) {
        this.mTextView.setText(str);
    }
    void syncProgress(SeekBar seekBar) {
        int progress = seekBar.getProgress();
        if (progress != this.mProgress) {
            if (callChangeListener(Integer.valueOf(progress))) {
                setProgress(progress, true);
            } else {
                seekBar.setProgress(this.mProgress);
            }
        }
    }
    public void setProgress(int i2) {
        setProgress(i2, true);
    }
    private void setProgress(int i2, boolean z) {
        int i3 = this.maxFontValue;
        if (i2 > i3) {
            i2 = i3;
        }
        int i4 = this.minFontValue;
        if (i2 < i4) {
            i2 = i4;
        }
        if (i2 != this.mProgress) {
            this.mProgress = i2;
            persistInt(i2);
            if (z) {
                notifyChanged();
            }
        }
    }
}
