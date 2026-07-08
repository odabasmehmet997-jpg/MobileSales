package com.proje.mobilesales.core.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.proje.mobilesales.R;

public class ScreenControl {
    private final Activity activity;
    public boolean isFullScreen = false;

    public ScreenControl(  Activity activity) {
        this.activity = activity;
    }
    @SuppressLint("WrongConstant")
    public void setScreenLock(boolean z) {
        if (z) {
            Display defaultDisplay = this.activity.getWindowManager().getDefaultDisplay();
            int i2 = this.activity.getResources().getConfiguration().orientation;
            int rotation = defaultDisplay.getRotation();
            if (((rotation == 2 || rotation == 0) && i2 == 1) || !(((rotation == 2 || rotation == 0) && i2 == 2) || ((rotation == 1 || rotation == 3) && i2 == 1))) {
                if (rotation == 0) {
                    this.activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    return;
                }
                if (rotation == 1) {
                    this.activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    return;
                } else if (rotation == 2) {
                    this.activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
                    return;
                } else {
                    if (rotation == 3) {
                        this.activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                        return;
                    }
                    throw new AssertionError();
                }
            }
            if (rotation == 0) {
                this.activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                return;
            }
            if (rotation == 1) {
                this.activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
                return;
            } else if (rotation == 2) {
                this.activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                return;
            } else {
                if (rotation == 3) {
                    this.activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    return;
                }
                throw new AssertionError();
            }
        }
        this.activity.setRequestedOrientation(-1);
    }

    public int getTextViewWidth(TextView textView, String str) {
        Rect rect = new Rect();
        textView.getPaint().getTextBounds(str, 0, str.length(), rect);
        return rect.width();
    }

    public void setFullScreen() {
        Window window = this.activity.getWindow();
        if (!this.isFullScreen) {
            window.clearFlags(2048);
            window.clearFlags(2048);
            window.addFlags(1024);
            this.isFullScreen = true;
            return;
        }
        window.clearFlags(1024);
        window.clearFlags(2048);
        window.addFlags(2048);
        this.isFullScreen = false;
    }

    public void updateMenuTitles(Menu menu) {
        MenuItem findItem = menu.findItem(R.id.reportFullScreen);
        if (this.isFullScreen) {
            findItem.setTitle(this.activity.getString(R.string.str_exit_full_screen));
        } else {
            findItem.setTitle(this.activity.getString(R.string.str_full_screen));
        }
    }

    public void hideKeyBoard(EditText editText) {
        ((InputMethodManager) this.activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (!inputMethodManager.isAcceptingText() || activity.getCurrentFocus() == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void setTextviewGravity(TextView textView, int i2) {
        textView.setGravity(i2);
    }
}
