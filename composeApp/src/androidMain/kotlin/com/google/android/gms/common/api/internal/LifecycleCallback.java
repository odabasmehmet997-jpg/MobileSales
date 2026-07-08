package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Keep;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
 
public class LifecycleCallback {
 
    protected final LifecycleFragment mLifecycleFragment; 
    protected LifecycleCallback(final LifecycleFragment lifecycleFragment) {
        mLifecycleFragment = lifecycleFragment;
    } 
    private static LifecycleFragment getChimeraLifecycleFragmentImpl(final LifecycleActivity lifecycleActivity) {
        throw new IllegalStateException("Method not available in SDK.");
    } 
    public static LifecycleFragment getFragment(final Activity activity) {
        return LifecycleCallback.getFragment(new LifecycleActivity(activity));
    } 
    public void dump(final String str, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] strArr) {
    } 
    public Activity getActivity() {
        final Activity lifecycleActivity = mLifecycleFragment.getLifecycleActivity();
        Preconditions.checkNotNull(lifecycleActivity);
        return lifecycleActivity;
    } 
    public void onActivityResult(final int i2, final int i3, final Intent intent) {
    } 
    public void onCreate(@Nullable final Bundle bundle) {
    } 
    public void onDestroy() {
    } 
    public void onResume() {
    } 
    public void onSaveInstanceState(final Bundle bundle) {
    } 
    public void onStart() {
    } 
    public void onStop() {
    } 
    protected static LifecycleFragment getFragment(final LifecycleActivity lifecycleActivity) {
        if (lifecycleActivity.zzd()) {
            return zzd.zzc(lifecycleActivity.zzb());
        }
        if (lifecycleActivity.zzc()) {
            return zzb.zzc(lifecycleActivity.zza());
        }
        throw new IllegalArgumentException("Can't get fragment for unexpected activity.");
    }
}
