package com.proje.mobilesales.core.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.proje.mobilesales.R;
import static com.proje.mobilesales.core.utils.AppUtils.getThemedResId;

public class MenuTintDelegate {
    private int mTextColorPrimary;
    public void onActivityCreated(Context context) {
        this.mTextColorPrimary = ContextCompat.getColor(context, getThemedResId(context, R.attr.themedTextColorPrimary));
    }
    public void onOptionsMenuCreated(Menu menu) {
        for (int i2 = 0; i2 < menu.size(); i2++) {
            Drawable icon = menu.getItem(i2).getIcon();
            if (icon != null) {
                DrawableCompat.setTint(DrawableCompat.wrap(icon), this.mTextColorPrimary);
            }
        }
    }
    public void setIcon(MenuItem menuItem, int i2) {
        menuItem.setIcon(i2);
        DrawableCompat.setTint(DrawableCompat.wrap(menuItem.getIcon()), this.mTextColorPrimary);
    }
}
