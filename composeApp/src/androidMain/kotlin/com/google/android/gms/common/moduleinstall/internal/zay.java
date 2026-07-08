package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.moduleinstall.ModuleInstallClient;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zay extends GoogleApi implements ModuleInstallClient {
    public static final int r8clinit = 0;
    private static final Api.ClientKey zac;
    private static final Api.AbstractClientBuilder zad;
    private static final Api zae;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zac = clientKey;
        zaq zaq = new zaq();
        zad = zaq;
        zae = new Api("ModuleInstall.API", zaq, clientKey);
    }
}
