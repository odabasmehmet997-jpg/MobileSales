package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.UserMenu;
import com.proje.mobilesales.core.utils.StringUtils;
import java.util.HashMap;

public class UserMenuRights implements Parcelable {
    public static final Parcelable.Creator<UserMenuRights> CREATOR = new Parcelable.Creator<UserMenuRights>() {
        public UserMenuRights createFromParcel(final Parcel parcel) {
            return new UserMenuRights(parcel);
        }
        public UserMenuRights[] newArray(final int i) {
            return new UserMenuRights[i];
        }
    };
    private static final String TAG = "UserMenuRights";
    private final int mMenuItemCount;
    private final HashMap<Integer, Boolean> mMenuRightList;
    private boolean mPro;
    public int describeContents() {
        return 0;
    }
    public UserMenuRights() {
        mMenuRightList = new HashMap<>();
        mMenuItemCount = UserMenu.values().length;
        this.checkMenuList();
    }
    private void checkMenuList() {
        if (0 == this.mMenuRightList.size()) {
            this.initMenuList(true);
        }
    }
    private void initMenuList(final boolean z) {
        for (int i = 0; i < mMenuItemCount; i++) {
            this.putMenuValue(i, z);
        }
    }
    private void putMenuValue(final int i, final boolean z) {
        mMenuRightList.put(Integer.valueOf(i), Boolean.valueOf(z));
    }
    private boolean getMenuValue(final int i) {
        final HashMap<Integer, Boolean> hashMap;
        return -1 == i || null == (hashMap = this.mMenuRightList) || hashMap.get(Integer.valueOf(i)).booleanValue();
    }
    public boolean getMenuValue(final int i, final boolean z) {
        final boolean menuValue = this.getMenuValue(i);
        if (z) {
            return menuValue && mPro;
        }
        return menuValue;
    }
    public void setMenuRights(final ErpType erpType, final String str, final boolean z) {
        mPro = z;
        try {
            final String[] split = StringUtils.split(str, ",");
            if (null != split) {
                this.initMenuList(false);
                this.putMenuValue(mMenuItemCount, true);
                for (final String str2 : split) {
                    this.putMenuValue(StringUtils.convertStringToInt(str2.trim()), true);
                }
                return;
            }
            this.initMenuList(true);
        } catch (final Exception e) {
            Log.e(UserMenuRights.TAG, "setMenuRights: ", e);
        }
    }
    public void writeToParcel(final Parcel parcel, final int i) {
        parcel.writeSerializable(mMenuRightList);
        parcel.writeInt(mMenuItemCount);
        parcel.writeByte(mPro ? (byte) 1 : 0);
    }
    protected UserMenuRights(final Parcel parcel) {
        mMenuRightList = (HashMap) parcel.readSerializable();
        mMenuItemCount = parcel.readInt();
        mPro = 0 != parcel.readByte();
    }
}