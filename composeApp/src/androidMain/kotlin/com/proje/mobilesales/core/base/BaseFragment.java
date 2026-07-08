package com.proje.mobilesales.core.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.proje.mobilesales.core.ActivityModule;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.interfaces.di.ActivityComponent;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.MenuTintDelegate;

import java.util.Objects;

public abstract class BaseFragment extends Fragment {
    private ActivityComponent mActivityComponent;
    private boolean mAttached;
    protected final MenuTintDelegate mMenuTintDelegate = new MenuTintDelegate();
    protected void createOptionsMenu(Menu menu, MenuInflater menuInflater) {
    }
    protected void prepareOptionsMenu(Menu menu) {
    }
    public ActivityComponent getActivityComponent() {
        return this.mActivityComponent;
    }
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mAttached = true;
    }
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d("***", getClass().getName());
        this.mActivityComponent = ((MobileSales) getActivity().getApplication()).getAppComponent().plus(new ActivityModule(getContext()));
        ContextUtils.setmContext(getContext());
        if (getActivity() != null) {
            ContextUtils.setmActivity(getActivity());
        }
    }
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mMenuTintDelegate.onActivityCreated(getActivity());
    }
    public final void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (isAttached()) {
            createOptionsMenu(menu, menuInflater);
            this.mMenuTintDelegate.onOptionsMenuCreated(menu);
        }
    }
    public final void onPrepareOptionsMenu(Menu menu) {
        if (isAttached()) {
            prepareOptionsMenu(menu);
        }
    }
    public void onDetach() {
        super.onDetach();
        this.mAttached = false;
    }
    public void onDestroy() {
        super.onDestroy();
    }
    public boolean isAttached() {
        return this.mAttached;
    }
    public void hideKeyboardFrom() {
        try {
            View currentFocus = getActivity() == null ? null : getActivity().getCurrentFocus();
            if (currentFocus != null) {
                ((InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
            }
        } catch (Exception e2) {
            Log.e("BaseFragment", "hideKeyBoardFrom", e2);
        }
    }
}
