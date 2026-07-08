package com.google.android.gms.signin;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ShowFirstParty;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public enum zad {
    ;
    public static final Api.ClientKey zaa;
    @ShowFirstParty
    public static final Api.ClientKey zab;
    public static final Api.AbstractClientBuilder zac;
    static final Api.AbstractClientBuilder zad;
    public static final Scope zae = new Scope("profile");
    public static final Scope zaf = new Scope(NotificationCompat.CATEGORY_EMAIL);
    public static final Api zag;
    public static final Api zah;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zaa = clientKey;
        Api.ClientKey clientKey2 = new Api.ClientKey();
        zab = clientKey2;
        zaa zaa2 = new zaa();
        zac = zaa2;
        zab zab2 = new zab();
        zad = zab2;
        zag = new Api("SignIn.API", zaa2, clientKey);
        zah = new Api("SignIn.INTERNAL_API", zab2, clientKey2);
    }
}
