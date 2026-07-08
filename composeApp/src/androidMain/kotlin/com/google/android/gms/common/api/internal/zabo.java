package com.google.android.gms.common.api.internal;
final class zabo implements Runnable {
    final  zabp zaa;

    zabo(zabp zabpVar) {
        this.zaa = zabpVar;
    }

    public void run() {
        zabq zabqVar = this.zaa.zaa;
        zabqVar.zac.disconnect(zabqVar.zac.getClass().getName().concat(" disconnecting because it was signed out."));
    }
}
