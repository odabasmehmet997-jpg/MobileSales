package com.proje.mobilesales.features.fragment;

import android.R;
import android.content.Context;
import android.graphics.Insets;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.TooltipCompat;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.databinding.FragmentVariantListDialogBinding;
import com.proje.mobilesales.databinding.FragmentVariantListDialogItemBinding;
import com.proje.mobilesales.features.model.SelectedVariant;
import com.proje.mobilesales.features.model.VariantHeaderModel;
import com.proje.mobilesales.features.model.VariantModel;
import com.proje.mobilesales.features.model.VariantSelectionParams;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;

import kotlinx.coroutines.Dispatchers;
 
public final class VariantListDialogFragment extends BottomSheetDialogFragment {
    public static final Companion Companion = new Companion(null);
    private FragmentVariantListDialogBinding _binding;
    ItemAdapter itemAdapter;
    List<VariantModel> mList = new ArrayList();
    private IVariantSelectionListener selectionListener;
    VariantSelectionParams variantSelectionParams;
 
    public interface IVariantSelectionListener {
        void onCancelled();

        void operationCompleted(ArrayList<SelectedVariant> arrayList, int i2);
    }

    public static VariantListDialogFragment newInstance(VariantSelectionParams variantSelectionParams) {
        return Companion.newInstance(variantSelectionParams);
    }

    public FragmentVariantListDialogBinding getBinding() {
        FragmentVariantListDialogBinding fragmentVariantListDialogBinding = this._binding;
        Intrinsics.checkNotNull(fragmentVariantListDialogBinding);
        return fragmentVariantListDialogBinding;
    }
 
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentVariantListDialogBinding.inflate(inflater, viewGroup, false);
        View root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }
 
    public void onStart() {
        super.onStart();
        ViewParent parent = requireView().getParent();
        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.widget.FrameLayout");
        Object parent2 = requireView().getParent();
        Intrinsics.checkNotNull(parent2, "null cannot be cast to non-null type android.view.View");
        BottomSheetBehavior<?> from = BottomSheetBehavior.from((View) parent2);
        Intrinsics.checkNotNullExpressionValue(from, "from(...)");
        from.setState(3);
        from.setDraggable(false);
        from.setSkipCollapsed(true);
        from.setHideable(false);
        keepFullScreen((FrameLayout) parent, from);
    }
 
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        loadVariantList();
        getBinding().list.setOnTouchListener(new View.OnTouchListener() { 
            public void VariantListDialogFragmentExternalSyntheticLambda0() {
            } 
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                boolean onViewCreatedlambda0;
                onViewCreatedlambda0 = VariantListDialogFragment.onViewCreatedlambda0(VariantListDialogFragment.this, view2, motionEvent);
                return onViewCreatedlambda0;
            }
        });
        getBinding().btnCancel.setOnClickListener(new View.OnClickListener() { 
            public void VariantListDialogFragmentExternalSyntheticLambda1() {
            }
 
            public void onClick(View view2) {
                VariantListDialogFragment.onViewCreatedlambda1(VariantListDialogFragment.this, view2);
            }
        });
        getBinding().btnDone.setOnClickListener(new View.OnClickListener() {
            public void VariantListDialogFragmentExternalSyntheticLambda2() {
            }
            public void onClick(View view2) {
                VariantListDialogFragment.onViewCreatedlambda2(VariantListDialogFragment.this, view2);
            }
        });
        getBinding().btnSelectAll.setOnClickListener(new View.OnClickListener() {
            public void VariantListDialogFragmentExternalSyntheticLambda3() {
            }
            public void onClick(View view2) {
                VariantListDialogFragment.onViewCreatedlambda3(VariantListDialogFragment.this, view2);
            }
        });
        getBinding().btnUnSelectAll.setOnClickListener(new View.OnClickListener() {
            public void VariantListDialogFragmentExternalSyntheticLambda4() {
            }
 
            public void onClick(View view2) {
                VariantListDialogFragment.onViewCreatedlambda4(VariantListDialogFragment.this, view2);
            }
        });
        TooltipCompat.setTooltipText(getBinding().btnCancel, getString(R.string.str_cancel));
        TooltipCompat.setTooltipText(getBinding().btnSelectAll, getString(R.string.str_select_all));
        TooltipCompat.setTooltipText(getBinding().btnDone, getString(R.string.str_okey));
        TooltipCompat.setTooltipText(getBinding().btnUnSelectAll, getString(R.string.str_remove_all));
        getBinding().edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.proje.mobilesales.features.fragment.VariantListDialogFragmentExternalSyntheticLambda5
            public void VariantListDialogFragmentExternalSyntheticLambda5() {
            }
            public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
                boolean onViewCreatedlambda5;
                onViewCreatedlambda5 = VariantListDialogFragment.onViewCreatedlambda5(VariantListDialogFragment.this, textView, i2, keyEvent);
                return onViewCreatedlambda5;
            }
        });
        getBinding().imgBtnSearch.setOnClickListener(new View.OnClickListener() { 
            public void VariantListDialogFragmentExternalSyntheticLambda6() {
            }
            public void onClick(View view2) {
                VariantListDialogFragment.onViewCreatedlambda6(VariantListDialogFragment.this, view2);
            }
        });
        getBinding().imgBtnClearSearch.setOnClickListener(new View.OnClickListener() { 
            public void VariantListDialogFragmentExternalSyntheticLambda7() {
            }
            public void onClick(View view2) {
                VariantListDialogFragment.onViewCreatedlambda7(VariantListDialogFragment.this, view2);
            }
        });
    }

    public static boolean onViewCreatedlambda0(VariantListDialogFragment this0, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Object systemService = this0.requireContext().getSystemService("input_method");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        ((InputMethodManager) systemService).hideSoftInputFromWindow(view.getWindowToken(), 0);
        return false;
    }

    public static void onViewCreatedlambda1(VariantListDialogFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.dismiss();
        IVariantSelectionListener iVariantSelectionListener = this0.selectionListener;
        if (iVariantSelectionListener != null) {
            iVariantSelectionListener.onCancelled();
        }
    }

    public static void onViewCreatedlambda2(VariantListDialogFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.onDoneClick();
    }

    public static void onViewCreatedlambda3(VariantListDialogFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.selectAll();
    }

    public static void onViewCreatedlambda4(VariantListDialogFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.unSelectAll();
    }

    public static boolean onViewCreatedlambda5(VariantListDialogFragment this0, TextView textView, int i2, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (i2 != 3 && i2 != 6 && (keyEvent.getAction() != 0 || keyEvent.getKeyCode() != 66)) {
            return false;
        }
        this0.searchVariants();
        return true;
    }

    public static void onViewCreatedlambda6(VariantListDialogFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.searchVariants();
    }

    public static void onViewCreatedlambda7(VariantListDialogFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.clearSearch();
    }

    public void setVariantSelectionListener(IVariantSelectionListener variantSelectionListener) {
        Intrinsics.checkNotNullParameter(variantSelectionListener, "variantSelectionListener");
        this.selectionListener = variantSelectionListener;
    }

    public Object loadItems(Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new VariantListDialogFragmentloadItems2(this, null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    private void unSelectAll() {
        ObservableField<Integer> selectedItemCount;
        List<VariantModel> mVariantModelList;
        VariantHeaderModel variantHeader = getBinding().getVariantHeader();
        if (variantHeader != null && (mVariantModelList = variantHeader.getMVariantModelList()) != null) {
            ArrayList<VariantModel> arrayList = new ArrayList();
            for (Object obj : mVariantModelList) {
                if (Intrinsics.areEqual(((VariantModel) obj).getChecked().get(), Boolean.TRUE)) {
                    arrayList.add((VariantModel) obj);
                }
            }
            for (VariantModel variantModel : arrayList) {
                variantModel.getChecked().set(Boolean.FALSE);
                variantModel.getAmount().set("0");
            }
        }
        getBinding().btnSelectAll.setVisibility(0);
        getBinding().btnUnSelectAll.setVisibility(8);
        VariantHeaderModel variantHeader2 = getBinding().getVariantHeader();
        if (variantHeader2 == null || (selectedItemCount = variantHeader2.getSelectedItemCount()) == null) {
            return;
        }
        selectedItemCount.set(0);
    }

    private void selectAll() {
        ObservableField<Integer> selectedItemCount;
        List<VariantModel> mVariantModelList;
        VariantHeaderModel variantHeader = getBinding().getVariantHeader();
        if (variantHeader != null && (mVariantModelList = variantHeader.getMVariantModelList()) != null) {
            ArrayList<VariantModel> arrayList = new ArrayList();
            for (Object obj : mVariantModelList) {
                if (Intrinsics.areEqual(((VariantModel) obj).getChecked().get(), Boolean.FALSE)) {
                    arrayList.add((VariantModel) obj);
                }
            }
            for (VariantModel variantModel : arrayList) {
                variantModel.getChecked().set(Boolean.TRUE);
                variantModel.getAmount().set(BuildConfig.NETSIS_DEMO_PASSWORD);
            }
        }
        getBinding().btnSelectAll.setVisibility(8);
        getBinding().btnUnSelectAll.setVisibility(0);
        VariantHeaderModel variantHeader2 = getBinding().getVariantHeader();
        if (variantHeader2 == null || (selectedItemCount = variantHeader2.getSelectedItemCount()) == null) {
            return;
        }
        selectedItemCount.set(Integer.valueOf(this.mList.size()));
    }

    private void onDoneClick() {
        Collection emptyList;
        VariantSelectionParams variantSelectionParams;
        double d2;
        List<VariantModel> mVariantModelList;
        String str;
        if (getBinding().getVariantHeader() != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("selected items count ");
            VariantHeaderModel variantHeader = getBinding().getVariantHeader();
            Intrinsics.checkNotNull(variantHeader);
            sb.append(variantHeader.getSelectedItemCount().get());
            Log.d("VarintInfo", sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("selected items total ");
            VariantHeaderModel variantHeader2 = getBinding().getVariantHeader();
            Intrinsics.checkNotNull(variantHeader2);
            double d3 = 0.0d;
            for (VariantModel variantModel : variantHeader2.getMVariantModelList()) {
                String str2 = variantModel.getAmount().get();
                if (str2 == null || str2.length() == 0) {
                    str = "0";
                } else {
                    String str3 = variantModel.getAmount().get();
                    Intrinsics.checkNotNull(str3);
                    str = str3;
                }
                d3 += Double.parseDouble(str);
            }
            sb2.append(d3);
            Log.d("VarintInfo", sb2.toString());
        }
        VariantHeaderModel variantHeader3 = getBinding().getVariantHeader();
        if (variantHeader3 == null || (mVariantModelList = variantHeader3.getMVariantModelList()) == null) {
            emptyList = CollectionsKt.emptyList();
        } else {
            emptyList = new ArrayList();
            for (Object obj : mVariantModelList) {
                if (Intrinsics.areEqual(((VariantModel) obj).getChecked().get(), Boolean.TRUE)) {
                    emptyList.add(obj);
                }
            }
        }
        ArrayList<SelectedVariant> arrayList = new ArrayList<>();
        Iterator it = emptyList.iterator();
        while (true) {
            variantSelectionParams = null;
            if (!it.hasNext()) {
                break;
            }
            VariantModel variantModel2 = (VariantModel) it.next();
            VariantSelectionParams variantSelectionParams2 = this.variantSelectionParams;
            if (variantSelectionParams2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("variantSelectionParams");
            } else {
                variantSelectionParams = variantSelectionParams2;
            }
            String productCode = variantSelectionParams.getProductCode();
            String str4 = variantModel2.getCode().get();
            String str5 = str4 == null ? "" : str4;
            Intrinsics.checkNotNull(str5);
            int id = variantModel2.getId();
            String str6 = variantModel2.getName().get();
            String str7 = str6 == null ? "" : str6;
            Intrinsics.checkNotNull(str7);
            String str8 = variantModel2.getAmount().get();
            if (str8 != null) {
                Intrinsics.checkNotNull(str8);
                Double doubleOrNull = StringsKt.toDoubleOrNull(str8);
                if (doubleOrNull != null) {
                    d2 = doubleOrNull.doubleValue();
                    arrayList.add(new SelectedVariant(productCode, str5, id, str7, d2, variantModel2.getPriceInfo()));
                }
            }
            d2 = 0.0d;
            arrayList.add(new SelectedVariant(productCode, str5, id, str7, d2, variantModel2.getPriceInfo()));
        }
        if (!arrayList.isEmpty()) {
            Iterator<T> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                if (((SelectedVariant) it2.next()).getAmount() == 0.0d) {
                    Toast.makeText(getContext(), R.string.str_amount_could_not_be_zero_for_selected, 1).show();
                    return;
                }
            }
        }
        dismiss();
        IVariantSelectionListener iVariantSelectionListener = this.selectionListener;
        if (iVariantSelectionListener != null) {
            VariantSelectionParams variantSelectionParams3 = this.variantSelectionParams;
            if (variantSelectionParams3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("variantSelectionParams");
            } else {
                variantSelectionParams = variantSelectionParams3;
            }
            iVariantSelectionListener.operationCompleted(arrayList, variantSelectionParams.getPosition());
        }
    }

    private void loadVariantList() {
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        BuildersKt.launch(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new VariantListDialogFragmentloadVariantList1(this, null));
    }
    private void searchVariants() {
        String str;
        ItemAdapter itemAdapter;
        String obj;
        String obj2;
        IBinder windowToken = getBinding().edtSearch.getWindowToken();
        Intrinsics.checkNotNullExpressionValue(windowToken, "getWindowToken(...)");
        hideKeyboard(windowToken);
        Editable text = getBinding().edtSearch.getText();
        if (text == null || (obj = text.toString()) == null || (obj2 = StringsKt.trim(obj).toString()) == null) {
            str = null;
        } else {
            str = obj2.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(str, "toUpperCase(...)");
        }
        ArrayList arrayList = new ArrayList();
        if (str != null && str.length() != 0) {
            List<VariantModel> list = this.mList;
            ArrayList arrayList2 = new ArrayList();
            for (Object obj3 : list) {
                VariantModel variantModel = (VariantModel) obj3;
                String str2 = variantModel.getName().get();
                boolean z = true;
                if (str2 != null && str2.length() != 0) {
                    String str3 = variantModel.getName().get();
                    if (str3 != null) {
                        Intrinsics.checkNotNull(str3);
                        String upperCase = str3.toUpperCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                        boolean contains = upperCase != null && StringsKt.contains(upperCase, str, false);
                    }
                }
                String str4 = variantModel.getCode().get();
                if (!(str4 == null || str4.length() == 0)) {
                    String str5 = variantModel.getCode().get();
                    if (str5 != null) {
                        Intrinsics.checkNotNull(str5);
                        String upperCase2 = str5.toUpperCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(upperCase2, "toUpperCase(...)");
                        boolean contains = upperCase2 != null && StringsKt.contains(upperCase2, str, false);
                    }
                }
                z = false;
                if (z) {
                    arrayList2.add(obj3);
                }
            }
            List mutableList = CollectionsKt.toMutableList(arrayList2);
            arrayList.addAll(mutableList);
            List<VariantModel> minus = CollectionsKt.minus(this.mList, mutableList);
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(minus, 10));
            for (VariantModel variantModel2 : minus) {
                arrayList3.add(new VariantModel(variantModel2.getActualStock(), variantModel2.getRealStock(), variantModel2.getName(), variantModel2.getCode(), variantModel2.getAmount(), variantModel2.getChecked(), variantModel2.getId(), variantModel2.getDefinedPrice(), variantModel2.getPriceInfo(), true));
            }
            arrayList.addAll(arrayList3);
        } else {
            arrayList.addAll(this.mList);
        }
        ItemAdapter itemAdapter2 = this.itemAdapter;
        if (itemAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("itemAdapter");
            itemAdapter2 = null;
        }
        itemAdapter2.updateList(arrayList);
        ItemAdapter itemAdapter3 = this.itemAdapter;
        if (itemAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("itemAdapter");
            itemAdapter = null;
        } else {
            itemAdapter = itemAdapter3;
        }
        itemAdapter.notifyDataSetChanged();
    }

    private void clearSearch() {
        getBinding().edtSearch.setText("");
        ItemAdapter itemAdapter = this.itemAdapter;
        ItemAdapter itemAdapter2 = null;
        if (itemAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("itemAdapter");
            itemAdapter = null;
        }
        itemAdapter.updateList(this.mList);
        ItemAdapter itemAdapter3 = this.itemAdapter;
        if (itemAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("itemAdapter");
        } else {
            itemAdapter2 = itemAdapter3;
        }
        itemAdapter2.notifyDataSetChanged();
        IBinder windowToken = getBinding().edtSearch.getWindowToken();
        Intrinsics.checkNotNullExpressionValue(windowToken, "getWindowToken(...)");
        hideKeyboard(windowToken);
    }
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        VariantSelectionParams variantSelectionParams = this.variantSelectionParams;
        if (variantSelectionParams == null) {
            Intrinsics.throwUninitializedPropertyAccessException("variantSelectionParams");
            variantSelectionParams = null;
        }
        outState.putParcelable(VariantListDialogFragmentKt.ARG_VARIANT_SELECTION_PARAMS, variantSelectionParams);
    }

    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (context instanceof IVariantSelectionListener) {
            this.selectionListener = (IVariantSelectionListener) context;
        }
    }
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            VariantSelectionParams variantSelectionParams = bundle.getParcelable(VariantListDialogFragmentKt.ARG_VARIANT_SELECTION_PARAMS);
            if (variantSelectionParams == null) {
                variantSelectionParams = new VariantSelectionParams(0, "", "", false, -1, 0, null, -1, "", new ArrayList(), -99);
            }
            this.variantSelectionParams = variantSelectionParams;
            return;
        }
        Bundle arguments = getArguments();
        VariantSelectionParams variantSelectionParams2 = arguments != null ? (VariantSelectionParams) arguments.getParcelable(VariantListDialogFragmentKt.ARG_VARIANT_SELECTION_PARAMS) : null;
        if (variantSelectionParams2 == null) {
            variantSelectionParams2 = new VariantSelectionParams(0, "", "", false, -1, 0, null, -1, "", new ArrayList(), -99);
        }
        this.variantSelectionParams = variantSelectionParams2;
    }

    private void keepFullScreen(View view, BottomSheetBehavior<?> bottomSheetBehavior) {
        Intrinsics.checkNotNull(view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int usableScreenHeight = (getUsableScreenHeight() - calculateActionBarHeight()) - calculateStatusBarHeight();
        if (layoutParams != null) {
            layoutParams.height = usableScreenHeight;
        }
        view.setLayoutParams(layoutParams);
        bottomSheetBehavior.setPeekHeight(usableScreenHeight, true);
        bottomSheetBehavior.setState(3);
    }

    private final class ViewHolder extends RecyclerView.ViewHolder {
        final VariantListDialogFragment this0;
        private final FragmentVariantListDialogItemBinding xBinding;

        public ViewHolder(VariantListDialogFragment variantListDialogFragment, FragmentVariantListDialogItemBinding xBinding) {
            super(xBinding.getRoot());
            Intrinsics.checkNotNullParameter(xBinding, "xBinding");
            this.this0 = variantListDialogFragment;
            this.xBinding = xBinding;
        }

        public FragmentVariantListDialogItemBinding getXBinding() {
            return this.xBinding;
        }

        public void bind(VariantModel item) {
            Intrinsics.checkNotNullParameter(item, "item");
            this.xBinding.setVariant(item);
            this.xBinding.executePendingBindings();
        }
    }

    private final class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {
        private List<VariantModel> myList;
        final VariantListDialogFragment this0;

        public ItemAdapter(VariantListDialogFragment variantListDialogFragment, List<VariantModel> myList) {
            Intrinsics.checkNotNullParameter(myList, "myList");
            this.this0 = variantListDialogFragment;
            this.myList = myList;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int i2) {
            Intrinsics.checkNotNullParameter(parent, "parent");
            VariantListDialogFragment variantListDialogFragment = this.this0;
            FragmentVariantListDialogItemBinding inflate = FragmentVariantListDialogItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
            return new ViewHolder(variantListDialogFragment, inflate);
        }

        public void updateList(List<VariantModel> list) {
            Intrinsics.checkNotNullParameter(list, "list");
            this.myList = list;
        }

        public void onBindViewHolder(ViewHolder holder, int i2) {
            int paintFlags;
            int paintFlags2;
            Intrinsics.checkNotNullParameter(holder, "holder");
            holder.bind(this.myList.get(i2));
            VariantSelectionParams variantSelectionParams = this.this0.variantSelectionParams;
            if (variantSelectionParams == null) {
                Intrinsics.throwUninitializedPropertyAccessException("variantSelectionParams");
                variantSelectionParams = null;
            }
            if (variantSelectionParams.isDivUnit()) {
                holder.getXBinding().edtUserAmount.setInputType(8194);
            } else {
                holder.getXBinding().edtUserAmount.setInputType(2);
            }
            TextView textView = holder.getXBinding().tvVariantName;
            if (this.myList.get(i2).getStriked()) {
                paintFlags = holder.getXBinding().tvVariantName.getPaintFlags() | 16;
            } else {
                paintFlags = holder.getXBinding().tvVariantName.getPaintFlags() & (-17);
            }
            textView.setPaintFlags(paintFlags);
            TextView textView2 = holder.getXBinding().tvCode;
            if (this.myList.get(i2).getStriked()) {
                paintFlags2 = holder.getXBinding().tvCode.getPaintFlags() | 16;
            } else {
                paintFlags2 = holder.getXBinding().tvCode.getPaintFlags() & (-17);
            }
            textView2.setPaintFlags(paintFlags2);
            holder.getXBinding().cbListItem.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.fragment.VariantListDialogFragmentItemAdapterExternalSyntheticLambda0
                public final int f1 = 0;
                public final ViewHolder f2 = null;
                public final VariantListDialogFragment f3 = null;

                public void VariantListDialogFragmentItemAdapterExternalSyntheticLambda0(int i22, ViewHolder holder2, VariantListDialogFragment variantListDialogFragment) {
                    r2 = i22;
                    r3 = holder2;
                    r4 = variantListDialogFragment;
                }
                public void onClick(View view) {
                    ItemAdapter.onBindViewHolderlambda3(ItemAdapter.this, r2, r3, r4, view);
                }
            });
        }

        public static void onBindViewHolderlambda3(ItemAdapter this0, int i2, ViewHolder holder, VariantListDialogFragment this1, View view) {
            VariantHeaderModel variantHeader;
            ObservableField<Integer> selectedItemCount;
            Intrinsics.checkNotNullParameter(this0, "this0");
            Intrinsics.checkNotNullParameter(holder, "holder");
            Intrinsics.checkNotNullParameter(this1, "this1");
            if (Intrinsics.areEqual(this0.myList.get(i2).getChecked().get(), Boolean.TRUE)) {
                this0.myList.get(i2).getAmount().set(BuildConfig.NETSIS_DEMO_PASSWORD);
                holder.getXBinding().edtUserAmount.postDelayed(new Runnable() { // from class: com.proje.mobilesales.features.fragment.VariantListDialogFragmentItemAdapterExternalSyntheticLambda1
                    public final VariantListDialogFragment f1 = null;

                    public void VariantListDialogFragmentItemAdapterExternalSyntheticLambda1(VariantListDialogFragment this12) {
                        r2 = this12;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        ItemAdapter.onBindViewHolderlambda3lambda0(ViewHolder.this, r2);
                    }
                }, 50L);
            } else {
                this0.myList.get(i2).getAmount().set("0");
                holder.getXBinding().edtUserAmount.postDelayed(new Runnable() { // from class: com.proje.mobilesales.features.fragment.VariantListDialogFragmentItemAdapterExternalSyntheticLambda2
                    public final VariantListDialogFragment f1 = null;

                    public void VariantListDialogFragmentItemAdapterExternalSyntheticLambda2(VariantListDialogFragment this12) {
                        r2 = this12;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        ItemAdapter.onBindViewHolderlambda3lambda1(ViewHolder.this, r2);
                    }
                }, 50L);
            }
            FragmentVariantListDialogBinding fragmentVariantListDialogBinding = this12._binding;
            if (fragmentVariantListDialogBinding == null || (variantHeader = fragmentVariantListDialogBinding.getVariantHeader()) == null || (selectedItemCount = variantHeader.getSelectedItemCount()) == null) {
                return;
            }
            List<VariantModel> list = this0.myList;
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                if (Intrinsics.areEqual(((VariantModel) obj).getChecked().get(), Boolean.TRUE)) {
                    arrayList.add(obj);
                }
            }
            selectedItemCount.set(Integer.valueOf(arrayList.size()));
        }

        public static void onBindViewHolderlambda3lambda0(ViewHolder holder, VariantListDialogFragment this0) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            Intrinsics.checkNotNullParameter(this0, "this0");
            holder.getXBinding().edtUserAmount.requestFocus();
            Object systemService = this0.requireContext().getSystemService("input_method");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
            ((InputMethodManager) systemService).showSoftInput(holder.getXBinding().edtUserAmount, 1);
        }

        public static void onBindViewHolderlambda3lambda1(ViewHolder holder, VariantListDialogFragment this0) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            Intrinsics.checkNotNullParameter(this0, "this0");
            holder.getXBinding().edtUserAmount.clearFocus();
            IBinder windowToken = holder.getXBinding().edtUserAmount.getWindowToken();
            Intrinsics.checkNotNullExpressionValue(windowToken, "getWindowToken(...)");
            this0.hideKeyboard(windowToken);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.myList.size();
        }
    }

    /* compiled from: VariantListDialogFragment.kt */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public VariantListDialogFragment newInstance(VariantSelectionParams variantSelectionParams) {
            Intrinsics.checkNotNullParameter(variantSelectionParams, "variantSelectionParams");
            VariantListDialogFragment variantListDialogFragment = new VariantListDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(VariantListDialogFragmentKt.ARG_VARIANT_SELECTION_PARAMS, variantSelectionParams);
            variantListDialogFragment.setArguments(bundle);
            return variantListDialogFragment;
        }
    }
    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }

    private int calculateActionBarHeight() {
        TypedValue typedValue = new TypedValue();
        if (requireActivity().getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics());
        }
        return 0;
    }

    private int calculateStatusBarHeight() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private int getUsableScreenHeight() {
        WindowMetrics currentWindowMetrics;
        WindowInsets windowInsets;
        int navigationBars;
        int displayCutout;
        Insets insetsIgnoringVisibility;
        int i2;
        int i3;
        Rect bounds;
        Object systemService = requireActivity().getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        WindowManager windowManager = (WindowManager) systemService;
        if (Build.VERSION.SDK_INT >= 30) {
            currentWindowMetrics = windowManager.getCurrentWindowMetrics();
            Intrinsics.checkNotNullExpressionValue(currentWindowMetrics, "getCurrentWindowMetrics(...)");
            windowInsets = currentWindowMetrics.getWindowInsets();
            Intrinsics.checkNotNullExpressionValue(windowInsets, "getWindowInsets(...)");
            navigationBars = WindowInsets.Type.navigationBars();
            displayCutout = WindowInsets.Type.displayCutout();
            insetsIgnoringVisibility = windowInsets.getInsetsIgnoringVisibility(navigationBars | displayCutout);
            Intrinsics.checkNotNullExpressionValue(insetsIgnoringVisibility, "getInsetsIgnoringVisibility(...)");
            i2 = insetsIgnoringVisibility.top;
            i3 = insetsIgnoringVisibility.bottom;
            bounds = currentWindowMetrics.getBounds();
            Intrinsics.checkNotNullExpressionValue(bounds, "getBounds(...)");
            return bounds.height() - (i2 + i3);
        }
        Point point = new Point();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        if (defaultDisplay != null) {
            defaultDisplay.getSize(point);
        }
        return point.y;
    }

    public void hideKeyboard(IBinder iBinder) {
        Object systemService = requireContext().getSystemService("input_method");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        ((InputMethodManager) systemService).hideSoftInputFromWindow(iBinder, 0);
    }
}
