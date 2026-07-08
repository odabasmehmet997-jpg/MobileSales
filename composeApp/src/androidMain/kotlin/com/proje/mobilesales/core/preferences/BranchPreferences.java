package com.proje.mobilesales.core.preferences;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.model.NetsisBranch;

public class BranchPreferences extends Preference {
    private String mBranchName;
    private String mEnterpriseName;
    private String mFullBranchName;
    private ImageView mImgClear;
    private NetsisBranch mNetsisBranch;
    private TextView mTxtBranch;
    private TextView mTxtEnterprise;
    public BranchPreferences(Context context) {
        this(context, null, 0);
    }
    public BranchPreferences(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
    public BranchPreferences(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        setLayoutResource(R.layout.preference_device);
    }
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        this.mTxtBranch = (TextView) preferenceViewHolder.findViewById(R.id.txtDeviceAddress);
        this.mTxtEnterprise = (TextView) preferenceViewHolder.findViewById(R.id.txtDeviceName);
        ImageView imageView = (ImageView) preferenceViewHolder.findViewById(R.id.imgClear);
        this.mImgClear = imageView;
        imageView.setVisibility(View.GONE);
        setTextViewValue(this.mTxtBranch, this.mBranchName);
        setTextViewValue(this.mTxtEnterprise, this.mEnterpriseName);
    }
    public void setBranchName(String str) {
        this.mBranchName = str;
        setTextViewValue(this.mTxtBranch, str);
        try {
            notifyChanged();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    public void setEnterpriseName(String str) {
        this.mEnterpriseName = str;
        setTextViewValue(this.mTxtEnterprise, str);
        try {
            notifyChanged();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    private void setTextViewValue(TextView textView, String str) {
        if (!TextUtils.isEmpty(str)) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(str);
        } else {
            textView.setVisibility(View.GONE);
        }
    }
    public void setNetsisBranch(NetsisBranch netsisBranch) {
        this.mNetsisBranch = netsisBranch;
        setBranchName(netsisBranch.getBranchCodeAndName());
        setEnterpriseName(this.mNetsisBranch.getEnterpriseCodeAndName());
        setFullBranchName(this.mNetsisBranch.toString());
    }
    protected void onSetInitialValue(boolean z, Object obj) {
        super.onSetInitialValue(z, obj);
        String persistedString = z ? getPersistedString(null) : (String) obj;
        if (TextUtils.isEmpty(persistedString)) {
            return;
        }
        setFullBranchName(persistedString);
        String[] split = persistedString.split("\\n");
        this.mEnterpriseName = split[0];
        if (split.length > 1) {
            this.mBranchName = split[1];
        }
    }
    public void setFullBranchName(String str) {
        ImageView imageView;
        this.mFullBranchName = str;
        if (!TextUtils.isEmpty(str) && (imageView = this.mImgClear) != null) {
            imageView.setVisibility(View.VISIBLE);
        }
        persistString(this.mFullBranchName);
        try {
            notifyChanged();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    public void clearSelection() {
        ImageView imageView;
        if (this.mTxtBranch != null) {
            setBranchName("");
        }
        if (this.mTxtEnterprise != null) {
            setEnterpriseName("");
        }
        this.mFullBranchName = "";
        if (!TextUtils.isEmpty("") && (imageView = this.mImgClear) != null) {
            imageView.setVisibility(View.VISIBLE);
        }
        persistString(this.mFullBranchName);
        try {
            notifyChanged();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    protected Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        if (isPersistent()) {
            return onSaveInstanceState;
        }
        SavedState savedState = new SavedState(onSaveInstanceState);
        savedState.netsisBranch = this.mNetsisBranch;
        savedState.branchName = this.mBranchName;
        savedState.enterPriseName = this.mEnterpriseName;
        return savedState;
    }
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!parcelable.getClass().equals(SavedState.class)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mNetsisBranch = savedState.netsisBranch;
        this.mBranchName = savedState.branchName;
        this.mEnterpriseName = savedState.enterPriseName;
        notifyChanged();
    }
    private static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.proje.mobilesales.core.preferences.BranchPreferences.SavedState.1
            
            
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            
            
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };
        String branchName;
        String enterPriseName;
        NetsisBranch netsisBranch;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.netsisBranch = parcel.readParcelable(NetsisBranch.class.getClassLoader());
            this.branchName = parcel.readString();
            this.enterPriseName = parcel.readString();
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeParcelable(this.netsisBranch, i2);
            parcel.writeString(this.branchName);
            parcel.writeString(this.enterPriseName);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }
}
