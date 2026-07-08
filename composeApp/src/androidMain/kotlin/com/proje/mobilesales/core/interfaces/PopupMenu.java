package com.proje.mobilesales.core.interfaces;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.IdRes;
import androidx.annotation.MenuRes;
import androidx.annotation.StringRes;
import java.util.Objects;
public interface PopupMenu {

    interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    PopupMenu create(Context context, View view, int i2);

    PopupMenu inflate(@MenuRes int i2);

    PopupMenu setMenuItemTitle(@IdRes int i2, @StringRes int i3);

    PopupMenu setMenuItemVisible(@IdRes int i2, boolean z);

    PopupMenu setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener);

    void show();

    class Impl implements PopupMenu {
        private androidx.appcompat.widget.PopupMenu mSupportPopupMenu;

        public PopupMenu create(Context context, View view, int i2) {
            this.mSupportPopupMenu = new androidx.appcompat.widget.PopupMenu(context, view, i2);
            return this;
        }
        public PopupMenu inflate(@MenuRes int i2) {
            this.mSupportPopupMenu.inflate(i2);
            return this;
        }

        public PopupMenu setMenuItemVisible(@IdRes int i2, boolean z) {
            this.mSupportPopupMenu.getMenu().findItem(i2).setVisible(z);
            return this;
        }

        public PopupMenu setMenuItemTitle(@IdRes int i2, @StringRes int i3) {
            this.mSupportPopupMenu.getMenu().findItem(i2).setTitle(i3);
            return this;
        }
        public PopupMenu setOnMenuItemClickListener(final OnMenuItemClickListener onMenuItemClickListener) {
            androidx.appcompat.widget.PopupMenu popupMenu = this.mSupportPopupMenu;
            Objects.requireNonNull(onMenuItemClickListener);
            popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem menuItem) {
                    return OnMenuItemClickListener.this.onMenuItemClick(menuItem);
                }
            });
            return this;
        }
        public void show() {
            this.mSupportPopupMenu.show();
        }
    }
}
