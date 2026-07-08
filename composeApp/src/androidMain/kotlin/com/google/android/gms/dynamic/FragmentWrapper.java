package com.google.android.gms.dynamic;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

@SuppressLint("NewApi")
@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public final class FragmentWrapper extends IFragmentWrapper.Stub {
    private final Fragment zza;

    private FragmentWrapper(Fragment fragment) {
        this.zza = fragment;
    }

    @KeepForSdk
    @Nullable
    public static FragmentWrapper wrap(@Nullable Fragment fragment) {
        if (null != fragment) {
            return new FragmentWrapper(fragment);
        }
        return null;
    }

    public boolean zzA() {
        return this.zza.isVisible();
    }

    public int zzb() {
        return this.zza.getId();
    }

    public int zzc() {
        return this.zza.getTargetRequestCode();
    }

    @Nullable
    public Bundle zzd() {
        return this.zza.getArguments();
    }

    @Nullable
    public IFragmentWrapper zze() {
        return wrap(this.zza.getParentFragment());
    }

    @Nullable
    public IFragmentWrapper zzf() {
        return wrap(this.zza.getTargetFragment());
    }

    @NonNull
    public IObjectWrapper zzg() {
        return ObjectWrapper.wrap(this.zza.getActivity());
    }

    @NonNull
    public IObjectWrapper zzh() {
        return ObjectWrapper.wrap(this.zza.getResources());
    }

    @NonNull
    public IObjectWrapper zzi() {
        return ObjectWrapper.wrap(this.zza.getView());
    }

    @Nullable
    public String zzj() {
        return this.zza.getTag();
    }

    public void zzk(@NonNull IObjectWrapper iObjectWrapper) {
        View view = ObjectWrapper.unwrap(iObjectWrapper);
        Preconditions.checkNotNull(view);
        this.zza.registerForContextMenu(view);
    }

    public void zzl(boolean z) {
        this.zza.setHasOptionsMenu(z);
    }

    public void zzm(boolean z) {
        this.zza.setMenuVisibility(z);
    }

    public void zzn(boolean z) {
        this.zza.setRetainInstance(z);
    }

    public void zzo(boolean z) {
        this.zza.setUserVisibleHint(z);
    }

    public void zzp(@NonNull Intent intent) {
        this.zza.startActivity(intent);
    }

    public void zzq(@NonNull Intent intent, int i2) {
        this.zza.startActivityForResult(intent, i2);
    }

    public void zzr(@NonNull IObjectWrapper iObjectWrapper) {
        View view = ObjectWrapper.unwrap(iObjectWrapper);
        Preconditions.checkNotNull(view);
        this.zza.unregisterForContextMenu(view);
    }

    public boolean zzs() {
        return this.zza.getRetainInstance();
    }

    public boolean zzt() {
        return this.zza.getUserVisibleHint();
    }

    public boolean zzu() {
        return this.zza.isAdded();
    }

    public boolean zzv() {
        return this.zza.isDetached();
    }

    public boolean zzw() {
        return this.zza.isHidden();
    }

    public boolean zzx() {
        return this.zza.isInLayout();
    }

    public boolean zzy() {
        return this.zza.isRemoving();
    }

    public boolean zzz() {
        return this.zza.isResumed();
    }
}
