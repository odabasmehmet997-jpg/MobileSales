package com.gu.toolargetool;
import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import kotlin.jvm.internal.Intrinsics;

public final class DefaultFormatter implements Formatter {
    public String format(final Activity activity, final Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        return activity.getClass().getSimpleName() + ".onSaveInstanceState wrote: " + TooLargeTool.bundleBreakdown(bundle);
    }

    public String format(final FragmentManager fragmentManager, final Fragment fragment, final Bundle bundle) {
        Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        final String str = fragment.getClass().getSimpleName() + ".onSaveInstanceState wrote: " + TooLargeTool.bundleBreakdown(bundle);
        final Bundle arguments = fragment.getArguments();
        if (null == arguments) {
            return str;
        }
        return str + "\n* fragment arguments = " + TooLargeTool.bundleBreakdown(arguments);
    }
}
