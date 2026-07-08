package com.proje.mobilesales.features.settings.preferences;

import android.annotation.SuppressLint;
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
import java.util.List;
import java.util.ListIterator;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
 
public final class DevicePreference extends Preference {
    private String mDeviceAddress;
    private String mDeviceFullAddress;
    private String mDeviceName;
    private ImageView mImgDeviceClear;
    private TextView mTxtDeviceAddress;
    private TextView mTxtDeviceName;
    @SuppressLint("ResourceType")
    public DevicePreference(final Context context, final AttributeSet attributeSet, final int i2) {
        super(context, attributeSet, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        this.setLayoutResource(R.layout.preference_device);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DevicePref);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
        try {
            mDeviceAddress = obtainStyledAttributes.getString(0);
            mDeviceName = obtainStyledAttributes.getString(1);
        } finally {
            obtainStyledAttributes.recycle();
        }
    } 
    public DevicePreference(final Context context) {
        this(context, null, 0);
        Intrinsics.checkNotNullParameter(context, "context");
    } 
    public DevicePreference(final Context context, final AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }
    public void onBindViewHolder(final PreferenceViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        super.onBindViewHolder(holder);
        final View findViewById = holder.findViewById(R.id.txtDeviceAddress);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        mTxtDeviceAddress = (TextView) findViewById;
        final View findViewById2 = holder.findViewById(R.id.txtDeviceName);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
        mTxtDeviceName = (TextView) findViewById2;
        final View findViewById3 = holder.findViewById(R.id.imgClear);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.ImageView");
        mImgDeviceClear = (ImageView) findViewById3;
        this.setTextViewValue(mTxtDeviceName, mDeviceName);
        this.setTextViewValue(mTxtDeviceAddress, mDeviceAddress);
        final ImageView imageView = mImgDeviceClear;
        Intrinsics.checkNotNull(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {  
            public void onClick(final View view) {
                onBindViewHolderlambda0(DevicePreference.this, view);
            }
        });
        if (TextUtils.isEmpty(mDeviceFullAddress)) {
            final ImageView imageView2 = mImgDeviceClear;
            Intrinsics.checkNotNull(imageView2);
            imageView2.setVisibility(View.GONE);
        } else {
            final ImageView imageView3 = mImgDeviceClear;
            Intrinsics.checkNotNull(imageView3);
            imageView3.setVisibility(View.VISIBLE);
        }
    }
    public static void onBindViewHolderlambda0(final DevicePreference this0, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.clear();
    }
    private void clear() {
        this.setmDeviceName("");
        this.setmDeviceAddress("");
        this.setFullDeviceAddress("");
    }
    private void setTextViewValue(final TextView textView, final String str) {
        if (!TextUtils.isEmpty(str)) {
            Intrinsics.checkNotNull(textView);
            textView.setVisibility(View.VISIBLE);
            textView.setText(str);
        } else {
            Intrinsics.checkNotNull(textView);
            textView.setVisibility(View.GONE);
        }
        final ImageView imageView = mImgDeviceClear;
        Intrinsics.checkNotNull(imageView);
        imageView.setVisibility(View.GONE);
    }  
    protected void onSetInitialValue(final boolean z, final Object obj) {
        final String str;
        final List emptyList;
        super.onSetInitialValue(z, obj);
        if (z) {
            str = this.getPersistedString(null);
        } else {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
            str = (String) obj;
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.setFullDeviceAddress(str);
        Intrinsics.checkNotNull(str);
        final List<String> split = new Regex("\\n").split(str, 0);
        if (!split.isEmpty()) {
            final ListIterator<String> listIterator = split.listIterator(split.size());
            while (listIterator.hasPrevious()) {
                if (!listIterator.previous().isEmpty()) {
                    break;
                }
            }
        }
        emptyList = CollectionsKt.emptyList();
        final String[] strArr = (String[]) emptyList.toArray(new String[0]);
        mDeviceName = strArr[0];
        mDeviceAddress = strArr[1];
    } 
    protected Object onGetDefaultValue(final TypedArray a2, final int i2) {
        Intrinsics.checkNotNullParameter(a2, "a");
        return "";
    }
    public void setmDeviceAddress(final String str) {
        mDeviceAddress = str;
        this.setTextViewValue(mTxtDeviceAddress, str);
        this.notifyChanged();
    }
    public void setmDeviceName(final String str) {
        mDeviceName = str;
        this.setTextViewValue(mTxtDeviceName, str);
        this.notifyChanged();
    }
    public void setFullDeviceAddress(final String str) {
        final ImageView imageView;
        mDeviceFullAddress = str;
        if (!TextUtils.isEmpty(str) && null != (imageView = this.mImgDeviceClear)) {
            Intrinsics.checkNotNull(imageView);
            imageView.setVisibility(View.VISIBLE);
        }
        this.persistString(mDeviceFullAddress);
        this.notifyChanged();
    } 
    protected Parcelable onSaveInstanceState() {
        final Parcelable onSaveInstanceState = super.onSaveInstanceState();
        if (this.isPersistent()) {
            return onSaveInstanceState;
        }
        final SavedState savedState = new SavedState(onSaveInstanceState);
        savedState.setDeviceAddress(mDeviceAddress);
        savedState.setDeviceName(mDeviceName);
        return savedState;
    } 
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        Intrinsics.checkNotNull(parcelable);
        if (!Intrinsics.areEqual(parcelable.getClass(), SavedState.class)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        final SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        mDeviceName = savedState.getDeviceName();
        mDeviceAddress = savedState.getDeviceAddress();
        this.notifyChanged();
    }
    private static final class SavedState extends Preference.BaseSavedState {
        private String deviceAddress;
        private String deviceName;
        public static final Companion Companion = new Companion(null);
        private static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { 
   
            public DevicePreference.SavedState createFromParcel(final Parcel in) {
                Intrinsics.checkNotNullParameter(in, "in");
                return new DevicePreference.SavedState(in);
            } 
            public DevicePreference.SavedState[] newArray(final int i2) {
                return new DevicePreference.SavedState[i2];
            }
        };

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(final String str) {
            deviceName = str;
        }

        public String getDeviceAddress() {
            return deviceAddress;
        }

        public void setDeviceAddress(final String str) {
            deviceAddress = str;
        }

        
        public SavedState(final Parcel source) {
            super(source);
            Intrinsics.checkNotNullParameter(source, "source");
            deviceName = source.readString();
            deviceAddress = source.readString();
        } 
        public void writeToParcel(final Parcel dest, final int i2) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            super.writeToParcel(dest, i2);
            dest.writeString(deviceAddress);
            dest.writeString(deviceName);
        }

        public SavedState(final Parcelable parcelable) {
            super(parcelable);
        }
 
        public static final class Companion {
            public   Companion(final DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public static   void getCREATORannotations() {
            }

            private Companion() {
            }

            public Parcelable.Creator<SavedState> getCREATOR() {
                return CREATOR;
            }
        }
    }
}
