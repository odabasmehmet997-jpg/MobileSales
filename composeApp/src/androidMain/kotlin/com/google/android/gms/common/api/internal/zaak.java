package com.google.android.gms.common.api.internal;

final class zaak implements Runnable {
    final zaaw zaa;

    zaak(zaaw zaawVar) {
        this.zaa = zaawVar;
    }

    public   void run() {
        zaaw zaawVar = this.zaa;
        zaawVar.zad.cancelAvailabilityErrorNotifications(zaawVar.zac);
    }
}
