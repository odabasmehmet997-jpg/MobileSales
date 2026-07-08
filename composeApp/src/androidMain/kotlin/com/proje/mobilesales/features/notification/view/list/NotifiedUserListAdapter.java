package com.proje.mobilesales.features.notification.view.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.C2682R;
import com.proje.mobilesales.core.enums.NotificationStatus;
import com.proje.mobilesales.databinding.FragmentNotifiedUserListDialogItemBinding;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotifiedUserListAdapter.kt */

public final class NotifiedUserListAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final List<NotifiedUserModel> notifiedUsers;

    /* compiled from: NotifiedUserListAdapter.kt */
    
    public class WhenMappings {
        public static final int[] EnumSwitchMapping0;

        static {
            int[] iArr = new int[NotificationStatus.values().length];
            try {
                iArr[NotificationStatus.Created.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[NotificationStatus.Delivered.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[NotificationStatus.Read.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[NotificationStatus.Deleted.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            EnumSwitchMapping0 = iArr;
        }
    }

    public NotifiedUserListAdapter(List<NotifiedUserModel> list) {
        Intrinsics.checkNotNullParameter(list, "notifiedUsers");
        this.notifiedUsers = list;
    }

    public List<NotifiedUserModel> getNotifiedUsers() {
        return this.notifiedUsers;
    }

    /* compiled from: NotifiedUserListAdapter.kt */
    
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private final FragmentNotifiedUserListDialogItemBinding itemBinding;
        final NotifiedUserListAdapter this0;

        public ViewHolder(NotifiedUserListAdapter notifiedUserListAdapter, FragmentNotifiedUserListDialogItemBinding fragmentNotifiedUserListDialogItemBinding) {
            super(fragmentNotifiedUserListDialogItemBinding.getRoot());
            Intrinsics.checkNotNullParameter(fragmentNotifiedUserListDialogItemBinding, "itemBinding");
            this.this0 = notifiedUserListAdapter;
            this.itemBinding = fragmentNotifiedUserListDialogItemBinding;
        }

        public FragmentNotifiedUserListDialogItemBinding getItemBinding() {
            return this.itemBinding;
        }

        public void bind(NotifiedUserModel notifiedUserModel) {
            Intrinsics.checkNotNullParameter(notifiedUserModel, "item");
            this.itemBinding.setNotifiedUser(notifiedUserModel);
            this.itemBinding.executePendingBindings();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        FragmentNotifiedUserListDialogItemBinding inflate = FragmentNotifiedUserListDialogItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ViewHolder(this, inflate);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        NotifiedUserModel notifiedUserModel = this.notifiedUsers.get(i);
        viewHolder.bind(notifiedUserModel);
        viewHolder.getItemBinding().tvStatus.setText(getNotificationStatus(notifiedUserModel.getStatus()));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.notifiedUsers.size();
    }

    public int getNotificationStatus(Integer num) {
        if (num == null) {
            return 0;
        }
        int i = WhenMappings.EnumSwitchMapping0[NotificationStatus.Companion.fromValue(num.intValue()).ordinal()];
        if (i == 1) {
            return R.string.str_notification_status_created;
        }
        if (i == 2) {
            return R.string.str_notification_status_delivered;
        }
        if (i == 3 || i == 4) {
            return R.string.str_notification_status_read;
        }
        throw new NoWhenBranchMatchedException();
    }
}
