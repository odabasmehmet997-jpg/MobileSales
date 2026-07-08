package com.proje.mobilesales.core.design;

import android.content.DialogInterface;

public class TigerSendMailExternalSyntheticLambda0(
        Tiger.SendMail f0) implements DialogInterface.OnCancelListener {
    private final Tiger.SendMail f0;
    public TigerSendMailExternalSyntheticLambda0(Tiger.SendMail f0) {
        this.f0 = f0;
    }
    public void onCancel(DialogInterface dialogInterface) {
        f0.lambdaonPreExecute0(dialogInterface);
    }
}
