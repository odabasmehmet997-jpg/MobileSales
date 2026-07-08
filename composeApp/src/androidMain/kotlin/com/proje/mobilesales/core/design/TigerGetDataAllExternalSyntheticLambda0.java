package com.proje.mobilesales.core.design;


import android.content.DialogInterface;

public class TigerGetDataAllExternalSyntheticLambda0(
        Tiger.GetDataAll f0) implements DialogInterface.OnCancelListener {
    private final Netsis.GetAllData f0;
    public TigerGetDataAllExternalSyntheticLambda0(Netsis.GetAllData f0) {
        this.f0 = f0;
    }
    public void onCancel(DialogInterface dialogInterface) {
        f0.lambdaonPreExecute0(dialogInterface);
    }
}
