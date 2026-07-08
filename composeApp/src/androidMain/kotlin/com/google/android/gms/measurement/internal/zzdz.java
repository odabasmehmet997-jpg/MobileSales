package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public interface zzdz extends IInterface {
    @Nullable
    String zzd(zzp zzp) throws RemoteException;

    @Nullable
    List zze(zzp zzp, boolean z) throws RemoteException;

    List zzf(@Nullable String str, @Nullable String str2, zzp zzp) throws RemoteException;

    List zzg(String str, @Nullable String str2, @Nullable String str3) throws RemoteException;

    List zzh(@Nullable String str, @Nullable String str2, boolean z, zzp zzp) throws RemoteException;

    List zzi(String str, @Nullable String str2, @Nullable String str3, boolean z) throws RemoteException;

    void zzj(zzp zzp) throws RemoteException;

    void zzk(zzau zzau, zzp zzp) throws RemoteException;

    void zzl(zzau zzau, String str, @Nullable String str2) throws RemoteException;

    void zzm(zzp zzp) throws RemoteException;

    void zzn(zzab zzab, zzp zzp) throws RemoteException;

    void zzo(zzab zzab) throws RemoteException;

    void zzp(zzp zzp) throws RemoteException;

    void zzq(long j2, @Nullable String str, @Nullable String str2, String str3) throws RemoteException;

    void zzr(Bundle bundle, zzp zzp) throws RemoteException;

    void zzs(zzp zzp) throws RemoteException;

    void zzt(zzku zzku, zzp zzp) throws RemoteException;

    @Nullable
    byte[] zzu(zzau zzau, String str) throws RemoteException;
}
