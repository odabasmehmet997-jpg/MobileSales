package com.proje.mobilesales.core.design;

import android.content.DialogInterface;

public class TigerGetUserReportsExternalSyntheticLambda0(
        Tiger.GetUserReports f0) implements DialogInterface.OnCancelListener {

    private final Tiger.GetUserReports f0;

    public TigerGetUserReportsExternalSyntheticLambda0(Tiger.GetUserReports f0) {
        this.f0 = f0;
    }

    public void onCancel(DialogInterface dialogInterface) {
        f0.lambdaonPreExecute0(dialogInterface);
    }
}
