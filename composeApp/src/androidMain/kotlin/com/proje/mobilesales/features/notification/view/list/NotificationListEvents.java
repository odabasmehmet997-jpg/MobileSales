package com.proje.mobilesales.features.notification.view.list;

import com.proje.mobilesales.features.notification.model.NotificationModel;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationListEvents.kt */

public abstract class NotificationListEvents {
    public NotificationListEvents(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: NotificationListEvents.kt */
    
    public static final class Delete extends NotificationListEvents {
        private final int notificationId;

        public static Delete copydefault(Delete delete, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = delete.notificationId;
            }
            return delete.copy(i);
        }

        public int component1() {
            return this.notificationId;
        }

        public Delete copy(int i) {
            return new Delete(i);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Delete) && this.notificationId == ((Delete) obj).notificationId;
        }

        public int hashCode() {
            return Integer.hashCode(this.notificationId);
        }

        public String toString() {
            return "Delete(notificationId=" + this.notificationId + ')';
        }

        public Delete(int i) {
            super(null);
            this.notificationId = i;
        }

        public int getNotificationId() {
            return this.notificationId;
        }
    }

    private NotificationListEvents() {
    }

    /* compiled from: NotificationListEvents.kt */
    
    public static final class StatusUpdate extends NotificationListEvents {
        private final int notificationId;
        private final int status;

        public static StatusUpdate copydefault(StatusUpdate statusUpdate, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = statusUpdate.notificationId;
            }
            if ((i3 & 2) != 0) {
                i2 = statusUpdate.status;
            }
            return statusUpdate.copy(i, i2);
        }

        public int component1() {
            return this.notificationId;
        }

        public int component2() {
            return this.status;
        }

        public StatusUpdate copy(int i, int i2) {
            return new StatusUpdate(i, i2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StatusUpdate statusUpdate)) {
                return false;
            }
            return this.notificationId == statusUpdate.notificationId && this.status == statusUpdate.status;
        }

        public int hashCode() {
            return (Integer.hashCode(this.notificationId) * 31) + Integer.hashCode(this.status);
        }

        public String toString() {
            return "StatusUpdate(notificationId=" + this.notificationId + ", status=" + this.status + ')';
        }

        public StatusUpdate(int i, int i2) {
            super(null);
            this.notificationId = i;
            this.status = i2;
        }

        public int getNotificationId() {
            return this.notificationId;
        }

        public int getStatus() {
            return this.status;
        }
    }

    /* compiled from: NotificationListEvents.kt */
    
    public static final class ItemAdded extends NotificationListEvents {
        private final NotificationModel notificationModel;

        public static ItemAdded copydefault(ItemAdded itemAdded, NotificationModel notificationModel, int i, Object obj) {
            if ((i & 1) != 0) {
                notificationModel = itemAdded.notificationModel;
            }
            return itemAdded.copy(notificationModel);
        }

        public NotificationModel component1() {
            return this.notificationModel;
        }

        public ItemAdded copy(NotificationModel notificationModel) {
            Intrinsics.checkNotNullParameter(notificationModel, "notificationModel");
            return new ItemAdded(notificationModel);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ItemAdded) && Intrinsics.areEqual(this.notificationModel, ((ItemAdded) obj).notificationModel);
        }

        public int hashCode() {
            return this.notificationModel.hashCode();
        }

        public String toString() {
            return "ItemAdded(notificationModel=" + this.notificationModel + ')';
        }
        public ItemAdded(NotificationModel notificationModel) {
            super(null);
            Intrinsics.checkNotNullParameter(notificationModel, "notificationModel");
            this.notificationModel = notificationModel;
        }

        public NotificationModel getNotificationModel() {
            return this.notificationModel;
        }
    }
}
