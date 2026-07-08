package com.google.android.gms.measurement.internal;

import android.net.Uri;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 * @param zzd synthetic
 * @param zze synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzhv(zzhx zze, boolean zza, Uri zzb, String zzc, String zzd) implements Runnable {

    /*  WARNING: Removed duplicated region for block: B:34:0x00a3 A[SYNTHETIC, Splitter:B:34:0x00a3] */
    /*  WARNING: Removed duplicated region for block: B:46:0x00f9 A[Catch:{ RuntimeException -> 0x006c }] */
    /*  WARNING: Removed duplicated region for block: B:47:0x00fb A[Catch:{ RuntimeException -> 0x006c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r17 = this;
            r1 = r17
            com.google.android.gms.measurement.internal.zzhx r2 = r1.zze
            boolean r0 = r1.zza
            android.net.Uri r3 = r1.zzb
            java.lang.String r4 = r1.zzc
            java.lang.String r5 = r1.zzd
            com.google.android.gms.measurement.internal.zzhy r6 = r2.zza
            r6.zzg()
            com.google.android.gms.measurement.internal.zzhy r6 = r2.zza     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzft r6 = r6.zzs     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzky r6 = r6.zzv()     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.internal.measurement.zzns.zzc()     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzhy r7 = r2.zza     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzft r7 = r7.zzs     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzaf r7 = r7.zzf()     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzdv r8 = com.google.android.gms.measurement.internal.zzdw.zzav     // Catch:{ RuntimeException -> 0x006c }
            r9 = 0
            boolean r7 = r7.zzs(r9, r8)     // Catch:{ RuntimeException -> 0x006c }
            boolean r10 = android.text.TextUtils.isEmpty(r5)     // Catch:{ RuntimeException -> 0x006c }
            java.lang.String r11 = "_cis"
            java.lang.String r12 = "Activity created with data 'referrer' without required params"
            java.lang.String r13 = "utm_medium"
            java.lang.String r14 = "utm_source"
            java.lang.String r15 = "utm_campaign"
            java.lang.String r9 = "gclid"
            if (r10 == 0) goto L_0x003f
        L_0x003d:
            r1 = 0
            goto L_0x009f
        L_0x003f:
            boolean r10 = r5.contains(r9)     // Catch:{ RuntimeException -> 0x006c }
            if (r10 != 0) goto L_0x007d
            boolean r10 = r5.contains(r15)     // Catch:{ RuntimeException -> 0x006c }
            if (r10 != 0) goto L_0x007d
            boolean r10 = r5.contains(r14)     // Catch:{ RuntimeException -> 0x006c }
            if (r10 != 0) goto L_0x007d
            boolean r10 = r5.contains(r13)     // Catch:{ RuntimeException -> 0x006c }
            if (r10 != 0) goto L_0x007d
            if (r7 == 0) goto L_0x006f
            java.lang.String r7 = "utm_id"
            boolean r7 = r5.contains(r7)     // Catch:{ RuntimeException -> 0x006c }
            if (r7 != 0) goto L_0x006a
            java.lang.String r7 = "dclid"
            boolean r7 = r5.contains(r7)     // Catch:{ RuntimeException -> 0x006c }
            if (r7 != 0) goto L_0x006a
            goto L_0x006f
        L_0x006a:
            r7 = 1
            goto L_0x007d
        L_0x006c:
            r0 = move-exception
            goto L_0x018e
        L_0x006f:
            com.google.android.gms.measurement.internal.zzft r6 = r6.zzs     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzej r6 = r6.zzay()     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzeh r6 = r6.zzc()     // Catch:{ RuntimeException -> 0x006c }
            r6.zza(r12)     // Catch:{ RuntimeException -> 0x006c }
            goto L_0x003d
        L_0x007d:
            java.lang.String r10 = "https://google.com/search?"
            int r16 = r5.length()     // Catch:{ RuntimeException -> 0x006c }
            if (r16 == 0) goto L_0x008a
            java.lang.String r10 = r10.concat(r5)     // Catch:{ RuntimeException -> 0x006c }
            goto L_0x0090
        L_0x008a:
            java.lang.String r1 = new java.lang.String     // Catch:{ RuntimeException -> 0x006c }
            r1.<init>(r10)     // Catch:{ RuntimeException -> 0x006c }
            r10 = r1
        L_0x0090:
            android.net.Uri r1 = android.net.Uri.parse(r10)     // Catch:{ RuntimeException -> 0x006c }
            android.os.Bundle r1 = r6.zzs(r1, r7)     // Catch:{ RuntimeException -> 0x006c }
            if (r1 == 0) goto L_0x009f
            java.lang.String r6 = "referrer"
            r1.putString(r11, r6)     // Catch:{ RuntimeException -> 0x006c }
        L_0x009f:
            java.lang.String r6 = "_cmp"
            if (r0 == 0) goto L_0x00f3
            com.google.android.gms.measurement.internal.zzhy r0 = r2.zza     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzft r0 = r0.zzs     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzky r0 = r0.zzv()     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.internal.measurement.zzns.zzc()     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzhy r7 = r2.zza     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzft r7 = r7.zzs     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzaf r7 = r7.zzf()     // Catch:{ RuntimeException -> 0x006c }
            r10 = 0
            boolean r7 = r7.zzs(r10, r8)     // Catch:{ RuntimeException -> 0x006c }
            android.os.Bundle r0 = r0.zzs(r3, r7)     // Catch:{ RuntimeException -> 0x006c }
            if (r0 == 0) goto L_0x00f3
            java.lang.String r3 = "intent"
            r0.putString(r11, r3)     // Catch:{ RuntimeException -> 0x006c }
            boolean r3 = r0.containsKey(r9)     // Catch:{ RuntimeException -> 0x006c }
            if (r3 != 0) goto L_0x00e7
            if (r1 == 0) goto L_0x00e7
            boolean r3 = r1.containsKey(r9)     // Catch:{ RuntimeException -> 0x006c }
            if (r3 == 0) goto L_0x00e7
            java.lang.String r3 = r1.getString(r9)     // Catch:{ RuntimeException -> 0x006c }
            java.lang.Object[] r3 = new java.lang.Object[]{r3}     // Catch:{ RuntimeException -> 0x006c }
            java.lang.String r7 = "_cer"
            java.lang.String r8 = "gclid=%s"
            java.lang.String r3 = java.lang.String.format(r8, r3)     // Catch:{ RuntimeException -> 0x006c }
            r0.putString(r7, r3)     // Catch:{ RuntimeException -> 0x006c }
        L_0x00e7:
            com.google.android.gms.measurement.internal.zzhy r3 = r2.zza     // Catch:{ RuntimeException -> 0x006c }
            r3.zzG(r4, r6, r0)     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzhy r3 = r2.zza     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzr r3 = r3.zzb     // Catch:{ RuntimeException -> 0x006c }
            r3.zza(r4, r0)     // Catch:{ RuntimeException -> 0x006c }
        L_0x00f3:
            boolean r0 = android.text.TextUtils.isEmpty(r5)     // Catch:{ RuntimeException -> 0x006c }
            if (r0 == 0) goto L_0x00fb
            goto L_0x017d
        L_0x00fb:
            com.google.android.gms.measurement.internal.zzhy r0 = r2.zza     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzft r0 = r0.zzs     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzay()     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzc()     // Catch:{ RuntimeException -> 0x006c }
            java.lang.String r3 = "Activity created with referrer"
            r0.zzb(r3, r5)     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzhy r0 = r2.zza     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzft r0 = r0.zzs     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzaf r0 = r0.zzf()     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzdv r3 = com.google.android.gms.measurement.internal.zzdw.zzY     // Catch:{ RuntimeException -> 0x006c }
            r7 = 0
            boolean r0 = r0.zzs(r7, r3)     // Catch:{ RuntimeException -> 0x006c }
            java.lang.String r3 = "_ldl"
            java.lang.String r7 = "auto"
            if (r0 == 0) goto L_0x0149
            if (r1 == 0) goto L_0x0130
            com.google.android.gms.measurement.internal.zzhy r0 = r2.zza     // Catch:{ RuntimeException -> 0x006c }
            r0.zzG(r4, r6, r1)     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzhy r0 = r2.zza     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzr r0 = r0.zzb     // Catch:{ RuntimeException -> 0x006c }
            r0.zza(r4, r1)     // Catch:{ RuntimeException -> 0x006c }
            goto L_0x0141
        L_0x0130:
            com.google.android.gms.measurement.internal.zzhy r0 = r2.zza     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzft r0 = r0.zzs     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzay()     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzc()     // Catch:{ RuntimeException -> 0x006c }
            java.lang.String r1 = "Referrer does not contain valid parameters"
            r0.zzb(r1, r5)     // Catch:{ RuntimeException -> 0x006c }
        L_0x0141:
            com.google.android.gms.measurement.internal.zzhy r0 = r2.zza     // Catch:{ RuntimeException -> 0x006c }
            r1 = 0
            r4 = 1
            r0.zzX(r7, r3, r1, r4)     // Catch:{ RuntimeException -> 0x006c }
            return
        L_0x0149:
            boolean r0 = r5.contains(r9)     // Catch:{ RuntimeException -> 0x006c }
            if (r0 == 0) goto L_0x017e
            boolean r0 = r5.contains(r15)     // Catch:{ RuntimeException -> 0x006c }
            if (r0 != 0) goto L_0x0171
            boolean r0 = r5.contains(r14)     // Catch:{ RuntimeException -> 0x006c }
            if (r0 != 0) goto L_0x0171
            boolean r0 = r5.contains(r13)     // Catch:{ RuntimeException -> 0x006c }
            if (r0 != 0) goto L_0x0171
            java.lang.String r0 = "utm_term"
            boolean r0 = r5.contains(r0)     // Catch:{ RuntimeException -> 0x006c }
            if (r0 != 0) goto L_0x0171
            java.lang.String r0 = "utm_content"
            boolean r0 = r5.contains(r0)     // Catch:{ RuntimeException -> 0x006c }
            if (r0 == 0) goto L_0x017e
        L_0x0171:
            boolean r0 = android.text.TextUtils.isEmpty(r5)     // Catch:{ RuntimeException -> 0x006c }
            if (r0 != 0) goto L_0x017d
            com.google.android.gms.measurement.internal.zzhy r0 = r2.zza     // Catch:{ RuntimeException -> 0x006c }
            r1 = 1
            r0.zzX(r7, r3, r5, r1)     // Catch:{ RuntimeException -> 0x006c }
        L_0x017d:
            return
        L_0x017e:
            com.google.android.gms.measurement.internal.zzhy r0 = r2.zza     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzft r0 = r0.zzs     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzej r0 = r0.zzay()     // Catch:{ RuntimeException -> 0x006c }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzc()     // Catch:{ RuntimeException -> 0x006c }
            r0.zza(r12)     // Catch:{ RuntimeException -> 0x006c }
            return
        L_0x018e:
            com.google.android.gms.measurement.internal.zzhy r1 = r2.zza
            com.google.android.gms.measurement.internal.zzft r1 = r1.zzs
            com.google.android.gms.measurement.internal.zzej r1 = r1.zzay()
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzd()
            java.lang.String r2 = "Throwable caught in handleReferrerForOnActivityCreated"
            r1.zzb(r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzhv.run():void");
    }
}
