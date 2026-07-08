package com.proje.mobilesales.features.notification.view.list;

import android.view.View;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import kotlin.jvm.functions.Function1;

/* compiled from: R8SyntheticClass */

public record NotificationListAdapterViewHolderExternalSyntheticLambda2(Function1 f0,
                                                                           NotificationModel f1) implements View.OnClickListener {

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NotificationListAdapter.ViewHolder.bindlambda3lambda2(this.f0, this.f1, view);
    }
}
