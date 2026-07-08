package com.proje.mobilesales.features.fragment;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.searchdialog.BottomSheetDialogSearchResultListener;
import com.proje.mobilesales.core.searchdialog.BottomSheetSearchDialogAdapter;
import com.proje.mobilesales.core.searchdialog.FilterResultListener;
import com.proje.mobilesales.core.searchdialog.Searchable;
import com.proje.mobilesales.core.searchdialog.SimpleSearchFilter;
import com.proje.mobilesales.features.model.BaseSearchModel;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: BaseSearchModelListDialogFragment.kt */

public final class BaseSearchModelListDialogFragment<T extends Searchable> extends BottomSheetDialogFragment {
    private static final String ARG_ITEMS = "arg-items";
    private static final String ARG_TITLE = "arg-title";
    private static final String ARG_USESEARCH = "arg-useSearch";
    public static final Companion Companion = new Companion(null);
    private static final String STATE_ITEMS = "state:searchItems";
    private static final String STATE_TITLE = "state:title";
    private static final String STATE_USE_SEARCH = "state:useSearch";
    private boolean isUseSearch;
    private BottomSheetDialog mBottomSheetDialog;
    private BottomSheetSearchDialogAdapter<T> mBottomSheetSearchDialogAdapter;
    private Filter mFilter;
    private FilterResultListener<T> mFilterResultListener;
    private ArrayList<BaseSearchModel> mItems;
    private String mTitle;
    private EditText searchBox;
    public BottomSheetDialogSearchResultListener<BaseSearchModel> searchResultListener;

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            Intrinsics.checkNotNull(arguments);
            this.mTitle = arguments.getString(ARG_TITLE, "");
            Bundle arguments2 = getArguments();
            Intrinsics.checkNotNull(arguments2);
            this.mItems = arguments2.getParcelableArrayList(ARG_ITEMS);
            Bundle arguments3 = getArguments();
            Intrinsics.checkNotNull(arguments3);
            this.isUseSearch = arguments3.getBoolean(ARG_USESEARCH);
        }
        if (bundle != null) {
            this.mTitle = bundle.getString(STATE_TITLE, "");
            this.mItems = bundle.getParcelableArrayList(STATE_ITEMS);
            this.isUseSearch = bundle.getBoolean(STATE_USE_SEARCH);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(R.layout.fragment_search_list_dialog, viewGroup, false);
        this.searchBox = inflate.findViewById(R.id.edtSearch);
        TextView textView = inflate.findViewById(R.id.txtDialogTitle);
        if (TextUtils.isEmpty(this.mTitle)) {
            textView.setVisibility(8);
        } else {
            textView.setText(this.mTitle);
        }
        EditText editText = this.searchBox;
        Intrinsics.checkNotNull(editText);
        editText.addTextChangedListener(new TextWatcher(this) { // from class: com.proje.mobilesales.features.fragment.BaseSearchModelListDialogFragmentonCreateView1
            TextWatcher this0;

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                Intrinsics.checkNotNullParameter(editable, "editable");
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                Intrinsics.checkNotNullParameter(charSequence, "charSequence");
            }

            void BaseSearchModelListDialogFragmentonCreateView1(TextWatcher this) {
                this.this0 = this;
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                Filter filter;
                Intrinsics.checkNotNullParameter(charSequence, "charSequence");
                filter = ((BaseSearchModelListDialogFragment) this.this0).mFilter;
                Intrinsics.checkNotNull(filter);
                filter.filter(charSequence);
            }
        });
        inflate.findViewById(R.id.closeButton).setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.fragment.BaseSearchModelListDialogFragmentExternalSyntheticLambda0
            public void BaseSearchModelListDialogFragmentExternalSyntheticLambda0() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BaseSearchModelListDialogFragment.onCreateViewlambda0(BaseSearchModelListDialogFragment.this, view);
            }
        });
        inflate.findViewById(R.id.clearButton).setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.fragment.BaseSearchModelListDialogFragmentExternalSyntheticLambda1
            public void BaseSearchModelListDialogFragmentExternalSyntheticLambda1() {
            }

            public void onClick(View view) {
                BaseSearchModelListDialogFragment.onCreateViewlambda1(BaseSearchModelListDialogFragment.this, view);
            }
        });
        this.mFilterResultListener = new FilterResultListener() { // from class: com.proje.mobilesales.features.fragment.BaseSearchModelListDialogFragmentExternalSyntheticLambda2
            public void BaseSearchModelListDialogFragmentExternalSyntheticLambda2() {
            }

            public void onFilter(ArrayList arrayList) {
                BaseSearchModelListDialogFragment.onCreateViewlambda2(BaseSearchModelListDialogFragment.this, arrayList);
            }
        };
        return inflate;
    }

    public static void onCreateViewlambda0(BaseSearchModelListDialogFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.dismiss();
    }

    public static void onCreateViewlambda1(BaseSearchModelListDialogFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        BottomSheetDialogSearchResultListener<BaseSearchModel> bottomSheetDialogSearchResultListener = this0.searchResultListener;
        Intrinsics.checkNotNull(bottomSheetDialogSearchResultListener);
        bottomSheetDialogSearchResultListener.onCancelled(this0.mBottomSheetDialog);
    }

    public static void onCreateViewlambda2(BaseSearchModelListDialogFragment this0, ArrayList arrayList) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        BottomSheetSearchDialogAdapter<T> bottomSheetSearchDialogAdapter = this0.mBottomSheetSearchDialogAdapter;
        Intrinsics.checkNotNull(bottomSheetSearchDialogAdapter);
        EditText editText = this0.searchBox;
        Intrinsics.checkNotNull(editText);
        bottomSheetSearchDialogAdapter.setSearchTag(editText.getText().toString()).setItems(arrayList);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (bundle != null) {
            this.mTitle = bundle.getString(STATE_TITLE, "");
            this.isUseSearch = bundle.getBoolean(STATE_USE_SEARCH, false);
            Serializable serializable = bundle.getSerializable(STATE_ITEMS);
            if (serializable != null) {
                this.mItems = (ArrayList) serializable;
            }
        }
        RecyclerView recyclerView = view.findViewById(R.id.list);
        ArrayList<BaseSearchModel> arrayList = this.mItems;
        FilterResultListener<T> filterResultListener = this.mFilterResultListener;
        Intrinsics.checkNotNull(filterResultListener);
        this.mFilter = new SimpleSearchFilter(arrayList, filterResultListener, false, 0.0f);
        BottomSheetSearchDialogAdapter<T> bottomSheetSearchDialogAdapter = new BottomSheetSearchDialogAdapter<>(getContext(), R.layout.simple_list_item_single_choice, this.mItems);
        this.mBottomSheetSearchDialogAdapter = bottomSheetSearchDialogAdapter;
        Intrinsics.checkNotNull(bottomSheetSearchDialogAdapter);
        bottomSheetSearchDialogAdapter.setHighlightPartsInCommon(false);
        BottomSheetSearchDialogAdapter<T> bottomSheetSearchDialogAdapter2 = this.mBottomSheetSearchDialogAdapter;
        Intrinsics.checkNotNull(bottomSheetSearchDialogAdapter2);
        bottomSheetSearchDialogAdapter2.setSearchResultListener(this.searchResultListener);
        if (!this.isUseSearch) {
            EditText editText = this.searchBox;
            Intrinsics.checkNotNull(editText);
            editText.setVisibility(8);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(this.mBottomSheetSearchDialogAdapter);
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
        int windowHeight = getWindowHeight();
        if (layoutParams != null) {
            layoutParams.height = (int) (windowHeight * 0.8d);
        }
        view.setLayoutParams(layoutParams);
        bottomSheetBehavior.setState(3);
        bottomSheetBehavior.setPeekHeight((int) (windowHeight * 0.8d));
    }

    private int getWindowHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    @Override // com.google.android.material.bottomsheet.BottomSheetDialogFragment, androidx.appcompat.app.AppCompatDialogFragment, androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        Intrinsics.checkNotNullExpressionValue(onCreateDialog, "onCreateDialog(...)");
        onCreateDialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.proje.mobilesales.features.fragment.BaseSearchModelListDialogFragmentExternalSyntheticLambda3
            public void BaseSearchModelListDialogFragmentExternalSyntheticLambda3() {
            }

            public void onShow(DialogInterface dialogInterface) {
                BaseSearchModelListDialogFragment.onCreateDialoglambda3(BaseSearchModelListDialogFragment.this, dialogInterface);
            }
        });
        return onCreateDialog;
    }

    public static void onCreateDialoglambda3(BaseSearchModelListDialogFragment this0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.mBottomSheetDialog = (BottomSheetDialog) dialogInterface;
        BottomSheetSearchDialogAdapter<T> bottomSheetSearchDialogAdapter = this0.mBottomSheetSearchDialogAdapter;
        Intrinsics.checkNotNull(bottomSheetSearchDialogAdapter);
        bottomSheetSearchDialogAdapter.setSearchDialog(this0.mBottomSheetDialog);
        BottomSheetDialog bottomSheetDialog = this0.mBottomSheetDialog;
        Intrinsics.checkNotNull(bottomSheetDialog);
        this0.setupFullHeight(bottomSheetDialog);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putParcelableArrayList(STATE_ITEMS, this.mItems);
        outState.putBoolean(STATE_USE_SEARCH, this.isUseSearch);
        outState.putString(STATE_TITLE, this.mTitle);
        super.onSaveInstanceState(outState);
    }

    public void setSearchResultListener(BottomSheetDialogSearchResultListener<BaseSearchModel> searchResultListener) {
        Intrinsics.checkNotNullParameter(searchResultListener, "searchResultListener");
        this.searchResultListener = searchResultListener;
    }

    /* compiled from: BaseSearchModelListDialogFragment.kt */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public BaseSearchModelListDialogFragment<?> newInstance(String str, ArrayList<BaseSearchModel> items, boolean z) {
            Intrinsics.checkNotNullParameter(items, "items");
            BaseSearchModelListDialogFragment<?> baseSearchModelListDialogFragment = new BaseSearchModelListDialogFragment<>();
            Bundle bundle = new Bundle();
            bundle.putString(BaseSearchModelListDialogFragment.ARG_TITLE, str);
            bundle.putParcelableArrayList(BaseSearchModelListDialogFragment.ARG_ITEMS, items);
            bundle.putBoolean(BaseSearchModelListDialogFragment.ARG_USESEARCH, z);
            baseSearchModelListDialogFragment.setArguments(bundle);
            return baseSearchModelListDialogFragment;
        }
    }
}
