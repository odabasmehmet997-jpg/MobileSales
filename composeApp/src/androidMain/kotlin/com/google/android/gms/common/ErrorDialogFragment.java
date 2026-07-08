package com.google.android.gms.common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;

public class ErrorDialogFragment extends DialogFragment {
    private Dialog zaa;
    private DialogInterface.OnCancelListener zab;
    @Nullable
    private Dialog zac;

    public void onCancel(@NonNull final DialogInterface dialogInterface) {
        final DialogInterface.OnCancelListener onCancelListener = zab;
        if (null != onCancelListener) {
            onCancelListener.onCancel(dialogInterface);
        }
    }

    @NonNull
    public Dialog onCreateDialog(@Nullable final Bundle bundle) {
        final Dialog dialog = zaa;
        if (null != dialog) {
            return dialog;
        }
        this.setShowsDialog(false);
        if (null == this.zac) {
            zac = new AlertDialog.Builder(Preconditions.checkNotNull(this.getActivity())).create();
        }
        return zac;
    }

    public void show(@NonNull final FragmentManager fragmentManager, @Nullable final String str) {
        super.show(fragmentManager, str);
    }

    @NonNull
    public static ErrorDialogFragment newInstance(@NonNull final Dialog dialog, @Nullable final DialogInterface.OnCancelListener onCancelListener) {
        final ErrorDialogFragment errorDialogFragment = new ErrorDialogFragment();
        final Dialog dialog2 = Preconditions.checkNotNull(dialog, "Cannot display null dialog");
        dialog2.setOnCancelListener(null);
        dialog2.setOnDismissListener(null);
        errorDialogFragment.zaa = dialog2;
        if (null != onCancelListener) {
            errorDialogFragment.zab = onCancelListener;
        }
        return errorDialogFragment;
    }
}
