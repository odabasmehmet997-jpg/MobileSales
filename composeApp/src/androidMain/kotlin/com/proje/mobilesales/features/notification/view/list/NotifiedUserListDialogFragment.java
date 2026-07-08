package com.proje.mobilesales.features.notification.view.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.proje.mobilesales.core.extensions.ViewExtensions;
import com.proje.mobilesales.databinding.FragmentNotifiedUserListDialogBinding;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotifiedUserListDialogFragment.kt */

public final class NotifiedUserListDialogFragment extends BottomSheetDialogFragment {
    public static final Companion Companion = new Companion(null);
    private FragmentNotifiedUserListDialogBinding _binding;
    private List<NotifiedUserModel> notifiedUsers = CollectionsKt.emptyList();

    private FragmentNotifiedUserListDialogBinding getBinding() {
        FragmentNotifiedUserListDialogBinding fragmentNotifiedUserListDialogBinding = this._binding;
        Intrinsics.checkNotNull(fragmentNotifiedUserListDialogBinding);
        return fragmentNotifiedUserListDialogBinding;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        this._binding = FragmentNotifiedUserListDialogBinding.inflate(layoutInflater, viewGroup, false);
        RecyclerView root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        getBinding().list.setLayoutManager(new LinearLayoutManager(getContext()));
        getBinding().list.setAdapter(new NotifiedUserListAdapter(this.notifiedUsers));
        getBinding().list.addItemDecoration(new DividerItemDecoration(getContext(), 1));
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        ViewParent parent = requireView().getParent();
        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.widget.FrameLayout");
        ViewParent parent2 = requireView().getParent();
        Intrinsics.checkNotNull(parent2, "null cannot be cast to non-null type android.view.View");
        BottomSheetBehavior from = BottomSheetBehavior.from((View) parent2);
        Intrinsics.checkNotNullExpressionValue(from, "from(...)");
        from.setState(3);
        from.setDraggable(false);
        from.setSkipCollapsed(true);
        from.setHideable(false);
        Object systemService = requireActivity().getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        ViewExtensions.setBottomSheetHeight((FrameLayout) parent, from, 0.8d, (WindowManager) systemService);
    }

    /* compiled from: NotifiedUserListDialogFragment.kt */
    
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public NotifiedUserListDialogFragment newInstance(List<NotifiedUserModel> list) {
            Intrinsics.checkNotNullParameter(list, "notifiedUserList");
            NotifiedUserListDialogFragment notifiedUserListDialogFragment = new NotifiedUserListDialogFragment();
            notifiedUserListDialogFragment.notifiedUsers = list;
            return notifiedUserListDialogFragment;
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }
}
