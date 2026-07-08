package com.proje.mobilesales;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.proje.mobilesales.databinding.ActivityItemReportsBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class DataBinderMapperImpl extends DataBinderMapper {

    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYITEMREPORTS = 1;
    private static final int LAYOUT_ACTIVITYNOTIFICATIONDETAIL = 2;
    private static final int LAYOUT_FRAGMENTITEMREPORTS = 3;
    private static final int LAYOUT_FRAGMENTNOTIFICATIONUSERSELECTIONDIALOGITEM = 4;
    private static final int LAYOUT_FRAGMENTNOTIFIEDUSERLISTDIALOGITEM = 5;
    private static final int LAYOUT_FRAGMENTVARIANTLISTDIALOG = 6;
    private static final int LAYOUT_FRAGMENTVARIANTLISTDIALOGITEM = 7;
    private static final int LAYOUT_NOTIFICATIONLISTITEM = 8;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(8);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_item_reports, 1);
        sparseIntArray.put(R.layout.activity_notification_detail, 2);
        sparseIntArray.put(R.layout.fragment_item_reports, 3);
        sparseIntArray.put(R.layout.fragment_notification_user_selection_dialog_item, 4);
        sparseIntArray.put(R.layout.fragment_notified_user_list_dialog_item, 5);
        sparseIntArray.put(R.layout.fragment_variant_list_dialog, 6);
        sparseIntArray.put(R.layout.fragment_variant_list_dialog_item, 7);
        sparseIntArray.put(R.layout.notification_list_item, 8);
    }

    public ActivityItemReportsBindingImpl getDataBinder(DataBindingComponent dataBindingComponent, View view, int i2) {
        int i3 = INTERNAL_LAYOUT_ID_LOOKUP.get(i2);
        if (i3 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag == null) {
            throw new RuntimeException("view must have a tag");
        }
        switch (i3) {
            case 1:
                if ("layout/activity_item_reports_0".equals(tag)) {
                    return new ActivityItemReportsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_item_reports is invalid. Received: " + tag);
            case 2:
                if ("layout/activity_notification_detail_0".equals(tag)) {
                    return new ActivityItemReportsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_notification_detail is invalid. Received: " + tag);
            case 3:
                if ("layout/fragment_item_reports_0".equals(tag)) {
                    return new ActivityItemReportsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_item_reports is invalid. Received: " + tag);
            case 4:
                if ("layout/fragment_notification_user_selection_dialog_item_0".equals(tag)) {
                    return new ActivityItemReportsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_notification_user_selection_dialog_item is invalid. Received: " + tag);
            case 5:
                if ("layout/fragment_notified_user_list_dialog_item_0".equals(tag)) {
                    return new ActivityItemReportsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_notified_user_list_dialog_item is invalid. Received: " + tag);
            case 6:
                if ("layout/fragment_variant_list_dialog_0".equals(tag)) {
                    return new ActivityItemReportsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_variant_list_dialog is invalid. Received: " + tag);
            case 7:
                if ("layout/fragment_variant_list_dialog_item_0".equals(tag)) {
                    return new ActivityItemReportsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_variant_list_dialog_item is invalid. Received: " + tag);
            case 8:
                if ("layout/notification_list_item_0".equals(tag)) {
                    return new ActivityItemReportsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for notification_list_item is invalid. Received: " + tag);
            default:
                return null;
        }
    }
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i2) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i2) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }
    public final int getLayoutId(String str) {
        int result = 0;
        Integer num;
        if (str != null && (num = InnerLayoutIdLookup.sKeys.get(str)) != null) {
            result = num;
        }
        return result;
    }
    public final String convertBrIdToString(int i2) {
        return InnerBrLookup.sKeys.get(i2);
    }
    public List<DataBinderMapper> collectDependencies() {
        ArrayList<DataBinderMapper> arrayList = new ArrayList<>(1);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        return arrayList;
    }
    private static class InnerBrLookup {
        static final SparseArray<String> sKeys = new SparseArray<>(7);

        private InnerBrLookup() {
        }

        static {
            sKeys.put(0, "_all");
            sKeys.put(1, "notification");
            sKeys.put(2, "notificationDetail");
            sKeys.put(3, "notificationUser");
            sKeys.put(4, "notifiedUser");
            sKeys.put(5, "variant");
            sKeys.put(6, "variantHeader");
        }
    }
    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys = new HashMap<>(8);
        private InnerLayoutIdLookup() {
        }
        static {
            sKeys.put("layout/activity_item_reports_0", R.layout.activity_item_reports);
            sKeys.put("layout/activity_notification_detail_0", R.layout.activity_notification_detail);
            sKeys.put("layout/fragment_item_reports_0", R.layout.fragment_item_reports);
            sKeys.put("layout/fragment_notification_user_selection_dialog_item_0", Integer.valueOf(R.layout.fragment_notification_user_selection_dialog_item));
            sKeys.put("layout/fragment_notified_user_list_dialog_item_0", Integer.valueOf(R.layout.fragment_notified_user_list_dialog_item));
            sKeys.put("layout/fragment_variant_list_dialog_0", Integer.valueOf(R.layout.fragment_variant_list_dialog));
            sKeys.put("layout/fragment_variant_list_dialog_item_0", Integer.valueOf(R.layout.fragment_variant_list_dialog_item));
            sKeys.put("layout/notification_list_item_0", Integer.valueOf(R.layout.notification_list_item));
        }
    }
}
