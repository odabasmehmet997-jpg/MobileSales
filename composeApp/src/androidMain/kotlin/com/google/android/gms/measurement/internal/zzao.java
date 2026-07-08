package com.google.android.gms.measurement.internal;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import androidx.annotation.WorkerThread;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzao extends zzgn {
    private long zza;
    private String zzb;
    private AccountManager zzc;
    private Boolean zzd;
    private long zze;

    zzao(zzft zzft) {
        super(zzft);
    }

    
    @WorkerThread
    public long zza() {
        zzg();
        return this.zze;
    }

    public long zzb() {
        zzu();
        return this.zza;
    }

    public String zzc() {
        zzu();
        return this.zzb;
    }

    
    @WorkerThread
    public void zzd() {
        zzg();
        this.zzd = null;
        this.zze = 0;
    }

    
    @WorkerThread
    public boolean zze() {
        zzg();
        long currentTimeMillis = this.zzs.zzav().currentTimeMillis();
        if (86400000 < currentTimeMillis - zze) {
            this.zzd = null;
        }
        Boolean bool = this.zzd;
        if (null != bool) {
            return bool.booleanValue();
        }
        if (0 != ContextCompat.checkSelfPermission(zzs.zzau(), "android.permission.GET_ACCOUNTS")) {
            this.zzs.zzay().zzm().zza("Permission error checking for dasher/unicorn accounts");
            this.zze = currentTimeMillis;
            this.zzd = Boolean.FALSE;
            return false;
        }
        if (null == zzc) {
            this.zzc = AccountManager.get(this.zzs.zzau());
        }
        try {
            Account[] result = this.zzc.getAccountsByTypeAndFeatures("com.google", new String[]{"service_HOSTED"}, null, null).getResult();
            if (null == result || 0 >= result.length) {
                Account[] result2 = this.zzc.getAccountsByTypeAndFeatures("com.google", new String[]{"service_uca"}, null, null).getResult();
                if (null != result2 && 0 < result2.length) {
                    this.zzd = Boolean.TRUE;
                    this.zze = currentTimeMillis;
                    return true;
                }
                this.zze = currentTimeMillis;
                this.zzd = Boolean.FALSE;
                return false;
            }
            this.zzd = Boolean.TRUE;
            this.zze = currentTimeMillis;
            return true;
        } catch (AuthenticatorException e2) {
            e = e2;
            this.zzs.zzay().zzh().zzb("Exception checking account types", e);
            this.zze = currentTimeMillis;
            this.zzd = Boolean.FALSE;
            return false;
        } catch (IOException e3) {
            e = e3;
            this.zzs.zzay().zzh().zzb("Exception checking account types", e);
            this.zze = currentTimeMillis;
            this.zzd = Boolean.FALSE;
            return false;
        } catch (OperationCanceledException e4) {
            e = e4;
            this.zzs.zzay().zzh().zzb("Exception checking account types", e);
            this.zze = currentTimeMillis;
            this.zzd = Boolean.FALSE;
            return false;
        }
    }

    
    public boolean zzf() {
        Calendar instance = Calendar.getInstance();
        this.zza = TimeUnit.MINUTES.convert(instance.get(15) + instance.get(16), TimeUnit.MILLISECONDS);
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        Locale locale2 = Locale.ENGLISH;
        String lowerCase = language.toLowerCase(locale2);
        String lowerCase2 = locale.getCountry().toLowerCase(locale2);
        final String sb = lowerCase +
                "-" +
                lowerCase2;
        this.zzb = sb;
        return false;
    }
}
