package com.google.android.gms.tagmanager;

import android.util.Log;
import com.google.android.gms.internal.gtm.zzrc;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
record zzeg(zzeh zzb, zzrc zza) implements Runnable {

    public void run() {
        zzrc zzrc = this.zza;
        File zze = this.zzb.zze();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zze);
            try {
                zzrc.zzT(fileOutputStream);
                try {
                    fileOutputStream.close();
                } catch (IOException unused) {
                    Log.w("GoogleTagManager", "error closing stream for writing resource to disk");
                }
            } catch (IOException unused2) {
                Log.w("GoogleTagManager", "Error writing resource to disk. Removing resource from disk.");
                zze.delete();
                try {
                    fileOutputStream.close();
                } catch (IOException unused3) {
                    Log.w("GoogleTagManager", "error closing stream for writing resource to disk");
                }
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused4) {
                    Log.w("GoogleTagManager", "error closing stream for writing resource to disk");
                }
                throw th;
            }
        } catch (FileNotFoundException unused5) {
            Log.e("GoogleTagManager", "Error opening resource file for writing");
        }
    }
}
