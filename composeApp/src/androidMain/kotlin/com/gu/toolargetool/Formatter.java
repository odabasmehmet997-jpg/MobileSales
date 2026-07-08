package com.gu.toolargetool;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public interface Formatter {
    String format(Activity activity, Bundle bundle);
    String format(FragmentManager fragmentManager, Fragment fragment, Bundle bundle);
}
