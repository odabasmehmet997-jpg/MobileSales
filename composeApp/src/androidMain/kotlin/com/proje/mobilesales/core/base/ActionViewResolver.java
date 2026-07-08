package com.proje.mobilesales.core.base;

import android.view.MenuItem;
import android.view.View;
import androidx.core.view.MenuItemCompat;

public class ActionViewResolver {
    public View getActionView(MenuItem menuItem) {
        return MenuItemCompat.getActionView(menuItem);
    }
}
