package com.proje.mobilesales.features.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.interfaces.MasterPassCardResultListener;
import com.proje.mobilesales.core.masterpass.MasterPassCardItem;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.features.adapter.MasterPassCardRecyclerViewAdapter;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: MasterPassCardFragment.kt */

public final class MasterPassCardFragment extends BottomSheetDialogFragment {
    private static final String ARG_ITEMS = "arg-cardItems";
    private static final String ARG_TITLE = "arg-cardTitle";
    private static final String ARG_USESEARCH = "arg-cardUseSearch";
    public static final Companion Companion = new Companion(null);
    private static final String STATE_ITEMS = "state:cardSearchItems";
    private static final String STATE_TITLE = "state:cardTitle";
    private static final String STATE_USE_SEARCH = "state:cardUseSearch";
    private MasterPassCardRecyclerViewAdapter adapter;
    private MasterPassCardResultListener cardResultListener;
    private boolean isUseSearch;
    private BottomSheetDialog mBottomSheetDialog;
    private ArrayList<MasterPassCardItem> mItems;
    private String mTitle;
    private EditText searchBox;

    public MasterPassCardResultListener getCardResultListener() {
        return this.cardResultListener;
    }

    public void setCardResultListener(MasterPassCardResultListener masterPassCardResultListener) {
        this.cardResultListener = masterPassCardResultListener;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            Intrinsics.checkNotNull(arguments);
            this.mTitle = arguments.getString(ARG_TITLE, "");
            Bundle arguments2 = getArguments();
            Intrinsics.checkNotNull(arguments2);
            this.mItems = (ArrayList) arguments2.getSerializable(ARG_ITEMS);
            Bundle arguments3 = getArguments();
            Intrinsics.checkNotNull(arguments3);
            this.isUseSearch = arguments3.getBoolean(ARG_USESEARCH);
        }
        if (bundle != null) {
            this.mTitle = bundle.getString(STATE_TITLE, "");
            this.mItems = (ArrayList) bundle.getSerializable(STATE_ITEMS);
            this.isUseSearch = bundle.getBoolean(STATE_USE_SEARCH);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(R.layout.fragment_master_pass_card_list, viewGroup, false);
        RecyclerView recyclerView = inflate.findViewById(R.id.list);
        View findViewById = inflate.findViewById(R.id.edtSearch);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.searchBox = (EditText) findViewById;
        TextView textView = inflate.findViewById(R.id.txtDialogTitle);
        if (TextUtils.isEmpty(this.mTitle)) {
            textView.setVisibility(8);
        } else {
            textView.setText(this.mTitle);
        }
        if (!this.isUseSearch) {
            EditText editText = this.searchBox;
            if (editText == null) {
                Intrinsics.throwUninitializedPropertyAccessException("searchBox");
                editText = null;
            }
            editText.setVisibility(8);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MasterPassCardRecyclerViewAdapter masterPassCardRecyclerViewAdapter = new MasterPassCardRecyclerViewAdapter(this.mItems);
        this.adapter = masterPassCardRecyclerViewAdapter;
        Intrinsics.checkNotNull(masterPassCardRecyclerViewAdapter);
        masterPassCardRecyclerViewAdapter.setCardResultListener(this.cardResultListener);
        recyclerView.setAdapter(this.adapter);
        inflate.findViewById(R.id.closeButton).setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.fragment.MasterPassCardFragmentExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MasterPassCardFragment.onCreateViewlambda0(MasterPassCardFragment.this, view);
            }
        });
        inflate.findViewById(R.id.clearButton).setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.fragment.MasterPassCardFragmentExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MasterPassCardFragment.onCreateViewlambda1(MasterPassCardFragment.this, view);
            }
        });
        return inflate;
    }

    
    public static void onCreateViewlambda0(MasterPassCardFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.dismiss();
    }

    
    public static void onCreateViewlambda1(MasterPassCardFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (this0.cardResultListener == null) {
            this0.dismiss();
        }
        MasterPassCardResultListener masterPassCardResultListener = this0.cardResultListener;
        Intrinsics.checkNotNull(masterPassCardResultListener);
        masterPassCardResultListener.onCancelled(this0.mBottomSheetDialog);
    }

    private void setupFullHeight(BottomSheetDialog bottomSheetDialog) {
        FrameLayout frameLayout = bottomSheetDialog.findViewById(R.id.design_bottom_sheet);
        Intrinsics.checkNotNull(frameLayout);
        BottomSheetBehavior<?> from = BottomSheetBehavior.from(frameLayout);
        Intrinsics.checkNotNullExpressionValue(from, "from(...)");
        from.setDraggable(false);
        from.setSkipCollapsed(true);
        from.setHideable(false);
        keepFullScreen(frameLayout, from);
    }

    private void keepFullScreen(View view, BottomSheetBehavior<?> bottomSheetBehavior) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int windowHeight = (int) (getWindowHeight() * 0.8d);
        if (layoutParams != null) {
            layoutParams.height = windowHeight;
        }
        view.setLayoutParams(layoutParams);
        bottomSheetBehavior.setPeekHeight(windowHeight);
        bottomSheetBehavior.setState(3);
    }

    private int getWindowHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Context context = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    @Override // com.google.android.material.bottomsheet.BottomSheetDialogFragment, androidx.appcompat.app.AppCompatDialogFragment, androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        Intrinsics.checkNotNullExpressionValue(onCreateDialog, "onCreateDialog(...)");
        onCreateDialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.proje.mobilesales.features.fragment.MasterPassCardFragmentExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnShowListener
            public void onShow(DialogInterface dialogInterface) {
                MasterPassCardFragment.onCreateDialoglambda2(MasterPassCardFragment.this, dialogInterface);
            }
        });
        return onCreateDialog;
    }

    
    public static void onCreateDialoglambda2(MasterPassCardFragment this0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialogInterface;
        this0.mBottomSheetDialog = bottomSheetDialog;
        Intrinsics.checkNotNull(bottomSheetDialog);
        this0.setupFullHeight(bottomSheetDialog);
        MasterPassCardRecyclerViewAdapter masterPassCardRecyclerViewAdapter = this0.adapter;
        Intrinsics.checkNotNull(masterPassCardRecyclerViewAdapter);
        masterPassCardRecyclerViewAdapter.setSearchDialog(this0.mBottomSheetDialog);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putSerializable(STATE_ITEMS, this.mItems);
        outState.putBoolean(STATE_USE_SEARCH, this.isUseSearch);
        outState.putString(STATE_TITLE, this.mTitle);
        super.onSaveInstanceState(outState);
    }

    /* compiled from: MasterPassCardFragment.kt */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public MasterPassCardFragment newInstance(String str, ArrayList<MasterPassCardItem> items, boolean z) {
            Intrinsics.checkNotNullParameter(items, "items");
            MasterPassCardFragment masterPassCardFragment = new MasterPassCardFragment();
            Bundle bundle = new Bundle();
            bundle.putString(MasterPassCardFragment.ARG_TITLE, str);
            bundle.putSerializable(MasterPassCardFragment.ARG_ITEMS, items);
            bundle.putBoolean(MasterPassCardFragment.ARG_USESEARCH, z);
            masterPassCardFragment.setArguments(bundle);
            return masterPassCardFragment;
        }
    }
}
