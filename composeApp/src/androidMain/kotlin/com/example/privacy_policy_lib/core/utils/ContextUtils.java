package com.example.privacy_policy_lib.core.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import androidx.annotation.StringRes;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ContextUtils {
    public static final Companion Companion = new Companion(null);
    @SuppressLint({"StaticFieldLeak"})
    private static Context mContext;
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
        private Companion() {
        }
        public Context getMContext() {
            return ContextUtils.mContext;
        }
        public void setMContext(Context context) {
            ContextUtils.mContext = context;
        }
        public Context getmContext() {
            return getMContext();
        }
        public void setmContext(Context context) {
            Intrinsics.checkNotNullParameter(context, "mContext");
            setMContext(context);
        }
        public String getStringResource(@StringRes int i2) {
            if (i2 == 0) {
                return "";
            }
            try {
                Context context = getmContext();
                return String.valueOf(context != null ? context.getString(i2) : null);
            } catch (Exception e2) {
                Log.e("ContentValues", "getStringResource: ", e2);
                return "";
            }
        }
    }
}
