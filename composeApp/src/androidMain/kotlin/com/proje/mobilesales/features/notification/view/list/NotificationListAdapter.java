package com.proje.mobilesales.features.notification.view.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.C2682R;
import com.proje.mobilesales.core.enums.NotificationStatus;
import com.proje.mobilesales.databinding.NotificationListItemBinding;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationListAdapter.kt */

public final class NotificationListAdapter extends PagingDataAdapter<NotificationModel, ViewHolder> {
    private final DeleteClickListener deleteClickListener;
    private final InfoClickListener infoClickListener;
    private final boolean isSalesManager;
    private final NotificationClickListener itemClickListener;
    private final List<Integer> statusColors;
    private final int userRef;

    public List<Integer> getStatusColors() {
        return this.statusColors;
    }

    public boolean isSalesManager() {
        return this.isSalesManager;
    }

    public int getUserRef() {
        return this.userRef;
    }
     public NotificationListAdapter(List<Integer> list, boolean z, int i, NotificationClickListener notificationClickListener, InfoClickListener infoClickListener, DeleteClickListener deleteClickListener) {
        super(DiffUtilCallBack.INSTANCE, (CoroutineContext) null, (CoroutineContext) null, 6, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(list, "statusColors");
        Intrinsics.checkNotNullParameter(notificationClickListener, "itemClickListener");
        Intrinsics.checkNotNullParameter(infoClickListener, "infoClickListener");
        Intrinsics.checkNotNullParameter(deleteClickListener, "deleteClickListener");
        this.statusColors = list;
        this.isSalesManager = z;
        this.userRef = i;
        this.itemClickListener = notificationClickListener;
        this.infoClickListener = infoClickListener;
        this.deleteClickListener = deleteClickListener;
    }

    /* compiled from: NotificationListAdapter.kt */
    
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private final NotificationListItemBinding binding;
        final NotificationListAdapter this0;

        /* compiled from: NotificationListAdapter.kt */
        
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
         public ViewHolder(NotificationListAdapter notificationListAdapter, NotificationListItemBinding notificationListItemBinding) {
            super(notificationListItemBinding.getRoot());
            Intrinsics.checkNotNullParameter(notificationListItemBinding, "binding");
            this.this0 = notificationListAdapter;
            this.binding = notificationListItemBinding;
        }

        public void bind(NotificationModel notificationModel, Function1<? super NotificationModel, Unit> function1, Function1<? super NotificationModel, Unit> function12, Function1<? super NotificationModel, Unit> function13) {
            Intrinsics.checkNotNullParameter(notificationModel, "item");
            Intrinsics.checkNotNullParameter(function1, "itemClickListener");
            Intrinsics.checkNotNullParameter(function12, "infoClickListener");
            Intrinsics.checkNotNullParameter(function13, "deleteClickListener");
            NotificationListItemBinding notificationListItemBinding = this.binding;
            NotificationListAdapter notificationListAdapter = this.this0;
            notificationListItemBinding.setNotification(notificationModel);
            int i = 4;
            notificationListItemBinding.imgBtnInfo.setVisibility(notificationListAdapter.getUserRef() == notificationModel.getSenderRef() ? 0 : 4);
            notificationListItemBinding.imgViewStatus.setColorFilter(getColor(notificationModel.getStatus()));
            AppCompatImageButton appCompatImageButton = notificationListItemBinding.imgBtnDelete;
            Integer status = notificationModel.getStatus();
            int status2 = NotificationStatus.Deleted.getStatus();
            if (status == null || status.intValue() != status2) {
                i = 0;
            }
            appCompatImageButton.setVisibility(i);
            notificationListItemBinding.cardView.setRadius((float) this.binding.getRoot().getContext().getResources().getDimensionPixelSize(R.dimen.cardview_default_radius));
            notificationListItemBinding.cardView.setElevation((float) this.binding.getRoot().getContext().getResources().getDimensionPixelSize(R.dimen.cardview_default_elevation));
            notificationListItemBinding.cardView.setUseCompatPadding(true);
            notificationListItemBinding.cardView.setOnClickListener(new NotificationListAdapterViewHolderExternalSyntheticLambda0(function1, notificationModel));
            notificationListItemBinding.imgBtnInfo.setOnClickListener(new NotificationListAdapterViewHolderExternalSyntheticLambda1(function12, notificationModel));
            notificationListItemBinding.imgBtnDelete.setOnClickListener(new NotificationListAdapterViewHolderExternalSyntheticLambda2(function13, notificationModel));
            notificationListItemBinding.executePendingBindings();
        }

        
        public static void bindlambda3lambda0(Function1 function1, NotificationModel notificationModel, View view) {
            Intrinsics.checkNotNullParameter(function1, "itemClickListener");
            Intrinsics.checkNotNullParameter(notificationModel, "item");
            function1.invoke(notificationModel);
        }

        
        public static void bindlambda3lambda1(Function1 function1, NotificationModel notificationModel, View view) {
            Intrinsics.checkNotNullParameter(function1, "infoClickListener");
            Intrinsics.checkNotNullParameter(notificationModel, "item");
            function1.invoke(notificationModel);
        }

        
        public static void bindlambda3lambda2(Function1 function1, NotificationModel notificationModel, View view) {
            Intrinsics.checkNotNullParameter(function1, "deleteClickListener");
            Intrinsics.checkNotNullParameter(notificationModel, "item");
            function1.invoke(notificationModel);
        }

        private int getColor(Integer num) {
            if (num == null) {
                return R.C2683color.grey800;
            }
            int i = WhenMappings.EnumSwitchMapping0[NotificationStatus.Companion.fromValue(num.intValue()).ordinal()];
            if (i == 1 || i == 2) {
                return this.this0.getStatusColors().get(0).intValue();
            }
            if (i == 3 || i == 4) {
                return this.this0.getStatusColors().get(1).intValue();
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        NotificationListItemBinding inflate = NotificationListItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ViewHolder(this, inflate);
    }

    /* compiled from: NotificationListAdapter.kt */
    
    public static final class DiffUtilCallBack extends DiffUtil.ItemCallback<NotificationModel> {
        public static final DiffUtilCallBack INSTANCE = new DiffUtilCallBack();

        private DiffUtilCallBack() {
        }

        public boolean areItemsTheSame(NotificationModel notificationModel, NotificationModel notificationModel2) {
            Intrinsics.checkNotNullParameter(notificationModel, "oldItem");
            Intrinsics.checkNotNullParameter(notificationModel2, "newItem");
            return notificationModel.getNotificationId() == notificationModel2.getNotificationId();
        }

        public boolean areContentsTheSame(NotificationModel notificationModel, NotificationModel notificationModel2) {
            Intrinsics.checkNotNullParameter(notificationModel, "oldItem");
            Intrinsics.checkNotNullParameter(notificationModel2, "newItem");
            return Intrinsics.areEqual(notificationModel, notificationModel2);
        }
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        NotificationModel item = getItem(i);
        if (item != null) {
            viewHolder.bind(item, this.itemClickListener.getClickListener(), this.infoClickListener.getClickListener(), this.deleteClickListener.getClickListener());
        }
    }

    /* compiled from: NotificationListAdapter.kt */
    
    public static final class NotificationClickListener {
        private final Function1<NotificationModel, Unit> clickListener;

        public static NotificationClickListener copydefault(NotificationClickListener notificationClickListener, Function1 function1, int i, Object obj) {
            if ((i & 1) != 0) {
                function1 = notificationClickListener.clickListener;
            }
            return notificationClickListener.copy(function1);
        }

        public Function1<NotificationModel, Unit> component1() {
            return this.clickListener;
        }

        public NotificationClickListener copy(Function1<? super NotificationModel, Unit> function1) {
            Intrinsics.checkNotNullParameter(function1, "clickListener");
            return new NotificationClickListener(function1);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof NotificationClickListener) && Intrinsics.areEqual(this.clickListener, ((NotificationClickListener) obj).clickListener);
        }

        public int hashCode() {
            return this.clickListener.hashCode();
        }

        public String toString() {
            return "NotificationClickListener(clickListener=" + this.clickListener + ')';
        }

        public NotificationClickListener(Function1<? super NotificationModel, Unit> function1) {
            Intrinsics.checkNotNullParameter(function1, "clickListener");
            this.clickListener = function1;
        }

        public Function1<NotificationModel, Unit> getClickListener() {
            return this.clickListener;
        }
    }

    /* compiled from: NotificationListAdapter.kt */
    
    public static final class InfoClickListener {
        private final Function1<NotificationModel, Unit> clickListener;

        public static InfoClickListener copydefault(InfoClickListener infoClickListener, Function1 function1, int i, Object obj) {
            if ((i & 1) != 0) {
                function1 = infoClickListener.clickListener;
            }
            return infoClickListener.copy(function1);
        }

        public Function1<NotificationModel, Unit> component1() {
            return this.clickListener;
        }

        public InfoClickListener copy(Function1<? super NotificationModel, Unit> function1) {
            Intrinsics.checkNotNullParameter(function1, "clickListener");
            return new InfoClickListener(function1);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof InfoClickListener) && Intrinsics.areEqual(this.clickListener, ((InfoClickListener) obj).clickListener);
        }

        public int hashCode() {
            return this.clickListener.hashCode();
        }

        public String toString() {
            return "InfoClickListener(clickListener=" + this.clickListener + ')';
        }

        
        public InfoClickListener(Function1<? super NotificationModel, Unit> function1) {
            Intrinsics.checkNotNullParameter(function1, "clickListener");
            this.clickListener = function1;
        }

        public Function1<NotificationModel, Unit> getClickListener() {
            return this.clickListener;
        }
    }

    /* compiled from: NotificationListAdapter.kt */
    
    public static final class DeleteClickListener {
        private final Function1<NotificationModel, Unit> clickListener;

        public static DeleteClickListener copydefault(DeleteClickListener deleteClickListener, Function1 function1, int i, Object obj) {
            if ((i & 1) != 0) {
                function1 = deleteClickListener.clickListener;
            }
            return deleteClickListener.copy(function1);
        }

        public Function1<NotificationModel, Unit> component1() {
            return this.clickListener;
        }

        public DeleteClickListener copy(Function1<? super NotificationModel, Unit> function1) {
            Intrinsics.checkNotNullParameter(function1, "clickListener");
            return new DeleteClickListener(function1);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof DeleteClickListener) && Intrinsics.areEqual(this.clickListener, ((DeleteClickListener) obj).clickListener);
        }

        public int hashCode() {
            return this.clickListener.hashCode();
        }

        public String toString() {
            return "DeleteClickListener(clickListener=" + this.clickListener + ')';
        }
        
        public DeleteClickListener(Function1<? super NotificationModel, Unit> function1) {
            Intrinsics.checkNotNullParameter(function1, "clickListener");
            this.clickListener = function1;
        }

        public Function1<NotificationModel, Unit> getClickListener() {
            return this.clickListener;
        }
    }
}
