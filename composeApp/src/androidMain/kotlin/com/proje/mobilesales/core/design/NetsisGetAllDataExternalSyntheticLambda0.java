package com.proje.mobilesales.core.design;

import android.content.DialogInterface;


public record NetsisGetAllDataExternalSyntheticLambda0(
        Netsis.GetAllData f0) implements DialogInterface.OnCancelListener {
    public void onCancel(DialogInterface dialogInterface) {
        this.f0.lambdaonPreExecute0(dialogInterface);
    }
}
